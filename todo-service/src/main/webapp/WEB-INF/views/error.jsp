<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Error</title>
</head>
<body>

<h1>Something went wrong</h1>

<p>${message}</p>

<p>Status: ${status}</p>

<a href="${pageContext.request.contextPath}/todos">
    Back to Todos
</a>

</body>
</html>