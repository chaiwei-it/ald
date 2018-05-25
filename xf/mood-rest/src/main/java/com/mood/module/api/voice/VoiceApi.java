package com.mood.module.api.voice;

import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;

/**
 * 模块
 *
 * @author chaiwei
 * @time 2018-05-25 17:39
 */
@RestController
@RequestMapping("/api/{version}/voice")
public class VoiceApi {
    @GetMapping("")
    public String getApps(){
        String fileString = "D:/test.wav";
        return aiuiWebApi(fileString);
    }

    public static String aiuiWebApi(String fileString){
        String appid = "5b077766";
        String appKey = "387338a060c5e80893deb3775d2b0692";
        String curTime = String.valueOf(System.currentTimeMillis()/1000);

        String url = "http://api.xfyun.cn/v1/service/v1/iat";

        String xParam = "{\"engine_type\":\"sms16k\",\"aue\":\"raw\"}";
        Base64 base64 = new Base64();
        String param = null;
        try {
            param = base64.encodeToString(xParam.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


//        String param = Base64.getEncoder().encodeToString(xParam.getBytes("UTF-8"));
//        param = "eyJlbmdpbmVfdHlwZSI6ICJzbXMxNmsiLCJhdWUiOiAicmF3In0=";
        //文件转base64
        byte[] b = new byte[0];
        try {
            b = Files.readAllBytes(Paths.get(fileString));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String body = base64.encodeToString(b);
        String body_data = null;
        try {
            body_data = "audio=" + URLEncoder.encode(body,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        String body = Base64.getEncoder().encodeToString(IOUtils.toByteArray(fileInputStream));
        String checkSum = EncoderByMd5(appKey + curTime + param);

        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringEntity entity = new StringEntity(body_data,"utf-8");
        entity.setContentType("application/x-www-form-urlencoded");
        httpPost.setEntity(entity);
        httpPost.setHeader("X-Appid", appid);
        httpPost.setHeader("X-CurTime", curTime);
        httpPost.setHeader("X-Param", param);
        httpPost.setHeader("X-CheckSum", checkSum);

        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response.getStatusLine().getStatusCode() == 200){
            HttpEntity responseEntity = response.getEntity();
            String resJson = null;
            try {
                resJson = EntityUtils.toString(responseEntity,"utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = JSONObject.fromObject(resJson);
            System.out.println(jsonObject);
            String code = jsonObject.getString("code");
            if(code.equals("0")) { // 成功
                String dataJson = jsonObject.getString("data");
//                JSONObject dataObject = JSONObject.fromObject(dataJson);
//                String result = dataObject.getString("result");
                return dataJson;
            }
            else { // 失败
                String desc = jsonObject.getString("desc");
//                System.out.println("讯飞语音接口调用失败："+desc);
            }
        }
        return "调用讯飞语音接口失败";
    }

    public static String EncoderByMd5(String str){
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            String md5=new BigInteger(1, md.digest()).toString(16);
            //BigInteger会把0省略掉，需补全至32位
            return fillMD5(md5);
        } catch (Exception e) {
            throw new RuntimeException("MD5加密错误:"+e.getMessage(),e);
        }
    }

    public static String fillMD5(String md5){
        return md5.length()==32?md5:fillMD5("0"+md5);
    }
}
