
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "custom" uri = "../../WEB-INF/custom-tag.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="item-detail bg-white mx-5 shadow-sm" itemid="${bookItem.ID}" id="item-detail">
    <div class="row py-3 ps-3 pe-4">
        <div class="col-4">
            <div class="item-images">
                <img class="img-fluid" src="${bookItem.image}">
            </div>
        </div>
        <div class="col-8">
            <div class="item-info">
                <h3 class="item-info__title fw-normal text-capitalize">${bookItem.name}</h3>
                <div class="item-info__price bg-light p-3 fs-3 text-primary mt-4">
                    <span class="item-info__price--old"><custom:currencyFormat amount="${bookItem.price}" currencyFormat="vi"/></span>
                    <span class="item-info__price--new"><custom:currencyFormat amount="${bookItem.price - bookItem.price*(bookItem.discount/100)}" currencyFormat="vi"/></span>
                    <span class="item-info__price--discount">${bookItem.discount}% GIẢM</span>
                </div>

                <div class="row mt-4">
                    <div class="col-2 label d-flex align-items-center">
                        <div class="text-muted text-capitalize">Số Lượng</div>
                    </div>
                    <div class="col-10 content d-flex align-items-center">
                        <c:choose>
                            <c:when test="${book.remainingQuantity < 1}">
                                <jsp:include page="counter.jsp">
                                    <jsp:param name="id" value="item-counter-${bookItem.ID}"></jsp:param>
                                    <jsp:param name="quantity" value="1"></jsp:param>
                                    <jsp:param name="className" value="pe-none text-muted user-select-none" ></jsp:param>
                                </jsp:include>
                            </c:when>
                            <c:otherwise>
                                <jsp:include page="counter.jsp">
                                    <jsp:param name="id" value="item-counter-${bookItem.ID}"></jsp:param>
                                    <jsp:param name="quantity" value="1"></jsp:param>
                                </jsp:include>
                            </c:otherwise>
                        </c:choose>

                        <div class="item-info__quantity-available ms-3 text-muted">
                            ${book.remainingQuantity} sản phẩm có sẵn
                        </div>
                    </div>
                </div>

                <div class="item-control mt-5">
                    <button type="button" class="btn btn-outline-primary text-capitalize me-2" id="add-to-cart">
                        <i class="fas fa-cart-plus me-2"></i>Thêm vào giỏ hàng
                    </button>
                    <button type="button" class="btn btn-primary text-capitalize" id="buy-now">Mua ngay</button>
                </div>
            </div>
            <hr class="text-muted my-4"/>
            <div class="return-guarantee mb-2">
                <span class="return-guarantee__amount text-capitalize text-muted d-flex align-items-center">
                    <i class="fas fa-shield-alt me-1  fs-5 text-success"></i>
                    <span class="return-guarantee__caption text-capitalize text-success me-3">Shopyy đảm bảo</span>
                    3 Ngày trả hàng / hoàn tiền
                </span>
            </div>
        </div>
    </div>
</div>
<div class="mx-5 mt-3">
    <div class="row">
        <div class="col-10">
            <div class="shadow-sm">
                <div class="item-detail-info text-uppercase  p-4 bg-white">
                    <h4 class="fw-normal text-uppercase bg-light px-3 py-2">Chi tiết sản phẩm</h4>
                    <ul class="list-unstyled text-capitalize px-3 mt-4">
                        <li class="mb-3 row align-items-center">
                            <div class="col-2">
                                <h6 class="fw-normal text-muted mb-0">
                                    Danh mục
                                </h6>
                            </div>
                            <div class="col-10">
                                <p class="mb-0">
                                    <a href="home">Shopyy</a>
                                    > 
                                    <a href="#">${bookItem.category}</a>
                                </p>
                            </div>
                        </li>

                        <li class="mb-3 row align-items-center">
                            <div class="col-2">
                                <h6 class="fw-normal text-muted mb-0">
                                    Kho hàng
                                </h6>
                            </div>
                            <div class="col-10">
                                <p class="mb-0">
                                    ${book.remainingQuantity}
                                </p>
                            </div>
                        </li>
                        
                        <li class="mb-3 row align-items-center">
                            <div class="col-2">
                                <h6 class="fw-normal text-muted mb-0">
                                    Tác giả
                                </h6>
                            </div>
                            <div class="col-10">
                                <p class="mb-0">
                                    <c:forEach items="${book.aut}" var="author">
                                        ${author.name},
                                    </c:forEach>
                                </p>
                            </div>
                        </li>
                        
                        <li class="mb-3 row align-items-center">
                            <div class="col-2">
                                <h6 class="fw-normal text-muted mb-0">
                                    NXB
                                </h6>
                            </div>
                            <div class="col-10">
                                <p class="mb-0">
                                    ${book.pub.name}
                                </p>
                            </div>
                        </li>
                        
                        <li class="mb-3 row align-items-center">
                            <div class="col-2">
                                <h6 class="fw-normal text-muted mb-0">
                                    Ngôn Ngữ
                                </h6>
                            </div>
                            <div class="col-10">
                                <p class="mb-0">
                                    ${book.language}
                                </p>
                            </div>
                        </li>
                        
                         <li class="mb-3 row align-items-center">
                            <div class="col-2">
                                <h6 class="fw-normal text-muted mb-0">
                                    Năm XB
                                </h6>
                            </div>
                            <div class="col-10">
                                <p class="mb-0">
                                    ${book.publicationYear}
                                </p>
                            </div>
                        </li>
                        
                         <li class="mb-3 row align-items-center">
                            <div class="col-2">
                                <h6 class="fw-normal text-muted mb-0">
                                    Số trang
                                </h6>
                            </div>
                            <div class="col-10">
                                <p class="mb-0">
                                    ${book.numberOfPage}
                                </p>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="item-detail-desc text-uppercase  p-4 bg-white">
                    <h4 class="fw-normal text-uppercase bg-light px-3 py-2">Mô tả sản phẩm</h4>
                    <div class="item-detail-desc__content px-3 mt-4">
                        <h6 class="text-capitalize fw-bold">${bookItem.name}</h6>
                        <p class="text-wrap">${bookItem.description}</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-2">
            <div class="top-selling bg-white p-3 shadow-sm">
                <h5 class="top-selling-title text-muted fw-normal mb-3">
                    Top Sản Phẩm Bán Chạy
                </h5>
                <ul class="top-selling-list list-unstyled">
                    <a href="" class="top-selling-item d-block text-decoration-none text-black">
                        <img class="img-fluid top-selling-item__img" src="https://cf.shopee.vn/file/2d6c1fb67c7888e641558e86f94ede66_tn">
                        <h5 class="top-selling-item__title my-2 fw-normal">Tai nghe nhét tai chống ồn tích hợp míc</h5>
                        <div class="top-selling-item__price text-primary"><sup>₫</sup>63.234</div>
                    </a>
                </ul>         
            </div>                          
        </div>
    </div>
</div>