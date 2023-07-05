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
		
		double lat1 = main.get(0).getLat(); // 첫 번째 좌표의 위도
        double lon1 = main.get(0).getLon(); // 첫 번째 좌표의 경도
        double lat2 = main.get(1).getLat(); // 두 번째 좌표의 위도
        double lon2 = main.get(1).getLon(); // 두 번째 좌표의 경도
        double lat3 = main.get(2).getLat(); // 첫 번째 좌표의 위도
        double lon3 = main.get(2).getLon(); // 첫 번째 좌표의 경도

        Haversine ha = new Haversine();
        List LatLon1 = ha.LatLon(lat1, lon1, lat2, lon2);
        List LatLon2 = ha.LatLon(lat2, lon2, lat3, lon3);

        model.addAttribute("Lat" , (LatLon1.get(0) + " <= Lat <= " + LatLon1.get(1)));
        model.addAttribute("Lon" , (LatLon1.get(2) + " <= Lon <= " + LatLon1.get(3)));
        
        model.addAttribute("Lat1" , (LatLon2.get(0) + " <= Lat <= " + LatLon2.get(1)));
        model.addAttribute("Lon1" , (LatLon2.get(2) + " <= Lon <= " + LatLon2.get(3)));
        
        
        return "/song/plan2";
	}
	
}