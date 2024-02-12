<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="header.jsp"%>

<div class="container-menu">
    <div class="container">
        <div class="h2-text">
            <h2>Список не завершенных матчей</h2>
        </div>
        <div class="table-matches">
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
    </div>
</div>

<%@ include file="footer.jsp"%>