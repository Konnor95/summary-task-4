<%@include file="../../includes/main.jsp" %>
<fmt:message var="title" key="text.profile"/>
<m:layout title="${title}" description="${title}">
    <jsp:attribute name="body">
        <div id="breadcrumb-container">
            <div class="container">
                <ul class="breadcrumb">
                    <li><a href="<t:url value="/"/>"><fmt:message key="text.home"/></a></li>
                    <li class="active">${title}</li>
                </ul>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <header class="content-title">
                        <h1 class="title">${title}</h1>
                    </header>
                    <div class="xs-margin"></div>
                    <div class="tab-container left clearfix">
                        <ul class="nav-tabs">
                            <li class="active">
                                <a href="#tab1" data-toggle="tab"><fmt:message key="text.personalInfo"/></a>
                            </li>
                            <c:if test="${!empty requestScope.ordered}">
                                <li>
                                    <a href="#tab2" data-toggle="tab"><fmt:message key="text.books.ordered"/></a>
                                </li>
                            </c:if>
                            <c:if test="${!empty requestScope.checkedOut}">
                                <li><a href="#tab3" data-toggle="tab"><fmt:message key="text.books.checkedOut"/> </a>
                                </li>
                            </c:if>
                        </ul>
                        <div class="tab-content clearfix">
                            <div class="tab-pane active" id="tab1">
                                <ul class="product-list">
                                    <li><span><fmt:message
                                            key="text.firstName"/>:</span>${requestScope.reader.firstName}</li>
                                    <li><span><fmt:message
                                            key="text.lastName"/>:</span>${requestScope.reader.lastName}</li>
                                    <li><span><fmt:message
                                            key="text.login"/>:</span>${requestScope.reader.login}</li>
                                    <li><span><fmt:message
                                            key="text.subscription.id"/>:</span>${requestScope.reader.subscription.id}
                                    </li>
                                    <li><span><fmt:message key="text.subscription.expires"/>:</span>
                                        <fmt:formatDate
                                                value="${requestScope.reader.subscription.expirationDate}"
                                                pattern="dd.MM.yyyy"/>
                                    </li>
                                </ul>
                                <br>
                                <a href="<t:url value="/logout"/>" class="btn btn-custom">
                                    <fmt:message key="text.log-out"/>
                                </a>
                            </div>
                            <c:if test="${!empty requestScope.ordered}">
                                <div class="tab-pane" id="tab2">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th class="table-title">
                                                <fmt:message key="text.book"/>
                                            </th>
                                            <th class="table-title">
                                                <fmt:message key="text.takeBefore"/>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${requestScope.ordered}" var="order">
                                            <tr>
                                                <td>
                                                    <div style="max-width: 180px; display: inline-block">
                                                        <a href="<t:url value="/book?id=${order.bookId}"/>">
                                                            <img src="<c:url value="/uploads/books/${order.book.image}"/>">
                                                        </a>
                                                        <br>
                                                        <a href="<t:url value="/book?id=${order.bookId}"/>">
                                                                ${order.book.title.value(sessionScope.currentLocale)}
                                                        </a>
                                                    </div>
                                                </td>
                                                <td>
                                                    <fmt:formatDate value="${order.dueDate}" pattern="dd.MM.yyyy"/>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:if>
                            <c:if test="${!empty requestScope.checkedOut}">
                                <div class="tab-pane" id="tab3">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th class="table-title">
                                                <fmt:message key="text.book"/>
                                            </th>
                                            <th class="table-title">
                                                <fmt:message key="text.returnBefore"/>
                                            </th>
                                            <th class="table-title">
                                                <fmt:message key="text.fee"/>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${requestScope.checkedOut}" var="order">
                                            <tr>
                                                <td>
                                                    <div style="max-width: 180px; display: inline-block">
                                                        <a href="<t:url value="/book?id=${order.bookId}"/>">
                                                            <img src="<c:url value="/uploads/books/${order.book.image}"/>">
                                                        </a>
                                                        <br>
                                                        <a href="<t:url value="/book?id=${order.bookId}"/>">
                                                                ${order.book.title.value(sessionScope.currentLocale)}
                                                        </a>
                                                    </div>
                                                </td>
                                                <td>
                                                    <fmt:formatDate value="${order.dueDate}" pattern="dd.MM.yyyy"/>
                                                </td>
                                                <td>${order.fee}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="lg-margin visible-xs"></div>
                </div>
            </div>
            <div class="lg-margin"></div>
        </div>
    </jsp:attribute>
</m:layout>