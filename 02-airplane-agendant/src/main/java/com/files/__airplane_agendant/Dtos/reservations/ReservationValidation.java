package com.files.__airplane_agendant.Dtos.reservations;

import java.util.Date;

import com.files.__airplane_agendant.enums.ReservationEnum;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationValidation {

    @NotNull(message = "El pais de origen no puede ser nulo!")
    private String originCountry;

    @NotNull(message = "El pais de origen no puede ser nulo!")
    private String destinyOrigin;

    @Future(message = "La fecha de viaje debe ser posterior al dia de hoy!")
    private Date travelDate;

    @NotNull(message = "El tipo de preferencia de viaje es obligatorio!")
    private ReservationEnum travelPreferencies;
}
