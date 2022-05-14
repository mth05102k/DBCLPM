<%-- 
    Document   : user
    Created on : Nov 16, 2021, 1:04:50 PM
    Author     : Admin
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "custom" uri = "../WEB-INF/custom-tag.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shopyy</title>

        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link href="${pageContext.request.contextPath}/css/variables.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/auth-modals.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/item.css" rel="stylesheet" />

        <link href="${pageContext.request.contextPath}/css/user.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/customer-order.css" rel="stylesheet" />

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body class="bg-light">
        <jsp:include page="components/header.jsp"></jsp:include>
            <div class="container px-5 mt-5">
                <div class="row">
                    <div class="col-2">
                        <div class="user__general-info">
                            <img class="user__avatar rounded-circle" src="https://cf.shopee.vn/file/1d74524ec09542f944ad95a2a6fd111d_tn" alt="user avatar">
                            <div class="d-flex flex-column ms-3">
                                <div class="user__name mb-1"><c:out value="${user.account.username}" /></div>
                            <div class="user__edit-profile-ctrl text-capitalize text-muted">
                                <i class="fas fa-pen"></i>
                                Sửa hồ sơ
                            </div>
                        </div>
                    </div>

                    <hr class="text-muted"/>

                    <div class="user__nav">
                        <ul class="nav-list list-unstyled">
                            <li class="nav-item">
                                <a href="" class="d-flex align-items-center">
                                    <img class="img-fluid me-2" src="https://cf.shopee.vn/file/84feaa363ce325071c0a66d3c9a88748">
                                    <span>
                                        Ưu đãi dành riêng cho bạn
                                    </span>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/user/account/profile"  class="d-flex align-items-center">
                                    <i class="far fa-user me-2 text-primary"></i>
                                    <span>
                                        Tài khoản của tôi
                                    </span>
                                </a>
                            </li>
                            <li class="nav-item active">
                                <a href="${pageContext.request.contextPath}/user/order">
                                    <i class="fas fa-clipboard-list me-2"></i>
                                    Đơn hàng
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-10">
                    <div class="user-order">
                        <div class="bg-white shadow-sm rounded">
                            <ul class="list-unstyled row order-list-nav mb-0 g-0">
                                <li class="order-list-nav__link col-3 py-3"><a href="order">Tất cả</a></li>
                                <li class="order-list-nav__link col-3 py-3"><a href="order?status=0">Chờ xác nhận</a></li>
                                <li class="order-list-nav__link col-3 py-3"><a href="order?status=2">Đang giao</a></li>
                                <li class="order-list-nav__link col-3 py-3"><a href="order?status=3">Đã giao</a></li>
                            </ul>
                        </div>
                        <div class="order-list-wrapper mt-3">
                            <ul class="order-list list-unstyled">
                                <c:forEach items="${orderList}" var="order" varStatus="status">
                                    <c:set var="totalBill" scope="page" value="${0}" />
                                    <li class="order-item">
                                        <div class="order p-4 pb-3 bg-white shadow-sm rounded">
                                            <div class="order__header d-flex align-items-center justify-content-between fs-6">
                                                <div class="order__created-date d-flex align-items-center">
                                                    <span class="me-1">Ngày tạo:</span>
                                                    <span><a href="#" class="text-decoration-none text-primary"><c:out value="${order.createdDate}"/></a></span>
                                                </div>
                                                <div class="order__status d-flex align-items-center">
                                                    <a href="" class="text-success text-decoration-none">
                                                        <i class="fas fa-truck"></i>
                                                        <c:choose>
                                                            <c:when test="${order.status == 0}">
                                                                <span>Đang chờ xác nhận</span>
                                                            </c:when>
                                                            <c:when test="${order.status == 1}">
                                                                <span>Đang chuẩn bị hàng</span>
                                                            </c:when>
                                                            <c:when test="${order.status == 2}">
                                                                <span>Đang vận chuyển</span>
                                                            </c:when>
                                                            <c:when test="${order.status == 3}">
                                                                <span>Giao hàng thành công</span>
                                                            </c:when>
                                                            <c:when test="${order.status == 4}">
                                                                <span>Giao hàng thất bại</span>
                                                            </c:when>
                                                            <c:when test="${order.status == 5}">
                                                                <span>Đang trả hàng</span>
                                                            </c:when>
                                                            <c:when test="${order.status == 6}">
                                                                <span>Trả hàng thành công</span>
                                                            </c:when>
                                                        </c:choose>
                                                    </a>
                                                    <i class="far fa-question-circle ms-2" 
                                                       data-bs-toggle="tooltip" 
                                                       data-bs-placement="bottom" 
                                                       title="Cập Nhật Mới Nhất &#013;&#010; <c:out value="${order.createdDate}" />"
                                                       ></i>
                                                </div>
                                            </div>
                                            <hr class="text-muted"/>
                                            <div class="order__body mt-3">
                                                <a href="" class="d-block text-decoration-none text-black">
                                                    <ul class="order__item-list list-unstyled">
                                                        <c:forEach items="${listBookItem[status.index]}" var="bookItemAndQuantity">
                                                            <li class="order__item d-flex align-items-center">
                                                                <div class="order__item-img me-3">
                                                                    <img src="<c:out value="${bookItemAndQuantity.key.image}" />" alt=""/>
                                                                </div>
                                                                <div class="order__item-info me-3">
                                                                    <div class="order__item-name mb-1 fs-6">
                                                                        <c:out value="${bookItemAndQuantity.key.name}"/>
                                                                    </div>
                                                                    <div class="order__item-multiply fs-7">x<c:out value="${bookItemAndQuantity.value}" /></div>
                                                                </div>
                                                                <div class="order__item-price d-flex ms-auto fs-7">
                                                                    <c:choose>
                                                                        <c:when test="${bookItemAndQuantity.key.discount > 0}">
                                                                            <c:set 
                                                                                var="totalBill" 
                                                                                value="${
                                                                                totalBill + (
                                                                                    bookItemAndQuantity.key.price 
                                                                                    - bookItemAndQuantity.key.price*(bookItemAndQuantity.key.discount/100))*bookItemAndQuantity.value
                                                                                }" 
                                                                                />
                                                                            <span class="order__item-price--old text-decoration-line-through text-muted opacity-50">
                                                                                <custom:currencyFormat 
                                                                                    amount="${bookItemAndQuantity.key.price}"
                                                                                    currencyFormat="vi"
                                                                                    />
                                                                            </span>
                                                                            <span class="order__item-price--new text-primary ms-1">
                                                                                <custom:currencyFormat 
                                                                                    amount="${bookItemAndQuantity.key.price - bookItemAndQuantity.key.price*(bookItemAndQuantity.key.discount/100)}"
                                                                                    currencyFormat="vi"
                                                                                    />
                                                                            </span>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <c:set 
                                                                                var="totalBill"  
                                                                                value="${totalBill + (bookItemAndQuantity.key.price)*bookItemAndQuantity.value}" 
                                                                                />
                                                                            <span class="order__item-price--new text-primary ms-1">
                                                                                <custom:currencyFormat 
                                                                                    amount="${bookItemAndQuantity.key.price}"
                                                                                    currencyFormat="vi"
                                                                                    />
                                                                            </span>
                                                                        </c:otherwise>
                                                                    </c:choose>

                                                                </div>
                                                            </li>
                                                        </c:forEach>
                                                    </ul>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="order-post-purchase-control p-4 pb-3 shadow-sm rounded">
                                            <div class="order-total-bill fs-7 text-end mb-4">
                                                <span>Tổng số tiền:</span>
                                                <span class="ms-3 fs-4 text-primary">
                                                    <custom:currencyFormat 
                                                        amount="${totalBill}"
                                                        currencyFormat="vi"
                                                        />
                                                </span>
                                            </div>
                                            <div class="order-control text-end">
                                                <button type="button" class="btn btn-primary me-2 min-w-150">Mua lại</button>
                                                <a href="order/<c:out value="${order.id}" />" class="btn btn-outline-white min-w-150">
                                                    Chi Tiết Đơn Hàng
                                                </a>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="components/footer.jsp"></jsp:include>

        <!--activate bs tooltip-->
        <script>
            var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
            var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
                return new bootstrap.Tooltip(tooltipTriggerEl)
            })
        </script>
        <script>
            document.querySelectorAll('.order-list-nav__link a').forEach((linkEle) => {
                if(linkEle.href === window.location.href) {
                    linkEle.parentElement.classList.add('active');
                }
            })
        </script>
    </body>
</html>
