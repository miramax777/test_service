package com.test.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "session")
public class Session implements Serializable {

    @Serial
    private static final long serialVersionUID = 234869340345L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "sessionType")
    private SessionType sessionType;

    @Basic
    @Column(name = "total_duration")
    private long totalDuration;

    @Basic
    @Column(name = "downloaded_times")
    private int downloadedTimes;

    @Basic
    @Column(name = "watched_times")
    private int watchedTimes;

    @Column(name = "skin_issues")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = SkinIssues.class, fetch = FetchType.EAGER)
    private Set<SkinIssues> skinIssues = new HashSet<>();

    @OneToMany(mappedBy = "session_product",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            targetEntity = SessionProduct.class)
    private Set<SessionProduct> sessionProducts = new HashSet<>();

    @Column(name = "active")
    private boolean active;

    @Column(name = "recently_created")
    private boolean recentlyCreated;
}
