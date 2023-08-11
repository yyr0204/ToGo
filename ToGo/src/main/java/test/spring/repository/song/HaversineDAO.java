package test.spring.repository.song;

import java.util.ArrayList;
import java.util.List;

public class HaversineDAO {

	// 두 지점의 위도와 경도를 이용하여 Haversine 거리를 계산합니다.
    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        double earthRadius = 6371; // 지구 반지름 (단위: km)

        // 위도와 경도를 라디안으로 변환합니다.
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // Haversine 공식을 사용하여 거리를 계산합니다.
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;
        
        return distance;
    }
    
    // 두 대각선 꼭지점을 이용하여 정사각형의 위도와 경도 범위를 계산합니다.
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
    
    // 주어진 지점으로부터 일정 반경(단위: km) 내의 위도와 경도 범위를 계산합니다.
	public static List<Double> radius(double lat1, double lon1, double radius) {
       
		List<Double> bounds = new ArrayList<>();
        double earthRadius = 6371; // 지구 반지름 (단위: km)
       
        // 최소 및 최대 위도 범위를 계산합니다.
        double minLatitude = lat1 - (radius / earthRadius) * (180 / Math.PI);
        double maxLatitude = lat1 + (radius / earthRadius) * (180 / Math.PI);

        // 최소 및 최대 경도 범위를 계산합니다.
        double minLongitude = lon1 - (radius / earthRadius) * (180 / Math.PI) / Math.cos(Math.toRadians(lat1));
        double maxLongitude = lon1 + (radius / earthRadius) * (180 / Math.PI) / Math.cos(Math.toRadians(lat1));
        
        bounds.add(minLatitude);
        bounds.add(maxLatitude);
        bounds.add(minLongitude);
        bounds.add(maxLongitude);

        return bounds;
    }
    
	// 주어진 지점과 경계 상자로부터 일정 반경(단위: km) 내의 위도와 경도 범위를 계산합니다.
    public static List<Double> radius(double Lat, double Lon, double lat1, double lon1, double lat2, double lon2) {
       
    	List<Double> list = new ArrayList<>();
        HaversineDAO ha = new HaversineDAO();
        double radius = ha.haversineDistance(lat1, lon1, lat2, lon2);   // 반경 (단위: km)

        // 최소 및 최대 위도 범위를 계산합니다.
        double minLatitude = Lat - (radius / 6371) * (180 / Math.PI);
        double maxLatitude = Lat + (radius / 6371) * (180 / Math.PI);

        // 최소 및 최대 경도 범위를 계산합니다.
        double minLongitude = Lon - (radius / 6371) * (180 / Math.PI) / Math.cos(Math.toRadians(lat1));
        double maxLongitude = Lon + (radius / 6371) * (180 / Math.PI) / Math.cos(Math.toRadians(lat1));
        
        list.add(minLatitude);
        list.add(maxLatitude);
        list.add(minLongitude);
        list.add(maxLongitude);

        
        return list;
    }

}