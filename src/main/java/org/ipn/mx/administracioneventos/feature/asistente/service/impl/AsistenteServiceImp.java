package org.ipn.mx.administracioneventos.feature.asistente.service.impl;

import org.ipn.mx.administracioneventos.Core.domain.Asistente;
import org.ipn.mx.administracioneventos.feature.asistente.repository.AsistenteRepository;
import org.ipn.mx.administracioneventos.feature.asistente.service.AsistenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class AsistenteServiceImp implements AsistenteService {

    @Autowired
    private AsistenteRepository asistenteRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Asistente> findAll() {

        return asistenteRepository.findAll();
    }


    @Override
    public List<Asistente> finAll() {
        return List.of();
    }

    @Override
    @Transactional

    public Asistente findById(Long id) {
        return asistenteRepository.findById(id).orElse(null);
    }


    @Override
    @Transactional
    public Asistente save(Asistente asistente) {
        return asistenteRepository.save(asistente);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        asistenteRepository.deleteById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public ByteArrayInputStream reportePDF(List<Asistente> listAssistente) {

        //este nos toca a nosotros completar
        return null;
    }
}
