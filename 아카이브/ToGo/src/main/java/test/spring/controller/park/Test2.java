package test.spring.controller.park;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Test2 {

    @GetMapping("/test/2")
    public String getData(Model model) {
        String apiUrl = "https://www.mof.go.kr/statPortal/openapi/service/rest/getList.do";
        String apiKey = "7538bc3123a3477696be14d10aa48b0d";
        int formId = 5002350;
        int styleNum = 1;
        int startDt = 2021;
        int endDt = 2021;

        try {
            StringBuilder urlBuilder = new StringBuilder(apiUrl);
            urlBuilder.append("?key=" + URLEncoder.encode(apiKey, "UTF-8"));
            urlBuilder.append("&form_id=" + URLEncoder.encode(String.valueOf(formId), "UTF-8"));
            urlBuilder.append("&style_num=" + URLEncoder.encode(String.valueOf(styleNum), "UTF-8"));
            urlBuilder.append("&start_dt=" + URLEncoder.encode(String.valueOf(startDt), "UTF-8"));
            urlBuilder.append("&end_dt=" + URLEncoder.encode(String.valueOf(endDt), "UTF-8"));

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // 응답 처리
                List<Data> dataList = parseResponse(response.toString());
                model.addAttribute("dataList", dataList);
            } else {
                System.out.println("API request failed. Response code: " + responseCode);
                return "error"; // 에러 페이지로 리다이렉트 또는 에러 처리를 위한 뷰 반환
            }

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            return "error"; // 에러 페이지로 리다이렉트 또는 에러 처리를 위한 뷰 반환
        }

        return "/park/beach";
    }

    private List<Data> parseResponse(String response) {
        // 응답 데이터를 파싱하여 Data 객체의 리스트로 변환하는 로직을 구현
        // ...

        // 임시로 데이터 샘플 생성
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data("Name 1", "2021-01-01", "2021-12-31"));
        dataList.add(new Data("Name 2", "2021-03-15", "2021-11-30"));

        return dataList;
    }

    // 데이터 클래스
    public static class Data {
        private String name;
        private String startDate;
        private String endDate;

        public Data(String name, String startDate, String endDate) {
            this.name = name;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        // Getter 및 Setter 생략
    }
}