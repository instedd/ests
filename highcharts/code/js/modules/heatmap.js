/*
 Highcharts JS v5.0.6 (2016-12-07)

 (c) 2009-2016 Torstein Honsi

 License: www.highcharts.com/license
 */
(function (m) {
    "object" === typeof module && module.exports ? module.exports = m : m(Highcharts)
})(function (m) {
    (function (b) {
        var k = b.Axis, r = b.Chart, h = b.color, g, e = b.each, w = b.extend, x = b.isNumber, p = b.Legend, t = b.LegendSymbolMixin, y = b.noop, q = b.merge, v = b.pick, u = b.wrap;
        g = b.ColorAxis = function () {
            this.init.apply(this, arguments)
        };
        w(g.prototype, k.prototype);
        w(g.prototype, {
            defaultColorAxisOptions: {
                lineWidth: 0,
                minPadding: 0,
                maxPadding: 0,
                gridLineWidth: 1,
                tickPixelInterval: 72,
                startOnTick: !0,
                endOnTick: !0,
                offset: 0,
                marker: {
                    animation: {duration: 50},
                    width: .01
                },
                labels: {overflow: "justify"},
                minColor: "#e6ebf5",
                maxColor: "#003399",
                tickLength: 5,
                showInLegend: !0
            },
            keepProps: ["legendGroup", "legendItem", "legendSymbol"].concat(k.prototype.keepProps),
            init: function (a, c) {
                var d = "vertical" !== a.options.legend.layout, f;
                this.coll = "colorAxis";
                f = q(this.defaultColorAxisOptions, {side: d ? 2 : 1, reversed: !d}, c, {
                    opposite: !d,
                    showEmpty: !1,
                    title: null
                });
                k.prototype.init.call(this, a, f);
                c.dataClasses && this.initDataClasses(c);
                this.initStops(c);
                this.horiz = d;
                this.zoomEnabled = !1;
                this.defaultLegendLength =
                    200
            },
            tweenColors: function (a, c, d) {
                var f;
                c.rgba.length && a.rgba.length ? (a = a.rgba, c = c.rgba, f = 1 !== c[3] || 1 !== a[3], a = (f ? "rgba(" : "rgb(") + Math.round(c[0] + (a[0] - c[0]) * (1 - d)) + "," + Math.round(c[1] + (a[1] - c[1]) * (1 - d)) + "," + Math.round(c[2] + (a[2] - c[2]) * (1 - d)) + (f ? "," + (c[3] + (a[3] - c[3]) * (1 - d)) : "") + ")") : a = c.input || "none";
                return a
            },
            initDataClasses: function (a) {
                var c = this, d, f = 0, n = this.chart.options.chart.colorCount, b = this.options, l = a.dataClasses.length;
                this.dataClasses = d = [];
                this.legendItems = [];
                e(a.dataClasses, function (a,
                                           e) {
                    a = q(a);
                    d.push(a);
                    a.color || ("category" === b.dataClassColor ? (a.colorIndex = f, f++, f === n && (f = 0)) : a.color = c.tweenColors(h(b.minColor), h(b.maxColor), 2 > l ? .5 : e / (l - 1)))
                })
            },
            initStops: function (a) {
                this.stops = a.stops || [[0, this.options.minColor], [1, this.options.maxColor]];
                e(this.stops, function (a) {
                    a.color = h(a[1])
                })
            },
            setOptions: function (a) {
                k.prototype.setOptions.call(this, a);
                this.options.crosshair = this.options.marker
            },
            setAxisSize: function () {
                var a = this.legendSymbol, c = this.chart, d = c.options.legend || {}, f, n;
                a ? (this.left =
                    d = a.attr("x"), this.top = f = a.attr("y"), this.width = n = a.attr("width"), this.height = a = a.attr("height"), this.right = c.chartWidth - d - n, this.bottom = c.chartHeight - f - a, this.len = this.horiz ? n : a, this.pos = this.horiz ? d : f) : this.len = (this.horiz ? d.symbolWidth : d.symbolHeight) || this.defaultLegendLength
            },
            toColor: function (a, c) {
                var d = this.stops, f, n, b = this.dataClasses, l, e;
                if (b)for (e = b.length; e--;) {
                    if (l = b[e], f = l.from, d = l.to, (void 0 === f || a >= f) && (void 0 === d || a <= d)) {
                        n = l.color;
                        c && (c.dataClass = e, c.colorIndex = l.colorIndex);
                        break
                    }
                } else {
                    this.isLog &&
                    (a = this.val2lin(a));
                    a = 1 - (this.max - a) / (this.max - this.min || 1);
                    for (e = d.length; e-- && !(a > d[e][0]););
                    f = d[e] || d[e + 1];
                    d = d[e + 1] || f;
                    a = 1 - (d[0] - a) / (d[0] - f[0] || 1);
                    n = this.tweenColors(f.color, d.color, a)
                }
                return n
            },
            getOffset: function () {
                var a = this.legendGroup, c = this.chart.axisOffset[this.side];
                a && (this.axisParent = a, k.prototype.getOffset.call(this), this.added || (this.added = !0, this.labelLeft = 0, this.labelRight = this.width), this.chart.axisOffset[this.side] = c)
            },
            setLegendColor: function () {
                var a, c = this.options, d = this.reversed;
                a = d ? 1 : 0;
                d = d ? 0 : 1;
                a = this.horiz ? [a, 0, d, 0] : [0, d, 0, a];
                this.legendColor = {
                    linearGradient: {x1: a[0], y1: a[1], x2: a[2], y2: a[3]},
                    stops: c.stops || [[0, c.minColor], [1, c.maxColor]]
                }
            },
            drawLegendSymbol: function (a, c) {
                var d = a.padding, f = a.options, b = this.horiz, e = v(f.symbolWidth, b ? this.defaultLegendLength : 12), l = v(f.symbolHeight, b ? 12 : this.defaultLegendLength), g = v(f.labelPadding, b ? 16 : 30), f = v(f.itemDistance, 10);
                this.setLegendColor();
                c.legendSymbol = this.chart.renderer.rect(0, a.baseline - 11, e, l).attr({zIndex: 1}).add(c.legendGroup);
                this.legendItemWidth = e + d + (b ? f : g);
                this.legendItemHeight = l + d + (b ? g : 0)
            },
            setState: y,
            visible: !0,
            setVisible: y,
            getSeriesExtremes: function () {
                var a;
                this.series.length && (a = this.series[0], this.dataMin = a.valueMin, this.dataMax = a.valueMax)
            },
            drawCrosshair: function (a, c) {
                var d = c && c.plotX, b = c && c.plotY, e, g = this.pos, l = this.len;
                c && (e = this.toPixels(c[c.series.colorKey]), e < g ? e = g - 2 : e > g + l && (e = g + l + 2), c.plotX = e, c.plotY = this.len - e, k.prototype.drawCrosshair.call(this, a, c), c.plotX = d, c.plotY = b, this.cross && this.cross.addClass("highcharts-coloraxis-marker").add(this.legendGroup))
            },
            getPlotLinePath: function (a, c, d, b, e) {
                return x(e) ? this.horiz ? ["M", e - 4, this.top - 6, "L", e + 4, this.top - 6, e, this.top, "Z"] : ["M", this.left, e, "L", this.left - 6, e + 6, this.left - 6, e - 6, "Z"] : k.prototype.getPlotLinePath.call(this, a, c, d, b)
            },
            update: function (a, c) {
                var d = this.chart, b = d.legend;
                e(this.series, function (a) {
                    a.isDirtyData = !0
                });
                a.dataClasses && b.allItems && (e(b.allItems, function (a) {
                    a.isDataClass && a.legendGroup.destroy()
                }), d.isDirtyLegend = !0);
                d.options[this.coll] = q(this.userOptions, a);
                k.prototype.update.call(this, a,
                    c);
                this.legendItem && (this.setLegendColor(), b.colorizeItem(this, !0))
            },
            getDataClassLegendSymbols: function () {
                var a = this, c = this.chart, d = this.legendItems, f = c.options.legend, g = f.valueDecimals, u = f.valueSuffix || "", l;
                d.length || e(this.dataClasses, function (f, p) {
                    var k = !0, q = f.from, h = f.to;
                    l = "";
                    void 0 === q ? l = "\x3c " : void 0 === h && (l = "\x3e ");
                    void 0 !== q && (l += b.numberFormat(q, g) + u);
                    void 0 !== q && void 0 !== h && (l += " - ");
                    void 0 !== h && (l += b.numberFormat(h, g) + u);
                    d.push(w({
                        chart: c, name: l, options: {}, drawLegendSymbol: t.drawRectangle,
                        visible: !0, setState: y, isDataClass: !0, setVisible: function () {
                            k = this.visible = !k;
                            e(a.series, function (a) {
                                e(a.points, function (a) {
                                    a.dataClass === p && a.setVisible(k)
                                })
                            });
                            c.legend.colorizeItem(this, k)
                        }
                    }, f))
                });
                return d
            },
            name: ""
        });
        e(["fill", "stroke"], function (a) {
            b.Fx.prototype[a + "Setter"] = function () {
                this.elem.attr(a, g.prototype.tweenColors(h(this.start), h(this.end), this.pos), null, !0)
            }
        });
        u(r.prototype, "getAxes", function (a) {
            var c = this.options.colorAxis;
            a.call(this);
            this.colorAxis = [];
            c && new g(this, c)
        });
        u(p.prototype,
            "getAllItems", function (a) {
                var c = [], d = this.chart.colorAxis[0];
                d && d.options && (d.options.showInLegend && (d.options.dataClasses ? c = c.concat(d.getDataClassLegendSymbols()) : c.push(d)), e(d.series, function (a) {
                    a.options.showInLegend = !1
                }));
                return c.concat(a.call(this))
            });
        u(p.prototype, "colorizeItem", function (a, c, d) {
            a.call(this, c, d);
            d && c.legendColor && c.legendSymbol.attr({fill: c.legendColor})
        })
    })(m);
    (function (b) {
        var k = b.defined, r = b.each, h = b.noop;
        b.colorPointMixin = {
            isValid: function () {
                return null !== this.value
            }, setVisible: function (b) {
                var e =
                    this, g = b ? "show" : "hide";
                r(["graphic", "dataLabel"], function (b) {
                    if (e[b])e[b][g]()
                })
            }, setState: function (g) {
                b.Point.prototype.setState.call(this, g);
                this.graphic && this.graphic.attr({zIndex: "hover" === g ? 1 : 0})
            }
        };
        b.colorSeriesMixin = {
            pointArrayMap: ["value"],
            axisTypes: ["xAxis", "yAxis", "colorAxis"],
            optionalAxis: "colorAxis",
            trackerGroups: ["group", "markerGroup", "dataLabelsGroup"],
            getSymbol: h,
            parallelArrays: ["x", "y", "value"],
            colorKey: "value",
            translateColors: function () {
                var b = this, e = this.options.nullColor, k = this.colorAxis,
                    h = this.colorKey;
                r(this.data, function (g) {
                    var t = g[h];
                    if (t = g.options.color || (g.isNull ? e : k && void 0 !== t ? k.toColor(t, g) : g.color || b.color))g.color = t
                })
            },
            colorAttribs: function (b) {
                var e = {};
                k(b.color) && (e[this.colorProp || "fill"] = b.color);
                return e
            }
        }
    })(m);
    (function (b) {
        var k = b.colorPointMixin, r = b.each, h = b.merge, g = b.noop, e = b.pick, m = b.Series, x = b.seriesType, p = b.seriesTypes;
        x("heatmap", "scatter", {
                animation: !1,
                borderWidth: 0,
                dataLabels: {
                    formatter: function () {
                        return this.point.value
                    }, inside: !0, verticalAlign: "middle", crop: !1,
                    overflow: !1, padding: 0
                },
                marker: null,
                pointRange: null,
                tooltip: {pointFormat: "{point.x}, {point.y}: {point.value}\x3cbr/\x3e"},
                states: {normal: {animation: !0}, hover: {halo: !1, brightness: .2}}
            }, h(b.colorSeriesMixin, {
                pointArrayMap: ["y", "value"],
                hasPointSpecificOptions: !0,
                supportsDrilldown: !0,
                getExtremesFromAll: !0,
                directTouch: !0,
                init: function () {
                    var b;
                    p.scatter.prototype.init.apply(this, arguments);
                    b = this.options;
                    b.pointRange = e(b.pointRange, b.colsize || 1);
                    this.yAxis.axisPointRange = b.rowsize || 1
                },
                translate: function () {
                    var b =
                        this.options, e = this.xAxis, g = this.yAxis, k = function (b, a, c) {
                        return Math.min(Math.max(a, b), c)
                    };
                    this.generatePoints();
                    r(this.points, function (h) {
                        var a = (b.colsize || 1) / 2, c = (b.rowsize || 1) / 2, d = k(Math.round(e.len - e.translate(h.x - a, 0, 1, 0, 1)), -e.len, 2 * e.len), a = k(Math.round(e.len - e.translate(h.x + a, 0, 1, 0, 1)), -e.len, 2 * e.len), f = k(Math.round(g.translate(h.y - c, 0, 1, 0, 1)), -g.len, 2 * g.len), c = k(Math.round(g.translate(h.y + c, 0, 1, 0, 1)), -g.len, 2 * g.len);
                        h.plotX = h.clientX = (d + a) / 2;
                        h.plotY = (f + c) / 2;
                        h.shapeType = "rect";
                        h.shapeArgs =
                        {x: Math.min(d, a), y: Math.min(f, c), width: Math.abs(a - d), height: Math.abs(c - f)}
                    });
                    this.translateColors()
                },
                drawPoints: function () {
                    p.column.prototype.drawPoints.call(this);
                    r(this.points, function (b) {
                        b.graphic.css(this.colorAttribs(b))
                    }, this)
                },
                animate: g,
                getBox: g,
                drawLegendSymbol: b.LegendSymbolMixin.drawRectangle,
                alignDataLabel: p.column.prototype.alignDataLabel,
                getExtremes: function () {
                    m.prototype.getExtremes.call(this, this.valueData);
                    this.valueMin = this.dataMin;
                    this.valueMax = this.dataMax;
                    m.prototype.getExtremes.call(this)
                }
            }),
            k)
    })(m)
});
