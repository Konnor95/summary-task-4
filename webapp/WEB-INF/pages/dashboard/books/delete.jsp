<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="text.delete.book"/>
<d:layout title="${title}">
  <jsp:attribute name="body">
        <div class="row">
          <div class="col-lg-12">
            <p>
              <fmt:message key="text.delete.bookLeftPart"/>
              <span> ${requestScope.book.title.value(requestScope.currentLocale)}</span>? <fmt:message
                    key="text.delete.bookRightPart"/>
            </p>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-12">
            <form action="<t:url value="/dashboard/books/delete?id=${requestScope.book.id}"/>" method="post">
            <u:submit link="/dashboard/books"/>
            </form>
          </div>
        </div>
  </jsp:attribute>
</d:layout>
