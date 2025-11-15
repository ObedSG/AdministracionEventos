package org.ipn.mx.administracioneventos.feature.asistente.service;

import org.ipn.mx.administracioneventos.Core.domain.Asistente;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.util.List;



public interface AsistenteService {

    @Transactional(readOnly = true)
    List<Asistente> findAll();

    public List<Asistente> finAll();
    public Asistente findById(Long id);
    public Asistente save(Asistente asistente); //para crear o actualizar
    public void delete(Long id);

    public ByteArrayInputStream reportePDF(List<Asistente> listAsistentes);


}
