<%@include file="header.jsp" %>
<h1>New match tennis!<br></h1>
<form class="class-form-register" method="post" action="/new-match">
    <label> Player one name:
        <input type="text" name="playerOne" minlength="3">
        <br>
    </label>
    <br>
    <label> Player two name:
        <input type="text" name="playerTwo" minlength="3">
        <br>
    </label>
    <input type="submit" value="start">
</form>
<%@include file="footer.jsp" %>
