package test.spring.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import test.spring.component.song.SampleListDTO;
import test.spring.repository.song.Haversine;
import test.spring.repository.song.PermutationDAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ex02 {
    public static void main(String[] args) {
        plan2();
    }

    public static String plan2() {

        boolean home = true;
        mainLoop:
        while (home) {
            System.out.println("loop");
            String area = "서울특별시";
            List plan = new ArrayList();
            plan.add("7/10");
            plan.add("7/11");
            plan.add("7/12");
            plan.add("7/13");
            int day = plan.size();
            System.out.println("start");
            List<SampleListDTO> list = new ArrayList<>();
            list = mainList("관광지");

            List<SampleListDTO> main = new ArrayList<>();
            int mainNum = day * 2;
            SampleListDTO dto;
            for (int i = 0; main.size() < mainNum; i++) {
                dto = list.get((int) (Math.random() * list.size()));
                if (!main.contains(dto)) {
                    main.add(dto);
                }
            }
            System.out.println("main="+main+"mainSize="+main.size());
            // 동선최적화
            ArrayList<ArrayList<SampleListDTO>> allPermutations = PermutationDAO.permutation((ArrayList<SampleListDTO>) main);
            List<HashMap<String, Object>> result = new ArrayList<>();
            System.out.println("allPermutations="+allPermutations.size());
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
            main = (List<SampleListDTO>) map.get("permutation");

//            List<List<SampleListDTO>> daySub = new ArrayList<>();
            Map<String,List<SampleListDTO>>daySub = new HashMap<>();
            // 동선에 맞는 서브일정 추가
            for (int i = 0; i < day; i++) {
                int i2 = 2 * i;
                double lat1;
                double lon1;
                double lat2;
                double lon2;
                double lat3;
                double lon3;
                if (i2 < 6) {
                    lat1 = main.get(i2).getLat();
                    lon1 = main.get(i2).getLon();
                    lat2 = main.get(i2 + 1).getLat();
                    lon2 = main.get(i2 + 1).getLon();
                    lat3 = main.get(i2 + 2).getLat();
                    lon3 = main.get(i2 + 2).getLon();
                } else {
                    lat1 = main.get(i2).getLat();
                    lon1 = main.get(i2).getLon();
                    lat2 = main.get(i2 + 1).getLat();
                    lon2 = main.get(i2 + 1).getLon();
                    lat3 = main.get(i2).getLat();
                    lon3 = main.get(i2).getLon();
                }

                List<Double> LatLon1 = Haversine.LatLon(lat1, lon1, lat2, lon2);

                List<SampleListDTO> subList;

                // 아침,점심,저녁
                System.out.println("서브 시작");
                List<SampleListDTO> breakfast = meal("음식점", LatLon1);
//                List<SampleListDTO> abendessen = new ArrayList<>();
//		        abendessen = service.abendessen(area, (double)LatLon2.get(0), (double)LatLon2.get(1), (double)LatLon2.get(2), (double)LatLon2.get(3));
                subList = subList("쇼핑", LatLon1);
                List<SampleListDTO> sub = new ArrayList<>();


                Map<String,List<SampleListDTO>> subAll = new HashMap<>();
                subAll.put("관광지",subList);
                subAll.put("음식점",breakfast);
                List<SampleListDTO> spotList = subAll.get("관광지");
                List<SampleListDTO> mealList = subAll.get("음식점");
                boolean result2;
                do{
                    result2=false;
                    int random = (int)(Math.random()*spotList.size()*1.1);
                    dto=spotList.get(random);
                }while(result2);
                do{
                    result2=false;
                    int random = (int)(Math.random()*mealList.size()*1.1);
                    List<SampleListDTO> ex01 = daySub.get("음식점");
                    dto=ex01.get(random);
                    for(SampleListDTO dto2 : ex01){
                        if(dto2.equals(dto)){
                            result2 = true;
                            break;
                        }
                    }
                }while(result2);
//                subAll.add(abendessen);
                // 중복방지
//                for (int y = 0; y < subAll.size(); y++) {
//                    for (int x = 0; sub.size() < (y + 1); x++) {
//                        dto = arr.get((int) (Math.random() * arr.size()));
//                        int num = 0;
//                        if (daySub != null) {
//                            for(List<SampleListDTO> dto2:daySub.values()){
//                                if(dto2.contains(dto)){
//                                    num++;
//                                }
//                            }
//                        }
//                        if (num == 0 && !main.contains(dto)) {
//                            sub.add(dto);
//                        }
//                    }
//                }
                // 중복방지S
//                for (int x = 0; sub.size() < 1; x++) {
//                    dto = subList.get((int) (Math.random() * subList.size()));
//                    int num = 0;
//                    /////////////중복체크//////////////
//                    if (daySub != null) {
//                        for (List arr : daySub.values()) {
//                            if (arr.contains(dto)) {
//                                num++;
//                            }
//                            int number = 0;
//                            for (int f = 0; f < subList.size(); f++) {
//                                SampleListDTO test = subList.get(f);
//                                if (!arr.contains(test) && !main.contains(test)) {
//                                    number++;
//                                }
//                            }
//                        }
//                    }
//                    //////////////////////////////////
//                    System.out.println("sub_add");
//                    if (num == 0 && !main.contains(dto)) {
//                        sub.add(dto);
//                    }
//                    if (x > 10) {
//                        continue mainLoop;
//                    }
//                }
            }

            // finalList에 main이랑 서브일정 합치기
            List dayList = new ArrayList();
            int x = 0;
            System.out.println("합치기");
            System.out.println("main="+main);
            System.out.println("daysub="+daySub);
            for (int i = 0; i < main.size() / 2; i++) {
                List<SampleListDTO> sample = new ArrayList<>();
                List<SampleListDTO> sub = daySub.get(i);
                sample.add(sub.get(0));        // 아침식사
                sample.add(main.get(x));    // main일정
                sample.add(sub.get(1));        // 점심식사
                sample.add(sub.get(2));        // 서브일정
                sample.add(main.get(x + 1));    // main일정
                //		sample.add(sub.get(3));		// 저녁식사

                dayList.add(sample);
                x = x + 2;
            }

            List finalList = new ArrayList();
            for (int i = 0; i < dayList.size(); i++) {
                finalList.addAll((List) dayList.get(i));
            }
            home = false;
            System.out.println("end");
        }
        return "/map/testMap";
    }

    public static List<SampleListDTO> mainList(String type) {
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
        int num = 200; // 데이터 조회 갯수
        String keyword = "서울";
        url1.append("?").append(URLEncoder.encode("serviceKey", StandardCharsets.UTF_8)).append("=").append(key);
        url1.append("&").append(URLEncoder.encode("numOfRows", StandardCharsets.UTF_8)).append("=").append(num);
        url1.append("&").append(URLEncoder.encode("MobileOS", StandardCharsets.UTF_8)).append("=").append(os);
        url1.append("&").append(URLEncoder.encode("MobileApp", StandardCharsets.UTF_8)).append("=").append(MobileApp);
        url1.append("&").append(URLEncoder.encode("contentTypeId", StandardCharsets.UTF_8)).append("=").append(contentTypeId.get(type));
        url1.append("&").append(URLEncoder.encode("_type", StandardCharsets.UTF_8)).append("=").append("json");
        url1.append("&").append(URLEncoder.encode("keyword", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode(keyword));
        List<SampleListDTO> result = new ArrayList<>();
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
            for (Object o : item) {
                SampleListDTO dto = new SampleListDTO();
                JSONObject mainList = (JSONObject) o;
                dto.setAdress(mainList.get("addr1").toString());
                dto.setName(mainList.get("title").toString());
                dto.setLon(Double.parseDouble(mainList.get("mapx").toString()));
                dto.setLat(Double.parseDouble(mainList.get("mapy").toString()));
                dto.setCategory(type);
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<SampleListDTO> subList(String type, List<Double> latlng) {
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
        int num = 200; // 데이터 조회 갯수
        String keyword = "서울";
        url1.append("?").append(URLEncoder.encode("serviceKey", StandardCharsets.UTF_8)).append("=").append(key);
        url1.append("&").append(URLEncoder.encode("numOfRows", StandardCharsets.UTF_8)).append("=").append(num);
        url1.append("&").append(URLEncoder.encode("MobileOS", StandardCharsets.UTF_8)).append("=").append(os);
        url1.append("&").append(URLEncoder.encode("MobileApp", StandardCharsets.UTF_8)).append("=").append(MobileApp);
        url1.append("&").append(URLEncoder.encode("contentTypeId", StandardCharsets.UTF_8)).append("=").append(contentTypeId.get(type));
        url1.append("&").append(URLEncoder.encode("_type", StandardCharsets.UTF_8)).append("=").append("json");
        url1.append("&").append(URLEncoder.encode("keyword", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode(keyword));
        List<SampleListDTO> result = new ArrayList<>();
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
            Double minLat = latlng.get(0);
            Double maxLat = latlng.get(1);
            Double minLng = latlng.get(2);
            Double maxLng = latlng.get(3);
            for (Object o : item) {
                SampleListDTO dto = new SampleListDTO();
                JSONObject mainList = (JSONObject) o;
                double lat = Double.parseDouble(mainList.get("mapy").toString());
                double lng = Double.parseDouble(mainList.get("mapx").toString());
                if (lat > minLat && lat < maxLat && lng > minLng && lng < maxLng) {
                    dto.setAdress(mainList.get("addr1").toString());
                    dto.setName(mainList.get("title").toString());
                    dto.setLon(Double.parseDouble(mainList.get("mapx").toString()));
                    dto.setLat(Double.parseDouble(mainList.get("mapy").toString()));
                    dto.setCategory(type);
                    result.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<SampleListDTO> meal(String type, List<Double> latlng) {
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
        int num = 200; // 데이터 조회 갯수
        String keyword = "서울";
        url1.append("?").append(URLEncoder.encode("serviceKey", StandardCharsets.UTF_8)).append("=").append(key);
        url1.append("&").append(URLEncoder.encode("numOfRows", StandardCharsets.UTF_8)).append("=").append(num);
        url1.append("&").append(URLEncoder.encode("MobileOS", StandardCharsets.UTF_8)).append("=").append(os);
        url1.append("&").append(URLEncoder.encode("MobileApp", StandardCharsets.UTF_8)).append("=").append(MobileApp);
        url1.append("&").append(URLEncoder.encode("contentTypeId", StandardCharsets.UTF_8)).append("=").append(contentTypeId.get(type));
        url1.append("&").append(URLEncoder.encode("_type", StandardCharsets.UTF_8)).append("=").append("json");
        url1.append("&").append(URLEncoder.encode("keyword", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode(keyword));
        List<SampleListDTO> result = new ArrayList<>();
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
            Double minLat = latlng.get(0);
            Double maxLat = latlng.get(1);
            Double minLng = latlng.get(2);
            Double maxLng = latlng.get(3);
            for (Object o : item) {
                SampleListDTO dto = new SampleListDTO();
                JSONObject mainList = (JSONObject) o;
                double lat = Double.parseDouble(mainList.get("mapy").toString());
                double lng = Double.parseDouble(mainList.get("mapx").toString());
                if (lat > minLat && lat < maxLat && lng > minLng && lng < maxLng) {
                    dto.setAdress(mainList.get("addr1").toString());
                    dto.setName(mainList.get("title").toString());
                    dto.setLon(Double.parseDouble(mainList.get("mapx").toString()));
                    dto.setLat(Double.parseDouble(mainList.get("mapy").toString()));
                    dto.setCategory(type);
                    result.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
