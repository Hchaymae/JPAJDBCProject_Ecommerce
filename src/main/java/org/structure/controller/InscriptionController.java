package org.structure.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.structure.dao.ClientDAO;
import org.structure.model.Client;
import org.structure.service.ClientService;

public class InscriptionController extends HttpServlet {

    private ClientService clientService;
    private Client client;

    public void init() {
        clientService = new ClientService();
        client = new Client();
    }

    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String adresse = request.getParameter("adresse");
        int codepostale = Integer.parseInt(request.getParameter("codepostale"));
        String ville = request.getParameter("ville");
        String tel = request.getParameter("tel");
        String mdp = request.getParameter("mdp");

        client = new Client(email,nom,prenom,adresse,codepostale,ville,tel,mdp);

        String url ;
        request.getAttribute("SUCCES");
        request.getAttribute("ERREUR");

        if(clientService.AddService(request,client)){
            url = "/view/identifier.jsp";
        }else{
            url = "/view/inscrire.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request,response);
    }

    public void destroy() {
    }
}
