<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="header.jsp"%>

<div class="main">
    <div class="main-menu">
        <div class="text-menu">
            Ошибки
        </div>
        <p class="errors">
            ${requestScope.error}
        </p>
    </div>
</div>

<%@ include file="footer.jsp"%>