package test.spring.controller.song;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import test.spring.component.choi.KakaoDTO;
import test.spring.component.song.ImageBoard1DTO;
import test.spring.service.park.MyPageService;
import test.spring.service.song.ImageBoard1Service;


@Controller
@RequestMapping("/imageboard1/*")
public class ImageBoard1Controller {

	@Autowired
	private ImageBoard1Service service;
	@Autowired
	private MyPageService mpservice;
	
	@RequestMapping("list")
	public String list(HttpSession session, HttpServletRequest request, Model model) {
		
		String memId = (String)session.getAttribute("memId");
		
		int pageSize = 50;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		
		int count = service.boardListCount();
		List boardList = service.boardList(String.valueOf(startRow), String.valueOf(endRow));
		
		if(count > 0) {
			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			int startPage = (int)(currentPage/10)*10+1;
			int pageBlock = 10;
			int endPage = startPage + pageBlock - 1;
			if(endPage > pageCount) endPage = pageCount;
			List<String> page = new ArrayList();
			for(int i = startPage; i <= endPage; i++) {
				page.add(String.valueOf(i));
			}
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("page", page);
		}
		
		model.addAttribute("memId", memId);
		model.addAttribute("count", count);
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("currentPage", currentPage);
		
		return "/song/imageboard1/list";
	}
	
	@RequestMapping("mylist")
	public String mylist(HttpSession session, HttpServletRequest request, Model model) {
		
		String memId = (String)session.getAttribute("memId");
		
		int pageSize = 50;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		
		int count = service.myBoardListCount(memId);
		List boardList = service.myBoardList(memId, String.valueOf(startRow), String.valueOf(endRow));
		
		if(count > 0) {
			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			int startPage = (int)(currentPage/10)*10+1;
			int pageBlock = 10;
			int endPage = startPage + pageBlock - 1;
			if(endPage > pageCount) endPage = pageCount;
			List<String> page = new ArrayList();
			for(int i = startPage; i <= endPage; i++) {
				page.add(String.valueOf(i));
			}
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("page", page);
		}
		
		model.addAttribute("memId", memId);
		model.addAttribute("count", count);
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("currentPage", currentPage);
		
		return "/song/imageboard1/mylist";
	}
	
	@RequestMapping("writeForm")
	public String sessionWriteForm(HttpSession session, Model model) {
		String memId = (String)session.getAttribute("memId");
		KakaoDTO dto = mpservice.user_info(memId);
		model.addAttribute("dto", dto);
		model.addAttribute("memId", memId);
		
		return "/song/imageboard1/writeForm";
	}
	
	@RequestMapping("writePro")
	public String sessionWritePro(MultipartFile [] save, ImageBoard1DTO dto, HttpSession session, HttpServletRequest request, Model model) throws FileNotFoundException {
		
		String memId = (String)session.getAttribute("memId");

		dto.setIp(request.getRemoteAddr());
		
		String [] file_Name = new String[2];
		
		String uploadPath = request.getRealPath("/resources/static/song/upload");
		
		for(int i = 0; i < save.length; i++) {
			File copy = null;
			String file_name = save[i].getOriginalFilename();
			int a = 0;
			try {
				File file = new File(uploadPath+"//"+file_name);
				boolean exists = file.exists();
				while(true) {
					if(exists) {
						file_name = String.valueOf(((int)( a + 1 ))) + save[i].getOriginalFilename();
						a = a + 1;
						file = new File(uploadPath+"//"+file_name);
						exists = file.exists();
					}else {
						copy = new File(uploadPath+"//"+file_name);
						break;
					}
				}
				String OrgName = save[i].getOriginalFilename();
				String name = save[i].getContentType();
				if(OrgName != null) {
					String [] type = name.split("/");	// �뾽濡쒕뱶�븯�뒗 �뙆�씪�쓽 ���엯�쓣 泥댄겕�븯�뒗 硫붿냼�뱶
					if(type[0].equals("image")) {
						save[i].transferTo(copy); //�뾽濡쒕뱶
						file_Name[i] = file_name;
						System.out.println("�궗吏꾩엯�땲�떎. �뾽濡쒕뱶 �셿猷�!!!");
					}else {
						System.out.println("�궗吏꾨쭔 �뾽濡쒕뱶 媛��뒫�빀�땲�떎. �떎�떆 �뾽濡쒕뱶�븯�꽭�슂");
					}
				}
			}catch(Exception e) { e.printStackTrace(); }
		}
		dto.setThumbnail(file_Name[0]);
		dto.setImage(file_Name[1]);
		
		service.write(dto);
		
		return "/song/imageboard1/writePro";
	}
	
	@RequestMapping("updateForm")
	public String sessionUpdateForm(HttpServletRequest request, Model model) {
		int num = Integer.parseInt(request.getParameter("num"));
		
		model.addAttribute("numContent", service.numContent(num));
		
		return "/song/imageboard1/updateForm";
	}
	
	@RequestMapping("updatePro")
	public String sessionUpdatePro(MultipartFile [] save, ImageBoard1DTO dto, HttpSession session, HttpServletRequest request, Model model) {
		
		String memId = (String)session.getAttribute("memId");

		dto.setIp(request.getRemoteAddr());
		
		String [] file_Name = new String[2];
		
		String uploadPath = request.getRealPath("/resources/static/song/upload");
	//	String uploadPath = "C:\\Song\\Spring\\blog\\src\\main\\webapp\\resources\\upload";
		System.out.println(uploadPath);
		
		for(int i = 0; i < save.length; i++) {
			if(save[i] != null) {
				File copy = null;
				String file_name = save[i].getOriginalFilename();
				int a = 0;
				try {
					File file = new File(uploadPath+"//"+file_name);
					boolean exists = file.exists();
					while(true) {
						if(exists) {
							file_name = String.valueOf(((int)( a + 1 ))) + save[i].getOriginalFilename();
							a = a + 1;
							file = new File(uploadPath+"//"+file_name);
							exists = file.exists();
						}else {
							copy = new File(uploadPath+"//"+file_name);
							break;
						}
					}
					String OrgName = save[i].getOriginalFilename();
					String name = save[i].getContentType();
					if(OrgName != null) {
						String [] type = name.split("/");	// �뾽濡쒕뱶�븯�뒗 �뙆�씪�쓽 ���엯�쓣 泥댄겕�븯�뒗 硫붿냼�뱶
						if(type[0].equals("image")) {
							save[i].transferTo(copy); //�뾽濡쒕뱶
							file_Name[i] = file_name;
							System.out.println("�궗吏꾩엯�땲�떎. �뾽濡쒕뱶 �셿猷�!!!");
						}else {
							System.out.println("�궗吏꾨쭔 �뾽濡쒕뱶 媛��뒫�빀�땲�떎. �떎�떆 �뾽濡쒕뱶�븯�꽭�슂");
						}
					}
				}catch(Exception e) { e.printStackTrace(); }
			}
		}
		
		if(file_Name[0] != null && file_Name[0] != "") {
			dto.setThumbnail(file_Name[0]);
		}
		if(file_Name[1] != null && file_Name[1] != "") {
			dto.setImage(file_Name[1]);
		}
		
		service.update(dto);
		
		return "/song/imageboard1/writePro";
	}
	
	@RequestMapping("contentForm")
	public String contentForm(ImageBoard1DTO dto, HttpSession session, HttpServletRequest request, Model model) {

		String memId = (String)session.getAttribute("memId");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		service.addReadcount(num, (service.numContent(num).getReadcount() + 1));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		model.addAttribute("dto", service.numContent(num));
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("sdf", sdf);
		model.addAttribute("memId", memId);
		model.addAttribute("dto2", mpservice.user_info(memId));
		
		
		
		int pr_pageSize = 10;
		String pr_pageNum = request.getParameter("pr_pageNum");
		if(pr_pageNum == null) {
			pr_pageNum = "1";
		}
		int pr_currentPage = Integer.parseInt(pr_pageNum);
		int startRow = (pr_currentPage - 1) * pr_pageSize + 1;
		int endRow = pr_currentPage * pr_pageSize;
		
		int count = service.subBoardListCount(num);
		List contentBoard = service.subBoardList(num, String.valueOf(startRow), String.valueOf(endRow));
		
		if(count > 0) {
			int pageCount = count / pr_pageSize + (count % pr_pageSize == 0 ? 0 : 1);
			int startPage = (int)(pr_currentPage/10)*10+1;
			int pageBlock = 10;
			int endPage = startPage + pageBlock - 1;
			if(endPage > pageCount) endPage = pageCount;
			List<String> page = new ArrayList();
			for(int i = startPage; i <= endPage; i++) {
				page.add(String.valueOf(i));
			}
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("page", page);
		}
		model.addAttribute("count", count);
		model.addAttribute("contentBoard", contentBoard);
		model.addAttribute("pr_pageNum", pr_pageNum);
		model.addAttribute("pr_currentPage", pr_currentPage);
		
		
		
		
		return "/song/imageboard1/contentForm";
	}
	
	@RequestMapping("contentPro")
	public String contentPro(ImageBoard1DTO dto, HttpSession session, HttpServletRequest request, Model model) {		
		String num = request.getParameter("num");
		String pageNum = request.getParameter("pageNum");
		String pr_pageNum = request.getParameter("pr_pageNum");
		String pick = request.getParameter("pick");
		
		dto.setBoardnum(Integer.valueOf(num));
		dto.setIp(request.getRemoteAddr());
		if(pick == null || pick == "") {
			int maxNum = service.maxNum();
			dto.setRef(maxNum);
			dto.setRe_step(0);
			dto.setRe_level(0);
		}else {
			int pickNum = Integer.valueOf(pick);
			ImageBoard1DTO sample = service.boardcontent(pickNum);
			service.reUpdate(sample.getRef(), sample.getRe_step());
			
			System.out.println(sample.getRef());
			System.out.println(sample.getRe_step());
			System.out.println(sample.getRe_level());
			
			dto.setRef(sample.getRef());
			dto.setRe_step(sample.getRe_step() + 1);
			dto.setRe_level(sample.getRe_level() + 1);
		}
		
		service.subWrite(dto);
		
		return "forward:/imageboard1/contentForm?num="+num+"&pageNum="+pageNum+"&pr_pageNum="+pr_pageNum;
	}
	
	@RequestMapping("contentDelete")
	public String deleteForm(HttpServletRequest request, Model model) {		
		int num = Integer.valueOf(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		ImageBoard1DTO dto =service.numContent(num);
		
		model.addAttribute("dto", dto);
		model.addAttribute("pageNum", pageNum);
		
		return "/song/imageboard1/contentDelete";
	}
	
	@RequestMapping("contentDeletePro")
	public String deletePro(ImageBoard1DTO dto, HttpServletRequest request, Model model) {		
		String pageNum = request.getParameter("pageNum");
		int result = service.delete(dto);
		if(result == 1) {
			service.subDeleteAll(dto);
		}
		model.addAttribute("result", result);
		model.addAttribute("num", dto.getNum());
		model.addAttribute("pageNum", pageNum);
		
		return "/song/imageboard1/contentDeletePro";
	}
	
	@RequestMapping("subDelete")
	public String subDelete(ImageBoard1DTO dto, HttpServletRequest request, Model model) {		
		int contentNum = Integer.valueOf(request.getParameter("contentnum"));
		String num = request.getParameter("num");
		String pageNum = request.getParameter("pageNum");
		
		
		service.subDelete(contentNum);
		
		return "forward:/song/imageboard1/contentForm?num=" + num + "&pageNum=" + pageNum;
	}
	
}
