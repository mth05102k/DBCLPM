<%-- 
    Document   : item-detail
    Created on : Nov 13, 2021, 10:01:28 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link href="${pageContext.request.contextPath}/css/auth-modals.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/carousel.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/item.css" rel="stylesheet" />

        <link href="${pageContext.request.contextPath}/css/item-detail.css" rel="stylesheet" />

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body class="bg-light">

        <jsp:include page="components/header.jsp"></jsp:include>

        <jsp:include page="auth-modals.jsp"></jsp:include>


            <div class="container my-5 main">
                <div class="bread-crumb"></div>
            <jsp:include page="components/book-detail.jsp"></jsp:include>

                <div class="similar-item mx-5 mt-5">
                    <div class="row">
                        <div class="col-10">
                            <h5 class="similar-item__title fw-normal text-uppercase text-muted">Sản phẩm tương tự</h5>
                            <div class="similar-item-list">
                                <div id="similar-item-carousel" class="carousel slide" data-bs-ride="carousel">

                                    <!-- The slideshow/carousel -->
                                    <div class="carousel-inner custom-carousel-inner">
                                        <div class="carousel-item active">
                                            <div class="row row-cols-4 g-3">
                                                <div class="col-2">
                                                    <a class="home-product__item" href="#">
                                                        <div class="home-product__item-img"
                                                             style="background-image: url(https://cf.shopee.vn/file/2df142298b8762051efcf635c368d99d);">
                                                        </div>
                                                        <h4 class="home-product__item-name">Áo Hoodie Nam Nữ Happy, Áo sweater form rộng unisex HT60</h4>
                                                        <div class="home-product__item-price">
                                                            <span class="home-product__item-price--old">23.000<sup>đ</sup></span>
                                                            <span class="home-product__item-price--current">16.000<sup>đ</sup></span>
                                                        </div>
                                                        <div class="home-product__item-origin">
                                                            <span class="home-product__item-brand">Woho</span>
                                                            <span class="home-product__item-country">Nhật bản</span>
                                                        </div>
                                                    </a>
                                                </div>
                                                <div class="col-2">
                                                    <a class="home-product__item" href="#">
                                                        <div class="home-product__item-img"
                                                             style="background-image: url(https://cf.shopee.vn/file/2df142298b8762051efcf635c368d99d);">
                                                        </div>
                                                        <h4 class="home-product__item-name">Áo Hoodie Nam Nữ Happy, Áo sweater form rộng unisex HT60</h4>
                                                        <div class="home-product__item-price">
                                                            <span class="home-product__item-price--old">23.000<sup>đ</sup></span>
                                                            <span class="home-product__item-price--current">16.000<sup>đ</sup></span>
                                                        </div>
                                                        <div class="home-product__item-origin">
                                                            <span class="home-product__item-brand">Woho</span>
                                                            <span class="home-product__item-country">Nhật bản</span>
                                                        </div>
                                                    </a>
                                                </div>
                                                <div class="col-2">
                                                    <a class="home-product__item" href="#">
                                                        <div class="home-product__item-img"
                                                             style="background-image: url(https://cf.shopee.vn/file/2df142298b8762051efcf635c368d99d);">
                                                        </div>
                                                        <h4 class="home-product__item-name">Áo Hoodie Nam Nữ Happy, Áo sweater form rộng unisex HT60</h4>
                                                        <div class="home-product__item-price">
                                                            <span class="home-product__item-price--old">23.000<sup>đ</sup></span>
                                                            <span class="home-product__item-price--current">16.000<sup>đ</sup></span>
                                                        </div>
                                                        <div class="home-product__item-origin">
                                                            <span class="home-product__item-brand">Woho</span>
                                                            <span class="home-product__item-country">Nhật bản</span>
                                                        </div>
                                                    </a>
                                                </div>
                                                <div class="col-2">
                                                    <a class="home-product__item" href="#">
                                                        <div class="home-product__item-img"
                                                             style="background-image: url(https://cf.shopee.vn/file/2df142298b8762051efcf635c368d99d);">
                                                        </div>
                                                        <h4 class="home-product__item-name">Áo Hoodie Nam Nữ Happy, Áo sweater form rộng unisex HT60</h4>
                                                        <div class="home-product__item-price">
                                                            <span class="home-product__item-price--old">23.000<sup>đ</sup></span>
                                                            <span class="home-product__item-price--current">16.000<sup>đ</sup></span>
                                                        </div>
                                                        <div class="home-product__item-origin">
                                                            <span class="home-product__item-brand">Woho</span>
                                                            <span class="home-product__item-country">Nhật bản</span>
                                                        </div>
                                                    </a>
                                                </div>
                                                <div class="col-2">
                                                    <a class="home-product__item" href="#">
                                                        <div class="home-product__item-img"
                                                             style="background-image: url(https://cf.shopee.vn/file/2df142298b8762051efcf635c368d99d);">
                                                        </div>
                                                        <h4 class="home-product__item-name">Áo Hoodie Nam Nữ Happy, Áo sweater form rộng unisex HT60</h4>
                                                        <div class="home-product__item-price">
                                                            <span class="home-product__item-price--old">23.000<sup>đ</sup></span>
                                                            <span class="home-product__item-price--current">16.000<sup>đ</sup></span>
                                                        </div>
                                                        <div class="home-product__item-origin">
                                                            <span class="home-product__item-brand">Woho</span>
                                                            <span class="home-product__item-country">Nhật bản</span>
                                                        </div>
                                                    </a>
                                                </div>
                                                <div class="col-2">
                                                    <a class="home-product__item" href="#">
                                                        <div class="home-product__item-img"
                                                             style="background-image: url(https://cf.shopee.vn/file/2df142298b8762051efcf635c368d99d);">
                                                        </div>
                                                        <h4 class="home-product__item-name">Áo Hoodie Nam Nữ Happy, Áo sweater form rộng unisex HT60</h4>
                                                        <div class="home-product__item-price">
                                                            <span class="home-product__item-price--old">23.000<sup>đ</sup></span>
                                                            <span class="home-product__item-price--current">16.000<sup>đ</sup></span>
                                                        </div>
                                                        <div class="home-product__item-origin">
                                                            <span class="home-product__item-brand">Woho</span>
                                                            <span class="home-product__item-country">Nhật bản</span>
                                                        </div>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="carousel-item">
                                            <div class="row row-cols-4 g-3">
                                                <div class="col-2">
                                                    <a class="home-product__item" href="#">
                                                        <div class="home-product__item-img"
                                                             style="background-image: url(https://cf.shopee.vn/file/2df142298b8762051efcf635c368d99d);">
                                                        </div>
                                                        <h4 class="home-product__item-name">Áo Hoodie Nam Nữ Happy, Áo sweater form rộng unisex HT60</h4>
                                                        <div class="home-product__item-price">
                                                            <span class="home-product__item-price--old">23.000<sup>đ</sup></span>
                                                            <span class="home-product__item-price--current">16.000<sup>đ</sup></span>
                                                        </div>
                                                        <div class="home-product__item-origin">
                                                            <span class="home-product__item-brand">Woho</span>
                                                            <span class="home-product__item-country">Nhật bản</span>
                                                        </div>
                                                    </a>
                                                </div>
                                                <div class="col-2">
                                                    <a class="home-product__item" href="#">
                                                        <div class="home-product__item-img"
                                                             style="background-image: url(https://cf.shopee.vn/file/2df142298b8762051efcf635c368d99d);">
                                                        </div>
                                                        <h4 class="home-product__item-name">Áo Hoodie Nam Nữ Happy, Áo sweater form rộng unisex HT60</h4>
                                                        <div class="home-product__item-price">
                                                            <span class="home-product__item-price--old">23.000<sup>đ</sup></span>
                                                            <span class="home-product__item-price--current">16.000<sup>đ</sup></span>
                                                        </div>
                                                        <div class="home-product__item-origin">
                                                            <span class="home-product__item-brand">Woho</span>
                                                            <span class="home-product__item-country">Nhật bản</span>
                                                        </div>
                                                    </a>
                                                </div>
                                                <div class="col-2">
                                                    <a class="home-product__item" href="#">
                                                        <div class="home-product__item-img"
                                                             style="background-image: url(https://cf.shopee.vn/file/2df142298b8762051efcf635c368d99d);">
                                                        </div>
                                                        <h4 class="home-product__item-name">Áo Hoodie Nam Nữ Happy, Áo sweater form rộng unisex HT60</h4>
                                                        <div class="home-product__item-price">
                                                            <span class="home-product__item-price--old">23.000<sup>đ</sup></span>
                                                            <span class="home-product__item-price--current">16.000<sup>đ</sup></span>
                                                        </div>
                                                        <div class="home-product__item-origin">
                                                            <span class="home-product__item-brand">Woho</span>
                                                            <span class="home-product__item-country">Nhật bản</span>
                                                        </div>
                                                    </a>
                                                </div>
                                                <div class="col-2">
                                                    <a class="home-product__item" href="#">
                                                        <div class="home-product__item-img"
                                                             style="background-image: url(https://cf.shopee.vn/file/2df142298b8762051efcf635c368d99d);">
                                                        </div>
                                                        <h4 class="home-product__item-name">Áo Hoodie Nam Nữ Happy, Áo sweater form rộng unisex HT60</h4>
                                                        <div class="home-product__item-price">
                                                            <span class="home-product__item-price--old">23.000<sup>đ</sup></span>
                                                            <span class="home-product__item-price--current">16.000<sup>đ</sup></span>
                                                        </div>
                                                        <div class="home-product__item-origin">
                                                            <span class="home-product__item-brand">Woho</span>
                                                            <span class="home-product__item-country">Nhật bản</span>
                                                        </div>
                                                    </a>
                                                </div>
                                                <div class="col-2">
                                                    <a class="home-product__item" href="#">
                                                        <div class="home-product__item-img"
                                                             style="background-image: url(https://cf.shopee.vn/file/2df142298b8762051efcf635c368d99d);">
                                                        </div>
                                                        <h4 class="home-product__item-name">Áo Hoodie Nam Nữ Happy, Áo sweater form rộng unisex HT60</h4>
                                                        <div class="home-product__item-price">
                                                            <span class="home-product__item-price--old">23.000<sup>đ</sup></span>
                                                            <span class="home-product__item-price--current">16.000<sup>đ</sup></span>
                                                        </div>
                                                        <div class="home-product__item-origin">
                                                            <span class="home-product__item-brand">Woho</span>
                                                            <span class="home-product__item-country">Nhật bản</span>
                                                        </div>
                                                    </a>
                                                </div>
                                                <div class="col-2">
                                                    <a class="home-product__item" href="#">
                                                        <div class="home-product__item-img"
                                                             style="background-image: url(https://cf.shopee.vn/file/2df142298b8762051efcf635c368d99d);">
                                                        </div>
                                                        <h4 class="home-product__item-name">Áo Hoodie Nam Nữ Happy, Áo sweater form rộng unisex HT60</h4>
                                                        <div class="home-product__item-price">
                                                            <span class="home-product__item-price--old">23.000<sup>đ</sup></span>
                                                            <span class="home-product__item-price--current">16.000<sup>đ</sup></span>
                                                        </div>
                                                        <div class="home-product__item-origin">
                                                            <span class="home-product__item-brand">Woho</span>
                                                            <span class="home-product__item-country">Nhật bản</span>
                                                        </div>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Right controls/icons -->
                                    <button class="carousel-control-next" type="button" data-bs-target="#similar-item-carousel"
                                            data-bs-slide="next">
                                        <span class="carousel-control-next-icon"></span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <!-- Footer -->
        <jsp:include page="components/footer.jsp"></jsp:include>


            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/counter.js"></script>
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="${pageContext.request.contextPath}/js/add-to-cart.js"></script>
    </body>
</html>
