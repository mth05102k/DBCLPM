<%-- 
    Document   : sidebar
    Created on : Jan 3, 2022, 10:22:11 AM
    Author     : Admin
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<aside class="left-sidebar d-flex flex-column">
    <div class="sidebar-header">
        <h1 class="fs-4 fw-bolder text-white">Shopyy</h1>
    </div>
    <nav class="sidebar__nav flex-fill">
        <ul class="nav-list list-unstyled mb-0">
            <li class="nav-item text-capitalize">
                <a href="<c:url value="/staff/book"/>" class="text-decoration-none text-white">
                    <div class="icon-wrapper me-3">
                        <i class="fas fa-book"></i>
                    </div>
                    Sách
                </a>
            </li>
            <li class="nav-item text-capitalize">
                <a href="#" class="text-decoration-none text-white">
                    <div class="icon-wrapper me-3">
                        <i class="fas fa-cubes"></i>
                    </div>
                    Đơn hàng
                </a>
            </li>
            <li class="nav-item text-capitalize">
                <a href="#" class="text-decoration-none text-white">
                    <div class="icon-wrapper me-3">
                        <i class="fas fa-users"></i>
                    </div>
                    Khách hàng
                </a>
            </li>
        </ul>
    </nav>
    <div class="sidebar__auto-collapse text-white text-end">
        <i class="fas fa-chevron-left"></i>
    </div>
</aside>