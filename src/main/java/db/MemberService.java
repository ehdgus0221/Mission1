package db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import api.dbSaveTest;

public class MemberService {

	public List<Member> list() {

		List<Member> memberList = new ArrayList<>();

		// sqlite db와 연동
		String url ="jdbc:sqlite:/C:\\sqlite-tools-win32-x86-3420000\\dongHyeon.db"; 

		// 1. jdbc 드라이버 연동하기
		// 2. 커넥션 객체 생성
		// 3. 스테이트먼트 객체 생성
		// 4. 쿼리 실행
		// 5. 결과 수행
		// 6. 객체 연결 해제(close)

		// 드라이버 로드
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(url);

			String sql = " select *" + " from wifi_info ";

			preparedStatement = connection.prepareStatement(sql);
			// sql 구문 ?부분에 넣는 명령어
			// preparedStatement.setString(1, memberTypeValue);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {

				String distance = rs.getString("distance");
				String x_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
				String x_SWIFI_WRDOFC = rs.getString("X_SWIFI_WRDOFC");
				String x_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
				String x_SWIFI_ADRES1 = rs.getString("X_SWIFI_ADRES1");
				String x_SWIFI_ADRES2 = rs.getString("X_SWIFI_ADRES2");
				String x_SWIFI_INSTL_FLOOR = rs.getString("X_SWIFI_INSTL_FLOOR");
				String x_SWIFI_INSTL_TY = rs.getString("X_SWIFI_INSTL_TY");
				String x_SWIFI_INSTL_MBY = rs.getString("X_SWIFI_INSTL_MBY");
				String x_SWIFI_SVC_SE = rs.getString("X_SWIFI_SVC_SE");
				String x_SWIFI_CMCWR = rs.getString("X_SWIFI_CMCWR");
				String x_SWIFI_CNSTC_YEAR = rs.getString("X_SWIFI_CNSTC_YEAR");
				String x_SWIFI_INOUT_DOOR = rs.getString("X_SWIFI_INOUT_DOOR");
				String x_SWIFI_REMARS3 = rs.getString("X_SWIFI_REMARS3");
				String lat = rs.getString("LAT");
				String lnt = rs.getString("LNT");
				String wORK_DTTM = rs.getString("WORK_DTTM");
				
				Member member = new Member();
				member.setDistance(distance);
				member.setX_SWIFI_MGR_NO(x_SWIFI_MGR_NO);
				member.setX_SWIFI_WRDOFC(x_SWIFI_WRDOFC);
				member.setX_SWIFI_MAIN_NM(x_SWIFI_MAIN_NM);
				member.setX_SWIFI_ADRES1(x_SWIFI_ADRES1);
				member.setX_SWIFI_ADRES2(x_SWIFI_ADRES2);
				member.setX_SWIFI_INSTL_FLOOR(x_SWIFI_INSTL_FLOOR);
				member.setX_SWIFI_INSTL_TY(x_SWIFI_INSTL_TY);
				member.setX_SWIFI_INSTL_MBY(x_SWIFI_INSTL_MBY);
				member.setX_SWIFI_SVC_SE(x_SWIFI_SVC_SE);
				member.setX_SWIFI_CMCWR(x_SWIFI_CMCWR);
				member.setX_SWIFI_CNSTC_YEAR(x_SWIFI_CNSTC_YEAR);
				member.setX_SWIFI_INOUT_DOOR(x_SWIFI_INOUT_DOOR);
				member.setX_SWIFI_REMARS3(x_SWIFI_REMARS3);
				member.setLAT(lat);
				member.setLNT(lnt);
				member.setWORK_DTTM(wORK_DTTM);
				
				memberList.add(member);

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			// 열었던 객체 닫기 rs, statement, connection

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return memberList;
	}

	public void register(long startRow, long lastRow, double lat, double lng) {
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
		  
		  try { 
			  connection = DriverManager.getConnection(dbUrl);	
			  connection.setAutoCommit(false);

			try {
				String key = "4668544c47656864383278504b4f49";
				// 파싱한 데이터를 저장할 변수
				String result = "";

				URL url = new URL("http://openapi.seoul.go.kr:8088/" + key + "/json/TbPublicWifiInfo/" + startRow + "/" + lastRow + "/");

				BufferedReader bf;

				bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

				result = bf.readLine();

		    	JSONParser jsonParser = new JSONParser();
		    	JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
		    	JSONObject TbPublicWifiInfo = (JSONObject)jsonObject.get("TbPublicWifiInfo");

		    	
		    	// 파싱
		    	
		    	JSONArray row = (JSONArray)TbPublicWifiInfo.get("row");
		      	
				// 1. wifi_twenty 테이블의 모든 레코드 삭제


		      	for(int i = 0; i < row.size(); i++) {
		          	JSONObject rows = (JSONObject)row.get(i);
		      
				  String sql =
				  " insert into wifi_info (distance, x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm, x_swifi_adres1, x_swifi_adres2, x_swifi_instl_floor, x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se, x_swifi_cmcwr, x_swifi_cnstc_year, x_swifi_inout_door, x_swifi_remars3, lat, lnt, work_dttm) "
				  + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
				  
				  preparedStatement = connection.prepareStatement(sql);
				  
				  distanceCalculate coordinate = new distanceCalculate();
				  
				  preparedStatement.setDouble(1, (coordinate.distance(lat, lng, Double.valueOf((String) rows.get("LNT")), Double.valueOf((String)rows.get("LAT")))));
				  
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
		          preparedStatement.setInt(12, Integer.valueOf((String) rows.get("X_SWIFI_CNSTC_YEAR")));
				  preparedStatement.setString(13, (String) rows.get("X_SWIFI_INOUT_DOOR"));
				  preparedStatement.setString(14, (String) rows.get("X_SWIFI_REMARS3"));
				  preparedStatement.setString(15, (String) rows.get("LNT"));
				  preparedStatement.setString(16, (String) rows.get("LAT"));
				  preparedStatement.setString(17,(String) rows.get("WORK_DTTM"));
				  
				  preparedStatement.addBatch();
		          preparedStatement.executeBatch();
		          preparedStatement.clearBatch();		    
		
				}
		        connection.commit();
		        System.out.println("완료");
				}
				catch(Exception e) {
						e.printStackTrace();
						connection.rollback();
					}
				  } catch (SQLException e) { throw new RuntimeException(e);
			  
			  } finally { //열었던 객체 닫기 rs, statement, connection
			  
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
			 
			}

	public void createTwentyList() {

		String url ="jdbc:sqlite:C:\\sqlite-tools-win32-x86-3420000\\dongHyeon.db"; 
		// 1. jdbc 드라이버 연동하기
		// 2. 커넥션 객체 생성
		// 3. 스테이트먼트 객체 생성
		// 4. 쿼리 실행
		// 5. 결과 수행
		// 6. 객체 연결 해제(close)

		// 드라이버 로드
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		Statement statement = null;

		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			// 1. wifi_twenty 테이블의 모든 레코드 삭제
			try {
			    String deleteSql = "DROP TABLE IF EXISTS wifi_twenty";
			    statement.executeUpdate(deleteSql);
			} catch (SQLException e) {
			    // 오류 발생 시 예외 처리
			    // 테이블이 존재하지 않는 경우에는 오류가 발생할 수 있으므로, 예외를 무시하고 진행합니다.
			}

			// 2. wifi_info의 상위 20개 레코드를 wifi_twenty로 저장
			String createSql = "CREATE TABLE wifi_twenty AS SELECT * FROM wifi_info ORDER BY distance LIMIT 20";
			statement.executeUpdate(createSql);


	        System.out.println("wifi_twenty 테이블 생성 및 데이터 추가 완료");


		} catch (SQLException e) {
	        throw new RuntimeException("Failed to create wifi_twenty table", e);

		} 

		return;
	}
	public List<Member> twentyWifiList() {

		List<Member> twentyWifiList = new ArrayList<>();

		// sqlite db와 연동
		String url ="jdbc:sqlite:C:\\sqlite-tools-win32-x86-3420000\\dongHyeon.db"; 

		// 1. jdbc 드라이버 연동하기
		// 2. 커넥션 객체 생성
		// 3. 스테이트먼트 객체 생성
		// 4. 쿼리 실행
		// 5. 결과 수행
		// 6. 객체 연결 해제(close)

		// 드라이버 로드
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(url);

			String sql = " select *" + " from wifi_twenty ";

			preparedStatement = connection.prepareStatement(sql);
			// sql 구문 ?부분에 넣는 명령어
			// preparedStatement.setString(1, memberTypeValue);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {

				String distance = rs.getString("distance");
				String x_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
				String x_SWIFI_WRDOFC = rs.getString("X_SWIFI_WRDOFC");
				String x_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
				String x_SWIFI_ADRES1 = rs.getString("X_SWIFI_ADRES1");
				String x_SWIFI_ADRES2 = rs.getString("X_SWIFI_ADRES2");
				String x_SWIFI_INSTL_FLOOR = rs.getString("X_SWIFI_INSTL_FLOOR");
				String x_SWIFI_INSTL_TY = rs.getString("X_SWIFI_INSTL_TY");
				String x_SWIFI_INSTL_MBY = rs.getString("X_SWIFI_INSTL_MBY");
				String x_SWIFI_SVC_SE = rs.getString("X_SWIFI_SVC_SE");
				String x_SWIFI_CMCWR = rs.getString("X_SWIFI_CMCWR");
				String x_SWIFI_CNSTC_YEAR = rs.getString("X_SWIFI_CNSTC_YEAR");
				String x_SWIFI_INOUT_DOOR = rs.getString("X_SWIFI_INOUT_DOOR");
				String x_SWIFI_REMARS3 = rs.getString("X_SWIFI_REMARS3");
				String lat = rs.getString("LAT");
				String lnt = rs.getString("LNT");
				String wORK_DTTM = rs.getString("WORK_DTTM");
				
				Member twentyMember = new Member();
				twentyMember.setDistance(distance);
				twentyMember.setX_SWIFI_MGR_NO(x_SWIFI_MGR_NO);
				twentyMember.setX_SWIFI_WRDOFC(x_SWIFI_WRDOFC);
				twentyMember.setX_SWIFI_MAIN_NM(x_SWIFI_MAIN_NM);
				twentyMember.setX_SWIFI_ADRES1(x_SWIFI_ADRES1);
				twentyMember.setX_SWIFI_ADRES2(x_SWIFI_ADRES2);
				twentyMember.setX_SWIFI_INSTL_FLOOR(x_SWIFI_INSTL_FLOOR);
				twentyMember.setX_SWIFI_INSTL_TY(x_SWIFI_INSTL_TY);
				twentyMember.setX_SWIFI_INSTL_MBY(x_SWIFI_INSTL_MBY);
				twentyMember.setX_SWIFI_SVC_SE(x_SWIFI_SVC_SE);
				twentyMember.setX_SWIFI_CMCWR(x_SWIFI_CMCWR);
				twentyMember.setX_SWIFI_CNSTC_YEAR(x_SWIFI_CNSTC_YEAR);
				twentyMember.setX_SWIFI_INOUT_DOOR(x_SWIFI_INOUT_DOOR);
				twentyMember.setX_SWIFI_REMARS3(x_SWIFI_REMARS3);
				twentyMember.setLAT(lat);
				twentyMember.setLNT(lnt);
				twentyMember.setWORK_DTTM(wORK_DTTM);
				
				twentyWifiList.add(twentyMember);

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			// 열었던 객체 닫기 rs, statement, connection

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return twentyWifiList;
	}
	public void deleteAllRecord(String tableName) {	// API를 받아올때 마다 기존 테이블 내에 존재하는 레코드를 제거하기 위해 사용
		// 테이블 이름 유효성 검사
		if (!tableName.matches("[a-zA-Z0-9_]+")) {
		    throw new IllegalArgumentException("Invalid table name");
		}
		String url ="jdbc:sqlite:C:\\sqlite-tools-win32-x86-3420000\\dongHyeon.db"; 
		// 1. jdbc 드라이버 연동하기
		// 2. 커넥션 객체 생성
		// 3. 스테이트먼트 객체 생성
		// 4. 쿼리 실행
		// 5. 결과 수행
		// 6. 객체 연결 해제(close)

		// 드라이버 로드
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
	        throw new RuntimeException("Failed to load JDBC driver", e);
		}
		
	    try (Connection connection = DriverManager.getConnection(url);
	        Statement statement = connection.createStatement()) {

	    	String sql = "DELETE FROM " + tableName;
	        statement.executeUpdate(sql);
	        
	        System.out.println(tableName + " 테이블 레코드 제거 완료");
			

		} catch (SQLException e) {
	        throw new RuntimeException("Failed to delete records from table", e);
		} 
	   // trh-with-resources 구문은 사용 후 statement, connection객체가 자동으로 닫힌다.
	   

		return;
	}
	public void insertLocationHistory(double x, double y) {

		SimpleDateFormat formatDate = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss.0");
		
		Calendar time = Calendar.getInstance();
		String format_time = formatDate.format(time.getTime());
					String dbUrl ="jdbc:sqlite:/C:\\sqlite-tools-win32-x86-3420000\\dongHyeon.db"; 
					//드라이버 로드 
					try { 
						Class.forName("org.sqlite.JDBC"); 
						} catch (ClassNotFoundException e) { 
						e.printStackTrace(); 
					}
				  
				  Connection connection = null; 
				  PreparedStatement preparedStatement = null;
				  
				  try { 
					  connection = DriverManager.getConnection(dbUrl);	
					  connection.setAutoCommit(false);


						  String sql =
						  " insert into location_history (x_coordi, y_coordi, load_date) "
						  + " values ( ?, ?, ?) ";
						  
						  preparedStatement = connection.prepareStatement(sql);
						  
						  preparedStatement.setDouble(1, x);
						  preparedStatement.setDouble(2, y );
						  preparedStatement.setString(3, format_time);

						  
						  preparedStatement.addBatch();
				          preparedStatement.executeBatch();
				          preparedStatement.clearBatch();		    
				
						
				        connection.commit();
				        
				        System.out.println("insertLocationHistory 완료");
						}
						catch(Exception e) {
								e.printStackTrace();
								try {
									connection.rollback();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							
						  } finally { //열었던 객체 닫기 rs, statement, connection
					  
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

	
	public List<Member> historyList() {
		List<Member> historyList = new ArrayList<>();

		// sqlite db와 연동
		String url ="jdbc:sqlite:C:\\sqlite-tools-win32-x86-3420000\\dongHyeon.db"; 

		// 1. jdbc 드라이버 연동하기
		// 2. 커넥션 객체 생성
		// 3. 스테이트먼트 객체 생성
		// 4. 쿼리 실행
		// 5. 결과 수행
		// 6. 객체 연결 해제(close)

		// 드라이버 로드
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(url);

			String sql = " select *" + " from location_history ";

			preparedStatement = connection.prepareStatement(sql);
			// sql 구문 ?부분에 넣는 명령어
			// preparedStatement.setString(1, memberTypeValue);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {

				int id =  rs.getInt("id");
				double x_coordi = rs.getDouble("x_coordi");
				double y_coordi = rs.getDouble("y_coordi");
				String load_date = rs.getString("load_date");
				

				Member historyMember = new Member();
				historyMember.setId(id);
				historyMember.setX_coordi(x_coordi);
				historyMember.setY_coordi(y_coordi);
				historyMember.setLoad_date(load_date);

				
				historyList.add(historyMember);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			// 열었던 객체 닫기 rs, statement, connection

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return historyList;
	
	}

	public void deleteHistoryList(int idx) {	// API를 받아올때 마다 기존 테이블 내에 존재하는 레코드를 제거하기 위해 사용
		// 테이블 이름 유효성 검사

		String url ="jdbc:sqlite:C:\\sqlite-tools-win32-x86-3420000\\dongHyeon.db"; 
		// 1. jdbc 드라이버 연동하기
		// 2. 커넥션 객체 생성
		// 3. 스테이트먼트 객체 생성
		// 4. 쿼리 실행
		// 5. 결과 수행
		// 6. 객체 연결 해제(close)

		// 드라이버 로드
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
	        throw new RuntimeException("Failed to load JDBC driver", e);
		}
		
		try (Connection connection = DriverManager.getConnection(url); ) {
	        connection.setAutoCommit(false);

	        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM location_history WHERE id = ?"); ) {
	            preparedStatement.setInt(1, idx);
	            preparedStatement.executeUpdate();
	        }

	        connection.commit();

	        System.out.println(idx + " 번째 레코드 제거 완료");
	    } catch (SQLException e) {
	        throw new RuntimeException("Failed to delete records from table", e);
	    }

	    return;
	}
	
	public void insertBookmark(String bookmarkName, int order) {

		SimpleDateFormat formatDate = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss.0");
		
		Calendar time = Calendar.getInstance();
		String format_time = formatDate.format(time.getTime());
		String dbUrl ="jdbc:sqlite:/C:\\sqlite-tools-win32-x86-3420000\\dongHyeon.db"; 
		
		
					//드라이버 로드 
					try { 
						Class.forName("org.sqlite.JDBC"); 
						} catch (ClassNotFoundException e) { 
						e.printStackTrace(); 
					}
				  
				  Connection connection = null; 
				  PreparedStatement preparedStatement = null;
				  
				  try { 
					  connection = DriverManager.getConnection(dbUrl);	
					  connection.setAutoCommit(false);


						  String sql =
						  " insert into bookmarkGroupList (bookmarkName, orderValue, registerDate, editDate) "
						  + " values ( ?, ?, ?, ?) ";
						  
						  preparedStatement = connection.prepareStatement(sql);
						  
						  preparedStatement.setString(1, bookmarkName);
						  preparedStatement.setInt(2, order);
						  preparedStatement.setString(3, format_time);
						  preparedStatement.setString(4, "");


						  preparedStatement.addBatch();
				          preparedStatement.executeBatch();
				          preparedStatement.clearBatch();		    
				
						
				        connection.commit();
				        
				        System.out.println("insertBookmarkList 완료");
						}
						catch(Exception e) {
								e.printStackTrace();
								try {
									connection.rollback();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							
						  } finally { //열었던 객체 닫기 rs, statement, connection
					  
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

	public void editBookmark(String bookmarkName, int order, int idx) {

		SimpleDateFormat formatDate = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss.0");
		
		Calendar time = Calendar.getInstance();
		String format_time = formatDate.format(time.getTime());
		String dbUrl ="jdbc:sqlite:/C:\\sqlite-tools-win32-x86-3420000\\dongHyeon.db"; 
		
		
					//드라이버 로드 
					try { 
						Class.forName("org.sqlite.JDBC"); 
						} catch (ClassNotFoundException e) { 
						e.printStackTrace(); 
					}
				  
				  Connection connection = null; 
				  PreparedStatement preparedStatement = null;
				  
				  try { 
					  connection = DriverManager.getConnection(dbUrl);	
					  connection.setAutoCommit(false);


					  String sql = "UPDATE bookmarkGroupList SET bookmarkName = ?, orderValue = ?, editDate = ? WHERE id = ?";

						  
						  preparedStatement = connection.prepareStatement(sql);
						  
						  preparedStatement.setString(1, bookmarkName);
						  preparedStatement.setInt(2, order);
						  preparedStatement.setString(3, format_time);
						  preparedStatement.setInt(4, idx);


						  preparedStatement.addBatch();
				          preparedStatement.executeBatch();
				          preparedStatement.clearBatch();		    
				
						
				        connection.commit();
				        
				        System.out.println("editBookmarkList 완료");
						}
						catch(Exception e) {
								e.printStackTrace();
								try {
									connection.rollback();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							
						  } finally { //열었던 객체 닫기 rs, statement, connection
					  
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
	
	public void deleteBookmarkGroup(int idx) {	// API를 받아올때 마다 기존 테이블 내에 존재하는 레코드를 제거하기 위해 사용

		
		// 테이블 이름 유효성 검사
		
		String url ="jdbc:sqlite:C:\\sqlite-tools-win32-x86-3420000\\dongHyeon.db"; 
		// 1. jdbc 드라이버 연동하기
		// 2. 커넥션 객체 생성
		// 3. 스테이트먼트 객체 생성
		// 4. 쿼리 실행
		// 5. 결과 수행
		// 6. 객체 연결 해제(close)

		// 드라이버 로드
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
	        throw new RuntimeException("Failed to load JDBC driver", e);
		}
		
		try (Connection connection = DriverManager.getConnection(url); ) {
	        connection.setAutoCommit(false);

	        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM bookmarkGroupList WHERE id = ?"); ) {
	            preparedStatement.setInt(1, idx);
	            preparedStatement.executeUpdate();
	        }

	        connection.commit();

	        System.out.println(idx + " 번째 레코드 제거 완료");
	    } catch (SQLException e) {
	        throw new RuntimeException("Failed to delete records from table", e);
	    }

	    return;
	}

	public List<Member> bookmarkGroupList() {
		List<Member> bookmarkGroupMember = new ArrayList<>();

		// sqlite db와 연동
		String url ="jdbc:sqlite:C:\\sqlite-tools-win32-x86-3420000\\dongHyeon.db"; 

		// 1. jdbc 드라이버 연동하기
		// 2. 커넥션 객체 생성
		// 3. 스테이트먼트 객체 생성
		// 4. 쿼리 실행
		// 5. 결과 수행
		// 6. 객체 연결 해제(close)

		// 드라이버 로드
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(url);

			String sql = " select *" + " from bookmarkGroupList ";

			preparedStatement = connection.prepareStatement(sql);
			// sql 구문 ?부분에 넣는 명령어
			// preparedStatement.setString(1, memberTypeValue);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {

				int id =  rs.getInt("id");
				String bookmarkName = rs.getString("bookmarkName");
				int orderValue = rs.getInt("orderValue");
				String registerDate = rs.getString("registerDate");
				String editDate = rs.getString("editDate");
				

				Member bookmarkMember = new Member();
				bookmarkMember.setId(id);
				bookmarkMember.setBookmarkName(bookmarkName);
				bookmarkMember.setOrderValue(orderValue);
				bookmarkMember.setRegisterDate(registerDate);
				bookmarkMember.setEditDate(editDate);

				
				bookmarkGroupMember.add(bookmarkMember);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			// 열었던 객체 닫기 rs, statement, connection

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return bookmarkGroupMember;
	
	}

	public void insertDetailBookmark(String bookmarkName, String wifiName, String registerDate) {

		String dbUrl ="jdbc:sqlite:/C:\\sqlite-tools-win32-x86-3420000\\dongHyeon.db"; 
		
		
					//드라이버 로드 
					try { 
						Class.forName("org.sqlite.JDBC"); 
						} catch (ClassNotFoundException e) { 
						e.printStackTrace(); 
					}
				  
				  Connection connection = null; 
				  PreparedStatement preparedStatement = null;
				  
				  try { 
					  connection = DriverManager.getConnection(dbUrl);	
					  connection.setAutoCommit(false);


						  String sql =
						  " insert into bookmarkList (bookmarkName, wifiName, registerDate) "
						  + " values (?, ?, ?) ";
						  
						  preparedStatement = connection.prepareStatement(sql);
						  
						  preparedStatement.setString(1, bookmarkName);
						  preparedStatement.setString(2, wifiName);
						  preparedStatement.setString(3, registerDate);


						  preparedStatement.addBatch();
				          preparedStatement.executeBatch();
				          preparedStatement.clearBatch();		    
				
						
				        connection.commit();
				        
				        System.out.println("북마크 추가 완료");
						}
						catch(Exception e) {
								e.printStackTrace();
								try {
									connection.rollback();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							
						  } finally { //열었던 객체 닫기 rs, statement, connection
					  
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

	public List<Member> bookmarkList() {
		List<Member> bookmarkMember = new ArrayList<>();

		// sqlite db와 연동
		String url ="jdbc:sqlite:C:\\sqlite-tools-win32-x86-3420000\\dongHyeon.db"; 

		// 1. jdbc 드라이버 연동하기
		// 2. 커넥션 객체 생성
		// 3. 스테이트먼트 객체 생성
		// 4. 쿼리 실행
		// 5. 결과 수행
		// 6. 객체 연결 해제(close)

		// 드라이버 로드
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(url);

			String sql = " select *" + " from bookmarkList ";

			preparedStatement = connection.prepareStatement(sql);
			// sql 구문 ?부분에 넣는 명령어
			// preparedStatement.setString(1, memberTypeValue);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {

				int id =  rs.getInt("id");
				String bookmarkName = rs.getString("bookmarkName");
				String wifiName = rs.getString("wifiName");
				String registerDate = rs.getString("registerDate");
				

				Member bookmarklist = new Member();
				bookmarklist.setId(id);
				bookmarklist.setBookmarkName(bookmarkName);
				bookmarklist.setWifiName(wifiName);
				bookmarklist.setRegisterDate(registerDate);

				
				bookmarkMember.add(bookmarklist);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			// 열었던 객체 닫기 rs, statement, connection

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return bookmarkMember;
	
	}
	
	public void deleteBookmark(int id) {	// API를 받아올때 마다 기존 테이블 내에 존재하는 레코드를 제거하기 위해 사용
	
			
			// 테이블 이름 유효성 검사
			
			String url ="jdbc:sqlite:C:\\sqlite-tools-win32-x86-3420000\\dongHyeon.db"; 
			// 1. jdbc 드라이버 연동하기
			// 2. 커넥션 객체 생성
			// 3. 스테이트먼트 객체 생성
			// 4. 쿼리 실행
			// 5. 결과 수행
			// 6. 객체 연결 해제(close)
	
			// 드라이버 로드
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
		        throw new RuntimeException("Failed to load JDBC driver", e);
			}
			
			try (Connection connection = DriverManager.getConnection(url); ) {
		        connection.setAutoCommit(false);
	
		        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM bookmarkList WHERE id = ?"); ) {
		            preparedStatement.setInt(1, id);
		            preparedStatement.executeUpdate();
		        }
	
		        connection.commit();
	
		    } catch (SQLException e) {
		        throw new RuntimeException("Failed to delete records from table", e);
		    }
	
		    return;
		}
}
