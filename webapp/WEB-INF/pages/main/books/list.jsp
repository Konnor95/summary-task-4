<%@include file="../../../includes/main.jsp" %>
<fmt:message var="title" key="text.books"/>
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
                  <div class="row">
                      <div class="col-md-9 col-sm-8 col-xs-12 main-content">
                          <c:if test="${empty requestScope.books}">
                              <h2><fmt:message key="text.nothingFound"/></h2>
                          </c:if>
                          <m:pagination url="/books" max="${requestScope.max}"/>
                      </div>
                      <div class="md-margin"></div>
                      <div class="category-item-container">
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
                                                  <span>
                                                    <c:choose>
                                                        <c:when test="${book.authors.size()>1}">
                                                            <fmt:message key="text.authors"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <fmt:message key="text.author"/>
                                                        </c:otherwise>
                                                    </c:choose>:
                                                  </span>
                                                  <c:forEach items="${book.authors}" var="author" varStatus="status">
                                                      <a href="<t:url value="/author?id=${author.id}"/>">
                                                              ${author.name.value(sessionScope.currentLocale)}
                                                      </a>
                                                      <c:if test="${!status.last}">, </c:if>
                                                  </c:forEach>
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
                  <aside class="col-md-3 col-sm-4 col-xs-12 sidebar">
                      <div class="widget">
                          <div class="panel-group custom-accordion sm-accordion" id="category-filter">
                              <div class="panel">
                                  <div class="accordion-header">
                                      <div class="accordion-title"><span><fmt:message
                                              key="text.search"/></span></div>
                                  </div>
                                  <div class="collapse in">
                                      <div class="panel-body">
                                          <form role="form"
                                                action="<t:url value="/books"/>">
                                              <input type="text" name="search" class="form-control"
                                                     value="${param.search}" title="">
                                              <input type="hidden" name="orderBy" class="form-control"
                                                     value="${param.orderBy}">
                                              <br>
                                              <button type="submit" class="btn btn-custom-2 btn-sm">
                                                  <fmt:message key="text.search"/>
                                              </button>
                                          </form>
                                      </div>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </aside>
              </div>
          </div>
      </div>
  </jsp:attribute>
</m:layout>