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
		List<sampleListDTO> main = new ArrayList();
		
		for(int i = 0; i < 6; i++) {
			dto = (sampleListDTO)list.get((int)(Math.random()*list.size()));
			main.add(dto);
		}
		model.addAttribute("main" , main);
		
		List length = new ArrayList();
		for(int i = 0; i < (main.size()-1); i++) {
				sampleListDTO sample1 = main.get(i);
				sampleListDTO sample2 = main.get(i+1);
				
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
		
		return "/song/length";
	}
	
}