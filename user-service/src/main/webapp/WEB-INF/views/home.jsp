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
<body class="xp-desktop xp-page">
<main class="xp-window app-window">
    <div class="xp-titlebar">
        <span>Enterprise Todo</span>
        <span class="title-right">Home</span>
    </div>
    <section class="xp-content">
        <p class="eyebrow">Home</p>
        <h1 class="xp-heading">Welcome back</h1>
        <p class="xp-subtitle">Your workspace is ready. Sign in to pick up where you left off.</p>
        <div class="form-actions">
            <a class="xp-button primary-button" href="${pageContext.request.contextPath}/users/login">Sign In</a>
            <a class="text-link" href="${pageContext.request.contextPath}/users/register">Create account</a>
        </div>
    </section>
    <footer class="xp-statusbar">Ready</footer>
</main>
</body>
</html>
