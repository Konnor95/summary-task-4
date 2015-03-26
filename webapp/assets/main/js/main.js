/* ================================================
 ----------- Venedor ---------- */
(function ($) {
    "use strict";

    // Check for Mobile device
    var mobileDetected;
    if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
        mobileDetected = true;
    } else {
        mobileDetected = false;
    }

    // Check for placeholder support
    jQuery.support.placeholder = (function () {
        var i = document.createElement('input');
        return 'placeholder' in i;
    })();

    // if Placeholder is not supported call plugin
    if (!jQuery.support.placeholder && $.fn.placeholder) {
        $('input, textarea').placeholder();
    }


    // function check for window width
    function checkWindowWidth() {
        return $(window).width();
    }


    /* =========================================
     ---- Create Responsive Menu
     =========================================== */
    var menu = $('.menu').clone(true).removeClass('menu').addClass('responsive-nav'),
        container = $('#responsive-nav');

    container.append(menu);


    container.find('li, .col-2, .col-3, .col-4, .col-5').each(function () {

        var $this = $(this);


        if ($this.hasClass('mega-menu-container')) {
            $this.removeClass('mega-menu-container');
        }


        $this.has('ul, .megamenu').prepend('<span class="menu-button"></span>');

    });


    $('span.menu-button').on('click', function () {
        var $this = $(this);

        if (!$this.hasClass('active')) {
            $(this)
                .addClass('active')
                .siblings('ul, .mega-menu')
                .slideDown('800');
        } else {
            $(this)
                .removeClass('active')
                .siblings('ul, .mega-menu')
                .slideUp('800');
        }
    });


    $('#responsive-nav-button').on('click', function () {
        var $this = $(this);

        if ($this.hasClass('active')) {
            $('#responsive-nav').find('.responsive-nav').slideUp(300, function () {
                $this.removeClass('active');
            });

        } else {
            $('#responsive-nav').find('.responsive-nav').slideDown(300, function () {
                $this.addClass('active');
            });
        }
    });


    // Sub menu show/hide with hoverIntent plugin
    if ($.fn.hoverIntent) {
        $('ul.menu').hoverIntent(function () {
                $(this).children('ul, .mega-menu').fadeIn(100);

            }, function () {
                $(this).children('ul, .mega-menu').fadeOut(50);
            },
            'li');

    } else {

        $('ul.menu').find('li').mouseover(function () {
            $(this).children('ul, .mega-menu').css('display', 'block');

        }).mouseout(function () {
            $(this).children('ul, .mega-menu').css('display', 'none');
        });
    }


    /* =========================================
     ---- Search bar input animation for Better Responsive
     ----- if not empty send form
     =========================================== */
    var formInputOpen = true;
    $('#quick-search').on('click', function (e) {
        var $this = $(this),
            parentForm = $this.closest('.quick-search-form'),
            searchInput = parentForm.find('.form-control'),
            searchInputVal = $.trim(searchInput.val());

        if (searchInputVal === '') {
            var hiddenGroup = parentForm.find(':hidden.form-group'),
                formGroup = parentForm.find('.form-group ');

            if (formInputOpen) {
                hiddenGroup.animate({width: 'show'}, 400, function () {
                    formInputOpen = false;
                });
            } else {
                formGroup.animate({width: 'hide'}, 400, function () {
                    formInputOpen = true;
                });
            }

            e.preventDefault();
        }

    });


    /* =========================================
     ---- Item hover animation
     =========================================== */

    function itemAnimationIn() {
        var $this = $(this),
            itemText = $this.find('.icon-cart-text'),
            itemWidth = $this.width(),
            ratingAmount = $this.find('.ratings-amount'),
            moreActionBtns = $this.find('.item-action-inner');


        if (itemWidth < 220) {
            itemText.animate({width: 'hide'}, 100, function () {
                $(this).closest('.item-add-btn').addClass('icon-cart');
            });
        }
        ratingAmount.animate({width: 'show'}, 300);
        moreActionBtns.css({'visibility': 'visible', 'overflow': 'hidden'}).animate({width: 90}, 300);
    }

    function itemAnimationOut() {
        var $this = $(this),
            itemText = $this.find('.icon-cart-text'),
            itemWidth = $this.width(),
            ratingAmount = $this.find('.ratings-amount'),
            moreActionBtns = $this.find('.item-action-inner');


        if (itemWidth < 220) {
            // be careful about this duration
            // make sure that it is the same as below's
            itemText.animate({width: 'show'}, 300).closest('.item-add-btn').removeClass('icon-cart');
        }

        ratingAmount.animate({width: 'hide'}, 300);
        moreActionBtns.animate({width: 0}, 300).css({'visibility': 'hidden', 'overflow': 'hidden'});
    }

    /* =========================================
     ---- Category-item filter color box background
     =========================================== */
    $('.filter-color-box').each(function () {
        var $this = $(this),
            bgColor = $this.data('bgcolor');

        $this.css('background-color', bgColor);
    });


    /* =========================================
     ---- Twitter Feed Plugin
     =========================================== */
    if ($.fn.tweet) {
        $('.twitter_feed').tweet({
            modpath: './js/twitter/',
            avatar_size: '',
            count: 4,
            query: "themeforest", // write feed query here
            loading_text: "searching twitter...",
            join_text: "",
            template: "{join}{text}{time}"
            /* etc... */
        });

        $('.twitter_feed.flexslider').flexslider({
            animation: "slide",
            selector: ".tweet_list > li",
            controlNav: false,// false
            prevText: '',
            nextText: '',
            animationLoop: true,
            smoothHeight: true,
            slideshowSpeed: 5000
        });
    }


    /* =========================================
     ----  FitVids.js / responsive video
     =========================================== */
    /* Check for fitVids plugin */
    if ($.fn.fitVids) {
        $('.video-container').fitVids();
    }


    /* =========================================
     ----  Category Price Slider
     =========================================== */
    if ($.fn.noUiSlider) {
        $('#price-range').noUiSlider({
            range: [0, 999],
            start: [0, 999],
            handles: 2,
            connect: true,
            step: 1,
            serialization: {
                to: [$('#price-range-low'), $('#price-range-high')],
                resolution: 1
            }
        });
    }


    /* =========================================
     ---- Item/ Product rating
     =========================================== */
    $.each($('.ratings-result'), function () {
        var $this = $(this),
            parentWidth = $this.closest('.ratings').width(),
            rating = $(this).data('result'),
            newWidth = (parentWidth / 100) * rating;

        $(this).css('width', newWidth);
    });


    /* =========================================
     ---- Floated left-right tabs menu height with Bootstrap tab plugin custom event
     =========================================== */

    function tabMenuHeight(containerMinHeight) {
        var container = $('.tab-container'),
            newHeight = container.find('.tab-pane.active').outerHeight(),
            navContainer = container.find('.nav-tabs');


        if (newHeight > containerMinHeight) {
            navContainer.css('height', newHeight);
            navContainer.find('li:last-child').find('a').css('border-bottom-color', '#dcdcdc');
        } else {
            navContainer.css('height', containerMinHeight);
            navContainer.find('li:last-child').find('a').css('border-bottom-color', 'transparent');
        }
    }

    $(window).on('resize load', function () {
        var winWidth = checkWindowWidth();

        if (winWidth > 767) {

            var containerMinHeight = $('.tab-container').find('ul.nav-tabs').outerHeight();

            tabMenuHeight(containerMinHeight);

            $('.tab-container').find('ul.nav-tabs').find('a').on('shown.bs.tab', function (e) {
                tabMenuHeight(containerMinHeight);
            });

        }

    });


    /* =========================================
     ---- Collapse/Accordion toggle arrows
     =========================================== */

    // Blog Sidebar Widget Collapse with plugin's custom events
    $('.panel-title').on('click', function () {
        var $this = $(this),
            targetAcc = $this.find('a').attr('href');

        $(targetAcc).on('shown.bs.collapse', function () {
            $this.find('.icon-box').html('&plus;');
        }).on('hidden.bs.collapse', function () {
            $this.find('.icon-box').html('&minus;');
        });
    });


    // Checkout Collapse//Accordion
    $('.accordion-btn').on('click', function () {
        var $this = $(this),
            targetAcc = $this.data('target');

        $(targetAcc).on('shown.bs.collapse', function () {
            $this.addClass('opened');
        }).on('hidden.bs.collapse', function () {
            if ($this.hasClass('opened')) {
                $this.removeClass('opened');
            }

        });

    });

    /* =========================================
     ---- Scroll Top Button
     =========================================== */
    $(window).on('scroll', function () {
        var windowTop = $(window).scrollTop(),
            scrollTop = $('#scroll-top');

        if (windowTop >= 300) {
            scrollTop.addClass('fixed');
        } else {
            scrollTop.removeClass('fixed');
        }
    });


    $('#scroll-top').on('click', function (e) {
        $('html, body').animate({
            'scrollTop': 0
        }, 1200);
        e.preventDefault();
    });

    $('.input-group input').on('input', function () {
        var $parent = $(this).parent();
        $parent.next('.error').html("");
    });


    /* =============================================
     ----- check for element
     ------- existing && plugin
     =========================================== */
    function checkSupport(elemname, pluginname) {
        return (elemname.length && pluginname) ? true : false;
    }

    /* =========================================
     ----  Brand Slider
     =========================================== */

    var brandSlider = $('div.brand-slider.owl-carousel');
    if (checkSupport(brandSlider, $.fn.owlCarousel)) {
        brandSlider.owlCarousel({
            items: 6,
            itemsDesktop: [1199, 5],
            itemsDesktopSmall: [979, 4],
            itemsTablet: [768, 2],
            itemsMobile: [479, 1],
            slideSpeed: 600,
            autoPlay: 10000,
            stopOnHover: true,
            navigation: false,
            pagination: false,
            responsive: true,
            autoHeight: true
        }).data('navigationBtns', ['#brand-slider-prev', '#brand-slider-next']);
    }

    /* =========================================
     ----  Home Page Onsale - hot-items carousel
     =========================================== */

    var hotItems = $('.hot-items-slider.owl-carousel');
    if (checkSupport(hotItems, $.fn.owlCarousel)) {
        hotItems.owlCarousel({
            items: 3,
            itemsDesktop: [1199, 3],
            itemsDesktopSmall: [979, 2],
            itemsTablet: [768, 2],
            itemsMobile: [479, 1],
            slideSpeed: 400,
            autoPlay: 8000,
            stopOnHover: true,
            navigation: false,
            pagination: false,
            responsive: true,
            mouseDrag: false,
            autoHeight: true
        }).data('navigationBtns', ['#hot-items-slider-prev', '#hot-items-slider-next']);
    }


    /* =========================================
     ----  Also purchased slider - product.html
     =========================================== */

    var purchasedItems = $('.purchased-items-slider.owl-carousel');
    if (checkSupport(purchasedItems, $.fn.owlCarousel)) {
        purchasedItems.owlCarousel({
            items: 4,
            itemsDesktop: [1199, 4],
            itemsDesktopSmall: [979, 3],
            itemsTablet: [768, 2],
            itemsMobile: [479, 1],
            slideSpeed: 400,
            autoPlay: 8000,
            stopOnHover: true,
            navigation: false,
            pagination: false,
            responsive: true,
            mouseDrag: false,
            autoHeight: true
        }).data('navigationBtns', ['#purchased-items-slider-prev', '#purchased-items-slider-next']);
    }


    /* =========================================
     ----  Similiar Items slider - cart.html
     =========================================== */

    var similiarItems = $('.similiar-items-slider.owl-carousel');
    if (checkSupport(similiarItems, $.fn.owlCarousel)) {
        similiarItems.owlCarousel({
            items: 4,
            itemsDesktop: [1199, 4],
            itemsDesktopSmall: [979, 3],
            itemsTablet: [768, 2],
            itemsMobile: [479, 1],
            slideSpeed: 400,
            autoPlay: 8000,
            stopOnHover: true,
            navigation: false,
            pagination: false,
            responsive: true,
            mouseDrag: false,
            autoHeight: true
        }).data('navigationBtns', ['#similiar-items-slider-prev', '#similiar-items-slider-next']);
    }


    /* =========================================
     ----  Related portfolio - single-portfolio.html
     =========================================== */

    var relatedPortfolio = $('.related-portfolio.owl-carousel');
    if (checkSupport(relatedPortfolio, $.fn.owlCarousel)) {
        relatedPortfolio.owlCarousel({
            items: 4,
            itemsDesktop: [1199, 4],
            itemsDesktopSmall: [979, 3],
            itemsTablet: [768, 2],
            itemsMobile: [479, 1],
            slideSpeed: 400,
            autoPlay: 8000,
            stopOnHover: true,
            navigation: false,
            pagination: false,
            responsive: true,
            mouseDrag: false,
            autoHeight: true
        }).data('navigationBtns', ['#related-slider-prev', '#related-slider-next']);
    }

    /* =========================================
     ----  Register OwlCarousel custom navigation buttons
     =========================================== */

    if (checkSupport($('.owl-carousel'), $.fn.owlCarousel)) {
        $('.owl-carousel').each(function () {
            var $this = $(this),
                owlCarousel = $this.data('owlCarousel'),
                owlBtns = $this.data('navigationBtns'),
                prevBtn, nextBtn;

            if (typeof owlCarousel === 'undefined' || typeof owlBtns === 'undefined') {
                return;
            }

            for (var key in owlBtns) {
                if (owlBtns[key].indexOf('next') == -1) {
                    prevBtn = $(owlBtns[key]);
                } else {
                    nextBtn = $(owlBtns[key]);
                }
            }

            prevBtn.on('click touchstart', function (e) {
                owlCarousel.prev();
                e.preventDefault();
            });

            nextBtn.on('click touchstart', function (e) {
                owlCarousel.next();
                e.preventDefault();
            });
        });
    }


    /* =========================================
     ----  Category Banner Slider
     =========================================== */
    $('.category-image-slider.flexslider').flexslider({
        animation: "slide",
        animationLoop: true,
        prevText: '',
        nextText: '',
        controlNav: false,
        smoothHeight: true,
        slideshowSpeed: 6500
    });

    /* =========================================
     ----  Sidebar Latest Posts Slider
     =========================================== */
    $('.latest-posts-slider.flexslider').flexslider({
        animation: "slide",
        selector: ".latest-posts-list > li",
        prevText: '',
        nextText: '',
        controlNav: false, // false
        smoothHeight: true,
        slideshowSpeed: 6000
    });

    /* =========================================
     ----  Sidebar Latest Posts Slider
     =========================================== */
    $('.recent-posts-slider.flexslider').flexslider({
        animation: "slide",
        selector: ".recent-posts-list > li",
        prevText: '',
        nextText: '',
        controlNav: false,// false
        smoothHeight: true,
        slideshowSpeed: 5500
    });

    /* =========================================
     ----  Sidebar Testimonials Slider
     =========================================== */
    $('.testimonials-slider.flexslider').flexslider({
        animation: "fade",
        selector: ".testimonials-list > li",
        prevText: '',
        nextText: '',
        controlNav: false, // false
        slideshowSpeed: 4800
    });


    /* =========================================
     ----  Sidebar Featured Product Slider
     =========================================== */
    $('.featured-slider.flexslider').flexslider({
        animation: "slide",
        selector: ".featured-list > li",
        controlNav: false,// false
        prevText: '',
        nextText: '',
        smoothHeight: true,
        slideshowSpeed: 7000
    });


    /* =========================================
     ----  Sidebar Related Product Slider
     =========================================== */
    $('.related-slider.flexslider').flexslider({
        animation: "slide",
        selector: ".related-list > li",
        controlNav: false,// false
        prevText: '',
        nextText: '',
        smoothHeight: true,
        slideshowSpeed: 7000
    });


    /* =========================================
     ----  Sidebar Banner Slider
     =========================================== */
    $('.banner-slider.flexslider').flexslider({
        animation: "fade",
        selector: ".banner-slider-list > li",
        directionNav: false, // false next/prev
        controlNav: true,
        prevText: '',
        nextText: '',
        slideshowSpeed: 6500
    });


    /* =============================================
     ----- About us section skill bars
     =========================================== */

    if ($.fn.appear) {

        $('.progress-animate').appear();
        $('.progress-animate').on('appear', function () {
            var $this = $(this),
                progressVal = $(this).data('width'),
                progressText = $this.find('.progress-text');

            $this.css({'width': progressVal + '%'}, 400);
            progressText.fadeIn(500);
        });


    } else {

        $('.progress-animate').each(function () {
            var $this = $(this),
                progressVal = $(this).data('width'),
                progressText = $this.find('.progress-text');

            $this.css({'width': progressVal + '%'}, 400);
            progressText.fadeIn(500);
        });
    }


    // Testimonials Slider -  About Us Page
    $('.about-us-testimonials.flexslider').flexslider({
        animation: "slide",
        controlNav: true,// false
        directionNav: false,
        animationLoop: true,
        smoothHeight: true,
        slideshowSpeed: 6000
    });


    /* =========================================
     ---- Portfolio  Filter / Isotope Plugin
     =========================================== */

    var $container = $('.portfolio-item-container');

    function CalcItemWidth() {

        var widthPort = $container.outerWidth(),
            maxCol = parseInt($container.data('max-col')),
            itemCol;

        if (widthPort > 1140) {
            itemCol = (maxCol >= 4) ? 4 : maxCol;
        } else if (widthPort > 940) {
            itemCol = (maxCol >= 4) ? 4 : maxCol;
        } else if (widthPort > 750) {
            itemCol = (maxCol >= 3) ? 3 : maxCol;
        } else if (widthPort > 520) {
            itemCol = (maxCol >= 2) ? 2 : maxCol;
        } else {
            itemCol = 1;
        }

        $('.portfolio-item').css('width', Math.floor((widthPort) / itemCol) - 1);

    }

    /*fix width */
    CalcItemWidth();

    $(window).on('resize orientationchange', CalcItemWidth);

    /* check for isotope - includes imagesloaded plugin */
    if ($.fn.isotope) {
        $container.imagesLoaded(function () {
            // initialize isotope

            $container.isotope({
                itemSelector: '.portfolio-item',
                layoutMode: 'fitRows',
                animationEngine: 'best-available'

            });


            // filter items when filter link is clicked
            $('#portfolio-filter').find('a').on('click', function (e) {
                var selector = $(this).attr('data-filter');
                $('#portfolio-filter').find('.active').removeClass('active');

                $container.isotope({
                    filter: selector
                });

                $(this).addClass('active');
                e.preventDefault();
            });

        });

    }


    /* =========================================
     ---- Portfolio prettPhoto Plugin
     =========================================== */
    if ($.fn.prettyPhoto) {
        /* update prettyphoto plugin for feeds */
        $("a[data-rel^='prettyPhoto']").prettyPhoto({
            hook: 'data-rel',
            animation_speed: 'fast',
            slideshow: 3000,
            autoplay_slideshow: false,
            show_title: false,
            deeplinking: false,
            social_tools: '',
            overlay_gallery: false,
            theme: 'light_square'
        });
    }
    /*----------------------------------------------------*/
    /* Category filter sidebar custom scrollbar with jscrollpane plugin */
    /*----------------------------------------------------*/

    var catFilter = $('.category-filter-list.jscrollpane'),
        checkForScrollbar = function (a) {
            var catHeight = a.height();
            if (catHeight > 300) {
                a.css('height', 300);
                a.jScrollPane({
                    showArrows: false
                });
            }
        };

    // on document ready call plugin if section height > 300
    $.each(catFilter, function () {
        var $this = $(this);
        checkForScrollbar($this);

    });

    // Call plugin after collapse activated
    $('#category-filter').find('.collapse').on('shown.bs.collapse', function () {
        var cFilter = $(this).find('.category-filter-list.jscrollpane');
        checkForScrollbar(cFilter);
    });


    // on window resize fix scroll bar position
    $(window).on('resize', function () {
        $('.category-filter-list.jscrollpane').each(function () {
            var apiJsc = $(this).data('jsp'),
                resTime;

            if (!resTime) {
                resTime = setTimeout(function () {
                    if (apiJsc) {
                        apiJsc.reinitialise();
                    }
                    resTime = null;
                }, 50);
            }
        });
    });


    /*----------------------------------------------------*/
//* Parallax Background -- About-us page */
    /*----------------------------------------------------*/
    if (!mobileDetected && $.fn.parallax) {
        $('#page-header').addClass('parallax').parallax("50%", 0.3);
        $('#testimonials-section').addClass('parallax').parallax("50%", 0.3);
    }


}(jQuery));


/*!
 * jQuery Cookie Plugin v1.4.0
 * https://github.com/carhartl/jquery-cookie
 *
 * Copyright 2013 Klaus Hartl
 * Released under the MIT license
 */
(function (factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as anonymous module.
        define(['jquery'], factory);
    } else {
        // Browser globals.
        factory(jQuery);
    }
}(function ($) {

    var pluses = /\+/g;

    function encode(s) {
        return config.raw ? s : encodeURIComponent(s);
    }

    function decode(s) {
        return config.raw ? s : decodeURIComponent(s);
    }

    function stringifyCookieValue(value) {
        return encode(config.json ? JSON.stringify(value) : String(value));
    }

    function parseCookieValue(s) {
        if (s.indexOf('"') === 0) {
            // This is a quoted cookie as according to RFC2068, unescape...
            s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
        }

        try {
            // Replace server-side written pluses with spaces.
            // If we can't decode the cookie, ignore it, it's unusable.
            // If we can't parse the cookie, ignore it, it's unusable.
            s = decodeURIComponent(s.replace(pluses, ' '));
            return config.json ? JSON.parse(s) : s;
        } catch (e) {
        }
    }

    function read(s, converter) {
        var value = config.raw ? s : parseCookieValue(s);
        return $.isFunction(converter) ? converter(value) : value;
    }

    var config = $.cookie = function (key, value, options) {

        // Write

        if (value !== undefined && !$.isFunction(value)) {
            options = $.extend({}, config.defaults, options);

            if (typeof options.expires === 'number') {
                var days = options.expires, t = options.expires = new Date();
                t.setTime(+t + days * 864e+5);
            }

            return (document.cookie = [
                encode(key), '=', stringifyCookieValue(value),
                options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
                options.path ? '; path=' + options.path : '',
                options.domain ? '; domain=' + options.domain : '',
                options.secure ? '; secure' : ''
            ].join(''));
        }

        // Read

        var result = key ? undefined : {};

        // To prevent the for loop in the first place assign an empty array
        // in case there are no cookies at all. Also prevents odd result when
        // calling $.cookie().
        var cookies = document.cookie ? document.cookie.split('; ') : [];

        for (var i = 0, l = cookies.length; i < l; i++) {
            var parts = cookies[i].split('=');
            var name = decode(parts.shift());
            var cookie = parts.join('=');

            if (key && key === name) {
                // If second argument (value) is a function it's a converter...
                result = read(cookie, value);
                break;
            }

            // Prevent storing a cookie that we couldn't decode.
            if (!key && (cookie = read(cookie)) !== undefined) {
                result[name] = cookie;
            }
        }

        return result;
    };

    config.defaults = {};

    $.removeCookie = function (key, options) {
        if ($.cookie(key) === undefined) {
            return false;
        }

        // Must not alter options, thus extending a fresh object...
        $.cookie(key, '', $.extend({}, options, {expires: -1}));
        return !$.cookie(key);
    };

}));

function deleteBook(id, prefix, url) {
    var selector = '#' + prefix + id;
    $(selector).hide('slow');
    $(selector).html('');
    $.post(url, {id: id}, function (resp) {
        if (resp == 'true' || resp == true || resp == "true") {
           location.reload();
        }
    })
}

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


function increment(id, max, url) {
    var input = '#quantity-' + id;
    var valueField = '#quantityInput-' + id;
    var value = $(input).val();
    if (value >= max) {
        max = parseInt(max);
        $(input).val(max);
        $(valueField).val(max);
        if (value == max) {
            setQuantity(url, id, max);
        }
    }
    else {
        value = parseInt(value) + 1;
        $(input).val(value);
        $(valueField).val(value);
        setQuantity(url, id, value);
    }

}

function decrement(id, min, url) {
    var input = '#quantity-' + id;
    var valueField = '#quantityInput-' + id;
    var value = $(input).val();
    if (value <= min) {
        $(input).val(parseInt(min));
        $(valueField).val(parseInt(min));
        if (value == min) {
            setQuantity(url, id, min);
        }
    }
    else {
        value = parseInt(value) - 1;
        $(input).val(value);
        $(valueField).val(value);
        setQuantity(url, id, value);
    }
}

function setQuantity(url, id, quantity) {
    console.log("url: " + url);
    console.log("id: " + id);
    console.log("quantity: " + quantity);
    $.post(url, {id: id, quantity: quantity}, function (resp) {
        console.log(resp);
    })
}

$(document).ready(function () {
    $('.custom-quantity-input a').on('click', function (e) {
        e.preventDefault();
    })
});