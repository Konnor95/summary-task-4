<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="text.add.author"/>
<d:layout title="${title}">
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <u:message result="${requestScope.result}"/>
                <form action="<t:url value="/dashboard/authors/add"/>" method="post" enctype="multipart/form-data">
                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <u:text label="text.fullName" localeKey="${locale.key}" localeValue="${locale.value}" id="name"
                                value="${requestScope.author.name.value(locale.key)}"
                                required="true"/>
                    </c:forEach>
                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <u:textarea label="text.description" localeKey="${locale.key}" localeValue="${locale.value}" id="description"
                                    value="${requestScope.author.description.value(locale.key)}"/>
                    </c:forEach>
                    <d:imageUpload id="image"/>
                    <u:submit link="/dashboard/authors"/>
                </form>
            </div>
        </div>
  </jsp:attribute>
</d:layout>