package com.files.__airplane_agendant.models;

import java.util.Date;

import com.files.__airplane_agendant.enums.ReservationEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Data
@Entity
@Table(name = "reservations")
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String originCountry;

    @Column(nullable = false)
    private String destinyOrigin;

    @Column(nullable = false)
    private Date travelDate;

    @Column(nullable = false)
    private ReservationEnum travelPreferencies;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Version
    private Long version;
}
