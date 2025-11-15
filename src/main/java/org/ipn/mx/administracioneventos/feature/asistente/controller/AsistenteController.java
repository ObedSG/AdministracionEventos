package org.ipn.mx.administracioneventos.feature.asistente.controller;

import org.ipn.mx.administracioneventos.Core.domain.Asistente;
import org.ipn.mx.administracioneventos.feature.asistente.service.AsistenteService;
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
@RequestMapping("/api/v1/asistentes")
public class AsistenteController {

    @Autowired
    private AsistenteService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Asistente> readAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        Asistente asistente = null;
        Map<String, Object> respuesta = new HashMap<>();
        try {
            asistente = service.findById(id);
        } catch (DataAccessException ex) {
            respuesta.put("mensaje", "Error al realizar la consulta en la base de datos");
            respuesta.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (asistente == null) {
            respuesta.put("mensaje", "El evento con ID ".concat(id.toString()).concat(" no existe en la base de datos"));
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(asistente, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Asistente asistente) {
        Asistente e = null;
        Map<String, Object> respuesta = new HashMap<>();

        try {
            e = service.save(asistente);
        } catch (DataAccessException ex) {
            respuesta.put("mensaje", "Error al realizar el registro en la base de datos");
            respuesta.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El asistente se ha creado correctamente");
        respuesta.put("asistente", e);

        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Asistente asistente, @PathVariable Long id) {
        Asistente e = service.findById(id);
        Asistente asistenteActualizado;
        Map<String, Object> respuesta = new HashMap<>();

        if (e == null) {
            respuesta.put("mensaje", "Error: no se puede editar. El evento con ID ".concat(id.toString()).concat(" no existe en la base de datos."));
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }

        try {
            e.setNombre(asistente.getNombre());
            e.setPaterno(asistente.getPaterno());
            e.setMaterno(asistente.getMaterno());
            e.setEmail(asistente.getEmail());
            e.setFechaRegistro(asistente.getFechaRegistro());
//            e.setIdAsistente(asistente.getIdAsistente());
//            e.setIdEvento(asistente.getIdEvento());

            asistenteActualizado = service.save(e);
        } catch (DataAccessException ex) {
            respuesta.put("mensaje", "Error al actualizar el registro en la base de datos");
            respuesta.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El evento se ha actualizado correctamente");
        respuesta.put("evento", asistenteActualizado);

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

        respuesta.put("mensaje", "El asistente se ha eliminado correctamente");


        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }




}
