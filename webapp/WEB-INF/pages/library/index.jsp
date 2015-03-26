<%@include file="../../includes/library.jsp" %>
<fmt:message key="text.library" var="title"/>
<lb:layout title="${title}">
    <jsp:attribute name="body">
         <div class="row">
             <u:panel color="primary" icon="shopping-cart" number="${requestScope.orderedCount}"
                      label="text.books.ordered" link="/library/orders/ordered"/>
             <u:panel color="green" icon="book" number="${requestScope.checkedOutCount}"
                      label="text.books.checkedOut" link="/library/orders/checked-out"/>
             <u:panel color="yellow" icon="check-square-o" number="${requestScope.completedCount}"
                      label="text.orders.completed" link="/library/orders/completed"/>
             <u:panel color="red" icon="users" number="${requestScope.readingRoomsCount}"
                      label="text.orders.readingRooms" link="/library/orders/reading-rooms"/>
         </div>
    </jsp:attribute>
</lb:layout>