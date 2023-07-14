package test.spring.controller.park;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import test.spring.component.park.CmBoardDTO;
import test.spring.component.park.PageResolver;
import test.spring.service.park.CmService;

@Controller
public class TestController {
    @Autowired
	private CmService cmservice;

	// home 게시판 리스트 화면
	@GetMapping("/board/home")
	public String home(@RequestParam(value = "loginId", required = false) String loginid,
			@RequestParam(value = "pageNum", defaultValue = "1") String pageNum, Model model, HttpSession session,
			CmBoardDTO dto, String option, String keyword) {
		String loginId = (String) session.getAttribute("loginId");

//		long postno = bDto.getPgroup();
//		int commentCnt = boardService.commentCnt(postno);

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

//		// System.out.println("total=" + total);
//		// System.out.println("beginPage=" + beginPage);
//		// System.out.println("endPage=" + endPage);
//		// System.out.println("page=" + page);
//		// System.out.println("pageNum=" + pageNum);
//		// System.out.println("=================================");

		dto.setBeginPage(beginPage);
		dto.setEndPage(endPage);

		List<CmBoardDTO> boardList = cmservice.getBoardList(dto);
		PageResolver pr = new PageResolver(page, pageSize, total);

		model.addAttribute("boardList", boardList);
		model.addAttribute("pr", pr);
		model.addAttribute("loginId", loginId);
		model.addAttribute("option", option);

		return "park/community/home";
	}

	// 게시물 작성 화면
	@GetMapping("/board/write")
	public String openBoardWrite(HttpSession session, Model model, CmBoardDTO dto) {
		String loginId = (String) session.getAttribute("loginId");
		// System.out.println(loginId);
		model.addAttribute("loginId", loginId);

		return "park/community/write";
	}

	// 게시물 작성
	@PostMapping("/board/addBoard")
	public String addBoard(HttpSession session, CmBoardDTO dto, Model model) {
		String loginId = (String) session.getAttribute("loginId");
		dto.setCm_writer(loginId);

		int write = 0;
		write = cmservice.addBoard(dto);
		// System.out.println("본문 작성 컨트롤러 = " + bDto);
		model.addAttribute("write", write);

		return "redirect:/park/community/view?cm_no=" + dto.getCm_group();
	}

	// 게시물 삭제
	@GetMapping("/board/delete")
	public String deletePost(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session,
			CmBoardDTO dto, Model model) {
		String id = (String) session.getAttribute("loginId");
		int delete = 0;
		dto.setCm_writer(id);
		dto.setCm_no(cm_no);
		// System.out.println("postno =" + postno + ", id =" + id);
		cmservice.deleteBoard(dto);
		model.addAttribute("delete", delete);
		model.addAttribute("loginId", id);
		return "redirect:/park/community/home";
	}

	// 게시물 수정 화면
	@GetMapping("/board/modify")
	public String modifyBoard(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session,
			Model model, CmBoardDTO dto) {
		String loginId = (String) session.getAttribute("loginId");
		dto = cmservice.getBoardDetail(cm_no);
		model.addAttribute("loginId", loginId);
		model.addAttribute("dto", dto);
		// System.out.println("bDto" + bDto);

		return "park/community/modify";
	}

	// 게시물 수정
	@PostMapping("/board/update")
	public String updateBoard(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session,
			Model model, CmBoardDTO dto) {
		String id = (String) session.getAttribute("loginId");
		System.out.println();

		int modify = 0;
		dto.setCm_writer(id);
		dto.setCm_no(cm_no);
		modify = cmservice.modifyBoard(dto);
		model.addAttribute("modify", modify);

		return "redirect:/park/community/view?cm_no=" + dto.getCm_no();
	}

	// 자세히 보기
	@GetMapping("/board/view")
	public String openBoardDetail(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session,
			Model model, CmBoardDTO dto) {
		String loginId = (String) session.getAttribute("loginId");

		dto = cmservice.getBoardDetail(cm_no);
		int commentCnt = cmservice.commentCnt(cm_no);

		Document doc = Jsoup.parse(dto.getCm_content());
		dto.setDoc(doc);
		List<CmBoardDTO> commentList = cmservice.getCommentList(dto);

		model.addAttribute("dto", dto);
		model.addAttribute("commentList", commentList);
		model.addAttribute("loginId", loginId);
		model.addAttribute("commentCnt", commentCnt);

		return "park/community/view";
	}

	@GetMapping("/board/mypost")
	public String myhome(@RequestParam(value = "loginId", required = false) String loginid, Model model,
			HttpSession session, CmBoardDTO dto, String pageNum, String option, String keyword) {
		String id = (String) session.getAttribute("loginId");
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
		model.addAttribute("loginId", id);
		model.addAttribute("option", option);

		// System.out.println(model);

		return "park/community/mypost";
	}

	// 자세히 보기
	@GetMapping("/board/ajaxTest")
	public String openBoardDetail2(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session,
			Model model, CmBoardDTO dto) {
		String loginId = (String) session.getAttribute("loginId");
		dto = cmservice.getBoardDetail(cm_no);
		Document doc = Jsoup.parse(dto.getCm_content());
		dto.setDoc(doc);

		model.addAttribute("dto", dto);
		model.addAttribute("loginId", loginId);

		return "park/community/ajaxTest";
	}

	// 로그인 체크
	@GetMapping("/park/loginCheck")
	@ResponseBody
	public Map<String, Object> loginId(HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		String loginId = (String) session.getAttribute("loginId");
		result.put("loginId", loginId);
		return result;
	}

	// ajax 댓글 & 대댓글 작성
	@PostMapping("/board/addC")
	public String ajaxInsertComment(HttpSession session, CmBoardDTO dto, Model model) {
		String loginId = (String) session.getAttribute("loginId");
		dto.setCm_writer(loginId);

		int cWrite = 0;
		cWrite = cmservice.addBoard(dto);
		model.addAttribute("cWrite", cWrite);
		// System.out.println("댓글 작성 컨트롤러 = " + bDto);

		return "park/community/ajaxTest :: #commentList";
	}

	// ajax 댓글 조회
	@GetMapping("/board/commentList")
	@ResponseBody
	public List<CmBoardDTO> commentList(@RequestParam(value = "cm_no", required = false) Long cm_no, CmBoardDTO dto) {

		dto.setCm_no(cm_no);
		List<CmBoardDTO> commentList = cmservice.getCommentList(dto);

		return commentList;
	}

	// ajax 댓글 수 조회
	@GetMapping("/board/commentCnt")
	@ResponseBody
	public int commentCnt(@RequestParam(value = "cm_no", required = false) Long cm_no, CmBoardDTO dto) {

		dto.setCm_no(cm_no);
		int commentCnt = cmservice.commentCnt(cm_no);

		return commentCnt;
	}

	// 댓글 삭제
	@PostMapping("/board/ajaxdelete")
	public String ajaxDelete(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session,
			CmBoardDTO dto, Model model) {
		String id = (String) session.getAttribute("loginId");
		int delete = 0;
		dto.setCm_writer(id);
		dto.setCm_no(cm_no);

		cmservice.deleteBoard(dto);
		model.addAttribute("delete", delete);
		model.addAttribute("loginId", id);

		return "park/community/ajaxTest :: #commentList";
	}

	// 댓글 수정
	@PostMapping("/board/ajaxupdate")
	public String updateComment(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session,
			Model model, CmBoardDTO dto) {
		String id = (String) session.getAttribute("loginId");
		int modify = 0;
		dto.setCm_writer(id);
		dto.setCm_no(cm_no);
		System.out.println(cm_no);

		modify = cmservice.modifyBoard(dto);
		model.addAttribute("modify", modify);

		return "park/community/ajaxTest :: #commentList";

	}
	//해수욕장
//  @GetMapping("/result")
//	public String getBeachInformation( Model model) {
//		try {
//		 StringBuilder urlBuilder = new StringBuilder("http://api.odcloud.kr/api/15056091/v1/uddi:e6b792cd-5f5f-4c74-867c-83159645f0ec"); /*URL*/
//		 urlBuilder.append("?" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
//		 urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode("264", "UTF-8")); /*한 페이지 결과 수*/
//	     urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8") + "=g%2BdzVqHbtyZ4yDYOaF3yYrZr0sZPNvlIWf2PAg2uvpPpjJav%2Fm%2B%2Bbyjs5mbKyj1W17CfFilBfaxHTpMupA6%2FxQ%3D%3D"); /*Service Key*/
//	     urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*XML/JSON 여부*/
//		// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
//		
//		// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
//		urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); /* 서비스별 추가 요청인자들*/
//		
//		URL url = new URL(urlBuilder.toString());
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("GET");
//		conn.setRequestProperty("Content-type", "application/json");
//		System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
//		BufferedReader rd;
//
//		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
//		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//				rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
//		} else {
//				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//		}
//		StringBuilder sb = new StringBuilder();
//		String line;
//		while ((line = rd.readLine()) != null) {
//				sb.append(line);
//		}
//		rd.close();
//		conn.disconnect();
//		// JSON 데이터를 자바 객체로 변환
//		ObjectMapper objectMapper = new ObjectMapper();
//		BeachResultData resultData = objectMapper.readValue(sb.toString(), BeachResultData.class);
//		
//      model.addAttribute("resultData", resultData);
//	    
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//	    
//	    return "/park/beach";
//	}
}
