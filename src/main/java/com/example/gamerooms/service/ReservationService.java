package com.example.gamerooms.service;

import com.example.gamerooms.dao.impl.ReservationRepository;
import com.example.gamerooms.business.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(Reservation reservation) {
        // Ajouter la logique de validation ici
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Ajoutez d'autres méthodes de service pour récupérer, mettre à jour et supprimer des réservations
}
