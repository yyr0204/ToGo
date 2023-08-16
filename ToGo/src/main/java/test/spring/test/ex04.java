package test.spring.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import test.spring.component.map.mapDTO;
import test.spring.component.song.PlanDTO;
import test.spring.component.song.SampleListDTO;
import test.spring.mapper.song.TripMapper;
import test.spring.repository.song.PermutationDAO;
import test.spring.repository.song.PlanListDAO;
import test.spring.service.song.TripService;
import test.spring.service.song.TripServiceImpl;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ex04 {
    public static void main(String[] args) throws ParseException {
        Map<String,String> map = new HashMap<>();
        map.put("name","yoon");
        map.put("age","29");
        map.put("phone","iphone");
        Iterator<String> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            String str = iterator.next();
            System.out.println(str);
        }
    }
}