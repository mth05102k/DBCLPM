<%-- 
    Document   : home
    Created on : Nov 12, 2021, 1:31:01 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
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
        <link href="${pageContext.request.contextPath}/css/carousel.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/item.css" rel="stylesheet" />

        <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet" />

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>

    <body class="bg-light">

        <jsp:include page="components/header.jsp"></jsp:include>

        <jsp:include page="auth-modals.jsp"></jsp:include>



            <!-- Intro -->
            <div class="container p-5 pt-0">
                <div class="row row-cols-3 mt-5">

                    <!-- Left component -->
                    <div class="col-2 bg-light shadow-sm">
                        <div class="row row-cols-1 bg-white">
                            <div class="col pt-3">
                                <div class="row-cols-1 text-center">
                                    <h6 class="text-capitalize">Sản phẩm khuyến mãi</h6>
                                </div>
                                <hr class="text-muted"/>
                                <div class="row-cols-1 g-3">
<!--                                    <div class="mb-3">
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
                                    <div class="mb-3">
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
                                    </div>-->
                                </div>
                            </div>
                        </div>
                    </div>




                    <!-- Middle component -->
                    <div class="col-8 bg-light px-5">

                        <!-- Slider -->
                        <div class="row row-cols-1 bg-white mb-4 py-2 shadow-sm">

                            <div id="demo" class="carousel slide" data-bs-ride="carousel">

                                <!-- Indicators/dots -->
                                <div class="carousel-indicators">
                                    <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
                                    <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
                                    <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
                                </div>

                                <!-- The slideshow/carousel -->
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <img src="https://cdn0.fahasa.com/media/magentothem/banner7/TrangMonthlySale_T122_mainbanner__920x420.jpg" alt="Los Angeles" class="d-block py-2" style="width:100%">
                                        <div class="carousel-caption text-black">
                                        </div>
                                    </div>
                                </div>

                                <!-- Left and right controls/icons -->
                                <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon"></span>
                                </button>
                                <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
                                    <span class="carousel-control-next-icon"></span>
                                </button>
                            </div>
                        </div>

                        <!-- Danh mục hàng -->
                        <div class="row row-cols-1 bg-white mt-5 shadow-sm">
                            <div class="col py-3">
                                <div class="text-center text-uppercase">
                                    <h4 class="mb-0">Danh mục</h4>
                                </div>
                                <hr class="text-muted"/>
                                <div class="px-3 row row-cols-4">
                                    <div class="col-3 text-start">
                                        <div class="category-list">
                                            <h5 class="">
                                                <a href="${pageContext.request.contextPath}/product?category=2" class="text-black text-decoration-none">
                                                <!--<img src="https://cdn0.fahasa.com/media/catalog/product/cache/1/small_image/400x400/9df78eab33525d08d6e5fb8d27136e95/n/a/naruto---tap-67.jpg" alt="fashion">-->
                                                Sách thiếu nhi
                                            </a>
                                        </h5>
                                    </div>
                                </div>
                                <div class="col-3 text-start">
                                    <div class="category-list">
                                        <h5 class="">
                                            <a href="${pageContext.request.contextPath}/product?category=3" class="text-black text-decoration-none">
                                                <!--<img src="https://cf.shopee.vn/file/74ca517e1fa74dc4d974e5d03c3139de_tn" alt="shoes">-->
                                                Sách trong nước
                                            </a>
                                        </h5>
                                    </div>
                                </div>
                                <div class="col-3 text-start">
                                    <div class="category-list">
                                        <h5 class="">
                                            <a href="${pageContext.request.contextPath}/product?category=1" class="text-black text-decoration-none">
                                                <!--<img src="https://cf.shopee.vn/file/36013311815c55d303b0e6c62d6a8139_tn" alt="books">-->
                                                Sách nước ngoài
                                            </a>
                                        </h5>
                                    </div>
                                </div>
                                <div class="col-3 text-start">
                                    <div class="category-list">
                                        <h5 class="">
                                            <a href="${pageContext.request.contextPath}/product?category=4" class="text-black text-decoration-none">
                                                <!--<img src="https://cf.shopee.vn/file/978b9e4cb61c611aaaf58664fae133c5_tn" alt="electronics">-->
                                                E-book
                                            </a>
                                        </h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <!-- Right component -->
                <div class="col-2 bg-light rounded shadow-sm">
                    <div class="row row-cols-1 bg-white">
                        <div class="col pt-3">
                            <div class="row-cols-1 text-center text-capitalize">
                                <h6>Bán chạy</h6>
                            </div>
                            <hr class="text-muted"/>
                            <div class="row-cols-1 g-3">
<!--                                <div class="mb-3">
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
                                <div class="mb-3">
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
                                </div>-->
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <!--banner sale-->
        <div class="container mb-5">
            <div class="banner-sale px-4">
                <img src="https://www.magiamgiaonline.com/wp-content/uploads/2019/03/m%C3%A3-gi%E1%BA%A3m-gi%C3%A1-banner.jpeg" alt="">
                <img src="http://thegioidohoacom.s3.ap-southeast-1.amazonaws.com/wp-content/uploads/2019/01/10040532/201807120816_banner-bai-viet-ctkm-hung-tuy-1511235823.jpg" alt="">
            </div>
        </div>

        <!-- Slider Best Seller -->

        <div class="container px-5">
            <div class="bg-white best-seller py-2 rounded shadow-sm">
                <div class="d-flex align-items-center justify-content-between text-capitalize mt-2 mb-3 px-4">
                    <h4 class="mb-0 fw-normal">Best Seller</h4>
                    <a href="" class="best-seller__view-all-link">
                        Xem tất cả
                        <i class="fas fa-chevron-right"></i>
                    </a>
                </div>
                <hr class="text-muted" />

                <div id="best-seller-carousel" class="carousel slide ps-3" data-bs-ride="carousel">

                    <!-- The slideshow/carousel -->
                    <div class="carousel-inner custom-carousel-inner">
                        <div class="carousel-item active">
                            <div class="row row-cols-4 g-5">
                                <div class="col-2">
                                    <a href="" class="best-seller__item">
                                        <div class="best-seller__badge bg-danger text-white">HOT</div>
                                        <img src="https://cf.shopee.vn/file/46f4a1c42ce6c897c179817f956bea8f_tn" />
                                        <div class="best-seller__item-selling-status">Bán <span class="best-seller__item-selling-amount">81k+</span> / tháng</div>
                                        <div class="best-seller__item-caption">Quần Ống Rộng Nữ</div>
                                    </a>
                                </div>
                                <div class="col-2">
                                    <a href="" class="best-seller__item">
                                        <div class="best-seller__badge bg-danger text-white">HOT</div>
                                        <img src="https://cf.shopee.vn/file/46f4a1c42ce6c897c179817f956bea8f_tn" />
                                        <div class="best-seller__item-selling-status">Bán <span class="best-seller__item-selling-amount">81k+</span> / tháng</div>
                                        <div class="best-seller__item-caption">Quần Ống Rộng Nữ</div>
                                    </a>
                                </div>
                                <div class="col-2">
                                    <a href="" class="best-seller__item">
                                        <div class="best-seller__badge bg-danger text-white">HOT</div>
                                        <img src="https://cf.shopee.vn/file/46f4a1c42ce6c897c179817f956bea8f_tn" />
                                        <div class="best-seller__item-selling-status">Bán <span class="best-seller__item-selling-amount">81k+</span> / tháng</div>
                                        <div class="best-seller__item-caption">Quần Ống Rộng Nữ</div>
                                    </a>
                                </div>
                                <div class="col-2">
                                    <a href="" class="best-seller__item">
                                        <div class="best-seller__badge bg-danger text-white">HOT</div>
                                        <img src="https://cf.shopee.vn/file/46f4a1c42ce6c897c179817f956bea8f_tn" />
                                        <div class="best-seller__item-selling-status">Bán <span class="best-seller__item-selling-amount">81k+</span> / tháng</div>
                                        <div class="best-seller__item-caption">Quần Ống Rộng Nữ</div>
                                    </a>
                                </div>
                                <div class="col-2">
                                    <a href="" class="best-seller__item">
                                        <div class="best-seller__badge bg-danger text-white">HOT</div>
                                        <img src="https://cf.shopee.vn/file/46f4a1c42ce6c897c179817f956bea8f_tn" />
                                        <div class="best-seller__item-selling-status">Bán <span class="best-seller__item-selling-amount">81k+</span> / tháng</div>
                                        <div class="best-seller__item-caption">Quần Ống Rộng Nữ</div>
                                    </a>
                                </div>
                                <div class="col-2">
                                    <a href="" class="best-seller__item">
                                        <div class="best-seller__badge bg-danger text-white">HOT</div>
                                        <img src="https://cf.shopee.vn/file/46f4a1c42ce6c897c179817f956bea8f_tn" />
                                        <div class="best-seller__item-selling-status">Bán <span class="best-seller__item-selling-amount">81k+</span> / tháng</div>
                                        <div class="best-seller__item-caption">Quần Ống Rộng Nữ</div>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <div class="row row-cols-4 g-5">
                                <div class="col-2">
                                    <a href="" class="best-seller__item">
                                        <div class="best-seller__badge bg-danger text-white">HOT</div>
                                        <img src="https://cf.shopee.vn/file/46f4a1c42ce6c897c179817f956bea8f_tn" />
                                        <div class="best-seller__item-selling-status">Bán <span class="best-seller__item-selling-amount">81k+</span> / tháng</div>
                                        <div class="best-seller__item-caption">Quần Ống Rộng Nữ</div>
                                    </a>
                                </div>
                                <div class="col-2">
                                    <a href="" class="best-seller__item">
                                        <div class="best-seller__badge bg-danger text-white">HOT</div>
                                        <img src="https://cf.shopee.vn/file/46f4a1c42ce6c897c179817f956bea8f_tn" />
                                        <div class="best-seller__item-selling-status">Bán <span class="best-seller__item-selling-amount">81k+</span> / tháng</div>
                                        <div class="best-seller__item-caption">Quần Ống Rộng Nữ</div>
                                    </a>
                                </div>
                                <div class="col-2">
                                    <a href="" class="best-seller__item">
                                        <div class="best-seller__badge bg-danger text-white">HOT</div>
                                        <img src="https://cf.shopee.vn/file/46f4a1c42ce6c897c179817f956bea8f_tn" />
                                        <div class="best-seller__item-selling-status">Bán <span class="best-seller__item-selling-amount">81k+</span> / tháng</div>
                                        <div class="best-seller__item-caption">Quần Ống Rộng Nữ</div>
                                    </a>
                                </div>
                                <div class="col-2">
                                    <a href="" class="best-seller__item">
                                        <div class="best-seller__badge bg-danger text-white">HOT</div>
                                        <img src="https://cf.shopee.vn/file/46f4a1c42ce6c897c179817f956bea8f_tn" />
                                        <div class="best-seller__item-selling-status">Bán <span class="best-seller__item-selling-amount">81k+</span> / tháng</div>
                                        <div class="best-seller__item-caption">Quần Ống Rộng Nữ</div>
                                    </a>
                                </div>
                                <div class="col-2">
                                    <a href="" class="best-seller__item">
                                        <div class="best-seller__badge bg-danger text-white">HOT</div>
                                        <img src="https://cf.shopee.vn/file/46f4a1c42ce6c897c179817f956bea8f_tn" />
                                        <div class="best-seller__item-selling-status">Bán <span class="best-seller__item-selling-amount">81k+</span> / tháng</div>
                                        <div class="best-seller__item-caption">Quần Ống Rộng Nữ</div>
                                    </a>
                                </div>
                                <div class="col-2">
                                    <a href="" class="best-seller__item">
                                        <div class="best-seller__badge bg-danger text-white">HOT</div>
                                        <img src="https://cf.shopee.vn/file/46f4a1c42ce6c897c179817f956bea8f_tn" />
                                        <div class="best-seller__item-selling-status">Bán <span class="best-seller__item-selling-amount">81k+</span> / tháng</div>
                                        <div class="best-seller__item-caption">Quần Ống Rộng Nữ</div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Right controls/icons -->
                    <button class="carousel-control-next" type="button" data-bs-target="#best-seller-carousel"
                            data-bs-slide="next">
                        <span class="carousel-control-next-icon"></span>
                    </button>
                </div>
            </div>
        </div>


        <!-- Footer -->
        <jsp:include page="components/footer.jsp"></jsp:include>

            <script src="${pageContext.request.contextPath}/js/toggle-element.js"></script>
        <script>
            elementToggler('login-password', 'show-password-checkbox', 'fa-eye-slash', 'fa-eye');
        </script>
    </body>
</html>
