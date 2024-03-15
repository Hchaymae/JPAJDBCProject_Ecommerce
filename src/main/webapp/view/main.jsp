<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/header.jsp" %>

<c:choose>
    <c:when test="${not empty sessionScope.clientId}">
        <h2 align="center">Bonjour Mr/Mme ${sessionScope.clientName}!</h2>
        <hr><br><br>
        <div style="margin: 0 auto; width: 50%;">
            <b><a href="/sebo/catalogue" style="text-decoration: none;  display: block; color:black;"><span style="color: #6DA5C0;"> Consulter Le Catalogue </span></a></b><br><br>
            <b><a href="suivre.jsp" style="text-decoration: none; display: block; color:black;"><span style="color: #6DA5C0;"> Suivre Vos Commandes </span></a></b> <br><br>
            <b><a href="visualiser.jsp" style="text-decoration: none; display: block; color:black;"><span style="color: #6DA5C0;"> Visualiser Votre Panier </span></a></b> <br><br>
        </div>
    </c:when>
    <c:otherwise>
        <h1 style="margin-left:4rem;">Bienvenue chez Sebo - Veuillez-vous identifier ou vous inscrire</h1>
        <br><br>
        <div style="margin: 0 auto; width: 50%;">
            <b><a href="view/identifier.jsp" style="text-decoration: none;  display: block; color:black;">Déjà inscrit ,<span style="color: #6DA5C0;"> Identifiez-vous </span></a></b><br><br>
            <b><a href="view/inscrire.jsp" style="text-decoration: none; display: block; color:black;">Nouveau client ,<span style="color: #6DA5C0;"> Inscrivez-vous </span></a></b> <br><br>
        </div>
    </c:otherwise>
</c:choose>
<%@ include file="/WEB-INF/jspf/footer.jsp" %>
