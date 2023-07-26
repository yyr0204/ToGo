package test.spring.repository.song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import test.spring.component.song.SampleListDTO;
import test.spring.service.song.TripService;

@Repository
public class PlanListDAO {
	
	@Autowired
	private TripService service;
	
	// 메인일정 생성
	public List<SampleListDTO> generateMainList(String table, int day) {
	    
		int mainNum = 2*day;	// 일정에따른 main 개수
		List<SampleListDTO> list;
		List<SampleListDTO> main = new ArrayList<>();
		SampleListDTO dto;
		
		Loop:
		for(int h = 0; h < 1; h++) {
			
			list = service.mainList(table);	// 선택 지역의 전체 리스트 생성
			for (int i = 0; main.size() < mainNum; i++) {
				List radius = null;

				if(main.size() != 0) {
					HaversineDAO ha = new HaversineDAO();
					List test = null;
					SampleListDTO sample = (SampleListDTO)main.get(main.size()-1);
					test = ha.radius(sample.Lat, sample.Lon, day);
					radius = service.mainList(table, (double)test.get(0), (double)test.get(1), (double)test.get(2), (double)test.get(3));
					list.removeAll(radius);
				}
				dto = list.get((int) (Math.random() * list.size()));
				main.add(dto);
				list.remove(dto);
				if(list.size() == 0) {
					continue Loop;
				}
			}
		}
		
		return main;
	}

	// 메인일정 동선 최적화 작업
	public List<SampleListDTO> optimizeMainList(List<SampleListDTO> main) {
		
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
		
		return main;
	}

	// 서브일정 생성 작업
	public List<List<SampleListDTO>> generateDaySubList(String table, List<SampleListDTO> main) {
	    
		List<List<SampleListDTO>> daySub = new ArrayList();
		int day = main.size()/2;
		SampleListDTO dto;
		
		for(int i = 0; i < day; i++) {
			int i2 = 2*i;
			
			List lat = new ArrayList();
			List lon = new ArrayList();
			List lat1 = new ArrayList();
			List lon1 = new ArrayList();
			List lat2 = new ArrayList();
			List lon2 = new ArrayList();
			
			lat1.add(main.get(i2).getLat());
			lat1.add(main.get(i2+1).getLat());
			lon1.add(main.get(i2).getLon());
			lon1.add(main.get(i2+1).getLon());
			
			Collections.sort(lat1);
			Collections.sort(lon1);
			
			if(i2 != 0) {
				List yesterday = daySub.get(daySub.size()-1);
				SampleListDTO dumy = (SampleListDTO)yesterday.get(3);
				lat.add(dumy.getLat());
				lat.add(main.get(i2).getLat());
				lon.add(dumy.getLon());
				lon.add(main.get(i2).getLon());
				Collections.sort(lat);
				Collections.sort(lon);
			}
			
			if(i2 < main.size()-2) {	// 마지막날 제외

				lat2.add(main.get(i2+1).getLat());
				lat2.add(main.get(i2+2).getLat());			
				lon2.add(main.get(i2+1).getLon());
				lon2.add(main.get(i2+2).getLon());
				
				Collections.sort(lat2);
				Collections.sort(lon2);

	        }else {		// 마지막날
				
				lat2.add(main.get(i2+1).getLat());
				lat2.add(main.get(i2).getLat());			
				lon2.add(main.get(i2+1).getLon());
				lon2.add(main.get(i2).getLon());
				
				Collections.sort(lat2);
				Collections.sort(lon2);

	        }
			
			HaversineDAO ha = new HaversineDAO();
			List radius1 = ha.radius((double)main.get(i2).getLat(), (double)main.get(i2).getLon(), (double)lat1.get(0), (double)lon1.get(0), (double)lat1.get(1), (double)lon1.get(1));
			List radius2 = ha.radius((double)main.get(i2+1).getLat(), (double)main.get(i2+1).getLon(), (double)lat1.get(0), (double)lon1.get(0), (double)lat1.get(1), (double)lon1.get(1));
	        
			List breakfast_LatLon = new ArrayList();
			List luncheon_LatLon = ha.LatLon((double)lat1.get(0), (double)lon1.get(0), (double)lat1.get(1), (double)lon1.get(1));
	        List subList_LatLon = ha.LatLon(((double)lat1.get(0) + (double)lat1.get(1))/2, ((double)lon1.get(0) + (double)lon1.get(1))/2, (double)lat1.get(1), (double)lon1.get(1));
	        List abendessen_LatLon = ha.LatLon((double)lat2.get(0), (double)lon2.get(0), (double)lat2.get(1), (double)lon2.get(1));
	        
	        List subList = new ArrayList();
	        List breakfast = new ArrayList();
	        List breakfast1 = new ArrayList();
	        List luncheon = new ArrayList();
	        List abendessen = new ArrayList();
	        if(i2 != 0) {
	        	breakfast_LatLon = ha.LatLon((double)lat.get(0), (double)lon.get(0), (double)lat.get(1), (double)lon.get(1));
	        	breakfast = service.breaklunch(table, (double)breakfast_LatLon.get(0), (double)breakfast_LatLon.get(1), (double)breakfast_LatLon.get(2), (double)breakfast_LatLon.get(3));
	        }else {
	        	breakfast = service.breaklunch(table, (double)radius1.get(0), (double)radius1.get(1), (double)radius1.get(2), (double)radius1.get(3));
	        	breakfast1 = service.breaklunch(table, (double)radius2.get(0), (double)radius2.get(1), (double)radius2.get(2), (double)radius2.get(3));
	        	breakfast.removeAll(breakfast1);
	        }
	        
	        luncheon = service.breaklunch(table, (double)luncheon_LatLon.get(0), (double)luncheon_LatLon.get(1), (double)luncheon_LatLon.get(2), (double)luncheon_LatLon.get(3));
			subList = service.subList(table, (double)subList_LatLon.get(0), (double)subList_LatLon.get(1), (double)subList_LatLon.get(2), (double)subList_LatLon.get(3));
			abendessen = service.abendessen(table, (double)abendessen_LatLon.get(0), (double)abendessen_LatLon.get(1), (double)abendessen_LatLon.get(2), (double)abendessen_LatLon.get(3));
			
			System.out.println(breakfast.size());
			System.out.println(luncheon.size());
			System.out.println(subList.size());
			System.out.println(abendessen.size());
	    // 중복방지
	        
	        List sub = new ArrayList();
	        List subAll = new ArrayList();
	        
	        if((breakfast.size() == 0) || (luncheon.size() == 0) && (subList.size() == 0) || (abendessen.size() == 0)) {
	        	return null;
	        }
	        
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
	        			return null;
	        		}
	        	}
	        }
	        daySub.add(sub);
		}
		return daySub;
	}

	// finalList에 main이랑 서브일정 합치기 작업
	public List<SampleListDTO> dayList(List<List<SampleListDTO>> daySub, List<SampleListDTO> main) {
	    
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

		return dayList;
	}
	
	// 최종 일정 호출작업
	public List<SampleListDTO> finalList(List<List<SampleListDTO>> daySub, List<SampleListDTO> main) {
	    
		List<SampleListDTO> finalList = new ArrayList();
		List dayList = dayList(daySub, main);
		
		for(int i = 0; i < dayList.size(); i++) {
			finalList.addAll((List)dayList.get(i));
		}
		
		return finalList;
	}

	// 일차별로 그룹화 작업
	public Map<String, List<SampleListDTO>> groupByDay(List<List<SampleListDTO>> daySub, List<SampleListDTO> main) {
	    
		HashMap daymap = new HashMap();
		List dayList = dayList(daySub, main);
		
		for(int i = 0; i < main.size()/2; i++) {
			daymap.put((i+1)+"일차", dayList.get(i));
		}
		
		return daymap;
	}
	
}
