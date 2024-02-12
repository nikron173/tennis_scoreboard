<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="header.jsp"%>

<div class="container-menu">
    <div class="h2-text">
        <h2>Ошибки</h2>
    </div>
    <p class="errors">
        ${requestScope.error}
    </p>
</div>

<%@ include file="footer.jsp"%>