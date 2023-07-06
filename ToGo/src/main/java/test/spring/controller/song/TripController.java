package test.spring.controller.song;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

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
		
		List<List> daySub = new ArrayList();
		// 1일차 서브 일정, 경로 일정
		for(int i = 0; i < day; i++) {
			
			double lat1 = main.get(i).getLat(); // 첫 번째 좌표의 위도
	        double lon1 = main.get(i).getLon(); // 첫 번째 좌표의 경도
	        double lat2 = main.get(i+1).getLat(); // 두 번째 좌표의 위도
	        double lon2 = main.get(i+1).getLon(); // 두 번째 좌표의 경도
	        double lat3 = main.get(i+2).getLat(); // 첫 번째 좌표의 위도
	        double lon3 = main.get(i+2).getLon(); // 첫 번째 좌표의 경도

	        Haversine ha = new Haversine();
	        List LatLon1 = ha.LatLon(lat1, lon1, lat2, lon2);	// 아침,점심,서브
	        List LatLon2 = ha.LatLon(lat2, lon2, lat3, lon3);	// 저녁
	        
	        // 서브 일정
	        List subList = new ArrayList();
	        List sub = new ArrayList();
	        subList = service.subList(area, (double)LatLon1.get(0), (double)LatLon1.get(1), (double)LatLon1.get(2), (double)LatLon1.get(3));

	        dto = (SampleListDTO)subList.get((int)(Math.random() * subList.size()));
	        sub.add(dto);
	        
	        /*
	        // 아침,점심,저녁
	        List breakfast = new ArrayList();
	        List luncheon = new ArrayList();
	        List abendessen = new ArrayList();
	        breakfast = service.breakfast(area, (double)LatLon1.get(0), (double)LatLon1.get(1), (double)LatLon1.get(2), (double)LatLon1.get(3));
	        luncheon = service.luncheon(area, (double)LatLon1.get(0), (double)LatLon1.get(1), (double)LatLon1.get(2), (double)LatLon1.get(3));
	        abendessen = service.abendessen(area, (double)LatLon2.get(0), (double)LatLon2.get(1), (double)LatLon2.get(2), (double)LatLon2.get(3));
	        
	        dto = (SampleListDTO)breakfast.get((int)(Math.random() * breakfast.size()));
	        sub.add(dto)
	        dto = (SampleListDTO)luncheon.get((int)(Math.random() * luncheon.size()));
	        sub.add(dto)
	        dto = (SampleListDTO)abendessen.get((int)(Math.random() * abendessen.size()));
	        sub.add(dto)
	        */
			
			daySub.add(sub);
		}
		model.addAttribute("daySub" , daySub);
		
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
        return "/song/plan2";	
	}
}
	