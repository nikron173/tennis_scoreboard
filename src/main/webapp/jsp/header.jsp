<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jsp/css/header.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jsp/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jsp/css/general.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jsp/css/table.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jsp/css/footer.css"/>
    <title>${requestScope.title}</title>
</head>
<body>
<!-- HEADER -->
    <div class="header">
        <div class="header-logo">
            <img class="header-logo-img" src="${pageContext.request.contextPath}/jsp/image/logo-2.png" alt="logo">
        </div>

        <div class="nav">
            <a class="nav-item" href="${pageContext.request.contextPath}/">Главная</a>
            <a class="nav-item" href="${pageContext.request.contextPath}/new-match">Новый матч</a>
            <a class="nav-item" href="${pageContext.request.contextPath}/matches">Завершенные матчи</a>
            <a class="nav-item" href="${pageContext.request.contextPath}/unfinished-matches">Не завершенные матчи</a>
        </div>
    </div>
