<%@include file="../../../includes/dashboard.jsp" %>
<fmt:message var="title" key="text.edit.librarian"/>
<d:layout title="${title}">
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <u:message result="${requestScope.result}"/>
                <form action="<t:url value="/dashboard/users/librarians/edit"/>" method="post">
                    <input type="hidden" name="id" value="${requestScope.librarian.id}">
                    <u:text label="text.firstName" id="firstName" value="${requestScope.librarian.firstName}"/>
                    <u:text label="text.lastName" id="lastName" value="${requestScope.librarian.lastName}"/>
                    <u:text label="text.email" id="email" value="${requestScope.librarian.email}"/>
                    <u:text label="text.login" id="login" value="${requestScope.librarian.login}"/>
                    <div class="btn btn-outline btn-primary btn-xs toggle" id="toggle">
                        <fmt:message key="text.edit.password"/>
                    </div>
                    <div class="btn btn-outline btn-primary btn-xs toggle" id="cancelChanging">
                        <fmt:message key="text.cancelChanging"/>
                    </div>
                    <div id="toggle-password">
                        <input type="hidden" name="changePassword" id="changePassword" value="0">
                        <u:text id="password">
                        <jsp:attribute name="label">
                            <fmt:message key="text.password"/>
                        </jsp:attribute>
                        </u:text>
                    </div>
                    <u:submit link="/dashboard/users/librarians"/>
                </form>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="head">
        <style>
            #toggle-password, #cancelChanging {
                display: none;
            }

            #toggle, #cancelChanging {
                margin-bottom: 10px;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="bottom">
        <script>
            $(function () {
                $('#toggle').on('click', function () {
                    $('#toggle').hide();
                    $('#cancelChanging').css('display', 'inline-block');
                    $('#toggle-password').show('slow');
                    $('#changePassword').val(true);
                });
                $('#cancelChanging').on('click', function () {
                    $('#toggle').css('display', 'inline-block');
                    $('#cancelChanging').hide();
                    $('#toggle-password').hide('slow');
                    $('#changePassword').val(false);
                });
            });
        </script>
    </jsp:attribute>
</d:layout>