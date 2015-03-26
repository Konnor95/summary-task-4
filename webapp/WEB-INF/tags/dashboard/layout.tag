<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="translations"/>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@attribute name="body" fragment="true" required="true" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="bottom" fragment="true" %>
<%@attribute name="actionButtons" fragment="true" %>
<%@attribute name="title" required="true" %>
<l:navLayout title="${title}" home="/dashboard">
    <jsp:attribute name="topNav">
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-language fa-fw"></i> <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu locales">
                <c:forEach items="${applicationScope.locales}" var="locale" varStatus="status">
                    <li>
                        <a href="<t:changeLocaleLink locale="${locale.key}"/>">
                            <div>
                                <img src="<c:url value="/assets/img/locales/${locale.key}.png"/>"/>
                                <span>${locale.value}</span>
                            </div>
                        </a>
                    </li>
                    <c:if test="${!status.last}">
                        <li class="divider"></li>
                    </c:if>
                </c:forEach>
            </ul>
        </li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li>
                    <a href="<t:url value="/dashboard/logout"/>"><i class="fa fa-sign-out fa-fw"></i>
                        <fmt:message key="text.log-out"/>
                    </a>
                </li>
            </ul>
        </li>
    </jsp:attribute>
    <jsp:attribute name="sideNav">
        <li>
            <a href="<t:url value="/dashboard"/>"><i class="fa fa-dashboard fa-fw"></i>
                <fmt:message key="text.dashboard"/>
            </a>
        </li>
        <li>
            <a href="#">
                <i class="fa fa-users fa-fw"></i>
                <fmt:message key="text.users"/><span class="fa arrow"></span>
            </a>
            <ul class="nav nav-second-level">
                <li>
                    <a href="<t:url value="/dashboard/users/not-confirmed"/>">
                        <fmt:message key="role.notConfirmedMultiple"/>
                    </a>
                </li>
                <li>
                    <a href="<t:url value="/dashboard/users"/>">
                        <fmt:message key="role.userMultiple"/>
                    </a>
                </li>
                <li>
                    <a href="<t:url value="/dashboard/users/librarians"/>">
                        <fmt:message key="role.librarianMultiple"/>
                    </a>
                </li>
                <li>
                    <a href="<t:url value="/dashboard/users/administrators"/>">
                        <fmt:message key="role.adminMultiple"/>
                    </a>
                </li>
                <li>
                    <a href="<t:url value="/dashboard/users/black-list"/>">
                        <fmt:message key="role.bannedMultiple"/>
                    </a>
                </li>
                <li>
                    <a href="<t:url value="/dashboard/users/overdue"/>">
                        <fmt:message key="role.overdue"/>
                    </a>
                </li>
            </ul>
        </li>
        <li>
            <a href="#">
                <i class="fa fa-book fa-fw"></i>
                <fmt:message key="text.library"/><span class="fa arrow"></span>
            </a>
            <ul class="nav nav-second-level">
                <li>
                    <a href="<t:url value="/dashboard/books"/>">
                        <fmt:message key="text.books"/>
                    </a>
                </li>
                <li>
                    <a href="<t:url value="/dashboard/authors"/>">
                        <fmt:message key="text.authors"/>
                    </a>
                </li>
                <li>
                    <a href="<t:url value="/dashboard/publishers"/>">
                        <fmt:message key="text.publishers"/>
                    </a>
                </li>
            </ul>
        </li>
    </jsp:attribute>
    <jsp:attribute name="body">
        <jsp:invoke fragment="body"/>
    </jsp:attribute>
   <jsp:attribute name="actionButtons">
        <jsp:invoke fragment="actionButtons"/>
   </jsp:attribute>
    <jsp:attribute name="head">
        <jsp:invoke fragment="head"/>
    </jsp:attribute>
     <jsp:attribute name="bottom">
        <jsp:invoke fragment="bottom"/>
    </jsp:attribute>
</l:navLayout>