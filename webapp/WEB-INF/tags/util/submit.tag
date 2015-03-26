<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="link" required="true" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="translations"/>
<div class="form-group">
    <input id="submit" type="submit" value="<fmt:message key="text.submit"/>" class="btn btn-primary btn-default" />
    <button type="button" class="btn btn-default btn-outline link-button">
        <a href="<t:url value="${link}"/>"><fmt:message key="text.back"/></a>
    </button>
</div>
<br>
