<%@ page language="java" isELIgnored="false"%>

<!DOCTYPE html>
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link href="https://fonts.googleapis.com/css2?family=Inconsolata&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" href="css/test.css"/>
    <title>Document</title>
</head>
<body>
    
    <div class="login-form-container">
        
        <form class="login-form-wrapper" action="/Controller" method="get">
            <h3>LOGIN</h3>
            <input type="text" value="" name="username" placeholder="username"/>
            <input type="password" value="" name="password" placeholder="password"/>
            <input type="hidden" name="command" value="login"/><br>
            <a> ${requestScope.error} </a><br>
            <button class="confrim-button" type="submit">LOG IN</button>
    
        </form>
    </div>
    
</body>
</html>