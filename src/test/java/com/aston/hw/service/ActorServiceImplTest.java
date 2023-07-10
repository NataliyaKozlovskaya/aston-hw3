package com.aston.hw.service;
import com.aston.hw.dao.ActorDAOImpl;
import com.aston.hw.models.Actor;
import com.aston.hw.models.Film;
import com.aston.hw.services.ActorServiceImpl;
import com.aston.hw.services.FilmServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;

@RequiredArgsConstructor   // TODO
@ExtendWith(MockitoExtension.class)
public class ActorServiceImplTest {
    private ActorServiceImpl actorServiceImpl;
    @Mock
    private ActorDAOImpl actorDAOImpl;
    private FilmServiceImpl filmServiceImpl;

    @BeforeEach
    public void doWork(){
        actorServiceImpl = new ActorServiceImpl();
        filmServiceImpl = new FilmServiceImpl();

    }

    @Test
    void should_Get_All_Actors() {

//        List<Actor> actorList = new ArrayList<>();
//        Mockito.when(actorDAOImpl.getAll()).thenReturn(actorList);//   null
//        List<Actor> actors = actorServiceImpl.getAll();
//        Mockito.verify(actorDAOImpl, times(1)).getAll();//  actorDAOImpl should be mock
//        Assertions.assertEquals(actorList, actors);

    }

    @Test
    void should_Get_Actor_By_Valid_Id() {//2 tests
//        Integer id = 4;
//        Actor actor = actorServiceImpl.getById(id);
//
//        Assertions.assertEquals("ivan", actor.getName());//&&&&&&&&&&&
//        //Assertions.assertThrows(RuntimeException.class, ()->actorServiceImpl.getById(id));//in service!!!!!

    }

    @Test
    void shouldCreateActor() {
//        List<Film> filmList = new ArrayList<>();
//        Actor actor = new Actor(1, "Ivan", filmList);
//        actorServiceImpl.create(actor);
//
//        Mockito.verify(actorDAOImpl, times(1)).create(actor);
    }

    @Test
    void shouldUpdateActor() {
//        List<Film> filmList = new ArrayList<>();
//        Actor actor = new Actor(1, "Ivan", filmList);
//        actorServiceImpl.update(actor);
//
//        //Mockito.verify(actorDAOImpl, times(1)).update(actor);
    }

    @Test
    void shouldDeleteActor() {
//        Integer id = 4;
//        actorServiceImpl.delete(id);
//
//        //Assertions.assertThrows(Exception.class, ()->actorDAOImpl.delete(id));
//        //Mockito.verify(actorDAOImpl, times(1)).delete(id);
    }

}