package edu.kpi.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "history_entries")
public class HistoryEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Connection connection;

    private LocalDateTime timestamp;
    private String command;
    private String result;
}
