package test.spring.controller.park;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;

import test.spring.component.park.CmBoardDTO;
import test.spring.component.park.PageResolver;
import test.spring.service.park.CmService;
import test.spring.service.park.MyPageService;

@Controller
@RequestMapping("/board/*")
public class CommunityController {
	@Autowired
	private CmService cmservice;
	@Autowired
	private MyPageService mpservice;
	// community main
	@RequestMapping("/cmMain")
	public String home(@RequestParam(value = "memId", required = false) String memId,
			@RequestParam(value = "pageNum", defaultValue = "1") String pageNum, Model model, HttpSession session,
			CmBoardDTO dto, String option, String keyword) {
		memId = (String) session.getAttribute("memId");
		if (keyword != null) {
			dto.setOption(option);
			dto.setKeyword(keyword);
		}
		int pageSize = 10;
		int page = 1;
		try {
			if (pageNum != null && !pageNum.equals("")) {
				page = Integer.parseInt(pageNum);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		int total = cmservice.selectBoardTotalCount(dto);
		int beginPage = (page - 1) * pageSize + 1;
		int endPage = beginPage + pageSize - 1;
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
		model.addAttribute("memId", memId);

		return "park/community/write";
	}
	
	// community writepro
	@PostMapping("/cmWritePro")
	public String addBoard(HttpSession session, CmBoardDTO dto, Model model, @RequestParam("save") MultipartFile[] save, HttpServletRequest request) {
	    String memId = (String) session.getAttribute("memId");
	    String uploadDirectory = request.getRealPath("/resources/static/cmImage/"); 
	    StringBuilder filenamesBuilder = new StringBuilder(); // 파일 이름들을 저장할 StringBuilder 객체
	    boolean isFirstFile = true;
	    for (MultipartFile file : save) {
	        String fileName = file.getOriginalFilename();
	        if (fileName == null || fileName.isEmpty()) {
	            // 파일명이 비어있는 경우 예외 처리
	            continue; // 다음 파일로 넘어감
	        }

	        // 중복된 파일이 있는지 체크
	        File checkFile = new File(uploadDirectory + fileName);
	        while (checkFile.exists()) {
	            // 중복된 파일이 있으면 UUID를 사용하여 새로운 파일명 생성
	            String nameWithoutExtension = fileName.substring(0, fileName.lastIndexOf("."));
	            String extension = fileName.substring(fileName.lastIndexOf("."));
	            fileName = nameWithoutExtension + "_" + UUID.randomUUID().toString() + extension;
	            checkFile = new File(uploadDirectory + fileName);
	        }

	        String filePath = uploadDirectory + fileName;
	        try {
	            file.transferTo(new File(filePath));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        if (isFirstFile) {
	            isFirstFile = false;
	        } else {
	            filenamesBuilder.append(","); // 구분자인 쉼표를 추가
	        }
	        filenamesBuilder.append(fileName); // 파일 이름을 추가
	    }

	    String allFilenames = filenamesBuilder.toString(); // 모든 파일 이름들을 하나의 문자열로 만듦
	    dto.setFilename(allFilenames); // dto에 파일 이름들을 구분자로 구분한 문자열을 저장
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

		if (keyword != null) {
			dto.setOption(option);
			dto.setKeyword(keyword);
		}
		int pageSize = 5; 
		int page = 1;
		try {
			if (pageNum != null && !pageNum.equals("")) {
				page = Integer.parseInt(pageNum);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		int total = cmservice.selectMyPostTotalCount(dto);
		int beginPage = (page - 1) * pageSize + 1;
		int endPage = beginPage + pageSize - 1;

		dto.setBeginPage(beginPage);
		dto.setEndPage(endPage);

		List<CmBoardDTO> boardList = cmservice.getMypost(dto);
		PageResolver pr = new PageResolver(page, pageSize, total);

		model.addAttribute("boardList", boardList);
		model.addAttribute("pr", pr);
		model.addAttribute("memId", id);
		model.addAttribute("option", option);

		return "park/community/mypost";
	}
	// AJAX comment add
    @PostMapping("/cmAddC")
    @ResponseBody
    public String ajaxInsertComment(HttpSession session, @RequestBody CmBoardDTO dto,@RequestParam(value = "commentStep", required = false) Long commentStep) {
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
    @RequestMapping("/CmAjaxdelete")
    @ResponseBody
    public String ajaxDelete(@RequestParam(value = "cm_no", required = false) Long cm_no, HttpSession session) {
        String id = (String) session.getAttribute("memId");
        int delete = 0;
        CmBoardDTO dto = new CmBoardDTO();
        dto.setCm_writer(id);
        dto.setCm_no(cm_no);
        delete = cmservice.deleteBoard(dto);
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
