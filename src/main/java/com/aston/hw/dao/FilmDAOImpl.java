package com.aston.hw.dao;
import com.aston.hw.exceptions.FilmNotFoundException;
import com.aston.hw.models.Actor;
import com.aston.hw.models.Film;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.aston.hw.util.ConnectionDB.*;

public class FilmDAOImpl implements FilmDAO {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Film> getAll() {

        List<Film> filmList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("SELECT film.film_id, title, cinema_id, actor.name, film_actor.actor_id " +
                            " FROM film LEFT JOIN film_actor ON (film.film_id = film_actor.film_id) LEFT JOIN actor ON (film_actor.actor_id = actor.id)");
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                Film film = new Film();
                List<Actor> actorList = new ArrayList<>();
                Actor actor = new Actor();

                film.setFilm_id(resultSet.getInt("film_id"));
                film.setTitle(resultSet.getString("title"));
                film.setCinemaId(resultSet.getInt("cinema_id"));

                actor.setId(resultSet.getInt("actor_id"));
                actor.setName(resultSet.getString("name"));
                actorList.add(actor);

                film.setActorList(actorList);
                filmList.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmList;
    }

    @Override
    public Film getById(Integer film_id) {
        Film film = null;
        List<Actor> actorList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement prepareStatement = connection.prepareStatement//("select * from film where film_id=?");
                    ("SELECT film.film_id, title, cinema_id, actor.name, actor.id " +
                            "FROM (film LEFT JOIN film_actor ON (film.film_id = film_actor.film_id)) LEFT JOIN actor ON (film_actor.actor_id = actor.id) " +
                            "WHERE film.film_id = ?");

            prepareStatement.setInt(1, film_id);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                Actor actor = new Actor();

                film.setFilm_id(resultSet.getInt("film_id"));
                film.setTitle(resultSet.getString("title"));
                film.setCinemaId(resultSet.getInt("cinema_id"));

                actor.setId(resultSet.getInt("id"));
                actor.setName(resultSet.getString("name"));
                actorList.add(actor);

                film.setActorList(actorList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(film == null) {
            throw new FilmNotFoundException("Film is not found");
        }
        return film;
    }


    @Override
    public void create(Film film) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("INSERT INTO film(title, cinema_id) VALUES (?, ?)");

            prepareStatement.setString(1, film.getTitle());
            prepareStatement.setInt(2, film.getCinemaId());

            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Film updatedFilm) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("UPDATE film SET title = ?, cinema_id = ? WHERE film_id = ?");

            prepareStatement.setString(1, updatedFilm.getTitle());
            prepareStatement.setInt(2, updatedFilm.getCinemaId());
            prepareStatement.setInt(3, updatedFilm.getFilm_id());

            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFilmActor(Integer filmId, Integer actorId) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("INSERT INTO film_actor(film_id, actor_id) VALUES (?, ?)");

            prepareStatement.setInt(1, filmId);
            prepareStatement.setInt(2, actorId);

            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFilmActor(Integer film_id, Integer actor_id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("DELETE FROM film_actor WHERE film_id = ? && actor_id = ?");

            prepareStatement.setInt(1, film_id);
            prepareStatement.setInt(2, actor_id);

            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer film_id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM film WHERE film_id = ?");

            prepareStatement.setInt(1, film_id);

            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
