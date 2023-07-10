package com.aston.hw.services;
import com.aston.hw.dao.CinemaDAOImpl;
import com.aston.hw.models.Cinema;
import java.util.List;

public class CinemaServiceImpl implements CinemaService{
    private final CinemaDAOImpl cinemaDAOImpl = new CinemaDAOImpl();

    @Override
    public Cinema getById(Integer id) {
        return cinemaDAOImpl.getById(id);
    }

    @Override
    public List<Cinema> getAll() {
        return cinemaDAOImpl.getAll();
    }

    @Override
    public void delete(int id) {
        cinemaDAOImpl.delete(id);
    }

    @Override
    public void create(Cinema cinema) {
        cinemaDAOImpl.create(cinema);
    }

    @Override
    public void update(Cinema cinema) {
        cinemaDAOImpl.update(cinema);
    }

}
