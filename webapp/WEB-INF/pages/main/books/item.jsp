<%@include file="../../../includes/main.jsp" %>
<c:set var="title" value="${requestScope.book.title.value(sessionScope.currentLocale)}"/>
<c:set var="description" value="${requestScope.book.description.value(sessionScope.currentLocale)}"/>
<c:set var="quantity" value="${requestScope.orderedBooks.get(requestScope.book.id)}"/>
<c:if test="${empty quantity}">
    <c:set var="quantity" value="0"/>
</c:if>
<m:layout title="${title}" description="${description}">
  <jsp:attribute name="body">
      <div id="breadcrumb-container">
          <div class="container">
              <ul class="breadcrumb">
                  <li><a href="<t:url value="/"/>"><fmt:message key="text.home"/></a></li>
                  <li><a href="<t:url value="/books"/>"><fmt:message key="text.books"/></a></li>
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
                                    <c:when test="${empty requestScope.book.image}">
                                        <img src="<c:url value="/assets/img/book.png"/>">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="<c:url value="/uploads/books/${requestScope.book.image}"/>"
                                             alt="item1">
                                    </c:otherwise>
                                </c:choose>
                            </figure>
                        </div>
                    </div>
                    <div class="col-md-7 col-sm-12 col-xs-12 product">
                        <div class="lg-margin visible-sm visible-xs"></div>
                        <h1 class="product-name">${title}</h1>
                        <ul class="product-list">
                            <li><span><fmt:message key="text.inStock"/>:</span>${requestScope.book.inStock}</li>
                            <li><span><fmt:message key="text.bookYear"/>:</span>${requestScope.book.year}</li>
                            <li><span><fmt:message key="text.pagesCount"/>:</span>${requestScope.book.pages}</li>
                            <li><span>
                                <c:choose>
                                    <c:when test="${requestScope.book.authors.size()>1}">
                                        <fmt:message key="text.authors"/>
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:message key="text.author"/>
                                    </c:otherwise>
                                </c:choose>:</span>
                                <c:forEach items="${requestScope.book.authors}" var="author" varStatus="status">
                                    <a href="<t:url value="/author?id=${author.id}"/>">
                                            ${author.name.value(sessionScope.currentLocale)}
                                    </a>
                                    <c:if test="${!status.last}">, </c:if>
                                </c:forEach>
                            </li>
                            <li>
                                <span><fmt:message key="text.publisher"/>:</span>
                                    ${requestScope.book.publisher.title.value(sessionScope.currentLocale)}
                            </li>
                            <li>
                                <span><fmt:message key="text.description"/>:</span>
                                    ${description}
                            </li>
                        </ul>
                        <c:if test="${!empty sessionScope.currentUser}">
                            <hr>
                            <ul class="product-list">
                                <li><span><fmt:message key="text.inCart"/>:</span></li>
                            </ul>
                            <div class="product-add clearfix">
                                <form role="form" action="<t:url value="/book"/>" method="post">
                                    <div class="custom-quantity-input">
                                        <input type="hidden" name="id" value="${requestScope.book.id}"/>
                                        <input type="hidden" id="quantity" name="quantity"
                                               value="${quantity}">
                                        <input type="text" id="quantityInput"
                                               value="${quantity}" title="" disabled>
                                        <a href="#"
                                           onclick="incr('#quantity','#quantityInput', ${requestScope.book.inStock});"
                                           class="quantity-btn quantity-input-up"><i
                                                class="fa fa-angle-up"></i></a>
                                        <a href="#" onclick="decr('#quantity','#quantityInput', 0);"
                                           class="quantity-btn quantity-input-down"><i
                                                class="fa fa-angle-down"></i></a>
                                    </div>
                                    <button class="btn btn-custom-2" type="submit">
                                        <fmt:message key="text.update"/>
                                    </button>
                                </form>
                            </div>
                        </c:if>
                        <div class="md-margin"></div>
                    </div>
                </div>
                <div class="lg-margin2x"></div>
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