package api;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class dbSaveTest {
	public Long totalWifi() {
		String key = "4668544c47656864383278504b4f49";
		int startRow = 1;
		int lastRow = 1;
		// 파싱한 데이터를 저장할 변수
		String result = "";
		Long list_total_count = null;
		

		try {
			URL url = new URL("http://openapi.seoul.go.kr:8088/" + key + "/json/TbPublicWifiInfo/" + startRow + "/" + lastRow + "/");

			BufferedReader bf;

			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

			result = bf.readLine();

	    	JSONParser jsonParser = new JSONParser();
	    	JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
	    	JSONObject TbPublicWifiInfo = (JSONObject)jsonObject.get("TbPublicWifiInfo");
	    	list_total_count = (long)TbPublicWifiInfo.get("list_total_count");

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list_total_count;
	}

}