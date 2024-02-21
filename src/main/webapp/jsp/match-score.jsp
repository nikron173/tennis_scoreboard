<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="header.jsp"%>

<div class="main">
    <div class="main-menu">
        <div class="text-menu">
            Матч
        </div>
        <div class="main-menu-table">
            <form class="form-width form-left" action="${requestScope.context.contextPath}/match-score?uuid=${requestScope.uuid}"
                  method="post">
                <button class="btn" type="submit" name="first_player" value="${requestScope.firstPlayer.name}">Игрок 1</button>
            </form>
            <table>
                <tr>
                    <th>Игроки</th>
                    <th>Point</th>
                    <th>Game</th>
                    <th>Set</th>
                </tr>
                <tr>
                    <td>${requestScope.firstPlayer.name}</td>
                    <td>${requestScope.firstPlayerViewPoint}</td>
                    <td>${requestScope.firstScore.getGame()}</td>
                    <td>${requestScope.firstScore.getSet()}</td>
                </tr>
                <tr>
                    <td>${requestScope.secondPlayer.name}</td>
                    <td>${requestScope.secondPlayerViewPoint}</td>
                    <td>${requestScope.secondScore.getGame()}</td>
                    <td>${requestScope.secondScore.getSet()}</td>
                </tr>
            </table>
            <form class="form-width form-right" action="${requestScope.context.contextPath}/match-score?uuid=${requestScope.uuid}"
                  method="post">
                <button class="btn" type="submit" name="second_player" value="${requestScope.secondPlayer.name}">Игрок 2</button>
            </form>
        </div>
    </div>
</div>


<%@ include file="footer.jsp"%>