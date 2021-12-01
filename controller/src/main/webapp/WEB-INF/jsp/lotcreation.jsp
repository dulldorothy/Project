<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    
</head>
<body>
    
    <%@ include file="header.jsp" %>

    <div>

        <form action="Controller" method="post" enctype="multipart/form-data">
            <input type="text" name="title" placeholder="Title"/>
            <input type="number" name="price" placeholder="Price"/>
            <input type="hidden" name="command" value="create_lot"/>
            <input type="radio" name="type" id="auction" value="auction"/>
            <label for="auction"> Auction </label>
            <input type="radio" name="type" value="marketLot"/>
            <label for="marketLot"> "Buy now" lot </label>
            <br>
            <input type="text" name="description" placeholder="description"/>
            <input type="file" name="image"/>
            <select name="tag_list" id="tag_select">
                <option value="">--Please choose an option--</option>
                <option value="householdproducts">Household products</option>
                <option value="electronics">Electronics</option>
                <option value="petsupplies">Pet supplies</option>
                <option value="furniture">Furniture</option>
                <option value="clothing">Clothing</option>
                <option value="goods">Food and drinks</option>
            </select>
            <button type="submit" >Save Lot</button>

        </form>

        ${requestScope.errorMessage}

    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>