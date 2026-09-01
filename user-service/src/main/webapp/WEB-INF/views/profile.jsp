<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
</head>
<body>

<h1>Authentication Test Page</h1>

<h2>You are authenticated! 🎉</h2>

<p>
    <strong>User ID:</strong>
    ${user.id}
</p>

<p>
    <strong>Username:</strong>
    ${user.username}
</p>
<p>
    <strong>Name:</strong>
    ${user.name}
</p>

<p>
    <strong>Email:</strong>
    ${user.email}
</p>

<br>

<form action="${pageContext.request.contextPath}/users/logout"
      method="post">

    <button type="submit">Logout</button>

</form>

</body>
</html>