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

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import test.spring.component.song.SampleListDTO;
import test.spring.repository.song.PlanListDAO;
import test.spring.repository.song.WeatherDAO;
import test.spring.service.song.TripService;

@Controller
@RequestMapping("/trip/*")
public class TripController {

	@Autowired
	private TripService service;
	
	@Autowired
	private PlanListDAO dao;
	
	@RequestMapping("plan")
	public String plan(Model model) {
        
		boolean home = true;
		
		////////////////////////////////////////////////////////////////////
		// 일정 입력값
			String area = "서울";
			List<String> plan = new ArrayList();
			plan.add("7/10");
			plan.add("7/11");
			plan.add("7/12");
			plan.add("7/13");
			int day = plan.size();
			model.addAttribute("plan" , plan);
		///////////////////////////////////////////////////////////////////
		
		long startTime = System.currentTimeMillis();
		
		Loop:
		while(home) {
			
			long startTime1 = System.currentTimeMillis();
			List<SampleListDTO> main = dao.generateMainList(area, plan);
			long endTime1 = System.currentTimeMillis();
			long executionTime1 = endTime1 - startTime1;

			System.out.println("main일정 생성 : " + executionTime1 + "밀리초");
			
			long startTime2 = System.currentTimeMillis();
		    List<SampleListDTO> optimizedMain = dao.optimizeMainList(main);
		    long endTime2 = System.currentTimeMillis();
			long executionTime2 = endTime2 - startTime2;

			System.out.println("main일정 동선 최적화 : " + executionTime2 + "밀리초");
		    
			long startTime3 = System.currentTimeMillis();
		    List<List<SampleListDTO>> daySub = dao.generateDaySubList(area, optimizedMain);
		    if(daySub == null) {
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
		    Map<String, List<SampleListDTO>> dayMap = dao.groupByDay(daySub, main);
		    long endTime5 = System.currentTimeMillis();
			long executionTime5 = endTime5 - startTime5;

			System.out.println("최종일정 호출 : " + executionTime5 + "밀리초");
			
			long endTime = System.currentTimeMillis();
			long executionTime = endTime - startTime;

			System.out.println("최종일정 호출 : " + executionTime + "밀리초");
		    
		    model.addAttribute("main", optimizedMain);
		    model.addAttribute("finalList", finalList);
		    model.addAttribute("dayMap", dayMap);
		    
		    home = false;
		}
		
        return "/map/testMap";	
	}
	
	@RequestMapping("weather")
	public String weatherTest(Model model) {
    	
		//double lat = 37.5635694;
		//double lon = 126.5003;
		
		double lat = 55;
		double lon = 127;
		
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd"); 

		String today = simpleDateFormat.format(date); 
    	
		System.out.println(today);
		
		String baseDay = today;
		String baseTime = "0600";
		
		WeatherDAO dao2 = new WeatherDAO();
		
	//	String weather = dao.weather(lat, lon, baseDay, baseTime);
	//	String weather = dao.weather("109", today + baseTime);	// 11B00000
		JSONObject item = dao2.weather2("11B00000", today + baseTime);
		
		List day = new ArrayList();
		for(int i = 3; i < 8; i++) {
			List list = new ArrayList();
			
			String am = "wf" + i + "Am";
			String am2 = "rnSt" + i + "Am";
			item.get(am);
			item.get(am2);
			list.add("오전 : " + item.get(am) + "(" + item.get(am2) + "%)");
			
			String pm = "wf" + i + "Pm";
			String pm2 = "rnSt" + i + "Pm";
			item.get(pm);
			item.get(pm2);
			list.add("오후 : " + item.get(pm) + "(" + item.get(pm2) + "%)");
			
			day.add(list);
		}
		for(int i = 8; i < 11; i++) {
			List list = new ArrayList();
			
			String all = "wf" + i;
			String all2 = "rnSt" + i;
			item.get(all);
			item.get(all2);
			list.add(item.get(all) + "(" + item.get(all2) + "%)");
			
			day.add(list);
		}
		
		model.addAttribute("weather", day);
		
    	return "/song/weather";
    }
	
    public String data(SampleListDTO dto){
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
            for (int i =0;i<item.length();i++){
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
	