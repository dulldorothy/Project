<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="prop.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="messages.unread.messages" var="unread"/>
    <fmt:message bundle="${loc}" key="messages.all.messages" var="all"/>
    <fmt:message bundle="${loc}" key="messages.mark.read" var="read"/>
</head>
<body>

    <%@ include file="header.jsp" %>

    <c:choose>
        <c:when  test="${param.command == 'go_to_unread_messages'}">
            <c:out value="${unread}"/>
            <a href="${pageContext.request.contextPath}/Controller?page=messages&command=go_to_all_messages&currentPage=1"><c:out value="${all}"/></a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/Controller?page=messages&command=go_to_unread_messages&currentPage=1"><c:out value="${unread}"/></a>
            <c:out value="${all}"/>
        </c:otherwise>
    </c:choose>
    <c:forEach var="Message" items="${requestScope.Messages}">
                        <div class="product_item">
                            <div class="desc_part">
                                <h2>${Message.email}</h2>
                                <h2>${Message.lotName} </h2> 
                                <h2>${Message.buyerName} </h2> 
                                ${Message.id}
                                
                                <c:choose>                               <c:when  test="${param.command == 'go_to_unread_messages'}">
                                    <form action="/Controller" method="post">
                                        <input type="hidden" name="command" value="change_message_read_status"/>
                                        <input type="hidden" name="messageid" value="${Message.id}"/>
                                        <button type="submit"><c:out value="${read}"/></button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                 </c:otherwise>
                                </c:choose>
 
                            </div>
                            
                        </div>

    </c:forEach>
   

    <div class="pagination">
        <div class="pag_body">
            <c:if test="${param.currentPage > 1}">
                <a href="${pageContext.request.contextPath}/Controller?page=messages&command=${param.command}&currentPage=${param.currentPage - 1}">
                   
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
                            <a href="${pageContext.request.contextPath}/Controller?page=messages&command=${param.command}&currentPage=${i}">
                                    ${i}
                            </a>
                        </div>
                    </c:when>
                </c:choose>
            </c:forEach>
            <c:if test="${param.currentPage lt requestScope.numberOfPages}">
                <a href="${pageContext.request.contextPath}/Controller?page=messages&command=${param.command}&currentPage=${param.currentPage + 1}">
                    
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