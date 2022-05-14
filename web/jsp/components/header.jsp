<%-- 
    Document   : header
    Created on : Nov 13, 2021, 12:04:44 PM
    Author     : Admin
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Shopyy</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="nav navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/product">Các mặt hàng</a></li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">Hot</a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">Hàng HOT</a></li>
                        <li><a class="dropdown-item" href="#!">Hàng mới</a></li>
                    </ul>
                </li>
            </ul>
            <div class="search me-5 position-relative" style="width: 35%;">
                <input type="text" placeholder="Tìm kiếm mặt hàng" class="form-control pe-5" id="search-input">
                <i class="fas fa-search position-absolute" id="search-control-btn"></i>
            </div>
            <div class="cart fs-3">
                <a href="${pageContext.request.contextPath}/cart">
                    <i class="bi-cart-fill me-1 text-white"></i>
                </a>
            </div>
            <div class="user position-relative">
                <a href="${pageContext.request.contextPath}/user" class="d-block">
                    <i class="far fa-user-circle text-white"></i>
                </a>
                <div class="user-control position-absolute d-flex flex-column shadow-sm">
                    <c:choose>
                        
                        <c:when test="${sessionScope.userId == null}">
                            <a href="" class="text-decoration-none" data-bs-toggle="modal" data-bs-target="#register-modal" >Đăng ký</a>
                            <a href="" class="text-decoration-none" data-bs-toggle="modal" data-bs-target="#login-modal">Đăng nhập</a>
                        </c:when>
                            
                        <c:otherwise>
                            <ul class="list-unstyled">
                                <li><a href="${pageContext.request.contextPath}/user/account/profile" class="text-decoration-none text-capitalize">Tài khoản của tôi</a></li>
                                <li><a href="${pageContext.request.contextPath}/customer/order" class="text-decoration-none text-capitalize">Đơn mua</a></li>
                                <li><a href="${pageContext.request.contextPath}/auth/logout" class="text-decoration-none text-capitalize">Đăng xuất</a></li>
                            </ul>
                        </c:otherwise>
                            
                    </c:choose>

                </div>     
            </div>
        </div>
    </div>

</nav>
