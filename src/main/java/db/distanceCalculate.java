package db;

public class distanceCalculate {
	public double distance(double lat1, double lng1, double lat2, double lng2) {
		int R = 6371; // 지구 반지름 (단위: km)
		  double dLat = deg2rad(lat2 - lat1);
		  double dLon = deg2rad(lng2 - lng1);
		  double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		            Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
		            Math.sin(dLon/2) * Math.sin(dLon/2);
		  double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		  double distanceCoordi = R * c; // 두 지점 간의 거리 (단위: km)
		  return distanceCoordi;
		}
		// 라디안 변환 함수
	public double deg2rad(double deg) {
		return deg * (Math.PI/180);
	}

}
