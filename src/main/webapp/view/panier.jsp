<%@ include file="/WEB-INF/jspf/header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div style="margin: 0 auto; width: 50%;">
    <h2 align="center">Votre Panier ${nomprenom}</h2>
    <hr><br><br>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Nom du Client</th>
            <th scope="col">Designation de l'article</th>
            <th scope="col">Prix de l'article</th>
            <th scope="col">Stock de l'article</th>
            <th scope="col">Quantité commandée</th>
            <th scope="col">Date de commande</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ligneCommande" items="${articlescommandes}" >
            <tr>
                <td>${ligneCommande.commande.numcommande}</td>
                <td>${ligneCommande.article.designation}</td>
                <td>${ligneCommande.article.prix}</td>
                <td>${ligneCommande.article.stock}</td>
                <td>${ligneCommande.qtecde}</td>
                <td>${ligneCommande.commande.datecommande}</td>
            </tr>
            <h4>${ligneCommande.commande.total} DH</h4>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="/WEB-INF/jspf/footer.jsp" %>
