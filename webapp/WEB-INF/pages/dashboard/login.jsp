<%@include file="../../includes/header.jsp" %>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@taglib prefix="u" tagdir="/WEB-INF/tags/util" %>
<fmt:message var="title" key="text.log-in"/>
<l:layout title="${title}">
  <jsp:attribute name="body">
   <div class="container">
       <div class="row">
           <div class="col-md-4 col-md-offset-4">
               <div class="login-panel panel panel-default">
                   <div class="panel-heading">
                       <h3 class="panel-title"><fmt:message key="text.dashboard"/></h3>
                   </div>
                   <div class="panel-body">
                       <form role="form" action="login" method="post">
                           <span class="error">${requestScope.messages.message}</span>
                           <fieldset>
                               <u:text label="text.login" id="login" value="${requestScope.login}" autofocus="true"/>
                               <u:password label="text.password" id="password" value="${requestScope.password}"/>
                               <button type="submit" class="btn btn-lg btn-success btn-block">
                                   <fmt:message key="text.log-in"/>
                               </button>
                           </fieldset>
                       </form>
                   </div>
               </div>
           </div>
       </div>
   </div>
  </jsp:attribute>
</l:layout>