<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="text.delete.admin"/>
<d:layout title="${title}">
  <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <p>
                    <fmt:message key="text.delete.adminLeftPart"/>
                <span>
                    ${requestScope.admin.login}
                    (${requestScope.admin.firstName}&nbsp;${requestScope.admin.lastName})?
                </span> <fmt:message key="text.delete.adminRightPart"/>
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form action="<t:url value="/dashboard/users/administrators/delete?id=${requestScope.admin.id}"/>" method="post">
                    <u:submit link="/dashboard/users/administrators"/>
                </form>
            </div>
        </div>
  </jsp:attribute>
</d:layout>
