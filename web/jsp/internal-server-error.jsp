<%-- 
    Document   : internal-server-error
    Created on : Nov 23, 2021, 12:23:32 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
        <link href="${pageContext.request.contextPath}/css/variables.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/auth-modals.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/checkout.css" rel="stylesheet" />
        <title>Internal Server Error</title>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <jsp:include page="components/header.jsp"></jsp:include>

            <div class="container text-center mt-5">
                <img class="img-fluid" alt="page not found" src="http://freeminecraftserverhosting.com/wp-content/uploads/2021/05/loi-503.png">
            </div>
            <h3 class="text-center">Opps! Có lỗi ở máy chủ, vui lòng thử lại sau</h3>
            
        <jsp:include page="components/footer.jsp"></jsp:include>

    </body>
</html>
