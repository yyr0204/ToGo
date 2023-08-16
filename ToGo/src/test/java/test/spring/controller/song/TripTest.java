package test.spring.controller.song;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import test.spring.component.song.PlanDTO;
import test.spring.component.song.SampleListDTO;
import test.spring.repository.song.HaversineDAO;
import test.spring.repository.song.PermutationDAO;
import test.spring.repository.song.PlanListDAO;
import test.spring.service.song.TripService;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.*;
import test.spring.repository.song.PlanListDAO;
import test.spring.service.song.TripServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:/src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TripTest {
    public Map<String, List<SampleListDTO>> place(Model model, PlanDTO dto, HttpSession session) {
        PlanListDAO dao = new PlanListDAO();
        TripService service = new TripServiceImpl();
        boolean home = true;
        ////////////////////////////////////////////////////////////////////
        // 일정 입력값
        try {
            String area = "서울";
            int day = 3;
            System.out.println(day);
            model.addAttribute("day", day);
            ///////////////////////////////////////////////////////////////////
            String table = service.tableName(area);
            String memId = (String) session.getAttribute("memId");
            String userMbti;
            List<SampleListDTO> mainlist = null;
            List userAtmosphere = new ArrayList();
            if (memId != null) {
                userMbti = service.userMbti(memId);
                userAtmosphere = service.userAtmosphere(userMbti);
            }

            long startTime = System.currentTimeMillis();
            Loop:
            while (home) {

                long startTime1 = System.currentTimeMillis();
                List<SampleListDTO> main = dao.generateMainList(table, mainlist, userAtmosphere, day);
                long endTime1 = System.currentTimeMillis();
                long executionTime1 = endTime1 - startTime1;

                System.out.println("main일정 생성 : " + executionTime1 + "밀리초");

                long startTime2 = System.currentTimeMillis();
                List<SampleListDTO> optimizedMain = dao.optimizeMainList(main);
                long endTime2 = System.currentTimeMillis();
                long executionTime2 = endTime2 - startTime2;

                System.out.println("main일정 동선 최적화 : " + executionTime2 + "밀리초");

                long startTime3 = System.currentTimeMillis();
                List<List<SampleListDTO>> daySub = dao.generateDaySubList(table, userAtmosphere, optimizedMain);
                if (daySub.size() == 0) {
                    continue Loop;
                }
                long endTime3 = System.currentTimeMillis();
                long executionTime3 = endTime3 - startTime3;

                System.out.println("sub일정 생성 및 추가 : " + executionTime3 + "밀리초");

                long startTime4 = System.currentTimeMillis();
                List<SampleListDTO> finalList = dao.finalList(daySub, optimizedMain);
                long endTime4 = System.currentTimeMillis();
                long executionTime4 = endTime4 - startTime4;

                System.out.println("최종일정 호출 : " + executionTime4 + "밀리초");

                long startTime5 = System.currentTimeMillis();
                Map<String, List<SampleListDTO>> dayMap = dao.groupByDay(daySub, optimizedMain);
                long endTime5 = System.currentTimeMillis();
                long executionTime5 = endTime5 - startTime5;

                System.out.println("최종일정 호출 : " + executionTime5 + "밀리초");

                long endTime = System.currentTimeMillis();
                double executionTime = (double) (endTime - startTime) / (1000 * 60);

                System.out.println("최종일정 호출 : " + executionTime + "분");

                model.addAttribute("main", optimizedMain);
                model.addAttribute("finalList", finalList);
                model.addAttribute("dayMap", dayMap);

                home = false;

                return dayMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}