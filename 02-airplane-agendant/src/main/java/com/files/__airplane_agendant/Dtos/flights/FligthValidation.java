package com.files.__airplane_agendant.Dtos.flights;

import com.files.__airplane_agendant.enums.FligthEnum;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FligthValidation {
    
    @NotNull(message = "the passport can't null!")
    private String passport;

    @NotNull(message = "the identifacion cant null!")
    @Size(min = 10)
    private Integer identification;

    @NotNull(message = "the type of flight not null!")
    private FligthEnum fligthEnum;

    @Size(max = 40, message = "the weight must not exceed 40!")
    private Float luggageweight;
}
