package com.rentingbook.api.model.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String username;
    @Email
    private String email;
    private String fullName;
    @JsonIgnore
    private String password;
    private boolean enable;
    private AccountRole accountRole;
}
