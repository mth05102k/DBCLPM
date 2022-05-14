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
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" rel="stylesheet"/>
        <link href="<c:url value="/css/staff/book-detail.css"/>" rel="stylesheet" />

    </head>
    <body>
        <div class="main d-flex vh-100">
            <%@include file="/jsp/staff/components/sidebar.jsp" %>
            <div class="main-content flex-fill bg-light">
                <%@include file="/jsp/staff/components/header.jsp" %>
                <div class="book-detail px-3">
                    <div class="container-fluid">
                        <div class="book-detail__header d-flex align-items-center justify-content-between mb-3 mt-4">
                            <h3 class="text-capitalize fs-4 mb-0">Chi tiết sách</h3>
                            <div class="book-edit-control d-flex">
                                <div class="book-status-control rounded-pill shadow-sm <c:if test="${book.status == false}">down</c:if>" 
                                     id="book-status-control" 
                                     data-js-status="<c:out value="${book.status}" />"
                                    >
                                    <div class="status-control-btn">
                                        <i class="fas fa-chevron-circle-up shadow rounded-circle"></i>
                                    </div>
                                </div>

                                <a class="btn btn-primary ms-3 shadow-sm" href="<c:url value="/staff/book-item/edit?id=${book.bookItemId}" />">
                                    Cập nhật thông tin trên trang bán
                                </a>
                            </div>
                        </div>
                        <div class="book-detail__form">
                            <form action="<c:url value="/staff/book/edit" />" method="POST">
                                <div class="form-body">
                                    <div class="row">
                                        <div class="col">
                                            <div class="general-info rounded shadow-sm px-3 pt-4 pb-2 bg-white">
                                                <h4 class="text-capitalize fs-5">Thông tin chung</h4>
                                                <div class="form-group">
                                                    <label for="book-isbn" class="form-label">ISBN:</label>
                                                    <input class="form-control" type="text" id="book-isbn" name="isbn" value="<c:out value="${book.IBSN}" />">
                                                    <input type="hidden" value="<c:out value="${book.id}" />" name="id" id="book-id">
                                                </div>
                                                <div class="form-group">
                                                    <label for="book-title" class="form-label">Tiêu đề:</label>
                                                    <input class="form-control" type="text" id="book-title" name="title" value="<c:out value="${book.title}" />">
                                                </div>
                                                <div class="form-group">
                                                    <label for="book-page" class="form-label">Số trang:</label>
                                                    <input class="form-control" type="number" id="book-page" name="page" min="1" value="<c:out value="${book.numberOfPage}" />">
                                                </div>
                                                <div class="form-group">
                                                    <label for="book-language" class="form-label">Ngôn ngữ:</label>
                                                    <input class="form-control" type="text" id="book-language" name="language" value="<c:out value="${book.language}" />">
                                                </div>
                                                <div class="form-group">
                                                    <label for="book-summary" class="form-label">Tóm tắt:</label>
                                                    <textarea class="form-control" id="book-summary" name="summary" rows="4"><c:out value="${book.summary}" /></textarea>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col">
                                            <div class="publish-info rounded shadow-sm px-3 pt-4 pb-2 bg-white mb-2">
                                                <h4 class="text-capitalize fs-5">Thông tin xuất bản</h4>
                                                <div class="form-group">
                                                    <label for="book-author" class="form-label">Tác giả:</label>
                                                    <div class="book-author-control" id="book-author">
                                                        <ul class="author-list list-unstyled mb-0 d-flex align-items-center flex-wrap">
                                                            <c:forEach items="${book.aut}" var="author">
                                                                <li class="author-item me-2">
                                                                    <a class="author-control text-decoration-none" 
                                                                       href="<c:url value="/staff/book/author/edit?bookid=${book.id}&authorid=${author.id}" />"
                                                                       >
                                                                        <c:out value="${author.name}" />; 
                                                                    </a>
                                                                </li>
                                                            </c:forEach>
                                                            <li class="author-item" data-bs-toggle="tooltip" data-bs-placement="top" title="Thêm tác giả">
                                                                <a class="author-control d-flex align-items-center justify-content-center text-decoration-none" 
                                                                   href="<c:url value="/staff/book/author/add?bookid=${book.id}" />"
                                                                   >
                                                                    <i class="fas fa-plus"></i>
                                                                </a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="book-publisher" class="form-label">Nhà xuất bản:</label>
                                                    <input class="form-control" type="text" id="book-publisher" name="publisher" value="<c:out value="${book.pub.name}" />">
                                                </div>
                                                <div class="form-group">
                                                    <label for="book-publish-year" class="form-label me-3">Năm xuất bản:</label>
                                                    <input type="text" class="datepicker form-control d-inline-block w-auto ps-3" 
                                                           id="book-publish-year" name="publishyear"  
                                                           value="<c:out value="${book.publicationYear}" />"
                                                           >
                                                </div>
                                            </div>

                                            <div class="storage-info rounded shadow-sm px-3 pt-4 pb-2 bg-white">
                                                <h4 class="text-capitalize fs-5">Thông tin nhập hàng</h4>
                                                <div class="form-group">
                                                    <label for="book-price" class="form-label">Giá nhập (VND):</label>
                                                    <input class="form-control" type="text" id="book-price" name="price" value="<c:out value="${book.cost}" />">
                                                </div>
                                                <div class="form-group">
                                                    <label for="book-remaining" class="form-label">Kho:</label>
                                                    <input class="form-control" type="number" id="book-remaining" name="remaining" min="0" value="<c:out value="${book.remainingQuantity}" />">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-btn text-end mt-3 mb-3">
                                    <button class="btn btn-danger me-3" data-bs-toggle="modal" data-bs-target="#delete-modal">Xóa thông tin sách</button>
                                    <button class="btn btn-primary min-w-150" type="submit">Lưu</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%@include file="/jsp/staff/components/delete-modal.jsp" %>
        <form action="<c:url value="/staff/book/delete" />" method="POST" id="delete-form" class="d-none">
            <input type="hidden" name="id" value="<c:out value="${book.id}" />">
        </form>

        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <script>
            document.getElementById('book-status-control').addEventListener('click', async function () {
                const status = !(this.dataset.jsStatus === 'true');
                const bookId = document.getElementById('book-id').value;
                this.classList.toggle('down');

                const response = await fetch('http://localhost:8080/g11/staff/book/edit/status', {
                    method: 'POST',
                    body: new URLSearchParams({
                        status,
                        id: bookId
                    })
                });

                const data = await response.text();
                if (data) {
                    const dataTokens = data.split(';');

                    if (dataTokens[0] === '201') {
                        this.dataset.jsStatus = status;

                        if (status) {
                            Swal.fire({
                                position: 'top-end',
                                icon: 'success',
                                title: 'Sản phẩm đã được đưa lên trang bán',
                                showConfirmButton: false,
                                timer: 1500
                            })
                        } else {
                            Swal.fire({
                                position: 'top-end',
                                icon: 'success',
                                title: 'Sản phẩm đã được gỡ khỏi trang bán',
                                showConfirmButton: false,
                                timer: 1500
                            })
                        }

                        return;
                    }
                }

                this.classList.toggle('down');
                Swal.fire({
                    position: 'top-end',
                    icon: 'error',
                    title: 'Có lỗi xảy ra, vui lòng thử lại sau',
                    showConfirmButton: false,
                    timer: 1500
                })
            })
        </script>

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

        <!--activate bootstrap tooltip-->
        <script>
            var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
            var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
                return new bootstrap.Tooltip(tooltipTriggerEl)
            })
        </script>

        <script>
            document.getElementById('delete-btn').addEventListener('click', () => {
                document.getElementById('delete-form').submit();
            })
        </script>
    </body>
</html>
