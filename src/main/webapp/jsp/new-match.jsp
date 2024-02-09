<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="header.jsp"%>

<div class="container-form">
    <div class="class-form">
        <form method="post" action="${requestScope.context.contextPath}/new-match">
            <label>
                Игрок 1
                <input name="first_player" type="text" required>
            </label>
            <br>
            <label>
                Игрок 2
                <input name="second_player" type="text" required>
            </label>
            <br>
            <button type="submit" class="btn">Начать</button>
        </form>
    </div>
</div>



<%@ include file="footer.jsp"%>