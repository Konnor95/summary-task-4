<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="role.librarianMultiple"/>
<d:layout title="${title}">
     <jsp:attribute name="actionButtons">
        <button type="button" class="btn  btn-success link-button">
            <a href="<t:url value="/dashboard/users/librarians/add"/>">
                <fmt:message key="text.add.librarian"/>
            </a>
        </button>
    </jsp:attribute>
  <jsp:attribute name="body">
      <div class="row">
          <div class="col-lg-12">
              <u:message result="${requestScope.result}"/>
              <c:if test="${!empty requestScope.librarians}">
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
                          <c:forEach items="${requestScope.librarians}" var="librarian">
                              <tr>
                                  <td>${librarian.id}</td>
                                  <td>${librarian.firstName}</td>
                                  <td>${librarian.lastName}</td>
                                  <td>${librarian.email}</td>
                                  <td>${librarian.login}</td>
                                  <td>
                                      <a href="<t:url value="/dashboard/users/librarians/edit"/>?id=${librarian.id}">
                                          <fmt:message key="text.edit"/>
                                      </a>
                                      <a href="<t:url value="/dashboard/users/librarians/delete"/>?id=${librarian.id}">
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