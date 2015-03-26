<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="role.notConfirmedMultiple"/>
<d:layout title="${title}">
  <jsp:attribute name="body">
      <div class="row">
          <div class="col-lg-12">
              <u:message result="${requestScope.result}"/>
              <c:if test="${!empty requestScope.users}">
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
                          <c:forEach items="${requestScope.users}" var="user">
                              <tr>
                                  <td>${user.id}</td>
                                  <td>${user.firstName}</td>
                                  <td>${user.lastName}</td>
                                  <td>${user.email}</td>
                                  <td>${user.login}</td>
                                  <td>
                                      <a href="<t:url value="/dashboard/users/confirm"/>?id=${user.id}">
                                          <fmt:message key="text.confirm"/>
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