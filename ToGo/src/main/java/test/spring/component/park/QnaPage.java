package test.spring.component.park;

import java.util.List;
import org.springframework.stereotype.Component;


@Component
public class QnaPage extends PageVO {
	private List<QnaDTO> list;
	private String option;
    private String keyword;
	public List<QnaDTO> getList() {
		return list;
	}

	public void setList(List<QnaDTO> list) {
		this.list = list;
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
	
}