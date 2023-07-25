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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import test.spring.component.park.BeachResultData;
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
		// ���߿� �����ڷ� �ٲ����
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
		// �˻�����
		if (keyword != null) {
			dto.setOption(option);
			dto.setKeyword(keyword);
		}
		// ���������̼�
		int pageSize = 10; // ������ �� �Խñ� ����
		int page = 1;
		try {
			if (pageNum != null && !pageNum.equals("")) {
				page = Integer.parseInt(pageNum);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		// ����Ʈ �� ����
		int total = qnaservice.totalList(dto);
		// ù �� ��ȣ
		int beginPage = (page - 1) * pageSize + 1;
		// ������ �� ��ȣ
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
	//QNA �� �� ȭ�� ��û
	@RequestMapping("/qnaDetail")
	public String detail(int num, Model model) {
		//������ QNA �ۿ� ���� ��ȸ�� ���� ó��
		qnaservice.qnaRead(num);
		//������ QNA �� ������ DB�� ��ȸ�ؿ� �� ȭ�鿡 ���
		model.addAttribute("dto", qnaservice.qnaDetail(num));
		
		return "/park/qna/qnaDetail";
	} 
	//QNA �� ���� ȭ�� ��û
	@RequestMapping("/qnaModifyForm")
	public String modify(int num, Model model) {
		//������ QNA �� ������ DB���� ��ȸ�ؿ� ���� ȭ�鿡 ���
		model.addAttribute("dto", qnaservice.qnaDetail(num));
		return "/park/qna/qnaModify";
	} //modify()
	
	//QNA �� ���� ó�� ��û
	@RequestMapping("/qnaModifyPro")
	public String update(QnaDTO dto, HttpSession session, String title,String content,@RequestParam(value = "num") int num) {
		//ȭ�鿡�� ������ ������ DB�� ������ �� �� ȭ������ ����
		dto.setNum(num);
		dto.setTitle(title);
		dto.setContent(content);
		qnaservice.qnaUpdate(dto);
		return "redirect:/board/qnaDetail?num=" + dto.getNum();
	} //update()	
	//QNA �� ���� ó�� ��û
	@RequestMapping("/qnaDelete")
	public String delete(int num) {
		//������ QNA ���� DB���� ������ �� ��� ȭ������ ����
		qnaservice.qnaDelete(num);
		
		return "redirect:/board/qnaList";
	}
	//��� ���� ȭ�� ��û==================================================================
	@RequestMapping("/qnaReplyForm")
	public String reply(Model model, int num) {
		//������ ������ ��� ���� ȭ�鿡�� �� �� �ֵ��� �Ѵ�.
		model.addAttribute("dto", qnaservice.qnaDetail(num));
		
		return "park/qna/qnaReply";
	} //reply()
	
	//�ű� ��� ���� ó�� ��û==============================================================
	@RequestMapping("/qnaReplyPro")
	public String reply_insert(QnaDTO dto, HttpSession session) {
		//�ۼ��ڴ� ������ �ΰ��
//		dto.setWriter(((MemberVO) session.getAttribute("login_info")).getId());
		//�ӽ�
		String memId = (String) session.getAttribute("memId");
		dto.setWriter(memId);
		//ȭ�鿡�� �Է��� ������ DB�� ������ �� ��� ȭ������ ����
		qnaservice.qnaReplyInsert(dto);
		return "redirect:/board/qnaList";
	}
	
	//���� ���� �����̵�
	@RequestMapping("/fstvl")
	public String fstvl(FstvlDTO dto, Model model) {
		List<FstvlDTO> fstvlList = festivalService.fstvl(dto);
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
	    return "/park/festival/fstvl";
	}
	//���� ����Ʈ
	@RequestMapping("/fstvlList")
	public String fstvlList(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum, Model model,FstvlDTO dto, String option, String keyword) {
		// �˻�����
		if (keyword != null) {
			dto.setOption(option);
			dto.setKeyword(keyword);
		}
		// ���������̼�
		int pageSize = 5; // ������ �� �Խñ� ����
		int page = 1;
		try {
			if (pageNum != null && !pageNum.equals("")) {
				page = Integer.parseInt(pageNum);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		// ����Ʈ �� ����
		int total = festivalService.selectFstvlCount(dto);
		// ù �� ��ȣ
		int beginPage = (page - 1) * pageSize + 1;
		// ������ �� ��ȣ
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
	//�ؼ����� api
	@GetMapping("/beach")
	public String getBeachInformation( Model model) {
		try {
		 StringBuilder urlBuilder = new StringBuilder("http://api.odcloud.kr/api/15056091/v1/uddi:e6b792cd-5f5f-4c74-867c-83159645f0ec"); /*URL*/
		 urlBuilder.append("?" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*������ ��ȣ*/
		 urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode("264", "UTF-8")); /*�� ������ ��� ��*/
	     urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8") + "=g%2BdzVqHbtyZ4yDYOaF3yYrZr0sZPNvlIWf2PAg2uvpPpjJav%2Fm%2B%2Bbyjs5mbKyj1W17CfFilBfaxHTpMupA6%2FxQ%3D%3D"); /*Service Key*/
	     urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*XML/JSON ����*/
		// ���� 5���� �ʼ������� �����ٲ��� �ʰ� ȣ���ؾ� �մϴ�.
		
		// ���񽺺� �߰� ��û �����̸� �ڼ��� ������ �� ���񽺺� '��û����'�κп� �ڼ��� ���� �ֽ��ϴ�.
		urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); /* ���񽺺� �߰� ��û���ڵ�*/
		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode()); /* ���� ��ü�� ���� Ȯ���� �ʿ��ϹǷ� �߰��մϴ�.*/
		BufferedReader rd;

		// �����ڵ尡 �����̸� 200~300������ ���ڰ� ���ɴϴ�.
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
		// JSON �����͸� �ڹ� ��ü�� ��ȯ
		ObjectMapper objectMapper = new ObjectMapper();
		BeachResultData resultData = objectMapper.readValue(sb.toString(), BeachResultData.class);
		
        model.addAttribute("resultData", resultData);
	    
		}catch (Exception e){
			e.printStackTrace();
		}
	    
	    return "/park/beach";
	}
	
}
