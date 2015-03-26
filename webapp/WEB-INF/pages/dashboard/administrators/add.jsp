<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="text.add.admin"/>
<d:layout title="${title}">
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <u:message result="${requestScope.result}"/>

                <form action="<t:url value="/dashboard/users/administrators/add"/>" method="post">
                    <u:text label="text.firstName" id="firstName" value="${requestScope.admin.firstName}"/>
                    <u:text label="text.lastName" id="lastName" value="${requestScope.admin.lastName}"/>
                    <u:text label="text.email" id="email" value="${requestScope.admin.email}"/>
                    <u:text label="text.login" id="login" value="${requestScope.admin.login}"/>
                    <u:password label="text.password" id="password" value="${requestScope.admin.password}"/>
                    <u:submit link="/dashboard/users/administators"/>
                </form>
            </div>
        </div>
    </jsp:attribute>
</d:layout>