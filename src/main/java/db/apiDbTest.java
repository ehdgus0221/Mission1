package db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import api.dbSaveTest;

public class apiDbTest {
	public void register() {
	  dbSaveTest wifiNum = new dbSaveTest();
	  Long wifiTotalNum = wifiNum.totalWifi();
	  // 1. jdbc 드라이버 연동하기 // 2. 커넥션 객체 생성 // 3. 스테이트먼트 객체 생성 // 4. 쿼리 실행 // 5. 결과수행 // 6. 객체 연결 해제(close) 
		String dbUrl ="jdbc:sqlite:/C:\\sqlite-tools-win32-x86-3420000\\dongHyeon.db"; 
		//드라이버 로드 
		try { 
			Class.forName("org.sqlite.JDBC"); 
			} catch (ClassNotFoundException e) { 
			e.printStackTrace(); 
		}
	  
	  Connection connection = null; 
	  PreparedStatement preparedStatement = null;
	  ResultSet rs = null;
	  
	  try { connection = DriverManager.getConnection(dbUrl);

		

		try {
			String key = "4668544c47656864383278504b4f49";
			// 파싱한 데이터를 저장할 변수
			String result = "";

			int startRow = 1;
			int lastRow = 1000;
			


			URL url = new URL("http://openapi.seoul.go.kr:8088/" + key + "/json/TbPublicWifiInfo/" + startRow + "/" + lastRow + "/");

			BufferedReader bf;

			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

			result = bf.readLine();

	    	JSONParser jsonParser = new JSONParser();
	    	JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
	    	JSONObject TbPublicWifiInfo = (JSONObject)jsonObject.get("TbPublicWifiInfo");
	    	long list_total_count = (long)TbPublicWifiInfo.get("list_total_count");

	    	System.out.print(list_total_count);
	    	System.out.println("개의 WIFI 정보를 정상적으로 저장하였습니다.");
	    	
	    	
	    	// 파싱
	    	
	    	JSONArray row = (JSONArray)TbPublicWifiInfo.get("row");


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
      	

	  String sql =
	  " insert into wifi_info (distance, X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) "
	  + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
	  
	  preparedStatement = connection.prepareStatement(sql);
	  
	  preparedStatement.setString(1, "100");
	  preparedStatement.setString(2, (String) rows.get("X_SWIFI_MGR_NO"));
	  preparedStatement.setString(3, (String) rows.get("X_SWIFI_WRDOFC"));
	  preparedStatement.setString(4, (String) rows.get("X_SWIFI_MAIN_NM"));
	  preparedStatement.setString(5, (String) rows.get("X_SWIFI_ADRES1"));
	  preparedStatement.setString(6, (String) rows.get("X_SWIFI_ADRES2"));
	  preparedStatement.setString(7, (String) rows.get("X_SWIFI_INSTL_FLOOR"));
	  preparedStatement.setString(8, (String) rows.get("X_SWIFI_INSTL_TY"));
	  preparedStatement.setString(9, (String) rows.get("X_SWIFI_INSTL_MBY"));
	  preparedStatement.setString(10, (String) rows.get("X_SWIFI_SVC_SE"));
	  preparedStatement.setString(11, (String) rows.get("X_SWIFI_CMCWR"));
	  preparedStatement.setString(12, (String) rows.get("X_SWIFI_CNSTC_YEAR"));
	  preparedStatement.setString(13, (String) rows.get("X_SWIFI_INOUT_DOOR"));
	  preparedStatement.setString(14, (String) rows.get("X_SWIFI_REMARS3"));
	  preparedStatement.setString(15, (String) rows.get("LAT"));
	  preparedStatement.setString(16, (String) rows.get("LNT"));
	  preparedStatement.setString(17,(String) rows.get("WORK_DTTM"));

	  int affected = preparedStatement.executeUpdate();
	  
		  if (affected > 0) { 
			  System.out.println(" 저장 성공 "); 
		  } 
		  else {
			  System.out.println(" 저장 실패"); }
			}
		}
		catch(Exception e) {
				e.printStackTrace();
			}
		  } catch (SQLException e) { throw new RuntimeException(e);
	  
	  } finally { //열었던 객체 닫기 rs, statement, connection
	  
	  try { if (rs != null && !rs.isClosed()) { 
		  rs.close(); 
	  	} 
	  } catch (SQLException e) { 
		  e.printStackTrace(); }
	  
	  try { 
		  if (preparedStatement != null && !preparedStatement.isClosed()) {
			  preparedStatement.close(); 
			  } 
		  } catch (SQLException e) {
			  e.printStackTrace();
	  } try { if (connection != null && !connection.isClosed()) {
		  connection.close(); 
		  } 
	  } catch (SQLException e) {
		  e.printStackTrace(); 
		  } 
	  }
	  return;
	}

}
