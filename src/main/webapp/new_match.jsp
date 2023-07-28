<%@include file="header.jsp" %>
<body>
    <h1>New match tennis!<br></h1>
    <form method="post" action="/new-match">
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
