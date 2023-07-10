package com.aston.hw.dao;
import com.aston.hw.models.Film;
import java.util.List;

/***
 Интерфейс FilmDAO определяет методы для хранения, поиска, удаления и обновления объектов FilmDAOImpl.
 ***/

public interface FilmDAO {
    List<Film> getAll();
    Film getById(Integer id);
    void create(Film film);
    void update(Film film);
    void delete(Integer filmId);

}

