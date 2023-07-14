package test.spring.controller.song;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import test.spring.component.song.HaversineDAO;
import test.spring.component.song.SampleListDTO;
import test.spring.repository.song.Haversine;
import test.spring.repository.song.PermutationDAO;
import test.spring.service.song.TripService;

@Controller
@RequestMapping("/trip/*")
public class TripController {

    @Autowired
    private TripService service;

    @RequestMapping("plan")
    public String plan(Model model) {

        boolean home = true;
        mainLoop:
        while (home) {

            ////////////////////////////////////////////////////////////////////
            // 일정 입력값
            String area = "서울";
            List plan = new ArrayList();
            plan.add("7/10");
            plan.add("7/11");
            plan.add("7/12");
            plan.add("7/13");
            int day = plan.size();
            model.addAttribute("plan", plan);
            ///////////////////////////////////////////////////////////////////

            List<SampleListDTO> list;

            SampleListDTO dto;
            List<SampleListDTO> main = new ArrayList<>();

            int mainNum = 2 * day;

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // main일정 생성
            Loop:
            for (int i = 0; main.size() < mainNum; i++) {
                list = mainList("관광지");
                List sampleAll = new ArrayList();
                List radius = null;

                for (int s = 0; s < main.size(); s++) {
                    if (main.get(s) != null) {
                        SampleListDTO sample = (SampleListDTO) main.get(s);
                        sampleAll.add(sample);
                    }
                }
                if(sampleAll != null) {
                    for(int j = 0; j < main.size(); j++) {
                        HaversineDAO ha = new HaversineDAO();
                        List<Double> test = null;
                        SampleListDTO sample = (SampleListDTO)sampleAll.get(j);
                        test = ha.radius(sample.Lat, sample.Lon, 7-day);

                        radius = mainList2("관광지",test);
                        list.removeAll(radius);
                    }
                }
                dto = list.get((int) (Math.random() * list.size()));

                main.add(dto);
                list.remove(dto);
                if (list.size() == 0) {
                    continue Loop;
                }
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // main일정 동선최적화
            PermutationDAO dao = new PermutationDAO();
            ArrayList<SampleListDTO> mainArrayList = new ArrayList<>(main);
            ArrayList<ArrayList<SampleListDTO>> allPermutations = dao.permutation(mainArrayList);

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
            main = (List<SampleListDTO>) map.get("permutation");
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            System.out.println(main);
            model.addAttribute("main", main);

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // 동선에 맞는 서브일정 추가
            List<List> daySub = new ArrayList();

            for (int i = 0; i < day; i++) {

                int i2 = 2 * i;

                List lat = new ArrayList();
                List lon = new ArrayList();
                List lat1 = new ArrayList();
                List lon1 = new ArrayList();
                List lat2 = new ArrayList();
                List lon2 = new ArrayList();
                if (i2 != 0) {
                    List yesterday = daySub.get(daySub.size() - 1);
                    System.out.println("내일일정" + yesterday);
                    SampleListDTO dumy = (SampleListDTO) yesterday.get(3);
                    lat.add(dumy.getLat());
                    lat.add(main.get(i2).getLat());
                    lon.add(dumy.getLon());
                    lon.add(main.get(i2).getLon());
                    Collections.sort(lat);
                    Collections.sort(lon);
                }
                if (i2 < day - 2) {

                    lat1.add(main.get(i2).getLat());
                    lat1.add(main.get(i2 + 1).getLat());
                    lon1.add(main.get(i2).getLon());
                    lon1.add(main.get(i2 + 1).getLon());

                    lat2.add(main.get(i2 + 1).getLat());
                    lat2.add(main.get(i2 + 2).getLat());
                    lon2.add(main.get(i2 + 1).getLon());
                    lon2.add(main.get(i2 + 2).getLon());

                    Collections.sort(lat1);
                    Collections.sort(lat2);
                    Collections.sort(lon1);
                    Collections.sort(lon2);

                } else {

                    lat1.add(main.get(i2).getLat());
                    lat1.add(main.get(i2 + 1).getLat());
                    lon1.add(main.get(i2).getLon());
                    lon1.add(main.get(i2 + 1).getLon());

                    lat2.add(main.get(i2 + 1).getLat());
                    lat2.add(main.get(i2).getLat());
                    lon2.add(main.get(i2 + 1).getLon());
                    lon2.add(main.get(i2).getLon());

                    Collections.sort(lat1);
                    Collections.sort(lat2);
                    Collections.sort(lon1);
                    Collections.sort(lon2);

                }

                HaversineDAO ha = new HaversineDAO();
                List<Double> radius1 = HaversineDAO.radius((double) lat1.get(0), (double) lon1.get(0), (double) lat1.get(1), (double) lon1.get(1));
                List<Double> radius2 = HaversineDAO.radius((double) lat1.get(1), (double) lon1.get(1), 3);
                List<Double> LatLon1 = ha.LatLon((double) lat1.get(0), (double) lon1.get(0), (double) lat1.get(1), (double) lon1.get(1));
                List<Double> LatLon2 = ha.LatLon((double) lat2.get(0), (double) lon2.get(0), (double) lat2.get(1), (double) lon2.get(1));
                int num2 = 0;
                List subList = new ArrayList();
                List breakfast = new ArrayList();
                List breakfast1 = new ArrayList();
                List luncheon = new ArrayList();
                List abendessen = new ArrayList();
                List<Double> latlon = new ArrayList<>(List.of((double) lat1.get(0), (double) lat1.get(1), (double) lon1.get(0), (double) lon1.get(1)));
                if (i2 != 0) {
                    breakfast = meal("음식점", LatLon1);
                } else {
                    breakfast = meal("음식점", radius1);
                    breakfast1 = meal("음식점", radius2);
                    breakfast.removeAll(breakfast1);
                }
                luncheon = meal("음식점", LatLon1);
                abendessen = meal("음식점", LatLon2);
                subList = subList("관광지", LatLon1);
                num2 = luncheon.size() + abendessen.size() + subList.size() + breakfast.size();
                if(num2==0){
                    System.out.println(main.get(i));
                }
                ////////////////////////////////////////////////////////////////////////
                // 중복방지

                List sub = new ArrayList();
                List subAll = new ArrayList();
                subAll.add(breakfast);
                subAll.add(luncheon);
                subAll.add(subList);
                subAll.add(abendessen);

                for (int y = 0; y < subAll.size(); y++) {
                    List arr = (List) subAll.get(y);
                    for (int x = 0; sub.size() < (y + 1); x++) {
                        dto = (SampleListDTO) arr.get((int) (Math.random() * arr.size()));
                        int num = 0;
                        if (daySub != null) {
                            System.out.println("daySub : " + daySub);
                            for (List arr2 : daySub) {
                                if (arr2.contains(dto)) {
                                    num++;
                                }
                            }
                        }
                        if (num == 0 && !main.contains(dto) && !sub.contains(dto)) {
                            sub.add(dto);
                        }
                        if (x > 10) {
                            continue mainLoop;
                        }
                    }
                }
                daySub.add(sub);
                System.out.println("daysub=" + daySub);
            }
            model.addAttribute("daySub", daySub);
            /////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // finalList에 main이랑 서브일정 합치기
            List<List> dayList = new ArrayList();
            int x = 0;
            for (int i = 0; i < main.size() / 2; i++) {
                List sample = new ArrayList();
                List sub = daySub.get(i);
                sample.add(sub.get(0));        // 아침식사
                sample.add(main.get(x));    // main일정
                sample.add(sub.get(1));        // 점심식사
                sample.add(sub.get(2));        // 서브일정
                sample.add(main.get(x + 1));    // main일정
                sample.add(sub.get(3));        // 저녁식사

                dayList.add(sample);
                x = x + 2;
            }

            List finalList = new ArrayList();
            for (int i = 0; i < dayList.size(); i++) {
                finalList.addAll(dayList.get(i));
            }

            model.addAttribute("finalList", finalList);
            System.out.println(finalList);
            home = false;
        }

        return "/map/testMap";
    }

    @RequestMapping("plan2")
    public String plan2(Model model) {

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
            model.addAttribute("plan", plan);
            List<SampleListDTO> list = mainList("관광지");

            List<SampleListDTO> main;
            int mainNum = day * 2;

            SampleListDTO dto;
            main = new ArrayList<>();
            for (int i = 0; main.size() < mainNum; i++) {
                dto = list.get((int) (Math.random() * list.size()));
                if (!main.contains(dto)) {
                    main.add(dto);
                }
            }
            // 동선최적화
            ArrayList<SampleListDTO> mainArrayList = new ArrayList<>(main);
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
            main = (List<SampleListDTO>) map.get("permutation");

            List<List> daySub = new ArrayList();
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


                List<List<SampleListDTO>> subAll = new ArrayList<>();
                subAll.add(subList);
                subAll.add(breakfast);
                System.out.println(subAll);
//                subAll.add(abendessen);
                // 중복방지
                for (int y = 0; y < subAll.size(); y++) {
                    List<SampleListDTO> arr = subAll.get(y);
                    for (int x = 0; sub.size() < (y + 1); x++) {
                        dto = arr.get((int) (Math.random() * arr.size()));
                        int num = 0;
                        if (daySub != null) {
                            for (List arr2 : daySub) {
                                if (arr2.contains(dto)) {
                                    num++;
                                }
                            }
                        }
                        if (num == 0 && !main.contains(dto)) {
                            sub.add(dto);
                        }
                    }
                }

                // 중복방지S
                for (int x = 0; sub.size() < 1; x++) {
                    dto = subList.get((int) (Math.random() * subList.size()));
                    int num = 0;
                    /////////////중복체크//////////////
                    if (daySub != null) {
                        for (List arr : daySub) {
                            if (arr.contains(dto)) {
                                num++;
                            }
                            int number = 0;
                            for (int f = 0; f < subList.size(); f++) {
                                SampleListDTO test = subList.get(f);
                                if (!arr.contains(test) && !main.contains(test)) {
                                    number++;
                                }
                            }
                        }
                    }
                    //////////////////////////////////
                    System.out.println("sub_add");
                    if (num == 0 && !main.contains(dto)) {
                        sub.add(dto);
                    }
                    if (x > 10) {
                        continue mainLoop;
                    }
                }

                daySub.add(sub);
            }
            model.addAttribute("daySub", daySub);

            // finalList에 main이랑 서브일정 합치기
            List dayList = new ArrayList();
            int x = 0;
            System.out.println("loot5");
            for (int i = 0; i < main.size() / 2; i++) {
                List sample = new ArrayList();
                List sub = daySub.get(i);
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

            model.addAttribute("finalList", finalList);
            home = false;
            System.out.println("end");
        }
        return "/map/testMap";
    }

    @RequestMapping("insert")
    public String data() {
        String mapy = "35.1569749";
        String mapx = "126.8533638";
        List<String> type1 = new ArrayList<>();
        type1.add("12");
        type1.add("14");
        type1.add("28");
        type1.add("38");
        type1.add("39");
        int num = 0;
        Map<String, String> map1 = new HashMap<>();
        while (num < 5) {
            String url1 = "https://apis.data.go.kr/B551011/KorService1/locationBasedList1?serviceKey=tueSYVJWEmvANaRohYnSMi9HK2YStViwfRtj6%2Fiqv4HaQZqV2Ql0FLqX2WA9PKXFgkyghnvdJwJzK5kEvmyhKw%3D%3D&numOfRows=3000&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=" + mapx + "&mapY=" + mapy + "&radius=20000&contentTypeId=" + type1.get(num);
            ;
            if (num == 0) {
                map1.put("관광지", url1);
            } else if (num == 1) {
                map1.put("문화시설", url1);
            } else if (num == 2) {
                map1.put("레포츠", url1);
            } else if ((num == 3)) {
                map1.put("쇼핑", url1);
            } else {
                map1.put("음식점", url1);
            }
            num++;
        }

        try {
            List<String> list2 = new ArrayList<>(map1.values());
            List<String> list3 = new ArrayList<>(map1.keySet());
            for (int i = 0; i < 3; i++) {
                String type = list3.get(i);
                URL url = new URL(list2.get(i));
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                String result = bufferedReader.readLine();
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
                JSONObject response = (JSONObject) jsonObject.get("response");
                JSONObject body = (JSONObject) response.get("body");
                JSONObject items = (JSONObject) body.get("items");
                JSONArray item = (JSONArray) items.get("item");
                for (int x = 0; x < item.size(); x++) {
                    SampleListDTO dto = new SampleListDTO();
                    JSONObject list = (JSONObject) item.get(x);
                    dto.setAdress(list.get("addr1").toString());
                    dto.setLat(Double.parseDouble(list.get("mapy").toString()));
                    dto.setLon(Double.parseDouble(list.get("mapx").toString()));
                    dto.setName(list.get("title").toString());
                    dto.setCategory(type);
                    service.insertList(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/map/list";
    }

    public List<SampleListDTO> mainList2(String type, List<Double> latlng) {
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
        int num = 1000; // 데이터 조회 갯수
        String keyword = "서울";
        url1.append("?").append(URLEncoder.encode("serviceKey", StandardCharsets.UTF_8)).append("=").append(key);
        url1.append("&").append(URLEncoder.encode("numOfRows", StandardCharsets.UTF_8)).append("=").append(num);
        url1.append("&").append(URLEncoder.encode("MobileOS", StandardCharsets.UTF_8)).append("=").append(os);
        url1.append("&").append(URLEncoder.encode("MobileApp", StandardCharsets.UTF_8)).append("=").append(MobileApp);
        url1.append("&").append(URLEncoder.encode("contentTypeId", StandardCharsets.UTF_8)).append("=").append(contentTypeId.get(type));
        url1.append("&").append(URLEncoder.encode("_type", StandardCharsets.UTF_8)).append("=").append("json");
        url1.append("&").append(URLEncoder.encode("keyword", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode(keyword));
        List<SampleListDTO> result = new ArrayList<>();
        Double minLat = latlng.get(0);
        Double maxLat = latlng.get(1);
        Double minLng = latlng.get(2);
        Double maxLng = latlng.get(3);
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
                if (dto.getLat() > minLat && dto.getLat() < maxLat && dto.getLon() > minLng && dto.getLon() < maxLng) {
                    result.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<SampleListDTO> mainList(String type) {
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
        int num = 5000; // 데이터 조회 갯수
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

    public List<SampleListDTO> subList(String type, List<Double> latlng) {
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
        int num = 5000; // 데이터 조회 갯수
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

    public List<SampleListDTO> meal(String type, List<Double> latlng) {
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
        int num = 5000; // 데이터 조회 갯수
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
	