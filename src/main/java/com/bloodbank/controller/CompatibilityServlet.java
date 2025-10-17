package com.bloodbank.controller;

import com.bloodbank.service.CompatibiliteService;
import com.bloodbank.service.DonneurService;
import com.bloodbank.service.ReceveurService;
import com.bloodbank.model.Donneur;
import com.bloodbank.model.Receveur;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class CompatibilityServlet extends HttpServlet {

    private CompatibiliteService compatibilityService = new CompatibiliteService();
    private DonneurService donneurService = new DonneurService();
    private ReceveurService receveurService = new ReceveurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("donneurs-compatible".equals(action)) {
            // Afficher les donneurs compatibles pour un receveur
            Long receveurId = Long.parseLong(request.getParameter("receveurId"));
            List<Receveur> receveurs = receveurService.getAllReceveurs();
            Receveur receveur = receveurs.stream()
                    .filter(r -> r.getId().equals(receveurId))
                    .findFirst()
                    .orElse(null);
            
            if (receveur != null) {
                List<Donneur> donneursCompatible = compatibilityService.trouverDonneursCompatible(receveur);
                request.setAttribute("donneursCompatible", donneursCompatible);
                request.setAttribute("receveur", receveur);
            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/compatibility.jsp");
            dispatcher.forward(request, response);
            
        } else if ("receveurs-compatible".equals(action)) {
            // Afficher les receveurs compatibles pour un donneur
            Long donneurId = Long.parseLong(request.getParameter("donneurId"));
            List<Donneur> donneurs = donneurService.getAllDonneurs();
            Donneur donneur = donneurs.stream()
                    .filter(d -> d.getId().equals(donneurId))
                    .findFirst()
                    .orElse(null);
            
            if (donneur != null) {
                List<Receveur> receveursCompatible = compatibilityService.trouverReceveursCompatible(donneur);
                request.setAttribute("receveursCompatible", receveursCompatible);
                request.setAttribute("donneur", donneur);
            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/compatibility.jsp");
            dispatcher.forward(request, response);
            
        } else {
            // Vue d'ensemble des compatibilités
            String statistiques = compatibilityService.obtenirStatistiquesCompatibility();
            request.setAttribute("statistiques", statistiques);
            
            List<Donneur> donneursDisponibles = donneurService.getDonneursDisponibles();
            List<Receveur> receveursEnAttente = receveurService.getReceveursEnAttente();
            
            request.setAttribute("donneursDisponibles", donneursDisponibles);
            request.setAttribute("receveursEnAttente", receveursEnAttente);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/compatibility.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("match".equals(action)) {
            // Créer un match entre un donneur et un receveur
            Long donneurId = Long.parseLong(request.getParameter("donneurId"));
            Long receveurId = Long.parseLong(request.getParameter("receveurId"));
            
            // Marquer le donneur comme non disponible et le receveur comme satisfait
            donneurService.marquerNonDisponible(donneurId);
            receveurService.marquerSatisfait(receveurId);
            
            response.sendRedirect("compatibility?message=match-success");
        }
    }
}
