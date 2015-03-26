<%@include file="../../includes/main.jsp" %>
<fmt:message var="title" key="text.home"/>
<fmt:message var="description" key="text.welcomeDescription"/>
<m:layout title="${title}" description="${description}">
    <jsp:attribute name="body">
       	<div id="slider-sequence" class="homeslider">
            <div class="sequence-prev"></div>
            <div class="sequence-next"></div>
            <ul class="sequence-canvas">
                <li class="sequence-slide1">
                    <div class="sequencebg">
                        <img src="<c:url value="/assets/img/homeslider/slide1.jpg"/>" alt="Slide 1 image"
                             class="sequencebg-image">
                    </div>
                    <div class="sequence-container container">
                        <div class="sequence-title"><fmt:message key="text.slider.slide1.title"/></div>
                        <div class="sequence-subtitle"><fmt:message key="text.slider.slide1.substitle"/></div>
                        <a href="<t:url value="/books"/>" class="btn btn-custom-2 btn-sequence"><fmt:message
                                key="text.slider.slide1.button"/></a>
                    </div>
                </li>

                <li class="sequence-slide2">
                    <div class="sequencebg">
                        <img src="<c:url value="/assets/img/homeslider/slide2.jpg"/>" alt="Slide 2 image"
                             class="sequencebg-image">
                    </div>
                    <div class="sequence-container container">
                        <div class="sequence-title"><fmt:message key="text.slider.slide2.title"/></div>
                    </div>
                </li>

                <li class="sequence-slide3">
                    <div class="sequencebg">
                        <img src="<c:url value="/assets/img/homeslider/slide3.jpg"/>" alt="Slide 3 image"
                             class="sequencebg-image">
                    </div>
                    <div class="sequence-container container">
                        <div class="sequence-title"><fmt:message key="text.slider.slide3.title"/></div>
                    </div>
                </li>
            </ul>
            <ul class="sequence-pagination">
                <li>Frame 1</li>
                <li>Frame 2</li>
                <li>Frame 3</li>
            </ul>
        </div>
        <div class="md-margin2x"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12 main-content">
                            <div class="row">
                                <div class="col-md-8 col-sm-8 col-xs-12">
                                    <header class="content-title">
                                        <h2 class="title"><fmt:message key="text.welcomeToSite"/></h2>
                                    </header>
                                    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${description}</p>
                                </div>
                                <div class="col-md-3 col-sm-3 col-xs-12">
                                    <div class="sm-margin visible-xs"></div>
                                    <img src="<c:url value="/assets/img/logo.png"/>" class="img-responsive">
                                </div>
                            </div>
                            <div class="xlg-margin"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="head">
     <link rel="stylesheet" href="<c:url value="/assets/main/css/sequence-slider.min.css"/>">
    </jsp:attribute>
    <jsp:attribute name="bottom">
    <script src="<c:url value="/assets/main/js/jquery.sequence-min.js"/>"></script>
        <%--suppress JSUnusedLocalSymbols --%>
        <script>
            $(function () {
                var options = {
                            nextButton: true,
                            prevButton: true,
                            pagination: true,
                            autoPlay: true,
                            autoPlayDelay: 8500,
                            pauseOnHover: true,
                            preloader: true,
                            theme: 'slide',
                            speed: 700,
                            animateStartingFrameIn: true
                        },
                        homeSlider = $('#slider-sequence').sequence(options).data("sequence");
            });
        </script>
  </jsp:attribute>
</m:layout>