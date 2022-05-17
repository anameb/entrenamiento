package co.com.sofkau.entrenamiento.curso.commands;

import co.com.sofka.domain.generic.Command;
import co.com.sofkau.entrenamiento.curso.values.CursoId;
import co.com.sofkau.entrenamiento.curso.values.Directiz;
import co.com.sofkau.entrenamiento.curso.values.MentoriaId;

public class AgregarDirectrizDeMentoria extends Command {
    private final CursoId coursoId;

    private final MentoriaId mentoriaId;

    private final Directiz directiz;

    public AgregarDirectrizDeMentoria(CursoId coursoId, MentoriaId mentoriaId, Directiz directiz) {
        this.coursoId = coursoId;
        this.mentoriaId = mentoriaId;
        this.directiz = directiz;
    }

    public CursoId getCoursoId() {
        return coursoId;
    }

    public MentoriaId getMentoriaId() {
        return mentoriaId;
    }

    public Directiz getDirectiz() {
        return directiz;
    }
}
