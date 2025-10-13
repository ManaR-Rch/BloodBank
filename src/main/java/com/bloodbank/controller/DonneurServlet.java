package com.bloodbank.controller;

import com.bloodbank.dao.DonneurDAO;
import com.bloodbank.model.Donneur;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class DonneurServlet extends HttpServlet {

    private DonneurDAO donneurDAO = new DonneurDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Donneur> donneurs = donneurDAO.getAll();
        request.setAttribute("donneurs", donneurs);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/donneurs.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Donneur d = new Donneur();
        d.setNom(request.getParameter("nom"));
        d.setPrenom(request.getParameter("prenom"));
        d.setCin(request.getParameter("cin"));
        d.setTelephone(request.getParameter("telephone"));
        d.setGroupeSanguin(request.getParameter("groupe"));
        d.setAge(Integer.parseInt(request.getParameter("age")));
        d.setPoids(Double.parseDouble(request.getParameter("poids")));
        donneurDAO.ajouter(d);
        response.sendRedirect("donneurs");
    }

}
