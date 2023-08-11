package test.spring.controller.song;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import test.spring.component.map.userDTO;
import test.spring.component.park.FstvlDTO;
import test.spring.component.park.PageResolver;
import test.spring.component.park.QnaDTO;
import test.spring.component.song.CityimgDTO;
import test.spring.component.song.PlanDTO;
import test.spring.component.song.SampleListDTO;
import test.spring.repository.song.PlanListDAO;
import test.spring.repository.song.WeatherDAO;
import test.spring.service.park.FestivalService;
import test.spring.service.song.TripService;

@Controller
@RequestMapping("/trip/*")
public class TripController {

    @Autowired
    private TripService service;

    @Autowired
    private FestivalService festivalService;

    @Autowired
    private PlanListDAO dao;

    @RequestMapping("main")
    public String main(FstvlDTO dto, Model model) {
        List<FstvlDTO> randomFstvlList = festivalService.getRandomFstvlList(dto);
        model.addAttribute("fstvlList", randomFstvlList);

        List list = service.cityimgList();
        
        List list2 = new ArrayList();
        for (int i = 0; list2.size() < 9; i++) {
            CityimgDTO city = (CityimgDTO) list.get((int) (Math.random() * list.size()));
            if (!list2.contains(city)) {
                list2.add(city);
            }
        }
        System.out.println(randomFstvlList);
        model.addAttribute("fstvlList", randomFstvlList);
        model.addAttribute("cityList", list2);

        List wePlan = service.wePlan();

        model.addAttribute("wePlan", wePlan);

        return "/song/main";
    }

    @RequestMapping("plan")
    public String plan(Model model) {

        return "/song/plan";
    }

    @RequestMapping("planMap")
    public String planMap(Model model) {

        return "/map/testMap";
    }
    @RequestMapping("place")
    public @ResponseBody Map<String, List<SampleListDTO>> place(Model model, PlanDTO dto, HttpSession session) {

        boolean home = true;

        ////////////////////////////////////////////////////////////////////
        // 일정 입력값
        try {
            String area = dto.area;
            int day = dto.getTotalDay();
            String table = service.tableName(area);
            String memId = (String) session.getAttribute("memId");
            String userMbti;
            List<SampleListDTO> mainlist = new ArrayList<>();
            for(int count=0; count<dto.getMainList().size();count++){
                Map<String,String> place_bag = new HashMap<>();
                place_bag.put("name",dto.getName().toUpperCase());
                place_bag.put("place",dto.getMainList().get(count));
                System.out.println(place_bag);
                mainlist.add(service.cityList(place_bag));
            }
            System.out.println("mainlist="+mainlist);
            List userAtmosphere = new ArrayList();
            if (memId != null) {
                userMbti = service.userMbti(memId);
                if(userMbti!=null) {
                    userAtmosphere = service.userAtmosphere(userMbti);
                }
            }

            long startTime = System.currentTimeMillis();
            int count = 0;
            Loop:
            while (home) {
            	List<SampleListDTO> bag = mainlist;
            	System.out.println("장바구니 = " + bag);
                long startTime1 = System.currentTimeMillis();
                if(count>30){
                    break;
                }
                List<SampleListDTO> main = dao.generateMainList(table, bag, userAtmosphere, day);
                long endTime1 = System.currentTimeMillis();
                long executionTime1 = endTime1 - startTime1;
                System.out.println("main일정 생성 : " + executionTime1 + "밀리초");

                long startTime2 = System.currentTimeMillis();
                List<SampleListDTO> optimizedMain = dao.optimizeMainList(main);
                long endTime2 = System.currentTimeMillis();
                long executionTime2 = endTime2 - startTime2;
                System.out.println("main일정 동선 최적화 : " + executionTime2 + "밀리초");

                long startTime3 = System.currentTimeMillis();
                List<List<SampleListDTO>> daySub = dao.generateDaySubList(table, userAtmosphere, optimizedMain);
                if (daySub == null) {
                    count++;
                    
                    continue Loop;
                }
                long endTime3 = System.currentTimeMillis();
                long executionTime3 = endTime3 - startTime3;

                System.out.println("sub일정 생성 및 추가 : " + executionTime3 + "밀리초");

                long startTime4 = System.currentTimeMillis();
                List<SampleListDTO> finalList = dao.finalList(daySub, optimizedMain);
                long endTime4 = System.currentTimeMillis();
                long executionTime4 = endTime4 - startTime4;

                System.out.println("최종일정 호출 : " + executionTime4 + "밀리초");

                long startTime5 = System.currentTimeMillis();
                Map<String, List<SampleListDTO>> dayMap = dao.groupByDay(daySub, optimizedMain);
                long endTime5 = System.currentTimeMillis();
                long executionTime5 = endTime5 - startTime5;

                System.out.println("최종일정 호출 : " + executionTime5 + "밀리초");

                long endTime = System.currentTimeMillis();
                double executionTime = (double) (endTime - startTime) / (1000 * 60);

                System.out.println("최종일정 호출 : " + executionTime + "분");

                model.addAttribute("main", optimizedMain);
                model.addAttribute("finalList", finalList);
                model.addAttribute("dayMap", dayMap);

                home = false;

                return dayMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @RequestMapping("myPlan")
    public String myPlan(Model model, HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") String pageNum,userDTO dto) {
    	String memId = (String) session.getAttribute("memId");
    	if(memId != null) {
    		int pageSize = 10; 
    		int page = 1;
    		try {
    			if (pageNum != null && !pageNum.equals("")) {
    				page = Integer.parseInt(pageNum);
    			}
    		} catch (NumberFormatException e) {
    			e.printStackTrace();
    		}
    		int total = service.userPlanCount(memId);
    		int beginPage = (page - 1) * pageSize + 1;
    		int endPage = beginPage + pageSize - 1;
    		
    		dto.setBeginPage(beginPage);
    		dto.setEndPage(endPage);
    		PageResolver pr = new PageResolver(page, pageSize, total);
    		
    		List userPlan = service.userPlan(memId);
    		model.addAttribute("pr", pr);
    		
    		model.addAttribute("userPlan", userPlan);
    	}else {
    		
    	}
    	
    	return "/song/myPlan";
    }
    
    @RequestMapping("myPlace")
    public String place(String plan_num, Model model, HttpSession session) {
    	
    	String memId = (String) session.getAttribute("memId");
    	if(memId != null) {
    		List userPlan = service.userPlan2(plan_num);
    		List day = new ArrayList();
    		for(int a = 0; a < userPlan.size(); a++) {
    			userDTO dto = (userDTO)userPlan.get(a);
    			List list = new ArrayList();
    			list.add(dto.getCourse1());
    			list.add(dto.getCourse2());
    			list.add(dto.getCourse3());
    			list.add(dto.getCourse4());
    			list.add(dto.getCourse5());
    			list.add(dto.getCourse6());
    			day.add(list);
    		}
    		List time = new ArrayList();
    		time.add("08:00 ~ 10:00");
    		time.add("09:00 ~ 11:00");
    		time.add("11:00 ~ 13:00");
    		time.add("13:00 ~ 15:00");
    		time.add("15:00 ~ 17:00");
    		time.add("17:00 ~ 19:00");
    		model.addAttribute("userPlan", userPlan);
    		model.addAttribute("day", day);
    		model.addAttribute("time", time);
    	}else {
    		
    	}
    	
    	return "/song/place";
    }
    
    @RequestMapping("popularPlace")
    public String popularPlace(Model model, String area) {
    	System.out.println(area);
    	String [] areaName = area.split(" ");
    	System.out.println(areaName[1]);
    	String table = service.tableName(areaName[1]);
    	String tableMain = table+"_main";
    	String tableSub = table+"_sub";
    	
    	List main = service.popular(tableMain);
    	List sub = service.popular(tableSub);
    	
    	model.addAttribute("main", main);
    	model.addAttribute("sub", sub);
    	
    	return "/song/popularPlace";
    }

    @RequestMapping("weather")
    public String weatherTest(Model model) {

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        String today = simpleDateFormat.format(date);

        String baseDay = today;
        String baseTime = "0600";

        WeatherDAO dao2 = new WeatherDAO();

        JSONObject item = dao2.weather2("11B00000", today + baseTime);

        HashMap weather = new HashMap();
        for (int i = 3; i < 8; i++) {
            List list = new ArrayList();

            String am = "wf" + i + "Am";	// 맑음
            String am2 = "rnSt" + i + "Am";	// 강수확률
            String pm = "wf" + i + "Pm";	// 맑음
            String pm2 = "rnSt" + i + "Pm";	// 강수확률
            
            String dayAm = item.get(am) + "(" + item.get(am2) + "%)";
            String dayPm = item.get(pm) + "(" + item.get(pm2) + "%)";
            
            HashMap day = new HashMap();
            day.put("오전", dayAm);
            day.put("오후", dayPm);
            
            weather.put(i+"일후", day);
        }
        for (int i = 8; i < 11; i++) {
            List list = new ArrayList();

            String all = "wf" + i;	// 구름많음
            String all2 = "rnSt" + i;	// 강수 확률
            String day = item.get(all) + "(" + item.get(all2) + "%)";
            
            weather.put(i+"일후", day);
        }
        
        model.addAttribute("weather", weather);

        return "/song/weather";
    }

    public String data(SampleListDTO dto) {
        String url1 = "https://apis.data.go.kr/B551011/KorService1/searchKeyword1?serviceKey=tueSYVJWEmvANaRohYnSMi9HK2YStViwfRtj6%2Fiqv4HaQZqV2Ql0FLqX2WA9PKXFgkyghnvdJwJzK5kEvmyhKw%3D%3D&numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&keyword=%EB%8C%80%EC%A0%84&contentTypeId=12";
        try {
            URL url = new URL(url1);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String result = bufferedReader.readLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject response = (JSONObject) jsonObject.get("response");
            JSONObject body = (JSONObject) response.get("body");
            JSONObject items = (JSONObject) body.get("items");
            JSONArray item = (JSONArray) items.get("item");
            for (int i = 0; i < item.length(); i++) {
                JSONObject list = (JSONObject) item.get(i);
                dto.setAdress(list.get("addr1").toString());
                dto.setLat(Double.parseDouble(list.get("mapy").toString()));
                dto.setLon(Double.parseDouble(list.get("mapx").toString()));
                dto.setName(list.get("title").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
