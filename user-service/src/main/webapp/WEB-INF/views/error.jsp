<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/xp.css">
</head>
<body class="xp-desktop xp-page">
<main class="xp-window app-window">
    <div class="xp-titlebar">
        <span>Enterprise Todo</span>
        <span class="title-right">Error</span>
    </div>
    <section class="xp-content">
        <h1 class="xp-heading">Something went wrong</h1>
        <p class="notice error">${message}</p>
        <p style="color: var(--text-muted); font-size: 13px;">Status: ${status}</p>
        <div class="form-actions">
            <a class="xp-button" href="${pageContext.request.contextPath}/users/login">Back to Sign In</a>
        </div>
    </section>
    <footer class="xp-statusbar">Error</footer>
</main>
</body>
</html>
