<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="/WEB-INF/jsp/backend/common/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/backend/common/nav.jsp"></jsp:include>
<style>
    .error {
        color: red;
    }
</style>
<div class="container">
    <div>
        <div class="bg-light p-5 rounded">
            <div class="col-sm-8 mx-auto">
                <h1>Tạo mới tài khoản</h1>
                <form:form class="row g-3 needs-validation" method="post" action="/backend/user/save" modelAttribute="userDto">
                    <div class="col-md-6">
                        <label for="validationCustom01" class="form-label">Họ và tên</label>
                        <form:input type="text" class="form-control" id="validationCustom01" path="fullName" />
                        <form:errors cssClass="error" path="fullName"></form:errors>
                    </div>
                    <div class="col-md-6">
                        <label for="validationCustom02" class="form-label">Email<span class="error">(*)</span></label>
                        <form:input type="text" class="form-control" id="validationCustom02" path="email" />
                        <form:errors cssClass="error" path="email"></form:errors>
                    </div>
                    <div class="col-md-6">
                        <label for="validationCustom03" class="form-label">Mật khẩu</label>
                        <form:input type="password" class="form-control" id="validationCustom03" path="password" />
                        <form:errors cssClass="error" path="password"></form:errors>
                    </div>
                    <div class="col-md-6">
                        <label for="validationCustom04" class="form-label">Nhập lại mật khẩu</label>
                        <form:input type="password" class="form-control" id="validationCustom04" path="rePassword" />
                        <form:errors cssClass="error" path="rePassword"></form:errors>
                    </div>

                    <div class="col-md-6">
                        <label for="validationCustom04" class="form-label">Trang thái</label>
                        <select class="form-control" name="status" >
                            <option value="0">TẠM DỪNG</option>
                            <option value="1">KÍCH HOẠT</option>
                        </select>
                    </div>
                    <div class="col-md-12">
                        <label for="validationCustom02" class="form-label">Địa chỉ</label>
                        <form:input type="text" class="form-control" id="validationCustom02" path="address" />
                        <form:errors cssClass="error" path="address"></form:errors>
                    </div>

                    <div class="col-md-6">
                        <label for="validationCustom04" class="form-label">Thông tin liên hệ</label>
                        <div id="inputFormRow">
                            <div class="input-group mb-3">
                                <input type="text" name="phones[]" class="form-control m-input" placeholder="Số điện thoại" autocomplete="off">
                                <div class="input-group-append">
                                    <button id="removeRow" type="button" class="btn btn-danger">Xóa</button>
                                </div>
                            </div>
                        </div>

                        <div id="newRow"></div>
                        <button id="addRow" type="button" class="btn btn-info" style="float: right">Thêm sđt</button>
                    </div>

                    <div class="col-md-6">
                        <label for="validationCustom04" class="form-label">Quyền hạn</label>
                        <form:select cssClass="form-select" path="role">
                            <form:option value="USER">USER</form:option>
                            <form:option value="ADMIN">ADMIN</form:option>
                            <form:option value="CUSTOMER">CUSTOMER</form:option>
                        </form:select>
                    </div>
                    <div class="col-12">
                        <button class="btn btn-primary" type="submit">Tạo mới</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/backend/common/footer.jsp"></jsp:include>

<script type="text/javascript">
    // add row
    $("#addRow").click(function () {
        var html = '';
        html += '<div id="inputFormRow">';
        html += '<div class="input-group mb-3">';
        html += '<input type="text" name="phones[]" class="form-control m-input" placeholder="Số điện thoại" autocomplete="off">';
        html += '<div class="input-group-append">';
        html += '<button id="removeRow" type="button" class="btn btn-danger">Xóa</button>';
        html += '</div>';
        html += '</div>';

        $('#newRow').append(html);
    });

    // remove row
    $(document).on('click', '#removeRow', function () {
        $(this).closest('#inputFormRow').remove();
    });
</script>


















