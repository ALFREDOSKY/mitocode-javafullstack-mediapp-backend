package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VitalSign {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVitalSign;

    @ManyToOne
    @JoinColumn(name = "id_patient", nullable = false, foreignKey = @ForeignKey(name = "FK_VITSIG_PATIENT"))
    private Patient patient;

    @Column(length = 20, nullable = false)
    private String temperature;

    @Column(length = 50, nullable = false)
    private String pulse;

    @Column(length = 50, nullable = false)
    private String respiratoryRate;

    @Column(nullable = false)
    private LocalDateTime vitalSignDate;

}
