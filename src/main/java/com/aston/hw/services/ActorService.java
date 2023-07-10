package com.aston.hw.services;
import com.aston.hw.models.Actor;
import java.util.List;

public interface ActorService {
    Actor getById(Integer id);
    List<Actor> getAll();
    void delete(int id);
    void create(Actor actor);
    void update(Actor actor);

}
