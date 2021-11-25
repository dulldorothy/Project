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
                <img src="data:image/jpg;base64,${sessionScope.user.image}" alt="" class="profile-pic">
            </div>
            <div class="profile-info-container">
                <p>UserName: ${sessionScope.user.userName}</p>
                <p>First Name: ${sessionScope.user.firstName}</p>
                <p>Last Name: ${sessionScope.user.lastName}</p>
            </div>
            
            <a href="${pageContext.request.contextPath}/Controller?command=logout">logout</a>
            <a href="${pageContext.request.contextPath}/Controller?page=lotcreation&command=go_to_page">Create Lot</a>
            <a href="${pageContext.request.contextPath}/Controller?page=lotcreation&command=go_to_user_lots&currentPage=1">user Lots</a>
        </div>
        
    </div>
   
    <%@ include file="footer.jsp" %>
</body>
</html>