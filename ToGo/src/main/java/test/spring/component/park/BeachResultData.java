package test.spring.component.park;

import java.util.List;

public class BeachResultData {
	 private int currentCount;
	 private int matchCount;
	 private int page;
	 private int perPage;
	 private int totalCount;
	 private List<BeachDTO> data;

	    public int getCurrentCount() {
	        return currentCount;
	    }

	    public void setCurrentCount(int currentCount) {
	        this.currentCount = currentCount;
	    }

	    public List<BeachDTO> getData() {
	        return data;
	    }
	    

	    public void setData(List<BeachDTO> data) {
	        this.data = data;
	    }
	    public int getMatchCount() {
	        return matchCount;
	    }

	    public void setMatchCount(int matchCount) {
	        this.matchCount = matchCount;
	    }
	    public int getPage() {
	        return page;
	    }

	    public void setPage(int page) {
	        this.page = page;
	    }

		public int getPerPage() {
			return perPage;
		}

		public void setPerPage(int perPage) {
			this.perPage = perPage;
		}

		public int getTotalCount() {
			return totalCount;
		}

		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}
}
