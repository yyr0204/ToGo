package test.spring.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import test.spring.component.map.mapDTO;
import test.spring.component.song.SampleListDTO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ex03 {
    public static void main(String[] args) {
        for(int y=1; y<=4;y++) {
            int listCount = (int) (Math.random() * 3 + 2);
            System.out.println("listCount="+listCount);
            for (int n = 0; n < listCount; n++) {
                int random = (int) (Math.random() * 100);
                System.out.println("random="+random);
            }

        }
    }
}
