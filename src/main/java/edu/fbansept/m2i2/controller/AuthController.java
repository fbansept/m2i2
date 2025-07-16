package edu.fbansept.m2i2.controller;

import edu.fbansept.m2i2.dao.UtilisateurDao;
import edu.fbansept.m2i2.model.Role;
import edu.fbansept.m2i2.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected UtilisateurDao utilisateurDao;

    @Autowired
    protected AuthenticationProvider authenticationProvider;

    @PostMapping("/inscription")
    public ResponseEntity<Utilisateur> inscription(@RequestBody Utilisateur utilisateur) {

        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));

        //Par défaut on affecte le role CLIENT à l'utilisateur
        Role roleClient = new Role();
        roleClient.setId(1);
        utilisateur.setRole(roleClient);

        utilisateurDao.save(utilisateur);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/connexion")
    public ResponseEntity<String> connexion(@RequestBody Utilisateur utilisateur) {

        try {
            authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            utilisateur.getEmail(), utilisateur.getPassword()
                    ));

            return ResponseEntity.ok("le futur JWT");

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}














