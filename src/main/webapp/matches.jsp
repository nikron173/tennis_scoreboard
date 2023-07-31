<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@include file="header.jsp" %>
<body>
    <h1>Tennis matches board.<br></h1>
    <table>
        <tr>
            <th>Number match</th>
            <th>Player 1</th>
            <th>Player 2</th>
            <th>Winner player id</th>
        </tr>
        <c:forEach items="${matches}" var="match">
            <tr>
                <td><c:out value="${match.id}" /></td>
                <td><c:out value="${match.playerOne.username}" /></td>
                <td><c:out value="${match.playerTwo.username}" /></td>
                <td><c:out value="${match.playerWinner.username}" /></td>
            </tr>
        </c:forEach>
    </table>
    <div>
        <c:choose>
            <c:when test="${username == null}">
                <c:forEach begin="1" end="${requestScope.pageSize}" var="loop">
                    <a href="<c:out value="/matches?page=${loop}" />"><c:out value="${loop}"/></a>
                </c:forEach>
            </c:when>
            <c:when test="${username != null}">
                <c:forEach begin="1" end="${requestScope.pageSize}" var="loop">
                    <a href="<c:out value="/matches?page=${loop}&filter_by_player_name=${username}" />"><c:out value="${loop}"/></a>
                </c:forEach>
            </c:when>
        </c:choose>
    </div>
    <div class="find-player-form">
        <form method="get" action="<c:url value="${pageContext.request.contextPath}/matches"/>">
            <label> Match search by player:
                <input type="text" name="filter_by_player_name">
            </label>
            <input type="submit" value="Find">
        </form>
    </div>
    <%@include file="footer.jsp" %>
