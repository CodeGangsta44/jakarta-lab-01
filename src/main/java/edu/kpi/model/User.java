package edu.kpi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NamedQuery(
        name = "findByUsername",
        query = "SELECT u FROM User u WHERE u.username LIKE :username")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true)
    private String username;

    private String password;

    @OneToMany(mappedBy = "owner")
    private List<Connection> connections;
}
