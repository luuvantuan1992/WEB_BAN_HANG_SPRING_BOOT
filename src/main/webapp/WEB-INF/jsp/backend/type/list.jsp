<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/WEB-INF/jsp/backend/common/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/backend/common/nav.jsp"></jsp:include>
<div class="container">
    <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Danh sách ${title_path}</p>
    <p style="color: red"> ${message}</p>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">Tên</th>
            <th scope="col">Mô tả</th>
            <th scope="col">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="user">
            <tr>
                <th scope="col">${user.id}</th>
                <th scope="col">${user.name}</th>
                <th scope="col">${user.description}</th>
                <th scope="col"><a data-toggle="confirmation" data-title="Xóa tài khoản ${user.name}" data-popout="true"
                                   href="/backend/${path}/delete/${user.id}">Xóa</a> &nbsp;<a href="/backend/${path}/update/${user.id}">Sửa</a></th>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <%--<nav aria-label="...">
        <ul class="pagination">
            <li class="page-item <c:if test="${page == 1}">disabled</c:if>">
                <a class="page-link" href="/backend/user/list?page=${page - 1}&perpage=${perpage}" tabindex="-1" aria-disabled="true">Previous</a>
            </li>
            <c:forEach begin="1" end="${total}" step="1" var="num">
                <li class="page-item <c:if test="${page == num}">active</c:if> ">
                    <a class="page-link" href="/backend/user/list?page=${num}&perpage=${perpage}">${num}</a></li>
            </c:forEach>

            <li class="page-item <c:if test="${page == total}">disabled</c:if>">
                <a class="page-link" href="/backend/user/list?page=${page + 1}&perpage=${perpage}">Next</a>
            </li>
        </ul>
    </nav>--%>
</div>
<jsp:include page="/WEB-INF/jsp/common/footer.jsp"></jsp:include>
<script>
    $('[data-toggle=confirmation]').confirmation({
        rootSelector: '[data-toggle=confirmation]',
        onConfirm: function() {
            $('[data-toggle=confirmation]').confirmation('hide');
        },
        popout: true
    });
</script>