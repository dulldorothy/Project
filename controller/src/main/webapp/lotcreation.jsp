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

    <div>

        <form action="Controller" method="post">
            <input type="text" name="title" placeholder="Title"/>
            <input type="number" name="price" placeholder="Price"/>
            <input type="hidden" name="command" value="addLot">
            <button type="submit">Save Lot</button>

        </form>



    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>