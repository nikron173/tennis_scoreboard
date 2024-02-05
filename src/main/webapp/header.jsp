<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <title>Главная</title>
</head>
<body>
<!-- HEADER -->
    <div class="header">
        <div class="container-hea">

            <div class="header-line">
                <div class="header-logo">
                    <img src="image/logo-2.png" height="70px" width="100px">
                </div>

                <div class="nav">
                    <a class="nav-item" href="@">Главная</a>
                    <a class="nav-item" href="/new-match">Новый матч</a>
                    <a class="nav-item" href="/matches">Завершенные матчи</a>
                </div>
            </div>

        </div>
    </div>
