package com.aston.hw.servlets;
import com.aston.hw.services.FilmServiceImpl;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/filmActor")
@Slf4j
public class FilmAndActorServlet extends HttpServlet {
    private final FilmServiceImpl filmServiceImpl= new FilmServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int film_id = Integer.parseInt(request.getParameter("film_id"));
        int actor_id = Integer.parseInt(request.getParameter("actor_id"));
        filmServiceImpl.addFilmActor(film_id, actor_id);

    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String parameter1 = request.getParameter("film_id");
        String parameter2 = request.getParameter("actor_id");

        if (parameter1 == null | parameter2 == null) {
            log.error("Invalid id");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid id");
        }
        int film_id;
        int actor_id;
        try {
            film_id = Integer.parseInt(parameter1);
            actor_id = Integer.parseInt(parameter2);
        } catch (NumberFormatException e) {
            log.error("Invalid film id");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid film id");
            return;
        }
        filmServiceImpl.deleteFilmActor(film_id, actor_id);
    }

}
