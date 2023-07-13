package test.spring.controller.song;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import test.spring.component.song.SampleListDTO;
import test.spring.repository.song.HaversineDAO;
import test.spring.repository.song.PermutationDAO;
import test.spring.repository.song.WeatherDAO;
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
		while(home) {
			
		////////////////////////////////////////////////////////////////////
		// 일정 입력값
			String area = "서울";
			List plan = new ArrayList();
			plan.add("7/10");
			plan.add("7/11");
			plan.add("7/12");
			plan.add("7/13");
			int day = plan.size();
			model.addAttribute("plan" , plan);
		///////////////////////////////////////////////////////////////////
			
			List<SampleListDTO> list;
	
			SampleListDTO dto;
			List<SampleListDTO> main = new ArrayList<>();
	
			int mainNum = 2*day;
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// main일정 생성
			Loop:
			for (int i = 0; main.size() < mainNum; i++) {
				list = service.mainList(area);
				List sampleAll = new ArrayList();
				List radius = null;
				System.out.println("list.size() : " + list.size());
				
				for(int s = 0; s < main.size(); s++) {
					if(main.get(s) != null) {
						SampleListDTO sample = (SampleListDTO)main.get(s);
						sampleAll.add(sample);
					}
				}
				if(sampleAll != null) {
					for(int j = 0; j < main.size(); j++) {
						HaversineDAO ha = new HaversineDAO();
						List test = null;
						SampleListDTO sample = (SampleListDTO)sampleAll.get(j);
						test = ha.radius(sample.Lat, sample.Lon, 7-day);
						radius = service.mainList(area, (double)test.get(0), (double)test.get(1), (double)test.get(2), (double)test.get(3));
						list.removeAll(radius);
					}
				}
				System.out.println("반경 뺀 list : " + list.size());
				System.out.println();
				dto = list.get((int) (Math.random() * list.size()));
				
				main.add(dto);
				list.remove(dto);
				if(list.size() == 0) {
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
			main = (List<SampleListDTO>)map.get("permutation");
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			System.out.println(main);
			
			model.addAttribute("main" , main);
			
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 동선에 맞는 서브일정 추가
			List<List> daySub = new ArrayList();
			
			for(int i = 0; i < day; i++) {
				int i2 = 2*i;
				
				List lat = new ArrayList();
				List lon = new ArrayList();
				List lat1 = new ArrayList();
				List lon1 = new ArrayList();
				List lat2 = new ArrayList();
				List lon2 = new ArrayList();
				if(i2 != 0) {
					List yesterday = daySub.get(daySub.size());
					System.out.println(yesterday);
					SampleListDTO dumy = (SampleListDTO)yesterday.get(6);
					lat.add(dumy.getLat());
					lat.add(main.get(i2).getLat());
					lon.add(dumy.getLon());
					lon.add(main.get(i2).getLon());
					Collections.sort(lat);
					Collections.sort(lon);
				}
				if(i2 < day-2) {
					
					lat1.add(main.get(i2).getLat());
					lat1.add(main.get(i2+1).getLat());
					lon1.add(main.get(i2).getLon());
					lon1.add(main.get(i2+1).getLon());
					
					lat2.add(main.get(i2+1).getLat());
					lat2.add(main.get(i2+2).getLat());			
					lon2.add(main.get(i2+1).getLon());
					lon2.add(main.get(i2+2).getLon());
					
					Collections.sort(lat1);
					Collections.sort(lat2);
					Collections.sort(lon1);
					Collections.sort(lon2);

		        }else {
		        	
					lat1.add(main.get(i2).getLat());
					lat1.add(main.get(i2+1).getLat());
					lon1.add(main.get(i2).getLon());
					lon1.add(main.get(i2+1).getLon());
					
					lat2.add(main.get(i2+1).getLat());
					lat2.add(main.get(i2).getLat());			
					lon2.add(main.get(i2+1).getLon());
					lon2.add(main.get(i2).getLon());
					
					Collections.sort(lat1);
					Collections.sort(lat2);
					Collections.sort(lon1);
					Collections.sort(lon2);

		        }
				
				HaversineDAO ha = new HaversineDAO();
				List radius1 = ha.radius((double)lat1.get(0), (double)lon1.get(0), (double)lat1.get(1), (double)lon1.get(1));
				List radius2 = ha.radius((double)lat1.get(1), (double)lon1.get(1), 3);
		        List LatLon1 = ha.LatLon((double)lat1.get(0), (double)lon1.get(0), (double)lat1.get(1), (double)lon1.get(1));
		        List LatLon2 = ha.LatLon((double)lat2.get(0), (double)lon2.get(0), (double)lat2.get(1), (double)lon2.get(1));
		        
		        List subList = new ArrayList();
		        List breakfast = new ArrayList();
		        List breakfast1 = new ArrayList();
		        List luncheon = new ArrayList();
		        List abendessen = new ArrayList();
		        if(i2 != 0) {
		        	breakfast = service.breakfast(area, (double)lat.get(0), (double)lat.get(1), (double)lon.get(0), (double)lon.get(1));
		        }else {
		        	breakfast = service.breakfast(area, (double)radius1.get(0), (double)radius1.get(1), (double)radius1.get(2), (double)radius1.get(3));
		        	breakfast1 = service.breakfast(area, (double)radius2.get(0), (double)radius2.get(1), (double)radius2.get(2), (double)radius2.get(3));
		        	breakfast.removeAll(breakfast1);
		        }
		        luncheon = service.luncheon(area, (double)lat1.get(0), (double)lat1.get(1), (double)lon1.get(0), (double)lon1.get(1));
		        abendessen = service.abendessen(area, (double)lat2.get(0), (double)lat2.get(1), (double)lon2.get(0), (double)lon2.get(1));
		        subList = service.subList(area, (double)lat1.get(0), (double)lat1.get(1), (double)lon1.get(0), (double)lon1.get(1));
		        
		    ////////////////////////////////////////////////////////////////////////     
		    // 중복방지
		        
		        List sub = new ArrayList();
		        List subAll = new ArrayList();
		        subAll.add(breakfast);
		        subAll.add(luncheon);
		        subAll.add(subList);
		        subAll.add(abendessen);
		       	
		        for(int y = 0; y < subAll.size(); y++) {
		        	List arr = (List)subAll.get(y);
		        	for(int x = 0; sub.size() < (y+1); x++) {
		        		dto = (SampleListDTO)arr.get((int)(Math.random() * arr.size()));
		        		int num = 0;
		        		if(daySub != null) {
		        			System.out.println("daySub : " + daySub);
		        			for(List arr2 : daySub) {
		        				if(arr2.contains(dto)) {
		        					num++;
		        				}
		        			}
		        		}
		        		if(num == 0 && !main.contains(dto) && !sub.contains(dto)) {
		        			sub.add(dto);
		        		}
		        		if(x > 10) {
		        			continue mainLoop;
		        		}
		        	}
		        }
		        daySub.add(sub);
			}
			model.addAttribute("daySub" , daySub);
			/////////////////////////////////////////////////////////////////////////////
			
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// finalList에 main이랑 서브일정 합치기
			List dayList = new ArrayList();
			int x = 0;
			for(int i = 0; i < main.size()/2; i++) {
				List sample = new ArrayList();
				List sub = daySub.get(i);
				sample.add(sub.get(0));		// 아침식사
				sample.add(main.get(x));	// main일정
				sample.add(sub.get(1));		// 점심식사
				sample.add(sub.get(2));		// 서브일정
				sample.add(main.get(x+1));	// main일정
				sample.add(sub.get(3));		// 저녁식사
				
				dayList.add(sample);
				x = x + 2;
			}

			List finalList = new ArrayList();
			for(int i = 0; i < dayList.size(); i++) {
				finalList.addAll((List)dayList.get(i));
			}

			model.addAttribute("finalList" , finalList);
			
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
		
		String baseDay = "20230712";
		String baseTime = "0500";
		
		WeatherDAO dao = new WeatherDAO();
		
		String weather = dao.weather(lat, lon, baseDay, baseTime);
		List list = new ArrayList();
		list.add(weather.split("55127"));
		System.out.println(list);
		
		model.addAttribute("weather", dao.weather(lat, lon, baseDay, baseTime));
		
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
            for (int i =0;i<item.size();i++){
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
	