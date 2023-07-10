package test.spring.controller.song;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
		
		String area = "대전광역시";
		List plan = new ArrayList();
		plan.add("7/10");
		plan.add("7/11");
		plan.add("7/12");
		plan.add("7/13");
		int day = plan.size();
		model.addAttribute("plan" , plan);
		
		List<SampleListDTO> list = service.mainList(area);

		SampleListDTO dto;
		List<SampleListDTO> main = new ArrayList<>();

		int mainNum = 2*day;

		for (int i = 0; main.size() < mainNum; i++) {
			dto = list.get((int) (Math.random() * list.size()));
			if(!main.contains(dto)) {
				main.add(dto);
			}
		}

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
		
		model.addAttribute("main" , main);
		
		List length = new ArrayList();
		for(int i = 0; i < (main.size()-1); i++) {
				SampleListDTO sample1 = main.get(i);
				SampleListDTO sample2 = main.get(i+1);
				
				double [] a = {sample1.getLat(), sample1.getLon()};
				double [] b = {sample2.getLat(), sample2.getLon()};
				
				double dLat = Math.toRadians(b[0] - a[0]);
				double dLon = Math.toRadians(b[1] - a[1]);

				double x = Math.sin(dLat/2)* Math.sin(dLat/2)+ Math.cos(Math.toRadians(a[0]))* Math.cos(Math.toRadians(b[0]))* Math.sin(dLon/2)* Math.sin(dLon/2);
				double y = 2 * Math.atan2(Math.sqrt(x), Math.sqrt(1-x));
				double z = 6371 * y;    // Distance in m
				
				length.add(z);
		}
		model.addAttribute("length" , length);
		
		return "/song/plan";
	}
	
	@RequestMapping("plan2")
	public String plan2(Model model) {
        
		boolean home = true;
		mainLoop:
		while(home) {
			
			String area = "대전광역시";
			List plan = new ArrayList();
			plan.add("7/10");
			plan.add("7/11");
			plan.add("7/12");
			plan.add("7/13");
			int day = plan.size();
			model.addAttribute("plan" , plan);
			// 일정 입력값
			
			List<SampleListDTO> list = service.mainList(area);
	
			SampleListDTO dto;
			List<SampleListDTO> main = new ArrayList<>();
	
			int mainNum = 2*day;
		
			for (int i = 0; main.size() < mainNum; i++) {
				dto = list.get((int) (Math.random() * list.size()));
				if(!main.contains(dto)) {
					main.add(dto);
				}
			}

			// 동선최적화
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
			
			model.addAttribute("main" , main);
			
			List<List> daySub = new ArrayList();
			// 동선에 맞는 서브일정 추가

			for(int i = 0; i < day; i++) {
				int i2 = 2*i;

				double lat1;
		        double lon1;
		        double lat2;
		        double lon2;
	        	double lat3;
		        double lon3;
				if(i2 < 6) {
		        	lat1 = main.get(i2).getLat();
			        lon1 = main.get(i2).getLon();
			        lat2 = main.get(i2+1).getLat();
			        lon2 = main.get(i2+1).getLon();
		        	lat3 = main.get(i2+2).getLat();
			        lon3 = main.get(i2+2).getLon();
		        }else {
		        	lat1 = main.get(i2).getLat();
			        lon1 = main.get(i2).getLon();
			        lat2 = main.get(i2+1).getLat();
			        lon2 = main.get(i2+1).getLon();
			        lat3 = main.get(i2).getLat();
			        lon3 = main.get(i2).getLon();
		        }
		       
		        Haversine ha = new Haversine();
		        List LatLon1 = ha.LatLon(lat1, lon1, lat2, lon2);
		        List LatLon2 = ha.LatLon(lat2, lon2, lat3, lon3);

		        List subList = new ArrayList();
		    /*
		        // 아침,점심,저녁
		        List breakfast = new ArrayList();
		        List luncheon = new ArrayList();
		        List abendessen = new ArrayList();
		        breakfast = service.breakfast(area, (double)LatLon1.get(0), (double)LatLon1.get(1), (double)LatLon1.get(2), (double)LatLon1.get(3));
		        luncheon = service.luncheon(area, (double)LatLon1.get(0), (double)LatLon1.get(1), (double)LatLon1.get(2), (double)LatLon1.get(3));
		        abendessen = service.abendessen(area, (double)LatLon2.get(0), (double)LatLon2.get(1), (double)LatLon2.get(2), (double)LatLon2.get(3));
		    */
		        subList = service.subList(area, (double)LatLon1.get(0), (double)LatLon1.get(1), (double)LatLon1.get(2), (double)LatLon1.get(3));

		        List sub = new ArrayList();
		        
		    /*  
		       List subAll = new ArrayList();
		       subAll.add(subList);
		       subAll.add(breakfast);
		       subAll.add(luncheon);
		       subAll.add(abendessen);
		       	// 중복방지
		       for(int y = 0; y < subAll.size(); y++) {
		    	   List arr = (List)subAll.get(y);
		    	   for(int x = 0; sub.size() < (y+1); x++) {
			    	   dto = (SampleListDTO)arr.get((int)(Math.random() * arr.size()));
			    	   int num = 0;
			    	   if(daySub != null) {
			    		   for(List arr2 : daySub) {
			    			   if(arr2.contains(dto)) {
			    				   num++;
			    			   }
			    		   }
			    	   }
			    	   if(num == 0 && !main.contains(dto)) {
			    		   sub.add(dto);
			    	   }
			       }
		       }
		    */  
		        
		        // 중복방지
		        for(int x = 0; sub.size() < 1; x++) {
		        	dto = (SampleListDTO)subList.get((int)(Math.random() * subList.size()));
		        	int num = 0;
		        	if(daySub != null) {
		        		for(List arr : daySub) {
			        		if(arr.contains(dto)) {
					        	num++;
					        }
			        	}
		        	}
		        	if(num == 0 && !main.contains(dto)) {
		        		sub.add(dto);
		        	}
		        	if(x > 10) {
		        		continue mainLoop;
		        	}
		        }
				
				daySub.add(sub);
			}
			model.addAttribute("daySub" , daySub);
			
			// finalList에 main이랑 서브일정 합치기
			List dayList = new ArrayList();
			int x = 0;
			for(int i = 0; i < main.size()/2; i++) {
				List sample = new ArrayList();
				List sub = daySub.get(i);
				sample.add(main.get(x));	// main일정
				sample.add(sub.get(0));		// 아침식사
		//		sample.add(sub.get(1));		// 점심식사
		//		sample.add(sub.get(2));		// 서브일정
				sample.add(main.get(x+1));	// main일정
		//		sample.add(sub.get(3));		// 저녁식사
				
				dayList.add(sample);
				x = x + 2;
			}

			List finalList = new ArrayList();
			for(int i = 0; i < dayList.size(); i++) {
				finalList.addAll((List)dayList.get(i));
			}

			model.addAttribute("finalList" , finalList);
			
			/*
			for(List sub : daySub) {
				main.addAll(sub);
			}
			
			if(1 < 3) {
			
				// 동선최적화
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
				
			}
			
			model.addAttribute("main" , main);   
	        */
			home = false;
		}
		
        return "/map/testMap";	
	}
	
	@RequestMapping("plan3")
	public String plan3(Model model) {
        
		boolean home = true;
		mainLoop:
		while(home) {
			
			String area = "대전광역시";
			List plan = new ArrayList();
			plan.add("7/10");
			plan.add("7/11");
			plan.add("7/12");
			plan.add("7/13");
			int day = plan.size();
			model.addAttribute("plan" , plan);
			// 일정 입력값
			
			List<SampleListDTO> list = service.mainList(area);
	
			SampleListDTO dto;
			List<SampleListDTO> main = new ArrayList<>();
	
			int mainNum = 2*day;
		
			for (int i = 0; main.size() < mainNum; i++) {
				dto = list.get((int) (Math.random() * list.size()));
				if(!main.contains(dto)) {
					main.add(dto);
				}
			}

			// 동선최적화
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
			
			model.addAttribute("main" , main);
			
			List<List> daySub = new ArrayList();
			// 동선에 맞는 서브일정 추가

			for(int i = 0; i < day; i++) {
				int i2 = 2*i;
				
				List lat1 = new ArrayList();
				List lon1 = new ArrayList();
				List lat2 = new ArrayList();
				List lon2 = new ArrayList();
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
				
				Haversine ha = new Haversine();
				List LatLon = ha.LatLon((double)lat1.get(0), (double)lon1.get(0));
		        List LatLon1 = ha.LatLon((double)lat1.get(0), (double)lon1.get(0), (double)lat1.get(1), (double)lon1.get(1));
		        List LatLon2 = ha.LatLon((double)lat2.get(0), (double)lon2.get(0), (double)lat2.get(1), (double)lon2.get(1));
		        
		        List subList = new ArrayList();
		        List breakfast = new ArrayList();
		        List luncheon = new ArrayList();
		        List abendessen = new ArrayList();
		        breakfast = service.breakfast(area, (double)LatLon.get(0), (double)LatLon.get(1), (double)LatLon.get(2), (double)LatLon.get(3));
		        luncheon = service.luncheon(area, (double)lat1.get(0), (double)lat1.get(1), (double)lon1.get(0), (double)lon1.get(1));
		        abendessen = service.abendessen(area, (double)lat2.get(0), (double)lat2.get(1), (double)lon2.get(0), (double)lon2.get(1));
		        subList = service.subList(area, (double)lat1.get(0), (double)lat1.get(1), (double)lon1.get(0), (double)lon1.get(1));
		        
		        List sub = new ArrayList();
		        
		      
		        List subAll = new ArrayList();
		        subAll.add(breakfast);
		        subAll.add(luncheon);
		        subAll.add(subList);
		        subAll.add(abendessen);
		       	// 중복방지
		        for(int y = 0; y < subAll.size(); y++) {
		        	List arr = (List)subAll.get(y);
		        	for(int x = 0; sub.size() < (y+1); x++) {
		        		dto = (SampleListDTO)arr.get((int)(Math.random() * arr.size()));
		        		int num = 0;
		        		if(daySub != null) {
		        			for(List arr2 : daySub) {
		        				if(arr2.contains(dto)) {
		        					num++;
		        				}
		        			}
		        		}
		        		if(num == 0 && !main.contains(dto)) {
		        			sub.add(dto);
		        		}
		        		if(x > 10) {
		        			continue mainLoop;
		        		}
		        	}
		        	daySub.add(sub);
		        }
			}
			model.addAttribute("daySub" , daySub);
			
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
	
	@RequestMapping("plan4")
	public String plan4(Model model) {
        
		boolean home = true;
		mainLoop:
		while(home) {
			
			String area = "대전광역시";
			List plan = new ArrayList();
			plan.add("7/10");
			plan.add("7/11");
			plan.add("7/12");
			plan.add("7/13");
			int day = plan.size();
			model.addAttribute("plan" , plan);
			// 일정 입력값
			
			List<SampleListDTO> list = service.mainList(area);
	
			SampleListDTO dto;
			List<SampleListDTO> main = new ArrayList<>();
	
			int mainNum = 2*day;
		
			for (int i = 0; main.size() < mainNum; i++) {
				dto = list.get((int) (Math.random() * list.size()));
				if(!main.contains(dto)) {
					main.add(dto);
				}
			}

			// 동선최적화
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
			
			model.addAttribute("main" , main);
			
			List<List> daySub = new ArrayList();
			// 동선에 맞는 서브일정 추가

			for(int i = 0; i < day; i++) {
				int i2 = 2*i;

				double lat1;
		        double lon1;
		        double lat2;
		        double lon2;
	        	double lat3;
		        double lon3;
				if(i2 < 6) {
		        	lat1 = main.get(i2).getLat();
			        lon1 = main.get(i2).getLon();
			        lat2 = main.get(i2+1).getLat();
			        lon2 = main.get(i2+1).getLon();
		        	lat3 = main.get(i2+2).getLat();
			        lon3 = main.get(i2+2).getLon();
		        }else {
		        	lat1 = main.get(i2).getLat();
			        lon1 = main.get(i2).getLon();
			        lat2 = main.get(i2+1).getLat();
			        lon2 = main.get(i2+1).getLon();
			        lat3 = main.get(i2).getLat();
			        lon3 = main.get(i2).getLon();
		        }
		       
		        Haversine ha = new Haversine();
		        List LatLon1 = ha.LatLon(lat1, lon1, lat2, lon2);
		        List LatLon2 = ha.LatLon(lat2, lon2, lat3, lon3);

		        List subList = new ArrayList();
		        List breakfast = new ArrayList();
		        List luncheon = new ArrayList();
		        List abendessen = new ArrayList();
		        breakfast = service.breakfast(area, (double)LatLon1.get(0), (double)LatLon1.get(1), (double)LatLon1.get(2), (double)LatLon1.get(3));
		        luncheon = service.luncheon(area, (double)LatLon1.get(0), (double)LatLon1.get(1), (double)LatLon1.get(2), (double)LatLon1.get(3));
		        abendessen = service.abendessen(area, (double)LatLon2.get(0), (double)LatLon2.get(1), (double)LatLon2.get(2), (double)LatLon2.get(3));
		        subList = service.subList(area, (double)LatLon1.get(0), (double)LatLon1.get(1), (double)LatLon1.get(2), (double)LatLon1.get(3));

		        List sub = new ArrayList();
		        
		      
		       List subAll = new ArrayList();
		       subAll.add(subList);
		       subAll.add(breakfast);
		       subAll.add(luncheon);
		       subAll.add(abendessen);
		       	// 중복방지
		       for(int y = 0; y < subAll.size(); y++) {
		    	   List arr = (List)subAll.get(y);
		    	   for(int x = 0; sub.size() < (y+1); x++) {
			    	   dto = (SampleListDTO)arr.get((int)(Math.random() * arr.size()));
			    	   int num = 0;
			    	   if(daySub != null) {
			    		   for(List arr2 : daySub) {
			    			   if(arr2.contains(dto)) {
			    				   num++;
			    			   }
			    		   }
			    	   }
			    	   if(num == 0 && !main.contains(dto)) {
			    		   sub.add(dto);
			    	   }
			    	   if(x > 10) {
			        		continue mainLoop;
			        	}
			       }
		    	   daySub.add(sub);
		       }
			}
			model.addAttribute("daySub" , daySub);
			
			// finalList에 main이랑 서브일정 합치기
			List dayList = new ArrayList();
			int x = 0;
			for(int i = 0; i < main.size()/2; i++) {
				List sample = new ArrayList();
				List sub = daySub.get(i);
				sample.add(main.get(x));	// main일정
				sample.add(sub.get(0));		// 아침식사
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
	