<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h2>Login</h2>

<form method="post" action="${pageContext.request.contextPath}/users/login">

    <label>Name:</label>
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