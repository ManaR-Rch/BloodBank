package com.bloodbank.controller;

import com.bloodbank.service.ReceveurService;
import com.bloodbank.model.Receveur;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ReceveurServlet extends HttpServlet {

    private ReceveurService receveurService = new ReceveurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Receveur> receveurs = receveurService.getAllReceveurs();
        request.setAttribute("receveurs", receveurs);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/receveurs.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Receveur r = new Receveur();
        r.setNom(request.getParameter("nom"));
        r.setPrenom(request.getParameter("prenom"));
        r.setCin(request.getParameter("cin"));
        r.setTelephone(request.getParameter("telephone"));
        r.setGroupeSanguin(request.getParameter("groupe"));
        
        // Utilisation du service pour déterminer la priorité
        r.setPriorite(receveurService.determinerPriorite(r));
        
        // Utilisation du service pour la logique métier
        receveurService.ajouterReceveur(r);
        response.sendRedirect("receveurs");
    }
}
