package com.frankmoley.lil.learningspring.business;

import com.frankmoley.lil.learningspring.data.Guest;
import com.frankmoley.lil.learningspring.data.GuestRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> getGuests() {
        return guestRepository.findAll(Sort.by(Sort.Direction.ASC, "lastName"));
    }

    public void addGuest(Guest guest) {
        Guest guest1 = guestRepository.save(guest);
    }
}
