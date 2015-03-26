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
<l:navLayout title="${title}" home="/library">
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
                    <a href="<t:url value="/library/logout"/>"><i class="fa fa-sign-out fa-fw"></i>
                        <fmt:message key="text.log-out"/>
                    </a>
                </li>
            </ul>
        </li>
    </jsp:attribute>
    <jsp:attribute name="sideNav">
        <li>
            <a href="<t:url value="/library"/>">
                <i class="fa fa-dashboard fa-fw"></i>
                <fmt:message key="text.library"/>
            </a>
        </li>
         <li>
             <a href="<t:url value="/library/orders/add"/>">
                 <i class="fa fa-plus fa-fw"></i>
                 <fmt:message key="text.checkOutBook"/>
             </a>
         </li>
        <li>
            <a href="<t:url value="/library/orders/ordered"/>">
                <i class="fa fa-shopping-cart fa-fw"></i>
                <fmt:message key="text.orders.ordered"/>
            </a>
        </li>
        <li>
            <a href="<t:url value="/library/orders/checked-out"/>">
                <i class="fa fa-book fa-fw"></i>
                <fmt:message key="text.orders.checkedOut"/>
            </a>
        </li>
        <li>
            <a href="<t:url value="/library/orders/completed"/>">
                <i class="fa fa-check-square-o fa-fw"></i>
                <fmt:message key="text.orders.completed"/>
            </a>
        </li>
        <li>
            <a href="<t:url value="/library/orders/reading-rooms"/>">
                <i class="fa fa-users fa-fw"></i>
                <fmt:message key="text.orders.readingRooms"/>
            </a>
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