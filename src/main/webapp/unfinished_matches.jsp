<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<div class="main-menu-index">
  <c:choose>
    <c:when test="${empty unfinished_matches}">
      <p>No matches started</p>
    </c:when>
    <c:when test="${not empty unfinished_matches}">
      <ul>
      <c:forEach items="${unfinished_matches}" var="match">
        <li><a href="<c:out value="/match-score?uuid=${match.key}"/>"><c:out value="${match.value.playerOne.username} vs ${match.value.playerTwo.username}" /></a></li>
      </c:forEach>
      </ul>
    </c:when>
  </c:choose>
</div>
<%@include file="footer.jsp"%>
