<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@attribute name="body" fragment="true" required="true" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="bottom" fragment="true" %>
<%@attribute name="title" required="true" %>
<!DOCTYPE html>
<html lang="${sessionScope.currentLocale}">
<%--<!--suppress HtmlUnknownTarget -->--%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title}</title>
    <link href="<c:url value="/assets/img/icon.png"/>" rel="icon">
    <link href="<c:url value="/assets/dashboard/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/dashboard/css/metisMenu.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/dashboard/css/custom.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/dashboard/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
    <%-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries --%>
    <%-- WARNING: Respond.js doesn't work if you view the page via file:// --%>
    <!--[if lt IE 9 ]>
    <script src="${pageContext.request.contextPath}/assets/dashboard/js/ie/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/dashboard/js/ie/respond.min.js"></script>
    <![endif]-->
    <jsp:invoke fragment="head"/>
</head>
<body>
<jsp:invoke fragment="body"/>
<script src="<c:url value="/assets/dashboard/js/jquery.min.js"/>"></script>
<script src="<c:url value="/assets/dashboard/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/assets/dashboard/js/metisMenu.min.js"/>"></script>
<script src="<c:url value="/assets/dashboard/js/custom.min.js"/>"></script>
<jsp:invoke fragment="bottom"/>
</body>
</html>