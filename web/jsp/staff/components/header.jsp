<%-- 
    Document   : header
    Created on : Jan 3, 2022, 10:28:31 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="header p-2 bg-white shadow-sm">
    <div class="container-fluid d-flex align-items-center justify-content-between">
        <div class="header-left d-flex text-muted">
            <div class="collapse-control me-4 header-item">
                <i class="fas fa-bars"></i>
            </div>
            <div class="header-nav">
                <ul class="list-unstyled mb-0 d-flex text-capitalize">
                    <li><a href="" class="text-decoration-none text-muted header-item"></a></li>
                    <li><a href="" class="text-decoration-none text-muted header-item"></a></li>
                    <li><a href="" class="text-decoration-none text-muted header-item">Cài đặt</a></li>
                </ul>
            </div>
        </div>
        <div class="header-right d-flex align-items-center">
            <div class="theme d-flex me-4">
                <div class="theme-control theme-control--light shadow-sm rounded-start active">
                    <i class="far fa-sun"></i>
                </div>
                <div class="theme-control theme-control--dark shadow-sm rounded-end">
                    <i class="far fa-moon"></i>
                </div>
            </div>
            <div class="header__menu">
                <ul class="list-unstyled mb-0 d-flex fs-4 text-secondary">
                    <li class="menu-item header-item"><i class="far fa-bell"></i></li>
                    <li class="menu-item header-item"><i class="fas fa-tasks"></i></li>
                    <li class="menu-item header-item"><i class="far fa-envelope"></i></li>
                </ul>
            </div>
            <div class="user position-relative">
                <div class="user__avatar">
                    <img class="rounded-circle" src="${sessionScope.avatar}" alt="User avatar"/>
                </div>
                <div class="user-control position-absolute d-none shadow bg-white rounded">
                    <ul class="list-unstyled mb-0" >
                        <li><a href="${pageContext.request.contextPath}/user/account/profile" class="text-decoration-none text-capitalize">Tài khoản của tôi</a></li>
                        <li><a href="${pageContext.request.contextPath}/auth/logout" class="text-decoration-none text-capitalize">Đăng xuất</a></li>
                    </ul>
                </div>     
            </div>
        </div>
    </div>
</header>
