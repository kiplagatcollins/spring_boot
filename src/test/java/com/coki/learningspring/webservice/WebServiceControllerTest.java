package com.coki.learningspring.webservice;

import com.coki.learningspring.business.GuestService;
import com.coki.learningspring.business.ReservationService;
import com.coki.learningspring.business.RoomReservation;
import com.coki.learningspring.business.RoomService;
import com.coki.learningspring.data.Guest;
import com.coki.learningspring.data.Room;
import com.coki.learningspring.util.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WebServiceControllerTest {
    @Mock
    private DateUtils dateUtils;
    @Mock
    private ReservationService reservationService;
    @Mock
    private GuestService guestService;
    @Mock
    private RoomService roomService;

    private WebServiceController webServiceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webServiceController = new WebServiceController(dateUtils, reservationService, guestService, roomService);
    }

    @Test
    void getReservations_ShouldReturnListOfRoomReservations() {
        Date date = new Date();
        List<RoomReservation> expectedReservations = Arrays.asList(new RoomReservation(), new RoomReservation());

        when(dateUtils.createDateFromDateString(anyString())).thenReturn(date);
        when(reservationService.getRoomReservationsForDate(date)).thenReturn(expectedReservations);

        List<RoomReservation> actualReservations = webServiceController.getReservations("2023-07-04");

        assertEquals(expectedReservations, actualReservations);
        verify(dateUtils).createDateFromDateString("2023-07-04");
        verify(reservationService).getRoomReservationsForDate(date);
    }

    @Test
    void getGuests_ShouldReturnListOfGuests() {
        List<Guest> expectedGuests = Arrays.asList(new Guest(), new Guest());

        when(guestService.getGuests()).thenReturn(expectedGuests);

        List<Guest> actualGuests = webServiceController.getGuests();

        assertEquals(expectedGuests, actualGuests);
        verify(guestService).getGuests();
    }

    @Test
    void addGuest_ShouldReturnCreatedGuest() {
        Guest guest = new Guest();

        Guest createdGuest = webServiceController.addGuest(guest);

        assertEquals(guest, createdGuest);
        verify(guestService).addGuest(guest);
    }

    @Test
    void getRooms_ShouldReturnListOfRooms() {
        List<Room> expectedRooms = Arrays.asList(new Room(), new Room());

        when(roomService.getRooms()).thenReturn(expectedRooms);

        List<Room> actualRooms = webServiceController.getRooms();

        assertEquals(expectedRooms, actualRooms);
        verify(roomService).getRooms();
    }
}
