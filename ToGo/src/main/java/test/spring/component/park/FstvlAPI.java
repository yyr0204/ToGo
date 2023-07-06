package test.spring.component.park;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class FstvlAPI {

    public FstvlDTO getFestivals() {
        String apiUrl = "http://api.data.go.kr/openapi/tn_pubr_public_cltur_fstvl_api";
        String serviceKey = "YOUR_SERVICE_KEY";
        String numOfRows = "10";
        String pageNo = "1";
        String mobileOS = "YOUR_MOBILE_OS";
        String mobileApp = "YOUR_MOBILE_APP";
        String type = "json";
        String listYN = "Y";
        String arrange = "D";
        String eventStartDate = "2023-07-05";

        // API 호출을 위한 매개변수 설정
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("serviceKey", "g%2BdzVqHbtyZ4yDYOaF3yYrZr0sZPNvlIWf2PAg2uvpPpjJav%2Fm%2B%2Bbyjs5mbKyj1W17CfFilBfaxHTpMupA6%2FxQ%3D%3D")
                .queryParam("numOfRows", 10)
                .queryParam("pageNo", 1)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "AppTest")
                .queryParam("_type", "json")
                .queryParam("listYN", "Y")
                .queryParam("arrange", "A")
                .queryParam("eventStartDate", 20170901);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FstvlDTO> response = restTemplate.getForEntity(builder.toUriString(), FstvlDTO.class);

        return response.getBody();
    }
}