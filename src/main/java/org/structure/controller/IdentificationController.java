package org.structure.controller;


import java.io.*;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.structure.model.Client;
import org.structure.service.ClientService;

public class IdentificationController extends HttpServlet {
    private ClientService clientService;

    public void init() {
        clientService = new ClientService();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        System.out.println(email);
        System.out.println(mdp);

        Client client = clientService.FindByEmailMdpService(email, mdp);
        String url = null;
        if (client != null) {
            url = "/view/main.jsp";
            int clientId = client.getId();
            String nomComplet = client.getNom() + " " + client.getPrenom();

            HttpSession session = request.getSession(true);
            session.setAttribute("clientId", clientId);
            session.setAttribute("clientName", nomComplet);
        } else {
            url = "/view/erreur.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }



    public void destroy() {
    }
}