<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                <h1>Tạo mới sản phẩm</h1>
                <p style="color: red">${message}</p>
                <form:form class="row g-3 needs-validation" method="post" action="/backend/product/save" modelAttribute="productDto">
                    <div class="col-md-6">
                        <label for="validationCustom01" class="form-label">Tên sản phẩm</label>
                        <form:input type="text" class="form-control" id="validationCustom01" path="name" />
                        <form:errors cssClass="error" path="name"></form:errors>
                    </div>
                    <div class="col-md-6">
                        <label for="validationCustom02" class="form-label">Giá</label>
                        <form:input type="number" class="form-control" id="validationCustom02" path="price" />
                        <form:errors cssClass="error" path="price"></form:errors>
                    </div>
                    <div class="col-md-12">
                        <label for="validationCustom03" class="form-label">Mô tả</label>
                        <form:textarea cols="4" type="password" class="form-control" id="validationCustom03" path="description" />
                    </div>

                <div class="row">
                    <div class="col-md-6">
                        <label for="fileUploadId" class="form-label">Thể loại</label>
                        <form:select cssClass="form-select" path="categoryId">
                            <c:forEach items="${categories}" var="c">
                                <form:option value="${c.id}">${c.name}</form:option>
                            </c:forEach>
                        </form:select>

                    </div>
                    <div class="col-md-6">
                        <label for="fileUploadId" class="form-label">Nhãn hiệu</label>
                        <form:select cssClass="form-select" path="brandId">
                            <c:forEach items="${brands}" var="b">
                                <form:option value="${b.id}">${b.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                </div>
                    <br>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="fileUploadId" class="form-label">Image</label>
                            <input type="text" hidden class="form-control" id="fileuploadName" name="image" />
                            <input type="file" id="fileUploadId" class="form-control"/><%-- thẻ input chon ảnh--%>
                            <div></div>
                        </div>
                        <div class="col-md-6">
                            <div>
                                <img id="outputImage" width="100px" />
                            </div>
                        </div>

                        <script>
                            $('#fileUploadId').on("change", function () {
                                var file = $(this)[0].files[0];
                                var reader = new FileReader();
                                reader.onload = function(){
                                    // var output = document.getElementById('outputImage');
                                    // output.src = reader.result;
                                    var output = $('#outputImage');
                                    output.attr('src', reader.result)
                                };
                                reader.readAsDataURL(file);
                                var data = new FormData();
                                data.append("file", file, file.name);
                                $.ajax({
                                    type: "POST",
                                    enctype: 'multipart/form-data',
                                    url: "/backend/product/upload",
                                    data: data,
                                    // prevent jQuery from automatically transforming the data into a query string
                                    processData: false,
                                    contentType: false,
                                    cache: false,
                                    timeout: 1000000,
                                    success: function(data, textStatus, jqXHR) {
                                        $("#fileuploadName").val(data);
                                       alert("Tải file " + data + " thành công");
                                    },
                                    error: function(jqXHR, textStatus, errorThrown) {
                                        alert("tải file thất bại")

                                    }
                                });
                            })
                        </script>
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


















