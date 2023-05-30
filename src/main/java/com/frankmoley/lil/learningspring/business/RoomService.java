package com.frankmoley.lil.learningspring.business;

import com.frankmoley.lil.learningspring.data.Room;
import com.frankmoley.lil.learningspring.data.RoomRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRooms() {
        return roomRepository.findAll(Sort.by(Sort.Direction.ASC, "roomNumber"));
    }

}
