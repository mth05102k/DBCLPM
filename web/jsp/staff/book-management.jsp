<%-- 
    Document   : book-management
    Created on : Jan 2, 2022, 10:08:22 AM
    Author     : Admin
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shopyy</title>

        <%@include file="/jsp/includes/stylesheet.jsp" %>
        <link href="<c:url value="/css/staff/book-management.css"/>" rel="stylesheet" />

    </head>
    <body class="">
        <div class="main d-flex vh-100">

            <%@include file="/jsp/staff/components/sidebar.jsp" %>

            <div class="main-content flex-fill bg-light">

                <%@include file="/jsp/staff/components/header.jsp" %>

                <div class="book-information">
                    <div class="book-information__header pt-4 px-3">
                        <h3 class="text-capitalize fs-4 pb-3 mb-0">Thông tin sách</h3>
                        <div class="book-information__functionality d-flex align-items-center justify-content-between fw-500">
                            <div class="functionality__sort">
                                <span class="text-capitalize text-muted">Sắp xếp theo</span>
                                <select class="form-select-sm custom-form-select bg-light fw-500">
                                    <option value="">Không</option>
                                </select>
                            </div>
                            <div class="functionality__pagination text-muted d-flex align-items-center">
                                <div class="current-number me-3">
                                    <span>Hiển thị</span>
                                    <span class="text-black">1 - 69</span>
                                    <span>trên <c:out value="${bookQuantity}" /></span>
                                </div>
                                <div class="pagination__control d-flex align-items-center">
                                    <div class="control__left me-2">
                                        <i class="fas fa-chevron-left"></i>
                                    </div>
                                    <div class="control__right">
                                        <i class="fas fa-chevron-right"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="book-information__table mt-3">
                        <table class="table table-white table-borderless overflow-scroll">
                            <thead class="fs-7">
                                <tr class="text-uppercase text-muted">
                                    <th scope="col" class="ps-3 fw-500">ISBN</th>
                                    <th scope="col" class="fw-500">Tiêu đề</th>
                                    <th scope="col" class="fw-500">Năm xuất bản</th>
                                    <th scope="col" class="fw-500">Kho</th>
                                    <th scope="col" class="fw-500">Trạng thái</th>
                                    <th scope="col" class="pe-3 fw-500"></th>
                                </tr>
                            </thead>
                            <tbody class="fw-500 overflow-scroll">
                                <c:forEach items="${listBook}" var="book">
                                    <tr data-js-href="http://localhost:8080/g11/staff/book/edit?id=<c:out value="${book.id}" />" 
                                        class="align-middle" 
                                        >
                                        <td class="ps-3 py-3"><c:out value="${book.IBSN}" /></td>
                                        <td class="py-3"><c:out value="${book.title}" /></td>
                                        <td class="py-3"><c:out value="${book.publicationYear}" /></td>
                                        <td class="py-3"><c:out value="${book.remainingQuantity}" /></td>
                                        <td class="py-3">
                                            <c:choose>
                                                <c:when test="${book.status == true}">
                                                    Đã lên trang bán
                                                </c:when>
                                                <c:otherwise>
                                                    Chưa lên trang bán
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="pe-3 py-3 unclickable">
                                            <div class="text-muted d-flex align-items-center justify-content-end">
                                                <div class="row-control px-2 book-edit-control"
                                                     data-js-href="http://localhost:8080/g11/staff/book/edit?id=<c:out value="${book.id}" />"
                                                     >
                                                    <i class="fas fa-pen-fancy"></i>
                                                </div>
                                                <div class="gutter"></div>
                                                <div class="row-control px-2 book-delete-control" data-js-bookid="<c:out value="${book.id}" />">
                                                    <i class="fas fa-trash-alt"></i>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <script>
            document.querySelectorAll('.book-information__table tbody tr').forEach((tableRow) => {
                tableRow.addEventListener('click', (e) => {
                    window.location.href = tableRow.dataset.jsHref;
                });
            })

            document.querySelectorAll('.book-information__table tbody tr .unclickable').forEach((tableRow) => {
                tableRow.addEventListener('click', (e) => {
                    e.stopPropagation();
                })
            })

            document.querySelectorAll('.book-edit-control').forEach((editControl) => {
                editControl.addEventListener('click', function (e) {
                    window.location.href = this.dataset.jsHref;
                })
            })

            document.querySelectorAll('.book-delete-control').forEach((deleteControl) => {
                deleteControl.addEventListener('click', async function (e) {
                    const id = this.dataset.jsBookid;
                    const response = await fetch('http://localhost:8080/g11/staff/book/delete', {
                        method: 'POST',
                        body: new URLSearchParams({
                            id
                        })
                    });

                    if (response.status === 200 && response.redirected === true) {
                        const tableRow = this.closest('tr')
                        if (tableRow) {
                            tableRow.remove();
                        }
                    }
                })
            })
        </script>
    </body>
</html>
