<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        body { font-family: Arial, sans-serif; color: #333; }
        .box { padding: 16px; background: #f7f7f7; border-radius: 8px; }
        .title { font-size: 20px; font-weight: bold; }
        .info { margin-top: 12px; }
        .footer { margin-top: 24px; color: #666; font-size: 12px; }
    </style>
</head>

<body>
<div class="box">
    <p class="title">Your Account Credentials</p>

    <p>Dear ${fullName},</p>

    <p>An account has been created for you.</p>

    <div class="info">
        <p><b>Username:</b> ${username}</p>
        <p><b>Password:</b> ${password}</p>
    </div>

    <p>Please change your password upon first login.</p>

    <p class="footer">Best regards,<br>Medical Staff Management Team</p>
</div>
</body>
</html>
