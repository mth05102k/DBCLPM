/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// login
(function () {
    document.getElementById('login-form').addEventListener('submit', async function (event) {
        event.preventDefault();

        const formData = new URLSearchParams();
        const username = document.getElementById('login-username').value;
        const password = document.getElementById('login-password').value;

        formData.append('username', username);
        formData.append('password', password);

        const response = await fetch('http://localhost:8080/g11/auth/login',
                {
                    method: "POST",
                    contentType: "application/x-www-form-urlencoded",
                    body: formData,
                }
        );

        const data = await response.text();
        if (data) {
            const dataTokens = data.split(';');
            if (dataTokens[0] === '200') {
                const redirectPath = dataTokens[1]
                document.location.pathname = redirectPath;
            } else if (dataTokens[0] === '401') {
                document.querySelector('#login-form .form-message').innerText = 'Sai tên tài khoản hoặc mật khẩu';
            }
        }
    });
})();

// register
(function () {
    document.getElementById('register-form').addEventListener('submit', async function (event) {
        event.preventDefault();

        const formData = new URLSearchParams();
        const username = document.getElementById('register-username').value;
        const password = document.getElementById('register-password').value;
        const rePassword = document.getElementById('register-password-confirm').value;

        if (rePassword !== password) {
            document.querySelector('#register-form .form-message').innerText = "Nhập lại mật khẩu không khớp!";
            return;
        }

        formData.append('username', username);
        formData.append('password', password);

        const response = await fetch('http://localhost:8080/g11/auth/register',
                {
                    method: "POST",
                    contentType: "application/x-www-form-urlencoded",
                    body: formData,
                }
        );

        const data = await response.text();
        if (data) {
            const formMessageEle = document.querySelector('#register-form .form-message');
            const dataTokens = data.split(';');

            if (dataTokens[0] === '201') {
                document.location.pathname = "/g11/home";
            } else if (dataTokens[0] === '409') {
                formMessageEle.innerText = 'Tài khoản đã tồn tại';
            }
        }
    });
})();
