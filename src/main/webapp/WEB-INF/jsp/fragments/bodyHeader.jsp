<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">

        <a href="meals">
            <div class="navbar-header navbar-brand"><fmt:message key="app.title"/></div>
        </a>

        <div class="collapse navbar-collapse">
            <form class="navbar-form navbar-right">
                <a class="btn btn-info" role="button" href="users"><fmt:message key="users.title"/></a>
            </form>
        </div>
    </div>
</div>
