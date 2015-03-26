<%@include file="../../../../includes/library.jsp" %>
<fmt:message var="title" key="text.completeOrder"/>
<lb:layout title="${title} ${requestScope.order.id}">
  <jsp:attribute name="body">
       <form action="<t:url value="/library/orders/checked-out/complete"/>" method="post">
           <div class="row">
               <div class="col-lg-12">
                   <p>
                       <fmt:message key="text.completeOrderLeft"/> <span> ${requestScope.order.id}</span>?
                   </p>
               </div>
           </div>
           <div class="row">
               <div class="col-lg-12">
                   <input type="hidden" name="id" value="${requestScope.order.id}">
                   <u:submit link="/library/orders/checked-out"/>
               </div>
           </div>
       </form>
  </jsp:attribute>
</lb:layout>
