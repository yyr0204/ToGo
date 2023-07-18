package test.spring.controller.park;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	//faq
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
	//qna 
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
//		//QNA Ŭ�� �ϸ� admin���� �ڵ� �α���
//		HashMap<String, String> map = new HashMap<String, String>();
//		//HashMap : �����͸� ���� �ڷ� ����
//		map.put("id", "admin");
//		map.put("pw", "1234");
//		session.setAttribute("login_info", member.member_login(map));
//		session.setAttribute("category", "qna");
		
		//DB���� �� ��� ��ȸ�ؿ� ȭ�鿡 ���
		page.setCurPage(curPage);
		page.setSearch(search);
		page.setKeyword(keyword);
		model.addAttribute("page", qnaservice.qnaList(page));
		return "/park/qna/qnaList";
	}
	//QNA �� �� ȭ�� ��û
		@RequestMapping("/qnaDetail")
		public String detail(int num, Model model) {
			//������ QNA �ۿ� ���� ��ȸ�� ���� ó��
			qnaservice.qnaDetail(num);
			
			//������ QNA �� ������ DB�� ��ȸ�ؿ� �� ȭ�鿡 ���
			model.addAttribute("dto", qnaservice.qnaDetail(num));
			model.addAttribute("crlf", "\r\n");
			model.addAttribute("page", page);
			
			return "/park/qna/qnaDetail";
		} 
		
		//���� ����
		@RequestMapping("/fstvl")
		public String fstvlList(FstvlDTO dto, Model model) {
		    List<FstvlDTO> fstvlList = festivalService.fstvlList(dto);

		    List<FstvlDTO> randomFstvlList = new ArrayList<>();
		    if (fstvlList.size() > 5) {
		        List<Integer> indexes = new ArrayList<>();
		        for (int i = 0; i < fstvlList.size(); i++) {
		            indexes.add(i);
		        }
		        Collections.shuffle(indexes); // �ε����� �����ϰ� ����

		        for (int i = 0; i < 5; i++) {
		            randomFstvlList.add(fstvlList.get(indexes.get(i)));
		        }
		    } else {
		        randomFstvlList = fstvlList;
		    }

		    model.addAttribute("fstvlList", randomFstvlList);
		    return "/song/main";
		}
//		@GetMapping("/scrape-and-save")
//		public String scrapeAndSaveFestivals() {
//		    String testURL = "https://www.mcst.go.kr/kor/s_culture/festival/festivalList.jsp?pMenuCD=&pCurrentPage=%d&pSearchType=&pSearchWord=&pSeq=&pSido=&pOrder=&pPeriod=&fromDt=&toDt=";
//
//		    // ũ�Ѹ� �� �����ͺ��̽� ������Ʈ
//		    for (int i = 1; i <= 12; i++) {
//		        String url = String.format(testURL, i);
//		        festivalService.testCrawling(url);
//		    }
//
//		    return "/park/fstvl";
//		}

}
