<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    
</head>
<body>
    
    <%@ include file="header.jsp" %>

    <div>

        <form action="Controller" method="post" enctype="multipart/form-data">
            <input type="text" name="title" placeholder="Title"/>
            <input type="number" step="0.01" min="0" name="price" placeholder="Price" value="1"/>
            <input type="hidden" name="command" value="create_lot"/>

            <br>
            <input type="text" name="description" placeholder="description"/>
            <input type="file" name="image"/>
            <select name="tag_list" id="tag_select">
                <option value="householdproducts">Household products</option>
                <option value="electronics">Electronics</option>
                <option value="petsupplies">Pet supplies</option>
                <option value="furniture">Furniture</option>
                <option value="clothing">Clothing</option>
                <option value="goods">Kitchen furniture</option>
            </select>
            <button type="submit" >Save Lot</button>

        </form>

        ${requestScope.errorMessage}

    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>