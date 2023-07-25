package test.spring.controller.map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import test.spring.component.map.mapDTO;
import test.spring.component.song.SampleListDTO;
import test.spring.repository.map.listUp;
import test.spring.service.map.mapService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        model.addAttribute("allList",listUp.list_up("서울","관광지"));
        model.addAttribute("lodgingList",listUp.list_up("서울","숙박"));
        return "/map/testMap";
    }
    @RequestMapping("/list")
    @ResponseBody
    public String list(Model model, Map<String, Objects> data,mapDTO dto){
        return "/map/list";
    }
}
