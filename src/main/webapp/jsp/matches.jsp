<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="header.jsp"%>

<div class="container-menu">
    <div class="container-table">
        <table>
            <tr>
                <th>Id матча</th>
                <th>Игрок 1</th>
                <th>Игрок 2</th>
                <th>Победитель матча</th>
            </tr>
            <c:forEach var="match" items="${requestScope.matches}">
                <tr>
                    <td>${match.matchId}</td>
                    <td>${match.firstPlayerName}</td>
                    <td>${match.secondPlayerName}</td>
                    <td>${match.winnerPlayerName}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<%@ include file="footer.jsp"%>