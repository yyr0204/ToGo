package test.spring.controller.song;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import test.spring.component.song.SampleListDTO;
import test.spring.repository.song.HaversineDAO;
import test.spring.repository.song.PermutationDAO;
import test.spring.service.song.TripService;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TripTest {
    @Autowired
    private TripService service;

    @Test
    @RequestMapping("plan")
    public void plan() {

        boolean home = true;
        mainLoop:
        while (home) {

            ////////////////////////////////////////////////////////////////////
            // 일정 입력값
            String area = "서울";
            List plan = new ArrayList();
            plan.add("7/10");
            plan.add("7/11");
            plan.add("7/12");
            plan.add("7/13");
            int day = plan.size();
            ///////////////////////////////////////////////////////////////////

            List<SampleListDTO> list;

            SampleListDTO dto;
            List<SampleListDTO> main = new ArrayList<>();

            int mainNum = 2 * day;    // 일정에따른 main 개수

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // main일정 생성
            double mainStart = System.nanoTime();
            Loop:
            for (int i = 0; main.size() < mainNum; i++) {
                list = service.mainList(area);    // 선택 지역의 전체 리스트 생성
                List sampleAll = new ArrayList();
                List radius = null;

                if (sampleAll != null) {
                    for (int j = 0; j < main.size(); j++) {
                        test.spring.repository.song.HaversineDAO ha = new test.spring.repository.song.HaversineDAO();
                        List test = null;
                        SampleListDTO sample = (SampleListDTO) main.get(j);
                        test = ha.radius(sample.Lat, sample.Lon, 7 - day);
                        radius = service.mainList(area, (double) test.get(0), (double) test.get(1), (double) test.get(2), (double) test.get(3));
                        list.removeAll(radius);
                    }
                }


                dto = list.get((int) (Math.random() * list.size()));

                main.add(dto);
                list.remove(dto);
                if (list.size() == 0) {
                    continue Loop;
                }
            }
            double mainEnd = System.nanoTime();
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // main일정 동선최적화
            double start2 = System.nanoTime();
            PermutationDAO dao = new PermutationDAO();
            ArrayList<SampleListDTO> mainArrayList = new ArrayList<>(main);
            ArrayList<ArrayList<SampleListDTO>> allPermutations = dao.permutation(mainArrayList);

            List<HashMap<String, Object>> result = new ArrayList<>();
            // allPermutations list contains all permutations of the main list
            long start1 = System.nanoTime();
            for (ArrayList<SampleListDTO> permutation : allPermutations) {

                double sum = 0;
                for (int i = 0; i < (permutation.size() - 1); i++) {
                    SampleListDTO sample1 = permutation.get(i);
                    SampleListDTO sample2 = permutation.get(i + 1);

                    double[] a = {sample1.getLat(), sample1.getLon()};
                    double[] b = {sample2.getLat(), sample2.getLon()};

                    double dLat = Math.toRadians(b[0] - a[0]);
                    double dLon = Math.toRadians(b[1] - a[1]);

                    double x = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(a[0])) * Math.cos(Math.toRadians(b[0])) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
                    double y = 2 * Math.atan2(Math.sqrt(x), Math.sqrt(1 - x));
                    double z = 6371 * y; // Distance in m

                    sum = sum + z;
                }
                HashMap<String, Object> max = new HashMap<>();
                max.put("sum", sum);
                max.put("permutation", permutation);
                result.add(max);
            }
            long end1 = System.nanoTime();


            result.sort(Comparator.comparingDouble(map -> (double) map.get("sum")));
            double end2 = System.nanoTime();
            HashMap map = result.get(0);
            main = (List<SampleListDTO>) map.get("permutation");
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // 동선에 맞는 서브일정 추가
            List<List> daySub = new ArrayList();

            for (int i = 0; i < day; i++) {
                int i2 = 2 * i;

                List lat = new ArrayList();
                List lon = new ArrayList();
                List lat1 = new ArrayList();
                List lon1 = new ArrayList();
                List lat2 = new ArrayList();
                List lon2 = new ArrayList();
                if (i2 != 0) {
                    List yesterday = daySub.get(daySub.size() - 1);
                    SampleListDTO dumy = (SampleListDTO) yesterday.get(3);
                    lat.add(dumy.getLat());
                    lat.add(main.get(i2).getLat());
                    lon.add(dumy.getLon());
                    lon.add(main.get(i2).getLon());
                    Collections.sort(lat);
                    Collections.sort(lon);
                }
                if (i2 < mainNum - 2) {

                    lat1.add(main.get(i2).getLat());
                    lat1.add(main.get(i2 + 1).getLat());
                    lon1.add(main.get(i2).getLon());
                    lon1.add(main.get(i2 + 1).getLon());

                    lat2.add(main.get(i2 + 1).getLat());
                    lat2.add(main.get(i2 + 2).getLat());
                    lon2.add(main.get(i2 + 1).getLon());
                    lon2.add(main.get(i2 + 2).getLon());

                    Collections.sort(lat1);
                    Collections.sort(lat2);
                    Collections.sort(lon1);
                    Collections.sort(lon2);

                } else {

                    lat1.add(main.get(i2).getLat());
                    lat1.add(main.get(i2 + 1).getLat());
                    lon1.add(main.get(i2).getLon());
                    lon1.add(main.get(i2 + 1).getLon());

                    lat2.add(main.get(i2 + 1).getLat());
                    lat2.add(main.get(i2).getLat());
                    lon2.add(main.get(i2 + 1).getLon());
                    lon2.add(main.get(i2).getLon());

                    Collections.sort(lat1);
                    Collections.sort(lat2);
                    Collections.sort(lon1);
                    Collections.sort(lon2);

                }

                test.spring.repository.song.HaversineDAO ha = new HaversineDAO();
                List radius1 = ha.radius((double) main.get(i2).getLat(), (double) main.get(i2).getLon(), (double) lat1.get(0), (double) lon1.get(0), (double) lat1.get(1), (double) lon1.get(1));
                List radius2 = ha.radius((double) main.get(i2 + 1).getLat(), (double) main.get(i2 + 1).getLon(), (double) lat1.get(0), (double) lon1.get(0), (double) lat1.get(1), (double) lon1.get(1));

                List breakfast_LatLon = new ArrayList();
                List luncheon_LatLon = ha.LatLon((double) lat1.get(0), (double) lon1.get(0), (double) lat1.get(1), (double) lon1.get(1));
                List subList_LatLon = ha.LatLon(((double) lat1.get(0) + (double) lat1.get(1)) / 2, ((double) lon1.get(0) + (double) lon1.get(1)) / 2, (double) lat1.get(1), (double) lon1.get(1));
                List abendessen_LatLon = ha.LatLon((double) lat2.get(0), (double) lon2.get(0), (double) lat2.get(1), (double) lon2.get(1));

                List subList = new ArrayList();
                List breakfast = new ArrayList();
                List breakfast1 = new ArrayList();
                List luncheon = new ArrayList();
                List abendessen = new ArrayList();
                if (i2 != 0) {
                    breakfast_LatLon = ha.LatLon((double) lat.get(0), (double) lon.get(0), (double) lat.get(1), (double) lon.get(1));
                    breakfast = service.breaklunch(area, (double) breakfast_LatLon.get(0), (double) breakfast_LatLon.get(1), (double) breakfast_LatLon.get(2), (double) breakfast_LatLon.get(3));

                } else {
                    breakfast = service.breaklunch(area, (double) radius1.get(0), (double) radius1.get(1), (double) radius1.get(2), (double) radius1.get(3));
                    breakfast1 = service.breaklunch(area, (double) radius2.get(0), (double) radius2.get(1), (double) radius2.get(2), (double) radius2.get(3));
                    breakfast.removeAll(breakfast1);
                }

                luncheon = service.breaklunch(area, (double) luncheon_LatLon.get(0), (double) luncheon_LatLon.get(1), (double) luncheon_LatLon.get(2), (double) luncheon_LatLon.get(3));
                subList = service.subList(area, (double) subList_LatLon.get(0), (double) subList_LatLon.get(1), (double) subList_LatLon.get(2), (double) subList_LatLon.get(3));
                abendessen = service.abendessen(area, (double) abendessen_LatLon.get(0), (double) abendessen_LatLon.get(1), (double) abendessen_LatLon.get(2), (double) abendessen_LatLon.get(3));

                ////////////////////////////////////////////////////////////////////////
                // 중복방지

                List sub = new ArrayList();
                List subAll = new ArrayList();

                if ((breakfast.size() == 0) || (luncheon.size() == 0) && (subList.size() == 0) || (abendessen.size() == 0)) {
                    continue mainLoop;
                }
                subAll.add(breakfast);
                subAll.add(luncheon);
                subAll.add(subList);
                subAll.add(abendessen);

                for (int y = 0; y < subAll.size(); y++) {
                    List arr = (List) subAll.get(y);
                    for (int x = 0; sub.size() < (y + 1); x++) {
                        dto = (SampleListDTO) arr.get((int) (Math.random() * arr.size()));
                        int num = 0;
                        if (daySub != null) {
                            for (List arr2 : daySub) {
                                if (arr2.contains(dto)) {
                                    num++;
                                }
                            }
                        }
                        if (num == 0 && !main.contains(dto) && !sub.contains(dto)) {
                            sub.add(dto);
                        }
                        if (x > 10) {
                            continue mainLoop;
                        }
                    }
                }
                daySub.add(sub);
            }

            /////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // finalList에 main이랑 서브일정 합치기
            List dayList = new ArrayList();
            int x = 0;
            for (int i = 0; i < main.size() / 2; i++) {
                List sample = new ArrayList();
                List sub = daySub.get(i);
                sample.add(sub.get(0));        // 아침식사
                sample.add(main.get(x));    // main일정
                sample.add(sub.get(1));        // 점심식사
                sample.add(sub.get(2));        // 서브일정
                sample.add(main.get(x + 1));    // main일정
                sample.add(sub.get(3));        // 저녁식사

                dayList.add(sample);
                x = x + 2;
            }

            List finalList = new ArrayList();
            for (int i = 0; i < dayList.size(); i++) {
                finalList.addAll((List) dayList.get(i));
            }


            //////////////////////////////////////////////////////////////////////
            HashMap<Integer, List<SampleListDTO>> daymap = new HashMap();
            int num = 0;
            for (int i = 0; i < day; i++) {
                List sample = new ArrayList();
                for (int a = 0; a < 6; a++) {
                    sample.add(finalList.get(num + a));
                }
                daymap.put(i, sample);
                num = (i + 1) * 6;
            }
            //////////////////////////////////////////////////////////////////////

            home = false;
            System.out.println("첫번째 구간" + (end1 - start1)*1000000000);
            System.out.println("동선최적화 구간" + (end2 - start2)*1000000000);
            System.out.println("메인 구간" + (mainEnd - mainStart)*1000000000);
        }


    }
}