package edu.fbansept.m2i2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Utilisateur {

    public interface add {
    }

    public interface update {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(nullable = false, unique = true)
    @NotBlank(groups = {add.class, update.class})
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            groups = {add.class, update.class},
            message = "L'email est mal formé")
    protected String email;

    @Column(nullable = false)
    @NotBlank(groups = {add.class})
    protected String password;

    @ManyToOne(optional = false)
    protected Role role;

}
