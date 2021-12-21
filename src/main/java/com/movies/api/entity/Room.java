package com.movies.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="room",  schema = "cinema_manage")
@Getter
@Setter
public class Room implements Serializable {
    private static final long serialVersionUID = 6489021462409984216L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "capacity")
    private int capacity;

    @JsonManagedReference
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<Chair> chairs;

    @JsonBackReference
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Presentation> presentations;


}
