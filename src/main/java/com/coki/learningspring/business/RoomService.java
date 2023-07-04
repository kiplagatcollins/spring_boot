package com.coki.learningspring.business;

import com.coki.learningspring.data.Room;
import com.coki.learningspring.data.RoomRepository;
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
