<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="text.add.librarian"/>
<d:layout title="${title}">
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <u:message result="${requestScope.result}"/>
                <form action="<t:url value="/dashboard/users/librarians/add"/>" method="post">
                    <u:text label="text.firstName" id="firstName" value="${requestScope.librarian.firstName}"/>
                    <u:text label="text.lastName" id="lastName" value="${requestScope.librarian.lastName}"/>
                    <u:text label="text.email" id="email" value="${requestScope.librarian.email}"/>
                    <u:text label="text.login" id="login" value="${requestScope.librarian.login}"/>
                    <u:password label="text.password" id="password" value="${requestScope.librarian.password}"/>
                    <u:submit link="/dashboard/users/librarians"/>
                </form>
            </div>
        </div>
    </jsp:attribute>
</d:layout>