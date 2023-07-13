package test.spring.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
        String url1 = "http://www.localdata.go.kr/platform/rest/TO0/openDataApi?authKey=idPz=k173GcUqPFYGLnMH=i1lW8adqvBY3K=nu8A1GA=&resultType=json&state=01&pageSize=500&pageIndex=10";
        try {
            URL url = new URL(url1);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String result = bufferedReader.readLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject response = (JSONObject) jsonObject.get("result");
            JSONObject body = (JSONObject) response.get("body");
            JSONArray rows = (JSONArray) body.get("rows");
            JSONObject row = (JSONObject) rows.get(0);
            JSONArray rowList = (JSONArray) row.get("row");
            System.out.println(rowList.size());


//            for (Object jb : item) {
//                System.out.println(jb);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
