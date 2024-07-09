package com.sdut.parking.system.controller;

import com.sdut.parking.common.vo.Result;
import com.sdut.parking.system.entity.User;
import com.sdut.parking.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class GitHubAuthController {

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;
    @Autowired
    private IUserService userService;

    @PostMapping("/github")
    public Result<Map<String, Object>> githubLogin(@RequestBody Map<String, String> request) {
        String code = request.get("code");
        String state = request.get("state");

        if (code == null || code.isEmpty()) {
            return Result.fail("Invalid code");
        }

        // Configure proxy
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(proxy);

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("code", code);
        params.put("state", state);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<Map> response;
        try {
            response = restTemplate.exchange(
                    "https://github.com/login/oauth/access_token",
                    HttpMethod.POST,
                    entity,
                    Map.class
            );
        } catch (HttpClientErrorException e) {
            return Result.fail("Failed to retrieve access token: " + e.getResponseBodyAsString());
        }

        Map<String, String> body = response.getBody();
        String accessToken = body.get("access_token");

        if (accessToken == null) {
            return Result.fail("Failed to retrieve access token");
        }

        // 使用访问令牌获取用户信息
        headers.set("Authorization", "token " + accessToken);
        entity = new HttpEntity<>(headers);

        ResponseEntity<Map> userResponse;
        try {
            userResponse = restTemplate.exchange(
                    "https://api.github.com/user",
                    HttpMethod.GET,
                    entity,
                    Map.class
            );
        } catch (HttpClientErrorException e) {
            return Result.fail("Failed to retrieve user information: " + e.getResponseBodyAsString());
        }

        Map<String, Object> user = userResponse.getBody();
        Object login = user.get("login");
        Object avatarUrl = user.get("avatar_url");
        Object id = user.get("id");

        User user1 = new User();
        user1.setRoleIdList(Collections.singletonList(2L));
        user1.setGithubId(String.valueOf(id));
        user1.setNickName((String) login);
        user1.setAvatar((String) avatarUrl);
        user1.setUserName((String) login);
        // 处理用户信息，例如保存到数据库或创建 JWT
        Map<String, Object> data = userService.loginByGithub(user1);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail("登录失败");
    }
}
