package test.spring.test;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ex01 {
    public static void main(String[] args) {
        String url1 = "https://gist.githubusercontent.com/hmmhmmhm/b3a950e84f865f8abbd00fa460aa4613/raw/84c0bbdcfc0ef96f6841bd5670835beb4756dae6/sigungu.json";
        String url2= "https://apis.data.go.kr/B551011/KorService1/searchFestival1?serviceKey=tueSYVJWEmvANaRohYnSMi9HK2YStViwfRtj6%2Fiqv4HaQZqV2Ql0FLqX2WA9PKXFgkyghnvdJwJzK5kEvmyhKw%3D%3D&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&eventStartDate=20170901";
        try {
            URL url = new URL(url2);
            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            String result = bf.readLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
//            System.out.println(jsonObject);
            JSONObject response = (JSONObject)jsonObject.get("response");
            JSONObject body = (JSONObject)response.get("body");
            JSONObject items = (JSONObject)body.get("items");
            JSONArray item = (JSONArray)items.get("item");
            for(int i=0;i<item.size();i++){
                JSONObject itemResult=(JSONObject)item.get(i);
                System.out.println(itemResult.get("title"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }
}
