<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="text.edit.book"/>
<jsp:useBean id="today" class="java.util.Date" scope="page"/>
<fmt:formatDate value="${today}" pattern="yyyy" var="yearValue"/>
<d:layout title="${title}">
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <u:message result="${requestScope.result}"/>
                <form action="<t:url value="/dashboard/books/edit?id=${requestScope.book.id}"/>" method="post"
                      enctype="multipart/form-data">
                    <input type="hidden" name="title_id" value="${requestScope.author.name.id}">
                    <input type="hidden" name="description_id" value="${requestScope.author.description.id}">
                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <u:text label="text.title" localeKey="${locale.key}" localeValue="${locale.value}" id="title"
                                value="${requestScope.book.title.value(locale.key)}"
                                required="true"/>
                    </c:forEach>
                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <u:textarea label="text.description" localeKey="${locale.key}" localeValue="${locale.value}"
                                    id="description"
                                    value="${requestScope.book.description.value(locale.key)}"/>
                    </c:forEach>
                    <u:int label="text.bookAmount" id="amount" min="0" value="${requestScope.book.amount}"/>
                    <u:int label="text.bookYear" id="year" min="0" max="${yearValue}"
                           value="${requestScope.book.year}" required="true"/>
                    <u:int label="text.pagesCount" id="pages" min="1" value="${requestScope.book.pages}"
                           required="true"/>
                    <d:chosen label="text.authors" placeholder="text.chooseAuthors" required="true" id="authors"
                              multiple="true">
                        <jsp:attribute name="body">
                           <c:forEach items="${requestScope.authors}" var="author">
                               <option value="${author.id}"
                                       <c:if test="${f:contains(requestScope.book.authors,author)==true}">selected</c:if>>
                                       ${author.name.value(sessionScope.currentLocale)}
                               </option>
                           </c:forEach>
                        </jsp:attribute>
                    </d:chosen>
                    <d:chosen label="text.publishers" placeholder="text.choosePublisher" required="true"
                              id="publisherId">
                        <jsp:attribute name="body">
                           <c:forEach items="${requestScope.publishers}" var="publisher">
                               <option value="${publisher.id}"
                                       <c:if test="${publisher.id eq requestScope.book.publisher.id}">selected</c:if>>
                                       ${publisher.title.value(sessionScope.currentLocale)}
                               </option>
                           </c:forEach>
                        </jsp:attribute>
                    </d:chosen>
                    <d:imageUpload id="image" subFolder="books" path="${requestScope.book.image}"/>
                    <u:submit link="/dashboard/books"/>
                </form>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="head">
          <link href="<c:url value="/assets/dashboard/css/chosen.min.css"/>" rel="stylesheet">
    </jsp:attribute>
    <jsp:attribute name="bottom">
        <script src="<c:url value="/assets/dashboard/js/chosen.jquery.min.js"/>"></script>
    </jsp:attribute>
</d:layout>