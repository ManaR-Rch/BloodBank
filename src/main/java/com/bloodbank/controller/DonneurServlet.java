package com.bloodbank.controller;

import com.bloodbank.service.DonneurService;
import com.bloodbank.model.Donneur;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class DonneurServlet extends HttpServlet {

    private DonneurService donneurService = new DonneurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Donneur> donneurs = donneurService.getAllDonneurs();
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
        
        // Utilisation du service pour la logique métier
        donneurService.ajouterDonneur(d);
        response.sendRedirect("donneurs");
    }

}
