<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="text.books"/>
<d:layout title="${title}">
    <jsp:attribute name="actionButtons">
        <button type="button" class="btn  btn-success link-button">
            <a href="<t:url value="/dashboard/books/add"/>">
                <fmt:message key="text.add.book"/>
            </a>
        </button>
    </jsp:attribute>
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <u:message result="${requestScope.result}"/>
                <c:if test="${!empty requestScope.books}">
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th><fmt:message key="text.title"/></th>
                                <th><fmt:message key="text.description"/></th>
                                <th><fmt:message key="text.bookAmount"/></th>
                                <th><fmt:message key="text.bookYear"/></th>
                                <th><fmt:message key="text.pagesCount"/></th>
                                <th><fmt:message key="text.authors"/></th>
                                <th><fmt:message key="text.publisher"/></th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.books}" var="book">
                                <tr>
                                    <td>${book.id}</td>
                                    <td>${book.title.value(sessionScope.currentLocale)}</td>
                                    <td>${book.description.value(sessionScope.currentLocale)}</td>
                                    <td>${book.amount}</td>
                                    <td>${book.year}</td>
                                    <td>${book.pages}</td>
                                    <td>
                                        <c:forEach items="${book.authors}" var="author" varStatus="status">
                                            <a href="<t:url value="/dashboard/authors/edit?id=${author.id}"/>">
                                                    ${author.name.value(sessionScope.currentLocale)}
                                            </a><c:if test="${!status.last}">, </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <a href="<t:url value="/dashboard/publishers/edit?id=${book.publisher.id}"/>">
                                                ${book.publisher.title.value(sessionScope.currentLocale)}
                                        </a>
                                    </td>
                                    <td>
                                        <a href="<t:url value="/dashboard/books/edit"/>?id=${book.id}">
                                            <fmt:message key="text.edit"/>
                                        </a>

                                        <a href="<t:url value="/dashboard/books/delete"/>?id=${book.id}">
                                            <fmt:message key="text.delete"/>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
        </div>
  </jsp:attribute>
</d:layout>