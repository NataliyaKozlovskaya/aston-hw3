package com.aston.hw.servlets;
import com.aston.hw.models.Cinema;
import com.aston.hw.services.CinemaServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/cinema")
public class CinemaServlet extends HttpServlet {
    private final CinemaServiceImpl cinemaServiceImpl= new CinemaServiceImpl();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter wr = response.getWriter();

        String str = request.getParameter("id");
        if(str != null){
            int id = Integer.parseInt(str);
            Cinema cinema = cinemaServiceImpl.getById(id);
            wr.write(mapper.writeValueAsString(cinema));
        }else {
            List<Cinema> cinemaList = cinemaServiceImpl.getAll();
            wr.write(mapper.writeValueAsString(cinemaList));
        }
        wr.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cinema cinema = mapper.readValue(request.getReader(), Cinema.class);
        cinemaServiceImpl.create(cinema);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cinema cinema = mapper.readValue(request.getReader(), Cinema.class);
        cinemaServiceImpl.update(cinema);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        cinemaServiceImpl.delete(id);
    }

}
