<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="text.publishers"/>
<d:layout title="${title}">
    <jsp:attribute name="actionButtons">
        <button type="button" class="btn  btn-success link-button">
            <a href="<t:url value="/dashboard/publishers/add"/>">
                <fmt:message key="text.add.publisher"/>
            </a>
        </button>
    </jsp:attribute>
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <u:message result="${requestScope.result}"/>
                <c:if test="${!empty requestScope.publishers}">
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th><fmt:message key="text.title"/></th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.publishers}" var="publisher">
                                <tr>
                                    <td>${publisher.id}</td>
                                    <td>${publisher.title.value(sessionScope.currentLocale)}</td>
                                    <td>
                                        <a href="<t:url value="/dashboard/publishers/edit"/>?id=${publisher.id}">
                                            <fmt:message key="text.edit"/>
                                        </a>
                                        <a href="<t:url value="/dashboard/publishers/delete"/>?id=${publisher.id}">
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