package com.gosuljo.exception.member;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
	private final MemberRepository memberRepository;
	
	@PostConstruct
	@Transactional
	public void init() throws SQLException {
		
		
	}
}
