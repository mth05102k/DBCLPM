<%-- 
    Document   : cart
    Created on : Nov 13, 2021, 9:52:15 AM
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
        <link href="${pageContext.request.contextPath}/css/counter.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" />

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body class="bg-light">

        <%@include file="/jsp/components/header.jsp" %>

        <!-- Cart -->
        <div class="container my-5 p-3">
            <div class="ps-3">
                <h3 class="text-primary">Giỏ hàng của tôi</h3>
            </div>
            <hr>
            <div class="row mt-5 p-0">
                <div>
                    <table class="table table-borderless shadow-sm bg-white">
                        <thead style="background-color: rgb(245, 245, 245);">
                            <tr>
                                <th class="text-center"><input type="checkbox" name="" id="select-all-checkbox" class="form-check-input select-item"></th>
                                <th>Sản phẩm</th>
                                <th>Đơn Giá</th>
                                <th>Số lượng</th>
                                <th>Số tiền</th>
                                <th style="width: 100px;" class="text-center">Thao Tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listItem}" var="item" varStatus="status">
                                <tr class="align-content-lg-between center item" itemId="<c:out value="${item.ID}"/>">
                                    <td class="text-center"><input type="checkbox" name="" id="" class="form-check-input item-selector"></td>
                                    <td>
                                        <a href="<c:url value="/product/${item.ID}" />" class="item-general-info">
                                            <div class="item-img">
                                                <img src="<c:out value="${item.image}"/>" alt="item image">
                                            </div>
                                            <div class="item-name text-capitalize">
                                                ${item.name}
                                            </div>                               
                                        </a>
                                    </td>
                                    <td>
                                        <div class="item-unit-price">
                                            <input type="hidden" value="${item.price - item.price*(item.discount/100)}" id="item-unit-price-${item.ID}">
                                            <div id="item-unit-price-display">
                                                <custom:currencyFormat amount="${item.price - item.price*(item.discount/100)}" currencyFormat="vi" />
                                            </div>
                                        </div>
                                    </td>
                                    <td>                                    
                                        <jsp:include page="components/counter.jsp">
                                            <jsp:param name="id" value="item-counter-${item.ID}"/>
                                            <jsp:param name="quantity" value="${listQuantity[status.index]}" />
                                        </jsp:include>
                                    </td>
                                    <td class="item-total-price">
                                        <input type="hidden" value="${(item.price - item.price*(item.discount/100))*listQuantity[status.index]}" class="item-total-price__input">
                                        <div id="item-total-price-display">
                                            <custom:currencyFormat amount="${(item.price - item.price*(item.discount/100))*listQuantity[status.index]}" currencyFormat="vi" />
                                        </div>
                                    </td>
                                    <td class="text-center"><i class="bi-trash-fill text-danger delete-item-btn" for="<c:out value="${item.ID}"/>"></i></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class=" d-flex flex-column">
                <div class="total-bill">
                    <div class="total-bill__title">Tổng thanh toán:</div>
                    <div class="total-bill__cost ms-2" id="bill-container">
                        <input type="hidden" value="0" class="item-total-bill__input">
                        <custom:currencyFormat amount="0" currencyFormat="vi" />
                    </div>
                </div>
                <button class="btn btn-primary mt-3" id="pay-now">Thanh toán</button>
            </div>
        </div>
                    
        <!-- Footer -->
        <%@include file="/jsp/components/footer.jsp" %>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/cart.js"></script>
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    </body>
</html>
