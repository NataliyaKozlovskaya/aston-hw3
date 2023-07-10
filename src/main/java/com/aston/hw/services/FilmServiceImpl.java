package com.aston.hw.services;
import com.aston.hw.dao.FilmDAOImpl;
import com.aston.hw.models.Film;
import java.util.List;

public class FilmServiceImpl implements FilmService{
    private final FilmDAOImpl filmDAOImpl = new FilmDAOImpl();

    @Override
    public Film getById(Integer id) {
        return filmDAOImpl.getById(id);
    }

    @Override
    public List<Film> getAll() {
        return filmDAOImpl.getAll();
    }

    @Override
    public void delete(int id) {
        filmDAOImpl.delete(id);
    }

    @Override
    public void create(Film film) {
        filmDAOImpl.create(film);
    }

    @Override
    public void update(Film film) {
        filmDAOImpl.update(film);
    }

    public void addFilmActor(Integer film_id, Integer actor_id) {
            filmDAOImpl.addFilmActor(film_id, actor_id);
    }

    public void deleteFilmActor(Integer film_id, Integer actor_id) {
        filmDAOImpl.deleteFilmActor(film_id, actor_id);
    }
}
