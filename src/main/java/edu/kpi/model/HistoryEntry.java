package edu.kpi.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "history_entries")
@NamedQuery(
        name = "findByConnection",
        query = "SELECT e FROM HistoryEntry e WHERE e.connection = :connection")
@Data
public class HistoryEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Connection connection;

    private LocalDateTime startTimestamp;
    private LocalDateTime finishTimestamp;
    private String command;

    @Column(columnDefinition = "TEXT")
    private String result;
}
