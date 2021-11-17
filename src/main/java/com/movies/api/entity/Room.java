package com.movies.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "capacity")
    private int capacity;

    @JsonBackReference
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<Chair> chairs;

   // @JsonManagedReference
    @JsonIgnore
    @ManyToMany(mappedBy = "rooms", fetch = FetchType.LAZY)
    private List<Movie> movies;

  public Room(int capacity){

      this.capacity=capacity;
  }

    public Room(long id, int capacity){

        this.capacity=capacity;
    }
    public Room() {

    }
}
