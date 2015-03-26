<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="color" required="true" %>
<%@attribute name="icon" required="true" %>
<%@attribute name="number" required="true" %>
<%@attribute name="label" required="true" %>
<%@attribute name="link" required="true" %>
<div class="col-lg-3 col-md-6">
    <div class="panel panel-${color}">
        <div class="panel-heading">
            <div class="row">
                <div class="col-xs-3">
                    <i class="fa fa-${icon} fa-4x"></i>
                </div>
                <div class="col-xs-9 text-right">
                    <div class="huge">${number}</div>
                    <div><fmt:message key="${label}"/></div>
                </div>
            </div>
        </div>
        <a href="<t:url value="${link}"/>">
            <div class="panel-footer">
                <span class="pull-left"><fmt:message key="text.viewAll"/></span>
                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>

                <div class="clearfix"></div>
            </div>
        </a>
    </div>
</div>