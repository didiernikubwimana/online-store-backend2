package edu.miu.cs545.project.onlinestore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shippings")
public class Shipping implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;

    @NotNull
    private String receiverFirstName;

    @NotNull
    private String receiverLastName;

    private String receiverPhone;
    private LocalDate deliveredDate;
    private String receiverStreet;
    private String receiverCity;
    private String receiverState;
    private String receiverCountry;
    private String receiverZipcode;


    public Shipping(String firstName, String lastName, String phoneNumber, LocalDate now, String street, String fairfield, String iowa, String usa, String i) {
    this.receiverFirstName = firstName;
    this.receiverLastName = lastName;
    this.receiverPhone = phoneNumber;
    this.deliveredDate = now;
    this.receiverStreet = street;
    this.receiverCity = fairfield;
    this.receiverState = iowa;
    this.receiverCountry = usa;
    this.receiverZipcode = i;
    }
}