package board.NaverAPI;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import board.board.shop.Shop;
import lombok.extern.log4j.Log4j;

@Service
public class ApiExamSearchBlog {


    public List<Shop> shopSearch(String keywords) {
        String clientId = "A04lWAJHpKNU1VtuhUND"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "_4CeafjZ9F"; //애플리케이션 클라이언트 시크릿값"

        //RestTemplate restTemplate;
        String text = null;
        try {
            text = URLEncoder.encode(keywords, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

       
        String apiURL = "https://openapi.naver.com/v1/search/shop?query=" + text;    // json 결과


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);


        System.out.println(responseBody);
        
        return parseData(responseBody);
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
    
    private static List<Shop> parseData(String responseBody) {
        Shop sh = new Shop();
        List<Shop> shopList = new ArrayList<Shop>();
        
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(responseBody.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            
            for(int i=0 ; i< jsonArray.length();i++) {
            JSONObject shopArray = (JSONObject)jsonArray.get(i);
            sh.setTitle(shopArray.get("title").toString());
            sh.setLink(shopArray.get("link").toString());
            sh.setImage(shopArray.get("image").toString());
            sh.setLprice(shopArray.getInt("lprice"));
            
            shopList.add(sh);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
		return shopList;

    }
}