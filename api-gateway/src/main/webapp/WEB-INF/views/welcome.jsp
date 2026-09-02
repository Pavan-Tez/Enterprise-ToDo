<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enterprise Todo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/xp.css">
</head>
<body class="xp-desktop">
<main class="xp-window welcome-window">
    <div class="xp-titlebar">
        <span>Enterprise Todo</span>
    </div>
    <section class="xp-content welcome-content">
        <div class="xp-logo" aria-hidden="true"><i></i><i></i><i></i><i></i></div>
        <div>
            <p class="eyebrow">WELCOME</p>
            <h1>Enterprise Todo</h1>
            <p class="lead">A simple place to organise the work that matters.</p>
            <a class="xp-button primary-button" href="${pageContext.request.contextPath}/users/login">Log On</a>
            <a class="text-link" href="${pageContext.request.contextPath}/users/register">Create a new account</a>
        </div>
    </section>
    <footer class="xp-statusbar">Ready</footer>
</main>
</body>
</html>
