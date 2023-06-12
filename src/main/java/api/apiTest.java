package api;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class apiTest {
	public static void main(String[] args) {
		// 인증키 (개인이 받아와야함)
				String key = "4668544c47656864383278504b4f49";
		    	int startRow = 1;
		    	int lastRow = 5;
		    	// 파싱한 데이터를 저장할 변수
		    	String result = "";

		    	try {

		    		URL url = new URL("http://openapi.seoul.go.kr:8088/" + key + "/json/TbPublicWifiInfo/" + startRow + "/" + lastRow + "/");

		    		BufferedReader bf;

		    		bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

		    		result = bf.readLine();

		        	JSONParser jsonParser = new JSONParser();
		        	JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
		        	JSONObject TbPublicWifiInfo = (JSONObject)jsonObject.get("TbPublicWifiInfo");
		        	
		        	System.out.println(TbPublicWifiInfo.get("list_total_count"));

		        	//JSONArray X_SWIFI_MGR_NO = (JSONArray)row.get("X_SWIFI_MGR_NO");
		        	//JSONObject X_SWIFI_MGR_NO_NUM = (JSONObject)X_SWIFI_MGR_NO.get(0);
		        	//System.out.println("검색된 WIFI 수 : " + list_total_count);
		        	//System.out.println("관리번호 : " + row.get("X_SWIFI_MGR_NO"));
		        	
		        	
		        	JSONArray row = (JSONArray)TbPublicWifiInfo.get("row");

		        	Long list_total_count = (Long)TbPublicWifiInfo.get("list_total_count");
		        	System.out.println(list_total_count);
		        	String X_SWIFI_MGR_NO = "";
		           	
		        	for(int i = 0; i < row.size(); i++) {
		            	JSONObject rows = (JSONObject)row.get(i);
		            	System.out.print(rows.get("X_SWIFI_MGR_NO")+ " ");
		            	
		            	System.out.print(rows.get("X_SWIFI_WRDOFC")+ " ");// 한 줄에 있는 모든 데이터
		            	System.out.print(rows.get("X_SWIFI_MAIN_NM")+ " ");
		            	System.out.print(rows.get("X_SWIFI_ADRES1")+ " ");
		            	System.out.print(rows.get("X_SWIFI_ADRES2")+ " ");
		            	System.out.print(rows.get("X_SWIFI_INSTL_FLOOR")+ " ");
		            	System.out.print(rows.get("X_SWIFI_INSTL_TY")+ " ");
		            	System.out.print(rows.get("X_SWIFI_INSTL_MBY")+ " ");
		            	System.out.print(rows.get("X_SWIFI_SVC_SE")+ " ");
		            	System.out.print(rows.get("X_SWIFI_CMCWR")+ " ");
		            	System.out.print(rows.get("X_SWIFI_CNSTC_YEAR")+ " ");
		            	System.out.print(rows.get("X_SWIFI_INOUT_DOOR")+ " ");
		            	System.out.print(rows.get("X_SWIFI_REMARS3")+ " ");
		            	System.out.print(rows.get("LAT")+ " ");
		            	System.out.print(rows.get("LNT")+ " ");
		            	System.out.print(rows.get("WORK_DTTM")+ " ");
		            	System.out.println();
		            	X_SWIFI_MGR_NO += rows.get("X_SWIFI_MGR_NO") + " ";	// 데이터 중 X_SWIFI_MGR_NO
		        	}


		    	}catch(Exception e) {
		    		e.printStackTrace();
		    	}
		    }

}
