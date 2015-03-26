<%@include file="../../includes/main.jsp" %>
<fmt:message var="title" key="text.log-in"/>
<m:layout title="${title}" description="${title}">
    <jsp:attribute name="body">
        <div id="breadcrumb-container">
            <div class="container">
                <ul class="breadcrumb">
                    <li><a href="<t:url value="/"/>"><fmt:message key="text.home"/></a></li>
                    <li class="active">${title}</li>
                </ul>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <header class="content-title">
                        <h1 class="title">${title}</h1>

                        <p class="title-desc"><fmt:message key="text.notRegisteredLeftPart"/>
                            <a href="<t:url value="/registration"/>"> <fmt:message
                                    key="text.notRegisteredRightPart"/></a>.
                        </p>
                    </header>
                    <div class="xs-margin"></div>
                    <form action="<t:url value="/login"/>" method="post" id="register-form">
                        <div class="row">
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <div class="error error-lg">${requestScope.messages.error}</div>
                                <fieldset>
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <span class="input-icon input-icon-user"></span>
                                            <span class="input-text"><fmt:message key="text.login"/></span>
                                        </span>
                                        <input type="text" name="login" required
                                               class="form-control input-lg"
                                               title="<fmt:message key="text.login"/>"
                                               value="${requestScope.login}">
                                    </div>
                                    <div class="error">${requestScope.messages.login}</div>
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <span class="input-icon input-icon-password"></span>
                                            <span class="input-text"><fmt:message key="text.password"/></span>
                                        </span>
                                        <input type="password" name="password" required
                                               class="form-control input-lg"
                                               title="<fmt:message key="text.password"/>"
                                               value="${requestScope.password}">
                                    </div>
                                    <div class="error">${requestScope.messages.password}</div>
                                </fieldset>
                                <input type="submit" value="<fmt:message key="text.log-in"/>"
                                       class="btn btn-custom-2 btn-lg md-margin">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </jsp:attribute>
</m:layout>