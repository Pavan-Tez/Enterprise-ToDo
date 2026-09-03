<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/xp.css">
</head>
<body class="xp-desktop xp-page">
<main class="xp-window app-window">
    <div class="xp-titlebar">
        <span>Enterprise Todo</span>
        <span class="title-right">Profile</span>
    </div>
    <section class="xp-content">
        <h1 class="xp-heading">Your Profile</h1>
        <p class="xp-subtitle">Your account details are shown below.</p>

        <dl class="detail-list">
            <div>
                <dt>User ID</dt>
                <dd>${user.id}</dd>
            </div>
            <div>
                <dt>Username</dt>
                <dd>${user.username}</dd>
            </div>
            <div>
                <dt>Name</dt>
                <dd>${user.name}</dd>
            </div>
            <div>
                <dt>Email</dt>
                <dd>${user.email}</dd>
            </div>
        </dl>

        <div class="form-actions">
            <a class="xp-button primary-button" href="${pageContext.request.contextPath}/todos">My Todos</a>
            <form action="${pageContext.request.contextPath}/users/logout" method="post">
                <button class="xp-button" type="submit">Sign Out</button>
            </form>
        </div>
    </section>
    <footer class="xp-statusbar">Signed in as ${user.username}</footer>
</main>
</body>
</html>
