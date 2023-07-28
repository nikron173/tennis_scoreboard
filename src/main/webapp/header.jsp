<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>
        <c:choose>
            <c:when test="${title == null}">
                Home
            </c:when>
            <c:otherwise>
                <c:out value="${title}"/>
            </c:otherwise>
        </c:choose>
    </title>
  <link rel="stylesheet" href="styles/mystyle.css">
</head>
<body>
    <header class="header-class">

    </header>

