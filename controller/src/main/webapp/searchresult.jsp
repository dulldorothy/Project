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
                            <div class="product_image">
                                <img class="product_image" src="data:image/jpg;base64,${Lot.image}">
                           
                            </div>
                            <div class="desc_part">
                               <a href="${pageContext.request.contextPath}/Controller?lot_id=${Lot.id}&command=go_to_lot_page"> ${Lot.title}</a>
                                <h2>${Lot.price} $</h2>
                                <h2>${Lot.id} id</h2> 
                            </div>
                        </div>
    </c:forEach>

    
    <div class="pagination">
        <div class="pag_body">
            <c:if test="${param.currentPage > 1}">
                <a href="${pageContext.request.contextPath}/Controller?page=searchresult&command=go_to_full_catalog&currentPage=${param.currentPage - 1}">
                    <div class="prev">
                        <svg xmlns="http://www.w3.org/2000/svg" width="7" height="12"
                             viewBox="0 0 7 12">
                            <path fill="#CDCDCD" fill-rule="nonzero"
                                  d="M3.137 6L7 10.5 5.5 12 0 6l5.5-6L7 1.5z"/>
                        </svg>
                    </div>
                </a>
            </c:if>

            <c:forEach begin="1" end="${requestScope.numberOfPages}" var="i">
                <c:choose>
                    <c:when test="${param.currentPage eq i}">
                        <div class="page_number _active">${i}</div>
                    </c:when>
                    <c:when test="${i > (param.currentPage - 3) && i < param.currentPage + 3}">
                        <div class="page_number">
                            <a href="${pageContext.request.contextPath}/Controller?page=searchresult&command=go_to_full_catalog&currentPage=${i}">
                                    ${i}
                            </a>
                        </div>
                    </c:when>
                </c:choose>
            </c:forEach>
            <c:if test="${param.currentPage lt requestScope.numberOfPages}">
                <a href="${pageContext.request.contextPath}/Controller?page=searchresult&command=go_to_full_catalog&currentPage=${param.currentPage + 1}">
                    <div class="next">
                        <svg xmlns="http://www.w3.org/2000/svg" width="7" height="12"
                             viewBox="0 0 7 12">
                            <path fill="#CDCDCD" fill-rule="nonzero"
                                  d="M3.863 6L0 1.5 1.5 0 7 6l-5.5 6L0 10.5z"/>
                        </svg>
                    </div>
                </a>
            </c:if>

        </div>
    <%@ include file="footer.jsp" %>
</body>
</html>