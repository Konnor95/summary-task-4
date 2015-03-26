<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="text.subscription.extend"/>
<d:layout title="${title}">
  <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <p>
                    <fmt:message key="text.subscription.extendLeftPart"/> <span> ${requestScope.user.login}</span>?
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form action="<t:url value="${requestScope.submitUrl}"/>" method="post">
                    <input type="hidden" name="id" value="${requestScope.user.id}">
                    <u:submit link="${backUrl}"/>
                </form>
            </div>
        </div>
  </jsp:attribute>
</d:layout>
