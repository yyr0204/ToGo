package test.spring.controller.park;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import test.spring.component.park.FstvlDTO;

@Controller
@RequestMapping("/fstvl/*")
public class fstvlTest {
    @RequestMapping("/test")
    public String getList(Model model) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=g%2BdzVqHbtyZ4yDYOaF3yYrZr0sZPNvlIWf2PAg2uvpPpjJav%2Fm%2B%2Bbyjs5mbKyj1W17CfFilBfaxHTpMupA6%2FxQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("listYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("arrange","UTF-8") + "=" + URLEncoder.encode("A", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("eventStartDate","UTF-8") + "=" + URLEncoder.encode("20170901", "UTF-8")); 

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
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
        String response = sb.toString();
        
        // 응답을 파싱하여 festivalData 리스트를 생성하는 코드
        List<FstvlDTO> festivals = parseFestivalData(response);
        
        model.addAttribute("festivals", festivals);
        
        return "/festivals";
    }

    private List<FstvlDTO> parseFestivalData(String response) {
        List<FstvlDTO> festivalData = new ArrayList<>();

        try {
            // XML 파서 설정
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            ByteArrayInputStream input = new ByteArrayInputStream(response.getBytes("UTF-8"));
            Document document = builder.parse(input);

            // "response" 엘리먼트 아래의 "body" 엘리먼트를 찾습니다.
            Element responseElement = document.getDocumentElement();
            Element bodyElement = (Element) responseElement.getElementsByTagName("body").item(0);

            // "body" 엘리먼트 아래의 "items" 엘리먼트를 찾습니다.
            Element itemsElement = (Element) bodyElement.getElementsByTagName("items").item(0);

            // "items" 엘리먼트 아래의 모든 "item" 엘리먼트를 찾습니다.
            NodeList itemNodeList = itemsElement.getElementsByTagName("item");

            for (int i = 0; i < itemNodeList.getLength(); i++) {
                Node itemNode = itemNodeList.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) itemNode;

                    // "item" 엘리먼트에서 필요한 데이터를 추출합니다.
                    String title = getElementValue(itemElement, "title");
                    String firstimage = getElementValue(itemElement, "firstimage");
                    String eventstartdate = getElementValue(itemElement, "eventstartdate");
                    String eventenddate = getElementValue(itemElement, "eventenddate");

                    // Festival 객체를 생성하고 리스트에 추가합니다.
                    FstvlDTO festival = new FstvlDTO();
                    if (title != null) {
                        festival.setTitle(title);
                    }
                    if (firstimage != null) {
                        festival.setFirstimage(firstimage);
                    }
                    if (eventstartdate != null) {
                        festival.setEventstartdate(eventstartdate);
                    }
                    if (eventenddate != null) {
                        festival.setEventenddate(eventenddate);
                    }

                    festivalData.add(festival);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return festivalData;
    }

    // XML 엘리먼트의 값을 가져오는 유틸리티 메소드
    private String getElementValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            return node.getTextContent();
        } else {
            System.out.println("Element value for tag " + tagName + " not found.");
            return ""; // 엘리먼트 값이 없을 경우 빈 문자열을 반환하도록 수정
        }
    }

}
