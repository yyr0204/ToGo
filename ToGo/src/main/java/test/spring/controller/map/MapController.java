package test.spring.controller.map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import test.spring.component.map.listDTO;
import test.spring.component.map.mapDTO;
import test.spring.component.map.userDTO;
import test.spring.component.song.PlanDTO;
import test.spring.repository.map.listUp;
import test.spring.service.choi.LoginService;
import test.spring.service.map.mapService;
import test.spring.service.map.userService;
import test.spring.service.song.TripService;

import javax.servlet.http.HttpSession;
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
    @Autowired
    private LoginService ls;

    @RequestMapping("tourMap")
    public String tourMap(Model model, PlanDTO dto) {
        try {
            mapDTO dto2 = service.latlon(dto.getArea());
            model.addAttribute("allList", listUp.list_up("서울", "관광지"));
            model.addAttribute("lodgingList", listUp.list_up("서울", "숙박"));
            model.addAttribute("tourInfo", dto);
            model.addAttribute("latlon", dto2);
            return "/map/tourMap";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/map/tourMap";
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
        return list;
    }
    @RequestMapping("search_list")
    public @ResponseBody List<mapDTO> search_list(mapDTO dto) {
        Map<String,String> map =new HashMap<>();
        map.put("start", String.valueOf(dto.getStart())); //페이징 처리를 위해 갯수지정
        map.put("end",String.valueOf(dto.getEnd())); //페이징 처리를 위해 갯수지정
        map.put("area",dto.getArea()); //검색하는 지역을 구분하기 위해 지역명 지정
        map.put("str",dto.getStr()); //검색하는 검색어을 담는부분
        List<mapDTO> list = service.search(map);
        return list;
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

    @RequestMapping("/schedulerSave")
    public @ResponseBody String schedulerSave(@RequestBody JSONObject jsonObject) { /////json 형식으로 파라미터를 넘겨받음
        int count = 0;
        String area = String.valueOf(jsonObject.get("area")); //객체에서 db에 저장할 여행지의 지역을 꺼냄
        int day = Integer.parseInt(String.valueOf(jsonObject.get("day"))); //객체에서 여행의 날짜를 꺼냄
        LinkedHashMap<String,Date> days = (LinkedHashMap<String, Date>) jsonObject.get("days");//여행의 시작날짜와 끝날짜가 담긴 객체를 맵형식으로 꺼냄
        Map<String, String> user_info = new HashMap<>(); //db에 넘길 값들을 넣기위해 HashMap을 생성
        user_info.put("id", (String) jsonObject.get("id"));
        user_info.put("title", (String) jsonObject.get("title"));
        user_info.put("startDay",String.valueOf(days.get("start")));
        user_info.put("endDay",String.valueOf(days.get("end")));
        service2.user_tour_info(user_info);
        int result = Integer.parseInt(user_info.get("plan_num")); //insert한 값을 selectKey로
        // 바로 조회하여 넘겨받은 map에 새로운 객체로 담겨져 넘겨받음
        LinkedHashMap<String, LinkedHashMap<String, Objects>> user_schedule = (LinkedHashMap<String, LinkedHashMap<String, Objects>>) jsonObject.get("user_schedule");
        while (count < user_schedule.size()) { // 이용자의 상세한 여행 일정을 저장하기 위한 반복문
            List<String> user_scheduler = new ArrayList<>();
            userDTO dto = new userDTO();
            dto.setArea(area); //여행지 설정
            dto.setPlan_num(result); //생성된 여행일정의 고유넘버
            dto.setDay(day);
            count++;
            int count2 = 0;
            List<LinkedHashMap<String, Objects>> list = (List) user_schedule.get(count + "일차");
            while (10 > count2) {
                if (count2 < list.size()) {
                    try {
                        LinkedHashMap<String, Objects> map = list.get(count2);
                        user_scheduler.add(String.valueOf(map.get("name")));
                    }catch (Exception e){
                        user_scheduler.add("null");
                    }
                } else {
                    user_scheduler.add("null");
                }
                count2++;
            }
            dto.setCourse(user_scheduler);
            service2.add_user_schedule(dto);
            service2.placeCount(dto);
        }
        return "/map/test1";
    }
    @RequestMapping("/signup")
    public String login(){
        return "/map/signup";
    }
    @RequestMapping("/id_chk")
    public@ResponseBody boolean userCheck(@RequestParam(value="id")String id){
        System.out.println(id);
        int result = ls.check2(id);
        if(result==0){
            return true;
        }else if(result>0){
            return false;
        }
        return false;
    }

}
