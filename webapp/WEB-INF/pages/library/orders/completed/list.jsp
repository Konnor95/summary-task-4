<%@include file="../../../../includes/library.jsp" %>
<fmt:message var="title" key="text.orders.completed"/>
<lb:layout title="${title}">
    <jsp:attribute name="actionButtons">
        <button type="button" class="btn btn-primary link-button">
            <a href="<t:url value="/library/orders/completed/report.odt"/>">
                <i class="fa fa-file-word-o"> </i> ODT
            </a>
        </button>
         <button type="button" class="btn btn-danger link-button">
             <a href="<t:url value="/library/orders/completed/report.pdf"/>" target="_blank">
                 <i class="fa fa-file-pdf-o"> </i> PDF
             </a>
         </button>
    </jsp:attribute>
  <jsp:attribute name="body">
      <div class="row">
          <div class="col-lg-12">
              <u:message result="${requestScope.result}"/>
              <c:if test="${!empty requestScope.orders}">
                  <div class="table-responsive">
                      <table class="table table-bordered table-hover">
                          <thead>
                          <tr>
                              <th>#</th>
                              <th><fmt:message key="text.book"/></th>
                              <th><fmt:message key="text.subscription.id"/></th>
                          </tr>
                          </thead>
                          <tbody>
                          <c:forEach items="${requestScope.orders}" var="order">
                              <tr>
                                  <td>${order.id}</td>
                                  <td>${order.bookId}</td>
                                  <td>${order.subscriptionId}</td>
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