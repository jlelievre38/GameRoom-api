package com.example.gamerooms;

import com.example.gamerooms.business.Reservation;
import com.example.gamerooms.business.Room;
import com.example.gamerooms.business.Utilisateur;
import com.example.gamerooms.service.ReservationService;
import com.example.gamerooms.service.RoomService;
import com.example.gamerooms.service.UtilisateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@SpringBootApplication
public class GameRoomsApplication {
    int year = 2026;
    int month = 3;
    int day = 22;
    int hour = 14;
    int minute = 30;
    int durationInHours = 2;

    LocalDateTime startTime = LocalDateTime.of(year, month, day, hour, minute);
    LocalDateTime endTime = startTime.plusHours(durationInHours);
    public static void main(String[] args) {
        SpringApplication.run(GameRoomsApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UtilisateurService utilisateurService, RoomService roomService, ReservationService reservationService) {
        return (args) -> {
            Utilisateur utilisateur = utilisateurService.ajouterUtilisateur("Steven", "Steven", "Steven.doe@example.com");
            System.out.println("Utilisateur ajouté avec succès : " + utilisateur.getNom() + " " + utilisateur.getPrenom());

            // Vérifiez si les salles existent déjà
            if (!roomService.roomsExist()) {
                int numberOfRooms = 12;
                roomService.createRooms(numberOfRooms);
                System.out.println(numberOfRooms + " salles ont été créées avec succès.");
            } else {
                System.out.println("Les salles existent déjà. Aucune nouvelle salle n'a été créée.");
            }
            Room room = roomService.getRoomById(4L);


            Reservation createdReservation = reservationService.createReservation(utilisateur, room, startTime, endTime);
            if (createdReservation != null) {
                System.out.println("Réservation créée avec succès pour l'utilisateur " + utilisateur.getNom() + " " + utilisateur.getPrenom());
            } else {
                System.out.println("Erreur lors de la création de la réservation.");
            }
        };
    }


}

