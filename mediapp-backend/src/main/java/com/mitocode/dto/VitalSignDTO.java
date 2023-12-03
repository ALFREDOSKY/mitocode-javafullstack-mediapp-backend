package com.mitocode.dto;

import com.mitocode.model.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VitalSignDTO {

    @EqualsAndHashCode.Include
    private Integer idVitalSign;

    @NotNull
    private PatientDTO patient;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20, message = "{temperature.size}")
    private String temperature;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50, message = "{pulse.size}")
    private String pulse;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50, message = "{respiratoryRate.size}")
    private String respiratoryRate; //

    @NotNull
    private LocalDateTime vitalSignDate;
}
