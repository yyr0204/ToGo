package test.spring.controller.park;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/fstvl/*")
public class FestivalController {
	@RequestMapping("/list")
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://api.data.go.kr/openapi/tn_pubr_public_cltur_fstvl_api"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=Acwd%2BFH%2FWMtE76wtBVIxBBM00Iv%2FA9AqvlvL0Sqsao3vWd43v%2Beu%2BlZ1cfqUq%2B35S5%2B5FWsY3QnmXp1zm54qlQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*XML/JSON 여부*/
        urlBuilder.append("&" + URLEncoder.encode("fstvlNm","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*축제명*/
        urlBuilder.append("&" + URLEncoder.encode("opar","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*개최장소*/
        urlBuilder.append("&" + URLEncoder.encode("fstvlStartDate","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*축제시작일자*/
        urlBuilder.append("&" + URLEncoder.encode("fstvlEndDate","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*축제종료일자*/
        urlBuilder.append("&" + URLEncoder.encode("fstvlCo","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*축제내용*/
        urlBuilder.append("&" + URLEncoder.encode("mnnst","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*주관기관*/
        urlBuilder.append("&" + URLEncoder.encode("auspcInstt","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*주최기관*/
        urlBuilder.append("&" + URLEncoder.encode("suprtInstt","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*후원기관*/
        urlBuilder.append("&" + URLEncoder.encode("phoneNumber","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*전화번호*/
        urlBuilder.append("&" + URLEncoder.encode("homepageUrl","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*홈페이지주소*/
        urlBuilder.append("&" + URLEncoder.encode("relateInfo","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*관련정보*/
        urlBuilder.append("&" + URLEncoder.encode("rdnmadr","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*소재지도로명주소*/
        urlBuilder.append("&" + URLEncoder.encode("lnmadr","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*소재지지번주소*/
        urlBuilder.append("&" + URLEncoder.encode("latitude","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*위도*/
        urlBuilder.append("&" + URLEncoder.encode("longitude","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*경도*/
        urlBuilder.append("&" + URLEncoder.encode("referenceDate","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*데이터기준일자*/
        urlBuilder.append("&" + URLEncoder.encode("instt_code","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*제공기관코드*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
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
        System.out.println(sb.toString());
    }
	@RequestMapping("/test")
	public String test() {
		return "/park/fstvl";
	}
	
}