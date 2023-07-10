package com.aston.hw.services;
import com.aston.hw.models.Film;
import java.util.List;

public interface FilmService {
    Film getById(Integer id);
    List<Film> getAll();
    void delete(int id);
    void create(Film film);
    void update(Film film);

}
