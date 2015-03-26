<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="text.edit.author"/>
<fmt:message var="formDescription" key="text.description"/>
<d:layout title="${title}">
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <u:message result="${requestScope.result}"/>
                <form action="<t:url value="/dashboard/authors/edit?id=${requestScope.author.id}"/>" method="post"
                      enctype="multipart/form-data">
                    <input type="hidden" name="name_id" value="${requestScope.author.name.id}">
                    <input type="hidden" name="description_id" value="${requestScope.author.description.id}">
                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <u:text label="text.fullName" localeKey="${locale.key}" localeValue="${locale.value}" id="name"
                                value="${requestScope.author.name.value(locale.key)}"
                                required="true"/>
                    </c:forEach>

                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <u:textarea label="text.description" localeKey="${locale.key}" localeValue="${locale.value}" id="description"
                                    value="${requestScope.author.description.value(locale.key)}"/>
                    </c:forEach>
                    <d:imageUpload id="image"
                                   subFolder="authors"
                                   path="${requestScope.author.image}"/>

                    <u:submit link="/dashboard/authors"/>
                </form>
            </div>
        </div>
  </jsp:attribute>
</d:layout>