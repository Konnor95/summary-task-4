<%@include file="../../../includes/library.jsp" %>
<fmt:message var="title" key="text.books.ordered"/>
<lb:layout title="${title}">
  <jsp:attribute name="body">
      <div class="row">
          <div class="col-lg-12">
              <u:message result="${requestScope.result}"/>
              <c:if test="${!empty requestScope.userBooks}">
                  <div class="table-responsive">
                      <table class="table table-bordered table-hover">
                          <thead>
                          <tr>
                              <th>#</th>
                              <th><fmt:message key="text.users"/></th>
                              <th><fmt:message key="text.subscription.id"/></th>
                              <th><fmt:message key="text.books"/></th>
                              <th><fmt:message key="text.fee"/></th>
                          </tr>
                          </thead>
                          <tbody>
                          <c:forEach items="${requestScope.userBooks}" var="userBook">
                              <tr>
                                  <td>${userBook.id}</td>
                                  <td>${userBook.login}</td>
                                  <td>${userBook.subscription.id}</td>
                                  <td>
                                      <c:forEach items="${userBook.books}" var="book" varStatus="status">
                                          ${book.title.value(sessionScope.currentLocale)}
                                          <c:if test="${!status.last}">, </c:if>
                                      </c:forEach>
                                  </td>
                                  <td>${userBook.feeSum}</td>
                              </tr>
                          </c:forEach>
                          </tbody>
                      </table>
                  </div>
              </c:if>
          </div>
      </div>
  </jsp:attribute>
</lb:layout>