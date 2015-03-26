<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="text.authors"/>
<d:layout title="${title}">
    <jsp:attribute name="actionButtons">
        <button type="button" class="btn  btn-success link-button">
            <a href="<t:url value="/dashboard/authors/add"/>">
                <fmt:message key="text.add.author"/>
            </a>
        </button>
    </jsp:attribute>
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <u:message result="${requestScope.result}"/>
                <c:if test="${!empty requestScope.authors}">
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th><fmt:message key="text.fullName"/></th>
                                <th><fmt:message key="text.description"/></th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.authors}" var="author">
                                <tr>
                                    <td>${author.id}</td>
                                    <td>${author.name.value(sessionScope.currentLocale)}</td>
                                    <td>${author.description.value(sessionScope.currentLocale)}</td>
                                    <td>
                                        <a href="<t:url value="/dashboard/authors/edit"/>?id=${author.id}">
                                            <fmt:message key="text.edit"/>
                                        </a>

                                        <a href="<t:url value="/dashboard/authors/delete"/>?id=${author.id}">
                                            <fmt:message key="text.delete"/>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
        </div>
  </jsp:attribute>
</d:layout>