package com.gosuljo.mock;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {
	private final RoomReservator roomReservator;
	
	public void reservation(Room room) {
		roomReservator.reservation(room);
	}
}
