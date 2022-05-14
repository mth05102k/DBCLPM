<%-- 
    Document   : user
    Created on : Nov 16, 2021, 1:04:50 PM
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

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body class="bg-light">
        <jsp:include page="components/header.jsp"></jsp:include>
            <div class="container px-5 mt-5">
                <div class="row">
                    <div class="col-2">
                        <div class="user__general-info">
                            <img class="user__avatar rounded-circle" src="<c:out value="${(user.avatar eq null) ? 'https://cf.shopee.vn/file/1d74524ec09542f944ad95a2a6fd111d_tn' : user.avatar }" />" alt="user avatar">
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
                            <li class="nav-item active">
                                <a href="${pageContext.request.contextPath}/user/account/profile"  class="d-flex align-items-center">
                                    <i class="far fa-user me-2 text-primary"></i>
                                    <span>
                                        Tài khoản của tôi
                                    </span>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/customer/order">
                                    <i class="fas fa-clipboard-list me-2"></i>
                                    Đơn hàng
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-10">
                    <div class="user-profile px-4 py-3 bg-white shadow-sm rounded">
                        <div class="user-profile__header">
                            <h4 class="text-capitalize fw-normal">Hồ Sơ Của Tôi</h4>
                            <span>Quản lý thông tin hồ sơ để bảo mật tài khoản</span>
                        </div> 

                        <hr class="text-muted"/>

                        <div class="user-profile__content">
                            <form action="${pageContext.request.contextPath}/user/update" method="POST">
                                <ul class="user-profile__info-list list-unstyled row">
                                    <div class="col-8 user-profile__info-list-left">
                                        <li class="user-profile__info-item text-muted row align-items-center">
                                            <label class="user-profile__info-label form-label col-4" for="username">
                                                Tên Đăng Nhập
                                            </label>
                                            <div class="user-profile__info-input col-8 text-black" id="username"><c:out value="${user.account.username}"/></div>
                                        </li>
                                        <li class="user-profile__info-item text-muted row align-items-center">
                                            <label class="user-profile__info-label form-label col-4" for="fullname">
                                                Tên
                                            </label>
                                            <div class="col-8">
                                                <input type="text" class="user-profile__info-input form-control" id="fullname" name="fullname" value="<c:out value="${user.fullName.toString()}" />">
                                            </div>
                                        </li>
                                        <li class="user-profile__info-item text-muted row align-items-center">
                                            <label class="user-profile__info-label form-label col-4" for="email">
                                                Email
                                            </label>
                                            <div class="col-8 d-flex">
                                                <input type="text" class="user-profile__info-input form-control form-control-plaintext" readonly id="email" name="email" value="<c:out value="${user.mail}" />">
                                                <input type="checkbox" class="d-none" id="email-input-toggler">
                                                <label class="input-edit-control" for="email-input-toggler">Thay đổi</label>
                                            </div>
                                        </li>
                                        <li class="user-profile__info-item text-muted row align-items-center">
                                            <label class="user-profile__info-label form-label col-4" for="email">
                                                Số Điện Thoại
                                            </label>
                                            <div class="col-8 d-flex">
                                                <input type="text" class="user-profile__info-input form-control form-control-plaintext" readonly id="phonenumber" name="phonenumber" value="<c:out value="${user.phone}" />">
                                                <input type="checkbox" class="d-none" id="phonenumber-input-toggler">
                                                <label class="input-edit-control" for="phonenumber-input-toggler">Thay đổi</label>
                                            </div>
                                        </li>
                                        <li class="user-profile__info-item text-muted row align-items-center">
                                            <label class="user-profile__info-label form-label col-4" for="email">
                                                Giới tính
                                            </label>
                                            <div class="col-8 d-flex align-items-center">
                                                <div class="form-group me-3">
                                                    <input class="form-check-input" type="radio" value="male" id="gender-male" name="gender"  <c:if test="${user.gender eq 'male'}">checked</c:if>>
                                                        <label class="form-check-label" for="gender-male">Nam</label>
                                                    </div>
                                                    <div class="form-group me-3">
                                                        <input class="form-check-input" type="radio" value="female" id="gender-female" name="gender"  <c:if test="${user.gender eq 'female'}">checked</c:if>>
                                                        <label class="form-check-label" for="gender-female">Nữ</label>
                                                    </div>
                                                    <div class="form-group">
                                                        <input class="form-check-input" type="radio" value="other" id="gender-other" name="gender" <c:if test="${user.gender eq 'other'}">checked</c:if> >
                                                        <label class="form-check-label" for="gender-other">Khác</label>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="user-profile__info-item text-muted row align-items-center">
                                                <label class="user-profile__info-label form-label col-4" for="detail-address">
                                                    Địa chỉ cụ thể
                                                </label>
                                                <div class="col-8 d-flex">
                                                    <input type="text" class="user-profile__info-input form-control" id="detail-address" name="addressdetail" value="<c:out value="${user.address.addressDetail}" />">
                                            </div>
                                        </li>
                                        <li class="user-profile__info-item text-muted row align-items-center">
                                            <label class="user-profile__info-label form-label col-4" for="district">
                                                Quận huyện
                                            </label>
                                            <div class="col-8 d-flex justify-content-between">
                                                <div id="district-uneditable"><c:out value="${user.address.district}" /></div>
                                                <select id="district-select"  name="district" class="form-select d-none">
                                                    <option value="">-- Chọn quận huyện --</option>
                                                </select>

                                                <input type="checkbox" class="d-none" id="district-input-toggler">
                                                <label class="input-edit-control" for="district-input-toggler">Thay đổi</label>
                                            </div>
                                        </li>
                                        <li class="user-profile__info-item text-muted row align-items-center">
                                            <label class="user-profile__info-label form-label col-4" for="city">
                                                Tỉnh/Thành phố
                                            </label>
                                            <div class="col-8 d-flex justify-content-between">
                                                <div id="city-uneditable"><c:out value="${user.address.city}" /></div>
                                                <select id="city-select"  name="city" class="form-select d-none">
                                                    <option value="">-- Chọn tỉnh/thành phố --</option>
                                                </select>
                                                
                                                <input type="checkbox" class="d-none" id="city-input-toggler">
                                                <label class="input-edit-control" for="city-input-toggler">Thay đổi</label>
                                            </div>
                                        </li>
                                    </div>

                                    <div class="col-4">
                                        <div class="user-profile__avatar-control text-center d-flex flex-column align-items-center">
                                            <img src="<c:out value="${(user.avatar eq null) ? 'https://cf.shopee.vn/file/1d74524ec09542f944ad95a2a6fd111d_tn' : user.avatar }" />" alt="user avatar" class="rounded-circle mb-3" id="user-avatar-preview">
                                            <input type="file" id="avatar-chooser-input" name="avatar" accept=".png,.jpg">
                                            <label for="avatar-chooser-input" class="btn btn-outline-secondary">Chọn Ảnh</label>
                                            <div class="avatar-choose-constraints text-muted mt-2">
                                                <div>Dụng lượng file tối đa 1 MB</div>
                                                <div>Định dạng:.JPEG, .PNG</div>
                                            </div>
                                        </div>
                                    </div>
                                </ul>

                                <hr class="text-muted"/>

                                <div class="text-end">
                                    <button class="btn btn-primary ms-auto px-4" type="submit">Lưu</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="components/footer.jsp"></jsp:include>

            <script src="${pageContext.request.contextPath}/js/user.js"></script>
        <!--toggle input-->
        <script>
            toggleInputEditable('email', 'email-input-toggler', 'form-control-plaintext', null, 'Thay đổi', 'Hủy');
            toggleInputEditable('phonenumber', 'phonenumber-input-toggler', 'form-control-plaintext', null, 'Thay đổi', 'Hủy');

            toggleEditable('district-uneditable', 'district-select', 'd-block', 'd-none', 'district-input-toggler', 'Thay đổi', 'Hủy');
            toggleEditable('city-uneditable', 'city-select', 'd-block', 'd-none', 'city-input-toggler', 'Thay đổi', 'Hủy');
        </script>

        <script src="${pageContext.request.contextPath}/js/address-api.js"></script>

        <script src="${pageContext.request.contextPath}/js/image-previewer.js"></script>
        <script src="${pageContext.request.contextPath}/js/file-validator.js"></script>
        <!--image preview-->
        <script>
            imagePreviewer('avatar-chooser-input', 'user-avatar-preview', customFileValidator, [1]);
        </script>
    </body>
</html>
