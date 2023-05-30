package com.frankmoley.lil.learningspring.web;

import com.frankmoley.lil.learningspring.business.GuestService;
import com.frankmoley.lil.learningspring.data.Guest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/guests")
public class GuestController {
    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;

    }

    @GetMapping
    public String getGuests(Model model) {
        List<Guest> guests = guestService.getGuests();
        model.addAttribute("guests", guests);
        return "guests";
    }
}
