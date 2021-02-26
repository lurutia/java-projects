package com.gosuljo.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class MockApplicationTests {
	
	private Room room;
	
	@BeforeEach
	public void initial() {
		log.info("MockApplication init");
		
		Room room = new Room();
		room.setName("new room");
		room.setRoomNumber(1L);
		this.room = room;
	}
	
	@DisplayName("use mock reservator test correct")
	@Test
	void mockReservationCorrectTest() {
		RoomReservator mockRoomReservator = mock(RoomReservator.class);
		ReservationService reservationService = new ReservationService(mockRoomReservator);
		
		reservationService.reservation(room);
		
		verify(mockRoomReservator).reservation(room);
	}
	
//	@Test
//	void mockReservationWrongTest() {
//		final Room room = new Room();
//		room.setName("wrong room");
//		room.setRoomNumber(1L);
//		
//		RoomReservator mockRoomReservator = mock(RoomReservator.class);
//		ReservationService reservationService = new ReservationService(mockRoomReservator);
//		
//		reservationService.reservation();
//		
//		
//		verify(mockRoomReservator).reservation(room);
//	}
	
	@Test
	@DisplayName("not user mock reservator test")
	void noMockReservationTest() {
		ReservationRepository reservationRepository = new ReservationRepository();
		RoomReservator roomReservator = new RoomReservator(reservationRepository);
		ReservationService reservationService = new ReservationService(roomReservator);
		
		reservationService.reservation(room);
		
		assertTrue(true);
	}
}
