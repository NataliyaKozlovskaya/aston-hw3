package com.aston.hw.servlets;
import com.aston.hw.models.Film;
import com.aston.hw.services.FilmServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/film")
@Slf4j
public class FilmServlet extends HttpServlet {
    private final FilmServiceImpl filmServiceImpl= new FilmServiceImpl();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter wr = response.getWriter();

        String str = request.getParameter("film_id");
        if(str != null){
            int id = Integer.parseInt(str);
            Film film = filmServiceImpl.getById(id);
            wr.write(mapper.writeValueAsString(film));
        }else {
            List<Film> filmList = filmServiceImpl.getAll();
            wr.write(mapper.writeValueAsString(filmList));
        }
        wr.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            Film film = mapper.readValue(request.getReader(), Film.class);
            if (film == null) {
                log.error("Invalid film data");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid film data");
            }
            filmServiceImpl.create(film);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Film film = mapper.readValue(request.getReader(), Film.class);
        if (film == null) {
            log.error("Invalid film data");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid film data");
        }
        filmServiceImpl.update(film);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParameter = request.getParameter("id");
        if (idParameter == null) {
            log.error("Invalid film id");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid film id");
        }
        int id;
        try {
            id = Integer.parseInt(idParameter);
        } catch (NumberFormatException e) {
            log.error("Invalid film id");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid film id");
            return;
        }
        filmServiceImpl.delete(id);
    }

}
