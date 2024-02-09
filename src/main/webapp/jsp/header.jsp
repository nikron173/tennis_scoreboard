<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jsp/css/style.css"/>
    <title>${requestScope.title}</title>
</head>
<body>
<!-- HEADER -->
    <div class="header">
        <div class="container-hea">

            <div class="header-line">
                <div class="header-logo">
                    <img src="${pageContext.request.contextPath}/jsp/image/logo-2.png"
                         height="70px" width="100px" alt="logo">
                </div>

                <div class="nav">
                    <a class="nav-item" href="${pageContext.request.contextPath}/">Главная</a>
                    <a class="nav-item" href="${pageContext.request.contextPath}/new-match">Новый матч</a>
                    <a class="nav-item" href="${pageContext.request.contextPath}/matches">Завершенные матчи</a>
                </div>
            </div>

        </div>
    </div>
