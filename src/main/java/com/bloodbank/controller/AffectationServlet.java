package com.bloodbank.controller;

import com.bloodbank.model.Donneur;
import com.bloodbank.model.Receveur;
import com.bloodbank.service.CompatibiliteService;
import com.bloodbank.service.DonneurService;
import com.bloodbank.service.ReceveurService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AffectationServlet extends HttpServlet {

    private final ReceveurService receveurService = new ReceveurService();
    private final DonneurService donneurService = new DonneurService();
    private final CompatibiliteService compatibiliteService = new CompatibiliteService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Receveur> receveursEnAttente = receveurService.getReceveursEnAttente();
        request.setAttribute("receveursEnAttente", receveursEnAttente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/affectation.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long receveurId = Long.parseLong(request.getParameter("receveurId"));
        Receveur receveur = receveurService.getAllReceveurs().stream()
                .filter(r -> r.getId().equals(receveurId))
                .findFirst()
                .orElse(null);

        if (receveur != null) {
            List<Donneur> compatibles = compatibiliteService.trouverDonneursCompatible(receveur);
            if (!compatibles.isEmpty()) {
                Donneur donneur = compatibles.get(0);
                // Marquer le donneur comme indisponible et le receveur comme servi
                donneurService.marquerNonDisponible(donneur.getId());
                receveurService.marquerServi(receveur.getId());
                response.sendRedirect("affectation?message=success");
                return;
            } else {
                response.sendRedirect("affectation?message=aucun_donneur_compatible");
                return;
            }
        }

        response.sendRedirect("affectation?message=receveur_introuvable");
    }
}


