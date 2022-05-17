package co.com.sofkau.entrenamento.curso;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.entrenamiento.curso.Curso;
import co.com.sofkau.entrenamiento.curso.commands.AgregarDirectrizDeMentoria;
import co.com.sofkau.entrenamiento.curso.commands.AgregarMentoria;
import co.com.sofkau.entrenamiento.curso.events.CursoCreado;
import co.com.sofkau.entrenamiento.curso.events.DirectrizAgregadaAMentoria;
import co.com.sofkau.entrenamiento.curso.events.MentoriaCreada;
import co.com.sofkau.entrenamiento.curso.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgregarDirectrizDeMentoriaUseCaseTest {

    @InjectMocks
    private AgregarDirectrizDeMentoriaUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void agregarDirectrizDeMentoriaHappyPass(){
        //arrange
        CursoId coursoId = CursoId.of("ddddd");
        //Curso.from(coursoId, )
        MentoriaId mentoriaId = new MentoriaId("kjhj");
        Directiz directiz = new Directiz("Ejemplo de Directriz");
        var command = new AgregarDirectrizDeMentoria(coursoId, mentoriaId, directiz);

        when(repository.getEventsBy("ddddd")).thenReturn(history());
        useCase.addRepository(repository);

        //when(repository.getEventsBy("ddddd")).thenReturn(historyMentoria());
        //useCase.addRepository(repository);

        //act

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(command.getCoursoId().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var event = (DirectrizAgregadaAMentoria)events.get(0);
        Assertions.assertEquals("Ejemplo de Directriz", event.getDirectiz().value());
    }

    private List<DomainEvent> history() {
        Nombre nombre = new Nombre("DDD");
        Descripcion descripcion = new Descripcion("Curso complementario para el training");
        var event = new CursoCreado(
                nombre,
                descripcion
        );

        event.setAggregateRootId("xxxxx");

        //_----------------------------------
        Nombre nombreMentoria = new Nombre("Prueba");
        Fecha fecha = new Fecha(LocalDateTime.now(), LocalDate.now());
        MentoriaId mentoriaId = new MentoriaId();
        var event2 = new MentoriaCreada(
                MentoriaId.of("kjhj"), nombreMentoria, fecha
        );

        //event.setAggregateRootId("xxxxx");
        return List.of(event,event2);
    }


}