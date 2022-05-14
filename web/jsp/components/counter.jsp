<%-- 
    Document   : counter
    Created on : Nov 15, 2021, 11:58:37 AM
    Author     : Admin
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="item-info__quantity-select <c:out value="${param.className}"/>">
    <div class="number item-counter" class="" id="<c:out value="${param.id}"/>">
        <span class="minus">-</span>
        <input type="text" value="<c:out value="${param.quantity}"/>"/>
        <span class="plus">+</span>
    </div>
</div>