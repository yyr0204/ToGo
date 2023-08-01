package test.spring.component.kim;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pos {
	private double lat;
    private double lng;
    
    @Override
    public String toString() {
        return "Pos{lat=" + lat + ", lng=" + lng + "}";
    }
}
