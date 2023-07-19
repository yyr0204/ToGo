package test.spring.controller.park;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import test.spring.component.park.CmBoardDTO;
import test.spring.component.park.PageResolver;
import test.spring.service.park.CmService;

@Controller
@RequestMapping("/board/*")
public class CommunityController {
	@Autowired
	private CmService cmservice;
	
	
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
		int total = cmservice.selectBoardTotalCount(dto);
		// 첫 글 번호
		int beginPage = (page - 1) * pageSize + 1;
		// 마지막 글 번호
		int endPage = beginPage + pageSize - 1;

		 System.out.println("total=" + total);
		 System.out.println("beginPage=" + beginPage);
		 System.out.println("endPage=" + endPage);
		 System.out.println("page=" + page);
		 System.out.println("pageNum=" + pageNum);
		 System.out.println("=================================");

		dto.setBeginPage(beginPage);
		dto.setEndPage(endPage);

		List<CmBoardDTO> boardList = cmservice.getBoardList(dto);
		PageResolver pr = new PageResolver(page, pageSize, total);

		model.addAttribute("boardList", boardList);
		model.addAttribute("pr", pr);
		model.addAttribute("memId", memId);
		model.addAttribute("option", option);
		model.addAttribute("keyword", keyword);

		return "park/community/main";
	}
	// community write
	@GetMapping("/cmWriteForm")
	public String openBoardWrite(HttpSession session, Model model, CmBoardDTO dto) {
		String memId = (String) session.getAttribute("memId");
		 System.out.println(memId);
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
	// community delete
	@GetMapping("/cmDelete")
	public String deletePost(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session,
			CmBoardDTO dto, Model model) {
		String id = (String) session.getAttribute("memId");
		int delete = 0;
		dto.setCm_writer(id);
		dto.setCm_no(cm_no);
		delete = cmservice.deleteBoard(dto);
		model.addAttribute("delete", delete);
		model.addAttribute("memId", id);
		return "redirect:/board/cmMain";
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

	// community modify pro
	@PostMapping("/cmModifyPro")
	public String updateBoard(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session,
			Model model, CmBoardDTO dto) {
		String id = (String) session.getAttribute("memId");
		System.out.println();

		int modify = 0;
		dto.setCm_writer(id);
		dto.setCm_no(cm_no);
		modify = cmservice.modifyBoard(dto);
		model.addAttribute("modify", modify);

		return "redirect:/board/cmView?cm_no=" + dto.getCm_no();
	}

	// community view detail
	@GetMapping("/cmView")
	public String openBoardDetail(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session,
			Model model, CmBoardDTO dto) {
		String memId = (String) session.getAttribute("memId");
		cmservice.updatereadcnt(cm_no);
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

//			// ajax
//			@GetMapping("/cmAjaxTest")
//			public String openBoardDetail2(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session,
//					Model model, CmBoardDTO dto) {
//				String memId = (String) session.getAttribute("memId");
//				dto = cmservice.getBoardDetail(cm_no);
//				Document doc = Jsoup.parse(dto.getCm_content());
//				dto.setDoc(doc);
//
//				model.addAttribute("dto", dto);
//				model.addAttribute("memId", memId);
//
//				return "park/community/ajaxTest";
//			}

	// AJAX comment add
    @PostMapping("/cmAddC")
    @ResponseBody
    public String ajaxInsertComment(HttpSession session, @RequestBody CmBoardDTO dto,@RequestParam(value = "commentStep", required = false) Long commentStep) {
        String memId = (String) session.getAttribute("memId");
        dto.setCm_writer(memId);
        
        System.out.println("깊이="+dto.getDepth());
        System.out.println("스텝="+dto.getStep());
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
    @RequestMapping("/CmAjaxdelete")
    @ResponseBody
    public String ajaxDelete(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session) {
        String id = (String) session.getAttribute("memId");
        int delete = 0;
        CmBoardDTO dto = new CmBoardDTO();
        dto.setCm_writer(id);
        dto.setCm_no(cm_no);
        delete = cmservice.deleteBoard(dto);
        System.out.println("delete : "+delete);
        System.out.println("cm_no : "+cm_no );
        System.out.println("id: "+id);

        if (delete > 0) {
            return "success";
        } else {
            return "error";
        }
    }

	// update comment
	@PostMapping("/cmAjaxupdate")
	@ResponseBody
	public String updateComment(@RequestBody CmBoardDTO dto, HttpSession session) {
	    String id = (String) session.getAttribute("memId");
	    int modify = 0;
	    dto.setCm_writer(id);
	    dto.setCm_title("comment");

	    modify = cmservice.modifyBoard(dto);
	    System.out.println("modify : "+modify);

	    if (modify > 0) {
	        return "success";
	    } else {
	        return "error";
	    }
	}
}
