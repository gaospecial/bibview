package com.ruoyi.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.http.HttpUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ZoteroApiClient {

    private static final String API_KEY = "xFJCPkRvAZS3SbtZaM0taalD";
    private static final String USER_ID = "12704142";

    public static void main(String[] args) throws IOException {
        System.out.println(getFulltext(USER_ID,"9JPDZK49",API_KEY));
    }

    /**
     * 读取目录
     * **/
    public  static  void  collections(String query ) throws IOException  {
        // 构建 Zotero API 请求 URL
        String apiUrl = "https://api.zotero.org/users/" + USER_ID + "/items?q="+ URLEncoder.encode(query, "UTF-8");
        String apiKeyParam = "?key=" + API_KEY;
        String requestUrl = apiUrl + apiKeyParam;

        // 发送 GET 请求
        HttpURLConnection connection = (HttpURLConnection) new URL(requestUrl).openConnection();
        connection.setRequestMethod("GET");


        // 读取 API 响应
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // 输出 API 响应
        System.out.println("Zotero API Response:");
        System.out.println(response.toString());

        // 关闭连接
        connection.disconnect();
    }



    /**
     * 获取新的内容数组
     * **/
    public static  List<String> getNewContent(String userId,String itemKey,String apiKey) throws IOException {
        String url ="https://api.zotero.org/users/"+userId+"/fulltext?since=3";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer "+apiKey);
        //结果
        String result= HttpUtils.sendGet(url,"",headers);

        // 解析 JSON
        JSONObject jsonObject = JSON.parseObject(result);
        List<String> keys=new ArrayList<>();

        // 提取键
        // 提取键
        for (String key : jsonObject.keySet()) {
            keys.add(key);
        }


        return keys;
    }



    /**
     * 获取简略信息
     * **/
    public  static  JSONObject  getBriefInfo(String userId,String itemKey,String apiKey) throws IOException {
        String url ="https://api.zotero.org/users/"+userId+"/items/"+itemKey+"?v=3";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer "+apiKey);
        String result=  HttpUtils.sendGet(url,"",headers);
        //请求成功后要去获取data下的parentItem参数，用这个参数去请求详细内容

        JSONObject jsonObj = JSON.parseObject(result); // 解析 JSON 字符串
        JSONObject data = jsonObj.getJSONObject("data"); // 获取data部分
        String parentItem = data.getString("parentItem"); // 从data中提取parentItem参数

        System.out.println("Parent Item: " + parentItem);



        return  getDetailInfo(userId,parentItem,apiKey);

    }


    /**
     * 获取详细信息
     * **/
    public  static  JSONObject  getDetailInfo(String userId,String itemKey,String apiKey) throws IOException {
        String url ="https://api.zotero.org/users/"+userId+"/items/"+itemKey+"?v=3";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer "+apiKey);
        String result=  HttpUtils.sendGet(url,"",headers);
        JSONObject jsonObj = JSON.parseObject(result); // 解析 JSON 字符串
        JSONObject data = jsonObj.getJSONObject("data"); // 获取data部分
        System.out.println(data);
        return data;
    }



    /**
     * 获取全文内容
     * **/
    public  static  String  getFulltext(String userId,String itemKey,String apiKey){
       String url ="https://api.zotero.org/users/"+userId+"/items/"+itemKey+"/fulltext";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer "+apiKey);
        String result=  HttpUtils.sendGet(url,"",headers);
        JSONObject jsonObj = JSON.parseObject(result); // 解析 JSON 字符串
        String content = jsonObj.getString("content"); // 获取data部分
        return content;
    }

}
