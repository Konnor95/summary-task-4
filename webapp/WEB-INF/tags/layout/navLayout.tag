<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="translations"/>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@attribute name="body" fragment="true" required="true" %>
<%@attribute name="topNav" fragment="true"  required="true" %>
<%@attribute name="sideNav" fragment="true"  required="true" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="bottom" fragment="true" %>
<%@attribute name="actionButtons" fragment="true" %>
<%@attribute name="title" required="true" %>
<%@attribute name="home" required="true" %>
<l:layout title="${title}">
    <jsp:attribute name="body">
        <div id="wrapper">
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only"><fmt:message key="text.toggleNavigation"/></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<t:url value="${home}"/>"><fmt:message key="text.siteTitle"/> </a>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <jsp:invoke fragment="topNav"/>
                </ul>
                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                            <jsp:invoke fragment="sideNav"/>
                        </ul>
                    </div>
                </div>
            </nav>
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">${title}</h1>
                        <c:if test="${!empty actionButtons}">
                            <div class="action-buttons">
                                <jsp:invoke fragment="actionButtons"/>
                            </div>
                        </c:if>
                    </div>
                </div>
                <jsp:invoke fragment="body"/>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="head">
        <jsp:invoke fragment="head"/>
    </jsp:attribute>
     <jsp:attribute name="bottom">
        <jsp:invoke fragment="bottom"/>
    </jsp:attribute>
</l:layout>