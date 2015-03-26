<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="text.delete.publisher"/>
<d:layout title="${title}">
  <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <p>
                    <fmt:message key="text.delete.publisherLeftPart"/>
                    <span> ${requestScope.publisher.title.value(requestScope.currentLocale)}</span>? <fmt:message
                        key="text.delete.publisherRightPart"/>
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form action="<t:url value="/dashboard/publishers/delete?id=${requestScope.publisher.id}"/>"
                      method="post">
                    <u:submit link="/dashboard/publishers"/>
                </form>
            </div>
        </div>
  </jsp:attribute>
</d:layout>
