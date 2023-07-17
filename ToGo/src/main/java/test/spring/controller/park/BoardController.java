package test.spring.controller.park;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import test.spring.component.park.BeachResultData;
import test.spring.component.park.CmBoardDTO;
import test.spring.component.park.FaqBoardDTO;
import test.spring.component.park.FstvlDTO;
import test.spring.component.park.PageResolver;
import test.spring.component.park.QnaDTO;
import test.spring.component.park.QnaPage;
import test.spring.service.park.CmService;
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
	private CmService cmservice;
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
//		//QNA 클릭 하면 admin으로 자동 로그인
//		HashMap<String, String> map = new HashMap<String, String>();
//		//HashMap : 데이터를 담을 자료 구조
//		map.put("id", "admin");
//		map.put("pw", "1234");
//		session.setAttribute("login_info", member.member_login(map));
//		session.setAttribute("category", "qna");
		
		//DB에서 글 목록 조회해와 화면에 출력
		page.setCurPage(curPage);
		page.setSearch(search);
		page.setKeyword(keyword);
		model.addAttribute("page", qnaservice.qnaList(page));
		return "/park/qna/qnaList";
	}
	//QNA 글 상세 화면 요청
		@RequestMapping("/qnaDetail")
		public String detail(int num, Model model) {
			//선택한 QNA 글에 대한 조회수 증가 처리
			qnaservice.qnaDetail(num);
			
			//선택한 QNA 글 정보를 DB에 조회해와 상세 화면에 출력
			model.addAttribute("dto", qnaservice.qnaDetail(num));
			model.addAttribute("crlf", "\r\n");
			model.addAttribute("page", page);
			
			return "/park/qna/qnaDetail";
		} 
		
	//축제 정보 슬라이드
	@RequestMapping("/fstvl")
	public String fstvl(FstvlDTO dto, Model model) {
	    List<FstvlDTO> fstvlList = festivalService.fstvlList(dto);

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
	public String fstvlList(FstvlDTO dto, Model model) {
	    List<FstvlDTO> fstvlList = festivalService.fstvlList(dto);
	    model.addAttribute("fstvlList", fstvlList);
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
	// community main
		@GetMapping("/cmMain")
		public String home(@RequestParam(value = "memId", required = false) String memId,
				@RequestParam(value = "pageNum", defaultValue = "1") String pageNum, Model model, HttpSession session,
				CmBoardDTO dto, String option, String keyword) {
			memId = (String) session.getAttribute("memId");

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
			int total = cmservice.selectBoardTotalCount(dto);
			// 첫 글 번호
			int beginPage = (page - 1) * pageSize + 1;
			// 마지막 글 번호
			int endPage = beginPage + pageSize - 1;

//			// System.out.println("total=" + total);
//			// System.out.println("beginPage=" + beginPage);
//			// System.out.println("endPage=" + endPage);
//			// System.out.println("page=" + page);
//			// System.out.println("pageNum=" + pageNum);
//			// System.out.println("=================================");

			dto.setBeginPage(beginPage);
			dto.setEndPage(endPage);

			List<CmBoardDTO> boardList = cmservice.getBoardList(dto);
			PageResolver pr = new PageResolver(page, pageSize, total);

			model.addAttribute("boardList", boardList);
			model.addAttribute("pr", pr);
			model.addAttribute("memId", memId);
			model.addAttribute("option", option);

			return "park/community/main";
		}
		// community write
		@GetMapping("/cmWriteForm")
		public String openBoardWrite(HttpSession session, Model model, CmBoardDTO dto) {
			String memId = (String) session.getAttribute("memId");
			// System.out.println(memId);
			model.addAttribute("memId", memId);

			return "park/community/write";
		}

		// community writepro
		@PostMapping("/cmWritePro")
		public String addBoard(HttpSession session, CmBoardDTO dto, Model model) {
			String memId = (String) session.getAttribute("memId");
			dto.setCm_writer(memId);
			cmservice.addBoard(dto);

			return "redirect:/board/cmView?cm_no=" + dto.getCm_group();
		}
		@RequestMapping("")
		// commnity delete
		@GetMapping("/cmDelete")
		public String deletePost(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session,
				CmBoardDTO dto, Model model) {
			String id = (String) session.getAttribute("memId");
			int delete = 0;
			dto.setCm_writer(id);
			dto.setCm_no(cm_no);
			// System.out.println("postno =" + postno + ", id =" + id);
			cmservice.deleteBoard(dto);
			model.addAttribute("delete", delete);
			model.addAttribute("memId", id);
			return "redirect:/park/community/main";
		}

		// community modify form
		@GetMapping("/cmModifyForm")
		public String modifyBoard(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session,
				Model model, CmBoardDTO dto) {
			String memId = (String) session.getAttribute("memId");
			dto = cmservice.getBoardDetail(cm_no);
			model.addAttribute("memId", memId);
			model.addAttribute("dto", dto);

			return "park/community/modify";
		}

		// community midify pro
		@PostMapping("/cmModiyPro")
		public String updateBoard(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session,
				Model model, CmBoardDTO dto) {
			String id = (String) session.getAttribute("memId");
			System.out.println();

			int modify = 0;
			dto.setCm_writer(id);
			dto.setCm_no(cm_no);
			modify = cmservice.modifyBoard(dto);
			model.addAttribute("modify", modify);

			return "redirect:/park/community/view?cm_no=" + dto.getCm_no();
		}

		// community view detail
		@GetMapping("/cmView")
		public String openBoardDetail(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session,
				Model model, CmBoardDTO dto) {
			String memId = (String) session.getAttribute("memId");

			dto = cmservice.getBoardDetail(cm_no);
			int commentCnt = cmservice.commentCnt(cm_no);

			Document doc = Jsoup.parse(dto.getCm_content());
			dto.setDoc(doc);
			List<CmBoardDTO> commentList = cmservice.getCommentList(dto);

			model.addAttribute("dto", dto);
			model.addAttribute("commentList", commentList);
			model.addAttribute("memId", memId);
			model.addAttribute("commentCnt", commentCnt);

			return "park/community/view";
		}
		//community my post
		@GetMapping("/cmMypost")
		public String myhome(@RequestParam(value = "memId", required = false) String memId, Model model,
				HttpSession session, CmBoardDTO dto, String pageNum, String option, String keyword) {
			String id = (String) session.getAttribute("memId");
			dto.setCm_writer(id);

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
			int total = cmservice.selectMyPostTotalCount(dto);
			// 첫 글 번호
			int beginPage = (page - 1) * pageSize + 1;
			// 마지막 글 번호
			int endPage = beginPage + pageSize - 1;

			dto.setBeginPage(beginPage);
			dto.setEndPage(endPage);

			List<CmBoardDTO> boardList = cmservice.getMypost(dto);
			PageResolver pr = new PageResolver(page, pageSize, total);

			model.addAttribute("boardList", boardList);
			model.addAttribute("pr", pr);
			model.addAttribute("memId", id);
			model.addAttribute("option", option);

			// System.out.println(model);

			return "park/community/mypost";
		}

		// ajax
		@GetMapping("/cmAjaxTest")
		public String openBoardDetail2(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session,
				Model model, CmBoardDTO dto) {
			String memId = (String) session.getAttribute("memId");
			dto = cmservice.getBoardDetail(cm_no);
			Document doc = Jsoup.parse(dto.getCm_content());
			dto.setDoc(doc);

			model.addAttribute("dto", dto);
			model.addAttribute("memId", memId);

			return "park/community/ajaxTest";
		}

		// 로그인 체크
//		@GetMapping("/park/loginCheck")
//		@ResponseBody
//		public Map<String, Object> memId(HttpSession session) {
//			Map<String, Object> result = new HashMap<>();
//			String memId = (String) session.getAttribute("memId");
//			result.put("memId", memId);
//			return result;
//		}

		// ajax comment add
//		@PostMapping("/cmAddC")
//		public String ajaxInsertComment(HttpSession session, CmBoardDTO dto, Model model) {
//			String memId = (String) session.getAttribute("memId");
//			dto.setCm_writer(memId);
//			cmservice.addBoard(dto);
//			model.addAttribute("cWrite", cmservice.addBoard(dto));
//
//			return "park/community/ajaxTest :: commentList";
//		}
		// AJAX comment add
	    @PostMapping("/cmAddC")
	    @ResponseBody
	    public String ajaxInsertComment(HttpSession session, @RequestBody CmBoardDTO dto) {
	        String memId = (String) session.getAttribute("memId");
	        dto.setCm_writer(memId);
	        cmservice.addBoard(dto);
	        return "success";
	    }

	    @GetMapping("/cmCommentList")
	    @ResponseBody
	    public List<CmBoardDTO> commentList(@RequestParam(value = "cm_no", required = false) long cm_no) {
	        CmBoardDTO dto = new CmBoardDTO();
	        dto.setCm_no(cm_no);
	        List<CmBoardDTO> commentList = cmservice.getCommentList(dto);

	        return commentList;
	    }

	    // AJAX count comment
	    @GetMapping("/cmCommentCnt")
	    @ResponseBody
	    public int commentCnt(@RequestParam(value = "cm_no", required = false) Long cm_no, CmBoardDTO dto) {
	        dto.setCm_no(cm_no);
	        int commentCnt = cmservice.commentCnt(cm_no);

	        return commentCnt;
	    }

		// delete comment
		@PostMapping("/CmAjaxdelete")
		public String ajaxDelete(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session,
				CmBoardDTO dto, Model model) {
			String id = (String) session.getAttribute("memId");
			int delete = 0;
			dto.setCm_writer(id);
			dto.setCm_no(cm_no);

			cmservice.deleteBoard(dto);
			model.addAttribute("delete", delete);
			model.addAttribute("memId", id);

			return "park/community/ajaxTest :: #commentList";
		}

		// update comment
		@PostMapping("/cmAjaxupdate")
		public String updateComment(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session,
				Model model, CmBoardDTO dto) {
			String id = (String) session.getAttribute("memId");
			int modify = 0;
			dto.setCm_writer(id);
			dto.setCm_no(cm_no);
			System.out.println(cm_no);

			modify = cmservice.modifyBoard(dto);
			model.addAttribute("modify", modify);

			return "park/community/ajaxTest :: #commentList";

		}
}
