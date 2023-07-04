package test.spring.controller.park;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import test.spring.component.park.FaqBoardDTO;
import test.spring.service.park.FaqService;

@Controller
@RequestMapping("/faq/*")
public class faqController {
	@Autowired
	private FaqService faqService;
	
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
	
//	@RequestMapping("/faqList")
//	public String faqList(FaqBoardDTO dto, Model model) {
//	    FaqBoardDTO faqDTO = faqService.faqList(dto);
//	    List<FaqBoardDTO> faqList = new ArrayList<>();
//	    faqList.add(faqDTO);
//
//	    model.addAttribute("faqList", faqList);
//	    return "/park/faq/faqList";
//	}
	@RequestMapping("/faqList")
	public String faqList(FaqBoardDTO dto, Model model) {
	    List<FaqBoardDTO> faqList = faqService.faqList(dto);

	    model.addAttribute("faqList", faqList);
	    return "/park/faq/faqList";
	}
}
