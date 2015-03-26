<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="translations"/>
<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@attribute name="url" required="true" type="java.lang.String" %>
<%@attribute name="max" required="true" type="java.lang.Integer" %>
<c:choose>
    <c:when test="${empty param.search}">
        <c:set var="pSearch" value=""/>
    </c:when>
    <c:otherwise>
        <c:set var="pSearch" value="&search=${param.search}"/>
    </c:otherwise>
</c:choose>
<div class="category-toolbar clearfix">
<c:if test="${max > 1}">
    <div class="toolbox-filter clearfix">
        <c:choose>
            <c:when test="${empty param.orderBy}">
                <c:set var="pOrder" value=""/>
                <fmt:message var="pOrderLabel" key="orderByBook.default"/>
            </c:when>
            <c:otherwise>
                <c:set var="pOrder" value="&orderBy=${param.orderBy}"/>
                <fmt:message var="pOrderLabel" key="orderByBook.${param.orderBy}"/>
            </c:otherwise>
        </c:choose>
        <div class="sort-box">
            <span class="separator"><fmt:message key="text.sortBy"/>:</span>

            <div class="btn-group select-dropdown">
                <button type="button" class="btn select-btn">${pOrderLabel}</button>
                <button type="button" class="btn dropdown-toggle"
                        data-toggle="dropdown">
                    <i class="fa fa-angle-down"></i>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <c:forEach items="${requestScope.sortOrders}" var="sortOrder">
                        <c:if test="${(empty param.orderBy && sortOrder!='default')||(!empty param.orderBy&&sortOrder!=param.orderBy) }">
                            <li>
                                <a href="<t:url value="/books?page=${requestScope.current}&orderBy=${sortOrder}${pSearch}"/>">
                                    <fmt:message key="orderByBook.${sortOrder}"/>
                                </a>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <div class="pull-right">
        <ul class="pagination">
            <c:choose>
                <c:when test="${requestScope.current==1}">
                    <li class="disabled"><a href="#"><i class="fa fa-angle-left"></i></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="<t:url value="${url}?page=${requestScope.current - 1}${pSearch}${pOrder}"/>"><i
                            class="fa fa-angle-left"></i></a></li>
                </c:otherwise>
            </c:choose>
            <c:set var="l" value="${requestScope.current - 4}"/>
            <c:set var="r" value="${requestScope.current + 4}"/>
            <c:set var="left" value="${l > 1}"/>

            <c:if test="${left}">
                <li><a href="<t:url value="${url}?page=1${pSearch}${pOrder}"/>">1</a></li>
                <li class="disabled"><a href="#">...</a></li>
            </c:if>

            <c:set var="right" value="${max > r}"/>
            <c:forEach begin="${left ? l : 1}" end="${right ? r : max}" step="1" var="i">
                <li <c:if test="${i == requestScope.current}">class="active"</c:if>>
                    <a href="<t:url value="${url}?page=${i}${pSearch}${pOrder}"/>">${i}</a>
                </li>
            </c:forEach>

            <c:if test="${right}">
                <li class="disabled"><a href="#">...</a></li>
                <li><a href="<t:url value="${url}?page=${max}${pSearch}${pOrder}"/>">${max}</a></li>
            </c:if>

            <c:choose>
                <c:when test="${requestScope.current==max}">
                    <li class="disabled"><a href="#"><i class="fa fa-angle-right"></i></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="<t:url value="${url}?page=${requestScope.current + 1}${pSearch}${pOrder}"/>"><i
                            class="fa fa-angle-right"></i></a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</c:if>