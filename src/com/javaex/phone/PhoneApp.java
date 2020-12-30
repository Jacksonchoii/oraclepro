package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		List<PersonVo> phoneList;
		PhoneDao phoneDao = new PhoneDao();
		
		System.out.println("*****************************************");
		System.out.println("*             전화번호 관리 프로그램                         *");
		System.out.println("*****************************************");
		
		
		while(true) {
			System.out.println("1.리스트\t" + "2.등록\t" + "3.수정\t" + "4.삭제\t" + "5.검색\t" + "6.종료");
			System.out.println("----------------------------------------------------------");
			System.out.print(">메뉴번호: ");
			int num = sc.nextInt();
			
			if(num == 6) {
				
				System.out.println("*****************************************");
				System.out.println("*                  감사합니다                               *");
				System.out.println("*****************************************");
				break;
				
			} else if(num == 1) {
				
				System.out.println("<1.리스트>");
				phoneList = phoneDao.getPersonList();
				for(int i = 0; i < phoneList.size(); i++) {
					PersonVo pvo = phoneList.get(i);
					System.out.println(pvo.getPersonId() +"\t"+ pvo.getName() +"\t"+ pvo.getHp() +"\t"+ pvo.getCompany());
				}
				
				System.out.println("");
				
			} else if(num == 2) {
				
				System.out.println("<2.등록>");
				
				System.out.print("이름 > ");
				String name = sc.nextLine();
				System.out.print("휴대전화 > ");
				String hp = sc.nextLine();
				System.out.print("회사번호 > ");
				String company = sc.nextLine();
				
				PersonVo pvo = new PersonVo(name, hp, company);
				phoneDao.personInsert(pvo);
				
				System.out.println("");
				
			} else if(num == 3) {
				
				System.out.println("<3.수정>");
				
				System.out.print("번호 > ");
				int unum = sc.nextInt();
				sc.nextLine();
				System.out.print("이름 > ");
				String name = sc.nextLine();
				System.out.print("휴대전화 > ");
				String hp = sc.nextLine();
				System.out.print("회사번호 > ");
				String company = sc.nextLine();
				
				PersonVo pvo = new PersonVo(unum, name, hp, company);
				phoneDao.personUpdate(pvo);
				
				System.out.println("");
			
			} else if(num == 4) {
				
				System.out.println("<4.삭제>");
				
				System.out.print(">번호 : ");
				int dnum = sc.nextInt();
				sc.nextLine();
				
				phoneDao.personDelete(dnum);
				
				System.out.println("");
				
			} else if(num == 5) {
				
				System.out.println("<5.검색>");
				
				System.out.print("검색어 > ");
				
				
				
			}
			
			
			
		}
		
		
		
		
		
		
		
		sc.close();
	}

}
