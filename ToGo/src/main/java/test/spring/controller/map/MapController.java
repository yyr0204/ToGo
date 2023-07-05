package test.spring.controller.map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import test.spring.component.map.mapDTO;
import test.spring.service.map.mapService;

import java.util.List;

@Controller
@RequestMapping("/map/*")
public class MapController {
    @Autowired
    private mapService service;
    @RequestMapping("testMap")
    public String testMap(Model model, mapDTO dto){
        List<mapDTO> places= service.place();
        model.addAttribute("places",places);
        return "/map/testMap";
    }
    @RequestMapping("/list")
    public String list(Model model){

        model.addAttribute("list","test");
        return "/map/list";
    }

}
