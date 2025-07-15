package edu.fbansept.m2i2.controller;

import edu.fbansept.m2i2.dao.ProduitDao;
import edu.fbansept.m2i2.model.Produit;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produit")
public class ProduitController {

    @Autowired
    protected ProduitDao produitDao;

    @GetMapping("/liste")
    public List<Produit> getAll() {
        return produitDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> get(@PathVariable int id) {

        Optional<Produit> produitOptional = produitDao.findById(id);

        if(produitOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            //return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(produitOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Produit> add(
            @RequestBody @Valid Produit produitEnvoye) {

        produitDao.save(produitEnvoye);

        return new ResponseEntity<>(produitEnvoye, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {

        Optional<Produit> produitOptional = produitDao.findById(id);

        if(produitOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        produitDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable int id,
            @RequestBody @Valid Produit produitEnvoye) {

        produitEnvoye.setId(id);

        Optional<Produit> produitOptional = produitDao.findById(id);

        if(produitOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        produitDao.save(produitEnvoye);

        return new ResponseEntity<>(produitEnvoye, HttpStatus.OK);

    }

}
