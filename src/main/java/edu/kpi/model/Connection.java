package edu.kpi.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "connections")
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
    private String command;

    @OneToMany(mappedBy = "connection")
    private List<HistoryEntry> history;
}
