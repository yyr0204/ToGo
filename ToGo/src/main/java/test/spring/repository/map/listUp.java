package test.spring.repository.map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class listUp {
    public static Map<Integer, List<SampleListDTO>> mainList(String type, int day) {
        StringBuilder url1 = new StringBuilder();
        url1.append("https://apis.data.go.kr/B551011/KorService1/searchKeyword1");
        String key = "tueSYVJWEmvANaRohYnSMi9HK2YStViwfRtj6%2Fiqv4HaQZqV2Ql0FLqX2WA9PKXFgkyghnvdJwJzK5kEvmyhKw%3D%3D";
        Map<String, Integer> contentTypeId = new HashMap<>() {{
            put("관광지", 12);
            put("문화시설", 14);
            put("축제공연행사", 15);
            put("레포츠", 28);
            put("숙박", 32);
            put("쇼핑", 38);
            put("음식점", 39);
        }}; // 조회 데이터 타입
        String os = "WIN"; //OS타입
        String MobileApp = "AppTest"; //서비스명
        int num = 75; // 데이터 조회 갯수
        String keyword = "서울";
        url1.append("?").append(URLEncoder.encode("serviceKey", StandardCharsets.UTF_8)).append("=").append(key);
        url1.append("&").append(URLEncoder.encode("numOfRows", StandardCharsets.UTF_8)).append("=").append(num);
        url1.append("&").append(URLEncoder.encode("MobileOS", StandardCharsets.UTF_8)).append("=").append(os);
        url1.append("&").append(URLEncoder.encode("MobileApp", StandardCharsets.UTF_8)).append("=").append(MobileApp);
        url1.append("&").append(URLEncoder.encode("contentTypeId", StandardCharsets.UTF_8)).append("=").append(contentTypeId.get(type));
        url1.append("&").append(URLEncoder.encode("_type", StandardCharsets.UTF_8)).append("=").append("json");
        url1.append("&").append(URLEncoder.encode("keyword", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode(keyword));
        Map<Integer, List<SampleListDTO>> allList = new HashMap<>();
        List<SampleListDTO> dayList = new ArrayList<>();
        try {
            URL mainUrl = new URL(url1.toString());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mainUrl.openStream()));
            String list = bufferedReader.readLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(list);
            JSONObject response = (JSONObject) jsonObject.get("response");
            JSONObject body = (JSONObject) response.get("body");
            JSONObject items = (JSONObject) body.get("items");
            JSONArray item = (JSONArray) items.get("item");
            for (int num1 = 0; num1 < day; num1++) {
                int listCount = (int) (Math.random() * 4 + 2);
                dayList = new ArrayList<>();
                for (int n = 0; n < listCount; n++) {
                    SampleListDTO dto = new SampleListDTO();
                    int random = (int) (Math.random() * 25);
                    JSONObject mainList = (JSONObject) item.get(random);
                    dto.setAdress(mainList.get("addr1").toString());
                    dto.setName(mainList.get("title").toString());
                    dto.setLon(Double.parseDouble(mainList.get("mapx").toString()));
                    dto.setLat(Double.parseDouble(mainList.get("mapy").toString()));
                    if (dayList.contains(dto)) {
                        num1--;
                        continue;
                    }
                    dayList.add(dto);
                }

                ArrayList<SampleListDTO> mainArrayList = new ArrayList<>(dayList);
                ArrayList<ArrayList<SampleListDTO>> allPermutations = PermutationDAO.permutation(mainArrayList);
                List<HashMap<String, Object>> result = new ArrayList<>();
                // allPermutations list contains all permutations of the main list
                for (ArrayList<SampleListDTO> permutation : allPermutations) {
                    double sum = 0;
                    for (int i = 0; i < (permutation.size() - 1); i++) {
                        SampleListDTO sample1 = permutation.get(i);
                        SampleListDTO sample2 = permutation.get(i + 1);

                        double[] a = {sample1.getLat(), sample1.getLon()};
                        double[] b = {sample2.getLat(), sample2.getLon()};

                        double dLat = Math.toRadians(b[0] - a[0]);
                        double dLon = Math.toRadians(b[1] - a[1]);

                        double x = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(a[0])) * Math.cos(Math.toRadians(b[0])) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
                        double y = 2 * Math.atan2(Math.sqrt(x), Math.sqrt(1 - x));
                        double z = 6371 * y; // Distance in m

                        sum = sum + z;
                    }
                    HashMap<String, Object> max = new HashMap<>();
                    max.put("sum", sum);
                    max.put("permutation", permutation);
                    result.add(max);
                }

                result.sort(Comparator.comparingDouble(map -> (double) map.get("sum")));

                HashMap map = result.get(0);
                dayList = (List<SampleListDTO>) map.get("permutation");
                System.out.println(num1 + "일차:" + dayList);
                System.out.println(" ");
                allList.put(num1, dayList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allList;
    }

    public static List<SampleListDTO> list_up(String area,String type) {
        StringBuilder url1 = new StringBuilder();
        url1.append("https://apis.data.go.kr/B551011/KorService1/searchKeyword1");
        String key = "tueSYVJWEmvANaRohYnSMi9HK2YStViwfRtj6%2Fiqv4HaQZqV2Ql0FLqX2WA9PKXFgkyghnvdJwJzK5kEvmyhKw%3D%3D";
        Map<String, Integer> contentTypeId = new HashMap<>() {{
            put("관광지", 12);
            put("문화시설", 14);
            put("축제공연행사", 15);
            put("레포츠", 28);
            put("숙박", 32);
            put("쇼핑", 38);
            put("음식점", 39);
        }}; // 조회 데이터 타입
        String os = "WIN"; //OS타입
        String MobileApp = "AppTest"; //서비스명
        int num = 500; // 데이터 조회 갯수
        String keyword = area;
        url1.append("?").append(URLEncoder.encode("serviceKey", StandardCharsets.UTF_8)).append("=").append(key);
        url1.append("&").append(URLEncoder.encode("numOfRows", StandardCharsets.UTF_8)).append("=").append(num);
        url1.append("&").append(URLEncoder.encode("MobileOS", StandardCharsets.UTF_8)).append("=").append(os);
        url1.append("&").append(URLEncoder.encode("MobileApp", StandardCharsets.UTF_8)).append("=").append(MobileApp);
        url1.append("&").append(URLEncoder.encode("contentTypeId", StandardCharsets.UTF_8)).append("=").append(contentTypeId.get(type));
        url1.append("&").append(URLEncoder.encode("_type", StandardCharsets.UTF_8)).append("=").append("json");
        url1.append("&").append(URLEncoder.encode("keyword", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode(keyword));
        Map<Integer, List<SampleListDTO>> allList = new HashMap<>();
        List<SampleListDTO> dayList = new ArrayList<>();
        try {
            URL mainUrl = new URL(url1.toString());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mainUrl.openStream()));
            String list = bufferedReader.readLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(list);
            JSONObject response = (JSONObject) jsonObject.get("response");
            JSONObject body = (JSONObject) response.get("body");
            JSONObject items = (JSONObject) body.get("items");
            JSONArray item = (JSONArray) items.get("item");
            for (int n = 0; n < 50; n++) {
                SampleListDTO dto = new SampleListDTO();
                int random = (int) (Math.random() * item.size());
                JSONObject mainList = (JSONObject) item.get(random);
                dto.setAdress(mainList.get("addr1").toString());
                dto.setName(mainList.get("title").toString());
                dto.setLon(Double.parseDouble(mainList.get("mapx").toString()));
                dto.setLat(Double.parseDouble(mainList.get("mapy").toString()));
                if(dayList.contains(dto)) {
                    n--;
                    continue;
                }
                dayList.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dayList;
    }
}
