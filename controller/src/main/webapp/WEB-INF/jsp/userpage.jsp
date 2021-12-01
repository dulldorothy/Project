<%@ page language="java" isELIgnored="false"%>


<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Document</title>
</head>
<body class="body-main">
    <%@ include file="header.jsp" %>
    <div class="main-container">
        <div class="profile-container-wrapper">
            <div class="profile-pic-container">
                <img src="data:image/jpg;base64,${sessionScope.user.image}" alt="" class="profile-pic">
                <form action="/Controller" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="command" value="change_user_image"/>
                    <input type="file" name="image"/>
                    <button type="submit"> Change</button>
                </form>
            </div>
            <div class="profile-info-container">
                
                <p>UserName: ${sessionScope.user.userName}</p>
                <p>First Name: ${sessionScope.user.firstName}</p>
                <form action="/Controller" method="post">
                    <input type="hidden" name="command" value="change_user_firstname"/>
                    <input type="text" name="firstname" value="${sessionScope.user.firstName}"/>
                    <button type="submit"> Change</button>
                </form>
                
                <p>Last Name: ${sessionScope.user.lastName}</p>
                <form action="/Controller" method="post">
                    <input type="hidden" name="command" value="change_user_lastname"/>
                    <input type="text" name="lastname" value="${sessionScope.user.lastName}"/>
                    <button type="submit"> Change</button>
                </form>
                <form action="/Controller" method="post">
                    <input type="hidden" name="command" value="change_user_password"/>
                    <input type="password" name="oldpassword" placeholder="Old password"/>
                    <input type="password" name="newpassword" placeholder="New password"/>
                    <button type="submit"> Change password</button>
                    <span>${requestScope.errorMessage}</span>
                </form>
            
                
            </div>
            
            <a href="${pageContext.request.contextPath}/Controller?command=logout">logout</a>
            <a href="${pageContext.request.contextPath}/Controller?page=lotcreation&command=go_to_page">Create Lot</a>
            <a href="${pageContext.request.contextPath}/Controller?page=lotcreation&command=go_to_user_lots&currentPage=1">user Lots</a>
        </div>
        
    </div>
   
    <%@ include file="footer.jsp" %>
</body>
</html>