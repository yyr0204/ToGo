package test.spring.repository.song;

import java.util.ArrayList;
import java.util.List;

public class Haversine {

<<<<<<< HEAD
    // Haversine 공식을 사용하여 두 좌표 사이의 거리 계산
    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        double earthRadius = 6371; // 지구 반지름 (단위: km)

        // 각도를 라디안으로 변환
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // Haversine 공식 계산
=======
    // Haversine ������ ����Ͽ� �� ��ǥ ������ �Ÿ� ���
    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        double earthRadius = 6371; // ���� ������ (����: km)

        // ������ �������� ��ȯ
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // Haversine ���� ���
>>>>>>> develop
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;

        return distance;
    }
<<<<<<< HEAD
    
    // 두 좌표반경의 범위 계산
    public static List<Double> LatLon(double lat1, double lon1, double lat2, double lon2) {
    	
    	List<Double> list = new ArrayList<>();

        // 원의 중심 좌표 계산
        double centerLat = (lat1 + lat2) / 2;
        double centerLon = (lon1 + lon2) / 2;

        // 두 좌표 사이의 거리 계산 (Haversine 공식 사용)
        Haversine ha = new Haversine();
        double distance = ha.haversineDistance(lat1, lon1, lat2, lon2);

        // 반경을 통해 위도 범위 계산
        double radiusLat = Math.toDegrees(distance / 6371); // 위도 1도 당 거리는 약 111km
        double squareMinLat = centerLat - radiusLat + (radiusLat/100);
        double squareMaxLat = centerLat + radiusLat - (radiusLat/100);

        // 반경을 통해 경도 범위 계산
        double radiusLon = Math.toDegrees(distance / (6371 * Math.cos(Math.toRadians(centerLat)))); // 경도 1도 당 거리는 약 111km * cos(위도)
        double squareMinLon = centerLon - radiusLon + (radiusLon/100);
        double squareMaxLon = centerLon + radiusLon - (radiusLon/100);
=======

    // �� ��ǥ�ݰ��� ���� ���
    public static List<Double> LatLon(double lat1, double lon1, double lat2, double lon2) {

        List<Double> list = new ArrayList<>();

        // ���� �߽� ��ǥ ���
        double centerLat = (lat1 + lat2) / 2;
        double centerLon = (lon1 + lon2) / 2;

        // �� ��ǥ ������ �Ÿ� ��� (Haversine ���� ���)
        Haversine ha = new Haversine();
        double distance = ha.haversineDistance(lat1, lon1, lat2, lon2);

        // �ݰ��� ���� ���� ���� ���
        double radiusLat = Math.toDegrees(distance / 6371); // ���� 1�� �� �Ÿ��� �� 111km
        double squareMinLat = centerLat - radiusLat;
        double squareMaxLat = centerLat + radiusLat;

        // �ݰ��� ���� �浵 ���� ���
        double radiusLon = Math.toDegrees(distance / (6371 * Math.cos(Math.toRadians(centerLat)))); // �浵 1�� �� �Ÿ��� �� 111km * cos(����)
        double squareMinLon = centerLon - radiusLon;
        double squareMaxLon = centerLon + radiusLon;
>>>>>>> develop

        list.add(squareMinLat);
        list.add(squareMaxLat);
        list.add(squareMinLon);
        list.add(squareMaxLon);
<<<<<<< HEAD
        
        System.out.println(squareMinLat);
        System.out.println(squareMaxLat);
        System.out.println(squareMinLon);
        System.out.println(squareMaxLon);
        System.out.println();
        
=======

>>>>>>> develop
        return list;
    }

}
