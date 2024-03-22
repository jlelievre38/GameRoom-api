package com.example.gamerooms.controller;

import com.example.gamerooms.business.Room;
import com.example.gamerooms.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("test")
public class RoomController {
    private  RoomService roomService;
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }
    @PostMapping("/room/{roomId}")
    public ResponseEntity<String> reserveRoom(@PathVariable Long roomId) {
        //RoomService roomService = new RoomService();
        Room room = this.roomService.getRoomById(roomId);
        System.out.println(room);
        if (room != null && room.isStatus()) {
            room.setStatus(false);
            roomService.save(room);
            return ResponseEntity.ok("La salle a été réservée.");
        } else {
            return ResponseEntity.badRequest().body("La salle n'est pas disponible ou n'existe pas.");
        }
    }
    @PostMapping("/room-cancel/{roomId}")
    public ResponseEntity<String> cancelRoom(@PathVariable Long roomId) {
        //RoomService roomService = new RoomService();
        Room room = this.roomService.getRoomById(roomId);
        System.out.println(room);
        if (room != null && !room.isStatus()) {
            room.setStatus(true);
            roomService.save(room);
            return ResponseEntity.ok("La salle a été annulé.");
        } else {
            return ResponseEntity.badRequest().body("La salle n'est pas disponible ou n'existe pas.");
        }
    }
}
