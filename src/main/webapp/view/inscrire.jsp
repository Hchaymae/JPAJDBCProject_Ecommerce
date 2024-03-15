<%@ include file="/WEB-INF/jspf/header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<h1 align="center"><%= "Inscrivez-Vous" %></h1>
<br/>

<form action="/sebo/inscrire" method="post" style="margin: 0 auto; width: 50%;">
    <c:if test="${not empty SUCCESS}">
        <div class="alert alert-success d-flex align-items-center" role="alert">
            <div>${SUCCESS}</div>
        </div>
    </c:if>

    <c:if test="${not empty ERROR}">
        <div class="alert alert-danger d-flex align-items-center" role="alert">
            <div>${ERROR}</div>
        </div>
    </c:if>

    <c:if test="${not empty WARNING}">
        <div class="alert alert-warning d-flex align-items-center" role="alert">
            <div>${WARNING}</div>
        </div>
    </c:if>
    <br/>
    <div class="mb-3">
         <label for="nom" class="form-label">Nom</label>
         <input type="text" id="nom" name="nom" class="form-control" placeholder="Entrez votre Nom" required>
    </div>
    <div class="mb-3">
         <label for="prenom" class="form-label">Prénom</label>
         <input type="text" id="prenom" name="prenom" class="form-control" placeholder="Entrez votre Prénom" required>
    </div>
    <div class="mb-3">
         <label for="ville" class="form-label">Ville</label>
         <input type="text" id="ville" name="ville" class="form-control" placeholder="Entrez la Ville" required>
    </div>
    <div class="mb-3">
         <label for="adresse" class="form-label">Adresse</label>
         <input type="text" id="adresse"  name="adresse" class="form-control" placeholder="Entrez l'Adresse" required>
    </div>
    <div class="mb-3">
         <label for="codepostale" class="form-label">Code postale</label>
         <input type="text" id="codepostale" name="codepostale" class="form-control" required>
    </div>
    <div class="mb-3">
         <label for="tel" class="form-label">Téléphone</label>
         <input type="text" id="tel" name="tel" class="form-control" required>
    </div>
    <div class="mb-3">
      <label for="email" class="form-label">Email</label>
      <input type="email" class="form-control" name="email" id="email" placeholder="name@example.com" required>
    </div>
    <label for="mdp" class="form-label">Mot de passe</label>
    <input type="password" id="mdp" name="mdp" class="form-control" aria-describedby="passwordHelpBlock" required>
    <div id="passwordHelpBlock" class="form-text">
      Your password must be 8-20 characters long, contain letters and numbers, and must not contain spaces, special characters, or emoji.
    </div> <br>
    <div  style="text-align: center;">
        <button type="submit" class="btn btn-primary">S'inscrire</button>
    </div>
</form>

<%@ include file="/WEB-INF/jspf/footer.jsp" %>
