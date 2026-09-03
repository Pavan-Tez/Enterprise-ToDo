<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign In</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/xp.css">
</head>
<body class="xp-desktop xp-page">
<main class="xp-window app-window">
    <div class="xp-titlebar">
        <span>Enterprise Todo</span>
        <span class="title-right">Sign In</span>
    </div>
    <section class="xp-content">
        <h1 class="xp-heading">Sign in</h1>
        <p class="xp-subtitle">Enter your credentials to access your account.</p>

        <c:if test="${not empty param.error}">
            <p class="notice error">
                <c:out value="${param.error}" />
            </p>
        </c:if>

        <c:if test="${param.notification == 'account-created'}">
            <p class="notice success">
                Account created successfully. Your user ID is <c:out value="${param.userId}" />.
            </p>
        </c:if>

        <c:if test="${param.notification == 'logged-out'}">
            <p class="notice success">
                You have been signed out.
            </p>
        </c:if>

        <form class="xp-form" method="post" action="${pageContext.request.contextPath}/users/login">
            <div class="form-row">
                <label for="username">Username</label>
                <input id="username" type="text" name="username" placeholder="Enter your username" required>
            </div>
            <div class="form-row">
                <label for="password">Password</label>
                <input id="password" type="password" name="password" placeholder="Enter your password" required>
            </div>
            <div class="form-actions">
                <button class="xp-button primary-button" type="submit">Sign In</button>
                <a class="xp-button" href="${pageContext.request.contextPath}/">Home</a>
                <a class="text-link" href="${pageContext.request.contextPath}/users/register">Create an account</a>
            </div>
        </form>
    </section>
    <footer class="xp-statusbar">Please enter your username and password.</footer>
</main>
</body>
</html>
