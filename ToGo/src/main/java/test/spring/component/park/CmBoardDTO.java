package test.spring.component.park;

import java.util.Date;

import org.jsoup.nodes.Document;

public class CmBoardDTO {
	private Long cm_no;
	private Long cm_group;
	private Long step;
	private Long depth;
	private String cm_writer;
	private String cm_title;
	private String cm_content;
	private String status;
	private Date reg_date;
	private int readcount;
	private int commentCnt;
	private String filename;
    private Document doc;
    
    private int beginPage;
    private int endPage;

    private String option;
    private String keyword;
	public Long getCm_no() {
		return cm_no;
	}
	public void setCm_no(Long cm_no) {
		this.cm_no = cm_no;
	}
	public Long getCm_group() {
		return cm_group;
	}
	public void setCm_group(Long cm_group) {
		this.cm_group = cm_group;
	}
	public Long getStep() {
		return step;
	}
	public void setStep(Long step) {
		this.step = step;
	}
	public Long getDepth() {
		return depth;
	}
	public void setDepth(Long depth) {
		this.depth = depth;
	}
	public String getCm_writer() {
		return cm_writer;
	}
	public void setCm_writer(String cm_writer) {
		this.cm_writer = cm_writer;
	}
	public String getCm_title() {
		return cm_title;
	}
	public void setCm_title(String cm_title) {
		this.cm_title = cm_title;
	}
	public String getCm_content() {
		return cm_content;
	}
	public void setCm_content(String cm_content) {
		this.cm_content = cm_content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getCommentCnt() {
		return commentCnt;
	}
	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}
	public Document getDoc() {
		return doc;
	}
	public void setDoc(Document doc) {
		this.doc = doc;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
    
	
	
}
