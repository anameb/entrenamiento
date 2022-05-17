package co.com.sofkau.entrenamiento.curso;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generic.Entity;
import co.com.sofkau.entrenamiento.curso.values.Directiz;
import co.com.sofkau.entrenamiento.curso.values.Fecha;
import co.com.sofkau.entrenamiento.curso.values.MentoriaId;
import co.com.sofkau.entrenamiento.curso.values.Nombre;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Mentoria extends Entity<MentoriaId> {

    protected Nombre nombre;
    protected Set<Directiz> directices;
    protected Fecha fecha;

    public Mentoria(MentoriaId entityId, Nombre nombre, Fecha fecha) {
        super(entityId);
        this.nombre = nombre;
        this.fecha = fecha;
        this.directices = new HashSet<>();
    }

    /*public static Mentoria from(MentoriaId entityId, List<DomainEvent> events){
        var mentoria = new Mentoria(entityId);
        events.forEach(mentoria::applyEvent);
    }*/

    public void agregarDirectiz(Directiz directiz){
        //TODO: validaciones
        this.directices.add(directiz);
    }


}
