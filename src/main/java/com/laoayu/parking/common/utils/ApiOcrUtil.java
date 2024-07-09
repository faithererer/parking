package com.laoayu.parking.common.utils;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.baidu.aip.ocr.AipOcr;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: LaoAyu
 * @date: 2023/04/02
 **/
@Component
@Configuration
public class ApiOcrUtil {
    //设置APPID/AK/SK


    @Value("${baidu.id}")
    private static String APP_ID;

    @Value("${baidu.api-key}")
    private static String API_KEY;

    @Value("${baidu.secret-key}")
    private static String SECRET_KEY;
    @Value("${pic_path}")
    private static String PATH;
    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();


    public static void main(String[] args) {

        // 初始化一个AipOcr
        AipOcr aipOcr = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        //设置本地图片地址
//        String path = "D:\\项目模板\\car-OCR-master\\car-OCR-master\\res\\image\\baidu_image\\test1.jpg";
//        String path = PATH;
//        //通用文字识别
//        licencePlateNumber(aipOcr,path);

    }

    /**
     * 测试静态图片
     * 车牌号识别
     */
    public static String licencePlateNumber(AipOcr aipOcr,String path){
        try {
            // 传入可选参数调用接口
            HashMap<String, String> options = new HashMap<String, String>();
            /**
             * 是否检测多张车牌，默认为false
             * 当置为true的时候可以对一张图片内的多张车牌进行识别
             */
            options.put("multi_detect", "false");//是否检测多张图片，默认false
            //本地图片识别，返回JSON对象
            JSONObject jsonObject = aipOcr.plateLicense(path, options);
            //获取需要的信息
            JSONObject result = jsonObject.getJSONObject("words_result");
            System.out.print(result+"\n");
            System.out.println("车牌颜色:"+result.getString("color")+"\n"+"车牌号:"+result.getString("number"));
            Object number = result.get("number");
            return number.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
    /**
     * 参数为本地图片路径
     */
    public Map<String, Object> plateLicense2(String image) {
        try {
            AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
            client.setConnectionTimeoutInMillis(2000);
            client.setSocketTimeoutInMillis(60000);
            HashMap<String, String> options = new HashMap<>();
            /**
             * 是否检测多张车牌，默认为false
             * 当置为true的时候可以对一张图片内的多张车牌进行识别
             */
            options.put("multi_detect", "true");
            JSONObject res = client.plateLicense(image, options);
            System.out.println("扫描结果：" + res.toString());
            Object result = res.get("words_result");
            JSONArray array = JSON.parseArray(result.toString());
            com.alibaba.fastjson2.JSONObject object = JSON.parseObject(array.get(0).toString());
            Object number = object.get("number");
            Object color = object.get("color");
            Map<String, Object> map = new HashMap<>();
            map.put("number",number);
            map.put("color",color);
            System.out.println("map中的值" + map);

            return map;
            //  return map.toString();
        }catch (Exception e){
            e.printStackTrace();
            //  return "";
            return null;
        }
    }

    public Map<String, Object> plateLicense(String imagePath) {
        try {
            String base64Image = getFileContentAsBase64(imagePath, true);
            String accessToken = getAccessToken();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "image=" + base64Image);
            Request request = new Request.Builder()
                    .url("https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate?access_token=" + accessToken)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Accept", "application/json")
                    .build();
            Response response = HTTP_CLIENT.newCall(request).execute();
            String responseBody = response.body().string();
            System.out.println("扫描结果：" + responseBody);

            JSONObject res = new JSONObject(responseBody);
            JSONObject wordsResult = res.getJSONObject("words_result");
            String number = wordsResult.getString("number");
            String color = wordsResult.getString("color");
            Map<String, Object> map = new HashMap<>();
            map.put("number", number);
            map.put("color", color);
            System.out.println("map中的值" + map);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static String getFileContentAsBase64(String path, boolean urlEncode) throws IOException {
        byte[] b = Files.readAllBytes(Paths.get(path));
        String base64 = Base64.getEncoder().encodeToString(b);
        if (urlEncode) {
            base64 = URLEncoder.encode(base64, "utf-8");
        }
        return base64;
    }

    static String getAccessToken() throws IOException {

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
//                .url(String.format("https://aip.baidubce.com/oauth/2.0/token?client_id=%s&client_secret=%s&grant_type=client_credentials",API_KEY,SECRET_KEY))

                .url("https://aip.baidubce.com/oauth/2.0/token?client_id=gXReeTOVNtfWta6zFLgTw96m&client_secret=zD73l2OmPrAUXeo4Xto96x1XlmTsjryw&grant_type=client_credentials")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();


        HashMap bean = JSONUtil.toBean(response.body().string(), HashMap.class);
        System.out.println("yy");
        System.out.println(bean);
        return (String) bean.get("access_token");
    }



}
