package test.spring.controller.map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import test.spring.component.choi.LoginDTO;
import test.spring.component.map.mapDTO;
import test.spring.component.map.userDTO;
import test.spring.component.song.PlanDTO;
import test.spring.component.song.SampleListDTO;
import test.spring.repository.map.listUp;
import test.spring.service.map.mapService;
import test.spring.service.map.userService;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/map/*")
public class MapController {
    @Autowired
    private mapService service;
    @Autowired
    private userService service2;
    @RequestMapping("tourMap")
    public String tourMap(Model model, PlanDTO dto){
        try {
            System.out.println(dto);
            mapDTO dto2 = service.latlon(dto.getArea());
            System.out.println(dto2);
            model.addAttribute("allList", listUp.list_up("서울", "관광지"));
            model.addAttribute("lodgingList", listUp.list_up("서울", "숙박"));
            model.addAttribute("tourInfo", dto);
            model.addAttribute("latlon", dto2);
            return "/map/testMap";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/map/testMap";
    }
    @RequestMapping("mapInfo")
    public @ResponseBody mapDTO mapInfo(mapDTO dto){
        System.out.println(dto.getName());
        dto = service.latlon(dto.getName());
        System.out.println(dto);
        return dto;
    }
    @RequestMapping("testMap")
    public String testMap(Model model, PlanDTO dto){
        try {
            model.addAttribute("allList", listUp.list_up("서울", "관광지"));
            model.addAttribute("lodgingList", listUp.list_up("서울", "숙박"));
            return "/map/testMap";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/map/testMap";
    }
    @RequestMapping("/list")
    public String list(Model model, Map<String, Objects> data,mapDTO dto){
        return "/map/list";
    }
    @RequestMapping("/myPage")
    public String myPage(userDTO dto, Model model, HttpSession session){
        String id = session.getAttribute("memId").toString();
        dto = service2.profile_inquiry(id);
        System.out.println(dto);
        model.addAttribute("profile",dto);
        return "/map/profile";
    }
}
