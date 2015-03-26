<%@include file="../../includes/main.jsp" %>
<fmt:message var="title" key="text.registrationSucceedTitle"/>
<m:layout title="${title}" description="${title}">
    <jsp:attribute name="body">
        <div id="breadcrumb-container">
            <div class="container">
                <ul class="breadcrumb">
                    <li><a href="<t:url value="/"/>"><fmt:message key="text.home"/></a></li>
                    <li class="active"><a href="<t:url value="/registration"/>"><fmt:message
                            key="text.registration"/></a></li>
                    <li class="active">${title}</li>
                </ul>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <header class="content-title">
                        <h1 class="title">${title}</h1>

                        <p class="title-desc">
                            <fmt:message key="text.registrationSucceedText"/>
                        </p>
                    </header>
                    <div class="xs-margin"></div>
                    <a href="<t:url value="/"/>" class="btn btn-custom-2 btn-lg md-margin">
                        <fmt:message key="text.backToHomePage"/>
                    </a>
                </div>
            </div>
        </div>
    </jsp:attribute>
</m:layout>