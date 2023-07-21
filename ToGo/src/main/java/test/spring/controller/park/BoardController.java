package test.spring.controller.park;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import test.spring.component.park.BeachResultData;
import test.spring.component.park.CmBoardDTO;
import test.spring.component.park.FaqBoardDTO;
import test.spring.component.park.FstvlDTO;
import test.spring.component.park.PageResolver;
import test.spring.component.park.QnaDTO;
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
	
	//FAQ
	@RequestMapping("/faqWriteForm")
	public String faqWriteForm() {
		return "/park/faq/faqWriteForm";
	}
	@RequestMapping("/faqWritePro")
	public String insert(FaqBoardDTO dto, Model model,HttpSession session) {
		// 나중에 관리자로 바꿔야함
		String memId = (String) session.getAttribute("memId");
		dto.setFaq_writer(memId);
		model.addAttribute("success",faqService.insert(dto));
		return "redirect:/board/faqList";
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
	public String qnaInsert(QnaDTO dto,HttpSession session) {
		String memId = (String) session.getAttribute("memId");
		dto.setWriter(memId);
		qnaservice.qnaInsert(dto);
		return "redirect:/board/qnaList";
	}
	@RequestMapping("/qnaList")
	public String list(Model model, HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") String pageNum, String option, String keyword
			,QnaDTO dto,@RequestParam(value = "memId", required = false) String memId) {
		memId = (String) session.getAttribute("memId");
		// 검색조건
		if (keyword != null) {
			dto.setOption(option);
			dto.setKeyword(keyword);
		}
		// 페이지네이션
		int pageSize = 10; // 페이지 당 게시글 갯수
		int page = 1;
		try {
			if (pageNum != null && !pageNum.equals("")) {
				page = Integer.parseInt(pageNum);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		// 리스트 총 갯수
		int total = qnaservice.totalList(dto);
		// 첫 글 번호
		int beginPage = (page - 1) * pageSize + 1;
		// 마지막 글 번호
		int endPage = beginPage + pageSize - 1;

		dto.setBeginPage(beginPage);
		dto.setEndPage(endPage);

		List<QnaDTO> boardList = qnaservice.qnaList(dto);
		PageResolver pr = new PageResolver(page, pageSize, total);

		model.addAttribute("boardList", boardList);
		model.addAttribute("pr", pr);
		model.addAttribute("memId", memId);
		model.addAttribute("option", option);
		model.addAttribute("keyword", keyword);

		return "/park/qna/qnaList";
	}
	//QNA 글 상세 화면 요청
	@RequestMapping("/qnaDetail")
	public String detail(int num, Model model) {
		//선택한 QNA 글에 대한 조회수 증가 처리
		qnaservice.qnaRead(num);
		//선택한 QNA 글 정보를 DB에 조회해와 상세 화면에 출력
		model.addAttribute("dto", qnaservice.qnaDetail(num));
		
		return "/park/qna/qnaDetail";
	} 
	//QNA 글 수정 화면 요청
	@RequestMapping("/qnaModifyForm")
	public String modify(int num, Model model) {
		//선택한 QNA 글 정보를 DB에서 조회해와 수정 화면에 출력
		model.addAttribute("dto", qnaservice.qnaDetail(num));
		return "/park/qna/qnaModify";
	} //modify()
	
	//QNA 글 수정 처리 요청
	@RequestMapping("/qnaModifyPro")
	public String update(QnaDTO dto, HttpSession session, String title,String content,@RequestParam(value = "num") int num) {
		//화면에서 변경한 정보를 DB에 저장한 후 상세 화면으로 연결
		dto.setNum(num);
		dto.setTitle(title);
		dto.setContent(content);
		qnaservice.qnaUpdate(dto);
		return "redirect:/board/qnaDetail?num=" + dto.getNum();
	} //update()	
	//QNA 글 삭제 처리 요청
	@RequestMapping("/qnaDelete")
	public String delete(int num) {
		//선택한 QNA 글을 DB에서 삭제한 후 목록 화면으로 연결
		qnaservice.qnaDelete(num);
		
		return "redirect:/board/qnaList";
	}
	//답글 쓰기 화면 요청==================================================================
	@RequestMapping("/qnaReplyForm")
	public String reply(Model model, int num) {
		//원글의 정보를 답글 쓰기 화면에서 알 수 있도록 한다.
		model.addAttribute("dto", qnaservice.qnaDetail(num));
		
		return "park/qna/qnaReply";
	} //reply()
	
	//신규 답글 저장 처리 요청==============================================================
	@RequestMapping("/qnaReplyPro")
	public String reply_insert(QnaDTO dto, HttpSession session) {
		//작성자는 관리자 인경우
//		dto.setWriter(((MemberVO) session.getAttribute("login_info")).getId());
		//임시
		String memId = (String) session.getAttribute("memId");
		dto.setWriter(memId);
		//화면에서 입력한 정보를 DB에 저장한 후 목록 화면으로 연결
		qnaservice.qnaReplyInsert(dto);
		return "redirect:/board/qnaList";
	}
	
	//축제 정보 슬라이드
	@RequestMapping("/fstvl")
	public String fstvl(FstvlDTO dto, Model model) {
		List<FstvlDTO> fstvlList = festivalService.fstvl(dto);
	    List<FstvlDTO> randomFstvlList = new ArrayList<>();
	    if (fstvlList.size() > 5) {
	        List<Integer> indexes = new ArrayList<>();
	        for (int i = 0; i < fstvlList.size(); i++) {
	            indexes.add(i);
	        }
	        Collections.shuffle(indexes); // 인덱스를 랜덤하게 섞음

	        for (int i = 0; i < 5; i++) {
	            randomFstvlList.add(fstvlList.get(indexes.get(i)));
	        }
	    } else {
	        randomFstvlList = fstvlList;
	    }
	    model.addAttribute("fstvlList", randomFstvlList);
	    return "/park/festival/fstvl";
	}
	//축제 리스트
	@RequestMapping("/fstvlList")
	public String fstvlList(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum, Model model,FstvlDTO dto, String option, String keyword) {
		// 검색조건
		if (keyword != null) {
			dto.setOption(option);
			dto.setKeyword(keyword);
		}
		// 페이지네이션
		int pageSize = 5; // 페이지 당 게시글 갯수
		int page = 1;
		try {
			if (pageNum != null && !pageNum.equals("")) {
				page = Integer.parseInt(pageNum);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		// 리스트 총 갯수
		int total = festivalService.selectFstvlCount(dto);
		// 첫 글 번호
		int beginPage = (page - 1) * pageSize + 1;
		// 마지막 글 번호
		int endPage = beginPage + pageSize - 1;
		dto.setBeginPage(beginPage);
		dto.setEndPage(endPage);

		List<FstvlDTO> fstvlList = festivalService.fstvlList(dto);
		PageResolver pr = new PageResolver(page, pageSize, total);

		model.addAttribute("fstvlList", fstvlList);
		model.addAttribute("pr", pr);
		model.addAttribute("option", option);
		model.addAttribute("keyword", keyword);

		return "/park/festival/fstvlList";
	}
	//해수욕장 api
	@GetMapping("/beach")
	public String getBeachInformation( Model model) {
		try {
		 StringBuilder urlBuilder = new StringBuilder("http://api.odcloud.kr/api/15056091/v1/uddi:e6b792cd-5f5f-4c74-867c-83159645f0ec"); /*URL*/
		 urlBuilder.append("?" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
		 urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode("264", "UTF-8")); /*한 페이지 결과 수*/
	     urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8") + "=g%2BdzVqHbtyZ4yDYOaF3yYrZr0sZPNvlIWf2PAg2uvpPpjJav%2Fm%2B%2Bbyjs5mbKyj1W17CfFilBfaxHTpMupA6%2FxQ%3D%3D"); /*Service Key*/
	     urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*XML/JSON 여부*/
		// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
		
		// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
		urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); /* 서비스별 추가 요청인자들*/
		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
		BufferedReader rd;

		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
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
		// JSON 데이터를 자바 객체로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		BeachResultData resultData = objectMapper.readValue(sb.toString(), BeachResultData.class);
		
        model.addAttribute("resultData", resultData);
	    
		}catch (Exception e){
			e.printStackTrace();
		}
	    
	    return "/park/beach";
	}
	
}
