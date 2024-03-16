<%@ include file="/WEB-INF/jspf/header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div style="margin: 0 auto; width: 50%;" class="text-center">

    <h2 align="center">Catalogue</h2>
    <hr><br><br>
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
    <table class="table">
        <thead>
        <tr>
            <th scope="col" class="text-primary">Référence</th>
            <th scope="col" class="text-primary">Titre</th>
            <th scope="col" class="text-primary">Auteur</th>
            <th scope="col" class="text-primary">Photo</th>
            <th scope="col" class="text-primary">Prix</th>
            <th scope="col" class="text-primary"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="article" items="${articles}" >
            <tr>
                <td><a href="/sebo/details?designation=${article.designation}">${article.designation}</a></td>
                <td>${article.titre}</td>
                <td>${article.auteur}</td>
                <td><img src="${article.photo}" alt="Image de l'article"  style="width: 80px; height: 80px;"></td>
                <td>${article.prix}</td>
                <td>
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop_${article.codeArticle}" data-designation="${article.designation}">
                        Ajouter au panier
                    </button>
                </td>
            </tr>

            <div class="modal fade" id="staticBackdrop_${article.codeArticle}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel_${article.codeArticle}" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="staticBackdropLabel_${article.codeArticle}">Ajouter</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form method="POST" action="/sebo/consulter?designation=${article.designation}">
                                <div class="mb-3">
                                    <label class="col-form-label">Quantité :</label>
                                    <input type="number" name="qte" id="qte" class="form-control">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">
                                        Ajouter au panier
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="/WEB-INF/jspf/footer.jsp" %>
