<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="text.userUnban"/>
<d:layout title="${title}">
  <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <p>
                    <fmt:message key="text.unbanLeftPart"/> <span> ${requestScope.user.login}</span>? <fmt:message
                        key="text.unbanRightPart"/>
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form action="<t:url value="/dashboard/users/unban"/>" method="post">
                    <input type="hidden" name="id" value="${requestScope.user.id}">
                    <u:submit link="/dashboard/black-list"/>
                </form>
            </div>
        </div>
  </jsp:attribute>
</d:layout>
