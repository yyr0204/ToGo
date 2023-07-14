package test.spring.component.park;

public class PageResolver {
	private int page;	// currentPage
	private int pageSize;	// rowPerpage
	private int total;
	private int totalPage;
	private int pagePerBlock = 5;
	private int startPage;// startPage
	private int endPage;//endpade

	public PageResolver(int page, int pageSize, int total) {
		this.page = page;
		this.pageSize = pageSize;
		this.total = total;

		totalPage = (int)(Math.ceil((double)total/pageSize));
		startPage = page - (page - 1) % pagePerBlock;
		endPage = startPage + pagePerBlock - 1;
		if (endPage > totalPage) endPage = totalPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPagePerBlock() {
		return pagePerBlock;
	}

	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
}
