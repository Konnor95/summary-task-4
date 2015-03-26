<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="result" required="true" type="ua.nure.bekuzarov.SummaryTask4.util.ProcessResult" %>
<c:if test="${!empty result}">
    <div class="alert alert-dismissable ${result.succeeded ? "alert-success" : "alert-danger"}">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <span>${result.message}</span>
    </div>
</c:if>