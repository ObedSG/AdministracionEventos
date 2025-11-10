package org.ipn.mx.administracioneventos.feature.evento.service.impl;

import org.ipn.mx.administracioneventos.Core.domain.Evento;
import org.ipn.mx.administracioneventos.feature.evento.repository.EventoRepository;
import org.ipn.mx.administracioneventos.feature.evento.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class EventoServiceImp implements EventoService {

   @Autowired
   private EventoRepository eventoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }


    @Override
    @Transactional

    public Evento findById(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }


    @Override
    @Transactional
    public Evento save(Evento evento) {
        return eventoRepository.save(evento);

    }

    @Override
    @Transactional
    public void delete(Long id) {
         eventoRepository.deleteById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public ByteArrayInputStream reportePDF(List<Evento> listEventos) {

        //este nos toca a nosotros completar
        return null;
    }




}
