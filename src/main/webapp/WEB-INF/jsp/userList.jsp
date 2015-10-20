<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>

<html>
<dandelion:bundle includes="topjavaDatatable"/>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><fmt:message key="users.title"/></h3>

            <div class="view-box">
                <a class="btn btn-sm btn-info" id="add">Add User</a>

                <datatables:table id="datatable" data="${userList}" row="user" theme="bootstrap3"
                                  cssClass="table table-striped" pageable="false" info="false">
                    <datatables:column title="Name" property="name"/>
                    <datatables:column title="Email">
                        <a href="<c:url value="mailto:${user.email}"/>">${user.email}</a>
                    </datatables:column>
                    <datatables:column title="Roles" property="roles"/>
                    <datatables:column title="Active">
                        <input type="checkbox"
                               <c:if test="${user.enabled}">checked</c:if> id="${user.id}"/>
                    </datatables:column>
                    <datatables:column title="Registered">
                        <fmt:formatDate value="${user.registered}" pattern="dd-MMMM-yyyy"/>
                    </datatables:column>
                    <datatables:column filterable="false" sortable="false">
                        <a class="btn btn-xs btn-danger delete" id="${user.id}">Delete</a>
                    </datatables:column>
                </datatables:table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>