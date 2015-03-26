<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="label" required="true" %>
<%@attribute name="localeKey" %>
<%@attribute name="localeValue" %>
<%@attribute name="id" required="true" %>
<%@attribute name="value" %>
<%@attribute name="placeholder" %>
<%@attribute name="required" type="java.lang.Boolean" %>
<%@attribute name="autofocus" type="java.lang.Boolean" %>
<c:if test="${!empty localeKey}">
    <c:set var="id" value="${id}_${localeKey}"/>
</c:if>
<c:set var="message" value="${requestScope.messages.get(id)}"/>
<div class="form-group ${empty message ? "" : "has-error"}">
    <label class="control-label" for="${id}">
        <fmt:message key="${label}"/>&nbsp;<c:if test="${!empty localeValue}">(${localeValue})</c:if>
        <c:if test="${required=='true'}"><span class="asterix"> *</span></c:if>
    </label>
    <input type="text" class="form-control" id="${id}" name="${id}" placeholder="${placeholder}"
           value="${value}" <c:if test="${autofocus=='true'}">autofocus</c:if>/>
    <label class="error" for="${id}">${message}</label>
</div>