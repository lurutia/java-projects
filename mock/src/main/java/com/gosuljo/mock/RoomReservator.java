package com.gosuljo.mock;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoomReservator {
	private final ReservationRepository reservationRepository;
	
	public void reservation(Room room) {
		reservationRepository.insert(room);
	}
}
