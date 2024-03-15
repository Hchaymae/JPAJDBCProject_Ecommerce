<%@ include file="/WEB-INF/jspf/header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<h1 align="center"><%= "Identifiez-Vous" %></h1>

<div style="margin: 0 auto; width: 50%;">
    <br/>
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
    <form action="/sebo/identifier" method="post" style="margin: 0 auto; width: 50%;">
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
            <button type="submit" class="btn btn-primary">S'identifier</button>
        </div>
    </form>
</div>

<%@ include file="/WEB-INF/jspf/footer.jsp" %>

