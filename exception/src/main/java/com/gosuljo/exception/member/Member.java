package com.gosuljo.exception.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	@Id @GeneratedValue
	Long id;
	
	@Column(unique = true)
	String username;
	
	String password;
}
