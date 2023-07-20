package test.spring.component.park;

import java.util.List;
import org.springframework.stereotype.Component;


@Component
public class QnaPage extends PageVO {
	private List<QnaDTO> list;

	public List<QnaDTO> getList() {
		return list;
	}

	public void setList(List<QnaDTO> list) {
		this.list = list;
	}
}