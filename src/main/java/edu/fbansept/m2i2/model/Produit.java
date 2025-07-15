package edu.fbansept.m2i2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @NotBlank
    @Column(unique=true , nullable=false)
    protected String nom;

    @DecimalMin(value = "0.01")
    @Column(columnDefinition = "DECIMAL(6,2)")
    protected float prix;

    @ManyToOne
    protected Utilisateur vendeur;

}

