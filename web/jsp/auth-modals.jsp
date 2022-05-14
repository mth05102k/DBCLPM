<%-- 
    Document   : auth-modals
    Created on : Nov 13, 2021, 12:13:04 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Login modal -->
<div class="modal fade" id="login-modal">
    <div class="modal-dialog">  
        <div class="modal-content">

            <div class="modal-header text-center">
                <h4 class="modal-title ">Đăng nhập</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <div class="modal-body">
                <form action="auth/login" method="POST" class="login-form mx-3" id="login-form">
                    <div class="mt-3 mb-4">
                        <input type="text" name="username" id="login-username" class="form-control"
                               placeholder="Tên tài khoản" required>
                    </div>
                    <div class="form-group position-relative">
                        <input type="password" name="password" id="login-password" class="form-control"
                               placeholder="Mật khẩu" required>
                        <input type="checkbox" id="show-password-checkbox" class="d-none">
                        <label for="show-password-checkbox" class="show-password-control position-absolute">
                            <i class="far fa-eye-slash hide-password-icon"></i>
                        </label>
                    </div>
                    <div class="form-message text-danger fs-6"></div>
                    <a href="${pageContext.request.contextPath}/forgot-password" class="forgot-password-link mt-2 d-inline-block">Quên mật khẩu</a>
                </form>
            </div>


            <div class="modal-footer mx-3">
                <button type="submit" form="login-form" class="btn btn-primary">Đăng nhập</button>
            </div>

        </div>
    </div>
</div>

<!-- register modal -->
<div class="modal fade" id="register-modal">
    <div class="modal-dialog">  
        <div class="modal-content">


            <div class="modal-header text-center">
                <h4 class="modal-title ">Đăng ký</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>


            <div class="modal-body">
                <form action="auth/register" method="POST" class="register-form"  id="register-form">
                    <div class="my-3">
                        <input type="text" name="username" id="register-username" class="form-control"
                               placeholder="Tên tài khoản" required>
                    </div>
                    <div class="my-3">
                        <input type="password" name="password" id="register-password" class="form-control"
                               placeholder="Mật khẩu" required>
                    </div>
                    <div class="my-3">
                        <input type="password" name="rePassword" id="register-password-confirm" class="form-control"
                               placeholder="Nhập lại mật khẩu" required>
                    </div>
                    <div class="form-message text-danger fs-6"></div>
                    <div class="my-3 register-policy">
                        Bằng việc đăng kí, bạn đã đồng ý với Shopyy về 
                        <a href="">Điều khoản dịch vụ</a>
                        &
                        <a href="">Chính sách bảo mật</a>
                    </div>
                </form>
            </div>


            <div class="modal-footer mx-3">
                <button type="submit" form="register-form" class="btn btn-primary">Đăng ký</button>
            </div>

        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/auth.js"></script>