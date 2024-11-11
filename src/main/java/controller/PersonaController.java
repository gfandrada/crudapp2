/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import modelo.Persona;
import service.PersonaService;

/**
 * Controlador para gestionar las operaciones de Persona.
 */
@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired // @Autowired se utiliza para realizar la inyección de dependencias
    private PersonaService personaService;

    // Manejar solicitudes HTTP de tipo GET para obtener todas las personas
    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaService.getAllPersonas();
    }

    // Manejar solicitudes HTTP de tipo GET para obtener una persona por ID
    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        Optional<Persona> persona = personaService.getPersonaById(id);
        return persona.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Manejar solicitudes HTTP de tipo POST para agregar una nueva persona
    @PostMapping
    public ResponseEntity<Persona> addPersona(@RequestBody Persona persona) {
        Persona newPersona = personaService.addPersona(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPersona);
    }

    // Manejar solicitudes HTTP de tipqo PUT para actualizar una persona
    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona updatedPersona) {
        Persona persona = personaService.updatePersona(id, updatedPersona);
        return persona != null ? ResponseEntity.ok(persona) : ResponseEntity.notFound().build();
    }

    // Manejar solicitudes HTTP de tipo DELETE para eliminar una persona
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        return personaService.deletePersona(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
