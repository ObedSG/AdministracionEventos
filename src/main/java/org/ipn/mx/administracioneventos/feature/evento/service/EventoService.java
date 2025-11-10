package org.ipn.mx.administracioneventos.feature.evento.service;

import org.ipn.mx.administracioneventos.Core.domain.Evento;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface EventoService {

    public List<Evento> findAll();
    public Evento findById(Long id);
    public Evento save(Evento evento); //para crear o actualizar
    public void delete(Long id);

    public ByteArrayInputStream reportePDF(List<Evento> listEventos);

}
