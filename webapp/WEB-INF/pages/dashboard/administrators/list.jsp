<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="role.adminMultiple"/>
<d:layout title="${title}">
     <jsp:attribute name="actionButtons">
        <button type="button" class="btn  btn-success link-button">
            <a href="<t:url value="/dashboard/users/administrators/add"/>">
                <fmt:message key="text.add.admin"/>
            </a>
        </button>
    </jsp:attribute>
  <jsp:attribute name="body">
      <div class="row">
          <div class="col-lg-12">
              <u:message result="${requestScope.result}"/>
              <c:if test="${!empty requestScope.administrators}">
                  <div class="table-responsive">
                      <table class="table table-bordered table-hover">
                          <thead>
                          <tr>
                              <th>#</th>
                              <th><fmt:message key="text.firstName"/></th>
                              <th><fmt:message key="text.lastName"/></th>
                              <th><fmt:message key="text.email"/></th>
                              <th><fmt:message key="text.login"/></th>
                              <th></th>
                          </tr>
                          </thead>
                          <tbody>
                          <c:forEach items="${requestScope.administrators}" var="admin">
                              <tr>
                                  <td>${admin.id}</td>
                                  <td>${admin.firstName}</td>
                                  <td>${admin.lastName}</td>
                                  <td>${admin.email}</td>
                                  <td>${admin.login}</td>
                                  <td>
                                      <a href="<t:url value="/dashboard/users/administrators/edit"/>?id=${admin.id}">
                                          <fmt:message key="text.edit"/>
                                      </a>
                                      <c:if test="${sessionScope.currentAdmin.id!=admin.id}">
                                          <a href="<t:url value="/dashboard/users/administrators/delete"/>?id=${admin.id}">
                                              <fmt:message key="text.delete"/>
                                          </a>
                                      </c:if>
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