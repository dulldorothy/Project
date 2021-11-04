<%@ page language="java" isELIgnored="false"%>


<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <title>Document</title>
</head>
<body class="body-main">
    <%@ include file="header.jsp" %>
    <div class="main-container">
        <div class="profile-container-wrapper">
            <div class="profile-pic-container">
                <img src="https://previews.123rf.com/images/gesrey/gesrey1903/gesrey190300189/118727657-the-face-of-a-sleeping-cat-square-format-toned-close-up-.jpg" alt="" class="profile-pic">
            </div>
            <div class="profile-info-container">
                <p>UserName: ${sessionScope.username}</p>
                <p>First Name: ${sessionScope.firstname}</p>
                <p>Last Name: ${sessionScope.lastname}</p>
            </div>
            
        </div>
        
    </div>
   
    <%@ include file="footer.jsp" %>
</body>
</html>