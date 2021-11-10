package com.movies.api.entity;



import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "user",  schema = "cinema_manage")
@NoArgsConstructor
@Getter
@Setter
public class User {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String idCardType;
    @NotNull
    @Column(unique = true)
    private String idCard;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;
    @NotNull
    @ManyToMany
    @JoinTable(name="role_has_user", joinColumns = @JoinColumn(name="user_id"),
    inverseJoinColumns = @JoinColumn(name="role_id_role"))
    private Set<Rol> roles = new HashSet<>();

    @OneToMany( mappedBy = "user", fetch = FetchType.LAZY)
    private List<Reservation> reservationList;

    public User(String idCardType, String idCard, String firstName,
                String lastName, String email, String password
              ) {

        this.idCardType = idCardType;
        this.idCard = idCard;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

}

public void addReservations(Reservation reservation){
    if(reservationList==null) reservationList=new ArrayList<>();
    reservationList.add(reservation);
    reservation.setUser(this);
}
}
