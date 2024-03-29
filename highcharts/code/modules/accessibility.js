/*
 Highcharts JS v5.0.6 (2016-12-07)
 Accessibility module

 (c) 2010-2016 Highsoft AS
 Author: Oystein Moseng

 License: www.highcharts.com/license
 */
(function (h) {
    "object" === typeof module && module.exports ? module.exports = h : h(Highcharts)
})(function (h) {
    (function (e) {
        function h(a) {
            for (var b = a.childNodes.length; b--;)a.appendChild(a.childNodes[b])
        }

        function q(a) {
            var b;
            a && a.onclick && (b = l.createEvent("Events"), b.initEvent("click", !0, !1), a.onclick(b))
        }

        var y = e.win, l = y.document, g = e.each, A = e.erase, v = e.addEvent, B = e.removeEvent, w = e.fireEvent, C = e.dateFormat, u = e.merge, r = {
            "default": ["series", "data point", "data points"],
            line: ["line", "data point", "data points"],
            spline: ["line",
                "data point", "data points"],
            area: ["line", "data point", "data points"],
            areaspline: ["line", "data point", "data points"],
            pie: ["pie", "slice", "slices"],
            column: ["column series", "column", "columns"],
            bar: ["bar series", "bar", "bars"],
            scatter: ["scatter series", "data point", "data points"],
            boxplot: ["boxplot series", "box", "boxes"],
            arearange: ["arearange series", "data point", "data points"],
            areasplinerange: ["areasplinerange series", "data point", "data points"],
            bubble: ["bubble series", "bubble", "bubbles"],
            columnrange: ["columnrange series",
                "column", "columns"],
            errorbar: ["errorbar series", "errorbar", "errorbars"],
            funnel: ["funnel", "data point", "data points"],
            pyramid: ["pyramid", "data point", "data points"],
            waterfall: ["waterfall series", "column", "columns"],
            map: ["map", "area", "areas"],
            mapline: ["line", "data point", "data points"],
            mappoint: ["point series", "data point", "data points"],
            mapbubble: ["bubble series", "bubble", "bubbles"]
        }, D = {
            boxplot: " Box plot charts are typically used to display groups of statistical data. Each data point in the chart can have up to 5 values: minimum, lower quartile, median, upper quartile and maximum. ",
            arearange: " Arearange charts are line charts displaying a range between a lower and higher value for each point. ",
            areasplinerange: " These charts are line charts displaying a range between a lower and higher value for each point. ",
            bubble: " Bubble charts are scatter charts where each data point also has a size value. ",
            columnrange: " Columnrange charts are column charts displaying a range between a lower and higher value for each point. ",
            errorbar: " Errorbar series are used to display the variability of the data. ",
            funnel: " Funnel charts are used to display reduction of data in stages. ",
            pyramid: " Pyramid charts consist of a single pyramid with item heights corresponding to each point value. ",
            waterfall: " A waterfall chart is a column chart where each column contributes towards a total end value. "
        }, E = "name id category x value y".split(" "), z = "z open high q3 median q1 low close".split(" ");
        e.setOptions({accessibility: {enabled: !0, pointDescriptionThreshold: 30, keyboardNavigation: {enabled: !0}}});
        e.wrap(e.Series.prototype,
            "render", function (a) {
                a.apply(this, Array.prototype.slice.call(arguments, 1));
                this.chart.options.accessibility.enabled && this.setA11yDescription()
            });
        e.Series.prototype.setA11yDescription = function () {
            var a = this.chart.options.accessibility, b = this.points && this.points.length && this.points[0].graphic && this.points[0].graphic.element, d = b && b.parentNode || this.graph && this.graph.element || this.group && this.group.element;
            d && (d.lastChild === b && h(d), this.points && (this.points.length < a.pointDescriptionThreshold || !1 === a.pointDescriptionThreshold) &&
            g(this.points, function (c) {
                c.graphic && (c.graphic.element.setAttribute("role", "img"), c.graphic.element.setAttribute("tabindex", "-1"), c.graphic.element.setAttribute("aria-label", a.pointDescriptionFormatter && a.pointDescriptionFormatter(c) || c.buildPointInfoString()))
            }), 1 < this.chart.series.length || a.describeSingleSeries) && (d.setAttribute("role", "region"), d.setAttribute("tabindex", "-1"), d.setAttribute("aria-label", a.seriesDescriptionFormatter && a.seriesDescriptionFormatter(this) || this.buildSeriesInfoString()))
        };
        e.Series.prototype.buildSeriesInfoString = function () {
            var a = r[this.type] || r.default, b = this.description || this.options.description;
            return (this.name ? this.name + ", " : "") + (1 === this.chart.types.length ? a[0] : "series") + " " + (this.index + 1) + " of " + this.chart.series.length + (1 === this.chart.types.length ? " with " : ". " + a[0] + " with ") + (this.points.length + " " + (1 === this.points.length ? a[1] : a[2])) + (b ? ". " + b : "") + (1 < this.chart.yAxis.length && this.yAxis ? ". Y axis, " + this.yAxis.getDescription() : "") + (1 < this.chart.xAxis.length &&
                this.xAxis ? ". X axis, " + this.xAxis.getDescription() : "")
        };
        e.Point.prototype.buildPointInfoString = function () {
            var a = this, b = a.series, d = b.chart.options.accessibility, c = "", f = !1, x = b.xAxis && b.xAxis.isDatetimeAxis, b = x && C(d.pointDateFormatter && d.pointDateFormatter(a) || d.pointDateFormat || e.Tooltip.prototype.getXDateFormat(a, b.chart.options.tooltip, b.xAxis), a.x);
            g(z, function (c) {
                void 0 !== a[c] && (f = !0)
            });
            f ? (x && (c = b), g(E.concat(z), function (b) {
                void 0 === a[b] || x && "x" === b || (c += (c ? ". " : "") + b + ", " + this[b])
            })) : c = (this.name ||
                b || this.category || this.id || "x, " + this.x) + ", " + (void 0 !== this.value ? this.value : this.y);
            return this.index + 1 + ". " + c + "." + (this.description ? " " + this.description : "")
        };
        e.Axis.prototype.getDescription = function () {
            return this.userOptions && this.userOptions.description || this.axisTitle && this.axisTitle.textStr || this.options.id || this.categories && "categories" || "values"
        };
        e.Axis.prototype.panStep = function (a, b) {
            var d = b || 3;
            b = this.getExtremes();
            var c = (b.max - b.min) / d * a, d = b.max + c, c = b.min + c, f = d - c;
            0 > a && c < b.dataMin ? (c = b.dataMin,
                d = c + f) : 0 < a && d > b.dataMax && (d = b.dataMax, c = d - f);
            this.setExtremes(c, d)
        };
        e.wrap(e.Series.prototype, "init", function (a) {
            a.apply(this, Array.prototype.slice.call(arguments, 1));
            var b = this.chart;
            b.options.accessibility.enabled && (b.types = b.types || [], 0 > b.types.indexOf(this.type) && b.types.push(this.type), v(this, "remove", function () {
                var a = this, c = !1;
                g(b.series, function (f) {
                    f !== a && 0 > b.types.indexOf(a.type) && (c = !0)
                });
                c || A(b.types, a.type)
            }))
        });
        e.Chart.prototype.getTypeDescription = function () {
            var a = this.types && this.types[0],
                b = this.series[0] && this.series[0].mapTitle;
            if (a) {
                if ("map" === a)return b ? "Map of " + b : "Map of unspecified region.";
                if (1 < this.types.length)return "Combination chart.";
                if (-1 < ["spline", "area", "areaspline"].indexOf(a))return "Line chart."
            } else return "Empty chart.";
            return a + " chart." + (D[a] || "")
        };
        e.Chart.prototype.getAxesDescription = function () {
            var a = this.xAxis.length, b = this.yAxis.length, d = {}, c;
            if (a)if (d.xAxis = "The chart has " + a + (1 < a ? " X axes" : " X axis") + " displaying ", 2 > a)d.xAxis += this.xAxis[0].getDescription() +
                "."; else {
                for (c = 0; c < a - 1; ++c)d.xAxis += (c ? ", " : "") + this.xAxis[c].getDescription();
                d.xAxis += " and " + this.xAxis[c].getDescription() + "."
            }
            if (b)if (d.yAxis = "The chart has " + b + (1 < b ? " Y axes" : " Y axis") + " displaying ", 2 > b)d.yAxis += this.yAxis[0].getDescription() + "."; else {
                for (c = 0; c < b - 1; ++c)d.yAxis += (c ? ", " : "") + this.yAxis[c].getDescription();
                d.yAxis += " and " + this.yAxis[c].getDescription() + "."
            }
            return d
        };
        e.Chart.prototype.addAccessibleContextMenuAttribs = function () {
            var a = this.exportDivElements;
            a && (g(a, function (b) {
                "DIV" !==
                b.tagName || b.children && b.children.length || (b.setAttribute("role", "menuitem"), b.setAttribute("tabindex", -1))
            }), a[0].parentNode.setAttribute("role", "menu"), a[0].parentNode.setAttribute("aria-label", "Chart export"))
        };
        e.Point.prototype.highlight = function () {
            var a = this.series.chart;
            this.graphic && this.graphic.element.focus && this.graphic.element.focus();
            this.isNull ? a.tooltip.hide(0) : (this.onMouseOver(), a.tooltip.refresh(a.tooltip.shared ? [this] : this));
            a.highlightedPoint = this;
            return this
        };
        e.Chart.prototype.highlightAdjacentPoint =
            function (a) {
                var b = this.series, d = this.highlightedPoint, c = d && d.index || 0;
                if (!b[0] || !b[0].points)return !1;
                if (!d)return b[0].points[0].highlight();
                if (d.series.points[c] !== d)for (var f = 0; f < d.series.points.length; ++f)if (d.series.points[f] === d) {
                    c = f;
                    break
                }
                b = b[d.series.index + (a ? 1 : -1)];
                d = d.series.points[c + (a ? 1 : -1)] || b && b.points[a ? 0 : b.points.length - 1];
                return void 0 === d ? !1 : d.isNull && this.options.accessibility.keyboardNavigation && this.options.accessibility.keyboardNavigation.skipNullPoints ? (this.highlightedPoint =
                    d, this.highlightAdjacentPoint(a)) : d.highlight()
            };
        e.Chart.prototype.showExportMenu = function () {
            this.exportSVGElements && this.exportSVGElements[0] && (this.exportSVGElements[0].element.onclick(), this.highlightExportItem(0))
        };
        e.Chart.prototype.highlightExportItem = function (a) {
            var b = this.exportDivElements && this.exportDivElements[a], d = this.exportDivElements && this.exportDivElements[this.highlightedExportItem];
            if (b && "DIV" === b.tagName && (!b.children || !b.children.length)) {
                b.focus && b.focus();
                if (d && d.onmouseout)d.onmouseout();
                if (b.onmouseover)b.onmouseover();
                this.highlightedExportItem = a;
                return !0
            }
        };
        e.Chart.prototype.highlightRangeSelectorButton = function (a) {
            var b = this.rangeSelector.buttons;
            b[this.highlightedRangeSelectorItemIx] && b[this.highlightedRangeSelectorItemIx].setState(this.oldRangeSelectorItemState || 0);
            this.highlightedRangeSelectorItemIx = a;
            return b[a] ? (b[a].element.focus && b[a].element.focus(), this.oldRangeSelectorItemState = b[a].state, b[a].setState(2), !0) : !1
        };
        e.Chart.prototype.highlightLegendItem = function (a) {
            var b =
                this.legend.allItems;
            b[this.highlightedLegendItemIx] && w(b[this.highlightedLegendItemIx].legendGroup.element, "mouseout");
            this.highlightedLegendItemIx = a;
            return b[a] ? (b[a].legendGroup.element.focus && b[a].legendGroup.element.focus(), w(b[a].legendGroup.element, "mouseover"), !0) : !1
        };
        e.Chart.prototype.hideExportMenu = function () {
            var a = this.exportDivElements;
            if (a) {
                g(a, function (b) {
                    w(b, "mouseleave")
                });
                if (a[this.highlightedExportItem] && a[this.highlightedExportItem].onmouseout)a[this.highlightedExportItem].onmouseout();
                this.highlightedExportItem = 0;
                this.renderTo.focus()
            }
        };
        e.Chart.prototype.addKeyboardNavEvents = function () {
            function a(c) {
                this.keyCodeMap = c.keyCodeMap;
                this.move = c.move;
                this.validate = c.validate;
                this.init = c.init;
                this.transformTabs = !1 !== c.transformTabs
            }

            function b(b, d) {
                return new a(u({
                    keyCodeMap: b, move: function (b) {
                        c.keyboardNavigationModuleIndex += b;
                        var a = c.keyboardNavigationModules[c.keyboardNavigationModuleIndex];
                        if (a) {
                            if (a.validate && !a.validate())return this.move(b);
                            if (a.init)return a.init(b), !0
                        }
                        c.keyboardNavigationModuleIndex =
                            0;
                        c.slipNextTab = !0;
                        return !1
                    }
                }, d))
            }

            function d(b) {
                b = b || y.event;
                var a = c.keyboardNavigationModules[c.keyboardNavigationModuleIndex];
                9 === (b.which || b.keyCode) && c.slipNextTab ? c.slipNextTab = !1 : (c.slipNextTab = !1, a && a.run(b) && b.preventDefault())
            }

            var c = this;
            a.prototype = {
                run: function (c) {
                    var b = this, a = c.which || c.keyCode, f = !1, a = this.transformTabs && 9 === a ? c.shiftKey ? 37 : 39 : a;
                    g(this.keyCodeMap, function (d) {
                        -1 < d[0].indexOf(a) && (f = !1 === d[1].call(b, a, c) ? !1 : !0)
                    });
                    return f
                }
            };
            c.keyboardNavigationModules = [b([[[37, 39], function (b) {
                if (!c.highlightAdjacentPoint(39 ===
                        b))return this.move(39 === b ? 1 : -1)
            }], [[38, 40], function (b) {
                var a;
                if (c.highlightedPoint)if ((a = c.series[c.highlightedPoint.series.index + (38 === b ? -1 : 1)]) && a.points[0])a.points[0].highlight(); else return this.move(40 === b ? 1 : -1)
            }], [[13, 32], function () {
                c.highlightedPoint && c.highlightedPoint.firePointEvent("click")
            }]], {
                init: function (b) {
                    var a = c.series && c.series[c.series.length - 1], a = a && a.points && a.points[a.points.length - 1];
                    0 > b && a && a.highlight()
                }
            }), b([[[37, 38], function () {
                for (var a = c.highlightedExportItem || 0, b = !0,
                         d = c.series; a--;)if (c.highlightExportItem(a)) {
                    b = !1;
                    break
                }
                if (b)return c.hideExportMenu(), d && d.length && (a = d[d.length - 1], a.points.length && a.points[a.points.length - 1].highlight()), this.move(-1)
            }], [[39, 40], function () {
                for (var a = !0, b = (c.highlightedExportItem || 0) + 1; b < c.exportDivElements.length; ++b)if (c.highlightExportItem(b)) {
                    a = !1;
                    break
                }
                if (a)return c.hideExportMenu(), this.move(1)
            }], [[13, 32], function () {
                q(c.exportDivElements[c.highlightedExportItem])
            }]], {
                validate: function () {
                    return c.exportChart && !(c.options.exporting &&
                        !1 === c.options.exporting.enabled)
                }, init: function (a) {
                    c.highlightedPoint = null;
                    c.showExportMenu();
                    if (0 > a && c.exportDivElements)for (a = c.exportDivElements.length; -1 < a && !c.highlightExportItem(a); --a);
                }
            }), b([[[38, 40, 37, 39], function (a) {
                c[38 === a || 40 === a ? "yAxis" : "xAxis"][0].panStep(39 > a ? -1 : 1)
            }], [[9], function (a, b) {
                c.mapNavButtons[c.focusedMapNavButtonIx].setState(0);
                if (b.shiftKey && !c.focusedMapNavButtonIx || !b.shiftKey && c.focusedMapNavButtonIx)return c.mapZoom(), this.move(b.shiftKey ? -1 : 1);
                c.focusedMapNavButtonIx +=
                    b.shiftKey ? -1 : 1;
                a = c.mapNavButtons[c.focusedMapNavButtonIx];
                a.element.focus && a.element.focus();
                a.setState(2)
            }], [[13, 32], function () {
                q(c.mapNavButtons[c.focusedMapNavButtonIx].element)
            }]], {
                validate: function () {
                    return c.mapZoom && c.mapNavButtons && 2 === c.mapNavButtons.length
                }, transformTabs: !1, init: function (a) {
                    var b = c.mapNavButtons[0], d = c.mapNavButtons[1], b = 0 < a ? b : d;
                    g(c.mapNavButtons, function (a, c) {
                        a.element.setAttribute("tabindex", -1);
                        a.element.setAttribute("role", "button");
                        a.element.setAttribute("aria-label",
                            "Zoom " + (c ? "out" : "") + "chart")
                    });
                    b.element.focus && b.element.focus();
                    b.setState(2);
                    c.focusedMapNavButtonIx = 0 < a ? 0 : 1
                }
            }), b([[[37, 39, 38, 40], function (a) {
                a = 37 === a || 38 === a ? -1 : 1;
                if (!c.highlightRangeSelectorButton(c.highlightedRangeSelectorItemIx + a))return this.move(a)
            }], [[13, 32], function () {
                3 !== c.oldRangeSelectorItemState && q(c.rangeSelector.buttons[c.highlightedRangeSelectorItemIx].element)
            }]], {
                validate: function () {
                    return c.rangeSelector && c.rangeSelector.buttons && c.rangeSelector.buttons.length
                }, init: function (a) {
                    g(c.rangeSelector.buttons,
                        function (a) {
                            a.element.setAttribute("tabindex", "-1");
                            a.element.setAttribute("role", "button");
                            a.element.setAttribute("aria-label", "Select range " + (a.text && a.text.textStr))
                        });
                    c.highlightRangeSelectorButton(0 < a ? 0 : c.rangeSelector.buttons.length - 1)
                }
            }), b([[[9, 38, 40], function (a, b) {
                a = 9 === a && b.shiftKey || 38 === a ? -1 : 1;
                b = c.highlightedInputRangeIx += a;
                if (1 < b || 0 > b)return this.move(a);
                c.rangeSelector[b ? "maxInput" : "minInput"].focus()
            }]], {
                validate: function () {
                    return c.rangeSelector && c.rangeSelector.inputGroup && "hidden" !==
                        c.rangeSelector.inputGroup.element.getAttribute("visibility") && !1 !== c.options.rangeSelector.inputEnabled && c.rangeSelector.minInput && c.rangeSelector.maxInput
                }, transformTabs: !1, init: function (a) {
                    c.highlightedInputRangeIx = 0 < a ? 0 : 1;
                    c.rangeSelector[c.highlightedInputRangeIx ? "maxInput" : "minInput"].focus()
                }
            }), b([[[37, 39, 38, 40], function (a) {
                    a = 37 === a || 38 === a ? -1 : 1;
                    if (!c.highlightLegendItem(c.highlightedLegendItemIx + a))return this.move(a)
                }], [[13, 32], function () {
                    q(c.legend.allItems[c.highlightedLegendItemIx].legendItem.element.parentNode)
                }]],
                {
                    validate: function () {
                        return c.legend && c.legend.allItems && !c.colorAxis
                    }, init: function (a) {
                    g(c.legend.allItems, function (a) {
                        a.legendGroup.element.setAttribute("tabindex", "-1");
                        a.legendGroup.element.setAttribute("role", "button");
                        a.legendGroup.element.setAttribute("aria-label", "Toggle visibility of series " + a.name)
                    });
                    c.highlightLegendItem(0 < a ? 0 : c.legend.allItems.length - 1)
                }
                })];
            c.keyboardNavigationModuleIndex = 0;
            c.renderTo.tabIndex || c.renderTo.setAttribute("tabindex", "0");
            v(c.renderTo, "keydown", d);
            v(c, "destroy",
                function () {
                    B(c.renderTo, "keydown", d)
                })
        };
        e.Chart.prototype.addScreenReaderRegion = function (a) {
            var b = this, d = b.series, c = b.options, e = c.accessibility, g = b.screenReaderRegion = l.createElement("div"), h = l.createElement("h3"), n = l.createElement("a"), p = l.createElement("h3"), t = {
                position: "absolute",
                left: "-9999px",
                top: "auto",
                width: "1px",
                height: "1px",
                overflow: "hidden"
            }, k = b.types || [], k = (1 === k.length && "pie" === k[0] || "map" === k[0]) && {} || b.getAxesDescription(), m = d[0] && r[d[0].type] || r.default;
            g.setAttribute("role", "region");
            g.setAttribute("aria-label", "Chart screen reader information.");
            g.innerHTML = e.screenReaderSectionFormatter && e.screenReaderSectionFormatter(b) || '\x3cdiv tabindex\x3d"0"\x3eUse regions/landmarks to skip ahead to chart' + (1 < d.length ? " and navigate between data series" : "") + ".\x3c/div\x3e\x3ch3\x3eSummary.\x3c/h3\x3e\x3cdiv\x3e" + (c.title.text || "Chart") + (c.subtitle && c.subtitle.text ? ". " + c.subtitle.text : "") + "\x3c/div\x3e\x3ch3\x3eLong description.\x3c/h3\x3e\x3cdiv\x3e" + (c.chart.description || "No description available.") +
                "\x3c/div\x3e\x3ch3\x3eStructure.\x3c/h3\x3e\x3cdiv\x3eChart type: " + (c.chart.typeDescription || b.getTypeDescription()) + "\x3c/div\x3e" + (1 === d.length ? "\x3cdiv\x3e" + m[0] + " with " + d[0].points.length + " " + (1 === d[0].points.length ? m[1] : m[2]) + ".\x3c/div\x3e" : "") + (k.xAxis ? "\x3cdiv\x3e" + k.xAxis + "\x3c/div\x3e" : "") + (k.yAxis ? "\x3cdiv\x3e" + k.yAxis + "\x3c/div\x3e" : "");
            b.getCSV && (n.innerHTML = "View as data table.", n.href = "#" + a, n.setAttribute("tabindex", "-1"), n.onclick = e.onTableAnchorClick || function () {
                    b.viewData();
                    l.getElementById(a).focus()
                },
                h.appendChild(n), g.appendChild(h));
            p.innerHTML = "Chart graphic.";
            b.renderTo.insertBefore(p, b.renderTo.firstChild);
            b.renderTo.insertBefore(g, b.renderTo.firstChild);
            u(!0, p.style, t);
            u(!0, g.style, t)
        };
        e.Chart.prototype.callbacks.push(function (a) {
            var b = a.options, d = b.accessibility;
            if (d.enabled) {
                var c = l.createElementNS("http://www.w3.org/2000/svg", "title"), f = l.createElementNS("http://www.w3.org/2000/svg", "g"), h = a.container.getElementsByTagName("desc")[0], q = a.container.getElementsByTagName("text"), n = "highcharts-title-" +
                    a.index, p = "highcharts-data-table-" + a.index, t = b.title.text || "Chart", k = b.exporting && b.exporting.csv && b.exporting.csv.columnHeaderFormatter, m = [];
                c.textContent = t;
                c.id = n;
                h.parentNode.insertBefore(c, h);
                a.renderTo.setAttribute("role", "region");
                a.renderTo.setAttribute("aria-label", t + ". Use up and down arrows to navigate.");
                if (a.exportSVGElements && a.exportSVGElements[0] && a.exportSVGElements[0].element) {
                    var r = a.exportSVGElements[0].element.onclick, c = a.exportSVGElements[0].element.parentNode;
                    a.exportSVGElements[0].element.onclick =
                        function () {
                            r.apply(this, Array.prototype.slice.call(arguments));
                            a.addAccessibleContextMenuAttribs();
                            a.highlightExportItem(0)
                        };
                    a.exportSVGElements[0].element.setAttribute("role", "button");
                    a.exportSVGElements[0].element.setAttribute("aria-label", "View export menu");
                    f.appendChild(a.exportSVGElements[0].element);
                    f.setAttribute("role", "region");
                    f.setAttribute("aria-label", "Chart export menu");
                    c.appendChild(f)
                }
                a.rangeSelector && g(["minInput", "maxInput"], function (b, c) {
                    a.rangeSelector[b] && (a.rangeSelector[b].setAttribute("tabindex",
                        "-1"), a.rangeSelector[b].setAttribute("role", "textbox"), a.rangeSelector[b].setAttribute("aria-label", "Select " + (c ? "end" : "start") + " date."))
                });
                g(q, function (a) {
                    a.setAttribute("aria-hidden", "true")
                });
                a.addScreenReaderRegion(p);
                d.keyboardNavigation && a.addKeyboardNavEvents();
                u(!0, b.exporting, {
                    csv: {
                        columnHeaderFormatter: function (a, b, c) {
                            var d = m[m.length - 1];
                            1 < c && (d && d.text) !== a.name && m.push({text: a.name, span: c});
                            return k ? k.call(this, a, b, c) : 1 < c ? b : a.name
                        }
                    }
                });
                e.wrap(a, "getTable", function (a) {
                    return a.apply(this,
                        Array.prototype.slice.call(arguments, 1)).replace("\x3ctable\x3e", '\x3ctable id\x3d"' + p + '" summary\x3d"Table representation of chart"\x3e\x3ccaption\x3e' + t + "\x3c/caption\x3e")
                });
                e.wrap(a, "viewData", function (a) {
                    if (!this.insertedTable) {
                        a.apply(this, Array.prototype.slice.call(arguments, 1));
                        var b = l.getElementById(p), c = b.getElementsByTagName("tbody")[0], d = c.firstChild.children, e = "\x3ctr\x3e\x3ctd\x3e\x3c/td\x3e", f, h;
                        b.setAttribute("tabindex", "-1");
                        g(c.children, function (a) {
                            f = a.firstChild;
                            h = l.createElement("th");
                            h.setAttribute("scope", "row");
                            h.innerHTML = f.innerHTML;
                            f.parentNode.replaceChild(h, f)
                        });
                        g(d, function (a) {
                            "TH" === a.tagName && a.setAttribute("scope", "col")
                        });
                        m.length && (g(m, function (a) {
                            e += '\x3cth scope\x3d"col" colspan\x3d"' + a.span + '"\x3e' + a.text + "\x3c/th\x3e"
                        }), c.insertAdjacentHTML("afterbegin", e))
                    }
                })
            }
        })
    })(h)
});
