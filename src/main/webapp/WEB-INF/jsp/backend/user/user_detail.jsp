<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include>

<div class="container">
    <h2>Chi tiết tài khoản</h2>
    <form>
        <!-- 2 column grid layout with text inputs for the first and last names -->
        <div class="row mb-4">
            <div class="col">
                <div class="form-outline">
                    <input type="text" id="form6Example1" value="${user.fullName}" class="form-control" />
<%--                    <label class="form-label" >Họ và tên</label>--%>
                </div>
            </div>
            <div class="col">
                <div class="form-outline">
                    <input type="text" id="form6Example2" value="${user.email}" class="form-control" />
<%--                    <label class="form-label" for="form6Example2">Email</label>--%>
                </div>
            </div>
        </div>

        <!-- Text input -->
        <div class="form-outline mb-4">
            <input type="text" id="form6Example3" value="${user.role}" class="form-control" />
<%--            <label class="form-label" for="form6Example3">Quyền</label>--%>
        </div>


        <!-- Submit button -->
        <button type="submit" class="btn btn-primary btn-block mb-4">Place order</button>
    </form>
</div>

<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>