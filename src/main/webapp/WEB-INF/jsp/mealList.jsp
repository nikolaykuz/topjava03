<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Description</th>
            <th>Date and time</th>
            <th>Calories</th>
        </tr>
        </thead>
        <c:forEach items="${mealList}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.UserMeal"/>

            <tr>
                <td><c:out value="${meal.description}"/></td>
                <td>${meal.dateTime}</td>
                <td>${meal.calories}</td>
                <%--<td>
                    <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd HH:mm" var="parsedDate" type="date" />
                    <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd HH:mm" type="date"/>
                </td>--%>
            </tr>
        </c:forEach>
    </table>
</section>
<hr>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
