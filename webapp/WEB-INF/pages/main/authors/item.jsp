<%@include file="../../../includes/main.jsp" %>
<c:set var="title" value="${requestScope.author.name.value(sessionScope.currentLocale)}"/>
<c:set var="description" value="${requestScope.author.description.value(sessionScope.currentLocale)}"/>
<m:layout title="${title}" description="${description}">
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

                <div class="row">

                    <div class="col-md-5 col-sm-12 col-xs-12 product-viewer clearfix">
                        <div id="product-image-container">
                            <figure>
                                <c:choose>
                                    <c:when test="${empty requestScope.author.image}">
                                        <img src="<c:url value="/assets/img/author.png"/>">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="<c:url value="/uploads/authors/${requestScope.author.image}"/>">
                                    </c:otherwise>
                                </c:choose>
                            </figure>
                        </div>
                    </div>
                    <div class="col-md-7 col-sm-12 col-xs-12 product">
                        <div class="lg-margin visible-sm visible-xs"></div>
                        <h1 class="product-name">${title}</h1>
                        <ul class="product-list">
                            <li>
                                <span><fmt:message key="text.description"/>:</span>
                                    ${description}
                            </li>
                        </ul>
                        <div class="md-margin"></div>
                    </div>
                </div>
                <div class="lg-margin2x"></div>
                <h2 class="title"><fmt:message key="text.books"/></h2>

                <div class="row">
                    <c:forEach items="${requestScope.books}" var="book">
                        <div class="item item-list clearfix">
                            <div class="item-image-container">
                                <figure>
                                    <a href="<t:url value="/book?id=${book.id}"/>">
                                        <c:choose>
                                            <c:when test="${empty book.image}">
                                                <img src="<c:url value="/assets/img/book.png"/>">
                                            </c:when>
                                            <c:otherwise>
                                                <img src="<c:url value="/uploads/books/${book.image}"/>"
                                                     alt="item1">
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </figure>
                            </div>
                            <div class="item-meta-container">
                                <h3 class="item-name">
                                    <a href="<t:url value="/book?id=${book.id}"/>">
                                            ${book.title.value(sessionScope.currentLocale)}
                                    </a>
                                </h3>
                                <ul class="product-list">
                                    <li>
                                        <span><fmt:message key="text.bookYear"/>:</span>
                                            ${book.year}
                                    </li>
                                    <li>
                                        <span><fmt:message key="text.publisher"/>:</span>
                                            ${book.publisher.title.value(sessionScope.currentLocale)}
                                    </li>
                                </ul>
                                <br>

                                <div class="item-action">
                                    <a href="<t:url value="/book?id=${book.id}"/>" class="item-add-btn">
                                                            <span class="icon-cart-text">
                                                                <fmt:message key="text.more"/>
                                                            </span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
  </jsp:attribute>
    <jsp:attribute name="bottom">
        <script>
            function incr(input, valueField, max) {
                var value = $(input).val();
                if (value >= max) {
                    $(input).val(parseInt(max));
                    $(valueField).val(parseInt(max));
                }
                else {
                    $(input).val(parseInt(value) + 1);
                    $(valueField).val(parseInt(value) + 1);
                }
            }

            function decr(input, valueField, min) {
                var value = $(input).val();
                if (value <= min) {
                    $(input).val(parseInt(min));
                    $(valueField).val(parseInt(min));
                }
                else {
                    $(input).val(parseInt(value) - 1);
                    $(valueField).val(parseInt(value) - 1);
                }
            }

            $(document).ready(function () {
                $('.custom-quantity-input a').on('click', function (e) {
                    e.preventDefault();
                })
            })
        </script>

    </jsp:attribute>
</m:layout>