package com.kh.finalteam1.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.finalteam1.entity.client.ClientDto;
import com.kh.finalteam1.entity.client.ClientGradeDto;
import com.kh.finalteam1.repository.ClientDao;

@Controller
public class MemberController {
	@Autowired
	private ClientDao clientDao;
	
	@RequestMapping("/yourAccount")
	public String yourAccount(Model model) {
		//세션에서 회원 번호를 받아서 진행
		int clientNo = 2;
		ClientDto clientDto = clientDao.findClient(clientNo);
		ClientGradeDto clientGradeDto = clientDao.getGrade(clientDto.getGradeNo());
		
		SimpleDateFormat format = new SimpleDateFormat("다음 결제일은 yyyy년 MM월 dd일입니다.");
		String nextExpire = format.format(clientDto.getClientExpire());
				
		model.addAttribute("clientDto", clientDto);
		model.addAttribute("clientGradeDto", clientGradeDto);
		model.addAttribute("nextExpire", nextExpire);
		
		return "member/yourAccount";
	}
	
	@RequestMapping("/loginPage")
	public String login() {
		return "member/loginPage";
	}
	
	@RequestMapping("/changePhone")
	public String changePhone(@RequestParam String phoneNumber, Model model) {
		model.addAttribute("phoneNumber", phoneNumber);
		return "member/changePhone";
	}
}
