package com.bloodbank.controller;

import com.bloodbank.dao.ReceveurDAO;
import com.bloodbank.model.Receveur;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ReceveurServlet extends HttpServlet {

    private ReceveurDAO receveurDAO = new ReceveurDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Receveur> receveurs = receveurDAO.getAll();
        request.setAttribute("receveurs", receveurs);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/receveurs.jsp");
        dispatcher.forward(request, response);
    }
}
