package miniproject.phonebook;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;





public class PhoneBookApp {

	public static void main(String[] args) {
		int N = 0;
		System.out.println("****************************************");
		System.out.println("*           전화번호 관리 프로그램          *");
		System.out.println("****************************************");
		
		while(true) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("1.리스트 2.등록 3.삭제. 4.검색 5.종료");
			System.out.println("----------------------------------------");
			System.out.print(">메뉴번호: ");
			N = scanner.nextInt();
			if(N == 5) {
				System.out.println("****************************************");
				System.out.println("*          감사합니다          *");
				System.out.println("****************************************");
				break;
			}
			else if(N == 1) {
				System.out.println("<1.리스트>");
				selectAll();
				System.out.println();
				
			}
			else if (N == 2) {
				System.out.println("<2.등록>");
				insertPhoneBook();
				System.out.println();
			}
			else if (N == 3) {
				System.out.println("<3.삭제>");
				deletePhoneBook();
			}
			else if(N == 4) {
				System.out.println("<4.검색>");
				searchPhoneBook();
			}
			else {
				System.out.println("[다시 입력해 주세요]");
				continue;
			}
				
		}

	}
	
	private static void searchPhoneBook() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.printf("이름: ");
		String keword = scanner.next();
		
		PhoneBookDAO dao = new PhoneBookDAOImpl();
		List<PhoneBookVO> list = dao.search(keword);
		
		Iterator<PhoneBookVO> it = list.iterator();
		
		while(it.hasNext()) {
			PhoneBookVO vo = it.next();
			System.out.printf("%d %s %s  %s%n", vo.getId(),vo.getName(),vo.getHp(),vo.getTel());
		}
	}
	private static void deletePhoneBook() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("번호: ");
		int id = scanner.nextInt();
		
		PhoneBookDAO dao = new PhoneBookDAOImpl();
		boolean success = dao.delete(Long.valueOf(id));
		
		System.out.println((success ? "[삭제되었습니다]": "[삭제 실패!]"));
		
		
		
	}
	private static void insertPhoneBook() {
		// Scanner에서 이름 입력 -> INSERT
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		Scanner sc3 = new Scanner(System.in);
		System.out.print("이름: ");
		String name = sc1.next();
		System.out.print("휴대전화: ");
		String hp = sc2.next();
		System.out.print("집전화: ");
		String tel = sc3.next();
		
		PhoneBookVO vo = new PhoneBookVO(null,name, hp, tel);
		PhoneBookDAO dao = new PhoneBookDAOImpl();
		
		boolean success = dao.insert(vo);
		
		System.out.println((success ? "[등록되었습니다]": "등록이 실패했습니다"));
		
		selectAll();
		
		
	}
	private static void selectAll() {
		PhoneBookDAO dao = new PhoneBookDAOImpl();
		List<PhoneBookVO> list = dao.getList();
		
		// Iterator
		Iterator<PhoneBookVO> it = list.iterator();
		
		
		while(it.hasNext()) {
			PhoneBookVO item = it.next(); // iterator에서 요소 호출
			System.out.printf("%d. %s %s  %s",
					item.getId(),item.getName(),item.getHp(),item.getTel());
			System.out.println();
		}
		
	}
	
}
