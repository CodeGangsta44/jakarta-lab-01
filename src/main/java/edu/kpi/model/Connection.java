package edu.kpi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "connections")
@NamedQuery(
        name = "findByUser",
        query = "SELECT c FROM Connection c WHERE c.owner = :user")
@Data
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User owner;

    private String username;
    private String host;
    private int port;
    private String password;
    private String connectionName;

    @OneToMany(mappedBy = "connection")
    private List<HistoryEntry> history;
}
