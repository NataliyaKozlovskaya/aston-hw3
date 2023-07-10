package com.aston.hw.dao;
import com.aston.hw.models.Cinema;
import java.util.List;

/***
 Интерфейс CinemaDAO определяет методы для хранения, поиска, удаления и обновления объектов CinemaDAOImpl.
 ***/

public interface CinemaDAO {
    List<Cinema> getAll();
    Cinema getById(Integer id);
    void create(Cinema cinema);
    void update(Cinema cinema);
    void delete(Integer cinemaId);

}

