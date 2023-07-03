package test.spring.controller.map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/map/*")
public class MapController {
    @RequestMapping("testMap")
    public String testMap(){
        return "/map/testMap";
    }
    @RequestMapping("/list")
    public String list(Model model){

        model.addAttribute("list","ASd");
        return "/map/list";
    }

}
