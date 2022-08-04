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
                <h1>Cập nhật ${title_path}</h1>
                <form:form class="row g-3 needs-validation" method="post" action="/backend/${path}/save" modelAttribute="dto">
                    <input hidden name="id" value="${dto.id}"/>
                    <div class="col-md-6">
                        <label for="validationCustom01" class="form-label">Tên</label>
                        <form:input type="text" class="form-control" id="validationCustom01" path="name" />
                        <form:errors cssClass="error" path="name"></form:errors>
                    </div>

                    <div class="col-md-12">
                        <label for="validationCustom02" class="form-label">Mô tả</label>
                        <form:input type="text" class="form-control" id="validationCustom02" path="description" />
                        <form:errors cssClass="error" path="description"></form:errors>
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


















