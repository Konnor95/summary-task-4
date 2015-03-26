<%@include file="../../includes/main.jsp" %>
<fmt:message var="title" key="text.registration"/>
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

                        <p class="title-desc"><fmt:message key="text.haveAccountLeftPart"/>
                            <a href="<t:url value="/login"/>">
                                <fmt:message key="text.haveAccountRightPart"/>
                            </a>.
                        </p>
                    </header>
                    <div class="xs-margin"></div>
                    <form action="registration" method="post" id="register-form">
                        <div class="row">
                            <div class="col-md-8 col-sm-8 col-xs-12">
                                <fieldset>
                                    <div class="error error-lg">${requestScope.messages.error}</div>
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <span class="input-icon input-icon-user"></span>
                                            <span class="input-text"><fmt:message key="text.firstName"/></span>
                                        </span>
                                        <input type="text" name="firstName"  
                                               class="form-control input-lg"
                                               title="<fmt:message key="text.firstName"/>"
                                               value="${requestScope.user.firstName}">
                                    </div>
                                    <div class="error">${requestScope.messages.firstName}</div>
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <span class="input-icon input-icon-user"></span>
                                            <span class="input-text"><fmt:message key="text.lastName"/></span>
                                        </span>
                                        <input type="text" name="lastName"  
                                               class="form-control input-lg"
                                               title="<fmt:message key="text.lastName"/>"
                                               value="${requestScope.user.lastName}">
                                    </div>
                                    <div class="error">${requestScope.messages.lastName}</div>
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <span class="input-icon input-icon-email"></span>
                                            <span class="input-text"><fmt:message key="text.email"/></span>
                                        </span>
                                        <input type="text" name="email"
                                               class="form-control input-lg"
                                               title="<fmt:message key="text.email"/>"
                                               value="${requestScope.user.email}">
                                    </div>
                                    <div class="error">${requestScope.messages.email}</div>
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <span class="input-icon input-icon-user"></span>
                                            <span class="input-text"><fmt:message key="text.login"/></span>
                                        </span>
                                        <input type="text" name="login"  
                                               class="form-control input-lg"
                                               title="<fmt:message key="text.login"/>"
                                               value="${requestScope.user.login}">
                                    </div>
                                    <div class="error">${requestScope.messages.login}</div>
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <span class="input-icon input-icon-password"></span>
                                            <span class="input-text"><fmt:message key="text.password"/></span>
                                        </span>
                                        <input type="password" name="password"  
                                               class="form-control input-lg"
                                               title="<fmt:message key="text.password"/>"
                                               value="${requestScope.user.password}">
                                    </div>
                                    <div class="error">${requestScope.messages.password}</div>
                                </fieldset>
                                <input type="submit" value="<fmt:message key="text.createAccount"/>"
                                       class="btn btn-custom-2 btn-lg md-margin">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </jsp:attribute>
</m:layout>