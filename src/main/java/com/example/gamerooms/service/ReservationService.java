package com.example.gamerooms.service;

import com.example.gamerooms.business.Utilisateur;
import com.example.gamerooms.business.Room;
import com.example.gamerooms.dao.ReservationRepository;
import com.example.gamerooms.business.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationService(){

    }

    public Reservation createReservation(Utilisateur utilisateur, Room room, LocalDateTime startTime, LocalDateTime endTime) {
        // Ajouter la logique de validation ici si nécessaire
        Reservation reservation = new Reservation();
        reservation.setUtilisateur(utilisateur);
        reservation.setRoom(room);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}
