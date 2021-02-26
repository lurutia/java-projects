package com.gosuljo.mock;

import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepository {
	public void insert(Room room) {
		System.out.println(room.getName() + "을 예약하였습니다.");
	}
}
