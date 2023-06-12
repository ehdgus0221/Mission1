package db;

public class DbTestMain {
	public static void main(String[] args) {
	MemberService memberService = new MemberService();
	// 조회
	memberService.list();
	
	// 추가
	//memberService.register(null);
	}
}
