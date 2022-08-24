package com.spring.user;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClient {
	
	public static void main(String[] args) {
		//1. spring 컨테이너 구동
		AbstractApplicationContext container = 
				new GenericXmlApplicationContext("applicationContext.xml");
		
		//2. dao 처리
		UserDAO userDAO = (UserDAO)container.getBean("userDAO");
		UserVO vo = new UserVO();
		vo.setId("test");
		vo.setPasswd("test123");
		boolean result = userDAO.login(vo);
		if(result) {	//result == true
			System.out.println("로그인에 성공했습니다.");
		} else {
			System.out.println("아이디나 비밀번호를 확인해주세요.");
		}
		
		//3. spring 컨테이너 종료
		container.close();
	}
}
