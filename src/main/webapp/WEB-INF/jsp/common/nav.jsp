<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="header-area">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="user-menu">
                    <ul>

<%--                    <security:authorize access="hasRole('CUSTOMER')">--%>
<%--                        <li><a href="#"><i class="fa fa-user"></i>Tài khoản</a></li>--%>
<%--                        <li><a href="cart.html"><i class="fa fa-user"></i>Giỏ hàng</a></li>--%>
<%--                        <li><a href="checkout.html"><i class="fa fa-user"></i> Thanh toán</a></li>--%>
<%--                    </security:authorize>--%>
                        <security:authorize access="isAuthenticated()">
                            <li><a href="#"><i class="fa fa-user">

                            </i><security:authentication property="principal.fullName"></security:authentication></a></li>
                            <li><a href="/cart"><i class="fa fa-user"></i>Giỏ hàng</a></li>
                            <li><a href="/checkout"><i class="fa fa-user"></i> Thanh toán</a></li>
                            <li><a href="/logout"><i class="fa fa-user"></i> Đăng xuất</a></li>
                        </security:authorize>
                        <security:authorize access="!isAuthenticated()">
                            <li><a href="/login"><i class="fa fa-user"></i> Đăng nhập</a></li>
                        </security:authorize>

                    </ul>
                </div>
            </div>

            <div class="col-md-4">
                <div class="header-right">
                    <ul class="list-unstyled list-inline">
                        <li class="dropdown dropdown-small">
                            <a data-toggle="dropdown" data-hover="dropdown" class="dropdown-toggle" href="#"><span class="key">
                                <spring:message code="currency"></spring:message> :</span><span class="value">
                                     <c:if test="${pageContext.response.locale == 'en'}">
                                         USD
                                     </c:if>
                                    <c:if test="${pageContext.response.locale == 'vi'}">
                                        VND
                                    </c:if>
                            </span><b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">USD</a></li>
                                <li><a href="#">VND</a></li>
                            </ul>
                        </li>

                        <li class="dropdown dropdown-small">
                            <a data-toggle="dropdown" data-hover="dropdown" class="dropdown-toggle" href="#"><span class="key"> <spring:message code="language"></spring:message> :</span>
                                <span class="value">
                                    <c:if test="${pageContext.response.locale == 'en'}">
                                        <spring:message code="english"></spring:message>
                                    </c:if>
                                    <c:if test="${pageContext.response.locale == 'vi'}">
                                        <spring:message code="vietnam"></spring:message>
                                    </c:if>
                                </span><b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="?lang=en">English</a></li>
                                <li><a  href="?lang=vi">Việt Nam</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End header area -->
<div class="site-branding-area">
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <div class="logo">
                    <h1><a href="./"><img src="/img/logo.png"></a></h1>
                </div>
            </div>

            <div class="col-sm-6">
                <div class="shopping-item">
                    <a href="/cart">Cart - <span class="cart-amunt">$100</span> <i class="fa fa-shopping-cart"></i> <span class="product-count">5</span></a>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End site branding area -->
<div class="mainmenu-area">
    <div class="container">
        <div class="row">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="index.html">Home</a></li>
                    <li><a href="shop.html">Shop page</a></li>
                    <li><a href="single-product.html">Single product</a></li>
                    <li><a href="cart">Cart</a></li>
                    <li><a href="checkout">Checkout</a></li>
                    <li><a href="#">Category</a></li>
                    <li><a href="#">Others</a></li>
                    <li><a href="#">Contact</a></li>
                </ul>
            </div>
        </div>
    </div>
</div> <!-- End mainmenu area -->