package com.frankmoley.lil.learningspring.webservice;

import com.frankmoley.lil.learningspring.business.GuestService;
import com.frankmoley.lil.learningspring.business.ReservationService;
import com.frankmoley.lil.learningspring.business.RoomReservation;
import com.frankmoley.lil.learningspring.business.RoomService;
import com.frankmoley.lil.learningspring.data.Guest;
import com.frankmoley.lil.learningspring.data.Room;
import com.frankmoley.lil.learningspring.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebServiceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;
    private final GuestService guestService;
    private final RoomService roomService;

    public WebServiceController(DateUtils dateUtils, ReservationService reservationService, GuestService guestService, RoomService roomService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
        this.guestService = guestService;
        this.roomService = roomService;
    }

    @GetMapping(path = "/reservations")
    public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false) String dateString) {
        Date date = dateUtils.createDateFromDateString(dateString);
        return reservationService.getRoomReservationsForDate(date);
    }

    @GetMapping("/guests")
    public List<Guest> getGuests() {
        return guestService.getGuests();
    }

    @PostMapping("/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public Guest addGuest(@RequestBody Guest guest) {
        guestService.addGuest(guest);
        return guest;
    }

    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return roomService.getRooms();
    }
}
