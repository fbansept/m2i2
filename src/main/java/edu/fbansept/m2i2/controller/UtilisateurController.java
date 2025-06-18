package edu.fbansept.m2i2.controller;

import edu.fbansept.m2i2.dao.UtilisateurDao;
import edu.fbansept.m2i2.model.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {

    @Autowired
    protected UtilisateurDao utilisateurDao;

    @GetMapping("/liste")
    public List<Utilisateur> getAll() {
        return utilisateurDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> get(@PathVariable int id) {

        Optional<Utilisateur> utilisateurOptional = utilisateurDao.findById(id);

        if(utilisateurOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            //return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(utilisateurOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Utilisateur> add(
            @RequestBody @Validated(Utilisateur.add.class) Utilisateur utilisateurEnvoye) {

        utilisateurDao.save(utilisateurEnvoye);

        return new ResponseEntity<>(utilisateurEnvoye, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {

        Optional<Utilisateur> utilisateurOptional = utilisateurDao.findById(id);

        if(utilisateurOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        utilisateurDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable int id,
            @RequestBody @Validated(Utilisateur.update.class) Utilisateur utilisateurEnvoye) {

        utilisateurEnvoye.setId(id);

        Optional<Utilisateur> utilisateurOptional = utilisateurDao.findById(id);

        if(utilisateurOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //pour forcer le fait de ne pas mettre à jour le mot de passe
        //on affecte à l'utilisateur a sauvegarder l'ancien mot de passe
        utilisateurEnvoye.setPassword(utilisateurOptional.get().getPassword());

        utilisateurDao.save(utilisateurEnvoye);

        return new ResponseEntity<>(utilisateurEnvoye, HttpStatus.OK);

    }

}
