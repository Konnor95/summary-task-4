<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="label" required="true" %>
<%@attribute name="placeholder" required="true" %>
<%@attribute name="id" required="true" %>
<%@attribute name="required" type="java.lang.Boolean" %>
<%@attribute name="multiple" type="java.lang.Boolean" %>
<%@attribute name="body" fragment="true" %>
<c:set var="message" value="${requestScope.messages.get(id)}"/>
<div class="form-group ${empty message ? "" : "has-error"}">
    <label class="control-label" for="${id}">
        <fmt:message key="${label}"/>
        <c:if test="${required=='true'}"><span class="asterix"> *</span></c:if>
    </label>
    <select data-placeholder="<fmt:message key="${placeholder}"/>" class="form-control chosen" name="${id}" id="${id}"
            <c:if test="${multiple==true}">multiple="multiple"</c:if>>
        <jsp:invoke fragment="body"/>
    </select>
    <label class="error" for="${id}">${message}</label>
</div>