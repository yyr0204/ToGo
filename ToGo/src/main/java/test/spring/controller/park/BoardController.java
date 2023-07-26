package test.spring.controller.park;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import test.spring.component.park.BeachResultData;
import test.spring.component.park.FaqBoardDTO;
import test.spring.component.park.FstvlDTO;
import test.spring.component.park.PageResolver;
import test.spring.component.park.QnaDTO;
import test.spring.service.park.BeachService;
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
	private BeachService beachService;
	
	//FAQ
	@RequestMapping("/faqWriteForm")
	public String faqWriteForm() {
		return "/park/faq/faqWriteForm";
	}
	@RequestMapping("/faqWritePro")
	public String insert(FaqBoardDTO dto, Model model,HttpSession session) {
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
		int total = qnaservice.totalList(dto);
		int beginPage = (page - 1) * pageSize + 1;
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
	//qna detail
	@RequestMapping("/qnaDetail")
	public String detail(int num, Model model) {
		qnaservice.qnaRead(num);
		model.addAttribute("dto", qnaservice.qnaDetail(num));
		
		return "/park/qna/qnaDetail";
	} 
	//qna modify
	@RequestMapping("/qnaModifyForm")
	public String modify(int num, Model model) {
		model.addAttribute("dto", qnaservice.qnaDetail(num));
		return "/park/qna/qnaModify";
	} //modify()
	
	@RequestMapping("/qnaModifyPro")
	public String update(QnaDTO dto, HttpSession session, String title,String content,@RequestParam(value = "num") int num) {
		dto.setNum(num);
		dto.setTitle(title);
		dto.setContent(content);
		qnaservice.qnaUpdate(dto);
		return "redirect:/board/qnaDetail?num=" + dto.getNum();
	} 
	//qna delete
	@RequestMapping("/qnaDelete")
	public String delete(int num) {
		qnaservice.qnaDelete(num);
		return "redirect:/board/qnaList";
	}
	//qna reply
	@RequestMapping("/qnaReplyForm")
	public String reply(Model model, int num) {
		model.addAttribute("dto", qnaservice.qnaDetail(num));
		
		return "park/qna/qnaReply";
	} 
	
	@RequestMapping("/qnaReplyPro")
	public String reply_insert(QnaDTO dto, HttpSession session) {
		
//		dto.setWriter(((MemberVO) session.getAttribute("login_info")).getId());
		String memId = (String) session.getAttribute("memId");
		dto.setWriter(memId);
		qnaservice.qnaReplyInsert(dto);
		return "redirect:/board/qnaList";
	}
	//fstvl slide
	@RequestMapping("/fstvl")
	public String fstvl(FstvlDTO dto, Model model) {
        List<FstvlDTO> randomFstvlList = festivalService.getRandomFstvlList(dto);
        model.addAttribute("fstvlList", randomFstvlList);
        return "/park/festival/fstvl";
    }
	//festival list
	@RequestMapping("/fstvlList")
	public String fstvlList(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum, Model model,FstvlDTO dto, String option, String keyword) {
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
		int total = festivalService.selectFstvlCount(dto);
		int beginPage = (page - 1) * pageSize + 1;
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
	//beach 
	 @GetMapping("/beach")
    public String getBeachInformation( Model model) {
        BeachResultData resultData = beachService.getBeachInformation();
        model.addAttribute("resultData", resultData);
        return "/park/beach";
    }
	
}
