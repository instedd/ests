/*

 The MIT License (MIT)

 Copyright (c) 2015 yWorks GmbH

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */
(function (U) {
    function V(a, c) {
        var b = B(a, "font-family");
        b && e.setFont(b);
        c && c.ok && e.setTextColor(c.r, c.g, c.b);
        var d;
        (b = B(a, "font-weight")) && "bold" === b && (d = "bold");
        (b = B(a, "font-style")) && "italic" === b && (d += "italic");
        e.setFontType(d);
        if (d = B(a, "font-size"))d = parseFloat(d), e.setFontSize(d)
    }

    var z, e, X = 2 / 3, Y = function (a) {
        var c = a.pathSegList;
        if (c)return c;
        c = [];
        a = a.getAttribute("d");
        for (var b = /([a-df-zA-DF-Z])([^a-df-zA-DF-Z]*)/g, d; d = b.exec(a);) {
            var k = E(d[2]);
            d = d[1];
            var e = 0 <= "zZ".indexOf(d) ? 0 : 0 <= "hHvV".indexOf(d) ?
                1 : 0 <= "mMlLtT".indexOf(d) ? 2 : 0 <= "sSqQ".indexOf(d) ? 4 : 0 <= "cC".indexOf(d) ? 6 : -1, g = 0;
            do {
                var f = {pathSegTypeAsLetter: d};
                switch (d) {
                    case "h":
                    case "H":
                        f.x = k[g];
                        break;
                    case "v":
                    case "V":
                        f.y = k[g];
                        break;
                    case "c":
                    case "C":
                        f.x1 = k[g + e - 6], f.y1 = k[g + e - 5];
                    case "s":
                    case "S":
                        f.x2 = k[g + e - 4], f.y2 = k[g + e - 3];
                    case "t":
                    case "T":
                    case "l":
                    case "L":
                    case "m":
                    case "M":
                        f.x = k[g + e - 2];
                        f.y = k[g + e - 1];
                        break;
                    case "q":
                    case "Q":
                        f.x1 = k[g], f.y1 = k[g + 1], f.x = k[g + 2], f.y = k[g + 3]
                }
                c.push(f);
                g += e
            } while (g < k.length)
        }
        c.getItem = function (a) {
            return this[a]
        };
        c.numberOfItems =
            c.length;
        return c
    }, B = function (a, c, b) {
        b = b || c;
        return a.getAttribute(c) || a.style[b]
    }, y = function (a, c) {
        return 0 <= c.split(",").indexOf(a.tagName.toLowerCase())
    }, L = function (a, c) {
        for (var b = [], d = 0; d < a.childNodes.length; d++) {
            var e = a.childNodes[d];
            "#" !== e.nodeName.charAt(0) && b.push(e)
        }
        for (d = 0; d < b.length; d++)c(d, b[d])
    }, R = function (a, c) {
        return Math.atan2(c[1] - a[1], c[0] - a[0])
    }, Z = function (a, c) {
        return [a[0] + 2 * (c[0] - a[0]), a[1] + 2 * (c[1] - a[1])]
    }, v = function (a, c) {
        return [X * (c[0] - a[0]) + a[0], X * (c[1] - a[1]) + a[1]]
    }, M = function (a,
                     c, b, d, e) {
        b = b.getItem(a - 1);
        return 0 < a && ("C" === b.pathSegTypeAsLetter || "S" === b.pathSegTypeAsLetter) ? Z([b.x2, b.y2], c) : 0 < a && ("c" === b.pathSegTypeAsLetter || "s" === b.pathSegTypeAsLetter) ? Z([b.x2 + d, b.y2 + e], c) : [c[0], c[1]]
    }, aa = function (a) {
        this.prefix = a;
        this.id = 0;
        this.nextChild = function () {
            return new aa("_" + this.id++ + "_" + this.get())
        };
        this.get = function () {
            return this.prefix
        }
    }, ga = function (a, c) {
        for (var b = /_\d+_/; !c[a] && b.exec(a);)a = a.replace(b, "");
        return c[a]
    }, P = function (a) {
        var c, b, d, k, m, g, f;
        g = e.unitMatrix;
        y(a, "svg,g") ?
            (f = parseFloat(a.getAttribute("x")) || 0, g = parseFloat(a.getAttribute("y")) || 0, (c = a.getAttribute("viewBox")) ? (m = E(c), k = m[2] - m[0], d = m[3] - m[1], b = parseFloat(a.getAttribute("width")) || k, c = parseFloat(a.getAttribute("height")) || d, g = new e.Matrix(b / k, 0, 0, c / d, f - m[0], g - m[1])) : g = new e.Matrix(1, 0, 0, 1, f, g)) : y(a, "marker") && (f = -parseFloat(a.getAttribute("refX")) || 0, g = -parseFloat(a.getAttribute("refY")) || 0, (c = a.getAttribute("viewBox")) ? (m = E(c), k = m[2] - m[0], d = m[3] - m[1], b = parseFloat(a.getAttribute("markerWidth")) || k, c =
            parseFloat(a.getAttribute("markerHeight")) || d, c = new e.Matrix(b / k, 0, 0, c / d, 0, 0), g = new e.Matrix(1, 0, 0, 1, f, g), g = e.matrixMult(g, c)) : g = new e.Matrix(1, 0, 0, 1, f, g));
        return (a = a.getAttribute("transform")) ? e.matrixMult(g, ba(a)) : g
    }, ca = function (a) {
        a = E(a);
        for (var c = [], b = 0; b < a.length - 1; b += 2)c.push([a[b], a[b + 1]]);
        return c
    }, ba = function (a) {
        if (!a)return e.unitMatrix;
        for (var c = /^\s*matrix\(([^\)]+)\)\s*/, b = /^\s*translate\(([^\)]+)\)\s*/, d = /^\s*rotate\(([^\)]+)\)\s*/, k = /^\s*scale\(([^\)]+)\)\s*/, m = /^\s*skewX\(([^\)]+)\)\s*/,
                 g = /^\s*skewY\(([^\)]+)\)\s*/, f = e.unitMatrix, n; 0 < a.length;) {
            var q = c.exec(a);
            q && (n = E(q[1]), f = e.matrixMult(new e.Matrix(n[0], n[1], n[2], n[3], n[4], n[5]), f), a = a.substr(q[0].length));
            if (q = d.exec(a)) {
                n = E(q[1]);
                var h = Math.PI * n[0] / 180, f = e.matrixMult(new e.Matrix(Math.cos(h), Math.sin(h), -Math.sin(h), Math.cos(h), 0, 0), f);
                n[1] && n[2] && (h = new e.Matrix(1, 0, 0, 1, n[1], n[2]), n = new e.Matrix(1, 0, 0, 1, -n[1], -n[2]), f = e.matrixMult(n, e.matrixMult(f, h)));
                a = a.substr(q[0].length)
            }
            if (q = b.exec(a))n = E(q[1]), f = e.matrixMult(new e.Matrix(1,
                0, 0, 1, n[0], n[1] || 0), f), a = a.substr(q[0].length);
            if (q = k.exec(a))n = E(q[1]), n[1] || (n[1] = n[0]), f = e.matrixMult(new e.Matrix(n[0], 0, 0, n[1], 0, 0), f), a = a.substr(q[0].length);
            if (q = m.exec(a))n = parseFloat(q[1]), f = e.matrixMult(new e.Matrix(1, 0, Math.tan(n), 1, 0, 0), f), a = a.substr(q[0].length);
            if (q = g.exec(a))n = parseFloat(q[1]), f = e.matrixMult(new e.Matrix(1, Math.tan(n), 0, 1, 0, 0), f), a = a.substr(q[0].length)
        }
        return f
    }, E = function (a) {
        for (var c = [], b, d = /[+-]?(?:(?:\d+\.?\d*)|(?:\d*\.?\d+))(?:[eE][+-]?\d+)?/g; b = d.exec(a);)c.push(parseFloat(b[0]));
        return c
    }, ha = function (a) {
        var c = /\s*rgba\(((?:[^,\)]*,){3}[^,\)]*)\)\s*/.exec(a);
        return c ? (a = E(c[1]), c = new z("rgb(" + a.slice(0, 3).join(",") + ")"), c.a = a[3], c) : new z(a)
    }, N = function (a, c) {
        var b = a[0], d = a[1];
        return [c.a * b + c.c * d + c.e, c.b * b + c.d * d + c.f]
    }, C = function (a) {
        var c, b, d, e, m, g, f = parseFloat;
        if (y(a, "polygon")) {
            f = ca(a.getAttribute("points"));
            d = b = Number.POSITIVE_INFINITY;
            m = e = Number.NEGATIVE_INFINITY;
            for (c = 0; c < f.length; c++) {
                var n = f[c];
                b = Math.min(b, n[0]);
                e = Math.max(e, n[0]);
                d = Math.min(d, n[1]);
                m = Math.max(m, n[1])
            }
            g =
                [b, d, e - b, m - d]
        } else if (y(a, "path")) {
            var q = Y(a);
            d = b = Number.POSITIVE_INFINITY;
            m = e = Number.NEGATIVE_INFINITY;
            var h = 0, r = 0, S, w, H, t, A, F;
            for (c = 0; c < q.numberOfItems; c++) {
                var p = q.getItem(c), J = p.pathSegTypeAsLetter;
                switch (J) {
                    case "H":
                        w = p.x;
                        H = r;
                        break;
                    case "h":
                        w = p.x + h;
                        H = r;
                        break;
                    case "V":
                        w = h;
                        H = p.y;
                        break;
                    case "v":
                        w = h;
                        H = p.y + r;
                        break;
                    case "C":
                        t = [p.x1, p.y1];
                        A = [p.x2, p.y2];
                        F = [p.x, p.y];
                        break;
                    case "c":
                        t = [p.x1 + h, p.y1 + r];
                        A = [p.x2 + h, p.y2 + r];
                        F = [p.x + h, p.y + r];
                        break;
                    case "S":
                        t = M(c, [h, r], q, n, S);
                        A = [p.x2, p.y2];
                        F = [p.x, p.y];
                        break;
                    case "s":
                        t =
                            M(c, [h, r], q, n, S);
                        A = [p.x2 + h, p.y2 + r];
                        F = [p.x + h, p.y + r];
                        break;
                    case "Q":
                        f = [p.x1, p.y1];
                        t = v([h, r], f);
                        A = v([p.x, p.y], f);
                        F = [p.x, p.y];
                        break;
                    case "q":
                        f = [p.x1 + h, p.y1 + r];
                        t = v([h, r], f);
                        A = v([h + p.x, r + p.y], f);
                        F = [p.x + h, p.y + r];
                        break;
                    case "T":
                        t = M(c, [h, r], q, n, S);
                        t = v([h, r], f);
                        A = v([p.x, p.y], f);
                        F = [p.x, p.y];
                        break;
                    case "t":
                        f = M(c, [h, r], q, n, S), t = v([h, r], f), A = v([h + p.x, r + p.y], f), F = [p.x + h, p.y + r]
                }
                0 <= "sScCqQtT".indexOf(J) && (n = h, S = r);
                0 <= "MLCSQT".indexOf(J) ? (h = p.x, r = p.y) : 0 <= "mlcsqt".indexOf(J) ? (h = p.x + h, r = p.y + r) : 0 > "zZ".indexOf(J) && (h =
                    w, r = H);
                0 <= "CSQTcsqt".indexOf(J) ? (b = Math.min(b, h, t[0], A[0], F[0]), e = Math.max(e, h, t[0], A[0], F[0]), d = Math.min(d, r, t[1], A[1], F[1]), m = Math.max(m, r, t[1], A[1], F[1])) : (b = Math.min(b, h), e = Math.max(e, h), d = Math.min(d, r), m = Math.max(m, r))
            }
            g = [b, d, e - b, m - d]
        } else {
            if (y(a, "svg"))return (b = a.getAttribute("viewBox")) && (c = E(b)), [f(a.getAttribute("x")) || c && c[0] || 0, f(a.getAttribute("y")) || c && c[1] || 0, f(a.getAttribute("width")) || c && c[2] || 0, f(a.getAttribute("height")) || c && c[3] || 0];
            if (y(a, "g"))g = [0, 0, 0, 0], L(a, function (a, c) {
                var b =
                    C(c);
                g = [Math.min(g[0], b[0]), Math.min(g[1], b[1]), Math.max(g[0] + g[2], b[0] + b[2]) - Math.min(g[0], b[0]), Math.max(g[1] + g[3], b[1] + b[3]) - Math.min(g[1], b[1])]
            }); else {
                if (y(a, "marker"))return (b = a.getAttribute("viewBox")) && (c = E(b)), [c && c[0] || 0, c && c[1] || 0, c && c[2] || f(a.getAttribute("marker-width")) || 0, c && c[3] || f(a.getAttribute("marker-height")) || 0];
                if (y(a, "pattern"))return [f(a.getAttribute("x")) || 0, f(a.getAttribute("y")) || 0, f(a.getAttribute("width")) || 0, f(a.getAttribute("height")) || 0];
                c = f(a.getAttribute("x1")) ||
                    f(a.getAttribute("x")) || f(a.getAttribute("cx") - f(a.getAttribute("r"))) || 0;
                b = f(a.getAttribute("x2")) || c + f(a.getAttribute("width")) || f(a.getAttribute("cx")) + f(a.getAttribute("r")) || 0;
                d = f(a.getAttribute("y1")) || f(a.getAttribute("y")) || f(a.getAttribute("cy")) - f(a.getAttribute("r")) || 0;
                f = f(a.getAttribute("y2")) || d + f(a.getAttribute("height")) || f(a.getAttribute("cy")) + f(a.getAttribute("r")) || 0;
                g = [Math.min(c, b), Math.min(d, f), Math.max(c, b) - Math.min(c, b), Math.max(d, f) - Math.min(d, f)]
            }
        }
        return y(a, "marker,svg,g") ?
            g : (f = B(a, "stroke-width") || 1, B(a, "stroke-miterlimit") && (f *= .5 / Math.sin(Math.PI / 12)), [g[0] - f, g[1] - f, g[2] + 2 * f, g[3] + 2 * f])
    }, ia = function (a, c, b, d, k) {
        a = ca(a.getAttribute("points"));
        for (var m = [{op: "m", c: N(a[0], c)}], g = 1; g < a.length; g++) {
            var f = N(a[g], c);
            m.push({op: "l", c: f})
        }
        m.push({op: "h"});
        e.path(m, b, d, k)
    }, ja = function (a) {
        var c = a.getAttribute("xlink:href") || a.getAttribute("href"), b = new Image;
        b.src = c;
        var d = document.createElement("canvas"), c = parseFloat(a.getAttribute("width")), k = parseFloat(a.getAttribute("height")),
            m = parseFloat(a.getAttribute("x") || 0);
        a = parseFloat(a.getAttribute("y") || 0);
        d.width = c;
        d.height = k;
        var g = d.getContext("2d");
        g.fillStyle = "#fff";
        g.fillRect(0, 0, c, k);
        g.drawImage(b, 0, 0, c, k);
        b = d.toDataURL("image/jpeg");
        e.addImage(b, "jpeg", m, a, c, k)
    }, ka = function (a, c, b, d, k, m) {
        var g = Y(a), f = a.getAttribute("marker-end"), n = a.getAttribute("marker-start"), q = a.getAttribute("marker-mid");
        a = function (a, c) {
            for (var b = 0, d = 0, h = b, k = d, p, m, r, y, x, u, G, I, B = [], E = [], z, C = 0, Q = function (a, b, d) {
                var f = Math.cos(a);
                a = Math.sin(a);
                b = new e.Matrix(f,
                    a, -a, f, b[0], b[1]);
                E.push({type: d, tf: e.matrixMult(b, c)})
            }, D = 0; D < g.numberOfItems; D++) {
                var l = g.getItem(D), K = l.pathSegTypeAsLetter;
                switch (K) {
                    case "M":
                        h = b;
                        k = d;
                        x = [l.x, l.y];
                        z = "m";
                        break;
                    case "m":
                        h = b;
                        k = d;
                        x = [l.x + b, l.y + d];
                        z = "m";
                        break;
                    case "L":
                        x = [l.x, l.y];
                        z = "l";
                        break;
                    case "l":
                        x = [l.x + b, l.y + d];
                        z = "l";
                        break;
                    case "H":
                        x = [l.x, d];
                        z = "l";
                        r = l.x;
                        y = d;
                        break;
                    case "h":
                        x = [l.x + b, d];
                        z = "l";
                        r = l.x + b;
                        y = d;
                        break;
                    case "V":
                        x = [b, l.y];
                        z = "l";
                        r = b;
                        y = l.y;
                        break;
                    case "v":
                        x = [b, l.y + d];
                        z = "l";
                        r = b;
                        y = l.y + d;
                        break;
                    case "C":
                        G = [l.x1, l.y1];
                        I = [l.x2, l.y2];
                        x = [l.x, l.y];
                        break;
                    case "c":
                        G = [l.x1 + b, l.y1 + d];
                        I = [l.x2 + b, l.y2 + d];
                        x = [l.x + b, l.y + d];
                        break;
                    case "S":
                        G = M(D, [b, d], g, p, m);
                        I = [l.x2, l.y2];
                        x = [l.x, l.y];
                        break;
                    case "s":
                        G = M(D, [b, d], g, p, m);
                        I = [l.x2 + b, l.y2 + d];
                        x = [l.x + b, l.y + d];
                        break;
                    case "Q":
                        u = [l.x1, l.y1];
                        G = v([b, d], u);
                        I = v([l.x, l.y], u);
                        x = [l.x, l.y];
                        break;
                    case "q":
                        u = [l.x1 + b, l.y1 + d];
                        G = v([b, d], u);
                        I = v([b + l.x, d + l.y], u);
                        x = [l.x + b, l.y + d];
                        break;
                    case "T":
                        G = M(D, [b, d], g, p, m);
                        G = v([b, d], u);
                        I = v([l.x, l.y], u);
                        x = [l.x, l.y];
                        break;
                    case "t":
                        u = M(D, [b, d], g, p, m);
                        G = v([b, d], u);
                        I = v([b + l.x, d + l.y],
                            u);
                        x = [l.x + b, l.y + d];
                        break;
                    case "Z":
                    case "z":
                        b = h, d = k, B.push({op: "h"})
                }
                var L = n && (1 === D || 0 > "mM".indexOf(K) && 0 <= "mM".indexOf(g.getItem(D - 1).pathSegTypeAsLetter)), O = f && (D === g.numberOfItems - 1 || 0 > "mM".indexOf(K) && 0 <= "mM".indexOf(g.getItem(D + 1).pathSegTypeAsLetter)), P = q && 0 < D && !(1 === D && 0 <= "mM".indexOf(g.getItem(D - 1).pathSegTypeAsLetter));
                0 <= "sScCqQtT".indexOf(K) ? (L && Q(R([b, d], G), [b, d], "start"), O && Q(R(I, x), x, "end"), P && (u = R([b, d], G), u = 0 <= "mM".indexOf(g.getItem(D - 1).pathSegTypeAsLetter) ? u : .5 * (C + u), Q(u, [b, d], "mid")),
                    C = R(I, x), p = b, m = d, G = N(G, c), I = N(I, c), u = N(x, c), B.push({
                    op: "c",
                    c: [G[0], G[1], I[0], I[1], u[0], u[1]]
                })) : 0 <= "lLhHvVmM".indexOf(K) && (u = R([b, d], x), L && Q(u, [b, d], "start"), O && Q(u, x, "end"), P && (C = 0 <= "mM".indexOf(K) ? C : 0 <= "mM".indexOf(g.getItem(D - 1).pathSegTypeAsLetter) ? u : .5 * (C + u), Q(C, [b, d], "mid")), C = u, u = N(x, c), B.push({
                    op: z,
                    c: u
                }));
                0 <= "MLCSQT".indexOf(K) ? (b = l.x, d = l.y) : 0 <= "mlcsqt".indexOf(K) ? (b = l.x + b, d = l.y + d) : 0 > "zZ".indexOf(K) && (b = r, d = y)
            }
            return {lines: B, markers: E}
        }(g, c);
        if (f || n || q)for (c = 0; c < a.markers.length; c++) {
            var h =
                a.markers[c], r;
            switch (h.type) {
                case "start":
                    r = b.get() + /url\(#(\w+)\)/.exec(n)[1];
                    break;
                case "end":
                    r = b.get() + /url\(#(\w+)\)/.exec(f)[1];
                    break;
                case "mid":
                    r = b.get() + /url\(#(\w+)\)/.exec(q)[1]
            }
            e.doFormObject(r, h.tf)
        }
        0 < a.lines.length && e.path(a.lines, d, k, m)
    }, la = function (a, c, b) {
        var d = a.getAttribute("href") || a.getAttribute("xlink:href");
        if (d) {
            var k = e.getFormObject(b.get() + d.substring(1)), m = a.getAttribute("x") || 0, g = a.getAttribute("y") || 0, f = a.getAttribute("width") || k.width;
            a = a.getAttribute("height") || k.height;
            k = new e.Matrix(f / k.width || 0, 0, 0, a / k.height || 0, m, g);
            k = e.matrixMult(k, c);
            e.doFormObject(b.get() + d.substring(1), k)
        }
    }, ma = function (a, c) {
        var b = N([parseFloat(a.getAttribute("x1")), parseFloat(a.getAttribute("y1"))], c), d = N([parseFloat(a.getAttribute("x2")), parseFloat(a.getAttribute("y2"))], c);
        e.line(b[0], b[1], d[0], d[1])
    }, na = function (a, c, b, d) {
        e.roundedRect(parseFloat(a.getAttribute("x")) || 0, parseFloat(a.getAttribute("y")) || 0, parseFloat(a.getAttribute("width")), parseFloat(a.getAttribute("height")), parseFloat(a.getAttribute("rx")) ||
            0, parseFloat(a.getAttribute("ry")) || 0, c, b, d)
    }, oa = function (a, c, b, d) {
        e.ellipse(parseFloat(a.getAttribute("cx")) || 0, parseFloat(a.getAttribute("cy")) || 0, parseFloat(a.getAttribute("rx")), parseFloat(a.getAttribute("ry")), c, b, d)
    }, pa = function (a, c, b, d) {
        var k = parseFloat(a.getAttribute("r")) || 0;
        e.ellipse(parseFloat(a.getAttribute("cx")) || 0, parseFloat(a.getAttribute("cy")) || 0, k, k, c, b, d)
    }, da = function (a, c) {
        switch (B(a, "text-transform")) {
            case "uppercase":
                return c.toUpperCase();
            case "lowercase":
                return c.toLowerCase();
            default:
                return c
        }
    }, qa = function (a, c, b, d) {
        e.saveGraphicsState();
        V(a, d);
        var k = function (a, c) {
            var b = 0;
            switch (a) {
                case "end":
                    b = c;
                    break;
                case "middle":
                    b = c / 2
            }
            return b
        }, m = function (a, c) {
            var b;
            return (b = a && a.toString().match(/^([\-0-9.]+)em$/)) ? parseFloat(b[1]) * c : (b = a && a.toString().match(/^([\-0-9.]+)(px|)$/)) ? parseFloat(b[1]) : 0
        };
        b = document.createElementNS("http://www.w3.org/2000/svg", "svg");
        b.appendChild(a);
        b.setAttribute("visibility", "hidden");
        document.body.appendChild(b);
        var g = a.getBBox();
        d = 0;
        var f = B(a, "text-anchor");
        f && (d = k(f, g.width));
        var k = e.getFontSize(), n = m(a.getAttribute("x"), k), q = m(a.getAttribute("y"), k), h = e.matrixMult(new e.Matrix(1, 0, 0, 1, n, q), c);
        c = m(a.getAttribute("dx"), k);
        m = m(a.getAttribute("dy"), k);
        0 === a.childElementCount ? e.text(c - d, m, da(a, a.textContent.replace(/[\n\s\r]+/, " ").trim()), void 0, h) : L(a, function (c, b) {
            e.saveGraphicsState();
            var d = B(b, "fill");
            V(b, d && new z(d));
            d = b.getExtentOfChar(0);
            e.text(d.x - n, d.y + .7 * d.height - q, da(a, b.textContent.replace(/[\n\s\r]+/, " ").trim()), void 0, h);
            e.restoreGraphicsState()
        });
        document.body.removeChild(b);
        e.restoreGraphicsState()
    }, ea = function (a, c, b, d, e) {
        L(a, function (a, g) {
            "defs" === g.tagName.toLowerCase() && (W(g, c, b, d, e), g.parentNode.removeChild(g))
        })
    }, ra = function (a, c, b, d, e) {
        d = d.nextChild();
        var m = {}, g;
        for (g in b)b.hasOwnProperty(g) && (m[g] = b[g]);
        ea(a, c, m, d, e);
        T(a, c, m, d, e)
    }, T = function (a, c, b, d, e) {
        L(a, function (a, g) {
            W(g, c, b, d, e)
        })
    }, fa = function (a, c, b, d, k) {
        var m = [], g = 0, f = !1, n;
        L(a, function (a, b) {
            if ("stop" === b.tagName.toLowerCase()) {
                var c = new z(B(b, "stop-color"));
                m.push({
                    offset: parseFloat(b.getAttribute("offset")),
                    color: [c.r, c.g, c.b]
                });
                (c = B(b, "stop-opacity")) && 1 != c && (g += parseFloat(c), f = !0)
            }
        });
        f && (n = new e.GState({opacity: g / b.length}));
        c = new e.ShadingPattern(c, b, m, n);
        k = k.get() + a.getAttribute("id");
        e.addShadingPattern(k, c);
        d[k] = a
    }, sa = function (a, c, b) {
        var d = b.get() + a.getAttribute("id");
        c[d] = a;
        var k = C(a), k = new e.TilingPattern([k[0], k[1], k[0] + k[2], k[1] + k[3]], k[2], k[3], null, P(a));
        e.beginTilingPattern(k);
        T(a, e.unitMatrix, c, b, !1);
        e.endTilingPattern(d, k)
    }, W = function (a, c, b, d, k) {
        var m = !1, g = null, f = null, n = null, q = null, h, r =
            k && !y(a, "lineargradient,radialgradient,pattern");
        r ? (c = P(a), h = C(a), e.beginFormObject(h[0], h[1], h[2], h[3], c), c = e.unitMatrix, k = !1) : (c = e.matrixMult(P(a), c), e.saveGraphicsState());
        if (y(a, "g,path,rect,text,ellipse,line,circle,polygon")) {
            var v = function () {
                g = new z("rgb(0, 0, 0)");
                m = !0;
                f = "F"
            }, w = B(a, "fill");
            if (w) {
                var H = /url\(#(\w+)\)/.exec(w);
                if (H)if (n = d.get() + H[1], (w = ga(n, b)) && y(w, "lineargradient,radialgradient"))q = c, w.hasAttribute("gradientUnits") && "objectboundingbox" !== w.getAttribute("gradientUnits").toLowerCase() ||
                (h || (h = C(a)), q = new e.Matrix(h[2], 0, 0, h[3], h[0], h[1]), h = P(a), q = e.matrixMult(q, h)), h = ba(w.getAttribute("gradientTransform")), q = e.matrixMult(h, q); else if (w && y(w, "pattern")) {
                    var t, A, q = {}, F = e.unitMatrix;
                    w.hasAttribute("patternUnits") && "objectboundingbox" !== w.getAttribute("patternUnits").toLowerCase() || (h || (h = C(a)), F = new e.Matrix(1, 0, 0, 1, h[0], h[1]), t = C(w), A = t[0] * h[0], v = t[1] * h[1], H = t[2] * h[2], t = t[3] * h[3], q.boundingBox = [A, v, A + H, v + t], q.xStep = H, q.yStep = t);
                    var p = e.unitMatrix;
                    w.hasAttribute("patternContentUnits") &&
                    "objectboundingbox" === w.getAttribute("patternContentUnits").toLowerCase() && (h || (h = C(a)), p = new e.Matrix(h[2], 0, 0, h[3], 0, 0), t = q.boundingBox || C(w), A = t[0] / h[0], v = t[1] / h[1], H = t[2] / h[2], t = t[3] / h[3], q.boundingBox = [A, v, A + H, v + t], q.xStep = H, q.yStep = t);
                    q.matrix = e.matrixMult(e.matrixMult(p, F), c);
                    f = "F"
                } else n = w = null, v(); else g = ha(w), g.ok ? (m = !0, f = "F") : f = null
            } else v();
            h = 1;
            (w = a.getAttribute("opacity") || a.getAttribute("fill-opacity")) && (h *= parseFloat(w));
            g && "number" === typeof g.a && (h *= g.a);
            e.setGState(new e.GState({opacity: h}))
        }
        if (y(a,
                "g,path,rect,ellipse,line,circle,polygon") && (m && e.setFillColor(g.r, g.g, g.b), h = a.getAttribute("stroke"))) {
            var J;
            a.hasAttribute("stroke-width") && (J = Math.abs(parseFloat(a.getAttribute("stroke-width"))), e.setLineWidth(J));
            h = new z(h);
            h.ok && (e.setDrawColor(h.r, h.g, h.b), 0 !== J && (f = (f || "") + "D"));
            a.hasAttribute("stroke-linecap") && e.setLineCap(a.getAttribute("stroke-linecap"));
            a.hasAttribute("stroke-linejoin") && e.setLineJoin(a.getAttribute("stroke-linejoin"));
            a.hasAttribute("stroke-dasharray") && e.setLineDashPattern(E(a.getAttribute("stroke-dasharray")),
                parseInt(a.getAttribute("stroke-dashoffset")) || 0);
            a.hasAttribute("stroke-miterlimit") && e.setLineMiterLimit(parseFloat(a.getAttribute("stroke-miterlimit")))
        }
        V(a, g);
        switch (a.tagName.toLowerCase()) {
            case "svg":
                ra(a, c, b, d, k);
                break;
            case "g":
                ea(a, c, b, d, k);
            case "a":
            case "marker":
                T(a, c, b, d, k);
                break;
            case "defs":
                T(a, c, b, d, !0);
                break;
            case "use":
                la(a, c, d);
                break;
            case "line":
                ma(a, c);
                break;
            case "rect":
                e.setCurrentTransformationMatrix(c);
                na(a, f, n, q);
                break;
            case "ellipse":
                e.setCurrentTransformationMatrix(c);
                oa(a, f, n,
                    q);
                break;
            case "circle":
                e.setCurrentTransformationMatrix(c);
                pa(a, f, n, q);
                break;
            case "text":
                qa(a, c, m, g);
                break;
            case "path":
                ka(a, c, d, f, n, q);
                break;
            case "polygon":
                ia(a, c, f, n, q);
                break;
            case "image":
                e.setCurrentTransformationMatrix(c);
                ja(a);
                break;
            case "lineargradient":
                fa(a, "axial", [a.getAttribute("x1"), a.getAttribute("y1"), a.getAttribute("x2"), a.getAttribute("y2")], b, d);
                break;
            case "radialgradient":
                fa(a, "radial", [a.getAttribute("fx") || a.getAttribute("cx"), a.getAttribute("fy") || a.getAttribute("cy"), 0, a.getAttribute("cx") ||
                0, a.getAttribute("cy") || 0, a.getAttribute("r") || 0], b, d);
                break;
            case "pattern":
                sa(a, b, d)
        }
        r ? e.endFormObject(d.get() + a.getAttribute("id")) : e.restoreGraphicsState()
    }, O = function (a, c, b) {
        e = c;
        c = b.scale || 1;
        var d = b.xOffset || 0;
        b = b.yOffset || 0;
        e.saveGraphicsState();
        e.setCurrentTransformationMatrix(new e.Matrix(c, 0, 0, c, d, b));
        W(a.cloneNode(!0), e.unitMatrix, {}, new aa(""), !1);
        e.restoreGraphicsState();
        return e
    };
    "function" === typeof define && define.amd ? define(["rgbcolor"], function (a) {
        z = a;
        return O
    }) : "undefined" !== typeof module &&
    module.exports ? (z = require("./rgbcolor.js"), module.exports = O) : (z = U.RGBColor, U.svg2pdf = O, U.svgElementToPdf = O);
    return O
})("undefined" !== typeof self && self || "undefined" !== typeof window && window || this);
