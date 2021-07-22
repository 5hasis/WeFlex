package com.kh.finalteam1.repository;

import java.util.List;

import com.kh.finalteam1.entity.ClientDto;
import com.kh.finalteam1.entity.ClientGradeDto;
import com.kh.finalteam1.entity.ClientGradeListDto;
import com.kh.finalteam1.vo.ClientUpdatePasswordVO;

public interface ClientDao {
	ClientDto findClient(int clientNo);
	ClientGradeDto getGrade(int gradeNo);
	boolean changePhone(ClientDto clientDto);
	boolean changeEmail(ClientDto clientDto);
	boolean changePassword(ClientUpdatePasswordVO clientVO);
	
	List<ClientGradeListDto> list();//회원 전체 목록 출력(관리자 게시판 사용)

}
