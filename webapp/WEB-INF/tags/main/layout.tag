<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://www.SummatyTask4.ua/func" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="translations"/>
<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@attribute name="body" fragment="true" required="true" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="bottom" fragment="true" %>
<%@attribute name="title" required="true" %>
<%@attribute name="description" required="true" %>
<jsp:useBean id="today" class="java.util.Date" scope="page"/>
<!DOCTYPE html>
<!--[if IE 8]> <html class="ie8" lang="${sessionScope.currentLocale}"> <![endif]-->
<!--[if IE 9]> <html class="ie9" lang="${sessionScope.currentLocale}"> <![endif]-->
<!--[if !IE]><!-->
<html lang="${sessionScope.currentLocale}"> <!--<![endif]-->
<%--<!--suppress HtmlUnknownTarget -->--%>
<head>
    <meta charset="utf-8">
    <title>${title}</title>
    <meta name="description" content="<c:out value="${description}"/>">
    <!--[if IE]>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"> <![endif]-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/assets/main/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/main/css/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/main/css/owl.carousel.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/main/css/style.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/main/css/responsive.min.css"/>">
    <link href="<c:url value="/assets/img/icon.png"/>" rel="icon">
    <script src="<c:url value="/assets/main/js/jquery-1.11.0.min.js"/>"></script>
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/assets/dashboard/js/ie/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/dashboard/js/ie/respond.min.js"></script>
    <![endif]-->
    <jsp:invoke fragment="head"/>
</head>
<body>
<div id="wrapper">
    <header id="header">
        <div id="inner-header">
            <div id="main-nav-container">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12 clearfix">
                            <div class="col-md-2 col-sm-12 col-xs-12 logo-container">
                                <h1 class="logo clearfix">
                                    <span><fmt:message key="text.siteTitle"/></span>
                                    <a href="<t:url value="/"/>" title="<fmt:message key="text.home"/>">
                                        <img src="<c:url value="/assets/img/logo.png"/>" height="46">
                                    </a>
                                </h1>
                            </div>
                            <nav id="main-nav">
                                <div id="responsive-nav">
                                    <div id="responsive-nav-button">
                                        Menu <span id="responsive-nav-button-icon"></span>
                                    </div>
                                </div>
                                <ul class="menu clearfix">
                                    <li>
                                        <a class="active" href="<t:url value="/"/>">
                                            <fmt:message key="text.home"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="<t:url value="/books"/>">
                                            <fmt:message key="text.books"/>
                                        </a>
                                    </li>
                                    <li>
                                        <c:choose>
                                            <c:when test="${empty sessionScope.currentUser}">
                                                <a href="<t:url value="/login"/>">
                                                    <fmt:message key="text.log-in"/>
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="<t:url value="/profile"/>">
                                                    <fmt:message key="text.profile"/>
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </li>
                                </ul>
                            </nav>
                            <div class="header-top-right">
                                <div class="header-top-dropdowns pull-right">
                                    <div class="btn-group dropdown-language">
                                        <button type="button" class="btn btn-custom dropdown-toggle"
                                                data-toggle="dropdown">
                                        <span class="flag-container">
                                             <img class="locale-flag"
                                                  src="<c:url value="/assets/img/locales/${sessionScope.currentLocale}.png"/>"/>
                                        </span>
                                            <span class="hide-for-xs">${applicationScope.locales[sessionScope.currentLocale]}</span>
                                        </button>
                                        <ul class="dropdown-menu pull-right" role="menu">
                                            <c:forEach items="${applicationScope.locales}" var="locale"
                                                       varStatus="status">
                                                <c:if test="${locale.key != sessionScope.currentLocale}">
                                                    <li>
                                                        <a href="<t:changeLocaleLink locale="${locale.key}"/>">
                                                        <span class="flag-container">
                                                            <img class="locale-flag"
                                                                 src="<c:url value="/assets/img/locales/${locale.key}.png"/>"/>
                                                        </span>
                                                            <span class="hide-for-xs">${locale.value}</span>
                                                        </a>
                                                    </li>
                                                </c:if>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${!empty sessionScope.currentUser}">
                                <div id="quick-access">
                                    <div class="dropdown-cart-menu-container pull-right">
                                        <div class="btn-group dropdown-cart">
                                            <a href="<t:url value="/cart"/>" class="btn btn-custom dropdown-toggle">
                                                <span class="cart-menu-icon"></span>
                                                <span><fmt:message key="text.cart"/>: </span>
                                                <span>${requestScope.orderedBooksCount}</span>
                                                <fmt:message
                                                        key="text.books.multiple${f:getNumberGroup(requestScope.orderedBooksCount)}"/>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <section id="content">
        <jsp:invoke fragment="body"/>
    </section>
    <footer id="footer">
        <div id="footer-bottom">
            <div class="container">
                <div class="row">
                    <div class="col-md-7 col-sm-7 col-xs-12 footer-social-links-container">
                        <ul class="social-links clearfix">
                            <li><a href="#" class="social-icon icon-facebook"></a></li>
                            <li><a href="#" class="social-icon icon-twitter"></a></li>
                            <li><a href="#" class="social-icon icon-rss"></a></li>
                            <li><a href="#" class="social-icon icon-delicious"></a></li>
                            <li><a href="#" class="social-icon icon-linkedin"></a></li>
                            <li><a href="#" class="social-icon icon-flickr"></a></li>
                            <li><a href="#" class="social-icon icon-skype"></a></li>
                            <li><a href="#" class="social-icon icon-email"></a></li>
                        </ul>
                    </div>
                    <div class="col-md-5 col-sm-5 col-xs-12 footer-text-container">
                        <p>&copy; <span><fmt:formatDate value="${today}" pattern="yyyy"/> </span> <fmt:message
                                key="text.siteTitle"/>. <fmt:message key="text.allRightsReserved"/>.</p>
                    </div>
                </div>
            </div>
        </div>
    </footer>
</div>
<a href="#" id="scroll-top" title="Scroll to Top"><i class="fa fa-angle-up"></i></a>
<script src="<c:url value="/assets/main/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/assets/main/js/smoothscroll.min.js"/>"></script>
<script src="<c:url value="/assets/main/js/jquery.placeholder.min.js"/>"></script>
<script src="<c:url value="/assets/main/js/jquery.hoverIntent.min.js"/>"></script>
<script src="<c:url value="/assets/main/js/jquery.flexslider.min.js"/>"></script>
<script src="<c:url value="/assets/main/js/owl.carousel.min.js"/>"></script>
<script src="<c:url value="/assets/main/js/main.min.js"/>"></script>
<jsp:invoke fragment="bottom"/>
</body>
</html>