package org.ipn.mx.administracioneventos.feature.evento.controller;

import org.ipn.mx.administracioneventos.Core.domain.Evento;
import org.ipn.mx.administracioneventos.feature.evento.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/v1/eventos")
public class EventoController {

    @Autowired
    private EventoService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Evento> readAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        Evento evento = null;
        Map<String, Object> respuesta = new HashMap<>();
        try {
            evento = service.findById(id);
        } catch (DataAccessException ex) {
            respuesta.put("mensaje", "Error al realizar la consulta en la base de datos");
            respuesta.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (evento == null) {
            respuesta.put("mensaje", "El evento con ID ".concat(id.toString()).concat(" no existe en la base de datos"));
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(evento, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Evento evento) {
        Evento e = null;
        Map<String, Object> respuesta = new HashMap<>();

        try {
            e = service.save(evento);
        } catch (DataAccessException ex) {
            respuesta.put("mensaje", "Error al realizar el registro en la base de datos");
            respuesta.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El evento se ha creado correctamente");
        respuesta.put("evento", e);

        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Evento evento, @PathVariable Long id) {
        Evento e = service.findById(id);
        Evento eventoActualizado;
        Map<String, Object> respuesta = new HashMap<>();

        if (e == null) {
            respuesta.put("mensaje", "Error: no se puede editar. El evento con ID ".concat(id.toString()).concat(" no existe en la base de datos."));
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }

        try {
            e.setNombreEvento(evento.getNombreEvento());
            e.setDescripcionEvento(evento.getDescripcionEvento());
            e.setFechaInicio(evento.getFechaInicio());
            e.setFechaFin(evento.getFechaFin());

            eventoActualizado = service.save(e);
        } catch (DataAccessException ex) {
            respuesta.put("mensaje", "Error al actualizar el registro en la base de datos");
            respuesta.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El evento se ha actualizado correctamente");
        respuesta.put("evento", eventoActualizado);

        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }



    //BORRAR POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> respuesta = new HashMap<>();

        //antes de eliminar verificar que el registro exista
       //Evento e = service.findById(id);


        try {
            service.delete(id);
        } catch (DataAccessException ex) {
            respuesta.put("mensaje", "Error eliminar el registro en la bd");
            respuesta.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

       respuesta.put("mensaje", "El evento se ha eliminado correctamente");


        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }




}
