package test.spring.controller.song;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import test.spring.component.song.sampleListDTO;
import test.spring.service.song.TripService;

@Controller
@RequestMapping("/trip/*")
public class TripController {

	@Autowired
	private TripService service;
	
	@RequestMapping("test")
	public String test(Model model) {
		List list = new ArrayList();
		List list2 = new ArrayList();
		List list3 = new ArrayList();
		
		for(int i = 0; i < 12; i++) {
			list.add(i);
		}
		
		list2.add("7/10");
		list2.add("7/11");
		list2.add("7/12");
		
		list3.add(3);
		list3.add(5);
		list3.add(4);
		System.out.println(list3.get(0));
		
		model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		
		return "/song/test";
	}
	
	@RequestMapping("length")
	public String length(Model model) {
		
		List list = service.sampleList();
		sampleListDTO dto;
		List main = new ArrayList();
		List main_string = new ArrayList();
		for(int i = 0; i < 6; i++) {
			dto = (sampleListDTO)list.get((int)(Math.random()*list.size()));
			double [] arr = {dto.getLat() , dto.getLon()};
			String [] name = {dto.getName() , dto.getAdress()}; 

			main.add(arr);
			main_string.add(name);
		}
		for(int i = 0; i < main.size(); i++) {
			double [] test = (double[])main.get(i);
			String[] test2 = (String[])main_string.get(i);
			
			System.out.println(test2[0] + " , " + test2[1]);
			System.out.println(test[0] + " , " + test[1]);
		}
		model.addAttribute("place1" , main_string.get(0));
		model.addAttribute("place2" , main_string.get(1));
		model.addAttribute("LatLon1" , main.get(0));
		model.addAttribute("LatLon2" , main.get(1));
		
		double [] a = (double[])main.get(0);
		System.out.println(a[0] + " , " + a[1]);
		double [] b = (double[])main.get(1);
		System.out.println(b[0] + " , " + b[1]);
		
		double dLat = Math.toRadians(b[0] - a[0]);
		double dLon = Math.toRadians(b[1] - a[1]);

		double x = Math.sin(dLat/2)* Math.sin(dLat/2)+ Math.cos(Math.toRadians(a[0]))* Math.cos(Math.toRadians(b[0]))* Math.sin(dLon/2)* Math.sin(dLon/2);
		double y = 2 * Math.atan2(Math.sqrt(x), Math.sqrt(1-x));
		double z = 6371 * y;    // Distance in m
		
		model.addAttribute("length" , z);
		System.out.println(z);
		
		return "/song/length";
	}
}