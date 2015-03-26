<%@include file="../../includes/dashboard.jsp" %>
<fmt:message key="text.dashboard" var="title"/>
<d:layout title="${title}">
    <jsp:attribute name="body">
        <div class="row">
            <u:panel color="primary" icon="book" number="${requestScope.booksCount}" label="text.books"
                     link="/dashboard/books"/>
            <u:panel color="green" icon="pencil-square-o" number="${requestScope.authorsCount}" label="text.authors"
                     link="/dashboard/authors"/>
            <u:panel color="yellow" icon="print" number="${requestScope.publishersCount}" label="text.publishers"
                     link="/dashboard/publishers"/>
            <u:panel color="red" icon="users" number="${requestScope.readersCount}" label="role.userMultiple"
                     link="/dashboard/users"/>
        </div>
    </jsp:attribute>
</d:layout>