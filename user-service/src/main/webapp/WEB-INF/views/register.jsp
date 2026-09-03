<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Account</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/xp.css">
</head>
<body class="xp-desktop xp-page">
<main class="xp-window app-window">
    <div class="xp-titlebar">
        <span>Enterprise Todo</span>
        <span class="title-right">Register</span>
    </div>
    <section class="xp-content">
        <h1 class="xp-heading">Create an account</h1>
        <p class="xp-subtitle">Set up your profile to start managing tasks.</p>

        <form class="xp-form" method="post" action="${pageContext.request.contextPath}/users/register">
            <div class="form-row">
                <label for="name">Full Name</label>
                <input id="name" type="text" name="name" placeholder="Your full name" required>
            </div>
            <div class="form-row">
                <label for="username">Username</label>
                <input id="username" type="text" name="username" placeholder="Choose a username" required>
            </div>
            <div class="form-row">
                <label for="email">Email</label>
                <input id="email" type="email" name="email" placeholder="you@example.com">
            </div>
            <div class="form-row">
                <label for="password">Password</label>
                <input id="password" type="password" name="password" placeholder="Choose a password" required>
            </div>
            <div class="form-actions">
                <button class="xp-button primary-button" type="submit">Create Account</button>
                <a class="text-link" href="${pageContext.request.contextPath}/users/login">Already have an account? Sign in</a>
            </div>
        </form>
    </section>
    <footer class="xp-statusbar">Create your profile.</footer>
</main>
</body>
</html>
