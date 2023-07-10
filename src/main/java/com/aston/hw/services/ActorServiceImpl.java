package com.aston.hw.services;
import com.aston.hw.dao.ActorDAOImpl;
import com.aston.hw.models.Actor;
import java.util.List;

public class ActorServiceImpl implements ActorService{
    private final ActorDAOImpl actorDAOImpl = new ActorDAOImpl();

    @Override
    public Actor getById(Integer id) {
        return actorDAOImpl.getById(id);
    }

    @Override
    public List<Actor> getAll() {
        return actorDAOImpl.getAll();
    }

    @Override
    public void delete(int id) {
        actorDAOImpl.delete(id);
    }

    @Override
    public void create(Actor actor) {
        actorDAOImpl.create(actor);
    }

    @Override
    public void update(Actor actor) {
        actorDAOImpl.update(actor);
    }

}
