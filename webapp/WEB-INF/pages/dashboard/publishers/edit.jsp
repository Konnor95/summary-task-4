<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="text.edit.publisher"/>
<fmt:message var="formTitle" key="text.title"/>
<d:layout title="${title}">
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <u:message result="${requestScope.result}"/>
                <form action="<t:url value="/dashboard/publishers/edit?id=${requestScope.publisher.id}"/>"
                      method="post">
                    <input type="hidden" name="title_id" value="${requestScope.publisher.title.id}">
                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <u:text label="text.title" localeKey="${locale.key}" localeValue="${locale.value}" id="title"
                                value="${requestScope.publisher.title.value(locale.key)}"
                                required="true"/>
                    </c:forEach>
                    <u:submit link="/dashboard/publishers"/>
                </form>
            </div>
        </div>
  </jsp:attribute>
</d:layout>