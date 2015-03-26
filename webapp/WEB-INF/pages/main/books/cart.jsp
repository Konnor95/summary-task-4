<%@include file="../../../includes/main.jsp" %>
<fmt:message var="title" key="text.cart"/>
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
                    <c:choose>
                        <c:when test="${empty requestScope.orderedBooks}">
                            <header class="content-title">
                                <h1 class="title">${title}</h1>

                                <p class="title-desc">
                                    <fmt:message key="text.emptyCart"/>
                                </p>
                            </header>
                            <div class="xs-margin"></div>

                            <a href="<t:url value="/"/>" class="btn btn-custom-2 btn-lg md-margin">
                                <fmt:message key="text.backToHomePage"/>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <header class="content-title">
                                <h1 class="title">${title}</h1>

                                <p class="title-desc">
                                    <c:choose>
                                        <c:when test="${empty requestScope.result}">
                                            <fmt:message key="text.cartMainTip"/>
                                        </c:when>
                                        <c:otherwise>
                                            ${requestScope.result.message}
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </header>
                            <div class="xs-margin"></div>
                            <form action="<t:url value="/cart"/>" method="post">
                                <div class="row">
                                    <div class="col-md-12 table-responsive">
                                        <table class="table cart-table">
                                            <thead>
                                            <tr>
                                                <th class="table-title">
                                                    <fmt:message key="text.book"/>
                                                </th>
                                                <th class="table-title">
                                                    <fmt:message key="text.inStock"/>
                                                </th>
                                                <th class="table-title">
                                                    <fmt:message key="text.quantity"/>
                                                </th>
                                                <th class="table-title">
                                                    <fmt:message key="text.delete"/>
                                                </th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${requestScope.books}" var="book">
                                                <tr id="row${book.id}">
                                                    <td class="item-name-col">
                                                        <figure>
                                                            <a href="<t:url value="/book?id=${book.id}"/>">
                                                                <img src="<c:url value="/uploads/books/${book.image}"/>">
                                                            </a>
                                                        </figure>
                                                        <header class="item-name">
                                                            <a href="<t:url value="/book?id=${book.id}"/>">
                                                                    ${book.title.value(sessionScope.currentLocale)}
                                                            </a>
                                                        </header>
                                                        <ul>
                                                            <li>
                                                               <span>
                                                                    <c:choose>
                                                                        <c:when test="${book.authors.size()>1}">
                                                                            <b><fmt:message key="text.authors"/></b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b><fmt:message key="text.author"/></b>
                                                                        </c:otherwise>
                                                                    </c:choose>:
                                                                </span>
                                                                <c:forEach items="${book.authors}" var="author"
                                                                           varStatus="status">
                                                                    <a href="<t:url value="/author?id=${author.id}"/>">
                                                                            ${author.name.value(sessionScope.currentLocale)}
                                                                    </a>
                                                                    <c:if test="${!status.last}">, </c:if>
                                                                </c:forEach>
                                                            </li>
                                                            <li>
                                                                <span><b><fmt:message key="text.publisher"/></b>:</span>
                                                                    ${book.publisher.title.value(sessionScope.currentLocale)}
                                                            </li>
                                                        </ul>
                                                    </td>
                                                    <td class="item-price-col">
                                                        <span>${book.inStock}</span>
                                                    </td>
                                                    <td>
                                                        <div class="custom-quantity-input">
                                                            <input type="hidden" id="quantity-${book.id}"
                                                                   value="${requestScope.orderedBooks.get(book.id)}">
                                                            <input type="text" id="quantityInput-${book.id}"
                                                                   value="${requestScope.orderedBooks.get(book.id)}"
                                                                   disabled title="">
                                                            <a href="#"
                                                               onclick="increment(${book.id}, ${book.inStock},
                                                                       '<t:url value="/cart/update"/>');"
                                                               class="quantity-btn quantity-input-up">
                                                                <i class="fa fa-angle-up"></i>
                                                            </a>
                                                            <a href="#"
                                                               onclick="decrement(${book.id},
                                                                       0,
                                                                       '<t:url value="/cart/update"/>');"
                                                               class="quantity-btn quantity-input-down">
                                                                <i class="fa fa-angle-down"></i>
                                                            </a>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <button onclick="deleteBook(${book.id},'row', '<t:url
                                                                value="/cart/delete"/>');"
                                                                class="close-button"></button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="lg-margin"></div>
                                <div class="row">
                                    <div class="col-md-4 col-sm-12 col-xs-12">
                                        <div class="md-margin"></div>
                                        <button type="submit" class="btn btn-custom">
                                            <fmt:message key="text.submit"/>
                                        </button>
                                    </div>
                                </div>
                                <div class="lg-margin2x"></div>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </jsp:attribute>
</m:layout>