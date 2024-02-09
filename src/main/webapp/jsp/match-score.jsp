<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="header.jsp"%>

<div class="container-menu">
    <div class="container-table">
        <div class="table">
            <table>
                <tr>
                    <th>Игроки</th>
                    <th>Point</th>
                    <th>Game</th>
                    <th>Set</th>
                </tr>
                <tr>
                    <td>${requestScope.first_player.name}</td>
                    <td>${requestScope.first_score.getPoint()}</td>
                    <td>${requestScope.first_score.getGame()}</td>
                    <td>${requestScope.first_score.getSet()}</td>
                </tr>
                <tr>
                    <td>${requestScope.second_player.name}</td>
                    <td>${requestScope.second_score.getPoint()}</td>
                    <td>${requestScope.second_score.getGame()}</td>
                    <td>${requestScope.second_score.getSet()}</td>
                </tr>
            </table>
            <form action="${requestScope.context.contextPath}/match-score?uuid=${requestScope.uuid}"
                  method="post">
                <button type="submit" name="first_player" value="first_player">Игрок 1</button>
                <br>
                <button type="submit" name="second_player" value="second_player">Игрок 2</button>
            </form>
        </div>
    </div>
</div>


<%@ include file="footer.jsp"%>