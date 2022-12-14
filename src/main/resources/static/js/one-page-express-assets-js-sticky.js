(function () {
    jQuery(document).ready(function ($) {
        var dataprefix = "data-sticky";

        function attrName(name) {
            return name ? dataprefix + "-" + name : dataprefix;
        }

        var stickyElements = $('[' + dataprefix + ']');
        stickyElements.each(function (index, el) {
            var $el = $(el);

            if ($el.data('stickData')) {
                return;
            }

            var distance = parseInt($el.attr(attrName()));
            var stickyOnMobile = $el.attr(attrName("mobile")) == "1";
            var stickyOnTablet = true; //$el.attr(attrName("tablet")) == "1" ;
            var useShrink = $el.attr(attrName("shrinked")) == "1";
            var toBottom = $el.attr(attrName("to")) == "bottom";

            if (useShrink) {
                $el.attr(attrName(), "initial");
            }

            var stickData = {
                center: true,
                responsiveWidth: true,
                zIndex: (10000 + index),
                topSpacing: distance,
                stickyOnMobile: stickyOnMobile,
                stickyOnTablet: stickyOnTablet,
                useShrink: useShrink,
                toBottom: toBottom,
                useNativeSticky: false
            }

            if (useShrink) {
                return;
            }


            if (distance === 0 && jQuery('#wpadminbar').length && jQuery('#wpadminbar').css('position') === "absolute") {
                distance = 0;
            }

            stickData['topSpacing'] = distance;
            stickData['top'] = distance;

            $el.data('stickData', stickData);
            $el.fixTo('body', stickData);
        });

        var resizeCallback = function () {
            var stickyElements = this.$els;

            if (window.innerWidth < 1024) {
                stickyElements.each(function (index, el) {
                    var data = $(this).data();
                    var stickData = data.stickData;

                    if (!stickData) {
                        return;
                    }

                    var fixToInstance = data.fixtoInstance;
                    if (!fixToInstance)
                        return true;

                    if (window.innerWidth <= 767) {
                        if (!stickData.stickyOnMobile) {
                            fixToInstance.stop();
                        }
                    } else {
                        if (!stickData.stickyOnTablet) {
                            fixToInstance.stop();
                        }
                    }
                });
            } else {
                stickyElements.each(function (index, el) {
                    var data = $(this).data();
                    if (!data) {
                        return;
                    }
                    var fixToInstance = data.fixtoInstance;
                    if (!fixToInstance)
                        return true;
                    fixToInstance.start();
                })
            }
        }
            .bind({
                "$els": stickyElements
            })

        $(window).bind('resize.sticky orientationchange.sticky', function () {
            setTimeout(resizeCallback, 50);
        });
        $(window).trigger('resize.sticky');
    });

})()

/*This file was exported by "Export WP Page to Static HTML" plugin which created by ReCorp (https://myrecorp.com) */