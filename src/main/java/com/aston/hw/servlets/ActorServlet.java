package com.aston.hw.servlets;
import com.aston.hw.models.Actor;
import com.aston.hw.services.ActorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/actor")
public class ActorServlet extends HttpServlet {
    private final ActorServiceImpl actorServiceImpl= new ActorServiceImpl();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter wr = response.getWriter();

        String str = request.getParameter("id");
        if(str != null){
            int id = Integer.parseInt(str);
            Actor actor = actorServiceImpl.getById(id);
            wr.write(mapper.writeValueAsString(actor));
        }else {
            List<Actor> actorList = actorServiceImpl.getAll();
            wr.write(mapper.writeValueAsString(actorList));
        }
        wr.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Actor actor = mapper.readValue(request.getReader(), Actor.class);
        actorServiceImpl.create(actor);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Actor actor = mapper.readValue(request.getReader(), Actor.class);
        actorServiceImpl.update(actor);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        actorServiceImpl.delete(id);
    }

}
