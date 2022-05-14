<%-- 
    Document   : author-detail
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
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" rel="stylesheet"/>

    </head>
    <body>
        <div class="main d-flex vh-100">
            <%@include file="/jsp/staff/components/sidebar.jsp" %>
            <div class="main-content flex-fill bg-light">
                <%@include file="/jsp/staff/components/header.jsp" %>
                <div class="author-detail px-3">
                    <div class="container-fluid">
                        <div class="author-detail__header d-flex align-items-center justify-content-between mb-3 mt-4">
                            <h3 class="text-capitalize fs-4 mb-0">Chi tiết tác giả</h3>
                            <div class="author-edit-control d-flex">
                                <button class="btn btn-danger shadow-sm" data-bs-toggle="modal" data-bs-target="#delete-modal">Xóa thông tin tác giả</button>
                                <a class="btn btn-secondary shadow-sm ms-2" href="<c:url value="/staff/book/edit?id=${bookId}" />">Quay lại</a>
                            </div>
                        </div>
                        <div class="author-detail__form">
                            <c:choose>
                                <c:when test="${action == 'edit'}">
                                    <form action="<c:url value="/staff/book/author/edit" />" method="POST">
                                        <input type="hidden" name="authorid" id="author-id" value="<c:out value="${author.id}" />">
                                        <input type="hidden" name="bookid" id="book-id" value="<c:out value="${bookId}" />">
                                        <div class="form-body rounded shadow-sm p-4 bg-white">
                                            <h4 class="text-capitalize fs-5 mb-4">Thông tin tác giả</h4>
                                            <div class="form-group mb-3">
                                                <label for="author-name" class="form-label required">Họ và tên:</label>
                                                <input class="form-control" type="text" id="author-name" name="name" value="<c:out value="${author.name}" />" required>
                                            </div>
                                            <div class="form-group mb-3">
                                                <label for="author-biography" class="form-label">Tiểu sử:</label>
                                                <input class="form-control" type="text" id="author-biography" name="biography" value="<c:out value="${author.biography}" />">
                                            </div>
                                            <div class="form-group mb-3">
                                                <label for="author-email" class="form-label">Email:</label>
                                                <input class="form-control" type="email" id="author-email" name="email" value="<c:out value="${author.email}" />">
                                            </div>
                                            <div class="form-group mb-3">
                                                <label for="author-address" class="form-label">Địa chỉ:</label>
                                                <input class="form-control" type="text" id="author-address" name="address" value="<c:out value="${author.address}" />">
                                            </div>
                                        </div>
                                        <div class="form-btn text-end mt-3 mb-3">
                                            <button class="btn btn-primary min-w-150" type="submit">Lưu</button>
                                        </div>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form action="<c:url value="/staff/book/author/add" />" method="POST">
                                        <input type="hidden" name="bookid" id="book-id" value="<c:out value="${bookId}" />">
                                        <div class="form-body rounded shadow-sm p-4 bg-white">
                                            <h4 class="text-capitalize fs-5 mb-4">Thông tin tác giả</h4>
                                            <div class="form-group mb-3">
                                                <label for="author-name" class="form-label required">Họ và tên:</label>
                                                <input class="form-control" type="text" id="author-name" name="name" required>
                                            </div>
                                            <div class="form-group mb-3">
                                                <label for="author-biography" class="form-label">Tiểu sử:</label>
                                                <input class="form-control" type="text" id="author-biography" name="biography">
                                            </div>
                                            <div class="form-group mb-3">
                                                <label for="author-email" class="form-label">Email:</label>
                                                <input class="form-control" type="email" id="author-email" name="email">
                                            </div>
                                            <div class="form-group mb-3">
                                                <label for="author-address" class="form-label">Địa chỉ:</label>
                                                <input class="form-control" type="text" id="author-address" name="address">
                                            </div>
                                        </div>
                                        <div class="form-btn text-end mt-3 mb-3">
                                            <button class="btn btn-primary min-w-150" type="submit">Lưu</button>
                                        </div>
                                    </form>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--delete modal-->                    
        <%@include file="/jsp/staff/components/delete-modal.jsp" %>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>

        <!--render bootstrap year picker-->
        <script>
            $('body').on('focus', ".datepicker", function () {
                if ($(this).hasClass('hasDatepicker') === false) {
                    $(this).datepicker({
                        minViewMode: 2,
                        format: 'yyyy'
                    });
                }
            });
        </script>

        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="<c:url value="/js/staff/author-detail.js" />"></script>

    </body>
</html>
