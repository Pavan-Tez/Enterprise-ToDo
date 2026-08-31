<!DOCTYPE html>
<html>
<head>
    <title>Create User</title>
</head>
<body>

<h2>Create User</h2>

<form method="post" action="${pageContext.request.contextPath}/users/register">

    <label>Name:</label>
    <input type="text" name="name" required>

    <br><br>

    <label>Username:</label>
    <input type="text" name="username" required>

    <br><br>

    <label>Email:</label>
    <input type="email" name="email">

    <br><br>

    <label>Password:</label>
    <input type="password" name="password" required>

    <br><br>

    <button type="submit">Create Account</button>

</form>

<br>

<a href="${pageContext.request.contextPath}/users/login">
    Already have an account? Login
</a>

</body>
</html>