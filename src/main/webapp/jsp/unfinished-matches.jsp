<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="header.jsp"%>

<div class="main">
    <div class="main-menu">
        <c:choose>
            <c:when test="${not empty requestScope.matches.values()}">
                <div class="text-menu">
                    Список не завершенных матчей
                </div>
                <div class="nav-main">
                    <ul>
                        <c:forEach var="match" items="${requestScope.matches.values()}">
                            <li>
                                <a class="nav-item-main" href="${pageContext.request.contextPath}/match-score?uuid=${match.id}">
                                        ${match.firstPlayer.name} vs ${match.secondPlayer.name}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:when>
            <c:when test="${empty requestScope.matches.values()}">
                <div class="text-menu">
                    Список не завершенных матчей пуст
                </div>
            </c:when>
        </c:choose>
    </div>
</div>

<%@ include file="footer.jsp"%>