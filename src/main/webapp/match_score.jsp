<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@include file="header.jsp" %>
<body>
  <h1>Match identifier uuid - <c:out value="${uuid}"/></h1>
  <table>
    <tr>
      <td>Player One</td>
      <td>Player Two</td>
    </tr>
    <tr>
      <td><c:out value="${playerOne.username}"/> </td>
      <td><c:out value="${playerTwo.username}"/> </td>
    </tr>
    <tr>
      <td colspan="2" class="center-str">Score</td>
    </tr>
    <tr>
      <td><c:out value="${scorePlayerOne}" /></td>
      <td><c:out value="${scorePlayerTwo}" /></td>
    </tr>
    <tr>
      <td colspan="2" class="center-str">Game</td>
    </tr>
    <tr>
      <td><c:out value="${gamePlayerOne}" /></td>
      <td><c:out value="${gamePlayerTwo}" /></td>
    </tr>
    <tr>
      <td colspan="2" class="center-str">Set</td>
    </tr>
    <tr>
      <td><c:out value="${setPlayerOne}" /></td>
      <td><c:out value="${setPlayerTwo}" /></td>
    </tr>
  </table>

  <form action="<c:url value="/match-score?uuid=${uuid}" />" method="post">
    <label>Add one point player <c:out value="${playerOne.username}"/>
      <input type="submit" name="addPointOne" value="add point">
    </label>
  </form>
  <form action="<c:url value="/match-score?uuid=${uuid}" />" method="post">
    <label>Add one point player <c:out value="${playerTwo.username}"/>
      <input type="submit" name="addPointTwo" value="add point">
    </label>
  </form>
  <%@include file="footer.jsp" %>
