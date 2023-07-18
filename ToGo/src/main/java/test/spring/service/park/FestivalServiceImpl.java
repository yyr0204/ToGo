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

//    public FstvlDTO testCrawling(String testURL) {
//        FstvlDTO dto = new FstvlDTO();
//        System.setProperty("webdriver.chrome.driver", "D:\\p\\selenium-server-standalone-master\\bin\\chromedriver.exe");
//
//        WebDriver driver = new ChromeDriver();
//        driver.get(testURL);
//
//        List<String> titleList = new ArrayList<>();
//        List<String> imageList = new ArrayList<>();
//        List<String> locationList = new ArrayList<>();
//        List<String> periodList = new ArrayList<>();
//        List<String> websiteList = new ArrayList<>();
//
//        for (int i = 1; i <= 12; i++) {
//            String url = "https://www.mcst.go.kr/kor/s_culture/festival/festivalList.jsp?pMenuCD=&pCurrentPage=" + i + "&pSearchType=&pSearchWord=&pSeq=&pSido=&pOrder=&pPeriod=&fromDt=&toDt=";
//            driver.navigate().to(url);
//
//            for (int j = 1; j <= 5; j++) {
//                WebElement element = driver.findElement(By.cssSelector("#content > div.contentWrap > ul.mediaWrap.color01 > li:nth-child(" + j + ") > a"));
//                element.click();
//
//                WebElement titleElement = driver.findElement(By.cssSelector("#content > div.contentWrap > div.viewWarp > div.view_title"));
//                WebElement imageElement = driver.findElement(By.cssSelector("#content > div.contentWrap > div.viewWarp > div.culture_view_img > img"));
//                WebElement locationElement = driver.findElement(By.cssSelector("#content > div.contentWrap > div.viewWarp > dl > dd:nth-child(2)"));
//                WebElement periodElement = driver.findElement(By.cssSelector("#content > div.contentWrap > div.viewWarp > dl > dd:nth-child(4)"));
//                WebElement websiteElement = driver.findElement(By.cssSelector("#content > div.contentWrap > div.viewWarp > dl > dd:nth-child(8)"));
//
//                String title = titleElement.getText();
//                String image_url = imageElement.getAttribute("src");
//                String location = locationElement.getText();
//                String period = periodElement.getText();
//                String website = websiteElement.getText();
//
//                titleList.add(title);
//                imageList.add(image_url);
//                locationList.add(location);
//                periodList.add(period);
//                websiteList.add(website);
//
//                driver.navigate().back();
//            }
//        }
//
//        driver.quit();
//        List<FstvlDTO> dtoList = new ArrayList<>();
//     // 크롤링한 데이터를 DTO에 설정
//        for (int i = 0; i < titleList.size(); i++) {
//            FstvlDTO festival = new FstvlDTO();
//            festival.setTitle(titleList.get(i));
//            festival.setImage_url(imageList.get(i));
//            festival.setLocation(locationList.get(i));
//            festival.setPeriod(periodList.get(i));
//            festival.setWebsite(websiteList.get(i));
//
//            dtoList.add(festival);
//        }
//
//        // 크롤링한 데이터를 DB에 저장
//        mapper.insertFestivals(dtoList);
//
//
//        return dto;
//    }

//   
//    @Override
//    public void saveFestivals(FstvlDTO dto) {
//        List<String> titleList = dto.getTitleList();
//        List<String> imageUrlList = dto.getImageUrlList();
//        List<String> locationList = dto.getLocationList();
//        List<String> periodList = dto.getPeriodList();
//        List<String> websiteList = dto.getWebsiteList();
//
//        for (int i = 0; i < titleList.size(); i++) {
//            FstvlDTO festival = new FstvlDTO();
//            festival.setTitle(titleList.get(i));
//            festival.setImage_url(imageUrlList.get(i)); // 수정된 부분
//            festival.setLocation(locationList.get(i));
//            festival.setPeriod(periodList.get(i));
//            festival.setWebsite(websiteList.get(i));
//
//            mapper.insertFestivals(festival);
//        }
//    }

}
