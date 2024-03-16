package org.structure.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.structure.model.Client;
import org.structure.model.LigneCommande;
import org.structure.service.ClientService;
import org.structure.service.CommandeService;
import org.structure.service.LigneCommandeService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VisualisePanierController extends HttpServlet {

    private LigneCommandeService lignecommandeService;
    private ClientService clientService;
    private Client client;
    private CommandeService commandeService;

    public void init() {
        lignecommandeService = new LigneCommandeService();
        clientService = new ClientService();
        commandeService = new CommandeService() ;
        client = new Client();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        Object clientIdObject = session.getAttribute("clientId");
        Integer clientId = Integer.valueOf(clientIdObject.toString());

        client = clientService.findClientByIdService(clientId);
        String nomComplet = client.getNom() + " " + client.getPrenom();

        List<LigneCommande> articlescommandes = lignecommandeService.findAllLiCmdeByClientIdService(clientId);
        request.setAttribute("articlescommandes", articlescommandes);
        System.out.println(articlescommandes);
        request.setAttribute("nomprenom", nomComplet);

        int numcommande = commandeService.findNumcommandService(clientId);
        double total = commandeService.calculateTotalAmountService(numcommande);
        request.setAttribute("total", total);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/panier.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}
