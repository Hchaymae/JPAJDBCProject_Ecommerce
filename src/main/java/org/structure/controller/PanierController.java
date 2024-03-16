package org.structure.controller;


import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.structure.model.Article;
import org.structure.model.Commande;
import org.structure.model.LigneCommande;
import org.structure.model.StatusCommande;
import org.structure.service.ArticleService;
import org.structure.service.CommandeService;
import org.structure.service.LigneCommandeService;

public class PanierController extends HttpServlet {
    private ArticleService articleService;
    private CommandeService commandeService;
    private LigneCommandeService lignecommandeService;
    private Commande commande;
    private Article article;

    public void init() {
        articleService = new ArticleService();
        commandeService = new CommandeService();
        lignecommandeService = new LigneCommandeService();
        commande = new Commande();
        article = new Article();
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);

        try {
            String articleDesignation = request.getParameter("designation");
            Article article = articleService.FindArticleByDesignation(articleDesignation);
            int codearticle = article.getCodeArticle();
            int qteCde = Integer.parseInt(request.getParameter("qte"));

            Object clientIdObject = session.getAttribute("clientId");
            if (clientIdObject == null) {
                // Handle the case where the client ID is not set in the session
                return;
            }
            Integer clientId = Integer.valueOf(clientIdObject.toString());

            Commande commande = new Commande(clientId, new Date());
            String status = commandeService.findStatusService(clientId);
            int rs = commandeService.addCommande(request,commande,clientId);
            LigneCommande ligneCde = null;
            if (rs > 0 || rs == -1) {
                if (status.equals(StatusCommande.Normal.toString())) {
                    if (rs > 0) {
                        ligneCde = new LigneCommande(commandeService.findNumcommandService(clientId), codearticle, qteCde);
                    } else if (rs == -1) {
                        ligneCde = new LigneCommande(commandeService.findByClientIdService(clientId).getNumcommande(), codearticle, qteCde);
                        commandeService.calculateTotalAmountService(commandeService.findByClientIdService(clientId).getNumcommande());
                    }
                    boolean insertion2 = lignecommandeService.addLigneCommande(request, ligneCde);
                    if (insertion2) {
                        response.sendRedirect(request.getContextPath() + "/view/consulter.jsp");
                        return;
                    }
                }
            }
            List<Article> articles = articleService.findAllService();
            request.setAttribute("articles", articles);
        } catch (NumberFormatException e) {
            // Handle the case where the quantity is not a valid integer
        } catch (Exception e) {
            // Handle other exceptions
        }
    }

    public void destroy() {
    }
}