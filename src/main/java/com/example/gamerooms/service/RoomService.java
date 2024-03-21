package com.example.gamerooms.service;

import com.example.gamerooms.dao.RoomRepository;
import com.example.gamerooms.business.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
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
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            room.setStatus(newStatus);
            roomRepository.save(room);
            System.out.println("Statut de la salle avec l'ID " + roomId + " mis à jour avec succès.");
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
}
