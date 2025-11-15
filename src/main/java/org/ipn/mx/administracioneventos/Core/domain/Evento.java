package org.ipn.mx.administracioneventos.Core.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Length;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@Table(name = "Evento", schema = "public")
public class Evento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idEvento", nullable = false)
    private Long idEvento;

    @NotEmpty(message = "No puede estar vacio")
    @Column(name = "nombreEvento", length=200, nullable = false, unique = true)
    private String nombreEvento;

    @NotEmpty(message = "No puede estar vacia")
    @Column(name = "descripcionEvento", length=500, nullable = false)
    private String descripcionEvento;

    @NotNull(message = "La fecha no puede ser nula")
    @FutureOrPresent(message = "La fecha de inicio debera ser hoy o cualquier fecha en el futuro")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha no puede ser nula")
    private LocalDate fechaFin;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "idEvento")
    @JsonManagedReference
    private List<Asistente> asistentes;



    //TERMINAR EL CODIGO DE UN ASISTENTE SE REGSITRE LE LLEGUE UN CORREO

    //TERMINAR CONTROLADOR ASITENTE






}