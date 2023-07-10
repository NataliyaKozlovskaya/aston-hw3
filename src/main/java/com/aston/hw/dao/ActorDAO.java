package com.aston.hw.dao;
import com.aston.hw.models.Actor;
import java.util.List;

/***
 Интерфейс ActorDAO определяет методы для хранения, поиска, удаления и обновления объектов ActorDAOImpl.
 ***/

public interface ActorDAO {
    List<Actor> getAll();

    Actor getById(Integer id);

    void create(Actor actor);

    void update(Actor actor);

    void delete(Integer actorId);
}

