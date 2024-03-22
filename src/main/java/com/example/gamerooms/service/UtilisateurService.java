package com.example.gamerooms.service;

import com.example.gamerooms.business.Utilisateur;
import com.example.gamerooms.dao.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public UtilisateurService(){

    }

    public Utilisateur ajouterUtilisateur(String nom, String prenom, String email) {
        Utilisateur utilisateur = new Utilisateur(nom, prenom, email);
        return utilisateurRepository.save(utilisateur);
    }
}
