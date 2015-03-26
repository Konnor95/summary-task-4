<%@include file="../../../../includes/library.jsp" %>
<fmt:message var="title" key="text.acceptOrder"/>
<lb:layout title="${title} ${requestScope.order.id}">
  <jsp:attribute name="body">
       <form action="<t:url value="/library/orders/ordered/accept"/>" method="post">
           <div class="row">
               <div class="col-lg-12">
                   <u:int label="text.checkingOutDuration" id="days" max="${requestScope.max}" min="1" required="true"
                          value="${requestScope.days}"/>
               </div>
           </div>
           <div class="row">
               <div class="col-lg-12">
                   <input type="hidden" name="id" value="${requestScope.order.id}">
                   <u:submit link="/library/orders/ordered"/>
               </div>
           </div>
       </form>
  </jsp:attribute>
</lb:layout>
