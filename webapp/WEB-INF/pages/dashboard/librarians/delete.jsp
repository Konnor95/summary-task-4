<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="text.delete.librarian"/>
<d:layout title="${title}">
  <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <p>
                    <fmt:message key="text.delete.librarianLeftPart"/>
                <span>
                    ${requestScope.librarian.login}
                    (${requestScope.librarian.firstName}&nbsp;${requestScope.librarian.lastName})?
                </span> <fmt:message key="text.delete.librarianRightPart"/>
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form action="<t:url value="/dashboard/users/librarians/delete?id=${requestScope.librarian.id}"/>" method="post">
                    <u:submit link="/dashboard/users/librarians"/>
                </form>
            </div>
        </div>
  </jsp:attribute>
</d:layout>
