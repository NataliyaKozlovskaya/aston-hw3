package com.aston.hw.dao;
import com.aston.hw.models.Actor;
import com.aston.hw.models.Film;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.aston.hw.util.ConnectionDB.*;

public class ActorDAOImpl implements ActorDAO {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Actor> getAll() {
        List<Actor> actorList = new ArrayList<>();
        Film film = new Film();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("SELECT actor.id, actor.name, film_actor.film_id, film.title, film.cinema_id " +
                            " FROM actor LEFT JOIN film_actor ON (actor.id = film_actor.actor_id) " +
                            "LEFT JOIN film ON (film_actor.film_id = film.film_id)");
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                Actor actor = new Actor();
                List<Film> filmList = new ArrayList<>();

                //actor.setId(resultSet.getInt("id"));
                actor.setName(resultSet.getString("name"));

                film.setFilm_id(resultSet.getInt("film_id"));
                film.setTitle(resultSet.getString("title"));
                film.setCinemaId(resultSet.getInt("cinema_id"));

                filmList.add(film);
                actor.setFilmList(filmList);
                actorList.add(actor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actorList;
    }

    @Override
    public Actor getById(Integer film_id) {
        Actor actor = new Actor();
        List<Film> filmList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement prepareStatement = connection.prepareStatement//("select * from film where film_id=?");
                    ("SELECT actor.id, actor.name, film_actor.film_id, film.title, film.cinema_id " +
                            " FROM actor LEFT JOIN film_actor ON (actor.id = film_actor.actor_id) " +
                            "LEFT JOIN film ON (film_actor.film_id = film.film_id) WHERE film.film_id=?");
            prepareStatement.setInt(1, film_id);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                Film film = new Film();

                actor.setId(resultSet.getInt("id"));
                actor.setName(resultSet.getString("name"));

                film.setFilm_id(resultSet.getInt("film_id"));
                film.setTitle(resultSet.getString("title"));
                film.setCinemaId(resultSet.getInt("cinema_id"));
                filmList.add(film);

                actor.setFilmList(filmList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actor;
    }


    @Override
    public void create(Actor actor) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("INSERT INTO actor(name) VALUES (?)");
            prepareStatement.setString(1, actor.getName());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Actor updatedActor) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("UPDATE actor SET name = ? WHERE id = ?");
            prepareStatement.setString(1, updatedActor.getName());
            prepareStatement.setInt(2, updatedActor.getId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Integer id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM actor WHERE id = ?");
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
