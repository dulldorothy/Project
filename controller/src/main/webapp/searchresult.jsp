<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
    <c:forEach var="Lot" items="${requestScope.Lots}">
                        <div class="product_item">
                            <div class="desc_part">
                               <a href="#"> ${Lot.title}</a>
                                <h2>${Lot.price} $</h2> 
                            </div>
                        </div>
                    </c:forEach>
    <%@ include file="footer.jsp" %>
</body>
</html>