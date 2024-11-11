/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import modelo.Persona;
import org.springframework.stereotype.Service;
/**
 *
 * @author gusta
 */

@Service
public class PersonaService {
    private List<Persona> personas = new ArrayList<>();
    private Long nextId = 1L;
    
    public PersonaService(){
        personas.add(new Persona(nextId++, "Gustavo", "Andrada", 52));
        personas.add(new Persona(nextId++, "Carlos", "Sanchez", 28));
    }
    //Ver todas las personas
    public List<Persona> getAllPersonas(){
        return personas;
    }
    //Ver persona por Id
    public Optional<Persona> getPersonaById(Long id){
        return personas.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
    //Agregar persona
    public Persona addPersona(Persona persona){
        persona.setId(nextId++);
        persona.add(persona);
        return persona;
    }
    //Actualizar persona
    public Persona updatePersona(Long id, Persona updatePersona){
        Optional<Persona> optionalPersona = getPersonaById(id);
        if (optionalPersona.isPresent()){
            Persona persona = optionalPersona.get();
            persona.setNombre(updatePersona.getNombre());
            persona.setApellido(updatePersona.getApellido());
            persona.setEdad(updatePersona.getEdad());
            return persona;
        }
        return null;
    }
    //Eliminar persona
    public boolean deletePersona(Long Id){
        return personas.removeIf(p -> p.getId().equals(Id));
    }
}
