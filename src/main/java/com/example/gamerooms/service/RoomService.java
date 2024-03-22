package com.example.gamerooms.service;

import com.example.gamerooms.dao.RoomRepository;
import com.example.gamerooms.business.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    //@Autowired
    public RoomRepository roomRepository;


    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    public void createRooms(int numberOfRooms) {
        for (int i = 1; i <= numberOfRooms; i++) {
            Room room = new Room();
            room.setStatus(true);
            roomRepository.save(room);
        }
    }
    public boolean roomsExist() {
        return roomRepository.count() > 0;
    }

    public void updateRoomStatus(Long roomId, boolean newStatus) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room != null) {
            room.setStatus(newStatus);
            save(room); // Utiliser la méthode save pour sauvegarder les changements
        } else {
            System.out.println("La salle avec l'ID " + roomId + " n'existe pas.");
        }
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public String checkRoomAvailability(Long roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room != null) {
            if (room.isStatus()) {
                return "La salle est disponible.";
            } else {
                return "La salle n'est pas disponible.";
            }
        } else {
            return "La salle n'existe pas.";
        }
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }
}
