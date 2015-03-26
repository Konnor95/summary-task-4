<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="text.delete.author"/>
<d:layout title="${title}">
  <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <p>
                    <fmt:message key="text.delete.authorLeftPart"/>
                    <span> ${requestScope.author.name.value(requestScope.currentLocale)}</span>? <fmt:message
                        key="text.delete.authorRightPart"/>
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form action="<t:url value="/dashboard/authors/delete?id=${requestScope.author.id}"/>" method="post">
                    <u:submit link="/dashboard/authors"/>
                </form>
            </div>
        </div>
  </jsp:attribute>
</d:layout>
