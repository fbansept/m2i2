package edu.fbansept.m2i2.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fbansept.m2i2.dao.ProduitDao;
import edu.fbansept.m2i2.model.Produit;
import edu.fbansept.m2i2.security.IsVendeur;
import edu.fbansept.m2i2.view.ProduitView;
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
    @JsonView(ProduitView.class)
    public List<Produit> getAll() {
        return produitDao.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(ProduitView.class)
    public ResponseEntity<Produit> get(@PathVariable int id) {

        Optional<Produit> produitOptional = produitDao.findById(id);

        if(produitOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            //return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(produitOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    @JsonView(ProduitView.class)
    @IsVendeur
    public ResponseEntity<Produit> add(
            @RequestBody @Valid Produit produitEnvoye) {

        produitDao.save(produitEnvoye);

        return new ResponseEntity<>(produitEnvoye, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    @IsVendeur
    public ResponseEntity<?> delete(@PathVariable int id) {

        Optional<Produit> produitOptional = produitDao.findById(id);

        if(produitOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        produitDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/{id}")
    @JsonView(ProduitView.class)
    @IsVendeur
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
