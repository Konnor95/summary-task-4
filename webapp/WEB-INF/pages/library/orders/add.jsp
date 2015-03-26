<%@include file="../../../includes/library.jsp" %>
<fmt:message var="title" key="text.checkOutBook"/>
<lb:layout title="${title} ${requestScope.order.id}">
  <jsp:attribute name="body">
       <form action="<t:url value="/library/orders/add"/>" method="post">
           <div class="row">
               <div class="col-lg-12">
                   <u:int label="text.book" id="bookId" min="1" required="true" value="${requestScope.bookId}"/>
                   <u:int label="text.subscription.id" id="subscriptionId" min="1" required="true"
                          value="${requestScope.subscriptionId}"/>
               </div>
           </div>
           <div class="row">
               <div class="col-lg-12">
                   <input type="hidden" name="id" value="${requestScope.order.id}">
                   <u:submit link="/library/orders/add"/>
               </div>
           </div>
       </form>
  </jsp:attribute>
</lb:layout>
