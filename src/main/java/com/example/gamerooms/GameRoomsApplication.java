package com.example.gamerooms;

import com.example.gamerooms.business.Utilisateur;
import com.example.gamerooms.service.RoomService;
import com.example.gamerooms.service.UtilisateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GameRoomsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameRoomsApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UtilisateurService utilisateurService, RoomService roomService) {
        return (args) -> {
            Utilisateur utilisateur = utilisateurService.ajouterUtilisateur("Steven", "Steven", "Steven.doe@example.com");
            System.out.println("Utilisateur ajouté avec succès : " + utilisateur.getNom() + " " + utilisateur.getPrenom());
            if (!roomService.roomsExist()) {
                int numberOfRooms = 8;
                roomService.createRooms(numberOfRooms);
                System.out.println(numberOfRooms + " salles ont été créées avec succès.");
            } else {
                System.out.println("Les salles existent déjà. Aucune nouvelle salle n'a été créée.");
            }
            Long roomIdToUpdate = 4L;
            boolean newStatus = false;
            roomService.updateRoomStatus(roomIdToUpdate, newStatus);
        };
    }
}
