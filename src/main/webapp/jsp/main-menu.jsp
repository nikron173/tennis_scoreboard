<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="header.jsp"%>

    <div class="main-menu">
        <div class="container-menu">
            <div class="text-menu">
                <h2 class="h2-text">
                    Главное меню
                </h2>
                <div class="nav-main">
                    <a class="nav-item-main" href="${pageContext.request.contextPath}/">Главная</a><br>
                    <a class="nav-item-main" href="${pageContext.request.contextPath}/new-match">Новый матч</a><br>
                    <a class="nav-item-main" href="${pageContext.request.contextPath}/matches">Завершенные матчи</a><br>
                    <a class="nav-item-main" href="${pageContext.request.contextPath}/unfinished-matches">Не завершенные матчи</a><br>
                </div>
            </div>
        </div>
    </div>

<%@ include file="footer.jsp"%>