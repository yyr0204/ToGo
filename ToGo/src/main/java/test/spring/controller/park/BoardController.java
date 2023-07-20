package test.spring.controller.park;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import test.spring.component.park.BeachResultData;
import test.spring.component.park.FaqBoardDTO;
import test.spring.component.park.FstvlDTO;
import test.spring.component.park.QnaDTO;
import test.spring.component.park.QnaPage;
import test.spring.service.park.FaqService;
import test.spring.service.park.FestivalService;
import test.spring.service.park.QnaService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	private FaqService faqService;
	@Autowired 
	private QnaService qnaservice;
	@Autowired 
	private FestivalService festivalService;
	@Autowired 
	private QnaPage page;
	
	//FAQ
	@RequestMapping("/faqWriteForm")
	public String faqWriteForm() {
		return "/park/faq/faqWriteForm";
	}
	@RequestMapping("/faqWritePro")
	public String insert(FaqBoardDTO dto, Model model) {
		int isTrue = faqService.insert(dto);
		faqService.insert(dto);
		model.addAttribute("isTrue",isTrue);
		return "/park/faq/faqList";
	}
	@RequestMapping("/faqList")
	public String faqList(FaqBoardDTO dto, Model model) {
	    List<FaqBoardDTO> faqList = faqService.faqList(dto);
	    model.addAttribute("faqList", faqList);
	    return "/park/faq/faqList";
	}
	//QnA
	@RequestMapping("/qnaWriteForm")
	public String qnaWriteForm() {
		return "/park/qna/qnaWriteForm";
	}
	@RequestMapping("/qnaInsert")
	public String qnaInsert(QnaDTO dto) {
		qnaservice.qnaInsert(dto);
		return "/park/qna/qnaWriteForm";
	}
	@RequestMapping("/qnaList")
	public String list(Model model, HttpSession session, @RequestParam(defaultValue = "1") int curPage, String search, String keyword) {
//		//QNA Å¬ï¿½ï¿½ ï¿½Ï¸ï¿½ adminï¿½ï¿½ï¿½ï¿½ ï¿½Úµï¿½ ï¿½Î±ï¿½ï¿½ï¿½
//		HashMap<String, String> map = new HashMap<String, String>();
//		//HashMap : ï¿½ï¿½ï¿½ï¿½ï¿½Í¸ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½Ú·ï¿½ ï¿½ï¿½ï¿½ï¿½
//		map.put("id", "admin");
//		map.put("pw", "1234");
//		session.setAttribute("login_info", member.member_login(map));
//		session.setAttribute("category", "qna");
		
		//DBï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ ï¿½ï¿½È¸ï¿½Ø¿ï¿½ È­ï¿½é¿¡ ï¿½ï¿½ï¿½
		page.setCurPage(curPage);
		page.setSearch(search);
		page.setKeyword(keyword);
		model.addAttribute("page", qnaservice.qnaList(page));
		return "/park/qna/qnaList";
	}
	//QNA ï¿½ï¿½ ï¿½ï¿½ È­ï¿½ï¿½ ï¿½ï¿½Ã»
		@RequestMapping("/qnaDetail")
		public String detail(int num, Model model) {
			//ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ QNA ï¿½Û¿ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½È¸ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ Ã³ï¿½ï¿½
			qnaservice.qnaDetail(num);
			
			//ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ QNA ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ DBï¿½ï¿½ ï¿½ï¿½È¸ï¿½Ø¿ï¿½ ï¿½ï¿½ È­ï¿½é¿¡ ï¿½ï¿½ï¿½
			model.addAttribute("dto", qnaservice.qnaDetail(num));
			model.addAttribute("crlf", "\r\n");
			model.addAttribute("page", page);
			
			return "/park/qna/qnaDetail";
		} 
		
<<<<<<< HEAD
	//ÃàÁ¦ Á¤º¸ ½½¶óÀÌµå
	@RequestMapping("/fstvl")
	public String fstvl(FstvlDTO dto, Model model) {
	    List<FstvlDTO> fstvlList = festivalService.fstvlList(dto);

	    List<FstvlDTO> randomFstvlList = new ArrayList<>();
	    if (fstvlList.size() > 5) {
	        List<Integer> indexes = new ArrayList<>();
	        for (int i = 0; i < fstvlList.size(); i++) {
	            indexes.add(i);
	        }
	        Collections.shuffle(indexes); // ÀÎµ¦½º¸¦ ·£´ýÇÏ°Ô ¼¯À½
=======
		//ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
		@RequestMapping("/fstvl")
		public String fstvlList(FstvlDTO dto, Model model) {
		    List<FstvlDTO> fstvlList = festivalService.fstvlList(dto);

		    List<FstvlDTO> randomFstvlList = new ArrayList<>();
		    if (fstvlList.size() > 5) {
		        List<Integer> indexes = new ArrayList<>();
		        for (int i = 0; i < fstvlList.size(); i++) {
		            indexes.add(i);
		        }
		        Collections.shuffle(indexes); // ï¿½Îµï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Ï°ï¿½ ï¿½ï¿½ï¿½ï¿½
>>>>>>> develop_Song

	        for (int i = 0; i < 5; i++) {
	            randomFstvlList.add(fstvlList.get(indexes.get(i)));
	        }
	    } else {
	        randomFstvlList = fstvlList;
	    }

<<<<<<< HEAD
	    model.addAttribute("fstvlList", randomFstvlList);
	    return "/park/festival/fstvl";
	}
	//ÃàÁ¦ ¸®½ºÆ®
	@RequestMapping("/fstvlList")
	public String fstvlList(FstvlDTO dto, Model model) {
	    List<FstvlDTO> fstvlList = festivalService.fstvlList(dto);
	    model.addAttribute("fstvlList", fstvlList);
	    return "/park/festival/fstvlList";
	}

	//ÇØ¼ö¿åÀå api
	@GetMapping("/beach")
	public String getBeachInformation( Model model) {
		try {
		 StringBuilder urlBuilder = new StringBuilder("http://api.odcloud.kr/api/15056091/v1/uddi:e6b792cd-5f5f-4c74-867c-83159645f0ec"); /*URL*/
		 urlBuilder.append("?" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*ÆäÀÌÁö ¹øÈ£*/
		 urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode("264", "UTF-8")); /*ÇÑ ÆäÀÌÁö °á°ú ¼ö*/
	     urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8") + "=g%2BdzVqHbtyZ4yDYOaF3yYrZr0sZPNvlIWf2PAg2uvpPpjJav%2Fm%2B%2Bbyjs5mbKyj1W17CfFilBfaxHTpMupA6%2FxQ%3D%3D"); /*Service Key*/
	     urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*XML/JSON ¿©ºÎ*/
		// »óÀ§ 5°³´Â ÇÊ¼öÀûÀ¸·Î ¼ø¼­¹Ù²ÙÁö ¾Ê°í È£ÃâÇØ¾ß ÇÕ´Ï´Ù.
		
		// ¼­ºñ½ºº° Ãß°¡ ¿äÃ» ÀÎÀÚÀÌ¸ç ÀÚ¼¼ÇÑ ³»¿ëÀº °¢ ¼­ºñ½ºº° '¿äÃ»ÀÎÀÚ'ºÎºÐ¿¡ ÀÚ¼¼È÷ ³ª¿Í ÀÖ½À´Ï´Ù.
		urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); /* ¼­ºñ½ºº° Ãß°¡ ¿äÃ»ÀÎÀÚµé*/
		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode()); /* ¿¬°á ÀÚÃ¼¿¡ ´ëÇÑ È®ÀÎÀÌ ÇÊ¿äÇÏ¹Ç·Î Ãß°¡ÇÕ´Ï´Ù.*/
		BufferedReader rd;

		// ¼­ºñ½ºÄÚµå°¡ Á¤»óÀÌ¸é 200~300»çÀÌÀÇ ¼ýÀÚ°¡ ³ª¿É´Ï´Ù.
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
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
		// JSON µ¥ÀÌÅÍ¸¦ ÀÚ¹Ù °´Ã¼·Î º¯È¯
		ObjectMapper objectMapper = new ObjectMapper();
		BeachResultData resultData = objectMapper.readValue(sb.toString(), BeachResultData.class);
		
        model.addAttribute("resultData", resultData);
	    
		}catch (Exception e){
			e.printStackTrace();
		}
	    
	    return "/park/beach";
	}
=======
		    model.addAttribute("fstvlList", randomFstvlList);
		    return "/song/main";
		}
//		@GetMapping("/scrape-and-save")
//		public String scrapeAndSaveFestivals() {
//		    String testURL = "https://www.mcst.go.kr/kor/s_culture/festival/festivalList.jsp?pMenuCD=&pCurrentPage=%d&pSearchType=&pSearchWord=&pSeq=&pSido=&pOrder=&pPeriod=&fromDt=&toDt=";
//
//		    // Å©ï¿½Ñ¸ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Íºï¿½ï¿½Ì½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Æ®
//		    for (int i = 1; i <= 12; i++) {
//		        String url = String.format(testURL, i);
//		        festivalService.testCrawling(url);
//		    }
//
//		    return "/park/fstvl";
//		}

>>>>>>> develop_Song
}
