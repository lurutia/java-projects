package com.gosuljo.exception.member;

import java.sql.SQLException;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberDataLoader implements ApplicationRunner {

	private final MemberRepository memberRepository;
	
	@Transactional
	public void run(ApplicationArguments args) throws SQLException {
		Member member = Member.builder()
				.username("katy")
				.password("12345678")
				.build();
		
		Member savedMember = memberRepository.save(member);
		Member findMember = memberRepository.findById(savedMember.id).get();
		
//		throw new SQLException("SQLException은 checkedException으로 롤백되지 않습니다.");
//		throw new IndexOutOfBoundsException("IndexOutOfBoundsException은 uncheckedException으로 롤백됩니다.");
//		throw new DataIntegrityViolationException("DataIntegrityViolation은 uncheckedException으로 롤백됩니다.");

//		try {
////			throw new UncheckedIOException(new IOException());
////			throw new DataIntegrityViolationException("DataIntegrityViolation은 uncheckedException으로 롤백됩니다.");
//
//		} catch (Exception e) {
//			
//		}
		
	}
}
