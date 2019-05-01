/*
 Highcharts JS v5.0.6 (2016-12-07)

 (c) 2009-2016 Torstein Honsi

 License: www.highcharts.com/license
 */
(function (K, a) {
    "object" === typeof module && module.exports ? module.exports = K.document ? a(K) : a : K.Highcharts = a(K)
})("undefined" !== typeof window ? window : this, function (K) {
    K = function () {
        var a = window, w = a.document, B = a.navigator && a.navigator.userAgent || "", D = w && w.createElementNS && !!w.createElementNS("http://www.w3.org/2000/svg", "svg").createSVGRect, F = /(edge|msie|trident)/i.test(B) && !window.opera, q = !D, e = /Firefox/.test(B), c = e && 4 > parseInt(B.split("Firefox/")[1], 10);
        return a.Highcharts ? a.Highcharts.error(16, !0) : {
            product: "Highcharts",
            version: "5.0.6",
            deg2rad: 2 * Math.PI / 360,
            doc: w,
            hasBidiBug: c,
            hasTouch: w && void 0 !== w.documentElement.ontouchstart,
            isMS: F,
            isWebKit: /AppleWebKit/.test(B),
            isFirefox: e,
            isTouchDevice: /(Mobile|Android|Windows Phone)/.test(B),
            SVG_NS: "http://www.w3.org/2000/svg",
            chartCount: 0,
            seriesTypes: {},
            symbolSizes: {},
            svg: D,
            vml: q,
            win: a,
            charts: [],
            marginNames: ["plotTop", "marginRight", "marginBottom", "plotLeft"],
            noop: function () {
            }
        }
    }();
    (function (a) {
        var w = [], B = a.charts, D = a.doc, F = a.win;
        a.error = function (q, e) {
            q = a.isNumber(q) ? "Highcharts error #" +
            q + ": www.highcharts.com/errors/" + q : q;
            if (e)throw Error(q);
            F.console && console.log(q)
        };
        a.Fx = function (a, e, c) {
            this.options = e;
            this.elem = a;
            this.prop = c
        };
        a.Fx.prototype = {
            dSetter: function () {
                var a = this.paths[0], e = this.paths[1], c = [], m = this.now, n = a.length, l;
                if (1 === m)c = this.toD; else if (n === e.length && 1 > m)for (; n--;)l = parseFloat(a[n]), c[n] = isNaN(l) ? a[n] : m * parseFloat(e[n] - l) + l; else c = e;
                this.elem.attr("d", c, null, !0)
            }, update: function () {
                var a = this.elem, e = this.prop, c = this.now, m = this.options.step;
                if (this[e + "Setter"])this[e +
                "Setter"](); else a.attr ? a.element && a.attr(e, c, null, !0) : a.style[e] = c + this.unit;
                m && m.call(a, c, this)
            }, run: function (a, e, c) {
                var q = this, n = function (a) {
                    return n.stopped ? !1 : q.step(a)
                }, l;
                this.startTime = +new Date;
                this.start = a;
                this.end = e;
                this.unit = c;
                this.now = this.start;
                this.pos = 0;
                n.elem = this.elem;
                n.prop = this.prop;
                n() && 1 === w.push(n) && (n.timerId = setInterval(function () {
                    for (l = 0; l < w.length; l++)w[l]() || w.splice(l--, 1);
                    w.length || clearInterval(n.timerId)
                }, 13))
            }, step: function (a) {
                var e = +new Date, c, q = this.options;
                c = this.elem;
                var n = q.complete, l = q.duration, h = q.curAnim, b;
                if (c.attr && !c.element)c = !1; else if (a || e >= l + this.startTime) {
                    this.now = this.end;
                    this.pos = 1;
                    this.update();
                    a = h[this.prop] = !0;
                    for (b in h)!0 !== h[b] && (a = !1);
                    a && n && n.call(c);
                    c = !1
                } else this.pos = q.easing((e - this.startTime) / l), this.now = this.start + (this.end - this.start) * this.pos, this.update(), c = !0;
                return c
            }, initPath: function (q, e, c) {
                function m(a) {
                    var f, d;
                    for (p = a.length; p--;)f = "M" === a[p] || "L" === a[p], d = /[a-zA-Z]/.test(a[p + 3]), f && d && a.splice(p + 1, 0, a[p + 1], a[p + 2], a[p + 1], a[p +
                    2])
                }

                function n(a, b) {
                    for (; a.length < f;) {
                        a[0] = b[f - a.length];
                        var d = a.slice(0, r);
                        [].splice.apply(a, [0, 0].concat(d));
                        H && (d = a.slice(a.length - r), [].splice.apply(a, [a.length, 0].concat(d)), p--)
                    }
                    a[0] = "M"
                }

                function l(a, b) {
                    for (var d = (f - a.length) / r; 0 < d && d--;)k = a.slice().splice(a.length / t - r, r * t), k[0] = b[f - r - d * r], y && (k[r - 6] = k[r - 2], k[r - 5] = k[r - 1]), [].splice.apply(a, [a.length / t, 0].concat(k)), H && d--
                }

                e = e || "";
                var h, b = q.startX, C = q.endX, y = -1 < e.indexOf("C"), r = y ? 7 : 3, f, k, p;
                e = e.split(" ");
                c = c.slice();
                var H = q.isArea, t = H ? 2 : 1, E;
                y && (m(e), m(c));
                if (b && C) {
                    for (p = 0; p < b.length; p++)if (b[p] === C[0]) {
                        h = p;
                        break
                    } else if (b[0] === C[C.length - b.length + p]) {
                        h = p;
                        E = !0;
                        break
                    }
                    void 0 === h && (e = [])
                }
                e.length && a.isNumber(h) && (f = c.length + h * t * r, E ? (n(e, c), l(c, e)) : (n(c, e), l(e, c)));
                return [e, c]
            }
        };
        a.extend = function (a, e) {
            var c;
            a || (a = {});
            for (c in e)a[c] = e[c];
            return a
        };
        a.merge = function () {
            var q, e = arguments, c, m = {}, n = function (e, h) {
                var b, c;
                "object" !== typeof e && (e = {});
                for (c in h)h.hasOwnProperty(c) && (b = h[c], a.isObject(b, !0) && "renderTo" !== c && "number" !== typeof b.nodeType ?
                    e[c] = n(e[c] || {}, b) : e[c] = h[c]);
                return e
            };
            !0 === e[0] && (m = e[1], e = Array.prototype.slice.call(e, 2));
            c = e.length;
            for (q = 0; q < c; q++)m = n(m, e[q]);
            return m
        };
        a.pInt = function (a, e) {
            return parseInt(a, e || 10)
        };
        a.isString = function (a) {
            return "string" === typeof a
        };
        a.isArray = function (a) {
            a = Object.prototype.toString.call(a);
            return "[object Array]" === a || "[object Array Iterator]" === a
        };
        a.isObject = function (q, e) {
            return q && "object" === typeof q && (!e || !a.isArray(q))
        };
        a.isNumber = function (a) {
            return "number" === typeof a && !isNaN(a)
        };
        a.erase =
            function (a, e) {
                for (var c = a.length; c--;)if (a[c] === e) {
                    a.splice(c, 1);
                    break
                }
            };
        a.defined = function (a) {
            return void 0 !== a && null !== a
        };
        a.attr = function (q, e, c) {
            var m, n;
            if (a.isString(e))a.defined(c) ? q.setAttribute(e, c) : q && q.getAttribute && (n = q.getAttribute(e)); else if (a.defined(e) && a.isObject(e))for (m in e)q.setAttribute(m, e[m]);
            return n
        };
        a.splat = function (q) {
            return a.isArray(q) ? q : [q]
        };
        a.syncTimeout = function (a, e, c) {
            if (e)return setTimeout(a, e, c);
            a.call(0, c)
        };
        a.pick = function () {
            var a = arguments, e, c, m = a.length;
            for (e =
                     0; e < m; e++)if (c = a[e], void 0 !== c && null !== c)return c
        };
        a.css = function (q, e) {
            a.isMS && !a.svg && e && void 0 !== e.opacity && (e.filter = "alpha(opacity\x3d" + 100 * e.opacity + ")");
            a.extend(q.style, e)
        };
        a.createElement = function (q, e, c, m, n) {
            q = D.createElement(q);
            var l = a.css;
            e && a.extend(q, e);
            n && l(q, {padding: 0, border: "none", margin: 0});
            c && l(q, c);
            m && m.appendChild(q);
            return q
        };
        a.extendClass = function (q, e) {
            var c = function () {
            };
            c.prototype = new q;
            a.extend(c.prototype, e);
            return c
        };
        a.pad = function (a, e, c) {
            return Array((e || 2) + 1 - String(a).length).join(c ||
                    0) + a
        };
        a.relativeLength = function (a, e) {
            return /%$/.test(a) ? e * parseFloat(a) / 100 : parseFloat(a)
        };
        a.wrap = function (a, e, c) {
            var m = a[e];
            a[e] = function () {
                var a = Array.prototype.slice.call(arguments), e = arguments, h = this;
                h.proceed = function () {
                    m.apply(h, arguments.length ? arguments : e)
                };
                a.unshift(m);
                a = c.apply(this, a);
                h.proceed = null;
                return a
            }
        };
        a.getTZOffset = function (q) {
            var e = a.Date;
            return 6E4 * (e.hcGetTimezoneOffset && e.hcGetTimezoneOffset(q) || e.hcTimezoneOffset || 0)
        };
        a.dateFormat = function (q, e, c) {
            if (!a.defined(e) || isNaN(e))return a.defaultOptions.lang.invalidDate ||
                "";
            q = a.pick(q, "%Y-%m-%d %H:%M:%S");
            var m = a.Date, n = new m(e - a.getTZOffset(e)), l, h = n[m.hcGetHours](), b = n[m.hcGetDay](), C = n[m.hcGetDate](), y = n[m.hcGetMonth](), r = n[m.hcGetFullYear](), f = a.defaultOptions.lang, k = f.weekdays, p = f.shortWeekdays, H = a.pad, m = a.extend({
                a: p ? p[b] : k[b].substr(0, 3),
                A: k[b],
                d: H(C),
                e: H(C, 2, " "),
                w: b,
                b: f.shortMonths[y],
                B: f.months[y],
                m: H(y + 1),
                y: r.toString().substr(2, 2),
                Y: r,
                H: H(h),
                k: h,
                I: H(h % 12 || 12),
                l: h % 12 || 12,
                M: H(n[m.hcGetMinutes]()),
                p: 12 > h ? "AM" : "PM",
                P: 12 > h ? "am" : "pm",
                S: H(n.getSeconds()),
                L: H(Math.round(e %
                    1E3), 3)
            }, a.dateFormats);
            for (l in m)for (; -1 !== q.indexOf("%" + l);)q = q.replace("%" + l, "function" === typeof m[l] ? m[l](e) : m[l]);
            return c ? q.substr(0, 1).toUpperCase() + q.substr(1) : q
        };
        a.formatSingle = function (q, e) {
            var c = /\.([0-9])/, m = a.defaultOptions.lang;
            /f$/.test(q) ? (c = (c = q.match(c)) ? c[1] : -1, null !== e && (e = a.numberFormat(e, c, m.decimalPoint, -1 < q.indexOf(",") ? m.thousandsSep : ""))) : e = a.dateFormat(q, e);
            return e
        };
        a.format = function (q, e) {
            for (var c = "{", m = !1, n, l, h, b, C = [], y; q;) {
                c = q.indexOf(c);
                if (-1 === c)break;
                n = q.slice(0,
                    c);
                if (m) {
                    n = n.split(":");
                    l = n.shift().split(".");
                    b = l.length;
                    y = e;
                    for (h = 0; h < b; h++)y = y[l[h]];
                    n.length && (y = a.formatSingle(n.join(":"), y));
                    C.push(y)
                } else C.push(n);
                q = q.slice(c + 1);
                c = (m = !m) ? "}" : "{"
            }
            C.push(q);
            return C.join("")
        };
        a.getMagnitude = function (a) {
            return Math.pow(10, Math.floor(Math.log(a) / Math.LN10))
        };
        a.normalizeTickInterval = function (q, e, c, m, n) {
            var l, h = q;
            c = a.pick(c, 1);
            l = q / c;
            e || (e = n ? [1, 1.2, 1.5, 2, 2.5, 3, 4, 5, 6, 8, 10] : [1, 2, 2.5, 5, 10], !1 === m && (1 === c ? e = a.grep(e, function (a) {
                return 0 === a % 1
            }) : .1 >= c && (e = [1 / c])));
            for (m = 0; m < e.length && !(h = e[m], n && h * c >= q || !n && l <= (e[m] + (e[m + 1] || e[m])) / 2); m++);
            return h * c
        };
        a.stableSort = function (a, e) {
            var c = a.length, m, n;
            for (n = 0; n < c; n++)a[n].safeI = n;
            a.sort(function (a, h) {
                m = e(a, h);
                return 0 === m ? a.safeI - h.safeI : m
            });
            for (n = 0; n < c; n++)delete a[n].safeI
        };
        a.arrayMin = function (a) {
            for (var e = a.length, c = a[0]; e--;)a[e] < c && (c = a[e]);
            return c
        };
        a.arrayMax = function (a) {
            for (var e = a.length, c = a[0]; e--;)a[e] > c && (c = a[e]);
            return c
        };
        a.destroyObjectProperties = function (a, e) {
            for (var c in a)a[c] && a[c] !== e && a[c].destroy &&
            a[c].destroy(), delete a[c]
        };
        a.discardElement = function (q) {
            var e = a.garbageBin;
            e || (e = a.createElement("div"));
            q && e.appendChild(q);
            e.innerHTML = ""
        };
        a.correctFloat = function (a, e) {
            return parseFloat(a.toPrecision(e || 14))
        };
        a.setAnimation = function (q, e) {
            e.renderer.globalAnimation = a.pick(q, e.options.chart.animation, !0)
        };
        a.animObject = function (q) {
            return a.isObject(q) ? a.merge(q) : {duration: q ? 500 : 0}
        };
        a.timeUnits = {
            millisecond: 1,
            second: 1E3,
            minute: 6E4,
            hour: 36E5,
            day: 864E5,
            week: 6048E5,
            month: 24192E5,
            year: 314496E5
        };
        a.numberFormat =
            function (q, e, c, m) {
                q = +q || 0;
                e = +e;
                var n = a.defaultOptions.lang, l = (q.toString().split(".")[1] || "").length, h, b, C = Math.abs(q);
                -1 === e ? e = Math.min(l, 20) : a.isNumber(e) || (e = 2);
                h = String(a.pInt(C.toFixed(e)));
                b = 3 < h.length ? h.length % 3 : 0;
                c = a.pick(c, n.decimalPoint);
                m = a.pick(m, n.thousandsSep);
                q = (0 > q ? "-" : "") + (b ? h.substr(0, b) + m : "");
                q += h.substr(b).replace(/(\d{3})(?=\d)/g, "$1" + m);
                e && (m = Math.abs(C - h + Math.pow(10, -Math.max(e, l) - 1)), q += c + m.toFixed(e).slice(2));
                return q
            };
        Math.easeInOutSine = function (a) {
            return -.5 * (Math.cos(Math.PI *
                    a) - 1)
        };
        a.getStyle = function (q, e) {
            return "width" === e ? Math.min(q.offsetWidth, q.scrollWidth) - a.getStyle(q, "padding-left") - a.getStyle(q, "padding-right") : "height" === e ? Math.min(q.offsetHeight, q.scrollHeight) - a.getStyle(q, "padding-top") - a.getStyle(q, "padding-bottom") : (q = F.getComputedStyle(q, void 0)) && a.pInt(q.getPropertyValue(e))
        };
        a.inArray = function (a, e) {
            return e.indexOf ? e.indexOf(a) : [].indexOf.call(e, a)
        };
        a.grep = function (a, e) {
            return [].filter.call(a, e)
        };
        a.find = function (a, e) {
            return [].find.call(a, e)
        };
        a.map = function (a,
                          e) {
            for (var c = [], m = 0, n = a.length; m < n; m++)c[m] = e.call(a[m], a[m], m, a);
            return c
        };
        a.offset = function (a) {
            var e = D.documentElement;
            a = a.getBoundingClientRect();
            return {
                top: a.top + (F.pageYOffset || e.scrollTop) - (e.clientTop || 0),
                left: a.left + (F.pageXOffset || e.scrollLeft) - (e.clientLeft || 0)
            }
        };
        a.stop = function (a, e) {
            for (var c = w.length; c--;)w[c].elem !== a || e && e !== w[c].prop || (w[c].stopped = !0)
        };
        a.each = function (a, e, c) {
            return Array.prototype.forEach.call(a, e, c)
        };
        a.addEvent = function (q, e, c) {
            function m(a) {
                a.target = a.srcElement ||
                    F;
                c.call(q, a)
            }

            var n = q.hcEvents = q.hcEvents || {};
            q.addEventListener ? q.addEventListener(e, c, !1) : q.attachEvent && (q.hcEventsIE || (q.hcEventsIE = {}), q.hcEventsIE[c.toString()] = m, q.attachEvent("on" + e, m));
            n[e] || (n[e] = []);
            n[e].push(c);
            return function () {
                a.removeEvent(q, e, c)
            }
        };
        a.removeEvent = function (q, e, c) {
            function m(a, b) {
                q.removeEventListener ? q.removeEventListener(a, b, !1) : q.attachEvent && (b = q.hcEventsIE[b.toString()], q.detachEvent("on" + a, b))
            }

            function n() {
                var a, b;
                if (q.nodeName)for (b in e ? (a = {}, a[e] = !0) : a = h, a)if (h[b])for (a =
                                                                                              h[b].length; a--;)m(b, h[b][a])
            }

            var l, h = q.hcEvents, b;
            h && (e ? (l = h[e] || [], c ? (b = a.inArray(c, l), -1 < b && (l.splice(b, 1), h[e] = l), m(e, c)) : (n(), h[e] = [])) : (n(), q.hcEvents = {}))
        };
        a.fireEvent = function (q, e, c, m) {
            var n;
            n = q.hcEvents;
            var l, h;
            c = c || {};
            if (D.createEvent && (q.dispatchEvent || q.fireEvent))n = D.createEvent("Events"), n.initEvent(e, !0, !0), a.extend(n, c), q.dispatchEvent ? q.dispatchEvent(n) : q.fireEvent(e, n); else if (n)for (n = n[e] || [], l = n.length, c.target || a.extend(c, {
                preventDefault: function () {
                    c.defaultPrevented = !0
                }, target: q,
                type: e
            }), e = 0; e < l; e++)(h = n[e]) && !1 === h.call(q, c) && c.preventDefault();
            m && !c.defaultPrevented && m(c)
        };
        a.animate = function (q, e, c) {
            var m, n = "", l, h, b;
            a.isObject(c) || (m = arguments, c = {duration: m[2], easing: m[3], complete: m[4]});
            a.isNumber(c.duration) || (c.duration = 400);
            c.easing = "function" === typeof c.easing ? c.easing : Math[c.easing] || Math.easeInOutSine;
            c.curAnim = a.merge(e);
            for (b in e)a.stop(q, b), h = new a.Fx(q, c, b), l = null, "d" === b ? (h.paths = h.initPath(q, q.d, e.d), h.toD = e.d, m = 0, l = 1) : q.attr ? m = q.attr(b) : (m = parseFloat(a.getStyle(q,
                    b)) || 0, "opacity" !== b && (n = "px")), l || (l = e[b]), l.match && l.match("px") && (l = l.replace(/px/g, "")), h.run(m, l, n)
        };
        a.seriesType = function (q, e, c, m, n) {
            var l = a.getOptions(), h = a.seriesTypes;
            l.plotOptions[q] = a.merge(l.plotOptions[e], c);
            h[q] = a.extendClass(h[e] || function () {
                }, m);
            h[q].prototype.type = q;
            n && (h[q].prototype.pointClass = a.extendClass(a.Point, n));
            return h[q]
        };
        a.uniqueKey = function () {
            var a = Math.random().toString(36).substring(2, 9), e = 0;
            return function () {
                return "highcharts-" + a + "-" + e++
            }
        }();
        F.jQuery && (F.jQuery.fn.highcharts =
            function () {
                var q = [].slice.call(arguments);
                if (this[0])return q[0] ? (new (a[a.isString(q[0]) ? q.shift() : "Chart"])(this[0], q[0], q[1]), this) : B[a.attr(this[0], "data-highcharts-chart")]
            });
        D && !D.defaultView && (a.getStyle = function (q, e) {
            var c = {width: "clientWidth", height: "clientHeight"}[e];
            if (q.style[e])return a.pInt(q.style[e]);
            "opacity" === e && (e = "filter");
            if (c)return q.style.zoom = 1, Math.max(q[c] - 2 * a.getStyle(q, "padding"), 0);
            q = q.currentStyle[e.replace(/\-(\w)/g, function (a, e) {
                return e.toUpperCase()
            })];
            "filter" ===
            e && (q = q.replace(/alpha\(opacity=([0-9]+)\)/, function (a, e) {
                return e / 100
            }));
            return "" === q ? 1 : a.pInt(q)
        });
        Array.prototype.forEach || (a.each = function (a, e, c) {
            for (var m = 0, n = a.length; m < n; m++)if (!1 === e.call(c, a[m], m, a))return m
        });
        Array.prototype.indexOf || (a.inArray = function (a, e) {
            var c, m = 0;
            if (e)for (c = e.length; m < c; m++)if (e[m] === a)return m;
            return -1
        });
        Array.prototype.filter || (a.grep = function (a, e) {
            for (var c = [], m = 0, n = a.length; m < n; m++)e(a[m], m) && c.push(a[m]);
            return c
        });
        Array.prototype.find || (a.find = function (a, e) {
            var c,
                m = a.length;
            for (c = 0; c < m; c++)if (e(a[c], c))return a[c]
        })
    })(K);
    (function (a) {
        var w = a.each, B = a.isNumber, D = a.map, F = a.merge, q = a.pInt;
        a.Color = function (e) {
            if (!(this instanceof a.Color))return new a.Color(e);
            this.init(e)
        };
        a.Color.prototype = {
            parsers: [{
                regex: /rgba\(\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]?(?:\.[0-9]+)?)\s*\)/,
                parse: function (a) {
                    return [q(a[1]), q(a[2]), q(a[3]), parseFloat(a[4], 10)]
                }
            }, {
                regex: /#([a-fA-F0-9]{2})([a-fA-F0-9]{2})([a-fA-F0-9]{2})/, parse: function (a) {
                    return [q(a[1],
                        16), q(a[2], 16), q(a[3], 16), 1]
                }
            }, {
                regex: /rgb\(\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*\)/, parse: function (a) {
                    return [q(a[1]), q(a[2]), q(a[3]), 1]
                }
            }], names: {white: "#ffffff", black: "#000000"}, init: function (e) {
                var c, m, n, l;
                if ((this.input = e = this.names[e] || e) && e.stops)this.stops = D(e.stops, function (h) {
                    return new a.Color(h[1])
                }); else for (n = this.parsers.length; n-- && !m;)l = this.parsers[n], (c = l.regex.exec(e)) && (m = l.parse(c));
                this.rgba = m || []
            }, get: function (a) {
                var c = this.input, e = this.rgba, n;
                this.stops ?
                    (n = F(c), n.stops = [].concat(n.stops), w(this.stops, function (e, h) {
                        n.stops[h] = [n.stops[h][0], e.get(a)]
                    })) : n = e && B(e[0]) ? "rgb" === a || !a && 1 === e[3] ? "rgb(" + e[0] + "," + e[1] + "," + e[2] + ")" : "a" === a ? e[3] : "rgba(" + e.join(",") + ")" : c;
                return n
            }, brighten: function (a) {
                var e, m = this.rgba;
                if (this.stops)w(this.stops, function (e) {
                    e.brighten(a)
                }); else if (B(a) && 0 !== a)for (e = 0; 3 > e; e++)m[e] += q(255 * a), 0 > m[e] && (m[e] = 0), 255 < m[e] && (m[e] = 255);
                return this
            }, setOpacity: function (a) {
                this.rgba[3] = a;
                return this
            }
        };
        a.color = function (e) {
            return new a.Color(e)
        }
    })(K);
    (function (a) {
        var w, B, D = a.addEvent, F = a.animate, q = a.attr, e = a.charts, c = a.color, m = a.css, n = a.createElement, l = a.defined, h = a.deg2rad, b = a.destroyObjectProperties, C = a.doc, y = a.each, r = a.extend, f = a.erase, k = a.grep, p = a.hasTouch, H = a.isArray, t = a.isFirefox, E = a.isMS, G = a.isObject, z = a.isString, d = a.isWebKit, A = a.merge, u = a.noop, I = a.pick, J = a.pInt, g = a.removeEvent, v = a.splat, Q = a.stop, O = a.svg, S = a.SVG_NS, P = a.symbolSizes, L = a.win;
        w = a.SVGElement = function () {
            return this
        };
        w.prototype = {
            opacity: 1,
            SVG_NS: S,
            textProps: "direction fontSize fontWeight fontFamily fontStyle color lineHeight width textDecoration textOverflow textOutline".split(" "),
            init: function (a, g) {
                this.element = "span" === g ? n(g) : C.createElementNS(this.SVG_NS, g);
                this.renderer = a
            },
            animate: function (x, g, f) {
                g = a.animObject(I(g, this.renderer.globalAnimation, !0));
                0 !== g.duration ? (f && (g.complete = f), F(this, x, g)) : this.attr(x, null, f);
                return this
            },
            colorGradient: function (x, g, f) {
                var v = this.renderer, d, b, k, M, p, h, e, t, r, u, c, N = [], m;
                x.linearGradient ? b = "linearGradient" : x.radialGradient && (b = "radialGradient");
                if (b) {
                    k = x[b];
                    p = v.gradients;
                    e = x.stops;
                    u = f.radialReference;
                    H(k) && (x[b] = k = {
                        x1: k[0], y1: k[1], x2: k[2],
                        y2: k[3], gradientUnits: "userSpaceOnUse"
                    });
                    "radialGradient" === b && u && !l(k.gradientUnits) && (M = k, k = A(k, v.getRadialAttr(u, M), {gradientUnits: "userSpaceOnUse"}));
                    for (c in k)"id" !== c && N.push(c, k[c]);
                    for (c in e)N.push(e[c]);
                    N = N.join(",");
                    p[N] ? u = p[N].attr("id") : (k.id = u = a.uniqueKey(), p[N] = h = v.createElement(b).attr(k).add(v.defs), h.radAttr = M, h.stops = [], y(e, function (x) {
                        0 === x[1].indexOf("rgba") ? (d = a.color(x[1]), t = d.get("rgb"), r = d.get("a")) : (t = x[1], r = 1);
                        x = v.createElement("stop").attr({
                            offset: x[0], "stop-color": t,
                            "stop-opacity": r
                        }).add(h);
                        h.stops.push(x)
                    }));
                    m = "url(" + v.url + "#" + u + ")";
                    f.setAttribute(g, m);
                    f.gradient = N;
                    x.toString = function () {
                        return m
                    }
                }
            },
            applyTextOutline: function (a) {
                var x = this.element, g, v, d, b;
                -1 !== a.indexOf("contrast") && (a = a.replace(/contrast/g, this.renderer.getContrast(x.style.fill)));
                this.fakeTS = !0;
                this.ySetter = this.xSetter;
                g = [].slice.call(x.getElementsByTagName("tspan"));
                a = a.split(" ");
                v = a[a.length - 1];
                (d = a[0]) && "none" !== d && (d = d.replace(/(^[\d\.]+)(.*?)$/g, function (a, x, g) {
                    return 2 * x + g
                }), y(g, function (a) {
                    "highcharts-text-outline" ===
                    a.getAttribute("class") && f(g, x.removeChild(a))
                }), b = x.firstChild, y(g, function (a, g) {
                    0 === g && (a.setAttribute("x", x.getAttribute("x")), g = x.getAttribute("y"), a.setAttribute("y", g || 0), null === g && x.setAttribute("y", 0));
                    a = a.cloneNode(1);
                    q(a, {
                        "class": "highcharts-text-outline",
                        fill: v,
                        stroke: v,
                        "stroke-width": d,
                        "stroke-linejoin": "round"
                    });
                    x.insertBefore(a, b)
                }))
            },
            attr: function (a, g, f, v) {
                var x, d = this.element, b, k = this, p;
                "string" === typeof a && void 0 !== g && (x = a, a = {}, a[x] = g);
                if ("string" === typeof a)k = (this[a + "Getter"] ||
                this._defaultGetter).call(this, a, d); else {
                    for (x in a)g = a[x], p = !1, v || Q(this, x), this.symbolName && /^(x|y|width|height|r|start|end|innerR|anchorX|anchorY)/.test(x) && (b || (this.symbolAttr(a), b = !0), p = !0), !this.rotation || "x" !== x && "y" !== x || (this.doTransform = !0), p || (p = this[x + "Setter"] || this._defaultSetter, p.call(this, g, x, d));
                    this.doTransform && (this.updateTransform(), this.doTransform = !1)
                }
                f && f();
                return k
            },
            addClass: function (a, g) {
                var x = this.attr("class") || "";
                -1 === x.indexOf(a) && (g || (a = (x + (x ? " " : "") + a).replace("  ",
                    " ")), this.attr("class", a));
                return this
            },
            hasClass: function (a) {
                return -1 !== q(this.element, "class").indexOf(a)
            },
            removeClass: function (a) {
                q(this.element, "class", (q(this.element, "class") || "").replace(a, ""));
                return this
            },
            symbolAttr: function (a) {
                var x = this;
                y("x y r start end width height innerR anchorX anchorY".split(" "), function (g) {
                    x[g] = I(a[g], x[g])
                });
                x.attr({d: x.renderer.symbols[x.symbolName](x.x, x.y, x.width, x.height, x)})
            },
            clip: function (a) {
                return this.attr("clip-path", a ? "url(" + this.renderer.url + "#" + a.id +
                ")" : "none")
            },
            crisp: function (a, g) {
                var x, f = {}, v;
                g = g || a.strokeWidth || 0;
                v = Math.round(g) % 2 / 2;
                a.x = Math.floor(a.x || this.x || 0) + v;
                a.y = Math.floor(a.y || this.y || 0) + v;
                a.width = Math.floor((a.width || this.width || 0) - 2 * v);
                a.height = Math.floor((a.height || this.height || 0) - 2 * v);
                l(a.strokeWidth) && (a.strokeWidth = g);
                for (x in a)this[x] !== a[x] && (this[x] = f[x] = a[x]);
                return f
            },
            css: function (a) {
                var x = this.styles, g = {}, f = this.element, v, d, b = "";
                v = !x;
                a && a.color && (a.fill = a.color);
                if (x)for (d in a)a[d] !== x[d] && (g[d] = a[d], v = !0);
                if (v) {
                    v = this.textWidth =
                        a && a.width && "text" === f.nodeName.toLowerCase() && J(a.width) || this.textWidth;
                    x && (a = r(x, g));
                    this.styles = a;
                    v && !O && this.renderer.forExport && delete a.width;
                    if (E && !O)m(this.element, a); else {
                        x = function (a, x) {
                            return "-" + x.toLowerCase()
                        };
                        for (d in a)b += d.replace(/([A-Z])/g, x) + ":" + a[d] + ";";
                        q(f, "style", b)
                    }
                    this.added && (v && this.renderer.buildText(this), a && a.textOutline && this.applyTextOutline(a.textOutline))
                }
                return this
            },
            getStyle: function (a) {
                return L.getComputedStyle(this.element || this, "").getPropertyValue(a)
            },
            strokeWidth: function () {
                var a =
                    this.getStyle("stroke-width"), g;
                a.indexOf("px") === a.length - 2 ? a = J(a) : (g = C.createElementNS(S, "rect"), q(g, {
                    width: a,
                    "stroke-width": 0
                }), this.element.parentNode.appendChild(g), a = g.getBBox().width, g.parentNode.removeChild(g));
                return a
            },
            on: function (a, g) {
                var x = this, f = x.element;
                p && "click" === a ? (f.ontouchstart = function (a) {
                    x.touchEventFired = Date.now();
                    a.preventDefault();
                    g.call(f, a)
                }, f.onclick = function (a) {
                    (-1 === L.navigator.userAgent.indexOf("Android") || 1100 < Date.now() - (x.touchEventFired || 0)) && g.call(f, a)
                }) : f["on" +
                a] = g;
                return this
            },
            setRadialReference: function (a) {
                var x = this.renderer.gradients[this.element.gradient];
                this.element.radialReference = a;
                x && x.radAttr && x.animate(this.renderer.getRadialAttr(a, x.radAttr));
                return this
            },
            translate: function (a, g) {
                return this.attr({translateX: a, translateY: g})
            },
            invert: function (a) {
                this.inverted = a;
                this.updateTransform();
                return this
            },
            updateTransform: function () {
                var a = this.translateX || 0, g = this.translateY || 0, f = this.scaleX, v = this.scaleY, d = this.inverted, b = this.rotation, k = this.element;
                d && (a += this.attr("width"), g += this.attr("height"));
                a = ["translate(" + a + "," + g + ")"];
                d ? a.push("rotate(90) scale(-1,1)") : b && a.push("rotate(" + b + " " + (k.getAttribute("x") || 0) + " " + (k.getAttribute("y") || 0) + ")");
                (l(f) || l(v)) && a.push("scale(" + I(f, 1) + " " + I(v, 1) + ")");
                a.length && k.setAttribute("transform", a.join(" "))
            },
            toFront: function () {
                var a = this.element;
                a.parentNode.appendChild(a);
                return this
            },
            align: function (a, g, v) {
                var x, d, b, k, p = {};
                d = this.renderer;
                b = d.alignedObjects;
                var h, e;
                if (a) {
                    if (this.alignOptions = a, this.alignByTranslate =
                            g, !v || z(v))this.alignTo = x = v || "renderer", f(b, this), b.push(this), v = null
                } else a = this.alignOptions, g = this.alignByTranslate, x = this.alignTo;
                v = I(v, d[x], d);
                x = a.align;
                d = a.verticalAlign;
                b = (v.x || 0) + (a.x || 0);
                k = (v.y || 0) + (a.y || 0);
                "right" === x ? h = 1 : "center" === x && (h = 2);
                h && (b += (v.width - (a.width || 0)) / h);
                p[g ? "translateX" : "x"] = Math.round(b);
                "bottom" === d ? e = 1 : "middle" === d && (e = 2);
                e && (k += (v.height - (a.height || 0)) / e);
                p[g ? "translateY" : "y"] = Math.round(k);
                this[this.placed ? "animate" : "attr"](p);
                this.placed = !0;
                this.alignAttr = p;
                return this
            },
            getBBox: function (a, g) {
                var x, f = this.renderer, v, d = this.element, b = this.styles, k, p = this.textStr, e, t = f.cache, u = f.cacheKeys, c;
                g = I(g, this.rotation);
                v = g * h;
                k = d && w.prototype.getStyle.call(d, "font-size");
                void 0 !== p && (c = p.toString(), -1 === c.indexOf("\x3c") && (c = c.replace(/[0-9]/g, "0")), c += ["", g || 0, k, d.style.width, d.style["text-overflow"]].join());
                c && !a && (x = t[c]);
                if (!x) {
                    if (d.namespaceURI === this.SVG_NS || f.forExport) {
                        try {
                            (e = this.fakeTS && function (a) {
                                    y(d.querySelectorAll(".highcharts-text-outline"), function (x) {
                                        x.style.display =
                                            a
                                    })
                                }) && e("none"), x = d.getBBox ? r({}, d.getBBox()) : {
                                width: d.offsetWidth,
                                height: d.offsetHeight
                            }, e && e("")
                        } catch (T) {
                        }
                        if (!x || 0 > x.width)x = {width: 0, height: 0}
                    } else x = this.htmlGetBBox();
                    f.isSVG && (a = x.width, f = x.height, E && b && "11px" === b.fontSize && "16.9" === f.toPrecision(3) && (x.height = f = 14), g && (x.width = Math.abs(f * Math.sin(v)) + Math.abs(a * Math.cos(v)), x.height = Math.abs(f * Math.cos(v)) + Math.abs(a * Math.sin(v))));
                    if (c && 0 < x.height) {
                        for (; 250 < u.length;)delete t[u.shift()];
                        t[c] || u.push(c);
                        t[c] = x
                    }
                }
                return x
            },
            show: function (a) {
                return this.attr({
                    visibility: a ?
                        "inherit" : "visible"
                })
            },
            hide: function () {
                return this.attr({visibility: "hidden"})
            },
            fadeOut: function (a) {
                var x = this;
                x.animate({opacity: 0}, {
                    duration: a || 150, complete: function () {
                        x.attr({y: -9999})
                    }
                })
            },
            add: function (a) {
                var x = this.renderer, g = this.element, f;
                a && (this.parentGroup = a);
                this.parentInverted = a && a.inverted;
                void 0 !== this.textStr && x.buildText(this);
                this.added = !0;
                if (!a || a.handleZ || this.zIndex)f = this.zIndexSetter();
                f || (a ? a.element : x.box).appendChild(g);
                if (this.onAdd)this.onAdd();
                return this
            },
            safeRemoveChild: function (a) {
                var x =
                    a.parentNode;
                x && x.removeChild(a)
            },
            destroy: function () {
                var a = this.element || {}, g = this.renderer.isSVG && "SPAN" === a.nodeName && this.parentGroup, v, d;
                a.onclick = a.onmouseout = a.onmouseover = a.onmousemove = a.point = null;
                Q(this);
                this.clipPath && (this.clipPath = this.clipPath.destroy());
                if (this.stops) {
                    for (d = 0; d < this.stops.length; d++)this.stops[d] = this.stops[d].destroy();
                    this.stops = null
                }
                for (this.safeRemoveChild(a); g && g.div && 0 === g.div.childNodes.length;)a = g.parentGroup, this.safeRemoveChild(g.div), delete g.div, g = a;
                this.alignTo &&
                f(this.renderer.alignedObjects, this);
                for (v in this)delete this[v];
                return null
            },
            xGetter: function (a) {
                "circle" === this.element.nodeName && ("x" === a ? a = "cx" : "y" === a && (a = "cy"));
                return this._defaultGetter(a)
            },
            _defaultGetter: function (a) {
                a = I(this[a], this.element ? this.element.getAttribute(a) : null, 0);
                /^[\-0-9\.]+$/.test(a) && (a = parseFloat(a));
                return a
            },
            dSetter: function (a, g, f) {
                a && a.join && (a = a.join(" "));
                /(NaN| {2}|^$)/.test(a) && (a = "M 0 0");
                f.setAttribute(g, a);
                this[g] = a
            },
            alignSetter: function (a) {
                this.element.setAttribute("text-anchor",
                    {left: "start", center: "middle", right: "end"}[a])
            },
            opacitySetter: function (a, g, f) {
                this[g] = a;
                f.setAttribute(g, a)
            },
            titleSetter: function (a) {
                var g = this.element.getElementsByTagName("title")[0];
                g || (g = C.createElementNS(this.SVG_NS, "title"), this.element.appendChild(g));
                g.firstChild && g.removeChild(g.firstChild);
                g.appendChild(C.createTextNode(String(I(a), "").replace(/<[^>]*>/g, "")))
            },
            textSetter: function (a) {
                a !== this.textStr && (delete this.bBox, this.textStr = a, this.added && this.renderer.buildText(this))
            },
            fillSetter: function (a,
                                  g, f) {
                "string" === typeof a ? f.setAttribute(g, a) : a && this.colorGradient(a, g, f)
            },
            visibilitySetter: function (a, g, f) {
                "inherit" === a ? f.removeAttribute(g) : f.setAttribute(g, a)
            },
            zIndexSetter: function (a, g) {
                var f = this.renderer, v = this.parentGroup, d = (v || f).element || f.box, x, b = this.element, k;
                x = this.added;
                var p;
                l(a) && (b.zIndex = a, a = +a, this[g] === a && (x = !1), this[g] = a);
                if (x) {
                    (a = this.zIndex) && v && (v.handleZ = !0);
                    g = d.childNodes;
                    for (p = 0; p < g.length && !k; p++)v = g[p], x = v.zIndex, v !== b && (J(x) > a || !l(a) && l(x) || 0 > a && !l(x) && d !== f.box) && (d.insertBefore(b,
                        v), k = !0);
                    k || d.appendChild(b)
                }
                return k
            },
            _defaultSetter: function (a, g, f) {
                f.setAttribute(g, a)
            }
        };
        w.prototype.yGetter = w.prototype.xGetter;
        w.prototype.translateXSetter = w.prototype.translateYSetter = w.prototype.rotationSetter = w.prototype.verticalAlignSetter = w.prototype.scaleXSetter = w.prototype.scaleYSetter = function (a, g) {
            this[g] = a;
            this.doTransform = !0
        };
        B = a.SVGRenderer = function () {
            this.init.apply(this, arguments)
        };
        B.prototype = {
            Element: w, SVG_NS: S, init: function (a, g, f, v, b, k) {
                var x;
                v = this.createElement("svg").attr({
                    version: "1.1",
                    "class": "highcharts-root"
                });
                x = v.element;
                a.appendChild(x);
                -1 === a.innerHTML.indexOf("xmlns") && q(x, "xmlns", this.SVG_NS);
                this.isSVG = !0;
                this.box = x;
                this.boxWrapper = v;
                this.alignedObjects = [];
                this.url = (t || d) && C.getElementsByTagName("base").length ? L.location.href.replace(/#.*?$/, "").replace(/([\('\)])/g, "\\$1").replace(/ /g, "%20") : "";
                this.createElement("desc").add().element.appendChild(C.createTextNode("Created with Highcharts 5.0.6"));
                this.defs = this.createElement("defs").add();
                this.allowHTML = k;
                this.forExport =
                    b;
                this.gradients = {};
                this.cache = {};
                this.cacheKeys = [];
                this.imgCount = 0;
                this.setSize(g, f, !1);
                var p;
                t && a.getBoundingClientRect && (g = function () {
                    m(a, {left: 0, top: 0});
                    p = a.getBoundingClientRect();
                    m(a, {left: Math.ceil(p.left) - p.left + "px", top: Math.ceil(p.top) - p.top + "px"})
                }, g(), this.unSubPixelFix = D(L, "resize", g))
            }, definition: function (a) {
                function g(a, d) {
                    var x;
                    y(v(a), function (a) {
                        var v = f.createElement(a.tagName), b, k = {};
                        for (b in a)"tagName" !== b && "children" !== b && "textContent" !== b && (k[b] = a[b]);
                        v.attr(k);
                        v.add(d || f.defs);
                        a.textContent && v.element.appendChild(C.createTextNode(a.textContent));
                        g(a.children || [], v);
                        x = v
                    });
                    return x
                }

                var f = this;
                return g(a)
            }, isHidden: function () {
                return !this.boxWrapper.getBBox().width
            }, destroy: function () {
                var a = this.defs;
                this.box = null;
                this.boxWrapper = this.boxWrapper.destroy();
                b(this.gradients || {});
                this.gradients = null;
                a && (this.defs = a.destroy());
                this.unSubPixelFix && this.unSubPixelFix();
                return this.alignedObjects = null
            }, createElement: function (a) {
                var g = new this.Element;
                g.init(this, a);
                return g
            }, draw: u,
            getRadialAttr: function (a, g) {
                return {cx: a[0] - a[2] / 2 + g.cx * a[2], cy: a[1] - a[2] / 2 + g.cy * a[2], r: g.r * a[2]}
            }, buildText: function (a) {
                for (var g = a.element, f = this, v = f.forExport, d = I(a.textStr, "").toString(), x = -1 !== d.indexOf("\x3c"), b = g.childNodes, p, h, e, t, c = q(g, "x"), r = a.styles, u = a.textWidth, l = r && r.lineHeight, A = r && r.textOutline, n = r && "ellipsis" === r.textOverflow, L = b.length, z = u && !a.added && this.box, E = function (a) {
                    return l ? J(l) : f.fontMetrics(void 0, a.getAttribute("style") ? a : g).h
                }; L--;)g.removeChild(b[L]);
                x || A || n || u || -1 !==
                d.indexOf(" ") ? (p = /<.*class="([^"]+)".*>/, h = /<.*style="([^"]+)".*>/, e = /<.*href="(http[^"]+)".*>/, z && z.appendChild(g), d = x ? d.replace(/<(b|strong)>/g, '\x3cspan style\x3d"font-weight:bold"\x3e').replace(/<(i|em)>/g, '\x3cspan style\x3d"font-style:italic"\x3e').replace(/<a/g, "\x3cspan").replace(/<\/(b|strong|i|em|a)>/g, "\x3c/span\x3e").split(/<br.*?>/g) : [d], d = k(d, function (a) {
                    return "" !== a
                }), y(d, function (d, x) {
                    var b, k = 0;
                    d = d.replace(/^\s+|\s+$/g, "").replace(/<span/g, "|||\x3cspan").replace(/<\/span>/g, "\x3c/span\x3e|||");
                    b = d.split("|||");
                    y(b, function (d) {
                        if ("" !== d || 1 === b.length) {
                            var l = {}, A = C.createElementNS(f.SVG_NS, "tspan"), y, L;
                            p.test(d) && (y = d.match(p)[1], q(A, "class", y));
                            h.test(d) && (L = d.match(h)[1].replace(/(;| |^)color([ :])/, "$1fill$2"), q(A, "style", L));
                            e.test(d) && !v && (q(A, "onclick", 'location.href\x3d"' + d.match(e)[1] + '"'), m(A, {cursor: "pointer"}));
                            d = (d.replace(/<(.|\n)*?>/g, "") || " ").replace(/&lt;/g, "\x3c").replace(/&gt;/g, "\x3e");
                            if (" " !== d) {
                                A.appendChild(C.createTextNode(d));
                                k ? l.dx = 0 : x && null !== c && (l.x = c);
                                q(A, l);
                                g.appendChild(A);
                                !k && x && (!O && v && m(A, {display: "block"}), q(A, "dy", E(A)));
                                if (u) {
                                    l = d.replace(/([^\^])-/g, "$1- ").split(" ");
                                    y = "nowrap" === r.whiteSpace;
                                    for (var z = 1 < b.length || x || 1 < l.length && !y, M, G, Q = [], J = E(A), N = a.rotation, I = d, H = I.length; (z || n) && (l.length || Q.length);)a.rotation = 0, M = a.getBBox(!0), G = M.width, !O && f.forExport && (G = f.measureSpanWidth(A.firstChild.data, a.styles)), M = G > u, void 0 === t && (t = M), n && t ? (H /= 2, "" === I || !M && .5 > H ? l = [] : (I = d.substring(0, I.length + (M ? -1 : 1) * Math.ceil(H)), l = [I + (3 < u ? "\u2026" : "")], A.removeChild(A.firstChild))) :
                                        M && 1 !== l.length ? (A.removeChild(A.firstChild), Q.unshift(l.pop())) : (l = Q, Q = [], l.length && !y && (A = C.createElementNS(S, "tspan"), q(A, {
                                            dy: J,
                                            x: c
                                        }), L && q(A, "style", L), g.appendChild(A)), G > u && (u = G)), l.length && A.appendChild(C.createTextNode(l.join(" ").replace(/- /g, "-")));
                                    a.rotation = N
                                }
                                k++
                            }
                        }
                    })
                }), t && a.attr("title", a.textStr), z && z.removeChild(g), A && a.applyTextOutline && a.applyTextOutline(A)) : g.appendChild(C.createTextNode(d.replace(/&lt;/g, "\x3c").replace(/&gt;/g, "\x3e")))
            }, getContrast: function (a) {
                a = c(a).rgba;
                return 510 <
                a[0] + a[1] + a[2] ? "#000000" : "#FFFFFF"
            }, button: function (a, g, d, f, v, b, k, p, h) {
                var x = this.label(a, g, d, h, null, null, null, null, "button"), e = 0;
                x.attr(A({padding: 8, r: 2}, v));
                D(x.element, E ? "mouseover" : "mouseenter", function () {
                    3 !== e && x.setState(1)
                });
                D(x.element, E ? "mouseout" : "mouseleave", function () {
                    3 !== e && x.setState(e)
                });
                x.setState = function (a) {
                    1 !== a && (x.state = e = a);
                    x.removeClass(/highcharts-button-(normal|hover|pressed|disabled)/).addClass("highcharts-button-" + ["normal", "hover", "pressed", "disabled"][a || 0])
                };
                return x.on("click",
                    function (a) {
                        3 !== e && f.call(x, a)
                    })
            }, crispLine: function (a, g) {
                a[1] === a[4] && (a[1] = a[4] = Math.round(a[1]) - g % 2 / 2);
                a[2] === a[5] && (a[2] = a[5] = Math.round(a[2]) + g % 2 / 2);
                return a
            }, path: function (a) {
                var g = {};
                H(a) ? g.d = a : G(a) && r(g, a);
                return this.createElement("path").attr(g)
            }, circle: function (a, g, d) {
                a = G(a) ? a : {x: a, y: g, r: d};
                g = this.createElement("circle");
                g.xSetter = g.ySetter = function (a, g, d) {
                    d.setAttribute("c" + g, a)
                };
                return g.attr(a)
            }, arc: function (a, g, d, f, v, b) {
                G(a) && (g = a.y, d = a.r, f = a.innerR, v = a.start, b = a.end, a = a.x);
                a = this.symbol("arc",
                    a || 0, g || 0, d || 0, d || 0, {innerR: f || 0, start: v || 0, end: b || 0});
                a.r = d;
                return a
            }, rect: function (a, g, d, f, v, b) {
                v = G(a) ? a.r : v;
                b = this.createElement("rect");
                a = G(a) ? a : void 0 === a ? {} : {x: a, y: g, width: Math.max(d, 0), height: Math.max(f, 0)};
                v && (a.r = v);
                b.rSetter = function (a, g, d) {
                    q(d, {rx: a, ry: a})
                };
                return b.attr(a)
            }, setSize: function (a, g, d) {
                var f = this.alignedObjects, v = f.length;
                this.width = a;
                this.height = g;
                for (this.boxWrapper.animate({width: a, height: g}, {
                    step: function () {
                        this.attr({viewBox: "0 0 " + this.attr("width") + " " + this.attr("height")})
                    },
                    duration: I(d, !0) ? void 0 : 0
                }); v--;)f[v].align()
            }, g: function (a) {
                var g = this.createElement("g");
                return a ? g.attr({"class": "highcharts-" + a}) : g
            }, image: function (a, g, d, f, v) {
                var b = {preserveAspectRatio: "none"};
                1 < arguments.length && r(b, {x: g, y: d, width: f, height: v});
                b = this.createElement("image").attr(b);
                b.element.setAttributeNS ? b.element.setAttributeNS("http://www.w3.org/1999/xlink", "href", a) : b.element.setAttribute("hc-svg-href", a);
                return b
            }, symbol: function (a, g, d, f, v, b) {
                var k = this, x, p = this.symbols[a], h = l(g) && p && p(Math.round(g),
                        Math.round(d), f, v, b), t = /^url\((.*?)\)$/, c, u;
                p ? (x = this.path(h), r(x, {
                    symbolName: a,
                    x: g,
                    y: d,
                    width: f,
                    height: v
                }), b && r(x, b)) : t.test(a) && (c = a.match(t)[1], x = this.image(c), x.imgwidth = I(P[c] && P[c].width, b && b.width), x.imgheight = I(P[c] && P[c].height, b && b.height), u = function () {
                    x.attr({width: x.width, height: x.height})
                }, y(["width", "height"], function (a) {
                    x[a + "Setter"] = function (a, g) {
                        var d = {}, f = this["img" + g], v = "width" === g ? "translateX" : "translateY";
                        this[g] = a;
                        l(f) && (this.element && this.element.setAttribute(g, f), this.alignByTranslate ||
                        (d[v] = ((this[g] || 0) - f) / 2, this.attr(d)))
                    }
                }), l(g) && x.attr({
                    x: g,
                    y: d
                }), x.isImg = !0, l(x.imgwidth) && l(x.imgheight) ? u() : (x.attr({
                    width: 0,
                    height: 0
                }), n("img", {
                    onload: function () {
                        var a = e[k.chartIndex];
                        0 === this.width && (m(this, {position: "absolute", top: "-999em"}), C.body.appendChild(this));
                        P[c] = {width: this.width, height: this.height};
                        x.imgwidth = this.width;
                        x.imgheight = this.height;
                        x.element && u();
                        this.parentNode && this.parentNode.removeChild(this);
                        k.imgCount--;
                        if (!k.imgCount && a && a.onload)a.onload()
                    }, src: c
                }), this.imgCount++));
                return x
            }, symbols: {
                circle: function (a, g, d, f) {
                    var v = .166 * d;
                    return ["M", a + d / 2, g, "C", a + d + v, g, a + d + v, g + f, a + d / 2, g + f, "C", a - v, g + f, a - v, g, a + d / 2, g, "Z"]
                }, square: function (a, g, d, f) {
                    return ["M", a, g, "L", a + d, g, a + d, g + f, a, g + f, "Z"]
                }, triangle: function (a, g, d, f) {
                    return ["M", a + d / 2, g, "L", a + d, g + f, a, g + f, "Z"]
                }, "triangle-down": function (a, g, d, f) {
                    return ["M", a, g, "L", a + d, g, a + d / 2, g + f, "Z"]
                }, diamond: function (a, g, d, f) {
                    return ["M", a + d / 2, g, "L", a + d, g + f / 2, a + d / 2, g + f, a, g + f / 2, "Z"]
                }, arc: function (a, g, d, f, v) {
                    var b = v.start;
                    d = v.r || d || f;
                    var k = v.end - .001;
                    f = v.innerR;
                    var p = v.open, x = Math.cos(b), h = Math.sin(b), e = Math.cos(k), k = Math.sin(k);
                    v = v.end - b < Math.PI ? 0 : 1;
                    return ["M", a + d * x, g + d * h, "A", d, d, 0, v, 1, a + d * e, g + d * k, p ? "M" : "L", a + f * e, g + f * k, "A", f, f, 0, v, 0, a + f * x, g + f * h, p ? "" : "Z"]
                }, callout: function (a, g, d, f, v) {
                    var b = Math.min(v && v.r || 0, d, f), k = b + 6, p = v && v.anchorX;
                    v = v && v.anchorY;
                    var h;
                    h = ["M", a + b, g, "L", a + d - b, g, "C", a + d, g, a + d, g, a + d, g + b, "L", a + d, g + f - b, "C", a + d, g + f, a + d, g + f, a + d - b, g + f, "L", a + b, g + f, "C", a, g + f, a, g + f, a, g + f - b, "L", a, g + b, "C", a, g, a, g, a + b, g];
                    p && p > d ? v > g + k && v < g + f - k ? h.splice(13,
                        3, "L", a + d, v - 6, a + d + 6, v, a + d, v + 6, a + d, g + f - b) : h.splice(13, 3, "L", a + d, f / 2, p, v, a + d, f / 2, a + d, g + f - b) : p && 0 > p ? v > g + k && v < g + f - k ? h.splice(33, 3, "L", a, v + 6, a - 6, v, a, v - 6, a, g + b) : h.splice(33, 3, "L", a, f / 2, p, v, a, f / 2, a, g + b) : v && v > f && p > a + k && p < a + d - k ? h.splice(23, 3, "L", p + 6, g + f, p, g + f + 6, p - 6, g + f, a + b, g + f) : v && 0 > v && p > a + k && p < a + d - k && h.splice(3, 3, "L", p - 6, g, p, g - 6, p + 6, g, d - b, g);
                    return h
                }
            }, clipRect: function (g, d, f, v) {
                var b = a.uniqueKey(), k = this.createElement("clipPath").attr({id: b}).add(this.defs);
                g = this.rect(g, d, f, v, 0).add(k);
                g.id = b;
                g.clipPath =
                    k;
                g.count = 0;
                return g
            }, text: function (a, g, d, f) {
                var v = !O && this.forExport, b = {};
                if (f && (this.allowHTML || !this.forExport))return this.html(a, g, d);
                b.x = Math.round(g || 0);
                d && (b.y = Math.round(d));
                if (a || 0 === a)b.text = a;
                a = this.createElement("text").attr(b);
                v && a.css({position: "absolute"});
                f || (a.xSetter = function (a, g, d) {
                    var f = d.getElementsByTagName("tspan"), v, b = d.getAttribute(g), k;
                    for (k = 0; k < f.length; k++)v = f[k], v.getAttribute(g) === b && v.setAttribute(g, a);
                    d.setAttribute(g, a)
                });
                return a
            }, fontMetrics: function (a, g) {
                a = g &&
                    w.prototype.getStyle.call(g, "font-size");
                a = /px/.test(a) ? J(a) : /em/.test(a) ? parseFloat(a) * (g ? this.fontMetrics(null, g.parentNode).f : 16) : 12;
                g = 24 > a ? a + 3 : Math.round(1.2 * a);
                return {h: g, b: Math.round(.8 * g), f: a}
            }, rotCorr: function (a, g, d) {
                var f = a;
                g && d && (f = Math.max(f * Math.cos(g * h), 4));
                return {x: -a / 3 * Math.sin(g * h), y: f}
            }, label: function (a, d, f, v, b, k, p, h, e) {
                var t = this, c = t.g("button" !== e && "label"), u = c.text = t.text("", 0, 0, p).attr({zIndex: 1}), x, m, L = 0, n = 3, z = 0, E, G, C, Q, O, J = {}, I, H = /^url\((.*?)\)$/.test(v), q = H, S, N, M, P;
                e && c.addClass("highcharts-" +
                    e);
                q = !0;
                S = function () {
                    return x.strokeWidth() % 2 / 2
                };
                N = function () {
                    var a = u.element.style, g = {};
                    m = (void 0 === E || void 0 === G || O) && l(u.textStr) && u.getBBox();
                    c.width = (E || m.width || 0) + 2 * n + z;
                    c.height = (G || m.height || 0) + 2 * n;
                    I = n + t.fontMetrics(a && a.fontSize, u).b;
                    q && (x || (c.box = x = t.symbols[v] || H ? t.symbol(v) : t.rect(), x.addClass(("button" === e ? "" : "highcharts-label-box") + (e ? " highcharts-" + e + "-box" : "")), x.add(c), a = S(), g.x = a, g.y = (h ? -I : 0) + a), g.width = Math.round(c.width), g.height = Math.round(c.height), x.attr(r(g, J)), J = {})
                };
                M = function () {
                    var a =
                        z + n, g;
                    g = h ? 0 : I;
                    l(E) && m && ("center" === O || "right" === O) && (a += {center: .5, right: 1}[O] * (E - m.width));
                    if (a !== u.x || g !== u.y)u.attr("x", a), void 0 !== g && u.attr("y", g);
                    u.x = a;
                    u.y = g
                };
                P = function (a, g) {
                    x ? x.attr(a, g) : J[a] = g
                };
                c.onAdd = function () {
                    u.add(c);
                    c.attr({text: a || 0 === a ? a : "", x: d, y: f});
                    x && l(b) && c.attr({anchorX: b, anchorY: k})
                };
                c.widthSetter = function (a) {
                    E = a
                };
                c.heightSetter = function (a) {
                    G = a
                };
                c["text-alignSetter"] = function (a) {
                    O = a
                };
                c.paddingSetter = function (a) {
                    l(a) && a !== n && (n = c.padding = a, M())
                };
                c.paddingLeftSetter = function (a) {
                    l(a) &&
                    a !== z && (z = a, M())
                };
                c.alignSetter = function (a) {
                    a = {left: 0, center: .5, right: 1}[a];
                    a !== L && (L = a, m && c.attr({x: C}))
                };
                c.textSetter = function (a) {
                    void 0 !== a && u.textSetter(a);
                    N();
                    M()
                };
                c["stroke-widthSetter"] = function (a, g) {
                    a && (q = !0);
                    this["stroke-width"] = a;
                    P(g, a)
                };
                c.rSetter = function (a, g) {
                    P(g, a)
                };
                c.anchorXSetter = function (a, g) {
                    b = a;
                    P(g, Math.round(a) - S() - C)
                };
                c.anchorYSetter = function (a, g) {
                    k = a;
                    P(g, a - Q)
                };
                c.xSetter = function (a) {
                    c.x = a;
                    L && (a -= L * ((E || m.width) + 2 * n));
                    C = Math.round(a);
                    c.attr("translateX", C)
                };
                c.ySetter = function (a) {
                    Q =
                        c.y = Math.round(a);
                    c.attr("translateY", Q)
                };
                var R = c.css;
                return r(c, {
                    css: function (a) {
                        if (a) {
                            var g = {};
                            a = A(a);
                            y(c.textProps, function (d) {
                                void 0 !== a[d] && (g[d] = a[d], delete a[d])
                            });
                            u.css(g)
                        }
                        return R.call(c, a)
                    }, getBBox: function () {
                        return {width: m.width + 2 * n, height: m.height + 2 * n, x: m.x - n, y: m.y - n}
                    }, destroy: function () {
                        g(c.element, "mouseenter");
                        g(c.element, "mouseleave");
                        u && (u = u.destroy());
                        x && (x = x.destroy());
                        w.prototype.destroy.call(c);
                        c = t = N = M = P = null
                    }
                })
            }
        };
        a.Renderer = B
    })(K);
    (function (a) {
        var w = a.attr, B = a.createElement,
            D = a.css, F = a.defined, q = a.each, e = a.extend, c = a.isFirefox, m = a.isMS, n = a.isWebKit, l = a.pInt, h = a.SVGRenderer, b = a.win, C = a.wrap;
        e(a.SVGElement.prototype, {
            htmlCss: function (a) {
                var b = this.element;
                if (b = a && "SPAN" === b.tagName && a.width)delete a.width, this.textWidth = b, this.updateTransform();
                a && "ellipsis" === a.textOverflow && (a.whiteSpace = "nowrap", a.overflow = "hidden");
                this.styles = e(this.styles, a);
                D(this.element, a);
                return this
            }, htmlGetBBox: function () {
                var a = this.element;
                "text" === a.nodeName && (a.style.position = "absolute");
                return {x: a.offsetLeft, y: a.offsetTop, width: a.offsetWidth, height: a.offsetHeight}
            }, htmlUpdateTransform: function () {
                if (this.added) {
                    var a = this.renderer, b = this.element, f = this.x || 0, k = this.y || 0, p = this.textAlign || "left", h = {
                        left: 0,
                        center: .5,
                        right: 1
                    }[p], c = this.styles;
                    D(b, {marginLeft: this.translateX || 0, marginTop: this.translateY || 0});
                    this.inverted && q(b.childNodes, function (d) {
                        a.invertChild(d, b)
                    });
                    if ("SPAN" === b.tagName) {
                        var e = this.rotation, m = l(this.textWidth), z = c && c.whiteSpace, d = [e, p, b.innerHTML, this.textWidth, this.textAlign].join();
                        d !== this.cTT && (c = a.fontMetrics(b.style.fontSize).b, F(e) && this.setSpanRotation(e, h, c), D(b, {
                            width: "",
                            whiteSpace: z || "nowrap"
                        }), b.offsetWidth > m && /[ \-]/.test(b.textContent || b.innerText) && D(b, {
                            width: m + "px",
                            display: "block",
                            whiteSpace: z || "normal"
                        }), this.getSpanCorrection(b.offsetWidth, c, h, e, p));
                        D(b, {left: f + (this.xCorr || 0) + "px", top: k + (this.yCorr || 0) + "px"});
                        n && (c = b.offsetHeight);
                        this.cTT = d
                    }
                } else this.alignOnAdd = !0
            }, setSpanRotation: function (a, h, f) {
                var k = {}, p = m ? "-ms-transform" : n ? "-webkit-transform" : c ? "MozTransform" :
                    b.opera ? "-o-transform" : "";
                k[p] = k.transform = "rotate(" + a + "deg)";
                k[p + (c ? "Origin" : "-origin")] = k.transformOrigin = 100 * h + "% " + f + "px";
                D(this.element, k)
            }, getSpanCorrection: function (a, b, f) {
                this.xCorr = -a * f;
                this.yCorr = -b
            }
        });
        e(h.prototype, {
            html: function (a, b, f) {
                var k = this.createElement("span"), p = k.element, h = k.renderer, c = h.isSVG, l = function (a, f) {
                    q(["opacity", "visibility"], function (d) {
                        C(a, d + "Setter", function (a, d, b, k) {
                            a.call(this, d, b, k);
                            f[b] = d
                        })
                    })
                };
                k.textSetter = function (a) {
                    a !== p.innerHTML && delete this.bBox;
                    p.innerHTML =
                        this.textStr = a;
                    k.htmlUpdateTransform()
                };
                c && l(k, k.element.style);
                k.xSetter = k.ySetter = k.alignSetter = k.rotationSetter = function (a, f) {
                    "align" === f && (f = "textAlign");
                    k[f] = a;
                    k.htmlUpdateTransform()
                };
                k.attr({text: a, x: Math.round(b), y: Math.round(f)}).css({position: "absolute"});
                p.style.whiteSpace = "nowrap";
                k.css = k.htmlCss;
                c && (k.add = function (a) {
                    var f, d = h.box.parentNode, b = [];
                    if (this.parentGroup = a) {
                        if (f = a.div, !f) {
                            for (; a;)b.push(a), a = a.parentGroup;
                            q(b.reverse(), function (a) {
                                var p, h = w(a.element, "class");
                                h && (h = {className: h});
                                f = a.div = a.div || B("div", h, {
                                        position: "absolute",
                                        left: (a.translateX || 0) + "px",
                                        top: (a.translateY || 0) + "px",
                                        display: a.display,
                                        opacity: a.opacity,
                                        pointerEvents: a.styles && a.styles.pointerEvents
                                    }, f || d);
                                p = f.style;
                                e(a, {
                                    on: function () {
                                        k.on.apply({element: b[0].div}, arguments);
                                        return a
                                    }, translateXSetter: function (g, d) {
                                        p.left = g + "px";
                                        a[d] = g;
                                        a.doTransform = !0
                                    }, translateYSetter: function (g, d) {
                                        p.top = g + "px";
                                        a[d] = g;
                                        a.doTransform = !0
                                    }
                                });
                                l(a, p)
                            })
                        }
                    } else f = d;
                    f.appendChild(p);
                    k.added = !0;
                    k.alignOnAdd && k.htmlUpdateTransform();
                    return k
                });
                return k
            }
        })
    })(K);
    (function (a) {
        function w() {
            var c = a.defaultOptions.global, m, n = c.useUTC, l = n ? "getUTC" : "get", h = n ? "setUTC" : "set";
            a.Date = m = c.Date || e.Date;
            m.hcTimezoneOffset = n && c.timezoneOffset;
            m.hcGetTimezoneOffset = n && c.getTimezoneOffset;
            m.hcMakeTime = function (a, h, c, e, f, k) {
                var b;
                n ? (b = m.UTC.apply(0, arguments), b += D(b)) : b = (new m(a, h, q(c, 1), q(e, 0), q(f, 0), q(k, 0))).getTime();
                return b
            };
            B("Minutes Hours Day Date Month FullYear".split(" "), function (a) {
                m["hcGet" + a] = l + a
            });
            B("Milliseconds Seconds Minutes Hours Date Month FullYear".split(" "),
                function (a) {
                    m["hcSet" + a] = h + a
                })
        }

        var B = a.each, D = a.getTZOffset, F = a.merge, q = a.pick, e = a.win;
        a.defaultOptions = {
            symbols: ["circle", "diamond", "square", "triangle", "triangle-down"],
            lang: {
                loading: "Loading...",
                months: "January February March April May June July August September October November December".split(" "),
                shortMonths: "Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec".split(" "),
                weekdays: "Sunday Monday Tuesday Wednesday Thursday Friday Saturday".split(" "),
                decimalPoint: ".",
                numericSymbols: "kMGTPE".split(""),
                resetZoom: "Reset zoom",
                resetZoomTitle: "Reset zoom level 1:1",
                thousandsSep: " "
            },
            global: {useUTC: !0},
            chart: {
                borderRadius: 0,
                colorCount: 10,
                defaultSeriesType: "line",
                ignoreHiddenSeries: !0,
                spacing: [10, 10, 15, 10],
                resetZoomButton: {theme: {zIndex: 20}, position: {align: "right", x: -10, y: 10}},
                width: null,
                height: null
            },
            title: {text: "Chart title", align: "center", margin: 15, widthAdjust: -44},
            subtitle: {text: "", align: "center", widthAdjust: -44},
            plotOptions: {},
            labels: {style: {position: "absolute", color: "#333333"}},
            legend: {
                enabled: !0,
                align: "center",
                layout: "horizontal",
                labelFormatter: function () {
                    return this.name
                },
                borderColor: "#999999",
                borderRadius: 0,
                navigation: {},
                itemCheckboxStyle: {position: "absolute", width: "13px", height: "13px"},
                squareSymbol: !0,
                symbolPadding: 5,
                verticalAlign: "bottom",
                x: 0,
                y: 0,
                title: {}
            },
            loading: {},
            tooltip: {
                enabled: !0,
                animation: a.svg,
                borderRadius: 3,
                dateTimeLabelFormats: {
                    millisecond: "%A, %b %e, %H:%M:%S.%L",
                    second: "%A, %b %e, %H:%M:%S",
                    minute: "%A, %b %e, %H:%M",
                    hour: "%A, %b %e, %H:%M",
                    day: "%A, %b %e, %Y",
                    week: "Week from %A, %b %e, %Y",
                    month: "%B %Y",
                    year: "%Y"
                },
                footerFormat: "",
                padding: 8,
                snap: a.isTouchDevice ? 25 : 10,
                headerFormat: '\x3cspan class\x3d"highcharts-header"\x3e{point.key}\x3c/span\x3e\x3cbr/\x3e',
                pointFormat: '\x3cspan class\x3d"highcharts-color-{point.colorIndex}"\x3e\u25cf\x3c/span\x3e {series.name}: \x3cb\x3e{point.y}\x3c/b\x3e\x3cbr/\x3e'
            },
            credits: {
                enabled: !0,
                href: "http://www.highcharts.com",
                position: {align: "right", x: -10, verticalAlign: "bottom", y: -5},
                text: "Highcharts.com"
            }
        };
        a.setOptions = function (c) {
            a.defaultOptions = F(!0,
                a.defaultOptions, c);
            w();
            return a.defaultOptions
        };
        a.getOptions = function () {
            return a.defaultOptions
        };
        a.defaultPlotOptions = a.defaultOptions.plotOptions;
        w()
    })(K);
    (function (a) {
        var w = a.arrayMax, B = a.arrayMin, D = a.defined, F = a.destroyObjectProperties, q = a.each, e = a.erase, c = a.merge, m = a.pick;
        a.PlotLineOrBand = function (a, c) {
            this.axis = a;
            c && (this.options = c, this.id = c.id)
        };
        a.PlotLineOrBand.prototype = {
            render: function () {
                var a = this, e = a.axis, h = e.horiz, b = a.options, C = b.label, y = a.label, r = b.to, f = b.from, k = b.value, p = D(f) && D(r),
                    H = D(k), t = a.svgElem, E = !t, G = [], z, d = m(b.zIndex, 0), A = b.events, G = {"class": "highcharts-plot-" + (p ? "band " : "line ") + (b.className || "")}, u = {}, I = e.chart.renderer, J = p ? "bands" : "lines", g;
                g = e.log2lin;
                e.isLog && (f = g(f), r = g(r), k = g(k));
                u.zIndex = d;
                J += "-" + d;
                (g = e[J]) || (e[J] = g = I.g("plot-" + J).attr(u).add());
                E && (a.svgElem = t = I.path().attr(G).add(g));
                if (H)G = e.getPlotLinePath(k, t.strokeWidth()); else if (p)G = e.getPlotBandPath(f, r, b); else return;
                if (E && G && G.length) {
                    if (t.attr({d: G}), A)for (z in b = function (g) {
                        t.on(g, function (d) {
                            A[g].apply(a,
                                [d])
                        })
                    }, A)b(z)
                } else t && (G ? (t.show(), t.animate({d: G})) : (t.hide(), y && (a.label = y = y.destroy())));
                C && D(C.text) && G && G.length && 0 < e.width && 0 < e.height && !G.flat ? (C = c({
                    align: h && p && "center",
                    x: h ? !p && 4 : 10,
                    verticalAlign: !h && p && "middle",
                    y: h ? p ? 16 : 10 : p ? 6 : -4,
                    rotation: h && !p && 90
                }, C), this.renderLabel(C, G, p, d)) : y && y.hide();
                return a
            }, renderLabel: function (a, c, h, b) {
                var e = this.label, l = this.axis.chart.renderer;
                e || (e = {
                    align: a.textAlign || a.align,
                    rotation: a.rotation,
                    "class": "highcharts-plot-" + (h ? "band" : "line") + "-label " + (a.className ||
                    "")
                }, e.zIndex = b, this.label = e = l.text(a.text, 0, 0, a.useHTML).attr(e).add());
                b = [c[1], c[4], h ? c[6] : c[1]];
                c = [c[2], c[5], h ? c[7] : c[2]];
                h = B(b);
                l = B(c);
                e.align(a, !1, {x: h, y: l, width: w(b) - h, height: w(c) - l});
                e.show()
            }, destroy: function () {
                e(this.axis.plotLinesAndBands, this);
                delete this.axis;
                F(this)
            }
        };
        a.AxisPlotLineOrBandExtension = {
            getPlotBandPath: function (a, c) {
                c = this.getPlotLinePath(c, null, null, !0);
                (a = this.getPlotLinePath(a, null, null, !0)) && c ? (a.flat = a.toString() === c.toString(), a.push(c[4], c[5], c[1], c[2], "z")) : a = null;
                return a
            }, addPlotBand: function (a) {
                return this.addPlotBandOrLine(a, "plotBands")
            }, addPlotLine: function (a) {
                return this.addPlotBandOrLine(a, "plotLines")
            }, addPlotBandOrLine: function (c, e) {
                var h = (new a.PlotLineOrBand(this, c)).render(), b = this.userOptions;
                h && (e && (b[e] = b[e] || [], b[e].push(c)), this.plotLinesAndBands.push(h));
                return h
            }, removePlotBandOrLine: function (a) {
                for (var c = this.plotLinesAndBands, h = this.options, b = this.userOptions, m = c.length; m--;)c[m].id === a && c[m].destroy();
                q([h.plotLines || [], b.plotLines ||
                [], h.plotBands || [], b.plotBands || []], function (b) {
                    for (m = b.length; m--;)b[m].id === a && e(b, b[m])
                })
            }
        }
    })(K);
    (function (a) {
        var w = a.correctFloat, B = a.defined, D = a.destroyObjectProperties, F = a.isNumber, q = a.pick, e = a.deg2rad;
        a.Tick = function (a, e, n, l) {
            this.axis = a;
            this.pos = e;
            this.type = n || "";
            this.isNew = !0;
            n || l || this.addLabel()
        };
        a.Tick.prototype = {
            addLabel: function () {
                var a = this.axis, e = a.options, n = a.chart, l = a.categories, h = a.names, b = this.pos, C = e.labels, y = a.tickPositions, r = b === y[0], f = b === y[y.length - 1], h = l ? q(l[b], h[b], b) : b,
                    l = this.label, y = y.info, k;
                a.isDatetimeAxis && y && (k = e.dateTimeLabelFormats[y.higherRanks[b] || y.unitName]);
                this.isFirst = r;
                this.isLast = f;
                e = a.labelFormatter.call({
                    axis: a,
                    chart: n,
                    isFirst: r,
                    isLast: f,
                    dateTimeLabelFormat: k,
                    value: a.isLog ? w(a.lin2log(h)) : h
                });
                B(l) ? l && l.attr({text: e}) : (this.labelLength = (this.label = l = B(e) && C.enabled ? n.renderer.text(e, 0, 0, C.useHTML).add(a.labelGroup) : null) && l.getBBox().width, this.rotation = 0)
            }, getLabelSize: function () {
                return this.label ? this.label.getBBox()[this.axis.horiz ? "height" :
                    "width"] : 0
            }, handleOverflow: function (a) {
                var c = this.axis, n = a.x, l = c.chart.chartWidth, h = c.chart.spacing, b = q(c.labelLeft, Math.min(c.pos, h[3])), h = q(c.labelRight, Math.max(c.pos + c.len, l - h[1])), C = this.label, y = this.rotation, r = {
                    left: 0,
                    center: .5,
                    right: 1
                }[c.labelAlign], f = C.getBBox().width, k = c.getSlotWidth(), p = k, H = 1, t, E = {};
                if (y)0 > y && n - r * f < b ? t = Math.round(n / Math.cos(y * e) - b) : 0 < y && n + r * f > h && (t = Math.round((l - n) / Math.cos(y * e))); else if (l = n + (1 - r) * f, n - r * f < b ? p = a.x + p * (1 - r) - b : l > h && (p = h - a.x + p * r, H = -1), p = Math.min(k, p), p < k && "center" ===
                    c.labelAlign && (a.x += H * (k - p - r * (k - Math.min(f, p)))), f > p || c.autoRotation && (C.styles || {}).width)t = p;
                t && (E.width = t, (c.options.labels.style || {}).textOverflow || (E.textOverflow = "ellipsis"), C.css(E))
            }, getPosition: function (a, e, n, l) {
                var c = this.axis, b = c.chart, m = l && b.oldChartHeight || b.chartHeight;
                return {
                    x: a ? c.translate(e + n, null, null, l) + c.transB : c.left + c.offset + (c.opposite ? (l && b.oldChartWidth || b.chartWidth) - c.right - c.left : 0),
                    y: a ? m - c.bottom + c.offset - (c.opposite ? c.height : 0) : m - c.translate(e + n, null, null, l) - c.transB
                }
            },
            getLabelPosition: function (a, m, n, l, h, b, C, y) {
                var c = this.axis, f = c.transA, k = c.reversed, p = c.staggerLines, H = c.tickRotCorr || {
                        x: 0,
                        y: 0
                    }, t = h.y;
                B(t) || (t = 0 === c.side ? n.rotation ? -8 : -n.getBBox().height : 2 === c.side ? H.y + 8 : Math.cos(n.rotation * e) * (H.y - n.getBBox(!1, 0).height / 2));
                a = a + h.x + H.x - (b && l ? b * f * (k ? -1 : 1) : 0);
                m = m + t - (b && !l ? b * f * (k ? 1 : -1) : 0);
                p && (n = C / (y || 1) % p, c.opposite && (n = p - n - 1), m += c.labelOffset / p * n);
                return {x: a, y: Math.round(m)}
            }, getMarkPath: function (a, e, n, l, h, b) {
                return b.crispLine(["M", a, e, "L", a + (h ? 0 : -n), e + (h ? n : 0)], l)
            },
            render: function (a, e, n) {
                var c = this.axis, h = c.options, b = c.chart.renderer, m = c.horiz, y = this.type, r = this.label, f = this.pos, k = h.labels, p = this.gridLine, H = c.tickSize(y ? y + "Tick" : "tick"), t = this.mark, E = !t, G = k.step, z = {}, d = !0, A = c.tickmarkOffset, u = this.getPosition(m, f, A, e), I = u.x, u = u.y, J = m && I === c.pos + c.len || !m && u === c.pos ? -1 : 1;
                n = q(n, 1);
                this.isActive = !0;
                p || (y || (z.zIndex = 1), e && (z.opacity = 0), this.gridLine = p = b.path().attr(z).addClass("highcharts-" + (y ? y + "-" : "") + "grid-line").add(c.gridGroup));
                if (!e && p && (f = c.getPlotLinePath(f +
                        A, p.strokeWidth() * J, e, !0)))p[this.isNew ? "attr" : "animate"]({d: f, opacity: n});
                H && (c.opposite && (H[0] = -H[0]), E && (this.mark = t = b.path().addClass("highcharts-" + (y ? y + "-" : "") + "tick").add(c.axisGroup)), t[E ? "attr" : "animate"]({
                    d: this.getMarkPath(I, u, H[0], t.strokeWidth() * J, m, b),
                    opacity: n
                }));
                r && F(I) && (r.xy = u = this.getLabelPosition(I, u, r, m, k, A, a, G), this.isFirst && !this.isLast && !q(h.showFirstLabel, 1) || this.isLast && !this.isFirst && !q(h.showLastLabel, 1) ? d = !1 : !m || c.isRadial || k.step || k.rotation || e || 0 === n || this.handleOverflow(u),
                G && a % G && (d = !1), d && F(u.y) ? (u.opacity = n, r[this.isNew ? "attr" : "animate"](u)) : r.attr("y", -9999), this.isNew = !1)
            }, destroy: function () {
                D(this, this.axis)
            }
        }
    })(K);
    (function (a) {
        var w = a.addEvent, B = a.animObject, D = a.arrayMax, F = a.arrayMin, q = a.AxisPlotLineOrBandExtension, e = a.correctFloat, c = a.defaultOptions, m = a.defined, n = a.deg2rad, l = a.destroyObjectProperties, h = a.each, b = a.extend, C = a.fireEvent, y = a.format, r = a.getMagnitude, f = a.grep, k = a.inArray, p = a.isArray, H = a.isNumber, t = a.isString, E = a.merge, G = a.normalizeTickInterval, z =
            a.pick, d = a.PlotLineOrBand, A = a.removeEvent, u = a.splat, I = a.syncTimeout, J = a.Tick;
        a.Axis = function () {
            this.init.apply(this, arguments)
        };
        a.Axis.prototype = {
            defaultOptions: {
                dateTimeLabelFormats: {
                    millisecond: "%H:%M:%S.%L",
                    second: "%H:%M:%S",
                    minute: "%H:%M",
                    hour: "%H:%M",
                    day: "%e. %b",
                    week: "%e. %b",
                    month: "%b '%y",
                    year: "%Y"
                },
                endOnTick: !1,
                labels: {enabled: !0, x: 0},
                minPadding: .01,
                maxPadding: .01,
                minorTickLength: 2,
                minorTickPosition: "outside",
                startOfWeek: 1,
                startOnTick: !1,
                tickLength: 10,
                tickmarkPlacement: "between",
                tickPixelInterval: 100,
                tickPosition: "outside",
                title: {align: "middle"},
                type: "linear"
            },
            defaultYAxisOptions: {
                endOnTick: !0,
                tickPixelInterval: 72,
                showLastLabel: !0,
                labels: {x: -8},
                maxPadding: .05,
                minPadding: .05,
                startOnTick: !0,
                title: {rotation: 270, text: "Values"},
                stackLabels: {
                    enabled: !1, formatter: function () {
                        return a.numberFormat(this.total, -1)
                    }
                }
            },
            defaultLeftAxisOptions: {labels: {x: -15}, title: {rotation: 270}},
            defaultRightAxisOptions: {labels: {x: 15}, title: {rotation: 90}},
            defaultBottomAxisOptions: {labels: {autoRotation: [-45], x: 0}, title: {rotation: 0}},
            defaultTopAxisOptions: {labels: {autoRotation: [-45], x: 0}, title: {rotation: 0}},
            init: function (a, d) {
                var g = d.isX;
                this.chart = a;
                this.horiz = a.inverted ? !g : g;
                this.isXAxis = g;
                this.coll = this.coll || (g ? "xAxis" : "yAxis");
                this.opposite = d.opposite;
                this.side = d.side || (this.horiz ? this.opposite ? 0 : 2 : this.opposite ? 1 : 3);
                this.setOptions(d);
                var f = this.options, v = f.type;
                this.labelFormatter = f.labels.formatter || this.defaultLabelFormatter;
                this.userOptions = d;
                this.minPixelPadding = 0;
                this.reversed = f.reversed;
                this.visible = !1 !== f.visible;
                this.zoomEnabled = !1 !== f.zoomEnabled;
                this.hasNames = "category" === v || !0 === f.categories;
                this.categories = f.categories || this.hasNames;
                this.names = this.names || [];
                this.isLog = "logarithmic" === v;
                this.isDatetimeAxis = "datetime" === v;
                this.isLinked = m(f.linkedTo);
                this.ticks = {};
                this.labelEdge = [];
                this.minorTicks = {};
                this.plotLinesAndBands = [];
                this.alternateBands = {};
                this.len = 0;
                this.minRange = this.userMinRange = f.minRange || f.maxZoom;
                this.range = f.range;
                this.offset = f.offset || 0;
                this.stacks = {};
                this.oldStacks = {};
                this.stacksTouched =
                    0;
                this.min = this.max = null;
                this.crosshair = z(f.crosshair, u(a.options.tooltip.crosshairs)[g ? 0 : 1], !1);
                var b;
                d = this.options.events;
                -1 === k(this, a.axes) && (g ? a.axes.splice(a.xAxis.length, 0, this) : a.axes.push(this), a[this.coll].push(this));
                this.series = this.series || [];
                a.inverted && g && void 0 === this.reversed && (this.reversed = !0);
                this.removePlotLine = this.removePlotBand = this.removePlotBandOrLine;
                for (b in d)w(this, b, d[b]);
                this.isLog && (this.val2lin = this.log2lin, this.lin2val = this.lin2log)
            },
            setOptions: function (a) {
                this.options =
                    E(this.defaultOptions, "yAxis" === this.coll && this.defaultYAxisOptions, [this.defaultTopAxisOptions, this.defaultRightAxisOptions, this.defaultBottomAxisOptions, this.defaultLeftAxisOptions][this.side], E(c[this.coll], a))
            },
            defaultLabelFormatter: function () {
                var g = this.axis, d = this.value, f = g.categories, b = this.dateTimeLabelFormat, k = c.lang, p = k.numericSymbols, k = k.numericSymbolMagnitude || 1E3, e = p && p.length, h, t = g.options.labels.format, g = g.isLog ? d : g.tickInterval;
                if (t)h = y(t, this); else if (f)h = d; else if (b)h = a.dateFormat(b,
                    d); else if (e && 1E3 <= g)for (; e-- && void 0 === h;)f = Math.pow(k, e + 1), g >= f && 0 === 10 * d % f && null !== p[e] && 0 !== d && (h = a.numberFormat(d / f, -1) + p[e]);
                void 0 === h && (h = 1E4 <= Math.abs(d) ? a.numberFormat(d, -1) : a.numberFormat(d, -1, void 0, ""));
                return h
            },
            getSeriesExtremes: function () {
                var a = this, d = a.chart;
                a.hasVisibleSeries = !1;
                a.dataMin = a.dataMax = a.threshold = null;
                a.softThreshold = !a.isXAxis;
                a.buildStacks && a.buildStacks();
                h(a.series, function (g) {
                    if (g.visible || !d.options.chart.ignoreHiddenSeries) {
                        var b = g.options, v = b.threshold, k;
                        a.hasVisibleSeries = !0;
                        a.isLog && 0 >= v && (v = null);
                        if (a.isXAxis)b = g.xData, b.length && (g = F(b), H(g) || g instanceof Date || (b = f(b, function (a) {
                            return H(a)
                        }), g = F(b)), a.dataMin = Math.min(z(a.dataMin, b[0]), g), a.dataMax = Math.max(z(a.dataMax, b[0]), D(b))); else if (g.getExtremes(), k = g.dataMax, g = g.dataMin, m(g) && m(k) && (a.dataMin = Math.min(z(a.dataMin, g), g), a.dataMax = Math.max(z(a.dataMax, k), k)), m(v) && (a.threshold = v), !b.softThreshold || a.isLog)a.softThreshold = !1
                    }
                })
            },
            translate: function (a, d, f, b, k, c) {
                var g = this.linkedParent || this, v = 1, p = 0, e = b ? g.oldTransA :
                    g.transA;
                b = b ? g.oldMin : g.min;
                var h = g.minPixelPadding;
                k = (g.isOrdinal || g.isBroken || g.isLog && k) && g.lin2val;
                e || (e = g.transA);
                f && (v *= -1, p = g.len);
                g.reversed && (v *= -1, p -= v * (g.sector || g.len));
                d ? (a = (a * v + p - h) / e + b, k && (a = g.lin2val(a))) : (k && (a = g.val2lin(a)), a = v * (a - b) * e + p + v * h + (H(c) ? e * c : 0));
                return a
            },
            toPixels: function (a, d) {
                return this.translate(a, !1, !this.horiz, null, !0) + (d ? 0 : this.pos)
            },
            toValue: function (a, d) {
                return this.translate(a - (d ? 0 : this.pos), !0, !this.horiz, null, !0)
            },
            getPlotLinePath: function (a, d, f, b, k) {
                var g = this.chart,
                    v = this.left, c = this.top, p, e, h = f && g.oldChartHeight || g.chartHeight, t = f && g.oldChartWidth || g.chartWidth, u;
                p = this.transB;
                var r = function (a, g, d) {
                    if (a < g || a > d)b ? a = Math.min(Math.max(g, a), d) : u = !0;
                    return a
                };
                k = z(k, this.translate(a, null, null, f));
                a = f = Math.round(k + p);
                p = e = Math.round(h - k - p);
                H(k) ? this.horiz ? (p = c, e = h - this.bottom, a = f = r(a, v, v + this.width)) : (a = v, f = t - this.right, p = e = r(p, c, c + this.height)) : u = !0;
                return u && !b ? null : g.renderer.crispLine(["M", a, p, "L", f, e], d || 1)
            },
            getLinearTickPositions: function (a, d, f) {
                var g, b = e(Math.floor(d /
                        a) * a), v = e(Math.ceil(f / a) * a), k = [];
                if (d === f && H(d))return [d];
                for (d = b; d <= v;) {
                    k.push(d);
                    d = e(d + a);
                    if (d === g)break;
                    g = d
                }
                return k
            },
            getMinorTickPositions: function () {
                var a = this.options, d = this.tickPositions, f = this.minorTickInterval, b = [], k, c = this.pointRangePadding || 0;
                k = this.min - c;
                var c = this.max + c, p = c - k;
                if (p && p / f < this.len / 3)if (this.isLog)for (c = d.length, k = 1; k < c; k++)b = b.concat(this.getLogTickPositions(f, d[k - 1], d[k], !0)); else if (this.isDatetimeAxis && "auto" === a.minorTickInterval)b = b.concat(this.getTimeTicks(this.normalizeTimeTickInterval(f),
                    k, c, a.startOfWeek)); else for (d = k + (d[0] - k) % f; d <= c && d !== b[0]; d += f)b.push(d);
                0 !== b.length && this.trimTicks(b, a.startOnTick, a.endOnTick);
                return b
            },
            adjustForMinRange: function () {
                var a = this.options, d = this.min, f = this.max, b, k = this.dataMax - this.dataMin >= this.minRange, c, p, e, t, u, r;
                this.isXAxis && void 0 === this.minRange && !this.isLog && (m(a.min) || m(a.max) ? this.minRange = null : (h(this.series, function (a) {
                    t = a.xData;
                    for (p = u = a.xIncrement ? 1 : t.length - 1; 0 < p; p--)if (e = t[p] - t[p - 1], void 0 === c || e < c)c = e
                }), this.minRange = Math.min(5 *
                    c, this.dataMax - this.dataMin)));
                f - d < this.minRange && (r = this.minRange, b = (r - f + d) / 2, b = [d - b, z(a.min, d - b)], k && (b[2] = this.isLog ? this.log2lin(this.dataMin) : this.dataMin), d = D(b), f = [d + r, z(a.max, d + r)], k && (f[2] = this.isLog ? this.log2lin(this.dataMax) : this.dataMax), f = F(f), f - d < r && (b[0] = f - r, b[1] = z(a.min, f - r), d = D(b)));
                this.min = d;
                this.max = f
            },
            getClosest: function () {
                var a;
                this.categories ? a = 1 : h(this.series, function (g) {
                    var d = g.closestPointRange, f = g.visible || !g.chart.options.chart.ignoreHiddenSeries;
                    !g.noSharedTooltip && m(d) &&
                    f && (a = m(a) ? Math.min(a, d) : d)
                });
                return a
            },
            nameToX: function (a) {
                var g = p(this.categories), d = g ? this.categories : this.names, f = a.options.x, b;
                a.series.requireSorting = !1;
                m(f) || (f = !1 === this.options.uniqueNames ? a.series.autoIncrement() : k(a.name, d));
                -1 === f ? g || (b = d.length) : b = f;
                this.names[b] = a.name;
                return b
            },
            updateNames: function () {
                var a = this;
                0 < this.names.length && (this.names.length = 0, this.minRange = void 0, h(this.series || [], function (g) {
                    g.xIncrement = null;
                    if (!g.points || g.isDirtyData)g.processData(), g.generatePoints();
                    h(g.points, function (d, f) {
                        var b;
                        d.options && void 0 === d.options.x && (b = a.nameToX(d), b !== d.x && (d.x = b, g.xData[f] = b))
                    })
                }))
            },
            setAxisTranslation: function (a) {
                var g = this, d = g.max - g.min, f = g.axisPointRange || 0, b, k = 0, c = 0, p = g.linkedParent, e = !!g.categories, u = g.transA, r = g.isXAxis;
                if (r || e || f)b = g.getClosest(), p ? (k = p.minPointOffset, c = p.pointRangePadding) : h(g.series, function (a) {
                    var d = e ? 1 : r ? z(a.options.pointRange, b, 0) : g.axisPointRange || 0;
                    a = a.options.pointPlacement;
                    f = Math.max(f, d);
                    g.single || (k = Math.max(k, t(a) ? 0 : d / 2), c = Math.max(c,
                        "on" === a ? 0 : d))
                }), p = g.ordinalSlope && b ? g.ordinalSlope / b : 1, g.minPointOffset = k *= p, g.pointRangePadding = c *= p, g.pointRange = Math.min(f, d), r && (g.closestPointRange = b);
                a && (g.oldTransA = u);
                g.translationSlope = g.transA = u = g.len / (d + c || 1);
                g.transB = g.horiz ? g.left : g.bottom;
                g.minPixelPadding = u * k
            },
            minFromRange: function () {
                return this.max - this.range
            },
            setTickInterval: function (g) {
                var d = this, f = d.chart, b = d.options, k = d.isLog, c = d.log2lin, p = d.isDatetimeAxis, t = d.isXAxis, u = d.isLinked, A = b.maxPadding, l = b.minPadding, E = b.tickInterval,
                    n = b.tickPixelInterval, y = d.categories, J = d.threshold, I = d.softThreshold, q, w, B, D;
                p || y || u || this.getTickAmount();
                B = z(d.userMin, b.min);
                D = z(d.userMax, b.max);
                u ? (d.linkedParent = f[d.coll][b.linkedTo], f = d.linkedParent.getExtremes(), d.min = z(f.min, f.dataMin), d.max = z(f.max, f.dataMax), b.type !== d.linkedParent.options.type && a.error(11, 1)) : (!I && m(J) && (d.dataMin >= J ? (q = J, l = 0) : d.dataMax <= J && (w = J, A = 0)), d.min = z(B, q, d.dataMin), d.max = z(D, w, d.dataMax));
                k && (!g && 0 >= Math.min(d.min, z(d.dataMin, d.min)) && a.error(10, 1), d.min = e(c(d.min),
                    15), d.max = e(c(d.max), 15));
                d.range && m(d.max) && (d.userMin = d.min = B = Math.max(d.min, d.minFromRange()), d.userMax = D = d.max, d.range = null);
                C(d, "foundExtremes");
                d.beforePadding && d.beforePadding();
                d.adjustForMinRange();
                !(y || d.axisPointRange || d.usePercentage || u) && m(d.min) && m(d.max) && (c = d.max - d.min) && (!m(B) && l && (d.min -= c * l), !m(D) && A && (d.max += c * A));
                H(b.floor) ? d.min = Math.max(d.min, b.floor) : H(b.softMin) && (d.min = Math.min(d.min, b.softMin));
                H(b.ceiling) ? d.max = Math.min(d.max, b.ceiling) : H(b.softMax) && (d.max = Math.max(d.max,
                    b.softMax));
                I && m(d.dataMin) && (J = J || 0, !m(B) && d.min < J && d.dataMin >= J ? d.min = J : !m(D) && d.max > J && d.dataMax <= J && (d.max = J));
                d.tickInterval = d.min === d.max || void 0 === d.min || void 0 === d.max ? 1 : u && !E && n === d.linkedParent.options.tickPixelInterval ? E = d.linkedParent.tickInterval : z(E, this.tickAmount ? (d.max - d.min) / Math.max(this.tickAmount - 1, 1) : void 0, y ? 1 : (d.max - d.min) * n / Math.max(d.len, n));
                t && !g && h(d.series, function (a) {
                    a.processData(d.min !== d.oldMin || d.max !== d.oldMax)
                });
                d.setAxisTranslation(!0);
                d.beforeSetTickPositions &&
                d.beforeSetTickPositions();
                d.postProcessTickInterval && (d.tickInterval = d.postProcessTickInterval(d.tickInterval));
                d.pointRange && !E && (d.tickInterval = Math.max(d.pointRange, d.tickInterval));
                g = z(b.minTickInterval, d.isDatetimeAxis && d.closestPointRange);
                !E && d.tickInterval < g && (d.tickInterval = g);
                p || k || E || (d.tickInterval = G(d.tickInterval, null, r(d.tickInterval), z(b.allowDecimals, !(.5 < d.tickInterval && 5 > d.tickInterval && 1E3 < d.max && 9999 > d.max)), !!this.tickAmount));
                this.tickAmount || (d.tickInterval = d.unsquish());
                this.setTickPositions()
            },
            setTickPositions: function () {
                var a = this.options, d, f = a.tickPositions, b = a.tickPositioner, k = a.startOnTick, c = a.endOnTick, p;
                this.tickmarkOffset = this.categories && "between" === a.tickmarkPlacement && 1 === this.tickInterval ? .5 : 0;
                this.minorTickInterval = "auto" === a.minorTickInterval && this.tickInterval ? this.tickInterval / 5 : a.minorTickInterval;
                this.tickPositions = d = f && f.slice();
                !d && (d = this.isDatetimeAxis ? this.getTimeTicks(this.normalizeTimeTickInterval(this.tickInterval, a.units), this.min, this.max,
                    a.startOfWeek, this.ordinalPositions, this.closestPointRange, !0) : this.isLog ? this.getLogTickPositions(this.tickInterval, this.min, this.max) : this.getLinearTickPositions(this.tickInterval, this.min, this.max), d.length > this.len && (d = [d[0], d.pop()]), this.tickPositions = d, b && (b = b.apply(this, [this.min, this.max]))) && (this.tickPositions = d = b);
                this.isLinked || (this.trimTicks(d, k, c), this.min === this.max && m(this.min) && !this.tickAmount && (p = !0, this.min -= .5, this.max += .5), this.single = p, f || b || this.adjustTickAmount())
            },
            trimTicks: function (a,
                                 d, f) {
                var g = a[0], b = a[a.length - 1], k = this.minPointOffset || 0;
                if (d)this.min = g; else for (; this.min - k > a[0];)a.shift();
                if (f)this.max = b; else for (; this.max + k < a[a.length - 1];)a.pop();
                0 === a.length && m(g) && a.push((b + g) / 2)
            },
            alignToOthers: function () {
                var a = {}, d, f = this.options;
                !1 === this.chart.options.chart.alignTicks || !1 === f.alignTicks || this.isLog || h(this.chart[this.coll], function (g) {
                    var f = g.options, f = [g.horiz ? f.left : f.top, f.width, f.height, f.pane].join();
                    g.series.length && (a[f] ? d = !0 : a[f] = 1)
                });
                return d
            },
            getTickAmount: function () {
                var a =
                    this.options, d = a.tickAmount, f = a.tickPixelInterval;
                !m(a.tickInterval) && this.len < f && !this.isRadial && !this.isLog && a.startOnTick && a.endOnTick && (d = 2);
                !d && this.alignToOthers() && (d = Math.ceil(this.len / f) + 1);
                4 > d && (this.finalTickAmt = d, d = 5);
                this.tickAmount = d
            },
            adjustTickAmount: function () {
                var a = this.tickInterval, d = this.tickPositions, f = this.tickAmount, b = this.finalTickAmt, k = d && d.length;
                if (k < f) {
                    for (; d.length < f;)d.push(e(d[d.length - 1] + a));
                    this.transA *= (k - 1) / (f - 1);
                    this.max = d[d.length - 1]
                } else k > f && (this.tickInterval *=
                    2, this.setTickPositions());
                if (m(b)) {
                    for (a = f = d.length; a--;)(3 === b && 1 === a % 2 || 2 >= b && 0 < a && a < f - 1) && d.splice(a, 1);
                    this.finalTickAmt = void 0
                }
            },
            setScale: function () {
                var a, d;
                this.oldMin = this.min;
                this.oldMax = this.max;
                this.oldAxisLength = this.len;
                this.setAxisSize();
                d = this.len !== this.oldAxisLength;
                h(this.series, function (d) {
                    if (d.isDirtyData || d.isDirty || d.xAxis.isDirty)a = !0
                });
                d || a || this.isLinked || this.forceRedraw || this.userMin !== this.oldUserMin || this.userMax !== this.oldUserMax || this.alignToOthers() ? (this.resetStacks &&
                this.resetStacks(), this.forceRedraw = !1, this.getSeriesExtremes(), this.setTickInterval(), this.oldUserMin = this.userMin, this.oldUserMax = this.userMax, this.isDirty || (this.isDirty = d || this.min !== this.oldMin || this.max !== this.oldMax)) : this.cleanStacks && this.cleanStacks()
            },
            setExtremes: function (a, d, f, k, c) {
                var g = this, p = g.chart;
                f = z(f, !0);
                h(g.series, function (a) {
                    delete a.kdTree
                });
                c = b(c, {min: a, max: d});
                C(g, "setExtremes", c, function () {
                    g.userMin = a;
                    g.userMax = d;
                    g.eventArgs = c;
                    f && p.redraw(k)
                })
            },
            zoom: function (a, d) {
                var g = this.dataMin,
                    f = this.dataMax, b = this.options, k = Math.min(g, z(b.min, g)), b = Math.max(f, z(b.max, f));
                if (a !== this.min || d !== this.max)this.allowZoomOutside || (m(g) && (a < k && (a = k), a > b && (a = b)), m(f) && (d < k && (d = k), d > b && (d = b))), this.displayBtn = void 0 !== a || void 0 !== d, this.setExtremes(a, d, !1, void 0, {trigger: "zoom"});
                return !0
            },
            setAxisSize: function () {
                var a = this.chart, d = this.options, f = d.offsetLeft || 0, b = this.horiz, k = z(d.width, a.plotWidth - f + (d.offsetRight || 0)), c = z(d.height, a.plotHeight), p = z(d.top, a.plotTop), d = z(d.left, a.plotLeft + f), f = /%$/;
                f.test(c) && (c = Math.round(parseFloat(c) / 100 * a.plotHeight));
                f.test(p) && (p = Math.round(parseFloat(p) / 100 * a.plotHeight + a.plotTop));
                this.left = d;
                this.top = p;
                this.width = k;
                this.height = c;
                this.bottom = a.chartHeight - c - p;
                this.right = a.chartWidth - k - d;
                this.len = Math.max(b ? k : c, 0);
                this.pos = b ? d : p
            },
            getExtremes: function () {
                var a = this.isLog, d = this.lin2log;
                return {
                    min: a ? e(d(this.min)) : this.min,
                    max: a ? e(d(this.max)) : this.max,
                    dataMin: this.dataMin,
                    dataMax: this.dataMax,
                    userMin: this.userMin,
                    userMax: this.userMax
                }
            },
            getThreshold: function (a) {
                var d =
                    this.isLog, f = this.lin2log, g = d ? f(this.min) : this.min, d = d ? f(this.max) : this.max;
                null === a ? a = g : g > a ? a = g : d < a && (a = d);
                return this.translate(a, 0, 1, 0, 1)
            },
            autoLabelAlign: function (a) {
                a = (z(a, 0) - 90 * this.side + 720) % 360;
                return 15 < a && 165 > a ? "right" : 195 < a && 345 > a ? "left" : "center"
            },
            tickSize: function (a) {
                var d = this.options, f = d[a + "Length"], g = z(d[a + "Width"], "tick" === a && this.isXAxis ? 1 : 0);
                if (g && f)return "inside" === d[a + "Position"] && (f = -f), [f, g]
            },
            labelMetrics: function () {
                return this.chart.renderer.fontMetrics(this.options.labels.style &&
                    this.options.labels.style.fontSize, this.ticks[0] && this.ticks[0].label)
            },
            unsquish: function () {
                var a = this.options.labels, d = this.horiz, f = this.tickInterval, b = f, k = this.len / (((this.categories ? 1 : 0) + this.max - this.min) / f), c, p = a.rotation, e = this.labelMetrics(), t, u = Number.MAX_VALUE, r, A = function (a) {
                    a /= k || 1;
                    a = 1 < a ? Math.ceil(a) : 1;
                    return a * f
                };
                d ? (r = !a.staggerLines && !a.step && (m(p) ? [p] : k < z(a.autoRotationLimit, 80) && a.autoRotation)) && h(r, function (a) {
                    var d;
                    if (a === p || a && -90 <= a && 90 >= a)t = A(Math.abs(e.h / Math.sin(n * a))), d = t +
                        Math.abs(a / 360), d < u && (u = d, c = a, b = t)
                }) : a.step || (b = A(e.h));
                this.autoRotation = r;
                this.labelRotation = z(c, p);
                return b
            },
            getSlotWidth: function () {
                var a = this.chart, d = this.horiz, f = this.options.labels, b = Math.max(this.tickPositions.length - (this.categories ? 0 : 1), 1), k = a.margin[3];
                return d && 2 > (f.step || 0) && !f.rotation && (this.staggerLines || 1) * a.plotWidth / b || !d && (k && k - a.spacing[3] || .33 * a.chartWidth)
            },
            renderUnsquish: function () {
                var a = this.chart, d = a.renderer, f = this.tickPositions, b = this.ticks, k = this.options.labels, c = this.horiz,
                    p = this.getSlotWidth(), e = Math.max(1, Math.round(p - 2 * (k.padding || 5))), u = {}, r = this.labelMetrics(), A = k.style && k.style.textOverflow, m, l = 0, z, n;
                t(k.rotation) || (u.rotation = k.rotation || 0);
                h(f, function (a) {
                    (a = b[a]) && a.labelLength > l && (l = a.labelLength)
                });
                this.maxLabelLength = l;
                if (this.autoRotation)l > e && l > r.h ? u.rotation = this.labelRotation : this.labelRotation = 0; else if (p && (m = {width: e + "px"}, !A))for (m.textOverflow = "clip", z = f.length; !c && z--;)if (n = f[z], e = b[n].label)e.styles && "ellipsis" === e.styles.textOverflow ? e.css({textOverflow: "clip"}) :
                b[n].labelLength > p && e.css({width: p + "px"}), e.getBBox().height > this.len / f.length - (r.h - r.f) && (e.specCss = {textOverflow: "ellipsis"});
                u.rotation && (m = {width: (l > .5 * a.chartHeight ? .33 * a.chartHeight : a.chartHeight) + "px"}, A || (m.textOverflow = "ellipsis"));
                if (this.labelAlign = k.align || this.autoLabelAlign(this.labelRotation))u.align = this.labelAlign;
                h(f, function (a) {
                    var d = (a = b[a]) && a.label;
                    d && (d.attr(u), m && d.css(E(m, d.specCss)), delete d.specCss, a.rotation = u.rotation)
                });
                this.tickRotCorr = d.rotCorr(r.b, this.labelRotation ||
                    0, 0 !== this.side)
            },
            hasData: function () {
                return this.hasVisibleSeries || m(this.min) && m(this.max) && !!this.tickPositions
            },
            addTitle: function (a) {
                var d = this.chart.renderer, f = this.horiz, g = this.opposite, b = this.options.title, k;
                this.axisTitle || ((k = b.textAlign) || (k = (f ? {
                    low: "left",
                    middle: "center",
                    high: "right"
                } : {
                    low: g ? "right" : "left",
                    middle: "center",
                    high: g ? "left" : "right"
                })[b.align]), this.axisTitle = d.text(b.text, 0, 0, b.useHTML).attr({
                    zIndex: 7,
                    rotation: b.rotation || 0,
                    align: k
                }).addClass("highcharts-axis-title").add(this.axisGroup),
                    this.axisTitle.isNew = !0);
                this.axisTitle[a ? "show" : "hide"](!0)
            },
            getOffset: function () {
                var a = this, d = a.chart, f = d.renderer, b = a.options, k = a.tickPositions, c = a.ticks, p = a.horiz, e = a.side, u = d.inverted ? [1, 0, 3, 2][e] : e, t, r, A = 0, l, E = 0, n = b.title, y = b.labels, G = 0, C = d.axisOffset, d = d.clipOffset, I = [-1, 1, 1, -1][e], H, q = b.className, B = a.axisParent, w = this.tickSize("tick");
                t = a.hasData();
                a.showAxis = r = t || z(b.showEmpty, !0);
                a.staggerLines = a.horiz && y.staggerLines;
                a.axisGroup || (a.gridGroup = f.g("grid").attr({zIndex: b.gridZIndex || 1}).addClass("highcharts-" +
                    this.coll.toLowerCase() + "-grid " + (q || "")).add(B), a.axisGroup = f.g("axis").attr({zIndex: b.zIndex || 2}).addClass("highcharts-" + this.coll.toLowerCase() + " " + (q || "")).add(B), a.labelGroup = f.g("axis-labels").attr({zIndex: y.zIndex || 7}).addClass("highcharts-" + a.coll.toLowerCase() + "-labels " + (q || "")).add(B));
                if (t || a.isLinked)h(k, function (d) {
                    c[d] ? c[d].addLabel() : c[d] = new J(a, d)
                }), a.renderUnsquish(), !1 === y.reserveSpace || 0 !== e && 2 !== e && {
                    1: "left",
                    3: "right"
                }[e] !== a.labelAlign && "center" !== a.labelAlign || h(k, function (a) {
                    G =
                        Math.max(c[a].getLabelSize(), G)
                }), a.staggerLines && (G *= a.staggerLines, a.labelOffset = G * (a.opposite ? -1 : 1)); else for (H in c)c[H].destroy(), delete c[H];
                n && n.text && !1 !== n.enabled && (a.addTitle(r), r && (A = a.axisTitle.getBBox()[p ? "height" : "width"], l = n.offset, E = m(l) ? 0 : z(n.margin, p ? 5 : 10)));
                a.renderLine();
                a.offset = I * z(b.offset, C[e]);
                a.tickRotCorr = a.tickRotCorr || {x: 0, y: 0};
                f = 0 === e ? -a.labelMetrics().h : 2 === e ? a.tickRotCorr.y : 0;
                E = Math.abs(G) + E;
                G && (E = E - f + I * (p ? z(y.y, a.tickRotCorr.y + 8 * I) : y.x));
                a.axisTitleMargin = z(l, E);
                C[e] = Math.max(C[e], a.axisTitleMargin + A + I * a.offset, E, t && k.length && w ? w[0] : 0);
                b = b.offset ? 0 : 2 * Math.floor(a.axisLine.strokeWidth() / 2);
                d[u] = Math.max(d[u], b)
            },
            getLinePath: function (a) {
                var d = this.chart, f = this.opposite, b = this.offset, g = this.horiz, k = this.left + (f ? this.width : 0) + b, b = d.chartHeight - this.bottom - (f ? this.height : 0) + b;
                f && (a *= -1);
                return d.renderer.crispLine(["M", g ? this.left : k, g ? b : this.top, "L", g ? d.chartWidth - this.right : k, g ? b : d.chartHeight - this.bottom], a)
            },
            renderLine: function () {
                this.axisLine || (this.axisLine =
                    this.chart.renderer.path().addClass("highcharts-axis-line").add(this.axisGroup))
            },
            getTitlePosition: function () {
                var a = this.horiz, d = this.left, f = this.top, b = this.len, k = this.options.title, c = a ? d : f, p = this.opposite, e = this.offset, h = k.x || 0, t = k.y || 0, u = this.chart.renderer.fontMetrics(k.style && k.style.fontSize, this.axisTitle).f, b = {
                    low: c + (a ? 0 : b),
                    middle: c + b / 2,
                    high: c + (a ? b : 0)
                }[k.align], d = (a ? f + this.height : d) + (a ? 1 : -1) * (p ? -1 : 1) * this.axisTitleMargin + (2 === this.side ? u : 0);
                return {
                    x: a ? b + h : d + (p ? this.width : 0) + e + h, y: a ? d + t - (p ?
                        this.height : 0) + e : b + t
                }
            },
            render: function () {
                var a = this, f = a.chart, b = f.renderer, k = a.options, c = a.isLog, p = a.lin2log, e = a.isLinked, t = a.tickPositions, u = a.axisTitle, r = a.ticks, A = a.minorTicks, l = a.alternateBands, m = k.stackLabels, E = k.alternateGridColor, n = a.tickmarkOffset, z = a.axisLine, y = f.hasRendered && H(a.oldMin), G = a.showAxis, C = B(b.globalAnimation), q, w;
                a.labelEdge.length = 0;
                a.overlap = !1;
                h([r, A, l], function (a) {
                    for (var d in a)a[d].isActive = !1
                });
                if (a.hasData() || e)a.minorTickInterval && !a.categories && h(a.getMinorTickPositions(),
                    function (d) {
                        A[d] || (A[d] = new J(a, d, "minor"));
                        y && A[d].isNew && A[d].render(null, !0);
                        A[d].render(null, !1, 1)
                    }), t.length && (h(t, function (d, f) {
                    if (!e || d >= a.min && d <= a.max)r[d] || (r[d] = new J(a, d)), y && r[d].isNew && r[d].render(f, !0, .1), r[d].render(f)
                }), n && (0 === a.min || a.single) && (r[-1] || (r[-1] = new J(a, -1, null, !0)), r[-1].render(-1))), E && h(t, function (b, g) {
                    w = void 0 !== t[g + 1] ? t[g + 1] + n : a.max - n;
                    0 === g % 2 && b < a.max && w <= a.max + (f.polar ? -n : n) && (l[b] || (l[b] = new d(a)), q = b + n, l[b].options = {
                        from: c ? p(q) : q,
                        to: c ? p(w) : w,
                        color: E
                    }, l[b].render(),
                        l[b].isActive = !0)
                }), a._addedPlotLB || (h((k.plotLines || []).concat(k.plotBands || []), function (d) {
                    a.addPlotBandOrLine(d)
                }), a._addedPlotLB = !0);
                h([r, A, l], function (a) {
                    var d, b, g = [], k = C.duration;
                    for (d in a)a[d].isActive || (a[d].render(d, !1, 0), a[d].isActive = !1, g.push(d));
                    I(function () {
                        for (b = g.length; b--;)a[g[b]] && !a[g[b]].isActive && (a[g[b]].destroy(), delete a[g[b]])
                    }, a !== l && f.hasRendered && k ? k : 0)
                });
                z && (z[z.isPlaced ? "animate" : "attr"]({d: this.getLinePath(z.strokeWidth())}), z.isPlaced = !0, z[G ? "show" : "hide"](!0));
                u &&
                G && (u[u.isNew ? "attr" : "animate"](a.getTitlePosition()), u.isNew = !1);
                m && m.enabled && a.renderStackTotals();
                a.isDirty = !1
            },
            redraw: function () {
                this.visible && (this.render(), h(this.plotLinesAndBands, function (a) {
                    a.render()
                }));
                h(this.series, function (a) {
                    a.isDirty = !0
                })
            },
            keepProps: "extKey hcEvents names series userMax userMin".split(" "),
            destroy: function (a) {
                var d = this, f = d.stacks, b, g = d.plotLinesAndBands, c;
                a || A(d);
                for (b in f)l(f[b]), f[b] = null;
                h([d.ticks, d.minorTicks, d.alternateBands], function (a) {
                    l(a)
                });
                if (g)for (a =
                               g.length; a--;)g[a].destroy();
                h("stackTotalGroup axisLine axisTitle axisGroup gridGroup labelGroup cross".split(" "), function (a) {
                    d[a] && (d[a] = d[a].destroy())
                });
                for (c in d)d.hasOwnProperty(c) && -1 === k(c, d.keepProps) && delete d[c]
            },
            drawCrosshair: function (a, d) {
                var f, b = this.crosshair, g = z(b.snap, !0), k, c = this.cross;
                a || (a = this.cross && this.cross.e);
                this.crosshair && !1 !== (m(d) || !g) ? (g ? m(d) && (k = this.isXAxis ? d.plotX : this.len - d.plotY) : k = a && (this.horiz ? a.chartX - this.pos : this.len - a.chartY + this.pos), m(k) && (f = this.getPlotLinePath(d &&
                        (this.isXAxis ? d.x : z(d.stackY, d.y)), null, null, null, k) || null), m(f) ? (d = this.categories && !this.isRadial, c || (this.cross = c = this.chart.renderer.path().addClass("highcharts-crosshair highcharts-crosshair-" + (d ? "category " : "thin ") + b.className).attr({zIndex: z(b.zIndex, 2)}).add()), c.show().attr({d: f}), d && !b.width && c.attr({"stroke-width": this.transA}), this.cross.e = a) : this.hideCrosshair()) : this.hideCrosshair()
            },
            hideCrosshair: function () {
                this.cross && this.cross.hide()
            }
        };
        b(a.Axis.prototype, q)
    })(K);
    (function (a) {
        var w =
            a.Axis, B = a.Date, D = a.dateFormat, F = a.defaultOptions, q = a.defined, e = a.each, c = a.extend, m = a.getMagnitude, n = a.getTZOffset, l = a.normalizeTickInterval, h = a.pick, b = a.timeUnits;
        w.prototype.getTimeTicks = function (a, l, r, f) {
            var k = [], p = {}, m = F.global.useUTC, t, E = new B(l - n(l)), G = B.hcMakeTime, z = a.unitRange, d = a.count, A;
            if (q(l)) {
                E[B.hcSetMilliseconds](z >= b.second ? 0 : d * Math.floor(E.getMilliseconds() / d));
                if (z >= b.second)E[B.hcSetSeconds](z >= b.minute ? 0 : d * Math.floor(E.getSeconds() / d));
                if (z >= b.minute)E[B.hcSetMinutes](z >= b.hour ?
                    0 : d * Math.floor(E[B.hcGetMinutes]() / d));
                if (z >= b.hour)E[B.hcSetHours](z >= b.day ? 0 : d * Math.floor(E[B.hcGetHours]() / d));
                if (z >= b.day)E[B.hcSetDate](z >= b.month ? 1 : d * Math.floor(E[B.hcGetDate]() / d));
                z >= b.month && (E[B.hcSetMonth](z >= b.year ? 0 : d * Math.floor(E[B.hcGetMonth]() / d)), t = E[B.hcGetFullYear]());
                if (z >= b.year)E[B.hcSetFullYear](t - t % d);
                if (z === b.week)E[B.hcSetDate](E[B.hcGetDate]() - E[B.hcGetDay]() + h(f, 1));
                t = E[B.hcGetFullYear]();
                f = E[B.hcGetMonth]();
                var u = E[B.hcGetDate](), y = E[B.hcGetHours]();
                if (B.hcTimezoneOffset ||
                    B.hcGetTimezoneOffset)A = (!m || !!B.hcGetTimezoneOffset) && (r - l > 4 * b.month || n(l) !== n(r)), E = E.getTime(), E = new B(E + n(E));
                m = E.getTime();
                for (l = 1; m < r;)k.push(m), m = z === b.year ? G(t + l * d, 0) : z === b.month ? G(t, f + l * d) : !A || z !== b.day && z !== b.week ? A && z === b.hour ? G(t, f, u, y + l * d) : m + z * d : G(t, f, u + l * d * (z === b.day ? 1 : 7)), l++;
                k.push(m);
                z <= b.hour && e(k, function (a) {
                    "000000000" === D("%H%M%S%L", a) && (p[a] = "day")
                })
            }
            k.info = c(a, {higherRanks: p, totalRange: z * d});
            return k
        };
        w.prototype.normalizeTimeTickInterval = function (a, c) {
            var e = c || [["millisecond",
                    [1, 2, 5, 10, 20, 25, 50, 100, 200, 500]], ["second", [1, 2, 5, 10, 15, 30]], ["minute", [1, 2, 5, 10, 15, 30]], ["hour", [1, 2, 3, 4, 6, 8, 12]], ["day", [1, 2]], ["week", [1, 2]], ["month", [1, 2, 3, 4, 6]], ["year", null]];
            c = e[e.length - 1];
            var f = b[c[0]], k = c[1], p;
            for (p = 0; p < e.length && !(c = e[p], f = b[c[0]], k = c[1], e[p + 1] && a <= (f * k[k.length - 1] + b[e[p + 1][0]]) / 2); p++);
            f === b.year && a < 5 * f && (k = [1, 2, 5]);
            a = l(a / f, k, "year" === c[0] ? Math.max(m(a / f), 1) : 1);
            return {unitRange: f, count: a, unitName: c[0]}
        }
    })(K);
    (function (a) {
        var w = a.Axis, B = a.getMagnitude, D = a.map, F = a.normalizeTickInterval,
            q = a.pick;
        w.prototype.getLogTickPositions = function (a, c, m, n) {
            var e = this.options, h = this.len, b = this.lin2log, C = this.log2lin, y = [];
            n || (this._minorAutoInterval = null);
            if (.5 <= a)a = Math.round(a), y = this.getLinearTickPositions(a, c, m); else if (.08 <= a)for (var h = Math.floor(c), r, f, k, p, H, e = .3 < a ? [1, 2, 4] : .15 < a ? [1, 2, 4, 6, 8] : [1, 2, 3, 4, 5, 6, 7, 8, 9]; h < m + 1 && !H; h++)for (f = e.length, r = 0; r < f && !H; r++)k = C(b(h) * e[r]), k > c && (!n || p <= m) && void 0 !== p && y.push(p), p > m && (H = !0), p = k; else c = b(c), m = b(m), a = e[n ? "minorTickInterval" : "tickInterval"], a = q("auto" ===
            a ? null : a, this._minorAutoInterval, e.tickPixelInterval / (n ? 5 : 1) * (m - c) / ((n ? h / this.tickPositions.length : h) || 1)), a = F(a, null, B(a)), y = D(this.getLinearTickPositions(a, c, m), C), n || (this._minorAutoInterval = a / 5);
            n || (this.tickInterval = a);
            return y
        };
        w.prototype.log2lin = function (a) {
            return Math.log(a) / Math.LN10
        };
        w.prototype.lin2log = function (a) {
            return Math.pow(10, a)
        }
    })(K);
    (function (a) {
        var w = a.dateFormat, B = a.each, D = a.extend, F = a.format, q = a.isNumber, e = a.map, c = a.merge, m = a.pick, n = a.splat, l = a.syncTimeout, h = a.timeUnits;
        a.Tooltip =
            function () {
                this.init.apply(this, arguments)
            };
        a.Tooltip.prototype = {
            init: function (a, c) {
                this.chart = a;
                this.options = c;
                this.crosshairs = [];
                this.now = {x: 0, y: 0};
                this.isHidden = !0;
                this.split = c.split && !a.inverted;
                this.shared = c.shared || this.split
            }, cleanSplit: function (a) {
                B(this.chart.series, function (b) {
                    var c = b && b.tt;
                    c && (!c.isActive || a ? b.tt = c.destroy() : c.isActive = !1)
                })
            }, applyFilter: function () {
                var a = this.chart;
                a.renderer.definition({
                    tagName: "filter", id: "drop-shadow-" + a.index, opacity: .5, children: [{
                        tagName: "feGaussianBlur",
                        in: "SourceAlpha", stdDeviation: 1
                    }, {tagName: "feOffset", dx: 1, dy: 1}, {
                        tagName: "feComponentTransfer",
                        children: [{tagName: "feFuncA", type: "linear", slope: .3}]
                    }, {
                        tagName: "feMerge",
                        children: [{tagName: "feMergeNode"}, {tagName: "feMergeNode", in: "SourceGraphic"}]
                    }]
                });
                a.renderer.definition({
                    tagName: "style",
                    textContent: ".highcharts-tooltip-" + a.index + "{filter:url(#drop-shadow-" + a.index + ")}"
                })
            }, getLabel: function () {
                var a = this.chart.renderer, c = this.options;
                this.label || (this.label = this.split ? a.g("tooltip") : a.label("", 0,
                    0, c.shape || "callout", null, null, c.useHTML, null, "tooltip").attr({
                        padding: c.padding,
                        r: c.borderRadius
                    }), this.applyFilter(), this.label.addClass("highcharts-tooltip-" + this.chart.index), this.label.attr({zIndex: 8}).add());
                return this.label
            }, update: function (a) {
                this.destroy();
                this.init(this.chart, c(!0, this.options, a))
            }, destroy: function () {
                this.label && (this.label = this.label.destroy());
                this.split && this.tt && (this.cleanSplit(this.chart, !0), this.tt = this.tt.destroy());
                clearTimeout(this.hideTimer);
                clearTimeout(this.tooltipTimeout)
            },
            move: function (a, c, e, h) {
                var f = this, b = f.now, p = !1 !== f.options.animation && !f.isHidden && (1 < Math.abs(a - b.x) || 1 < Math.abs(c - b.y)), r = f.followPointer || 1 < f.len;
                D(b, {
                    x: p ? (2 * b.x + a) / 3 : a,
                    y: p ? (b.y + c) / 2 : c,
                    anchorX: r ? void 0 : p ? (2 * b.anchorX + e) / 3 : e,
                    anchorY: r ? void 0 : p ? (b.anchorY + h) / 2 : h
                });
                f.getLabel().attr(b);
                p && (clearTimeout(this.tooltipTimeout), this.tooltipTimeout = setTimeout(function () {
                    f && f.move(a, c, e, h)
                }, 32))
            }, hide: function (a) {
                var b = this;
                clearTimeout(this.hideTimer);
                a = m(a, this.options.hideDelay, 500);
                this.isHidden || (this.hideTimer =
                    l(function () {
                        b.getLabel()[a ? "fadeOut" : "hide"]();
                        b.isHidden = !0
                    }, a))
            }, getAnchor: function (a, c) {
                var b, h = this.chart, f = h.inverted, k = h.plotTop, p = h.plotLeft, l = 0, t = 0, m, G;
                a = n(a);
                b = a[0].tooltipPos;
                this.followPointer && c && (void 0 === c.chartX && (c = h.pointer.normalize(c)), b = [c.chartX - h.plotLeft, c.chartY - k]);
                b || (B(a, function (a) {
                    m = a.series.yAxis;
                    G = a.series.xAxis;
                    l += a.plotX + (!f && G ? G.left - p : 0);
                    t += (a.plotLow ? (a.plotLow + a.plotHigh) / 2 : a.plotY) + (!f && m ? m.top - k : 0)
                }), l /= a.length, t /= a.length, b = [f ? h.plotWidth - t : l, this.shared && !f && 1 < a.length && c ? c.chartY - k : f ? h.plotHeight - l : t]);
                return e(b, Math.round)
            }, getPosition: function (a, c, e) {
                var b = this.chart, f = this.distance, k = {}, p = e.h || 0, h, t = ["y", b.chartHeight, c, e.plotY + b.plotTop, b.plotTop, b.plotTop + b.plotHeight], l = ["x", b.chartWidth, a, e.plotX + b.plotLeft, b.plotLeft, b.plotLeft + b.plotWidth], n = !this.followPointer && m(e.ttBelow, !b.inverted === !!e.negative), z = function (a, d, b, c, e, h) {
                    var g = b < c - f, t = c + f + b < d, u = c - f - b;
                    c += f;
                    if (n && t)k[a] = c; else if (!n && g)k[a] = u; else if (g)k[a] = Math.min(h - b, 0 > u - p ? u : u - p);
                    else if (t)k[a] = Math.max(e, c + p + b > d ? c : c + p); else return !1
                }, d = function (a, d, b, c) {
                    var g;
                    c < f || c > d - f ? g = !1 : k[a] = c < b / 2 ? 1 : c > d - b / 2 ? d - b - 2 : c - b / 2;
                    return g
                }, A = function (a) {
                    var d = t;
                    t = l;
                    l = d;
                    h = a
                }, u = function () {
                    !1 !== z.apply(0, t) ? !1 !== d.apply(0, l) || h || (A(!0), u()) : h ? k.x = k.y = 0 : (A(!0), u())
                };
                (b.inverted || 1 < this.len) && A();
                u();
                return k
            }, defaultFormatter: function (a) {
                var b = this.points || n(this), c;
                c = [a.tooltipFooterHeaderFormatter(b[0])];
                c = c.concat(a.bodyFormatter(b));
                c.push(a.tooltipFooterHeaderFormatter(b[0], !0));
                return c
            }, refresh: function (a,
                                  c) {
                var b = this.chart, e, f, k, p = {}, h = [];
                e = this.options.formatter || this.defaultFormatter;
                var p = b.hoverPoints, t = this.shared;
                clearTimeout(this.hideTimer);
                this.followPointer = n(a)[0].series.tooltipOptions.followPointer;
                k = this.getAnchor(a, c);
                c = k[0];
                f = k[1];
                !t || a.series && a.series.noSharedTooltip ? p = a.getLabelConfig() : (b.hoverPoints = a, p && B(p, function (a) {
                    a.setState()
                }), B(a, function (a) {
                    a.setState("hover");
                    h.push(a.getLabelConfig())
                }), p = {x: a[0].category, y: a[0].y}, p.points = h, this.len = h.length, a = a[0]);
                p = e.call(p, this);
                t = a.series;
                this.distance = m(t.tooltipOptions.distance, 16);
                !1 === p ? this.hide() : (e = this.getLabel(), this.isHidden && e.attr({opacity: 1}).show(), this.split ? this.renderSplit(p, b.hoverPoints) : (e.attr({text: p && p.join ? p.join("") : p}), e.removeClass(/highcharts-color-[\d]+/g).addClass("highcharts-color-" + m(a.colorIndex, t.colorIndex)), this.updatePosition({
                    plotX: c,
                    plotY: f,
                    negative: a.negative,
                    ttBelow: a.ttBelow,
                    h: k[2] || 0
                })), this.isHidden = !1)
            }, renderSplit: function (b, c) {
                var e = this, h = [], f = this.chart, k = f.renderer, p = !0,
                    l = this.options, t, n = this.getLabel();
                B(b.slice(0, b.length - 1), function (a, b) {
                    b = c[b - 1] || {isHeader: !0, plotX: c[0].plotX};
                    var d = b.series || e, A = d.tt, u = "highcharts-color-" + m(b.colorIndex, (b.series || {}).colorIndex, "none");
                    A || (d.tt = A = k.label(null, null, null, "callout").addClass("highcharts-tooltip-box " + u).attr({
                        padding: l.padding,
                        r: l.borderRadius
                    }).add(n));
                    A.isActive = !0;
                    A.attr({text: a});
                    a = A.getBBox();
                    u = a.width + A.strokeWidth();
                    b.isHeader ? (t = a.height, u = Math.max(0, Math.min(b.plotX + f.plotLeft - u / 2, f.chartWidth - u))) :
                        u = b.plotX + f.plotLeft - m(l.distance, 16) - u;
                    0 > u && (p = !1);
                    a = (b.series && b.series.yAxis && b.series.yAxis.pos) + (b.plotY || 0);
                    a -= f.plotTop;
                    h.push({
                        target: b.isHeader ? f.plotHeight + t : a,
                        rank: b.isHeader ? 1 : 0,
                        size: d.tt.getBBox().height + 1,
                        point: b,
                        x: u,
                        tt: A
                    })
                });
                this.cleanSplit();
                a.distribute(h, f.plotHeight + t);
                B(h, function (a) {
                    var b = a.point, d = b.series;
                    a.tt.attr({
                        visibility: void 0 === a.pos ? "hidden" : "inherit",
                        x: p || b.isHeader ? a.x : b.plotX + f.plotLeft + m(l.distance, 16),
                        y: a.pos + f.plotTop,
                        anchorX: b.isHeader ? b.plotX + f.plotLeft : b.plotX +
                        d.xAxis.pos,
                        anchorY: b.isHeader ? a.pos + f.plotTop - 15 : b.plotY + d.yAxis.pos
                    })
                })
            }, updatePosition: function (a) {
                var b = this.chart, c = this.getLabel(), c = (this.options.positioner || this.getPosition).call(this, c.width, c.height, a);
                this.move(Math.round(c.x), Math.round(c.y || 0), a.plotX + b.plotLeft, a.plotY + b.plotTop)
            }, getXDateFormat: function (a, c, e) {
                var b;
                c = c.dateTimeLabelFormats;
                var f = e && e.closestPointRange, k, p = {
                    millisecond: 15,
                    second: 12,
                    minute: 9,
                    hour: 6,
                    day: 3
                }, l, t = "millisecond";
                if (f) {
                    l = w("%m-%d %H:%M:%S.%L", a.x);
                    for (k in h) {
                        if (f ===
                            h.week && +w("%w", a.x) === e.options.startOfWeek && "00:00:00.000" === l.substr(6)) {
                            k = "week";
                            break
                        }
                        if (h[k] > f) {
                            k = t;
                            break
                        }
                        if (p[k] && l.substr(p[k]) !== "01-01 00:00:00.000".substr(p[k]))break;
                        "week" !== k && (t = k)
                    }
                    k && (b = c[k])
                } else b = c.day;
                return b || c.year
            }, tooltipFooterHeaderFormatter: function (a, c) {
                var b = c ? "footer" : "header";
                c = a.series;
                var e = c.tooltipOptions, f = e.xDateFormat, k = c.xAxis, p = k && "datetime" === k.options.type && q(a.key), b = e[b + "Format"];
                p && !f && (f = this.getXDateFormat(a, e, k));
                p && f && (b = b.replace("{point.key}", "{point.key:" +
                    f + "}"));
                return F(b, {point: a, series: c})
            }, bodyFormatter: function (a) {
                return e(a, function (a) {
                    var b = a.series.tooltipOptions;
                    return (b.pointFormatter || a.point.tooltipFormatter).call(a.point, b.pointFormat)
                })
            }
        }
    })(K);
    (function (a) {
        var w = a.addEvent, B = a.attr, D = a.charts, F = a.css, q = a.defined, e = a.doc, c = a.each, m = a.extend, n = a.fireEvent, l = a.offset, h = a.pick, b = a.removeEvent, C = a.splat, y = a.Tooltip, r = a.win;
        a.Pointer = function (a, b) {
            this.init(a, b)
        };
        a.Pointer.prototype = {
            init: function (a, b) {
                this.options = b;
                this.chart = a;
                this.runChartClick =
                    b.chart.events && !!b.chart.events.click;
                this.pinchDown = [];
                this.lastValidTouch = {};
                y && b.tooltip.enabled && (a.tooltip = new y(a, b.tooltip), this.followTouchMove = h(b.tooltip.followTouchMove, !0));
                this.setDOMEvents()
            }, zoomOption: function (a) {
                var b = this.chart, f = b.options.chart, c = f.zoomType || "", b = b.inverted;
                /touch/.test(a.type) && (c = h(f.pinchType, c));
                this.zoomX = a = /x/.test(c);
                this.zoomY = c = /y/.test(c);
                this.zoomHor = a && !b || c && b;
                this.zoomVert = c && !b || a && b;
                this.hasZoom = a || c
            }, normalize: function (a, b) {
                var f, c;
                a = a || r.event;
                a.target || (a.target = a.srcElement);
                c = a.touches ? a.touches.length ? a.touches.item(0) : a.changedTouches[0] : a;
                b || (this.chartPosition = b = l(this.chart.container));
                void 0 === c.pageX ? (f = Math.max(a.x, a.clientX - b.left), b = a.y) : (f = c.pageX - b.left, b = c.pageY - b.top);
                return m(a, {chartX: Math.round(f), chartY: Math.round(b)})
            }, getCoordinates: function (a) {
                var b = {xAxis: [], yAxis: []};
                c(this.chart.axes, function (f) {
                    b[f.isXAxis ? "xAxis" : "yAxis"].push({axis: f, value: f.toValue(a[f.horiz ? "chartX" : "chartY"])})
                });
                return b
            }, runPointActions: function (b) {
                var f =
                    this.chart, p = f.series, l = f.tooltip, t = l ? l.shared : !1, m = !0, r = f.hoverPoint, n = f.hoverSeries, d, A, u, I = [], J;
                if (!t && !n)for (d = 0; d < p.length; d++)if (p[d].directTouch || !p[d].options.stickyTracking)p = [];
                n && (t ? n.noSharedTooltip : n.directTouch) && r ? I = [r] : (t || !n || n.options.stickyTracking || (p = [n]), c(p, function (a) {
                    A = a.noSharedTooltip && t;
                    u = !t && a.directTouch;
                    a.visible && !A && !u && h(a.options.enableMouseTracking, !0) && (J = a.searchPoint(b, !A && 1 === a.kdDimensions)) && J.series && I.push(J)
                }), I.sort(function (a, d) {
                    var b = a.distX - d.distX,
                        f = a.dist - d.dist, c = d.series.group.zIndex - a.series.group.zIndex;
                    return 0 !== b && t ? b : 0 !== f ? f : 0 !== c ? c : a.series.index > d.series.index ? -1 : 1
                }));
                if (t)for (d = I.length; d--;)(I[d].x !== I[0].x || I[d].series.noSharedTooltip) && I.splice(d, 1);
                if (I[0] && (I[0] !== this.prevKDPoint || l && l.isHidden)) {
                    if (t && !I[0].series.noSharedTooltip) {
                        for (d = 0; d < I.length; d++)I[d].onMouseOver(b, I[d] !== (n && n.directTouch && r || I[0]));
                        I.length && l && l.refresh(I.sort(function (a, d) {
                            return a.series.index - d.series.index
                        }), b)
                    } else if (l && l.refresh(I[0], b),
                        !n || !n.directTouch)I[0].onMouseOver(b);
                    this.prevKDPoint = I[0];
                    m = !1
                }
                m && (p = n && n.tooltipOptions.followPointer, l && p && !l.isHidden && (p = l.getAnchor([{}], b), l.updatePosition({
                    plotX: p[0],
                    plotY: p[1]
                })));
                this.unDocMouseMove || (this.unDocMouseMove = w(e, "mousemove", function (d) {
                    if (D[a.hoverChartIndex])D[a.hoverChartIndex].pointer.onDocumentMouseMove(d)
                }));
                c(t ? I : [h(r, I[0])], function (a) {
                    c(f.axes, function (d) {
                        (!a || a.series && a.series[d.coll] === d) && d.drawCrosshair(b, a)
                    })
                })
            }, reset: function (a, b) {
                var f = this.chart, k = f.hoverSeries,
                    e = f.hoverPoint, h = f.hoverPoints, l = f.tooltip, m = l && l.shared ? h : e;
                a && m && c(C(m), function (d) {
                    d.series.isCartesian && void 0 === d.plotX && (a = !1)
                });
                if (a)l && m && (l.refresh(m), e && (e.setState(e.state, !0), c(f.axes, function (a) {
                    a.crosshair && a.drawCrosshair(null, e)
                }))); else {
                    if (e)e.onMouseOut();
                    h && c(h, function (a) {
                        a.setState()
                    });
                    if (k)k.onMouseOut();
                    l && l.hide(b);
                    this.unDocMouseMove && (this.unDocMouseMove = this.unDocMouseMove());
                    c(f.axes, function (a) {
                        a.hideCrosshair()
                    });
                    this.hoverX = this.prevKDPoint = f.hoverPoints = f.hoverPoint =
                        null
                }
            }, scaleGroups: function (a, b) {
                var f = this.chart, k;
                c(f.series, function (c) {
                    k = a || c.getPlotBox();
                    c.xAxis && c.xAxis.zoomEnabled && c.group && (c.group.attr(k), c.markerGroup && (c.markerGroup.attr(k), c.markerGroup.clip(b ? f.clipRect : null)), c.dataLabelsGroup && c.dataLabelsGroup.attr(k))
                });
                f.clipRect.attr(b || f.clipBox)
            }, dragStart: function (a) {
                var b = this.chart;
                b.mouseIsDown = a.type;
                b.cancelClick = !1;
                b.mouseDownX = this.mouseDownX = a.chartX;
                b.mouseDownY = this.mouseDownY = a.chartY
            }, drag: function (a) {
                var b = this.chart, f = b.options.chart,
                    c = a.chartX, e = a.chartY, h = this.zoomHor, l = this.zoomVert, m = b.plotLeft, d = b.plotTop, A = b.plotWidth, u = b.plotHeight, r, n = this.selectionMarker, g = this.mouseDownX, v = this.mouseDownY, q = f.panKey && a[f.panKey + "Key"];
                n && n.touch || (c < m ? c = m : c > m + A && (c = m + A), e < d ? e = d : e > d + u && (e = d + u), this.hasDragged = Math.sqrt(Math.pow(g - c, 2) + Math.pow(v - e, 2)), 10 < this.hasDragged && (r = b.isInsidePlot(g - m, v - d), b.hasCartesianSeries && (this.zoomX || this.zoomY) && r && !q && !n && (this.selectionMarker = n = b.renderer.rect(m, d, h ? 1 : A, l ? 1 : u, 0).attr({
                    "class": "highcharts-selection-marker",
                    zIndex: 7
                }).add()), n && h && (c -= g, n.attr({
                    width: Math.abs(c),
                    x: (0 < c ? 0 : c) + g
                })), n && l && (c = e - v, n.attr({
                    height: Math.abs(c),
                    y: (0 < c ? 0 : c) + v
                })), r && !n && f.panning && b.pan(a, f.panning)))
            }, drop: function (a) {
                var b = this, f = this.chart, e = this.hasPinched;
                if (this.selectionMarker) {
                    var h = {
                        originalEvent: a,
                        xAxis: [],
                        yAxis: []
                    }, l = this.selectionMarker, r = l.attr ? l.attr("x") : l.x, z = l.attr ? l.attr("y") : l.y, d = l.attr ? l.attr("width") : l.width, A = l.attr ? l.attr("height") : l.height, u;
                    if (this.hasDragged || e)c(f.axes, function (f) {
                        if (f.zoomEnabled && q(f.min) &&
                            (e || b[{xAxis: "zoomX", yAxis: "zoomY"}[f.coll]])) {
                            var c = f.horiz, g = "touchend" === a.type ? f.minPixelPadding : 0, k = f.toValue((c ? r : z) + g), c = f.toValue((c ? r + d : z + A) - g);
                            h[f.coll].push({axis: f, min: Math.min(k, c), max: Math.max(k, c)});
                            u = !0
                        }
                    }), u && n(f, "selection", h, function (a) {
                        f.zoom(m(a, e ? {animation: !1} : null))
                    });
                    this.selectionMarker = this.selectionMarker.destroy();
                    e && this.scaleGroups()
                }
                f && (F(f.container, {cursor: f._cursor}), f.cancelClick = 10 < this.hasDragged, f.mouseIsDown = this.hasDragged = this.hasPinched = !1, this.pinchDown =
                    [])
            }, onContainerMouseDown: function (a) {
                a = this.normalize(a);
                this.zoomOption(a);
                a.preventDefault && a.preventDefault();
                this.dragStart(a)
            }, onDocumentMouseUp: function (b) {
                D[a.hoverChartIndex] && D[a.hoverChartIndex].pointer.drop(b)
            }, onDocumentMouseMove: function (a) {
                var b = this.chart, f = this.chartPosition;
                a = this.normalize(a, f);
                !f || this.inClass(a.target, "highcharts-tracker") || b.isInsidePlot(a.chartX - b.plotLeft, a.chartY - b.plotTop) || this.reset()
            }, onContainerMouseLeave: function (b) {
                var f = D[a.hoverChartIndex];
                f && (b.relatedTarget ||
                b.toElement) && (f.pointer.reset(), f.pointer.chartPosition = null)
            }, onContainerMouseMove: function (b) {
                var f = this.chart;
                q(a.hoverChartIndex) && D[a.hoverChartIndex] && D[a.hoverChartIndex].mouseIsDown || (a.hoverChartIndex = f.index);
                b = this.normalize(b);
                b.returnValue = !1;
                "mousedown" === f.mouseIsDown && this.drag(b);
                !this.inClass(b.target, "highcharts-tracker") && !f.isInsidePlot(b.chartX - f.plotLeft, b.chartY - f.plotTop) || f.openMenu || this.runPointActions(b)
            }, inClass: function (a, b) {
                for (var f; a;) {
                    if (f = B(a, "class")) {
                        if (-1 !==
                            f.indexOf(b))return !0;
                        if (-1 !== f.indexOf("highcharts-container"))return !1
                    }
                    a = a.parentNode
                }
            }, onTrackerMouseOut: function (a) {
                var b = this.chart.hoverSeries;
                a = a.relatedTarget || a.toElement;
                if (!(!b || !a || b.options.stickyTracking || this.inClass(a, "highcharts-tooltip") || this.inClass(a, "highcharts-series-" + b.index) && this.inClass(a, "highcharts-tracker")))b.onMouseOut()
            }, onContainerClick: function (a) {
                var b = this.chart, f = b.hoverPoint, c = b.plotLeft, e = b.plotTop;
                a = this.normalize(a);
                b.cancelClick || (f && this.inClass(a.target,
                    "highcharts-tracker") ? (n(f.series, "click", m(a, {point: f})), b.hoverPoint && f.firePointEvent("click", a)) : (m(a, this.getCoordinates(a)), b.isInsidePlot(a.chartX - c, a.chartY - e) && n(b, "click", a)))
            }, setDOMEvents: function () {
                var b = this, c = b.chart.container;
                c.onmousedown = function (a) {
                    b.onContainerMouseDown(a)
                };
                c.onmousemove = function (a) {
                    b.onContainerMouseMove(a)
                };
                c.onclick = function (a) {
                    b.onContainerClick(a)
                };
                w(c, "mouseleave", b.onContainerMouseLeave);
                1 === a.chartCount && w(e, "mouseup", b.onDocumentMouseUp);
                a.hasTouch && (c.ontouchstart =
                    function (a) {
                        b.onContainerTouchStart(a)
                    }, c.ontouchmove = function (a) {
                    b.onContainerTouchMove(a)
                }, 1 === a.chartCount && w(e, "touchend", b.onDocumentTouchEnd))
            }, destroy: function () {
                var f;
                b(this.chart.container, "mouseleave", this.onContainerMouseLeave);
                a.chartCount || (b(e, "mouseup", this.onDocumentMouseUp), b(e, "touchend", this.onDocumentTouchEnd));
                clearInterval(this.tooltipTimeout);
                for (f in this)this[f] = null
            }
        }
    })(K);
    (function (a) {
        var w = a.charts, B = a.each, D = a.extend, F = a.map, q = a.noop, e = a.pick;
        D(a.Pointer.prototype, {
            pinchTranslate: function (a,
                                      e, n, l, h, b) {
                this.zoomHor && this.pinchTranslateDirection(!0, a, e, n, l, h, b);
                this.zoomVert && this.pinchTranslateDirection(!1, a, e, n, l, h, b)
            }, pinchTranslateDirection: function (a, e, n, l, h, b, q, y) {
                var c = this.chart, f = a ? "x" : "y", k = a ? "X" : "Y", p = "chart" + k, m = a ? "width" : "height", t = c["plot" + (a ? "Left" : "Top")], E, G, z = y || 1, d = c.inverted, A = c.bounds[a ? "h" : "v"], u = 1 === e.length, I = e[0][p], J = n[0][p], g = !u && e[1][p], v = !u && n[1][p], C;
                n = function () {
                    !u && 20 < Math.abs(I - g) && (z = y || Math.abs(J - v) / Math.abs(I - g));
                    G = (t - J) / z + I;
                    E = c["plot" + (a ? "Width" : "Height")] /
                        z
                };
                n();
                e = G;
                e < A.min ? (e = A.min, C = !0) : e + E > A.max && (e = A.max - E, C = !0);
                C ? (J -= .8 * (J - q[f][0]), u || (v -= .8 * (v - q[f][1])), n()) : q[f] = [J, v];
                d || (b[f] = G - t, b[m] = E);
                b = d ? 1 / z : z;
                h[m] = E;
                h[f] = e;
                l[d ? a ? "scaleY" : "scaleX" : "scale" + k] = z;
                l["translate" + k] = b * t + (J - b * I)
            }, pinch: function (a) {
                var c = this, n = c.chart, l = c.pinchDown, h = a.touches, b = h.length, C = c.lastValidTouch, y = c.hasZoom, r = c.selectionMarker, f = {}, k = 1 === b && (c.inClass(a.target, "highcharts-tracker") && n.runTrackerClick || c.runChartClick), p = {};
                1 < b && (c.initiated = !0);
                y && c.initiated && !k &&
                a.preventDefault();
                F(h, function (a) {
                    return c.normalize(a)
                });
                "touchstart" === a.type ? (B(h, function (a, b) {
                    l[b] = {chartX: a.chartX, chartY: a.chartY}
                }), C.x = [l[0].chartX, l[1] && l[1].chartX], C.y = [l[0].chartY, l[1] && l[1].chartY], B(n.axes, function (a) {
                    if (a.zoomEnabled) {
                        var b = n.bounds[a.horiz ? "h" : "v"], f = a.minPixelPadding, c = a.toPixels(e(a.options.min, a.dataMin)), k = a.toPixels(e(a.options.max, a.dataMax)), d = Math.max(c, k);
                        b.min = Math.min(a.pos, Math.min(c, k) - f);
                        b.max = Math.max(a.pos + a.len, d + f)
                    }
                }), c.res = !0) : c.followTouchMove &&
                1 === b ? this.runPointActions(c.normalize(a)) : l.length && (r || (c.selectionMarker = r = D({
                    destroy: q,
                    touch: !0
                }, n.plotBox)), c.pinchTranslate(l, h, f, r, p, C), c.hasPinched = y, c.scaleGroups(f, p), c.res && (c.res = !1, this.reset(!1, 0)))
            }, touch: function (c, m) {
                var n = this.chart, l, h;
                if (n.index !== a.hoverChartIndex)this.onContainerMouseLeave({relatedTarget: !0});
                a.hoverChartIndex = n.index;
                1 === c.touches.length ? (c = this.normalize(c), (h = n.isInsidePlot(c.chartX - n.plotLeft, c.chartY - n.plotTop)) && !n.openMenu ? (m && this.runPointActions(c),
                "touchmove" === c.type && (m = this.pinchDown, l = m[0] ? 4 <= Math.sqrt(Math.pow(m[0].chartX - c.chartX, 2) + Math.pow(m[0].chartY - c.chartY, 2)) : !1), e(l, !0) && this.pinch(c)) : m && this.reset()) : 2 === c.touches.length && this.pinch(c)
            }, onContainerTouchStart: function (a) {
                this.zoomOption(a);
                this.touch(a, !0)
            }, onContainerTouchMove: function (a) {
                this.touch(a)
            }, onDocumentTouchEnd: function (c) {
                w[a.hoverChartIndex] && w[a.hoverChartIndex].pointer.drop(c)
            }
        })
    })(K);
    (function (a) {
        var w = a.addEvent, B = a.charts, D = a.css, F = a.doc, q = a.extend, e = a.noop,
            c = a.Pointer, m = a.removeEvent, n = a.win, l = a.wrap;
        if (n.PointerEvent || n.MSPointerEvent) {
            var h = {}, b = !!n.PointerEvent, C = function () {
                var a, b = [];
                b.item = function (a) {
                    return this[a]
                };
                for (a in h)h.hasOwnProperty(a) && b.push({pageX: h[a].pageX, pageY: h[a].pageY, target: h[a].target});
                return b
            }, y = function (b, f, c, h) {
                "touch" !== b.pointerType && b.pointerType !== b.MSPOINTER_TYPE_TOUCH || !B[a.hoverChartIndex] || (h(b), h = B[a.hoverChartIndex].pointer, h[f]({
                    type: c,
                    target: b.currentTarget,
                    preventDefault: e,
                    touches: C()
                }))
            };
            q(c.prototype,
                {
                    onContainerPointerDown: function (a) {
                        y(a, "onContainerTouchStart", "touchstart", function (a) {
                            h[a.pointerId] = {pageX: a.pageX, pageY: a.pageY, target: a.currentTarget}
                        })
                    }, onContainerPointerMove: function (a) {
                    y(a, "onContainerTouchMove", "touchmove", function (a) {
                        h[a.pointerId] = {pageX: a.pageX, pageY: a.pageY};
                        h[a.pointerId].target || (h[a.pointerId].target = a.currentTarget)
                    })
                }, onDocumentPointerUp: function (a) {
                    y(a, "onDocumentTouchEnd", "touchend", function (a) {
                        delete h[a.pointerId]
                    })
                }, batchMSEvents: function (a) {
                    a(this.chart.container,
                        b ? "pointerdown" : "MSPointerDown", this.onContainerPointerDown);
                    a(this.chart.container, b ? "pointermove" : "MSPointerMove", this.onContainerPointerMove);
                    a(F, b ? "pointerup" : "MSPointerUp", this.onDocumentPointerUp)
                }
                });
            l(c.prototype, "init", function (a, b, c) {
                a.call(this, b, c);
                this.hasZoom && D(b.container, {"-ms-touch-action": "none", "touch-action": "none"})
            });
            l(c.prototype, "setDOMEvents", function (a) {
                a.apply(this);
                (this.hasZoom || this.followTouchMove) && this.batchMSEvents(w)
            });
            l(c.prototype, "destroy", function (a) {
                this.batchMSEvents(m);
                a.call(this)
            })
        }
    })(K);
    (function (a) {
        var w, B = a.addEvent, D = a.css, F = a.discardElement, q = a.defined, e = a.each, c = a.extend, m = a.isFirefox, n = a.marginNames, l = a.merge, h = a.pick, b = a.setAnimation, C = a.stableSort, y = a.win, r = a.wrap;
        w = a.Legend = function (a, b) {
            this.init(a, b)
        };
        w.prototype = {
            init: function (a, b) {
                this.chart = a;
                this.setOptions(b);
                b.enabled && (this.render(), B(this.chart, "endResize", function () {
                    this.legend.positionCheckboxes()
                }))
            }, setOptions: function (a) {
                var b = h(a.padding, 8);
                this.options = a;
                this.itemMarginTop = a.itemMarginTop ||
                    0;
                this.initialItemX = this.padding = b;
                this.initialItemY = b - 5;
                this.itemHeight = this.maxItemWidth = 0;
                this.symbolWidth = h(a.symbolWidth, 16);
                this.pages = []
            }, update: function (a, b) {
                var f = this.chart;
                this.setOptions(l(!0, this.options, a));
                this.destroy();
                f.isDirtyLegend = f.isDirtyBox = !0;
                h(b, !0) && f.redraw()
            }, colorizeItem: function (a, b) {
                a.legendGroup[b ? "removeClass" : "addClass"]("highcharts-legend-item-hidden")
            }, positionItem: function (a) {
                var b = this.options, f = b.symbolPadding, b = !b.rtl, c = a._legendItemPos, e = c[0], c = c[1], h = a.checkbox;
                (a = a.legendGroup) && a.element && a.translate(b ? e : this.legendWidth - e - 2 * f - 4, c);
                h && (h.x = e, h.y = c)
            }, destroyItem: function (a) {
                var b = a.checkbox;
                e(["legendItem", "legendLine", "legendSymbol", "legendGroup"], function (b) {
                    a[b] && (a[b] = a[b].destroy())
                });
                b && F(a.checkbox)
            }, destroy: function () {
                function a(a) {
                    this[a] && (this[a] = this[a].destroy())
                }

                e(this.getAllItems(), function (b) {
                    e(["legendItem", "legendGroup"], a, b)
                });
                e(["box", "title", "group"], a, this);
                this.display = null
            }, positionCheckboxes: function (a) {
                var b = this.group && this.group.alignAttr,
                    f, c = this.clipHeight || this.legendHeight, h = this.titleHeight;
                b && (f = b.translateY, e(this.allItems, function (e) {
                    var k = e.checkbox, p;
                    k && (p = f + h + k.y + (a || 0) + 3, D(k, {
                        left: b.translateX + e.checkboxOffset + k.x - 20 + "px",
                        top: p + "px",
                        display: p > f - 6 && p < f + c - 6 ? "" : "none"
                    }))
                }))
            }, renderTitle: function () {
                var a = this.padding, b = this.options.title, c = 0;
                b.text && (this.title || (this.title = this.chart.renderer.label(b.text, a - 3, a - 4, null, null, null, null, null, "legend-title").attr({zIndex: 1}).add(this.group)), a = this.title.getBBox(), c = a.height, this.offsetWidth =
                    a.width, this.contentGroup.attr({translateY: c}));
                this.titleHeight = c
            }, setText: function (b) {
                var f = this.options;
                b.legendItem.attr({text: f.labelFormat ? a.format(f.labelFormat, b) : f.labelFormatter.call(b)})
            }, renderItem: function (a) {
                var b = this.chart, c = b.renderer, f = this.options, e = "horizontal" === f.layout, l = this.symbolWidth, n = f.symbolPadding, m = this.padding, d = e ? h(f.itemDistance, 20) : 0, A = !f.rtl, u = f.width, r = f.itemMarginBottom || 0, q = this.itemMarginTop, g = this.initialItemX, v = a.legendItem, y = !a.series, C = !y && a.series.drawLegendSymbol ?
                    a.series : a, w = C.options, w = this.createCheckboxForItem && w && w.showCheckbox, B = f.useHTML;
                v || (a.legendGroup = c.g("legend-item").addClass("highcharts-" + C.type + "-series highcharts-color-" + a.colorIndex + (a.options.className ? " " + a.options.className : "") + (y ? " highcharts-series-" + a.index : "")).attr({zIndex: 1}).add(this.scrollGroup), a.legendItem = v = c.text("", A ? l + n : -n, this.baseline || 0, B).attr({
                    align: A ? "left" : "right",
                    zIndex: 2
                }).add(a.legendGroup), this.baseline || (this.fontMetrics = c.fontMetrics(12, v), this.baseline = this.fontMetrics.f +
                    3 + q, v.attr("y", this.baseline)), C.drawLegendSymbol(this, a), this.setItemEvents && this.setItemEvents(a, v, B), w && this.createCheckboxForItem(a));
                this.colorizeItem(a, a.visible);
                this.setText(a);
                c = v.getBBox();
                l = a.checkboxOffset = f.itemWidth || a.legendItemWidth || l + n + c.width + d + (w ? 20 : 0);
                this.itemHeight = n = Math.round(a.legendItemHeight || c.height);
                e && this.itemX - g + l > (u || b.chartWidth - 2 * m - g - f.x) && (this.itemX = g, this.itemY += q + this.lastLineHeight + r, this.lastLineHeight = 0);
                this.maxItemWidth = Math.max(this.maxItemWidth, l);
                this.lastItemY =
                    q + this.itemY + r;
                this.lastLineHeight = Math.max(n, this.lastLineHeight);
                a._legendItemPos = [this.itemX, this.itemY];
                e ? this.itemX += l : (this.itemY += q + n + r, this.lastLineHeight = n);
                this.offsetWidth = u || Math.max((e ? this.itemX - g - d : l) + m, this.offsetWidth)
            }, getAllItems: function () {
                var a = [];
                e(this.chart.series, function (b) {
                    var c = b && b.options;
                    b && h(c.showInLegend, q(c.linkedTo) ? !1 : void 0, !0) && (a = a.concat(b.legendItems || ("point" === c.legendType ? b.data : b)))
                });
                return a
            }, adjustMargins: function (a, b) {
                var c = this.chart, f = this.options,
                    k = f.align.charAt(0) + f.verticalAlign.charAt(0) + f.layout.charAt(0);
                f.floating || e([/(lth|ct|rth)/, /(rtv|rm|rbv)/, /(rbh|cb|lbh)/, /(lbv|lm|ltv)/], function (e, p) {
                    e.test(k) && !q(a[p]) && (c[n[p]] = Math.max(c[n[p]], c.legend[(p + 1) % 2 ? "legendHeight" : "legendWidth"] + [1, -1, -1, 1][p] * f[p % 2 ? "x" : "y"] + h(f.margin, 12) + b[p]))
                })
            }, render: function () {
                var a = this, b = a.chart, h = b.renderer, l = a.group, t, n, m, r, d = a.box, A = a.options, u = a.padding;
                a.itemX = a.initialItemX;
                a.itemY = a.initialItemY;
                a.offsetWidth = 0;
                a.lastItemY = 0;
                l || (a.group = l = h.g("legend").attr({zIndex: 7}).add(),
                    a.contentGroup = h.g().attr({zIndex: 1}).add(l), a.scrollGroup = h.g().add(a.contentGroup));
                a.renderTitle();
                t = a.getAllItems();
                C(t, function (a, d) {
                    return (a.options && a.options.legendIndex || 0) - (d.options && d.options.legendIndex || 0)
                });
                A.reversed && t.reverse();
                a.allItems = t;
                a.display = n = !!t.length;
                a.lastLineHeight = 0;
                e(t, function (d) {
                    a.renderItem(d)
                });
                m = (A.width || a.offsetWidth) + u;
                r = a.lastItemY + a.lastLineHeight + a.titleHeight;
                r = a.handleOverflow(r);
                r += u;
                d || (a.box = d = h.rect().addClass("highcharts-legend-box").attr({r: A.borderRadius}).add(l),
                    d.isNew = !0);
                0 < m && 0 < r && (d[d.isNew ? "attr" : "animate"](d.crisp({
                    x: 0,
                    y: 0,
                    width: m,
                    height: r
                }, d.strokeWidth())), d.isNew = !1);
                d[n ? "show" : "hide"]();
                "none" === l.getStyle("display") && (m = r = 0);
                a.legendWidth = m;
                a.legendHeight = r;
                e(t, function (d) {
                    a.positionItem(d)
                });
                n && l.align(c({width: m, height: r}, A), !0, "spacingBox");
                b.isResizing || this.positionCheckboxes()
            }, handleOverflow: function (a) {
                var b = this, c = this.chart, f = c.renderer, l = this.options, n = l.y, c = c.spacingBox.height + ("top" === l.verticalAlign ? -n : n) - this.padding, n = l.maxHeight,
                    m, r = this.clipRect, d = l.navigation, A = h(d.animation, !0), u = d.arrowSize || 12, q = this.nav, J = this.pages, g = this.padding, v, y = this.allItems, C = function (a) {
                        a ? r.attr({height: a}) : r && (b.clipRect = r.destroy(), b.contentGroup.clip());
                        b.contentGroup.div && (b.contentGroup.div.style.clip = a ? "rect(" + g + "px,9999px," + (g + a) + "px,0)" : "auto")
                    };
                "horizontal" !== l.layout || "middle" === l.verticalAlign || l.floating || (c /= 2);
                n && (c = Math.min(c, n));
                J.length = 0;
                a > c && !1 !== d.enabled ? (this.clipHeight = m = Math.max(c - 20 - this.titleHeight - g, 0), this.currentPage =
                    h(this.currentPage, 1), this.fullHeight = a, e(y, function (a, d) {
                    var b = a._legendItemPos[1];
                    a = Math.round(a.legendItem.getBBox().height);
                    var c = J.length;
                    if (!c || b - J[c - 1] > m && (v || b) !== J[c - 1])J.push(v || b), c++;
                    d === y.length - 1 && b + a - J[c - 1] > m && J.push(b);
                    b !== v && (v = b)
                }), r || (r = b.clipRect = f.clipRect(0, g, 9999, 0), b.contentGroup.clip(r)), C(m), q || (this.nav = q = f.g().attr({zIndex: 1}).add(this.group), this.up = f.symbol("triangle", 0, 0, u, u).on("click", function () {
                    b.scroll(-1, A)
                }).add(q), this.pager = f.text("", 15, 10).addClass("highcharts-legend-navigation").add(q),
                    this.down = f.symbol("triangle-down", 0, 0, u, u).on("click", function () {
                        b.scroll(1, A)
                    }).add(q)), b.scroll(0), a = c) : q && (C(), q.hide(), this.scrollGroup.attr({translateY: 1}), this.clipHeight = 0);
                return a
            }, scroll: function (a, c) {
                var f = this.pages, e = f.length;
                a = this.currentPage + a;
                var h = this.clipHeight, k = this.pager, l = this.padding;
                a > e && (a = e);
                0 < a && (void 0 !== c && b(c, this.chart), this.nav.attr({
                    translateX: l,
                    translateY: h + this.padding + 7 + this.titleHeight,
                    visibility: "visible"
                }), this.up.attr({
                    "class": 1 === a ? "highcharts-legend-nav-inactive" :
                        "highcharts-legend-nav-active"
                }), k.attr({text: a + "/" + e}), this.down.attr({
                    x: 18 + this.pager.getBBox().width,
                    "class": a === e ? "highcharts-legend-nav-inactive" : "highcharts-legend-nav-active"
                }), c = -f[a - 1] + this.initialItemY, this.scrollGroup.animate({translateY: c}), this.currentPage = a, this.positionCheckboxes(c))
            }
        };
        a.LegendSymbolMixin = {
            drawRectangle: function (a, b) {
                var c = a.options, f = c.symbolHeight || a.fontMetrics.f, c = c.squareSymbol;
                b.legendSymbol = this.chart.renderer.rect(c ? (a.symbolWidth - f) / 2 : 0, a.baseline - f + 1, c ? f :
                    a.symbolWidth, f, h(a.options.symbolRadius, f / 2)).addClass("highcharts-point").attr({zIndex: 3}).add(b.legendGroup)
            }, drawLineMarker: function (a) {
                var b = this.options.marker, c = a.symbolWidth, f = this.chart.renderer, e = this.legendGroup, h = a.baseline - Math.round(.3 * a.fontMetrics.b);
                this.legendLine = f.path(["M", 0, h, "L", c, h]).addClass("highcharts-graph").attr({}).add(e);
                b && !1 !== b.enabled && (a = 0 === this.symbol.indexOf("url") ? 0 : b.radius, this.legendSymbol = b = f.symbol(this.symbol, c / 2 - a, h - a, 2 * a, 2 * a, b).addClass("highcharts-point").add(e),
                    b.isMarker = !0)
            }
        };
        (/Trident\/7\.0/.test(y.navigator.userAgent) || m) && r(w.prototype, "positionItem", function (a, b) {
            var c = this, f = function () {
                b._legendItemPos && a.call(c, b)
            };
            f();
            setTimeout(f)
        })
    })(K);
    (function (a) {
        var w = a.addEvent, B = a.animObject, D = a.attr, F = a.doc, q = a.Axis, e = a.createElement, c = a.defaultOptions, m = a.discardElement, n = a.charts, l = a.css, h = a.defined, b = a.each, C = a.extend, y = a.find, r = a.fireEvent, f = a.getStyle, k = a.grep, p = a.isNumber, H = a.isObject, t = a.isString, E = a.Legend, G = a.marginNames, z = a.merge, d = a.Pointer,
            A = a.pick, u = a.pInt, I = a.removeEvent, J = a.seriesTypes, g = a.splat, v = a.svg, Q = a.syncTimeout, O = a.win, S = a.Renderer, P = a.Chart = function () {
                this.getArgs.apply(this, arguments)
            };
        a.chart = function (a, d, b) {
            return new P(a, d, b)
        };
        P.prototype = {
            callbacks: [], getArgs: function () {
                var a = [].slice.call(arguments);
                if (t(a[0]) || a[0].nodeName)this.renderTo = a.shift();
                this.init(a[0], a[1])
            }, init: function (d, b) {
                var f, g = d.series;
                d.series = null;
                f = z(c, d);
                f.series = d.series = g;
                this.userOptions = d;
                this.respRules = [];
                d = f.chart;
                g = d.events;
                this.margin =
                    [];
                this.spacing = [];
                this.bounds = {h: {}, v: {}};
                this.callback = b;
                this.isResizing = 0;
                this.options = f;
                this.axes = [];
                this.series = [];
                this.hasCartesianSeries = d.showAxes;
                var e;
                this.index = n.length;
                n.push(this);
                a.chartCount++;
                if (g)for (e in g)w(this, e, g[e]);
                this.xAxis = [];
                this.yAxis = [];
                this.pointCount = this.colorCounter = this.symbolCounter = 0;
                this.firstRender()
            }, initSeries: function (d) {
                var b = this.options.chart;
                (b = J[d.type || b.type || b.defaultSeriesType]) || a.error(17, !0);
                b = new b;
                b.init(this, d);
                return b
            }, isInsidePlot: function (a,
                                       d, b) {
                var c = b ? d : a;
                a = b ? a : d;
                return 0 <= c && c <= this.plotWidth && 0 <= a && a <= this.plotHeight
            }, redraw: function (d) {
                var c = this.axes, f = this.series, g = this.pointer, e = this.legend, h = this.isDirtyLegend, k, p, l = this.hasCartesianSeries, u = this.isDirtyBox, A = f.length, t = A, n = this.renderer, m = n.isHidden(), v = [];
                a.setAnimation(d, this);
                m && this.cloneRenderTo();
                for (this.layOutTitles(); t--;)if (d = f[t], d.options.stacking && (k = !0, d.isDirty)) {
                    p = !0;
                    break
                }
                if (p)for (t = A; t--;)d = f[t], d.options.stacking && (d.isDirty = !0);
                b(f, function (a) {
                    a.isDirty &&
                    "point" === a.options.legendType && (a.updateTotals && a.updateTotals(), h = !0);
                    a.isDirtyData && r(a, "updatedData")
                });
                h && e.options.enabled && (e.render(), this.isDirtyLegend = !1);
                k && this.getStacks();
                l && b(c, function (a) {
                    a.updateNames();
                    a.setScale()
                });
                this.getMargins();
                l && (b(c, function (a) {
                    a.isDirty && (u = !0)
                }), b(c, function (a) {
                    var d = a.min + "," + a.max;
                    a.extKey !== d && (a.extKey = d, v.push(function () {
                        r(a, "afterSetExtremes", C(a.eventArgs, a.getExtremes()));
                        delete a.eventArgs
                    }));
                    (u || k) && a.redraw()
                }));
                u && this.drawChartBox();
                b(f,
                    function (a) {
                        (u || a.isDirty) && a.visible && a.redraw()
                    });
                g && g.reset(!0);
                n.draw();
                r(this, "redraw");
                m && this.cloneRenderTo(!0);
                b(v, function (a) {
                    a.call()
                })
            }, get: function (a) {
                function d(d) {
                    return d.id === a || d.options.id === a
                }

                var b, c = this.series, f;
                b = y(this.axes, d) || y(this.series, d);
                for (f = 0; !b && f < c.length; f++)b = y(c[f].points || [], d);
                return b
            }, getAxes: function () {
                var a = this, d = this.options, c = d.xAxis = g(d.xAxis || {}), d = d.yAxis = g(d.yAxis || {});
                b(c, function (a, d) {
                    a.index = d;
                    a.isX = !0
                });
                b(d, function (a, d) {
                    a.index = d
                });
                c = c.concat(d);
                b(c, function (d) {
                    new q(a, d)
                })
            }, getSelectedPoints: function () {
                var a = [];
                b(this.series, function (d) {
                    a = a.concat(k(d.points || [], function (a) {
                        return a.selected
                    }))
                });
                return a
            }, getSelectedSeries: function () {
                return k(this.series, function (a) {
                    return a.selected
                })
            }, setTitle: function (a, d, c) {
                var f = this, g = f.options, e;
                e = g.title = z(g.title, a);
                g = g.subtitle = z(g.subtitle, d);
                b([["title", a, e], ["subtitle", d, g]], function (a, d) {
                    var b = a[0], c = f[b], g = a[1];
                    a = a[2];
                    c && g && (f[b] = c = c.destroy());
                    a && a.text && !c && (f[b] = f.renderer.text(a.text,
                        0, 0, a.useHTML).attr({
                            align: a.align,
                            "class": "highcharts-" + b,
                            zIndex: a.zIndex || 4
                        }).add(), f[b].update = function (a) {
                        f.setTitle(!d && a, d && a)
                    })
                });
                f.layOutTitles(c)
            }, layOutTitles: function (a) {
                var d = 0, c, f = this.renderer, g = this.spacingBox;
                b(["title", "subtitle"], function (a) {
                    var b = this[a], c = this.options[a], e;
                    b && (e = f.fontMetrics(e, b).b, b.css({width: (c.width || g.width + c.widthAdjust) + "px"}).align(C({y: d + e + ("title" === a ? -3 : 2)}, c), !1, "spacingBox"), c.floating || c.verticalAlign || (d = Math.ceil(d + b.getBBox().height)))
                }, this);
                c = this.titleOffset !== d;
                this.titleOffset = d;
                !this.isDirtyBox && c && (this.isDirtyBox = c, this.hasRendered && A(a, !0) && this.isDirtyBox && this.redraw())
            }, getChartSize: function () {
                var a = this.options.chart, d = a.width, a = a.height, b = this.renderToClone || this.renderTo;
                h(d) || (this.containerWidth = f(b, "width"));
                h(a) || (this.containerHeight = f(b, "height"));
                this.chartWidth = Math.max(0, d || this.containerWidth || 600);
                this.chartHeight = Math.max(0, A(a, 19 < this.containerHeight ? this.containerHeight : 400))
            }, cloneRenderTo: function (a) {
                var d =
                    this.renderToClone, b = this.container;
                if (a) {
                    if (d) {
                        for (; d.childNodes.length;)this.renderTo.appendChild(d.firstChild);
                        m(d);
                        delete this.renderToClone
                    }
                } else b && b.parentNode === this.renderTo && this.renderTo.removeChild(b), this.renderToClone = d = this.renderTo.cloneNode(0), l(d, {
                    position: "absolute",
                    top: "-9999px",
                    display: "block"
                }), d.style.setProperty && d.style.setProperty("display", "block", "important"), F.body.appendChild(d), b && d.appendChild(b)
            }, setClassName: function (a) {
                this.container.className = "highcharts-container " +
                    (a || "")
            }, getContainer: function () {
                var d, b = this.options, c = b.chart, f, g;
                d = this.renderTo;
                var h = a.uniqueKey(), k;
                d || (this.renderTo = d = c.renderTo);
                t(d) && (this.renderTo = d = F.getElementById(d));
                d || a.error(13, !0);
                f = u(D(d, "data-highcharts-chart"));
                p(f) && n[f] && n[f].hasRendered && n[f].destroy();
                D(d, "data-highcharts-chart", this.index);
                d.innerHTML = "";
                c.skipClone || d.offsetWidth || this.cloneRenderTo();
                this.getChartSize();
                f = this.chartWidth;
                g = this.chartHeight;
                this.container = d = e("div", {id: h}, void 0, this.renderToClone || d);
                this._cursor = d.style.cursor;
                this.renderer = new (a[c.renderer] || S)(d, f, g, null, c.forExport, b.exporting && b.exporting.allowHTML);
                this.setClassName(c.className);
                for (k in b.defs)this.renderer.definition(b.defs[k]);
                this.renderer.chartIndex = this.index
            }, getMargins: function (a) {
                var d = this.spacing, b = this.margin, c = this.titleOffset;
                this.resetMargins();
                c && !h(b[0]) && (this.plotTop = Math.max(this.plotTop, c + this.options.title.margin + d[0]));
                this.legend.display && this.legend.adjustMargins(b, d);
                this.extraBottomMargin && (this.marginBottom +=
                    this.extraBottomMargin);
                this.extraTopMargin && (this.plotTop += this.extraTopMargin);
                a || this.getAxisMargins()
            }, getAxisMargins: function () {
                var a = this, d = a.axisOffset = [0, 0, 0, 0], c = a.margin;
                a.hasCartesianSeries && b(a.axes, function (a) {
                    a.visible && a.getOffset()
                });
                b(G, function (b, f) {
                    h(c[f]) || (a[b] += d[f])
                });
                a.setChartSize()
            }, reflow: function (a) {
                var d = this, b = d.options.chart, c = d.renderTo, g = h(b.width), e = b.width || f(c, "width"), b = b.height || f(c, "height"), c = a ? a.target : O;
                if (!g && !d.isPrinting && e && b && (c === O || c === F)) {
                    if (e !== d.containerWidth ||
                        b !== d.containerHeight)clearTimeout(d.reflowTimeout), d.reflowTimeout = Q(function () {
                        d.container && d.setSize(void 0, void 0, !1)
                    }, a ? 100 : 0);
                    d.containerWidth = e;
                    d.containerHeight = b
                }
            }, initReflow: function () {
                var a = this, d;
                d = w(O, "resize", function (d) {
                    a.reflow(d)
                });
                w(a, "destroy", d)
            }, setSize: function (d, c, f) {
                var g = this, e = g.renderer;
                g.isResizing += 1;
                a.setAnimation(f, g);
                g.oldChartHeight = g.chartHeight;
                g.oldChartWidth = g.chartWidth;
                void 0 !== d && (g.options.chart.width = d);
                void 0 !== c && (g.options.chart.height = c);
                g.getChartSize();
                g.setChartSize(!0);
                e.setSize(g.chartWidth, g.chartHeight, f);
                b(g.axes, function (a) {
                    a.isDirty = !0;
                    a.setScale()
                });
                g.isDirtyLegend = !0;
                g.isDirtyBox = !0;
                g.layOutTitles();
                g.getMargins();
                g.setResponsive && g.setResponsive(!1);
                g.redraw(f);
                g.oldChartHeight = null;
                r(g, "resize");
                Q(function () {
                    g && r(g, "endResize", null, function () {
                        --g.isResizing
                    })
                }, B(void 0).duration)
            }, setChartSize: function (a) {
                var d = this.inverted, c = this.renderer, f = this.chartWidth, g = this.chartHeight, e = this.options.chart, h = this.spacing, k = this.clipOffset, p,
                    l, u, A;
                this.plotLeft = p = Math.round(this.plotLeft);
                this.plotTop = l = Math.round(this.plotTop);
                this.plotWidth = u = Math.max(0, Math.round(f - p - this.marginRight));
                this.plotHeight = A = Math.max(0, Math.round(g - l - this.marginBottom));
                this.plotSizeX = d ? A : u;
                this.plotSizeY = d ? u : A;
                this.plotBorderWidth = e.plotBorderWidth || 0;
                this.spacingBox = c.spacingBox = {x: h[3], y: h[0], width: f - h[3] - h[1], height: g - h[0] - h[2]};
                this.plotBox = c.plotBox = {x: p, y: l, width: u, height: A};
                f = 2 * Math.floor(this.plotBorderWidth / 2);
                d = Math.ceil(Math.max(f, k[3]) / 2);
                c = Math.ceil(Math.max(f, k[0]) / 2);
                this.clipBox = {
                    x: d,
                    y: c,
                    width: Math.floor(this.plotSizeX - Math.max(f, k[1]) / 2 - d),
                    height: Math.max(0, Math.floor(this.plotSizeY - Math.max(f, k[2]) / 2 - c))
                };
                a || b(this.axes, function (a) {
                    a.setAxisSize();
                    a.setAxisTranslation()
                })
            }, resetMargins: function () {
                var a = this, d = a.options.chart;
                b(["margin", "spacing"], function (c) {
                    var f = d[c], g = H(f) ? f : [f, f, f, f];
                    b(["Top", "Right", "Bottom", "Left"], function (b, f) {
                        a[c][f] = A(d[c + b], g[f])
                    })
                });
                b(G, function (d, b) {
                    a[d] = A(a.margin[b], a.spacing[b])
                });
                a.axisOffset =
                    [0, 0, 0, 0];
                a.clipOffset = [0, 0, 0, 0]
            }, drawChartBox: function () {
                var a = this.options.chart, d = this.renderer, b = this.chartWidth, c = this.chartHeight, f = this.chartBackground, g = this.plotBackground, e = this.plotBorder, h, k, p = this.plotLeft, l = this.plotTop, u = this.plotWidth, A = this.plotHeight, t = this.plotBox, n = this.clipRect, m = this.clipBox, r = "animate";
                f || (this.chartBackground = f = d.rect().addClass("highcharts-background").add(), r = "attr");
                h = k = f.strokeWidth();
                f[r]({x: k / 2, y: k / 2, width: b - k - h % 2, height: c - k - h % 2, r: a.borderRadius});
                r =
                    "animate";
                g || (r = "attr", this.plotBackground = g = d.rect().addClass("highcharts-plot-background").add());
                g[r](t);
                n ? n.animate({width: m.width, height: m.height}) : this.clipRect = d.clipRect(m);
                r = "animate";
                e || (r = "attr", this.plotBorder = e = d.rect().addClass("highcharts-plot-border").attr({zIndex: 1}).add());
                e[r](e.crisp({x: p, y: l, width: u, height: A}, -e.strokeWidth()));
                this.isDirtyBox = !1
            }, propFromSeries: function () {
                var a = this, d = a.options.chart, c, f = a.options.series, g, e;
                b(["inverted", "angular", "polar"], function (b) {
                    c = J[d.type ||
                    d.defaultSeriesType];
                    e = d[b] || c && c.prototype[b];
                    for (g = f && f.length; !e && g--;)(c = J[f[g].type]) && c.prototype[b] && (e = !0);
                    a[b] = e
                })
            }, linkSeries: function () {
                var a = this, d = a.series;
                b(d, function (a) {
                    a.linkedSeries.length = 0
                });
                b(d, function (d) {
                    var b = d.options.linkedTo;
                    t(b) && (b = ":previous" === b ? a.series[d.index - 1] : a.get(b)) && b.linkedParent !== d && (b.linkedSeries.push(d), d.linkedParent = b, d.visible = A(d.options.visible, b.options.visible, d.visible))
                })
            }, renderSeries: function () {
                b(this.series, function (a) {
                    a.translate();
                    a.render()
                })
            },
            renderLabels: function () {
                var a = this, d = a.options.labels;
                d.items && b(d.items, function (b) {
                    var c = C(d.style, b.style), f = u(c.left) + a.plotLeft, g = u(c.top) + a.plotTop + 12;
                    delete c.left;
                    delete c.top;
                    a.renderer.text(b.html, f, g).attr({zIndex: 2}).css(c).add()
                })
            }, render: function () {
                var a = this.axes, d = this.renderer, c = this.options, f, g, e;
                this.setTitle();
                this.legend = new E(this, c.legend);
                this.getStacks && this.getStacks();
                this.getMargins(!0);
                this.setChartSize();
                c = this.plotWidth;
                f = this.plotHeight -= 21;
                b(a, function (a) {
                    a.setScale()
                });
                this.getAxisMargins();
                g = 1.1 < c / this.plotWidth;
                e = 1.05 < f / this.plotHeight;
                if (g || e)b(a, function (a) {
                    (a.horiz && g || !a.horiz && e) && a.setTickInterval(!0)
                }), this.getMargins();
                this.drawChartBox();
                this.hasCartesianSeries && b(a, function (a) {
                    a.visible && a.render()
                });
                this.seriesGroup || (this.seriesGroup = d.g("series-group").attr({zIndex: 3}).add());
                this.renderSeries();
                this.renderLabels();
                this.addCredits();
                this.setResponsive && this.setResponsive();
                this.hasRendered = !0
            }, addCredits: function (a) {
                var d = this;
                a = z(!0, this.options.credits,
                    a);
                a.enabled && !this.credits && (this.credits = this.renderer.text(a.text + (this.mapCredits || ""), 0, 0).addClass("highcharts-credits").on("click", function () {
                    a.href && (O.location.href = a.href)
                }).attr({
                    align: a.position.align,
                    zIndex: 8
                }).add().align(a.position), this.credits.update = function (a) {
                    d.credits = d.credits.destroy();
                    d.addCredits(a)
                })
            }, destroy: function () {
                var d = this, c = d.axes, f = d.series, g = d.container, e, h = g && g.parentNode;
                r(d, "destroy");
                n[d.index] = void 0;
                a.chartCount--;
                d.renderTo.removeAttribute("data-highcharts-chart");
                I(d);
                for (e = c.length; e--;)c[e] = c[e].destroy();
                this.scroller && this.scroller.destroy && this.scroller.destroy();
                for (e = f.length; e--;)f[e] = f[e].destroy();
                b("title subtitle chartBackground plotBackground plotBGImage plotBorder seriesGroup clipRect credits pointer rangeSelector legend resetZoomButton tooltip renderer".split(" "), function (a) {
                    var b = d[a];
                    b && b.destroy && (d[a] = b.destroy())
                });
                g && (g.innerHTML = "", I(g), h && m(g));
                for (e in d)delete d[e]
            }, isReadyToRender: function () {
                var a = this;
                return v || O != O.top || "complete" ===
                F.readyState ? !0 : (F.attachEvent("onreadystatechange", function () {
                    F.detachEvent("onreadystatechange", a.firstRender);
                    "complete" === F.readyState && a.firstRender()
                }), !1)
            }, firstRender: function () {
                var a = this, c = a.options;
                if (a.isReadyToRender()) {
                    a.getContainer();
                    r(a, "init");
                    a.resetMargins();
                    a.setChartSize();
                    a.propFromSeries();
                    a.getAxes();
                    b(c.series || [], function (d) {
                        a.initSeries(d)
                    });
                    a.linkSeries();
                    r(a, "beforeRender");
                    d && (a.pointer = new d(a, c));
                    a.render();
                    a.renderer.draw();
                    if (!a.renderer.imgCount && a.onload)a.onload();
                    a.cloneRenderTo(!0)
                }
            }, onload: function () {
                b([this.callback].concat(this.callbacks), function (a) {
                    a && void 0 !== this.index && a.apply(this, [this])
                }, this);
                r(this, "load");
                h(this.index) && !1 !== this.options.chart.reflow && this.initReflow();
                this.onload = null
            }
        }
    })(K);
    (function (a) {
        var w, B = a.each, D = a.extend, F = a.erase, q = a.fireEvent, e = a.format, c = a.isArray, m = a.isNumber, n = a.pick, l = a.removeEvent;
        w = a.Point = function () {
        };
        w.prototype = {
            init: function (a, b, c) {
                var e = a.chart.options.chart.colorCount;
                this.series = a;
                this.applyOptions(b,
                    c);
                a.options.colorByPoint ? (b = a.colorCounter, a.colorCounter++, a.colorCounter === e && (a.colorCounter = 0)) : b = a.colorIndex;
                this.colorIndex = n(this.colorIndex, b);
                a.chart.pointCount++;
                return this
            }, applyOptions: function (a, b) {
                var c = this.series, e = c.options.pointValKey || c.pointValKey;
                a = w.prototype.optionsToObject.call(this, a);
                D(this, a);
                this.options = this.options ? D(this.options, a) : a;
                a.group && delete this.group;
                e && (this.y = this[e]);
                this.isNull = n(this.isValid && !this.isValid(), null === this.x || !m(this.y, !0));
                this.selected &&
                (this.state = "select");
                "name"in this && void 0 === b && c.xAxis && c.xAxis.hasNames && (this.x = c.xAxis.nameToX(this));
                void 0 === this.x && c && (this.x = void 0 === b ? c.autoIncrement(this) : b);
                return this
            }, optionsToObject: function (a) {
                var b = {}, e = this.series, h = e.options.keys, l = h || e.pointArrayMap || ["y"], f = l.length, k = 0, p = 0;
                if (m(a) || null === a)b[l[0]] = a; else if (c(a))for (!h && a.length > f && (e = typeof a[0], "string" === e ? b.name = a[0] : "number" === e && (b.x = a[0]), k++); p < f;)h && void 0 === a[k] || (b[l[p]] = a[k]), k++, p++; else"object" === typeof a &&
                (b = a, a.dataLabels && (e._hasPointLabels = !0), a.marker && (e._hasPointMarkers = !0));
                return b
            }, getClassName: function () {
                return "highcharts-point" + (this.selected ? " highcharts-point-select" : "") + (this.negative ? " highcharts-negative" : "") + (this.isNull ? " highcharts-null-point" : "") + (void 0 !== this.colorIndex ? " highcharts-color-" + this.colorIndex : "") + (this.options.className ? " " + this.options.className : "") + (this.zone && this.zone.className ? " " + this.zone.className : "")
            }, getZone: function () {
                var a = this.series, b = a.zones, a = a.zoneAxis ||
                    "y", c = 0, e;
                for (e = b[c]; this[a] >= e.value;)e = b[++c];
                e && e.color && !this.options.color && (this.color = e.color);
                return e
            }, destroy: function () {
                var a = this.series.chart, b = a.hoverPoints, c;
                a.pointCount--;
                b && (this.setState(), F(b, this), b.length || (a.hoverPoints = null));
                if (this === a.hoverPoint)this.onMouseOut();
                if (this.graphic || this.dataLabel)l(this), this.destroyElements();
                this.legendItem && a.legend.destroyItem(this);
                for (c in this)this[c] = null
            }, destroyElements: function () {
                for (var a = ["graphic", "dataLabel", "dataLabelUpper",
                    "connector", "shadowGroup"], b, c = 6; c--;)b = a[c], this[b] && (this[b] = this[b].destroy())
            }, getLabelConfig: function () {
                return {
                    x: this.category,
                    y: this.y,
                    color: this.color,
                    key: this.name || this.category,
                    series: this.series,
                    point: this,
                    percentage: this.percentage,
                    total: this.total || this.stackTotal
                }
            }, tooltipFormatter: function (a) {
                var b = this.series, c = b.tooltipOptions, h = n(c.valueDecimals, ""), l = c.valuePrefix || "", f = c.valueSuffix || "";
                B(b.pointArrayMap || ["y"], function (b) {
                    b = "{point." + b;
                    if (l || f)a = a.replace(b + "}", l + b + "}" + f);
                    a = a.replace(b +
                        "}", b + ":,." + h + "f}")
                });
                return e(a, {point: this, series: this.series})
            }, firePointEvent: function (a, b, c) {
                var e = this, h = this.series.options;
                (h.point.events[a] || e.options && e.options.events && e.options.events[a]) && this.importEvents();
                "click" === a && h.allowPointSelect && (c = function (a) {
                    e.select && e.select(null, a.ctrlKey || a.metaKey || a.shiftKey)
                });
                q(this, a, b, c)
            }, visible: !0
        }
    })(K);
    (function (a) {
        var w = a.addEvent, B = a.animObject, D = a.arrayMax, F = a.arrayMin, q = a.correctFloat, e = a.Date, c = a.defaultOptions, m = a.defined, n = a.each, l =
            a.erase, h = a.extend, b = a.fireEvent, C = a.grep, y = a.isArray, r = a.isNumber, f = a.isString, k = a.merge, p = a.pick, H = a.removeEvent, t = a.splat, E = a.SVGElement, G = a.syncTimeout, z = a.win;
        a.Series = a.seriesType("line", null, {
            allowPointSelect: !1,
            showCheckbox: !1,
            animation: {duration: 1E3},
            events: {},
            marker: {radius: 4, states: {hover: {animation: {duration: 50}, enabled: !0, radiusPlus: 2}}},
            point: {events: {}},
            dataLabels: {
                align: "center", formatter: function () {
                    return null === this.y ? "" : a.numberFormat(this.y, -1)
                }, verticalAlign: "bottom", x: 0, y: 0,
                padding: 5
            },
            cropThreshold: 300,
            pointRange: 0,
            softThreshold: !0,
            states: {hover: {lineWidthPlus: 1, marker: {}, halo: {size: 10}}, select: {marker: {}}},
            stickyTracking: !0,
            turboThreshold: 1E3
        }, {
            isCartesian: !0,
            pointClass: a.Point,
            sorted: !0,
            requireSorting: !0,
            directTouch: !1,
            axisTypes: ["xAxis", "yAxis"],
            colorCounter: 0,
            parallelArrays: ["x", "y"],
            coll: "series",
            init: function (a, b) {
                var d = this, c, f, g = a.series, e;
                d.chart = a;
                d.options = b = d.setOptions(b);
                d.linkedSeries = [];
                d.bindAxes();
                h(d, {
                    name: b.name, state: "", visible: !1 !== b.visible, selected: !0 ===
                    b.selected
                });
                f = b.events;
                for (c in f)w(d, c, f[c]);
                if (f && f.click || b.point && b.point.events && b.point.events.click || b.allowPointSelect)a.runTrackerClick = !0;
                d.getColor();
                d.getSymbol();
                n(d.parallelArrays, function (a) {
                    d[a + "Data"] = []
                });
                d.setData(b.data, !1);
                d.isCartesian && (a.hasCartesianSeries = !0);
                g.length && (e = g[g.length - 1]);
                d._i = p(e && e._i, -1) + 1;
                for (a = this.insert(g); a < g.length; a++)g[a].index = a, g[a].name = g[a].name || "Series " + (g[a].index + 1)
            },
            insert: function (a) {
                var d = this.options.index, b;
                if (r(d)) {
                    for (b = a.length; b--;)if (d >=
                        p(a[b].options.index, a[b]._i)) {
                        a.splice(b + 1, 0, this);
                        break
                    }
                    -1 === b && a.unshift(this);
                    b += 1
                } else a.push(this);
                return p(b, a.length - 1)
            },
            bindAxes: function () {
                var d = this, b = d.options, c = d.chart, f;
                n(d.axisTypes || [], function (e) {
                    n(c[e], function (a) {
                        f = a.options;
                        if (b[e] === f.index || void 0 !== b[e] && b[e] === f.id || void 0 === b[e] && 0 === f.index)d.insert(a.series), d[e] = a, a.isDirty = !0
                    });
                    d[e] || d.optionalAxis === e || a.error(18, !0)
                })
            },
            updateParallelArrays: function (a, b) {
                var d = a.series, c = arguments, f = r(b) ? function (c) {
                    var f = "y" === c && d.toYData ?
                        d.toYData(a) : a[c];
                    d[c + "Data"][b] = f
                } : function (a) {
                    Array.prototype[b].apply(d[a + "Data"], Array.prototype.slice.call(c, 2))
                };
                n(d.parallelArrays, f)
            },
            autoIncrement: function () {
                var a = this.options, b = this.xIncrement, c, f = a.pointIntervalUnit, b = p(b, a.pointStart, 0);
                this.pointInterval = c = p(this.pointInterval, a.pointInterval, 1);
                f && (a = new e(b), "day" === f ? a = +a[e.hcSetDate](a[e.hcGetDate]() + c) : "month" === f ? a = +a[e.hcSetMonth](a[e.hcGetMonth]() + c) : "year" === f && (a = +a[e.hcSetFullYear](a[e.hcGetFullYear]() + c)), c = a - b);
                this.xIncrement =
                    b + c;
                return b
            },
            setOptions: function (a) {
                var d = this.chart, b = d.options.plotOptions, d = d.userOptions || {}, f = d.plotOptions || {}, e = b[this.type];
                this.userOptions = a;
                b = k(e, b.series, a);
                this.tooltipOptions = k(c.tooltip, c.plotOptions[this.type].tooltip, d.tooltip, f.series && f.series.tooltip, f[this.type] && f[this.type].tooltip, a.tooltip);
                null === e.marker && delete b.marker;
                this.zoneAxis = b.zoneAxis;
                a = this.zones = (b.zones || []).slice();
                !b.negativeColor && !b.negativeFillColor || b.zones || a.push({
                    value: b[this.zoneAxis + "Threshold"] ||
                    b.threshold || 0, className: "highcharts-negative"
                });
                a.length && m(a[a.length - 1].value) && a.push({});
                return b
            },
            getCyclic: function (a, b, c) {
                var d, f = this.userOptions, g = a + "Index", e = a + "Counter", h = c ? c.length : p(this.chart.options.chart[a + "Count"], this.chart[a + "Count"]);
                b || (d = p(f[g], f["_" + g]), m(d) || (f["_" + g] = d = this.chart[e] % h, this.chart[e] += 1), c && (b = c[d]));
                void 0 !== d && (this[g] = d);
                this[a] = b
            },
            getColor: function () {
                this.getCyclic("color")
            },
            getSymbol: function () {
                this.getCyclic("symbol", this.options.marker.symbol, this.chart.options.symbols)
            },
            drawLegendSymbol: a.LegendSymbolMixin.drawLineMarker,
            setData: function (d, b, c, e) {
                var h = this, g = h.points, k = g && g.length || 0, l, t = h.options, m = h.chart, u = null, A = h.xAxis, q = t.turboThreshold, E = this.xData, z = this.yData, G = (l = h.pointArrayMap) && l.length;
                d = d || [];
                l = d.length;
                b = p(b, !0);
                if (!1 !== e && l && k === l && !h.cropped && !h.hasGroupedData && h.visible)n(d, function (a, d) {
                    g[d].update && a !== t.data[d] && g[d].update(a, !1, null, !1)
                }); else {
                    h.xIncrement = null;
                    h.colorCounter = 0;
                    n(this.parallelArrays, function (a) {
                        h[a + "Data"].length = 0
                    });
                    if (q &&
                        l > q) {
                        for (c = 0; null === u && c < l;)u = d[c], c++;
                        if (r(u))for (c = 0; c < l; c++)E[c] = this.autoIncrement(), z[c] = d[c]; else if (y(u))if (G)for (c = 0; c < l; c++)u = d[c], E[c] = u[0], z[c] = u.slice(1, G + 1); else for (c = 0; c < l; c++)u = d[c], E[c] = u[0], z[c] = u[1]; else a.error(12)
                    } else for (c = 0; c < l; c++)void 0 !== d[c] && (u = {series: h}, h.pointClass.prototype.applyOptions.apply(u, [d[c]]), h.updateParallelArrays(u, c));
                    f(z[0]) && a.error(14, !0);
                    h.data = [];
                    h.options.data = h.userOptions.data = d;
                    for (c = k; c--;)g[c] && g[c].destroy && g[c].destroy();
                    A && (A.minRange = A.userMinRange);
                    h.isDirty = m.isDirtyBox = !0;
                    h.isDirtyData = !!g;
                    c = !1
                }
                "point" === t.legendType && (this.processData(), this.generatePoints());
                b && m.redraw(c)
            },
            processData: function (d) {
                var b = this.xData, c = this.yData, f = b.length, e;
                e = 0;
                var g, h, k = this.xAxis, l, p = this.options;
                l = p.cropThreshold;
                var t = this.getExtremesFromAll || p.getExtremesFromAll, m = this.isCartesian, p = k && k.val2lin, n = k && k.isLog, r, q;
                if (m && !this.isDirty && !k.isDirty && !this.yAxis.isDirty && !d)return !1;
                k && (d = k.getExtremes(), r = d.min, q = d.max);
                if (m && this.sorted && !t && (!l || f > l || this.forceCrop))if (b[f -
                    1] < r || b[0] > q)b = [], c = []; else if (b[0] < r || b[f - 1] > q)e = this.cropData(this.xData, this.yData, r, q), b = e.xData, c = e.yData, e = e.start, g = !0;
                for (l = b.length || 1; --l;)f = n ? p(b[l]) - p(b[l - 1]) : b[l] - b[l - 1], 0 < f && (void 0 === h || f < h) ? h = f : 0 > f && this.requireSorting && a.error(15);
                this.cropped = g;
                this.cropStart = e;
                this.processedXData = b;
                this.processedYData = c;
                this.closestPointRange = h
            },
            cropData: function (a, b, c, f) {
                var d = a.length, e = 0, h = d, k = p(this.cropShoulder, 1), l;
                for (l = 0; l < d; l++)if (a[l] >= c) {
                    e = Math.max(0, l - k);
                    break
                }
                for (c = l; c < d; c++)if (a[c] >
                    f) {
                    h = c + k;
                    break
                }
                return {xData: a.slice(e, h), yData: b.slice(e, h), start: e, end: h}
            },
            generatePoints: function () {
                var a = this.options.data, b = this.data, c, f = this.processedXData, e = this.processedYData, g = this.pointClass, h = f.length, l = this.cropStart || 0, k, p = this.hasGroupedData, m, n = [], r;
                b || p || (b = [], b.length = a.length, b = this.data = b);
                for (r = 0; r < h; r++)k = l + r, p ? (m = (new g).init(this, [f[r]].concat(t(e[r]))), m.dataGroup = this.groupMap[r]) : (m = b[k]) || void 0 === a[k] || (b[k] = m = (new g).init(this, a[k], f[r])), m.index = k, n[r] = m;
                if (b && (h !==
                    (c = b.length) || p))for (r = 0; r < c; r++)r !== l || p || (r += h), b[r] && (b[r].destroyElements(), b[r].plotX = void 0);
                this.data = b;
                this.points = n
            },
            getExtremes: function (a) {
                var d = this.yAxis, b = this.processedXData, c, f = [], e = 0;
                c = this.xAxis.getExtremes();
                var h = c.min, k = c.max, l, p, t, m;
                a = a || this.stackedYData || this.processedYData || [];
                c = a.length;
                for (m = 0; m < c; m++)if (p = b[m], t = a[m], l = (r(t, !0) || y(t)) && (!d.isLog || t.length || 0 < t), p = this.getExtremesFromAll || this.options.getExtremesFromAll || this.cropped || (b[m + 1] || p) >= h && (b[m - 1] || p) <= k, l && p)if (l =
                        t.length)for (; l--;)null !== t[l] && (f[e++] = t[l]); else f[e++] = t;
                this.dataMin = F(f);
                this.dataMax = D(f)
            },
            translate: function () {
                this.processedXData || this.processData();
                this.generatePoints();
                var a = this.options, b = a.stacking, c = this.xAxis, f = c.categories, e = this.yAxis, g = this.points, h = g.length, l = !!this.modifyValue, k = a.pointPlacement, t = "between" === k || r(k), n = a.threshold, E = a.startFromThreshold ? n : 0, z, G, y, C, H = Number.MAX_VALUE;
                "between" === k && (k = .5);
                r(k) && (k *= p(a.pointRange || c.pointRange));
                for (a = 0; a < h; a++) {
                    var w = g[a], B =
                        w.x, D = w.y;
                    G = w.low;
                    var F = b && e.stacks[(this.negStacks && D < (E ? 0 : n) ? "-" : "") + this.stackKey], K;
                    e.isLog && null !== D && 0 >= D && (w.isNull = !0);
                    w.plotX = z = q(Math.min(Math.max(-1E5, c.translate(B, 0, 0, 0, 1, k, "flags" === this.type)), 1E5));
                    b && this.visible && !w.isNull && F && F[B] && (C = this.getStackIndicator(C, B, this.index), K = F[B], D = K.points[C.key], G = D[0], D = D[1], G === E && C.key === F[B].base && (G = p(n, e.min)), e.isLog && 0 >= G && (G = null), w.total = w.stackTotal = K.total, w.percentage = K.total && w.y / K.total * 100, w.stackY = D, K.setOffset(this.pointXOffset ||
                        0, this.barW || 0));
                    w.yBottom = m(G) ? e.translate(G, 0, 1, 0, 1) : null;
                    l && (D = this.modifyValue(D, w));
                    w.plotY = G = "number" === typeof D && Infinity !== D ? Math.min(Math.max(-1E5, e.translate(D, 0, 1, 0, 1)), 1E5) : void 0;
                    w.isInside = void 0 !== G && 0 <= G && G <= e.len && 0 <= z && z <= c.len;
                    w.clientX = t ? q(c.translate(B, 0, 0, 0, 1, k)) : z;
                    w.negative = w.y < (n || 0);
                    w.category = f && void 0 !== f[w.x] ? f[w.x] : w.x;
                    w.isNull || (void 0 !== y && (H = Math.min(H, Math.abs(z - y))), y = z);
                    w.zone = this.zones.length && w.getZone()
                }
                this.closestPointRangePx = H
            },
            getValidPoints: function (a,
                                      b) {
                var d = this.chart;
                return C(a || this.points || [], function (a) {
                    return b && !d.isInsidePlot(a.plotX, a.plotY, d.inverted) ? !1 : !a.isNull
                })
            },
            setClip: function (a) {
                var d = this.chart, b = this.options, c = d.renderer, f = d.inverted, e = this.clipBox, h = e || d.clipBox, k = this.sharedClipKey || ["_sharedClip", a && a.duration, a && a.easing, h.height, b.xAxis, b.yAxis].join(), l = d[k], p = d[k + "m"];
                l || (a && (h.width = 0, d[k + "m"] = p = c.clipRect(-99, f ? -d.plotLeft : -d.plotTop, 99, f ? d.chartWidth : d.chartHeight)), d[k] = l = c.clipRect(h), l.count = {length: 0});
                a && !l.count[this.index] &&
                (l.count[this.index] = !0, l.count.length += 1);
                !1 !== b.clip && (this.group.clip(a || e ? l : d.clipRect), this.markerGroup.clip(p), this.sharedClipKey = k);
                a || (l.count[this.index] && (delete l.count[this.index], --l.count.length), 0 === l.count.length && k && d[k] && (e || (d[k] = d[k].destroy()), d[k + "m"] && (d[k + "m"] = d[k + "m"].destroy())))
            },
            animate: function (a) {
                var d = this.chart, b = B(this.options.animation), c;
                a ? this.setClip(b) : (c = this.sharedClipKey, (a = d[c]) && a.animate({width: d.plotSizeX}, b), d[c + "m"] && d[c + "m"].animate({
                    width: d.plotSizeX +
                    99
                }, b), this.animate = null)
            },
            afterAnimate: function () {
                this.setClip();
                b(this, "afterAnimate")
            },
            drawPoints: function () {
                var a = this.points, b = this.chart, c, f, e, g, h = this.options.marker, k, l, t, m, n = this.markerGroup, q = p(h.enabled, this.xAxis.isRadial ? !0 : null, this.closestPointRangePx > 2 * h.radius);
                if (!1 !== h.enabled || this._hasPointMarkers)for (f = a.length; f--;)e = a[f], c = e.plotY, g = e.graphic, k = e.marker || {}, l = !!e.marker, t = q && void 0 === k.enabled || k.enabled, m = e.isInside, t && r(c) && null !== e.y ? (c = p(k.symbol, this.symbol), e.hasImage =
                    0 === c.indexOf("url"), t = this.markerAttribs(e, e.selected && "select"), g ? g[m ? "show" : "hide"](!0).animate(t) : m && (0 < t.width || e.hasImage) && (e.graphic = g = b.renderer.symbol(c, t.x, t.y, t.width, t.height, l ? k : h).add(n)), g && g.addClass(e.getClassName(), !0)) : g && (e.graphic = g.destroy())
            },
            markerAttribs: function (a, b) {
                var d = this.options.marker, c = a && a.options, f = c && c.marker || {}, c = p(f.radius, d.radius);
                b && (d = d.states[b], b = f.states && f.states[b], c = p(b && b.radius, d && d.radius, c + (d && d.radiusPlus || 0)));
                a.hasImage && (c = 0);
                a = {
                    x: Math.floor(a.plotX) -
                    c, y: a.plotY - c
                };
                c && (a.width = a.height = 2 * c);
                return a
            },
            destroy: function () {
                var a = this, c = a.chart, f = /AppleWebKit\/533/.test(z.navigator.userAgent), e, h = a.data || [], g, k, p;
                b(a, "destroy");
                H(a);
                n(a.axisTypes || [], function (b) {
                    (p = a[b]) && p.series && (l(p.series, a), p.isDirty = p.forceRedraw = !0)
                });
                a.legendItem && a.chart.legend.destroyItem(a);
                for (e = h.length; e--;)(g = h[e]) && g.destroy && g.destroy();
                a.points = null;
                clearTimeout(a.animationTimeout);
                for (k in a)a[k]instanceof E && !a[k].survive && (e = f && "group" === k ? "hide" : "destroy", a[k][e]());
                c.hoverSeries === a && (c.hoverSeries = null);
                l(c.series, a);
                for (k in a)delete a[k]
            },
            getGraphPath: function (a, b, c) {
                var d = this, f = d.options, e = f.step, h, k = [], l = [], p;
                a = a || d.points;
                (h = a.reversed) && a.reverse();
                (e = {right: 1, center: 2}[e] || e && 3) && h && (e = 4 - e);
                !f.connectNulls || b || c || (a = this.getValidPoints(a));
                n(a, function (g, h) {
                    var t = g.plotX, n = g.plotY, r = a[h - 1];
                    (g.leftCliff || r && r.rightCliff) && !c && (p = !0);
                    g.isNull && !m(b) && 0 < h ? p = !f.connectNulls : g.isNull && !b ? p = !0 : (0 === h || p ? h = ["M", g.plotX, g.plotY] : d.getPointSpline ? h = d.getPointSpline(a,
                        g, h) : e ? (h = 1 === e ? ["L", r.plotX, n] : 2 === e ? ["L", (r.plotX + t) / 2, r.plotY, "L", (r.plotX + t) / 2, n] : ["L", t, r.plotY], h.push("L", t, n)) : h = ["L", t, n], l.push(g.x), e && l.push(g.x), k.push.apply(k, h), p = !1)
                });
                k.xMap = l;
                return d.graphPath = k
            },
            drawGraph: function () {
                var a = this, b = (this.gappedPath || this.getGraphPath).call(this), c = [["graph", "highcharts-graph"]];
                n(this.zones, function (a, b) {
                    c.push(["zone-graph-" + b, "highcharts-graph highcharts-zone-graph-" + b + " " + (a.className || "")])
                });
                n(c, function (d, c) {
                    c = d[0];
                    var f = a[c];
                    f ? (f.endX = b.xMap,
                        f.animate({d: b})) : b.length && (a[c] = a.chart.renderer.path(b).addClass(d[1]).attr({zIndex: 1}).add(a.group));
                    f && (f.startX = b.xMap, f.isArea = b.isArea)
                })
            },
            applyZones: function () {
                var a = this, b = this.chart, c = b.renderer, f = this.zones, e, g, h = this.clips || [], k, l = this.graph, t = this.area, m = Math.max(b.chartWidth, b.chartHeight), r = this[(this.zoneAxis || "y") + "Axis"], q, E, G = b.inverted, z, y, C, w, H = !1;
                f.length && (l || t) && r && void 0 !== r.min && (E = r.reversed, z = r.horiz, l && l.hide(), t && t.hide(), q = r.getExtremes(), n(f, function (d, f) {
                    e = E ? z ? b.plotWidth :
                        0 : z ? 0 : r.toPixels(q.min);
                    e = Math.min(Math.max(p(g, e), 0), m);
                    g = Math.min(Math.max(Math.round(r.toPixels(p(d.value, q.max), !0)), 0), m);
                    H && (e = g = r.toPixels(q.max));
                    y = Math.abs(e - g);
                    C = Math.min(e, g);
                    w = Math.max(e, g);
                    r.isXAxis ? (k = {
                        x: G ? w : C,
                        y: 0,
                        width: y,
                        height: m
                    }, z || (k.x = b.plotHeight - k.x)) : (k = {
                        x: 0,
                        y: G ? w : C,
                        width: m,
                        height: y
                    }, z && (k.y = b.plotWidth - k.y));
                    h[f] ? h[f].animate(k) : (h[f] = c.clipRect(k), l && a["zone-graph-" + f].clip(h[f]), t && a["zone-area-" + f].clip(h[f]));
                    H = d.value > q.max
                }), this.clips = h)
            },
            invertGroups: function (a) {
                function b() {
                    var b =
                    {width: d.yAxis.len, height: d.xAxis.len};
                    n(["group", "markerGroup"], function (c) {
                        d[c] && d[c].attr(b).invert(a)
                    })
                }

                var d = this, c;
                d.xAxis && (c = w(d.chart, "resize", b), w(d, "destroy", c), b(a), d.invertGroups = b)
            },
            plotGroup: function (a, b, c, f, e) {
                var d = this[a], h = !d;
                h && (this[a] = d = this.chart.renderer.g(b).attr({zIndex: f || .1}).add(e), d.addClass("highcharts-series-" + this.index + " highcharts-" + this.type + "-series highcharts-color-" + this.colorIndex + " " + (this.options.className || "")));
                d.attr({visibility: c})[h ? "attr" : "animate"](this.getPlotBox());
                return d
            },
            getPlotBox: function () {
                var a = this.chart, b = this.xAxis, c = this.yAxis;
                a.inverted && (b = c, c = this.xAxis);
                return {translateX: b ? b.left : a.plotLeft, translateY: c ? c.top : a.plotTop, scaleX: 1, scaleY: 1}
            },
            render: function () {
                var a = this, b = a.chart, c, f = a.options, e = !!a.animate && b.renderer.isSVG && B(f.animation).duration, g = a.visible ? "inherit" : "hidden", h = f.zIndex, k = a.hasRendered, l = b.seriesGroup, p = b.inverted;
                c = a.plotGroup("group", "series", g, h, l);
                a.markerGroup = a.plotGroup("markerGroup", "markers", g, h, l);
                e && a.animate(!0);
                c.inverted = a.isCartesian ? p : !1;
                a.drawGraph && (a.drawGraph(), a.applyZones());
                a.drawDataLabels && a.drawDataLabels();
                a.visible && a.drawPoints();
                a.drawTracker && !1 !== a.options.enableMouseTracking && a.drawTracker();
                a.invertGroups(p);
                !1 === f.clip || a.sharedClipKey || k || c.clip(b.clipRect);
                e && a.animate();
                k || (a.animationTimeout = G(function () {
                    a.afterAnimate()
                }, e));
                a.isDirty = a.isDirtyData = !1;
                a.hasRendered = !0
            },
            redraw: function () {
                var a = this.chart, b = this.isDirty || this.isDirtyData, c = this.group, f = this.xAxis, e = this.yAxis;
                c &&
                (a.inverted && c.attr({
                    width: a.plotWidth,
                    height: a.plotHeight
                }), c.animate({translateX: p(f && f.left, a.plotLeft), translateY: p(e && e.top, a.plotTop)}));
                this.translate();
                this.render();
                b && delete this.kdTree
            },
            kdDimensions: 1,
            kdAxisArray: ["clientX", "plotY"],
            searchPoint: function (a, b) {
                var d = this.xAxis, c = this.yAxis, f = this.chart.inverted;
                return this.searchKDTree({
                    clientX: f ? d.len - a.chartY + d.pos : a.chartX - d.pos,
                    plotY: f ? c.len - a.chartX + c.pos : a.chartY - c.pos
                }, b)
            },
            buildKDTree: function () {
                function a(d, c, f) {
                    var e, g;
                    if (g = d && d.length)return e =
                        b.kdAxisArray[c % f], d.sort(function (a, b) {
                        return a[e] - b[e]
                    }), g = Math.floor(g / 2), {
                        point: d[g],
                        left: a(d.slice(0, g), c + 1, f),
                        right: a(d.slice(g + 1), c + 1, f)
                    }
                }

                var b = this, c = b.kdDimensions;
                delete b.kdTree;
                G(function () {
                    b.kdTree = a(b.getValidPoints(null, !b.directTouch), c, c)
                }, b.options.kdNow ? 0 : 1)
            },
            searchKDTree: function (a, b) {
                function d(a, b, g, k) {
                    var l = b.point, p = c.kdAxisArray[g % k], t, r, n = l;
                    r = m(a[f]) && m(l[f]) ? Math.pow(a[f] - l[f], 2) : null;
                    t = m(a[e]) && m(l[e]) ? Math.pow(a[e] - l[e], 2) : null;
                    t = (r || 0) + (t || 0);
                    l.dist = m(t) ? Math.sqrt(t) :
                        Number.MAX_VALUE;
                    l.distX = m(r) ? Math.sqrt(r) : Number.MAX_VALUE;
                    p = a[p] - l[p];
                    t = 0 > p ? "left" : "right";
                    r = 0 > p ? "right" : "left";
                    b[t] && (t = d(a, b[t], g + 1, k), n = t[h] < n[h] ? t : l);
                    b[r] && Math.sqrt(p * p) < n[h] && (a = d(a, b[r], g + 1, k), n = a[h] < n[h] ? a : n);
                    return n
                }

                var c = this, f = this.kdAxisArray[0], e = this.kdAxisArray[1], h = b ? "distX" : "dist";
                this.kdTree || this.buildKDTree();
                if (this.kdTree)return d(a, this.kdTree, this.kdDimensions, this.kdDimensions)
            }
        })
    })(K);
    (function (a) {
        function w(a, c, b, e, m) {
            var h = a.chart.inverted;
            this.axis = a;
            this.isNegative =
                b;
            this.options = c;
            this.x = e;
            this.total = null;
            this.points = {};
            this.stack = m;
            this.rightCliff = this.leftCliff = 0;
            this.alignOptions = {
                align: c.align || (h ? b ? "left" : "right" : "center"),
                verticalAlign: c.verticalAlign || (h ? "middle" : b ? "bottom" : "top"),
                y: n(c.y, h ? 4 : b ? 14 : -6),
                x: n(c.x, h ? b ? -6 : 6 : 0)
            };
            this.textAlign = c.textAlign || (h ? b ? "right" : "left" : "center")
        }

        var B = a.Axis, D = a.Chart, F = a.correctFloat, q = a.defined, e = a.destroyObjectProperties, c = a.each, m = a.format, n = a.pick;
        a = a.Series;
        w.prototype = {
            destroy: function () {
                e(this, this.axis)
            }, render: function (a) {
                var c =
                    this.options, b = c.format, b = b ? m(b, this) : c.formatter.call(this);
                this.label ? this.label.attr({
                    text: b,
                    visibility: "hidden"
                }) : this.label = this.axis.chart.renderer.text(b, null, null, c.useHTML).css(c.style).attr({
                    align: this.textAlign,
                    rotation: c.rotation,
                    visibility: "hidden"
                }).add(a)
            }, setOffset: function (a, c) {
                var b = this.axis, e = b.chart, h = e.inverted, l = b.reversed, l = this.isNegative && !l || !this.isNegative && l, f = b.translate(b.usePercentage ? 100 : this.total, 0, 0, 0, 1), b = b.translate(0), b = Math.abs(f - b);
                a = e.xAxis[0].translate(this.x) +
                    a;
                var k = e.plotHeight, h = {
                    x: h ? l ? f : f - b : a,
                    y: h ? k - a - c : l ? k - f - b : k - f,
                    width: h ? b : c,
                    height: h ? c : b
                };
                if (c = this.label)c.align(this.alignOptions, null, h), h = c.alignAttr, c[!1 === this.options.crop || e.isInsidePlot(h.x, h.y) ? "show" : "hide"](!0)
            }
        };
        D.prototype.getStacks = function () {
            var a = this;
            c(a.yAxis, function (a) {
                a.stacks && a.hasVisibleSeries && (a.oldStacks = a.stacks)
            });
            c(a.series, function (c) {
                !c.options.stacking || !0 !== c.visible && !1 !== a.options.chart.ignoreHiddenSeries || (c.stackKey = c.type + n(c.options.stack, ""))
            })
        };
        B.prototype.buildStacks =
            function () {
                var a = this.series, c, b = n(this.options.reversedStacks, !0), e = a.length, m;
                if (!this.isXAxis) {
                    this.usePercentage = !1;
                    for (m = e; m--;)a[b ? m : e - m - 1].setStackedPoints();
                    for (m = e; m--;)c = a[b ? m : e - m - 1], c.setStackCliffs && c.setStackCliffs();
                    if (this.usePercentage)for (m = 0; m < e; m++)a[m].setPercentStacks()
                }
            };
        B.prototype.renderStackTotals = function () {
            var a = this.chart, c = a.renderer, b = this.stacks, e, m, n = this.stackTotalGroup;
            n || (this.stackTotalGroup = n = c.g("stack-labels").attr({visibility: "visible", zIndex: 6}).add());
            n.translate(a.plotLeft,
                a.plotTop);
            for (e in b)for (m in a = b[e], a)a[m].render(n)
        };
        B.prototype.resetStacks = function () {
            var a = this.stacks, c, b;
            if (!this.isXAxis)for (c in a)for (b in a[c])a[c][b].touched < this.stacksTouched ? (a[c][b].destroy(), delete a[c][b]) : (a[c][b].total = null, a[c][b].cum = null)
        };
        B.prototype.cleanStacks = function () {
            var a, c, b;
            if (!this.isXAxis)for (c in this.oldStacks && (a = this.stacks = this.oldStacks), a)for (b in a[c])a[c][b].cum = a[c][b].total
        };
        a.prototype.setStackedPoints = function () {
            if (this.options.stacking && (!0 === this.visible ||
                !1 === this.chart.options.chart.ignoreHiddenSeries)) {
                var a = this.processedXData, c = this.processedYData, b = [], e = c.length, m = this.options, r = m.threshold, f = m.startFromThreshold ? r : 0, k = m.stack, m = m.stacking, p = this.stackKey, H = "-" + p, t = this.negStacks, E = this.yAxis, G = E.stacks, z = E.oldStacks, d, A, u, B, J, g, v;
                E.stacksTouched += 1;
                for (J = 0; J < e; J++)g = a[J], v = c[J], d = this.getStackIndicator(d, g, this.index), B = d.key, u = (A = t && v < (f ? 0 : r)) ? H : p, G[u] || (G[u] = {}), G[u][g] || (z[u] && z[u][g] ? (G[u][g] = z[u][g], G[u][g].total = null) : G[u][g] = new w(E,
                    E.options.stackLabels, A, g, k)), u = G[u][g], null !== v && (u.points[B] = u.points[this.index] = [n(u.cum, f)], q(u.cum) || (u.base = B), u.touched = E.stacksTouched, 0 < d.index && !1 === this.singleStacks && (u.points[B][0] = u.points[this.index + "," + g + ",0"][0])), "percent" === m ? (A = A ? p : H, t && G[A] && G[A][g] ? (A = G[A][g], u.total = A.total = Math.max(A.total, u.total) + Math.abs(v) || 0) : u.total = F(u.total + (Math.abs(v) || 0))) : u.total = F(u.total + (v || 0)), u.cum = n(u.cum, f) + (v || 0), null !== v && (u.points[B].push(u.cum), b[J] = u.cum);
                "percent" === m && (E.usePercentage = !0);
                this.stackedYData = b;
                E.oldStacks = {}
            }
        };
        a.prototype.setPercentStacks = function () {
            var a = this, e = a.stackKey, b = a.yAxis.stacks, m = a.processedXData, n;
            c([e, "-" + e], function (c) {
                for (var f = m.length, e, h; f--;)if (e = m[f], n = a.getStackIndicator(n, e, a.index, c), e = (h = b[c] && b[c][e]) && h.points[n.key])h = h.total ? 100 / h.total : 0, e[0] = F(e[0] * h), e[1] = F(e[1] * h), a.stackedYData[f] = e[1]
            })
        };
        a.prototype.getStackIndicator = function (a, c, b, e) {
            !q(a) || a.x !== c || e && a.key !== e ? a = {x: c, index: 0, key: e} : a.index++;
            a.key = [b, c, a.index].join();
            return a
        }
    })(K);
    (function (a) {
        var w = a.addEvent, B = a.Axis, D = a.createElement, F = a.css, q = a.defined, e = a.each, c = a.erase, m = a.extend, n = a.fireEvent, l = a.inArray, h = a.isNumber, b = a.isObject, C = a.merge, y = a.pick, r = a.Point, f = a.Series, k = a.seriesTypes, p = a.setAnimation, H = a.splat;
        m(a.Chart.prototype, {
            addSeries: function (a, b, c) {
                var f, d = this;
                a && (b = y(b, !0), n(d, "addSeries", {options: a}, function () {
                    f = d.initSeries(a);
                    d.isDirtyLegend = !0;
                    d.linkSeries();
                    b && d.redraw(c)
                }));
                return f
            },
            addAxis: function (a, b, c, f) {
                var d = b ? "xAxis" : "yAxis", e = this.options;
                a =
                    C(a, {index: this[d].length, isX: b});
                new B(this, a);
                e[d] = H(e[d] || {});
                e[d].push(a);
                y(c, !0) && this.redraw(f)
            },
            showLoading: function (a) {
                var b = this, c = b.options, f = b.loadingDiv, d = function () {
                    f && F(f, {
                        left: b.plotLeft + "px",
                        top: b.plotTop + "px",
                        width: b.plotWidth + "px",
                        height: b.plotHeight + "px"
                    })
                };
                f || (b.loadingDiv = f = D("div", {className: "highcharts-loading highcharts-loading-hidden"}, null, b.container), b.loadingSpan = D("span", {className: "highcharts-loading-inner"}, null, f), w(b, "redraw", d));
                f.className = "highcharts-loading";
                b.loadingSpan.innerHTML = a || c.lang.loading;
                b.loadingShown = !0;
                d()
            },
            hideLoading: function () {
                var a = this.loadingDiv;
                a && (a.className = "highcharts-loading highcharts-loading-hidden");
                this.loadingShown = !1
            },
            propsRequireDirtyBox: "backgroundColor borderColor borderWidth margin marginTop marginRight marginBottom marginLeft spacing spacingTop spacingRight spacingBottom spacingLeft borderRadius plotBackgroundColor plotBackgroundImage plotBorderColor plotBorderWidth plotShadow shadow".split(" "),
            propsRequireUpdateSeries: "chart.inverted chart.polar chart.ignoreHiddenSeries chart.type colors plotOptions".split(" "),
            update: function (a, b) {
                var c, f = {credits: "addCredits", title: "setTitle", subtitle: "setSubtitle"}, d = a.chart, k, p;
                if (d) {
                    C(!0, this.options.chart, d);
                    "className"in d && this.setClassName(d.className);
                    if ("inverted"in d || "polar"in d)this.propFromSeries(), k = !0;
                    for (c in d)d.hasOwnProperty(c) && (-1 !== l("chart." + c, this.propsRequireUpdateSeries) && (p = !0), -1 !== l(c, this.propsRequireDirtyBox) && (this.isDirtyBox = !0))
                }
                for (c in a) {
                    if (this[c] && "function" === typeof this[c].update)this[c].update(a[c], !1); else if ("function" === typeof this[f[c]])this[f[c]](a[c]);
                    "chart" !== c && -1 !== l(c, this.propsRequireUpdateSeries) && (p = !0)
                }
                a.plotOptions && C(!0, this.options.plotOptions, a.plotOptions);
                e(["xAxis", "yAxis", "series"], function (b) {
                    a[b] && e(H(a[b]), function (a) {
                        var d = q(a.id) && this.get(a.id) || this[b][0];
                        d && d.coll === b && d.update(a, !1)
                    }, this)
                }, this);
                k && e(this.axes, function (a) {
                    a.update({}, !1)
                });
                p && e(this.series, function (a) {
                    a.update({}, !1)
                });
                a.loading && C(!0, this.options.loading, a.loading);
                c = d && d.width;
                d = d && d.height;
                h(c) && c !== this.chartWidth || h(d) && d !== this.chartHeight ? this.setSize(c,
                    d) : y(b, !0) && this.redraw()
            },
            setSubtitle: function (a) {
                this.setTitle(void 0, a)
            }
        });
        m(r.prototype, {
            update: function (a, c, f, e) {
                function d() {
                    h.applyOptions(a);
                    null === h.y && p && (h.graphic = p.destroy());
                    b(a, !0) && (p && p.element && a && a.marker && a.marker.symbol && (h.graphic = p.destroy()), a && a.dataLabels && h.dataLabel && (h.dataLabel = h.dataLabel.destroy()));
                    l = h.index;
                    k.updateParallelArrays(h, l);
                    m.data[l] = b(m.data[l], !0) ? h.options : a;
                    k.isDirty = k.isDirtyData = !0;
                    !k.fixedBox && k.hasCartesianSeries && (g.isDirtyBox = !0);
                    "point" === m.legendType &&
                    (g.isDirtyLegend = !0);
                    c && g.redraw(f)
                }

                var h = this, k = h.series, p = h.graphic, l, g = k.chart, m = k.options;
                c = y(c, !0);
                !1 === e ? d() : h.firePointEvent("update", {options: a}, d)
            }, remove: function (a, b) {
                this.series.removePoint(l(this, this.series.data), a, b)
            }
        });
        m(f.prototype, {
            addPoint: function (a, b, c, f) {
                var d = this.options, e = this.data, h = this.chart, k = this.xAxis && this.xAxis.names, p = d.data, g, l, m = this.xData, n, t;
                b = y(b, !0);
                g = {series: this};
                this.pointClass.prototype.applyOptions.apply(g, [a]);
                t = g.x;
                n = m.length;
                if (this.requireSorting &&
                    t < m[n - 1])for (l = !0; n && m[n - 1] > t;)n--;
                this.updateParallelArrays(g, "splice", n, 0, 0);
                this.updateParallelArrays(g, n);
                k && g.name && (k[t] = g.name);
                p.splice(n, 0, a);
                l && (this.data.splice(n, 0, null), this.processData());
                "point" === d.legendType && this.generatePoints();
                c && (e[0] && e[0].remove ? e[0].remove(!1) : (e.shift(), this.updateParallelArrays(g, "shift"), p.shift()));
                this.isDirtyData = this.isDirty = !0;
                b && h.redraw(f)
            }, removePoint: function (a, b, c) {
                var f = this, d = f.data, e = d[a], h = f.points, k = f.chart, l = function () {
                    h && h.length === d.length &&
                    h.splice(a, 1);
                    d.splice(a, 1);
                    f.options.data.splice(a, 1);
                    f.updateParallelArrays(e || {series: f}, "splice", a, 1);
                    e && e.destroy();
                    f.isDirty = !0;
                    f.isDirtyData = !0;
                    b && k.redraw()
                };
                p(c, k);
                b = y(b, !0);
                e ? e.firePointEvent("remove", null, l) : l()
            }, remove: function (a, b, c) {
                function f() {
                    d.destroy();
                    e.isDirtyLegend = e.isDirtyBox = !0;
                    e.linkSeries();
                    y(a, !0) && e.redraw(b)
                }

                var d = this, e = d.chart;
                !1 !== c ? n(d, "remove", null, f) : f()
            }, update: function (a, b) {
                var c = this, f = this.chart, d = this.userOptions, h = this.type, p = a.type || d.type || f.options.chart.type,
                    l = k[h].prototype, n = ["group", "markerGroup", "dataLabelsGroup"], g;
                if (p && p !== h || void 0 !== a.zIndex)n.length = 0;
                e(n, function (a) {
                    n[a] = c[a];
                    delete c[a]
                });
                a = C(d, {animation: !1, index: this.index, pointStart: this.xData[0]}, {data: this.options.data}, a);
                this.remove(!1, null, !1);
                for (g in l)this[g] = void 0;
                m(this, k[p || h].prototype);
                e(n, function (a) {
                    c[a] = n[a]
                });
                this.init(f, a);
                f.linkSeries();
                y(b, !0) && f.redraw(!1)
            }
        });
        m(B.prototype, {
            update: function (a, b) {
                var c = this.chart;
                a = c.options[this.coll][this.options.index] = C(this.userOptions,
                    a);
                this.destroy(!0);
                this.init(c, m(a, {events: void 0}));
                c.isDirtyBox = !0;
                y(b, !0) && c.redraw()
            }, remove: function (a) {
                for (var b = this.chart, f = this.coll, h = this.series, d = h.length; d--;)h[d] && h[d].remove(!1);
                c(b.axes, this);
                c(b[f], this);
                b.options[f].splice(this.options.index, 1);
                e(b[f], function (a, b) {
                    a.options.index = b
                });
                this.destroy();
                b.isDirtyBox = !0;
                y(a, !0) && b.redraw()
            }, setTitle: function (a, b) {
                this.update({title: a}, b)
            }, setCategories: function (a, b) {
                this.update({categories: a}, b)
            }
        })
    })(K);
    (function (a) {
        var w = a.each, B =
            a.map, D = a.pick, F = a.Series, q = a.seriesType;
        q("area", "line", {softThreshold: !1, threshold: 0}, {
            singleStacks: !1, getStackPoints: function () {
                var a = [], c = [], m = this.xAxis, n = this.yAxis, l = n.stacks[this.stackKey], h = {}, b = this.points, q = this.index, y = n.series, r = y.length, f, k = D(n.options.reversedStacks, !0) ? 1 : -1, p, H;
                if (this.options.stacking) {
                    for (p = 0; p < b.length; p++)h[b[p].x] = b[p];
                    for (H in l)null !== l[H].total && c.push(H);
                    c.sort(function (a, b) {
                        return a - b
                    });
                    f = B(y, function () {
                        return this.visible
                    });
                    w(c, function (b, e) {
                        var t = 0, z, d;
                        if (h[b] && !h[b].isNull)a.push(h[b]), w([-1, 1], function (a) {
                            var m = 1 === a ? "rightNull" : "leftNull", n = 0, t = l[c[e + a]];
                            if (t)for (p = q; 0 <= p && p < r;)z = t.points[p], z || (p === q ? h[b][m] = !0 : f[p] && (d = l[b].points[p]) && (n -= d[1] - d[0])), p += k;
                            h[b][1 === a ? "rightCliff" : "leftCliff"] = n
                        }); else {
                            for (p = q; 0 <= p && p < r;) {
                                if (z = l[b].points[p]) {
                                    t = z[1];
                                    break
                                }
                                p += k
                            }
                            t = n.toPixels(t, !0);
                            a.push({isNull: !0, plotX: m.toPixels(b, !0), plotY: t, yBottom: t})
                        }
                    })
                }
                return a
            }, getGraphPath: function (a) {
                var c = F.prototype.getGraphPath, e = this.options, n = e.stacking, l = this.yAxis,
                    h, b, q = [], y = [], r = this.index, f, k = l.stacks[this.stackKey], p = e.threshold, w = l.getThreshold(e.threshold), t, e = e.connectNulls || "percent" === n, E = function (b, c, d) {
                        var e = a[b];
                        b = n && k[e.x].points[r];
                        var h = e[d + "Null"] || 0;
                        d = e[d + "Cliff"] || 0;
                        var m, t, e = !0;
                        d || h ? (m = (h ? b[0] : b[1]) + d, t = b[0] + d, e = !!h) : !n && a[c] && a[c].isNull && (m = t = p);
                        void 0 !== m && (y.push({
                            plotX: f,
                            plotY: null === m ? w : l.getThreshold(m),
                            isNull: e
                        }), q.push({plotX: f, plotY: null === t ? w : l.getThreshold(t), doCurve: !1}))
                    };
                a = a || this.points;
                n && (a = this.getStackPoints());
                for (h = 0; h <
                a.length; h++)if (b = a[h].isNull, f = D(a[h].rectPlotX, a[h].plotX), t = D(a[h].yBottom, w), !b || e)e || E(h, h - 1, "left"), b && !n && e || (y.push(a[h]), q.push({
                    x: h,
                    plotX: f,
                    plotY: t
                })), e || E(h, h + 1, "right");
                h = c.call(this, y, !0, !0);
                q.reversed = !0;
                b = c.call(this, q, !0, !0);
                b.length && (b[0] = "L");
                b = h.concat(b);
                c = c.call(this, y, !1, e);
                b.xMap = h.xMap;
                this.areaPath = b;
                return c
            }, drawGraph: function () {
                this.areaPath = [];
                F.prototype.drawGraph.apply(this);
                var a = this, c = this.areaPath, m = this.options, n = [["area", "highcharts-area"]];
                w(this.zones, function (a,
                                        c) {
                    n.push(["zone-area-" + c, "highcharts-area highcharts-zone-area-" + c + " " + a.className])
                });
                w(n, function (e) {
                    var h = e[0], b = a[h];
                    b ? (b.endX = c.xMap, b.animate({d: c})) : (b = a[h] = a.chart.renderer.path(c).addClass(e[1]).attr({zIndex: 0}).add(a.group), b.isArea = !0);
                    b.startX = c.xMap;
                    b.shiftUnit = m.step ? 2 : 1
                })
            }, drawLegendSymbol: a.LegendSymbolMixin.drawRectangle
        })
    })(K);
    (function (a) {
        var w = a.pick;
        a = a.seriesType;
        a("spline", "line", {}, {
            getPointSpline: function (a, D, F) {
                var q = D.plotX, e = D.plotY, c = a[F - 1];
                F = a[F + 1];
                var m, n, l, h;
                if (c && !c.isNull && !1 !== c.doCurve && F && !F.isNull && !1 !== F.doCurve) {
                    a = c.plotY;
                    l = F.plotX;
                    F = F.plotY;
                    var b = 0;
                    m = (1.5 * q + c.plotX) / 2.5;
                    n = (1.5 * e + a) / 2.5;
                    l = (1.5 * q + l) / 2.5;
                    h = (1.5 * e + F) / 2.5;
                    l !== m && (b = (h - n) * (l - q) / (l - m) + e - h);
                    n += b;
                    h += b;
                    n > a && n > e ? (n = Math.max(a, e), h = 2 * e - n) : n < a && n < e && (n = Math.min(a, e), h = 2 * e - n);
                    h > F && h > e ? (h = Math.max(F, e), n = 2 * e - h) : h < F && h < e && (h = Math.min(F, e), n = 2 * e - h);
                    D.rightContX = l;
                    D.rightContY = h
                }
                D = ["C", w(c.rightContX, c.plotX), w(c.rightContY, c.plotY), w(m, q), w(n, e), q, e];
                c.rightContX = c.rightContY = null;
                return D
            }
        })
    })(K);
    (function (a) {
        var w = a.seriesTypes.area.prototype, B = a.seriesType;
        B("areaspline", "spline", a.defaultPlotOptions.area, {
            getStackPoints: w.getStackPoints,
            getGraphPath: w.getGraphPath,
            setStackCliffs: w.setStackCliffs,
            drawGraph: w.drawGraph,
            drawLegendSymbol: a.LegendSymbolMixin.drawRectangle
        })
    })(K);
    (function (a) {
        var w = a.animObject, B = a.each, D = a.extend, F = a.isNumber, q = a.merge, e = a.pick, c = a.Series, m = a.seriesType, n = a.svg;
        m("column", "line", {
            borderRadius: 0,
            groupPadding: .2,
            marker: null,
            pointPadding: .1,
            minPointLength: 0,
            cropThreshold: 50,
            pointRange: null,
            states: {hover: {halo: !1}},
            dataLabels: {align: null, verticalAlign: null, y: null},
            softThreshold: !1,
            startFromThreshold: !0,
            stickyTracking: !1,
            tooltip: {distance: 6},
            threshold: 0
        }, {
            cropShoulder: 0,
            directTouch: !0,
            trackerGroups: ["group", "dataLabelsGroup"],
            negStacks: !0,
            init: function () {
                c.prototype.init.apply(this, arguments);
                var a = this, e = a.chart;
                e.hasRendered && B(e.series, function (b) {
                    b.type === a.type && (b.isDirty = !0)
                })
            },
            getColumnMetrics: function () {
                var a = this, c = a.options, b = a.xAxis, m = a.yAxis, n = b.reversed,
                    r, f = {}, k = 0;
                !1 === c.grouping ? k = 1 : B(a.chart.series, function (b) {
                    var c = b.options, e = b.yAxis, d;
                    b.type === a.type && b.visible && m.len === e.len && m.pos === e.pos && (c.stacking ? (r = b.stackKey, void 0 === f[r] && (f[r] = k++), d = f[r]) : !1 !== c.grouping && (d = k++), b.columnIndex = d)
                });
                var p = Math.min(Math.abs(b.transA) * (b.ordinalSlope || c.pointRange || b.closestPointRange || b.tickInterval || 1), b.len), q = p * c.groupPadding, t = (p - 2 * q) / k, c = Math.min(c.maxPointWidth || b.len, e(c.pointWidth, t * (1 - 2 * c.pointPadding)));
                a.columnMetrics = {
                    width: c, offset: (t -
                    c) / 2 + (q + ((a.columnIndex || 0) + (n ? 1 : 0)) * t - p / 2) * (n ? -1 : 1)
                };
                return a.columnMetrics
            },
            crispCol: function (a, c, b, e) {
                var h = this.chart, l = this.borderWidth, f = -(l % 2 ? .5 : 0), l = l % 2 ? .5 : 1;
                h.inverted && h.renderer.isVML && (l += 1);
                b = Math.round(a + b) + f;
                a = Math.round(a) + f;
                e = Math.round(c + e) + l;
                f = .5 >= Math.abs(c) && .5 < e;
                c = Math.round(c) + l;
                e -= c;
                f && e && (--c, e += 1);
                return {x: a, y: c, width: b - a, height: e}
            },
            translate: function () {
                var a = this, h = a.chart, b = a.options, m = a.dense = 2 > a.closestPointRange * a.xAxis.transA, m = a.borderWidth = e(b.borderWidth, m ? 0 : 1),
                    n = a.yAxis, r = a.translatedThreshold = n.getThreshold(b.threshold), f = e(b.minPointLength, 5), k = a.getColumnMetrics(), p = k.width, q = a.barW = Math.max(p, 1 + 2 * m), t = a.pointXOffset = k.offset;
                h.inverted && (r -= .5);
                b.pointPadding && (q = Math.ceil(q));
                c.prototype.translate.apply(a);
                B(a.points, function (b) {
                    var c = e(b.yBottom, r), k = 999 + Math.abs(c), k = Math.min(Math.max(-k, b.plotY), n.len + k), d = b.plotX + t, l = q, m = Math.min(k, c), E, y = Math.max(k, c) - m;
                    Math.abs(y) < f && f && (y = f, E = !n.reversed && !b.negative || n.reversed && b.negative, m = Math.abs(m - r) >
                    f ? c - f : r - (E ? f : 0));
                    b.barX = d;
                    b.pointWidth = p;
                    b.tooltipPos = h.inverted ? [n.len + n.pos - h.plotLeft - k, a.xAxis.len - d - l / 2, y] : [d + l / 2, k + n.pos - h.plotTop, y];
                    b.shapeType = "rect";
                    b.shapeArgs = a.crispCol.apply(a, b.isNull ? [b.plotX, n.len / 2, 0, 0] : [d, m, l, y])
                })
            },
            getSymbol: a.noop,
            drawLegendSymbol: a.LegendSymbolMixin.drawRectangle,
            drawGraph: function () {
                this.group[this.dense ? "addClass" : "removeClass"]("highcharts-dense-data")
            },
            drawPoints: function () {
                var a = this, c = this.chart, b = c.renderer, e = a.options.animationLimit || 250, m;
                B(a.points,
                    function (h) {
                        var f = h.graphic;
                        if (F(h.plotY) && null !== h.y)if (m = h.shapeArgs, f)f[c.pointCount < e ? "animate" : "attr"](q(m)); else h.graphic = b[h.shapeType](m).attr({"class": h.getClassName()}).add(h.group || a.group); else f && (h.graphic = f.destroy())
                    })
            },
            animate: function (a) {
                var c = this, b = this.yAxis, e = c.options, l = this.chart.inverted, m = {};
                n && (a ? (m.scaleY = .001, a = Math.min(b.pos + b.len, Math.max(b.pos, b.toPixels(e.threshold))), l ? m.translateX = a - b.len : m.translateY = a, c.group.attr(m)) : (m[l ? "translateX" : "translateY"] = b.pos, c.group.animate(m,
                    D(w(c.options.animation), {
                        step: function (a, b) {
                            c.group.attr({scaleY: Math.max(.001, b.pos)})
                        }
                    })), c.animate = null))
            },
            remove: function () {
                var a = this, e = a.chart;
                e.hasRendered && B(e.series, function (b) {
                    b.type === a.type && (b.isDirty = !0)
                });
                c.prototype.remove.apply(a, arguments)
            }
        })
    })(K);
    (function (a) {
        a = a.seriesType;
        a("bar", "column", null, {inverted: !0})
    })(K);
    (function (a) {
        var w = a.Series;
        a = a.seriesType;
        a("scatter", "line", {
            lineWidth: 0, marker: {enabled: !0}, tooltip: {
                headerFormat: '\x3cspan style\x3d"color:{point.color}"\x3e\u25cf\x3c/span\x3e \x3cspan style\x3d"font-size: 0.85em"\x3e {series.name}\x3c/span\x3e\x3cbr/\x3e',
                pointFormat: "x: \x3cb\x3e{point.x}\x3c/b\x3e\x3cbr/\x3ey: \x3cb\x3e{point.y}\x3c/b\x3e\x3cbr/\x3e"
            }
        }, {
            sorted: !1,
            requireSorting: !1,
            noSharedTooltip: !0,
            trackerGroups: ["group", "markerGroup", "dataLabelsGroup"],
            takeOrdinalPosition: !1,
            kdDimensions: 2,
            drawGraph: function () {
                this.options.lineWidth && w.prototype.drawGraph.call(this)
            }
        })
    })(K);
    (function (a) {
        var w = a.pick, B = a.relativeLength;
        a.CenteredSeriesMixin = {
            getCenter: function () {
                var a = this.options, F = this.chart, q = 2 * (a.slicedOffset || 0), e = F.plotWidth - 2 * q, F = F.plotHeight -
                    2 * q, c = a.center, c = [w(c[0], "50%"), w(c[1], "50%"), a.size || "100%", a.innerSize || 0], m = Math.min(e, F), n, l;
                for (n = 0; 4 > n; ++n)l = c[n], a = 2 > n || 2 === n && /%$/.test(l), c[n] = B(l, [e, F, m, c[2]][n]) + (a ? q : 0);
                c[3] > c[2] && (c[3] = c[2]);
                return c
            }
        }
    })(K);
    (function (a) {
        var w = a.addEvent, B = a.defined, D = a.each, F = a.extend, q = a.inArray, e = a.noop, c = a.pick, m = a.Point, n = a.Series, l = a.seriesType, h = a.setAnimation;
        l("pie", "line", {
            center: [null, null],
            clip: !1,
            colorByPoint: !0,
            dataLabels: {
                distance: 30, enabled: !0, formatter: function () {
                    return null === this.y ?
                        void 0 : this.point.name
                }, x: 0
            },
            ignoreHiddenPoint: !0,
            legendType: "point",
            marker: null,
            size: null,
            showInLegend: !1,
            slicedOffset: 10,
            stickyTracking: !1,
            tooltip: {followPointer: !0}
        }, {
            isCartesian: !1,
            requireSorting: !1,
            directTouch: !0,
            noSharedTooltip: !0,
            trackerGroups: ["group", "dataLabelsGroup"],
            axisTypes: [],
            pointAttribs: a.seriesTypes.column.prototype.pointAttribs,
            animate: function (a) {
                var b = this, c = b.points, e = b.startAngleRad;
                a || (D(c, function (a) {
                    var c = a.graphic, f = a.shapeArgs;
                    c && (c.attr({
                        r: a.startR || b.center[3] / 2, start: e,
                        end: e
                    }), c.animate({r: f.r, start: f.start, end: f.end}, b.options.animation))
                }), b.animate = null)
            },
            updateTotals: function () {
                var a, c = 0, e = this.points, h = e.length, f, k = this.options.ignoreHiddenPoint;
                for (a = 0; a < h; a++)f = e[a], 0 > f.y && (f.y = null), c += k && !f.visible ? 0 : f.y;
                this.total = c;
                for (a = 0; a < h; a++)f = e[a], f.percentage = 0 < c && (f.visible || !k) ? f.y / c * 100 : 0, f.total = c
            },
            generatePoints: function () {
                n.prototype.generatePoints.call(this);
                this.updateTotals()
            },
            translate: function (a) {
                this.generatePoints();
                var b = 0, e = this.options, h = e.slicedOffset,
                    f = h + (e.borderWidth || 0), k, p, l, m = e.startAngle || 0, n = this.startAngleRad = Math.PI / 180 * (m - 90), m = (this.endAngleRad = Math.PI / 180 * (c(e.endAngle, m + 360) - 90)) - n, q = this.points, z = e.dataLabels.distance, e = e.ignoreHiddenPoint, d, A = q.length, u;
                a || (this.center = a = this.getCenter());
                this.getX = function (b, c) {
                    l = Math.asin(Math.min((b - a[1]) / (a[2] / 2 + z), 1));
                    return a[0] + (c ? -1 : 1) * Math.cos(l) * (a[2] / 2 + z)
                };
                for (d = 0; d < A; d++) {
                    u = q[d];
                    k = n + b * m;
                    if (!e || u.visible)b += u.percentage / 100;
                    p = n + b * m;
                    u.shapeType = "arc";
                    u.shapeArgs = {
                        x: a[0], y: a[1], r: a[2] /
                        2, innerR: a[3] / 2, start: Math.round(1E3 * k) / 1E3, end: Math.round(1E3 * p) / 1E3
                    };
                    l = (p + k) / 2;
                    l > 1.5 * Math.PI ? l -= 2 * Math.PI : l < -Math.PI / 2 && (l += 2 * Math.PI);
                    u.slicedTranslation = {
                        translateX: Math.round(Math.cos(l) * h),
                        translateY: Math.round(Math.sin(l) * h)
                    };
                    k = Math.cos(l) * a[2] / 2;
                    p = Math.sin(l) * a[2] / 2;
                    u.tooltipPos = [a[0] + .7 * k, a[1] + .7 * p];
                    u.half = l < -Math.PI / 2 || l > Math.PI / 2 ? 1 : 0;
                    u.angle = l;
                    f = Math.min(f, z / 5);
                    u.labelPos = [a[0] + k + Math.cos(l) * z, a[1] + p + Math.sin(l) * z, a[0] + k + Math.cos(l) * f, a[1] + p + Math.sin(l) * f, a[0] + k, a[1] + p, 0 > z ? "center" : u.half ?
                        "right" : "left", l]
                }
            },
            drawGraph: null,
            drawPoints: function () {
                var a = this, c = a.chart.renderer, e, h, f;
                D(a.points, function (b) {
                    null !== b.y && (h = b.graphic, f = b.shapeArgs, e = b.sliced ? b.slicedTranslation : {}, h ? h.setRadialReference(a.center).animate(F(f, e)) : (b.graphic = h = c[b.shapeType](f).addClass(b.getClassName()).setRadialReference(a.center).attr(e).add(a.group), b.visible || h.attr({visibility: "hidden"})))
                })
            },
            searchPoint: e,
            sortByAngle: function (a, c) {
                a.sort(function (a, b) {
                    return void 0 !== a.angle && (b.angle - a.angle) * c
                })
            },
            drawLegendSymbol: a.LegendSymbolMixin.drawRectangle,
            getCenter: a.CenteredSeriesMixin.getCenter,
            getSymbol: e
        }, {
            init: function () {
                m.prototype.init.apply(this, arguments);
                var a = this, e;
                a.name = c(a.name, "Slice");
                e = function (b) {
                    a.slice("select" === b.type)
                };
                w(a, "select", e);
                w(a, "unselect", e);
                return a
            }, setVisible: function (a, e) {
                var b = this, h = b.series, f = h.chart, k = h.options.ignoreHiddenPoint;
                e = c(e, k);
                a !== b.visible && (b.visible = b.options.visible = a = void 0 === a ? !b.visible : a, h.options.data[q(b, h.data)] = b.options, D(["graphic",
                    "dataLabel", "connector", "shadowGroup"], function (c) {
                    if (b[c])b[c][a ? "show" : "hide"](!0)
                }), b.legendItem && f.legend.colorizeItem(b, a), a || "hover" !== b.state || b.setState(""), k && (h.isDirty = !0), e && f.redraw())
            }, slice: function (a, e, l) {
                var b = this.series;
                h(l, b.chart);
                c(e, !0);
                this.sliced = this.options.sliced = a = B(a) ? a : !this.sliced;
                b.options.data[q(this, b.data)] = this.options;
                this.graphic.animate(a ? this.slicedTranslation : {translateX: 0, translateY: 0})
            }, haloPath: function (a) {
                var b = this.shapeArgs;
                return this.sliced || !this.visible ?
                    [] : this.series.chart.renderer.symbols.arc(b.x, b.y, b.r + a, b.r + a, {
                    innerR: this.shapeArgs.r,
                    start: b.start,
                    end: b.end
                })
            }
        })
    })(K);
    (function (a) {
        var w = a.addEvent, B = a.arrayMax, D = a.defined, F = a.each, q = a.extend, e = a.format, c = a.map, m = a.merge, n = a.noop, l = a.pick, h = a.relativeLength, b = a.Series, C = a.seriesTypes, y = a.stableSort;
        a.distribute = function (a, b) {
            function f(a, b) {
                return a.target - b.target
            }

            var e, h = !0, l = a, m = [], n;
            n = 0;
            for (e = a.length; e--;)n += a[e].size;
            if (n > b) {
                y(a, function (a, b) {
                    return (b.rank || 0) - (a.rank || 0)
                });
                for (n = e = 0; n <=
                b;)n += a[e].size, e++;
                m = a.splice(e - 1, a.length)
            }
            y(a, f);
            for (a = c(a, function (a) {
                return {size: a.size, targets: [a.target]}
            }); h;) {
                for (e = a.length; e--;)h = a[e], n = (Math.min.apply(0, h.targets) + Math.max.apply(0, h.targets)) / 2, h.pos = Math.min(Math.max(0, n - h.size / 2), b - h.size);
                e = a.length;
                for (h = !1; e--;)0 < e && a[e - 1].pos + a[e - 1].size > a[e].pos && (a[e - 1].size += a[e].size, a[e - 1].targets = a[e - 1].targets.concat(a[e].targets), a[e - 1].pos + a[e - 1].size > b && (a[e - 1].pos = b - a[e - 1].size), a.splice(e, 1), h = !0)
            }
            e = 0;
            F(a, function (a) {
                var b = 0;
                F(a.targets,
                    function () {
                        l[e].pos = a.pos + b;
                        b += l[e].size;
                        e++
                    })
            });
            l.push.apply(l, m);
            y(l, f)
        };
        b.prototype.drawDataLabels = function () {
            var a = this, b = a.options, c = b.dataLabels, h = a.points, n, t, q = a.hasRendered || 0, G, z, d = l(c.defer, !0), A = a.chart.renderer;
            if (c.enabled || a._hasPointLabels)a.dlProcessOptions && a.dlProcessOptions(c), z = a.plotGroup("dataLabelsGroup", "data-labels", d && !q ? "hidden" : "visible", c.zIndex || 6), d && (z.attr({opacity: +q}), q || w(a, "afterAnimate", function () {
                a.visible && z.show(!0);
                z[b.animation ? "animate" : "attr"]({opacity: 1},
                    {duration: 200})
            })), t = c, F(h, function (b) {
                var d, f = b.dataLabel, g, h, k = b.connector, p = !0;
                n = b.dlOptions || b.options && b.options.dataLabels;
                d = l(n && n.enabled, t.enabled) && null !== b.y;
                if (f && !d)b.dataLabel = f.destroy(); else if (d) {
                    c = m(t, n);
                    d = c.rotation;
                    g = b.getLabelConfig();
                    G = c.format ? e(c.format, g) : c.formatter.call(g, c);
                    if (f)D(G) ? (f.attr({text: G}), p = !1) : (b.dataLabel = f = f.destroy(), k && (b.connector = k.destroy())); else if (D(G)) {
                        f = {r: c.borderRadius || 0, rotation: d, padding: c.padding, zIndex: 1};
                        for (h in f)void 0 === f[h] && delete f[h];
                        f = b.dataLabel = A[d ? "text" : "label"](G, 0, -9999, c.shape, null, null, c.useHTML, null, "data-label").attr(f);
                        f.addClass("highcharts-data-label-color-" + b.colorIndex + " " + (c.className || "") + (c.useHTML ? "highcharts-tracker" : ""));
                        f.add(z)
                    }
                    f && a.alignDataLabel(b, f, c, null, p)
                }
            })
        };
        b.prototype.alignDataLabel = function (a, b, c, e, h) {
            var f = this.chart, k = f.inverted, m = l(a.plotX, -9999), p = l(a.plotY, -9999), d = b.getBBox(), n, r = c.rotation, w = c.align, y = this.visible && (a.series.forceDL || f.isInsidePlot(m, Math.round(p), k) || e && f.isInsidePlot(m,
                    k ? e.x + 1 : e.y + e.height - 1, k)), g = "justify" === l(c.overflow, "justify");
            y && (n = f.renderer.fontMetrics(void 0, b).b, e = q({
                x: k ? f.plotWidth - p : m,
                y: Math.round(k ? f.plotHeight - m : p),
                width: 0,
                height: 0
            }, e), q(c, {
                width: d.width,
                height: d.height
            }), r ? (g = !1, k = f.renderer.rotCorr(n, r), k = {
                x: e.x + c.x + e.width / 2 + k.x,
                y: e.y + c.y + {top: 0, middle: .5, bottom: 1}[c.verticalAlign] * e.height
            }, b[h ? "attr" : "animate"](k).attr({align: w}), m = (r + 720) % 360, m = 180 < m && 360 > m, "left" === w ? k.y -= m ? d.height : 0 : "center" === w ? (k.x -= d.width / 2, k.y -= d.height / 2) : "right" ===
            w && (k.x -= d.width, k.y -= m ? 0 : d.height)) : (b.align(c, null, e), k = b.alignAttr), g ? this.justifyDataLabel(b, c, k, d, e, h) : l(c.crop, !0) && (y = f.isInsidePlot(k.x, k.y) && f.isInsidePlot(k.x + d.width, k.y + d.height)), c.shape && !r && b.attr({
                anchorX: a.plotX,
                anchorY: a.plotY
            }));
            y || (b.attr({y: -9999}), b.placed = !1)
        };
        b.prototype.justifyDataLabel = function (a, b, c, e, h, l) {
            var f = this.chart, k = b.align, m = b.verticalAlign, d, n, p = a.box ? 0 : a.padding || 0;
            d = c.x + p;
            0 > d && ("right" === k ? b.align = "left" : b.x = -d, n = !0);
            d = c.x + e.width - p;
            d > f.plotWidth && ("left" ===
            k ? b.align = "right" : b.x = f.plotWidth - d, n = !0);
            d = c.y + p;
            0 > d && ("bottom" === m ? b.verticalAlign = "top" : b.y = -d, n = !0);
            d = c.y + e.height - p;
            d > f.plotHeight && ("top" === m ? b.verticalAlign = "bottom" : b.y = f.plotHeight - d, n = !0);
            n && (a.placed = !l, a.align(b, null, h))
        };
        C.pie && (C.pie.prototype.drawDataLabels = function () {
            var e = this, f = e.data, h, m = e.chart, n = e.options.dataLabels, t = l(n.connectorPadding, 10), q = l(n.connectorWidth, 1), G = m.plotWidth, z = m.plotHeight, d, A = n.distance, u = e.center, w = u[2] / 2, y = u[1], g = 0 < A, v, C, D, K, P = [[], []], L, x, M, N, R = [0, 0, 0,
                0];
            e.visible && (n.enabled || e._hasPointLabels) && (b.prototype.drawDataLabels.apply(e), F(f, function (a) {
                a.dataLabel && a.visible && (P[a.half].push(a), a.dataLabel._pos = null)
            }), F(P, function (b, d) {
                var f, g, k = b.length, l, p, q;
                if (k)for (e.sortByAngle(b, d - .5), 0 < A && (f = Math.max(0, y - w - A), g = Math.min(y + w + A, m.plotHeight), l = c(b, function (a) {
                    if (a.dataLabel)return q = a.dataLabel.getBBox().height || 21, {
                        target: a.labelPos[1] - f + q / 2,
                        size: q,
                        rank: a.y
                    }
                }), a.distribute(l, g + q - f)), N = 0; N < k; N++)h = b[N], D = h.labelPos, v = h.dataLabel, M = !1 === h.visible ?
                    "hidden" : "inherit", p = D[1], l ? void 0 === l[N].pos ? M = "hidden" : (K = l[N].size, x = f + l[N].pos) : x = p, L = n.justify ? u[0] + (d ? -1 : 1) * (w + A) : e.getX(x < f + 2 || x > g - 2 ? p : x, d), v._attr = {
                    visibility: M,
                    align: D[6]
                }, v._pos = {
                    x: L + n.x + ({left: t, right: -t}[D[6]] || 0),
                    y: x + n.y - 10
                }, D.x = L, D.y = x, null === e.options.size && (C = v.width, L - C < t ? R[3] = Math.max(Math.round(C - L + t), R[3]) : L + C > G - t && (R[1] = Math.max(Math.round(L + C - G + t), R[1])), 0 > x - K / 2 ? R[0] = Math.max(Math.round(-x + K / 2), R[0]) : x + K / 2 > z && (R[2] = Math.max(Math.round(x + K / 2 - z), R[2])))
            }), 0 === B(R) || this.verifyDataLabelOverflow(R)) &&
            (this.placeDataLabels(), g && q && F(this.points, function (a) {
                var b;
                d = a.connector;
                if ((v = a.dataLabel) && v._pos && a.visible) {
                    M = v._attr.visibility;
                    if (b = !d)a.connector = d = m.renderer.path().addClass("highcharts-data-label-connector highcharts-color-" + a.colorIndex).add(e.dataLabelsGroup);
                    d[b ? "attr" : "animate"]({d: e.connectorPath(a.labelPos)});
                    d.attr("visibility", M)
                } else d && (a.connector = d.destroy())
            }))
        }, C.pie.prototype.connectorPath = function (a) {
            var b = a.x, c = a.y;
            return l(this.options.dataLabels.softConnector, !0) ? ["M",
                b + ("left" === a[6] ? 5 : -5), c, "C", b, c, 2 * a[2] - a[4], 2 * a[3] - a[5], a[2], a[3], "L", a[4], a[5]] : ["M", b + ("left" === a[6] ? 5 : -5), c, "L", a[2], a[3], "L", a[4], a[5]]
        }, C.pie.prototype.placeDataLabels = function () {
            F(this.points, function (a) {
                var b = a.dataLabel;
                b && a.visible && ((a = b._pos) ? (b.attr(b._attr), b[b.moved ? "animate" : "attr"](a), b.moved = !0) : b && b.attr({y: -9999}))
            })
        }, C.pie.prototype.alignDataLabel = n, C.pie.prototype.verifyDataLabelOverflow = function (a) {
            var b = this.center, c = this.options, e = c.center, l = c.minSize || 80, m, n;
            null !== e[0] ?
                m = Math.max(b[2] - Math.max(a[1], a[3]), l) : (m = Math.max(b[2] - a[1] - a[3], l), b[0] += (a[3] - a[1]) / 2);
            null !== e[1] ? m = Math.max(Math.min(m, b[2] - Math.max(a[0], a[2])), l) : (m = Math.max(Math.min(m, b[2] - a[0] - a[2]), l), b[1] += (a[0] - a[2]) / 2);
            m < b[2] ? (b[2] = m, b[3] = Math.min(h(c.innerSize || 0, m), m), this.translate(b), this.drawDataLabels && this.drawDataLabels()) : n = !0;
            return n
        });
        C.column && (C.column.prototype.alignDataLabel = function (a, c, e, h, n) {
            var f = this.chart.inverted, k = a.series, p = a.dlBox || a.shapeArgs, q = l(a.below, a.plotY > l(this.translatedThreshold,
                    k.yAxis.len)), d = l(e.inside, !!this.options.stacking);
            p && (h = m(p), 0 > h.y && (h.height += h.y, h.y = 0), p = h.y + h.height - k.yAxis.len, 0 < p && (h.height -= p), f && (h = {
                x: k.yAxis.len - h.y - h.height,
                y: k.xAxis.len - h.x - h.width,
                width: h.height,
                height: h.width
            }), d || (f ? (h.x += q ? 0 : h.width, h.width = 0) : (h.y += q ? h.height : 0, h.height = 0)));
            e.align = l(e.align, !f || d ? "center" : q ? "right" : "left");
            e.verticalAlign = l(e.verticalAlign, f || d ? "middle" : q ? "top" : "bottom");
            b.prototype.alignDataLabel.call(this, a, c, e, h, n)
        })
    })(K);
    (function (a) {
        var w = a.Chart, B = a.each,
            D = a.pick, F = a.addEvent;
        w.prototype.callbacks.push(function (a) {
            function e() {
                var c = [];
                B(a.series, function (a) {
                    var e = a.options.dataLabels, l = a.dataLabelCollections || ["dataLabel"];
                    (e.enabled || a._hasPointLabels) && !e.allowOverlap && a.visible && B(l, function (e) {
                        B(a.points, function (a) {
                            a[e] && (a[e].labelrank = D(a.labelrank, a.shapeArgs && a.shapeArgs.height), c.push(a[e]))
                        })
                    })
                });
                a.hideOverlappingLabels(c)
            }

            e();
            F(a, "redraw", e)
        });
        w.prototype.hideOverlappingLabels = function (a) {
            var e = a.length, c, m, n, l, h, b, q, w, r, f = function (a,
                                                                       b, c, e, f, h, l, d) {
                return !(f > a + c || f + l < a || h > b + e || h + d < b)
            };
            for (m = 0; m < e; m++)if (c = a[m])c.oldOpacity = c.opacity, c.newOpacity = 1;
            a.sort(function (a, b) {
                return (b.labelrank || 0) - (a.labelrank || 0)
            });
            for (m = 0; m < e; m++)for (n = a[m], c = m + 1; c < e; ++c)if (l = a[c], n && l && n.placed && l.placed && 0 !== n.newOpacity && 0 !== l.newOpacity && (h = n.alignAttr, b = l.alignAttr, q = n.parentGroup, w = l.parentGroup, r = 2 * (n.box ? 0 : n.padding), h = f(h.x + q.translateX, h.y + q.translateY, n.width - r, n.height - r, b.x + w.translateX, b.y + w.translateY, l.width - r, l.height - r)))(n.labelrank <
            l.labelrank ? n : l).newOpacity = 0;
            B(a, function (a) {
                var b, c;
                a && (c = a.newOpacity, a.oldOpacity !== c && a.placed && (c ? a.show(!0) : b = function () {
                    a.hide()
                }, a.alignAttr.opacity = c, a[a.isOld ? "animate" : "attr"](a.alignAttr, null, b)), a.isOld = !0)
            })
        }
    })(K);
    (function (a) {
        var w = a.addEvent, B = a.Chart, D = a.createElement, F = a.css, q = a.defaultOptions, e = a.defaultPlotOptions, c = a.each, m = a.extend, n = a.fireEvent, l = a.hasTouch, h = a.inArray, b = a.isObject, C = a.Legend, y = a.merge, r = a.pick, f = a.Point, k = a.Series, p = a.seriesTypes, H = a.svg;
        a = a.TrackerMixin =
        {
            drawTrackerPoint: function () {
                var a = this, b = a.chart, e = b.pointer, f = function (a) {
                    for (var c = a.target, d; c && !d;)d = c.point, c = c.parentNode;
                    if (void 0 !== d && d !== b.hoverPoint)d.onMouseOver(a)
                };
                c(a.points, function (a) {
                    a.graphic && (a.graphic.element.point = a);
                    a.dataLabel && (a.dataLabel.div ? a.dataLabel.div.point = a : a.dataLabel.element.point = a)
                });
                a._hasTracking || (c(a.trackerGroups, function (b) {
                    if (a[b] && (a[b].addClass("highcharts-tracker").on("mouseover", f).on("mouseout", function (a) {
                            e.onTrackerMouseOut(a)
                        }), l))a[b].on("touchstart",
                        f)
                }), a._hasTracking = !0)
            }, drawTrackerGraph: function () {
            var a = this, b = a.options.trackByArea, e = [].concat(b ? a.areaPath : a.graphPath), f = e.length, d = a.chart, h = d.pointer, k = d.renderer, m = d.options.tooltip.snap, n = a.tracker, g, p = function () {
                if (d.hoverSeries !== a)a.onMouseOver()
            }, q = "rgba(192,192,192," + (H ? .0001 : .002) + ")";
            if (f && !b)for (g = f + 1; g--;)"M" === e[g] && e.splice(g + 1, 0, e[g + 1] - m, e[g + 2], "L"), (g && "M" === e[g] || g === f) && e.splice(g, 0, "L", e[g - 2] + m, e[g - 1]);
            n ? n.attr({d: e}) : a.graph && (a.tracker = k.path(e).attr({
                "stroke-linejoin": "round",
                visibility: a.visible ? "visible" : "hidden",
                stroke: q,
                fill: b ? q : "none",
                "stroke-width": a.graph.strokeWidth() + (b ? 0 : 2 * m),
                zIndex: 2
            }).add(a.group), c([a.tracker, a.markerGroup], function (a) {
                a.addClass("highcharts-tracker").on("mouseover", p).on("mouseout", function (a) {
                    h.onTrackerMouseOut(a)
                });
                if (l)a.on("touchstart", p)
            }))
        }
        };
        p.column && (p.column.prototype.drawTracker = a.drawTrackerPoint);
        p.pie && (p.pie.prototype.drawTracker = a.drawTrackerPoint);
        p.scatter && (p.scatter.prototype.drawTracker = a.drawTrackerPoint);
        m(C.prototype,
            {
                setItemEvents: function (a, b, c) {
                    var e = this.chart, d = "highcharts-legend-" + (a.series ? "point" : "series") + "-active";
                    (c ? b : a.legendGroup).on("mouseover", function () {
                        a.setState("hover");
                        e.seriesGroup.addClass(d)
                    }).on("mouseout", function () {
                        e.seriesGroup.removeClass(d);
                        a.setState()
                    }).on("click", function (b) {
                        var c = function () {
                            a.setVisible && a.setVisible()
                        };
                        b = {browserEvent: b};
                        a.firePointEvent ? a.firePointEvent("legendItemClick", b, c) : n(a, "legendItemClick", b, c)
                    })
                }, createCheckboxForItem: function (a) {
                a.checkbox = D("input",
                    {
                        type: "checkbox",
                        checked: a.selected,
                        defaultChecked: a.selected
                    }, this.options.itemCheckboxStyle, this.chart.container);
                w(a.checkbox, "click", function (b) {
                    n(a.series || a, "checkboxClick", {checked: b.target.checked, item: a}, function () {
                        a.select()
                    })
                })
            }
            });
        m(B.prototype, {
            showResetZoom: function () {
                var a = this, b = q.lang, c = a.options.chart.resetZoomButton, e = c.theme, d = e.states, f = "chart" === c.relativeTo ? null : "plotBox";
                this.resetZoomButton = a.renderer.button(b.resetZoom, null, null, function () {
                    a.zoomOut()
                }, e, d && d.hover).attr({
                    align: c.position.align,
                    title: b.resetZoomTitle
                }).addClass("highcharts-reset-zoom").add().align(c.position, !1, f)
            }, zoomOut: function () {
                var a = this;
                n(a, "selection", {resetSelection: !0}, function () {
                    a.zoom()
                })
            }, zoom: function (a) {
                var e, f = this.pointer, h = !1, d;
                !a || a.resetSelection ? c(this.axes, function (a) {
                    e = a.zoom()
                }) : c(a.xAxis.concat(a.yAxis), function (a) {
                    var b = a.axis;
                    f[b.isXAxis ? "zoomX" : "zoomY"] && (e = b.zoom(a.min, a.max), b.displayBtn && (h = !0))
                });
                d = this.resetZoomButton;
                h && !d ? this.showResetZoom() : !h && b(d) && (this.resetZoomButton = d.destroy());
                e && this.redraw(r(this.options.chart.animation, a && a.animation, 100 > this.pointCount))
            }, pan: function (a, b) {
                var e = this, f = e.hoverPoints, d;
                f && c(f, function (a) {
                    a.setState()
                });
                c("xy" === b ? [1, 0] : [1], function (b) {
                    b = e[b ? "xAxis" : "yAxis"][0];
                    var c = b.horiz, f = a[c ? "chartX" : "chartY"], c = c ? "mouseDownX" : "mouseDownY", h = e[c], g = (b.pointRange || 0) / 2, k = b.getExtremes(), l = b.toValue(h - f, !0) + g, g = b.toValue(h + b.len - f, !0) - g, m = g < l, h = m ? g : l, l = m ? l : g, g = Math.min(k.dataMin, k.min) - h, k = l - Math.max(k.dataMax, k.max);
                    b.series.length && 0 > g && 0 > k && (b.setExtremes(h,
                        l, !1, !1, {trigger: "pan"}), d = !0);
                    e[c] = f
                });
                d && e.redraw(!1);
                F(e.container, {cursor: "move"})
            }
        });
        m(f.prototype, {
            select: function (a, b) {
                var e = this, f = e.series, d = f.chart;
                a = r(a, !e.selected);
                e.firePointEvent(a ? "select" : "unselect", {accumulate: b}, function () {
                    e.selected = e.options.selected = a;
                    f.options.data[h(e, f.data)] = e.options;
                    e.setState(a && "select");
                    b || c(d.getSelectedPoints(), function (a) {
                        a.selected && a !== e && (a.selected = a.options.selected = !1, f.options.data[h(a, f.data)] = a.options, a.setState(""), a.firePointEvent("unselect"))
                    })
                })
            },
            onMouseOver: function (a, b) {
                var c = this.series, e = c.chart, d = e.tooltip, f = e.hoverPoint;
                if (this.series) {
                    if (!b) {
                        if (f && f !== this)f.onMouseOut();
                        if (e.hoverSeries !== c)c.onMouseOver();
                        e.hoverPoint = this
                    }
                    !d || d.shared && !c.noSharedTooltip ? d || this.setState("hover") : (this.setState("hover"), d.refresh(this, a));
                    this.firePointEvent("mouseOver")
                }
            }, onMouseOut: function () {
                var a = this.series.chart, b = a.hoverPoints;
                this.firePointEvent("mouseOut");
                b && -1 !== h(this, b) || (this.setState(), a.hoverPoint = null)
            }, importEvents: function () {
                if (!this.hasImportedEvents) {
                    var a =
                        y(this.series.options.point, this.options).events, b;
                    this.events = a;
                    for (b in a)w(this, b, a[b]);
                    this.hasImportedEvents = !0
                }
            }, setState: function (a, b) {
                var c = Math.floor(this.plotX), f = this.plotY, d = this.series, h = d.options.states[a] || {}, k = e[d.type].marker && d.options.marker, l = k && !1 === k.enabled, m = k && k.states && k.states[a] || {}, g = !1 === m.enabled, n = d.stateMarkerGraphic, p = this.marker || {}, q = d.chart, t = d.halo, w, y = k && d.markerAttribs;
                a = a || "";
                if (!(a === this.state && !b || this.selected && "select" !== a || !1 === h.enabled || a && (g || l && !1 ===
                    m.enabled) || a && p.states && p.states[a] && !1 === p.states[a].enabled)) {
                    y && (w = d.markerAttribs(this, a));
                    if (this.graphic)this.state && this.graphic.removeClass("highcharts-point-" + this.state), a && this.graphic.addClass("highcharts-point-" + a), w && this.graphic.animate(w, r(q.options.chart.animation, m.animation, k.animation)), n && n.hide(); else {
                        if (a && m)if (k = p.symbol || d.symbol, n && n.currentSymbol !== k && (n = n.destroy()), n)n[b ? "animate" : "attr"]({
                            x: w.x,
                            y: w.y
                        }); else k && (d.stateMarkerGraphic = n = q.renderer.symbol(k, w.x, w.y, w.width,
                            w.height).add(d.markerGroup), n.currentSymbol = k);
                        n && (n[a && q.isInsidePlot(c, f, q.inverted) ? "show" : "hide"](), n.element.point = this)
                    }
                    (c = h.halo) && c.size ? (t || (d.halo = t = q.renderer.path().add(y ? d.markerGroup : d.group)), t[b ? "animate" : "attr"]({d: this.haloPath(c.size)}), t.attr({"class": "highcharts-halo highcharts-color-" + r(this.colorIndex, d.colorIndex)}), t.point = this) : t && t.point && t.point.haloPath && t.animate({d: t.point.haloPath(0)});
                    this.state = a
                }
            }, haloPath: function (a) {
                return this.series.chart.renderer.symbols.circle(Math.floor(this.plotX) -
                    a, this.plotY - a, 2 * a, 2 * a)
            }
        });
        m(k.prototype, {
            onMouseOver: function () {
                var a = this.chart, b = a.hoverSeries;
                if (b && b !== this)b.onMouseOut();
                this.options.events.mouseOver && n(this, "mouseOver");
                this.setState("hover");
                a.hoverSeries = this
            }, onMouseOut: function () {
                var a = this.options, b = this.chart, c = b.tooltip, e = b.hoverPoint;
                b.hoverSeries = null;
                if (e)e.onMouseOut();
                this && a.events.mouseOut && n(this, "mouseOut");
                !c || a.stickyTracking || c.shared && !this.noSharedTooltip || c.hide();
                this.setState()
            }, setState: function (a) {
                var b = this;
                a =
                    a || "";
                b.state !== a && (c([b.group, b.markerGroup], function (c) {
                    c && (b.state && c.removeClass("highcharts-series-" + b.state), a && c.addClass("highcharts-series-" + a))
                }), b.state = a)
            }, setVisible: function (a, b) {
                var e = this, f = e.chart, d = e.legendItem, h, k = f.options.chart.ignoreHiddenSeries, l = e.visible;
                h = (e.visible = a = e.options.visible = e.userOptions.visible = void 0 === a ? !l : a) ? "show" : "hide";
                c(["group", "dataLabelsGroup", "markerGroup", "tracker", "tt"], function (a) {
                    if (e[a])e[a][h]()
                });
                if (f.hoverSeries === e || (f.hoverPoint && f.hoverPoint.series) ===
                    e)e.onMouseOut();
                d && f.legend.colorizeItem(e, a);
                e.isDirty = !0;
                e.options.stacking && c(f.series, function (a) {
                    a.options.stacking && a.visible && (a.isDirty = !0)
                });
                c(e.linkedSeries, function (b) {
                    b.setVisible(a, !1)
                });
                k && (f.isDirtyBox = !0);
                !1 !== b && f.redraw();
                n(e, h)
            }, show: function () {
                this.setVisible(!0)
            }, hide: function () {
                this.setVisible(!1)
            }, select: function (a) {
                this.selected = a = void 0 === a ? !this.selected : a;
                this.checkbox && (this.checkbox.checked = a);
                n(this, a ? "select" : "unselect")
            }, drawTracker: a.drawTrackerGraph
        })
    })(K);
    (function (a) {
        var w =
            a.Chart, B = a.each, D = a.inArray, F = a.isObject, q = a.pick, e = a.splat;
        w.prototype.setResponsive = function (a) {
            var c = this.options.responsive;
            c && c.rules && B(c.rules, function (c) {
                this.matchResponsiveRule(c, a)
            }, this)
        };
        w.prototype.matchResponsiveRule = function (c, e) {
            var m = this.respRules, l = c.condition, h;
            h = l.callback || function () {
                    return this.chartWidth <= q(l.maxWidth, Number.MAX_VALUE) && this.chartHeight <= q(l.maxHeight, Number.MAX_VALUE) && this.chartWidth >= q(l.minWidth, 0) && this.chartHeight >= q(l.minHeight, 0)
                };
            void 0 === c._id &&
            (c._id = a.uniqueKey());
            h = h.call(this);
            !m[c._id] && h ? c.chartOptions && (m[c._id] = this.currentOptions(c.chartOptions), this.update(c.chartOptions, e)) : m[c._id] && !h && (this.update(m[c._id], e), delete m[c._id])
        };
        w.prototype.currentOptions = function (a) {
            function c(a, h, b) {
                var l, m;
                for (l in a)if (-1 < D(l, ["series", "xAxis", "yAxis"]))for (a[l] = e(a[l]), b[l] = [], m = 0; m < a[l].length; m++)b[l][m] = {}, c(a[l][m], h[l][m], b[l][m]); else F(a[l]) ? (b[l] = {}, c(a[l], h[l] || {}, b[l])) : b[l] = h[l] || null
            }

            var n = {};
            c(a, this.options, n);
            return n
        }
    })(K);
    return K
});