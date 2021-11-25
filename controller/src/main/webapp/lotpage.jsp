<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/style.css">
    
</head>
<body>
    
    <%@ include file="header.jsp" %>
    <h1>${requestScope.Lot.title}</h1>
    <h2>${requestScope.Lot.price}</h2>
    <div class="product_image">
        <img class="product_image" src="data:image/jpg;base64,${requestScope.Lot.image}">
        
    </div>
    <form method="get" action="Controller">
        <input type="hidden" name="command" value="add_to_bookmark" >
        <input type="hidden" name="lot_id" value="${requestScope.Lot.id}" >
        <button type="submit"> add to bookmarks </button>
    </form>
    <p>${requestScope.Lot.description}</p>
    <%@ include file="footer.jsp" %>
</body>
</html>