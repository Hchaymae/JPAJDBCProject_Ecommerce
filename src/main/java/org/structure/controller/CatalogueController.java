package org.structure.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.structure.model.Article;
import org.structure.model.Client;
import org.structure.service.ArticleService;
import org.structure.service.ClientService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CatalogueController  extends HttpServlet {
    private ArticleService articleService;
    private Article article;
    private List<Article> articles;

    public void init() {
        articleService = new ArticleService();
        article = new Article();
        articles = new ArrayList<>();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        articles =  articleService.findAllService();

        request.setAttribute("articles", articles);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/consulter.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}
