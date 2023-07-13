package test.spring.service.park;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.spring.component.park.FstvlDTO;
import test.spring.mapper.park.FestivalMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class FestivalServiceImpl implements FestivalService {

    @Autowired
    private FestivalMapper mapper;
    @Override
    public List<FstvlDTO> fstvlList(FstvlDTO dto) {
        return mapper.fstvlList(dto);
    }

}
