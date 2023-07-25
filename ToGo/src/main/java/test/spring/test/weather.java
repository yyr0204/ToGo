package test.spring.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.*;

public class weather {
    public static void main(String[] args) {
        double start = System.nanoTime();
        String wertherURL = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey=tueSYVJWEmvANaRohYnSMi9HK2YStViwfRtj6%2Fiqv4HaQZqV2Ql0FLqX2WA9PKXFgkyghnvdJwJzK5kEvmyhKw%3D%3D&pageNo=1&numOfRows=1000&dataType=json&base_date=20230717&base_time=0500&nx=55&ny=127";
        try {
            URL mainUrl = new URL(wertherURL);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mainUrl.openStream()));
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(bufferedReader);
            JSONObject response = (JSONObject) jsonObject.get("response");
            JSONObject body = (JSONObject) response.get("body");
            JSONObject items = (JSONObject) body.get("items");
            JSONArray item = (JSONArray) items.get("item");
            Map<String, String> weatherInfoList = new HashMap<>();
            String fcst =" ";
            for (Object ob : item) {
                JSONObject weatherInfo = (JSONObject) ob;
                if(!Objects.equals(fcst, " ") &&!fcst.equals(weatherInfo.get("fcstTime").toString())){
                    System.out.println(fcst.substring(0,2)+"시 기준 날씨");
                    System.out.println(weatherInfoList.get("하늘"));
                    if(!weatherInfoList.get("강수확률").equals("0")) {
                        System.out.println("강수확률 "+weatherInfoList.get("강수확률") + "%");
                        System.out.println("강수량 "+weatherInfoList.get("강수량") + "mm");
                    };
                    System.out.println("현재기온 "+weatherInfoList.get("현재기온")+"도");
                    System.out.println("최고기온 "+weatherInfoList.get("최고기온")+"도");
                    System.out.println("최저기온 "+weatherInfoList.get("최저기온")+"도");
                }
                fcst =weatherInfo.get("fcstTime").toString();

                if (weatherInfo.get("category").equals("SKY")) {
                    String sky_info = weatherInfo.get("fcstValue").toString();
                    String sky;
                    if (sky_info.equals("1")) {
                        sky = "맑음";
                    } else if (sky_info.equals("3")) {
                        sky = "구름많음";
                    } else {
                        sky = "흐림";
                    }
                    weatherInfoList.put("하늘", sky);
                }
                if (weatherInfo.get("category").equals("POP")) {
                    weatherInfoList.put("강수확률", weatherInfo.get("fcstValue").toString());
                }
                if (weatherInfo.get("category").equals("PCP")) {
                    weatherInfoList.put("강수량", weatherInfo.get("fcstValue").toString());
                }
                if (weatherInfo.get("category").equals("TMP")) {
                    weatherInfoList.put("현재기온", weatherInfo.get("fcstValue").toString());
                }
                if (weatherInfo.get("category").equals("TMN")) {
                    weatherInfoList.put("최저기온", weatherInfo.get("fcstValue").toString());
                }
                if (weatherInfo.get("category").equals("TMN")) {
                    weatherInfoList.put("최고기온", weatherInfo.get("fcstValue").toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        double end = System.nanoTime();
        System.out.println("실행시간:"+(end-start)*1000000000);
    }
}
