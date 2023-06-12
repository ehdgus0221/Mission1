# Mission1


[api Package]
dbSaveTest.java : 와이파이의 총 개수를 구하기 위해 사용
apiTest.java : api 정보를 제대로 가져오는지 테스트 하기 위한 용도

[db Package]
Member.java : 데이터 값을 저장하기 위해 사용
MemberService.java : DB의 CRUD 및 Member 값 저장을 위해 사용
distanceCalculate.java : 두 좌표간 거리를 구하기 위해 사용
apiDbTest.java : api 정보를 받아온 뒤, DB에 저장이 되나 테스트 해본 용도 
dbTestMain.java : 테스트 용도

[JSP]

main.jsp : 메인화면
bookmarkList.jsp : 북마크 목록
bookmarkDelete.jsp : 북마크 삭제 버튼 눌렀을때 나타나는 페이지
deleteBookmark.jsp : 북마크 삭제하기 위한 파일
bookmarkGroup.jsp : 북마크 그룹 목록 
bookmarkGroupEdit.jsp : 북마크 그룹 수정
editBookmark.jsp : 북마크 그룹 수정될 때 실행되는 파일
bookmarkGroupAdd : 북마크 그룹 추가
insertBookmark.jsp : 북마크 그룹 이름 추가될 때 실행되는 파일
bookmarkGroupDelete.jsp : 북마크 그룹 이름 삭제 버튼 누를 때 실행되는 파일 
insertDetailBookmark.jsp : 북마크 추가 버튼 클릭 시 북마크 추가 기능 실행 파일
history.jsp : 위치 히스토리 목록
historyListDelete.jsp : 히스토리 목록에서 삭제 버튼 클릭 시 실행되는 파일
detail.jsp : 상세보기
wifiList.jsp : 20개의 와이파이 정보 가져오기 위한 파일
loadWifi : api로 가져온 전체 와이파이의 개수를 가져오기 위한 파일
distance.jsp : 내 위치와 wifi의 거리를 구하기 위해 사용하는 파일
coordinate.jsp : 내 위치 값을 넘겨서 위치 히스토리 목록 페이지에서 사용하기 위한 파일
common.jsp : 공통적으로 화면에 보이는 부분