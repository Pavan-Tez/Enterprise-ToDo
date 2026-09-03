<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enterprise Todo</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/xp.css">
</head>
<body class="xp-desktop">
<main class="xp-window welcome-window">
    <div class="xp-titlebar">
        <span>Enterprise Todo</span>
        <span class="title-right">v1.0</span>
    </div>
    <section class="xp-content welcome-content">
        <div class="xp-logo" aria-hidden="true"><i></i><i></i><i></i><i></i></div>
        <div>
            <p class="eyebrow">Welcome</p>
            <h1>Enterprise Todo</h1>
            <p class="lead">A clean, focused space to organise the work that matters most.</p>
            <div class="form-actions">
                <a class="xp-button primary-button" href="${pageContext.request.contextPath}/users/login">Get Started</a>
                <a class="text-link" href="${pageContext.request.contextPath}/users/register">Create an account</a>
            </div>
        </div>
    </section>
    <footer class="xp-statusbar">Ready</footer>
</main>
</body>
</html>
