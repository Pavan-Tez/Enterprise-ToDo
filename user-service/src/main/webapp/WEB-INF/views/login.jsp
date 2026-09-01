<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h2>Login</h2>

<c:if test="${not empty param.error}">
    <p style="color: red"><c:out value="${param.error}" /></p>
</c:if>

<c:if test="${param.notification == 'account-created'}">
    <p id="account-created-notification" style="color: green">
        User created successfully. Your user ID is <c:out value="${param.userId}"/>.
    </p>
    <script>
        window.alert(document.getElementById('account-created-notification').textContent);
    </script>
</c:if>

<c:if test="${param.notification == 'logged-out'}">
    <p id="logged-out-notification" style="color: green">You have been logged out.</p>
    <script>
        window.alert(document.getElementById('logged-out-notification').textContent);
    </script>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/users/login">

    <label>Username:</label>
    <input type="text" name="username" required>

    <br><br>

    <label>Password:</label>
    <input type="password" name="password" required>

    <br><br>

    <button type="submit">Login</button>

</form>

<br>

<a href="${pageContext.request.contextPath}/users/register">
    Create a new account
</a>

</body>
</html>
