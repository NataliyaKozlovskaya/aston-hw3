package com.aston.hw.services;
import com.aston.hw.models.Cinema;
import java.util.List;

public interface CinemaService {
    Cinema getById(Integer id);
    List<Cinema> getAll();
    void delete(int id);
    void create(Cinema cinema);
    void update(Cinema cinema);

}
