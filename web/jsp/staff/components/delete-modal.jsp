<%-- 
    Document   : delete-modal
    Created on : Jan 4, 2022, 9:16:12 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="modal fade" tabindex="-1" id="delete-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Bạn có chắc chắn muốn xóa?</h5>
            </div>
            <div class="modal-body">
                <p>Thông tin đã xóa không thể khôi phục lại</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" id="delete-btn" data-bs-dismiss="modal">Xóa</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
            </div>
        </div>
    </div>
</div>
