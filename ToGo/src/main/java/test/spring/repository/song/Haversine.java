package test.spring.repository.song;

import java.util.ArrayList;
import java.util.List;

public class Haversine {

    // Haversine 공식을 사용하여 두 좌표 사이의 거리 계산
    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        double earthRadius = 6371; // 지구 반지름 (단위: km)

        // 각도를 라디안으로 변환
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // Haversine 공식 계산
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;

        return distance;
    }
    
    public static List<Double> LatLon(double lat1, double lon1, double lat2, double lon2) {
        List<Double> list = new ArrayList<>();

        // 원의 중심 좌표 계산
        double centerLat = (lat1 + lat2) / 2;
        double centerLon = (lon1 + lon2) / 2;

        // 두 좌표 사이의 거리 계산 (Haversine 공식 사용)
        Haversine ha = new Haversine();
        double radius = ha.haversineDistance(lat1, lon1, lat2, lon2) / 2;

        // 원을 감싸는 정사각형의 위도 범위 계산
        double squareMinLat = centerLat - radius;
        double squareMaxLat = centerLat + radius;

        // 원을 감싸는 정사각형의 경도 범위 계산
        double squareMinLon = centerLon - radius;
        double squareMaxLon = centerLon + radius;

        list.add(squareMinLat);
        list.add(squareMaxLat);
        list.add(squareMinLon);
        list.add(squareMaxLon);

        return list;
    }
}
