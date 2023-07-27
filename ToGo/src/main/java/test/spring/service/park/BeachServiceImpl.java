package test.spring.service.park;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import test.spring.component.park.BeachResultData;

@Service("beachService")
public class BeachServiceImpl implements BeachService{
	public BeachResultData getBeachInformation() {
        try {
            StringBuilder urlBuilder = new StringBuilder("http://api.odcloud.kr/api/15056091/v1/uddi:e6b792cd-5f5f-4c74-867c-83159645f0ec");
            urlBuilder.append("?" + URLEncoder.encode("page", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("perPage", "UTF-8") + "=" + URLEncoder.encode("264", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("serviceKey", "UTF-8") + "=g%2BdzVqHbtyZ4yDYOaF3yYrZr0sZPNvlIWf2PAg2uvpPpjJav%2Fm%2B%2Bbyjs5mbKyj1W17CfFilBfaxHTpMupA6%2FxQ%3D%3D");
            urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode("20230301", "UTF-8"));
            
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
           // System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;

            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            // 데이터 타입을 JSON으로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            BeachResultData resultData = objectMapper.readValue(sb.toString(), BeachResultData.class);

            return resultData;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // 예외 처리 방법을 결정한 후에 적절하게 변경해주세요.
        }
    }
}
