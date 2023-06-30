package test.spring.controller.song;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trip/*")
public class TripController {

//	@Autowired
//	private TripService service;
	
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
}