<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="label" required="true" %>
<%@attribute name="id" required="true" %>
<%@attribute name="placeholder" %>
<%@attribute name="value" %>
<%@attribute name="required" type="java.lang.Boolean" %>
<%@attribute name="min" type="java.lang.Integer" %>
<%@attribute name="max" type="java.lang.Integer" %>
<c:set var="message" value="${requestScope.messages.get(id)}"/>
<div class="form-group ${empty message ? "" : "has-error"}">
    <label class="control-label" for="${id}">
        <fmt:message key="${label}"/>
        <c:if test="${required=='true'}"><span class="asterix"> *</span></c:if>
    </label>
    <input type="number" min="${min}" max="${max}" class="form-control" id="${id}" name="${id}"
           placeholder="${placeholder}"
           value="${value}"/>
    <label class="error" for="${id}">${message}</label>
</div>