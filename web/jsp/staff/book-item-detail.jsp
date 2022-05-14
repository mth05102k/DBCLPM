<%-- 
    Document   : book-detail
    Created on : Jan 3, 2022, 9:34:05 AM
    Author     : Admin
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <%@include file="/jsp/includes/stylesheet.jsp" %>
        <link href="<c:url value="/css/staff/book-item-detail.css"/>" rel="stylesheet" />

    </head>
    <body>
        <div class="main d-flex vh-100">
            <%@include file="/jsp/staff/components/sidebar.jsp" %>
            <div class="main-content flex-fill bg-light">
                <%@include file="/jsp/staff/components/header.jsp" %>
                <div class="book-detail px-3">
                    <div class="container-fluid">
                        <div class="book-detail__header d-flex align-items-center justify-content-between mb-3 mt-4">
                            <h3 class="text-capitalize fs-4 mb-0">Cập nhật trên trang bán</h3>
                        </div>
                        <div class="book-detail__form">
                            <form action="<c:url value="/staff/book-item/edit" />" method="POST">
                                <div class="form-body rounded shadow-sm p-4 bg-white">
                                    <h4 class="text-capitalize fs-5">Thông tin chung</h4>
                                    <input type="hidden" value="<c:out value="${book.ID}" />" name="id">
                                    <div class="form-group">
                                        <label for="book-name" class="form-label">Tiêu đề</label>
                                        <input class="form-control" type="text" id="book-name" name="name" value="<c:out value="${book.name}" />">
                                    </div>
                                    <div class="form-group">
                                        <label for="book-image" class="form-label">Ảnh minh họa</label>
                                        <input class="form-control" type="text" id="book-image" name="image" value="<c:out value="${book.image}" />">
                                    </div>
                                    <div class="form-group">
                                        <label for="book-category" class="form-label">Phân loại</label>
                                        <input class="form-control" type="text" id="book-category" name="category" value="<c:out value="${book.category}" />">
                                    </div>
                                    <div class="form-group">
                                        <label for="book-price" class="form-label">Giá bán (VND)</label>
                                        <input class="form-control" type="number" id="book-price" name="price" value="<c:out value="${book.price}" />">
                                    </div>
                                    <div class="form-group">
                                        <label for="book-discount" class="form-label">Khuyến mãi (%)</label>
                                        <input class="form-control" type="number" id="book-discount" name="discount" value="<c:out value="${book.discount}" />">
                                    </div>
                                    <div class="form-group">
                                        <label for="book-description" class="form-label">Tóm tắt</label>
                                        <textarea class="form-control" id="book-description" name="description" rows="5"><c:out value="${book.description}" /></textarea>
                                    </div>
                                </div>
                                <div class="form-btn text-end mt-3 mb-3">
                                    <button class="btn btn-primary min-w-150" type="submit">Lưu</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        
    </body>
</html>
