<%@ page language="java" isELIgnored="false"%>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link href="https://fonts.googleapis.com/css2?family=Inconsolata&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" href="css/test.css"/>

</head>
<body>
    <div class="login-form-container">
    <form class="login-form-wrapper" action="/Controller" method="post">
            <input type="text" name="username" placeholder="Username"/>
            <input type="password" name="password" placeholder="Password"/>
            <div class="names-input">
                <input type="text" name="firstname" placeholder="First name"/>
                <input type="text" name="lastname" placeholder="Last Name"/>
            </div>
            <input type="hidden" name="command" value="register"/>
            <button class="confrim-button" type="submit">Register</button>
    </form>
    <a> ${requestScope.error} </a>
</div>
</body> 
</html>