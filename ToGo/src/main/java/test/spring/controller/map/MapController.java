package test.spring.controller.map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import test.spring.component.map.mapDTO;
import test.spring.component.song.SampleListDTO;
import test.spring.repository.map.listUp;
import test.spring.service.map.mapService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/map/*")
public class MapController {
    @Autowired
    private mapService service;
    @RequestMapping("testMap")
    public String testMap(Model model, mapDTO dto){
        int day = 3;
        Map<Integer,List<SampleListDTO>> places2= listUp.mainList("관광지",day);
        model.addAttribute("places2",places2);
        model.addAttribute("allList",listUp.list_up("서울"));
        return "/map/testMap";
    }
    @RequestMapping("/list")
    public String list(Model model){

        model.addAttribute("list","test");
        return "/map/list";
    }
    @RequestMapping("/search")
    public String search(HttpServletRequest request){
        System.out.println(listUp.list_up(request.getParameter("search")));
        return "#";
    }



}
