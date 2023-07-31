package test.spring.repository.song;

import java.util.ArrayList;
import java.util.List;

public class HaversineDAO {

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
    
    // 두 좌표반경의 범위 계산
    public static List<Double> LatLon(double lat1, double lon1, double lat2, double lon2) {
       
       List<Double> list = new ArrayList<>();
       
       double centerLat = (lat1 + lat2) / 2;
        double centerLon = (lon1 + lon2) / 2;
       
       double lat_length = lat2 - lat1;
       double lon_length = lon2 - lon1;
       
       double squareMinLat = lat1;
        double squareMaxLat = lat2;
        double squareMinLon = lon1;
        double squareMaxLon = lon2;
       
       if(lat_length < lon_length) {
          if(lon_length > 0.3) {
             lon_length = 0.3;
          }
          squareMinLat = centerLat - lon_length/2;
            squareMaxLat = centerLat + lon_length/2;
            squareMinLon = lon1;
            squareMaxLon = lon2;
       }else if(lat_length > lon_length) {
          if(lat_length > 0.3) {
             lat_length = 0.3;
          }
          squareMinLat = lat1;
            squareMaxLat = lat2;
            squareMinLon = centerLon - lat_length/2;
            squareMaxLon = centerLon + lat_length/2;
       }else if(lat_length == lon_length) {
          squareMinLat = lat1;
            squareMaxLat = lat2;
            squareMinLon = lon1;
            squareMaxLon = lon2;
       }

        list.add(squareMinLat);
        list.add(squareMaxLat);
        list.add(squareMinLon);
        list.add(squareMaxLon);

        return list;
    }
    
    // ?km 반경 내의 최소 및 최대 위도, 경도 값 계산
    public static List<Double> radius(double lat1, double lon1, double length) {
       
       List<Double> list = new ArrayList<>();
       double radius = length; // 반경 범위 (단위: km)

        // 5km 반경 내에 있는 위도의 최소, 최대 값 계산
        double minLatitude = lat1 - (radius / 6371) * (180 / Math.PI);
        double maxLatitude = lat1 + (radius / 6371) * (180 / Math.PI);

        // 5km 반경 내에 있는 경도의 최소, 최대 값 계산
        double minLongitude = lon1 - (radius / 6371) * (180 / Math.PI) / Math.cos(Math.toRadians(lat1));
        double maxLongitude = lon1 + (radius / 6371) * (180 / Math.PI) / Math.cos(Math.toRadians(lat1));
        
        list.add(minLatitude);
        list.add(maxLatitude);
        list.add(minLongitude);
        list.add(maxLongitude);

        
        return list;
    }
    
    // ?km 반경 내의 최소 및 최대 위도, 경도 값 계산
    public static List<Double> radius(double Lat, double Lon, double lat1, double lon1, double lat2, double lon2) {
       
       List<Double> list = new ArrayList<>();
       // 두 좌표 사이의 거리 계산 (Haversine 공식 사용)
        HaversineDAO ha = new HaversineDAO();
        double radius = ha.haversineDistance(lat1, lon1, lat2, lon2);   // 반경 범위 (단위: km)

        // 5km 반경 내에 있는 위도의 최소, 최대 값 계산
        double minLatitude = Lat - (radius / 6371) * (180 / Math.PI);
        double maxLatitude = Lat + (radius / 6371) * (180 / Math.PI);

        // 5km 반경 내에 있는 경도의 최소, 최대 값 계산
        double minLongitude = Lon - (radius / 6371) * (180 / Math.PI) / Math.cos(Math.toRadians(lat1));
        double maxLongitude = Lon + (radius / 6371) * (180 / Math.PI) / Math.cos(Math.toRadians(lat1));
        
        list.add(minLatitude);
        list.add(maxLatitude);
        list.add(minLongitude);
        list.add(maxLongitude);

        
        return list;
    }

}