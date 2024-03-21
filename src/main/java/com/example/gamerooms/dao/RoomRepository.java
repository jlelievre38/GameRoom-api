package com.example.gamerooms.dao;

import com.example.gamerooms.business.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    // Vous pouvez ajouter des méthodes personnalisées de requête ici si nécessaire
}
