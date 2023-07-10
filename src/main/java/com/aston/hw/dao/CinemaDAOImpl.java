package com.aston.hw.dao;
import com.aston.hw.models.Cinema;
import com.aston.hw.models.Film;
import java.sql.*;
import java.util.*;
import static com.aston.hw.util.ConnectionDB.*;

public class CinemaDAOImpl  implements CinemaDAO {

    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cinema> getAll() {

        List<Cinema> cinemaList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("SELECT cinema.id , cinema.name , film.film_id , film.title" +
                    " FROM cinema LEFT JOIN film on cinema.id = film.cinema_id");

            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                Cinema cinema = new Cinema();
                List<Film> filmList = new ArrayList<>();
                Film film = new Film();

                cinema.setId(resultSet.getInt("id"));
                cinema.setName(resultSet.getString("name"));

                film.setFilm_id(resultSet.getInt("film_id"));
                film.setTitle(resultSet.getString("title"));

                filmList.add(film);
                cinema.setFilmList(filmList);
                cinemaList.add(cinema);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cinemaList;
    }

    @Override
    public Cinema getById(Integer id) {
        List<Film> filmList = new ArrayList<>();
        Cinema cinema = new Cinema();
         try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("SELECT cinema.id, cinema.name, film.film_id, film.title " +
                    " FROM cinema JOIN film ON cinema.id = film.cinema_id WHERE cinema.id=?");
             prepareStatement.setInt(1, id);
             ResultSet resultSet = prepareStatement.executeQuery();

            while(resultSet.next()){
                cinema.setId(resultSet.getInt("id"));
                cinema.setName(resultSet.getString("name"));
                Film film = new Film();
                film.setFilm_id(resultSet.getInt("film_id"));
                film.setTitle( resultSet.getString("title"));
                filmList.add(film);
                cinema.setFilmList(filmList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cinema;
    }


    @Override
    public void create(Cinema cinema) {
        int id=0;
         try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("INSERT INTO cinema (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

             prepareStatement.setString(1, cinema.getName());
             prepareStatement.executeUpdate();

             ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
             if(generatedKeys.next()){
                 id = generatedKeys.getInt(1);
             }
        } catch (SQLException e) {
             e.printStackTrace();
        }
        cinema.setId(id);
    }


        @Override
    public void update(Cinema updatedCinema) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement prepareStatement = connection.prepareStatement("UPDATE cinema SET name = ? WHERE id = ?");
            prepareStatement.setString(1, updatedCinema.getName());
            prepareStatement.setInt(2, updatedCinema.getId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Integer id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM cinema WHERE id = ?");
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
