package test.spring.controller.map;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import test.spring.component.choi.LoginDTO;
import test.spring.component.map.listDTO;
import test.spring.component.map.mapDTO;
import test.spring.component.map.userDTO;
import test.spring.component.song.PlanDTO;
import test.spring.component.song.SampleListDTO;
import test.spring.repository.map.listUp;
import test.spring.service.map.mapService;
import test.spring.service.map.userService;
import test.spring.service.song.TripService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.*;

@Controller
@RequestMapping("/map/*")
public class MapController {
    @Autowired
    private mapService service;
    @Autowired
    private userService service2;
    @Autowired
    private TripService tripService;

    @RequestMapping("tourMap")
    public String tourMap(Model model, PlanDTO dto) {
        try {
            mapDTO dto2 = service.latlon(dto.getArea());
            model.addAttribute("allList", listUp.list_up("서울", "관광지"));
            model.addAttribute("lodgingList", listUp.list_up("서울", "숙박"));
            model.addAttribute("tourInfo", dto);
            model.addAttribute("latlon", dto2);
            return "/map/testMap";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/map/testMap";
    }

    @RequestMapping("mapInfo")
    public @ResponseBody mapDTO mapInfo(mapDTO dto) {
        dto = service.latlon(dto.getName());
        return dto;
    }

    @RequestMapping("place_list")
    public @ResponseBody List<mapDTO> place_list(listDTO dto) {
        String area = tripService.tableName(dto.getArea());
        dto.setArea(area + "_main");
        List<mapDTO> list = service.place_list(dto);
        System.out.println(list);
        return list;
    }
    @RequestMapping("search_list")
    public @ResponseBody List<String> search_list(Map<String,String>map) {
        List<String> list =  service.search(map);
        System.out.println(list);
        return list;
    }

    @RequestMapping("testMap")
    public String testMap(Model model, PlanDTO dto) {
        try {
            model.addAttribute("allList", listUp.list_up("서울", "관광지"));
            model.addAttribute("lodgingList", listUp.list_up("서울", "숙박"));
            return "/map/testMap";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/map/testMap";
    }

    @RequestMapping("/list")
    public String list(Model model, Map<String, Objects> data, mapDTO dto) {
        dto.setName("테스트");
        model.addAttribute("list", dto);
        return "/map/list";
    }

    @RequestMapping("/myPage")
    public String myPage(userDTO dto, Model model, HttpSession session) {
        String id = session.getAttribute("memId").toString();
        dto = service2.profile_inquiry(id);
        System.out.println(dto);
        model.addAttribute("profile", dto);
        return "/map/profile";
    }

    @RequestMapping("/test")
    public String test01() {
        return "/map/test1";
    }

    @RequestMapping("/test2")
    public @ResponseBody String test02(@RequestBody JSONObject jsonObject) {
        int count = 0;
        String area = String.valueOf(jsonObject.get("area"));
        int day = Integer.parseInt(String.valueOf(jsonObject.get("day")));
        LinkedHashMap<String,Date> days = (LinkedHashMap<String, Date>) jsonObject.get("days");
        Map<String, String> user_info = new HashMap<>();
        user_info.put("id", (String) jsonObject.get("id"));
        user_info.put("title", (String) jsonObject.get("title"));
        user_info.put("startDay",String.valueOf(days.get("start")));
        user_info.put("endDay",String.valueOf(days.get("end")));
        service2.user_tour_info(user_info);
        int result = Integer.parseInt(user_info.get("plan_num"));
        LinkedHashMap<String, LinkedHashMap<String, Objects>> user_schedule = (LinkedHashMap<String, LinkedHashMap<String, Objects>>) jsonObject.get("user_schedule");
        while (count < user_schedule.size()) {
            List<String> user_scheduler = new ArrayList<>();
            userDTO dto = new userDTO();
            dto.setArea(area);
            dto.setPlan_num(result);
            dto.setDay(day);
            count++;
            int count2 = 0;
            List<LinkedHashMap<String, Objects>> list = (List) user_schedule.get(count + "일차");
            while (10 > count2) {
                if (count2 < list.size()) {
                    LinkedHashMap<String, Objects> map = list.get(count2);
                    user_scheduler.add(String.valueOf(map.get("name")));
                } else {
                    user_scheduler.add("null");
                }
                count2++;
            }
            dto.setCourse(user_scheduler);
            service2.add_user_schedule(dto);
        }
        return "/map/test1";
    }

}
