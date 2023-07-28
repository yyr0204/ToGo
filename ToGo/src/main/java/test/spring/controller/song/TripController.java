package test.spring.controller.song;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import test.spring.component.park.FstvlDTO;
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
		for(int i = 0; list2.size() < 12; i++) {
			CityimgDTO city = (CityimgDTO)list.get((int)(Math.random()*12));
			if(!list2.contains(city)) {
				list2.add(city);
			}
		}
		System.out.println(randomFstvlList);
		model.addAttribute("fstvlList", randomFstvlList);
		model.addAttribute("cityList" , list2);

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
	public @ResponseBody Map<String,List<SampleListDTO>> place(Model model, PlanDTO dto, HttpSession session) {
		
		boolean home = true;

		////////////////////////////////////////////////////////////////////
		// 일정 입력값
		String area = dto.area;
		int day = dto.endDay.getDate()-dto.startDay.getDate()+1;
		System.out.println(day);
		model.addAttribute("day" , day);
		///////////////////////////////////////////////////////////////////
		String table = service.tableName(area);
		String memId = (String)session.getAttribute("memId");
		String userMbti;
		List<SampleListDTO> mainlist = dto.mainList;
		List userAtmosphere = new ArrayList();
		if(memId != null) {
			userMbti = service.userMbti(memId);
			userAtmosphere = service.userAtmosphere(userMbti);
		}

		long startTime = System.currentTimeMillis();
			Loop:
			while (home) {

				long startTime1 = System.currentTimeMillis();
				List<SampleListDTO> main = dao.generateMainList(table, mainlist, userAtmosphere, day);
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
				if (daySub.size() == 0) {
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

		return null;
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
