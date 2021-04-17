package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.MemberMapper;
import com.example.demo.model.Member;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class MemberTest {
	
	@Autowired
	MemberMapper mapper;
	
	@Test
	public void findMemberById() {
		Member member = mapper.selectMemberById(1L);
		log.info("{}", member);
		
		assertNotNull(member);
	}
	
	@Test
	public void findAllMembers() {
		List<Member> list = mapper.selectAllMembers();
		log.info("{}", list);
		
		assertNotNull(list);
	}
	
	@Test
	public void insertMember() {
		Member member = Member.builder()
							.name("Fride")
							.password("1234")
							.email("fride@email.com")
							.build();
		mapper.insertMember(member);
	}

}
