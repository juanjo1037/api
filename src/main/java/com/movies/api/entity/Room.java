package com.movies.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="room",  schema = "cinema_manage")
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room", nullable = false)
    private long id;
    private int capacity;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Movie movie;

  public Room(int capacity){

      this.capacity=capacity;
  }
    public Room(long id, int capacity){

        this.capacity=capacity;
    }
    public Room() {

    }
}
