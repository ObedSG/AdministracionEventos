package org.ipn.mx.administracioneventos.feature.evento.repository;

import org.ipn.mx.administracioneventos.Core.domain.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    //Crear consulta en jpql que me devuelva un evento por nombre
}
