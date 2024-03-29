/*
 Highcharts JS v5.0.6 (2016-12-07)
 Highcharts Drilldown module

 Author: Torstein Honsi
 License: www.highcharts.com/license

 */
(function (n) {
    "object" === typeof module && module.exports ? module.exports = n : n(Highcharts)
})(function (n) {
    (function (f) {
        function n(a, b, d) {
            var c;
            b.rgba.length && a.rgba.length ? (a = a.rgba, b = b.rgba, c = 1 !== b[3] || 1 !== a[3], a = (c ? "rgba(" : "rgb(") + Math.round(b[0] + (a[0] - b[0]) * (1 - d)) + "," + Math.round(b[1] + (a[1] - b[1]) * (1 - d)) + "," + Math.round(b[2] + (a[2] - b[2]) * (1 - d)) + (c ? "," + (b[3] + (a[3] - b[3]) * (1 - d)) : "") + ")") : a = b.input || "none";
            return a
        }

        var C = f.noop, v = f.color, w = f.defaultOptions, l = f.each, p = f.extend, H = f.format, y = f.pick, x = f.wrap, q = f.Chart,
            t = f.seriesTypes, D = t.pie, r = t.column, E = f.Tick, z = f.fireEvent, F = f.inArray, G = 1;
        l(["fill", "stroke"], function (a) {
            f.Fx.prototype[a + "Setter"] = function () {
                this.elem.attr(a, n(v(this.start), v(this.end), this.pos), null, !0)
            }
        });
        p(w.lang, {drillUpText: "\u25c1 Back to {series.name}"});
        w.drilldown = {animation: {duration: 500}, drillUpButton: {position: {align: "right", x: -10, y: 10}}};
        f.SVGRenderer.prototype.Element.prototype.fadeIn = function (a) {
            this.attr({opacity: .1, visibility: "inherit"}).animate({opacity: y(this.newOpacity, 1)},
                a || {duration: 250})
        };
        q.prototype.addSeriesAsDrilldown = function (a, b) {
            this.addSingleSeriesAsDrilldown(a, b);
            this.applyDrilldown()
        };
        q.prototype.addSingleSeriesAsDrilldown = function (a, b) {
            var d = a.series, c = d.xAxis, e = d.yAxis, h, g = [], k = [], u, m, A;
            A = {colorIndex: y(a.colorIndex, d.colorIndex)};
            this.drilldownLevels || (this.drilldownLevels = []);
            u = d.options._levelNumber || 0;
            (m = this.drilldownLevels[this.drilldownLevels.length - 1]) && m.levelNumber !== u && (m = void 0);
            b = p(p({_ddSeriesId: G++}, A), b);
            h = F(a, d.points);
            l(d.chart.series,
                function (a) {
                    a.xAxis !== c || a.isDrilling || (a.options._ddSeriesId = a.options._ddSeriesId || G++, a.options._colorIndex = a.userOptions._colorIndex, a.options._levelNumber = a.options._levelNumber || u, m ? (g = m.levelSeries, k = m.levelSeriesOptions) : (g.push(a), k.push(a.options)))
                });
            a = p({
                levelNumber: u,
                seriesOptions: d.options,
                levelSeriesOptions: k,
                levelSeries: g,
                shapeArgs: a.shapeArgs,
                bBox: a.graphic ? a.graphic.getBBox() : {},
                color: a.isNull ? (new f.Color(v)).setOpacity(0).get() : v,
                lowerSeriesOptions: b,
                pointOptions: d.options.data[h],
                pointIndex: h,
                oldExtremes: {xMin: c && c.userMin, xMax: c && c.userMax, yMin: e && e.userMin, yMax: e && e.userMax}
            }, A);
            this.drilldownLevels.push(a);
            b = a.lowerSeries = this.addSeries(b, !1);
            b.options._levelNumber = u + 1;
            c && (c.oldPos = c.pos, c.userMin = c.userMax = null, e.userMin = e.userMax = null);
            d.type === b.type && (b.animate = b.animateDrilldown || C, b.options.animation = !0)
        };
        q.prototype.applyDrilldown = function () {
            var a = this.drilldownLevels, b;
            a && 0 < a.length && (b = a[a.length - 1].levelNumber, l(this.drilldownLevels, function (a) {
                a.levelNumber ===
                b && l(a.levelSeries, function (a) {
                    a.options && a.options._levelNumber === b && a.remove(!1)
                })
            }));
            this.redraw();
            this.showDrillUpButton()
        };
        q.prototype.getDrilldownBackText = function () {
            var a = this.drilldownLevels;
            if (a && 0 < a.length)return a = a[a.length - 1], a.series = a.seriesOptions, H(this.options.lang.drillUpText, a)
        };
        q.prototype.showDrillUpButton = function () {
            var a = this, b = this.getDrilldownBackText(), d = a.options.drilldown.drillUpButton, c, e;
            this.drillUpButton ? this.drillUpButton.attr({text: b}).align() : (e = (c = d.theme) && c.states,
                this.drillUpButton = this.renderer.button(b, null, null, function () {
                    a.drillUp()
                }, c, e && e.hover, e && e.select).addClass("highcharts-drillup-button").attr({
                    align: d.position.align,
                    zIndex: 7
                }).add().align(d.position, !1, d.relativeTo || "plotBox"))
        };
        q.prototype.drillUp = function () {
            for (var a = this, b = a.drilldownLevels, d = b[b.length - 1].levelNumber, c = b.length, e = a.series, h, g, k, f, m = function (c) {
                var b;
                l(e, function (a) {
                    a.options._ddSeriesId === c._ddSeriesId && (b = a)
                });
                b = b || a.addSeries(c, !1);
                b.type === k.type && b.animateDrillupTo && (b.animate =
                    b.animateDrillupTo);
                c === g.seriesOptions && (f = b)
            }; c--;)if (g = b[c], g.levelNumber === d) {
                b.pop();
                k = g.lowerSeries;
                if (!k.chart)for (h = e.length; h--;)if (e[h].options.id === g.lowerSeriesOptions.id && e[h].options._levelNumber === d + 1) {
                    k = e[h];
                    break
                }
                k.xData = [];
                l(g.levelSeriesOptions, m);
                z(a, "drillup", {seriesOptions: g.seriesOptions});
                f.type === k.type && (f.drilldownLevel = g, f.options.animation = a.options.drilldown.animation, k.animateDrillupFrom && k.chart && k.animateDrillupFrom(g));
                f.options._levelNumber = d;
                k.remove(!1);
                f.xAxis &&
                (h = g.oldExtremes, f.xAxis.setExtremes(h.xMin, h.xMax, !1), f.yAxis.setExtremes(h.yMin, h.yMax, !1))
            }
            z(a, "drillupall");
            this.redraw();
            0 === this.drilldownLevels.length ? this.drillUpButton = this.drillUpButton.destroy() : this.drillUpButton.attr({text: this.getDrilldownBackText()}).align();
            this.ddDupes.length = []
        };
        r.prototype.supportsDrilldown = !0;
        r.prototype.animateDrillupTo = function (a) {
            if (!a) {
                var b = this, d = b.drilldownLevel;
                l(this.points, function (a) {
                    a.graphic && a.graphic.hide();
                    a.dataLabel && a.dataLabel.hide();
                    a.connector &&
                    a.connector.hide()
                });
                setTimeout(function () {
                    b.points && l(b.points, function (a, b) {
                        b = b === (d && d.pointIndex) ? "show" : "fadeIn";
                        var c = "show" === b ? !0 : void 0;
                        if (a.graphic)a.graphic[b](c);
                        if (a.dataLabel)a.dataLabel[b](c);
                        if (a.connector)a.connector[b](c)
                    })
                }, Math.max(this.chart.options.drilldown.animation.duration - 50, 0));
                this.animate = C
            }
        };
        r.prototype.animateDrilldown = function (a) {
            var b = this, d = this.chart.drilldownLevels, c, e = this.chart.options.drilldown.animation, h = this.xAxis;
            a || (l(d, function (a) {
                b.options._ddSeriesId ===
                a.lowerSeriesOptions._ddSeriesId && (c = a.shapeArgs)
            }), c.x += y(h.oldPos, h.pos) - h.pos, l(this.points, function (a) {
                a.graphic && a.graphic.attr(c).animate(p(a.shapeArgs, {fill: a.color || b.color}), e);
                a.dataLabel && a.dataLabel.fadeIn(e)
            }), this.animate = null)
        };
        r.prototype.animateDrillupFrom = function (a) {
            var b = this.chart.options.drilldown.animation, d = this.group, c = this;
            l(c.trackerGroups, function (a) {
                if (c[a])c[a].on("mouseover")
            });
            delete this.group;
            l(this.points, function (c) {
                var e = c.graphic, g = a.shapeArgs, k = function () {
                    e.destroy();
                    d && (d = d.destroy())
                };
                e && (delete c.graphic, b ? e.animate(g, f.merge(b, {complete: k})) : (e.attr(g), k()))
            })
        };
        D && p(D.prototype, {
            supportsDrilldown: !0,
            animateDrillupTo: r.prototype.animateDrillupTo,
            animateDrillupFrom: r.prototype.animateDrillupFrom,
            animateDrilldown: function (a) {
                var b = this.chart.options.drilldown.animation, d = this.chart.drilldownLevels[this.chart.drilldownLevels.length - 1].shapeArgs, c = d.start, e = (d.end - c) / this.points.length;
                a || (l(this.points, function (a, g) {
                    var h = a.shapeArgs;
                    if (a.graphic)a.graphic.attr(f.merge(d,
                        {start: c + g * e, end: c + (g + 1) * e}))[b ? "animate" : "attr"](h, b)
                }), this.animate = null)
            }
        });
        f.Point.prototype.doDrilldown = function (a, b, d) {
            var c = this.series.chart, e = c.options.drilldown, f = (e.series || []).length, g;
            c.ddDupes || (c.ddDupes = []);
            for (; f-- && !g;)e.series[f].id === this.drilldown && -1 === F(this.drilldown, c.ddDupes) && (g = e.series[f], c.ddDupes.push(this.drilldown));
            z(c, "drilldown", {
                point: this,
                seriesOptions: g,
                category: b,
                originalEvent: d,
                points: void 0 !== b && this.series.xAxis.getDDPoints(b).slice(0)
            }, function (b) {
                var c =
                    b.point.series && b.point.series.chart, d = b.seriesOptions;
                c && d && (a ? c.addSingleSeriesAsDrilldown(b.point, d) : c.addSeriesAsDrilldown(b.point, d))
            })
        };
        f.Axis.prototype.drilldownCategory = function (a, b) {
            var d, c, e = this.getDDPoints(a);
            for (d in e)(c = e[d]) && c.series && c.series.visible && c.doDrilldown && c.doDrilldown(!0, a, b);
            this.chart.applyDrilldown()
        };
        f.Axis.prototype.getDDPoints = function (a) {
            var b = [];
            l(this.series, function (d) {
                var c, e = d.xData, f = d.points;
                for (c = 0; c < e.length; c++)if (e[c] === a && d.options.data[c] && d.options.data[c].drilldown) {
                    b.push(f ?
                        f[c] : !0);
                    break
                }
            });
            return b
        };
        E.prototype.drillable = function () {
            var a = this.pos, b = this.label, d = this.axis, c = "xAxis" === d.coll && d.getDDPoints, e = c && d.getDDPoints(a);
            c && (b && e.length ? (b.drillable = !0, b.addClass("highcharts-drilldown-axis-label").on("click", function (b) {
                d.drilldownCategory(a, b)
            })) : b && b.drillable && (b.on("click", null), b.removeClass("highcharts-drilldown-axis-label")))
        };
        x(E.prototype, "addLabel", function (a) {
            a.call(this);
            this.drillable()
        });
        x(f.Point.prototype, "init", function (a, b, d, c) {
            var e = a.call(this,
                b, d, c);
            c = (a = b.xAxis) && a.ticks[c];
            e.drilldown && f.addEvent(e, "click", function (a) {
                b.xAxis && !1 === b.chart.options.drilldown.allowPointDrilldown ? b.xAxis.drilldownCategory(e.x, a) : e.doDrilldown(void 0, void 0, a)
            });
            c && c.drillable();
            return e
        });
        x(f.Series.prototype, "drawDataLabels", function (a) {
            var b = this.chart.options.drilldown.activeDataLabelStyle, d = this.chart.renderer;
            a.call(this);
            l(this.points, function (a) {
                    a.drilldown && a.dataLabel && ("contrast" === b.color && d.getContrast(a.color || this.color), a.dataLabel.addClass("highcharts-drilldown-data-label"))
                },
                this)
        });
        var B, w = function (a) {
            a.call(this);
            l(this.points, function (a) {
                a.drilldown && a.graphic && a.graphic.addClass("highcharts-drilldown-point")
            })
        };
        for (B in t)t[B].prototype.supportsDrilldown && x(t[B].prototype, "drawTracker", w)
    })(n)
});
