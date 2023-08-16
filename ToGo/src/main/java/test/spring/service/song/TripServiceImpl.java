package test.spring.service.song;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.spring.component.map.userDTO;
import test.spring.component.song.CityimgDTO;
import test.spring.component.song.ImageBoard1DTO;
import test.spring.component.song.SampleListDTO;
import test.spring.mapper.song.TripMapper;

@Service
public class TripServiceImpl implements TripService{

	@Autowired
	private TripMapper mapper;
	
	@Override
	public List<SampleListDTO> mainList(String table, List userAtmosphere) {
		table = table+"_main";
		
		return mapper.mainList(table);
	}
	
	@Override
	public List<SampleListDTO> mainList(String table, List userAtmosphere, double minLat, double maxLat, double minLon, double maxLon) {
		table = table+"_main";
		
		return mapper.mainList2(table, minLat, maxLat, minLon, maxLon);
	}
	
	@Override
	public List<SampleListDTO> subList(String table, List userAtmosphere, double minLat, double maxLat, double minLon, double maxLon) {
		table = table+"_sub";
		
		String user;
		if(userAtmosphere.size() == 0) {
			user = table;
		}else {
			user = "(select * from " + table + " where";
			for(int i = 0 ; i < userAtmosphere.size(); i++) {
				if(i < userAtmosphere.size()-1) {
					user = user + " atmosphere Like '%" + userAtmosphere.get(i) + "%' or";
				}else if(i == userAtmosphere.size()-1) {
					user = user +  " atmosphere Like '%" + userAtmosphere.get(i) + "%')";
				}
			}
		}
		System.out.println(user);
		return mapper.subList(user, minLat, maxLat, minLon, maxLon);
	}
	
	@Override
	public List<SampleListDTO> breaklunch(String table, List userAtmosphere, double minLat, double maxLat, double minLon, double maxLon) {
		table = table+"_sub";
		
		String user;
		if(userAtmosphere.size() == 0) {
			user = table;
		}else {
			user = "(select * from " + table + " where";
			for(int i = 0 ; i < userAtmosphere.size(); i++) {
				if(i < userAtmosphere.size()-1) {
					user = user + " atmosphere Like '%" + userAtmosphere.get(i) + "%' or";
				}else if(i == userAtmosphere.size()-1) {
					user = user +  " atmosphere Like '%" + userAtmosphere.get(i) + "%')";
				}
			}
		}
		
		return mapper.breaklunch(user, minLat, maxLat, minLon, maxLon);
	}

	@Override
	public List<SampleListDTO> abendessen(String table, List userAtmosphere, double minLat, double maxLat, double minLon, double maxLon) {
		table = table+"_sub";
		
		String user;
		if(userAtmosphere.size() == 0) {
			user = table;
		}else {
			user = "(select * from " + table + " where";
			for(int i = 0 ; i < userAtmosphere.size(); i++) {
				if(i < userAtmosphere.size()-1) {
					user = user + " atmosphere Like '%" + userAtmosphere.get(i) + "%' or";
				}else if(i == userAtmosphere.size()-1) {
					user = user +  " atmosphere Like '%" + userAtmosphere.get(i) + "%')";
				}
			}
		}
		
		return mapper.abendessen(user, minLat, maxLat, minLon, maxLon);
	}

	@Override
	public SampleListDTO cityList(Map<String,String>place_bag) {
		return mapper.cityList(place_bag);
	}

	@Override
	public List<CityimgDTO> cityimgList() {
		return mapper.cityimgList();
	}
	
	@Override
	public String tableName(String area) {
		
		return mapper.tableName(area);
	}
	
	@Override
	public String userMbti(String memId) {

		return mapper.userMbti(memId);
	}
	
	@Override
	public List userAtmosphere(String mbti) {

		return mapper.userAtmosphere(mbti);
	}
	
	@Override
	public List<ImageBoard1DTO> wePlan() {
		
		return mapper.wePlan();
	}
	
	@Override
	public List<userDTO> userPlan(String memId) {
		System.out.println("userPlan : " + memId);
		return mapper.userPlan(memId);
	}
	
	@Override
	public List<userDTO> userPlan2(String plan_num) {

		return mapper.userPlan2(plan_num);
	}
	
	@Override
	public List<SampleListDTO> popular(String table) {
		
		return mapper.popular(table);
	}

	@Override
	public int userPlanCount(String memId) {
		return mapper.userPlanCount(memId);
	}

}
