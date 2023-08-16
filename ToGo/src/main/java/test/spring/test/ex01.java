package test.spring.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import test.spring.component.song.SampleListDTO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ex01 {
    public static void main(String[] args) {

        StringBuilder url1 = new StringBuilder();
        url1.append("https://apis.data.go.kr/B551011/KorService1/searchKeyword1");
        String key = "tueSYVJWEmvANaRohYnSMi9HK2YStViwfRtj6%2Fiqv4HaQZqV2Ql0FLqX2WA9PKXFgkyghnvdJwJzK5kEvmyhKw%3D%3D";
        Map<String,Integer> contentTypeId = new HashMap<>() {{
            put("관광지",12); put("문화시설",14); put("축제공연행사",15); put("레포츠",28); put("숙박",32); put("쇼핑",38);put("음식점",39);
        }}; // 조회 데이터 타입
        String os = "WIN"; //OS타입
        String MobileApp = "AppTest"; //서비스명
        int num = 5; // 데이터 조회 갯수
        String keyword="서울";
        url1.append("?").append(URLEncoder.encode("serviceKey", StandardCharsets.UTF_8)).append("=").append(key);
        url1.append("&").append(URLEncoder.encode("numOfRows", StandardCharsets.UTF_8)).append("=").append(num);
        url1.append("&").append(URLEncoder.encode("MobileOS", StandardCharsets.UTF_8)).append("=").append(os);
        url1.append("&").append(URLEncoder.encode("MobileApp", StandardCharsets.UTF_8)).append("=").append(MobileApp);
        url1.append("&").append(URLEncoder.encode("contentTypeId", StandardCharsets.UTF_8)).append("=").append(contentTypeId.get("관광지"));
        url1.append("&").append(URLEncoder.encode("_type", StandardCharsets.UTF_8)).append("=").append("json");
        url1.append("&").append(URLEncoder.encode("keyword", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode(keyword));
        List<SampleListDTO> result = new ArrayList<>();
        System.out.println(url1);
        try {
            URL mainUrl = new URL(url1.toString());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mainUrl.openStream()));
            String list = bufferedReader.readLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(list);
            JSONObject response = (JSONObject) jsonObject.get("response");
            JSONObject body = (JSONObject) response.get("body");
            JSONObject items = (JSONObject) body.get("items");
            System.out.println(items.getClass());
            JSONArray item = (JSONArray) items.get("item");
            for (Object o : item) {
                SampleListDTO dto = new SampleListDTO();
                JSONObject mainList = (JSONObject) o;
                dto.setAdress(mainList.get("addr1").toString());
                dto.setName(mainList.get("title").toString());
                dto.setLon(Double.parseDouble(mainList.get("mapx").toString()));
                dto.setLat(Double.parseDouble(mainList.get("mapy").toString()));
                result.add(dto);
            }
//
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
