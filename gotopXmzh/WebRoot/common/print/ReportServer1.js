/*!
 * jQuery JavaScript Library v1.6.4
 * http://jquery.com/
 *
 * Copyright 2011, John Resig
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * Includes Sizzle.js
 * http://sizzlejs.com/
 * Copyright 2011, The Dojo Foundation
 * Released under the MIT, BSD, and GPL Licenses.
 *
 * Date: Mon Sep 12 18:54:48 2011 -0400
 */
(function(a7, K) {
	var ap = a7.document,
	bq = a7.navigator,
	bh = a7.location;
	var b = (function() {
		var bB = function(bX, bY) {
			return new bB.fn.init(bX, bY, bz)
		},
		bR = a7.jQuery,
		bD = a7.$,
		bz,
		bV = /^(?:[^#<]*(<[\w\W]+>)[^>]*$|#([\w\-]*)$)/,
		bJ = /\S/,
		bF = /^\s+/,
		bA = /\s+$/,
		bE = /\d/,
		bw = /^<(\w+)\s*\/?>(?:<\/\1>)?$/,
		bK = /^[\],:{}\s]*$/,
		bT = /\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g,
		bM = /"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,
		bG = /(?:^|:|,)(?:\s*\[)+/g,
		bu = /(webkit)[ \/]([\w.]+)/,
		bO = /(opera)(?:.*version)?[ \/]([\w.]+)/,
		bN = /(msie) ([\w.]+)/,
		bP = /(mozilla)(?:.*? rv:([\w.]+))?/,
		bx = /-([a-z]|[0-9])/ig,
		bW = /^-ms-/,
		bQ = function(bX, bY) {
			return (bY + "").toUpperCase()
		},
		bU = bq.userAgent,
		bS,
		by,
		e,
		bI = Object.prototype.toString,
		bC = Object.prototype.hasOwnProperty,
		bv = Array.prototype.push,
		bH = Array.prototype.slice,
		bL = String.prototype.trim,
		br = Array.prototype.indexOf,
		bt = {};
		bB.fn = bB.prototype = {
			constructor: bB,
			init: function(bX, b1, b0) {
				var bZ, b2, bY, b3;
				if (!bX) {
					return this
				}
				if (bX.nodeType) {
					this.context = this[0] = bX;
					this.length = 1;
					return this
				}
				if (bX === "body" && !b1 && ap.body) {
					this.context = ap;
					this[0] = ap.body;
					this.selector = bX;
					this.length = 1;
					return this
				}
				if (typeof bX === "string") {
					if (bX.charAt(0) === "<" && bX.charAt(bX.length - 1) === ">" && bX.length >= 3) {
						bZ = [null, bX, null]
					} else {
						bZ = bV.exec(bX)
					}
					if (bZ && (bZ[1] || !b1)) {
						if (bZ[1]) {
							b1 = b1 instanceof bB ? b1[0] : b1;
							b3 = (b1 ? b1.ownerDocument || b1: ap);
							bY = bw.exec(bX);
							if (bY) {
								if (bB.isPlainObject(b1)) {
									bX = [ap.createElement(bY[1])];
									bB.fn.attr.call(bX, b1, true)
								} else {
									bX = [b3.createElement(bY[1])]
								}
							} else {
								bY = bB.buildFragment([bZ[1]], [b3]);
								bX = (bY.cacheable ? bB.clone(bY.fragment) : bY.fragment).childNodes
							}
							return bB.merge(this, bX)
						} else {
							b2 = ap.getElementById(bZ[2]);
							if (b2 && b2.parentNode) {
								if (b2.id !== bZ[2]) {
									return b0.find(bX)
								}
								this.length = 1;
								this[0] = b2
							}
							this.context = ap;
							this.selector = bX;
							return this
						}
					} else {
						if (!b1 || b1.jquery) {
							return (b1 || b0).find(bX)
						} else {
							return this.constructor(b1).find(bX)
						}
					}
				} else {
					if (bB.isFunction(bX)) {
						return b0.ready(bX)
					}
				}
				if (bX.selector !== K) {
					this.selector = bX.selector;
					this.context = bX.context
				}
				return bB.makeArray(bX, this)
			},
			selector: "",
			jquery: "1.6.4",
			length: 0,
			size: function() {
				return this.length
			},
			toArray: function() {
				return bH.call(this, 0)
			},
			get: function(bX) {
				return bX == null ? this.toArray() : (bX < 0 ? this[this.length + bX] : this[bX])
			},
			pushStack: function(bY, b0, bX) {
				var bZ = this.constructor();
				if (bB.isArray(bY)) {
					bv.apply(bZ, bY)
				} else {
					bB.merge(bZ, bY)
				}
				bZ.prevObject = this;
				bZ.context = this.context;
				if (b0 === "find") {
					bZ.selector = this.selector + (this.selector ? " ": "") + bX
				} else {
					if (b0) {
						bZ.selector = this.selector + "." + b0 + "(" + bX + ")"
					}
				}
				return bZ
			},
			each: function(bY, bX) {
				return bB.each(this, bY, bX)
			},
			ready: function(bX) {
				bB.bindReady();
				by.done(bX);
				return this
			},
			eq: function(bX) {
				return bX === -1 ? this.slice(bX) : this.slice(bX, +bX + 1)
			},
			first: function() {
				return this.eq(0)
			},
			last: function() {
				return this.eq( - 1)
			},
			slice: function() {
				return this.pushStack(bH.apply(this, arguments), "slice", bH.call(arguments).join(","))
			},
			map: function(bX) {
				return this.pushStack(bB.map(this,
				function(bZ, bY) {
					return bX.call(bZ, bY, bZ)
				}))
			},
			end: function() {
				return this.prevObject || this.constructor(null)
			},
			push: bv,
			sort: [].sort,
			splice: [].splice
		};
		bB.fn.init.prototype = bB.fn;
		bB.extend = bB.fn.extend = function() {
			var b6, bZ, bX, bY, b3, b4, b2 = arguments[0] || {},
			b1 = 1,
			b0 = arguments.length,
			b5 = false;
			if (typeof b2 === "boolean") {
				b5 = b2;
				b2 = arguments[1] || {};
				b1 = 2
			}
			if (typeof b2 !== "object" && !bB.isFunction(b2)) {
				b2 = {}
			}
			if (b0 === b1) {
				b2 = this; --b1
			}
			for (; b1 < b0; b1++) {
				if ((b6 = arguments[b1]) != null) {
					for (bZ in b6) {
						bX = b2[bZ];
						bY = b6[bZ];
						if (b2 === bY) {
							continue
						}
						if (b5 && bY && (bB.isPlainObject(bY) || (b3 = bB.isArray(bY)))) {
							if (b3) {
								b3 = false;
								b4 = bX && bB.isArray(bX) ? bX: []
							} else {
								b4 = bX && bB.isPlainObject(bX) ? bX: {}
							}
							b2[bZ] = bB.extend(b5, b4, bY)
						} else {
							if (bY !== K) {
								b2[bZ] = bY
							}
						}
					}
				}
			}
			return b2
		};
		bB.extend({
			noConflict: function(bX) {
				if (a7.$ === bB) {
					a7.$ = bD
				}
				if (bX && a7.jQuery === bB) {
					a7.jQuery = bR
				}
				return bB
			},
			isReady: false,
			readyWait: 1,
			holdReady: function(bX) {
				if (bX) {
					bB.readyWait++
				} else {
					bB.ready(true)
				}
			},
			ready: function(bX) {
				if ((bX === true && !--bB.readyWait) || (bX !== true && !bB.isReady)) {
					if (!ap.body) {
						return setTimeout(bB.ready, 1)
					}
					bB.isReady = true;
					if (bX !== true && --bB.readyWait > 0) {
						return
					}
					by.resolveWith(ap, [bB]);
					if (bB.fn.trigger) {
						bB(ap).trigger("ready").unbind("ready")
					}
				}
			},
			bindReady: function() {
				if (by) {
					return
				}
				by = bB._Deferred();
				if (ap.readyState === "complete") {
					return setTimeout(bB.ready, 1)
				}
				if (ap.addEventListener) {
					ap.addEventListener("DOMContentLoaded", e, false);
					a7.addEventListener("load", bB.ready, false)
				} else {
					if (ap.attachEvent) {
						ap.attachEvent("onreadystatechange", e);
						a7.attachEvent("onload", bB.ready);
						var bX = false;
						try {
							bX = a7.frameElement == null
						} catch(bY) {}
						if (ap.documentElement.doScroll && bX) {
							bs()
						}
					}
				}
			},
			isFunction: function(bX) {
				return bB.type(bX) === "function"
			},
			isArray: Array.isArray ||
			function(bX) {
				return bB.type(bX) === "array"
			},
			isWindow: function(bX) {
				return bX && typeof bX === "object" && "setInterval" in bX
			},
			isNaN: function(bX) {
				return bX == null || !bE.test(bX) || isNaN(bX)
			},
			type: function(bX) {
				return bX == null ? String(bX) : bt[bI.call(bX)] || "object"
			},
			isPlainObject: function(bZ) {
				if (!bZ || bB.type(bZ) !== "object" || bZ.nodeType || bB.isWindow(bZ)) {
					return false
				}
				try {
					if (bZ.constructor && !bC.call(bZ, "constructor") && !bC.call(bZ.constructor.prototype, "isPrototypeOf")) {
						return false
					}
				} catch(bY) {
					return false
				}
				var bX;
				for (bX in bZ) {}
				return bX === K || bC.call(bZ, bX)
			},
			isEmptyObject: function(bY) {
				for (var bX in bY) {
					return false
				}
				return true
			},
			error: function(bX) {
				throw bX
			},
			parseJSON: function(bX) {
				if (typeof bX !== "string" || !bX) {
					return null
				}
				bX = bB.trim(bX);
				if (a7.JSON && a7.JSON.parse) {
					return a7.JSON.parse(bX)
				}
				if (bK.test(bX.replace(bT, "@").replace(bM, "]").replace(bG, ""))) {
					return (new Function("return " + bX))()
				}
				bB.error("Invalid JSON: " + bX)
			},
			parseXML: function(bZ) {
				var bX, bY;
				try {
					if (a7.DOMParser) {
						bY = new DOMParser();
						bX = bY.parseFromString(bZ, "text/xml")
					} else {
						bX = new ActiveXObject("Microsoft.XMLDOM");
						bX.async = "false";
						bX.loadXML(bZ)
					}
				} catch(b0) {
					bX = K
				}
				if (!bX || !bX.documentElement || bX.getElementsByTagName("parsererror").length) {
					bB.error("Invalid XML: " + bZ)
				}
				return bX
			},
			noop: function() {},
			globalEval: function(bX) {
				if (bX && bJ.test(bX)) { (a7.execScript ||
					function(bY) {
						a7["eval"].call(a7, bY)
					})(bX)
				}
			},
			camelCase: function(bX) {
				return bX.replace(bW, "ms-").replace(bx, bQ)
			},
			nodeName: function(bY, bX) {
				return bY.nodeName && bY.nodeName.toUpperCase() === bX.toUpperCase()
			},
			each: function(b0, b3, bZ) {
				var bY, b1 = 0,
				b2 = b0.length,
				bX = b2 === K || bB.isFunction(b0);
				if (bZ) {
					if (bX) {
						for (bY in b0) {
							if (b3.apply(b0[bY], bZ) === false) {
								break
							}
						}
					} else {
						for (; b1 < b2;) {
							if (b3.apply(b0[b1++], bZ) === false) {
								break
							}
						}
					}
				} else {
					if (bX) {
						for (bY in b0) {
							if (b3.call(b0[bY], bY, b0[bY]) === false) {
								break
							}
						}
					} else {
						for (; b1 < b2;) {
							if (b3.call(b0[b1], b1, b0[b1++]) === false) {
								break
							}
						}
					}
				}
				return b0
			},
			trim: bL ?
			function(bX) {
				return bX == null ? "": bL.call(bX)
			}: function(bX) {
				return bX == null ? "": bX.toString().replace(bF, "").replace(bA, "")
			},
			makeArray: function(b0, bY) {
				var bX = bY || [];
				if (b0 != null) {
					var bZ = bB.type(b0);
					if (b0.length == null || bZ === "string" || bZ === "function" || bZ === "regexp" || bB.isWindow(b0)) {
						bv.call(bX, b0)
					} else {
						bB.merge(bX, b0)
					}
				}
				return bX
			},
			inArray: function(bZ, b0) {
				if (!b0) {
					return - 1
				}
				if (br) {
					return br.call(b0, bZ)
				}
				for (var bX = 0,
				bY = b0.length; bX < bY; bX++) {
					if (b0[bX] === bZ) {
						return bX
					}
				}
				return - 1
			},
			merge: function(b1, bZ) {
				var b0 = b1.length,
				bY = 0;
				if (typeof bZ.length === "number") {
					for (var bX = bZ.length; bY < bX; bY++) {
						b1[b0++] = bZ[bY]
					}
				} else {
					while (bZ[bY] !== K) {
						b1[b0++] = bZ[bY++]
					}
				}
				b1.length = b0;
				return b1
			},
			grep: function(bY, b3, bX) {
				var bZ = [],
				b2;
				bX = !!bX;
				for (var b0 = 0,
				b1 = bY.length; b0 < b1; b0++) {
					b2 = !!b3(bY[b0], b0);
					if (bX !== b2) {
						bZ.push(bY[b0])
					}
				}
				return bZ
			},
			map: function(bX, b4, b5) {
				var b2, b3, b1 = [],
				bZ = 0,
				bY = bX.length,
				b0 = bX instanceof bB || bY !== K && typeof bY === "number" && ((bY > 0 && bX[0] && bX[bY - 1]) || bY === 0 || bB.isArray(bX));
				if (b0) {
					for (; bZ < bY; bZ++) {
						b2 = b4(bX[bZ], bZ, b5);
						if (b2 != null) {
							b1[b1.length] = b2
						}
					}
				} else {
					for (b3 in bX) {
						b2 = b4(bX[b3], b3, b5);
						if (b2 != null) {
							b1[b1.length] = b2
						}
					}
				}
				return b1.concat.apply([], b1)
			},
			guid: 1,
			proxy: function(b1, b0) {
				if (typeof b0 === "string") {
					var bZ = b1[b0];
					b0 = b1;
					b1 = bZ
				}
				if (!bB.isFunction(b1)) {
					return K
				}
				var bX = bH.call(arguments, 2),
				bY = function() {
					return b1.apply(b0, bX.concat(bH.call(arguments)))
				};
				bY.guid = b1.guid = b1.guid || bY.guid || bB.guid++;
				return bY
			},
			access: function(bX, b5, b3, bZ, b2, b4) {
				var bY = bX.length;
				if (typeof b5 === "object") {
					for (var b0 in b5) {
						bB.access(bX, b0, b5[b0], bZ, b2, b3)
					}
					return bX
				}
				if (b3 !== K) {
					bZ = !b4 && bZ && bB.isFunction(b3);
					for (var b1 = 0; b1 < bY; b1++) {
						b2(bX[b1], b5, bZ ? b3.call(bX[b1], b1, b2(bX[b1], b5)) : b3, b4)
					}
					return bX
				}
				return bY ? b2(bX[0], b5) : K
			},
			now: function() {
				return (new Date()).getTime()
			},
			uaMatch: function(bY) {
				bY = bY.toLowerCase();
				var bX = bu.exec(bY) || bO.exec(bY) || bN.exec(bY) || bY.indexOf("compatible") < 0 && bP.exec(bY) || [];
				return {
					browser: bX[1] || "",
					version: bX[2] || "0"
				}
			},
			sub: function() {
				function bX(b0, b1) {
					return new bX.fn.init(b0, b1)
				}
				bB.extend(true, bX, this);
				bX.superclass = this;
				bX.fn = bX.prototype = this();
				bX.fn.constructor = bX;
				bX.sub = this.sub;
				bX.fn.init = function bZ(b0, b1) {
					if (b1 && b1 instanceof bB && !(b1 instanceof bX)) {
						b1 = bX(b1)
					}
					return bB.fn.init.call(this, b0, b1, bY)
				};
				bX.fn.init.prototype = bX.fn;
				var bY = bX(ap);
				return bX
			},
			browser: {}
		});
		bB.each("Boolean Number String Function Array Date RegExp Object".split(" "),
		function(bY, bX) {
			bt["[object " + bX + "]"] = bX.toLowerCase()
		});
		bS = bB.uaMatch(bU);
		if (bS.browser) {
			bB.browser[bS.browser] = true;
			bB.browser.version = bS.version
		}
		if (bB.browser.webkit) {
			bB.browser.safari = true
		}
		if (bJ.test("\xA0")) {
			bF = /^[\s\xA0]+/;
			bA = /[\s\xA0]+$/
		}
		bz = bB(ap);
		if (ap.addEventListener) {
			e = function() {
				ap.removeEventListener("DOMContentLoaded", e, false);
				bB.ready()
			}
		} else {
			if (ap.attachEvent) {
				e = function() {
					if (ap.readyState === "complete") {
						ap.detachEvent("onreadystatechange", e);
						bB.ready()
					}
				}
			}
		}
		function bs() {
			if (bB.isReady) {
				return
			}
			try {
				ap.documentElement.doScroll("left")
			} catch(bX) {
				setTimeout(bs, 1);
				return
			}
			bB.ready()
		}
		return bB
	})();
	var a = "done fail isResolved isRejected promise then always pipe".split(" "),
	aE = [].slice;
	b.extend({
		_Deferred: function() {
			var bt = [],
			bu,
			br,
			bs,
			e = {
				done: function() {
					if (!bs) {
						var bw = arguments,
						bx, bA, bz, by, bv;
						if (bu) {
							bv = bu;
							bu = 0
						}
						for (bx = 0, bA = bw.length; bx < bA; bx++) {
							bz = bw[bx];
							by = b.type(bz);
							if (by === "array") {
								e.done.apply(e, bz)
							} else {
								if (by === "function") {
									bt.push(bz)
								}
							}
						}
						if (bv) {
							e.resolveWith(bv[0], bv[1])
						}
					}
					return this
				},
				resolveWith: function(bw, bv) {
					if (!bs && !bu && !br) {
						bv = bv || [];
						br = 1;
						try {
							while (bt[0]) {
								bt.shift().apply(bw, bv)
							}
						} finally {
							bu = [bw, bv];
							br = 0
						}
					}
					return this
				},
				resolve: function() {
					e.resolveWith(this, arguments);
					return this
				},
				isResolved: function() {
					return !! (br || bu)
				},
				cancel: function() {
					bs = 1;
					bt = [];
					return this
				}
			};
			return e
		},
		Deferred: function(br) {
			var e = b._Deferred(),
			bt = b._Deferred(),
			bs;
			b.extend(e, {
				then: function(bv, bu) {
					e.done(bv).fail(bu);
					return this
				},
				always: function() {
					return e.done.apply(e, arguments).fail.apply(this, arguments)
				},
				fail: bt.done,
				rejectWith: bt.resolveWith,
				reject: bt.resolve,
				isRejected: bt.isResolved,
				pipe: function(bv, bu) {
					return b.Deferred(function(bw) {
						b.each({
							done: [bv, "resolve"],
							fail: [bu, "reject"]
						},
						function(by, bB) {
							var bx = bB[0],
							bA = bB[1],
							bz;
							if (b.isFunction(bx)) {
								e[by](function() {
									bz = bx.apply(this, arguments);
									if (bz && b.isFunction(bz.promise)) {
										bz.promise().then(bw.resolve, bw.reject)
									} else {
										bw[bA + "With"](this === e ? bw: this, [bz])
									}
								})
							} else {
								e[by](bw[bA])
							}
						})
					}).promise()
				},
				promise: function(bv) {
					if (bv == null) {
						if (bs) {
							return bs
						}
						bs = bv = {}
					}
					var bu = a.length;
					while (bu--) {
						bv[a[bu]] = e[a[bu]]
					}
					return bv
				}
			});
			e.done(bt.cancel).fail(e.cancel);
			delete e.cancel;
			if (br) {
				br.call(e, e)
			}
			return e
		},
		when: function(bw) {
			var br = arguments,
			bs = 0,
			bv = br.length,
			bu = bv,
			e = bv <= 1 && bw && b.isFunction(bw.promise) ? bw: b.Deferred();
			function bt(bx) {
				return function(by) {
					br[bx] = arguments.length > 1 ? aE.call(arguments, 0) : by;
					if (! (--bu)) {
						e.resolveWith(e, aE.call(br, 0))
					}
				}
			}
			if (bv > 1) {
				for (; bs < bv; bs++) {
					if (br[bs] && b.isFunction(br[bs].promise)) {
						br[bs].promise().then(bt(bs), e.reject)
					} else {--bu
					}
				}
				if (!bu) {
					e.resolveWith(e, br)
				}
			} else {
				if (e !== bw) {
					e.resolveWith(e, bv ? [bw] : [])
				}
			}
			return e.promise()
		}
	});
	b.support = (function() {
		var bB = ap.createElement("div"),
		bI = ap.documentElement,
		bu,
		bJ,
		bC,
		bs,
		bA,
		bv,
		by,
		br,
		bz,
		bD,
		bx,
		bH,
		bF,
		bt,
		bw,
		bE,
		bK;
		bB.setAttribute("className", "t");
		bB.innerHTML = "   <link/><table></table><a href='/a' style='top:1px;float:left;opacity:.55;'>a</a><input type='checkbox'/>";
		bu = bB.getElementsByTagName("*");
		bJ = bB.getElementsByTagName("a")[0];
		if (!bu || !bu.length || !bJ) {
			return {}
		}
		bC = ap.createElement("select");
		bs = bC.appendChild(ap.createElement("option"));
		bA = bB.getElementsByTagName("input")[0];
		by = {
			leadingWhitespace: (bB.firstChild.nodeType === 3),
			tbody: !bB.getElementsByTagName("tbody").length,
			htmlSerialize: !!bB.getElementsByTagName("link").length,
			style: /top/.test(bJ.getAttribute("style")),
			hrefNormalized: (bJ.getAttribute("href") === "/a"),
			opacity: /^0.55$/.test(bJ.style.opacity),
			cssFloat: !!bJ.style.cssFloat,
			checkOn: (bA.value === "on"),
			optSelected: bs.selected,
			getSetAttribute: bB.className !== "t",
			submitBubbles: true,
			changeBubbles: true,
			focusinBubbles: false,
			deleteExpando: true,
			noCloneEvent: true,
			inlineBlockNeedsLayout: false,
			shrinkWrapBlocks: false,
			reliableMarginRight: true
		};
		bA.checked = true;
		by.noCloneChecked = bA.cloneNode(true).checked;
		bC.disabled = true;
		by.optDisabled = !bs.disabled;
		try {
			delete bB.test
		} catch(bG) {
			by.deleteExpando = false
		}
		if (!bB.addEventListener && bB.attachEvent && bB.fireEvent) {
			bB.attachEvent("onclick",
			function() {
				by.noCloneEvent = false
			});
			bB.cloneNode(true).fireEvent("onclick")
		}
		bA = ap.createElement("input");
		bA.value = "t";
		bA.setAttribute("type", "radio");
		by.radioValue = bA.value === "t";
		bA.setAttribute("checked", "checked");
		bB.appendChild(bA);
		br = ap.createDocumentFragment();
		br.appendChild(bB.firstChild);
		by.checkClone = br.cloneNode(true).cloneNode(true).lastChild.checked;
		bB.innerHTML = "";
		bB.style.width = bB.style.paddingLeft = "1px";
		bz = ap.getElementsByTagName("body")[0];
		bx = ap.createElement(bz ? "div": "body");
		bH = {
			visibility: "hidden",
			width: 0,
			height: 0,
			border: 0,
			margin: 0,
			background: "none"
		};
		if (bz) {
			b.extend(bH, {
				position: "absolute",
				left: "-1000px",
				top: "-1000px"
			})
		}
		for (bE in bH) {
			bx.style[bE] = bH[bE]
		}
		bx.appendChild(bB);
		bD = bz || bI;
		bD.insertBefore(bx, bD.firstChild);
		by.appendChecked = bA.checked;
		by.boxModel = ap.compatMode === "CSS1Compat";
		if ("zoom" in bB.style) {
			bB.style.display = "inline";
			bB.style.zoom = 1;
			by.inlineBlockNeedsLayout = (bB.offsetWidth === 2);
			bB.style.display = "";
			bB.innerHTML = "<div style='width:4px;'></div>";
			by.shrinkWrapBlocks = (bB.offsetWidth !== 2)
		}
		bB.innerHTML = "<table><tr><td style='padding:0;border:0;display:none'></td><td>t</td></tr></table>";
		bF = bB.getElementsByTagName("td");
		bK = (bF[0].offsetHeight === 0);
		bF[0].style.display = "";
		bF[1].style.display = "none";
		by.reliableHiddenOffsets = bK && (bF[0].offsetHeight === 0);
		bB.innerHTML = "";
		if (ap.defaultView && ap.defaultView.getComputedStyle) {
			bv = ap.createElement("div");
			bv.style.width = "0";
			bv.style.marginRight = "0";
			bB.appendChild(bv);
			by.reliableMarginRight = (parseInt((ap.defaultView.getComputedStyle(bv, null) || {
				marginRight: 0
			}).marginRight, 10) || 0) === 0
		}
		bx.innerHTML = "";
		bD.removeChild(bx);
		if (bB.attachEvent) {
			for (bE in {
				submit: 1,
				change: 1,
				focusin: 1
			}) {
				bw = "on" + bE;
				bK = (bw in bB);
				if (!bK) {
					bB.setAttribute(bw, "return;");
					bK = (typeof bB[bw] === "function")
				}
				by[bE + "Bubbles"] = bK
			}
		}
		bx = br = bC = bs = bz = bv = bB = bA = null;
		return by
	})();
	b.boxModel = b.support.boxModel;
	var aL = /^(?:\{.*\}|\[.*\])$/,
	av = /([A-Z])/g;
	b.extend({
		cache: {},
		uuid: 0,
		expando: "jQuery" + (b.fn.jquery + Math.random()).replace(/\D/g, ""),
		noData: {
			embed: true,
			object: "clsid:D27CDB6E-AE6D-11cf-96B8-444553540000",
			applet: true
		},
		hasData: function(e) {
			e = e.nodeType ? b.cache[e[b.expando]] : e[b.expando];
			return !! e && !S(e)
		},
		data: function(bt, br, bv, bu) {
			if (!b.acceptData(bt)) {
				return
			}
			var bw, by, bz = b.expando,
			bx = typeof br === "string",
			bA = bt.nodeType,
			e = bA ? b.cache: bt,
			bs = bA ? bt[b.expando] : bt[b.expando] && b.expando;
			if ((!bs || (bu && bs && (e[bs] && !e[bs][bz]))) && bx && bv === K) {
				return
			}
			if (!bs) {
				if (bA) {
					bt[b.expando] = bs = ++b.uuid
				} else {
					bs = b.expando
				}
			}
			if (!e[bs]) {
				e[bs] = {};
				if (!bA) {
					e[bs].toJSON = b.noop
				}
			}
			if (typeof br === "object" || typeof br === "function") {
				if (bu) {
					e[bs][bz] = b.extend(e[bs][bz], br)
				} else {
					e[bs] = b.extend(e[bs], br)
				}
			}
			bw = e[bs];
			if (bu) {
				if (!bw[bz]) {
					bw[bz] = {}
				}
				bw = bw[bz]
			}
			if (bv !== K) {
				bw[b.camelCase(br)] = bv
			}
			if (br === "events" && !bw[br]) {
				return bw[bz] && bw[bz].events
			}
			if (bx) {
				by = bw[br];
				if (by == null) {
					by = bw[b.camelCase(br)]
				}
			} else {
				by = bw
			}
			return by
		},
		removeData: function(bu, bs, bv) {
			if (!b.acceptData(bu)) {
				return
			}
			var bw, bx = b.expando,
			by = bu.nodeType,
			br = by ? b.cache: bu,
			bt = by ? bu[b.expando] : b.expando;
			if (!br[bt]) {
				return
			}
			if (bs) {
				bw = bv ? br[bt][bx] : br[bt];
				if (bw) {
					if (!bw[bs]) {
						bs = b.camelCase(bs)
					}
					delete bw[bs];
					if (!S(bw)) {
						return
					}
				}
			}
			if (bv) {
				delete br[bt][bx];
				if (!S(br[bt])) {
					return
				}
			}
			var e = br[bt][bx];
			if (b.support.deleteExpando || !br.setInterval) {
				delete br[bt]
			} else {
				br[bt] = null
			}
			if (e) {
				br[bt] = {};
				if (!by) {
					br[bt].toJSON = b.noop
				}
				br[bt][bx] = e
			} else {
				if (by) {
					if (b.support.deleteExpando) {
						delete bu[b.expando]
					} else {
						if (bu.removeAttribute) {
							bu.removeAttribute(b.expando)
						} else {
							bu[b.expando] = null
						}
					}
				}
			}
		},
		_data: function(br, e, bs) {
			return b.data(br, e, bs, true)
		},
		acceptData: function(br) {
			if (br.nodeName) {
				var e = b.noData[br.nodeName.toLowerCase()];
				if (e) {
					return ! (e === true || br.getAttribute("classid") !== e)
				}
			}
			return true
		}
	});
	b.fn.extend({
		data: function(bu, bw) {
			var bv = null;
			if (typeof bu === "undefined") {
				if (this.length) {
					bv = b.data(this[0]);
					if (this[0].nodeType === 1) {
						var e = this[0].attributes,
						bs;
						for (var bt = 0,
						br = e.length; bt < br; bt++) {
							bs = e[bt].name;
							if (bs.indexOf("data-") === 0) {
								bs = b.camelCase(bs.substring(5));
								a1(this[0], bs, bv[bs])
							}
						}
					}
				}
				return bv
			} else {
				if (typeof bu === "object") {
					return this.each(function() {
						b.data(this, bu)
					})
				}
			}
			var bx = bu.split(".");
			bx[1] = bx[1] ? "." + bx[1] : "";
			if (bw === K) {
				bv = this.triggerHandler("getData" + bx[1] + "!", [bx[0]]);
				if (bv === K && this.length) {
					bv = b.data(this[0], bu);
					bv = a1(this[0], bu, bv)
				}
				return bv === K && bx[1] ? this.data(bx[0]) : bv
			} else {
				return this.each(function() {
					var bz = b(this),
					by = [bx[0], bw];
					bz.triggerHandler("setData" + bx[1] + "!", by);
					b.data(this, bu, bw);
					bz.triggerHandler("changeData" + bx[1] + "!", by)
				})
			}
		},
		removeData: function(e) {
			return this.each(function() {
				b.removeData(this, e)
			})
		}
	});
	function a1(bt, bs, bu) {
		if (bu === K && bt.nodeType === 1) {
			var br = "data-" + bs.replace(av, "-$1").toLowerCase();
			bu = bt.getAttribute(br);
			if (typeof bu === "string") {
				try {
					bu = bu === "true" ? true: bu === "false" ? false: bu === "null" ? null: !b.isNaN(bu) ? parseFloat(bu) : aL.test(bu) ? b.parseJSON(bu) : bu
				} catch(bv) {}
				b.data(bt, bs, bu)
			} else {
				bu = K
			}
		}
		return bu
	}
	function S(br) {
		for (var e in br) {
			if (e !== "toJSON") {
				return false
			}
		}
		return true
	}
	function bd(bu, bt, bw) {
		var bs = bt + "defer",
		br = bt + "queue",
		e = bt + "mark",
		bv = b.data(bu, bs, K, true);
		if (bv && (bw === "queue" || !b.data(bu, br, K, true)) && (bw === "mark" || !b.data(bu, e, K, true))) {
			setTimeout(function() {
				if (!b.data(bu, br, K, true) && !b.data(bu, e, K, true)) {
					b.removeData(bu, bs, true);
					bv.resolve()
				}
			},
			0)
		}
	}
	b.extend({
		_mark: function(br, e) {
			if (br) {
				e = (e || "fx") + "mark";
				b.data(br, e, (b.data(br, e, K, true) || 0) + 1, true)
			}
		},
		_unmark: function(bu, bt, br) {
			if (bu !== true) {
				br = bt;
				bt = bu;
				bu = false
			}
			if (bt) {
				br = br || "fx";
				var e = br + "mark",
				bs = bu ? 0 : ((b.data(bt, e, K, true) || 1) - 1);
				if (bs) {
					b.data(bt, e, bs, true)
				} else {
					b.removeData(bt, e, true);
					bd(bt, br, "mark")
				}
			}
		},
		queue: function(br, e, bt) {
			if (br) {
				e = (e || "fx") + "queue";
				var bs = b.data(br, e, K, true);
				if (bt) {
					if (!bs || b.isArray(bt)) {
						bs = b.data(br, e, b.makeArray(bt), true)
					} else {
						bs.push(bt)
					}
				}
				return bs || []
			}
		},
		dequeue: function(bt, bs) {
			bs = bs || "fx";
			var e = b.queue(bt, bs),
			br = e.shift(),
			bu;
			if (br === "inprogress") {
				br = e.shift()
			}
			if (br) {
				if (bs === "fx") {
					e.unshift("inprogress")
				}
				br.call(bt,
				function() {
					b.dequeue(bt, bs)
				})
			}
			if (!e.length) {
				b.removeData(bt, bs + "queue", true);
				bd(bt, bs, "queue")
			}
		}
	});
	b.fn.extend({
		queue: function(e, br) {
			if (typeof e !== "string") {
				br = e;
				e = "fx"
			}
			if (br === K) {
				return b.queue(this[0], e)
			}
			return this.each(function() {
				var bs = b.queue(this, e, br);
				if (e === "fx" && bs[0] !== "inprogress") {
					b.dequeue(this, e)
				}
			})
		},
		dequeue: function(e) {
			return this.each(function() {
				b.dequeue(this, e)
			})
		},
		delay: function(br, e) {
			br = b.fx ? b.fx.speeds[br] || br: br;
			e = e || "fx";
			return this.queue(e,
			function() {
				var bs = this;
				setTimeout(function() {
					b.dequeue(bs, e)
				},
				br)
			})
		},
		clearQueue: function(e) {
			return this.queue(e || "fx", [])
		},
		promise: function(bz, bs) {
			if (typeof bz !== "string") {
				bs = bz;
				bz = K
			}
			bz = bz || "fx";
			var e = b.Deferred(),
			br = this,
			bu = br.length,
			bx = 1,
			bv = bz + "defer",
			bw = bz + "queue",
			by = bz + "mark",
			bt;
			function bA() {
				if (! (--bx)) {
					e.resolveWith(br, [br])
				}
			}
			while (bu--) {
				if ((bt = b.data(br[bu], bv, K, true) || (b.data(br[bu], bw, K, true) || b.data(br[bu], by, K, true)) && b.data(br[bu], bv, b._Deferred(), true))) {
					bx++;
					bt.done(bA)
				}
			}
			bA();
			return e.promise()
		}
	});
	var aJ = /[\n\t\r]/g,
	ab = /\s+/,
	aN = /\r/g,
	g = /^(?:button|input)$/i,
	D = /^(?:button|input|object|select|textarea)$/i,
	l = /^a(?:rea)?$/i,
	aj = /^(?:autofocus|autoplay|async|checked|controls|defer|disabled|hidden|loop|multiple|open|readonly|required|scoped|selected)$/i,
	ba, aU;
	b.fn.extend({
		attr: function(e, br) {
			return b.access(this, e, br, true, b.attr)
		},
		removeAttr: function(e) {
			return this.each(function() {
				b.removeAttr(this, e)
			})
		},
		prop: function(e, br) {
			return b.access(this, e, br, true, b.prop)
		},
		removeProp: function(e) {
			e = b.propFix[e] || e;
			return this.each(function() {
				try {
					this[e] = K;
					delete this[e]
				} catch(br) {}
			})
		},
		addClass: function(bu) {
			var bw, bs, br, bt, bv, bx, e;
			if (b.isFunction(bu)) {
				return this.each(function(by) {
					b(this).addClass(bu.call(this, by, this.className))
				})
			}
			if (bu && typeof bu === "string") {
				bw = bu.split(ab);
				for (bs = 0, br = this.length; bs < br; bs++) {
					bt = this[bs];
					if (bt.nodeType === 1) {
						if (!bt.className && bw.length === 1) {
							bt.className = bu
						} else {
							bv = " " + bt.className + " ";
							for (bx = 0, e = bw.length; bx < e; bx++) {
								if (!~bv.indexOf(" " + bw[bx] + " ")) {
									bv += bw[bx] + " "
								}
							}
							bt.className = b.trim(bv)
						}
					}
				}
			}
			return this
		},
		removeClass: function(bv) {
			var bw, bs, br, bu, bt, bx, e;
			if (b.isFunction(bv)) {
				return this.each(function(by) {
					b(this).removeClass(bv.call(this, by, this.className))
				})
			}
			if ((bv && typeof bv === "string") || bv === K) {
				bw = (bv || "").split(ab);
				for (bs = 0, br = this.length; bs < br; bs++) {
					bu = this[bs];
					if (bu.nodeType === 1 && bu.className) {
						if (bv) {
							bt = (" " + bu.className + " ").replace(aJ, " ");
							for (bx = 0, e = bw.length; bx < e; bx++) {
								bt = bt.replace(" " + bw[bx] + " ", " ")
							}
							bu.className = b.trim(bt)
						} else {
							bu.className = ""
						}
					}
				}
			}
			return this
		},
		toggleClass: function(bt, br) {
			var bs = typeof bt,
			e = typeof br === "boolean";
			if (b.isFunction(bt)) {
				return this.each(function(bu) {
					b(this).toggleClass(bt.call(this, bu, this.className, br), br)
				})
			}
			return this.each(function() {
				if (bs === "string") {
					var bw, bv = 0,
					bu = b(this),
					bx = br,
					by = bt.split(ab);
					while ((bw = by[bv++])) {
						bx = e ? bx: !bu.hasClass(bw);
						bu[bx ? "addClass": "removeClass"](bw)
					}
				} else {
					if (bs === "undefined" || bs === "boolean") {
						if (this.className) {
							b._data(this, "__className__", this.className)
						}
						this.className = this.className || bt === false ? "": b._data(this, "__className__") || ""
					}
				}
			})
		},
		hasClass: function(e) {
			var bt = " " + e + " ";
			for (var bs = 0,
			br = this.length; bs < br; bs++) {
				if (this[bs].nodeType === 1 && (" " + this[bs].className + " ").replace(aJ, " ").indexOf(bt) > -1) {
					return true
				}
			}
			return false
		},
		val: function(bt) {
			var e, br, bs = this[0];
			if (!arguments.length) {
				if (bs) {
					e = b.valHooks[bs.nodeName.toLowerCase()] || b.valHooks[bs.type];
					if (e && "get" in e && (br = e.get(bs, "value")) !== K) {
						return br
					}
					br = bs.value;
					return typeof br === "string" ? br.replace(aN, "") : br == null ? "": br
				}
				return K
			}
			var bu = b.isFunction(bt);
			return this.each(function(bw) {
				var bv = b(this),
				bx;
				if (this.nodeType !== 1) {
					return
				}
				if (bu) {
					bx = bt.call(this, bw, bv.val())
				} else {
					bx = bt
				}
				if (bx == null) {
					bx = ""
				} else {
					if (typeof bx === "number") {
						bx += ""
					} else {
						if (b.isArray(bx)) {
							bx = b.map(bx,
							function(by) {
								return by == null ? "": by + ""
							})
						}
					}
				}
				e = b.valHooks[this.nodeName.toLowerCase()] || b.valHooks[this.type];
				if (!e || !("set" in e) || e.set(this, bx, "value") === K) {
					this.value = bx
				}
			})
		}
	});
	b.extend({
		valHooks: {
			option: {
				get: function(e) {
					var br = e.attributes.value;
					return ! br || br.specified ? e.value: e.text
				}
			},
			select: {
				get: function(e) {
					var bw, bu = e.selectedIndex,
					bx = [],
					by = e.options,
					bt = e.type === "select-one";
					if (bu < 0) {
						return null
					}
					for (var br = bt ? bu: 0, bv = bt ? bu + 1 : by.length; br < bv; br++) {
						var bs = by[br];
						if (bs.selected && (b.support.optDisabled ? !bs.disabled: bs.getAttribute("disabled") === null) && (!bs.parentNode.disabled || !b.nodeName(bs.parentNode, "optgroup"))) {
							bw = b(bs).val();
							if (bt) {
								return bw
							}
							bx.push(bw)
						}
					}
					if (bt && !bx.length && by.length) {
						return b(by[bu]).val()
					}
					return bx
				},
				set: function(br, bs) {
					var e = b.makeArray(bs);
					b(br).find("option").each(function() {
						this.selected = b.inArray(b(this).val(), e) >= 0
					});
					if (!e.length) {
						br.selectedIndex = -1
					}
					return e
				}
			}
		},
		attrFn: {
			val: true,
			css: true,
			html: true,
			text: true,
			data: true,
			width: true,
			height: true,
			offset: true
		},
		attrFix: {
			tabindex: "tabIndex"
		},
		attr: function(bw, bt, bx, bv) {
			var br = bw.nodeType;
			if (!bw || br === 3 || br === 8 || br === 2) {
				return K
			}
			if (bv && bt in b.attrFn) {
				return b(bw)[bt](bx)
			}
			if (! ("getAttribute" in bw)) {
				return b.prop(bw, bt, bx)
			}
			var bs, e, bu = br !== 1 || !b.isXMLDoc(bw);
			if (bu) {
				bt = b.attrFix[bt] || bt;
				e = b.attrHooks[bt];
				if (!e) {
					if (aj.test(bt)) {
						e = aU
					} else {
						if (ba) {
							e = ba
						}
					}
				}
			}
			if (bx !== K) {
				if (bx === null) {
					b.removeAttr(bw, bt);
					return K
				} else {
					if (e && "set" in e && bu && (bs = e.set(bw, bx, bt)) !== K) {
						return bs
					} else {
						bw.setAttribute(bt, "" + bx);
						return bx
					}
				}
			} else {
				if (e && "get" in e && bu && (bs = e.get(bw, bt)) !== null) {
					return bs
				} else {
					bs = bw.getAttribute(bt);
					return bs === null ? K: bs
				}
			}
		},
		removeAttr: function(br, e) {
			var bs;
			if (br.nodeType === 1) {
				e = b.attrFix[e] || e;
				b.attr(br, e, "");
				br.removeAttribute(e);
				if (aj.test(e) && (bs = b.propFix[e] || e) in br) {
					br[bs] = false
				}
			}
		},
		attrHooks: {
			type: {
				set: function(e, br) {
					if (g.test(e.nodeName) && e.parentNode) {
						b.error("type property can't be changed")
					} else {
						if (!b.support.radioValue && br === "radio" && b.nodeName(e, "input")) {
							var bs = e.value;
							e.setAttribute("type", br);
							if (bs) {
								e.value = bs
							}
							return br
						}
					}
				}
			},
			value: {
				get: function(br, e) {
					if (ba && b.nodeName(br, "button")) {
						return ba.get(br, e)
					}
					return e in br ? br.value: null
				},
				set: function(br, bs, e) {
					if (ba && b.nodeName(br, "button")) {
						return ba.set(br, bs, e)
					}
					br.value = bs
				}
			}
		},
		propFix: {
			tabindex: "tabIndex",
			readonly: "readOnly",
			"for": "htmlFor",
			"class": "className",
			maxlength: "maxLength",
			cellspacing: "cellSpacing",
			cellpadding: "cellPadding",
			rowspan: "rowSpan",
			colspan: "colSpan",
			usemap: "useMap",
			frameborder: "frameBorder",
			contenteditable: "contentEditable"
		},
		prop: function(bv, bt, bw) {
			var br = bv.nodeType;
			if (!bv || br === 3 || br === 8 || br === 2) {
				return K
			}
			var bs, e, bu = br !== 1 || !b.isXMLDoc(bv);
			if (bu) {
				bt = b.propFix[bt] || bt;
				e = b.propHooks[bt]
			}
			if (bw !== K) {
				if (e && "set" in e && (bs = e.set(bv, bw, bt)) !== K) {
					return bs
				} else {
					return (bv[bt] = bw)
				}
			} else {
				if (e && "get" in e && (bs = e.get(bv, bt)) !== null) {
					return bs
				} else {
					return bv[bt]
				}
			}
		},
		propHooks: {
			tabIndex: {
				get: function(br) {
					var e = br.getAttributeNode("tabindex");
					return e && e.specified ? parseInt(e.value, 10) : D.test(br.nodeName) || l.test(br.nodeName) && br.href ? 0 : K
				}
			}
		}
	});
	b.attrHooks.tabIndex = b.propHooks.tabIndex;
	aU = {
		get: function(br, e) {
			var bs;
			return b.prop(br, e) === true || (bs = br.getAttributeNode(e)) && bs.nodeValue !== false ? e.toLowerCase() : K
		},
		set: function(br, bt, e) {
			var bs;
			if (bt === false) {
				b.removeAttr(br, e)
			} else {
				bs = b.propFix[e] || e;
				if (bs in br) {
					br[bs] = true
				}
				br.setAttribute(e, e.toLowerCase())
			}
			return e
		}
	};
	if (!b.support.getSetAttribute) {
		ba = b.valHooks.button = {
			get: function(bs, br) {
				var e;
				e = bs.getAttributeNode(br);
				return e && e.nodeValue !== "" ? e.nodeValue: K
			},
			set: function(bs, bt, br) {
				var e = bs.getAttributeNode(br);
				if (!e) {
					e = ap.createAttribute(br);
					bs.setAttributeNode(e)
				}
				return (e.nodeValue = bt + "")
			}
		};
		b.each(["width", "height"],
		function(br, e) {
			b.attrHooks[e] = b.extend(b.attrHooks[e], {
				set: function(bs, bt) {
					if (bt === "") {
						bs.setAttribute(e, "auto");
						return bt
					}
				}
			})
		})
	}
	if (!b.support.hrefNormalized) {
		b.each(["href", "src", "width", "height"],
		function(br, e) {
			b.attrHooks[e] = b.extend(b.attrHooks[e], {
				get: function(bt) {
					var bs = bt.getAttribute(e, 2);
					return bs === null ? K: bs
				}
			})
		})
	}
	if (!b.support.style) {
		b.attrHooks.style = {
			get: function(e) {
				return e.style.cssText.toLowerCase() || K
			},
			set: function(e, br) {
				return (e.style.cssText = "" + br)
			}
		}
	}
	if (!b.support.optSelected) {
		b.propHooks.selected = b.extend(b.propHooks.selected, {
			get: function(br) {
				var e = br.parentNode;
				if (e) {
					e.selectedIndex;
					if (e.parentNode) {
						e.parentNode.selectedIndex
					}
				}
				return null
			}
		})
	}
	if (!b.support.checkOn) {
		b.each(["radio", "checkbox"],
		function() {
			b.valHooks[this] = {
				get: function(e) {
					return e.getAttribute("value") === null ? "on": e.value
				}
			}
		})
	}
	b.each(["radio", "checkbox"],
	function() {
		b.valHooks[this] = b.extend(b.valHooks[this], {
			set: function(e, br) {
				if (b.isArray(br)) {
					return (e.checked = b.inArray(b(e).val(), br) >= 0)
				}
			}
		})
	});
	var aW = /\.(.*)$/,
	a9 = /^(?:textarea|input|select)$/i,
	N = /\./g,
	be = / /g,
	aB = /[^\w\s.|`]/g,
	G = function(e) {
		return e.replace(aB, "\\$&")
	};
	b.event = {
		add: function(bt, bx, bC, bv) {
			if (bt.nodeType === 3 || bt.nodeType === 8) {
				return
			}
			if (bC === false) {
				bC = bg
			} else {
				if (!bC) {
					return
				}
			}
			var br, bB;
			if (bC.handler) {
				br = bC;
				bC = br.handler
			}
			if (!bC.guid) {
				bC.guid = b.guid++
			}
			var by = b._data(bt);
			if (!by) {
				return
			}
			var bD = by.events,
			bw = by.handle;
			if (!bD) {
				by.events = bD = {}
			}
			if (!bw) {
				by.handle = bw = function(bE) {
					return typeof b !== "undefined" && (!bE || b.event.triggered !== bE.type) ? b.event.handle.apply(bw.elem, arguments) : K
				}
			}
			bw.elem = bt;
			bx = bx.split(" ");
			var bA, bu = 0,
			e;
			while ((bA = bx[bu++])) {
				bB = br ? b.extend({},
				br) : {
					handler: bC,
					data: bv
				};
				if (bA.indexOf(".") > -1) {
					e = bA.split(".");
					bA = e.shift();
					bB.namespace = e.slice(0).sort().join(".")
				} else {
					e = [];
					bB.namespace = ""
				}
				bB.type = bA;
				if (!bB.guid) {
					bB.guid = bC.guid
				}
				var bs = bD[bA],
				bz = b.event.special[bA] || {};
				if (!bs) {
					bs = bD[bA] = [];
					if (!bz.setup || bz.setup.call(bt, bv, e, bw) === false) {
						if (bt.addEventListener) {
							bt.addEventListener(bA, bw, false)
						} else {
							if (bt.attachEvent) {
								bt.attachEvent("on" + bA, bw)
							}
						}
					}
				}
				if (bz.add) {
					bz.add.call(bt, bB);
					if (!bB.handler.guid) {
						bB.handler.guid = bC.guid
					}
				}
				bs.push(bB);
				b.event.global[bA] = true
			}
			bt = null
		},
		global: {},
		remove: function(bF, bA, bs, bw) {
			if (bF.nodeType === 3 || bF.nodeType === 8) {
				return
			}
			if (bs === false) {
				bs = bg
			}
			var bI, bv, bx, bC, bD = 0,
			bt, by, bB, bu, bz, e, bH, bE = b.hasData(bF) && b._data(bF),
			br = bE && bE.events;
			if (!bE || !br) {
				return
			}
			if (bA && bA.type) {
				bs = bA.handler;
				bA = bA.type
			}
			if (!bA || typeof bA === "string" && bA.charAt(0) === ".") {
				bA = bA || "";
				for (bv in br) {
					b.event.remove(bF, bv + bA)
				}
				return
			}
			bA = bA.split(" ");
			while ((bv = bA[bD++])) {
				bH = bv;
				e = null;
				bt = bv.indexOf(".") < 0;
				by = [];
				if (!bt) {
					by = bv.split(".");
					bv = by.shift();
					bB = new RegExp("(^|\\.)" + b.map(by.slice(0).sort(), G).join("\\.(?:.*\\.)?") + "(\\.|$)")
				}
				bz = br[bv];
				if (!bz) {
					continue
				}
				if (!bs) {
					for (bC = 0; bC < bz.length; bC++) {
						e = bz[bC];
						if (bt || bB.test(e.namespace)) {
							b.event.remove(bF, bH, e.handler, bC);
							bz.splice(bC--, 1)
						}
					}
					continue
				}
				bu = b.event.special[bv] || {};
				for (bC = bw || 0; bC < bz.length; bC++) {
					e = bz[bC];
					if (bs.guid === e.guid) {
						if (bt || bB.test(e.namespace)) {
							if (bw == null) {
								bz.splice(bC--, 1)
							}
							if (bu.remove) {
								bu.remove.call(bF, e)
							}
						}
						if (bw != null) {
							break
						}
					}
				}
				if (bz.length === 0 || bw != null && bz.length === 1) {
					if (!bu.teardown || bu.teardown.call(bF, by) === false) {
						b.removeEvent(bF, bv, bE.handle)
					}
					bI = null;
					delete br[bv]
				}
			}
			if (b.isEmptyObject(br)) {
				var bG = bE.handle;
				if (bG) {
					bG.elem = null
				}
				delete bE.events;
				delete bE.handle;
				if (b.isEmptyObject(bE)) {
					b.removeData(bF, K, true)
				}
			}
		},
		customEvent: {
			getData: true,
			setData: true,
			changeData: true
		},
		trigger: function(e, bx, bv, bC) {
			var bA = e.type || e,
			bs = [],
			br;
			if (bA.indexOf("!") >= 0) {
				bA = bA.slice(0, -1);
				br = true
			}
			if (bA.indexOf(".") >= 0) {
				bs = bA.split(".");
				bA = bs.shift();
				bs.sort()
			}
			if ((!bv || b.event.customEvent[bA]) && !b.event.global[bA]) {
				return
			}
			e = typeof e === "object" ? e[b.expando] ? e: new b.Event(bA, e) : new b.Event(bA);
			e.type = bA;
			e.exclusive = br;
			e.namespace = bs.join(".");
			e.namespace_re = new RegExp("(^|\\.)" + bs.join("\\.(?:.*\\.)?") + "(\\.|$)");
			if (bC || !bv) {
				e.preventDefault();
				e.stopPropagation()
			}
			if (!bv) {
				b.each(b.cache,
				function() {
					var bE = b.expando,
					bD = this[bE];
					if (bD && bD.events && bD.events[bA]) {
						b.event.trigger(e, bx, bD.handle.elem)
					}
				});
				return
			}
			if (bv.nodeType === 3 || bv.nodeType === 8) {
				return
			}
			e.result = K;
			e.target = bv;
			bx = bx != null ? b.makeArray(bx) : [];
			bx.unshift(e);
			var bB = bv,
			bt = bA.indexOf(":") < 0 ? "on" + bA: "";
			do {
				var by = b._data(bB, "handle");
				e.currentTarget = bB;
				if (by) {
					by.apply(bB, bx)
				}
				if (bt && b.acceptData(bB) && bB[bt] && bB[bt].apply(bB, bx) === false) {
					e.result = false;
					e.preventDefault()
				}
				bB = bB.parentNode || bB.ownerDocument || bB === e.target.ownerDocument && a7
			} while ( bB && ! e . isPropagationStopped ());
			if (!e.isDefaultPrevented()) {
				var bu, bz = b.event.special[bA] || {};
				if ((!bz._default || bz._default.call(bv.ownerDocument, e) === false) && !(bA === "click" && b.nodeName(bv, "a")) && b.acceptData(bv)) {
					try {
						if (bt && bv[bA]) {
							bu = bv[bt];
							if (bu) {
								bv[bt] = null
							}
							b.event.triggered = bA;
							bv[bA]()
						}
					} catch(bw) {}
					if (bu) {
						bv[bt] = bu
					}
					b.event.triggered = K
				}
			}
			return e.result
		},
		handle: function(bx) {
			bx = b.event.fix(bx || a7.event);
			var br = ((b._data(this, "events") || {})[bx.type] || []).slice(0),
			bw = !bx.exclusive && !bx.namespace,
			bu = Array.prototype.slice.call(arguments, 0);
			bu[0] = bx;
			bx.currentTarget = this;
			for (var bt = 0,
			e = br.length; bt < e; bt++) {
				var bv = br[bt];
				if (bw || bx.namespace_re.test(bv.namespace)) {
					bx.handler = bv.handler;
					bx.data = bv.data;
					bx.handleObj = bv;
					var bs = bv.handler.apply(this, bu);
					if (bs !== K) {
						bx.result = bs;
						if (bs === false) {
							bx.preventDefault();
							bx.stopPropagation()
						}
					}
					if (bx.isImmediatePropagationStopped()) {
						break
					}
				}
			}
			return bx.result
		},
		props: "altKey attrChange attrName bubbles button cancelable charCode clientX clientY ctrlKey currentTarget data detail eventPhase fromElement handler keyCode layerX layerY metaKey newValue offsetX offsetY pageX pageY prevValue relatedNode relatedTarget screenX screenY shiftKey srcElement target toElement view wheelDelta which".split(" "),
		fix: function(bu) {
			if (bu[b.expando]) {
				return bu
			}
			var br = bu;
			bu = b.Event(br);
			for (var bs = this.props.length,
			bw; bs;) {
				bw = this.props[--bs];
				bu[bw] = br[bw]
			}
			if (!bu.target) {
				bu.target = bu.srcElement || ap
			}
			if (bu.target.nodeType === 3) {
				bu.target = bu.target.parentNode
			}
			if (!bu.relatedTarget && bu.fromElement) {
				bu.relatedTarget = bu.fromElement === bu.target ? bu.toElement: bu.fromElement
			}
			if (bu.pageX == null && bu.clientX != null) {
				var bt = bu.target.ownerDocument || ap,
				bv = bt.documentElement,
				e = bt.body;
				bu.pageX = bu.clientX + (bv && bv.scrollLeft || e && e.scrollLeft || 0) - (bv && bv.clientLeft || e && e.clientLeft || 0);
				bu.pageY = bu.clientY + (bv && bv.scrollTop || e && e.scrollTop || 0) - (bv && bv.clientTop || e && e.clientTop || 0)
			}
			if (bu.which == null && (bu.charCode != null || bu.keyCode != null)) {
				bu.which = bu.charCode != null ? bu.charCode: bu.keyCode
			}
			if (!bu.metaKey && bu.ctrlKey) {
				bu.metaKey = bu.ctrlKey
			}
			if (!bu.which && bu.button !== K) {
				bu.which = (bu.button & 1 ? 1 : (bu.button & 2 ? 3 : (bu.button & 4 ? 2 : 0)))
			}
			return bu
		},
		guid: 100000000,
		proxy: b.proxy,
		special: {
			ready: {
				setup: b.bindReady,
				teardown: b.noop
			},
			live: {
				add: function(e) {
					b.event.add(this, p(e.origType, e.selector), b.extend({},
					e, {
						handler: ag,
						guid: e.handler.guid
					}))
				},
				remove: function(e) {
					b.event.remove(this, p(e.origType, e.selector), e)
				}
			},
			beforeunload: {
				setup: function(bs, br, e) {
					if (b.isWindow(this)) {
						this.onbeforeunload = e
					}
				},
				teardown: function(br, e) {
					if (this.onbeforeunload === e) {
						this.onbeforeunload = null
					}
				}
			}
		}
	};
	b.removeEvent = ap.removeEventListener ?
	function(br, e, bs) {
		if (br.removeEventListener) {
			br.removeEventListener(e, bs, false)
		}
	}: function(br, e, bs) {
		if (br.detachEvent) {
			br.detachEvent("on" + e, bs)
		}
	};
	b.Event = function(br, e) {
		if (!this.preventDefault) {
			return new b.Event(br, e)
		}
		if (br && br.type) {
			this.originalEvent = br;
			this.type = br.type;
			this.isDefaultPrevented = (br.defaultPrevented || br.returnValue === false || br.getPreventDefault && br.getPreventDefault()) ? i: bg
		} else {
			this.type = br
		}
		if (e) {
			b.extend(this, e)
		}
		this.timeStamp = b.now();
		this[b.expando] = true
	};
	function bg() {
		return false
	}
	function i() {
		return true
	}
	b.Event.prototype = {
		preventDefault: function() {
			this.isDefaultPrevented = i;
			var br = this.originalEvent;
			if (!br) {
				return
			}
			if (br.preventDefault) {
				br.preventDefault()
			} else {
				br.returnValue = false
			}
		},
		stopPropagation: function() {
			this.isPropagationStopped = i;
			var br = this.originalEvent;
			if (!br) {
				return
			}
			if (br.stopPropagation) {
				br.stopPropagation()
			}
			br.cancelBubble = true
		},
		stopImmediatePropagation: function() {
			this.isImmediatePropagationStopped = i;
			this.stopPropagation()
		},
		isDefaultPrevented: bg,
		isPropagationStopped: bg,
		isImmediatePropagationStopped: bg
	};
	var aa = function(bs) {
		var bt = bs.relatedTarget,
		e = false,
		br = bs.type;
		bs.type = bs.data;
		if (bt !== this) {
			if (bt) {
				e = b.contains(this, bt)
			}
			if (!e) {
				b.event.handle.apply(this, arguments);
				bs.type = br
			}
		}
	},
	aR = function(e) {
		e.type = e.data;
		b.event.handle.apply(this, arguments)
	};
	b.each({
		mouseenter: "mouseover",
		mouseleave: "mouseout"
	},
	function(br, e) {
		b.event.special[br] = {
			setup: function(bs) {
				b.event.add(this, e, bs && bs.selector ? aR: aa, br)
			},
			teardown: function(bs) {
				b.event.remove(this, e, bs && bs.selector ? aR: aa)
			}
		}
	});
	if (!b.support.submitBubbles) {
		b.event.special.submit = {
			setup: function(br, e) {
				if (!b.nodeName(this, "form")) {
					b.event.add(this, "click.specialSubmit",
					function(bu) {
						var bt = bu.target,
						bs = b.nodeName(bt, "input") || b.nodeName(bt, "button") ? bt.type: "";
						if ((bs === "submit" || bs === "image") && b(bt).closest("form").length) {
							aT("submit", this, arguments)
						}
					});
					b.event.add(this, "keypress.specialSubmit",
					function(bu) {
						var bt = bu.target,
						bs = b.nodeName(bt, "input") || b.nodeName(bt, "button") ? bt.type: "";
						if ((bs === "text" || bs === "password") && b(bt).closest("form").length && bu.keyCode === 13) {
							aT("submit", this, arguments)
						}
					})
				} else {
					return false
				}
			},
			teardown: function(e) {
				b.event.remove(this, ".specialSubmit")
			}
		}
	}
	if (!b.support.changeBubbles) {
		var bj, k = function(br) {
			var e = b.nodeName(br, "input") ? br.type: "",
			bs = br.value;
			if (e === "radio" || e === "checkbox") {
				bs = br.checked
			} else {
				if (e === "select-multiple") {
					bs = br.selectedIndex > -1 ? b.map(br.options,
					function(bt) {
						return bt.selected
					}).join("-") : ""
				} else {
					if (b.nodeName(br, "select")) {
						bs = br.selectedIndex
					}
				}
			}
			return bs
		},
		Y = function Y(bt) {
			var br = bt.target,
			bs, bu;
			if (!a9.test(br.nodeName) || br.readOnly) {
				return
			}
			bs = b._data(br, "_change_data");
			bu = k(br);
			if (bt.type !== "focusout" || br.type !== "radio") {
				b._data(br, "_change_data", bu)
			}
			if (bs === K || bu === bs) {
				return
			}
			if (bs != null || bu) {
				bt.type = "change";
				bt.liveFired = K;
				b.event.trigger(bt, arguments[1], br)
			}
		};
		b.event.special.change = {
			filters: {
				focusout: Y,
				beforedeactivate: Y,
				click: function(bt) {
					var bs = bt.target,
					br = b.nodeName(bs, "input") ? bs.type: "";
					if (br === "radio" || br === "checkbox" || b.nodeName(bs, "select")) {
						Y.call(this, bt)
					}
				},
				keydown: function(bt) {
					var bs = bt.target,
					br = b.nodeName(bs, "input") ? bs.type: "";
					if ((bt.keyCode === 13 && !b.nodeName(bs, "textarea")) || (bt.keyCode === 32 && (br === "checkbox" || br === "radio")) || br === "select-multiple") {
						Y.call(this, bt)
					}
				},
				beforeactivate: function(bs) {
					var br = bs.target;
					b._data(br, "_change_data", k(br))
				}
			},
			setup: function(bs, br) {
				if (this.type === "file") {
					return false
				}
				for (var e in bj) {
					b.event.add(this, e + ".specialChange", bj[e])
				}
				return a9.test(this.nodeName)
			},
			teardown: function(e) {
				b.event.remove(this, ".specialChange");
				return a9.test(this.nodeName)
			}
		};
		bj = b.event.special.change.filters;
		bj.focus = bj.beforeactivate
	}
	function aT(br, bt, e) {
		var bs = b.extend({},
		e[0]);
		bs.type = br;
		bs.originalEvent = {};
		bs.liveFired = K;
		b.event.handle.call(bt, bs);
		if (bs.isDefaultPrevented()) {
			e[0].preventDefault()
		}
	}
	if (!b.support.focusinBubbles) {
		b.each({
			focus: "focusin",
			blur: "focusout"
		},
		function(bt, e) {
			var br = 0;
			b.event.special[e] = {
				setup: function() {
					if (br++===0) {
						ap.addEventListener(bt, bs, true)
					}
				},
				teardown: function() {
					if (--br === 0) {
						ap.removeEventListener(bt, bs, true)
					}
				}
			};
			function bs(bu) {
				var bv = b.event.fix(bu);
				bv.type = e;
				bv.originalEvent = {};
				b.event.trigger(bv, null, bv.target);
				if (bv.isDefaultPrevented()) {
					bu.preventDefault()
				}
			}
		})
	}
	b.each(["bind", "one"],
	function(br, e) {
		b.fn[e] = function(bx, by, bw) {
			var bv;
			if (typeof bx === "object") {
				for (var bu in bx) {
					this[e](bu, by, bx[bu], bw)
				}
				return this
			}
			if (arguments.length === 2 || by === false) {
				bw = by;
				by = K
			}
			if (e === "one") {
				bv = function(bz) {
					b(this).unbind(bz, bv);
					return bw.apply(this, arguments)
				};
				bv.guid = bw.guid || b.guid++
			} else {
				bv = bw
			}
			if (bx === "unload" && e !== "one") {
				this.one(bx, by, bw)
			} else {
				for (var bt = 0,
				bs = this.length; bt < bs; bt++) {
					b.event.add(this[bt], bx, bv, by)
				}
			}
			return this
		}
	});
	b.fn.extend({
		unbind: function(bu, bt) {
			if (typeof bu === "object" && !bu.preventDefault) {
				for (var bs in bu) {
					this.unbind(bs, bu[bs])
				}
			} else {
				for (var br = 0,
				e = this.length; br < e; br++) {
					b.event.remove(this[br], bu, bt)
				}
			}
			return this
		},
		delegate: function(e, br, bt, bs) {
			return this.live(br, bt, bs, e)
		},
		undelegate: function(e, br, bs) {
			if (arguments.length === 0) {
				return this.unbind("live")
			} else {
				return this.die(br, null, bs, e)
			}
		},
		trigger: function(e, br) {
			return this.each(function() {
				b.event.trigger(e, br, this)
			})
		},
		triggerHandler: function(e, br) {
			if (this[0]) {
				return b.event.trigger(e, br, this[0], true)
			}
		},
		toggle: function(bt) {
			var br = arguments,
			e = bt.guid || b.guid++,
			bs = 0,
			bu = function(bv) {
				var bw = (b.data(this, "lastToggle" + bt.guid) || 0) % bs;
				b.data(this, "lastToggle" + bt.guid, bw + 1);
				bv.preventDefault();
				return br[bw].apply(this, arguments) || false
			};
			bu.guid = e;
			while (bs < br.length) {
				br[bs++].guid = e
			}
			return this.click(bu)
		},
		hover: function(e, br) {
			return this.mouseenter(e).mouseleave(br || e)
		}
	});
	var aP = {
		focus: "focusin",
		blur: "focusout",
		mouseenter: "mouseover",
		mouseleave: "mouseout"
	};
	b.each(["live", "die"],
	function(br, e) {
		b.fn[e] = function(bB, by, bD, bu) {
			var bC, bz = 0,
			bA, bt, bF, bw = bu || this.selector,
			bs = bu ? this: b(this.context);
			if (typeof bB === "object" && !bB.preventDefault) {
				for (var bE in bB) {
					bs[e](bE, by, bB[bE], bw)
				}
				return this
			}
			if (e === "die" && !bB && bu && bu.charAt(0) === ".") {
				bs.unbind(bu);
				return this
			}
			if (by === false || b.isFunction(by)) {
				bD = by || bg;
				by = K
			}
			bB = (bB || "").split(" ");
			while ((bC = bB[bz++]) != null) {
				bA = aW.exec(bC);
				bt = "";
				if (bA) {
					bt = bA[0];
					bC = bC.replace(aW, "")
				}
				if (bC === "hover") {
					bB.push("mouseenter" + bt, "mouseleave" + bt);
					continue
				}
				bF = bC;
				if (aP[bC]) {
					bB.push(aP[bC] + bt);
					bC = bC + bt
				} else {
					bC = (aP[bC] || bC) + bt
				}
				if (e === "live") {
					for (var bx = 0,
					bv = bs.length; bx < bv; bx++) {
						b.event.add(bs[bx], "live." + p(bC, bw), {
							data: by,
							selector: bw,
							handler: bD,
							origType: bC,
							origHandler: bD,
							preType: bF
						})
					}
				} else {
					bs.unbind("live." + p(bC, bw), bD)
				}
			}
			return this
		}
	});
	function ag(bB) {
		var by, bt, bH, bv, e, bD, bA, bC, bz, bG, bx, bw, bF, bE = [],
		bu = [],
		br = b._data(this, "events");
		if (bB.liveFired === this || !br || !br.live || bB.target.disabled || bB.button && bB.type === "click") {
			return
		}
		if (bB.namespace) {
			bw = new RegExp("(^|\\.)" + bB.namespace.split(".").join("\\.(?:.*\\.)?") + "(\\.|$)")
		}
		bB.liveFired = this;
		var bs = br.live.slice(0);
		for (bA = 0; bA < bs.length; bA++) {
			e = bs[bA];
			if (e.origType.replace(aW, "") === bB.type) {
				bu.push(e.selector)
			} else {
				bs.splice(bA--, 1)
			}
		}
		bv = b(bB.target).closest(bu, bB.currentTarget);
		for (bC = 0, bz = bv.length; bC < bz; bC++) {
			bx = bv[bC];
			for (bA = 0; bA < bs.length; bA++) {
				e = bs[bA];
				if (bx.selector === e.selector && (!bw || bw.test(e.namespace)) && !bx.elem.disabled) {
					bD = bx.elem;
					bH = null;
					if (e.preType === "mouseenter" || e.preType === "mouseleave") {
						bB.type = e.preType;
						bH = b(bB.relatedTarget).closest(e.selector)[0];
						if (bH && b.contains(bD, bH)) {
							bH = bD
						}
					}
					if (!bH || bH !== bD) {
						bE.push({
							elem: bD,
							handleObj: e,
							level: bx.level
						})
					}
				}
			}
		}
		for (bC = 0, bz = bE.length; bC < bz; bC++) {
			bv = bE[bC];
			if (bt && bv.level > bt) {
				break
			}
			bB.currentTarget = bv.elem;
			bB.data = bv.handleObj.data;
			bB.handleObj = bv.handleObj;
			bF = bv.handleObj.origHandler.apply(bv.elem, arguments);
			if (bF === false || bB.isPropagationStopped()) {
				bt = bv.level;
				if (bF === false) {
					by = false
				}
				if (bB.isImmediatePropagationStopped()) {
					break
				}
			}
		}
		return by
	}
	function p(br, e) {
		return (br && br !== "*" ? br + ".": "") + e.replace(N, "`").replace(be, "&")
	}
	b.each(("blur focus focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup error").split(" "),
	function(br, e) {
		b.fn[e] = function(bt, bs) {
			if (bs == null) {
				bs = bt;
				bt = null
			}
			return arguments.length > 0 ? this.bind(e, bt, bs) : this.trigger(e)
		};
		if (b.attrFn) {
			b.attrFn[e] = true
		}
	});
	/*!
 * Sizzle CSS Selector Engine
 *  Copyright 2011, The Dojo Foundation
 *  Released under the MIT, BSD, and GPL Licenses.
 *  More information: http://sizzlejs.com/
 */
	(function() {
		var bB = /((?:\((?:\([^()]+\)|[^()]+)+\)|\[(?:\[[^\[\]]*\]|['"][^'"]*['"]|[^\[\]'"]+)+\]|\\.|[^ >+~,(\[\\]+)+|[>+~])(\s*,\s*)?((?:.|\r|\n)*)/g,
		bC = 0,
		bF = Object.prototype.toString,
		bw = false,
		bv = true,
		bD = /\\/g,
		bJ = /\W/; [0, 0].sort(function() {
			bv = false;
			return 0
		});
		var bt = function(bO, e, bR, bS) {
			bR = bR || [];
			e = e || ap;
			var bU = e;
			if (e.nodeType !== 1 && e.nodeType !== 9) {
				return []
			}
			if (!bO || typeof bO !== "string") {
				return bR
			}
			var bL, bW, bZ, bK, bV, bY, bX, bQ, bN = true,
			bM = bt.isXML(e),
			bP = [],
			bT = bO;
			do {
				bB.exec("");
				bL = bB.exec(bT);
				if (bL) {
					bT = bL[3];
					bP.push(bL[1]);
					if (bL[2]) {
						bK = bL[3];
						break
					}
				}
			} while ( bL );
			if (bP.length > 1 && bx.exec(bO)) {
				if (bP.length === 2 && by.relative[bP[0]]) {
					bW = bG(bP[0] + bP[1], e)
				} else {
					bW = by.relative[bP[0]] ? [e] : bt(bP.shift(), e);
					while (bP.length) {
						bO = bP.shift();
						if (by.relative[bO]) {
							bO += bP.shift()
						}
						bW = bG(bO, bW)
					}
				}
			} else {
				if (!bS && bP.length > 1 && e.nodeType === 9 && !bM && by.match.ID.test(bP[0]) && !by.match.ID.test(bP[bP.length - 1])) {
					bV = bt.find(bP.shift(), e, bM);
					e = bV.expr ? bt.filter(bV.expr, bV.set)[0] : bV.set[0]
				}
				if (e) {
					bV = bS ? {
						expr: bP.pop(),
						set: bz(bS)
					}: bt.find(bP.pop(), bP.length === 1 && (bP[0] === "~" || bP[0] === "+") && e.parentNode ? e.parentNode: e, bM);
					bW = bV.expr ? bt.filter(bV.expr, bV.set) : bV.set;
					if (bP.length > 0) {
						bZ = bz(bW)
					} else {
						bN = false
					}
					while (bP.length) {
						bY = bP.pop();
						bX = bY;
						if (!by.relative[bY]) {
							bY = ""
						} else {
							bX = bP.pop()
						}
						if (bX == null) {
							bX = e
						}
						by.relative[bY](bZ, bX, bM)
					}
				} else {
					bZ = bP = []
				}
			}
			if (!bZ) {
				bZ = bW
			}
			if (!bZ) {
				bt.error(bY || bO)
			}
			if (bF.call(bZ) === "[object Array]") {
				if (!bN) {
					bR.push.apply(bR, bZ)
				} else {
					if (e && e.nodeType === 1) {
						for (bQ = 0; bZ[bQ] != null; bQ++) {
							if (bZ[bQ] && (bZ[bQ] === true || bZ[bQ].nodeType === 1 && bt.contains(e, bZ[bQ]))) {
								bR.push(bW[bQ])
							}
						}
					} else {
						for (bQ = 0; bZ[bQ] != null; bQ++) {
							if (bZ[bQ] && bZ[bQ].nodeType === 1) {
								bR.push(bW[bQ])
							}
						}
					}
				}
			} else {
				bz(bZ, bR)
			}
			if (bK) {
				bt(bK, bU, bR, bS);
				bt.uniqueSort(bR)
			}
			return bR
		};
		bt.uniqueSort = function(bK) {
			if (bE) {
				bw = bv;
				bK.sort(bE);
				if (bw) {
					for (var e = 1; e < bK.length; e++) {
						if (bK[e] === bK[e - 1]) {
							bK.splice(e--, 1)
						}
					}
				}
			}
			return bK
		};
		bt.matches = function(e, bK) {
			return bt(e, null, null, bK)
		};
		bt.matchesSelector = function(e, bK) {
			return bt(bK, null, null, [e]).length > 0
		};
		bt.find = function(bQ, e, bR) {
			var bP;
			if (!bQ) {
				return []
			}
			for (var bM = 0,
			bL = by.order.length; bM < bL; bM++) {
				var bN, bO = by.order[bM];
				if ((bN = by.leftMatch[bO].exec(bQ))) {
					var bK = bN[1];
					bN.splice(1, 1);
					if (bK.substr(bK.length - 1) !== "\\") {
						bN[1] = (bN[1] || "").replace(bD, "");
						bP = by.find[bO](bN, e, bR);
						if (bP != null) {
							bQ = bQ.replace(by.match[bO], "");
							break
						}
					}
				}
			}
			if (!bP) {
				bP = typeof e.getElementsByTagName !== "undefined" ? e.getElementsByTagName("*") : []
			}
			return {
				set: bP,
				expr: bQ
			}
		};
		bt.filter = function(bU, bT, bX, bN) {
			var bP, e, bL = bU,
			bZ = [],
			bR = bT,
			bQ = bT && bT[0] && bt.isXML(bT[0]);
			while (bU && bT.length) {
				for (var bS in by.filter) {
					if ((bP = by.leftMatch[bS].exec(bU)) != null && bP[2]) {
						var bY, bW, bK = by.filter[bS],
						bM = bP[1];
						e = false;
						bP.splice(1, 1);
						if (bM.substr(bM.length - 1) === "\\") {
							continue
						}
						if (bR === bZ) {
							bZ = []
						}
						if (by.preFilter[bS]) {
							bP = by.preFilter[bS](bP, bR, bX, bZ, bN, bQ);
							if (!bP) {
								e = bY = true
							} else {
								if (bP === true) {
									continue
								}
							}
						}
						if (bP) {
							for (var bO = 0; (bW = bR[bO]) != null; bO++) {
								if (bW) {
									bY = bK(bW, bP, bO, bR);
									var bV = bN ^ !!bY;
									if (bX && bY != null) {
										if (bV) {
											e = true
										} else {
											bR[bO] = false
										}
									} else {
										if (bV) {
											bZ.push(bW);
											e = true
										}
									}
								}
							}
						}
						if (bY !== K) {
							if (!bX) {
								bR = bZ
							}
							bU = bU.replace(by.match[bS], "");
							if (!e) {
								return []
							}
							break
						}
					}
				}
				if (bU === bL) {
					if (e == null) {
						bt.error(bU)
					} else {
						break
					}
				}
				bL = bU
			}
			return bR
		};
		bt.error = function(e) {
			throw "Syntax error, unrecognized expression: " + e
		};
		var by = bt.selectors = {
			order: ["ID", "NAME", "TAG"],
			match: {
				ID: /#((?:[\w\u00c0-\uFFFF\-]|\\.)+)/,
				CLASS: /\.((?:[\w\u00c0-\uFFFF\-]|\\.)+)/,
				NAME: /\[name=['"]*((?:[\w\u00c0-\uFFFF\-]|\\.)+)['"]*\]/,
				ATTR: /\[\s*((?:[\w\u00c0-\uFFFF\-]|\\.)+)\s*(?:(\S?=)\s*(?:(['"])(.*?)\3|(#?(?:[\w\u00c0-\uFFFF\-]|\\.)*)|)|)\s*\]/,
				TAG: /^((?:[\w\u00c0-\uFFFF\*\-]|\\.)+)/,
				CHILD: /:(only|nth|last|first)-child(?:\(\s*(even|odd|(?:[+\-]?\d+|(?:[+\-]?\d*)?n\s*(?:[+\-]\s*\d+)?))\s*\))?/,
				POS: /:(nth|eq|gt|lt|first|last|even|odd)(?:\((\d*)\))?(?=[^\-]|$)/,
				PSEUDO: /:((?:[\w\u00c0-\uFFFF\-]|\\.)+)(?:\((['"]?)((?:\([^\)]+\)|[^\(\)]*)+)\2\))?/
			},
			leftMatch: {},
			attrMap: {
				"class": "className",
				"for": "htmlFor"
			},
			attrHandle: {
				href: function(e) {
					return e.getAttribute("href")
				},
				type: function(e) {
					return e.getAttribute("type")
				}
			},
			relative: {
				"+": function(bP, bK) {
					var bM = typeof bK === "string",
					bO = bM && !bJ.test(bK),
					bQ = bM && !bO;
					if (bO) {
						bK = bK.toLowerCase()
					}
					for (var bL = 0,
					e = bP.length,
					bN; bL < e; bL++) {
						if ((bN = bP[bL])) {
							while ((bN = bN.previousSibling) && bN.nodeType !== 1) {}
							bP[bL] = bQ || bN && bN.nodeName.toLowerCase() === bK ? bN || false: bN === bK
						}
					}
					if (bQ) {
						bt.filter(bK, bP, true)
					}
				},
				">": function(bP, bK) {
					var bO, bN = typeof bK === "string",
					bL = 0,
					e = bP.length;
					if (bN && !bJ.test(bK)) {
						bK = bK.toLowerCase();
						for (; bL < e; bL++) {
							bO = bP[bL];
							if (bO) {
								var bM = bO.parentNode;
								bP[bL] = bM.nodeName.toLowerCase() === bK ? bM: false
							}
						}
					} else {
						for (; bL < e; bL++) {
							bO = bP[bL];
							if (bO) {
								bP[bL] = bN ? bO.parentNode: bO.parentNode === bK
							}
						}
						if (bN) {
							bt.filter(bK, bP, true)
						}
					}
				},
				"": function(bM, bK, bO) {
					var bN, bL = bC++,
					e = bH;
					if (typeof bK === "string" && !bJ.test(bK)) {
						bK = bK.toLowerCase();
						bN = bK;
						e = br
					}
					e("parentNode", bK, bL, bM, bN, bO)
				},
				"~": function(bM, bK, bO) {
					var bN, bL = bC++,
					e = bH;
					if (typeof bK === "string" && !bJ.test(bK)) {
						bK = bK.toLowerCase();
						bN = bK;
						e = br
					}
					e("previousSibling", bK, bL, bM, bN, bO)
				}
			},
			find: {
				ID: function(bK, bL, bM) {
					if (typeof bL.getElementById !== "undefined" && !bM) {
						var e = bL.getElementById(bK[1]);
						return e && e.parentNode ? [e] : []
					}
				},
				NAME: function(bL, bO) {
					if (typeof bO.getElementsByName !== "undefined") {
						var bK = [],
						bN = bO.getElementsByName(bL[1]);
						for (var bM = 0,
						e = bN.length; bM < e; bM++) {
							if (bN[bM].getAttribute("name") === bL[1]) {
								bK.push(bN[bM])
							}
						}
						return bK.length === 0 ? null: bK
					}
				},
				TAG: function(e, bK) {
					if (typeof bK.getElementsByTagName !== "undefined") {
						return bK.getElementsByTagName(e[1])
					}
				}
			},
			preFilter: {
				CLASS: function(bM, bK, bL, e, bP, bQ) {
					bM = " " + bM[1].replace(bD, "") + " ";
					if (bQ) {
						return bM
					}
					for (var bN = 0,
					bO; (bO = bK[bN]) != null; bN++) {
						if (bO) {
							if (bP ^ (bO.className && (" " + bO.className + " ").replace(/[\t\n\r]/g, " ").indexOf(bM) >= 0)) {
								if (!bL) {
									e.push(bO)
								}
							} else {
								if (bL) {
									bK[bN] = false
								}
							}
						}
					}
					return false
				},
				ID: function(e) {
					return e[1].replace(bD, "")
				},
				TAG: function(bK, e) {
					return bK[1].replace(bD, "").toLowerCase()
				},
				CHILD: function(e) {
					if (e[1] === "nth") {
						if (!e[2]) {
							bt.error(e[0])
						}
						e[2] = e[2].replace(/^\+|\s*/g, "");
						var bK = /(-?)(\d*)(?:n([+\-]?\d*))?/.exec(e[2] === "even" && "2n" || e[2] === "odd" && "2n+1" || !/\D/.test(e[2]) && "0n+" + e[2] || e[2]);
						e[2] = (bK[1] + (bK[2] || 1)) - 0;
						e[3] = bK[3] - 0
					} else {
						if (e[2]) {
							bt.error(e[0])
						}
					}
					e[0] = bC++;
					return e
				},
				ATTR: function(bN, bK, bL, e, bO, bP) {
					var bM = bN[1] = bN[1].replace(bD, "");
					if (!bP && by.attrMap[bM]) {
						bN[1] = by.attrMap[bM]
					}
					bN[4] = (bN[4] || bN[5] || "").replace(bD, "");
					if (bN[2] === "~=") {
						bN[4] = " " + bN[4] + " "
					}
					return bN
				},
				PSEUDO: function(bN, bK, bL, e, bO) {
					if (bN[1] === "not") {
						if ((bB.exec(bN[3]) || "").length > 1 || /^\w/.test(bN[3])) {
							bN[3] = bt(bN[3], null, null, bK)
						} else {
							var bM = bt.filter(bN[3], bK, bL, true ^ bO);
							if (!bL) {
								e.push.apply(e, bM)
							}
							return false
						}
					} else {
						if (by.match.POS.test(bN[0]) || by.match.CHILD.test(bN[0])) {
							return true
						}
					}
					return bN
				},
				POS: function(e) {
					e.unshift(true);
					return e
				}
			},
			filters: {
				enabled: function(e) {
					return e.disabled === false && e.type !== "hidden"
				},
				disabled: function(e) {
					return e.disabled === true
				},
				checked: function(e) {
					return e.checked === true
				},
				selected: function(e) {
					if (e.parentNode) {
						e.parentNode.selectedIndex
					}
					return e.selected === true
				},
				parent: function(e) {
					return !! e.firstChild
				},
				empty: function(e) {
					return ! e.firstChild
				},
				has: function(bL, bK, e) {
					return !! bt(e[3], bL).length
				},
				header: function(e) {
					return (/h\d/i).test(e.nodeName)
				},
				text: function(bL) {
					var e = bL.getAttribute("type"),
					bK = bL.type;
					return bL.nodeName.toLowerCase() === "input" && "text" === bK && (e === bK || e === null)
				},
				radio: function(e) {
					return e.nodeName.toLowerCase() === "input" && "radio" === e.type
				},
				checkbox: function(e) {
					return e.nodeName.toLowerCase() === "input" && "checkbox" === e.type
				},
				file: function(e) {
					return e.nodeName.toLowerCase() === "input" && "file" === e.type
				},
				password: function(e) {
					return e.nodeName.toLowerCase() === "input" && "password" === e.type
				},
				submit: function(bK) {
					var e = bK.nodeName.toLowerCase();
					return (e === "input" || e === "button") && "submit" === bK.type
				},
				image: function(e) {
					return e.nodeName.toLowerCase() === "input" && "image" === e.type
				},
				reset: function(bK) {
					var e = bK.nodeName.toLowerCase();
					return (e === "input" || e === "button") && "reset" === bK.type
				},
				button: function(bK) {
					var e = bK.nodeName.toLowerCase();
					return e === "input" && "button" === bK.type || e === "button"
				},
				input: function(e) {
					return (/input|select|textarea|button/i).test(e.nodeName)
				},
				focus: function(e) {
					return e === e.ownerDocument.activeElement
				}
			},
			setFilters: {
				first: function(bK, e) {
					return e === 0
				},
				last: function(bL, bK, e, bM) {
					return bK === bM.length - 1
				},
				even: function(bK, e) {
					return e % 2 === 0
				},
				odd: function(bK, e) {
					return e % 2 === 1
				},
				lt: function(bL, bK, e) {
					return bK < e[3] - 0
				},
				gt: function(bL, bK, e) {
					return bK > e[3] - 0
				},
				nth: function(bL, bK, e) {
					return e[3] - 0 === bK
				},
				eq: function(bL, bK, e) {
					return e[3] - 0 === bK
				}
			},
			filter: {
				PSEUDO: function(bL, bQ, bP, bR) {
					var e = bQ[1],
					bK = by.filters[e];
					if (bK) {
						return bK(bL, bP, bQ, bR)
					} else {
						if (e === "contains") {
							return (bL.textContent || bL.innerText || bt.getText([bL]) || "").indexOf(bQ[3]) >= 0
						} else {
							if (e === "not") {
								var bM = bQ[3];
								for (var bO = 0,
								bN = bM.length; bO < bN; bO++) {
									if (bM[bO] === bL) {
										return false
									}
								}
								return true
							} else {
								bt.error(e)
							}
						}
					}
				},
				CHILD: function(e, bM) {
					var bP = bM[1],
					bK = e;
					switch (bP) {
					case "only":
					case "first":
						while ((bK = bK.previousSibling)) {
							if (bK.nodeType === 1) {
								return false
							}
						}
						if (bP === "first") {
							return true
						}
						bK = e;
					case "last":
						while ((bK = bK.nextSibling)) {
							if (bK.nodeType === 1) {
								return false
							}
						}
						return true;
					case "nth":
						var bL = bM[2],
						bS = bM[3];
						if (bL === 1 && bS === 0) {
							return true
						}
						var bO = bM[0],
						bR = e.parentNode;
						if (bR && (bR.sizcache !== bO || !e.nodeIndex)) {
							var bN = 0;
							for (bK = bR.firstChild; bK; bK = bK.nextSibling) {
								if (bK.nodeType === 1) {
									bK.nodeIndex = ++bN
								}
							}
							bR.sizcache = bO
						}
						var bQ = e.nodeIndex - bS;
						if (bL === 0) {
							return bQ === 0
						} else {
							return (bQ % bL === 0 && bQ / bL >= 0)
						}
					}
				},
				ID: function(bK, e) {
					return bK.nodeType === 1 && bK.getAttribute("id") === e
				},
				TAG: function(bK, e) {
					return (e === "*" && bK.nodeType === 1) || bK.nodeName.toLowerCase() === e
				},
				CLASS: function(bK, e) {
					return (" " + (bK.className || bK.getAttribute("class")) + " ").indexOf(e) > -1
				},
				ATTR: function(bO, bM) {
					var bL = bM[1],
					e = by.attrHandle[bL] ? by.attrHandle[bL](bO) : bO[bL] != null ? bO[bL] : bO.getAttribute(bL),
					bP = e + "",
					bN = bM[2],
					bK = bM[4];
					return e == null ? bN === "!=": bN === "=" ? bP === bK: bN === "*=" ? bP.indexOf(bK) >= 0 : bN === "~=" ? (" " + bP + " ").indexOf(bK) >= 0 : !bK ? bP && e !== false: bN === "!=" ? bP !== bK: bN === "^=" ? bP.indexOf(bK) === 0 : bN === "$=" ? bP.substr(bP.length - bK.length) === bK: bN === "|=" ? bP === bK || bP.substr(0, bK.length + 1) === bK + "-": false
				},
				POS: function(bN, bK, bL, bO) {
					var e = bK[2],
					bM = by.setFilters[e];
					if (bM) {
						return bM(bN, bL, bK, bO)
					}
				}
			}
		};
		var bx = by.match.POS,
		bs = function(bK, e) {
			return "\\" + (e - 0 + 1)
		};
		for (var bu in by.match) {
			by.match[bu] = new RegExp(by.match[bu].source + (/(?![^\[]*\])(?![^\(]*\))/.source));
			by.leftMatch[bu] = new RegExp(/(^(?:.|\r|\n)*?)/.source + by.match[bu].source.replace(/\\(\d+)/g, bs))
		}
		var bz = function(bK, e) {
			bK = Array.prototype.slice.call(bK, 0);
			if (e) {
				e.push.apply(e, bK);
				return e
			}
			return bK
		};
		try {
			Array.prototype.slice.call(ap.documentElement.childNodes, 0)[0].nodeType
		} catch(bI) {
			bz = function(bN, bM) {
				var bL = 0,
				bK = bM || [];
				if (bF.call(bN) === "[object Array]") {
					Array.prototype.push.apply(bK, bN)
				} else {
					if (typeof bN.length === "number") {
						for (var e = bN.length; bL < e; bL++) {
							bK.push(bN[bL])
						}
					} else {
						for (; bN[bL]; bL++) {
							bK.push(bN[bL])
						}
					}
				}
				return bK
			}
		}
		var bE, bA;
		if (ap.documentElement.compareDocumentPosition) {
			bE = function(bK, e) {
				if (bK === e) {
					bw = true;
					return 0
				}
				if (!bK.compareDocumentPosition || !e.compareDocumentPosition) {
					return bK.compareDocumentPosition ? -1 : 1
				}
				return bK.compareDocumentPosition(e) & 4 ? -1 : 1
			}
		} else {
			bE = function(bR, bQ) {
				if (bR === bQ) {
					bw = true;
					return 0
				} else {
					if (bR.sourceIndex && bQ.sourceIndex) {
						return bR.sourceIndex - bQ.sourceIndex
					}
				}
				var bO, bK, bL = [],
				e = [],
				bN = bR.parentNode,
				bP = bQ.parentNode,
				bS = bN;
				if (bN === bP) {
					return bA(bR, bQ)
				} else {
					if (!bN) {
						return - 1
					} else {
						if (!bP) {
							return 1
						}
					}
				}
				while (bS) {
					bL.unshift(bS);
					bS = bS.parentNode
				}
				bS = bP;
				while (bS) {
					e.unshift(bS);
					bS = bS.parentNode
				}
				bO = bL.length;
				bK = e.length;
				for (var bM = 0; bM < bO && bM < bK; bM++) {
					if (bL[bM] !== e[bM]) {
						return bA(bL[bM], e[bM])
					}
				}
				return bM === bO ? bA(bR, e[bM], -1) : bA(bL[bM], bQ, 1)
			};
			bA = function(bK, e, bL) {
				if (bK === e) {
					return bL
				}
				var bM = bK.nextSibling;
				while (bM) {
					if (bM === e) {
						return - 1
					}
					bM = bM.nextSibling
				}
				return 1
			}
		}
		bt.getText = function(e) {
			var bK = "",
			bM;
			for (var bL = 0; e[bL]; bL++) {
				bM = e[bL];
				if (bM.nodeType === 3 || bM.nodeType === 4) {
					bK += bM.nodeValue
				} else {
					if (bM.nodeType !== 8) {
						bK += bt.getText(bM.childNodes)
					}
				}
			}
			return bK
		}; (function() {
			var bK = ap.createElement("div"),
			bL = "script" + (new Date()).getTime(),
			e = ap.documentElement;
			bK.innerHTML = "<a name='" + bL + "'/>";
			e.insertBefore(bK, e.firstChild);
			if (ap.getElementById(bL)) {
				by.find.ID = function(bN, bO, bP) {
					if (typeof bO.getElementById !== "undefined" && !bP) {
						var bM = bO.getElementById(bN[1]);
						return bM ? bM.id === bN[1] || typeof bM.getAttributeNode !== "undefined" && bM.getAttributeNode("id").nodeValue === bN[1] ? [bM] : K: []
					}
				};
				by.filter.ID = function(bO, bM) {
					var bN = typeof bO.getAttributeNode !== "undefined" && bO.getAttributeNode("id");
					return bO.nodeType === 1 && bN && bN.nodeValue === bM
				}
			}
			e.removeChild(bK);
			e = bK = null
		})(); (function() {
			var e = ap.createElement("div");
			e.appendChild(ap.createComment(""));
			if (e.getElementsByTagName("*").length > 0) {
				by.find.TAG = function(bK, bO) {
					var bN = bO.getElementsByTagName(bK[1]);
					if (bK[1] === "*") {
						var bM = [];
						for (var bL = 0; bN[bL]; bL++) {
							if (bN[bL].nodeType === 1) {
								bM.push(bN[bL])
							}
						}
						bN = bM
					}
					return bN
				}
			}
			e.innerHTML = "<a href='#'></a>";
			if (e.firstChild && typeof e.firstChild.getAttribute !== "undefined" && e.firstChild.getAttribute("href") !== "#") {
				by.attrHandle.href = function(bK) {
					return bK.getAttribute("href", 2)
				}
			}
			e = null
		})();
		if (ap.querySelectorAll) { (function() {
				var e = bt,
				bM = ap.createElement("div"),
				bL = "__sizzle__";
				bM.innerHTML = "<p class='TEST'></p>";
				if (bM.querySelectorAll && bM.querySelectorAll(".TEST").length === 0) {
					return
				}
				bt = function(bX, bO, bS, bW) {
					bO = bO || ap;
					if (!bW && !bt.isXML(bO)) {
						var bV = /^(\w+$)|^\.([\w\-]+$)|^#([\w\-]+$)/.exec(bX);
						if (bV && (bO.nodeType === 1 || bO.nodeType === 9)) {
							if (bV[1]) {
								return bz(bO.getElementsByTagName(bX), bS)
							} else {
								if (bV[2] && by.find.CLASS && bO.getElementsByClassName) {
									return bz(bO.getElementsByClassName(bV[2]), bS)
								}
							}
						}
						if (bO.nodeType === 9) {
							if (bX === "body" && bO.body) {
								return bz([bO.body], bS)
							} else {
								if (bV && bV[3]) {
									var bR = bO.getElementById(bV[3]);
									if (bR && bR.parentNode) {
										if (bR.id === bV[3]) {
											return bz([bR], bS)
										}
									} else {
										return bz([], bS)
									}
								}
							}
							try {
								return bz(bO.querySelectorAll(bX), bS)
							} catch(bT) {}
						} else {
							if (bO.nodeType === 1 && bO.nodeName.toLowerCase() !== "object") {
								var bP = bO,
								bQ = bO.getAttribute("id"),
								bN = bQ || bL,
								bZ = bO.parentNode,
								bY = /^\s*[+~]/.test(bX);
								if (!bQ) {
									bO.setAttribute("id", bN)
								} else {
									bN = bN.replace(/'/g, "\\$&")
								}
								if (bY && bZ) {
									bO = bO.parentNode
								}
								try {
									if (!bY || bZ) {
										return bz(bO.querySelectorAll("[id='" + bN + "'] " + bX), bS)
									}
								} catch(bU) {} finally {
									if (!bQ) {
										bP.removeAttribute("id")
									}
								}
							}
						}
					}
					return e(bX, bO, bS, bW)
				};
				for (var bK in e) {
					bt[bK] = e[bK]
				}
				bM = null
			})()
		} (function() {
			var e = ap.documentElement,
			bL = e.matchesSelector || e.mozMatchesSelector || e.webkitMatchesSelector || e.msMatchesSelector;
			if (bL) {
				var bN = !bL.call(ap.createElement("div"), "div"),
				bK = false;
				try {
					bL.call(ap.documentElement, "[test!='']:sizzle")
				} catch(bM) {
					bK = true
				}
				bt.matchesSelector = function(bP, bR) {
					bR = bR.replace(/\=\s*([^'"\]]*)\s*\]/g, "='$1']");
					if (!bt.isXML(bP)) {
						try {
							if (bK || !by.match.PSEUDO.test(bR) && !/!=/.test(bR)) {
								var bO = bL.call(bP, bR);
								if (bO || !bN || bP.document && bP.document.nodeType !== 11) {
									return bO
								}
							}
						} catch(bQ) {}
					}
					return bt(bR, null, null, [bP]).length > 0
				}
			}
		})(); (function() {
			var e = ap.createElement("div");
			e.innerHTML = "<div class='test e'></div><div class='test'></div>";
			if (!e.getElementsByClassName || e.getElementsByClassName("e").length === 0) {
				return
			}
			e.lastChild.className = "e";
			if (e.getElementsByClassName("e").length === 1) {
				return
			}
			by.order.splice(1, 0, "CLASS");
			by.find.CLASS = function(bK, bL, bM) {
				if (typeof bL.getElementsByClassName !== "undefined" && !bM) {
					return bL.getElementsByClassName(bK[1])
				}
			};
			e = null
		})();
		function br(bK, bP, bO, bS, bQ, bR) {
			for (var bM = 0,
			bL = bS.length; bM < bL; bM++) {
				var e = bS[bM];
				if (e) {
					var bN = false;
					e = e[bK];
					while (e) {
						if (e.sizcache === bO) {
							bN = bS[e.sizset];
							break
						}
						if (e.nodeType === 1 && !bR) {
							e.sizcache = bO;
							e.sizset = bM
						}
						if (e.nodeName.toLowerCase() === bP) {
							bN = e;
							break
						}
						e = e[bK]
					}
					bS[bM] = bN
				}
			}
		}
		function bH(bK, bP, bO, bS, bQ, bR) {
			for (var bM = 0,
			bL = bS.length; bM < bL; bM++) {
				var e = bS[bM];
				if (e) {
					var bN = false;
					e = e[bK];
					while (e) {
						if (e.sizcache === bO) {
							bN = bS[e.sizset];
							break
						}
						if (e.nodeType === 1) {
							if (!bR) {
								e.sizcache = bO;
								e.sizset = bM
							}
							if (typeof bP !== "string") {
								if (e === bP) {
									bN = true;
									break
								}
							} else {
								if (bt.filter(bP, [e]).length > 0) {
									bN = e;
									break
								}
							}
						}
						e = e[bK]
					}
					bS[bM] = bN
				}
			}
		}
		if (ap.documentElement.contains) {
			bt.contains = function(bK, e) {
				return bK !== e && (bK.contains ? bK.contains(e) : true)
			}
		} else {
			if (ap.documentElement.compareDocumentPosition) {
				bt.contains = function(bK, e) {
					return !! (bK.compareDocumentPosition(e) & 16)
				}
			} else {
				bt.contains = function() {
					return false
				}
			}
		}
		bt.isXML = function(e) {
			var bK = (e ? e.ownerDocument || e: 0).documentElement;
			return bK ? bK.nodeName !== "HTML": false
		};
		var bG = function(e, bQ) {
			var bO, bM = [],
			bN = "",
			bL = bQ.nodeType ? [bQ] : bQ;
			while ((bO = by.match.PSEUDO.exec(e))) {
				bN += bO[0];
				e = e.replace(by.match.PSEUDO, "")
			}
			e = by.relative[e] ? e + "*": e;
			for (var bP = 0,
			bK = bL.length; bP < bK; bP++) {
				bt(e, bL[bP], bM)
			}
			return bt.filter(bN, bM)
		};
		b.find = bt;
		b.expr = bt.selectors;
		b.expr[":"] = b.expr.filters;
		b.unique = bt.uniqueSort;
		b.text = bt.getText;
		b.isXMLDoc = bt.isXML;
		b.contains = bt.contains
	})();
	var X = /Until$/,
	al = /^(?:parents|prevUntil|prevAll)/,
	a5 = /,/,
	bm = /^.[^:#\[\.,]*$/,
	P = Array.prototype.slice,
	H = b.expr.match.POS,
	at = {
		children: true,
		contents: true,
		next: true,
		prev: true
	};
	b.fn.extend({
		find: function(e) {
			var bs = this,
			bu, br;
			if (typeof e !== "string") {
				return b(e).filter(function() {
					for (bu = 0, br = bs.length; bu < br; bu++) {
						if (b.contains(bs[bu], this)) {
							return true
						}
					}
				})
			}
			var bt = this.pushStack("", "find", e),
			bw,
			bx,
			bv;
			for (bu = 0, br = this.length; bu < br; bu++) {
				bw = bt.length;
				b.find(e, this[bu], bt);
				if (bu > 0) {
					for (bx = bw; bx < bt.length; bx++) {
						for (bv = 0; bv < bw; bv++) {
							if (bt[bv] === bt[bx]) {
								bt.splice(bx--, 1);
								break
							}
						}
					}
				}
			}
			return bt
		},
		has: function(br) {
			var e = b(br);
			return this.filter(function() {
				for (var bt = 0,
				bs = e.length; bt < bs; bt++) {
					if (b.contains(this, e[bt])) {
						return true
					}
				}
			})
		},
		not: function(e) {
			return this.pushStack(aA(this, e, false), "not", e)
		},
		filter: function(e) {
			return this.pushStack(aA(this, e, true), "filter", e)
		},
		is: function(e) {
			return !! e && (typeof e === "string" ? b.filter(e, this).length > 0 : this.filter(e).length > 0)
		},
		closest: function(bA, br) {
			var bx = [],
			bu,
			bs,
			bz = this[0];
			if (b.isArray(bA)) {
				var bw, bt, bv = {},
				e = 1;
				if (bz && bA.length) {
					for (bu = 0, bs = bA.length; bu < bs; bu++) {
						bt = bA[bu];
						if (!bv[bt]) {
							bv[bt] = H.test(bt) ? b(bt, br || this.context) : bt
						}
					}
					while (bz && bz.ownerDocument && bz !== br) {
						for (bt in bv) {
							bw = bv[bt];
							if (bw.jquery ? bw.index(bz) > -1 : b(bz).is(bw)) {
								bx.push({
									selector: bt,
									elem: bz,
									level: e
								})
							}
						}
						bz = bz.parentNode;
						e++
					}
				}
				return bx
			}
			var by = H.test(bA) || typeof bA !== "string" ? b(bA, br || this.context) : 0;
			for (bu = 0, bs = this.length; bu < bs; bu++) {
				bz = this[bu];
				while (bz) {
					if (by ? by.index(bz) > -1 : b.find.matchesSelector(bz, bA)) {
						bx.push(bz);
						break
					} else {
						bz = bz.parentNode;
						if (!bz || !bz.ownerDocument || bz === br || bz.nodeType === 11) {
							break
						}
					}
				}
			}
			bx = bx.length > 1 ? b.unique(bx) : bx;
			return this.pushStack(bx, "closest", bA)
		},
		index: function(e) {
			if (!e) {
				return (this[0] && this[0].parentNode) ? this.prevAll().length: -1
			}
			if (typeof e === "string") {
				return b.inArray(this[0], b(e))
			}
			return b.inArray(e.jquery ? e[0] : e, this)
		},
		add: function(e, br) {
			var bt = typeof e === "string" ? b(e, br) : b.makeArray(e && e.nodeType ? [e] : e),
			bs = b.merge(this.get(), bt);
			return this.pushStack(C(bt[0]) || C(bs[0]) ? bs: b.unique(bs))
		},
		andSelf: function() {
			return this.add(this.prevObject)
		}
	});
	function C(e) {
		return ! e || !e.parentNode || e.parentNode.nodeType === 11
	}
	b.each({
		parent: function(br) {
			var e = br.parentNode;
			return e && e.nodeType !== 11 ? e: null
		},
		parents: function(e) {
			return b.dir(e, "parentNode")
		},
		parentsUntil: function(br, e, bs) {
			return b.dir(br, "parentNode", bs)
		},
		next: function(e) {
			return b.nth(e, 2, "nextSibling")
		},
		prev: function(e) {
			return b.nth(e, 2, "previousSibling")
		},
		nextAll: function(e) {
			return b.dir(e, "nextSibling")
		},
		prevAll: function(e) {
			return b.dir(e, "previousSibling")
		},
		nextUntil: function(br, e, bs) {
			return b.dir(br, "nextSibling", bs)
		},
		prevUntil: function(br, e, bs) {
			return b.dir(br, "previousSibling", bs)
		},
		siblings: function(e) {
			return b.sibling(e.parentNode.firstChild, e)
		},
		children: function(e) {
			return b.sibling(e.firstChild)
		},
		contents: function(e) {
			return b.nodeName(e, "iframe") ? e.contentDocument || e.contentWindow.document: b.makeArray(e.childNodes)
		}
	},
	function(e, br) {
		b.fn[e] = function(bv, bs) {
			var bu = b.map(this, br, bv),
			bt = P.call(arguments);
			if (!X.test(e)) {
				bs = bv
			}
			if (bs && typeof bs === "string") {
				bu = b.filter(bs, bu)
			}
			bu = this.length > 1 && !at[e] ? b.unique(bu) : bu;
			if ((this.length > 1 || a5.test(bs)) && al.test(e)) {
				bu = bu.reverse()
			}
			return this.pushStack(bu, e, bt.join(","))
		}
	});
	b.extend({
		filter: function(bs, e, br) {
			if (br) {
				bs = ":not(" + bs + ")"
			}
			return e.length === 1 ? b.find.matchesSelector(e[0], bs) ? [e[0]] : [] : b.find.matches(bs, e)
		},
		dir: function(bs, br, bu) {
			var e = [],
			bt = bs[br];
			while (bt && bt.nodeType !== 9 && (bu === K || bt.nodeType !== 1 || !b(bt).is(bu))) {
				if (bt.nodeType === 1) {
					e.push(bt)
				}
				bt = bt[br]
			}
			return e
		},
		nth: function(bu, e, bs, bt) {
			e = e || 1;
			var br = 0;
			for (; bu; bu = bu[bs]) {
				if (bu.nodeType === 1 && ++br === e) {
					break
				}
			}
			return bu
		},
		sibling: function(bs, br) {
			var e = [];
			for (; bs; bs = bs.nextSibling) {
				if (bs.nodeType === 1 && bs !== br) {
					e.push(bs)
				}
			}
			return e
		}
	});
	function aA(bt, bs, e) {
		bs = bs || 0;
		if (b.isFunction(bs)) {
			return b.grep(bt,
			function(bv, bu) {
				var bw = !!bs.call(bv, bu, bv);
				return bw === e
			})
		} else {
			if (bs.nodeType) {
				return b.grep(bt,
				function(bv, bu) {
					return (bv === bs) === e
				})
			} else {
				if (typeof bs === "string") {
					var br = b.grep(bt,
					function(bu) {
						return bu.nodeType === 1
					});
					if (bm.test(bs)) {
						return b.filter(bs, br, !e)
					} else {
						bs = b.filter(bs, br)
					}
				}
			}
		}
		return b.grep(bt,
		function(bv, bu) {
			return (b.inArray(bv, bs) >= 0) === e
		})
	}
	var ac = / jQuery\d+="(?:\d+|null)"/g,
	am = /^\s+/,
	R = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/ig,
	d = /<([\w:]+)/,
	w = /<tbody/i,
	U = /<|&#?\w+;/,
	O = /<(?:script|object|embed|option|style)/i,
	n = /checked\s*(?:[^=]|=\s*.checked.)/i,
	bi = /\/(java|ecma)script/i,
	aI = /^\s*<!(?:\[CDATA\[|\-\-)/,
	ar = {
		option: [1, "<select multiple='multiple'>", "</select>"],
		legend: [1, "<fieldset>", "</fieldset>"],
		thead: [1, "<table>", "</table>"],
		tr: [2, "<table><tbody>", "</tbody></table>"],
		td: [3, "<table><tbody><tr>", "</tr></tbody></table>"],
		col: [2, "<table><tbody></tbody><colgroup>", "</colgroup></table>"],
		area: [1, "<map>", "</map>"],
		_default: [0, "", ""]
	};
	ar.optgroup = ar.option;
	ar.tbody = ar.tfoot = ar.colgroup = ar.caption = ar.thead;
	ar.th = ar.td;
	if (!b.support.htmlSerialize) {
		ar._default = [1, "div<div>", "</div>"]
	}
	b.fn.extend({
		text: function(e) {
			if (b.isFunction(e)) {
				return this.each(function(bs) {
					var br = b(this);
					br.text(e.call(this, bs, br.text()))
				})
			}
			if (typeof e !== "object" && e !== K) {
				return this.empty().append((this[0] && this[0].ownerDocument || ap).createTextNode(e))
			}
			return b.text(this)
		},
		wrapAll: function(e) {
			if (b.isFunction(e)) {
				return this.each(function(bs) {
					b(this).wrapAll(e.call(this, bs))
				})
			}
			if (this[0]) {
				var br = b(e, this[0].ownerDocument).eq(0).clone(true);
				if (this[0].parentNode) {
					br.insertBefore(this[0])
				}
				br.map(function() {
					var bs = this;
					while (bs.firstChild && bs.firstChild.nodeType === 1) {
						bs = bs.firstChild
					}
					return bs
				}).append(this)
			}
			return this
		},
		wrapInner: function(e) {
			if (b.isFunction(e)) {
				return this.each(function(br) {
					b(this).wrapInner(e.call(this, br))
				})
			}
			return this.each(function() {
				var br = b(this),
				bs = br.contents();
				if (bs.length) {
					bs.wrapAll(e)
				} else {
					br.append(e)
				}
			})
		},
		wrap: function(e) {
			return this.each(function() {
				b(this).wrapAll(e)
			})
		},
		unwrap: function() {
			return this.parent().each(function() {
				if (!b.nodeName(this, "body")) {
					b(this).replaceWith(this.childNodes)
				}
			}).end()
		},
		append: function() {
			return this.domManip(arguments, true,
			function(e) {
				if (this.nodeType === 1) {
					this.appendChild(e)
				}
			})
		},
		prepend: function() {
			return this.domManip(arguments, true,
			function(e) {
				if (this.nodeType === 1) {
					this.insertBefore(e, this.firstChild)
				}
			})
		},
		before: function() {
			if (this[0] && this[0].parentNode) {
				return this.domManip(arguments, false,
				function(br) {
					this.parentNode.insertBefore(br, this)
				})
			} else {
				if (arguments.length) {
					var e = b(arguments[0]);
					e.push.apply(e, this.toArray());
					return this.pushStack(e, "before", arguments)
				}
			}
		},
		after: function() {
			if (this[0] && this[0].parentNode) {
				return this.domManip(arguments, false,
				function(br) {
					this.parentNode.insertBefore(br, this.nextSibling)
				})
			} else {
				if (arguments.length) {
					var e = this.pushStack(this, "after", arguments);
					e.push.apply(e, b(arguments[0]).toArray());
					return e
				}
			}
		},
		remove: function(e, bt) {
			for (var br = 0,
			bs; (bs = this[br]) != null; br++) {
				if (!e || b.filter(e, [bs]).length) {
					if (!bt && bs.nodeType === 1) {
						b.cleanData(bs.getElementsByTagName("*"));
						b.cleanData([bs])
					}
					if (bs.parentNode) {
						bs.parentNode.removeChild(bs)
					}
				}
			}
			return this
		},
		empty: function() {
			for (var e = 0,
			br; (br = this[e]) != null; e++) {
				if (br.nodeType === 1) {
					b.cleanData(br.getElementsByTagName("*"))
				}
				while (br.firstChild) {
					br.removeChild(br.firstChild)
				}
			}
			return this
		},
		clone: function(br, e) {
			br = br == null ? false: br;
			e = e == null ? br: e;
			return this.map(function() {
				return b.clone(this, br, e)
			})
		},
		html: function(bt) {
			if (bt === K) {
				return this[0] && this[0].nodeType === 1 ? this[0].innerHTML.replace(ac, "") : null
			} else {
				if (typeof bt === "string" && !O.test(bt) && (b.support.leadingWhitespace || !am.test(bt)) && !ar[(d.exec(bt) || ["", ""])[1].toLowerCase()]) {
					bt = bt.replace(R, "<$1></$2>");
					try {
						for (var bs = 0,
						br = this.length; bs < br; bs++) {
							if (this[bs].nodeType === 1) {
								b.cleanData(this[bs].getElementsByTagName("*"));
								this[bs].innerHTML = bt
							}
						}
					} catch(bu) {
						this.empty().append(bt)
					}
				} else {
					if (b.isFunction(bt)) {
						this.each(function(bv) {
							var e = b(this);
							e.html(bt.call(this, bv, e.html()))
						})
					} else {
						this.empty().append(bt)
					}
				}
			}
			return this
		},
		replaceWith: function(e) {
			if (this[0] && this[0].parentNode) {
				if (b.isFunction(e)) {
					return this.each(function(bt) {
						var bs = b(this),
						br = bs.html();
						bs.replaceWith(e.call(this, bt, br))
					})
				}
				if (typeof e !== "string") {
					e = b(e).detach()
				}
				return this.each(function() {
					var bs = this.nextSibling,
					br = this.parentNode;
					b(this).remove();
					if (bs) {
						b(bs).before(e)
					} else {
						b(br).append(e)
					}
				})
			} else {
				return this.length ? this.pushStack(b(b.isFunction(e) ? e() : e), "replaceWith", e) : this
			}
		},
		detach: function(e) {
			return this.remove(e, true)
		},
		domManip: function(bx, bB, bA) {
			var bt, bu, bw, bz, by = bx[0],
			br = [];
			if (!b.support.checkClone && arguments.length === 3 && typeof by === "string" && n.test(by)) {
				return this.each(function() {
					b(this).domManip(bx, bB, bA, true)
				})
			}
			if (b.isFunction(by)) {
				return this.each(function(bD) {
					var bC = b(this);
					bx[0] = by.call(this, bD, bB ? bC.html() : K);
					bC.domManip(bx, bB, bA)
				})
			}
			if (this[0]) {
				bz = by && by.parentNode;
				if (b.support.parentNode && bz && bz.nodeType === 11 && bz.childNodes.length === this.length) {
					bt = {
						fragment: bz
					}
				} else {
					bt = b.buildFragment(bx, this, br)
				}
				bw = bt.fragment;
				if (bw.childNodes.length === 1) {
					bu = bw = bw.firstChild
				} else {
					bu = bw.firstChild
				}
				if (bu) {
					bB = bB && b.nodeName(bu, "tr");
					for (var bs = 0,
					e = this.length,
					bv = e - 1; bs < e; bs++) {
						bA.call(bB ? a6(this[bs], bu) : this[bs], bt.cacheable || (e > 1 && bs < bv) ? b.clone(bw, true, true) : bw)
					}
				}
				if (br.length) {
					b.each(br, bl)
				}
			}
			return this
		}
	});
	function a6(e, br) {
		return b.nodeName(e, "table") ? (e.getElementsByTagName("tbody")[0] || e.appendChild(e.ownerDocument.createElement("tbody"))) : e
	}
	function t(e, bx) {
		if (bx.nodeType !== 1 || !b.hasData(e)) {
			return
		}
		var bw = b.expando,
		bt = b.data(e),
		bu = b.data(bx, bt);
		if ((bt = bt[bw])) {
			var by = bt.events;
			bu = bu[bw] = b.extend({},
			bt);
			if (by) {
				delete bu.handle;
				bu.events = {};
				for (var bv in by) {
					for (var bs = 0,
					br = by[bv].length; bs < br; bs++) {
						b.event.add(bx, bv + (by[bv][bs].namespace ? ".": "") + by[bv][bs].namespace, by[bv][bs], by[bv][bs].data)
					}
				}
			}
		}
	}
	function ad(br, e) {
		var bs;
		if (e.nodeType !== 1) {
			return
		}
		if (e.clearAttributes) {
			e.clearAttributes()
		}
		if (e.mergeAttributes) {
			e.mergeAttributes(br)
		}
		bs = e.nodeName.toLowerCase();
		if (bs === "object") {
			e.outerHTML = br.outerHTML
		} else {
			if (bs === "input" && (br.type === "checkbox" || br.type === "radio")) {
				if (br.checked) {
					e.defaultChecked = e.checked = br.checked
				}
				if (e.value !== br.value) {
					e.value = br.value
				}
			} else {
				if (bs === "option") {
					e.selected = br.defaultSelected
				} else {
					if (bs === "input" || bs === "textarea") {
						e.defaultValue = br.defaultValue
					}
				}
			}
		}
		e.removeAttribute(b.expando)
	}
	b.buildFragment = function(bv, bt, br) {
		var bu, e, bs, bw;
		if (bt && bt[0]) {
			bw = bt[0].ownerDocument || bt[0]
		}
		if (!bw.createDocumentFragment) {
			bw = ap
		}
		if (bv.length === 1 && typeof bv[0] === "string" && bv[0].length < 512 && bw === ap && bv[0].charAt(0) === "<" && !O.test(bv[0]) && (b.support.checkClone || !n.test(bv[0]))) {
			e = true;
			bs = b.fragments[bv[0]];
			if (bs && bs !== 1) {
				bu = bs
			}
		}
		if (!bu) {
			bu = bw.createDocumentFragment();
			b.clean(bv, bw, bu, br)
		}
		if (e) {
			b.fragments[bv[0]] = bs ? bu: 1
		}
		return {
			fragment: bu,
			cacheable: e
		}
	};
	b.fragments = {};
	b.each({
		appendTo: "append",
		prependTo: "prepend",
		insertBefore: "before",
		insertAfter: "after",
		replaceAll: "replaceWith"
	},
	function(e, br) {
		b.fn[e] = function(bs) {
			var bv = [],
			by = b(bs),
			bx = this.length === 1 && this[0].parentNode;
			if (bx && bx.nodeType === 11 && bx.childNodes.length === 1 && by.length === 1) {
				by[br](this[0]);
				return this
			} else {
				for (var bw = 0,
				bt = by.length; bw < bt; bw++) {
					var bu = (bw > 0 ? this.clone(true) : this).get();
					b(by[bw])[br](bu);
					bv = bv.concat(bu)
				}
				return this.pushStack(bv, e, by.selector)
			}
		}
	});
	function bb(e) {
		if ("getElementsByTagName" in e) {
			return e.getElementsByTagName("*")
		} else {
			if ("querySelectorAll" in e) {
				return e.querySelectorAll("*")
			} else {
				return []
			}
		}
	}
	function au(e) {
		if (e.type === "checkbox" || e.type === "radio") {
			e.defaultChecked = e.checked
		}
	}
	function E(e) {
		if (b.nodeName(e, "input")) {
			au(e)
		} else {
			if ("getElementsByTagName" in e) {
				b.grep(e.getElementsByTagName("input"), au)
			}
		}
	}
	b.extend({
		clone: function(bu, bw, bs) {
			var bv = bu.cloneNode(true),
			e,
			br,
			bt;
			if ((!b.support.noCloneEvent || !b.support.noCloneChecked) && (bu.nodeType === 1 || bu.nodeType === 11) && !b.isXMLDoc(bu)) {
				ad(bu, bv);
				e = bb(bu);
				br = bb(bv);
				for (bt = 0; e[bt]; ++bt) {
					if (br[bt]) {
						ad(e[bt], br[bt])
					}
				}
			}
			if (bw) {
				t(bu, bv);
				if (bs) {
					e = bb(bu);
					br = bb(bv);
					for (bt = 0; e[bt]; ++bt) {
						t(e[bt], br[bt])
					}
				}
			}
			e = br = null;
			return bv
		},
		clean: function(bs, bu, bD, bw) {
			var bB;
			bu = bu || ap;
			if (typeof bu.createElement === "undefined") {
				bu = bu.ownerDocument || bu[0] && bu[0].ownerDocument || ap
			}
			var bE = [],
			bx;
			for (var bA = 0,
			bv; (bv = bs[bA]) != null; bA++) {
				if (typeof bv === "number") {
					bv += ""
				}
				if (!bv) {
					continue
				}
				if (typeof bv === "string") {
					if (!U.test(bv)) {
						bv = bu.createTextNode(bv)
					} else {
						bv = bv.replace(R, "<$1></$2>");
						var bG = (d.exec(bv) || ["", ""])[1].toLowerCase(),
						bt = ar[bG] || ar._default,
						bz = bt[0],
						br = bu.createElement("div");
						br.innerHTML = bt[1] + bv + bt[2];
						while (bz--) {
							br = br.lastChild
						}
						if (!b.support.tbody) {
							var e = w.test(bv),
							by = bG === "table" && !e ? br.firstChild && br.firstChild.childNodes: bt[1] === "<table>" && !e ? br.childNodes: [];
							for (bx = by.length - 1; bx >= 0; --bx) {
								if (b.nodeName(by[bx], "tbody") && !by[bx].childNodes.length) {
									by[bx].parentNode.removeChild(by[bx])
								}
							}
						}
						if (!b.support.leadingWhitespace && am.test(bv)) {
							br.insertBefore(bu.createTextNode(am.exec(bv)[0]), br.firstChild)
						}
						bv = br.childNodes
					}
				}
				var bC;
				if (!b.support.appendChecked) {
					if (bv[0] && typeof(bC = bv.length) === "number") {
						for (bx = 0; bx < bC; bx++) {
							E(bv[bx])
						}
					} else {
						E(bv)
					}
				}
				if (bv.nodeType) {
					bE.push(bv)
				} else {
					bE = b.merge(bE, bv)
				}
			}
			if (bD) {
				bB = function(bH) {
					return ! bH.type || bi.test(bH.type)
				};
				for (bA = 0; bE[bA]; bA++) {
					if (bw && b.nodeName(bE[bA], "script") && (!bE[bA].type || bE[bA].type.toLowerCase() === "text/javascript")) {
						bw.push(bE[bA].parentNode ? bE[bA].parentNode.removeChild(bE[bA]) : bE[bA])
					} else {
						if (bE[bA].nodeType === 1) {
							var bF = b.grep(bE[bA].getElementsByTagName("script"), bB);
							bE.splice.apply(bE, [bA + 1, 0].concat(bF))
						}
						bD.appendChild(bE[bA])
					}
				}
			}
			return bE
		},
		cleanData: function(br) {
			var bu, bs, e = b.cache,
			bz = b.expando,
			bx = b.event.special,
			bw = b.support.deleteExpando;
			for (var bv = 0,
			bt; (bt = br[bv]) != null; bv++) {
				if (bt.nodeName && b.noData[bt.nodeName.toLowerCase()]) {
					continue
				}
				bs = bt[b.expando];
				if (bs) {
					bu = e[bs] && e[bs][bz];
					if (bu && bu.events) {
						for (var by in bu.events) {
							if (bx[by]) {
								b.event.remove(bt, by)
							} else {
								b.removeEvent(bt, by, bu.handle)
							}
						}
						if (bu.handle) {
							bu.handle.elem = null
						}
					}
					if (bw) {
						delete bt[b.expando]
					} else {
						if (bt.removeAttribute) {
							bt.removeAttribute(b.expando)
						}
					}
					delete e[bs]
				}
			}
		}
	});
	function bl(e, br) {
		if (br.src) {
			b.ajax({
				url: br.src,
				async: false,
				dataType: "script"
			})
		} else {
			b.globalEval((br.text || br.textContent || br.innerHTML || "").replace(aI, "/*$0*/"))
		}
		if (br.parentNode) {
			br.parentNode.removeChild(br)
		}
	}
	var af = /alpha\([^)]*\)/i,
	ao = /opacity=([^)]*)/,
	z = /([A-Z]|^ms)/g,
	a8 = /^-?\d+(?:px)?$/i,
	bk = /^-?\d/,
	I = /^([\-+])=([\-+.\de]+)/,
	a3 = {
		position: "absolute",
		visibility: "hidden",
		display: "block"
	},
	ai = ["Left", "Right"],
	aY = ["Top", "Bottom"],
	V,
	aD,
	aS;
	b.fn.css = function(e, br) {
		if (arguments.length === 2 && br === K) {
			return this
		}
		return b.access(this, e, br, true,
		function(bt, bs, bu) {
			return bu !== K ? b.style(bt, bs, bu) : b.css(bt, bs)
		})
	};
	b.extend({
		cssHooks: {
			opacity: {
				get: function(bs, br) {
					if (br) {
						var e = V(bs, "opacity", "opacity");
						return e === "" ? "1": e
					} else {
						return bs.style.opacity
					}
				}
			}
		},
		cssNumber: {
			fillOpacity: true,
			fontWeight: true,
			lineHeight: true,
			opacity: true,
			orphans: true,
			widows: true,
			zIndex: true,
			zoom: true
		},
		cssProps: {
			"float": b.support.cssFloat ? "cssFloat": "styleFloat"
		},
		style: function(bt, bs, bz, bu) {
			if (!bt || bt.nodeType === 3 || bt.nodeType === 8 || !bt.style) {
				return
			}
			var bx, by, bv = b.camelCase(bs),
			br = bt.style,
			bA = b.cssHooks[bv];
			bs = b.cssProps[bv] || bv;
			if (bz !== K) {
				by = typeof bz;
				if (by === "string" && (bx = I.exec(bz))) {
					bz = ( + (bx[1] + 1) * +bx[2]) + parseFloat(b.css(bt, bs));
					by = "number"
				}
				if (bz == null || by === "number" && isNaN(bz)) {
					return
				}
				if (by === "number" && !b.cssNumber[bv]) {
					bz += "px"
				}
				if (!bA || !("set" in bA) || (bz = bA.set(bt, bz)) !== K) {
					try {
						br[bs] = bz
					} catch(bw) {}
				}
			} else {
				if (bA && "get" in bA && (bx = bA.get(bt, false, bu)) !== K) {
					return bx
				}
				return br[bs]
			}
		},
		css: function(bu, bt, br) {
			var bs, e;
			bt = b.camelCase(bt);
			e = b.cssHooks[bt];
			bt = b.cssProps[bt] || bt;
			if (bt === "cssFloat") {
				bt = "float"
			}
			if (e && "get" in e && (bs = e.get(bu, true, br)) !== K) {
				return bs
			} else {
				if (V) {
					return V(bu, bt)
				}
			}
		},
		swap: function(bt, bs, bu) {
			var e = {};
			for (var br in bs) {
				e[br] = bt.style[br];
				bt.style[br] = bs[br]
			}
			bu.call(bt);
			for (br in bs) {
				bt.style[br] = e[br]
			}
		}
	});
	b.curCSS = b.css;
	b.each(["height", "width"],
	function(br, e) {
		b.cssHooks[e] = {
			get: function(bu, bt, bs) {
				var bv;
				if (bt) {
					if (bu.offsetWidth !== 0) {
						return o(bu, e, bs)
					} else {
						b.swap(bu, a3,
						function() {
							bv = o(bu, e, bs)
						})
					}
					return bv
				}
			},
			set: function(bs, bt) {
				if (a8.test(bt)) {
					bt = parseFloat(bt);
					if (bt >= 0) {
						return bt + "px"
					}
				} else {
					return bt
				}
			}
		}
	});
	if (!b.support.opacity) {
		b.cssHooks.opacity = {
			get: function(br, e) {
				return ao.test((e && br.currentStyle ? br.currentStyle.filter: br.style.filter) || "") ? (parseFloat(RegExp.$1) / 100) + "": e ? "1": ""
			},
			set: function(bu, bv) {
				var bt = bu.style,
				br = bu.currentStyle,
				e = b.isNaN(bv) ? "": "alpha(opacity=" + bv * 100 + ")",
				bs = br && br.filter || bt.filter || "";
				bt.zoom = 1;
				if (bv >= 1 && b.trim(bs.replace(af, "")) === "") {
					bt.removeAttribute("filter");
					if (br && !br.filter) {
						return
					}
				}
				bt.filter = af.test(bs) ? bs.replace(af, e) : bs + " " + e
			}
		}
	}
	b(function() {
		if (!b.support.reliableMarginRight) {
			b.cssHooks.marginRight = {
				get: function(bs, br) {
					var e;
					b.swap(bs, {
						display: "inline-block"
					},
					function() {
						if (br) {
							e = V(bs, "margin-right", "marginRight")
						} else {
							e = bs.style.marginRight
						}
					});
					return e
				}
			}
		}
	});
	if (ap.defaultView && ap.defaultView.getComputedStyle) {
		aD = function(bu, bs) {
			var br, bt, e;
			bs = bs.replace(z, "-$1").toLowerCase();
			if (! (bt = bu.ownerDocument.defaultView)) {
				return K
			}
			if ((e = bt.getComputedStyle(bu, null))) {
				br = e.getPropertyValue(bs);
				if (br === "" && !b.contains(bu.ownerDocument.documentElement, bu)) {
					br = b.style(bu, bs)
				}
			}
			return br
		}
	}
	if (ap.documentElement.currentStyle) {
		aS = function(bu, bs) {
			var bv, br = bu && bu.currentStyle && bu.currentStyle[bs],
			e = bu && bu.runtimeStyle && bu.runtimeStyle[bs],
			bt = bu && bu.style;
			if (!a8.test(br) && bk.test(br)) {
				bv = bt.left;
				if (e) {
					bu.runtimeStyle.left = bu.currentStyle.left
				}
				bt.left = bs === "fontSize" ? "1em": (br || 0);
				br = bt.pixelLeft + "px";
				bt.left = bv;
				if (e) {
					bu.runtimeStyle.left = e
				}
			}
			return br === "" ? "auto": br
		}
	}
	V = aD || aS;
	function o(bs, br, e) {
		var bu = br === "width" ? bs.offsetWidth: bs.offsetHeight,
		bt = br === "width" ? ai: aY;
		if (bu > 0) {
			if (e !== "border") {
				b.each(bt,
				function() {
					if (!e) {
						bu -= parseFloat(b.css(bs, "padding" + this)) || 0
					}
					if (e === "margin") {
						bu += parseFloat(b.css(bs, e + this)) || 0
					} else {
						bu -= parseFloat(b.css(bs, "border" + this + "Width")) || 0
					}
				})
			}
			return bu + "px"
		}
		bu = V(bs, br, br);
		if (bu < 0 || bu == null) {
			bu = bs.style[br] || 0
		}
		bu = parseFloat(bu) || 0;
		if (e) {
			b.each(bt,
			function() {
				bu += parseFloat(b.css(bs, "padding" + this)) || 0;
				if (e !== "padding") {
					bu += parseFloat(b.css(bs, "border" + this + "Width")) || 0
				}
				if (e === "margin") {
					bu += parseFloat(b.css(bs, e + this)) || 0
				}
			})
		}
		return bu + "px"
	}
	if (b.expr && b.expr.filters) {
		b.expr.filters.hidden = function(bs) {
			var br = bs.offsetWidth,
			e = bs.offsetHeight;
			return (br === 0 && e === 0) || (!b.support.reliableHiddenOffsets && (bs.style.display || b.css(bs, "display")) === "none")
		};
		b.expr.filters.visible = function(e) {
			return ! b.expr.filters.hidden(e)
		}
	}
	var j = /%20/g,
	ak = /\[\]$/,
	bp = /\r?\n/g,
	bn = /#.*$/,
	ay = /^(.*?):[ \t]*([^\r\n]*)\r?$/mg,
	aV = /^(?:color|date|datetime|datetime-local|email|hidden|month|number|password|range|search|tel|text|time|url|week)$/i,
	aH = /^(?:about|app|app\-storage|.+\-extension|file|res|widget):$/,
	aK = /^(?:GET|HEAD)$/,
	c = /^\/\//,
	L = /\?/,
	a2 = /<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi,
	q = /^(?:select|textarea)/i,
	h = /\s+/,
	bo = /([?&])_=[^&]*/,
	J = /^([\w\+\.\-]+:)(?:\/\/([^\/?#:]*)(?::(\d+))?)?/,
	A = b.fn.load,
	W = {},
	r = {},
	az, s, aO = ["*/"] + ["*"];
	try {
		az = bh.href
	} catch(aq) {
		az = ap.createElement("a");
		az.href = "";
		az = az.href
	}
	s = J.exec(az.toLowerCase()) || [];
	function f(e) {
		return function(bu, bw) {
			if (typeof bu !== "string") {
				bw = bu;
				bu = "*"
			}
			if (b.isFunction(bw)) {
				var bt = bu.toLowerCase().split(h),
				bs = 0,
				bv = bt.length,
				br,
				bx,
				by;
				for (; bs < bv; bs++) {
					br = bt[bs];
					by = /^\+/.test(br);
					if (by) {
						br = br.substr(1) || "*"
					}
					bx = e[br] = e[br] || [];
					bx[by ? "unshift": "push"](bw)
				}
			}
		}
	}
	function aQ(br, bA, bv, bz, bx, bt) {
		bx = bx || bA.dataTypes[0];
		bt = bt || {};
		bt[bx] = true;
		var bw = br[bx],
		bs = 0,
		e = bw ? bw.length: 0,
		bu = (br === W),
		by;
		for (; bs < e && (bu || !by); bs++) {
			by = bw[bs](bA, bv, bz);
			if (typeof by === "string") {
				if (!bu || bt[by]) {
					by = K
				} else {
					bA.dataTypes.unshift(by);
					by = aQ(br, bA, bv, bz, by, bt)
				}
			}
		}
		if ((bu || !by) && !bt["*"]) {
			by = aQ(br, bA, bv, bz, "*", bt)
		}
		return by
	}
	function ah(bs, bt) {
		var br, e, bu = b.ajaxSettings.flatOptions || {};
		for (br in bt) {
			if (bt[br] !== K) { (bu[br] ? bs: (e || (e = {})))[br] = bt[br]
			}
		}
		if (e) {
			b.extend(true, bs, e)
		}
	}
	b.fn.extend({
		load: function(bs, bv, bw) {
			if (typeof bs !== "string" && A) {
				return A.apply(this, arguments)
			} else {
				if (!this.length) {
					return this
				}
			}
			var bu = bs.indexOf(" ");
			if (bu >= 0) {
				var e = bs.slice(bu, bs.length);
				bs = bs.slice(0, bu)
			}
			var bt = "GET";
			if (bv) {
				if (b.isFunction(bv)) {
					bw = bv;
					bv = K
				} else {
					if (typeof bv === "object") {
						bv = b.param(bv, b.ajaxSettings.traditional);
						bt = "POST"
					}
				}
			}
			var br = this;
			b.ajax({
				url: bs,
				type: bt,
				dataType: "html",
				data: bv,
				complete: function(by, bx, bz) {
					bz = by.responseText;
					if (by.isResolved()) {
						by.done(function(bA) {
							bz = bA
						});
						br.html(e ? b("<div>").append(bz.replace(a2, "")).find(e) : bz)
					}
					if (bw) {
						br.each(bw, [bz, bx, by])
					}
				}
			});
			return this
		},
		serialize: function() {
			return b.param(this.serializeArray())
		},
		serializeArray: function() {
			return this.map(function() {
				return this.elements ? b.makeArray(this.elements) : this
			}).filter(function() {
				return this.name && !this.disabled && (this.checked || q.test(this.nodeName) || aV.test(this.type))
			}).map(function(e, br) {
				var bs = b(this).val();
				return bs == null ? null: b.isArray(bs) ? b.map(bs,
				function(bu, bt) {
					return {
						name: br.name,
						value: bu.replace(bp, "\r\n")
					}
				}) : {
					name: br.name,
					value: bs.replace(bp, "\r\n")
				}
			}).get()
		}
	});
	b.each("ajaxStart ajaxStop ajaxComplete ajaxError ajaxSuccess ajaxSend".split(" "),
	function(e, br) {
		b.fn[br] = function(bs) {
			return this.bind(br, bs)
		}
	});
	b.each(["get", "post"],
	function(e, br) {
		b[br] = function(bs, bu, bv, bt) {
			if (b.isFunction(bu)) {
				bt = bt || bv;
				bv = bu;
				bu = K
			}
			return b.ajax({
				type: br,
				url: bs,
				data: bu,
				success: bv,
				dataType: bt
			})
		}
	});
	b.extend({
		getScript: function(e, br) {
			return b.get(e, K, br, "script")
		},
		getJSON: function(e, br, bs) {
			return b.get(e, br, bs, "json")
		},
		ajaxSetup: function(br, e) {
			if (e) {
				ah(br, b.ajaxSettings)
			} else {
				e = br;
				br = b.ajaxSettings
			}
			ah(br, e);
			return br
		},
		ajaxSettings: {
			url: az,
			isLocal: aH.test(s[1]),
			global: true,
			type: "GET",
			contentType: "application/x-www-form-urlencoded",
			processData: true,
			async: true,
			accepts: {
				xml: "application/xml, text/xml",
				html: "text/html",
				text: "text/plain",
				json: "application/json, text/javascript",
				"*": aO
			},
			contents: {
				xml: /xml/,
				html: /html/,
				json: /json/
			},
			responseFields: {
				xml: "responseXML",
				text: "responseText"
			},
			converters: {
				"* text": a7.String,
				"text html": true,
				"text json": b.parseJSON,
				"text xml": b.parseXML
			},
			flatOptions: {
				context: true,
				url: true
			}
		},
		ajaxPrefilter: f(W),
		ajaxTransport: f(r),
		ajax: function(bv, bt) {
			if (typeof bv === "object") {
				bt = bv;
				bv = K
			}
			bt = bt || {};
			var bz = b.ajaxSetup({},
			bt),
			bO = bz.context || bz,
			bC = bO !== bz && (bO.nodeType || bO instanceof b) ? b(bO) : b.event,
			bN = b.Deferred(),
			bJ = b._Deferred(),
			bx = bz.statusCode || {},
			by,
			bD = {},
			bK = {},
			bM,
			bu,
			bH,
			bA,
			bE,
			bw = 0,
			bs,
			bG,
			bF = {
				readyState: 0,
				setRequestHeader: function(bP, bQ) {
					if (!bw) {
						var e = bP.toLowerCase();
						bP = bK[e] = bK[e] || bP;
						bD[bP] = bQ
					}
					return this
				},
				getAllResponseHeaders: function() {
					return bw === 2 ? bM: null
				},
				getResponseHeader: function(bP) {
					var e;
					if (bw === 2) {
						if (!bu) {
							bu = {};
							while ((e = ay.exec(bM))) {
								bu[e[1].toLowerCase()] = e[2]
							}
						}
						e = bu[bP.toLowerCase()]
					}
					return e === K ? null: e
				},
				overrideMimeType: function(e) {
					if (!bw) {
						bz.mimeType = e
					}
					return this
				},
				abort: function(e) {
					e = e || "abort";
					if (bH) {
						bH.abort(e)
					}
					bB(0, e);
					return this
				}
			};
			function bB(bV, bQ, bW, bS) {
				if (bw === 2) {
					return
				}
				bw = 2;
				if (bA) {
					clearTimeout(bA)
				}
				bH = K;
				bM = bS || "";
				bF.readyState = bV > 0 ? 4 : 0;
				var bP, b0, bZ, bT = bQ,
				bU = bW ? bf(bz, bF, bW) : K,
				bR,
				bY;
				if (bV >= 200 && bV < 300 || bV === 304) {
					if (bz.ifModified) {
						if ((bR = bF.getResponseHeader("Last-Modified"))) {
							b.lastModified[by] = bR
						}
						if ((bY = bF.getResponseHeader("Etag"))) {
							b.etag[by] = bY
						}
					}
					if (bV === 304) {
						bT = "notmodified";
						bP = true
					} else {
						try {
							b0 = F(bz, bU);
							bT = "success";
							bP = true
						} catch(bX) {
							bT = "parsererror";
							bZ = bX
						}
					}
				} else {
					bZ = bT;
					if (!bT || bV) {
						bT = "error";
						if (bV < 0) {
							bV = 0
						}
					}
				}
				bF.status = bV;
				bF.statusText = "" + (bQ || bT);
				if (bP) {
					bN.resolveWith(bO, [b0, bT, bF])
				} else {
					bN.rejectWith(bO, [bF, bT, bZ])
				}
				bF.statusCode(bx);
				bx = K;
				if (bs) {
					bC.trigger("ajax" + (bP ? "Success": "Error"), [bF, bz, bP ? b0: bZ])
				}
				bJ.resolveWith(bO, [bF, bT]);
				if (bs) {
					bC.trigger("ajaxComplete", [bF, bz]);
					if (! (--b.active)) {
						b.event.trigger("ajaxStop")
					}
				}
			}
			bN.promise(bF);
			bF.success = bF.done;
			bF.error = bF.fail;
			bF.complete = bJ.done;
			bF.statusCode = function(bP) {
				if (bP) {
					var e;
					if (bw < 2) {
						for (e in bP) {
							bx[e] = [bx[e], bP[e]]
						}
					} else {
						e = bP[bF.status];
						bF.then(e, e)
					}
				}
				return this
			};
			bz.url = ((bv || bz.url) + "").replace(bn, "").replace(c, s[1] + "//");
			bz.dataTypes = b.trim(bz.dataType || "*").toLowerCase().split(h);
			if (bz.crossDomain == null) {
				bE = J.exec(bz.url.toLowerCase());
				bz.crossDomain = !!(bE && (bE[1] != s[1] || bE[2] != s[2] || (bE[3] || (bE[1] === "http:" ? 80 : 443)) != (s[3] || (s[1] === "http:" ? 80 : 443))))
			}
			if (bz.data && bz.processData && typeof bz.data !== "string") {
				bz.data = b.param(bz.data, bz.traditional)
			}
			aQ(W, bz, bt, bF);
			if (bw === 2) {
				return false
			}
			bs = bz.global;
			bz.type = bz.type.toUpperCase();
			bz.hasContent = !aK.test(bz.type);
			if (bs && b.active++===0) {
				b.event.trigger("ajaxStart")
			}
			if (!bz.hasContent) {
				if (bz.data) {
					bz.url += (L.test(bz.url) ? "&": "?") + bz.data;
					delete bz.data
				}
				by = bz.url;
				if (bz.cache === false) {
					var br = b.now(),
					bL = bz.url.replace(bo, "$1_=" + br);
					bz.url = bL + ((bL === bz.url) ? (L.test(bz.url) ? "&": "?") + "_=" + br: "")
				}
			}
			if (bz.data && bz.hasContent && bz.contentType !== false || bt.contentType) {
				bF.setRequestHeader("Content-Type", bz.contentType)
			}
			if (bz.ifModified) {
				by = by || bz.url;
				if (b.lastModified[by]) {
					bF.setRequestHeader("If-Modified-Since", b.lastModified[by])
				}
				if (b.etag[by]) {
					bF.setRequestHeader("If-None-Match", b.etag[by])
				}
			}
			bF.setRequestHeader("Accept", bz.dataTypes[0] && bz.accepts[bz.dataTypes[0]] ? bz.accepts[bz.dataTypes[0]] + (bz.dataTypes[0] !== "*" ? ", " + aO + "; q=0.01": "") : bz.accepts["*"]);
			for (bG in bz.headers) {
				bF.setRequestHeader(bG, bz.headers[bG])
			}
			if (bz.beforeSend && (bz.beforeSend.call(bO, bF, bz) === false || bw === 2)) {
				bF.abort();
				return false
			}
			for (bG in {
				success: 1,
				error: 1,
				complete: 1
			}) {
				bF[bG](bz[bG])
			}
			bH = aQ(r, bz, bt, bF);
			if (!bH) {
				bB( - 1, "No Transport")
			} else {
				bF.readyState = 1;
				if (bs) {
					bC.trigger("ajaxSend", [bF, bz])
				}
				if (bz.async && bz.timeout > 0) {
					bA = setTimeout(function() {
						bF.abort("timeout")
					},
					bz.timeout)
				}
				try {
					bw = 1;
					bH.send(bD, bB)
				} catch(bI) {
					if (bw < 2) {
						bB( - 1, bI)
					} else {
						b.error(bI)
					}
				}
			}
			return bF
		},
		param: function(e, bs) {
			var br = [],
			bu = function(bv, bw) {
				bw = b.isFunction(bw) ? bw() : bw;
				br[br.length] = encodeURIComponent(bv) + "=" + encodeURIComponent(bw)
			};
			if (bs === K) {
				bs = b.ajaxSettings.traditional
			}
			if (b.isArray(e) || (e.jquery && !b.isPlainObject(e))) {
				b.each(e,
				function() {
					bu(this.name, this.value)
				})
			} else {
				for (var bt in e) {
					v(bt, e[bt], bs, bu)
				}
			}
			return br.join("&").replace(j, "+")
		}
	});
	function v(bs, bu, br, bt) {
		if (b.isArray(bu)) {
			b.each(bu,
			function(bw, bv) {
				if (br || ak.test(bs)) {
					bt(bs, bv)
				} else {
					v(bs + "[" + (typeof bv === "object" || b.isArray(bv) ? bw: "") + "]", bv, br, bt)
				}
			})
		} else {
			if (!br && bu != null && typeof bu === "object") {
				for (var e in bu) {
					v(bs + "[" + e + "]", bu[e], br, bt)
				}
			} else {
				bt(bs, bu)
			}
		}
	}
	b.extend({
		active: 0,
		lastModified: {},
		etag: {}
	});
	function bf(bz, by, bv) {
		var br = bz.contents,
		bx = bz.dataTypes,
		bs = bz.responseFields,
		bu, bw, bt, e;
		for (bw in bs) {
			if (bw in bv) {
				by[bs[bw]] = bv[bw]
			}
		}
		while (bx[0] === "*") {
			bx.shift();
			if (bu === K) {
				bu = bz.mimeType || by.getResponseHeader("content-type")
			}
		}
		if (bu) {
			for (bw in br) {
				if (br[bw] && br[bw].test(bu)) {
					bx.unshift(bw);
					break
				}
			}
		}
		if (bx[0] in bv) {
			bt = bx[0]
		} else {
			for (bw in bv) {
				if (!bx[0] || bz.converters[bw + " " + bx[0]]) {
					bt = bw;
					break
				}
				if (!e) {
					e = bw
				}
			}
			bt = bt || e
		}
		if (bt) {
			if (bt !== bx[0]) {
				bx.unshift(bt)
			}
			return bv[bt]
		}
	}
	function F(bD, bv) {
		if (bD.dataFilter) {
			bv = bD.dataFilter(bv, bD.dataType)
		}
		var bz = bD.dataTypes,
		bC = {},
		bw, bA, bs = bz.length,
		bx, by = bz[0],
		bt,
		bu,
		bB,
		br,
		e;
		for (bw = 1; bw < bs; bw++) {
			if (bw === 1) {
				for (bA in bD.converters) {
					if (typeof bA === "string") {
						bC[bA.toLowerCase()] = bD.converters[bA]
					}
				}
			}
			bt = by;
			by = bz[bw];
			if (by === "*") {
				by = bt
			} else {
				if (bt !== "*" && bt !== by) {
					bu = bt + " " + by;
					bB = bC[bu] || bC["* " + by];
					if (!bB) {
						e = K;
						for (br in bC) {
							bx = br.split(" ");
							if (bx[0] === bt || bx[0] === "*") {
								e = bC[bx[1] + " " + by];
								if (e) {
									br = bC[br];
									if (br === true) {
										bB = e
									} else {
										if (e === true) {
											bB = br
										}
									}
									break
								}
							}
						}
					}
					if (! (bB || e)) {
						b.error("No conversion from " + bu.replace(" ", " to "))
					}
					if (bB !== true) {
						bv = bB ? bB(bv) : e(br(bv))
					}
				}
			}
		}
		return bv
	}
	var ax = b.now(),
	u = /(\=)\?(&|$)|\?\?/i;
	b.ajaxSetup({
		jsonp: "callback",
		jsonpCallback: function() {
			return b.expando + "_" + (ax++)
		}
	});
	b.ajaxPrefilter("json jsonp",
	function(bz, bw, by) {
		var bt = bz.contentType === "application/x-www-form-urlencoded" && (typeof bz.data === "string");
		if (bz.dataTypes[0] === "jsonp" || bz.jsonp !== false && (u.test(bz.url) || bt && u.test(bz.data))) {
			var bx, bs = bz.jsonpCallback = b.isFunction(bz.jsonpCallback) ? bz.jsonpCallback() : bz.jsonpCallback,
			bv = a7[bs],
			e = bz.url,
			bu = bz.data,
			br = "$1" + bs + "$2";
			if (bz.jsonp !== false) {
				e = e.replace(u, br);
				if (bz.url === e) {
					if (bt) {
						bu = bu.replace(u, br)
					}
					if (bz.data === bu) {
						e += (/\?/.test(e) ? "&": "?") + bz.jsonp + "=" + bs
					}
				}
			}
			bz.url = e;
			bz.data = bu;
			a7[bs] = function(bA) {
				bx = [bA]
			};
			by.always(function() {
				a7[bs] = bv;
				if (bx && b.isFunction(bv)) {
					a7[bs](bx[0])
				}
			});
			bz.converters["script json"] = function() {
				if (!bx) {
					b.error(bs + " was not called")
				}
				return bx[0]
			};
			bz.dataTypes[0] = "json";
			return "script"
		}
	});
	b.ajaxSetup({
		accepts: {
			script: "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript"
		},
		contents: {
			script: /javascript|ecmascript/
		},
		converters: {
			"text script": function(e) {
				b.globalEval(e);
				return e
			}
		}
	});
	b.ajaxPrefilter("script",
	function(e) {
		if (e.cache === K) {
			e.cache = false
		}
		if (e.crossDomain) {
			e.type = "GET";
			e.global = false
		}
	});
	b.ajaxTransport("script",
	function(bs) {
		if (bs.crossDomain) {
			var e, br = ap.head || ap.getElementsByTagName("head")[0] || ap.documentElement;
			return {
				send: function(bt, bu) {
					e = ap.createElement("script");
					e.async = "async";
					if (bs.scriptCharset) {
						e.charset = bs.scriptCharset
					}
					e.src = bs.url;
					e.onload = e.onreadystatechange = function(bw, bv) {
						if (bv || !e.readyState || /loaded|complete/.test(e.readyState)) {
							e.onload = e.onreadystatechange = null;
							if (br && e.parentNode) {
								br.removeChild(e)
							}
							e = K;
							if (!bv) {
								bu(200, "success")
							}
						}
					};
					br.insertBefore(e, br.firstChild)
				},
				abort: function() {
					if (e) {
						e.onload(0, 1)
					}
				}
			}
		}
	});
	var B = a7.ActiveXObject ?
	function() {
		for (var e in M) {
			M[e](0, 1)
		}
	}: false,
	y = 0,
	M;
	function aG() {
		try {
			return new a7.XMLHttpRequest()
		} catch(br) {}
	}
	function ae() {
		try {
			return new a7.ActiveXObject("Microsoft.XMLHTTP")
		} catch(br) {}
	}
	b.ajaxSettings.xhr = a7.ActiveXObject ?
	function() {
		return ! this.isLocal && aG() || ae()
	}: aG; (function(e) {
		b.extend(b.support, {
			ajax: !!e,
			cors: !!e && ("withCredentials" in e)
		})
	})(b.ajaxSettings.xhr());
	if (b.support.ajax) {
		b.ajaxTransport(function(e) {
			if (!e.crossDomain || b.support.cors) {
				var br;
				return {
					send: function(bx, bs) {
						var bw = e.xhr(),
						bv,
						bu;
						if (e.username) {
							bw.open(e.type, e.url, e.async, e.username, e.password)
						} else {
							bw.open(e.type, e.url, e.async)
						}
						if (e.xhrFields) {
							for (bu in e.xhrFields) {
								bw[bu] = e.xhrFields[bu]
							}
						}
						if (e.mimeType && bw.overrideMimeType) {
							bw.overrideMimeType(e.mimeType)
						}
						if (!e.crossDomain && !bx["X-Requested-With"]) {
							bx["X-Requested-With"] = "XMLHttpRequest"
						}
						try {
							for (bu in bx) {
								bw.setRequestHeader(bu, bx[bu])
							}
						} catch(bt) {}
						bw.send((e.hasContent && e.data) || null);
						br = function(bG, bA) {
							var bB, bz, by, bE, bD;
							try {
								if (br && (bA || bw.readyState === 4)) {
									br = K;
									if (bv) {
										bw.onreadystatechange = b.noop;
										if (B) {
											delete M[bv]
										}
									}
									if (bA) {
										if (bw.readyState !== 4) {
											bw.abort()
										}
									} else {
										bB = bw.status;
										by = bw.getAllResponseHeaders();
										bE = {};
										bD = bw.responseXML;
										if (bD && bD.documentElement) {
											bE.xml = bD
										}
										bE.text = bw.responseText;
										try {
											bz = bw.statusText
										} catch(bF) {
											bz = ""
										}
										if (!bB && e.isLocal && !e.crossDomain) {
											bB = bE.text ? 200 : 404
										} else {
											if (bB === 1223) {
												bB = 204
											}
										}
									}
								}
							} catch(bC) {
								if (!bA) {
									bs( - 1, bC)
								}
							}
							if (bE) {
								bs(bB, bz, bE, by)
							}
						};
						if (!e.async || bw.readyState === 4) {
							br()
						} else {
							bv = ++y;
							if (B) {
								if (!M) {
									M = {};
									b(a7).unload(B)
								}
								M[bv] = br
							}
							bw.onreadystatechange = br
						}
					},
					abort: function() {
						if (br) {
							br(0, 1)
						}
					}
				}
			}
		})
	}
	var Q = {},
	a4, m, aw = /^(?:toggle|show|hide)$/,
	aM = /^([+\-]=)?([\d+.\-]+)([a-z%]*)$/i,
	aZ, aC = [["height", "marginTop", "marginBottom", "paddingTop", "paddingBottom"], ["width", "marginLeft", "marginRight", "paddingLeft", "paddingRight"], ["opacity"]],
	a0;
	b.fn.extend({
		show: function(bt, bw, bv) {
			var bs, bu;
			if (bt || bt === 0) {
				return this.animate(aX("show", 3), bt, bw, bv)
			} else {
				for (var br = 0,
				e = this.length; br < e; br++) {
					bs = this[br];
					if (bs.style) {
						bu = bs.style.display;
						if (!b._data(bs, "olddisplay") && bu === "none") {
							bu = bs.style.display = ""
						}
						if (bu === "" && b.css(bs, "display") === "none") {
							b._data(bs, "olddisplay", x(bs.nodeName))
						}
					}
				}
				for (br = 0; br < e; br++) {
					bs = this[br];
					if (bs.style) {
						bu = bs.style.display;
						if (bu === "" || bu === "none") {
							bs.style.display = b._data(bs, "olddisplay") || ""
						}
					}
				}
				return this
			}
		},
		hide: function(bs, bv, bu) {
			if (bs || bs === 0) {
				return this.animate(aX("hide", 3), bs, bv, bu)
			} else {
				for (var br = 0,
				e = this.length; br < e; br++) {
					if (this[br].style) {
						var bt = b.css(this[br], "display");
						if (bt !== "none" && !b._data(this[br], "olddisplay")) {
							b._data(this[br], "olddisplay", bt)
						}
					}
				}
				for (br = 0; br < e; br++) {
					if (this[br].style) {
						this[br].style.display = "none"
					}
				}
				return this
			}
		},
		_toggle: b.fn.toggle,
		toggle: function(bs, br, bt) {
			var e = typeof bs === "boolean";
			if (b.isFunction(bs) && b.isFunction(br)) {
				this._toggle.apply(this, arguments)
			} else {
				if (bs == null || e) {
					this.each(function() {
						var bu = e ? bs: b(this).is(":hidden");
						b(this)[bu ? "show": "hide"]()
					})
				} else {
					this.animate(aX("toggle", 3), bs, br, bt)
				}
			}
			return this
		},
		fadeTo: function(e, bt, bs, br) {
			return this.filter(":hidden").css("opacity", 0).show().end().animate({
				opacity: bt
			},
			e, bs, br)
		},
		animate: function(bu, br, bt, bs) {
			var e = b.speed(br, bt, bs);
			if (b.isEmptyObject(bu)) {
				return this.each(e.complete, [false])
			}
			bu = b.extend({},
			bu);
			return this[e.queue === false ? "each": "queue"](function() {
				if (e.queue === false) {
					b._mark(this)
				}
				var by = b.extend({},
				e),
				bF = this.nodeType === 1,
				bC = bF && b(this).is(":hidden"),
				bv,
				bz,
				bx,
				bE,
				bD,
				bB,
				bw,
				bA,
				bG;
				by.animatedProperties = {};
				for (bx in bu) {
					bv = b.camelCase(bx);
					if (bx !== bv) {
						bu[bv] = bu[bx];
						delete bu[bx]
					}
					bz = bu[bv];
					if (b.isArray(bz)) {
						by.animatedProperties[bv] = bz[1];
						bz = bu[bv] = bz[0]
					} else {
						by.animatedProperties[bv] = by.specialEasing && by.specialEasing[bv] || by.easing || "swing"
					}
					if (bz === "hide" && bC || bz === "show" && !bC) {
						return by.complete.call(this)
					}
					if (bF && (bv === "height" || bv === "width")) {
						by.overflow = [this.style.overflow, this.style.overflowX, this.style.overflowY];
						if (b.css(this, "display") === "inline" && b.css(this, "float") === "none") {
							if (!b.support.inlineBlockNeedsLayout) {
								this.style.display = "inline-block"
							} else {
								bE = x(this.nodeName);
								if (bE === "inline") {
									this.style.display = "inline-block"
								} else {
									this.style.display = "inline";
									this.style.zoom = 1
								}
							}
						}
					}
				}
				if (by.overflow != null) {
					this.style.overflow = "hidden"
				}
				for (bx in bu) {
					bD = new b.fx(this, by, bx);
					bz = bu[bx];
					if (aw.test(bz)) {
						bD[bz === "toggle" ? bC ? "show": "hide": bz]()
					} else {
						bB = aM.exec(bz);
						bw = bD.cur();
						if (bB) {
							bA = parseFloat(bB[2]);
							bG = bB[3] || (b.cssNumber[bx] ? "": "px");
							if (bG !== "px") {
								b.style(this, bx, (bA || 1) + bG);
								bw = ((bA || 1) / bD.cur()) * bw;
								b.style(this, bx, bw + bG)
							}
							if (bB[1]) {
								bA = ((bB[1] === "-=" ? -1 : 1) * bA) + bw
							}
							bD.custom(bw, bA, bG)
						} else {
							bD.custom(bw, bz, "")
						}
					}
				}
				return true
			})
		},
		stop: function(br, e) {
			if (br) {
				this.queue([])
			}
			this.each(function() {
				var bt = b.timers,
				bs = bt.length;
				if (!e) {
					b._unmark(true, this)
				}
				while (bs--) {
					if (bt[bs].elem === this) {
						if (e) {
							bt[bs](true)
						}
						bt.splice(bs, 1)
					}
				}
			});
			if (!e) {
				this.dequeue()
			}
			return this
		}
	});
	function bc() {
		setTimeout(an, 0);
		return (a0 = b.now())
	}
	function an() {
		a0 = K
	}
	function aX(br, e) {
		var bs = {};
		b.each(aC.concat.apply([], aC.slice(0, e)),
		function() {
			bs[this] = br
		});
		return bs
	}
	b.each({
		slideDown: aX("show", 1),
		slideUp: aX("hide", 1),
		slideToggle: aX("toggle", 1),
		fadeIn: {
			opacity: "show"
		},
		fadeOut: {
			opacity: "hide"
		},
		fadeToggle: {
			opacity: "toggle"
		}
	},
	function(e, br) {
		b.fn[e] = function(bs, bu, bt) {
			return this.animate(br, bs, bu, bt)
		}
	});
	b.extend({
		speed: function(bs, bt, br) {
			var e = bs && typeof bs === "object" ? b.extend({},
			bs) : {
				complete: br || !br && bt || b.isFunction(bs) && bs,
				duration: bs,
				easing: br && bt || bt && !b.isFunction(bt) && bt
			};
			e.duration = b.fx.off ? 0 : typeof e.duration === "number" ? e.duration: e.duration in b.fx.speeds ? b.fx.speeds[e.duration] : b.fx.speeds._default;
			e.old = e.complete;
			e.complete = function(bu) {
				if (b.isFunction(e.old)) {
					e.old.call(this)
				}
				if (e.queue !== false) {
					b.dequeue(this)
				} else {
					if (bu !== false) {
						b._unmark(this)
					}
				}
			};
			return e
		},
		easing: {
			linear: function(bs, bt, e, br) {
				return e + br * bs
			},
			swing: function(bs, bt, e, br) {
				return (( - Math.cos(bs * Math.PI) / 2) + 0.5) * br + e
			}
		},
		timers: [],
		fx: function(br, e, bs) {
			this.options = e;
			this.elem = br;
			this.prop = bs;
			e.orig = e.orig || {}
		}
	});
	b.fx.prototype = {
		update: function() {
			if (this.options.step) {
				this.options.step.call(this.elem, this.now, this)
			} (b.fx.step[this.prop] || b.fx.step._default)(this)
		},
		cur: function() {
			if (this.elem[this.prop] != null && (!this.elem.style || this.elem.style[this.prop] == null)) {
				return this.elem[this.prop]
			}
			var e, br = b.css(this.elem, this.prop);
			return isNaN(e = parseFloat(br)) ? !br || br === "auto" ? 0 : br: e
		},
		custom: function(bv, bu, bt) {
			var e = this,
			bs = b.fx;
			this.startTime = a0 || bc();
			this.start = bv;
			this.end = bu;
			this.unit = bt || this.unit || (b.cssNumber[this.prop] ? "": "px");
			this.now = this.start;
			this.pos = this.state = 0;
			function br(bw) {
				return e.step(bw)
			}
			br.elem = this.elem;
			if (br() && b.timers.push(br) && !aZ) {
				aZ = setInterval(bs.tick, bs.interval)
			}
		},
		show: function() {
			this.options.orig[this.prop] = b.style(this.elem, this.prop);
			this.options.show = true;
			this.custom(this.prop === "width" || this.prop === "height" ? 1 : 0, this.cur());
			b(this.elem).show()
		},
		hide: function() {
			this.options.orig[this.prop] = b.style(this.elem, this.prop);
			this.options.hide = true;
			this.custom(this.cur(), 0)
		},
		step: function(bu) {
			var bt = a0 || bc(),
			e = true,
			bv = this.elem,
			br = this.options,
			bs,
			bx;
			if (bu || bt >= br.duration + this.startTime) {
				this.now = this.end;
				this.pos = this.state = 1;
				this.update();
				br.animatedProperties[this.prop] = true;
				for (bs in br.animatedProperties) {
					if (br.animatedProperties[bs] !== true) {
						e = false
					}
				}
				if (e) {
					if (br.overflow != null && !b.support.shrinkWrapBlocks) {
						b.each(["", "X", "Y"],
						function(by, bz) {
							bv.style["overflow" + bz] = br.overflow[by]
						})
					}
					if (br.hide) {
						b(bv).hide()
					}
					if (br.hide || br.show) {
						for (var bw in br.animatedProperties) {
							b.style(bv, bw, br.orig[bw])
						}
					}
					br.complete.call(bv)
				}
				return false
			} else {
				if (br.duration == Infinity) {
					this.now = bt
				} else {
					bx = bt - this.startTime;
					this.state = bx / br.duration;
					this.pos = b.easing[br.animatedProperties[this.prop]](this.state, bx, 0, 1, br.duration);
					this.now = this.start + ((this.end - this.start) * this.pos)
				}
				this.update()
			}
			return true
		}
	};
	b.extend(b.fx, {
		tick: function() {
			for (var br = b.timers,
			e = 0; e < br.length; ++e) {
				if (!br[e]()) {
					br.splice(e--, 1)
				}
			}
			if (!br.length) {
				b.fx.stop()
			}
		},
		interval: 13,
		stop: function() {
			clearInterval(aZ);
			aZ = null
		},
		speeds: {
			slow: 600,
			fast: 200,
			_default: 400
		},
		step: {
			opacity: function(e) {
				b.style(e.elem, "opacity", e.now)
			},
			_default: function(e) {
				if (e.elem.style && e.elem.style[e.prop] != null) {
					e.elem.style[e.prop] = (e.prop === "width" || e.prop === "height" ? Math.max(0, e.now) : e.now) + e.unit
				} else {
					e.elem[e.prop] = e.now
				}
			}
		}
	});
	if (b.expr && b.expr.filters) {
		b.expr.filters.animated = function(e) {
			return b.grep(b.timers,
			function(br) {
				return e === br.elem
			}).length
		}
	}
	function x(bt) {
		if (!Q[bt]) {
			var e = ap.body,
			br = b("<" + bt + ">").appendTo(e),
			bs = br.css("display");
			br.remove();
			if (bs === "none" || bs === "") {
				if (!a4) {
					a4 = ap.createElement("iframe");
					a4.frameBorder = a4.width = a4.height = 0
				}
				e.appendChild(a4);
				if (!m || !a4.createElement) {
					m = (a4.contentWindow || a4.contentDocument).document;
					m.write((ap.compatMode === "CSS1Compat" ? "<!doctype html>": "") + "<html><body>");
					m.close()
				}
				br = m.createElement(bt);
				m.body.appendChild(br);
				bs = b.css(br, "display");
				e.removeChild(a4)
			}
			Q[bt] = bs
		}
		return Q[bt]
	}
	var T = /^t(?:able|d|h)$/i,
	Z = /^(?:body|html)$/i;
	if ("getBoundingClientRect" in ap.documentElement) {
		b.fn.offset = function(bE) {
			var bu = this[0],
			bx;
			if (bE) {
				return this.each(function(e) {
					b.offset.setOffset(this, bE, e)
				})
			}
			if (!bu || !bu.ownerDocument) {
				return null
			}
			if (bu === bu.ownerDocument.body) {
				return b.offset.bodyOffset(bu)
			}
			try {
				bx = bu.getBoundingClientRect()
			} catch(bB) {}
			var bD = bu.ownerDocument,
			bs = bD.documentElement;
			if (!bx || !b.contains(bs, bu)) {
				return bx ? {
					top: bx.top,
					left: bx.left
				}: {
					top: 0,
					left: 0
				}
			}
			var by = bD.body,
			bz = aF(bD),
			bw = bs.clientTop || by.clientTop || 0,
			bA = bs.clientLeft || by.clientLeft || 0,
			br = bz.pageYOffset || b.support.boxModel && bs.scrollTop || by.scrollTop,
			bv = bz.pageXOffset || b.support.boxModel && bs.scrollLeft || by.scrollLeft,
			bC = bx.top + br - bw,
			bt = bx.left + bv - bA;
			return {
				top: bC,
				left: bt
			}
		}
	} else {
		b.fn.offset = function(bB) {
			var bv = this[0];
			if (bB) {
				return this.each(function(bC) {
					b.offset.setOffset(this, bB, bC)
				})
			}
			if (!bv || !bv.ownerDocument) {
				return null
			}
			if (bv === bv.ownerDocument.body) {
				return b.offset.bodyOffset(bv)
			}
			b.offset.initialize();
			var by, bs = bv.offsetParent,
			br = bv,
			bA = bv.ownerDocument,
			bt = bA.documentElement,
			bw = bA.body,
			bx = bA.defaultView,
			e = bx ? bx.getComputedStyle(bv, null) : bv.currentStyle,
			bz = bv.offsetTop,
			bu = bv.offsetLeft;
			while ((bv = bv.parentNode) && bv !== bw && bv !== bt) {
				if (b.offset.supportsFixedPosition && e.position === "fixed") {
					break
				}
				by = bx ? bx.getComputedStyle(bv, null) : bv.currentStyle;
				bz -= bv.scrollTop;
				bu -= bv.scrollLeft;
				if (bv === bs) {
					bz += bv.offsetTop;
					bu += bv.offsetLeft;
					if (b.offset.doesNotAddBorder && !(b.offset.doesAddBorderForTableAndCells && T.test(bv.nodeName))) {
						bz += parseFloat(by.borderTopWidth) || 0;
						bu += parseFloat(by.borderLeftWidth) || 0
					}
					br = bs;
					bs = bv.offsetParent
				}
				if (b.offset.subtractsBorderForOverflowNotVisible && by.overflow !== "visible") {
					bz += parseFloat(by.borderTopWidth) || 0;
					bu += parseFloat(by.borderLeftWidth) || 0
				}
				e = by
			}
			if (e.position === "relative" || e.position === "static") {
				bz += bw.offsetTop;
				bu += bw.offsetLeft
			}
			if (b.offset.supportsFixedPosition && e.position === "fixed") {
				bz += Math.max(bt.scrollTop, bw.scrollTop);
				bu += Math.max(bt.scrollLeft, bw.scrollLeft)
			}
			return {
				top: bz,
				left: bu
			}
		}
	}
	b.offset = {
		initialize: function() {
			var e = ap.body,
			br = ap.createElement("div"),
			bu,
			bw,
			bv,
			bx,
			bs = parseFloat(b.css(e, "marginTop")) || 0,
			bt = "<div style='position:absolute;top:0;left:0;margin:0;border:5px solid #000;padding:0;width:1px;height:1px;'><div></div></div><table style='position:absolute;top:0;left:0;margin:0;border:5px solid #000;padding:0;width:1px;height:1px;' cellpadding='0' cellspacing='0'><tr><td></td></tr></table>";
			b.extend(br.style, {
				position: "absolute",
				top: 0,
				left: 0,
				margin: 0,
				border: 0,
				width: "1px",
				height: "1px",
				visibility: "hidden"
			});
			br.innerHTML = bt;
			e.insertBefore(br, e.firstChild);
			bu = br.firstChild;
			bw = bu.firstChild;
			bx = bu.nextSibling.firstChild.firstChild;
			this.doesNotAddBorder = (bw.offsetTop !== 5);
			this.doesAddBorderForTableAndCells = (bx.offsetTop === 5);
			bw.style.position = "fixed";
			bw.style.top = "20px";
			this.supportsFixedPosition = (bw.offsetTop === 20 || bw.offsetTop === 15);
			bw.style.position = bw.style.top = "";
			bu.style.overflow = "hidden";
			bu.style.position = "relative";
			this.subtractsBorderForOverflowNotVisible = (bw.offsetTop === -5);
			this.doesNotIncludeMarginInBodyOffset = (e.offsetTop !== bs);
			e.removeChild(br);
			b.offset.initialize = b.noop
		},
		bodyOffset: function(e) {
			var bs = e.offsetTop,
			br = e.offsetLeft;
			b.offset.initialize();
			if (b.offset.doesNotIncludeMarginInBodyOffset) {
				bs += parseFloat(b.css(e, "marginTop")) || 0;
				br += parseFloat(b.css(e, "marginLeft")) || 0
			}
			return {
				top: bs,
				left: br
			}
		},
		setOffset: function(bt, bC, bw) {
			var bx = b.css(bt, "position");
			if (bx === "static") {
				bt.style.position = "relative"
			}
			var bv = b(bt),
			br = bv.offset(),
			e = b.css(bt, "top"),
			bA = b.css(bt, "left"),
			bB = (bx === "absolute" || bx === "fixed") && b.inArray("auto", [e, bA]) > -1,
			bz = {},
			by = {},
			bs,
			bu;
			if (bB) {
				by = bv.position();
				bs = by.top;
				bu = by.left
			} else {
				bs = parseFloat(e) || 0;
				bu = parseFloat(bA) || 0
			}
			if (b.isFunction(bC)) {
				bC = bC.call(bt, bw, br)
			}
			if (bC.top != null) {
				bz.top = (bC.top - br.top) + bs
			}
			if (bC.left != null) {
				bz.left = (bC.left - br.left) + bu
			}
			if ("using" in bC) {
				bC.using.call(bt, bz)
			} else {
				bv.css(bz)
			}
		}
	};
	b.fn.extend({
		position: function() {
			if (!this[0]) {
				return null
			}
			var bs = this[0],
			br = this.offsetParent(),
			bt = this.offset(),
			e = (!br[0] || Z.test(br[0].nodeName)) ? {
				top: 0,
				left: 0
			}: br.offset();
			bt.top -= parseFloat(b.css(bs, "marginTop")) || 0;
			bt.left -= parseFloat(b.css(bs, "marginLeft")) || 0;
			e.top += parseFloat(b.css(br[0], "borderTopWidth")) || 0;
			e.left += parseFloat(b.css(br[0], "borderLeftWidth")) || 0;
			return {
				top: bt.top - e.top,
				left: bt.left - e.left
			}
		},
		offsetParent: function() {
			return this.map(function() {
				var e = this.offsetParent || ap.body;
				while (e && (!Z.test(e.nodeName) && b.css(e, "position") === "static")) {
					e = e.offsetParent
				}
				return e
			})
		}
	});
	b.each(["Left", "Top"],
	function(br, e) {
		var bs = "scroll" + e;
		b.fn[bs] = function(bv) {
			var bt, bu;
			if (bv === K) {
				bt = this[0];
				if (!bt) {
					return null
				}
				bu = aF(bt);
				return bu ? ("pageXOffset" in bu) ? bu[br ? "pageYOffset": "pageXOffset"] : b.support.boxModel && bu.document.documentElement[bs] || bu.document.body[bs] : bt[bs]
			}
			return this.each(function() {
				bu = aF(this);
				if (bu) {
					bu.scrollTo(!br ? bv: b(bu).scrollLeft(), br ? bv: b(bu).scrollTop())
				} else {
					this[bs] = bv
				}
			})
		}
	});
	function aF(e) {
		return b.isWindow(e) ? e: e.nodeType === 9 ? e.defaultView || e.parentWindow: false
	}
	b.each(["Height", "Width"],
	function(br, e) {
		var bs = e.toLowerCase();
		b.fn["inner" + e] = function() {
			var bt = this[0];
			return bt && bt.style ? parseFloat(b.css(bt, bs, "padding")) : null
		};
		b.fn["outer" + e] = function(bu) {
			var bt = this[0];
			return bt && bt.style ? parseFloat(b.css(bt, bs, bu ? "margin": "border")) : null
		};
		b.fn[bs] = function(bv) {
			var bw = this[0];
			if (!bw) {
				return bv == null ? null: this
			}
			if (b.isFunction(bv)) {
				return this.each(function(bA) {
					var bz = b(this);
					bz[bs](bv.call(this, bA, bz[bs]()))
				})
			}
			if (b.isWindow(bw)) {
				var bx = bw.document.documentElement["client" + e],
				bt = bw.document.body;
				return bw.document.compatMode === "CSS1Compat" && bx || bt && bt["client" + e] || bx
			} else {
				if (bw.nodeType === 9) {
					return Math.max(bw.documentElement["client" + e], bw.body["scroll" + e], bw.documentElement["scroll" + e], bw.body["offset" + e], bw.documentElement["offset" + e])
				} else {
					if (bv === K) {
						var by = b.css(bw, bs),
						bu = parseFloat(by);
						return b.isNaN(bu) ? by: bu
					} else {
						return this.css(bs, typeof bv === "string" ? bv: bv + "px")
					}
				}
			}
		}
	});
	a7.jQuery = a7.$ = b
})(window); (function(b) {
	b.fn.__bind__ = b.fn.bind;
	b.fn.__unbind__ = b.fn.unbind;
	b.fn.__find__ = b.fn.find;
	var a = {
		version: "0.7.9",
		override: /keypress|keydown|keyup/g,
		triggersMap: {},
		specialKeys: {
			27 : "esc",
			9 : "tab",
			32 : "space",
			13 : "enter",
			8 : "backspace",
			145 : "scroll",
			20 : "capslock",
			144 : "numlock",
			19 : "pause",
			45 : "insert",
			36 : "home",
			46 : "del",
			35 : "end",
			33 : "pageup",
			34 : "pagedown",
			37 : "left",
			38 : "up",
			39 : "right",
			40 : "down",
			109 : "-",
			112 : "f1",
			113 : "f2",
			114 : "f3",
			115 : "f4",
			116 : "f5",
			117 : "f6",
			118 : "f7",
			119 : "f8",
			120 : "f9",
			121 : "f10",
			122 : "f11",
			123 : "f12",
			191 : "/"
		},
		shiftNums: {
			"`": "~",
			"1": "!",
			"2": "@",
			"3": "#",
			"4": "$",
			"5": "%",
			"6": "^",
			"7": "&",
			"8": "*",
			"9": "(",
			"0": ")",
			"-": "_",
			"=": "+",
			";": ":",
			"'": '"',
			",": "<",
			".": ">",
			"/": "?",
			"\\": "|"
		},
		newTrigger: function(e, d, f) {
			var c = {};
			c[e] = {};
			c[e][d] = {
				cb: f,
				disableInInput: false
			};
			return c
		}
	};
	a.specialKeys = b.extend(a.specialKeys, {
		96 : "0",
		97 : "1",
		98 : "2",
		99 : "3",
		100 : "4",
		101 : "5",
		102 : "6",
		103 : "7",
		104 : "8",
		105 : "9",
		106 : "*",
		107 : "+",
		109 : "-",
		110 : ".",
		111 : "/"
	});
	b.fn.find = function(c) {
		this.query = c;
		return b.fn.__find__.apply(this, arguments)
	};
	b.fn.unHotbind = function(h, e, g) {
		if (b.isFunction(e)) {
			g = e;
			e = null
		}
		if (e && typeof e === "string") {
			var f = ((this.prevObject && this.prevObject.query) || (this[0].id && this[0].id) || this[0]).toString();
			var d = h.split(" ");
			for (var c = 0; c < d.length; c++) {
				delete a.triggersMap[f][d[c]][e]
			}
		}
		return this.__unbind__(h, g)
	};
	b.fn.hotbind = function(j, f, k) {
		var h = j.match(a.override);
		if (b.isFunction(f) || !h) {
			return this.__bind__(j, f, k)
		} else {
			var n = null,
			i = b.trim(j.replace(a.override, ""));
			if (i) {
				n = this.__bind__(i, f, k)
			}
			if (typeof f === "string") {
				f = {
					combi: f
				}
			}
			if (f.combi) {
				for (var m = 0; m < h.length; m++) {
					var d = h[m];
					var g = f.combi.toLowerCase(),
					e = a.newTrigger(d, g, k),
					l = ((this.prevObject && this.prevObject.query) || (this[0].id && this[0].id) || this[0]).toString();
					e[d][g].disableInInput = f.disableInInput;
					if (!a.triggersMap[l]) {
						a.triggersMap[l] = e
					} else {
						if (!a.triggersMap[l][d]) {
							a.triggersMap[l][d] = e[d]
						}
					}
					var c = a.triggersMap[l][d][g];
					if (!c) {
						a.triggersMap[l][d][g] = [e[d][g]]
					} else {
						if (c.constructor !== Array) {
							a.triggersMap[l][d][g] = [c]
						} else {
							a.triggersMap[l][d][g][c.length] = e[d][g]
						}
					}
					this.each(function() {
						var o = b(this);
						if (o.attr("hkId") && o.attr("hkId") !== l) {
							l = o.attr("hkId") + ";" + l
						}
						o.attr("hkId", l)
					});
					n = this.__bind__(h.join(" "), f, a.handler)
				}
			}
			return n
		}
	};
	b.fn.codeToString = function(d) {
		var e = d.which,
		j = a.specialKeys[e],
		i = !j && String.fromCharCode(e).toLowerCase(),
		f = d.shiftKey,
		c = d.ctrlKey,
		h = d.altKey || d.originalEvent.altKey;
		var k = j || i;
		var g = "";
		if (h) {
			g += "alt+"
		}
		if (c) {
			g += "ctrl+"
		}
		if (f) {
			g += "shift+"
		}
		return g + k
	};
	a.findElement = function(c) {
		if (!b(c).attr("hkId")) {
			if (b.browser.opera || b.browser.safari) {
				while (!b(c).attr("hkId") && c.parentNode) {
					c = c.parentNode
				}
			}
		}
		return c
	};
	a.handler = function(e) {
		var o = a.findElement(e.currentTarget),
		i = b(o),
		d = i.attr("hkId");
		if (d) {
			d = d.split(";");
			var g = e.which,
			q = e.type,
			p = a.specialKeys[g],
			n = !p && String.fromCharCode(g).toLowerCase(),
			h = e.shiftKey,
			c = e.ctrlKey,
			m = e.altKey || e.originalEvent.altKey,
			f = null;
			for (var r = 0; r < d.length; r++) {
				if (a.triggersMap[d[r]][q]) {
					f = a.triggersMap[d[r]][q];
					break
				}
			}
			if (f) {
				var j;
				if (!h && !c && !m) {
					j = f[p] || (n && f[n])
				} else {
					var l = "";
					if (m) {
						l += "alt+"
					}
					if (c) {
						l += "ctrl+"
					}
					if (h) {
						l += "shift+"
					}
					j = f[l + p];
					if (!j) {
						if (n) {
							j = f[l + n] || f[l + a.shiftNums[n]] || (l === "shift+" && f[a.shiftNums[n]])
						}
					}
				}
				if (j) {
					var s = false;
					for (var r = 0; r < j.length; r++) {
						if (j[r].disableInInput) {
							var k = b(e.target);
							if (i.is("input") || i.is("textarea") || i.is("select") || k.is("input") || k.is("textarea") || k.is("select")) {
								return true
							}
						}
						s = s || j[r].cb.apply(this, [e])
					}
					return s
				}
			}
		}
	};
	window.hotkeys = a;
	return b
})(jQuery);
/*!
 * jQuery UI 1.8.1
 *
 * Copyright (c) 2010 AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT (MIT-LICENSE.txt)
 * and GPL (GPL-LICENSE.txt) licenses.
 *
 * http://docs.jquery.com/UI
 */
jQuery.ui || (function(a) {
	a.ui = {
		version: "1.8.1",
		plugin: {
			add: function(c, d, f) {
				var e = a.ui[c].prototype;
				for (var b in f) {
					e.plugins[b] = e.plugins[b] || [];
					e.plugins[b].push([d, f[b]])
				}
			},
			call: function(b, d, c) {
				var f = b.plugins[d];
				if (!f || !b.element[0].parentNode) {
					return
				}
				for (var e = 0; e < f.length; e++) {
					if (b.options[f[e][0]]) {
						f[e][1].apply(b.element, c)
					}
				}
			}
		},
		contains: function(d, c) {
			return document.compareDocumentPosition ? d.compareDocumentPosition(c) & 16 : d !== c && d.contains(c)
		},
		hasScroll: function(e, c) {
			if (a(e).css("overflow") == "hidden") {
				return false
			}
			var b = (c && c == "left") ? "scrollLeft": "scrollTop",
			d = false;
			if (e[b] > 0) {
				return true
			}
			e[b] = 1;
			d = (e[b] > 0);
			e[b] = 0;
			return d
		},
		isOverAxis: function(c, b, d) {
			return (c > b) && (c < (b + d))
		},
		isOver: function(g, c, f, e, b, d) {
			return a.ui.isOverAxis(g, f, b) && a.ui.isOverAxis(c, e, d)
		},
		keyCode: {
			ALT: 18,
			BACKSPACE: 8,
			CAPS_LOCK: 20,
			COMMA: 188,
			CONTROL: 17,
			DELETE: 46,
			DOWN: 40,
			END: 35,
			ENTER: 13,
			ESCAPE: 27,
			HOME: 36,
			INSERT: 45,
			LEFT: 37,
			NUMPAD_ADD: 107,
			NUMPAD_DECIMAL: 110,
			NUMPAD_DIVIDE: 111,
			NUMPAD_ENTER: 108,
			NUMPAD_MULTIPLY: 106,
			NUMPAD_SUBTRACT: 109,
			PAGE_DOWN: 34,
			PAGE_UP: 33,
			PERIOD: 190,
			RIGHT: 39,
			SHIFT: 16,
			SPACE: 32,
			TAB: 9,
			UP: 38
		}
	};
	a.fn.extend({
		_focus: a.fn.focus,
		focus: function(b, c) {
			return typeof b === "number" ? this.each(function() {
				var d = this;
				setTimeout(function() {
					a(d).focus(); (c && c.call(d))
				},
				b)
			}) : this._focus.apply(this, arguments)
		},
		enableSelection: function() {
			return this.attr("unselectable", "off").css("MozUserSelect", "")
		},
		disableSelection: function() {
			return this.attr("unselectable", "on").css("MozUserSelect", "none").css("-webkit-user-select", "none")
		},
		scrollParent: function() {
			var b;
			if ((a.browser.msie && (/(static|relative)/).test(this.css("position"))) || (/absolute/).test(this.css("position"))) {
				b = this.parents().filter(function() {
					return (/(relative|absolute|fixed)/).test(a.curCSS(this, "position", 1)) && (/(auto|scroll)/).test(a.curCSS(this, "overflow", 1) + a.curCSS(this, "overflow-y", 1) + a.curCSS(this, "overflow-x", 1))
				}).eq(0)
			} else {
				b = this.parents().filter(function() {
					return (/(auto|scroll)/).test(a.curCSS(this, "overflow", 1) + a.curCSS(this, "overflow-y", 1) + a.curCSS(this, "overflow-x", 1))
				}).eq(0)
			}
			return (/fixed/).test(this.css("position")) || !b.length ? a(document) : b
		},
		zIndex: function(e) {
			if (e !== undefined) {
				return this.css("zIndex", e)
			}
			if (this.length) {
				var c = a(this[0]),
				b,
				d;
				while (c.length && c[0] !== document) {
					b = c.css("position");
					if (b == "absolute" || b == "relative" || b == "fixed") {
						d = parseInt(c.css("zIndex"));
						if (!isNaN(d) && d != 0) {
							return d
						}
					}
					c = c.parent()
				}
			}
			return 0
		}
	});
	a.extend(a.expr[":"], {
		data: function(d, c, b) {
			return !! a.data(d, b[3])
		},
		focusable: function(c) {
			var d = c.nodeName.toLowerCase(),
			b = a.attr(c, "tabindex");
			return (/input|select|textarea|button|object/.test(d) ? !c.disabled: "a" == d || "area" == d ? c.href || !isNaN(b) : !isNaN(b)) && !a(c)["area" == d ? "parents": "closest"](":hidden").length
		},
		tabbable: function(c) {
			var b = a.attr(c, "tabindex");
			return (isNaN(b) || b >= 0) && a(c).is(":focusable")
		}
	})
})(jQuery);
/*!
 * jQuery UI Widget 1.8.1
 *
 * Copyright (c) 2010 AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT (MIT-LICENSE.txt)
 * and GPL (GPL-LICENSE.txt) licenses.
 *
 * http://docs.jquery.com/UI/Widget
 */
(function(b) {
	var a = b.fn.remove;
	b.fn.remove = function(c, d) {
		return this.each(function() {
			if (!d) {
				if (!c || b.filter(c, [this]).length) {
					b("*", this).add(this).each(function() {
						b(this).triggerHandler("remove")
					})
				}
			}
			return a.call(b(this), c, d)
		})
	};
	b.widget = function(d, f, c) {
		var e = d.split(".")[0],
		h;
		d = d.split(".")[1];
		h = e + "-" + d;
		if (!c) {
			c = f;
			f = b.Widget
		}
		b.expr[":"][h] = function(i) {
			return !! b.data(i, d)
		};
		b[e] = b[e] || {};
		b[e][d] = function(i, j) {
			if (arguments.length) {
				this._createWidget(i, j)
			}
		};
		var g = new f();
		g.options = b.extend({},
		g.options);
		b[e][d].prototype = b.extend(true, g, {
			namespace: e,
			widgetName: d,
			widgetEventPrefix: b[e][d].prototype.widgetEventPrefix || d,
			widgetBaseClass: h
		},
		c);
		b.widget.bridge(d, b[e][d])
	};
	b.widget.bridge = function(d, c) {
		b.fn[d] = function(g) {
			var e = typeof g === "string",
			f = Array.prototype.slice.call(arguments, 1),
			h = this;
			g = !e && f.length ? b.extend.apply(null, [true, g].concat(f)) : g;
			if (e && g.substring(0, 1) === "_") {
				return h
			}
			if (e) {
				this.each(function() {
					var i = b.data(this, d),
					j = i && b.isFunction(i[g]) ? i[g].apply(i, f) : i;
					if (j !== i && j !== undefined) {
						h = j;
						return false
					}
				})
			} else {
				this.each(function() {
					var i = b.data(this, d);
					if (i) {
						if (g) {
							i.option(g)
						}
						i._init()
					} else {
						b.data(this, d, new c(g, this))
					}
				})
			}
			return h
		}
	};
	b.Widget = function(c, d) {
		if (arguments.length) {
			this._createWidget(c, d)
		}
	};
	b.Widget.prototype = {
		widgetName: "widget",
		widgetEventPrefix: "",
		options: {
			disabled: false
		},
		_createWidget: function(d, e) {
			this.element = b(e).data(this.widgetName, this);
			this.options = b.extend(true, {},
			this.options, b.metadata && b.metadata.get(e)[this.widgetName], d);
			var c = this;
			this.element.bind("remove." + this.widgetName,
			function() {
				c.destroy()
			});
			this._create();
			this._init()
		},
		_create: function() {},
		_init: function() {},
		destroy: function() {
			this.element.unbind("." + this.widgetName).removeData(this.widgetName);
			this.widget().unbind("." + this.widgetName).removeAttr("aria-disabled").removeClass(this.widgetBaseClass + "-disabled ui-state-disabled")
		},
		widget: function() {
			return this.element
		},
		option: function(e, f) {
			var d = e,
			c = this;
			if (arguments.length === 0) {
				return b.extend({},
				c.options)
			}
			if (typeof e === "string") {
				if (f === undefined) {
					return this.options[e]
				}
				d = {};
				d[e] = f
			}
			b.each(d,
			function(g, h) {
				c._setOption(g, h)
			});
			return c
		},
		_setOption: function(e, f, d) {
			this.options[e] = f;
			if (e === "disabled") {
				this.widget()[f ? "addClass": "removeClass"](this.widgetBaseClass + "-disabled ui-state-disabled").attr("aria-disabled", f)
			}
			if (e == "invisible") {
				if (d) {
					var c = b(d).has(this.widget());
					if (c.length > 0) {
						this.widget()[f ? "hide": "show"]();
						c[f ? "hide": "show"]()
					}
				} else {
					this.widget()[f ? "hide": "show"]()
				}
			}
			return this
		},
		enable: function() {
			return this._setOption("disabled", false)
		},
		disable: function() {
			return this._setOption("disabled", true)
		},
		visible: function(c) {
			this._setOption("invisible", false, c)
		},
		invisible: function(c) {
			this._setOption("invisible", true, c)
		},
		_trigger: function(d, e, f) {
			var h = this.options[d];
			e = b.Event(e);
			e.type = (d === this.widgetEventPrefix ? d: this.widgetEventPrefix + d).toLowerCase();
			f = f || {};
			if (e.originalEvent) {
				for (var c = b.event.props.length,
				g; c;) {
					g = b.event.props[--c];
					e[g] = e.originalEvent[g]
				}
			}
			this.element.trigger(e, f);
			return ! (b.isFunction(h) && h.call(this.element[0], e, f) === false || e.isDefaultPrevented())
		}
	}
})(jQuery);
/*!
 * jQuery UI Mouse 1.8.1
 *
 * Copyright (c) 2010 AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT (MIT-LICENSE.txt)
 * and GPL (GPL-LICENSE.txt) licenses.
 *
 * http://docs.jquery.com/UI/Mouse
 *
 * Depends:
 *	jquery.ui.widget.js
 */
(function(a) {
	a.widget("ui.mouse", {
		options: {
			cancel: ":input,option",
			distance: 1,
			delay: 0
		},
		_mouseInit: function() {
			var b = this;
			this.element.bind("mousedown." + this.widgetName,
			function(c) {
				return b._mouseDown(c)
			}).bind("click." + this.widgetName,
			function(c) {
				if (b._preventClickEvent) {
					b._preventClickEvent = false;
					c.stopImmediatePropagation();
					return false
				}
			});
			this.started = false
		},
		_mouseDestroy: function() {
			this.element.unbind("." + this.widgetName)
		},
		_mouseDown: function(d) {
			d.originalEvent = d.originalEvent || {};
			if (d.originalEvent.mouseHandled) {
				return
			} (this._mouseStarted && this._mouseUp(d));
			this._mouseDownEvent = d;
			var c = this,
			e = (d.which == 1),
			b = (typeof this.options.cancel == "string" ? a(d.target).parents().add(d.target).filter(this.options.cancel).length: false);
			if (!e || b || !this._mouseCapture(d)) {
				return true
			}
			this.mouseDelayMet = !this.options.delay;
			if (!this.mouseDelayMet) {
				this._mouseDelayTimer = setTimeout(function() {
					c.mouseDelayMet = true
				},
				this.options.delay)
			}
			if (this._mouseDistanceMet(d) && this._mouseDelayMet(d)) {
				this._mouseStarted = (this._mouseStart(d) !== false);
				if (!this._mouseStarted) {
					d.preventDefault();
					return true
				}
			}
			this._mouseMoveDelegate = function(f) {
				return c._mouseMove(f)
			};
			this._mouseUpDelegate = function(f) {
				return c._mouseUp(f)
			};
			a(document).bind("mousemove." + this.widgetName, this._mouseMoveDelegate).bind("mouseup." + this.widgetName, this._mouseUpDelegate); (a.browser.safari || d.preventDefault());
			d.originalEvent.mouseHandled = true;
			return true
		},
		_mouseMove: function(b) {
			if (a.browser.msie && (!b.button) && (parseInt(a.browser.version) < 9)) {
				return this._mouseUp(b)
			}
			if (this._mouseStarted) {
				this._mouseDrag(b);
				return b.preventDefault()
			}
			if (this._mouseDistanceMet(b) && this._mouseDelayMet(b)) {
				this._mouseStarted = (this._mouseStart(this._mouseDownEvent, b) !== false); (this._mouseStarted ? this._mouseDrag(b) : this._mouseUp(b))
			}
			return ! this._mouseStarted
		},
		_mouseUp: function(b) {
			a(document).unbind("mousemove." + this.widgetName, this._mouseMoveDelegate).unbind("mouseup." + this.widgetName, this._mouseUpDelegate);
			if (this._mouseStarted) {
				this._mouseStarted = false;
				this._preventClickEvent = (b.target == this._mouseDownEvent.target);
				this._mouseStop(b)
			}
			return false
		},
		_mouseDistanceMet: function(b) {
			return (Math.max(Math.abs(this._mouseDownEvent.pageX - b.pageX), Math.abs(this._mouseDownEvent.pageY - b.pageY)) >= this.options.distance)
		},
		_mouseDelayMet: function(b) {
			return this.mouseDelayMet
		},
		_mouseStart: function(b) {},
		_mouseDrag: function(b) {},
		_mouseStop: function(b) {},
		_mouseCapture: function(b) {
			return true
		}
	})
})(jQuery); (function(f) {
	f.ui = f.ui || {};
	var c = /left|center|right/,
	e = "center",
	d = /top|center|bottom/,
	g = "center",
	a = f.fn.position,
	b = f.fn.offset;
	f.fn.position = function(i) {
		if (!i || !i.of) {
			return a.apply(this, arguments)
		}
		i = f.extend({},
		i);
		var l = f(i.of),
		n = (i.collision || "flip").split(" "),
		m = i.offset ? i.offset.split(" ") : [0, 0],
		k,
		h,
		j;
		if (i.of.nodeType === 9) {
			k = l.width();
			h = l.height();
			j = {
				top: 0,
				left: 0
			}
		} else {
			if (i.of.scrollTo && i.of.document) {
				k = l.width();
				h = l.height();
				j = {
					top: l.scrollTop(),
					left: l.scrollLeft()
				}
			} else {
				if (i.of.preventDefault) {
					i.at = "left top";
					k = h = 0;
					j = {
						top: i.of.pageY,
						left: i.of.pageX
					}
				} else {
					k = l.outerWidth();
					h = l.outerHeight();
					j = l.offset()
				}
			}
		}
		f.each(["my", "at"],
		function() {
			var o = (i[this] || "").split(" ");
			if (o.length === 1) {
				o = c.test(o[0]) ? o.concat([g]) : d.test(o[0]) ? [e].concat(o) : [e, g]
			}
			o[0] = c.test(o[0]) ? o[0] : e;
			o[1] = d.test(o[1]) ? o[1] : g;
			i[this] = o
		});
		if (n.length === 1) {
			n[1] = n[0]
		}
		m[0] = parseInt(m[0], 10) || 0;
		if (m.length === 1) {
			m[1] = m[0]
		}
		m[1] = parseInt(m[1], 10) || 0;
		if (i.at[0] === "right") {
			j.left += k
		} else {
			if (i.at[0] === e) {
				j.left += k / 2
			}
		}
		if (i.at[1] === "bottom") {
			j.top += h
		} else {
			if (i.at[1] === g) {
				j.top += h / 2
			}
		}
		j.left += m[0];
		j.top += m[1];
		return this.each(function() {
			var r = f(this),
			q = r.outerWidth(),
			p = r.outerHeight(),
			o = f.extend({},
			j);
			if (i.my[0] === "right") {
				o.left -= q
			} else {
				if (i.my[0] === e) {
					o.left -= q / 2
				}
			}
			if (i.my[1] === "bottom") {
				o.top -= p
			} else {
				if (i.my[1] === g) {
					o.top -= p / 2
				}
			}
			o.left = parseInt(o.left);
			o.top = parseInt(o.top);
			f.each(["left", "top"],
			function(t, s) {
				if (f.ui.position[n[t]]) {
					f.ui.position[n[t]][s](o, {
						targetWidth: k,
						targetHeight: h,
						elemWidth: q,
						elemHeight: p,
						offset: m,
						my: i.my,
						at: i.at
					})
				}
			});
			if (f.fn.bgiframe) {
				r.bgiframe()
			}
			r.offset(f.extend(o, {
				using: i.using
			}))
		})
	};
	f.ui.position = {
		fit: {
			left: function(h, i) {
				var k = f(window),
				j = h.left + i.elemWidth - k.width() - k.scrollLeft();
				h.left = j > 0 ? h.left - j: Math.max(0, h.left)
			},
			top: function(h, i) {
				var k = f(window),
				j = h.top + i.elemHeight - k.height() - k.scrollTop();
				h.top = j > 0 ? h.top - j: Math.max(0, h.top)
			}
		},
		flip: {
			left: function(i, j) {
				if (j.at[0] === "center") {
					return
				}
				var l = f(window),
				k = i.left + j.elemWidth - l.width() - l.scrollLeft(),
				h = j.my[0] === "left" ? -j.elemWidth: j.my[0] === "right" ? j.elemWidth: 0,
				m = -2 * j.offset[0];
				i.left += i.left < 0 ? h + j.targetWidth + m: k > 0 ? h - j.targetWidth + m: 0
			},
			top: function(i, k) {
				if (k.at[1] === "center") {
					return
				}
				var m = f(window),
				l = i.top + k.elemHeight - m.height() - m.scrollTop(),
				h = k.my[1] === "top" ? -k.elemHeight: k.my[1] === "bottom" ? k.elemHeight: 0,
				j = k.at[1] === "top" ? k.targetHeight: -k.targetHeight,
				n = -2 * k.offset[1];
				i.top += i.top < 0 ? h + k.targetHeight + n: l > 0 ? h + j + n: 0
			}
		}
	};
	if (!f.offset.setOffset) {
		f.offset.setOffset = function(l, i) {
			if (/static/.test(f.curCSS(l, "position"))) {
				l.style.position = "relative"
			}
			var k = f(l),
			n = k.offset(),
			h = parseInt(f.curCSS(l, "top", true), 10) || 0,
			m = parseInt(f.curCSS(l, "left", true), 10) || 0,
			j = {
				top: (i.top - n.top) + h,
				left: (i.left - n.left) + m
			};
			if ("using" in i) {
				i.using.call(l, j)
			} else {
				k.css(j)
			}
		};
		f.fn.offset = function(h) {
			var i = this[0];
			if (!i || !i.ownerDocument) {
				return null
			}
			if (h) {
				return this.each(function() {
					f.offset.setOffset(this, h)
				})
			}
			return b.call(this)
		}
	}
} (jQuery)); (function(a) {
	a.widget("ui.draggable", a.ui.mouse, {
		widgetEventPrefix: "drag",
		options: {
			addClasses: true,
			appendTo: "parent",
			axis: false,
			connectToSortable: false,
			containment: false,
			cursor: "auto",
			cursorAt: false,
			grid: false,
			handle: false,
			helper: "original",
			iframeFix: false,
			opacity: false,
			refreshPositions: false,
			revert: false,
			revertDuration: 500,
			scope: "default",
			scroll: true,
			scrollSensitivity: 20,
			scrollSpeed: 20,
			snap: false,
			snapMode: "both",
			snapTolerance: 20,
			stack: false,
			zIndex: false
		},
		_create: function() {
			if (this.options.helper == "original" && !(/^(?:r|a|f)/).test(this.element.css("position"))) {
				this.element[0].style.position = "relative"
			} (this.options.addClasses && this.element.addClass("ui-draggable")); (this.options.disabled && this.element.addClass("ui-draggable-disabled"));
			this._mouseInit()
		},
		destroy: function() {
			if (!this.element.data("draggable")) {
				return
			}
			this.element.removeData("draggable").unbind(".draggable").removeClass("ui-draggable ui-draggable-dragging ui-draggable-disabled");
			this._mouseDestroy();
			return this
		},
		_mouseCapture: function(b) {
			var c = this.options;
			if (this.helper || c.disabled || a(b.target).is(".ui-resizable-handle")) {
				return false
			}
			this.handle = this._getHandle(b);
			if (!this.handle) {
				return false
			}
			return true
		},
		_mouseStart: function(b) {
			var c = this.options;
			this.helper = this._createHelper(b);
			this._cacheHelperProportions();
			if (a.ui.ddmanager) {
				a.ui.ddmanager.current = this
			}
			this._cacheMargins();
			this.cssPosition = this.helper.css("position");
			this.scrollParent = this.helper.scrollParent();
			this.offset = this.positionAbs = this.element.offset();
			this.offset = {
				top: this.offset.top - this.margins.top,
				left: this.offset.left - this.margins.left
			};
			a.extend(this.offset, {
				click: {
					left: b.pageX - this.offset.left,
					top: b.pageY - this.offset.top
				},
				parent: this._getParentOffset(),
				relative: this._getRelativeOffset()
			});
			this.originalPosition = this.position = this._generatePosition(b);
			this.originalPageX = b.pageX;
			this.originalPageY = b.pageY; (c.cursorAt && this._adjustOffsetFromHelper(c.cursorAt));
			if (c.containment) {
				this._setContainment()
			}
			if (this._trigger("start", b) === false) {
				this._clear();
				return false
			}
			this._cacheHelperProportions();
			if (a.ui.ddmanager && !c.dropBehaviour) {
				a.ui.ddmanager.prepareOffsets(this, b)
			}
			this.helper.addClass("ui-draggable-dragging");
			this._mouseDrag(b, true);
			return true
		},
		_mouseDrag: function(b, d) {
			this.position = this._generatePosition(b);
			this.positionAbs = this._convertPositionTo("absolute");
			if (!d) {
				var c = this._uiHash();
				if (this._trigger("drag", b, c) === false) {
					this._mouseUp({});
					return false
				}
				this.position = c.position
			}
			if (!this.options.axis || this.options.axis != "y") {
				this.helper[0].style.left = this.position.left + "px"
			}
			if (!this.options.axis || this.options.axis != "x") {
				this.helper[0].style.top = this.position.top + "px"
			}
			if (a.ui.ddmanager) {
				a.ui.ddmanager.drag(this, b)
			}
			return false
		},
		_mouseStop: function(c) {
			var d = false;
			if (a.ui.ddmanager && !this.options.dropBehaviour) {
				d = a.ui.ddmanager.drop(this, c)
			}
			if (this.dropped) {
				d = this.dropped;
				this.dropped = false
			}
			if (!this.element[0] || !this.element[0].parentNode) {
				return false
			}
			if ((this.options.revert == "invalid" && !d) || (this.options.revert == "valid" && d) || this.options.revert === true || (a.isFunction(this.options.revert) && this.options.revert.call(this.element, d))) {
				var b = this;
				a(this.helper).animate(this.originalPosition, parseInt(this.options.revertDuration, 10),
				function() {
					if (b._trigger("stop", c) !== false) {
						b._clear()
					}
				})
			} else {
				if (this._trigger("stop", c) !== false) {
					this._clear()
				}
			}
			return false
		},
		cancel: function() {
			if (this.helper.is(".ui-draggable-dragging")) {
				this._mouseUp({})
			} else {
				this._clear()
			}
			return this
		},
		_getHandle: function(b) {
			var c = !this.options.handle || !a(this.options.handle, this.element).length ? true: false;
			a(this.options.handle, this.element).find("*").andSelf().each(function() {
				if (this == b.target) {
					c = true
				}
			});
			return c
		},
		_createHelper: function(c) {
			var d = this.options;
			var b = a.isFunction(d.helper) ? a(d.helper.apply(this.element[0], [c])) : (d.helper == "clone" ? this.element.clone() : this.element);
			if (!b.parents("body").length) {
				b.appendTo((d.appendTo == "parent" ? this.element[0].parentNode: d.appendTo))
			}
			if (b[0] != this.element[0] && !(/(fixed|absolute)/).test(b.css("position"))) {
				b.css("position", "absolute")
			}
			return b
		},
		_adjustOffsetFromHelper: function(b) {
			if (typeof b == "string") {
				b = b.split(" ")
			}
			if (a.isArray(b)) {
				b = {
					left: +b[0],
					top: +b[1] || 0
				}
			}
			if ("left" in b) {
				this.offset.click.left = b.left + this.margins.left
			}
			if ("right" in b) {
				this.offset.click.left = this.helperProportions.width - b.right + this.margins.left
			}
			if ("top" in b) {
				this.offset.click.top = b.top + this.margins.top
			}
			if ("bottom" in b) {
				this.offset.click.top = this.helperProportions.height - b.bottom + this.margins.top
			}
		},
		_getParentOffset: function() {
			this.offsetParent = this.helper.offsetParent();
			var b = this.offsetParent.offset();
			if (this.cssPosition == "absolute" && this.scrollParent[0] != document && a.ui.contains(this.scrollParent[0], this.offsetParent[0])) {
				b.left += this.scrollParent.scrollLeft();
				b.top += this.scrollParent.scrollTop()
			}
			if ((this.offsetParent[0] == document.body) || (this.offsetParent[0].tagName && this.offsetParent[0].tagName.toLowerCase() == "html" && a.browser.msie)) {
				b = {
					top: 0,
					left: 0
				}
			}
			return {
				top: b.top + (parseInt(this.offsetParent.css("borderTopWidth"), 10) || 0),
				left: b.left + (parseInt(this.offsetParent.css("borderLeftWidth"), 10) || 0)
			}
		},
		_getRelativeOffset: function() {
			if (this.cssPosition == "relative") {
				var b = this.element.position();
				return {
					top: b.top - (parseInt(this.helper.css("top"), 10) || 0) + this.scrollParent.scrollTop(),
					left: b.left - (parseInt(this.helper.css("left"), 10) || 0) + this.scrollParent.scrollLeft()
				}
			} else {
				return {
					top: 0,
					left: 0
				}
			}
		},
		_cacheMargins: function() {
			this.margins = {
				left: (parseInt(this.element.css("marginLeft"), 10) || 0),
				top: (parseInt(this.element.css("marginTop"), 10) || 0)
			}
		},
		_cacheHelperProportions: function() {
			this.helperProportions = {
				width: this.helper.outerWidth(),
				height: this.helper.outerHeight()
			}
		},
		_setContainment: function() {
			var e = this.options;
			if (e.containment == "parent") {
				e.containment = this.helper[0].parentNode
			}
			if (e.containment == "document" || e.containment == "window") {
				this.containment = [0 - this.offset.relative.left - this.offset.parent.left, 0 - this.offset.relative.top - this.offset.parent.top, a(e.containment == "document" ? document: window).width() - this.helperProportions.width - this.margins.left, (a(e.containment == "document" ? document: window).height() || document.body.parentNode.scrollHeight) - this.helperProportions.height - this.margins.top]
			}
			if (! (/^(document|window|parent)$/).test(e.containment) && e.containment.constructor != Array) {
				var c = a(e.containment)[0];
				if (!c) {
					return
				}
				var d = a(e.containment).offset();
				var b = (a(c).css("overflow") != "hidden");
				this.containment = [d.left + (parseInt(a(c).css("borderLeftWidth"), 10) || 0) + (parseInt(a(c).css("paddingLeft"), 10) || 0) - this.margins.left, d.top + (parseInt(a(c).css("borderTopWidth"), 10) || 0) + (parseInt(a(c).css("paddingTop"), 10) || 0) - this.margins.top, d.left + (b ? Math.max(c.scrollWidth, c.offsetWidth) : c.offsetWidth) - (parseInt(a(c).css("borderLeftWidth"), 10) || 0) - (parseInt(a(c).css("paddingRight"), 10) || 0) - this.helperProportions.width - this.margins.left, d.top + (b ? Math.max(c.scrollHeight, c.offsetHeight) : c.offsetHeight) - (parseInt(a(c).css("borderTopWidth"), 10) || 0) - (parseInt(a(c).css("paddingBottom"), 10) || 0) - this.helperProportions.height - this.margins.top]
			} else {
				if (e.containment.constructor == Array) {
					this.containment = e.containment
				}
			}
		},
		_convertPositionTo: function(f, h) {
			if (!h) {
				h = this.position
			}
			var c = f == "absolute" ? 1 : -1;
			var e = this.options,
			b = this.cssPosition == "absolute" && !(this.scrollParent[0] != document && a.ui.contains(this.scrollParent[0], this.offsetParent[0])) ? this.offsetParent: this.scrollParent,
			g = (/(html|body)/i).test(b[0].tagName);
			return {
				top: (h.top + this.offset.relative.top * c + this.offset.parent.top * c - (a.browser.safari && a.browser.version < 526 && this.cssPosition == "fixed" ? 0 : (this.cssPosition == "fixed" ? -this.scrollParent.scrollTop() : (g ? 0 : b.scrollTop())) * c)),
				left: (h.left + this.offset.relative.left * c + this.offset.parent.left * c - (a.browser.safari && a.browser.version < 526 && this.cssPosition == "fixed" ? 0 : (this.cssPosition == "fixed" ? -this.scrollParent.scrollLeft() : g ? 0 : b.scrollLeft()) * c))
			}
		},
		_generatePosition: function(e) {
			var h = this.options,
			b = this.cssPosition == "absolute" && !(this.scrollParent[0] != document && a.ui.contains(this.scrollParent[0], this.offsetParent[0])) ? this.offsetParent: this.scrollParent,
			i = (/(html|body)/i).test(b[0].tagName);
			var d = e.pageX;
			var c = e.pageY;
			if (this.originalPosition) {
				if (this.containment) {
					if (e.pageX - this.offset.click.left < this.containment[0]) {
						d = this.containment[0] + this.offset.click.left
					}
					if (e.pageY - this.offset.click.top < this.containment[1]) {
						c = this.containment[1] + this.offset.click.top
					}
					if (e.pageX - this.offset.click.left > this.containment[2]) {
						d = this.containment[2] + this.offset.click.left
					}
					if (e.pageY - this.offset.click.top > this.containment[3]) {
						c = this.containment[3] + this.offset.click.top
					}
				}
				if (h.grid) {
					var g = this.originalPageY + Math.round((c - this.originalPageY) / h.grid[1]) * h.grid[1];
					c = this.containment ? (!(g - this.offset.click.top < this.containment[1] || g - this.offset.click.top > this.containment[3]) ? g: (!(g - this.offset.click.top < this.containment[1]) ? g - h.grid[1] : g + h.grid[1])) : g;
					var f = this.originalPageX + Math.round((d - this.originalPageX) / h.grid[0]) * h.grid[0];
					d = this.containment ? (!(f - this.offset.click.left < this.containment[0] || f - this.offset.click.left > this.containment[2]) ? f: (!(f - this.offset.click.left < this.containment[0]) ? f - h.grid[0] : f + h.grid[0])) : f
				}
			}
			return {
				top: (c - this.offset.click.top - this.offset.relative.top - this.offset.parent.top + (a.browser.safari && a.browser.version < 526 && this.cssPosition == "fixed" ? 0 : (this.cssPosition == "fixed" ? -this.scrollParent.scrollTop() : (i ? 0 : b.scrollTop())))),
				left: (d - this.offset.click.left - this.offset.relative.left - this.offset.parent.left + (a.browser.safari && a.browser.version < 526 && this.cssPosition == "fixed" ? 0 : (this.cssPosition == "fixed" ? -this.scrollParent.scrollLeft() : i ? 0 : b.scrollLeft())))
			}
		},
		_clear: function() {
			this.helper.removeClass("ui-draggable-dragging");
			if (this.helper[0] != this.element[0] && !this.cancelHelperRemoval) {
				this.helper.remove()
			}
			this.helper = null;
			this.cancelHelperRemoval = false
		},
		_trigger: function(b, c, d) {
			d = d || this._uiHash();
			a.ui.plugin.call(this, b, [c, d]);
			if (b == "drag") {
				this.positionAbs = this._convertPositionTo("absolute")
			}
			return a.Widget.prototype._trigger.call(this, b, c, d)
		},
		plugins: {},
		_uiHash: function(b) {
			return {
				helper: this.helper,
				position: this.position,
				originalPosition: this.originalPosition,
				offset: this.positionAbs
			}
		}
	});
	a.extend(a.ui.draggable, {
		version: "1.8.1"
	});
	a.ui.plugin.add("draggable", "connectToSortable", {
		start: function(c, e) {
			var d = a(this).data("draggable"),
			f = d.options,
			b = a.extend({},
			e, {
				item: d.element
			});
			d.sortables = [];
			a(f.connectToSortable).each(function() {
				var g = a.data(this, "sortable");
				if (g && !g.options.disabled) {
					d.sortables.push({
						instance: g,
						shouldRevert: g.options.revert
					});
					g._refreshItems();
					g._trigger("activate", c, b)
				}
			})
		},
		stop: function(c, e) {
			var d = a(this).data("draggable"),
			b = a.extend({},
			e, {
				item: d.element
			});
			a.each(d.sortables,
			function() {
				if (this.instance.isOver) {
					this.instance.isOver = 0;
					d.cancelHelperRemoval = true;
					this.instance.cancelHelperRemoval = false;
					if (this.shouldRevert) {
						this.instance.options.revert = true
					}
					this.instance._mouseStop(c);
					this.instance.options.helper = this.instance.options._helper;
					if (d.options.helper == "original") {
						this.instance.currentItem.css({
							top: "auto",
							left: "auto"
						})
					}
				} else {
					this.instance.cancelHelperRemoval = false;
					this.instance._trigger("deactivate", c, b)
				}
			})
		},
		drag: function(c, f) {
			var e = a(this).data("draggable"),
			b = this;
			var d = function(i) {
				var n = this.offset.click.top,
				m = this.offset.click.left;
				var g = this.positionAbs.top,
				k = this.positionAbs.left;
				var j = i.height,
				l = i.width;
				var p = i.top,
				h = i.left;
				return a.ui.isOver(g + n, k + m, p, h, j, l)
			};
			a.each(e.sortables,
			function(g) {
				this.instance.positionAbs = e.positionAbs;
				this.instance.helperProportions = e.helperProportions;
				this.instance.offset.click = e.offset.click;
				if (this.instance._intersectsWith(this.instance.containerCache)) {
					if (!this.instance.isOver) {
						this.instance.isOver = 1;
						this.instance.currentItem = a(b).clone().appendTo(this.instance.element).data("sortable-item", true);
						this.instance.options._helper = this.instance.options.helper;
						this.instance.options.helper = function() {
							return f.helper[0]
						};
						c.target = this.instance.currentItem[0];
						this.instance._mouseCapture(c, true);
						this.instance._mouseStart(c, true, true);
						this.instance.offset.click.top = e.offset.click.top;
						this.instance.offset.click.left = e.offset.click.left;
						this.instance.offset.parent.left -= e.offset.parent.left - this.instance.offset.parent.left;
						this.instance.offset.parent.top -= e.offset.parent.top - this.instance.offset.parent.top;
						e._trigger("toSortable", c);
						e.dropped = this.instance.element;
						e.currentItem = e.element;
						this.instance.fromOutside = e
					}
					if (this.instance.currentItem) {
						this.instance._mouseDrag(c)
					}
				} else {
					if (this.instance.isOver) {
						this.instance.isOver = 0;
						this.instance.cancelHelperRemoval = true;
						this.instance.options.revert = false;
						this.instance._trigger("out", c, this.instance._uiHash(this.instance));
						this.instance._mouseStop(c, true);
						this.instance.options.helper = this.instance.options._helper;
						this.instance.currentItem.remove();
						if (this.instance.placeholder) {
							this.instance.placeholder.remove()
						}
						e._trigger("fromSortable", c);
						e.dropped = false
					}
				}
			})
		}
	});
	a.ui.plugin.add("draggable", "cursor", {
		start: function(c, d) {
			var b = a("body"),
			e = a(this).data("draggable").options;
			if (b.css("cursor")) {
				e._cursor = b.css("cursor")
			}
			b.css("cursor", e.cursor)
		},
		stop: function(b, c) {
			var d = a(this).data("draggable").options;
			if (d._cursor) {
				a("body").css("cursor", d._cursor)
			}
		}
	});
	a.ui.plugin.add("draggable", "iframeFix", {
		start: function(b, c) {
			var d = a(this).data("draggable").options;
			a(d.iframeFix === true ? "iframe": d.iframeFix).each(function() {
				a('<div class="ui-draggable-iframeFix" style="background: #fff;"></div>').css({
					width: this.offsetWidth + "px",
					height: this.offsetHeight + "px",
					position: "absolute",
					opacity: "0.001",
					zIndex: 1000
				}).css(a(this).offset()).appendTo("body")
			})
		},
		stop: function(b, c) {
			a("div.ui-draggable-iframeFix").each(function() {
				this.parentNode.removeChild(this)
			})
		}
	});
	a.ui.plugin.add("draggable", "opacity", {
		start: function(c, d) {
			var b = a(d.helper),
			e = a(this).data("draggable").options;
			if (b.css("opacity")) {
				e._opacity = b.css("opacity")
			}
			b.css("opacity", e.opacity)
		},
		stop: function(b, c) {
			var d = a(this).data("draggable").options;
			if (d._opacity) {
				a(c.helper).css("opacity", d._opacity)
			}
		}
	});
	a.ui.plugin.add("draggable", "scroll", {
		start: function(c, d) {
			var b = a(this).data("draggable");
			if (b.scrollParent[0] != document && b.scrollParent[0].tagName != "HTML") {
				b.overflowOffset = b.scrollParent.offset()
			}
		},
		drag: function(d, e) {
			var c = a(this).data("draggable"),
			f = c.options,
			b = false;
			if (c.scrollParent[0] != document && c.scrollParent[0].tagName != "HTML") {
				if (!f.axis || f.axis != "x") {
					if ((c.overflowOffset.top + c.scrollParent[0].offsetHeight) - d.pageY < f.scrollSensitivity) {
						c.scrollParent[0].scrollTop = b = c.scrollParent[0].scrollTop + f.scrollSpeed
					} else {
						if (d.pageY - c.overflowOffset.top < f.scrollSensitivity) {
							c.scrollParent[0].scrollTop = b = c.scrollParent[0].scrollTop - f.scrollSpeed
						}
					}
				}
				if (!f.axis || f.axis != "y") {
					if ((c.overflowOffset.left + c.scrollParent[0].offsetWidth) - d.pageX < f.scrollSensitivity) {
						c.scrollParent[0].scrollLeft = b = c.scrollParent[0].scrollLeft + f.scrollSpeed
					} else {
						if (d.pageX - c.overflowOffset.left < f.scrollSensitivity) {
							c.scrollParent[0].scrollLeft = b = c.scrollParent[0].scrollLeft - f.scrollSpeed
						}
					}
				}
			} else {
				if (!f.axis || f.axis != "x") {
					if (d.pageY - a(document).scrollTop() < f.scrollSensitivity) {
						b = a(document).scrollTop(a(document).scrollTop() - f.scrollSpeed)
					} else {
						if (a(window).height() - (d.pageY - a(document).scrollTop()) < f.scrollSensitivity) {
							b = a(document).scrollTop(a(document).scrollTop() + f.scrollSpeed)
						}
					}
				}
				if (!f.axis || f.axis != "y") {
					if (d.pageX - a(document).scrollLeft() < f.scrollSensitivity) {
						b = a(document).scrollLeft(a(document).scrollLeft() - f.scrollSpeed)
					} else {
						if (a(window).width() - (d.pageX - a(document).scrollLeft()) < f.scrollSensitivity) {
							b = a(document).scrollLeft(a(document).scrollLeft() + f.scrollSpeed)
						}
					}
				}
			}
			if (b !== false && a.ui.ddmanager && !f.dropBehaviour) {
				a.ui.ddmanager.prepareOffsets(c, d)
			}
		}
	});
	a.ui.plugin.add("draggable", "snap", {
		start: function(c, d) {
			var b = a(this).data("draggable"),
			e = b.options;
			b.snapElements = [];
			a(e.snap.constructor != String ? (e.snap.items || ":data(draggable)") : e.snap).each(function() {
				var g = a(this);
				var f = g.offset();
				if (this != b.element[0]) {
					b.snapElements.push({
						item: this,
						width: g.outerWidth(),
						height: g.outerHeight(),
						top: f.top,
						left: f.left
					})
				}
			})
		},
		drag: function(u, p) {
			var g = a(this).data("draggable"),
			q = g.options;
			var y = q.snapTolerance;
			var x = p.offset.left,
			w = x + g.helperProportions.width,
			f = p.offset.top,
			e = f + g.helperProportions.height;
			for (var v = g.snapElements.length - 1; v >= 0; v--) {
				var s = g.snapElements[v].left,
				n = s + g.snapElements[v].width,
				m = g.snapElements[v].top,
				A = m + g.snapElements[v].height;
				if (! ((s - y < x && x < n + y && m - y < f && f < A + y) || (s - y < x && x < n + y && m - y < e && e < A + y) || (s - y < w && w < n + y && m - y < f && f < A + y) || (s - y < w && w < n + y && m - y < e && e < A + y))) {
					if (g.snapElements[v].snapping) { (g.options.snap.release && g.options.snap.release.call(g.element, u, a.extend(g._uiHash(), {
							snapItem: g.snapElements[v].item
						})))
					}
					g.snapElements[v].snapping = false;
					continue
				}
				if (q.snapMode != "inner") {
					var c = Math.abs(m - e) <= y;
					var z = Math.abs(A - f) <= y;
					var j = Math.abs(s - w) <= y;
					var k = Math.abs(n - x) <= y;
					if (c) {
						p.position.top = g._convertPositionTo("relative", {
							top: m - g.helperProportions.height,
							left: 0
						}).top - g.margins.top
					}
					if (z) {
						p.position.top = g._convertPositionTo("relative", {
							top: A,
							left: 0
						}).top - g.margins.top
					}
					if (j) {
						p.position.left = g._convertPositionTo("relative", {
							top: 0,
							left: s - g.helperProportions.width
						}).left - g.margins.left
					}
					if (k) {
						p.position.left = g._convertPositionTo("relative", {
							top: 0,
							left: n
						}).left - g.margins.left
					}
				}
				var h = (c || z || j || k);
				if (q.snapMode != "outer") {
					var c = Math.abs(m - f) <= y;
					var z = Math.abs(A - e) <= y;
					var j = Math.abs(s - x) <= y;
					var k = Math.abs(n - w) <= y;
					if (c) {
						p.position.top = g._convertPositionTo("relative", {
							top: m,
							left: 0
						}).top - g.margins.top
					}
					if (z) {
						p.position.top = g._convertPositionTo("relative", {
							top: A - g.helperProportions.height,
							left: 0
						}).top - g.margins.top
					}
					if (j) {
						p.position.left = g._convertPositionTo("relative", {
							top: 0,
							left: s
						}).left - g.margins.left
					}
					if (k) {
						p.position.left = g._convertPositionTo("relative", {
							top: 0,
							left: n - g.helperProportions.width
						}).left - g.margins.left
					}
				}
				if (!g.snapElements[v].snapping && (c || z || j || k || h)) { (g.options.snap.snap && g.options.snap.snap.call(g.element, u, a.extend(g._uiHash(), {
						snapItem: g.snapElements[v].item
					})))
				}
				g.snapElements[v].snapping = (c || z || j || k || h)
			}
		}
	});
	a.ui.plugin.add("draggable", "stack", {
		start: function(c, d) {
			var f = a(this).data("draggable").options;
			var e = a.makeArray(a(f.stack)).sort(function(h, g) {
				return (parseInt(a(h).css("zIndex"), 10) || 0) - (parseInt(a(g).css("zIndex"), 10) || 0)
			});
			if (!e.length) {
				return
			}
			var b = parseInt(e[0].style.zIndex) || 0;
			a(e).each(function(g) {
				this.style.zIndex = b + g
			});
			this[0].style.zIndex = b + e.length
		}
	});
	a.ui.plugin.add("draggable", "zIndex", {
		start: function(c, d) {
			var b = a(d.helper),
			e = a(this).data("draggable").options;
			if (b.css("zIndex")) {
				e._zIndex = b.css("zIndex")
			}
			b.css("zIndex", e.zIndex)
		},
		stop: function(b, c) {
			var d = a(this).data("draggable").options;
			if (d._zIndex) {
				a(c.helper).css("zIndex", d._zIndex)
			}
		}
	})
})(jQuery); (function(c) {
	c.widget("ui.resizable", c.ui.mouse, {
		widgetEventPrefix: "resize",
		options: {
			alsoResize: false,
			animate: false,
			animateDuration: "slow",
			animateEasing: "swing",
			aspectRatio: false,
			autoHide: false,
			containment: false,
			ghost: false,
			grid: false,
			handles: "e,s,se",
			helper: false,
			maxHeight: null,
			maxWidth: null,
			minHeight: 10,
			minWidth: 10,
			zIndex: 1000
		},
		_create: function() {
			var e = this,
			j = this.options;
			this.element.addClass("ui-resizable");
			c.extend(this, {
				_aspectRatio: !!(j.aspectRatio),
				aspectRatio: j.aspectRatio,
				originalElement: this.element,
				_proportionallyResizeElements: [],
				_helper: j.helper || j.ghost || j.animate ? j.helper || "ui-resizable-helper": null
			});
			if (this.element[0].nodeName.match(/canvas|textarea|input|select|button|img/i)) {
				if (/relative/.test(this.element.css("position")) && c.browser.opera) {
					this.element.css({
						position: "relative",
						top: "auto",
						left: "auto"
					})
				}
				this.element.wrap(c('<div class="ui-wrapper" style="overflow: hidden;"></div>').css({
					position: this.element.css("position"),
					width: this.element.outerWidth(),
					height: this.element.outerHeight(),
					top: this.element.css("top"),
					left: this.element.css("left")
				}));
				this.element = this.element.parent().data("resizable", this.element.data("resizable"));
				this.elementIsWrapper = true;
				this.element.css({
					marginLeft: this.originalElement.css("marginLeft"),
					marginTop: this.originalElement.css("marginTop"),
					marginRight: this.originalElement.css("marginRight"),
					marginBottom: this.originalElement.css("marginBottom")
				});
				this.originalElement.css({
					marginLeft: 0,
					marginTop: 0,
					marginRight: 0,
					marginBottom: 0
				});
				this.originalResizeStyle = this.originalElement.css("resize");
				this.originalElement.css("resize", "none");
				this._proportionallyResizeElements.push(this.originalElement.css({
					position: "static",
					zoom: 1,
					display: "block"
				}));
				this.originalElement.css({
					margin: this.originalElement.css("margin")
				});
				this._proportionallyResize()
			}
			this.handles = j.handles || (!c(".ui-resizable-handle", this.element).length ? "e,s,se": {
				n: ".ui-resizable-n",
				e: ".ui-resizable-e",
				s: ".ui-resizable-s",
				w: ".ui-resizable-w",
				se: ".ui-resizable-se",
				sw: ".ui-resizable-sw",
				ne: ".ui-resizable-ne",
				nw: ".ui-resizable-nw"
			});
			if (this.handles.constructor == String) {
				if (this.handles == "all") {
					this.handles = "n,e,s,w,se,sw,ne,nw"
				}
				var k = this.handles.split(",");
				this.handles = {};
				for (var f = 0; f < k.length; f++) {
					var h = c.trim(k[f]),
					d = "ui-resizable-" + h;
					var g = c('<div class="ui-resizable-handle ' + d + '"></div>');
					if (/sw|se|ne|nw/.test(h)) {
						g.css({
							zIndex: ++j.zIndex
						})
					}
					if ("se" == h) {
						g.addClass("ui-icon ui-icon-gripsmall-diagonal-se")
					}
					this.handles[h] = ".ui-resizable-" + h;
					this.element.append(g)
				}
			}
			this._renderAxis = function(p) {
				p = p || this.element;
				for (var m in this.handles) {
					if (this.handles[m].constructor == String) {
						this.handles[m] = c(this.handles[m], this.element).show()
					}
					if (this.elementIsWrapper && this.originalElement[0].nodeName.match(/textarea|input|select|button/i)) {
						var n = c(this.handles[m], this.element),
						o = 0;
						o = /sw|ne|nw|se|n|s/.test(m) ? n.outerHeight() : n.outerWidth();
						var l = ["padding", /ne|nw|n/.test(m) ? "Top": /se|sw|s/.test(m) ? "Bottom": /^e$/.test(m) ? "Right": "Left"].join("");
						p.css(l, o);
						this._proportionallyResize()
					}
					if (!c(this.handles[m]).length) {
						continue
					}
				}
			};
			this._renderAxis(this.element);
			this._handles = c(".ui-resizable-handle", this.element).disableSelection();
			this._handles.mouseover(function() {
				if (!e.resizing) {
					if (this.className) {
						var i = this.className.match(/ui-resizable-(se|sw|ne|nw|n|e|s|w)/i)
					}
					e.axis = i && i[1] ? i[1] : "se"
				}
			});
			if (j.autoHide) {
				this._handles.hide();
				c(this.element).addClass("ui-resizable-autohide").hover(function() {
					c(this).removeClass("ui-resizable-autohide");
					e._handles.show()
				},
				function() {
					if (!e.resizing) {
						c(this).addClass("ui-resizable-autohide");
						e._handles.hide()
					}
				})
			}
			this._mouseInit()
		},
		destroy: function() {
			this._mouseDestroy();
			var d = function(f) {
				c(f).removeClass("ui-resizable ui-resizable-disabled ui-resizable-resizing").removeData("resizable").unbind(".resizable").find(".ui-resizable-handle").remove()
			};
			if (this.elementIsWrapper) {
				d(this.element);
				var e = this.element;
				e.after(this.originalElement.css({
					position: e.css("position"),
					width: e.outerWidth(),
					height: e.outerHeight(),
					top: e.css("top"),
					left: e.css("left")
				})).remove()
			}
			this.originalElement.css("resize", this.originalResizeStyle);
			d(this.originalElement);
			return this
		},
		_mouseCapture: function(e) {
			var f = false;
			for (var d in this.handles) {
				if (c(this.handles[d])[0] == e.target) {
					f = true
				}
			}
			return ! this.options.disabled && f
		},
		_mouseStart: function(f) {
			var i = this.options,
			e = this.element.position(),
			d = this.element;
			this.resizing = true;
			this.documentScroll = {
				top: c(document).scrollTop(),
				left: c(document).scrollLeft()
			};
			if (d.is(".ui-draggable") || (/absolute/).test(d.css("position"))) {
				d.css({
					position: "absolute",
					top: e.top,
					left: e.left
				})
			}
			if (c.browser.opera && (/relative/).test(d.css("position"))) {
				d.css({
					position: "relative",
					top: "auto",
					left: "auto"
				})
			}
			this._renderProxy();
			var j = b(this.helper.css("left")),
			g = b(this.helper.css("top"));
			if (i.containment) {
				j += c(i.containment).scrollLeft() || 0;
				g += c(i.containment).scrollTop() || 0
			}
			this.offset = this.helper.offset();
			this.position = {
				left: j,
				top: g
			};
			this.size = this._helper ? {
				width: d.outerWidth(),
				height: d.outerHeight()
			}: {
				width: d.width(),
				height: d.height()
			};
			this.originalSize = this._helper ? {
				width: d.outerWidth(),
				height: d.outerHeight()
			}: {
				width: d.width(),
				height: d.height()
			};
			this.originalPosition = {
				left: j,
				top: g
			};
			this.sizeDiff = {
				width: d.outerWidth() - d.width(),
				height: d.outerHeight() - d.height()
			};
			this.originalMousePosition = {
				left: f.pageX,
				top: f.pageY
			};
			this.aspectRatio = (typeof i.aspectRatio == "number") ? i.aspectRatio: ((this.originalSize.width / this.originalSize.height) || 1);
			var h = c(".ui-resizable-" + this.axis).css("cursor");
			c("body").css("cursor", h == "auto" ? this.axis + "-resize": h);
			d.addClass("ui-resizable-resizing");
			this._propagate("start", f);
			return true
		},
		_mouseDrag: function(d) {
			var g = this.helper,
			f = this.options,
			l = {},
			p = this,
			i = this.originalMousePosition,
			m = this.axis;
			var q = (d.pageX - i.left) || 0,
			n = (d.pageY - i.top) || 0;
			var h = this._change[m];
			if (!h) {
				return false
			}
			var k = h.apply(this, [d, q, n]),
			j = c.browser.msie && c.browser.version < 7,
			e = this.sizeDiff;
			if (this._aspectRatio || d.shiftKey) {
				k = this._updateRatio(k, d)
			}
			k = this._respectSize(k, d);
			this._propagate("resize", d);
			g.css({
				top: this.position.top + "px",
				left: this.position.left + "px",
				width: this.size.width + "px",
				height: this.size.height + "px"
			});
			if (!this._helper && this._proportionallyResizeElements.length) {
				this._proportionallyResize()
			}
			this._updateCache(k);
			this._trigger("resize", d, this.ui());
			return false
		},
		_mouseStop: function(g) {
			this.resizing = false;
			var h = this.options,
			l = this;
			if (this._helper) {
				var f = this._proportionallyResizeElements,
				d = f.length && (/textarea/i).test(f[0].nodeName),
				e = d && c.ui.hasScroll(f[0], "left") ? 0 : l.sizeDiff.height,
				j = d ? 0 : l.sizeDiff.width;
				var m = {
					width: (l.size.width - j),
					height: (l.size.height - e)
				},
				i = (parseInt(l.element.css("left"), 10) + (l.position.left - l.originalPosition.left)) || null,
				k = (parseInt(l.element.css("top"), 10) + (l.position.top - l.originalPosition.top)) || null;
				if (!h.animate) {
					this.element.css(c.extend(m, {
						top: k,
						left: i
					}))
				}
				l.helper.height(l.size.height);
				l.helper.width(l.size.width);
				if (this._helper && !h.animate) {
					this._proportionallyResize()
				}
			}
			c("body").css("cursor", "auto");
			this.element.removeClass("ui-resizable-resizing");
			this._propagate("stop", g);
			if (this._helper) {
				this.helper.remove()
			}
			return false
		},
		_updateCache: function(d) {
			var e = this.options;
			this.offset = this.helper.offset();
			if (a(d.left)) {
				this.position.left = d.left
			}
			if (a(d.top)) {
				this.position.top = d.top
			}
			if (a(d.height)) {
				this.size.height = d.height
			}
			if (a(d.width)) {
				this.size.width = d.width
			}
		},
		_updateRatio: function(g, f) {
			var h = this.options,
			i = this.position,
			e = this.size,
			d = this.axis;
			if (g.height) {
				g.width = (e.height * this.aspectRatio)
			} else {
				if (g.width) {
					g.height = (e.width / this.aspectRatio)
				}
			}
			if (d == "sw") {
				g.left = i.left + (e.width - g.width);
				g.top = null
			}
			if (d == "nw") {
				g.top = i.top + (e.height - g.height);
				g.left = i.left + (e.width - g.width)
			}
			return g
		},
		_respectSize: function(k, f) {
			var i = this.helper,
			h = this.options,
			q = this._aspectRatio || f.shiftKey,
			p = this.axis,
			s = a(k.width) && h.maxWidth && (h.maxWidth < k.width),
			l = a(k.height) && h.maxHeight && (h.maxHeight < k.height),
			g = a(k.width) && h.minWidth && (h.minWidth > k.width),
			r = a(k.height) && h.minHeight && (h.minHeight > k.height);
			if (g) {
				k.width = h.minWidth
			}
			if (r) {
				k.height = h.minHeight
			}
			if (s) {
				k.width = h.maxWidth
			}
			if (l) {
				k.height = h.maxHeight
			}
			var e = this.originalPosition.left + this.originalSize.width,
			n = this.position.top + this.size.height;
			var j = /sw|nw|w/.test(p),
			d = /nw|ne|n/.test(p);
			if (g && j) {
				k.left = e - h.minWidth
			}
			if (s && j) {
				k.left = e - h.maxWidth
			}
			if (r && d) {
				k.top = n - h.minHeight
			}
			if (l && d) {
				k.top = n - h.maxHeight
			}
			var m = !k.width && !k.height;
			if (m && !k.left && k.top) {
				k.top = null
			} else {
				if (m && !k.top && k.left) {
					k.left = null
				}
			}
			return k
		},
		_proportionallyResize: function() {
			var j = this.options;
			if (!this._proportionallyResizeElements.length) {
				return
			}
			var f = this.helper || this.element;
			for (var e = 0; e < this._proportionallyResizeElements.length; e++) {
				var g = this._proportionallyResizeElements[e];
				if (!this.borderDif) {
					var d = [g.css("borderTopWidth"), g.css("borderRightWidth"), g.css("borderBottomWidth"), g.css("borderLeftWidth")],
					h = [g.css("paddingTop"), g.css("paddingRight"), g.css("paddingBottom"), g.css("paddingLeft")];
					this.borderDif = c.map(d,
					function(k, m) {
						var l = parseInt(k, 10) || 0,
						n = parseInt(h[m], 10) || 0;
						return l + n
					})
				}
				if (c.browser.msie && !(!(c(f).is(":hidden") || c(f).parents(":hidden").length))) {
					continue
				}
				g.css({
					height: (f.height() - this.borderDif[0] - this.borderDif[2]) || 0,
					width: (f.width() - this.borderDif[1] - this.borderDif[3]) || 0
				})
			}
		},
		_renderProxy: function() {
			var e = this.element,
			h = this.options;
			this.elementOffset = e.offset();
			if (this._helper) {
				this.helper = this.helper || c('<div style="overflow:hidden;"></div>');
				var d = c.browser.msie && c.browser.version < 7,
				f = (d ? 1 : 0),
				g = (d ? 2 : -1);
				this.helper.addClass(this._helper).css({
					width: this.element.outerWidth() + g,
					height: this.element.outerHeight() + g,
					position: "absolute",
					left: this.elementOffset.left - f + "px",
					top: this.elementOffset.top - f + "px",
					zIndex: ++h.zIndex
				});
				this.helper.appendTo("body").disableSelection()
			} else {
				this.helper = this.element
			}
		},
		_change: {
			e: function(f, e, d) {
				return {
					width: this.originalSize.width + e
				}
			},
			w: function(g, e, d) {
				var i = this.options,
				f = this.originalSize,
				h = this.originalPosition;
				return {
					left: h.left + e,
					width: f.width - e
				}
			},
			n: function(g, e, d) {
				var i = this.options,
				f = this.originalSize,
				h = this.originalPosition;
				return {
					top: h.top + d,
					height: f.height - d
				}
			},
			s: function(f, e, d) {
				return {
					height: this.originalSize.height + d
				}
			},
			se: function(f, e, d) {
				return c.extend(this._change.s.apply(this, arguments), this._change.e.apply(this, [f, e, d]))
			},
			sw: function(f, e, d) {
				return c.extend(this._change.s.apply(this, arguments), this._change.w.apply(this, [f, e, d]))
			},
			ne: function(f, e, d) {
				return c.extend(this._change.n.apply(this, arguments), this._change.e.apply(this, [f, e, d]))
			},
			nw: function(f, e, d) {
				return c.extend(this._change.n.apply(this, arguments), this._change.w.apply(this, [f, e, d]))
			}
		},
		_propagate: function(e, d) {
			c.ui.plugin.call(this, e, [d, this.ui()]); (e != "resize" && this._trigger(e, d, this.ui()))
		},
		plugins: {},
		ui: function() {
			return {
				originalElement: this.originalElement,
				element: this.element,
				helper: this.helper,
				position: this.position,
				size: this.size,
				originalSize: this.originalSize,
				originalPosition: this.originalPosition
			}
		}
	});
	c.extend(c.ui.resizable, {
		version: "1.8.1"
	});
	c.ui.plugin.add("resizable", "alsoResize", {
		start: function(e, f) {
			var d = c(this).data("resizable"),
			h = d.options;
			var g = function(i) {
				c(i).each(function() {
					c(this).data("resizable-alsoresize", {
						width: parseInt(c(this).width(), 10),
						height: parseInt(c(this).height(), 10),
						left: parseInt(c(this).css("left"), 10),
						top: parseInt(c(this).css("top"), 10)
					})
				})
			};
			if (typeof(h.alsoResize) == "object" && !h.alsoResize.parentNode) {
				if (h.alsoResize.length) {
					h.alsoResize = h.alsoResize[0];
					g(h.alsoResize)
				} else {
					c.each(h.alsoResize,
					function(i, j) {
						g(i)
					})
				}
			} else {
				g(h.alsoResize)
			}
		},
		resize: function(f, h) {
			var e = c(this).data("resizable"),
			i = e.options,
			g = e.originalSize,
			k = e.originalPosition;
			var j = {
				height: (e.size.height - g.height) || 0,
				width: (e.size.width - g.width) || 0,
				top: (e.position.top - k.top) || 0,
				left: (e.position.left - k.left) || 0
			},
			d = function(l, m) {
				c(l).each(function() {
					var p = c(this),
					q = c(this).data("resizable-alsoresize"),
					o = {},
					n = m && m.length ? m: ["width", "height", "top", "left"];
					c.each(n || ["width", "height", "top", "left"],
					function(r, t) {
						var s = (q[t] || 0) + (j[t] || 0);
						if (s && s >= 0) {
							o[t] = s || null
						}
					});
					if (/relative/.test(p.css("position")) && c.browser.opera) {
						e._revertToRelativePosition = true;
						p.css({
							position: "absolute",
							top: "auto",
							left: "auto"
						})
					}
					p.css(o)
				})
			};
			if (typeof(i.alsoResize) == "object" && !i.alsoResize.nodeType) {
				c.each(i.alsoResize,
				function(l, m) {
					d(l, m)
				})
			} else {
				d(i.alsoResize)
			}
		},
		stop: function(e, f) {
			var d = c(this).data("resizable");
			if (d._revertToRelativePosition && c.browser.opera) {
				d._revertToRelativePosition = false;
				el.css({
					position: "relative"
				})
			}
			c(this).removeData("resizable-alsoresize-start")
		}
	});
	c.ui.plugin.add("resizable", "animate", {
		stop: function(h, m) {
			var n = c(this).data("resizable"),
			i = n.options;
			var g = n._proportionallyResizeElements,
			d = g.length && (/textarea/i).test(g[0].nodeName),
			e = d && c.ui.hasScroll(g[0], "left") ? 0 : n.sizeDiff.height,
			k = d ? 0 : n.sizeDiff.width;
			var f = {
				width: (n.size.width - k),
				height: (n.size.height - e)
			},
			j = (parseInt(n.element.css("left"), 10) + (n.position.left - n.originalPosition.left)) || null,
			l = (parseInt(n.element.css("top"), 10) + (n.position.top - n.originalPosition.top)) || null;
			n.element.animate(c.extend(f, l && j ? {
				top: l,
				left: j
			}: {}), {
				duration: i.animateDuration,
				easing: i.animateEasing,
				step: function() {
					var o = {
						width: parseInt(n.element.css("width"), 10),
						height: parseInt(n.element.css("height"), 10),
						top: parseInt(n.element.css("top"), 10),
						left: parseInt(n.element.css("left"), 10)
					};
					if (g && g.length) {
						c(g[0]).css({
							width: o.width,
							height: o.height
						})
					}
					n._updateCache(o);
					n._propagate("resize", h)
				}
			})
		}
	});
	c.ui.plugin.add("resizable", "containment", {
		start: function(e, q) {
			var s = c(this).data("resizable"),
			i = s.options,
			k = s.element;
			var f = i.containment,
			j = (f instanceof c) ? f.get(0) : (/parent/.test(f)) ? k.parent().get(0) : f;
			if (!j) {
				return
			}
			s.containerElement = c(j);
			if (/document/.test(f) || f == document) {
				s.containerOffset = {
					left: 0,
					top: 0
				};
				s.containerPosition = {
					left: 0,
					top: 0
				};
				s.parentData = {
					element: c(document),
					left: 0,
					top: 0,
					width: c(document).width(),
					height: c(document).height() || document.body.parentNode.scrollHeight
				}
			} else {
				var m = c(j),
				h = [];
				c(["Top", "Right", "Left", "Bottom"]).each(function(p, o) {
					h[p] = b(m.css("padding" + o))
				});
				s.containerOffset = m.offset();
				s.containerPosition = m.position();
				s.containerSize = {
					height: (m.innerHeight() - h[3]),
					width: (m.innerWidth() - h[1])
				};
				var n = s.containerOffset,
				d = s.containerSize.height,
				l = s.containerSize.width,
				g = (c.ui.hasScroll(j, "left") ? j.scrollWidth: l),
				r = (c.ui.hasScroll(j) ? j.scrollHeight: d);
				s.parentData = {
					element: j,
					left: n.left,
					top: n.top,
					width: g,
					height: r
				}
			}
		},
		resize: function(f, p) {
			var s = c(this).data("resizable"),
			h = s.options,
			e = s.containerSize,
			n = s.containerOffset,
			l = s.size,
			m = s.position,
			q = s._aspectRatio || f.shiftKey,
			d = {
				top: 0,
				left: 0
			},
			g = s.containerElement;
			if (g[0] != document && (/static/).test(g.css("position"))) {
				d = n
			}
			if (m.left < (s._helper ? n.left: 0)) {
				s.size.width = s.size.width + (s._helper ? (s.position.left - n.left) : (s.position.left - d.left));
				if (q) {
					s.size.height = s.size.width / h.aspectRatio
				}
				s.position.left = h.helper ? n.left: 0
			}
			if (m.top < (s._helper ? n.top: 0)) {
				s.size.height = s.size.height + (s._helper ? (s.position.top - n.top) : s.position.top);
				if (q) {
					s.size.width = s.size.height * h.aspectRatio
				}
				s.position.top = s._helper ? n.top: 0
			}
			s.offset.left = s.parentData.left + s.position.left;
			s.offset.top = s.parentData.top + s.position.top;
			var k = Math.abs((s._helper ? s.offset.left - d.left: (s.offset.left - d.left)) + s.sizeDiff.width),
			r = Math.abs((s._helper ? s.offset.top - d.top: (s.offset.top - n.top)) + s.sizeDiff.height);
			var j = s.containerElement.get(0) == s.element.parent().get(0),
			i = /relative|absolute/.test(s.containerElement.css("position"));
			if (j && i) {
				k -= s.parentData.left
			}
			if (k + s.size.width >= s.parentData.width) {
				s.size.width = s.parentData.width - k;
				if (q) {
					s.size.height = s.size.width / s.aspectRatio
				}
			}
			if (r + s.size.height >= s.parentData.height) {
				s.size.height = s.parentData.height - r;
				if (q) {
					s.size.width = s.size.height * s.aspectRatio
				}
			}
		},
		stop: function(e, m) {
			var p = c(this).data("resizable"),
			f = p.options,
			k = p.position,
			l = p.containerOffset,
			d = p.containerPosition,
			g = p.containerElement;
			var i = c(p.helper),
			q = i.offset(),
			n = i.outerWidth() - p.sizeDiff.width,
			j = i.outerHeight() - p.sizeDiff.height;
			if (p._helper && !f.animate && (/relative/).test(g.css("position"))) {
				c(this).css({
					left: q.left - d.left - l.left,
					width: n,
					height: j
				})
			}
			if (p._helper && !f.animate && (/static/).test(g.css("position"))) {
				c(this).css({
					left: q.left - d.left - l.left,
					width: n,
					height: j
				})
			}
		}
	});
	c.ui.plugin.add("resizable", "ghost", {
		start: function(f, g) {
			var d = c(this).data("resizable"),
			h = d.options,
			e = d.size;
			d.ghost = d.originalElement.clone();
			d.ghost.css({
				opacity: 0.25,
				display: "block",
				position: "relative",
				height: e.height,
				width: e.width,
				margin: 0,
				left: 0,
				top: 0
			}).addClass("ui-resizable-ghost").addClass(typeof h.ghost == "string" ? h.ghost: "");
			d.ghost.appendTo(d.helper)
		},
		resize: function(e, f) {
			var d = c(this).data("resizable"),
			g = d.options;
			if (d.ghost) {
				d.ghost.css({
					position: "relative",
					height: d.size.height,
					width: d.size.width
				})
			}
		},
		stop: function(e, f) {
			var d = c(this).data("resizable"),
			g = d.options;
			if (d.ghost && d.helper) {
				d.helper.get(0).removeChild(d.ghost.get(0))
			}
		}
	});
	c.ui.plugin.add("resizable", "grid", {
		resize: function(d, l) {
			var n = c(this).data("resizable"),
			g = n.options,
			j = n.size,
			h = n.originalSize,
			i = n.originalPosition,
			m = n.axis,
			k = g._aspectRatio || d.shiftKey;
			g.grid = typeof g.grid == "number" ? [g.grid, g.grid] : g.grid;
			var f = Math.round((j.width - h.width) / (g.grid[0] || 1)) * (g.grid[0] || 1),
			e = Math.round((j.height - h.height) / (g.grid[1] || 1)) * (g.grid[1] || 1);
			if (/^(se|s|e)$/.test(m)) {
				n.size.width = h.width + f;
				n.size.height = h.height + e
			} else {
				if (/^(ne)$/.test(m)) {
					n.size.width = h.width + f;
					n.size.height = h.height + e;
					n.position.top = i.top - e
				} else {
					if (/^(sw)$/.test(m)) {
						n.size.width = h.width + f;
						n.size.height = h.height + e;
						n.position.left = i.left - f
					} else {
						n.size.width = h.width + f;
						n.size.height = h.height + e;
						n.position.top = i.top - e;
						n.position.left = i.left - f
					}
				}
			}
		}
	});
	var b = function(d) {
		return parseInt(d, 10) || 0
	};
	var a = function(d) {
		return ! isNaN(parseInt(d, 10))
	}
})(jQuery);
if (window.FR == null) {
	window.FR = {};
}
$.extend(FR, {
	servletURL: '/ReportServer',
	serverURL: 'http://127.0.0.1:8080',
	server: 'http://127.0.0.1:8080',
	PROJECTNAME: ''
});;
if (window.FR == null) {
	window.FR = {}
}
$.extend(FR, {
	i18n: {
		Executing_Report: '报表计算中',
		Loading: '正在加载',
		File_Upload: '文件上传',
		Not_Allow_To_Upload_File: '是禁止上传的文件类型',
		Allow_To_Upload_Contains: '允许上传的文件包括',
		Denied_To_Upload_Contains: '禁止上传的文件包括',
		Upload: '上传',
		HJS_Send_Successfully: '发送成功',
		HJS_Send_Failed: '发送失败',
		Print: '打印',
		Printing: '正在打印',
		Loading_Component: '正在加载控件',
		Loading_PDF: '正在加载PDF',
		Loading_Applet: '正在加载Applet',
		Loading_Data: '正在加载数据',
		PageSetup_Page: '页面',
		OK: '确定',
		Clear: '清除',
		Choose: '选择',
		Choose_All: '全选',
		Choose_None: '不选',
		Deselect_All: '反选',
		Env_Invalid_User_or_Password: '用户名或密码错误',
		Start_Print: '开始打印',
		Setting: '选项',
		NOT_NULL: '不能为空',
		Not_In_List: '值不在下拉列表内',
		Error_Input_Value: '错误的输入值',
		Cancel: '取消',
		Chart: '图表',
		Alert: '警告',
		No_File_Warning: '文件不能为空',
		Please_Select: '请选择',
		Print_To_Fit_Paper_Size: '根据纸张大小缩放打印',
		Print_As_Image: '以图片方式打印( 针式打印机推荐使用 )',
		Name4Empty: '空值',
		Waiting: 'Waiting',
		Calculating: '处理中',
		No_Data: '没有数据',
		Export_PDF: 'PDF格式',
		Export_Excel: 'Excel格式',
		Export_Word: 'Word',
		Page: '页面',
		PageSetup_Page_Setup: '页面设置',
		Report: '报表',
		ReportWriteAttr_Msg: '没有设置填报属性',
		ReportServerP_Print_Server: '打印[服务器]',
		Email: '邮件',
		Tooltips: '提示',
		LargeData_Page_Info: '请耐心等待文件的生成，所花费的时间将视数据量的大小而定，在此过程中请勿关闭当前页面或浏览器。',
		Successfully: '成功',
		Failed: '失败',
		HTML_Write_Content_Save_Successfully: '填写内容保存成功',
		HTML_Write_Content_Save_Failed: '填写内容保存失败',
		Info: '信息',
		Import_Excel: 'Excel输入',
		Import_Failed: '导入失败',
		Import_Excel_Message: '需要选择一个Excel文件来源',
		Error: '错误',
		Verify_Success: '校验成功',
		Verify_Message: '错误信息',
		Verify_Data_Verify: '数据校验',
		Verify_Expression: '校验公式',
		Frozen: '冻结',
		Unfrozen: '解冻',
		UnloadCheckMsg: '有数据没有提交',
		Submitsuccess: '提交成功',
		Custom: '自定义',
		Value_Not_Match: '键入值不符合规范',
		Value_Must_Be_A_Number: '值必须是一个数字',
		Value_Is_Less_Than_Minimum: '值小于允许输入的最小值',
		Value_Is_Larger_Than_Maximum: '值大于允许输入的最大值',
		Value_Cannot_Be_Decimal: '必须为整数',
		Value_Cannot_Be_Negative: '不能为负数',
		Value_DecimalNumber_Out: '小数位过多',
		Invalid_Cell: '无效的单元格',
		HJS_All_Pages: '所有页',
		HJS_Current_Page: '当前页',
		HF_The_Page_Number: '页码',
		is: '为',
		HJS_Specified_Pages: '指定页',
		Example: '例如',
		Privilege_Name_Cannot_Be_Null: '名字不能为空',
		Privilege_Input_A_Name: '请输入一个名字',
		Privilege_Selected_None_Of_Any_Items: '没有选中任何一项',
		Info: '信息',
		failpass: '当前版本无此功能，请使用高级版本。',
		Preview: '预览',
		Sunday: '星期日',
		Monday: '星期一',
		Tuesday: '星期二',
		Wednesday: '星期三',
		Thursday: '星期四',
		Friday: '星期五',
		Saturday: '星期六',
		Sun: '日',
		Mon: '一',
		Tue: '二',
		Wed: '三',
		Thu: '四',
		Fri: '五',
		Sat: '六',
		January: '一月',
		February: '二月',
		March: '三月',
		April: '四月',
		May: '五月',
		June: '六月',
		July: '七月',
		August: '八月',
		September: '九月',
		October: '十月',
		November: '十一月',
		December: '十二月',
		Jan: '一',
		Feb: '二',
		Mar: '三',
		Apr: '四',
		Short_May: '五',
		Jun: '六',
		Jul: '七',
		Aug: '八',
		Sep: '九',
		Oct: '十',
		Nov: '十一',
		Dec: '十二',
		Year: '年',
		Month: '月',
		Day: '日',
		Hour: '时',
		Minute: '分',
		Second: '秒',
		Prev_Year: '上一年',
		Prev_Month: '上一月',
		Next_Month: '下一月',
		Next_Year: '下一年',
		Select_Date: '选择日期',
		Drag_To_Move: '点此拉动',
		Today: '今天',
		First_Of_Week: '为周起始',
		Close: '关闭',
		Week: '周',
		Time: '时间',
		Click_To_Change_Value: '点击改变值',
		Parameter_String: '字符串',
		Parameter_Integer: '整型',
		Parameter_Double: '双精度型',
		Parameter_Boolean: '布尔型',
		Parameter_Date: '日期',
		Parameter_Formula: '公式',
		DataFunction_None: '无',
		Unit: '单位',
		Unit_Ten: '十',
		Unit_Hundred: '百',
		Unit_Thousand: '千',
		Unit_Ten_Thousand: '万',
		Unit_Million: '百万',
		Unit_Hundred_Million: '亿',
		Unit_Billion: '十亿',
		SetPrinter_Offset: '打印机偏移',
		SaveiResult: '保存结果',
		SaveiDefinition: '保存定义',
		Normal: '普通',
		LightBlue: '淡蓝',
		Green: '青绿',
		Purple: '粉紫',
		Youhavenotmadeareport: '您还没有制作报表',
		Select: '选择',
		PleaseDrag: '请拖入行列字段、汇总字段',
		ConditionB_equals: '等于',
		ConditionB_does_not_equal_to: '不等于',
		ConditionB_is_greater_than: '大于',
		ConditionB_is_greater_than_or_equal_to: '大于或等于',
		ConditionB_is_less_than: '小于',
		ConditionB_is_less_than_or_equal_to: '小于或等于',
		ConditionB_begins_with: '开头是',
		ConditionB_does_not_begin_with: '开头不是',
		ConditionB_ends_with: '结尾是',
		ConditionB_does_not_end_with: '结尾不是',
		ConditionB_contains: '包含',
		ConditionB_does_not_contain: '不包含',
		ConditionB_in: '包含于',
		ConditionB_does_not_in: '不包含于',
		Parameter_String: '字符串',
		Number: '数字',
		Date: '日期',
		Sort_Original: '不排序',
		Sort_Ascending: '升序',
		Sort_Descending: '降序',
		DataFunction_Sum: '求和',
		DataFunction_Average: '平均',
		DataFunction_Max: '最大值',
		DataFunction_Min: '最小值',
		DataFunction_Count: '个数',
		Report_Title: '报表标题',
		Report_Subtitle: '报表副标题',
		Add: '增加',
		Delete: '删除',
		Edit: '编辑',
		File_Display_Name: '文件显示名',
		File_Description: '文件描述',
		Please_enter_the_export_file_name: '请输入导出文件名',
		Choose_to_generate_file_displayed_in_the_virtual_directory: '选择生成文件显示在的虚拟目录',
		Field_Filtration: '字段过滤',
		Select_Fields: '选择字段',
		FormulaD_Operations: '操作符',
		Data_Type: '数据格式',
		ChartF_Values: '数值',
		DashBoard_AdhocSetting: 'B/S设计器工具',
		Please_Install: '请安装',
		Restart_Browser_After_Installation: '安装之后请重启浏览器.',
		The_Num: '第',
		Page_Number: '页',
		Page_Total: '共',
		Complex_Page_Number: '页码',
		Complex_Number_of_Page: '总页数',
		Load_Pull_Up: '向上拉动加载更多数据',
		Release_To_Load_Data: '松开将加载数据',
		Input_To_Search: '搜索选项结果',
		Hide: '隐藏',
		Show: '显示',
		Back: '返回',
		Bulletin: '公告',
		Calendar: '日历'
	}
});;
if (window.FR == null) {
	window.FR = {}
}
$.extend(FR, {
	$defaultImport: function() {
		var a = arguments;
		if (a[0] != null) {
			a[0] = FR.servletURL + "?op=resource&resource=" + a[0]
		}
		this.$import.apply(null, a)
	},
	$import: function() {
		var b = {};
		function a(d, c) {
			var f = $("head script");
			$.each(f,
			function(g, h) {
				if (h.src.indexOf(d) != -1) {
					b[d] = true
				}
			});
			var e = $("head link");
			$.each(e,
			function(g, h) {
				if (h.href.indexOf(d) != -1 && c) {
					b[d] = false;
					$(h).remove()
				}
			})
		}
		return function(g, e, c) {
			a(g, c);
			if (b[g] === true) {
				return
			}
			if (e === "css") {
				var f = document.createElement("link");
				f.rel = "stylesheet";
				f.type = "text/css";
				f.href = g;
				var d = document.getElementsByTagName("head")[0];
				d.appendChild(f);
				b[g] = true
			} else {
				$.ajax({
					url: g,
					dataType: "script",
					async: false,
					complete: function(i, h) {
						if (h == "success") {
							b[g] = true
						}
					}
				})
			}
		}
	} ()
});
$.extend(FR, {
	Events: {
		NOSELECT: "NOSELECT",
		APPENDDATA: "APPENDDATA",
		EDITDATA: "EDITDATA",
		REMOVEDATA: "REMOVEDATA",
		REMOVESELF: "REMOVESELF",
		RESIZE: "_resize",
		BEFOREEDIT: "beforeedit",
		AFTEREDIT: "afteredit",
		VALUECHANGE: "valuechange",
		STOPEDIT: "stopedit",
		CHANGE: "change",
		EXPAND: "expand",
		COLLAPSE: "collapse",
		STATECHANGE: "statechange",
		CALLBACK: "callback",
		CLICK: "click",
		BEFORESTATECHANGE: "beforestatechange",
		DEALSELECTEDNODES: "dealselectednodes",
		AFTERBUILD: "afterbuild",
		AFTERREAD: "afterread",
		APPENDDATA: "appenddata",
		MOVE: "move",
		DEFAULTINIT: "defaultinit",
		TABCHANGE: "tabchange",
		TABCHANGESTART: "tabchangestart",
		SCROLLCHANGE: "scrollchange",
		AFTERINIT: "afterinit",
		STARTLOAD: "startload",
		AFTERLOAD: "afterload",
		INIT: "init",
		CELLCALUECHANGE: "cellvaluechange",
		BS: "beforesubmit",
		AS: "aftersubmit",
		SC: "submitcomplete",
		SF: "submitfailure",
		SS: "submitsuccess",
		BVW: "beforeverifywrite",
		AVW: "afterverifywrite",
		AV: "afterverify",
		BW: "beforewrite",
		AW: "afterwrite",
		WS: "writesuccess",
		WF: "writefailure",
		BA: "beforeappend",
		AA: "afterappend",
		BD: "beforedelete",
		AD: "beforedelete",
		BTOPDF: "beforetopdf",
		ATOPDF: "aftertopdf",
		BTOEXCEL: "beforetoexcel",
		ATOEXCEL: "aftertoexcel",
		BTOWORD: "beforetoword",
		ATOWORD: "aftertoword",
		BTOIMAGE: "beforetoimage",
		ATOIMAGE: "aftertoimage",
		BTOHTML: "beforetohtml",
		ATOHTML: "aftertohtml",
		BPDFPRINT: "beforepdfprint",
		APDFPRINT: "afterpdfprint",
		BFLASHPRINT: "beforeflashprint",
		AFLASHPRINT: "afterflashprint",
		BAPPLETPRINT: "beforeappletprint",
		AAPPLETPRINT: "afterappletprint",
		BSEVERPRINT: "beforeserverprint",
		ASERVERPRINT: "afterserverprint",
		BEMAIL: "beforeemail",
		AEMAIL: "afteremail"
	}
});
$.extend(Array.prototype, {
	indexOf: function(c) {
		for (var b = 0,
		a = this.length; b < a; b++) {
			if (this[b] == c) {
				return b
			}
		}
		return - 1
	},
	remove: function(b) {
		var a = this.indexOf(b);
		if (a != -1) {
			this.splice(a, 1)
		}
		return this
	}
});
$.fn.swapClass = function(b, a) {
	return this.removeClass(b).addClass(a)
};
$.fn.switchClass = function(b, a) {
	if (this.hasClass(b)) {
		return (a || a == 0) ? this.swapClass(b, a) : false
	} else {
		return this.swapClass(a, b)
	}
};
$.fn.isChildOf = function(a) {
	return (this.parents(a).length > 0)
};
$.fn.isChildAndSelfOf = function(a) {
	return (this.closest(a).length > 0)
};
$.extend($.Event.prototype, {
	stopEvent: function() {
		this.stopPropagation();
		this.preventDefault()
	}
});
$.extend(String, {
	escape: function(a) {
		return a.replace(/('|\\)/g, "\\$1")
	},
	leftPad: function(d, b, c) {
		var a = new String(d);
		if (!c) {
			c = " "
		}
		while (a.length < b) {
			a = c + a
		}
		return a.toString()
	},
	format: function(b) {
		var a = Array.prototype.slice.call(arguments, 1);
		return b.replace(/\{(\d+)\}/g,
		function(c, d) {
			return a[d]
		})
	}
});
$.extend(String.prototype, {
	indexReg: function(f, m) {
		var mm = (m == false) ? "i": "";
		var re = eval("/" + f + "/" + mm);
		var rt = this.match(re);
		return (rt == null) ? -1 : rt.index
	},
	startWith: function(a) {
		if (a == null || a == "" || this.length == 0 || a.length > this.length) {
			return false
		}
		if (this.substr(0, a.length) == a) {
			return true
		} else {
			return false
		}
		return true
	},
	endWith: function(a) {
		if (a == null || a == "" || this.length == 0 || a.length > this.length) {
			return false
		}
		if (this.substring(this.length - a.length) == a) {
			return true
		} else {
			return false
		}
		return true
	},
	getQuery: function(a) {
		var b = new RegExp("(^|&)" + a + "=([^&]*)(&|$)");
		var c = this.substr(this.indexOf("?") + 1).match(b);
		if (c != null) {
			return unescape(c[2])
		}
		return null
	},
	appendQuery: function(a) {
		if (!a) {
			return this
		}
		var b = this;
		if (b.indexOf("?") == -1) {
			b += "?"
		}
		if (b.endWith("?") == false) {
			b += "&"
		}
		$.each(a,
		function(c, d) {
			if (typeof(c) == "string") {
				b += c + "=" + d + "&"
			}
		});
		b = b.substr(0, b.length - 1);
		return b
	}
});
$.extend(Function.prototype, {
	createCallback: function() {
		var a = arguments;
		var b = this;
		return function() {
			return b.apply(window, a)
		}
	},
	createDelegate: function(c, b, a) {
		var d = this;
		return function() {
			var f = b || arguments;
			if (a === true) {
				f = Array.prototype.slice.call(arguments, 0);
				f = f.concat(b)
			} else {
				if (typeof a == "number") {
					f = Array.prototype.slice.call(arguments, 0);
					var e = [a, 0].concat(b);
					Array.prototype.splice.apply(f, e)
				}
			}
			return d.apply(c || window, f)
		}
	},
	createInterceptor: function(b, a) {
		if (typeof b != "function") {
			return this
		}
		var c = this;
		return function() {
			b.target = this;
			b.method = c;
			if (b.apply(a || this || window, arguments) === false) {
				return
			}
			return c.apply(this || window, arguments)
		}
	},
	defer: function(c, e, b, a) {
		var d = this.createDelegate(e, b, a);
		if (c) {
			return setTimeout(d, c)
		}
		d();
		return 0
	},
	createSequence: function(b, a) {
		if (typeof b != "function") {
			return this
		}
		var c = this;
		return function() {
			var d = c.apply(this || window, arguments);
			b.apply(a || this || window, arguments);
			return d
		}
	},
	getNameArguments: function() {
		var d = this.toString();
		var c = /function[^\(]*\(([^\)]*)\)/.exec(d);
		var b = [];
		if (c != null) {
			b = c[1].split(",")
		}
		return $.map(b,
		function(a) {
			return $.trim(a)
		})
	},
	arguments2Json: function() {
		var a = arguments;
		var b = this.getNameArguments();
		var c = {};
		$.each(b,
		function(d, e) {
			if (a.length > d) {
				c[e] = a[d]
			}
		});
		return c
	}
});
if (window.FR == null) {
	window.FR = {}
} (function() {
	var a = (typeof Touch != "undefined");
	var b = a ? "touchstart": "mousedown";
	var e = a ? "touchmove": "mousemove";
	var d = a ? "touchend": "mouseup";
	var c = a ? "touchcancel": "mouseout"
})(); (function() {
	$.extend(FR, {
		cjkEncodeDO: function(a) {
			if ($.isPlainObject(a)) {
				$.each(a,
				function(c, b) {
					if (! (typeof b == "string")) {
						b = FR.jsonEncode(b)
					}
					c = FR.cjkEncode(c);
					a[c] = FR.cjkEncode(b)
				})
			}
			return a
		},
		ajax: function(a) {
			if (a) {
				a.data = FR.cjkEncodeDO(a.data)
			}
			$.ajax(a)
		},
		get: function(a, c, d, b) {
			if ($.isFunction(c)) {
				b = b || d;
				d = c;
				c = undefined
			}
			if (c) {
				c = FR.cjkEncodeDO(c)
			}
			$.get(a, c, d, b)
		},
		param: function(b, c) {
			b = FR.cjkEncodeDO(b);
			return $.param(b)
		},
		url: function(a, b) {
			if ($.isPlainObject(b)) {
				return a + "?" + FR.param(b)
			}
			return FR.cjkEncode(a)
		},
		buildServletUrl: function(b, a) {
			if (a) {
				b = $.extend({
					_: ("" + new Date().getTime())
				},
				b)
			}
			return FR.url(FR.servletURL, b)
		},
		resource: function(a) {
			return FR.buildServletUrl({
				op: "resource",
				resource: a
			})
		}
	})
})(); (function() {
	$.extend(FR, {
		tc: function(b, a) {
			try {
				return b.call(a)
			} catch(c) {
				FR.Msg.toast(c.toString());
				throw c
			}
		},
		Msg: function() {
			return {
				verticalOffset: 0.77,
				horizontalOffset: 1,
				repositionOnResize: true,
				overlayOpacity: 0.01,
				overlayColor: "#FFF",
				draggable: true,
				okButton: FR.i18n.OK,
				cancelButton: FR.i18n.Cancel,
				dialogClass: null,
				alert: function(b, a, c) {
					FR.Keys.enable(false);
					if (b == null) {
						b = "Alert"
					}
					this._show(b, a, null, "alert",
					function(d) {
						FR.Keys.enable(true);
						if ($.isFunction(c)) {
							c(d)
						}
					},
					-1);
					if (typeof c == "number" && c > 0) {
						setTimeout(function() {
							FR.Msg._hide()
						},
						c)
					}
				},
				confirm: function(b, a, d, c) {
					FR.Keys.enable(false);
					if (b == null) {
						b = "Confirm"
					}
					this._show(b, a, null, "confirm",
					function(e) {
						FR.Keys.enable(true);
						if (d) {
							d(e)
						}
					},
					c ? c: -1)
				},
				prompt: function(c, a, b, e, d) {
					FR.Keys.enable(false);
					if (c == null) {
						c = "Prompt"
					}
					this._show(c, a, b, "prompt",
					function(f) {
						FR.Keys.enable(true);
						if (e) {
							e(f)
						}
					},
					d ? d: -1)
				},
				toast: function(d) {
					var c = -300;
					if ($.browser.msie && parseInt($.browser.version) < 8) {
						c = -370
					}
					var b = $("body").children("div.toast");
					if (b.length == 0) {
						var e = 60;
						if ($.browser.msie && parseInt($.browser.version) <= 6) {
							e += $("body")[0].scrollTop
						}
						b = $("<div/>").addClass("toast").css({
							right: c,
							top: e
						}).appendTo("body")
					}
					b.text(d);
					var a = $("body").children("div.toastIcon");
					if (a.length == 0) {
						a = $("<div/>").addClass("toastIcon").css({
							right: 0,
							top: b.css("top"),
							"z-index": b.css("z-index") + 1
						}).appendTo("body");
						new FR.IconButton({
							width: a.css("width"),
							height: a.css("height"),
							imgsrc: "fr_show_toast",
							renderEl: $("<div/>").appendTo(a),
							handler: function() {
								b.animate({
									right: -4
								},
								"slow");
								setTimeout(function() {
									b.animate({
										right: c
									},
									"slow")
								},
								5000)
							}
						})
					}
					a.hide();
					b.animate({
						right: -15
					},
					"slow");
					setTimeout(function() {
						b.animate({
							right: c
						},
						"slow",
						function() {
							a.show();
							a.fadeOut(5000)
						})
					},
					5000)
				},
				_show: function(k, a, l, h, m, b) {
					this._hide();
					this._overlay("show");
					$("BODY").append('<div id="popup_container"><h1 id="popup_title"></h1><div id="popup_content"><div id="popup_message"></div></div></div>');
					if (this.dialogClass) {
						$("#popup_container").addClass(this.dialogClass)
					}
					var j = ($.browser.msie) ? "absolute": "fixed";
					$("#popup_container").css({
						position: j,
						zIndex: 99999,
						padding: 0,
						margin: 0
					});
					$("#popup_title").html(k);
					$("#popup_content").addClass(h);
					$("#popup_message").text(a);
					$("#popup_message").html($("#popup_message").text().replace(/\n/g, "<br />"));
					var n = $("#popup_container").outerWidth();
					var i = $("#popup_container").outerWidth();
					$("#popup_container").css({
						minWidth: b > 0 ? Math.max(n, b) : n,
						maxWidth: b > 0 ? Math.max(i, b) : i
					});
					this._reposition();
					this._maintainPosition(true);
					switch (h) {
					case "alert":
						var d = $("<div id='popup_panel'>").appendTo("#popup_content");
						var c = $("<button type='button'>").appendTo($("<span style='display:inline-block;'></span>").appendTo(d)).text(this.okButton).bind("click",
						function() {
							FR.Msg._hide();
							m(true)
						});
						c.focus();
						c.keydown(function(o) {
							if (o.keyCode == 13 || o.keyCode == 27) {
								c.trigger("click")
							}
						});
						break;
					case "confirm":
						var d = $("<div id='popup_panel'>").appendTo("#popup_content");
						var c = $("<button type='button'>").appendTo($("<span style='display:inline-block;margin-right:10px;'></span>").appendTo(d)).text(this.okButton).bind("click",
						function() {
							FR.Msg._hide();
							m(true)
						});
						var g = $("<button type='button'>").appendTo($("<span style='display:inline-block;'></span>").appendTo(d)).text(this.cancelButton).bind("click",
						function() {
							FR.Msg._hide();
							m(false)
						});
						$("#popup_ok").focus();
						break;
					case "prompt":
						$("#popup_message").append('<br /><input type="text" size="30" id="popup_prompt" />');
						var d = $("<div id ='popup_panel'>").appendTo("#popup_content");
						var c = $("<button type='button'>").appendTo($("<span style='display:inline-block;margin-right:10px;'></span>").appendTo(d)).text(this.okButton).bind("click",
						function() {
							var e = $("#popup_prompt").val();
							FR.Msg._hide();
							if (m) {
								m(e)
							}
						});
						var g = $("<button type='button'>").appendTo($("<span style='display:inline-block;'></span>").appendTo(d)).text(this.cancelButton).bind("click",
						function() {
							FR.Msg._hide();
							if (m) {
								m(null)
							}
						});
						$("#popup_prompt").width($("#popup_message").width());
						if (l) {
							$("#popup_prompt").val(l)
						}
						$("#popup_prompt").focus().select();
						break
					}
					if (this.draggable) {
						try {
							$("#popup_container").draggable({
								handle: $("#popup_title")
							});
							$("#popup_title").css({
								cursor: "move"
							})
						} catch(f) {}
					}
				},
				_hide: function() {
					$("#popup_container").remove();
					this._overlay("hide");
					this._maintainPosition(false)
				},
				_overlay: function(a) {
					switch (a) {
					case "show":
						this._overlay("hide");
						$("BODY").append('<div id="popup_overlay"></div>');
						$("#popup_overlay").css({
							position:
							"absolute",
							zIndex: 99998,
							top: "0px",
							left: "0px",
							width: "100%",
							height: $(document).height(),
							background: this.overlayColor,
							opacity: this.overlayOpacity
						});
						break;
					case "hide":
						$("#popup_overlay").remove();
						break
					}
				},
				_reposition: function() {
					var b = (($(window).height() / 2) - ($("#popup_container").outerHeight() / 2)) + this.verticalOffset;
					b = b * this.verticalOffset;
					var a = (($(window).width() / 2) - ($("#popup_container").outerWidth() / 2)) + this.horizontalOffset;
					a = a * this.horizontalOffset;
					if (b < 0) {
						b = 0
					}
					if (a < 0) {
						a = 0
					}
					if ($.browser.msie) {
						b = b + $(window).scrollTop()
					}
					$("#popup_container").css({
						top: b + "px",
						left: a + "px"
					});
					$("#popup_overlay").height($(document).height())
				},
				_maintainPosition: function(a) {
					if (this.repositionOnResize) {
						switch (a) {
						case true:
							$(window).bind("resize",
							function() {
								FR.Msg._reposition()
							});
							break;
						case false:
							$(window).unbind("resize");
							break
						}
					}
				}
			}
		} ()
	})
})(); (function() {
	$.extend(FR, {
		formulaEvaluator: function(c, a) {
			var b = a;
			return function(d) {
				if (d || !b) {
					b = FR.remoteEvaluate(c)
				}
				return b
			}
		},
		remoteEvaluate: function(b) {
			var a = null;
			FR.ajax({
				url: FR.servletURL,
				type: "POST",
				async: false,
				data: {
					op: "fr_base",
					cmd: "evaluate_formula",
					sessionID: FR.SessionMgr.getSessionID(),
					expression: b
				},
				timeout: 5000,
				complete: function(d, c) {
					a = FR.jsonDecode(d.responseText);
					a = a.result
				}
			});
			return a
		}
	})
})();
var COL_IDS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
$.extend(FR, {
	limitData: 500,
	emptyFn: function() {},
	constant: {
		success: "success",
		failure: "failure"
	},
	isWidthOrHeight: function(a) {
		if (typeof a == "number") {
			return a >= 0
		} else {
			if (typeof a == "string") {
				return /^\d{1,3}%$/.exec(a) || a == "auto" || /^\d+px$/.exec(a)
			}
		}
	},
	number2Percentage: function(a) {
		return (~~ (a * 100)) + "%"
	},
	isArray: function(b) {
		return Object.prototype.toString.call(b) == "[object Array]" || b instanceof jQuery
	},
	applyStyles: function(a, c) {
		if (c) {
			if (typeof c == "string") {
				var b = /\s?([a-z\-]*)\:\s?([^;]*);?/gi;
				var d;
				while ((d = b.exec(c)) != null) {
					a.css(d[1], d[2])
				}
			} else {
				if (typeof c == "object") {
					a.css(c)
				} else {
					if (typeof c == "function") {
						FR.applyStyles(dom, c.call())
					}
				}
			}
		}
	},
	showMenuByEvent: function(c, a, b) {
		if (b === true) {
			FR.showMenuByLocation(c, {
				left: a.clientX,
				top: a.clientY
			},
			{
				left: -2,
				top: -2
			})
		} else {
			if (a.target) {
				FR.showMenuByEl(c, $(a.target))
			} else {
				FR.showMenuByLocation(c, {
					left: a.clientX,
					top: a.clientY + 10
				})
			}
		}
	},
	isEmptyArray: function(a) {
		if ($.isArray(a)) {
			if (a.length == 0) {
				return true
			} else {
				if (a.length == 1) {
					return a[0] === ""
				}
			}
		}
		return false
	},
	showMenuByEl: function(e, a) {
		var d;
		if (a.fr_menu && a.fr_menu.menu && (FR.equals($.extend({
			xxxMMenu: true
		},
		e), a.fr_menu.opts) || a.fr_menu.menu.close())) {
			d = a.fr_menu.menu
		} else {
			e.$el = a;
			d = FR.createShortMenu(e);
			delete e.$el
		}
		var c = 1;
		if (a.offset().top < d.$menuRoot.height() + c || a.offset().top + a.height() + c + d.$menuRoot.height() < document.body.clientHeight) {
			var b = a.offset().top + a.height() + c
		} else {
			var b = a.offset().top - d.$menuRoot.height() - c
		}
		e.xxxMMenu = true;
		d.show();
		d.position(a.offset().left, b);
		a.fr_menu = {
			menu: d,
			opts: e
		}
	},
	showMenuByLocation: function(f, a, e) {
		if (!e) {
			e = {
				left: 0,
				top: 0
			}
		}
		var d = FR.createShortMenu(f);
		if (a.left < d.$menuRoot.width() + e.left || a.left + e.left + d.$menuRoot.width() < document.body.clientWidth) {
			var c = a.left + e.left
		} else {
			var c = a.left - d.$menuRoot.width() - e.left
		}
		if (a.top < d.$menuRoot.height() + e.top || a.top + e.top + d.$menuRoot.height() < document.body.clientHeight) {
			var b = a.top + e.top
		} else {
			var b = a.top - d.$menuRoot.height() - e.top
		}
		d.show();
		d.position(c, b)
	},
	versionRemind: function(c) {
		var a = FR.jsonDecode(c);
		if (a.exception == "failpass") {
			var b = this.i18n.failpass;
			if (a.func) {
				b = a.func + ", " + b
			}
			FR.Msg.toast(b);
			return false
		}
		return true
	},
	createShortMenu: function(a) {
		a = $.extend({
			minWidth: 80,
			destroyOnClose: true
		},
		a);
		return new FR.frMenu(a)
	},
	_chart_Related: function(e, c) {
		for (var b = 0; b < e.length; b++) {
			var f = FR._chart_GetID_Index(e[b].id);
			var d = FR.ChartManager[f[0]];
			if (d && d[f[1]]) {
				var a = (new Function("return " + e[b].chartAttr + ";"))();
				d[f[1]].changeData(a, c)
			}
		}
	},
	_chart_GetID_Index: function(c) {
		var b = c;
		var a = "";
		if (b.indexOf("__index__") >= 0) {
			a = b.substring(b.indexOf("__index__") + 9, b.length);
			b = b.substring(0, b.indexOf("__index__"))
		}
		if (b.indexOf("Chart") == 0 && b.indexOf("__") >= 0) {
			b = b.substring(b.indexOf("__") + 2, b.length)
		}
		return [b, a]
	},
	_chart_Install: function(b, d) {
		FR.ChartManager = FR.ChartManager || {};
		var a = FR._chart_GetID_Index(d);
		if (b instanceof FR.Widget) {
			if (FR.ChartManager[a[0]]) {
				delete FR.ChartManager[a[0]]
			}
			var c = FR.ChartManager[a[0]] = {};
			c = c || {};
			c.Widget = b
		} else {
			var c = FR.ChartManager[a[0]] = FR.ChartManager[a[0]] || {};
			c[a[1]] = b;
			b.inits()
		}
	},
	chart_Refresh: function() {
		var d = "";
		if (arguments.length <= 0) {
			return
		} else {
			if (arguments.length == 1) {
				d = FR._chart_GetID_Index(arguments[0]);
				d = d[0]
			} else {
				if (arguments.length == 2) {
					var a = arguments[0] + "__" + arguments[1];
					$.each(FR.ChartManager,
					function(f, e) {
						if (f.indexOf(a) >= 0) {
							d = f
						}
					})
				}
			}
		}
		var c = FR.ChartManager[d];
		if (c && c.Widget) {
			var b = c.Widget.curChart.idxNumber;
			if (c[b]) {
				c[b].refresh()
			}
		}
	},
	chart_Change_Parameter: function() {
		var d = "";
		var b = "";
		if (arguments.length <= 1) {
			return
		} else {
			if (arguments.length == 2) {
				d = FR._chart_GetID_Index(arguments[0]);
				d = d[0];
				b = arguments[1]
			} else {
				if (arguments.length == 3) {
					var a = arguments[0] + "__" + arguments[1];
					if (a.startWith("Cells")) {
						a = a + "__"
					}
					$.each(FR.ChartManager,
					function(f, e) {
						if (f.indexOf(a) >= 0) {
							d = f
						}
					});
					b = arguments[2]
				}
			}
		}
		b = b || "";
		var c = FR.ChartManager[d];
		if (c) {
			$.each(c,
			function(f, e) {
				if (e != c.Widget) {
					e.dealChartAjax(b)
				}
			})
		}
	},
	chart_Change_Index: function() {
		var d = "";
		var b = 0;
		if (arguments.length <= 1) {
			return
		} else {
			if (arguments.length == 2) {
				d = FR._chart_GetID_Index(arguments[0]);
				d = d[0];
				b = arguments[1]
			} else {
				if (arguments.length == 3) {
					var a = arguments[0] + "__" + arguments[1];
					$.each(FR.ChartManager,
					function(f, e) {
						if (f.indexOf(a) >= 0) {
							d = f
						}
					});
					b = arguments[2]
				}
			}
		}
		b = b || 0;
		var c = FR.ChartManager[d];
		if (c && c.Widget) {
			c.Widget.changeChartImage(b)
		}
	},
	doHyperlink: function() {
		function a(b) {
			new Function(unescape(b))()
		}
		return function(c, g, d) {
			if (FR.isArray(g)) {
				switch (g.length) {
				case 0:
					return;
				case 1:
					FR.doHyperlink(c, g[0]);
					break;
				default:
					var e = [];
					var b = [];
					for (var f = 0; f < g.length; f++) {
						if (g[f].data && g[f].data.indexOf("chart_Change_Parameter") >= 0) {
							e[e.length] = g[f]
						} else {
							b[b.length] = g[f]
						}
					}
					for (var f = 0; f < e.length; f++) {
						a(e[f].data)
					}
					switch (b.length) {
					case 0:
						return;
					case 1:
						FR.doHyperlink(c, b[0]);
						break;
					default:
						FR.showMenuByEvent({
							items:
							$.map(b,
							function(h) {
								return {
									src: h.name,
									handler: a.createCallback(h.data)
								}
							})
						},
						c, d || false)
					}
				}
			} else {
				a(g.data)
			}
		}
	} (),
	doHyperlinkByGet: function(b, a, d, c) {
		a = $.extend({
			_: new Date().getTime()
		},
		a);
		var e = "";
		$.each(a,
		function(f, g) {
			if (f != "__LOCALE__" && f != "REPORTLET" && f != "OP") {
				e += "&" + encodeURIComponent(f) + "=" + encodeURIComponent(g)
			}
		});
		if (b.indexOf("?") == -1) {
			b += "?";
			e = e.substring(1)
		}
		if (d && d == "_dialog") {
			this.showHyperlinkDialog(b + e, c)
		} else {
			window.open(b + e, d)
		}
	},
	showHyperlinkDialog: function(c, d) {
		var b = "<iframe src='' width='100%' height='100%' scrolling='no' frameborder='0'></iframe>";
		b = $(b).attr("src", c)[0];
		if (d.split(",").length > 2) {
			var e = d.split(",")[0];
			if (e.indexOf("=") != -1) {
				e = e.substring(e.indexOf("=") + 1, e.length)
			}
			var a = d.split(",")[1];
			if (a.indexOf("=") != -1) {
				a = a.substring(a.indexOf("=") + 1, a.length)
			}
		}
		FR.showDialog(" ", parseInt(e) || 600, parseInt(a) || 400, b)
	},
	doHyperlinkByPost: function(d, a, f, e) {
		if (this.$hyperlinkForm) {
			this.$hyperlinkForm.html("")
		} else {
			this.$hyperlinkForm = ($("<div>").appendTo($(document.body))).css({
				display: "none",
				position: "absolute"
			})
		}
		if (f == null) {
			f = "formresult"
		}
		var c = $("<form method='post' target='" + f + "' action='" + d + "'></form>").appendTo(this.$hyperlinkForm);
		var b = $('<input type="hidden" name="__parameters__" value=""/>');
		b.attr("value", FR.cjkEncode(FR.jsonEncode(a)));
		c.append(b);
		if (f && f == "_dialog") {
			var g = "";
			$.each(a,
			function(h, i) {
				g += "&" + encodeURIComponent(h) + "=" + encodeURIComponent(i)
			});
			if (d.indexOf("?") == -1) {
				d += "?";
				g = g.substring(1)
			}
			this.showHyperlinkDialog(d + g, e)
		} else {
			c.submit()
		}
	},
	isEmpty: function(c, b) {
		var a = c === null || c === "undefined";
		if (b === true) {
			return a
		}
		return a || c === ""
	},
	toRE: function(a) {
		return a.replace(/\\/g, "\\\\").replace(/\[/g, "\\[").replace(/\]/g, "\\]").replace(/\(/g, "\\(").replace(/\)/g, "\\)").replace(/\{/g, "\\{").replace(/\}/g, "\\}").replace(/\*/g, "\\*").replace(/\|/g, "\\|").replace(/\?/g, "\\?").replace(/\$/g, "\\$").replace(/\^/g, "\\^")
	},
	extend: function() {
		return function(e, c, d) {
			if (typeof c == "object") {
				d = c;
				c = e;
				e = function() {
					c.apply(this, arguments)
				}
			}
			var b = function() {},
			a = c.prototype;
			b.prototype = a;
			e.prototype = new b();
			e.superclass = a;
			$.extend(e.prototype, d);
			return e
		}
	} (),
	SessionMgr: function() {
		var a, b;
		$(window).unload(function() {
			$(window).unbind("unload", arguments.callee);
			if (a && b && FR.servletURL && b.rtype != "preview" && b.rtype != "bi") {
				var c = function(d) {
					$.ajax({
						async: false,
						url: FR.servletURL,
						data: {
							op: "closesessionid",
							sessionID: d
						}
					})
				};
				c(a)
			}
		});
		setInterval(function() {
			if (a && b && FR.servletURL) {
				$.ajax({
					url: FR.servletURL,
					data: {
						sessionID: a,
						_: new Date().getTime()
					}
				})
			}
		},
		30000);
		return {
			getSessionID: function() {
				return a
			},
			getContentPane: function() {
				return b
			},
			get: function() {
				return b
			},
			register: function(d, c) {
				a = d;
				b = c
			}
		}
	} (),
	CookieInfor: {
		getCookies: function() {
			var a = document.cookie;
			return unescape(a).split(";")
		},
		addCookie: function(b, d) {
			var a = new Date();
			a.setTime(a.getTime() + (30 * 24 * 3600 * 1000));
			var c = b + "=" + escape(d) + ";expires=" + a.toGMTString();
			document.cookie = c
		},
		removeCookie: function(b) {
			var a = new Date();
			a.setTime(a.getTime() - 1);
			document.cookie = b + "=;expires=" + a.toGMTString()
		},
		clearCookie: function() {
			var a = this;
			var b = this.getCookies();
			$.each(b,
			function(c) {
				var d = b[c].split("=")[0];
				a.removeCookie(d)
			})
		},
		getCookieByName: function(b) {
			var a = this.getCookies();
			var c = new RegExp(b);
			var d = null;
			$.each(a,
			function(e) {
				if (a[e].match(c)) {
					d = a[e].split(b + "=")[1];
					return false
				}
			});
			return d
		}
	},
	cjkEncode: function(d) {
		if (typeof d !== "string") {
			return d
		}
		var c = "";
		for (var a = 0; a < d.length; a++) {
			var b = d.charCodeAt(a);
			if (b >= 128 || b == 91 || b == 93) {
				c += "[" + b.toString(16) + "]"
			} else {
				c += d.charAt(a)
			}
		}
		return c
	},
	cjkDecode: function(text) {
		if (text == null) {
			return ""
		}
		if (text.indexOf("[") == -1) {
			return text
		}
		var newText = "";
		for (var i = 0; i < text.length; i++) {
			var ch = text.charAt(i);
			if (ch == "[") {
				var rightIdx = text.indexOf("]", i + 1);
				if (rightIdx > i + 1) {
					var subText = text.substring(i + 1, rightIdx);
					if (subText.length > 0) {
						ch = String.fromCharCode(eval("0x" + subText))
					}
					i = rightIdx
				}
			}
			newText += ch
		}
		return newText
	},
	htmlSpaceDecode: function(a) {
		return (a == null) ? "": String(a).replace(/&nbsp;/, " ")
	},
	htmlEncode: function(a) {
		return (a == null) ? "": String(a).replace(/&/g, "&amp;").replace(/\"/g, "&quot;").replace(/</g, "&lt;").replace(/>/g, "&gt;")
	},
	htmlDecode: function(a) {
		return (a == null) ? "": String(a).replace(/&amp;/g, "&").replace(/&quot;/g, '"').replace(/&lt;/g, "<").replace(/&gt;/g, ">").replace(/&nbsp;/g, " ")
	},
	jsonEncode: function(c) {
		var f = {}.hasOwnProperty ? true: false;
		var e = {
			"\b": "\\b",
			"\t": "\\t",
			"\n": "\\n",
			"\f": "\\f",
			"\r": "\\r",
			'"': '\\"',
			"\\": "\\\\"
		};
		var k = function(a) {
			if (/["\\\x00-\x1f]/.test(a)) {
				return '"' + a.replace(/([\x00-\x1f\\"])/g,
				function(m, i) {
					var n = e[i];
					if (n) {
						return n
					}
					n = i.charCodeAt();
					return "\\u00" + Math.floor(n / 16).toString(16) + (n % 16).toString(16)
				}) + '"'
			}
			return '"' + a + '"'
		};
		var d = function(s) {
			var p = ["["],
			m,
			r,
			n = s.length,
			q;
			for (r = 0; r < n; r += 1) {
				q = s[r];
				switch (typeof q) {
				case "undefined":
				case "function":
				case "unknown":
					break;
				default:
					if (m) {
						p.push(",")
					}
					p.push(q === null ? "null": FR.jsonEncode(q));
					m = true
				}
			}
			p.push("]");
			return p.join("")
		};
		if (typeof c == "undefined" || c === null) {
			return "null"
		} else {
			if (FR.isArray(c)) {
				return d(c)
			} else {
				if (c instanceof Date) {
					return FR.jsonEncode({
						__time__: c.getTime()
					})
				} else {
					if (typeof c == "string") {
						return k(c)
					} else {
						if (typeof c == "number") {
							return isFinite(c) ? String(c) : "null"
						} else {
							if (typeof c == "boolean") {
								return String(c)
							} else {
								if ($.isFunction(c)) {
									return String(c)
								} else {
									var j = ["{"],
									h,
									g,
									l;
									for (g in c) {
										if (!f || c.hasOwnProperty(g)) {
											l = c[g];
											switch (typeof l) {
											case "undefined":
											case "unknown":
												break;
											default:
												if (h) {
													j.push(",")
												}
												j.push(FR.jsonEncode(g), ":", l === null ? "null": FR.jsonEncode(l));
												h = true
											}
										}
									}
									j.push("}");
									return j.join("")
								}
							}
						}
					}
				}
			}
		}
	},
	jsonDecode: function(c) {
		if (c == null) {
			return {}
		}
		var a;
		try {
			a = $.parseJSON(c)
		} catch(b) {
			try {
				a = new Function("return " + c)()
			} catch(b) {}
		}
		if (a == null) {
			a = []
		}
		return (function(e) {
			if (e && e.__time__ != null) {
				return new Date(e.__time__)
			}
			for (var d in e) {
				if (e[d] == e || typeof e[d] == "object" || $.isFunction(e[d])) {
					break
				}
				e[d] = arguments.callee(e[d])
			}
			return e
		})(a)
	},
	_numberFormat: function(s, t) {
		if (t.split(" ").length === 2 && t.split(" ")[1].startWith("#")) {
			return t.split(" ")[0] + " " + FR._numberFormat(s, t.split(" ")[1])
		}
		if (t.indexOf(";") >= 0) {
			if (s >= 0) {
				return FR._numberFormat(s + "", t.substring(0, t.indexOf(";")))
			} else {
				return FR._numberFormat(( - s) + "", t.substr(t.indexOf(";") + 1))
			}
		}
		var f = "";
		var u = false;
		if (t.match(/,/) && t.match(/#,/)) {
			u = true
		}
		var F = t.split(".");
		var k = s.split(".");
		var b = false;
		if (k.length > 1 && F.length <= 1) {
			var A = k[1].split("")[0].length > 0 ? k[1].split("")[0] : 0;
			b = A > 4
		}
		var E = b ? (parseInt(k[0]) + 1).toString().split("") : k[0].split("");
		var j = F[0].split("");
		var m = F[0].split("");
		var q = "";
		var e = "";
		var c = [];
		for (var C = 0; C < j.length; C++) {
			var x = j[C];
			if (x == "#" || x == "0") {
				c.push(C)
			}
		}
		if (E[0] && E[0].length > 0) {
			var l = E.length - 1;
			var g = 0;
			for (var C = c.length - 1; C >= 0; C--) {
				g++;
				var p = c[C];
				j[p] = E[l];
				if (l == 0) {
					break
				}
				l--
			}
			var B = c[0];
			if (m[B] == "#") {
				if (E.length > g) {
					var w = E.length - g;
					for (var C = 0; C < w; C++) {
						q += E[C]
					}
					j[B] = q + j[B]
				}
			}
		}
		if (B > 0) {
			f = j.join("").substring(0, B)
		}
		if (F[1] && F[1].length > 0) {
			var z = F[1].split("");
			var o = F[1].split("");
			if (k[1] && k[1].length > 0) {
				var h = k[1].split("");
				var y = [];
				for (var C = 0; C < z.length; C++) {
					var x = z[C];
					if (x == "#" || x == "0") {
						y.push(C)
					}
				}
				var g = 0;
				for (var C = 0; C < y.length; C++) {
					g++;
					var p = y[C];
					z[p] = h[C];
					if (C == h.length - 1) {
						break
					}
				}
				var n = y[y.length - 1];
				if (o[n] == "#") {
					for (var C = g; C < h.length; C++) {
						e += h[C]
					}
					z[n] += e
				}
			}
		}
		for (var C = 0; C < j.length; C++) {
			if (j[C] == "#") {
				j[C] = ""
			}
		}
		var r = j.join("");
		if (F[1]) {
			for (var C = 0; C < z.length; C++) {
				if (z[C] == "#") {
					z[C] = ""
				}
			}
			if (! (k[1]) && o[0] == "#") {
				r += z.join("")
			} else {
				r += "." + z.join("")
			}
		}
		var d = c[0];
		var a = r;
		r = r.substring(d, r.length);
		if (r.substring(0, 1) == ",") {
			r = r.substring(1)
		}
		r = a.substring(0, d) + r;
		if (r.substring(1, 2) == "," && isNaN(r.substring(0, 1))) {
			r = r.substring(0, 1) + r.substring(2)
		}
		if (s.substring(0, 1) == "-" && !isNaN(r.substring(0, 1))) {
			r = "-" + r
		}
		if (r.substring(r.length - 1) == ",") {
			r = r.substring(0, r.length)
		}
		if (u) {
			var D = [];
			f && (r = r.substring(1, r.length));
			tempResultArray = r.split(",");
			if (tempResultArray.length == 1) {
				D.push(tempResultArray[0]);
				return f + D.join(",")
			}
			D.unshift(r.split(",")[1]);
			D.unshift(FR._numberFormat(tempResultArray[0], t.split(".")[0]));
			return D.join(",")
		}
		return r
	},
	contentFormat: function(d, c) {
		if (d == null || d === "") {
			return ""
		}
		if (c == null) {
			return d + ""
		}
		var g = d.toString();
		if (c.match(/^T/)) {
			return g
		} else {
			if (c.match(/0(\.0*)?%$/)) {
				var b = d + "";
				var a = b.indexOf(".");
				if (a >= 0) {
					b = Number(b.replace(".", "")) / Math.pow(10, b.length - a - 3)
				} else {
					b = d * 10 * 10
				}
				g = FR.contentFormat(b, c.substring(0, c.length - 1)) + "%"
			} else {
				if ((c.match(/^D/))) {
					if (! (d instanceof Date)) {
						if (!isNaN(d)) {
							d = new Date(d)
						} else {
							d = Date.parseDate(d + "", Date.patterns.ISO8601Long)
						}
					}
					if (d != null) {
						var e = c.match(/^DT/);
						var f = FR.convertJavaDateFormat2JS(c.substring(e ? 2 : 1));
						g = "" + d.format(f);
						if (e && g.endWith("00:00:00")) {
							g = g.substring(0, g.length - 9)
						}
						g = FR.CNDateString(g)
					}
				} else {
					g = FR._numberFormat(g, c)
				}
			}
		}
		if (c.match(/¤/)) {
			g = g.replace(/¤/g, "￥")
		} else {
			if (c.match(/E/) && !(c.match(/EEEE/))) {
				g = FR._ENumberFormat(d, c)
			}
		}
		return g
	},
	_ENumberFormat: function(m, l) {
		if (l.indexOf(";") >= 0) {
			if (m >= 0) {
				return FR._ENumberFormat(m + "", l.substring(0, l.indexOf(";")))
			} else {
				return FR._ENumberFormat(( - m) + "", l.substr(l.indexOf(";") + 1))
			}
		}
		var g = l.indexOf("E");
		var h = l.toString();
		var b = h.slice(0, g);
		var o = h.slice(g + 1);
		if (m == 0) {
			m = 0
		}
		var j = 0;
		var c = m.toString();
		var n;
		if (c.indexOf(".") == -1) {
			n = c.length
		} else {
			n = c.slice(0, c.indexOf(".")).length
		}
		var k;
		if (b.indexOf(".") == -1) {
			k = b.length
		} else {
			k = b.slice(0, b.indexOf(".")).length;
			for (var f = 0; f < k; f++) {
				if (b[f] != "#" && b[f] != "0") {
					k--
				} else {
					break
				}
			}
		}
		j = Math.abs(n - k);
		if (m < 0) {
			n -= 1;
			j -= 1
		}
		var m = m * Math.pow(10, k - n);
		var a = FR.contentFormat(m.toString(), b);
		var d = FR._numberFormat(j.toString(), o);
		return a + "E" + d
	},
	CNDateString: function(a) {
		a = a.replace(/Jan/, FR.i18n.January);
		a = a.replace(/Feb/, FR.i18n.February);
		a = a.replace(/Mar/, FR.i18n.March);
		a = a.replace(/Apr/, FR.i18n.April);
		a = a.replace(/May/, FR.i18n.May);
		a = a.replace(/Jun/, FR.i18n.June);
		a = a.replace(/Jul/, FR.i18n.July);
		a = a.replace(/Aug/, FR.i18n.August);
		a = a.replace(/Sep/, FR.i18n.September);
		a = a.replace(/Oct/, FR.i18n.October);
		a = a.replace(/Nov/, FR.i18n.November);
		a = a.replace(/Dec/, FR.i18n.December);
		a = a.replace(/Monday/, FR.i18n.Monday);
		a = a.replace(/Tuesday/, FR.i18n.Tuesday);
		a = a.replace(/Wednesday/, FR.i18n.Wednesday);
		a = a.replace(/Thursday/, FR.i18n.Thursday);
		a = a.replace(/Friday/, FR.i18n.Friday);
		a = a.replace(/Saturday/, FR.i18n.Saturday);
		a = a.replace(/Sunday/, FR.i18n.Sunday);
		return a
	},
	convertJavaDateFormat2JS: function(a) {
		if (!a) {
			return ""
		}
		a = String(a).replace(/y{4,}/g, "Y").replace(/y{2}/g, "y");
		if (new RegExp("M{2,}", "g").test(a)) {
			a = a.replace(/M{4,}/g, "M").replace(/M{2}/g, "m")
		} else {
			a = a.replace(/M{1}/g, "n")
		}
		a = a.replace(/E{4,}/g, "l");
		if (new RegExp("d{2,}", "g").test(a)) {
			a = a.replace(/d{2,}/g, "d")
		} else {
			a = a.replace(/d{1}/g, "j")
		}
		if (new RegExp("h{2,}", "g").test(a)) {
			a = a.replace(/h{2,}/g, "h")
		} else {
			a = a.replace(/h{1}/g, "g")
		}
		if (new RegExp("H{2,}", "g").test(a)) {
			a = a.replace(/H{2,}/g, "H")
		} else {
			a = a.replace(/H{1}/g, "G")
		}
		a = a.replace(/m{2,}/g, "i").replace(/s{2,}/g, "s").replace(/z+/g, "T");
		return a
	},
	parseFmt: function(a) {
		if (!a) {
			return ""
		}
		a = String(a).replace(/y{4,}/g, "%Y").replace(/y{2}/g, "%y").replace(/M{4,}/g, "%b").replace(/M{3}/g, "%B").replace(/M{2}/g, "%X").replace(/M{1}/g, "%x").replace(/a{1}/g, "%p");
		if (new RegExp("d{2,}", "g").test(a)) {
			a = a.replace(/d{2,}/g, "%d")
		} else {
			a = a.replace(/d{1}/g, "%e")
		}
		if (new RegExp("h{2,}", "g").test(a)) {
			a = a.replace(/h{2,}/g, "%I")
		} else {
			a = a.replace(/h{1}/g, "%I")
		}
		if (new RegExp("H{2,}", "g").test(a)) {
			a = a.replace(/H{2,}/g, "%H")
		} else {
			a = a.replace(/H{1}/g, "%H")
		}
		a = a.replace(/m{2,}/g, "%M").replace(/s{2,}/g, "%S");
		return a
	},
	contains: function(a, b) {
		return FR.isAncestor(a, b) ? true: ((!a || !b) ? false: a == b)
	},
	isAncestor: function(d, f) {
		if (!d || !f) {
			return false
		}
		if (d.contains && !$.browser.safari) {
			try {
				return d.contains(f)
			} catch(b) {}
		}
		if (d.compareDocumentPosition) {
			return !! (d.compareDocumentPosition(f) & 16)
		} else {
			var a = f.parentNode;
			while (a) {
				if (a == d) {
					return true
				} else {
					if (!a.tagName || a.tagName.toUpperCase() == "HTML") {
						return false
					}
				}
				a = a.parentNode
			}
			return false
		}
	},
	isNavKeyPress: function(c) {
		if (!c) {
			return false
		}
		var b = {
			63234 : 37,
			63235 : 39,
			63232 : 38,
			63233 : 40,
			63276 : 33,
			63277 : 34,
			63272 : 46,
			63273 : 36,
			63275 : 35
		};
		var a = c.keyCode;
		a = $.browser.safari ? (b[a] || a) : a;
		return (a >= 33 && a <= 40) || a == 13 || a == $.ui.keyCode.TAB || a == $.ui.keyCode.ESCAPE
	},
	isSpecialKey: function(b) {
		if (!b) {
			return false
		}
		var a = b.keyCode;
		return (b.type == "keypress" && b.ctrlKey) || a == 9 || a == 13 || a == 40 || a == 27 || (a == 16) || (a == 17) || (a >= 18 && a <= 20) || (a >= 33 && a <= 35) || (a >= 36 && a <= 39) || a == 44 || (a == 13 || a == 229)
	},
	equals: function(g, f) {
		if (!g || !f) {
			return (!g && !f)
		}
		if (g == f) {
			return true
		} else {
			if (FR.isArray(g)) {
				if (g.length == f.length) {
					for (var c = 0; c < g.length; c++) {
						if (!FR.equals(g[c], f[c])) {
							return false
						}
					}
				} else {
					return false
				}
			} else {
				if (typeof g == "object") {
					var e = 0,
					d = 0;
					for (var c in g) {
						e++
					}
					for (var c in f) {
						d++
					}
					if (e != d) {
						return false
					}
					for (var b in g) {
						if (!FR.equals(g[b], f[b])) {
							return false
						}
					}
				} else {
					return g === f
				}
			}
		}
		return true
	},
	id2ColumnRowStr: function(a) {
		return a.replace(/([A-Z]+\d+)-\d+-\d+/, "$1")
	},
	id2ColumnRow: function(a) {
		return FR.cellStr2ColumnRow(FR.id2ColumnRowStr(a))
	},
	id2Location: function(c) {
		var b = FR.id2ColumnRow(c);
		var a = c.split("-");
		b.rptIdx = a[1];
		return b
	},
	cellStr2ColumnRow: function(c) {
		c = $.trim(c.toUpperCase());
		if (c.match(/^[A-Z]+\d+$/) == null) {
			return null
		}
		var e = c.replace(/([A-Z]+)\d+/, "$1");
		var d = c.replace(/[A-Z]+(\d+)/, "$1");
		var a = e.length;
		var b = FR.letter2Digit(e) - 1;
		var f = parseInt(d) - 1;
		return {
			col: b,
			row: f
		}
	},
	columnRow2CellStr: function(b) {
		var a = parseInt(b.col);
		var c = parseInt(b.row);
		if (typeof a != "number" || typeof c != "number") {
			return ""
		}
		return FR.digit2Letter(a + 1) + (c + 1)
	},
	letter2Digit: function(e) {
		var a = e.length;
		var b = 0;
		for (var d = a - 1; d >= 0; d--) {
			var f = e.charAt(d);
			b += (COL_IDS.indexOf(f) + 1) * Math.pow(26, a - 1 - d)
		}
		return b
	},
	digit2Letter: function(a) {
		var d, b = "";
		if (typeof a != "number" || a == 0 || isNaN(a) || !isFinite(a)) {
			return b
		}
		for (; a != 0;) {
			d = a % 26;
			if (d == 0) {
				d = 26
			}
			b = COL_IDS.charAt(d - 1) + b;
			a = (a - d) / 26
		}
		return b
	}
});
if (window._g == null) {
	window._g = FR.SessionMgr.getContentPane
}
$.extend(FR, {
	accDiv: function(arg1, arg2) {
		var t1 = 0,
		t2 = 0,
		r1, r2;
		try {
			t1 = arg1.toString().split(".")[1].length
		} catch(e) {}
		try {
			t2 = arg2.toString().split(".")[1].length
		} catch(e) {}
		with(Math) {
			r1 = Number(arg1.toString().replace(".", ""));
			r2 = Number(arg2.toString().replace(".", ""));
			return (r1 / r2) * pow(10, t2 - t1)
		}
	},
	accMul: function(d, b) {
		var a = 0,
		f = d.toString(),
		c = b.toString();
		try {
			a += f.split(".")[1].length
		} catch(g) {}
		try {
			a += c.split(".")[1].length
		} catch(g) {}
		return Number(f.replace(".", "")) * Number(c.replace(".", "")) / Math.pow(10, a)
	},
	accAdd: function(f, d) {
		var c, b, a;
		try {
			c = f.toString().split(".")[1].length
		} catch(g) {
			c = 0
		}
		try {
			b = d.toString().split(".")[1].length
		} catch(g) {
			b = 0
		}
		a = Math.pow(10, Math.max(c, b));
		return (FR.accMul(f, a) + FR.accMul(d, a)) / a
	},
	accMin: function(f, d) {
		var c, b, a;
		try {
			c = f.toString().split(".")[1].length
		} catch(g) {
			c = 0
		}
		try {
			b = d.toString().split(".")[1].length
		} catch(g) {
			b = 0
		}
		a = Math.pow(10, Math.max(c, b));
		return (FR.accMul(f, a) - FR.accMul(d, a)) / a
	},
	string2ints: function(h) {
		var b = [];
		var g = h.split(",");
		for (var e = 0; e < g.length; e++) {
			var d = g[e];
			var f = d.split("-");
			var a = parseInt(f[0]),
			c = a;
			if (f.length > 1) {
				c = parseInt(f[1])
			}
			for (var j = a; j <= c; j++) {
				b.push(j)
			}
		}
		return b
	}
});
FR.OB = function(a) {
	this.options = $.extend(this._defaultConfig(), a);
	this._init();
	this._confirmEvents()
};
$.extend(FR.OB.prototype, {
	_defaultConfig: function() {
		return {}
	},
	_init: function() {
		var a = this;
		if (this.options.listeners != null) {
			$.each(this.options.listeners,
			function(c, b) { (b.target ? b.target: a)[b.once ? "once": "on"](b.eventName, b.action.createDelegate(a))
			});
			delete this.options.listeners
		}
	},
	_confirmEvents: function() {
		this.fireEvent(FR.Events.AFTERINIT)
	},
	_getEvents: function() {
		if (!FR.isArray(this.events)) {
			this.events = []
		}
		return this.events
	},
	fireEvent: function() {
		var a = arguments[0].toLowerCase();
		var d = this._getEvents()[a];
		if (FR.isArray(d)) {
			var b = Array.prototype.slice.call(arguments, 1);
			for (var c = 0; c < d.length; c++) {
				if (d[c].apply(this, b) === false) {
					return false
				}
			}
		}
		return true
	},
	on: function(a, c) {
		a = a.toLowerCase();
		var b = this._getEvents()[a];
		if (!FR.isArray(b)) {
			b = [];
			this._getEvents()[a] = b
		}
		b.push(c)
	},
	once: function(a, c) {
		var b = function() {
			c.apply(this, arguments);
			this.un(a, b)
		};
		this.on(a, b)
	},
	un: function(a, c) {
		if (c == null) {
			delete this._getEvents()[a]
		} else {
			var b = this._getEvents()[a];
			if (FR.isArray(b)) {
				var d = [];
				$.each(b,
				function(e, f) {
					if (f != c) {
						d.push(f)
					}
				});
				this._getEvents()[a] = d
			}
		}
	},
	purgeListeners: function() {
		this.events = []
	}
});
FR.OB.capture = function(c, b, a) {
	c.fireEvent = c.fireEvent.createInterceptor(b, a)
};
FR.OB.releaseCapture = function(a) {
	a.fireEvent = FR.OB.prototype.fireEvent
};
FR.Widget = FR.extend(FR.OB, {
	_defaultConfig: function() {
		return $.extend(FR.Widget.superclass._defaultConfig.apply(), {
			widgetName: "",
			disabled: false,
			invisible: false
		})
	},
	_init: function() {
		FR.Widget.superclass._init.apply(this, arguments);
		var b = this.options;
		if (this.options.renderEl != null) {
			this.element = $(this.options.renderEl)
		} else {
			this.element = this._defaultRoot()
		}
		if (b.widgetName) {
			this.element.attr({
				widgetName: b.widgetName
			})
		}
		if (FR.isWidthOrHeight(b.width)) {
			this.element.css({
				width: b.width - FR.considerBoxModelWidth(this.element)
			})
		}
		if (FR.isWidthOrHeight(b.height)) {
			this.element.css({
				height: b.height - FR.considerBoxModelHeight(this.element)
			})
		}
		this[this.options.disabled ? "disable": "enable"]();
		this[this.options.invisible ? "invisible": "visible"]();
		var a = this;
		this.element.bind(FR.Events.RESIZE,
		function(c, d) {
			a.doResize(d)
		})
	},
	_defaultRoot: function() {
		return $("<div/>")
	},
	doResize: function(f) {
		var b = this.options;
		var e = this.element;
		if (f) {
			if (f.width) {
				b.width = f.width
			}
			if (f.height) {
				b.height = f.height
			}
			if (f.left != null) {
				b.left = f.left
			}
			if (f.top != null) {
				b.top = f.top
			}
		} else {
			if (b.fit == true) {
				var d = e.parent();
				var c = d.width();
				var a = d.height();
				b.width = c;
				b.height = a
			}
		}
		this.element.width(b.width);
		this.element.height(b.height)
	},
	destroy: function() {
		this.element.each(function() {
			$(this).remove();
			if ($.browser.msie) {
				this.outerHTML = ""
			}
		});
		if ($.isFunction(this.options.onDistroy)) {
			this.options.onDistroy.call(this)
		}
	},
	getValue: function() {},
	setValue: function() {},
	isEnabled: function() {
		return ! this.options.disabled
	},
	isVisible: function() {
		return ! this.options.invisible
	},
	disable: function() {
		this.setEnable(false)
	},
	enable: function() {
		this.setEnable(true)
	},
	invisible: function() {
		this.setVisible(false)
	},
	visible: function() {
		this.setVisible(true)
	},
	setEnable: function(a) {
		if (a == true) {
			this.options.disabled = false;
			this.element.addClass("ui-state-enabled");
			this.element.removeClass("ui-state-disabled")
		} else {
			if (a == false) {
				this.options.disabled = true;
				this.element.addClass("ui-state-disabled");
				this.element.removeClass("ui-state-enabled")
			}
		}
	},
	setVisible: function(a) {
		if (a == true) {
			this.options.invisible = false;
			this.element.show()
		} else {
			if (a == false) {
				this.options.invisible = true;
				this.element.hide()
			}
		}
	},
	addWidget2Form: function(a) {
		if (a) {
			a.add(this)
		}
	},
	setSource: function(a) {
		this.setValue(a)
	},
	removeWidgetFromForm: function(a) {
		if (a) {
			a.remove(this)
		}
	},
	couldUsedAsEditor: function() {
		return this.usedAsEditor
	}
}); (function() {
	var a = {};
	$.shortcut = function(c, b) {
		if (a[c] != null) {
			throw ("shortcut:[" + c + "] has been registed")
		}
		a[c] = b;
		$.extend(b.prototype, {
			xtype: c
		})
	};
	FR.createWidget = function(c) {
		if (c instanceof $) {
			return new FR.Widget({
				renderEl: c
			})
		}
		if (!c.type) {
			throw ("config type cannot be null.")
		}
		var d = c.type.toLowerCase();
		var b = a[d];
		return new b(c)
	};
	$.fn.extend({
		asComponent: function(b) {
			b = b || {};
			b.renderEl = $(this);
			return FR.createWidget(b)
		}
	})
})();
$.extend(FR, {
	activeBranches: function(branches, parameters, processid, taskName, serverURL, needClose) {
		if (processid && taskName) {
			var it = {
				id: processid,
				taskname: taskName
			}
		} else {
			var it = this.getProcessIDAndTaskName()
		}
		if (!it) {
			return
		}
		if (!$.isArray(branches)) {
			branches = [branches]
		}
		if (parameters != null && !$.isArray(parameters)) {
			parameters = [parameters]
		}
		var data = $.extend(it, {
			branches: branches
		});
		if (parameters) {
			$.each(parameters,
			function(idx, item) {
				if (item.value.toString().startWith("eval")) {
					var evalValue = item.value.toString().substring(5, item.value.toString().length - 1);
					item.value = eval(evalValue)
				}
			});
			data.parameters = parameters
		}
		this.ajax({
			url: (serverURL ? serverURL: FR.servletURL) + "?op=process_exe&cmd=continue",
			data: data,
			type: "post",
			complete: function(res, status) {
				if (res.responseText == "close") {
					FR.Msg.toast("Process is closed!");
					return
				}
				if (res.responseText != "success") {
					FR.Msg.toast("Operation Fail!")
				}
				this.refreshUserTask();
				if (branches.length == 1 && branches[0] == null) {
					FR.Msg.toast(FR.i18n.Submitsuccess + "！")
				} else {
					if (needClose !== false) {
						this.closeCurrentDialog()
					} else {
						FR.Msg.toast(FR.i18n.Submitsuccess + "！")
					}
				}
			}.createDelegate(this)
		})
	},
	getProcessIDAndTaskName: function() {
		var a = {};
		this.ajax({
			url: FR.servletURL + "?op=process_exe",
			async: false,
			data: {
				sessionID: FR.SessionMgr.getSessionID(),
				cmd: "values"
			},
			complete: function(c, b) {
				a = FR.jsonDecode(c.responseText)
			}
		});
		return a
	},
	closeCurrentDialog: function() {
		var a = window.parent;
		if (!a || !a.FS) {
			return
		}
		a.FS.tabPane.closeActiveTab()
	},
	refreshUserTask: function() {
		var a = window.parent;
		if (!a || !a.FS || !a.FS.Process) {
			return
		}
		a.FS.Process.refreshTask()
	},
	removeCurrentBookAddedPara: function() {}
});
/*!
 * jLayout - JavaScript Layout Algorithms v0.2
 *
 * Licensed under the revised BSD License.
 * Copyright 2008, Bram Stein
 * All rights reserved.
 */
jLayout = {
	layout: function(a, d) {
		var b = {},
		c = d || {};
		c.hgap = a.hgap || 0;
		c.vgap = a.vgap || 0;
		b.layout = function(e) {
			return e
		};
		b.preferred = function(e) {
			return {
				width: c.hgap,
				height: c.vgap
			}
		};
		b.minimum = function(e) {
			return {
				width: c.hgap,
				height: c.vgap
			}
		};
		b.maximum = function(e) {
			return {
				width: Number.MAX_VALUE,
				height: Number.MAX_VALUE
			}
		};
		return b
	},
	Absolute: function(b) {
		var d = {},
		a = b.items || [],
		c = this.layout(b, d);
		c.minimum = function(f) {
			var h = 0,
			e = 0;
			for (var g = 0; g < a.length; g++) {
				var j = a[g];
				if (j.el.isVisible()) {
					h = Math.max(h, j.x + j.width);
					e = Math.max(e, j.y + j.height)
				}
			}
			return {
				width: h,
				height: e
			}
		};
		return c
	},
	card: function(c) {
		var e = {},
		a = c,
		b = this.layout(c, e);
		b.layout = function(f) {
			a.doLayout()
		};
		function d(f) {
			return function(g) {
				if (g.indexEL[g.showIndex]) {
					return g.indexEL[g.showIndex][f + "Size"]()
				}
				return {
					width: 0,
					height: 0
				}
			}
		}
		b.preferred = d("preferred");
		b.minimum = d("minimum");
		b.maximum = d("maximum");
		return b
	},
	grid: function(h) {
		var g = {},
		d = this.layout(h, g),
		e = h.items || [],
		b = h.columns || e.length,
		i = h.rows || 0;
		if (i > 0) {
			b = Math.floor((e.length + i - 1) / i)
		} else {
			i = Math.floor((e.length + b - 1) / b)
		}
		var a = h.widths || [],
		f = h.heights || [];
		$.each([{
			ar: a,
			len: b
		},
		{
			ar: f,
			len: i
		}],
		function(j, k) {
			while (k.ar.length < k.len) {
				k.ar[k.ar.length] = -1
			}
		});
		d.layout = function(n) {
			var m = n.bounds();
			var u, t, q = n.insets(),
			z = q.left,
			w = q.top,
			r = m.width - (q.left + q.right) - (b - 1) * g.hgap,
			v = m.height - (q.top + q.bottom) - (i - 1) * g.vgap;
			var l = Array.prototype.concat.call([], a),
			s = Array.prototype.concat.call([], f);
			$.each([{
				ar: l,
				len: r
			},
			{
				ar: s,
				len: v
			}],
			function(y, x) {
				var j = 0;
				$.each(x.ar,
				function(C, D) {
					if (D >= 0) {
						x.len -= D
					} else {
						j++
					}
				});
				if (j > 0) {
					var B = x.len / j;
					$.each(x.ar,
					function(C, D) {
						if (D < 0) {
							x.ar[C] = B
						}
					})
				}
			});
			var k = 0,
			p = 0;
			for (u = 0; u < e.length; u++) {
				var o = l[k],
				A = s[p];
				e[u].bounds({
					x: z,
					y: w,
					width: o,
					height: A
				});
				if (k < b - 1) {
					z += o + g.hgap;
					k++
				} else {
					w += A + g.vgap;
					z = q.left;
					k = 0;
					p++
				}
				e[u].doLayout()
			}
			return n
		};
		function c(j) {
			return function(l) {
				var t, m = 0,
				u = 0,
				v, p = l.insets();
				var r = Array.prototype.concat.call([], a),
				n = Array.prototype.concat.call([], f);
				var k = 0,
				o = 0;
				for (var t = 0; t < e.length; t++) {
					v = e[t][j + "Size"]();
					var m = r[k],
					u = n[o];
					if (a[k] < 0) {
						r[k] = Math.max(r[k], v.width)
					}
					if (f[o] < 0) {
						n[o] = Math.max(n[o], v.height)
					}
					if (k < b - 1) {
						k++
					} else {
						k = 0;
						o++
					}
					e[t].doLayout()
				}
				var s = 0;
				$.each(r,
				function(x, y) {
					if (y > 0) {
						s += y
					}
				});
				var q = 0;
				$.each(n,
				function(w, x) {
					if (x > 0) {
						q += x
					}
				});
				return {
					width: p.left + p.right + s + (b - 1) * g.hgap,
					height: p.top + p.bottom + q + (i - 1) * g.vgap
				}
			}
		}
		d.preferred = c("preferred");
		d.minimum = c("minimum");
		d.maximum = c("maximum");
		return d
	},
	border: function(e) {
		var j = {},
		f = this.layout(e, j),
		g = e.east ? e.east.element: null,
		c = e.east ? e.east.element.width() : null,
		b = e.west ? e.west.element: null,
		s = e.west ? e.west.element.width() : null,
		m = e.north ? e.north.element: null,
		a = e.north ? e.north.element.height() : null,
		i = e.south ? e.south.element: null,
		h = e.south ? e.south.element.height() : null,
		r = e.center.element,
		l = g ? e.east.options.contentWidget: null,
		k = b ? e.west.options.contentWidget: null,
		o = m ? e.north.options.contentWidget: null,
		q = i ? e.south.options.contentWidget: null,
		p = r ? e.center.options.contentWidget: null;
		function n(u) {
			var v = $(u.find(">div.fr-panel-body"));
			var w = $(v.children()[0]),
			t = w.insets();
			if ($.browser.msie && $.browser.version <= "8.0") {
				parentWidth = isNaN(parseInt(v[0].style.width)) ? v.width() : parseInt(v[0].style.width);
				parentHeight = isNaN(parseInt(v[0].style.height)) ? v.height() : parseInt(v[0].style.height)
			} else {
				parentWidth = v.width();
				parentHeight = v.height()
			}
			w.css({
				width: parentWidth - t.left - t.right,
				height: parentHeight - t.top - t.bottom
			});
			w.doLayout()
		}
		f.layout = function(u) {
			var C = u.bounds(),
			w = u.insets(),
			y = w.top,
			t = C.height - w.bottom,
			v = w.left,
			z = C.width - w.right,
			x;
			if (m && m.isVisible() && o && o.isVisible()) {
				x = m.preferredSize();
				m.bounds({
					x: v,
					y: y,
					width: z - v,
					height: x.height || a
				});
				n(m);
				y += x.height || a + j.vgap
			}
			if (i && i.isVisible() && q && q.isVisible()) {
				x = i.preferredSize();
				if (!a) {
					a = 0
				}
				var A = t - h;
				A = A > a ? A: a;
				i.bounds({
					x: v,
					y: A,
					width: z - v,
					height: x.height || h
				});
				n(i);
				t -= x.height || h + j.vgap
			}
			if (b && b.isVisible() && k && k.isVisible()) {
				x = b.preferredSize();
				b.bounds({
					x: v,
					y: y,
					width: x.width || s,
					height: t - y
				});
				n(b);
				v += x.width || s + j.hgap
			}
			if (g && g.isVisible() && l && l.isVisible()) {
				x = g.preferredSize();
				if (!s) {
					s = 0
				}
				var B = z - c;
				B = B > s ? B: s;
				g.bounds({
					x: B,
					y: y,
					width: x.width || c,
					height: t - y
				});
				n(g);
				z -= x.width || c + j.hgap
			}
			if (r && r.isVisible() && p && p.isVisible()) {
				r.bounds({
					x: v,
					y: y,
					width: z - v,
					height: t - y
				});
				n(r)
			}
			return u
		};
		function d(t) {
			return function(w) {
				var v = w.insets(),
				x = 0,
				u = 0,
				y;
				contentheight = 0;
				contentwidth = 0;
				if (g && g.isVisible() && l && l.isVisible()) {
					y = l.element[t + "Size"]();
					x += c + j.hgap;
					u = Math.max(y.height, u)
				}
				if (b && b.isVisible() && k && k.isVisible()) {
					y = k.element[t + "Size"]();
					x += s + j.hgap;
					u = Math.max(y.height, u)
				}
				if (r && r.isVisible() && p && p.isVisible()) {
					y = p.element[t + "Size"]();
					x += y.width;
					u = Math.max(y.height, u)
				}
				if (m && m.isVisible() && o && o.isVisible()) {
					y = o.element[t + "Size"]();
					x = Math.max(y.width, x);
					u += a + j.vgap
				}
				if (i && i.isVisible() && q && q.isVisible()) {
					y = q.element[t + "Size"]();
					x = Math.max(y.width, x);
					u += h + j.vgap
				}
				return {
					width: x + v.left + v.right,
					height: u + v.top + v.bottom
				}
			}
		}
		f.preferred = d("preferred");
		f.minimum = d("minimum");
		f.maximum = d("maximum");
		return f
	},
	flow: function(b) {
		var e = {},
		c = this.layout(b, e),
		a = b.items || [],
		g = (b.alignment && (b.alignment === "center" || b.alignment === "right" || b.alignment === "left") && b.alignment) || "left";
		hgap = typeof b.hgap === "number" && !isNaN(b.hgap) ? b.hgap: 5;
		vgap = typeof b.vgap === "number" && !isNaN(b.vgap) ? b.vgap: 5;
		c.layout = function(h) {
			var j = h.bounds(),
			k = h.insets(),
			m = 0,
			n = a.length,
			q,
			p = [],
			o = {
				width: 0,
				height: 0
			},
			l = {
				x: k.left,
				y: k.top
			};
			j.width -= k.left + k.right;
			j.height -= k.top + k.bottom;
			for (; m < n; m += 1) {
				if (a[m].isVisible()) {
					q = a[m].preferredSize();
					if ((o.width + q.width) > j.width) {
						f(p, l, o, j);
						p = [];
						l.y += o.height;
						l.x = k.left;
						o.width = 0;
						o.height = 0
					}
					o.height = Math.max(o.height, q.height + vgap);
					o.width += q.width + hgap;
					p.push(a[m])
				}
			}
			f(p, l, o, j);
			return h
		};
		function f(o, n, l, m) {
			var j = {
				x: n.x,
				y: n.y
			},
			k = 0,
			h = o.length;
			switch (g) {
			case "center":
				j.x += (hgap + m.width - l.width) / 2;
				break;
			case "right":
				j.x += m.width - l.width + hgap;
				break
			}
			for (; k < h; k += 1) {
				j.y = n.y;
				o[k].bounds(j);
				o[k].doLayout();
				j.x += o[k].bounds().width + hgap
			}
		}
		function d(h) {
			return function(m) {
				var o = 0,
				p = 0,
				j = 0,
				n, l = false,
				k = m.insets();
				for (; o < a.length; o += 1) {
					if (a[o].isVisible()) {
						n = a[o][h + "Size"]();
						j = Math.max(j, n.height);
						p += n.width
					}
				}
				return {
					width: p + k.left + k.right + (a.length - 1) * hgap,
					height: j + k.top + k.bottom
				}
			}
		}
		c.preferred = d("preferred");
		c.minimum = d("minimum");
		c.maximum = d("maximum");
		return c
	},
	horizontal: function(h) {
		var g = {},
		c = this.layout(h, g),
		e = h.items || [],
		f = (h.alignment && (h.alignment === "center" || h.alignment === "right" || h.alignment === "left") && h.alignment) || "center",
		d = typeof h.hgap === "number" && !isNaN(h.hgap) ? h.hgap: 2,
		i = typeof h.vgap === "number" && !isNaN(h.vgap) ? h.vgap: 0;
		c.layout = function(k) {
			var j = k.bounds(),
			m = k.insets(),
			s = 0,
			r = m.top + i,
			t = j.width - (m.left + m.right + d * 2),
			o = 0,
			q = j.height - (m.top + m.bottom),
			l = q - 2 * i,
			n,
			p = e.length;
			for (n = 0; n < p; n++) {
				var u = e[n];
				if (u.el.isVisible()) {
					o += (u.width + d)
				}
			}
			b(s, r, t - o + 2 * d, l);
			return k
		};
		function b(l, q, p, k) {
			var o = {
				x: l,
				y: q,
				width: p,
				height: k
			},
			m = 0,
			j = e.length;
			switch (f) {
			case "left":
				o.x += 0;
				break;
			case "center":
				o.x += p / 2;
				break;
			case "right":
				o.x += p;
				break
			}
			for (; m < j; m++) {
				var n = e[m];
				if (n.el.isVisible()) {
					o.width = n.width;
					n.el.bounds(o);
					n.el.doLayout();
					o.x += (n.width + d)
				}
			}
		}
		function a(j) {
			return function(m) {
				var o = 0,
				p = 0,
				k = 0,
				n, l = m.insets();
				for (; o < e.length; o += 1) {
					if (e[o].el.isVisible()) {
						n = e[o].el[j + "Size"]();
						k = Math.max(k, n.height);
						p += e[o].width + d
					}
				}
				return {
					width: p + l.left + l.right,
					height: k + l.top + l.bottom
				}
			}
		}
		c.preferred = a("preferred");
		c.minimum = a("minimum");
		c.maximum = a("maximum");
		return c
	},
	vertical: function(b) {
		var h = {},
		d = this.layout(b, h),
		a = b.items || [],
		b = b,
		c = typeof b.hgap === "number" && !isNaN(b.hgap) ? b.hgap: 0,
		g = typeof b.vgap === "number" && !isNaN(b.vgap) ? b.vgap: 0;
		d.layout = function(k) {
			var j = k.bounds(),
			l = k.insets(),
			m,
			o = a.length,
			r = l.left + c,
			q = l.top + g,
			s = j.height - (l.top + l.bottom + g * 2),
			p = 0,
			n = j.width - (l.left + l.right),
			t = n - 2 * c;
			for (m = 0; m < o; m++) {
				var u = a[m];
				if (u.el.isVisible()) {
					p += u.height
				}
			}
			f(r, q, t, s - p);
			return k
		};
		function f(l, q, n, k) {
			var p = {
				x: l,
				y: q,
				width: n,
				height: k
			},
			m = 0,
			j = a.length;
			for (; m < j; m++) {
				var o = a[m];
				if (o.el.isVisible()) {
					p.height = o.height;
					o.el.bounds(p);
					o.el.doLayout();
					p.y += o.height + g
				}
			}
		}
		function e(i) {
			return function(l) {
				var n = 0,
				o = 0,
				j = 0,
				m, k = l.insets();
				for (; n < a.length; n += 1) {
					if (a[n].el.isVisible()) {
						m = a[n].el[i + "Size"]();
						o = Math.max(o, m.width);
						j += a[n].height + g
					}
				}
				return {
					width: o + k.left + k.right,
					height: j + k.top + k.bottom
				}
			}
		}
		d.preferred = e("preferred");
		d.minimum = e("minimum");
		d.maximum = e("maximum");
		return d
	},
	frgrid: function(j) {
		var h = {},
		e = this.layout(j, h),
		b = j.leftGap || 0,
		f = j.rightGap || 0,
		d = j.topGap || 0,
		i = j.bottomGap || 0,
		g = j.items || [],
		a = j.columns,
		k = j.rows;
		e.layout = function(m) {
			var l = m.bounds();
			var o = m.insets(),
			q = l.width - (o.left + o.right) - b - f,
			r = l.height - (o.top + o.bottom) - d - i,
			s = (q - (a - 1) * h.hgap) / a,
			n = (r - (k - 1) * h.vgap) / k;
			for (var p = 0; p < g.length; p++) {
				var t = g[p];
				t.el.bounds({
					x: t.column * (s + h.hgap) + b,
					y: t.row * (n + h.vgap) + d,
					width: s,
					height: n
				});
				t.el.doLayout()
			}
			return m
		};
		function c(l) {
			return function(o) {
				if (l == "minimum" || l == "maximum") {
					return o[l.substring(0, 3) + "Size"]()
				}
				var q = o.bounds();
				var n = o.insets(),
				m = q.width - (n.left + n.right),
				p = q.height - (n.top + n.bottom),
				s = m - (a - 1) * h.hgap,
				r = p - (k - 1) * h.vgap;
				return {
					width: s,
					height: r
				}
			}
		}
		e.preferred = c("preferred");
		e.minimum = c("minimum");
		e.maximum = c("maximum");
		return e
	},
	horizontalSplit: function(h) {
		var g = {},
		d = this.layout(h, g),
		b = h.aside,
		a = h.center,
		e = h.hgap,
		i = h.vgap,
		f = h.ratio;
		d.layout = function(l) {
			var o = l.bounds(),
			k = l.insets(),
			j = o.width - (k.left + k.right + e),
			n = o.height - (k.top + k.bottom + i * 2),
			m = 0;
			if (b) {
				m = j * f;
				b.element.bounds({
					x: k.left,
					y: k.top + e,
					width: m,
					height: n
				});
				b.element.doLayout()
			}
			if (a) {
				a.element.bounds({
					x: k.left + m,
					y: k.top + e,
					width: j - m,
					height: n
				});
				a.element.doLayout()
			}
		};
		function c(j) {
			return function(k) {
				return {
					width: 0,
					height: 0
				}
			}
		}
		d.preferred = c("preferred");
		d.minimum = c("minimum");
		d.maximum = c("maximum");
		return d
	},
	verticalSplit: function(h) {
		var g = {},
		d = this.layout(h, g),
		b = h.aside,
		a = h.center,
		e = h.hgap,
		i = h.vgap,
		f = h.ratio;
		d.layout = function(l) {
			var n = l.bounds(),
			k = l.insets(),
			j = n.width - (k.left + k.right + e * 2),
			m = n.height - (k.top + k.bottom + i),
			o = 0;
			if (b) {
				o = m * f;
				b.element.bounds({
					x: k.left + e,
					y: k.top,
					width: j,
					height: o
				});
				b.element.doLayout()
			}
			if (a) {
				a.element.bounds({
					x: k.left + e,
					y: k.top + i + o,
					width: j,
					height: m - o
				});
				a.element.doLayout()
			}
		};
		function c(j) {
			return function(k) {
				return {
					width: 0,
					height: 0
				}
			}
		}
		d.preferred = c("preferred");
		d.minimum = c("minimum");
		d.maximum = c("maximum");
		return d
	}
};
/*!
 * JSizes - JQuery plugin v0.31
 *
 * Licensed under the revised BSD License.
 * Copyright 2008, Bram Stein
 * All rights reserved.
 */
(function(b) {
	var a = function(c) {
		return parseInt(c, 10) || 0
	};
	b.each(["min", "max"],
	function(d, c) {
		b.fn[c + "Size"] = function(g) {
			var f, e;
			if (g) {
				if (g.width) {
					this.css(c + "-width", g.width)
				}
				if (g.height) {
					this.css(c + "-height", g.height)
				}
				return this
			} else {
				f = this.css(c + "-width");
				e = this.css(c + "-height");
				return {
					width: (c === "max" && (f === "none" || a(f) === -1) && Number.MAX_VALUE) || a(f),
					height: (c === "max" && (e === "none" || a(e) === -1) && Number.MAX_VALUE) || a(e)
				}
			}
		}
	});
	b.fn.isVisible = function() {
		return this.css("visibility") !== "hidden" && this.css("display") !== "none"
	};
	b.each(["border", "margin", "padding"],
	function(d, c) {
		b.fn[c] = function(e) {
			if (e) {
				if (e.top) {
					this.css(c + "-top" + (c === "border" ? "-width": ""), e.top)
				}
				if (e.bottom) {
					this.css(c + "-bottom" + (c === "border" ? "-width": ""), e.bottom)
				}
				if (e.left) {
					this.css(c + "-left" + (c === "border" ? "-width": ""), e.left)
				}
				if (e.right) {
					this.css(c + "-right" + (c === "border" ? "-width": ""), e.right)
				}
				return this
			} else {
				return {
					top: a(this.css(c + "-top" + (c === "border" ? "-width": ""))),
					bottom: a(this.css(c + "-bottom" + (c === "border" ? "-width": ""))),
					left: a(this.css(c + "-left" + (c === "border" ? "-width": ""))),
					right: a(this.css(c + "-right" + (c === "border" ? "-width": "")))
				}
			}
		}
	})
})(jQuery);
/*!
 * jLayout JQuery Plugin v0.11
 *
 * Licensed under the revised BSD License.
 * Copyright 2008, Bram Stein
 * All rights reserved.
 */
if (jQuery && jLayout) { (function(a) {
		a.fn.doLayout = function() {
			if (this.data("jlayout")) {
				this.data("jlayout").layout(this)
			}
			this.css({
				position: "absolute"
			})
		};
		a.fn.insets = function() {
			var d = this.padding(),
			c = this.border();
			return {
				top: d.top,
				bottom: d.bottom + c.bottom + c.top,
				left: d.left,
				right: d.right + c.right + c.left
			}
		};
		a.fn.bounds = function(c) {
			var b = {};
			if (c) {
				if (!isNaN(c.x)) {
					b.left = c.x
				}
				if (!isNaN(c.y)) {
					b.top = c.y
				}
				if (c.width != null) {
					b.width = (c.width - (this.outerWidth(true) - this.width()));
					b.width = (b.width >= 0) ? b.width: c.width
				}
				if (c.height != null) {
					b.height = c.height - (this.outerHeight(true) - this.height());
					b.height = (b.height >= 0) ? b.height: c.height
				}
				this.css(b);
				var d = this.data("tmp");
				if (!d || !FR.equals(d, b)) {
					this.triggerHandler(FR.Events.RESIZE, [b]);
					this.data("tmp", b)
				}
				return this
			} else {
				b = this.position();
				return {
					x: b.left,
					y: b.top,
					width: this.outerWidth(),
					height: this.outerHeight()
				}
			}
		};
		a.each(["min", "max"],
		function(c, b) {
			a.fn[b + "imumSize"] = function(d) {
				if (this.data("jlayout")) {
					return this.data("jlayout")[b + "imum"](this)
				} else {
					return this[b + "Size"](d)
				}
			}
		});
		a.fn.preferredSize = function() {
			var d, e, c = this.margin(),
			b = {
				width: 0,
				height: 0
			};
			if (this.data("jlayout")) {
				b = this.data("jlayout").preferred(this);
				d = this.minimumSize();
				e = this.maximumSize();
				b.width += c.left + c.right;
				b.height += c.top + c.bottom;
				if (b.width < d.width || b.height < d.height) {
					b.width = Math.max(b.width, d.width);
					b.height = Math.max(b.height, d.height)
				} else {
					if (b.width > e.width || b.height > e.height) {
						b.width = Math.min(b.width, e.width);
						b.height = Math.min(b.height, e.height)
					}
				}
			} else {
				b.width = this.bounds().width + c.left + c.right;
				b.height = this.bounds().height + c.top + c.bottom
			}
			return b
		}
	})(jQuery)
};
jQuery.extend({
	unselectContents: function() {
		if (window.getSelection) {
			window.getSelection().removeAllRanges()
		} else {
			if (document.selection) {
				document.selection.empty()
			}
		}
	}
});
jQuery.fn.extend({
	selectContents: function() {
		$(this).each(function(b) {
			var d = this;
			var c, a, f, e;
			if ((f = d.ownerDocument) && (e = f.defaultView) && typeof e.getSelection != "undefined" && typeof f.createRange != "undefined" && (c = window.getSelection()) && typeof c.removeAllRanges != "undefined") {
				a = f.createRange();
				a.selectNode(d);
				if (b == 0) {
					c.removeAllRanges()
				}
				c.addRange(a)
			} else {
				if (document.body && typeof document.body.createTextRange != "undefined" && (a = document.body.createTextRange())) {
					a.moveToElementText(d);
					a.select()
				}
			}
		})
	},
	setCaret: function() {
		if (!$.browser.msie) {
			return
		}
		var a = function() {
			var b = $(this).get(0);
			b.caretPos = document.selection.createRange().duplicate()
		};
		$(this).click(a).select(a).keyup(a)
	},
	insertAtCaret: function(c) {
		var b = $(this).get(0);
		if (document.all && b.createTextRange && b.caretPos) {
			var d = b.caretPos;
			d.text = d.text.charAt(d.text.length - 1) == "" ? c + "": c
		} else {
			if (b.setSelectionRange) {
				var g = b.selectionStart;
				var f = b.selectionEnd;
				var h = b.value.substring(0, g);
				var e = b.value.substring(f);
				b.value = h + c + e;
				b.focus();
				var a = c.length;
				b.setSelectionRange(g + a, g + a);
				b.blur()
			} else {
				b.value += c
			}
		}
	}
}); (function(a) {
	FR.frMenu = function(b) {
		this.$menuRoot = a("<div class='easyui-menu menu'>");
		this.init();
		this.applyOptions(b);
		return this
	};
	FR.frMenu.prototype = {
		init: function() {
			var b = this;
			this.$menuRoot.hover(function() {},
			function() {
				b.close()
			});
			this.$menuRoot.appendTo("body")
		},
		hasSubmenu: function() {
			var b = false;
			if (this.items) {
				a.each(this.items,
				function(c, d) {
					if (d.submenu) {
						b = true;
						return false
					}
				})
			}
			return b
		},
		applyOptions: function(b) {
			this.removechildren();
			this.$menuRoot.css("z-index", 3333);
			this.items = b.items;
			this.$el = b.$el;
			this.options = b;
			this.destroyOnClose = b.destroyOnClose != null ? b.destroyOnClose: true;
			this.closeOnHover = true;
			if (this.hasSubmenu()) {
				this.submenu = new FR.frMenu({
					destroyOnClose: false,
					item: []
				})
			}
			this.cItems = [];
			this.minWidth = b.minWidth || 80;
			this.xxxfixclientH = b.xxxfixclientH;
			this.xxxMMenu = b.xxxMMenu;
			if (a.isArray(this.items)) {
				this.addItems(this.items)
			}
			if (this.xxxMMenu) {
				if (contentPane && contentPane.$contentPane && !FR.xxxmenu_array) {
					FR.xxxmenu_array = [];
					contentPane.$contentPane.click(function() {
						var d = [];
						for (var c = 0; c < FR.xxxmenu_array.length; c++) {
							d.unshift(FR.xxxmenu_array[c])
						}
						for (var c = 0; c < d.length; c++) {
							d[c].close(true)
						}
					})
				}
			}
		},
		addItems: function(c) {
			for (var d = 0,
			b = c.length; d < b; d++) {
				this.addItem(c[d])
			}
			if (this.minWidth && this.minWidth >= this.$menuRoot.width()) {
				this.$menuRoot.width(this.minWidth);
				this.$menuRoot.children("div").width(this.minWidth - 8)
			}
		},
		addItem: function(i) {
			var e = this;
			var d = a("<div style='width:80px'>").appendTo(this.$menuRoot);
			if (i.type === "sep") {
				d.addClass("menu-sep").html("'&nbsp;'")
			} else {
				e.bindMenuItemEvent(d, i);
				var h = a('<div class="menu-text"></div>').html(i.src).appendTo(d);
				var c = h.position().left * 2 + h.width();
				if (this.minWidth < c) {
					this.minWidth = c
				}
				var f = i.iconSrc ? i.iconSrc.replace("css:", "") : "";
				if (f) {
					a('<div class="menu-icon"></div>').addClass(f).appendTo(d)
				}
				if (i.submenu) {
					var g = i.arrowSrc ? i.arrowSrc.replace("css:", "") : "";
					if (g) {
						a('<div class="menu-customarrow"></div>').addClass(g).appendTo(d)
					} else {
						a('<div class="menu-rightarrow"></div>').appendTo(d)
					}
				}
				if (a.boxModel == true) {
					var b = d.height() || 22;
					d.height(b - (d.outerHeight() - d.height()))
				}
				this.cItems[this.cItems.length] = d
			}
		},
		children: function() {
			return this.cItems
		},
		bindMenuItemEvent: function(c, d) {
			c.addClass("menu-item");
			var b = this;
			if (d.submenu) {
				c.hover(function() {
					b.closeSub();
					c.siblings().each(function() {
						a(this).removeClass("menu-active")
					});
					if (c.attr("disabled")) {
						return
					}
					c.addClass("menu-active");
					if (a.isArray(d.submenu)) {
						var e = {
							destroyOnClose: false,
							minWidth: b.minWidth,
							items: d.submenu,
							parMenu: b
						};
						b.submenu.applyOptions(e);
						var f = c.offset().left + c.outerWidth() + 2;
						if (f + b.submenu.$menuRoot.outerWidth() > a(window).width()) {
							f = c.offset().left - b.submenu.$menuRoot.outerWidth()
						}
						b.submenu.position(f, b.xxxfixclientH ? (c.offset().top + c.height() - b.submenu.$menuRoot.height() - 4) : (c.offset().top - 3));
						b.submenu.show()
					}
				},
				function(f) {})
			} else {
				c.mousedown(function(f) {
					if (c.attr("disabled")) {
						return
					}
					d.handler.call(b, f, this);
					b.close(true)
				});
				c.hover(function() {
					b.closeSub();
					if (c.attr("disabled")) {
						return
					}
					c.siblings().each(function() {
						a(this).removeClass("menu-active")
					});
					c.addClass("menu-active")
				},
				function(f) {
					c.removeClass("menu-active")
				})
			}
		},
		position: function(b, c) {
			this.$menuRoot.css({
				left: b,
				top: c
			})
		},
		show: function() {
			this.$menuRoot.show();
			if (parentMenu = this.options.parMenu) {
				parentMenu.closeOnHover = false
			}
		},
		close: function(b) {
			if (b) {
				if (this.destroyOnClose) {
					this.remove()
				} else {
					this.closeSub()
				}
				if (parentMenu = this.options.parMenu) {
					parentMenu.close(true)
				} else {
					this.$menuRoot.hide()
				}
			} else {
				if (this.closeOnHover) {
					if (this.destroyOnClose) {
						this.remove()
					} else {
						this.closeSub();
						this.$menuRoot.hide()
					}
					if (parentMenu = this.options.parMenu) {
						parentMenu.closeOnHover = true
					}
				}
			}
		},
		remove: function() {
			this.$menuRoot.remove();
			if (this.$el) {
				delete this.$el.fr_menu
			}
			if (this.submenu) {
				this.submenu.remove()
			}
		},
		removechildren: function() {
			if (this.cItems) {
				for (var c = 0,
				b = this.cItems.length; c < b; c++) {
					this.cItems[c].remove()
				}
			}
			if (this.submenu) {
				this.submenu.remove()
			}
		},
		closeSub: function() {
			if (this.submenu && !this.submenu.$menuRoot.is(":hidden")) {
				this.submenu.close()
			}
		}
	}
})(jQuery);
$.extend(FR, {
	createEmptyRecord: function(a) {
		return new FR.Record({
			data: {
				value: "",
				text: a ? a: ""
			},
			showField: "text",
			titleField: "titleText"
		})
	},
	createItemRecord: function(a) {
		return new FR.Record({
			data: a,
			showField: "text",
			valueField: "value",
			titleField: "titleText"
		})
	},
	createItemNode: function(b, a) {
		return new FR.Node({
			data: b,
			showField: "text",
			titleField: "titleText",
			url: a,
			valueField: "value",
			hasChild: "hasChildren",
			parameterFields: ["id", "value"]
		})
	},
	createDefaultRootNodes: function(a) {
		return new FR.RootNodeLoader({
			url: a
		})
	}
});
FR.Record = FR.extend(FR.OB, {
	_defaultConfig: function() {
		return $.extend(FR.ObjectSource.superclass._defaultConfig.apply(), {
			data: [],
			createFields: [],
			choosedFields: [],
			showField: "",
			titleField: "",
			showEmptyValue: "",
			valueField: "",
			showTemplate: "",
			valueTemplate: ""
		})
	},
	_init: function() {
		FR.Record.superclass._init.apply(this, arguments);
		$.extend(this, this.options);
		this.nameProperty();
		this.initMethods()
	},
	nameProperty: function() {
		if (this.createFields) {
			for (var b = 0,
			a = this.createFields.length; b < a; b++) {
				if (this.createFields[b] != b) {
					this.data[this.createFields[b]] = this.data[b];
					delete this.data[b]
				}
			}
		}
	},
	initMethods: function() {
		if (this.choosedFields) {
			var d = this;
			var c = function(i) {
				var i = i;
				return function() {
					return d.data[i]
				}
			};
			var g = function(i) {
				var i = i;
				return function(j) {
					d.data[i] = j
				}
			};
			for (var f = 0,
			a = this.choosedFields.length; f < a; f++) {
				var e = this.choosedFields[f];
				var h = e.startWith("is") || e.startWith("get") ? e: "get" + e;
				var b;
				if (e.startWith("is")) {
					b = "set" + e.substr(2)
				} else {
					if (e.startWith("get")) {
						b = "set" + e.substr(3)
					} else {
						if (e.startWith("set")) {
							b = e
						} else {
							b = "set" + e
						}
					}
				}
				this[h] = c(e);
				this[b] = g(e)
			}
		}
	},
	getShowValue: function() {
		var a = this.getShowTemplate() || this.getShowField();
		if (a == null) {
			a = this.getNoAppointShowValue()
		}
		a = this.formatShowValue(a);
		return (this.showEmptyValue && a == null) ? this.showEmptyValue: a
	},
	getTitleValue: function() {
		if (this.titleField) {
			return this.data[this.titleField]
		}
	},
	setTitleValue: function(a) {
		if (this.titleField) {
			this.data[this.titleField] = a;
			return true
		}
	},
	setShowValue: function(a) {
		this.setShowTemplate(a) || this.setShowField(a)
	},
	setShowTemplate: function(a) {
		if (this.showTemplate) {
			this.data[this.showTemplate] = a;
			return true
		}
	},
	setShowField: function(a) {
		if (this.showField) {
			this.data[this.showField] = a;
			return true
		}
	},
	getValue: function() {
		var a = this.getValueTemplate() || this.getValueField();
		if (a != null) {
			return a
		}
		return this.getNoAppointValue()
	},
	setValue: function(a) {
		this.setValueTemplate(a) || this.setValueField(a)
	},
	setValueTemplate: function(a) {
		if (this.valueTemplate) {
			this.data[this.valueTemplate] = a;
			return true
		}
	},
	setValueField: function(a) {
		if (this.valueField) {
			this.data[this.valueField] = a;
			return true
		}
	},
	getNoAppointShowValue: function() {
		if (this.showField != null || this.showTemplate != null) {
			return
		}
		return this.getFirstProperty()
	},
	getNoAppointValue: function() {
		if (this.valueField != null || this.valueTemplate != null) {
			return
		}
		return this.getFirstProperty()
	},
	getFirstProperty: function() {
		for (var a in this.data) {
			if (!$.isFunction(this.data[a])) {
				return this.data[a]
			}
		}
	},
	getShowField: function() {
		if (this.showField) {
			return this.data[this.showField]
		}
	},
	getValueField: function() {
		if (this.valueField) {
			return this.data[this.valueField]
		}
	},
	getShowTemplate: function() {
		if (this.showTemplate) {
			return this.dealTemplate(this.showTemplate)
		}
	},
	getValueTemplate: function() {
		if (this.valueTemplate) {
			return this.dealTemplate(this.valueTemplate)
		}
	},
	dealTemplate: function(b) {
		var a;
		while ((a = /{[^{}]+}/g.exec(b)) != null) {
			b = b.substr(0, a.index) + a[0].substr(1, a[0].length - 1) + b.substr(a.index + a[0].index)
		}
		return b
	},
	formatShowValue: function(a) {
		if (typeof a == "string" && a.indexOf("__time__") > -1) {
			return FR.jsonDecode(a).format(FR.convertJavaDateFormat2JS("yyyy-MM-dd HH:mm:ss"))
		}
		return a
	},
	getContent: function() {
		return this.data
	},
	setContent: function(a) {
		this.data = a
	},
	getProperty: function(a) {
		return this.data[a]
	},
	setProperty: function(a, b) {
		this.data[a] = b
	}
});
FR.Node = FR.extend(FR.Record, {
	_init: function() {
		FR.Node.superclass._init.apply(this, arguments);
		if (!this.nodeSource) {
			if (this.children && this.data[this.children]) {
				this.nodeSource = new FR.ObjectSource({
					data: this.data[this.children]
				})
			} else {
				if (this.options.url) {
					this.nodeSource = new FR.URLSource({
						url: this.options.url
					})
				}
			}
		}
		if (!this.nodeReader) {
			this.nodeReader = new FR.NodeReader(this.copyProperties())
		}
		this.childrenNodes = []
	},
	getParent: function() {
		return this.parent
	},
	setParent: function(a) {
		this.parent = a
	},
	getPathAsValueString: function() {
		var a = [this.data.value];
		var b = this.getParent();
		while (b) {
			a.unshift(b.data.value);
			b = b.getParent()
		}
		return a.join(",")
	},
	getPathAsTextString: function() {
		var a = [this.data.text];
		var b = this.getParent();
		while (b) {
			a.unshift(b.data.text);
			b = b.getParent()
		}
		return a.join(",")
	},
	getLeafNode: function(c) {
		if (c == undefined) {
			var c = []
		}
		if (!this.hasChild()) {
			c.push(this);
			return c
		} else {
			var b = this.getChildren();
			for (var a = 0; a < b.length; a++) {
				b[a].getLeafNode(c)
			}
		}
		return c
	},
	getAncestorsLength: function() {
		var a = 0;
		var b = this.getParent();
		while (b) {
			a++;
			b = b.getParent()
		}
		return a
	},
	hasChild: function() {
		return this.hasChildren != null ? this.data[this.hasChildren] || (this.data.ChildNodes && (this.data.ChildNodes).length != 0) : ((this.children != null && this.data[this.children]) || this.getChildrenLength() > 0)
	},
	getChildren: function() {
		return this.loadChildren()
	},
	getChildrenLength: function() {
		return this.loadChildren().length
	},
	getChild: function(a) {
		return this.loadChildren()[a]
	},
	getID: function() {
		return this.data[this.id]
	},
	setID: function(a) {
		this.data[this.id] = a
	},
	getParentID: function() {
		var a = this.data[this.parentID];
		return a != null ? a: (this.getParent() ? this.getParent().getID() : null)
	},
	setParentID: function() {},
	isSelected: function() {
		return this.selectedField != null ? this.data[this.selectedField] : this.selected
	},
	setSelected: function(a) {
		this.selectedField != null ? this.data[this.selectedField] = a: this.selected = a
	},
	loadChildren: function() {
		if (!this.hasChild()) {
			return []
		}
		if (this.isLoaded) {
			return this.childrenNodes
		}
		if (!this.nodeSource) {
			return []
		}
		this.resetStatus();
		this.childrenNodes = this.nodeReader.readObject(this.nodeSource.loadObject());
		this.isLoaded = true;
		return this.childrenNodes
	},
	isLoadComplete: function() {
		return this.nodeSource ? this.nodeSource.isLoaded() : true
	},
	copyProperties: function() {
		var a = {};
		this.createFields && (a.createFields = this.createFields);
		this.choosedFields && (a.choosedFields = this.choosedFields);
		this.showField && (a.showField = this.showField);
		this.titleField && (a.titleField = this.titleField);
		this.showEmptyValue && (a.showEmptyValue = this.showEmptyValue);
		this.valueField && (a.valueField = this.valueField);
		this.showTemplate && (a.showTemplate = this.showTemplate);
		this.valueTemplate && (a.valueTemplkate = this.valueTemplate);
		this.parameterFields && (a.parameterFields = this.parameterFields);
		this.hasChildren && (a.hasChildren = this.hasChildren);
		this.type && (a.type = this.type);
		this.id != null && (a.id = this.id);
		this.parentID != null && (a.parentID = this.parentID);
		this.options.url && (a.url = this.getChildURL() || this.options.url);
		this.childurlField && (a.childurlField = this.childurlField);
		this.children && (a.children = this.children);
		this.selectedField && (a.selectedField = this.selectedField);
		a.parent = this;
		return a
	},
	getChildURL: function() {
		return this.childurl ? this.childurl: this.data[this.childurlField]
	},
	setChildURL: function(a) {
		this.childurlField != null ? this.data[this.childurlField] = a: this.childurl = a
	},
	addNode: function(a) {
		this.isLoaded = true;
		this.childrenNodes.push(a);
		a.setParent(this)
	},
	removeNode: function(a) {
		this.childrenNodes.remove(a)
	},
	sortChildren: function() {},
	clearData: function() {
		this.isLoaded = false;
		delete this.childrenNodes;
		this.childrenNodes = []
	},
	createParaObj: function() {
		var b = {};
		if (this.parameterFields) {
			for (var c = 0,
			a = this.parameterFields.length; c < a; c++) {
				b[this.parameterFields[c]] = this.data[this.parameterFields[c]]
			}
		}
		if (this.additionalParameters) {
			for (var c in this.additionalParameters) {
				b[c] = this.additionalParameters[c]
			}
		}
		return {
			parameter: b
		}
	},
	additionalParameter: function(a) {
		this.additionalParameters = a.parameter
	},
	getAdditionalNodes: function() {
		var a = this.nodeReader.readObject(this.nodeSource.loadObject());
		this.childrenNodes = this.childrenNodes.concat(a);
		return a
	},
	resetStatus4AddNodes: function(b) {
		var a = this.createParaObj();
		$.extend(a.parameter, b.parameter);
		this.nodeSource.resetStatus(a)
	},
	resetStatus: function() {
		if (!this.nodeSource) {
			return
		}
		if (this.nodeSource.resetStatus(this.createParaObj())) {
			this.clearData()
		}
	}
});
FR.RootNodeLoader = FR.extend(FR.OB, {
	_init: function() {
		FR.RootNodeLoader.superclass._init.apply(this, arguments);
		this.options.rootSource = new FR.URLSource({
			url: this.options.url,
			data: this
		});
		if (!this.options.rootReader) {
			this.options.rootReader = new FR.DefaultNodeReader({
				url: this.options.url
			})
		}
		this.rootNodes = []
	},
	updateData: function(b) {
		var a = this.options.rootReader.readObject(b);
		if (this.rootNodes.length < 1) {
			this.rootNodes = a;
			this.fireEvent(FR.Events.AFTERREAD, a)
		} else {
			Array.prototype.push.apply(this.rootNodes, a);
			this.fireEvent(FR.Events.APPENDDATA, a)
		}
	},
	getRootNodes: function() {
		if (this.isLoaded) {
			return this.rootNodes
		}
		this.rootNodes = this.loadNodes();
		this.isLoaded = true;
		return this.rootNodes
	},
	loadNodes: function() {
		return this.options.rootReader.readObject(this.options.rootSource.loadObject())
	},
	appendRoot: function(a) {
		this.rootNodes.push(a)
	},
	resetStatus4AddRootNodes: function(a) {
		this.options.rootSource.resetStatus(a)
	},
	resetStatus: function(a) {
		if (this.options.rootSource.resetStatus(a)) {
			this.clearRoots()
		}
	},
	clearRoots: function() {
		this.isLoaded = false;
		this.rootNodes = []
	}
});
FR.DataSource = FR.extend(FR.OB, {
	_init: function() {
		FR.DataSource.superclass._init.apply(this, arguments)
	},
	loadObject: function() {
		return {}
	},
	resetStatus: function() {
		return false
	},
	isLocalSource: function() {
		return true
	},
	isLoaded: function() {
		return true
	}
});
FR.ObjectSource = FR.extend(FR.DataSource, {
	_defaultConfig: function() {
		return $.extend(FR.ObjectSource.superclass._defaultConfig.apply(), {
			data: []
		})
	},
	_init: function() {
		FR.ObjectSource.superclass._init.apply(this, arguments)
	},
	loadObject: function() {
		return this.options.data
	},
	isLocalSource: function() {
		return true
	}
});
FR.URLSource = FR.extend(FR.DataSource, {
	_defaultConfig: function() {
		return $.extend(FR.URLSource.superclass._defaultConfig.apply(), {
			parameter: {},
			isLoaded: false
		})
	},
	_init: function() {
		FR.URLSource.superclass._init.apply(this, arguments);
		this.checkUrl()
	},
	checkUrl: function() {
		if (!this.options.url) {
			FR.Msg.toast("URL is needed to create URLSource!");
			return
		}
		return true
	},
	setData: function(a, b) {
		this.options.parameter = a;
		this.data = b;
		this.options.isLoaded = true
	},
	loadObject: function() {
		if (!this.checkUrl()) {
			return
		}
		var c = "";
		if (this.options.url.indexOf("widgetname") >= 0) {
			var a = this.options.url.split("&");
			$.each(a,
			function(d, e) {
				if (e.indexOf("widgetname") >= 0) {
					a[d] = "widgetname=" + encodeURIComponent(encodeURIComponent(e.substring(11, e.length)))
				}
			});
			$.each(a,
			function(d, e) {
				if (d != a.length - 1) {
					c += e + "&"
				} else {
					c += e
				}
			})
		}
		if (this.need2load()) {
			var b = this;
			$.ajax({
				type: "POST",
				url: (c.length == 0 ? b.options.url: c),
				data: this.generateParaData(),
				reload: true,
				async: false,
				complete: function(d) {
					if (!FR.versionRemind(d.responseText)) {
						return
					}
					b.fireEvent(FR.Events.AFTERLOAD);
					b.options.isLoaded = true;
					b.data = FR.jsonDecode(d.responseText)
				},
				error: function(d) {
					FR.Msg.toast("Try to get DataSource error!")
				}
			})
		}
		return this.data
	},
	resetStatus: function(a) {
		return this.resetUrl(arguments.length == 1 ? a.url: arguments[0]) || this.resetParameter(arguments.length == 1 ? a.parameter: arguments[1])
	},
	resetUrl: function(a) {
		if (a != null && a != this.options.url) {
			this.options.url = a;
			this.clearData();
			return true
		}
	},
	resetParameter: function(a) {
		if (a != null && !FR.equals(this.options.parameter, a)) {
			if (!FR.equals(this.options.parameter.dependence, a.dependence)) {
				this.reload = true
			} else {
				this.reload = false
			}
			this.options.parameter = a;
			this.clearData();
			return true
		}
	},
	need2load: function() {
		return this.data == null
	},
	clearData: function() {
		if (this.data != null) {
			this.options.isLoaded = false;
			delete this.data;
			this.data = null
		}
	},
	destroy: function() {
		this.options.url = null;
		this.options.parameter = null;
		this.clearData()
	},
	generateParaData: function() {
		if (this.options.parameter) {
			var b = {};
			for (var a in this.options.parameter) {
				if (!$.isFunction(this.options.parameter[a])) {
					b[a] = FR.cjkEncode((typeof this.options.parameter[a] == "string") ? this.options.parameter[a] : FR.jsonEncode(this.options.parameter[a]))
				}
			}
			if (this.reload) {
				b.reload = true
			}
			return b
		}
	},
	isLocalSource: function() {
		return this.isLoaded()
	},
	isLoaded: function() {
		return this.options.isLoaded
	}
});
FR.SynchronObjectSource = FR.extend(FR.DataSource, {
	_defaultConfig: function() {
		return $.extend(FR.ObjectSource.superclass._defaultConfig.apply(), {
			object: []
		})
	},
	_init: function() {
		FR.ObjectSource.superclass._init.apply(this, arguments)
	},
	loadObject: function() {
		this.options.data.updateData(this.options.object)
	},
	isLocalSource: function() {
		return true
	},
	resetStatus: function(a) {
		return false
	}
});
FR.SynchronTreeSource = FR.extend(FR.OB, {
	_init: function() {
		FR.SynchronTreeSource.superclass._init.apply(this, arguments)
	},
	loadObject: function() {
		this.options.data.updateData(this.options.treedata)
	},
	resetStatus: function() {
		return false
	}
});
FR.SynchronURLSource = FR.extend(FR.URLSource, {
	_init: function() {
		FR.SynchronURLSource.superclass._init.apply(this, arguments)
	},
	loadObject: function() {
		if (!this.checkUrl()) {
			return
		}
		var a = this;
		$.ajax({
			type: "POST",
			url: this.options.url,
			data: this.generateParaData(),
			complete: function(b) {
				if (!FR.versionRemind(b.responseText)) {
					return
				}
				a.fireEvent(FR.Events.AFTERLOAD);
				a.options.isLoaded = true;
				if (a.options.data) {
					a.options.data.updateData(FR.jsonDecode(b.responseText))
				}
			},
			error: function(b) {
				FR.Msg.toast("Try to get DataSource error!")
			}
		})
	},
	resetParameter: function(b) {
		if (b != null && !FR.equals(this.options.parameter, b)) {
			var a = false;
			if (!FR.equals(this.options.parameter.dependence, b.dependence)) {
				a = true;
				this.reload = true
			} else {
				this.reload = false
			}
			if (!FR.equals(this.options.parameter.filter, b.filter)) {
				a = true
			}
			this.options.parameter = b;
			return a
		}
	}
});
FR.DataReader = FR.extend(FR.OB, {
	_defaultConfig: function() {
		return $.extend(FR.DataReader.superclass._defaultConfig.apply(), {})
	},
	_init: function() {
		FR.DataReader.superclass._init.apply(this, arguments)
	},
	readObject: function(e) {
		if ($.isArray(e)) {
			var b = [];
			var c = {};
			if (this.options) {
				c.createFields = this.options.createFields;
				c.choosedFields = this.options.choosedFields;
				c.showField = this.options.showField;
				c.titleField = this.options.titleField;
				c.showEmptyValue = this.options.showEmptyValue;
				c.valueField = this.options.valueField;
				c.showTemplate = this.options.showTemplate;
				c.valueTemplate = this.options.valueTemplate
			}
			for (var d = 0,
			a = e.length; d < a; d++) {
				c.data = e[d];
				b.push(new FR.Record(c))
			}
			return b
		}
	}
});
FR.ArrayReader = FR.extend(FR.DataReader, {
	_init: function() {
		FR.ArrayReader.superclass._init.apply(this, arguments)
	}
});
FR.JSONReader = FR.extend(FR.DataReader, {
	_init: function() {
		FR.JSONReader.superclass._init.apply(this, arguments)
	}
});
FR.DefaultReader = FR.extend(FR.ArrayReader, {
	_init: function() {
		FR.DefaultReader.superclass._init.apply(this, arguments)
	},
	readObject: function(e) {
		if ($.isArray(e)) {
			var b = [];
			var c = {};
			c.showField = "text";
			c.titleField = "titleText";
			c.valueField = "value";
			for (var d = 0,
			a = e.length; d < a; d++) {
				c.data = e[d];
				b.push(new FR.Record(c))
			}
			return b
		}
	}
});
FR.NodeReader = FR.extend(FR.DataReader, {
	_init: function() {
		FR.NodeReader.superclass._init.apply(this, arguments)
	},
	readObject: function(e) {
		if (!$.isArray(e)) {
			e = [e]
		}
		var b = [];
		var c = this.nodeProperties();
		for (var d = 0,
		a = e.length; d < a; d++) {
			c.data = e[d];
			b.push(new FR.Node(c))
		}
		return b
	},
	nodeProperties: function() {
		var a = {};
		if (!this.options) {
			return
		}
		a.createFields = this.options.createFields;
		a.choosedFields = this.options.choosedFields;
		a.showField = this.options.showField;
		a.titleField = this.options.titleField;
		a.showEmptyValue = this.options.showEmptyValue;
		a.valueField = this.options.valueField;
		a.showTemplate = this.options.showTemplate;
		a.valueTemplate = this.options.valueTemplate;
		a.parameterFields = this.options.parameterFields;
		a.hasChildren = this.options.hasChildren;
		a.type = this.options.type;
		a.id = this.options.id;
		a.parentID = this.options.parentID;
		a.url = this.options.url;
		a.parent = this.options.parent;
		a.selectedField = this.options.selectedField;
		a.children = this.options.children;
		return a
	}
});
FR.DefaultNodeReader = FR.extend(FR.NodeReader, {
	_init: function() {
		FR.DefaultNodeReader.superclass._init.apply(this, arguments)
	},
	nodeProperties: function() {
		return {
			url: this.options.url,
			id: "id",
			parentID: "parentID",
			showField: "text",
			titleField: "titleText",
			valueField: "value",
			hasChildren: "hasChildren",
			children: "ChildNodes",
			parameterFields: ["id", "value"],
			choosedFields: ["isexpand", "nodeicon", "classes", "showcheck", "checkstate"],
			parent: this.options.parent
		}
	}
});
FR.DefaultSynchronNodeReader = FR.extend(FR.NodeReader, {
	_init: function() {
		FR.DefaultSynchronNodeReader.superclass._init.apply(this, arguments)
	},
	nodeProperties: function() {
		return {
			id: "id",
			parentID: "parentID",
			showField: "text",
			titleField: "titleText",
			valueField: "value",
			hasChildren: "hasChildren",
			children: "ChildNodes",
			parameterFields: ["id", "value"],
			choosedFields: ["isexpand", "nodeicon", "classes", "showcheck", "checkstate"],
			parent: this.options.parent
		}
	}
});
FR.Data = FR.extend(FR.OB, {
	_defaultConfig: function() {
		return $.extend(FR.DataReader.superclass._defaultConfig.apply(), {
			records: []
		})
	},
	_init: function() {
		FR.Data.superclass._init.apply(this, arguments);
		if (!this.options.dataSource) {
			this.options.dataSource = new FR.ObjectSource()
		}
		if (!this.options.dataReader) {
			this.options.dataReader = new FR.DataReader()
		}
		this.records = this.options.records
	},
	executeData: function() {
		if (this.options.dataSource && this.options.dataReader) {
			return this.options.dataReader.readObject(this.options.dataSource.loadObject())
		}
	},
	getData: function() {
		return this.getRecords()
	},
	setData: function(a, b) {
		if (this.options.dataSource) {
			this.options.dataSource.setData(a, b)
		}
		this.records = this.executeData()
	},
	appendData: function() {
		var a = this.executeData();
		Array.prototype.push.apply(this.records, a);
		return a
	},
	getRecords: function() {
		if (this.records && this.records.length > 0) {
			return this.records
		}
		this.records = this.executeData();
		return this.records
	},
	getLoadedRecords: function() {
		return this.records
	},
	getLength: function() {
		return this.getRecords().length
	},
	getRecord: function(a) {
		return this.getRecords()[a]
	},
	addRecord: function(a) {
		this.getRecords().push(a)
	},
	sortRecords: function() {},
	removeRecord: function(a) {
		if (typeof a == "number") {
			this.getRecords().splice(a, 1)
		} else {
			if (typeof a == "object") {
				this.getRecords().remove(a)
			}
		}
	},
	clearData: function() {
		if (this.records && this.records.length > 0) {
			delete this.records;
			this.records = []
		}
	},
	resetStatus: function(a) {
		if (this.options.dataSource.resetStatus(a)) {
			this.clearData()
		}
	},
	resetStatusKeepData: function(a) {
		this.options.dataSource.resetStatus(a)
	},
	isLocalSource: function() {
		return this.options.dataSource.isLocalSource()
	},
	isLoaded: function() {
		return this.options.dataSource.isLoaded()
	}
});
FR.DataFactory = {};
$.extend(FR.DataFactory, {
	createArrayData: function(a) {
		return new FR.Data({
			dataSource: new FR.ObjectSource({
				data: a
			}),
			dataReader: new FR.DefaultReader()
		})
	},
	createSynchronArrayData: function(a) {
		return new FR.SynchronData({
			dataSource: new FR.SynchronObjectSource({
				object: a
			}),
			dataReader: new FR.DefaultReader()
		})
	},
	createJSONData: function(a) {
		return new FR.Data({
			dataSource: new FR.URLSource({
				url: a
			}),
			dataReader: new FR.DefaultReader()
		})
	},
	createSynchronJSONData: function(a, b) {
		return new FR.SynchronData({
			dataSource: new FR.SynchronURLSource({
				url: a,
				async: !!b
			}),
			dataReader: new FR.DefaultReader()
		})
	},
	createEmptyData: function() {
		return new FR.Data()
	}
});
FR.LocalTreeData = FR.extend(FR.OB, {
	_init: function() {},
	updateData: function() {},
	getData: function() {}
});
FR.TreeData = FR.extend(FR.OB, {
	_init: function() {
		FR.TreeData.superclass._init.apply(this, arguments);
		if (!this.options.treeLoader && !this.options.url) {
			FR.Msg.toast("DataUrl or TreeLoader is needed to create TreeData.");
			return
		}
		if (!this.options.treeLoader && this.options.url) {
			this.options.treeLoader = FR.createDefaultRootNodes(this.options.url)
		}
	},
	getData: function() {
		return this.options.treeLoader.getRootNodes()
	},
	loadNodes: function() {
		this.options.treeLoader.loadNodes()
	},
	getChildrenLength: function() {
		return this.options.treeLoader.getRootNodes().length
	},
	appendRoot: function(a) {
		this.options.treeLoader.add(a)
	},
	resetStatus4AddRootNodes: function(a) {
		this.options.treeLoader.resetStatus4AddRootNodes(a)
	},
	resetStatus: function(a) {
		this.options.treeLoader.resetStatus(a)
	},
	setData: function(a, b) {
		this.options.treeLoader.options.rootSource.setData(a, b)
	},
	afterRead: function(a) {
		if (!a) {
			return false
		}
		return this.options.treeLoader.on(FR.Events.AFTERREAD, a)
	},
	appendDataEvent: function(a) {
		if (!a) {
			return false
		}
		return this.options.treeLoader.on(FR.Events.APPENDDATA, a)
	},
	isLocalSource: function() {
		return false
	}
});
FR.SynchronData = FR.extend(FR.OB, {
	_init: function() {
		FR.SynchronData.superclass._init.apply(this, arguments);
		if (!this.options.url && !this.options.dataSource) {
			FR.Msg.toast("SourceURL is needed to create SynchronData");
			return
		}
		if (!this.options.dataSource) {
			this.options.dataSource = new FR.SynchronURLSource({
				url: this.options.url
			})
		}
		if (!this.options.dataReader) {
			this.options.dataReader = new FR.DefaultReader()
		}
		this.options.dataSource.options.data = this;
		this.records = []
	},
	updateData: function(b) {
		var a = this.options.dataReader.readObject(b);
		var c = this.records.length;
		this.appendData(a);
		return c ? this.fireEvent(FR.Events.APPENDDATA, a) : this.fireEvent(FR.Events.AFTERREAD, a)
	},
	addRecord: function(a) {
		this.records.push(a)
	},
	removeRecord: function(a) {
		this.records.remove(a)
	},
	getData: function() {
		return this
	},
	loadData: function() {
		this.options.dataSource.loadObject()
	},
	appendData: function(a) {
		if (a != null) {
			Array.prototype.push.apply(this.records, a)
		}
	},
	getRecords: function() {
		return this.records
	},
	getLoadedRecords: function() {
		return this.records
	},
	getLength: function() {
		return this.getRecords().length
	},
	getRecord: function(a) {
		return this.records[a]
	},
	afterRead: function(a) {
		if (!a) {
			return false
		}
		return this.on(FR.Events.AFTERREAD, a)
	},
	appendDataEvent: function(a) {
		if (!a) {
			return false
		}
		return this.on(FR.Events.APPENDDATA, a)
	},
	resetStatus: function(a) {
		this.options.dataSource.resetStatus(a);
		delete this.records;
		this.records = [];
		return true
	}
});
FR.SynchronTreeData = FR.extend(FR.OB, {
	_init: function() {
		FR.SynchronTreeData.superclass._init.apply(this, arguments);
		if (!this.options.url && !this.options.dataSource) {
			FR.Msg.toast("SourceURL is needed to create SynchronTreeData");
			return
		}
		if (!this.options.dataSource) {
			this.options.dataSource = new FR.SynchronURLSource({
				url: this.options.url
			})
		}
		if (!this.options.dataReader) {
			this.options.dataReader = new FR.DefaultSynchronNodeReader()
		}
		this.options.dataSource.options.data = this;
		this.nodes = []
	},
	updateData: function(b) {
		var a = this.options.dataReader.readObject(b);
		var c = this.nodes.length;
		this.appendData(a);
		return c ? this.fireEvent(FR.Events.APPENDDATA, a) : this.fireEvent(FR.Events.AFTERREAD, a)
	},
	getData: function() {
		return this.nodes
	},
	loadNodes: function() {
		this.loadData()
	},
	loadData: function() {
		this.options.dataSource.loadObject()
	},
	appendData: function(b) {
		for (var c = 0,
		a = b.length; c < a; c++) {
			var d;
			if (b[c].getParentID() != null) {
				d = this.getParentNodeByParentID(b[c].getParentID())
			} else {
				d = this.getParentNode(b[c].getID())
			}
			if (d) {
				d.addNode(b[c])
			} else {
				this.nodes.push(b[c])
			}
		}
	},
	removeData: function(b) {
		var a = b.getParent();
		if (a) {
			a.removeNode(b)
		} else {
			this.nodes.remove(b)
		}
	},
	getParentNodeByParentID: function(d) {
		for (var c = 0,
		a = this.nodes.length; c < a; c++) {
			var b = this.getNodeByID(this.nodes[c], d);
			if (b) {
				return b
			}
		}
	},
	getNodeByID: function(e, f) {
		if (e.getID() == f) {
			return e
		}
		var c = e.getChildren();
		for (var b = 0,
		a = c.length; b < a; b++) {
			var d = this.getNodeByID(c[b], f);
			if (d) {
				return d
			}
		}
	},
	getParentNode: function(e) {
		var b = new String(e).split("-");
		if (b.length < 2) {
			return
		}
		var d = this.nodes[b[0] - 1];
		for (var c = 1,
		a = b.length - 1; c < a; c++) {
			d = d.getChild(b[c] - 1)
		}
		return d
	},
	getAllNodesLength: function() {
		var c = this.nodes.length;
		for (var b = 0,
		a = this.nodes.length; b < a; b++) {
			c += this.executeLength(this.nodes[b])
		}
		return c
	},
	isEmptyData: function() {
		return this.nodes.length < 1
	},
	executeLength: function(c) {
		var d = c.getChildrenLength();
		for (var b = 0,
		a = c.getChildrenLength(); b < a; b++) {
			d += this.executeLength(c.getChild(b))
		}
		return d
	},
	afterRead: function(a) {
		if (!a) {
			return false
		}
		return this.on(FR.Events.AFTERREAD, a)
	},
	appendDataEvent: function(a) {
		if (!a) {
			return false
		}
		return this.on(FR.Events.APPENDDATA, a)
	},
	getLastRootNode: function() {
		return this.nodes[this.nodes.length - 1]
	},
	resetStatus: function(a) {
		if (this.options.dataSource.resetStatus(a)) {
			delete this.nodes;
			this.nodes = [];
			return true
		}
	},
	isLocalSource: function() {
		return false
	}
});
FR.TableData = FR.extend(FR.OB, {});
FR.widget = {
	opts: {
		zIndex: 8000,
		num: 0
	}
};
FR.Write = FR.extend(FR.OB, {
	_init: function() {
		FR.Write.superclass._init.apply(this, arguments);
		this.location_widgets = {};
		this.name_widgets = {};
		var b = this;
		var a = this.options.selector || "td[widget]";
		if (typeof a == "string") {
			$(a, this.options.renderEl).each(function(c, d) {
				if (d.hasInit != true) {
					b.addWidget($(d))
				}
			})
		}
	},
	addEditor: function(a) {
		if (a.options.location) {
			this.location_widgets[a.options.location] = a
		}
		if (a.options.widgetName) {
			this.name_widgets["$" + a.options.widgetName.toUpperCase()] = a
		}
	},
	addWidget: function(a) {
		a[0].hasInit = true;
		this.addLocationWidget(a, FR.jsonDecode(a.attr("widget")))
	},
	addWriteWidget: function(a) {
		this.addLocationWidgetWithoutWriteEvent(a, FR.jsonDecode(a.attr("editor")))
	},
	addLocationWidgetWithoutWriteEvent: function(c, d) {
		d.width = c.attr("widgetWidth");
		d.height = c.attr("widgetHeight");
		$.extend(d, {
			write: this
		});
		$editor = $("<div/>").appendTo(c);
		d.renderEl = $editor;
		if (d.jspath) {
			var b = d.jspath.split(";");
			$.each(b,
			function(e, f) {
				FR.$import(f, "js")
			})
		}
		if (d.csspath) {
			var b = d.csspath.split(";");
			$.each(b,
			function(e, f) {
				FR.$import(f, "css")
			})
		}
		var a = FR.createWidget(d);
		if (d.location) {
			this.location_widgets[d.location] = a
		}
		if (d.widgetName) {
			this.name_widgets["$" + d.widgetName.toUpperCase()] = a
		}
	},
	addLocationWidget: function(a, b) {
		this.addLocationWidgetWithoutWriteEvent(a, b);
		if (b.location) {
			this.widgetOnWriteEvent(this.location_widgets[b.location])
		}
	},
	widgetOnWriteEvent: function(a) {
		a.on(FR.Events.BEFORESTATECHANGE,
		function() {
			if (this.isEnabled()) {
				if (!_g(this.options.sessionID).curLGP.stopCellEditing()) {
					return false
				}
			}
		});
		a.on(FR.Events.STATECHANGE,
		function() {
			if (!this.isValidate()) {
				FR.Msg.toast(this.getErrorMessage());
				return false
			}
			_g(this.options.sessionID).curLGP.fireCellValueChange(_g(this.options.sessionID).curLGP.getWidgetCell(this), this.getValue())
		})
	},
	resolveVariable: function(a) {
		if ($.isFunction(this.options.resolveVariable)) {
			return this.options.resolveVariable(a)
		}
	},
	getWidgetByCell: function(a) {
		if (!this.location_widgets || !a) {
			return null
		}
		return this.location_widgets[a] || this.findLocationWidget(a)
	},
	getWidgetByName: function(a) {
		if (!a) {
			return
		}
		a = ((a.indexOf("$") != 0) ? "$" + a: a).toUpperCase();
		return this.name_widgets[a] || this.findNameWidget(a)
	},
	findLocationWidget: function(b) {
		var e = $("td[editor]", this.options.renderEl);
		for (var d = 0,
		a = e.length; d < a; d++) {
			var c = FR.jsonDecode($(e[d]).attr("editor"));
			if (c.location == b) {
				$.extend(c, {
					write: this,
					width: e[d].offsetWidth + _g().curLGP.getewadjst(),
					height: e[d].offsetHeight + _g().curLGP.getehadjst()
				});
				this.addEditor(FR.createWidget(c));
				return this.location_widgets[b]
			}
		}
	},
	findNameWidget: function(c) {
		var f = $("td[editor]", this.options.renderEl);
		for (var e = 0,
		a = f.length; e < a; e++) {
			var b = FR.jsonDecode($(f[e]).attr("editor"));
			if (b.widgetName) {
				var d = ((b.widgetName.indexOf("$") != 0) ? "$" + b.widgetName: b.widgetName).toUpperCase();
				if (d == c) {
					$.extend(b, {
						write: this,
						width: f[e].offsetWidth + _g().curLGP.getewadjst(),
						height: f[e].offsetHeight + _g().curLGP.getehadjst()
					});
					this.addEditor(FR.createWidget(b));
					return this.name_widgets[c]
				}
			}
		}
	}
});
$.shortcut("fr_write", FR.Write);
FR.Form = FR.extend(FR.OB, {
	type: "fr_form",
	_init: function() {
		FR.Form.superclass._init.apply(this, arguments);
		this.name_widgets = {};
		this.location_widgets = {};
		var b = this;
		var a = this.options.selector;
		if (typeof a == "string") {
			var c;
			$(a, this.options.renderEl).each(function(d, e) {
				if (e.hasInit != true) {
					c = b.addWidget($(e));
					if (c.options.widgetName != null) {
						b.initLinkWidgetEvent(c)
					}
				}
			})
		}
	},
	addWidget: function(c) {
		c[0].hasInit = true;
		var d = FR.jsonDecode(c.attr("widget"));
		d.width = c.attr("width");
		d.height = c.attr("height");
		$.extend(d, {
			form: this
		});
		$editor = $("<div/>").appendTo(c);
		d.renderEl = $editor;
		if (d.jspath) {
			var b = d.jspath.split(";");
			$.each(b,
			function(e, f) {
				FR.$import(f, "js")
			})
		}
		if (d.csspath) {
			var b = d.csspath.split(";");
			$.each(b,
			function(e, f) {
				FR.$import(f, "css")
			})
		}
		var a = FR.createWidget(d);
		if (d.widgetName) {
			this.name_widgets["$" + d.widgetName.toUpperCase()] = a
		}
		if (d.location) {
			this.location_widgets[d.location] = a
		}
		return a
	},
	initLinkWidgetEvent: function(c) {
		if (!c || !c.options) {
			return
		}
		var a = c.options.dependence;
		var b = this;
		if (FR.isArray(a)) {
			$.each(a,
			function(d, f) {
				var e = b.getWidgetByName(f);
				if (e != null && e != c) {
					if (!e.$linkWidget) {
						e.$linkWidget = {
							data: {},
							value: {}
						};
						e.on(FR.Events.AFTEREDIT,
						function() {
							b.fireWidgetValueChanged(e)
						})
					}
					e.$linkWidget.data[c.options.widgetName] = c
				}
			})
		}
	},
	fireWidgetValueChanged: function(b) {
		for (var a in b.$linkWidget.data) {
			b.$linkWidget.data[a].reset()
		}
	},
	getWidgetByName: function(a) {
		if (!a) {
			return
		}
		a = ((a.indexOf("$") != 0) ? "$" + a: a).toUpperCase();
		return this.name_widgets[a] || this.findNameWidget(a)
	},
	findNameWidget: function(b) {
		var d = $(this.options.selector, this.options.renderEl);
		for (var c = 0,
		a = d.length; c < a; c++) {
			if (FR.jsonDecode($(d[c]).attr("widget")).widgetName && "$" + FR.jsonDecode($(d[c]).attr("widget")).widgetName.toUpperCase() == b) {
				this.addWidget($(d[c]));
				this.initLinkWidgetEvent(this.name_widgets[b]);
				return this.name_widgets[b]
			}
		}
	},
	findLocationWidget: function(b) {
		var d = $(this.options.selector, this.options.renderEl);
		for (var c = 0,
		a = d.length; c < a; c++) {
			if (FR.jsonDecode($(d[c]).attr("widget")).location == b) {
				this.addWidget($(d[c]));
				if (this.location_widgets[b].options.widgetName != null) {
					this.initLinkWidgetEvent(this.location_widgets[b])
				}
				return this.location_widgets[b]
			}
		}
	},
	resolveVariable: function(a) {
		var b;
		if ($.isFunction(this.options.resolveVariable)) {
			b = this.options.resolveVariable(a)
		}
		if (b == null) {
			depWidget = this.getWidgetByName(a);
			b = depWidget == null ? null: depWidget.getValue()
		}
		return b
	},
	verify: function() {
		var a = this;
		var b = true;
		for (var c in this.name_widgets) {
			var d = this.name_widgets[c];
			if (d.isVisible() && d.isEnabled() && d.isValidate && !d.isValidate()) {
				FR.Msg.toast(d.errorMsg);
				b = false
			}
		}
		return b
	},
	getWidgetName4Collection: function(a) {
		return a.options.widgetName.toUpperCase()
	},
	collectionValue: function(c) {
		var e = {};
		for (var a in this.location_widgets) {
			var b = this.location_widgets[a];
			if (! (b.options.needSubmit === true)) {
				continue
			}
			if (this.getWidgetName4Collection(b)) {
				var d = b.getValue();
				if (d == undefined) {
					d = null
				}
				e[this.getWidgetName4Collection(b)] = d
			}
		}
		if (c) {
			$.each(c,
			function(f, g) {
				e[f] = g
			})
		}
		return FR.jsonEncode(e)
	},
	dbCommit: function(b, a) {
		if (_g().saveReport) {
			_g().saveReport((function() {
				this.formSubmit({
					url: FR.servletURL + "?op=dbcommit",
					data: {
						location: a ? a.options.location: null,
						sheetNum: _g().selectedIndex,
						xmlconf: b.xmlconf,
						callback: b.callback,
						feedbackMap: b.feedbackMap,
						sessionID: _g().currentSessionID
					}
				})
			}).createDelegate(this))
		} else {
			this.formSubmit({
				url: FR.servletURL + "?op=dbcommit",
				data: {
					xmlconf: b.xmlconf,
					callback: b.callback,
					feedbackMap: b.feedbackMap,
					sessionID: _g().currentSessionID
				}
			})
		}
	},
	formSubmit: function() {
		if (!this.verify()) {
			$.each(this.location_widgets,
			function(d, e) {
				e.enable()
			});
			return
		}
		var b = arguments[0];
		if (b.url == null) {
			return
		}
		var c = this.config = $.extend({
			asyn: true
		},
		b);
		this.$sform = $($(".form-submit", this.element)[0]);
		var a = this;
		if (c.asyn) {
			FR.ajax({
				url: c.url,
				type: "POST",
				data: {
					__parameters__: this.collectionValue(c.data)
				},
				complete: function(f, d) {
					var e = FR.jsonDecode(f.responseText);
					if (c.callback) {
						c.callback.call(a, f, d)
					}
					if (e.callback) {
						f.responseText = e.oldres;
						e.callback.call(a, f, d)
					}
					a._fireSubmitEvents(d)
				}
			})
		} else {
			this.$sform.attr("method", "post");
			this.$sform.attr("action", b.url);
			if (b.target) {
				this.$sform.attr("target", b.target)
			}
			if (!this.$sform_hidden) {
				this.$sform_hidden = $("<input name = '__parameters__' type='hidden'/>").appendTo(this.$sform);
				this.$sform_hidden.attr("value", FR.cjkEncode(this.collectionValue(c.data)))
			}
			this.$sform.submit()
		}
	},
	sentMail: function(a) {
		FR.ajax({
			url: FR.servletURL,
			type: "POST",
			async: false,
			data: {
				op: "fr_email",
				cmd: "sent_email",
				sessionID: FR.SessionMgr.getSessionID(),
				mailXML: a.xmlconf,
				paraMap: a.paraMap || {},
				__parameters__: this.collectionValue()
			}
		})
	},
	_fireSubmitEvents: function(a) {
		if (a == "success") {
			this.fireEvent(FR.Events.SS)
		} else {
			this.fireEvent(FR.Events.SF)
		}
		this.fireEvent(FR.Events.SC)
	},
	getValueByName: function(a) {
		var b = this.getWidgetByName(a);
		if (b && b.getValue) {
			return b.getValue()
		}
	},
	visible: function(e, b) {
		if ($.isArray(e)) {
			for (var c = 0,
			a = e.length; c < a; c++) {
				var d = this.getWidgetByName(e[c]);
				if (d) {
					d.visible(b)
				}
			}
		}
	},
	invisible: function(e, b) {
		if ($.isArray(e)) {
			for (var c = 0,
			a = e.length; c < a; c++) {
				var d = this.getWidgetByName(e[c]);
				if (d) {
					d.invisible(b)
				}
			}
		}
	}
});
$.shortcut("fr_form", FR.Form);
FR.contentForm = FR.extend(FR.Form, {
	type: "fr_contentForm",
	_init: function() {
		this.name_widgets = this.location_widgets = {};
		this.datakey_widgets = [];
		this.group_widgets = {};
		this.options = $.extend(this.options, {
			form: this
		});
		this.sessionID = this.options.widgetUrl.getQuery("sessionID");
		this.content = this.createConfig2Form(this.options);
		this.content.fireEvent(FR.Events.AFTERINIT)
	},
	createConfig2Form: function(c, a) {
		var f = this.afterInitEventList == undefined;
		if (f) {
			this.afterInitEventList = {}
		}
		config = this._createConfig4Form(c);
		var e = FR.createWidget(config);
		if (a === true) {
			this.loadContentData(e)
		}
		e.addWidget2Form(this);
		var b = this;
		$.each(this.name_widgets,
		function(g, h) {
			if (h.options.widgetName != null) {
				b.initLinkWidgetEvent(h)
			}
		});
		if (f) {
			var d = this.afterInitEventList;
			var b = this;
			delete this.afterInitEventList;
			e.once(FR.Events.AFTERINIT,
			function() {
				for (w in d) {
					var j = d[w];
					var k = b.getWidgetByName(w);
					if (k && FR.isArray(j)) {
						for (var h = 0; h < j.length; h++) {
							var g = j[h].action.createDelegate(j[h].target ? j[h].target: k);
							if (g.apply(k) === false) {
								break
							}
						}
					}
				}
			})
		}
		return e
	},
	_createConfig4Form: function(c) {
		c.form = this;
		if (c.items && c.type != "card") {
			for (var d = 0; d < c.items.length; d++) {
				var b;
				if (c.items[d].el) {
					b = c.items[d].el
				} else {
					if (c.items[d]) {
						b = c.items[d]
					}
				}
				if (b) {
					this._createConfig4Form(b)
				}
			}
		}
		if (c.listeners != null) {
			for (var d = 0; d < c.listeners.length; d++) {
				var a = c.listeners[d];
				if (a.eventName == "afterinit") {
					if (c.form.afterInitEventList[c.widgetName] == null) {
						c.form.afterInitEventList[c.widgetName] = []
					}
					c.listeners.remove(a);
					d--;
					c.form.afterInitEventList[c.widgetName].push(a)
				}
			}
		}
		return c
	},
	add: function(c) {
		if (c.options.Databinding) {
			var a = c.options.Databinding;
			a.widget = c;
			this.datakey_widgets.push(a)
		}
		if (c.options.group) {
			var d = c.options.group;
			if (this.group_widgets[d]) {
				this.group_widgets[d].push(c)
			} else {
				this.group_widgets[d] = [];
				this.group_widgets[d].push(c)
			}
		}
		var b = this;
		c.load = function() {
			b.loadContentData(this)
		};
		this.name_widgets[c.options.widgetName] = c;
		this.location_widgets[c.options.widgetName] = c
	},
	getWidgetByCell: function() {
		throw (' "getWidgetByCell" is not a function in form')
	},
	_confirmEvents: function() {
		this.fireEvent(FR.Events.AFTERINIT);
		this.doLayout()
	},
	initLinkWidgetEvent: function(c) {
		if (!c || !c.options) {
			return
		}
		FR.contentForm.superclass.initLinkWidgetEvent.apply(this, arguments);
		var a = c.options.valueDependence;
		var b = this;
		if (FR.isArray(a)) {
			$.each(a,
			function(d, f) {
				var e = b.getWidgetByName(f);
				if (e != null && e != c) {
					if (!e.$linkWidget) {
						e.$linkWidget = {
							data: {},
							value: {}
						};
						e.on(FR.Events.AFTEREDIT,
						function() {
							b.fireWidgetValueChanged(e)
						})
					}
					e.$linkWidget.value[c.options.widgetName] = c
				}
			})
		}
	},
	fireWidgetValueChanged: function(b) {
		if (!b.$linkWidget) {
			return
		}
		for (var f in b.$linkWidget.data) {
			b.$linkWidget.data[f].reset()
		}
		var j = FR.jsonDecode(this.collectionValue());
		var h = {};
		var e = new Array();
		var a = new Array();
		var c = function(m) {
			if (m) {
				for (var k in m.value) {
					var l = m.value[k];
					if (l instanceof FR.ChartWidget) {
						a.push(l)
					} else {
						h[k] = l;
						e.push(k);
						j[k] = "";
						c(l.$linkWidget)
					}
				}
				for (var k in m.data) {
					var l = m.data[k];
					e.push(k);
					j[k] = "";
					c(l.$linkWidget)
				}
			}
		};
		c(b.$linkWidget);
		if (e.length > 0) {
			var g = this;
			var d = FR.buildServletUrl({
				op: "fr_form",
				cmd: "form_getsource",
				sessionID: this.options.form.sessionID,
				__widgetname__: e
			});
			FR.ajax({
				url: d,
				type: "POST",
				async: false,
				data: {
					__parameters__: FR.jsonEncode(j)
				},
				complete: function(m, l) {
					if (l == "success") {
						var n = FR.jsonDecode(m.responseText);
						for (var k in n) {
							if (h[k]) {
								h[k].setSource(n[k])
							}
						}
					}
				}
			})
		}
		if (a.length > 0) {
			$.each(a,
			function(k, l) {
				l.reload()
			})
		}
	},
	loadContentData: function(d) {
		var e = {};
		var c = {
			add: function(f) {
				e[f.options.widgetName] = f
			},
			remove: function(f) {
				delete e[f.options.widgetName]
			}
		};
		d.addWidget2Form(c);
		var a = this;
		var b = FR.buildServletUrl({
			op: "fr_form",
			cmd: "form_getsource",
			sessionID: this.options.form.sessionID,
			__isContent__: true,
			__widgetname__: d.options.widgetName
		});
		FR.ajax({
			url: b,
			type: "POST",
			async: false,
			data: {
				__parameters__: a.collectionValue()
			},
			complete: function(h, g) {
				if (g == "success") {
					var j = FR.jsonDecode(h.responseText);
					for (var f in j) {
						if (e[f]) {
							e[f].setSource(j[f])
						}
					}
				} else {}
			}
		})
	},
	load: function() {
		this.loadContentData(this.content)
	},
	storeWidget: function() {},
	storeFormData: function(a) {
		var d = {};
		$.each(this.datakey_widgets,
		function(e, f) {
			if (f && f.Name == a) {
				d[f.Name + "." + f.Key] = f.widget.getValue()
			}
		});
		var b = FR.buildServletUrl({
			op: "fr_form",
			cmd: "set_source_value",
			tableDataSourceName: a,
			sessionID: this.sessionID
		});
		var c = false;
		FR.ajax({
			url: b,
			type: "POST",
			async: false,
			data: {
				__parameters__: FR.jsonEncode(d)
			},
			complete: function(f, e) {
				if (e == "success") {
					FR.Msg.toast(FR.i18n.Successfully)
				} else {
					FR.Msg.toast(FR.i18n.Failed + ":" + f.responseText)
				}
				if (!f.responseText) {
					return
				}
				c = f.responseText
			}
		});
		return c
	},
	remove: function(b) {
		var a = this;
		if (b.options.Databinding) {
			$.each(a.datakey_widgets,
			function(c, d) {
				if (d && d.Name == b.options.Databinding.Name && d.Key == b.options.Databinding.Key) {
					a.datakey_widgets.splice(c, 1)
				}
			})
		}
		delete this.name_widgets[b.options.widgetName];
		delete this.location_widgets[b.options.widgetName]
	},
	getWidgetsByGroup: function(a) {
		return this.group_widgets[a]
	},
	getValueByName: function(a) {
		if (!this.name_widgets || !a) {
			return null
		}
		var b = this.name_widgets[a.toUpperCase()];
		if (b && b.getValue) {
			return b.getValue()
		} else {
			return null
		}
	},
	getWidgetByName: function(a) {
		if (!this.name_widgets || !a) {
			return null
		}
		if (a.startWith("$")) {
			a = a.substring(1)
		}
		return this.name_widgets[a.toUpperCase()]
	},
	doLayout: function() {
		if (this.content) {
			this.content.setMinSize();
			this.content.doLayout()
		}
	},
	_fireSubmitEvents: function(a) {
		if (a == "success") {
			this.content.fireEvent(FR.Events.SS)
		} else {
			this.content.fireEvent(FR.Events.SF)
		}
		this.content.fireEvent(FR.Events.SC)
	}
});
$.shortcut("fr_contentForm", FR.contentForm);
$.extend(FR, {
	getDependence: function(a) {
		if (a.getDependenceObj) {
			return a.getDependenceObj()
		}
	},
	domFormSubmit: function(c, d, k, l) {
		FR.closeDialog();
		d = $.extend({
			cjkEncode: true
		},
		d);
		var m;
		if (d.context) {
			m = $(c, d.context)
		} else {
			m = $(c)
		}
		if (m.length == 0) {
			return
		}
		l = $.extend({
			scope: this
		},
		l);
		var b = m.serializeArray();
		var e = {};
		for (var f = 0; f < b.length; f++) {
			var h = b[f];
			if (d.cjkEncode === true) {
				e[FR.cjkEncode(h.name)] = FR.cjkEncode(h.value)
			} else {
				e[h.name] = h.value
			}
		}
		$.extend(e, d);
		var g = {
			type: "POST",
			data: e,
			url: d.url || m[0].action,
			complete: function(o, n) {
				$.isFunction(k) && k.call(l.scope, o, n, l)
			}
		};
		var a = $("input:file", m);
		if (a.length > 0) {
			if ($.browser.safari && g.closeKeepAlive) {
				$.get(g.closeKeepAlive, j)
			} else {
				j()
			}
		} else {
			$.ajax(g)
		}
		function j() {
			var t = m[0];
			if ($(":input[name=submit]", t).length) {
				alert('Error: Form elements must not be named "submit".');
				return
			}
			g.success = g.success || FR.emptyFn;
			var q = $.extend({},
			$.ajaxSettings, g);
			var F = jQuery.extend(true, {},
			$.extend(true, {},
			$.ajaxSettings), q);
			var r = "jqFormIO" + (new Date().getTime());
			var A = $('<iframe id="' + r + '" name="' + r + '" />');
			var C = A[0];
			if ($.browser.msie || $.browser.opera) {
				C.src = 'javascript:false;document.write("");'
			}
			A.css({
				position: "absolute",
				top: "-1000px",
				left: "-1000px"
			});
			var E = {
				aborted: 0,
				responseText: null,
				responseXML: null,
				status: 0,
				statusText: "n/a",
				getAllResponseHeaders: function() {},
				getResponseHeader: function() {},
				setRequestHeader: function() {},
				abort: function() {
					this.aborted = 1;
					A.attr("src", "about:blank")
				}
			};
			var B = q.global;
			if (B && !$.active++) {
				$.event.trigger("ajaxStart")
			}
			if (B) {
				$.event.trigger("ajaxSend", [E, q])
			}
			if (F.beforeSend && F.beforeSend(E, F) === false) {
				F.global && jQuery.active--;
				return
			}
			if (E.aborted) {
				return
			}
			var p = 0;
			var x = 0;
			var o = t.clk;
			if (o) {
				var u = o.name;
				if (u && !o.disabled) {
					g.extraData = g.extraData || {};
					g.extraData[u] = o.value;
					if (o.type == "image") {
						g.extraData[name + ".x"] = t.clk_x;
						g.extraData[name + ".y"] = t.clk_y
					}
				}
			}
			setTimeout(function() {
				var H = m.attr("target"),
				s = m.attr("action");
				m.attr({
					target: r,
					method: "POST",
					action: q.url
				});
				if (!g.skipEncodingOverride) {
					m.attr({
						encoding: "multipart/form-data",
						enctype: "multipart/form-data"
					})
				}
				if (q.timeout) {
					setTimeout(function() {
						x = true;
						y()
					},
					q.timeout)
				}
				var G = [];
				try {
					if (g.extraData) {
						for (var I in g.extraData) {
							G.push($('<input type="hidden" name="' + I + '" value="' + g.extraData[I] + '" />').appendTo(t)[0])
						}
					}
					A.appendTo("body");
					C.attachEvent ? C.attachEvent("onload", y) : C.addEventListener("load", y, false);
					t.submit()
				} finally {
					m.attr("action", s);
					H ? m.attr("target", H) : m.removeAttr("target");
					$(G).remove()
				}
			},
			10);
			function y() {
				if (p++) {
					return
				}
				C.detachEvent ? C.detachEvent("onload", y) : C.removeEventListener("load", y, false);
				var s = 0;
				var G = true;
				try {
					if (x) {
						throw "timeout"
					}
					var H, J;
					J = C.contentWindow ? C.contentWindow.document: C.contentDocument ? C.contentDocument: C.document;
					if (J.body == null && !s && $.browser.opera) {
						s = 1;
						p--;
						setTimeout(y, 100);
						return
					}
					E.responseText = J.body ? J.body.innerHTML: null;
					E.responseXML = J.XMLDocument ? J.XMLDocument: J;
					E.getResponseHeader = function(L) {
						var K = {
							"content-type": q.dataType
						};
						return K[L]
					};
					if (q.dataType == "json" || q.dataType == "script") {
						var n = J.getElementsByTagName("textarea")[0];
						E.responseText = n ? n.value: E.responseText
					} else {
						if (q.dataType == "xml" && !E.responseXML && E.responseText != null) {
							E.responseXML = z(E.responseText)
						}
					}
					H = D(E, q.dataType)
				} catch(I) {
					G = false;
					v(q, E, "error", I)
				}
				if (G) {
					q.success(H, "success");
					if (B) {
						$.event.trigger("ajaxSuccess", [E, q])
					}
				}
				if (B) {
					$.event.trigger("ajaxComplete", [E, q])
				}
				if (B && !--$.active) {
					$.event.trigger("ajaxStop")
				}
				if (q.complete) {
					q.complete(E, G ? "success": "error")
				}
				setTimeout(function() {
					A.remove();
					E.responseXML = null
				},
				100)
			}
			function v(G, I, n, H) {
				if (G.error) {
					G.error.call(G.context || window, I, n, H)
				}
				if (G.global) { (G.context ? jQuery(G.context) : jQuery.event).trigger("ajaxError", [I, G, H])
				}
			}
			function D(K, I, H) {
				var G = K.getResponseHeader("content-type") || "",
				n = I === "xml" || !I && G.indexOf("xml") >= 0,
				J = n ? K.responseXML: K.responseText;
				if (n && J.documentElement.nodeName === "parsererror") {
					throw "parsererror"
				}
				if (H && H.dataFilter) {
					J = H.dataFilter(J, I)
				}
				if (typeof J === "string") {
					if (I === "json" || !I && G.indexOf("json") >= 0) {
						if (/^[\],:{}\s]*$/.test(J.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, "@").replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, "]").replace(/(?:^|:|,)(?:\s*\[)+/g, ""))) {
							if (window.JSON && window.JSON.parse) {
								J = window.JSON.parse(J)
							} else {
								J = (new Function("return " + J))()
							}
						} else {
							throw "Invalid JSON: " + J
						}
					} else {
						if (I === "script" || !I && G.indexOf("javascript") >= 0) {
							jQuery.globalEval(J)
						}
					}
				}
				return J
			}
			function z(n, G) {
				if (window.ActiveXObject) {
					G = new ActiveXObject("Microsoft.XMLDOM");
					G.async = "false";
					G.loadXML(n)
				} else {
					G = (new DOMParser()).parseFromString(n, "text/xml")
				}
				return (G && G.documentElement && G.documentElement.tagName != "parsererror") ? G: null
			}
		}
	},
	formSubmitCallback: function(c, a, b) {
		FR.Msg.toast(a == "success" && c.responseText == "true" ? FR.i18n.HJS_Send_Successfully + "!": FR.i18n.HJS_Send_Failed + "!")
	},
	pagesetupSubmitCallback: function(c, a, b) {
		FR.closeDialog();
		var e = b.sessionID;
		var d = _g();
		if (d != null) {
			d.loadContentPane()
		}
	}
});
FR.Panel = FR.extend(FR.Widget, {
	_defaultConfig: function() {
		return $.extend(FR.Panel.superclass._defaultConfig.apply(), {
			baseCls: "fr-panel",
			toolPosition: "right",
			fit: false,
			dosize: false,
			border: true,
			closable: false,
			maximizable: false,
			minimizable: false,
			collapsible: true,
			autoScroll: true,
			closed: false,
			width: 300,
			height: 200
		})
	},
	_init: function() {
		var b = this.options;
		this.headerHeight = b.title ? 27 : (b.miniExpand ? 8 : 0);
		b.height += b.title ? 27 : 0;
		FR.Panel.superclass._init.apply(this, arguments);
		var a = this;
		this.element.addClass(b.baseCls);
		this._addContent();
		if (b.height > this.headerHeight) {
			this._addHeader()
		}
		if (b.buttons) {
			this._addBottom()
		}
		this._addBorder();
		if (b.dosize == true) {
			this.doResize({
				width: b.width,
				height: b.height
			})
		}
		if (b.closed == true) {
			this.doClose()
		}
		this.on("_resize",
		function() {
			if (a.options._resize) {
				a.options._resize()
			}
		})
	},
	_addBorder: function() {
		if (this.options.border == true) {
			this.element.find(">div.fr-panel-header").removeClass("fr-panel-header-noborder");
			this.element.find(">div.fr-panel-body").removeClass("fr-panel-body-noborder")
		} else {
			this.element.find("div.fr-panel-header").addClass("fr-panel-header-noborder");
			this.element.find("div.fr-panel-body").addClass("fr-panel-body-noborder")
		}
	},
	_addContent: function() {
		var a = this.options;
		this.element.wrapInner('<div class="fr-panel-body"></div>');
		var b = this.panelBody = $("div.fr-panel-body", this.element);
		if (a.autoScroll == false) {
			b.css({
				overflow: "hidden"
			})
		}
		this._loadContent()
	},
	_addBottom: function() {
		var b = this;
		var d = this.options;
		var a = (this.uiDialogButtonPane = $("<div/>")).addClass("ui-dialog-buttonpane").css({
			position: "absolute",
			bottom: "30px",
			right: "30px"
		}).appendTo(this.element);
		a.empty().hide();
		var c = d.buttons;
		if (c) {
			$.each(c,
			function() {
				return ! (hasButtons = true)
			});
			if (hasButtons) {
				a.show();
				$.each(c,
				function(e, f) {
					$("<button/>").text(e).click(function() {
						f.apply(b.element[0], arguments)
					}).appendTo(a)
				})
			}
		}
	},
	_loadContent: function(c) {
		var b = this.options;
		var a = this.panelBody;
		if (b.contentUrl && (!b.isLoaded || c == true)) {
			b.isLoaded = false;
			a.html($('<div class="fr-panel-loading"></div>').html("loading..."));
			$.ajax({
				url: b.contentUrl,
				type: "POST",
				complete: function(f, d) {
					var e = f.responseText;
					a.html(e);
					b.isLoaded = true
				}
			})
		} else {
			if (b.contentWidget) {
				a.html(b.contentWidget.element)
			} else {
				if (b.contentHtml) {
					a.html(b.contentHtml)
				} else {
					if (b.contentText) {
						a.text(b.contentText)
					}
				}
			}
		}
	},
	_addHeader: function() {
		var self = this;
		var opts = this.options;
		if (opts.title) {
			var header = $('<div class="fr-panel-header"><div class="fr-panel-title">' + opts.title + "</div></div>").prependTo(this.element);
			if (opts.iconCls) {
				$(".fr-panel-title", this.element).addClass("fr-panel-with-icon");
				$('<div class="fr-panel-icon"></div>').addClass(opts.iconCls).appendTo(header)
			}
			var tool = $('<div class="fr-panel-tool fr-panel-tool-' + opts.toolPosition + '"></div>').appendTo(header);
			if (opts.closable) {
				$('<div class="fr-panel-tool-close"></div>').appendTo(tool).bind("click", onClose)
			}
			if (opts.maximizable) {
				$('<div class="fr-panel-tool-max"></div>').appendTo(tool).bind("click", onMax)
			}
			if (opts.minimizable) {
				$('<div class="fr-panel-tool-min"></div>').appendTo(tool).bind("click", onMin)
			}
			if (opts.collapsible) {
				$('<div class="fr-panel-tool-collapse"></div>').appendTo(tool).bind("click", onToggle)
			}
			if (opts.tools) {
				for (var i = opts.tools.length - 1; i >= 0; i--) {
					var t = $("<div></div>").addClass(opts.tools[i].iconCls).appendTo(tool);
					if (opts.tools[i].handler) {
						t.bind("click", eval(opts.tools[i].handler))
					}
				}
			}
			tool.find("div").hover(function() {
				$(this).addClass("panel-tool-over")
			},
			function() {
				$(this).removeClass("panel-tool-over")
			});
			this.element.find(">div.fr-panel-body").removeClass("fr-panel-body-noheader")
		} else {
			if (opts.miniExpand) {
				var header = $("<div class='parameter-container-collapse'></div>").appendTo(self.element);
				header.css({
					width: "100%",
					height: "8px",
					background: "none repeat scroll 0 0 #EEEEEE"
				});
				var collapseImagWrapper = $("<div/>").css({
					width: "112px",
					height: "8px",
					"margin-left": "auto",
					"margin-right": "auto",
					position: "relative"
				}).appendTo(header);
				var collapseImag = $("<div class='parameter-container-collapseimg-up'/>").appendTo(collapseImagWrapper).click(eval(opts.miniExpand.handler))
			} else {
				this.element.find(">div.fr-panel-body").addClass("fr-panel-body-noheader")
			}
		}
		function onToggle() {
			if ($(this).hasClass("fr-panel-tool-expand")) {
				self.doExpand()
			} else {
				self.doCollapse()
			}
			return false
		}
		function onMin() {
			self.doMinimize();
			return false
		}
		function onMax() {
			if ($(this).hasClass("fr-panel-tool-restore")) {
				self.doRestore()
			} else {
				self.doMaximize()
			}
			return false
		}
		function onClose() {
			self.doClose();
			return false
		}
	},
	setTitle: function(a) {
		this.element.find(">div.fr-panel-header").find(">div.fr-panel-title").html(a)
	},
	doOpen: function() {
		this.element.show();
		this.options.closed = false;
		if ($.isFunction(this.options.onOpen)) {
			this.options.onOpen.call(this)
		}
		if (this.options.maximized == true) {
			this.doMaximize()
		}
		if (this.options.minimized == true) {
			this.doMinimize()
		}
		if (this.options.collapsed == true) {
			this.doCollapse()
		}
	},
	doClose: function() {
		this.element.hide();
		this.options.closed = true;
		if ($.isFunction(this.options.onClose)) {
			this.options.onClose.call(this)
		}
	},
	doCollapse: function() {
		var d = this.options;
		var c = this.element;
		var a = c.find(">div.fr-panel-body");
		var b = c.find(">div.fr-panel-header .fr-panel-tool-collapse");
		if (b.hasClass("fr-panel-tool-expand")) {
			return
		}
		a.stop(true, true);
		b.addClass("fr-panel-tool-expand");
		d.currentHeight = c.height();
		c.css({
			height: "auto"
		});
		a.slideUp("normal",
		function() {
			d.collapsed = true
		})
	},
	doExpand: function() {
		var e = this.options;
		var b = this;
		var d = this.element;
		var a = d.find(">div.fr-panel-body");
		var c = d.find(">div.fr-panel-header .fr-panel-tool-collapse");
		if (!c.hasClass("fr-panel-tool-expand")) {
			return
		}
		a.stop(true, true);
		c.removeClass("fr-panel-tool-expand");
		a.slideDown("normal",
		function() {
			e.collapsed = false;
			d.css({
				height: e.currentHeight
			});
			if (e.isLoaded == false) {
				b.loadData()
			}
		})
	},
	doMove: function(b) {
		var a = this.options;
		if (b) {
			if (b.left != null) {
				a.left = b.left
			}
			if (b.top != null) {
				a.top = b.top
			}
		}
		this.element.css({
			left: a.left,
			top: a.top
		});
		if ($.isFunction(a.onMove)) {
			a.onMove.apply(this, [a.left, a.top])
		}
	},
	doMaximize: function() {
		var b = this.options;
		var a = this.element.find(">div.fr-panel-header .fr-panel-tool-max");
		if (a.hasClass("fr-panel-tool-restore")) {
			return
		}
		a.addClass("fr-panel-tool-restore");
		this.options.original = {
			width: b.width,
			height: b.height,
			left: b.left,
			top: b.top
		};
		b.left = 0;
		b.top = 0;
		b.fit = true;
		this.doResize();
		b.minimized = false;
		b.maximized = true;
		if ($.isFunction(b.onMaximize)) {
			b.onMaximize.call(this)
		}
	},
	doMinimize: function() {
		var a = this.options;
		this.element.hide();
		a.minimized = true;
		a.maximized = false;
		if ($.isFunction(a.onMinimize)) {
			a.onMinimize.call(this)
		}
	},
	doRestore: function() {
		var c = this.options;
		var a = this.element.find(">div.fr-panel-header .fr-panel-tool-max");
		if (!a.hasClass("fr-panel-tool-restore")) {
			return
		}
		this.element.show();
		a.removeClass("fr-panel-tool-restore");
		var b = this.options.original;
		c.width = b.width;
		c.height = b.height;
		c.left = b.left;
		c.top = b.top;
		c.fit = b.fit;
		this.doResize();
		c.minimized = false;
		c.maximized = false;
		if ($.isFunction(c.onRestore)) {
			c.onRestore.call(this)
		}
	},
	doResize: function(e) {
		var a = this.options;
		var b = this.element;
		var d = b.find(">div.fr-panel-header");
		var h = b.find(">div.fr-panel-body");
		var g = b.find("div.ui-dialog-buttonpane");
		if (e) {
			if (e.width) {
				a.width = e.width
			}
			if (e.height) {
				a.height = e.height
			}
			if (e.left != null) {
				a.left = e.left
			}
			if (e.top != null) {
				a.top = e.top
			}
		}
		if (a.fit == true) {
			var c = b.parent();
			a.width = c.width();
			a.height = c.height()
		}
		this.element.css({
			left: a.left,
			top: a.top
		});
		if (a.cls) {
			b.addClass(a.cls)
		}
		if (a.headerCls) {
			d.addClass(a.headerCls)
		}
		if (a.bodyCls) {
			h.addClass(a.bodyCls)
		}
		if (!isNaN(a.width)) {
			if ($.support.boxModel == true) {
				b.width(a.width - (b.outerWidth() - b.width()));
				d.width(b.width() - (d.outerWidth() - d.width()));
				h.width(b.width() - (h.outerWidth() - h.width()));
				g && g.width(b.width() - (g.outerWidth() - g.width()))
			} else {
				b.width(a.width);
				d.width(b.width());
				h.width(b.width());
				g && g.width(b.width())
			}
		} else {
			b.width("auto");
			h.width("auto");
			g && g.width("auto")
		}
		if (!isNaN(a.height)) {
			if ($.boxModel == true) {
				b.height(a.height - (b.outerHeight() - b.height()));
				h.height(b.height() - this.headerHeight - (h.outerHeight() - h.height()))
			} else {
				b.height(a.height);
				h.height(b.height() - this.headerHeight)
			}
		} else {
			b.height("auto");
			h.height("auto")
		}
		if ($.isFunction(a.onResize)) {
			a.onResize.apply(this, [a.width, a.height])
		}
		if (a.contentWidget) {
			var j = h.attr("style");
			var f = parseInt(j.replace(/.*height\:\s*(\d+).*/gi, "$1"));
			a.contentWidget.doResize({
				width: h.width(),
				height: f
			})
		}
		this.fireEvent(FR.Events.RESIZE);
		b.doLayout()
	}
});
$.shortcut("panel", FR.Panel);
FR.Window = FR.extend(FR.Widget, {
	_defaultConfig: function() {
		return $.extend(FR.Window.superclass._defaultConfig.apply(), {
			baseCls: "fr-window",
			title: "Window",
			modal: true,
			closable: true,
			collapsible: false,
			closed: true,
			width: 300,
			height: 200,
			resizeable: true,
			draggable: true
		})
	},
	_init: function() {
		FR.Window.superclass._init.apply(this, arguments);
		var e = this.options;
		var d = this.win;
		if (!$.boxModel) {
			d.doResize({
				width: e.width,
				height: e.height
			})
		}
		var b = this;
		if (this.mask) {
			this.mask.remove()
		}
		if (e.modal == true) {
			this.mask = $('<div class="fr-window-mask"></div>').appendTo("body");
			this.mask.css({
				zIndex: FR.widget.opts.zIndex++,
				width: this._getPageArea().width,
				height: this._getPageArea().height,
				display: "none"
			});
			var b = this;
			$(window).resize(function() {
				b.mask.css({
					width: b._getPageArea().width,
					height: b._getPageArea().height
				})
			})
		}
		this.element.css({
			zIndex: FR.widget.opts.zIndex++
		});
		if (d.options.left == null) {
			var c = d.options.width;
			if (isNaN(c)) {
				c = this.element.outerWidth()
			}
			d.options.left = ($(window).width() - c) / 2 + $(document).scrollLeft()
		}
		if (d.options.top == null) {
			var a = d.options.height;
			if (isNaN(a)) {
				a = this.element.outerHeight()
			}
			d.options.top = ($(window).height() - a) / 2 + $(document).scrollTop()
		}
		d.doMove();
		this.setVisible(!e.closed);
		var f;
		if (this.options.draggalbe) {
			this.element.draggable({
				containment: "parent",
				handle: ">div.fr-panel-header",
				start: function(g) {
					startPosition = {
						x: g.clientX,
						y: g.target.clientY
					}
				},
				stop: function(g) {
					d.options.left += (g.clientX - startPosition.x);
					d.options.top += (g.clientY - startPosition.y)
				}
			})
		}
		if (this.options.resizeable) {
			this.element.resizable({
				start: function(g) {},
				resize: function(k) {
					var g = $(this).width() + 12;
					var j = $(this).height() + 12;
					if ($.browser.msie) {
						g += 12;
						j += 12
					}
					b.doResize({
						width: g,
						height: j
					})
				},
				stop: function(g) {}
			})
		}
	},
	_defaultRoot: function() {
		var b = this.options;
		var a = this;
		this.win = new FR.Panel($.extend(b, {
			fit: false,
			renderEl: $("<div/>").appendTo("body"),
			dosize: true,
			border: false,
			cls: b.baseCls,
			headerCls: "fr-window-header",
			bodyCls: "fr-window-body",
			onClose: function() {
				if (a.mask) {
					a.mask.hide()
				}
			},
			onOpen: function() {
				if (a.mask) {
					a.mask.show()
				}
			},
			onResize: function(d, c) {
				if ($.isFunction(b.onDialogResize)) {
					b.onDialogResize()
				}
			},
			onMove: function(d, c) {}
		}));
		return this.win.element
	},
	_getPageArea: function() {
		if (document.compatMode == "BackCompat") {
			return {
				width: Math.max(document.body.scrollWidth, document.body.clientWidth),
				height: Math.max(document.body.scrollHeight, document.body.clientHeight)
			}
		} else {
			return {
				width: Math.max(document.documentElement.scrollWidth, document.documentElement.clientWidth),
				height: Math.max(document.documentElement.scrollHeight, document.documentElement.clientHeight)
			}
		}
	},
	doResize: function(a) {
		this.win.doResize(a)
	},
	setTitle: function(a) {
		this.win.setTitle(a)
	},
	setContent: function(a) {
		this.win.options[a.type] = a.content;
		this.win._loadContent(true)
	},
	setVisible: function(a) {
		if (a == true && this.win.options.closed == true) {
			this.win.doOpen()
		} else {
			if (a == false && this.win.options.closed == false) {
				this.win.doClose()
			}
		}
	},
	destroy: function() {
		this.element.remove()
	}
});
$.shortcut("window", FR.Window);
FR.Dialog = FR.extend(FR.Window, {});
$.shortcut("dialog", FR.Dialog);
FR.Button = FR.extend(FR.Widget, {
	_defaultConfig: function() {
		return $.extend(FR.Button.superclass._defaultConfig.apply(), {
			baseCls: "fr-btn",
			hotkeys: "",
			icon: "",
			autoSize: false,
			scale: "small",
			iconAlign: "left",
			text: "",
			width: "auto",
			height: "auto",
			borderWidth: 6
		})
	},
	_init: function() {
		FR.Button.superclass._init.apply(this, arguments);
		var e = this.options;
		if (!e.text && e.value) {
			e.text = e.value
		}
		var h = e.baseCls + "-" + e.widgetName;
		var a = (e.icon) ? (e.text ? " fr-btn-text-icon": " fr-btn-icon") : " fr-btn-noicon";
		var c = "width:" + e.width + "px;height:" + e.height + "px";
		var g = "fr-btn-" + e.scale + " fr-btn-icon-" + e.scale + "-" + e.iconAlign;
		var d = ["<table id='", h, "' cellspacing='0' class='fr-btn ", a, "' style='", c, "'>", "<tbody class='", g, "'>", "<tr><td class='fr-btn-tl'><i>&#160;</i></td><td class='fr-btn-tc'></td><td class='fr-btn-tr'><i>&#160;</i></td></tr>", "<tr><td class='fr-btn-ml'><i>&#160;</i></td><td class='fr-btn-mc'><em unselectable='on'><button data-role='none' type='button' class='fr-btn-text'>", e.text, "</button></em></td><td class='fr-btn-mr'><i>&#160;</i></td></tr>", "<tr><td class='fr-btn-bl'><i>&#160;</i></td><td class='fr-btn-bc'></td><td class='fr-btn-br'><i>&#160;</i></td></tr>", "</tbody></table>"];
		this.$table = $(d.join("")).appendTo(this.element);
		this.$btn = $("button", this.$table);
		this.setIcon(e.icon);
		this.setMaxWidth(e.width);
		$("tr:eq(0)", this.$table).height(3);
		$("tr:eq(2)", this.$table).height(3);
		this.centertr = $("tr:eq(1)", this.$table);
		if (e.height > e.borderWidth) {
			this.centertr.height(e.height - e.borderWidth)
		}
		var b = this;
		var f = {
			onMouseOver: function(j) {
				b.$table.addClass(e.baseCls + "-over")
			},
			onMouseOut: function(j) {
				b.$table.removeClass(e.baseCls + "-over");
				b.$table.removeClass(e.baseCls + "-click")
			},
			onMouseDown: function(j) {
				b.$table.addClass(e.baseCls + "-click");
				b.$table.removeClass(e.baseCls + "-over");
				$(document).bind("mouseup", f.onMouseUp)
			},
			onMouseUp: function(j) {
				b.$table.removeClass(e.baseCls + "-click");
				b.$table.addClass(e.baseCls + "-over");
				$(document).unbind("mouseup", f.onMouseUp)
			}
		};
		this.$table.mouseover(f.onMouseOver.createInterceptor(this.isEnabled(), this)).mouseout(f.onMouseOut).mousedown(f.onMouseDown.createInterceptor(this.isEnabled(), this)).mouseup(f.onMouseUp);
		this.$table.keydown(function(j) {
			if (j.keyCode == 13) {
				j.stopPropagation()
			}
		});
		this.$table.click(function(j) {
			if (b.isEnabled()) {
				b.fireEvent(FR.Events.CLICK, j)
			}
		});
		if (e.hotkeys) {
			FR.Keys.reg(function(j) {
				if (($(document).codeToString(j) == e.hotkeys) && b.isEnabled()) {
					b.fireEvent(FR.Events.CLICK, j)
				}
			})
		}
		if ($.isFunction(e.handler)) {
			this.on(FR.Events.CLICK, e.handler.createDelegate(e.scope || this))
		}
	},
	getText: function() {
		return this.$btn.text()
	},
	setText: function(a) {
		if (FR.isEmpty(a)) {
			return
		}
		this.$btn.text(a)
	},
	getValue: function() {
		return this.$btn.text()
	},
	setValue: function(a) {
		if (FR.isEmpty(a)) {
			return
		}
		this.$btn.text(a)
	},
	setIcon: function(b) {
		if (FR.isEmpty(b)) {
			return
		}
		this.$btn.removeClass();
		this.$btn.addClass("fr-btn-text");
		var a = this.$btn;
		if ($.browser.msie && $.browser.version == "6.0") {
			a = $("<span></span>").css({
				"line-height": "16px",
				height: 16,
				width: 16,
				display: "block",
				"float": "left"
			}).prependTo(this.$btn.parent());
			this.$btn.css("padding-left", 2)
		}
		if (b.substring(0, 4) == "css:") {
			a.addClass(b.substring(4))
		} else {
			if (b.substring(0, 6) == "style:") {
				a.css({
					background: b.substring(6)
				})
			} else {
				a.css({
					"background-image": "url(" + b + ")"
				})
			}
		}
	},
	setMaxWidth: function(a) {
		if (!isNaN(a)) {
			var b = this.options;
			if ($.browser.msie) {
				if (($.boxModel ? FR.considerBoxModelWidth(this.$btn) : 0) + this.$btn.width() > (a - b.borderWidth)) {
					this.$btn.width(a - b.borderWidth)
				}
			} else {
				this.$btn.css("max-width", (a - b.borderWidth) + "px")
			}
		}
	},
	doResize: function(a) {
		FR.Button.superclass.doResize.call(this, a);
		this.$table.css({
			width: a.width,
			height: a.height
		});
		this.centertr.height(a.height - this.options.borderWidth);
		this.setMaxWidth(a.width)
	}
});
$.shortcut("button", FR.Button);
FR.IconButton = FR.extend(FR.Widget, {
	_defaultConfig: function() {
		return $.extend(FR.IconButton.superclass._defaultConfig.apply(), {
			tablable: true,
			render: false,
			width: 18,
			height: 18,
			baseClass: "fr-icon-btn"
		})
	},
	_init: function() {
		FR.IconButton.superclass._init.apply(this, arguments);
		var b = this.options;
		if (b.render === true) {
			this.$btn = $("<div/>").appendTo(this.element)
		} else {
			this.$btn = this.element
		}
		this.$btn.addClass(b.baseClass);
		if (b.title) {
			this.$btn.attr("title", b.title)
		}
		if (b.imgsrc) {
			this.$btn.addClass(b.imgsrc)
		}
		var a = this;
		var c = {
			onMouseOver: function(d) {
				if (a.isEnabled()) {
					a.$btn.addClass(a.options.baseClass + "-hover")
				}
			},
			onMouseOut: function(d) {
				a.$btn.removeClass(a.options.baseClass + "-hover");
				a.$btn.removeClass(a.options.baseClass + "-click")
			},
			onMouseDown: function(d) {
				if (a.isEnabled()) {
					a.$btn.removeClass(a.options.baseClass + "-hover");
					a.$btn.addClass(a.options.baseClass + "-click");
					$(document).bind("mouseup", c.onMouseUp)
				}
			},
			onMouseUp: function(d) {
				a.$btn.removeClass(a.options.baseClass + "-click");
				$(document).unbind("mouseup", c.onMouseUp)
			}
		};
		this.$btn.mouseover(c.onMouseOver).mouseout(c.onMouseOut).mousedown(c.onMouseDown).mouseup(c.onMouseUp);
		this.$btn.click(function(d) {
			if (a.isEnabled()) {
				a.fireEvent(FR.Events.CLICK, d)
			}
		});
		if ($.isFunction(b.handler)) {
			this.on(FR.Events.CLICK, b.handler.createDelegate(b.scope || this))
		}
	},
	destroy: function() {
		this.element.empty()
	},
	doResize: function(a) {
		FR.Button.superclass.doResize.call(this, a);
		this.$table.css({
			width: a.width,
			height: a.height
		})
	}
});
$.shortcut("iconbutton", FR.IconButton);
FR.BaseEditor = FR.extend(FR.Widget, {
	isSupportDatabinding: function() {
		return true
	},
	loadData: function() {
		if (this.options.widgetName) {
			var a = this.options.form.formData[this.options.widgetName];
			if (a) {
				this.setValue(a, false)
			}
		}
	},
	store: function() {
		if (this.options.Databinding) {
			var a = this.options.Databinding.Name;
			var b = this.options.Databinding.Key;
			if (a && b) {
				var d = this.getValue();
				var c = this.options.form.formData.setValue(a, b, d)
			}
		}
	},
	getValue: function() {},
	setValue: function(a) {
		if (!this.isValidate(a)) {
			return false
		}
		if ((a || a === 0) && this.options.watermark) {
			FR.$defaultImport("/com/fr/web/core/js/jquery.watermark.js", "js");
			this.editComp.watermark({
				clear: true
			})
		}
		if (arguments[1] != undefined) {
			this._setValue(a, arguments[1])
		} else {
			this._setValue(a)
		}
	},
	_setValue: function(a) {},
	getText: function() {
		return this.getValue()
	},
	setText: function(a) {
		this.setValue(a)
	},
	isValidate: function(b) {
		var a = this.options.allowBlank !== false;
		var d = b != null && b != undefined ? b: this.getValue();
		var c = this.options.regex;
		if (d === "" || FR.isEmptyArray(d)) {
			if (a) {
				return true
			} else {
				this.errorMsg = this.options.errorMsg || FR.i18n.NOT_NULL;
				return false
			}
		}
		if (c) {
			if (typeof c == "string") {
				c = new RegExp(c)
			}
			if (!c.test(d)) {
				this.errorMsg = this.options.errorMsg || FR.i18n.Error_Input_Value;
				return false
			}
		}
		return true
	},
	initData: function() {
		if (this.options.data) {
			return
		}
		if (this.options.widgetUrl) {
			this.options.data = FR.DataFactory.createJSONData(this.options.widgetUrl)
		} else {
			if (this.options.items) {
				this.options.data = FR.DataFactory.createArrayData(this.options.items)
			}
		}
	},
	getDependenceObj: function() {
		var b = this.options;
		var e = {};
		if (b.dependenceMap) {
			var k = this;
			$.each(b.dependenceMap,
			function(m, o) {
				if (k.options.form) {
					var q = k.options.form.resolveVariable(o);
					if (q != "fr_primitive" && q != null) {
						e[m.toUpperCase()] = q
					}
				} else {
					if (k.options.write) {
						o = o.startWith("$") ? o.substring(1) : o;
						var n;
						if (contentPane.curLGP.write) {
							var p = contentPane.curLGP.write.getWidgetByCell(o) || contentPane.curLGP.write.getWidgetByName(o);
							if (p) {
								n = p.getValue()
							}
						}
						if (!n) {
							var l = FR.cellStr2ColumnRow(o);
							if (l) {
								var r = contentPane.curLGP.getTDCell(l);
								if (r) {
									n = FR.getCellValue(r)
								}
							}
						}
						if (n != "fr_primitive" && n != null) {
							e[m.toUpperCase()] = n
						}
					}
				}
			})
		} else {
			if (FR.isArray(b.dependence)) {
				for (var j = 0; j < b.dependence.length; j++) {
					var h = b.dependence[j];
					if (this.options.form) {
						var d = this.options.form.resolveVariable(h);
						if (d != "fr_primitive" && d != null) {
							e[h.toUpperCase()] = d
						}
					} else {
						if (this.options.write) {
							h = h.startWith("$") ? h.substring(1) : h;
							var g;
							if (contentPane.curLGP.write) {
								var f = contentPane.curLGP.write.getWidgetByCell(h) || contentPane.curLGP.write.getWidgetByName(h);
								if (f) {
									g = f.getValue()
								}
							}
							if (!g) {
								var c = FR.cellStr2ColumnRow(h);
								if (c) {
									var a = contentPane.curLGP.getTDCell(c);
									if (a) {
										g = FR.getCellValue(a)
									}
								}
							}
							if (g != "fr_primitive" && g != null) {
								e[h.toUpperCase()] = g
							}
						}
					}
				}
			}
		}
		return e
	},
	createDependencePara4Data: function() {
		return {
			parameter: {
				dependence: this.getDependenceObj()
			}
		}
	}
});
FR.SynchronBaseEditor = FR.extend(FR.BaseEditor, {
	_confirmEvents: function() {
		this.once(FR.Events.DEFAULTINIT,
		function() {
			this.fireEvent(FR.Events.AFTERINIT)
		})
	}
});
FR.FreeButton = FR.extend(FR.BaseEditor, {
	_defaultConfig: function() {
		return $.extend(FR.FreeButton.superclass._defaultConfig.apply(), {
			width: 200,
			height: 100,
			initial: {
				background: ""
			}
		})
	},
	_init: function() {
		FR.FreeButton.superclass._init.apply(this, arguments);
		this.switchOn = false;
		var c = this.options;
		if (c.render === true) {
			this.$img = $("<div style='text-align: center;'/>").appendTo(this.element)
		} else {
			this.$img = this.element.css("text-align", "center")
		}
		this.element.addClass("fr-form-imgboard");
		if (c.icon) {
			icon = $("<img style='vertical-align: middle;margin-right: 3px;'/>");
			if (c.icon.background) {
				var e = c.icon.background.split("(");
				var b = e[1];
				e = b.split(")");
				b = e[0];
				icon.attr("src", b).appendTo(this.$img)
			}
		}
		this.text = $("<span style='text-align: center;'></span>").appendTo(this.$img);
		if (c.fontsize) {
			this.text.css("font-size", c.fontsize)
		}
		if (c.color) {
			this.text.css("color", c.color)
		}
		if (c.decoration) {
			this.text.css("text-decoration", c.decoration)
		}
		if (c.fontfamily) {
			this.text.css("font-family", c.fontfamily)
		}
		if (c.fontweight) {
			this.text.css("font-weight", c.fontweight)
		}
		if (c.fontstyle) {
			this.text.css("font-style", c.fontstyle)
		}
		if (c.text) {
			this.setValue(c.text)
		}
		if (c.width && c.height) {
			this.doResize(c)
		}
		if (c.hotkeys) {
			FR.Keys.reg(function(f) {
				if (($(document).codeToString(f) == c.hotkeys) && a.isEnabled()) {
					a.fireEvent(FR.Events.CLICK, f)
				}
			})
		}
		this.setBackground(c.initial.background);
		var a = this;
		var d = {
			onMouseOver: function(f) {
				if (!a.options.disabled) {
					a.fireOver()
				}
			},
			onMouseOut: function(f) {
				if (!a.options.disabled) {
					a.fireOut()
				}
			},
			onMouseDown: function(f) {
				if (!a.options.disabled) {
					a.fireDown()
				}
				$(document).bind("mouseup", d.onMouseUp)
			},
			onMouseUp: function(f) {
				if (!a.options.disabled) {
					a.fireUp()
				}
				$(document).unbind("mouseup", d.onMouseUp)
			}
		};
		this.$img.mouseover(d.onMouseOver.createInterceptor(this.isEnabled(), this)).mouseout(d.onMouseOut).mousedown(d.onMouseDown.createInterceptor(this.isEnabled(), this)).mouseup(d.onMouseUp);
		if (this._getEvents().click) {
			this.$img.addClass("fr-widget-click")
		} else {
			this.$img.css("cursor", "default")
		}
		this.$img.click(function(f) {
			if (a.isEnabled()) {
				a.fireEvent(FR.Events.CLICK, f)
			}
		});
		if ($.isFunction(c.handler)) {
			this.on(FR.Events.CLICK, c.handler.createDelegate(c.scope || this))
		}
	},
	setValue: function(a) {
		this.text.text(a)
	},
	getValue: function() {
		return this.text.text()
	},
	setEnable: function(a) {
		if (a == true) {
			this.options.disabled = false;
			if (this.options.isToggle && this.switchOn) {
				if (this.options.click) {
					this.element.css("background", this.options.click.background)
				}
			} else {
				this.element.css("background", this.options.initial.background)
			}
			this.element.addClass("ui-state-enabled");
			this.element.removeClass("ui-state-disabled")
		} else {
			if (a == false) {
				this.options.disabled = true;
				this.element.css("background", "none");
				this.element.addClass("ui-state-disabled");
				this.element.removeClass("ui-state-enabled")
			}
		}
	},
	fireOver: function() {
		if (this.options.over) {
			this.setBackground(this.options.over.background)
		}
	},
	fireOut: function() {
		if (this.options.isToggle && this.switchOn) {
			if (this.options.click) {
				this.setBackground(this.options.click.background)
			}
		} else {
			this.setBackground(this.options.initial.background)
		}
	},
	fireDown: function() {
		if (this.options.isToggle && !this.switchOn) {
			var c = this.getWidgetsInSameGroup();
			if (c) {
				for (var a = 0; a < c.length; a++) {
					var b = c[a];
					if (b != this) {
						b.switchDown()
					}
				}
			}
			this.switchOn = true
		}
		if (this.options.click) {
			this.setBackground(this.options.click.background)
		}
	},
	fireUp: function() {
		if (this.options.over) {
			this.setBackground(this.options.over.background)
		}
	},
	switchDown: function() {
		if (this.options.isToggle) {
			this.setBackground(this.options.initial.background);
			this.switchOn = false
		}
	},
	getWidgetsInSameGroup: function() {
		if (this.options.group) {
			return this.options.form.getWidgetsByGroup(this.options.group)
		}
	},
	setBackground: function(a) {
		this.$img.css("background", a)
	},
	doResize: function(a) {
		this.$img.css({
			width: a.width,
			height: a.height
		});
		this.text.css("line-height", a.height + "px")
	}
});
$.shortcut("freebutton", FR.FreeButton);
FR.Table = FR.extend(FR.BaseEditor, {
	_init: function() {
		FR.Table.superclass._init.apply(this, arguments);
		var a = this.options;
		this.element.addClass("fr-form-table");
		this.table = $("<table/>").attr({
			cellspacing: "0",
			cellpadding: "0"
		});
		this.initCellsWidget();
		if (this.options.dataURL) {
			this.generateData(this.options.dataURL)
		} else {
			if (this.options.items) {
				this.popuNewData(this.options.items)
			}
		}
		this.element.append(this.table);
		if (a.border == false || a.border == "false") {
			$(".fr-form-table-td").css("border", "none");
			$(".fr-form-table-tr th").css("border", "none")
		}
		if (a.background) {
			if (FR.isArray(a.background)) {
				$(".fr-form-table-even-tr").css("background-color", a.background[0]);
				$(".fr-form-table-odd-tr").css("background-color", a.background[1])
			} else {
				$(".fr-form-table-even-tr").css("background-color", a.background)
			}
		}
		if (a.value != null) {
			this.setValue(a.value)
		}
	},
	initCellsWidget: function() {
		if (!this.options.editors) {
			return
		}
		this.editors = [];
		for (var c = 0,
		a = this.options.editors.length; c < a; c++) {
			var b = this.options.editors[c].columnIndex;
			this.editors[b] = this.options.editors[c]
		}
	},
	initEvent: function() {
		var a = this;
		this.element.blur(function() {
			a.stopCellEditing()
		})
	},
	initTableHead: function(c) {
		if (!FR.isArray(c)) {
			return
		}
		var e = $("<thead/>").addClass("fr-form-table-thead");
		var d = $("<tr>").addClass("fr-form-table-tr");
		e.append(d);
		for (var b = 0,
		a = c.length; b < a; b++) {
			if (b != a - 1) {
				d.append($("<th/>").text(c[b]))
			} else {
				d.append(($("<th/>").addClass("fr-form-table-last-th")).text(c[b]))
			}
		}
		this.table.append(e)
	},
	setTableHead: function(a) {
		this.table.children("thead").empty();
		this.initTableHead(a)
	},
	getCellValue: function(b, c) {
		var a = this.table.children("tbody").children("tr");
		return $((a[c].children("td"))[b]).getValue()
	},
	getSelectedCellValue: function() {
		return this.getActiveCell().getValue()
	},
	generateRemoteData: function(a) {
		var b = {};
		$.ajax({
			url: a,
			type: "POST",
			async: false,
			complete: function(d, c) {
				try {
					b = FR.jsonDecode(d.responseText)
				} catch(f) {}
			}
		});
		this.popuNewData(b)
	},
	changeDataByUrl: function(a) {
		if (a == this.options.dataURL) {
			return
		}
		this.options.dataURL = a;
		this.generateRemoteData(a)
	},
	popuNewData: function(c) {
		if (c.tablehead) {
			this.setTableHead(c.tablehead)
		}
		this.table.children("tbody").empty();
		var d;
		if (!c.tablehead && FR.isArray(c)) {
			d = c
		}
		if (c.tablecontent) {
			d = c.tablecontent
		}
		for (var b = 0,
		a = d.length; b < a; b++) {
			this.addData(d[b])
		}
	},
	updateDatas: function() {
		var c = [];
		var b = this.table.children("tbody").children("tr");
		for (var e = 0,
		a = b.length; e < a; e++) {
			var g = [];
			var f = $(b[e]).children();
			for (var d = 0,
			h = f.length; d < h; d++) {
				g.push($(f[d]).getValue())
			}
			c.push(g)
		}
		return c
	},
	getValue: function() {
		return this.updateDatas()
	},
	setValue: function(a) {
		this.popuNewData(a)
	},
	addData: function(e) {
		if (!FR.isArray(e)) {
			var b = new Array();
			b.push(e);
			e = b
		}
		var d = $("<tr/>").addClass("fr-form-table-tr");
		for (var c = 0,
		a = e.length; c < a; c++) {
			d.append(this.geneCell(e[c], this.getEditor(c)))
		}
		this.table.append(d);
		this.embellishTable()
	},
	removeData: function(a) {
		$(a).empty()
	},
	removeActiveTR: function() {
		if (!this.activedCell) {
			return
		}
		var a = this.activedCell.parent("tr");
		this.removeData(a)
	},
	getEditor: function(a) {
		if (this.editors) {
			return this.editors[a]
		} else {
			return null
		}
	},
	embellishTable: function() {
		var a = this.table.children("tbody").children("tr");
		var b = this;
		$.each(a,
		function(d) {
			$($(".fr-form-table-td", $(a[d]))[0]).addClass("fr-form-table-first-td");
			if (d == 0 && b.table.children("thead").length == 0) {
				var c = $(".fr-form-table-td", a[d]);
				$.each(c,
				function(e) {
					$(c[e]).addClass("fr-form-table-top-td")
				})
			}
			if (d % 2 == 0) {
				$(a[d]).addClass("fr-form-table-even-tr")
			} else {
				$(a[d]).addClass("fr-form-table-odd-tr")
			}
		})
	},
	stopCellEditing: function() {
		if (this.getActiveCell()) {
			this.getActiveCell().stopCellEditing()
		}
	},
	getTable: function() {
		return this.table
	},
	fillTable: function() {},
	setActiveCell: function(a) {
		if (this.activedCell) {
			this.activedCell.removeClass("fr-form-table-active-td")
		}
		this.activedCell = a;
		this.activedCell.addClass("fr-form-table-active-td")
	},
	getActiveCell: function() {
		return this.activedCell
	},
	geneCell: function(c, a) {
		var b = this;
		var d = $("<td/>").addClass("fr-form-table-td").click(function() {
			b.setActiveCell(d);
			b.stopCellEditing()
		});
		if (a != null && a.height) {
			d.css("height", a.height + 1)
		}
		if (a != null && a.width) {
			d.css("width", a.width + 1)
		}
		$.extend(d, {
			_init: function() {
				this.createValuePane()
			},
			createValuePane: function() {
				if (a != null) {
					var f = $("<div/>");
					this.append(f);
					if (a.type) {
						this.editor = f.asComponent(a);
						if (this.editor.setValue) {
							this.editor.setValue(c)
						}
						if (a.clickShow) {
							var e = this;
							this.unbind("click");
							this.click(function() {
								b.setActiveCell(e);
								b.stopCellEditing();
								e.textPane.hide();
								e.editor.setValue(e.textPane.text());
								e.editor.visible();
								e.editor.$editorComponent.blur(function() {
									e.stopCellEditing()
								});
								e.editor.$editorComponent.focus()
							})
						}
					}
				}
				if (a == null || (a != null && a.clickShow) || (a != null && !a.type)) {
					this.textPane = $("<span/>").text(c);
					if (a && a.width) {
						this.textPane.width(a.width)
					}
					if (a && a.height) {
						this.textPane.height(a.height)
					}
					if (a && a.color) {
						this.textPane.css("color", a.color)
					}
					this.append(this.textPane);
					if (this.editor) {
						this.editor.invisible()
					}
				}
			},
			stopCellEditing: function() {
				if (this.editor && this.textPane) {
					this.textPane.text(this.editor.getValue());
					this.textPane.show();
					this.editor.invisible()
				}
			},
			setValue: function(e) {
				if (this.editor) {
					this.editor.setValue(e)
				}
				if (this.textPane) {
					this.textPane.text(e)
				}
			},
			getValue: function() {
				this.stopCellEditing();
				if (this.editor && this.editor.getValue) {
					return this.editor.getValue()
				}
				if (this.textPane) {
					return this.textPane.text()
				}
			}
		});
		d._init();
		return d
	},
	activeCell: function(a) {},
	isValidate: function(a) {
		return true
	},
	getErrorMsg: function() {
		return this.errorMsg
	},
	isDirty: function() {},
	startEditing: function() {},
	stopEditing: function() {}
});
$.shortcut("table", FR.Table);
FR.DataTable = FR.extend(FR.BaseEditor, {
	_init: function() {
		FR.DataTable.superclass._init.apply(this, arguments);
		var a = this.options;
		this.element.css("overflow", "auto");
		this.table = $("<table/>").attr({
			cellspacing: "0",
			cellpadding: "0"
		});
		this.element.append(this.table);
		this.popuNewData(this.options.value)
	},
	initTableHead: function(f) {
		if (!FR.isArray(f.head)) {
			return
		}
		var e = $("<thead/>").addClass("fr-form-table-thead");
		var d = $("<tr>").addClass("fr-form-table-tr");
		e.append(d);
		for (var b = 0,
		a = f.head.length; b < a; b++) {
			var c = $("<th/>").text(f.head[b]);
			if (f.columnWidth && f.columnWidth[b]) {
				c.css("width", f.columnWidth[b])
			}
			if (b == a - 1) {
				c.addClass("fr-form-table-last-th")
			}
			d.append(c)
		}
		this.table.append(e)
	},
	setTableHead: function(a) {
		this.table.children("thead").empty();
		this.initTableHead(a)
	},
	popuNewData: function(c) {
		this.setTableHead(c);
		this.table.children("tbody").empty();
		if (c.tablecontent) {
			var d = c.tablecontent;
			for (var b = 0,
			a = d.length; b < a; b++) {
				this.addData(d[b], c.rowHeight)
			}
		}
	},
	updateDatas: function() {
		var c = [];
		var b = this.table.children("tbody").children("tr");
		for (var e = 0,
		a = b.length; e < a; e++) {
			var g = [];
			var f = $(b[e]).children();
			for (var d = 0,
			h = f.length; d < h; d++) {
				g.push($(f[d]).text())
			}
			c.push(g)
		}
		return c
	},
	getValue: function() {
		return this.updateDatas()
	},
	setValue: function(a) {
		this.popuNewData(a)
	},
	addData: function(f, c) {
		if (!FR.isArray(f)) {
			var b = new Array();
			b.push(f);
			f = b
		}
		var e = $("<tr/>").addClass("fr-form-table-tr");
		for (var d = 0,
		a = f.length; d < a; d++) {
			var g = $("<td/>").addClass("fr-form-table-td").text(f[d] || "");
			g.css("height", c + 1);
			e.append(g)
		}
		this.table.append(e);
		this.embellishTable()
	},
	embellishTable: function() {
		var a = this.table.children("tbody").children("tr");
		var b = this;
		$.each(a,
		function(d) {
			$($(".fr-form-table-td", $(a[d]))[0]).addClass("fr-form-table-first-td");
			if (d == 0 && b.table.children("thead").length == 0) {
				var c = $(".fr-form-table-td", a[d]);
				$.each(c,
				function(e) {
					$(c[e]).addClass("fr-form-table-top-td")
				})
			}
			if (d % 2 == 0) {
				$(a[d]).addClass("fr-form-table-even-tr")
			} else {
				$(a[d]).addClass("fr-form-table-odd-tr")
			}
		})
	},
	getTable: function() {
		return this.table
	}
});
$.shortcut("datatable", FR.DataTable);
FR.Label = FR.extend(FR.BaseEditor, {
	_defaultConfig: function() {
		return $.extend(FR.Label.superclass._defaultConfig.apply(), {
			cls: "fr-label",
			width: 120,
			height: 24
		})
	},
	_init: function() {
		FR.Label.superclass._init.apply(this, arguments);
		var b = this.options;
		this.labelComp = $("<div/>").addClass(b.cls);
		this.textContainer = $("<table/>");
		this.textContainer.css({
			"table-layout": "fixed",
			width: this.options.width,
			height: this.options.height,
			"-webkit-text-size-adjust": "none"
		}).attr({
			cellPadding: 0,
			cellSpacing: 0
		}).append($("<tr/>").append($("<td/>").append(this.labelComp))).appendTo(this.element);
		var a = this;
		this.labelComp.click(function() {
			if (a.isEnabled()) {
				a.fireEvent(FR.Events.CLICK)
			}
		});
		if (this._getEvents().click) {
			this.labelComp.addClass("fr-widget-click")
		} else {
			this.labelComp.css("cursor", "default")
		}
		if (b.fontsize) {
			this.labelComp.css("font-size", b.fontsize)
		}
		if (b.color) {
			this.labelComp.css("color", b.color)
		}
		if (b.decoration) {
			this.labelComp.css("text-decoration", b.decoration)
		}
		if (b.lineheight) {
			this.labelComp.css("line-height", b.lineheight + "px")
		}
		if (b.textalign) {
			this.labelComp.css("text-align", b.textalign)
		}
		if (b.fontfamily) {
			this.labelComp.css("font-family", b.fontfamily)
		}
		if (b.fontweight) {
			this.labelComp.css("font-weight", b.fontweight)
		}
		if (b.fontstyle) {
			this.labelComp.css("font-style", b.fontstyle)
		}
		if (b.border == true) {
			this.labelComp.css({
				border: "1px solid #AAAAAA"
			})
		}
		if (b.autoline) {
			this.labelComp.css("word-break", "break-all");
			this.labelComp.css("word-wrap", "break-word")
		}
		if (b.width > -1) {
			this.element.css({
				width: b.width
			})
		}
		if (b.height > -1) {
			this.element.css({
				height: b.height
			})
		}
		if (b.text) {
			this.setValue(b.text)
		}
		if (b.title) {
			this.labelComp.attr("title", b.title)
		}
		if (b.noWrap) {
			this.labelComp.css("white-space", "nowrap");
			this.labelComp.css("word-wrap", "normal")
		}
		if (b.value != null) {
			this.setValue(b.value)
		}
	},
	setValue: function(b) {
		var a = this.getValue();
		this.labelComp.text(b);
		this.fireEvent(FR.Events.CHANGE, b, a)
	},
	getValue: function() {
		return this.labelComp.text()
	},
	doResize: function(a) {
		FR.Label.superclass.doResize.call(this, a);
		this.textContainer.css({
			width: a.width,
			height: a.height
		});
		this.labelComp.css({
			width: a.width
		});
		if (!this.options.verticalcenter) {
			this.labelComp.css("height", a.height + "px")
		}
	}
});
$.shortcut("label", FR.Label);
FR.WriteEditor = FR.extend(FR.BaseEditor, {
	_init: function() {
		FR.WriteEditor.superclass._init.apply(this, arguments);
		this.usedAsEditor = true
	},
	cssFrom: function(c) {
		if (!this.editComp) {
			return
		}
		var a = $(c);
		var b = this.editComp;
		b.addClass(a.attr("class"));
		$.each(["fontFamily", "fontVariant", "fontStyle", "fontWeight", "fontSize", "paddingTop", "paddingBottom", "paddingLeft", "paddingRight", "backgroundColor"],
		function(d, e) {
			b.css(e, a.css(e))
		});
		if ((/transparent|rgba\(0, 0, 0, 0\)/).test(b.css("backgroundColor"))) {
			b.css("backgroundColor", "white")
		}
	},
	isDirty: function() {
		return this.isWriteDataChanged()
	},
	isWriteDataChanged: function() {
		return this.options.oldValue == null || !FR.equals(this.options.oldValue, this.getValue())
	},
	setValue4Write: function(a) {
		this.options.oldValue = a;
		return this.setValue(a)
	},
	startEditing: function() {},
	stopEditing: function() {},
	reset: function() {
		this.setValue("")
	}
});
$.shortcut("writeEditor", FR.WriteEditor);
EditComp = FR.extend(FR.WriteEditor, {
	_defaultConfig: function() {
		return $.extend(EditComp.superclass._defaultConfig.apply(), {
			cls: "fr-texteditor",
			widgetName: "",
			width: 120,
			height: 22,
			disabled: false,
			autoVerify: false
		})
	},
	_init: function() {
		EditComp.superclass._init.apply(this, arguments);
		var a = this;
		var b = this.options;
		if (b.passwordText === true) {
			this.editComp = $('<input type="password"/>').appendTo(this.element)
		} else {
			var c = $("<textarea style='overflow-y:auto'></textarea>");
			this.editComp = (b.multiline === true ? c: $("<input data-role='none' type='text'/>")).appendTo(this.element);
			if (!this.options.value) {
				this.editComp.val("")
			}
		}
		this.editComp.removeClass("fr-invalid");
		this.editComp.removeAttr("title");
		this.editComp.attr("name", b.widgetName).addClass(b.cls);
		if (b.text) {
			this.editComp.attr("value", b.text)
		}
		if (b.id) {
			this.editComp.attr("id", b.id)
		}
		if (b.disabled == true) {
			this.editComp.attr("disabled", "disabled")
		}
		b.width = (b.width > -1) ? b.width: 120;
		b.height = (b.height > -1) ? b.height: 22;
		this._setElementSize(b);
		if (b.watermark) {
			FR.$defaultImport("/com/fr/web/core/js/jquery.watermark.js", "js");
			this.editComp.watermark(b.watermark)
		}
		FR.applyStyles(this.editComp, b.style);
		this.editComp.focus(function() {
			if (!a.options.write) {
				a.fireEvent(FR.Events.BEFOREEDIT)
			}
			a.editComp.addClass("fr-texteditor-focus")
		}).blur(function(d) {
			a.editComp.removeClass("fr-texteditor-focus");
			if (!a.isValidate()) {
				if (a.setValidate()) {
					a.validateCss()
				} else {
					a.invalidateCss()
				}
			} else {
				a.validateCss()
			}
			if (!a.options.write) {
				a.fireEvent(FR.Events.STOPEDIT)
			}
		}).keyup(function(d) {
			if (b.autoVerify) {
				if (!a.isValidate()) {
					a.invalidateCss()
				} else {
					a.validateCss()
				}
			}
			if (a.isValidateInput(d)) {
				a.fireEvent(FR.Events.AFTEREDIT, d)
			}
		});
		this.errorMsg = this.options.errorMsg || "";
		if (b.value != null) {
			this.setValue(b.value)
		}
	},
	_setElementSize: function(a) {
		this.element.css({
			width: a.width,
			height: a.height
		});
		this.editComp.css("width", $.boxModel ? a.width - 8 : a.width);
		this.editComp.css("height", $.boxModel ? a.height - 4 : a.height);
		if ($.browser.msie && $.browser.version < "9.0") {
			this.editComp.css("line-height", (($.boxModel ? a.height - 4 : a.height - 1)) + "px")
		}
	},
	validateCss: function() {
		this.editComp.removeClass("fr-invalid");
		this.editComp.removeAttr("title")
	},
	invalidateCss: function() {
		this.editComp.addClass("fr-invalid");
		this.editComp.attr("title", this.getErrorMsg())
	},
	doResize: function(a) {
		EditComp.superclass.doResize.call(this, a);
		this._setElementSize(a);
		if (this.options.watermark) {
			FR.$defaultImport("/com/fr/web/core/js/jquery.watermark.js", "js");
			this.editComp.watermark({
				remove: true
			});
			this.editComp.watermark(this.options.watermark)
		}
	},
	getErrorMsg: function() {
		return this.errorMsg
	},
	setEnable: function(a) {
		EditComp.superclass.setEnable.apply(this, arguments);
		if (this.editComp) {
			if (a == true) {
				this.editComp.removeAttr("disabled")
			} else {
				if (a == false) {
					this.editComp.attr("disabled", "disabled")
				}
			}
		}
	},
	selectText: function(f, a) {
		if (!this.editComp.is(":visible")) {
			return
		}
		var c = this.editComp.val();
		if (c && c.length > 0) {
			f = f === undefined ? 0 : f;
			a = a === undefined ? c.length: a;
			var e = this.editComp[0];
			if (e.setSelectionRange) {
				e.setSelectionRange(f, a)
			} else {
				if (e.createTextRange) {
					var b = e.createTextRange();
					b.moveStart("character", f);
					b.moveEnd("character", a - c.length);
					b.select()
				}
			}
		}
	}
});
FR.TextEditor = FR.extend(EditComp, {
	_defaultConfig: function() {
		return $.extend(FR.TextEditor.superclass._defaultConfig.apply(), {
			multiline: false,
			passwordText: false,
			autoVerify: true
		})
	},
	_init: function() {
		FR.TextEditor.superclass._init.apply(this, arguments);
		var b = this.options;
		var a = this;
		if (a.options.multiline == true) {
			this.editComp.keydown(function(f) {
				if (a.options.write && $.ui.keyCode.UP == f.keyCode || $.ui.keyCode.DOWN == f.keyCode) {
					f.stopPropagation();
					return
				}
				if (f.keyCode == 13) {
					if (a.options.form) {
						a.fireEvent(FR.Events.AFTEREDIT);
						f.stopPropagation()
					}
				}
				var d = this;
				if (f.ctrlKey && f.keyCode == 13) {
					f.ctrlKey = false;
					d.value += "\n";
					if (this.createTextRange) {
						var g = d.value.length;
						var c = d.createTextRange();
						c.moveStart("character", g);
						c.collapse(true);
						c.select();
						d.focus()
					}
					a.fireEvent(FR.Events.AFTEREDIT)
				}
			})
		}
	},
	isValidateInput: function(a) {
		return ! FR.isSpecialKey(a) || (this.options.multiline === true && a.keyCode == 13 && this.options.form)
	},
	_setValue: function(b) {
		var a = this.getValue();
		this.editComp.val(b);
		if (arguments[1] !== false) {
			this.fireEvent(FR.Events.CHANGE, b, a)
		}
	},
	getValue: function() {
		var a = this.editComp.val();
		if (this.options.passwordText) {
			$.extend(String.prototype, {
				encryption: true
			})
		}
		return a
	},
	setValidate: function(a) {
		return false
	}
});
$.shortcut("text", FR.TextEditor);
FR.NumberEditor = FR.extend(EditComp, {
	_defaultConfig: function() {
		return $.extend(FR.NumberEditor.superclass._defaultConfig.apply(), {
			allowDecimals: true,
			allowNegative: true,
			decimalPrecision: -1,
			autoVerify: true,
			decimalSeparator: "."
		})
	},
	_init: function() {
		FR.NumberEditor.superclass._init.apply(this, arguments);
		var c = this.options;
		var b = "0123456789";
		this.allowed = b + "";
		if (c.allowDecimals) {
			this.allowed += "."
		}
		if (c.allowNegative) {
			this.allowed += "-"
		}
		this.editComp.css("ime-mode", "disabled");
		var a = this;
		this.editComp.blur(function() {
			if ("." == a.editComp.val().charAt(0)) {
				a.editComp.val("0" + a.editComp.val())
			}
		}).keydown(function(d) {
			if (d.keyCode == $.ui.keyCode.ENTER && !a.options.write) {
				d.stopPropagation()
			}
		}).keypress(function(f) {
			var d = f.keyCode;
			if (!$.browser.msie && (FR.isNavKeyPress(f) || FR.isSpecialKey(f) || d == $.ui.keyCode.BACKSPACE)) {
				return
			}
			var h = f.charCode || f.keyCode;
			var g = String.fromCharCode(h);
			if ($.browser.msie && (h == 27 || h == 13)) {
				return
			}
			if (a.allowed.indexOf(g) === -1) {
				f.preventDefault()
			}
			if (this.value.indexOf(".") > -1 && g == ".") {
				f.preventDefault()
			}
		})
	},
	isValidateInput: function(a) {
		var d = a.charCode || a.keyCode;
		if (d == $.ui.keyCode.BACKSPACE || d == $.ui.keyCode.ENTER) {
			return true
		}
		var b = String.fromCharCode(d);
		if (d == 190) {
			b = "."
		}
		return (this.allowed.indexOf(b) > -1) && (b != "." || (this.editComp.val().substr(0, this.editComp.val().length - 1)).indexOf(".") === -1)
	},
	_setValue: function(b) {
		var a = this.getValue();
		b = parseFloat(b);
		this.editComp.val(isNaN(b) ? "": String(b).replace(".", this.options.decimalSeparator));
		var a = this.options.value;
		this.options.value = b;
		if (arguments[1] !== false) {
			this.fireEvent(FR.Events.CHANGE, b, a)
		}
	},
	_fixPrecision: function(b) {
		var a = isNaN(b);
		if (!this.options.allowDecimals || this.options.decimalPrecision == -1 || a || !b) {
			return a ? "": b
		}
		return parseFloat(parseFloat(b).toFixed(this.options.decimalPrecision))
	},
	_parseValue: function(a) {
		a = parseFloat(String(a).replace(this.options.decimalSeparator, "."));
		return isNaN(a) ? "": a
	},
	getValue: function() {
		var a = this._fixPrecision(this._parseValue(this.editComp.val()));
		return a
	},
	verifyDedimals: function() {
		if (typeof this.editComp.reg == "undefined") {
			this.editComp.reg = (this.options.allowNegative ? "-?": "") + "\\d+" + (this.options.allowDecimals ? ("(\\.\\d{0," + this.options.maxDecLength + "})?") : "")
		}
		var b = new RegExp(this.editComp.reg);
		var a = b.exec(this.editComp.val());
		if (a != null) {
			return a[0]
		}
		return ""
	},
	recoveryValue: function() {
		this.editComp.val(isNaN(this.options.value) ? "": String(this.options.value).replace(".", this.options.decimalSeparator))
	},
	isValidate: function(c) {
		var d = c != null && c != undefined ? c: this.editComp.val();
		if (d == null || d == undefined || d === "") {
			if (this.options.allowBlank != false) {
				return true
			} else {
				this.errorMsg = this.options.errorMsg || FR.i18n.NOT_NULL;
				return false
			}
		}
		d = ("" + d).replace(this.options.decimalSeparator, ".");
		if (isNaN(d)) {
			this.errorMsg = this.options.errorMsg || FR.i18n.Value_Must_Be_A_Number;
			return false
		}
		var b = this._parseValue(d);
		var a = "" + b;
		if (a.indexOf(this.options.decimalSeparator) > 0) {
			if (!this.options.allowDecimals) {
				this.errorMsg = this.options.errorMsg || FR.i18n.Value_Cannot_Be_Decimal;
				return false
			} else {
				if (this.options.allowDecimals && (a.length - a.indexOf(this.options.decimalSeparator) > this.options.maxDecLength + 1)) {
					this.errorMsg = this.options.errorMsg || FR.i18n.Value_DecimalNumber_Out;
					return false
				}
			}
		}
		if (!this.options.allowNegative && b < 0) {
			this.errorMsg = this.options.errorMsg || FR.i18n.Value_Cannot_Be_Negative;
			return false
		}
		if (this.options.minValue != null && b < this.options.minValue) {
			this.errorMsg = this.options.errorMsg || FR.i18n.Value_Is_Less_Than_Minimum + this.options.minValue;
			return false
		}
		if (this.options.maxValue != null && b > this.options.maxValue) {
			this.errorMsg = this.options.errorMsg || FR.i18n.Value_Is_Larger_Than_Maximum + this.options.maxValue;
			return false
		}
		return true
	},
	isDirty: function() {
		if (this.getValue() == 0) {
			return true
		}
		return FR.NumberEditor.superclass.isDirty.apply(this, arguments)
	},
	setValidate: function(a) {
		var b = a ? a: this.editComp.val();
		b = String(b).replace(this.options.decimalSeparator, ".");
		if ("." == b.charAt(0)) {
			b = "0" + b
		}
		if ("" == b || (b.charAt(0) != "-" && !this.isValidate(b.charAt(0)))) {
			return false
		}
		for (i = 2; i <= b.length; i++) {
			if (!this.isValidate(b.substring(0, i))) {
				if (i == 2 && b.charAt(0) == "-") {
					return false
				}
				this.editComp.val(b.substring(0, i - 1));
				return true
			}
		}
	},
	doResize: function(a) {
		FR.NumberEditor.superclass.doResize.call(this, a)
	}
});
$.shortcut("number", FR.NumberEditor);
FR.TriggerEditor = FR.extend(FR.WriteEditor, {
	_defaultConfig: function() {
		return $.extend(FR.TriggerEditor.superclass._defaultConfig.apply(), {
			baseCls: "fr-trigger-editor",
			btn_up: "fr-trigger-btn-up",
			btn_down: "fr-trigger-btn-down",
			width: 100,
			height: 22
		})
	},
	_init: function() {
		FR.TriggerEditor.superclass._init.apply(this, arguments);
		this.initData();
		this.addContent2Element()
	},
	addContent2Element: function() {
		var c = this.options;
		this.element.addClass(c.baseCls);
		this.triggerTextComp = $("<div class='fr-trigger-text'></div>").appendTo(this.element);
		this.editComp = $("<input type='text'/>").addClass("fr-texteditor").appendTo(this.triggerTextComp);
		var a = this;
		this.editComp.blur(function() {
			a.editComp.removeClass("fr-texteditor-focus");
			if (a.options.allowBlank == false && a.editComp.val() == "") {
				a.editComp.removeClass("fr-combo-notinlist");
				a.editComp.addClass("fr-invalid");
				a.editComp.attr("title", FR.i18n.NOT_NULL)
			} else {
				if (a.inList === false && a.options.directEdit !== true) {
					a.editComp.removeClass("fr-invalid");
					a.editComp.addClass("fr-combo-notinlist");
					a.editComp.attr("title", FR.i18n.Not_In_List);
					a.setValue(a.originalValue);
					a.searchText = a.editComp.val();
					a.options.need2BuildList = true
				} else {
					a.editComp.removeClass("fr-combo-notinlist");
					a.editComp.removeClass("fr-invalid");
					a.editComp.removeAttr("title")
				}
			}
			a.originalValue = undefined;
			if (!a.options.write) {
				a.fireEvent(FR.Events.STOPEDIT)
			}
		}).focus(function() {
			a.editComp.addClass("fr-texteditor-focus");
			if (a.originalValue == undefined) {
				a.originalValue = a.getValue()
			}
			if (!a.options.write) {
				a.fireEvent(FR.Events.BEFOREEDIT)
			}
		}).click(function() {
			a.fireEvent(FR.Events.CLICK)
		});
		var b = this.onTriggerClick.createDelegate(this);
		this.btn = $("<div class='fr-trigger-btn-up'></div>").appendTo(this.element);
		this.arrow = $("<div/>").addClass("fr-trigger-center");
		var e = {
			onMouSeenter: function(f) {
				a.btn.switchClass("fr-trigger-btn-over")
			},
			onMouseLeave: function(f) {
				a.btn.switchClass("fr-trigger-btn-up")
			},
			onMouseDown: function(f) {
				a.btn.switchClass("fr-trigger-btn-click")
			},
			onMouseUp: function(f) {
				a.btn.switchClass("fr-trigger-btn-over");
				b()
			}
		};
		this.btn.mouseenter(e.onMouSeenter).mouseleave(e.onMouseLeave).mouseup(e.onMouseUp).mousedown(e.onMouseDown);
		if (c.directEdit !== true && this.xtype != "combo") {
			this.editComp.attr("readonly", true)
		}
		if (c.disabled == true) {
			this.editComp.attr("disabled", "disabled")
		}
		c.width = (c.width > -1) ? c.width: this.triggerTextComp.width();
		c.height = (c.height > -1) ? c.height: this.triggerTextComp.height();
		this.btn.height(c.height);
		this.generateTrigger();
		var d = $.boxModel ? this.btn.outerWidth() : this.btn.width();
		this.btnWidth = d ? d: 18;
		this._setElementSize(c);
		if (c.watermark) {
			FR.$defaultImport("/com/fr/web/core/js/jquery.watermark.js", "js");
			this.editComp.watermark(c.watermark)
		}
		if (c.value || c.value === 0) {
			this._setValue(c.value, false)
		}
		this.editComp.__bind__("keydown", this, this._onKeyDown);
		this.initViewContainer()
	},
	_setElementSize: function(a) {
		this.triggerTextComp.css({
			width: a.width - this.btnWidth,
			height: a.height
		});
		this.editComp.css({
			width: $.boxModel ? a.width - this.btnWidth - 8 : a.width - this.btnWidth
		});
		this.editComp.css({
			height: $.boxModel ? a.height - 4 : a.height
		});
		if ($.browser.msie && $.browser.version < "9.0") {
			this.editComp.css("line-height", ($.boxModel ? a.height - 4 : a.height - 1) + "px")
		}
		this.btn.css({
			height: a.height
		});
		this.centertr.height(a.height - 2)
	},
	initViewContainer: function() {
		if (!FR.$view_container) {
			FR.$view_container = $("<div class='view-container'/>").appendTo("body")
		}
	},
	generateTrigger: function() {
		var a = $("<table/>").attr({
			border: "0",
			cellPadding: "0",
			cellSpacing: "0"
		}).css({
			width: "100%",
			height: "100%"
		}).appendTo(this.btn);
		var e = $("<tr/>").height(1);
		e.append($("<td/>").addClass("fr-trigger-top-center"));
		e.append($("<td/>").addClass("fr-trigger-top-right"));
		e.appendTo(a);
		var c = this.btn.innerHeight();
		var b = this.centertr = $("<tr/>");
		if (c - 2 > 0) {
			b.height(c - 2)
		}
		b.append($("<td/>").addClass("fr-trigger-middle-center").append(this.arrow));
		b.append($("<td/>").addClass("fr-trigger-middle-right"));
		b.appendTo(a);
		var d = $("<tr/>").height(1);
		d.append($("<td/>").addClass("fr-trigger-bottom-center"));
		d.append($("<td/>").addClass("fr-trigger-bottom-right"));
		d.appendTo(a)
	},
	setEnable: function(a) {
		FR.TriggerEditor.superclass.setEnable.apply(this, arguments);
		if (this.editComp) {
			if (a == true) {
				this.editComp.removeAttr("disabled")
			} else {
				if (a == false) {
					this.editComp.attr("disabled", "disabled")
				}
			}
		}
	},
	onTriggerClick: function() {},
	setIcon: function(a) {
		this.btn.attr("class", a)
	},
	doResize: function(a) {
		FR.TriggerEditor.superclass.doResize.apply(this, arguments);
		this._setElementSize(a);
		if (this.options.watermark) {
			FR.$defaultImport("/com/fr/web/core/js/jquery.watermark.js", "js");
			this.editComp.watermark({
				remove: true
			});
			this.editComp.watermark(this.options.watermark)
		}
	},
	startEditing: function() {
		this.triggerTextComp.show();
		this.editComp.focus();
		this.editComp.select()
	},
	stopEditing: function() {
		this.editComp.blur();
		this.triggerTextComp.hide();
		FR.TriggerEditor.superclass.stopEditing.apply(this, arguments)
	}
});
FR.BaseDateTimeEditor = FR.extend(FR.TriggerEditor, {
	_defaultConfig: function() {
		return $.extend(FR.BaseDateTimeEditor.superclass._defaultConfig.apply(), {})
	},
	_init: function() {
		FR.BaseDateTimeEditor.superclass._init.apply(this, arguments);
		if (this.options.value) {
			this._setValue(this.options.value, false)
		}
	},
	_setValue: function(b) {
		if (FR.isArray(b)) {
			b = b.toString()
		}
		var d = b || "";
		var c = this.options.format;
		if (d.date_milliseconds) {
			d = new Date(d.date_milliseconds)
		} else {
			if (typeof b == "string") {
				b = b.replace(/-/g, "/")
			}
			d = new Date(b)
		}
		if (d == "Invalid Date" || d == "NaN") {
			d = ""
		} else {
			if (d instanceof Date) {
				d = "" + d.format(FR.convertJavaDateFormat2JS(c));
				this.options.value = d
			} else {
				if (!Date.parseDate(String(d), c)) {
					d = ""
				}
			}
		}
		this.setText(d);
		this.options.currentDateTime = d;
		var a = this.options.value;
		this.options.value = b;
		if (arguments[1] !== false) {
			this.fireEvent(FR.Events.CHANGE, b, a)
		}
	},
	setText: function(a) {
		this.editComp.val(a)
	},
	isValidate: function(c) {
		var b = this.options.allowBlank != false;
		var g = this.options.format;
		var a = this.std;
		var f = this.edd;
		var e = c ? c: this.getValue();
		if (!c && !e && this.editComp.val()) {
			this.errorMsg = FR.i18n.Value_Not_Match;
			return false
		}
		var d = this.options.regex;
		if (d) {
			if (typeof d == "string") {
				d = new RegExp(d)
			}
			if (!d.test(e)) {
				this.errorMsg = this.options.errorMsg || FR.i18n.Error_Input_Value;
				return false
			}
		}
		if ((!e) || ($.isArray(e) && e.length == 0)) {
			if (b) {
				return true
			} else {
				this.errorMsg = this.options.errorMsg || FR.i18n.NOT_NULL;
				return false
			}
		}
		if (a && e < a) {
			this.errorMsg = this.options.errorMsg || FR.i18n.Value_Is_Less_Than_Minimum + a.format(FR.convertJavaDateFormat2JS(g));
			return false
		}
		if (f && e > f) {
			this.errorMsg = this.options.errorMsg || FR.i18n.Value_Is_Larger_Than_Maximum + f.format(FR.convertJavaDateFormat2JS(g));
			return false
		}
		return true
	}
});
FR.BaseComboBoxEditor = FR.extend(FR.TriggerEditor, {
	_defaultConfig: function() {
		return $.extend(FR.BaseComboBoxEditor.superclass._defaultConfig.apply(), {
			name4Empty: FR.i18n.Choose_None,
			searchTime: 500
		})
	},
	_init: function() {
		FR.BaseComboBoxEditor.superclass._init.apply(this, arguments)
	},
	initData: function() {
		FR.BaseComboBoxEditor.superclass.initData.apply(this, arguments);
		if (this.options.controlAttr) {
			this.setSource(this.options.controlAttr);
			delete this.options.controlAttr
		}
	},
	setSource: function(b) {
		if (b.data) {
			var a = {
				dependence: this.getDependenceObj(),
				startIndex: 0,
				limitIndex: this.options.limitData
			};
			this.options.data.setData(a, b.data)
		}
		if (b.value) {
			if (this.editComp) {
				this.setValue(b.value, false)
			}
		}
		this.shouldReBuildList()
	},
	allPara: function(c, b) {
		var a = this.createDependencePara4Data();
		if (this.searchText || this.searchText == 0) {
			a.parameter.filter = this.searchText
		}
		a.parameter.startIndex = c ? c: 0;
		a.parameter.limitIndex = b ? b: this.options.limitData;
		return a
	},
	_getViewList: function() {},
	getText: function() {
		return this.editComp.val()
	},
	getItemsLength: function() {
		return this.options.data.getLength()
	},
	_onEnterPressed: function() {
		this._selectWidthoutTriggerEvent();
		this.fireEvent(FR.Events.AFTEREDIT, this.selectedIndex < this.getItemsLength() ? this._getSelectedItem().text() : "")
	},
	_selectWidthoutTriggerEvent: function() {
		if ((this.getItemsLength() > 0) && (this.selectedIndex < this.getItemsLength())) {
			this.setText(this._getSelectedItem().text())
		} else {
			this.clearText()
		}
		this.collapse()
	},
	_setValue: function(e) {
		var c = this.getValue();
		var b = this.options.data.getRecords(),
		f = e;
		for (var d = 0,
		a = b.length; d < a; d++) {
			if (b[d].getValue() == e) {
				f = b[d].getShowValue();
				break
			}
		}
		this.setText(f);
		if (arguments[1] !== false) {
			this.fireEvent(FR.Events.CHANGE, e, c)
		}
	},
	setText: function(a) {
		this.editComp.val(a)
	},
	clearText: function() {
		this.editComp.val("")
	},
	_getSelectedItem: function() {
		return this.selectedItem
	},
	_getSelectedIndex: function() {
		return this.selectedIndex != null ? this.selectedIndex: -1
	},
	shouldReBuildList: function() {
		this.options.need2BuildList = true
	},
	considerAllowBlankLength: function() {
		return this.getItemsLength() + (this.options.allowBlank === false ? 0 : 1)
	},
	getValue: function() {
		var f = this.getText();
		var e = f,
		b = this.options.data.getLoadedRecords(),
		c = this._getSelectedIndex();
		if (c > -1 && c < b.length && b[c].getShowValue() == f) {
			return b[c].getValue()
		} else {
			for (var d = 0,
			a = b.length; d < a; d++) {
				if (b[d].getShowValue() == f) {
					e = b[d].getValue();
					if (this.$view) {
						this._setSelectedIndex(d)
					}
					break
				}
			}
			return e
		}
	},
	reset: function() {
		FR.BaseComboBoxEditor.superclass.reset.apply(this, arguments);
		this.shouldReBuildList();
		delete this.searchText;
		delete this.emptyContent;
		if (this.options.linkAutoChoose) {
			this.onTriggerClick();
			this._setSelectedIndex(0);
			this._selectWidthoutTriggerEvent()
		}
	}
});
$.extend(FR, {
	createTreeConfig4Widget: function(a) {
		FR.$defaultImport("/com/fr/web/core/js/jquerytree/tree.js", "js");
		FR.$defaultImport("/com/fr/web/core/js/jquerytree/common.js", "js");
		if (a.options.widgetCss && a.options.widgetCss.length != 0) {
			$.each(a.options.widgetCss,
			function(c, d) {
				FR.$import(d, "css", true)
			})
		}
		var b = {
			data: a.options.data,
			showcheck: a.options.mutiSelection,
			onnodeclick: function(d) {
				var c = a.options.disableID;
				if (c) {
					if (c == d.getID()) {
						return
					}
				}
				a._onEnterPressed(d)
			},
			beforeSelectItemChange: function() {
				return a.fireEvent(FR.Events.BEFORESTATECHANGE)
			},
			widget: a,
			f2scascade: true,
			s2fcascade: true,
			afterBuild: function() {
				return a.fireEvent(FR.Events.AFTERBUILD)
			}
		};
		return b
	}
});
FR.TreeEditor = FR.extend(FR.SynchronBaseEditor, {
	_defaultConfig: function() {
		return $.extend(FR.TreeEditor.superclass._defaultConfig.apply(), {
			width: 120,
			height: 200,
			limitData: 300,
			delimiter: ";"
		})
	},
	_init: function() {
		FR.TreeEditor.superclass._init.apply(this, arguments);
		if (this.options.rootLoader) {
			this.options.data = new FR.TreeData({
				treeLoader: this.options.rootLoader
			})
		} else {
			this.options.data = new FR.TreeData({
				url: this.options.widgetUrl
			})
		}
		if (this.options.widgetName != null) {
			this.element.attr("id", this.options.widgetName)
		}
		if (this.options.width > -1) {
			this.element.css("width", this.options.width)
		}
		if (this.options.height > -1) {
			this.element.css("height", this.options.height)
		}
		this.element.css("overflow", "auto");
		this.options.data.resetStatus(this.allPara());
		var a = FR.createTreeConfig4Widget(this);
		this.element.treeview(a)
	},
	allPara: function(c, b) {
		var a = this.createDependencePara4Data();
		a.parameter.startIndex = c ? c: 0;
		a.parameter.limitIndex = b ? b: this.options.limitData;
		return a
	},
	_onEnterPressed: function(a) {
		this.fireEvent(FR.Events.CLICK, a);
		this.fireEvent(FR.Events.AFTEREDIT)
	},
	getValue: function() {
		if (!this.options.mutiSelection) {
			return this.element.getTCPValue()
		} else {
			return this.element.getTCPValues()
		}
	},
	setValue: function(g) {
		var c = this;
		if (typeof g === "string") {
			var f = g.split(this.options.delimiter)
		} else {
			var f = $.makeArray(g)
		}
		if (!FR.isEmptyArray(f)) {
			var b = this.options.data.getData();
			var e = [];
			for (var d = 0; d < b.length; d++) {
				e = e.concat(b[d].getLeafNode())
			}
			var a = [];
			$.each(e,
			function(h, j) {
				if ($.inArray(j.getPathAsValueString(), f) > -1) {
					j.setcheckstate(1);
					a.push(j)
				} else {
					if (j.getcheckstate() == 1) {
						j.setcheckstate(0);
						a.push(j)
					}
				}
			});
			$.each(a,
			function(j, k) {
				var h = k.getParent();
				while (h) {
					h.setcheckstate(c.element.getNodeState(h));
					h = h.getParent()
				}
			});
			this.element.createTree(b)
		}
		e = b = null
	},
	reset: function() {
		this.setValue("")
	},
	doResize: function(a) {
		FR.TreeEditor.superclass.doResize.call(this, a)
	}
});
$.shortcut("tree", FR.TreeEditor);
FR.TableTree = FR.extend(FR.BaseEditor, {
	_defaultConfig: function() {
		return $.extend(FR.TableTree.superclass._defaultConfig.apply(), {
			width: 120,
			height: 200
		})
	},
	_init: function() {
		FR.TableTree.superclass._init.apply(this, arguments);
		FR.$defaultImport("/com/fr/web/platform/js/jquery.tabletree.js", "js");
		FR.$defaultImport("/com/fr/web/platform/css/jquery.tabletree.css", "css");
		var b = this.options;
		this.$TableTreeDiv = this.element;
		if (b.width > -1) {
			this.$TableTreeDiv.css("width", b.width)
		}
		if (b.height > -1) {
			this.$TableTreeDiv.css("height", b.height)
		}
		var a;
		this.options.data = new FR.Data({
			dataSource: new FR.URLSource({
				url: this.options.widgetUrl
			}),
			dataReader: new FR.DataReader({
				choosedFields: ["name", "state"]
			})
		});
		if (this.options.rootLoader) {
			this.options.treedata = new FR.TreeData({
				treeLoader: this.options.rootLoader
			})
		} else {
			if (this.options.url) {
				this.options.treedata = new FR.TreeData({
					url: this.options.url
				})
			} else {
				return
			}
		}
		this.$TableTreeDiv.tabletree({
			data: this.options.treedata.getData(),
			cvns: this.options.data.getData()
		})
	},
	getValue: function() {
		return this.$TableTreeDiv.getTableTreeValue()
	},
	_setValue: function(a) {
		this.$TableTreeDiv.setTableTreeValue(a)
	}
});
$.shortcut("tabletree", FR.TableTree);
FR.ListEditor = FR.extend(FR.SynchronBaseEditor, {
	_defaultConfig: function() {
		return $.extend(FR.ListEditor.superclass._defaultConfig.apply(), {
			width: 120,
			height: 200,
			icon: false,
			multi: false,
			removeSelf: false,
			textAlign: "left"
		})
	},
	_init: function() {
		FR.ListEditor.superclass._init.apply(this, arguments);
		this.initData();
		this._initList();
		var a = this;
		this.options.data.once(FR.Events.AFTERREAD,
		function() {
			a.fireEvent(FR.Events.DEFAULTINIT)
		})
	},
	initData: function() {
		if (this.options.data) {
			return
		}
		if (this.options.controlAttr) {
			this.setSource(this.options.controlAttr);
			return
		}
		if (this.options.widgetUrl) {
			this.options.data = FR.DataFactory.createSynchronJSONData(this.options.widgetUrl, false)
		} else {
			if (this.options.items) {
				this.options.data = FR.DataFactory.createSynchronArrayData(this.options.items)
			}
		}
	},
	setSource: function(b) {
		var a = this;
		if (b.value !== undefined) {
			this.once(FR.Events.DEFAULTINIT,
			function() {
				a.setValue(b.value, false)
			})
		}
		this.options.data = FR.DataFactory.createSynchronArrayData(b.data)
	},
	_initList: function() {
		FR.$defaultImport("/com/fr/web/platform/js/jquery.jlist.js", "js");
		var a = this.options;
		if (a.width > -1) {
			this.element.css("width", a.width)
		}
		if (a.height > -1) {
			this.element.css("height", a.height)
		}
		if (a.textAlign) {
			this.element.css("text-align", a.textAlign)
		}
		this.jlist()
	},
	getValue: function() {
		var a = this.getSelectedItem();
		return a != null ? a.getValue() : null
	},
	_setValue: function(f, c) {
		var e = this.options.data;
		for (var b = 0,
		a = e.getLength(); b < a; b++) {
			var d = this.options.data.getRecord(b);
			if (d.getShowValue() == f || d.getValue() == f) {
				this.setSelectedIndex(b);
				break
			}
		}
	},
	getSelectedText: function() {
		var a = this.getSelectedItem();
		return a != null ? a.getShowValue() : null
	},
	addItem: function(a) {
		this.doAddItem(a)
	},
	getAll: function() {
		var c = [],
		d = this.options.data;
		for (var b = 0,
		a = d.getLength(); b < a; b++) {
			var e = d.getRecord(b);
			c.push({
				text: e.getShowValue(),
				value: e.getValue() != null ? e.getValue() : e.getShowValue()
			})
		}
		return c
	},
	setAll: function(b) {
		var a = this;
		$.each(b,
		function(d, e) {
			var c = FR.createItemRecord({
				text: d,
				value: e
			});
			a.doAddItem(c);
			a.options.data.addRecord(c)
		})
	},
	clear: function() {
		this.doClear()
	},
	clearSelected: function() {
		$(".fr-list-node", this.element).removeClass("fr-list-node-selected");
		this.fireEvent(FR.Events.NOSELECT)
	},
	doFilter: function(b) {
		var a = $(".fr-list-node", this.element);
		if (b || b === 0) {
			b = b.toUpperCase();
			$.each(a,
			function(c, d) {
				var e = $("a > span", $(this)).html();
				if (e.toUpperCase().indexOf(b) > -1) {
					$(this).show()
				} else {
					$(this).hide()
				}
			})
		} else {
			a.show()
		}
	},
	popData: function(a) {
		this.doClear();
		this.options.data = new FR.SynchronData({
			dataSource: new FR.SynchronObjectSource({
				object: a
			})
		});
		this.jlist();
		if (this.getSelectedIndex() < 0) {
			this.fireEvent(FR.Events.NOSELECT)
		}
	},
	setSelectedIndex: function(b) {
		var c = $("ul.fr-list-content", this.element);
		var a = $("li", c);
		if (!a) {
			this.fireEvent(FR.Events.NOSELECT)
		}
		this.doSelected(null, $(a[b]))
	}
});
$.shortcut("list", FR.ListEditor);
FR.FileUploadEditor = FR.extend(FR.WriteEditor, {
	_defaultConfig: function() {
		return $.extend(FR.FileUploadEditor.superclass._defaultConfig.apply(), {
			width: 120,
			height: 20,
			render: true
		})
	},
	_init: function() {
		FR.FileUploadEditor.superclass._init.apply(this, arguments);
		var b = this.options;
		var a = this;
		this.$uploadForm = $('<form enctype="multipart/form-data"></form>');
		this.upload_options = {
			url: this.options.url ? this.options.url: FR.servletURL + "?op=fr_attach&cmd=ah_upload",
			el: this,
			allowTypes: this.options.allowTypes ? this.options.allowTypes: "",
			err: this.options.errorMsg ? this.options.errorMsg: "",
			callback: function(d, c, e) {
				if (c == "success") {
					a._preview(FR.jsonDecode(d.responseText));
					e.fireEvent(FR.Events.CALLBACK, d)
				}
				a._focusPreviewPane(true)
			},
			autoUpload: true
		};
		this.$fileupload = $('<input type="file" name="file"/>').addClass("fr-fileupload").appendTo(this.$uploadForm);
		this.$fileupload.change(function() {
			FR.autoSubmit(a.upload_options, a.$uploadForm)
		});
		this.$uploadForm.appendTo(this.element);
		var a = this;
		if (b.render === true) {
			this.$preview = $("<img>").attr("src", FR.servletURL + "?op=resource&resource=/com/fr/web/images/s.gif").appendTo(this.$uploadForm).addClass("fr-fileupload-image")
		} else {
			this.$preview = this.element
		}
		this.$preview.css({
			width: b.width,
			height: b.height
		});
		this.$fileupload.css("top", (this.$preview.height() - 15) / 2);
		if (b.value != null) {
			this.setValue(b.value)
		}
	},
	isValidate: function() {
		return true
	},
	startEditing: function() {
		this.$preview.show();
		if (this.options.disabled) {
			this.$preview.unbind("click")
		}
	},
	stopEditing: function() {
		this.$preview.hide();
		this._focusPreviewPane(false)
	},
	_showDialog: function() {
		var a = this;
		FR.showUploadDialog({
			url: this.options.url ? this.options.url: FR.servletURL + "?op=fr_attach&cmd=ah_upload",
			autoUpload: this.options.autoUpload,
			allowTypes: this.options.allowTypes,
			err: this.options.errorMsg,
			el: a,
			callback: function(c, b, d) {
				if (b == "success") {
					a._preview(FR.jsonDecode(c.responseText));
					d.fireEvent(FR.Events.CALLBACK, c)
				}
				a._focusPreviewPane(true)
			}
		});
		this._focusPreviewPane(false)
	},
	_focusPreviewPane: function(a) {
		$(document)[a ? "bind": "unbind"]("keydown", this, this._onKeyDown)
	},
	_onKeyDown: function(b) {
		var a = b.data;
		if (b.ctrlKey && b.keyCode == $.ui.keyCode.SPACE) {
			a._showDialog()
		}
	},
	_setValue: function(b) {
		var a = this.getValue();
		if (arguments[1] !== false) {
			this.fireEvent(FR.Events.CHANGE, b, a)
		}
		this._preview(b)
	},
	getValue: function() {
		return this.$preview.data("_cv")
	},
	_preview: function(a) {
		this.$preview.data("_cv", a);
		if (a.attach_type != null && a.attach_id != null) {
			FR.previewAttachment(this.$preview, a)
		}
	}
});
$.shortcut("file", FR.FileUploadEditor);
$.extend(FR, {
	lastTarget: undefined,
	previewAttachment: function() {
		function a(b) {
			window.open(FR.servletURL + "?op=fr_attach&cmd=ah_download&id=" + b.data);
			b.stopPropagation()
		}
		return function(j, c) {
			FR.lastTarget = j;
			var b = $(j);
			b.css("background", "");
			if (c.attach_type == "image") {
				var f = FR.servletURL + "?op=fr_attach&cmd=ah_image&id=" + c.attach_id;
				b.css("background", "url(" + f + ") 0 0 no-repeat transparent");
				b.css("cursor", "default").unbind("click", a)
			} else {
				if (FR.isArray(c)) {
					var e = ".";
					var h = "";
					for (var d = 0; d < c.length - 1; d++) {
						h = h + c[d].attach_id + e
					}
					h = h + c[c.length - 1].attach_id
				} else {
					h = c.attach_id
				}
				var g = $("<img class='attach_download'/>").attr("src", FR.servletURL + "?op=resource&resource=/com/fr/web/images/file/download.gif").css("cursor", "pointer").bind("click", h, a);
				if ($(".attach_download", b).length != 0) {
					$(".attach_download", b).remove()
				}
				b.append(g);
				g.show()
			}
		}
	} ()
});
FR.MultiFileEditor = FR.extend(FR.WriteEditor, {
	_defaultConfig: function() {
		return $.extend(FR.MultiFileEditor.superclass._defaultConfig.apply(), {
			width: 120,
			height: 20,
			render: true
		})
	},
	_init: function() {
		FR.$defaultImport("/com/fr/web/core/js/noswfupload.js", "js");
		FR.$defaultImport("/com/fr/web/core/css/widget/noswfupload-icons.css", "css");
		FR.MultiFileEditor.superclass._init.apply(this, arguments);
		var b = this.options;
		var a = this;
		this.$uploadForm = $('<form enctype="multipart/form-data"></form>').appendTo(this.element);
		this.divWrap = $("<div></div>").css({
			width: this.options.width + "px",
			height: this.options.height + "px",
			position: "relative",
			overflow: "hidden"
		}).appendTo(this.$uploadForm);
		this.$fileupload = $('<input type="file" name="file"/>').addClass("fr-fileupload").appendTo(this.divWrap);
		if (b.render === true) {
			this.$preview = $("<img>").attr("src", FR.servletURL + "?op=resource&resource=/com/fr/web/images/s.gif").appendTo(this.divWrap).addClass("fr-fileupload-image").css("left", this.element.outerWidth(true) - this.element.width())
		} else {
			this.$preview = this.element
		}
		this.$preview.css({
			width: b.width,
			height: b.height
		});
		if (this.options.write) {
			$(".fr-fileupload", a.element).css({
				right: 0,
				"font-size": a.element.height() < 100 ? "100px": a.element.height() + "px"
			})
		}
		this.wrap = noswfupload.wrap((this.$fileupload)[0], b.maxsize, this.options.width, this.options.height);
		wrap = this.wrap;
		wrap.onerror = function() {
			noswfupload.text(this.dom.info, "WARNING: Unable to upload " + this.file.fileName)
		};
		wrap.onloadstart = function(c, d) {
			this.show(0, 0);
			noswfupload.text(this.dom.info, "Preparing for upload ... ")
		};
		wrap.onprogress = function(c, d) {
			this.show((this.sent + c.loaded) * 100 / this.total, c.loaded * 100 / c.total);
			noswfupload.text(this.dom.info, "正在上传的文件： " + this.file.fileName);
			if (this.file.fileSize !== -1) {
				if (c.simulation) {
					noswfupload.text(this.dom.info, "正在上传文件： " + this.file.fileName, "所有文件共已上传" + noswfupload.size(this.sent + c.loaded))
				} else {
					noswfupload.text(this.dom.info, "正在上传文件： " + this.file.fileName, "该文件已上传： " + noswfupload.size(c.loaded), "所有文件共已上传" + noswfupload.size(this.sent + c.loaded))
				}
			} else {
				noswfupload.text(this.dom.info, "Uploading: " + this.file.fileName, "Sent: " + (this.sent / 100) + " out of " + (this.total / 100))
			}
		};
		wrap.onerror = function() {
			noswfupload.text(this.dom.info, "WARNING: Unable to upload " + this.file.fileName)
		};
		wrap.onload = function(c, e) {
			var d = this;
			noswfupload.text(this.dom.info, "Upload complete");
			setTimeout(function() {
				d.clean();
				d.hide();
				noswfupload.text(d.dom.info, "");
				a.fireEvent(FR.Events.AFTEREDIT)
			},
			1000);
			a.showViewList()
		};
		wrap.url = this.options.url ? this.options.url: FR.servletURL + "?op=fr_attach&cmd=ah_upload";
		wrap.maxlength = b.maxlength;
		wrap.fileType = b.accept;
		wrap.attach_array = [];
		this._setValue(b.value)
	},
	_setValue: function(a) {
		if (FR.equals(a, this.wrap.attach_array) || (this.wrap.attach_array.length == 1 && FR.equals(this.wrap.attach_array[0], a))) {
			return
		}
		if (($.isArray(a) && a.length > 0 && a[0].attach_id) || (a && a.attach_id)) {
			this.wrap.attach_array = $.isArray(a) ? a: [a];
			for (var b = 0; b < this.wrap.attach_array.length; b++) {
				wrap.files.push(new Object())
			}
			this.showViewList();
			wrap.files.length = 0
		}
	},
	showViewList: function() {
		var h = this;
		if (!h.options.hideFileList) {
			var g = $("ul", h.element);
			if (g.length == 0) {
				var c = $("<div/>").css({
					width: "240px",
					"max-height": "80%",
					border: "1px solid #CEE2F2",
					"z-index": 99,
					overflow: "auto",
					position: "absolute"
				}).appendTo(h.element);
				var j = $("<ul/>").addClass("fr-fileupload-list").appendTo(c)
			} else {
				var j = g
			}
		}
		wrap = h.wrap;
		if (h.options.maxlength == 1 && wrap.attach_array[0].attach_type == "image") {
			$("ul", h.element).remove();
			if (!h.$tempPreview) {
				h.$tempPreview = $("<img>").attr("src", FR.servletURL + "?op=resource&resource=/com/fr/web/images/s.gif").appendTo(this.divWrap)
			}
			var b = this.options;
			h.$tempPreview.css({
				left: h.element.outerWidth(true) - h.element.width(),
				width: b.width,
				height: b.height,
				top: 0,
				position: "absolute"
			});
			FR.previewAttachment(h.$tempPreview, wrap.attach_array[0])
		} else {
			if (FR.lastTarget) {
				$(FR.lastTarget).css("background", "")
			}
			if (h.$tempPreview) {
				h.$tempPreview.css("background", "")
			}
			if (h.options.maxlength == 1) {
				if ($(".fr-fileupload-progressCancel", h.element)[0]) {
					$($(".fr-fileupload-progressCancel", h.element)[0]).click()
				} else {}
			}
		}
		if (!h.options.hideFileList) {
			for (var e = wrap.attach_array.length - 1,
			d = 0; d < wrap.files.length; e--, d++) {
				var f = wrap.attach_array[e];
				var a = $("<li></li>");
				a.append(($("<a class='fr-fileupload-progressCancel' href='#'> </a>")).click(function() {
					h.removeAttach(f, a)
				})).append($("<div class='fr-fileupload-fileNameItem'>" + f.filename + " " + FR.__fileSizeFormat__(f.fileSize) + "</div>").bind("click",
				function(k) {
					return function() {
						var l = k.attach_id;
						window.open(FR.servletURL + "?op=fr_attach&cmd=ah_download&id=" + l)
					}
				} (f))).addClass("fr-fileupload-listitem");
				j.append(a)
			}
		}
	},
	removeAttach: function(d, b) {
		var a = d.attach_id;
		var c = this;
		$.ajax({
			url: FR.servletURL + "?op=fr_attach&cmd=ah_release",
			data: {
				id: a
			},
			complete: function(f, e) {
				if (e == "success") {
					if (b) {
						b.remove()
					}
					for (var g = 0; g < wrap.attach_array.length; g++) {
						if (wrap.attach_array[g].attach_id == a) {
							wrap.attach_array.remove(wrap.attach_array[g]);
							break
						}
					}
					c.fireEvent(FR.Events.AFTEREDIT)
				}
			}
		})
	},
	startEditing: function() {
		if (this.options.write) {
			var a = contentPane.curLGP.getTDCell(this.options.location);
			if (a && $(".attach_download", $(a)).length > 0) {
				$(".attach_download", $(a)).hide()
			}
		}
		this.$preview.show();
		if (this.options.disabled) {
			this.$preview.unbind("click")
		}
	},
	stopEditing: function() {
		if (this.options.write) {
			var a = contentPane.curLGP.getTDCell(this.options.location);
			if (a && $(".attach_download", $(a)).length > 0) {
				$(".attach_download", $(a)).show()
			}
		}
	},
	getValue: function() {
		if (!this.wrap.attach_array) {
			return []
		}
		if (this.options.maxlength == 1 && this.wrap.attach_array[0] && this.wrap.attach_array[0].attach_type == "image") {
			return this.wrap.attach_array[0]
		}
		return this.wrap.attach_array
	}
});
$.shortcut("multifile", FR.MultiFileEditor);
FR.ToggleButtonGroup = FR.extend(FR.SynchronBaseEditor, {
	_defaultConfig: function() {
		return $.extend(FR.ToggleButtonGroup.superclass._defaultConfig.apply(), {
			columnsInRow: 0
		})
	},
	_init: function() {
		FR.ToggleButtonGroup.superclass._init.apply(this, arguments);
		var c = this.options;
		var b = c.width > 0 ? c.width: 0;
		var a = c.height > 0 ? c.height: 0;
		this.$container = this.element.addClass("fr-form-btn-group");
		this.$container.css({
			width: b,
			height: a
		}).addClass(this.lbox_class);
		this.buttonArray = [];
		if (c.value != null) {
			this.once(FR.Events.DEFAULTINIT,
			function() {
				this.setValue(c.value, false)
			})
		}
		this.initData()
	},
	initData: function() {
		if (this.options.data) {
			return
		}
		if (this.options.controlAttr) {
			this.setSource(this.options.controlAttr);
			this._confirmEvents = function() {
				this.fireEvent(FR.Events.AFTERINIT)
			};
			return
		}
		if (this.options.widgetUrl) {
			this.options.data = FR.DataFactory.createSynchronJSONData(this.options.widgetUrl, false);
			this.options.data.resetStatus(this.createDependencePara4Data())
		} else {
			if (this.options.items) {
				this.options.data = FR.DataFactory.createSynchronArrayData(this.options.items)
			}
		}
		var a = this;
		this.options.data.afterRead(function(b) {
			if (a.isBoxBuild !== true) {
				a._buildBox(b)
			}
		});
		this.options.data.loadData()
	},
	setSource: function(b) {
		var a = this;
		if (b.value !== undefined) {
			this.once(FR.Events.DEFAULTINIT,
			function() {
				a.setValue(b.value, false)
			})
		}
		this.options.data = FR.DataFactory.createSynchronArrayData(b.data);
		this.options.data.afterRead(function(c) {
			a._buildBox(c)
		});
		this.options.data.loadData()
	},
	reset: function() {
		this.setValue(null);
		this.isBoxBuild = undefined;
		if (this.options.data.resetStatus(this.createDependencePara4Data())) {
			this.options.data.loadData()
		}
	},
	clearContent: function() {
		this.$container.empty()
	},
	_buildBox: function() {
		this.clearContent();
		var a = this.options.data.getRecords();
		this._initGridConfig(a);
		this._setItems(a);
		this.isBoxBuild = true;
		this.fireEvent(FR.Events.DEFAULTINIT)
	},
	_initGridConfig: function(b) {
		var a = b.length + (this.options.chooseAll === true ? 1 : 0),
		c = this.options.columnsInRow,
		d = c === 0 ? 1 : 1 + Math.floor((a - 0.1) / c);
		this.gridConfig = {
			columns: c === 0 || d === 0 ? a: c,
			rows: d,
			items: [],
			renderEl: this.$container
		};
		return this.gridConfig
	},
	setEnable: function(a) {
		if (this.buttonArray) {
			for (var b = 0; b < this.buttonArray.length; b++) {
				this.buttonArray[b].setEnable(a)
			}
		}
		FR.ToggleButtonGroup.superclass.setEnable.apply(this, arguments)
	},
	getErrorMessage: function() {
		return this.options.errorMsg || FR.i18n.NOT_NULL
	},
	doResize: function(a) {
		FR.ToggleButtonGroup.superclass.doResize.call(this, a);
		this.$container.css({
			width: a.width,
			height: a.height
		});
		if (this.grid) {
			this.grid.doLayout();
			this._checkTable()
		}
	},
	_checkTable: function() {
		if (this.$container.parent()[0] && this.$container.parent()[0].tagName == "TD") {
			this.$container.css("position", "relative")
		}
	}
});
$.shortcut("togglebuttongroup", FR.ToggleButtonGroup);
FR.CheckBoxGroup = FR.extend(FR.ToggleButtonGroup, {
	lbox_class: "fr-group-box",
	sbox_class: "fr-group-span",
	_defaultConfig: function() {
		return $.extend(FR.CheckBoxGroup.superclass._defaultConfig.apply(), {
			delimiter: ",",
			startSymbol: "",
			endSymbol: ""
		})
	},
	_init: function() {
		FR.CheckBoxGroup.superclass._init.apply(this, arguments)
	},
	changeAllState: function(c) {
		if (this.buttonArray) {
			for (var b = 0,
			a = this.buttonArray.length; b < a; b++) {
				this.buttonArray[b].setSelected(c)
			}
		}
	},
	_setItems: function(c) {
		var b = c || [];
		var a = this;
		$.each(b,
		function(e, f) {
			if (a.options.adaptive) {
				var d = $("<span></span>").addClass(a.sbox_class).appendTo(a.$container)
			} else {
				var d = $("<span/>").addClass(a.sbox_class);
				var g = {
					column: e % a.gridConfig.columns,
					row: Math.floor(e / a.gridConfig.columns),
					el: d
				};
				a.gridConfig.items.push(g)
			}
			a.buttonArray[e] = new FR.CheckBox({
				renderEl: $("<div/>").appendTo(d),
				disabled: a.options.disabled,
				text: f.getShowValue(),
				fieldValue: f.getValue(),
				sessionID: a.options.sessionID,
				widgetName: a.options.widgetName
			});
			a.buttonArray[e].on(FR.Events.BEFORESTATECHANGE,
			function() {
				a.fireEvent(FR.Events.BEFORESTATECHANGE)
			});
			a.buttonArray[e].on(FR.Events.STATECHANGE,
			function() {
				a.fireEvent(FR.Events.STATECHANGE, e, this.selected());
				a.fireEvent(FR.Events.AFTEREDIT)
			})
		});
		this._checkChooseAll()
	},
	doResize: function(a) {
		FR.ToggleButtonGroup.superclass.doResize.call(this, a);
		this.$container.css({
			width: a.width,
			height: a.height - (this.options.chooseAll ? 15 : 0)
		});
		this._checkTable()
	},
	_checkChooseAll: function() {
		if (!this.options.adaptive) {
			this.grid = new FR.GridLayout(this.gridConfig);
			this.grid.element.doLayout();
			this._checkTable()
		}
		if (this.options.chooseAll) {
			var c;
			if (this.options.adaptive) {
				var b = this.options.form ? this.element: this.element.parent();
				this.options.form || this.element.height(parseInt(b.height()) - 15);
				c = $("<span/>").addClass(this.sbox_class).appendTo(b)
			} else {
				c = $("<span/>").addClass(this.sbox_class);
				var d = this.buttonArray.length;
				var f = {
					column: d % this.gridConfig.columns,
					row: Math.floor(d / this.gridConfig.columns),
					el: c
				};
				this.gridConfig.items.push(f)
			}
			var e = new FR.CheckBox({
				renderEl: c,
				disable: true,
				text: FR.i18n.Choose_All
			});
			var a = this;
			e.on(FR.Events.STATECHANGE,
			function() {
				a.changeAllState(e.isSelected())
			})
		}
		if (!this.options.adaptive) {
			this.grid = new FR.GridLayout(this.gridConfig);
			this.grid.element.doLayout();
			this._checkTable()
		}
	},
	getValue: function() {
		var a = this;
		var c = [];
		var d = this.options.noSelected;
		$.each(a.buttonArray,
		function(e, f) {
			if (d) {
				if (f.selected()) {
					c[e] = a.options.data.getRecord(e).getValue()
				} else {
					c[e] = d
				}
			} else {
				if (f.selected()) {
					c[c.length] = a.options.data.getRecord(e).getValue()
				}
			}
		});
		if (this.options.returnArray) {
			return c
		}
		var b = this.options.startSymbol + c.join(this.options.delimiter) + this.options.endSymbol;
		return b
	},
	_setValue: function(e) {
		var a = this;
		var d = arguments[1];
		var b = function() {
			if (a.isBoxBuild != true) {
				return
			}
			clearInterval(c);
			var f = a.getValue();
			if (typeof e === "string") {
				var h = e.split(a.options.delimiter)
			} else {
				var h = $.makeArray(e)
			}
			if (!a.options.returnArray) {
				h = e + "";
				h = h.substring(a.options.startSymbol.length, h.length - a.options.endSymbol.length).split(a.options.delimiter)
			}
			for (var g = 0; g < a.buttonArray.length; g++) {
				a.buttonArray[g].setSelectedWithoutEvent(false)
			}
			$.each(h,
			function(k, l) {
				for (var m = 0,
				j = a.buttonArray.length; m < j; m++) {
					if (a.buttonArray[m].options.fieldValue == l) {
						a.buttonArray[m].setSelectedWithoutEvent(true);
						break
					}
				}
			});
			if (d !== false) {
				a.fireEvent(FR.Events.CHANGE, e, f)
			}
		};
		if (this.isBoxBuild == true) {
			b();
			return
		}
		var c = setInterval(b, 100)
	}
});
$.shortcut("checkboxgroup", FR.CheckBoxGroup);
FR.RadioGroup = FR.extend(FR.ToggleButtonGroup, {
	lbox_class: "fr-group-box",
	sbox_class: "fr-group-span",
	_init: function() {
		FR.RadioGroup.superclass._init.apply(this, arguments)
	},
	_setItems: function(d) {
		var c = d || [],
		a = c.length;
		var e;
		if (this.options.widgetName) {
			e = this.options.widgetName
		} else {
			e = "noNameChild"
		}
		var b = this;
		$.each(c,
		function(g, h) {
			if (b.options.adaptive) {
				var f = $("<span></span>").addClass(b.sbox_class).appendTo(b.$container)
			} else {
				var f = $("<span/>").addClass(b.sbox_class);
				var j = {
					column: g % b.gridConfig.columns,
					row: Math.floor(g / b.gridConfig.columns),
					el: f
				};
				b.gridConfig.items.push(j)
			}
			b.buttonArray[g] = new FR.RadioButton({
				renderEl: $("<div/>").appendTo(f),
				disabled: b.options.disabled,
				text: h.getShowValue(),
				fieldValue: h.getValue(),
				sessionID: b.options.sessionID,
				name: e
			});
			b.buttonArray[g].on(FR.Events.BEFORESTATECHANGE,
			function() {
				b.fireEvent(FR.Events.BEFORESTATECHANGE)
			});
			b.buttonArray[g].on(FR.Events.STATECHANGE,
			function() {
				b.assureOneButtonChecked(this);
				if (this.selected() == true || b.getValue() == "") {
					b.fireEvent(FR.Events.STATECHANGE, g);
					b.fireEvent(FR.Events.AFTEREDIT)
				}
			})
		});
		if (!b.options.adaptive) {
			this.grid = new FR.GridLayout(this.gridConfig);
			this.grid.element.doLayout();
			this._checkTable()
		}
	},
	assureOneButtonChecked: function(b) {
		if (b.isSelected()) {
			for (var c = 0,
			a = this.buttonArray.length; c < a; c++) {
				if (this.buttonArray[c] == b) {
					continue
				}
				this.buttonArray[c].setSelected(false)
			}
		}
	},
	getValue: function() {
		var a = this;
		var b = "";
		$.each(a.buttonArray,
		function(c, d) {
			if (d.selected()) {
				b = a.options.data.getRecord(c).getValue()
			}
		});
		return b
	},
	_setValue: function(e) {
		var a = this;
		var d = arguments[1];
		var b = function() {
			if (a.isBoxBuild != true) {
				return
			}
			clearInterval(c);
			var g = a.options.value;
			if (!e && e != 0) {
				return
			}
			for (var h = 0; h < a.buttonArray.length; h++) {
				a.buttonArray[h].setSelectedWithoutEvent(false)
			}
			if (typeof e == "boolean") {
				e = e ? "true": "false"
			}
			for (var h = 0,
			f = a.buttonArray.length; h < f; h++) {
				if (a.buttonArray[h].options.fieldValue == e) {
					a.buttonArray[h].setSelectedWithoutEvent(true);
					break
				}
			}
			if (d !== false) {
				a.fireEvent(FR.Events.CHANGE, e, g)
			}
		};
		if (this.isBoxBuild == true) {
			b();
			return
		}
		var c = setInterval(b, 100)
	}
});
$.shortcut("radiogroup", FR.RadioGroup);
FR.ToogleButton = FR.extend(FR.BaseEditor, {
	selected_class: "fr-checkbox-checkon",
	unselected_class: "fr-checkbox-checkoff",
	_defaultConfig: function() {
		return $.extend(FR.ToogleButton.superclass._defaultConfig.apply(), {
			selected: false,
			scope: this,
			render: true
		})
	},
	_init: function() {
		FR.ToogleButton.superclass._init.apply(this, arguments);
		var a = this.options;
		if (a.render == true) {
			this.$btn = $("<span>" + ((a.text != null && a.text != "") ? a.text: "&nbsp;") + "</span>").appendTo(this.element).addClass("x-text").addClass("fr-widget-click").click(function(b) {
				if (this.isEnabled()) {
					if (!this.selected(!this.selected())) {
						return false
					}
					this.fireEvent(FR.Events.AFTEREDIT);
					this.fireEvent(FR.Events.CLICK)
				}
			}.createDelegate(this))
		} else {
			this.$btn = this.element;
			if (a.text != null) {
				$("<span>" + a.text + "</span>").insertAfter(this.$btn)
			}
		}
		if ($.browser.webkit) {
			this.$btn.css("padding-top", 1)
		}
		this.$btn.css("display", "inline-block");
		if ($.isFunction(a.handler)) {
			this.$btn.click(a.handler.createDelegate(a.scope || this).createInterceptor(this.isEnabled, this))
		}
		this.changeBoxState(a.selected);
		this.$formbtn = this.initFormBtn();
		if (a.value) {
			if (!this.isValidate(a.value)) {
				FR.Msg.toast(this.errorMsg);
				return
			}
			if (typeof a.value == "boolean") {
				this.setSelectedWithoutEvent(a.value)
			} else {
				if (a.value == "true") {
					this.setSelectedWithoutEvent(true)
				} else {
					if (a.value == "false") {
						this.setSelectedWithoutEvent(false)
					}
				}
			}
		}
	},
	changeBoxState: function(a) {
		a = typeof a == "boolean" ? a: (a == "false" ? false: true);
		this.$btn[a ? "addClass": "removeClass"](this.selected_class);
		this.$btn[a ? "removeClass": "addClass"](this.unselected_class)
	},
	initFormBtn: function() {
		return null
	},
	getValue: function() {
		return this.selected()
	},
	_setValue: function(a) {
		if (typeof a == "boolean") {
			this.selected(a, "noFireEvent")
		} else {
			if (a == "true") {
				this.selected(true, "noFireEvent")
			} else {
				if (a == "false") {
					this.selected(false, "noFireEvent")
				}
			}
		}
	},
	getText: function() {
		return this.options.text
	},
	reset: function() {
		this.selected(false)
	},
	selected: function() {
		if (arguments.length == 0) {
			return this.isSelected()
		} else {
			if (arguments[1] == "noFireEvent") {
				return this.setSelectedWithoutEvent(arguments[0])
			} else {
				return this.setSelected(arguments[0])
			}
			return true
		}
	},
	isSelected: function() {
		return this.$btn.is("." + this.selected_class)
	},
	setSelected: function(a) {
		if (!this.setSelectedWithoutEvent(a)) {
			return false
		}
		this.fireEvent(FR.Events.STATECHANGE, this.selected());
		return true
	},
	setSelectedWithoutEvent: function(a) {
		if (this.$formbtn) {
			this.$formbtn.attr("checked", a !== false)
		}
		this.changeBoxState(a);
		return true
	},
	destroy: function() {
		this.element.empty()
	},
	doResize: function(a) {
		FR.ToogleButton.superclass.doResize.call(this, a)
	}
});
FR.TreeNodeToogleButton = FR.extend(FR.ToogleButton, {
	selected_class: "x-treenode-expand",
	unselected_class: "x-treenode-unexpand",
	son_array: undefined,
	expand_state: false,
	_init: function() {
		FR.TreeNodeToogleButton.superclass._init.apply(this, arguments);
		if ($.browser.msie) {
			this.$btn.css("background-position", "-1px -2px")
		}
	},
	_node_init: function() {
		var d = this.options = $.extend({
			expand: false
		},
		this.options);
		if (d.sonarray && d.sonarray.length > 1) {
			if (d.sonarray[0] == -1) {
				this.expand_mode = 0
			} else {
				this.expand_mode = 1
			}
			var b = _g();
			var c = b.curLGP;
			if (!b.TREEMGR) {
				b.TREEMGR = [];
				b.on(FR.Events.STARTLOAD,
				function(f) {
					if (b.TREEMGR) {
						b.TREEMGR[f.idx] = null
					}
				})
			}
			if (!b.TREEMGR[d.reportIndex]) {
				b.TREEMGR[d.reportIndex] = {};
				b.TREEMGR[d.reportIndex].nodes = [];
				b.TREEMGR[d.reportIndex].location_nodes = {}
			}
			if (!this.options.parNode) {
				for (var a = 0; a < b.TREEMGR[d.reportIndex].nodes.length; a++) {
					var e = b.TREEMGR[d.reportIndex].nodes[a];
					if (this.initNodeLayer(e)) {
						break
					}
				}
				if (!this.parentNode) {
					b.TREEMGR[d.reportIndex].nodes[b.TREEMGR[d.reportIndex].nodes.length] = this
				}
			} else {
				if (this.options.parNode != "null") {
					this.parent = b.TREEMGR[d.reportIndex].location_nodes[this.options.parNode];
					if (this.parent) {
						if (this.parent.sonNodes) {
							this.parent.sonNodes[this.parent.sonNodes.length] = this
						} else {
							this.parent.sonNodes = [];
							this.parent.sonNodes[0] = this
						}
					}
				}
				b.TREEMGR[d.reportIndex].location_nodes[this.options.location] = this
			}
			this.mgr = b.TREEMGR[this.options.reportIndex];
			if (!this.mgr.row_expand_state) {
				this.mgr.row_expand_state = [];
				this.mgr.col_expand_state = [];
				for (var a = 1; a < this.options.sonarray.length; a++) {
					if (this.expand_mode == 0) {
						this.mgr.row_expand_state[this.options.sonarray[a]] = 0
					} else {
						if (this.expand_mode == 1) {
							this.mgr.col_expand_state[this.options.sonarray[a]] = 0
						}
					}
				}
			}
			this.cells = []
		} else {
			this.expand_mode = -1
		}
		this.$btn.click(function() {
			this.treeNodeClick()
		}.createDelegate(this))
	},
	initNodeLayer: function(d) {
		if (d.expand_mode == this.expand_mode) {
			var a = this.options.sonarray[1];
			for (var b = 1; b < d.options.sonarray.length; b++) {
				if (a == d.options.sonarray[b]) {
					if (d.sonNodes) {
						for (var c = 0; c < d.sonNodes.length; c++) {
							if (this.initNodeLayer(d.sonNodes[c])) {
								return true
							}
						}
						d.sonNodes[d.sonNodes.length] = this;
						this.parentNode = d
					} else {
						d.sonNodes = [];
						d.sonNodes[0] = this;
						this.parentNode = d
					}
					return true
				} else {
					if (a < d.options.sonarray[b]) {
						return false
					}
				}
			}
		}
		return false
	},
	treeNodeClick: function() {
		if (!this.options.sonarray) {} else {
			this.TreeNodeAction(!this.expand_state)
		}
		this.fireEvent(FR.Events.CLICK);
		_g().fireEvent("refresh")
	},
	dealwithSelfSonArray: function() {
		if (this.options.needInit == false) {
			this.result_son_array = this.options.sonarray
		} else {
			if (this.sonNodes) {
				for (var b = 0; b < this.sonNodes.length; b++) {
					var d = 1;
					for (var a = 1; a < this.sonNodes[b].options.sonarray.length && d < this.options.sonarray.length; a++) {
						if (this.options.sonarray[d] == -1 || this.sonNodes[b].options.sonarray[a] > this.options.sonarray[d]) {
							a--;
							d++;
							continue
						} else {
							if (this.sonNodes[b].options.sonarray[a] == this.options.sonarray[d]) {
								this.options.sonarray[d] = -1;
								d++;
								continue
							}
						}
					}
				}
				this.result_son_array = [];
				this.result_son_array[0] = this.options.sonarray[0];
				for (var b = 1; b < this.options.sonarray.length; b++) {
					if (this.options.sonarray[b] >= 0) {
						this.result_son_array[this.result_son_array.length] = this.options.sonarray[b]
					}
				}
			} else {
				this.result_son_array = this.options.sonarray
			}
		}
		if (this.expand_mode == 0) {
			var c;
			for (var b = this.result_son_array.length - 1; b > 0; b--) {
				c = this.mgr.tr_array[this.result_son_array[b]];
				if (!c.hasClass("tntr")) {
					this.result_son_array.splice(b, 1)
				}
			}
		}
	},
	TreeNodeAction: function(g) {
		if (g == this.expand_state) {
			return
		}
		var k = this;
		if (!this.mgr) {
			this.mgr = _g().TREEMGR[this.options.reportIndex]
		}
		if (!this.mgr.tr_array) {
			var d = _g().curLGP;
			this.mgr.tr_array = [];
			var f;
			var h = $("table:eq(0)", d.$container);
			$.each((h.children("tbody")).children("tr[tridx]"),
			function(l, m) {
				f = $(m);
				k.mgr.tr_array[parseInt(f.attr("tridx"))] = f
			})
		}
		if (!this.result_son_array) {
			this.dealwithSelfSonArray()
		}
		this.selected(g);
		if (this.expand_mode == 0) {
			var f;
			for (var e = 1; e < this.result_son_array.length; e++) {
				f = this.mgr.tr_array[this.result_son_array[e]];
				if (f) {
					f.css("display", this.expand_state ? "none": "");
					if ($.browser.msie) {
						if (!this.cells[this.result_son_array[e]]) {
							this.cells[this.result_son_array[e]] = [];
							var c;
							$.each(f.children("td[tdcol]"),
							function(l, m) {
								c = $(m);
								k.cells[k.result_son_array[e]][parseInt(c.attr("tdcol"))] = c
							})
						}
						$.each(this.cells[this.result_son_array[e]],
						function(l, m) {
							if (!m || (!k.expand_state && k.mgr.col_expand_state[l] == 0)) {
								return
							}
							m.css("display", k.expand_state ? "none": "")
						})
					}
					this.mgr.row_expand_state[this.result_son_array[e]] = this.expand_state ? 0 : 1
				}
			}
		} else {
			if (this.expand_mode == 1) {
				var j = this.result_son_array[0];
				var c;
				var b;
				if (!this.mgr.td_table) {
					this.mgr.td_table = [];
					$.each(k.mgr.tr_array,
					function(l, m) {
						if (m) {
							$.each(m.children("td[tdcol]"),
							function(n, o) {
								o = $(o);
								b = parseInt(o.attr("tdcol"));
								if (!k.mgr.td_table[b]) {
									k.mgr.td_table[b] = []
								}
								k.mgr.td_table[b][l] = o
							})
						}
					})
				}
				var a;
				for (var e = 1; e < k.result_son_array.length; e++) {
					a = k.mgr.td_table[k.result_son_array[e]];
					if (a) {
						$.each(a,
						function(l, m) {
							if (m) {
								if (!k.expand_state && $.browser.msie && k.mgr.row_expand_state[l] == 0) {
									return
								}
								m.css("display", k.expand_state ? "none": "")
							}
						})
					}
				}
				for (var e = 1; e < this.result_son_array.length; e++) {
					this.mgr.col_expand_state[this.result_son_array[e]] = this.expand_state ? 0 : 1
				}
			}
		}
		if (this.sonNodes) {
			if (this.expand_state) {
				this.last_son_state = [];
				for (var e = 0; e < this.sonNodes.length; e++) {
					this.last_son_state[e] = this.sonNodes[e].selected();
					this.sonNodes[e].TreeNodeAction(false)
				}
			} else {
				if (this.last_son_state) {
					for (var e = 0; e < this.sonNodes.length; e++) {
						this.sonNodes[e].TreeNodeAction(this.last_son_state[e])
					}
				}
			}
		}
		this.expand_state = !this.expand_state
	}
});
$.shortcut("treenode", FR.TreeNodeToogleButton);
FR.CheckBox = FR.extend(FR.ToogleButton, {
	selected_class: "fr-checkbox-checkon",
	unselected_class: "fr-checkbox-checkoff",
	_init: function() {
		FR.CheckBox.superclass._init.apply(this, arguments);
		this.$btn.css("margin-left", 5)
	},
	initFormBtn: function() {
		if (this.options.sessionID && _g(this.options.sessionID).rtype == "form") {
			var a = $(this.$btn.parent());
			a.css("background-color", "white");
			this.$box = $("<input type='checkbox'/>").css("display", "none").attr("name", this.options.widgetName).appendTo(a);
			this.$box.attr("value", this.options.fieldValue || this.options.value || "")
		}
		return this.$box
	}
});
$.shortcut("checkbox", FR.CheckBox);
FR.RadioButton = FR.extend(FR.ToogleButton, {
	selected_class: "fr-radio-radioon",
	unselected_class: "fr-radio-radiooff",
	_init: function() {
		FR.RadioButton.superclass._init.apply(this, arguments)
	},
	initFormBtn: function() {
		if (this.options.sessionID && _g(this.options.sessionID).rtype == "form") {
			var a = $(this.$btn.parent());
			a.css("background-color", "white");
			this.$radio = $("<input type='radio'/>").css("display", "none").attr("name", this.options.name || this.options.widgetName).appendTo(a);
			this.$radio.attr("value", this.options.fieldValue || this.options.value || "")
		}
		return this.$radio
	}
});
$.extend(FR.RadioButton, {
	RadioButtonGroup: {}
});
$.shortcut("radio", FR.RadioButton);
FR.List = FR.extend(FR.BaseEditor, {
	_defaultConfig: function() {
		return $.extend(FR.List.superclass._defaultConfig.apply(), {
			baseCls: "fr-list",
			width: 120,
			height: 150
		})
	},
	_init: function() {
		FR.List.superclass._init.apply(this, arguments);
		var b = this.options;
		this.element.addClass(b.baseCls);
		if (b.width > -1) {
			this.element.css({
				width: b.width
			})
		}
		if (b.height > -1) {
			this.element.css({
				height: b.height
			})
		}
		var a = this;
		this.content = new FR.Panel({
			renderEl: $("<div/>").appendTo(this.element),
			width: b.width,
			height: b.height,
			title: "&nbsp",
			toolPosition: "left",
			dosize: true,
			collapsible: false,
			tools: [{
				iconCls: "fr-panel-tool-close",
				handler: function() {
					var c = $(".fr-list-node-selected", a.element).data("item");
					if (c) {
						a.remove(c)
					}
				}
			},
			{
				iconCls: "fr-panel-tool-max",
				handler: function() {
					var c = a.options.items.length;
					var d = prompt("请输入", "item" + (c + 1));
					if (d) {
						a.addItem({
							text: d,
							value: d
						})
					}
				}
			}]
		});
		this.listBody = this.content.element.find(">div.fr-panel-body");
		if (!b.items) {
			this.listBody.html($('<div class="fr-panel-loading"></div>').html("loading..."));
			this.options.data.getRecords()
		}
		this._setItems(this.options.data.getRecords())
	},
	_setItems: function(b) {
		var a = this;
		this.listBody.empty();
		$.each(b,
		function(c, d) {
			a._addItemContent(c, d)
		})
	},
	_addItemContent: function(a, b) {
		var c = this;
		$("<div/>").appendTo(c.listBody).text(b.getShowValue()).attr("title", b.getShowValue()).addClass("fr-combo-list-item").hover(function() {
			$(this).addClass("fr-list-node-over ")
		},
		function() {
			$(this).removeClass("fr-list-node-over ")
		}).click(function(d) {
			c.listBody.find(">div.fr-list-node-selected").removeClass("fr-list-node-selected");
			$(this).addClass("fr-list-node-selected");
			c.options.currentItem = b
		}).data("record", b)
	},
	addItem: function(a) {
		this.options.data.addRecord(a);
		this._addItemContent(this.options.data.getLength(), a)
	},
	remove: function(a) {
		this.options.data.removeRecord(a);
		$(".fr-combo-list-item", this.element).each(function(b, c) {
			var d = $(this).data("record");
			if (d == a) {
				$(this).remove()
			}
		})
	},
	removeAll: function() {
		this.options.data.clearData();
		this.listBody.empty()
	},
	getText: function() {
		return this.options.currentItem.getShowValue()
	},
	getValue: function() {
		return this.options.currentItem.getValue()
	},
	doResize: function(a) {
		this.content.doResize(a)
	}
});
$.shortcut("tlist", FR.List);
FR.IframeEditor = FR.extend(FR.BaseEditor, {
	_defaultConfig: function() {
		return $.extend(FR.IframeEditor.superclass._defaultConfig.apply(), {
			baseName: "fr_iframe",
			baseClass: "fr_iframeeditor",
			widgetName: "",
			src: "",
			width: "100%",
			height: "100%",
			showOverFlowX: true,
			showOverFlowY: true
		})
	},
	_init: function() {
		FR.IframeEditor.superclass._init.apply(this, arguments);
		this.initData();
		var a = this.options;
		this.editComp = this.element.css({
			width: "100%",
			height: "100%"
		});
		a.iframeName = a.widgetName || this.createNoRepeatName();
		this.$iframe = $("<iframe  name=" + a.iframeName + " id=" + a.iframeName + ">").css({
			width: a.width,
			height: a.height
		}).addClass(a.baseClass).attr({
			frameborder: 0,
			scrolling: !a.showOverFlowX && !a.showOverFlowY ? "no": "yes"
		}).appendTo(this.editComp);
		if ($.browser.msie && (this.$iframe.parent()).is("div") && (this.$iframe.parent().parent()).is("td") && (this.$iframe.parent().parent().attr("widget")) != null && (this.$iframe.parent().parent().parent()).is("tr")) {
			this.$iframe.parent().parent().attr("rowspan", "1")
		} else {
			this.$iframe.css("overflow-x", a.showOverFlowX ? "auto": "hidden");
			this.$iframe.css("overflow-y", a.showOverFlowY ? "auto": "hidden")
		}
		if (a.src) {
			this._loadIframeByGet()
		}
	},
	createNoRepeatName: function() {
		var a = $("iframe" + this.options.baseClass).length;
		while ($("iframe[name=" + this.options.baseName + a + "]").length != 0) {
			a++
		}
		return this.options.baseName + a
	},
	_loadIframeByGet: function() {
		var d = this.options.src;
		if (this.options.data) {
			for (var b = 0,
			a = this.options.data.getLength(); b < a; b++) {
				var c = this.options.data.getRecord(b).getContent();
				d = d.appendQuery(c)
			}
		}
		this.setValue(d)
	},
	getValue: function() {
		return this.$iframe.attr("src")
	},
	_setValue: function(a) {
		this.options.src = a;
		this.$iframe.attr("src", a)
	},
	doResize: function(a) {
		FR.IframeEditor.superclass.doResize.apply(this, arguments);
		if (this.submitForm) {
			this.submitForm.submit()
		}
	}
});
$.shortcut("iframe", FR.IframeEditor);
FR.Composite = FR.extend(FR.Widget, {
	type: "composite",
	_defaultConfig: function() {
		return $.extend(FR.Composite.superclass._defaultConfig.apply(), {
			innerWidget: {}
		})
	},
	_init: function() {
		FR.Composite.superclass._init.apply(this, arguments);
		var b = this.options.innerWidget;
		b.renderEl = $("<div>").appendTo($("body"));
		var a = FR.createWidget(b);
		if (a.element.data("jlayout")) {
			a.doResize(a.element.data("jlayout").preferred(this.element))
		}
		a.doLayout();
		a.element.css({
			position: ""
		});
		b.renderEl.appendTo(this.element.css({
			position: "relative"
		}))
	}
});
$.shortcut("composite", FR.Composite);
FR.Toolbar = FR.extend(FR.Widget, {
	type: "toolbar",
	_defaultConfig: function() {
		return $.extend(FR.Toolbar.superclass._defaultConfig.apply(), {
			baseCls: "x-toolbar",
			items: []
		})
	},
	_init: function() {
		FR.Toolbar.superclass._init.apply(this, arguments);
		var e = this.options;
		var a = 28;
		this.element.addClass(e.baseCls);
		this.element.css("height", a);
		if (e.toolbarbg) {
			if (e.toolbarbg.background) {
				if ($.browser.msie) {
					var d = navigator.userAgent.toLowerCase();
					if (d.indexOf("msie 6.0") == -1) {
						this.element.css("background", e.toolbarbg.background)
					} else {
						this.element.css("background", e.toolbarbg.background + " fixed")
					}
				} else {
					this.element.css("background", e.toolbarbg.background)
				}
			} else {
				this.element.css("background", "none")
			}
		}
		this.$tr = $("<tr>");
		this.$tr.appendTo($("<tbody>").appendTo($("<table cellspacing=0>").css({
			height: a,
			"vertical-align": "center"
		}).appendTo(this.element)));
		if (e.items != null) {
			if (!FR.isArray(e.items)) {
				e.items = [e.items]
			}
			for (var c = 0; c < e.items.length; c++) {
				if (c > 0) {
					this.addSep()
				}
				var b = FR.createWidget(e.items[c]);
				this.addJQuery(b.element)
			}
		}
	},
	addJQuery: function(b) {
		if (b != null) {
			var a = $("<td>").append(b);
			this.$tr.append(a)
		}
	},
	addSep: function() {
		this.addJQuery("<span class='sep'> </span>")
	}
});
$.shortcut("toolbar", FR.Toolbar);
FR.MenuButton = FR.extend(FR.Button, {
	_init: function() {
		FR.MenuButton.superclass._init.apply(this, arguments);
		this.$table.children("tbody").children("tr").children("td").children("em").addClass("fr-btn-arrow");
		var a = this.options.menu;
		if (FR.isArray(a)) {
			this.on("click",
			function() {
				FR.showMenuByEl({
					destroyOnClose: true,
					items: a,
					minWidth: 175,
					xxxfixclientH: this.options.istoolbarmenu ? true: false
				},
				this.element)
			}.createDelegate(this));
			this.$table.mouseleave(function(c) {
				if (this.fr_menu) {
					var b = {
						x: this.fr_menu.menu.$menuRoot.offset().left,
						y: this.fr_menu.menu.$menuRoot.offset().top - 1,
						width: this.fr_menu.menu.$menuRoot.bounds().width,
						height: this.fr_menu.menu.$menuRoot.bounds().height
					};
					if (c.clientY < b.y || c.clientY > b.y + b.height || c.clientX < b.x || c.clientX > b.x + b.width) {
						this.fr_menu.menu.close()
					}
				}
			}.createDelegate(this.element))
		}
	}
});
$.shortcut("menubutton", FR.MenuButton);
FR.SwitchButton = FR.extend(FR.Button, {
	_init: function() {
		FR.SwitchButton.superclass._init.apply(this, arguments);
		var a = this;
		this.$table.click(function() {
			a.options.selected = !a.options.selected;
			if (a.options.selected) {
				a.$table.addClass("fr-btn-pressed")
			} else {
				a.$table.removeClass("fr-btn-pressed")
			}
		})
	}
});
$.shortcut("switchbutton", FR.SwitchButton);
FR.ChartWidget = FR.extend(FR.Widget, {
	_init: function() {
		FR.ChartWidget.superclass._init.apply(this, arguments);
		this.loadData()
	},
	loadData: function() {
		var a = this.options;
		if (a.width && a.height) {
			this.element.width(a.width);
			this.element.height(a.height);
			this.reload()
		}
	},
	setSource: function(a) {
		this.options.dependPara = a;
		this.reload()
	},
	getDependence: function() {
		if (this.options.dependPara) {
			var b = this.options.dependPara;
			b.width = this.options.width;
			b.height = this.options.height;
			delete this.options.dependPara;
			return b
		}
		var b = {};
		if (FR.isArray(this.options.valueDependence)) {
			for (var a = 0; a < this.options.valueDependence.length; a++) {
				var c = this.options.valueDependence[a];
				var d = this.options.form.resolveVariable(c);
				if (d != "fr_primitive" && d != null) {
					b[c.toUpperCase()] = d
				}
			}
		}
		return {
			para: b,
			width: this.options.width,
			height: this.options.height
		}
	},
	reload: function() {
		if (!this.options.width || !this.options.height) {
			return
		}
		var f = this.getDependence();
		if (FR.equals(this.oldDependence, f)) {
			return
		}
		this.oldDependence = f;
		var c = this;
		var a = {};
		for (var b in f.para) {
			var e = f.para[b];
			if (b.startWith("$")) {
				b = b.substring(1)
			}
			a[b] = e
		}
		var d = FR.buildServletUrl({
			op: "fr_form",
			cmd: "form_getsource",
			sessionID: c.options.form.sessionID,
			__chartsourcename__: c.options.widgetName,
			__chartsize__: {
				width: c.options.width,
				height: c.options.height
			}
		});
		FR.ajax({
			url: d,
			type: "POST",
			async: false,
			data: {
				__parameters__: a
			},
			complete: function(h, g) {
				if (!h.responseText) {
					return
				}
				if (g == "success") {
					c.chart = FR.jsonDecode(h.responseText);
					c.addChartComp()
				}
			}
		})
	},
	addChartComp: function() {
		if (!this.chart) {
			return
		}
		this.element.empty();
		FR.createWidget(this.chart).element.appendTo(this.element)
	},
	reset: function() {
		this.reload()
	},
	doResize: function(b) {
		if (this.options.width == b.width && this.options.height == b.height) {
			return
		}
		var c = new Date();
		this.lastResizeTime = c;
		var a = this;
		setTimeout(function() {
			if (c == a.lastResizeTime) {
				delete a.lastResizeTime;
				if (b.width && b.height) {
					a.options.width = b.width;
					a.options.height = b.height;
					a.element.width(b.width);
					a.element.height(b.height);
					a.reload()
				}
			}
		},
		500)
	}
});
$.shortcut("chartwidget", FR.ChartWidget);
FR.SimpleChart = FR.extend(FR.Widget, {
	type: "simplechart",
	imgClass: "x-chart-img",
	topmenuClass: "x-chart-topmenu",
	topmenuitemClass: "x-chart-topmenuitem",
	chartlistClass: "x-chart-menu",
	menuitemClass: "x-chart-menuitem",
	menuitemHoverClass: "x-chart-menuitem-hover",
	menuitemVisitedClass: "x-chart-menuitem-visited",
	menuitemCurrentClass: "x-chart-menuitem-current",
	menuitemCurrentClass_msie6: "x-chart-menuitem-current-msie6",
	_init: function() {
		FR.SimpleChart.superclass._init.apply(this, arguments);
		if (this.element.parent().is(":hidden")) {
			return
		}
		var d = this.options;
		this.curChart = null;
		this.chartArray = [];
		this.width = d.chartWidth || 0;
		this.height = d.chartHeight || 0;
		var a = d.selectedIndex == undefined ? 0 : d.selectedIndex;
		this.$imgbox = $("<div></div>").css("position", "relative").css("width", this.width).css("height", this.height).appendTo(this.element);
		var b = this;
		var e = $.browser.msie && $.browser.version == "6.0";
		this.$topmenu = $("<div></div>").addClass(this.topmenuClass).css("height", 22).css("display", "none");
		this.$installdiv = $("<div></div>").addClass("x-chart-chrome-frame").css("position", "absolute").css("z-index", 20).css("display", "none");
		if (d && d.items && d.items.length > 1) {
			this.$topmenu.appendTo(this.$imgbox)
		}
		var c = $.browser.msie && parseInt($.browser.version) < 9;
		this.$topmenuitem = $("<div></div>").addClass(this.topmenuitemClass).css("height", $.browser.msie ? 22 : 20).css("width", $.browser.msie ? 100 : 80).appendTo(this.$topmenu);
		this.$topmenutext = $("<a>" + FR.i18n.Choose + FR.i18n.Chart + "</a>").addClass(e ? "x-chart-topmenuitem-a-msie6": "x-chart-topmenuitem-a").appendTo(this.$topmenuitem);
		this.$topmenuitem.mouseover(function() {
			if (!b.$topmenuitem.hasClass(b.menuitemVisitedClass)) {
				b.$topmenuitem.addClass(b.menuitemHoverClass);
				if (!e) {
					b.$topmenutext.addClass("x-chart-topmenuitem-a-hover")
				}
			}
		});
		this.$topmenuitem.mouseout(function() {
			if (!b.$topmenuitem.hasClass(b.menuitemVisitedClass)) {
				b.$topmenuitem.removeClass(b.menuitemHoverClass);
				if (!e) {
					b.$topmenutext.removeClass("x-chart-topmenuitem-a-hover")
				}
			}
		});
		this.$chartlistmenu = $("<div></div>").css("top", 22).css("display", "none").addClass(this.chartlistClass).appendTo(this.$imgbox);
		$.each(this.options.items,
		function(f, j) {
			var k = e ? j.name + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp": j.name;
			var g = $("<div></div>").addClass(b.menuitemClass).appendTo(b.$chartlistmenu);
			var h = $("<div>" + k + "</div>").addClass("x-chart-menuitem-text").css("padding", e ? "5px 5px 2px 24px": "2px 80px 2px 24px").css("padding-top", $.browser.msie ? "5px": "2px").appendTo(g);
			g.click(function() {
				b.changeChartImage(f);
				b.$chartlistmenu.hide();
				b.$topmenuitem.removeClass(b.menuitemVisitedClass);
				if (!e) {
					b.$topmenutext.removeClass("x-chart-topmenuitem-a-visited")
				}
				if (e) {
					$($("." + b.menuitemCurrentClass_msie6, b.$chartlistmenu)[0]).removeClass(b.menuitemCurrentClass_msie6);
					h.addClass(b.menuitemCurrentClass_msie6)
				} else {
					$($("." + b.menuitemCurrentClass, b.$chartlistmenu)[0]).removeClass(b.menuitemCurrentClass);
					h.addClass(b.menuitemCurrentClass)
				}
			}.createDelegate(b));
			g.mouseover(function() {
				g.addClass(b.menuitemHoverClass)
			});
			g.mouseout(function() {
				g.removeClass(b.menuitemHoverClass)
			})
		});
		this.$topmenuitem.click(function() {
			if (b.$chartlistmenu.is(":hidden")) {
				b.$chartlistmenu.show();
				b.$topmenuitem.addClass(b.menuitemVisitedClass);
				b.$topmenuitem.removeClass(b.menuitemHoverClass);
				if (!e) {
					b.$topmenutext.addClass("x-chart-topmenuitem-a-visited");
					b.$topmenutext.removeClass("x-chart-topmenuitem-a-hover")
				}
			} else {
				b.$chartlistmenu.hide();
				b.$topmenuitem.removeClass(b.menuitemVisitedClass);
				if (!e) {
					b.$topmenutext.removeClass("x-chart-topmenuitem-a-visited")
				}
			}
		});
		this.$chartlistmenu.mouseover(function() {
			b.$topmenu.show();
			b.$chartlistmenu.show();
			b.$topmenuitem.addClass(b.menuitemVisitedClass);
			b.$topmenuitem.removeClass(b.menuitemHoverClass);
			if (!e) {
				b.$topmenutext.addClass("x-chart-topmenuitem-a-visited");
				b.$topmenutext.removeClass("x-chart-topmenuitem-a-hover")
			}
		});
		this.$imgbox.mouseover(function() {
			b.$topmenu.show();
			b.$installdiv.show()
		});
		this.$imgbox.mouseout(function(g) {
			var f = g || window.event;
			if (!b.$chartlistmenu.is(f.relatedTarget) && !b.$topmenuitem.is(f.relatedTarget)) {
				b.$topmenu.hide();
				b.$installdiv.hide();
				b.$chartlistmenu.hide();
				b.$topmenuitem.removeClass(b.menuitemVisitedClass);
				if (!e) {
					b.$topmenutext.removeClass("x-chart-topmenuitem-a-visited")
				}
			}
		});
		$($(".x-chart-menuitem-text", b.$chartlistmenu)[a]).addClass(e ? b.menuitemCurrentClass_msie6: b.menuitemCurrentClass);
		FR._chart_Install(this, this.options.chartpainter_id_web_change_selected);
		this.changeChartImage(a)
	},
	resize: function(c, a) {
		this.width = c || this.width;
		this.height = a || this.height;
		this.$imgbox.css("width", this.width).css("height", this.height);
		if ($.browser.msie && $.browser.version < "9.0") {
			this.$installdiv.css("left", this.width - this.$installdiv.width() - 5)
		}
		if (this.curChart.chartType == "div" || this.curChart.chartType == "iframe") {
			if (this.curChart.chartWidth != this.width || this.curChart.chartHeight != this.height) {
				var b = FR._chart_GetID_Index(this.options.items[idx].simpleChartInShowID);
				var d = FR.ChartManager[b[0]];
				d[idx].resize(this.width, this.height);
				this.curChart.chartWidth = this.width;
				this.curChart.chartHeight = this.height
			}
			FR.chart_Refresh("Chart__" + this.options.items[idx].simpleChartInShowID)
		} else {
			if (this.curChart.chartWidth != this.width || this.curChart.chartHeight != this.height) {
				this.curChart.css("width", this.width).css("height", this.height);
				this.curChart.chartWidth = this.width;
				this.curChart.chartHeight = this.height
			}
		}
	},
	changeChartImage: function(l) {
		if (l < 0 || l >= this.options.items.length) {
			return
		}
		var j = FR.SessionMgr.getSessionID();
		$.ajax({
			type: "POST",
			url: FR.servletURL,
			data: {
				op: "chart",
				cmd: "change_selected",
				selectedValue: l,
				sessionID: j,
				chartpainter_id_web_change_selected: this.options.chartpainter_id_web_change_selected
			},
			async: false,
			complete: function(o) {}
		});
		if (!this.chartArray[l]) {
			var d = this.options.items[l].name;
			var e = this.options.items[l].url;
			var f = "Chart__" + this.options.items[l].simpleChartInShowID;
			var h = this.options.items[l].isJS;
			if (h) {
				var g = this.width;
				var m = this.height;
				if ($.browser.msie && parseInt($.browser.version) < 9) {
					var n = $("<div>");
					this.chartArray[l] = $("<iframe src='" + e + "'/>").css("width", g + "px").css("height", m + "px").attr("scrolling", "no").attr("frameborder", "0").attr("marginheight", "0").attr("marginwidth", "0").attr("name", f).attr("idx", l).appendTo(n);
					this.$imgbox.append(n.html());
					this.chartArray[l] = $("iframe[idx='" + l + "']", this.$imgbox);
					this.chartArray[l].chartType = "iframe"
				} else {
					var c = $("<div>");
					c.appendTo(this.$imgbox);
					c.__load__({
						url: e,
						scripts: true,
						params: {
							chartWidth: g,
							chartHeight: m
						}
					});
					this.chartArray[l] = c;
					this.chartArray[l].chartType = "div"
				}
				this.chartArray[l].chartWidth = g;
				this.chartArray[l].chartHeight = m
			} else {
				if ($.browser.msie) {
					var n = $("<div>");
					this.chartArray[l] = $("<img src='" + e + "'/>").css("width", this.width).css("height", this.height).attr("idx", l).addClass(this.imgClass).appendTo(n);
					if ($.browser.version == "6.0") {
						FR.correctPNG(this.chartArray[l])
					}
				} else {
					this.chartArray[l] = $("<img src='" + e + "'/>").css("width", this.width).css("height", this.height).attr("idx", l).addClass(this.imgClass).appendTo(this.$imgbox)
				}
				this.chartArray[l].chartType = "img";
				this.chartArray[l].chartWidth = this.width;
				this.chartArray[l].chartHeight = this.height;
				if (this.options.items[l].usemap && this.options.items[l].mapHtml) {
					this.chartArray[l].attr("usemap", this.options.items[l].usemap);
					if ($.browser.msie) {
						n.append(this.options.items[l].mapHtml)
					} else {
						this.$imgbox.append(this.options.items[l].mapHtml)
					}
				}
				if ($.browser.msie) {
					this.$imgbox.append(n.html());
					this.chartArray[l] = $("img[idx='" + l + "']", this.$imgbox)
				}
			}
		}
		this.chartArray[l].idxNumber = l;
		if (this.curChart) {
			if (this.curChart.idxNumber == l) {
				if (this.curChart.chartType == "div" || this.curChart.chartType == "iframe") {
					if (this.curChart.chartWidth != this.width || this.curChart.chartHeight != this.height) {
						var b = FR._chart_GetID_Index(this.options.items[l].simpleChartInShowID);
						var a = FR.ChartManager[b[0]];
						a[l].resize(this.width, this.height);
						this.curChart.chartWidth = this.width;
						this.curChart.chartHeight = this.height
					}
					FR.chart_Refresh("Chart__" + this.options.items[l].simpleChartInShowID)
				}
				return
			} else {
				if (this.curChart.chartWidth != this.width || this.curChart.chartHeight != this.height) {
					this.curChart.css("width", this.width).css("height", this.height);
					this.curChart.chartWidth = this.width;
					this.curChart.chartHeight = this.height
				}
			}
		}
		var k = this.chartArray[l];
		if (this.curChart) {
			if (this.curChart.chartType == "div" || this.curChart.chartType == "iframe") {
				this.curChart.css("display", "none");
				if (k.chartType == "div" || k.chartType == "iframe") {
					k.css("z-index", 10).css("width", this.width + "px").css("height", this.height + "px").css("display", "");
					this.curChart = k;
					if (this.curChart.isNotFirstShow) {
						if (this.curChart.chartWidth != this.width || this.curChart.chartHeight != this.height) {
							var b = FR._chart_GetID_Index(this.options.items[l].simpleChartInShowID);
							var a = FR.ChartManager[b[0]];
							a[l].resize(this.width, this.height);
							this.curChart.chartWidth = this.width;
							this.curChart.chartHeight = this.height
						}
						FR.chart_Refresh("Chart__" + this.options.items[l].simpleChartInShowID)
					} else {
						this.curChart.isNotFirstShow = true
					}
				} else {
					k.css("width", this.width).css("height", this.height).css("z-index", 10).css("display", "").css({
						opacity: 0
					}).animate({
						opacity: 1
					},
					1000,
					function() {
						this.curChart.css("z-index", 5);
						this.curChart = k
					}.createDelegate(this))
				}
			} else {
				this.curChart.css("z-index", 8).css("display", "none");
				if (k.chartType == "div" || k.chartType == "iframe") {
					k.css("z-index", 10).css("width", this.width + "px").css("height", this.height + "px").css("display", "");
					this.curChart = k;
					if (this.curChart.isNotFirstShow) {
						if (this.curChart.chartWidth != this.width || this.curChart.chartHeight != this.height) {
							var b = FR._chart_GetID_Index(this.options.items[l].simpleChartInShowID);
							var a = FR.ChartManager[b[0]];
							a[l].resize(this.width, this.height);
							this.curChart.chartWidth = this.width;
							this.curChart.chartHeight = this.height
						}
						FR.chart_Refresh("Chart__" + this.options.items[l].simpleChartInShowID)
					} else {
						this.curChart.isNotFirstShow = true
					}
				} else {
					k.css("width", this.width).css("height", this.height).css("z-index", 10).css("display", "").css({
						opacity: 0
					}).animate({
						opacity: 1
					},
					1000,
					function() {
						this.curChart.css("z-index", 5);
						this.curChart = k
					}.createDelegate(this))
				}
			}
		} else {
			if (k.chartType == "div" || k.chartType == "iframe") {
				k.css("z-index", 10);
				k.isNotFirstShow = true;
				this.curChart = k
			} else {
				k.css("z-index", 10).css({
					opacity: 1
				});
				this.curChart = k
			}
		}
	}
});
$.shortcut("simplechart", FR.SimpleChart);
FR.BasePane = FR.extend(FR.Widget, {
	type: "basepane",
	zoom: 1,
	_init: function() {
		FR.BasePane.superclass._init.apply(this, arguments);
		$.extend(this, this.option || {})
	},
	autoScale: function() {
		if (window != top && this.$contentPane && this.options.autoScale) {
			var b = $(".pageContentDIV", this.$contentPane);
			if (b) {
				var a = b.height();
				var d = b.width();
				var c = $(window).height();
				var e = $(window).width();
				this.scale(c / a)
			}
		}
	},
	scale: (function() {
		var a = [0.25, 0.5, 0.75, 1, 1.2, 1.5, 2];
		return function(e) {
			if (e === "+") {
				for (var d = 0,
				b = a.length; d < b; d++) {
					if (a[d] > this.zoom) {
						return this.scale(a[d])
					}
				}
				if (d == a.length) {
					return this.scale(a[a.length - 1])
				}
			} else {
				if (e === "-") {
					for (var d = (a.length - 1); d >= 0; d--) {
						if (a[d] < this.zoom) {
							return this.scale(a[d])
						}
					}
					if (d < 0) {
						return this.scale(a[0])
					}
				} else {
					if (typeof e == "number") {
						if (this.$contentPane) {
							var c = $(".pageContentDIV", this.$contentPane);
							if ($.browser.mozilla) {
								c.css("-moz-transform", "scale(" + e + "," + e + ")");
								c.css("-moz-transform-origin", "top left")
							} else {
								if ($.browser.webkit) {
									c.css("-webkit-transform", "scale(" + e + "," + e + ")");
									c.css("-webkit-transform-origin", "top left")
								} else {
									if ($.browser.opera) {
										c.css("-o-transform", "scale(" + e + "," + e + ")");
										c.css("-o-transform-origin", "top left")
									} else {
										c.css("zoom", e)
									}
								}
							}
							this.zoom = e;
							this.fireEvent("zoomchanged")
						}
					} else {
						return this.zoom
					}
				}
			}
		}
	})(),
	loadReportPane: function(c) {
		this.fireEvent(FR.Events.INIT);
		c = c || {};
		c.param = $.extend({
			showType: 1
		},
		c.param);
		var b = [];
		if (c.param.isForm === true) {
			this.parameterEl = this._createFormParameterPane(c.param, b)
		} else {
			this.parameterEl = this._createGridParameterPane(c.param, b)
		}
		this.$contentPane = this._createContentPane(c.sheets, c.browserbg);
		b.push({
			region: "center",
			el: this.$contentPane
		});
		this._doReportPaneBorderLayout(b);
		this.initContentPane();
		if (this.parameterEl == null) {
			this.loadContentPane()
		} else {
			if (c.param.delay === false) {
				var a = this;
				this.parameterCommit()
			}
		}
		this.delay = c.param.delay
	},
	_doReportPaneBorderLayout: function(a) {
		var b = new FR.BorderLayout({
			items: a,
			renderEl: this.options.renderEl
		});
		b.doLayout()
	},
	_createContentPane: function(b, a) {
		b = b || [];
		var d = $("div.content-container");
		if (d.length != 1) {
			d = $("<div id='content-container'>").appendTo(this.element).addClass("content-container")
		}
		d.css({
			overflow: "hidden"
		});
		if (b && b.length > 1) {
			this._initSheetTabPane(d, b)
		} else {
			d.css("overflow", "auto").css("border-top", "0px")
		}
		if (a.background != null) {
			if ($.browser.msie) {
				d.css("background", a.background + " fixed");
				var c = navigator.userAgent.toLowerCase();
				if (c.indexOf("msie 6.0") == -1 && $(".parameter-container").length > 0) {
					d.css("background-position", "0px " + $(".parameter-container").height() + "px 0px 0px")
				}
			} else {
				d.css("background", a.background)
			}
		}
		return d
	},
	_initSheetTabPane: function(c, a) {
		var b = new FR.TabPane({
			items: a,
			tabPosition: "bottom",
			renderEl: c
		});
		c.data("TabPane", b)
	},
	_initParameterFormPane: function() {
		return $("<div>").appendTo(this.element).addClass("parameter-container")
	},
	_createFormParameterPane: function(h, e) {
		var d = 8;
		var j = this;
		var c = h.html;
		var f = $("<div class='parameter-container'>");
		this._setBackground(h, f);
		if (h.showType == 1) {
			e.push({
				region: "north",
				el: f,
				height: h.height + d,
				miniExpand: true
			})
		}
		var a;
		if (h.showType == 0) {
			f.css({
				position: "absolute",
				width: h.width,
				height: h.height
			}).appendTo("body");
			c.listeners = [{
				once: true,
				eventName: "submitcomplete",
				action: function() {
					FR.closeDialog()
				}
			}];
			c.renderEl = f;
			a = new FR.contentForm(c)
		} else {
			var g = $("<div class='pmeter-container'>");
			if (h.alignLocation == 1) {
				g.addClass("pmeter-container-center");
				f.css("text-align", "center")
			}
			c.renderEl = g;
			a = new FR.contentForm(c);
			var b = new FR.HorizontalLayout({
				items: [{
					el: g,
					width: h.width
				}],
				alignment: h.alignLocation == 2 ? "right": "left",
				renderEl: f
			})
		}
		if (h.showType == 0) {
			a.doLayout();
			FR.showDialog(h.paramWindowTitle, h.width + 20, h.height + 45, f)
		}
		return a
	},
	_createGridParameterPane: function(b, c) {
		if (b.html == null) {
			return null
		}
		var a = $("div.parameter-container");
		if (a.length == 0) {
			a = this._initParameterFormPane();
			this._setBackground(b, a);
			if (b.showType == 1) {
				c.push({
					region: "north",
					el: a
				})
			}
		}
		return this._createGridParaPane(b, a)
	},
	_setBackground: function(b, a) {
		var c;
		if (b.parambg != null) {
			c = b.parambg.background
		}
		if (c != null) {
			a.css("background", c)
		}
	},
	_createGridParaPane: function(d, c) {
		c.html(d.html);
		var b = c.height();
		var a = 11;
		c.css("height", b + a + "px");
		var g = {
			type: "fr_form",
			selector: "td[widget]"
		};
		var f = $(c.find("table")[0]);
		var e = this;
		if (d.showType == 0) {
			this._createPopConfig(g, c, d, f.width() + 50, f.height() + 50, f)
		} else {
			f.wrapAll($("<div>").addClass("x-table-wrapper").width(f.width()));
			c.css("text-align", "center");
			if (d.alignLocation == 0) {
				if ($.browser.msie) {
					c.css("text-align", "left")
				}
				c.children(".x-table-wrapper").css("margin", "0px auto 0px 0px")
			} else {
				if (d.alignLocation == 1) {
					if ($.browser.msie) {
						c.css("text-align", "center")
					}
					c.children(".x-table-wrapper").css("margin", "auto")
				} else {
					if (d.alignLocation == 2) {
						if ($.browser.msie) {
							c.css("text-align", "right")
						}
						c.children(".x-table-wrapper").css("margin", "0px 0px 0px auto")
					}
				}
			}
		}
		return c.asComponent(g)
	},
	_createPopConfig: function(e, d, c, f, b, a) {
		e.listeners = [{
			once: true,
			eventName: "afterinit",
			action: function() {
				FR.showDialog(c.paramWindowTitle, f, b, a);
				d.remove()
			}
		},
		{
			once: true,
			eventName: "submitcomplete",
			action: function() {
				FR.closeDialog()
			}
		}];
		return e
	},
	parameterCommit: function() {
		if ($.isFunction(this.parameterEl.formSubmit)) {
			this.parameterEl.formSubmit({
				url: FR.servletURL + "?op=fr_dialog&cmd=parameters_d&sessionID=" + _g().currentSessionID,
				callback: function() {
					_g().loadContentPane()
				}
			})
		}
	},
	makeHighlight: function(c, g) {
		var b = this;
		if (!g) {
			g = "click"
		}
		var a = $("div.content-container");
		var e = $("tr", $("div.content-container"));
		var f = function(j) {
			if (!$(j.target).is("td")) {
				return
			}
			var h = $(j.target).parent();
			if (d != null) {
				d[0].style.backgroundColor = "White";
				if (h != d) {
					h[0].style.backgroundColor = (h[0].style.backgroundColor == c ? "White": c);
					d = h;
					return
				}
				d = null;
				return
			}
			h[0].style.backgroundColor = (h[0].style.backgroundColor == c ? "White": c);
			d = h
		};
		if (g.indexOf("over") != -1) {
			var d = null;
			a.mousemove(function(h) {
				f(h)
			})
		} else {
			var d = null;
			a.click(function(h) {
				f(h)
			})
		}
	}
});
FR.PagePane = FR.extend(FR.BasePane, {
	rtype: "page"
});
$.shortcut("page", FR.PagePane);
FR.ViewPane = FR.extend(FR.BasePane, {
	rtype: "view"
});
$.shortcut("view", FR.ViewPane);
FR.AnalysisPane = FR.extend(FR.BasePane, {
	rtype: "analysis"
});
$.shortcut("analysis", FR.AnalysisPane);
FR.BIReportPane = FR.extend(FR.BasePane, {
	rtype: "bi"
});
$.shortcut("bi", FR.BIReportPane);
FR.PreviewPane = FR.extend(FR.BasePane, {
	rtype: "preview"
});
$.shortcut("preview", FR.PreviewPane);
FR.FormPane = FR.extend(FR.BasePane, {
	rtype: "form"
});
$.shortcut("form", FR.FormPane);
FR.WritePane = FR.extend(FR.BasePane, {
	rtype: "write"
});
$.shortcut("write", FR.WritePane);
FR.EditPane = FR.extend(FR.BasePane, {
	rtype: "edit"
});
$.shortcut("edit", FR.EditPane);
FR.SimpleSheetPane = FR.extend(FR.BasePane, {
	rtype: "simplesheet",
	loadReportPane: function(a) {}
});
$.shortcut("simplesheet", FR.SimpleSheetPane);
FR.Slide = FR.extend(FR.BaseEditor, {
	_defaultConfig: function() {
		return $.extend(FR.Slide.superclass._defaultConfig.apply(), {
			needClose: false,
			width: 300
		})
	},
	_init: function() {
		FR.Slide.superclass._init.apply(this, arguments);
		var a = this;
		var c = 16;
		var b = this.options;
		this.leftSlideButton = $("<div/>").addClass("fr-slideleft").appendTo(this.element).click(function() {
			a.moveLeft(30)
		}).mouseover(function() {
			a.moving = true;
			a.moveLeft(10, true)
		}).mouseout(function() {
			a.moving = false
		});
		this.slideContent = $("<div/>").addClass("fr-slidecontent").appendTo(this.element);
		this.rightSlideButton = $("<div/>").addClass("fr-slideright").appendTo(this.element).click(function() {
			a.moveRight(30)
		}).mouseover(function() {
			a.moving = true;
			a.moveRight(10, true)
		}).mouseout(function() {
			a.moving = false
		});
		this.moving = false;
		this._initData(b.items);
		if (b.width > -1) {
			this.slideContent.width(b.width - 2 * c)
		}
		if (b.height > -1) {
			this.leftSlideButton.height(b.height);
			this.rightSlideButton.height(b.height);
			this.slideContent.height(b.height);
			$(".fr-slide-item").css("line-height", b.height + "px")
		}
		this.element.css("position", "relative")
	},
	_initData: function(b) {
		var a = this;
		if (typeof b === "string") {
			var c = b.split(this.options.delimiter)
		} else {
			var c = $.makeArray(b)
		}
		this.allItems = c;
		this.slideContent.empty();
		$.each(c,
		function(d, f) {
			var e = $("<span onselectstart='return false'/>").addClass("fr-slide-item").text(f).appendTo(a.slideContent)
		})
	},
	moveLeft: function(c, d) {
		var a = this;
		var b = this.slideContent.scrollLeft();
		if (b > 0) {
			this.slideContent.animate({
				scrollLeft: b > c ? b - c: 0
			},
			0);
			if (d && this.moving !== false) {
				setTimeout(function() {
					a.moveLeft(c, true)
				},
				100)
			}
		}
	},
	moveRight: function(d, e) {
		var b = this;
		var a = b._getMaxScrollWidth();
		if (a > 0) {
			var c = b.slideContent.scrollLeft();
			b.slideContent.animate({
				scrollLeft: a > d ? c + d: c + a
			},
			0);
			if (e && this.moving !== false) {
				setTimeout(function() {
					b.moveRight(d, true)
				},
				100)
			}
		}
	},
	_getMaxScrollWidth: function() {
		var a = this.slideContent;
		var b = 0;
		$("span.fr-slide-item", a).each(function() {
			b += $(this).outerWidth() + 2
		});
		return b - a.width() - a.scrollLeft()
	},
	addItem: function(a) {
		this.allItems.push(a);
		$("<span onselectstart='return false'/>").addClass("fr-slide-item").text(a).css("line-height", this.options.height + "px").appendTo(this.slideContent)
	},
	removeItem: function(b) {
		var a = this;
		$.each(this.allItems,
		function(d, e) {
			if (b == e) {
				a.allItems.splice(d, 1);
				return false
			}
		});
		var c = $("span.fr-slide-item", this.element);
		$.each(c,
		function(d, e) {
			if (b == $(e).text()) {
				$(e).remove()
			}
		})
	},
	getValue: function() {
		return this.allItems
	},
	_setValue: function(a) {
		this._initData(a)
	}
});
FR.More = FR.extend(FR.Widget, {
	_defaultConfig: function() {
		return $.extend(FR.Slide.superclass._defaultConfig.apply(), {
			column: 5,
			row: 2,
			sonHeight: 18
		})
	},
	_init: function() {
		FR.More.superclass._init.apply(this, arguments);
		this.buttonWidth = 40;
		var a = this.options;
		this.contentTable = $("<table/>").attr({
			cellspacing: "0",
			cellpadding: "0"
		}).css({
			"text-align": "center",
			border: "1px solid #97C5E0",
			"border-collapse": "collapse"
		}).append("<tbody/>").appendTo(this.element);
		this.element.css("position", "relative");
		this._initData(a.items)
	},
	_initData: function(h) {
		var l = this;
		if (typeof h === "string") {
			var b = h.split(this.options.delimiter)
		} else {
			var b = $.makeArray(h)
		}
		$("tbody", this.contentTable).empty();
		this.moreButton && this.moreButton.remove();
		this.moreItems = [];
		this.collapse();
		var a = this.options;
		var e = a.column * a.row;
		if (b.length > e) {
			var k = true;
			var g = (a.width - this.buttonWidth - a.column - 5) / a.column
		} else {
			var g = a.width / a.column
		}
		var d = a.height / a.row;
		for (var f = 0; f < a.row; f++) {
			var j = $("<tr/>").height(d).appendTo($("tbody", this.contentTable));
			for (var c = 0; c < a.column; c++) {
				$("<td/>").width(g).appendTo(j).css("border", "1px solid #97C5E0")
			}
		}
		$.each(b,
		function(m, n) {
			if (m < e) {
				$("td:eq(" + m + ")", l.contentTable).text(n)
			}
		});
		this.showItems = b.slice(0, e);
		if (k == true) {
			if (!this.$view) {
				this.$view = $("<div/>").appendTo(FR.$view_container).height(20)
			}
			this.moreItems = b.slice(e);
			this.moreButton = $('<div/ onselectstart="return false">').addClass("fr-morebutton").text("更多").appendTo(this.element).click(function() {
				if (l.isExpanded()) {
					l.collapse()
				} else {
					l._createItemList(l.moreItems);
					l.$view.show()
				}
			})
		}
	},
	_createItemList: function(b) {
		var a = this;
		this.embellishView();
		var c = document.createDocumentFragment();
		$.each(b,
		function(d, f) {
			var e = $("<div/>").height(a.options.sonHeight).addClass("fr-combo-list-item").text(f);
			c.appendChild(e[0])
		});
		this.tH = (b.length > 10 ? 10 : b.length) * (this.options.sonHeight + 2);
		this.$view.height(this.tH);
		this.$view[0].appendChild(c);
		this.modifyPosition();
		$(document).bind("mousedown", this, this.collapseIf).bind("mousewheel", this, this.collapseIf)
	},
	collapseIf: function(c) {
		var b = c.target,
		a = c.data;
		if ((c.pageX < a.$view.offset().left || c.pageX > a.$view.offset().left + a.$view.width() || c.pageY < a.$view.offset().top || c.pageY > a.$view.offset().top + a.$view.height()) && (c.pageX < a.moreButton.offset().left || c.pageX > a.moreButton.offset().left + a.moreButton[0].offsetWidth || c.pageY < a.moreButton.offset().top || c.pageY > a.moreButton.offset().top + a.moreButton[0].offsetheight)) {
			a.collapse()
		}
	},
	embellishView: function() {
		var b = this.options;
		$(".fr-combo-list").hide();
		var a = this;
		if (!FR.$view_container) {
			FR.$view_container = $("<div class='view-container'/>").appendTo("body")
		}
		if (!this.$view) {
			this.$view = $("<div/>").appendTo(FR.$view_container).height(20)
		}
		this.$view.empty();
		this.$view.addClass("fr-combo-list").css({
			position: "absolute",
			width: "auto",
			"z-index": "999",
			"min-width": "100px"
		});
		if ($.browser.msie && $.browser.version < "8.0") {
			this.$view.width(150)
		}
		this.viewEmbellished = true
	},
	modifyPosition: function() {
		var b = parseInt(this.moreButton.offset().top);
		var a = 30;
		if (b - $(".x-toolbar").height() < document.body.clientHeight - b - a) {
			this.$view.css("top", b + a + ($.browser.mozilla ? 1 : 0))
		} else {
			this.$view.css("top", b - this.tH)
		}
		this.$view.css("left", parseInt(this.moreButton.offset().left))
	},
	isExpanded: function() {
		return this.$view && this.$view.is(":visible")
	},
	collapse: function() {
		if (!this.isExpanded()) {
			return
		}
		this.$view.hide()
	},
	removeElement: function(b) {
		var a = this;
		var c = false;
		$.each(this.showItems,
		function(d, e) {
			if (e == b) {
				c = true;
				a.showItems.splice(d, 1);
				a._initData(a.showItems.concat(a.moreItems));
				return false
			}
		});
		if (c) {
			return
		}
		$.each(this.moreItems,
		function(d, e) {
			if (e == b) {
				c = true;
				a.moreItems.splice(d, 1);
				a.collapse();
				if (a.moreItems.length == 0) {
					a._initData(a.showItems)
				}
				return false
			}
		})
	},
	addElement: function(a) {
		this._initData(this.showItems.concat(this.moreItems.concat(a)))
	}
});
FR.ComboBoxEditor = FR.extend(FR.BaseComboBoxEditor, {
	_defaultConfig: function() {
		return $.extend(FR.ComboBoxEditor.superclass._defaultConfig.apply(), {
			name4Empty: FR.i18n.Choose_None,
			maxCount: 10,
			sonHeight: 18,
			value: "",
			limitData: 500
		})
	},
	_init: function() {
		FR.ComboBoxEditor.superclass._init.apply(this, arguments);
		this._dealSearch();
		var d = this.options;
		var a = this;
		var b = this.element.width();
		if (!this.$view) {
			this.$view = $("<div/>").appendTo(FR.$view_container).height(20)
		}
		this.$view.hide();
		this.shouldReBuildList();
		if (this.options.data && this.options.data.isLocalSource()) {
			var c = this.options.data.getRecords();
			this._setItems(c);
			this.checkViewScroll(c);
			this.options.need2BuildList = false
		}
		FR.applyStyles(this.editComp, d.style)
	},
	_dealSearch: function() {
		var a = this,
		b = this.options;
		this.editComp.keyup(function(c) {
			if (!FR.isNavKeyPress(c)) {
				setTimeout(function() {
					if (a.searchText == a.editComp.val() && c.keyCode != $.ui.keyCode.BACKSPACE) {
						if (!a.isExpanded()) {
							a.expand()
						}
						return
					}
					a.searchText = a.editComp.val();
					a._ajaxSearch();
					a._makeSureInputValueInResList();
					a.fireEvent(FR.Events.AFTEREDIT)
				},
				b.searchTime)
			}
		})
	},
	_ajaxSearch: function() {
		this.shouldReBuildList();
		this._createVieList()
	},
	_makeSureInputValueInResList: function() {
		var b = this;
		var a = b._getViewList();
		if (a.length != 0) {
			if (b.searchText == "") {
				b.inList = undefined
			} else {
				for (var c = 0; c < a.length; c++) {
					if (a.eq(c).text() == b.searchText) {
						b.inList = true;
						break
					}
					b.inList = false
				}
			}
		} else {
			b.inList = false
		}
	},
	rebuild: function(a) {
		this.options.data = null;
		this.options.widgetUrl = null;
		this.element.empty();
		this.$view.empty();
		this.options.items = a;
		this.options.need2BuildList = true;
		if (a.widgetUrl) {
			this.options.widgetUrl = a.widgetUrl
		}
		this._init()
	},
	_getViewList: function() {
		return this.$view.children(".fr-combo-list-item")
	},
	setSelectedIndex: function(a) {
		this._setSelectedIndex(a);
		this._onEnterPressed()
	},
	getData: function() {
		var f = this.getText();
		var e = f,
		b = this.options.data.getLoadedRecords(),
		c = this._getSelectedIndex();
		if (c > -1 && c < b.length && b[c].getShowValue() == f) {
			return b[c].getContent()
		} else {
			for (var d = 0,
			a = b.length; d < a; d++) {
				if (b[d].getShowValue() == f) {
					e = b[d].getContent();
					if (this.$view) {
						this._setSelectedIndex(d)
					}
					break
				}
			}
			return e
		}
	},
	_onKeyDown: function(b) {
		var a = b.data;
		switch (b.keyCode) {
		case $.ui.keyCode.DOWN:
			if (!a.isExpanded()) {
				a.onTriggerClick()
			} else {
				if (a.focusIndex == undefined) {
					a.focusIndex = a._getSelectedIndex() || 0
				}
				if (a.focusIndex < (a.considerAllowBlankLength() - 1)) {
					a._setItemFocus(a.focusIndex + 1, a.focusIndex);
					a.focusIndex = a.focusIndex + 1
				} else {
					a._setItemFocus(0, a.focusIndex);
					a.focusIndex = 0
				}
			}
			b.stopEvent();
			break;
		case $.ui.keyCode.UP:
			if (a.isExpanded()) {
				if (a.focusIndex == undefined) {
					a.focusIndex = a._getSelectedIndex()
				}
				if (a.focusIndex - 1 >= 0) {
					a._setItemFocus(a.focusIndex - 1, a.focusIndex);
					a.focusIndex = a.focusIndex - 1
				} else {
					a._setItemFocus(a.considerAllowBlankLength() - 1, a.focusIndex);
					a.focusIndex = a.considerAllowBlankLength() - 1
				}
				b.stopEvent()
			}
			break;
		case $.ui.keyCode.ENTER:
		case $.ui.keyCode.TAB:
			if (a.isExpanded()) {
				if (a.focusIndex != undefined) {
					a._setSelectedIndex(a.focusIndex)
				} else {
					a.collapse();
					a.editComp.blur();
					b.stopEvent();
					break
				}
				a._onEnterPressed();
				if (a.editComp.val() == "" && a.options.allowBlank !== false) {
					a.inList = true
				}
				b.stopEvent()
			}
			break
		}
	},
	onTriggerClick: function() {
		if (this.fireEvent(FR.Events.CLICK) === false) {
			return false
		}
		if (!this.isEnabled()) {
			return
		}
		if (this.isExpanded()) {
			this.collapse()
		} else {
			this._createVieList()
		}
	},
	_createVieList: function(b) {
		this.embellishView();
		if (this.options.need2BuildList) {
			this.$view.empty();
			if (b !== false) {
				this.$view.height(30).show();
				this.modifyPosition()
			}
			this.$view.__loadingMoreData__(true);
			this.options.data.resetStatus(this.allPara.apply(this, arguments));
			var c = this.options.data.getData();
			this.checkViewScroll(c);
			this._setItems(c);
			this._setSelectedIndex( - 1);
			this.$view.hide();
			this.$view.__loadingMoreData__(false);
			this.options.need2BuildList = false
		}
		if (this.$view.width() < this.element.width()) {
			var a = this.element.width();
			this.$view.width((!$.browser.mozilla) ? a: a - 2)
		}
		if ($.browser.msie && $.browser.version < "8.0" && this.$view.width() < 120) {
			this.$view.width(120)
		}
		if (b !== false) {
			this.expand();
			this.modifyPosition()
		}
	},
	scrollLoadMoreData: function() {
		if (this.isLoadingData) {
			return
		}
		this.isLoadingData = true;
		this.$view.__loadingMoreData__(true);
		this.options.data.resetStatusKeepData(this.allPara(this.options.data.getLength()));
		var a = this.options.data.appendData();
		this.checkViewScroll(a);
		this.addData2View(a);
		this.isLoadingData = false
	},
	addData2View: function(g) {
		if (this.$view) {
			if (!this.emptyContent) {
				var d = this.checkEmptyContent()
			} else {
				this.initContent(this.emptyContent, this.options.name4Empty, -1)
			}
			var e = this;
			var h = document.createDocumentFragment();
			var b = this.options.data.getLength();
			for (var f = b - g.length; f < b; f++) {
				var c = f + g.length - b;
				var a = $("<div/>");
				h.appendChild(a[0]);
				this.initContent(a, g[c].getShowValue(), f)
			}
			this.$view[0].appendChild(h);
			this.$view.append(d || this.emptyContent);
			this.$view.__loadingMoreData__(false)
		}
	},
	initContent: function(a, d, c) {
		if (d == null) {
			d = ""
		}
		var b = this;
		a.height(this.options.sonHeight).attr("title", d).text(d).addClass(c > -1 ? "fr-combo-list-item": "fr-combo-list-item fr-combo-list-item-noselect").hover(function() {
			$(this).addClass("fr-combo-selected")
		},
		function() {
			if (b.$view[0].style.display != "none") {
				$(this).removeClass("fr-combo-selected")
			}
		}).click(function(f) {
			b._setSelectedIndex(c > -1 ? c: b.getItemsLength());
			b._onEnterPressed()
		})
	},
	checkEmptyContent: function() {
		if (this.options.allowBlank === false) {
			return
		}
		this.emptyContent = $("<div/>");
		this.initContent(this.emptyContent, this.options.name4Empty, -1);
		return this.emptyContent
	},
	checkViewScroll: function(a) {
		this.$view.need2Scroll = this.options.limitData <= a.length
	},
	embellishView: function() {
		if (this.viewEmbellished) {
			return
		}
		var c = this.options;
		$(".fr-combo-list").hide();
		var a = this;
		var b = this.element.width();
		this.$view.addClass("fr-combo-list").css({
			position: "absolute",
			width: "auto",
			"z-index": "999"
		}).scroll(function() {
			if (a.$view.need2Scroll && $(this).scrollTop() + $(this).height() >= this.scrollHeight) {
				a.scrollLoadMoreData()
			}
		});
		this.viewEmbellished = true
	},
	_setItems: function(a) {
		var b = this.options.sonHeight,
		d = this.considerAllowBlankLength();
		var c = this.options.maxCount || 10;
		this.tH = d > c ? (b + 2) * c: (b + 2) * d;
		this.$view.height(this.tH);
		this.addData2View(a)
	},
	isExpanded: function() {
		return this.$view && this.$view.is(":visible")
	},
	collapseIf: function(c) {
		var b = c.target,
		a = c.data;
		if ((c.pageX < a.$view.offset().left || c.pageX > a.$view.offset().left + a.$view.width() || c.pageY < a.$view.offset().top || c.pageY > a.$view.offset().top + a.$view.height()) && (c.pageX < a.btn.offset().left || c.pageX > a.btn.offset().left + a.btn[0].offsetWidth || c.pageY < a.btn.offset().top || c.pageY > a.btn.offset().top + a.btn[0].offsetheight)) {
			a.collapse()
		}
	},
	expand: function() {
		if (this.isExpanded()) {
			return
		}
		this._showView();
		this._setSelectedIndex( - 1);
		$(document).bind("mousedown", this, this.collapseIf).bind("mousewheel", this, this.collapseIf);
		if (document.activeElement != this.editComp[0]) {
			this.editComp.focus()
		}
		this.fireEvent(FR.Events.EXPAND)
	},
	_showView: function() {
		this.$view.show()
	},
	_setSelectedIndex: function(a) {
		if (this.selectedItem) {
			this.selectedItem.removeClass("fr-combo-selected")
		}
		this.selectedItem = this.$view.children(".fr-combo-list-item:eq(" + a + ")");
		this.selectedIndex = a;
		if (a >= 0) {
			this.selectedItem.addClass("fr-combo-selected").__scroll2View__(this.$view, false)
		}
	},
	_setItemFocus: function(a, b) {
		var c = this.$view.children(".fr-combo-list-item");
		$(c[b]).removeClass("fr-combo-selected");
		$(c[a]).addClass("fr-combo-selected").__scroll2View__(this.$view, false)
	},
	collapse: function() {
		if (!this.isExpanded()) {
			return
		}
		this.$view.hide();
		$(document).unbind("mousedown", this.collapseIf).unbind("mousewheel", this.collapseIf);
		this.editComp.focus();
		this.focusIndex = undefined;
		this.fireEvent(FR.Events.COLLAPSE)
	},
	modifyPosition: function() {
		var b = parseInt(this.editComp.offset().top);
		var a = parseInt(this.options.height);
		if (b - $(".x-toolbar").height() < document.body.clientHeight - b - a) {
			this.$view.css("top", b + a + ($.browser.mozilla ? 1 : 0))
		} else {
			this.$view.css("top", b - this.tH)
		}
		this.$view.css("left", parseInt(this.editComp.offset().left))
	}
});
$.shortcut("combo", FR.ComboBoxEditor);
FR.CheckBoxEditor = FR.extend(FR.ComboBoxEditor, {
	_defaultConfig: function() {
		return $.extend(FR.CheckBoxEditor.superclass._defaultConfig.apply(), {
			delimiter: ",",
			startSymbol: "",
			endSymbol: ""
		})
	},
	_init: function() {
		FR.CheckBoxEditor.superclass._init.apply(this, arguments);
		this.$view.addClass("fr-checkbox-list");
		this.initCheckBoxContainer();
		this.options.delimiter = this.options.delimiter.replace(/\\r/g, "\n")
	},
	initCheckBoxContainer: function() {
		if (!this.ck_el_array) {
			this.ck_el_array = []
		}
	},
	_onKeyDown: function(c) {
		FR.CheckBoxEditor.superclass._onKeyDown.apply(this, arguments);
		var b = c.keyCode;
		var a = c.data;
		if (a.isExpanded() && b == $.ui.keyCode.SPACE && a._getSelectedIndex() >= 0) {
			a.ck_el_array[a._getSelectedIndex()].selected(!a.ck_el_array[a._getSelectedIndex()].selected());
			c.stopEvent()
		}
	},
	_setItems: function(c) {
		var b = c || [];
		var d = this.options.sonHeight;
		var f = this.options.maxCount || 10;
		var e = b.length > f ? (d + 2) * f: (d + 2) * b.length;
		this.$view.height(e + d);
		this.tH = e + d;
		this.modifyPosition();
		var a = this;
		this.$controlPane = $("<div style='display:inline padding-right:20px'>").addClass("fr-checkbox-control");
		this.innerCheckBox = new FR.CheckBox({
			renderEl: $("<div/>").appendTo(a.$controlPane),
			text: FR.i18n.Choose_All + "/" + FR.i18n.Deselect_All
		});
		this.innerCheckBox.on(FR.Events.CLICK,
		function() {
			if (this.selected()) {
				a.doSelectAll()
			} else {
				a.deSelectAll()
			}
			a.editComp.focus()
		});
		this.$view.append(this.$controlPane);
		this.ck_el_array = [];
		this.addData2View(b)
	},
	addData2View: function(d) {
		var j = this;
		var b = this.ck_el_array.length;
		for (var f = b,
		h = b + d.length; f < h; f++) {
			var e = d[f - b];
			var a = $("<div/>").height(this.options.sonHeight).attr("title", e.getShowValue()).addClass("fr-combo-list-item").appendTo(this.$view);
			this.ck_el_array[f] = new FR.CheckBox({
				renderEl: $("<div/>").appendTo(a),
				text: e.getShowValue()
			});
			this.ck_el_array[f].on(FR.Events.STATECHANGE,
			function() {
				if (!this.selected()) {
					j.innerCheckBox.selected(false)
				} else {
					var i = $(".fr-checkbox-checkon", j.$view);
					if (i.length == j.ck_el_array.length) {
						j.innerCheckBox.selected(true)
					}
				}
				j._refreshComponentValue(this);
				j.editComp.focus()
			})
		}
		var g = this.editComp.val().split(this.options.delimiter);
		if (!FR.isEmptyArray(g)) {
			$.each(this.ck_el_array,
			function(i, k) {
				if ($.inArray(k.options.text, g) > -1) {
					k.changeBoxState(true)
				} else {
					if (k.isSelected()) {
						k.changeBoxState(false)
					}
				}
			})
		}
		var c = $(".fr-checkbox-checkon", this.$view);
		if (c.length == this.ck_el_array.length) {
			this.innerCheckBox.selected(true)
		}
		this.$view.__loadingMoreData__(false)
	},
	_onEnterPressed: function() {
		this._refreshComponentValue();
		this.collapse();
		this.fireEvent(FR.Events.AFTEREDIT)
	},
	_refreshComponentValue: function(b) {
		var a = $.map($.grep(this.ck_el_array,
		function(c) {
			return c.selected()
		}),
		function(c) {
			return c.element.text()
		}).join(this.options.delimiter);
		this.editComp.val(a);
		this.fireEvent(FR.Events.AFTEREDIT)
	},
	doSelectAll: function() {
		var a = [];
		$.each(this.options.data.getRecords(),
		function(c, d) {
			a.push(d.getShowValue())
		});
		var b = this;
		this.editComp.val(a.join(this.options.delimiter));
		$.each(this.ck_el_array,
		function(c, d) {
			d.setSelectedWithoutEvent(true)
		});
		b.fireEvent(FR.Events.AFTEREDIT)
	},
	deSelectAll: function() {
		this.editComp.val("");
		var a = this;
		$.each(this.ck_el_array,
		function(b, c) {
			c.setSelectedWithoutEvent(false)
		});
		a.fireEvent(FR.Events.AFTEREDIT)
	},
	_setValue: function(d) {
		var b = this.getValue();
		if (typeof d === "string") {
			var c = d.split(this.options.delimiter)
		} else {
			var c = $.makeArray(d)
		}
		var a = this.options.data.getRecords();
		if (a.length > 0) {
			$.each(c,
			function(f, g) {
				for (var h = 0,
				e = a.length; h < e; h++) {
					if (a[h].getValue() == g) {
						c[f] = a[h].getShowValue();
						break
					}
				}
			})
		}
		this.editComp.val(c.join(this.options.delimiter));
		this.options.value = d;
		this.fireEvent(FR.Events.CHANGE, d, b)
	},
	getValue: function() {
		var c = this.editComp.val() ? this.editComp.val().split(this.options.delimiter) : [];
		var a = this.options.data.getLoadedRecords();
		if (FR.isArray(a) && a.length > 0) {
			$.each(c,
			function(e, g) {
				for (var f = 0,
				d = a.length; f < d; f++) {
					if (a[f].getShowValue() == g) {
						c[e] = a[f].getValue();
						break
					}
				}
				if (c[e] == "") {
					c.splice(e, 1)
				}
			})
		}
		if (this.options.returnArray) {
			return c
		}
		var b = this.options.startSymbol + c.join(this.options.delimiter) + this.options.endSymbol;
		return b
	},
	isValidate: function() {
		var a = this.options.allowBlank != false;
		if (!a && !this.editComp.val()) {
			this.errorMsg = this.options.errorMsg || FR.i18n.NOT_NULL;
			return false
		}
		return true
	},
	reset: function() {
		this.ck_el_array = [];
		this.setValue("");
		this.shouldReBuildList()
	}
});
$.shortcut("combocheckbox", FR.CheckBoxEditor);
FR.TreeComboBoxEditor = FR.extend(FR.CheckBoxEditor, {
	_defaultConfig: function() {
		return $.extend(FR.TreeComboBoxEditor.superclass._defaultConfig.apply(), {
			width: 120,
			height: 300,
			returnArray: true,
			delimiter: ";",
			startSymbol: "",
			endSymbol: "",
			cascadecheck: true,
			limitData: 300
		})
	},
	_init: function() {
		FR.TreeComboBoxEditor.superclass._init.apply(this, arguments);
		this.$view.attr("id", this.options.location)
	},
	initData: function() {
		if (this.options.data) {
			return
		}
		if (this.options.rootLoader) {
			this.options.data = new FR.TreeData({
				treeLoader: this.options.rootLoader
			})
		} else {
			if (this.options.widgetUrl) {
				this.options.data = new FR.TreeData({
					url: this.options.widgetUrl
				})
			}
		}
		if (this.options.controlAttr) {
			this.setSource(this.options.controlAttr);
			delete this.options.controlAttr
		}
	},
	_setItems: function(e) {
		var i = this;
		this.tH = 150;
		this.$view.height(this.tH);
		var a = FR.createTreeConfig4Widget(this);
		var f = this.editComp.val().split(this.options.delimiter);
		if (!FR.isEmptyArray(f)) {
			var b = this.options.data.getData();
			if (i.options.mutiSelection) {
				var g = function(j, l) {
					for (var k = 0; k < j.length; k++) {
						j[k].setcheckstate(l);
						if (j[k].isLoadComplete() && j[k].getChildrenLength() > 0) {
							g(j[k].getChildren(), l)
						}
					}
				};
				var h = function(l, k, p) {
					for (var o = 0; o < l.length; o++) {
						if (l[o].getShowValue() == p[k]) {
							var q = l[o].getChildren();
							if (k == p.length - 1) {
								l[o].setcheckstate(1);
								g(q, 1);
								return true
							}
							if (h(q, k + 1, p)) {
								var n = true;
								for (var m = 0; m < q.length; m++) {
									if (q[m].getcheckstate() != 1) {
										n = false;
										break
									}
								}
								l[o].setcheckstate(n ? 1 : 2);
								return true
							}
						}
					}
				};
				g(b, 0);
				for (var c = 0; c < f.length; c++) {
					h(b, 0, f[c].split(","))
				}
			} else {
				var d = function(k, j, m) {
					for (var l = 0; l < k.length; l++) {
						if (k[l].getShowValue() == m[j]) {
							if (j == m.length - 1) {
								a.currentnode = k[l]
							}
							d(k[l].getChildren(), j + 1, m)
						}
					}
				};
				d(b, 0, f)
			}
		}
		this.$view.treeview(a);
		this.treeBuilded = true
	},
	checkViewScroll: function() {
		return
	},
	initFilterData: function() {
		if (!this.options.filterData) {
			this.options.data = new FR.TreeData({
				url: this.options.widgetUrl
			})
		} else {
			this.options.data = this.options.filterData
		}
	},
	_ajaxSearch: function() {
		if (this.searchText || this.searchText === 0) {
			this.initFilterData()
		} else {
			this.initData()
		}
		this.shouldReBuildList();
		this._createVieList()
	},
	_onEnterPressed: function() {
		if (!this.options.mutiSelection) {
			this.editComp.val(this.$view.getTCPText());
			this.value = this.$view.getTCPValue();
			this.collapse()
		} else {
			var a = this.$view.getTCPTexts();
			if (FR.isArray(a)) {
				a = a.join(this.options.delimiter)
			}
			this.editComp.val(a);
			this.value = this.$view.getTCPValues()
		}
		this.fireEvent(FR.Events.AFTEREDIT)
	},
	getSelectedNodes: function() {
		this.selectedNodes = this.getValueNodes();
		this.fireEvent(FR.Events.DEALSELECTEDNODES);
		return this.selectedNodes
	},
	getValueNodes: function() {
		return this.$view.getTSNs()
	},
	_setValue: function(g) {
		var c = this.getValue();
		if (typeof g === "string") {
			var e = g.split(this.options.delimiter)
		} else {
			var e = $.makeArray(g)
		}
		var d = function(j, h, l) {
			for (var k = 0; k < j.length; k++) {
				if (j[k].getValue() == l[h]) {
					if (h == l.length - 1) {
						b.push(j[k].getPathAsTextString());
						f.push(l)
					}
					j = j[k].getChildren();
					d(j, h + 1, l);
					return
				}
			}
		};
		var b = [];
		var f = [];
		if (!this.options.mutiSelection) {
			d(this.options.data.getData(), 0, e)
		} else {
			for (var a = 0; a < e.length; a++) {
				d(this.options.data.getData(), 0, $.isArray(e[a]) ? e[a] : e[a].split(","))
			}
		}
		if (b.length != 0) {
			this.editComp.val(b.join(this.options.delimiter));
			this.value = this.options.mutiSelection ? f: f[0]
		} else {
			this.editComp.val(g);
			this.value = e
		}
		leafNodes = nodes = null;
		if (c != g) {
			this.options.need2BuildList = true
		}
	},
	getValue: function() {
		if (this.value) {
			return this.value
		}
		if (this.editComp.val() == "") {
			return this.options.mutiSelection ? [] : ""
		}
		if (this.options.need2BuildList) {
			this._createVieList(false)
		}
		if (!this.options.mutiSelection) {
			this.value = this.$view.getTCPValue() || this.editComp.val().split(this.options.delimiter)
		} else {
			this.value = this.$view.getTCPValues() || this.editComp.val().split(this.options.delimiter)
		}
		return this.value
	},
	reset: function() {
		this.setValue("");
		this.shouldReBuildList()
	}
});
$.shortcut("treecombobox", FR.TreeComboBoxEditor);
FR.DateTimeEditor = FR.extend(FR.BaseDateTimeEditor, {
	_defaultConfig: function() {
		return $.extend(FR.DateTimeEditor.superclass._defaultConfig.apply(), {
			format: "yyyy-MM-dd",
			directEdit: true
		})
	},
	_init: function() {
		FR.DateTimeEditor.superclass._init.apply(this, arguments);
		FR.$defaultImport("/com/fr/web/core/js/jsCal/jquery.dynDateTime.js", "js");
		FR.$defaultImport("/com/fr/web/core/js/jsCal/lang/calendar-lang.js", "js");
		this.arrow.switchClass("fr-date-trigger-center");
		if (this.options.widgetCss && this.options.widgetCss.length != 0) {
			$.each(this.options.widgetCss,
			function(d, e) {
				FR.$import(e, "css", true)
			})
		}
		var c = this.options;
		this.std = Calendar.createStartDate(c.startDate);
		this.edd = Calendar.createEndDate(c.endDate);
		var b = this;
		var a = (c.format.indexOf("H") != -1 || c.format.indexOf("h") != -1) ? true: false;
		this.btn.dynDateTime({
			inputFiled: this.editComp,
			showsTime: a,
			ifFormat: FR.parseFmt(c.format),
			startDate: c.startDate,
			endDate: c.endDate,
			disabled: c.disabled,
			onUpdate: function(d) {
				b.fireEvent(FR.Events.AFTEREDIT)
			}
		}).keydown(function(d) {
			if (!d.shiftKey) {
				Calendar.finish()
			}
		});
		this.editComp.keydown(function(d) {
			b.editComp[0].realValue = null
		})
	},
	_onKeyDown: function(a) {
		if (!a.shiftKey) {
			Calendar.finish()
		}
	},
	onTriggerClick: function(a) {
		if (!this.isEnabled()) {
			return
		}
		if (document.activeElement != this.editComp[0]) {
			this.editComp.focus()
		}
	},
	getValue: function() {
		var d = this.options.format;
		if (!this.options.returnDate) {
			var c = this.editComp.val();
			return Date.parseDate(c, FR.convertJavaDateFormat2JS(d)) == null ? "": c
		}
		var b = new Date();
		if (this.editComp[0].realValue) {
			b.setTime(this.editComp[0].realValue.getTime())
		} else {
			b = this.editComp.val()
		}
		if (!b) {
			return ""
		}
		var a = (b instanceof Date) ? b: Date.parseDate(b, FR.convertJavaDateFormat2JS(d));
		return (a == null) ? "": a
	}
});
$.shortcut("datetime", FR.DateTimeEditor);
FR.Layout = FR.extend(FR.Widget, {
	_init: function() {
		FR.Layout.superclass._init.apply(this, arguments);
		if (this.options.marginTop) {
			this.element.css("marginTop", this.options.marginTop);
			this.marginHeight = (this.marginHeight || 0) + parseInt(this.options.marginTop)
		}
		if (this.options.marginLeft) {
			this.element.css("marginLeft", this.options.marginLeft);
			this.marginWidth = (this.marginWidth || 0) + parseInt(this.options.marginLeft)
		}
		if (this.options.marginBottom) {
			this.element.css("marginBottom", this.options.marginBottom);
			this.marginHeight = (this.marginHeight || 0) + parseInt(this.options.marginBottom)
		}
		if (this.options.marginRight) {
			this.element.css("marginRight", this.options.marginRight);
			this.marginWidth = (this.marginWidth || 0) + parseInt(this.options.marginRight)
		}
		if (this.options.widgetBackground) {
			this.element.css("background", this.options.widgetBackground.background)
		}
		if (border = this.options.border) {
			this.element.css("border-style", border.type);
			this.element.css("border-color", border.color);
			this.element.css("border-width", border.width);
			if (border.corner) {
				this.element.css("border-radius", "15px 15px 15px 15px")
			}
		}
		this.element.css("overflow", "hidden");
		var a = this;
		this.element.click(function(b) {
			a.fireEvent(FR.Events.CLICK, b)
		})
	},
	doLayout: function() {
		this.element.doLayout()
	},
	setMinSize: function() {
		if (this.element[0].tagName == "BODY") {
			var a = this.element.minimumSize();
			this.element.css({
				"min-width": a.width,
				"min-height": a.height
			});
			$("html").css("overflow", "auto")
		}
	},
	addWidget2Form: function(c) {
		if (c) {
			c.add(this);
			for (var b = 0,
			a = this.options.widgets.length; b < a; b++) {
				this.options.widgets[b].addWidget2Form(c)
			}
		}
	},
	removeWidgetFromForm: function(c) {
		if (c) {
			c.remove(this);
			for (var b = 0,
			a = this.options.widgets.length; b < a; b++) {
				this.options.widgets[b].removeWidgetFromForm(c)
			}
		}
	},
	_confirmEvents: function() {}
});
FR.AbsoluteLayout = FR.extend(FR.Layout, {
	_init: function() {
		FR.AbsoluteLayout.superclass._init.apply(this, arguments);
		var g = this.options = $.extend({
			baseCls: "fr-absolutelayout"
		},
		this.options);
		this.element.addClass(g.baseCls);
		if (!$.isArray(g.items)) {
			g.items = [];
			return
		}
		var c = g.items,
		e, a = c.length;
		g.widgets = [];
		for (e = 0; e < a; e++) {
			var b = c[e];
			var f = FR.createWidget(b);
			g.widgets.push(f);
			f.doResize({
				width: b.width,
				height: b.height
			});
			var d = f.element;
			d.css({
				left: b.x,
				top: b.y,
				position: "absolute"
			});
			c[e].el = d;
			this.element.append(d)
		}
		this.element.data("jlayout", jLayout.Absolute(g))
	},
	doResize: function(b) {
		FR.AbsoluteLayout.superclass.doResize.call(this, b);
		var a = this.options;
		this.element.css({
			width: (b ? (b.width || a.width) : a.width),
			height: b ? (b.height || a.height) : a.height,
			left: b ? (b.left || a.left) : a.left,
			top: b ? (b.top || a.top) : a.top
		})
	},
	doLayout: function() {
		FR.AbsoluteLayout.superclass.doLayout.apply(this, arguments);
		for (var b = 0,
		a = this.options.widgets.length; b < a; b++) {
			var c = this.options.widgets[b];
			if ($.isFunction(c.doLayout)) {
				c.doLayout()
			}
		}
	}
});
$.shortcut("absolute", FR.AbsoluteLayout);
FR.CardLayout = FR.extend(FR.Layout, {
	_init: function() {
		FR.CardLayout.superclass._init.apply(this, arguments);
		if (!$.isArray(this.options.items)) {
			this.options.items = [];
			return
		}
		this.element.css({
			width: "100%",
			height: "100%"
		});
		this.options.widgets = [];
		this.name2index = [];
		this.indexContents = [];
		this.indexhasinit = [];
		this.element.indexEL = [];
		for (var c = 0,
		a = this.options.items.length; c < a; c++) {
			var b = this.options.items[c];
			b.renderEl = $("<div/>").appendTo(this.element);
			if (b.widgetName.startWith("_$$")) {
				b.widgetName = b.widgetName.substr(3)
			}
			this.name2index[b.widgetName] = c;
			this.indexContents[c] = b;
			this.indexhasinit[c] = false
		}
		if (this.options.defaultShowIndex >= 0 && this.indexContents.length > 0) {
			this.showPaneByIndex(this.options.defaultShowIndex)
		}
		this.element.data("jlayout", jLayout.card(this))
	},
	addWidget2Form: function(a) {
		if (a) {
			a.add(this);
			if (this.indexhasinit[this.showIndex]) {
				this.indexContents[this.showIndex].addWidget2Form(a)
			}
		}
	},
	createCard: function(b) {
		if (!this.indexhasinit[b]) {
			var c = this.options.form.createConfig2Form(this.indexContents[b], b != this.options.defaultShowIndex);
			this.indexhasinit[b] = true;
			this.options.widgets.push(c);
			this.indexContents[b] = c;
			this.element.indexEL[b] = c.element;
			c.fireEvent(FR.Events.AFTERINIT)
		}
		var a = this;
		this.createCard = function(d) {
			if (!a.indexhasinit[d]) {
				var e = this.options.form.createConfig2Form(a.indexContents[d], d != a.options.defaultShowIndex);
				a.indexhasinit[d] = true;
				a.options.widgets.push(e);
				a.indexContents[d] = e;
				a.element.indexEL[d] = e.element;
				e.fireEvent(FR.Events.AFTERINIT);
				if (a.give) {
					e.doResize({
						width: a.give.width - FR.considerBoxModelWidth(a.element),
						height: a.give.height - FR.considerBoxModelHeight(a.element)
					})
				} else {
					e.doResize({
						width: a.element.width() - FR.considerBoxModelWidth(a.element),
						height: a.element.height() - FR.considerBoxModelHeight(a.element)
					})
				}
				if ($.isFunction(e.doLayout)) {
					e.doLayout()
				}
			} else {
				a.indexContents[d].addWidget2Form(a.options.form)
			}
			return a.indexContents[d]
		};
		return this.indexContents[b]
	},
	showPaneByIndex: function(b) {
		var d = this.showIndex || this.options.defaultShowIndex;
		this.showIndex = b;
		this.element.showIndex = b;
		for (var c = 0,
		a = this.indexContents.length; c < a; c++) {
			if (this.indexhasinit[c]) {
				this.indexContents[c].setVisible(false)
			}
		}
		if (this.indexhasinit[d]) {
			this.indexContents[d].removeWidgetFromForm(this.options.form)
		}
		this.createCard(b).setVisible(true);
		this.options.form.doLayout()
	},
	showPaneByName: function(a) {
		this.showPaneByIndex(this.name2index[a.toUpperCase()])
	},
	doResize: function(c) {
		if (this.give != null && FR.equals(this.give, c)) {
			return
		}
		this.give = c;
		for (var b = 0,
		a = this.indexContents.length; b < a; b++) {
			if (this.indexhasinit[b]) {
				if (this.indexContents[b].isVisible()) {
					this.indexContents[b].doResize({
						width: c.width,
						height: c.height
					})
				}
			}
		}
	},
	doLayout: function() {
		for (var b = 0,
		a = this.indexContents.length; b < a; b++) {
			if (this.indexhasinit[b] && this.indexContents[b].doLayout) {
				this.indexContents[b].doLayout()
			}
		}
	}
});
$.shortcut("card", FR.CardLayout);
FR.TitlePane = FR.extend(FR.Widget, {
	_init: function() {
		FR.TitlePane.superclass._init.apply(this, arguments);
		this.element.asComponent({
			type: "border",
			items: [{
				region: "center",
				el: this.options.packee
			},
			{
				region: "north",
				el: $("<div>").text(this.options.title)
			}]
		})
	}
});
$.shortcut("titlepane", FR.TitlePane);
FR.BorderLayout = FR.extend(FR.Layout, {
	_init: function() {
		FR.BorderLayout.superclass._init.apply(this, arguments);
		var b = this.options = $.extend({
			baseCls: "fr-layout-border",
			fit: true
		},
		this.options);
		b.widgets = [];
		var e = {};
		this.panelContainer = [];
		var j = this.element;
		if (j[0].tagName == "BODY") {
			$("html").css({
				height: "100%"
			});
			$("body").css({
				height: "100%",
				border: "none"
			})
		}
		j.addClass(b.baseCls);
		var d = ["north", "south", "east", "west", "center"];
		for (var g = 0; g < d.length; g++) {
			var f = d[g];
			var i = f + "El";
			for (var h = 0; h < this.options.items.length; h++) {
				var c = this.options.items[h];
				if (c.region != f) {
					continue
				}
				if (this[i] != null) {
					this[i].remove()
				}
				var a = this._createPanel(c);
				b[f] = a;
				this[i] = a;
				if (a.element.parent()[0] != j[0]) {
					a.element.appendTo(j)
				}
				e[f] = this[i];
				break
			}
		}
		e.containerObj = this;
		j.data("jlayout", jLayout.border(e))
	},
	_createPanel: function(i) {
		var l = this;
		var e = this.element;
		var b = this.options;
		var f = i.region;
		var d = null;
		if (f == "north") {
			d = "fr-layout-border-button-up"
		} else {
			if (f == "south") {
				d = "fr-layout-border-button-down"
			} else {
				if (f == "east") {
					d = "fr-layout-border-button-right"
				} else {
					if (f == "west") {
						d = "fr-layout-border-button-left"
					}
				}
			}
		}
		var g = i.el;
		if (!i.height && g.height) {
			if ($.isFunction(g.height)) {
				i.height = parseInt(g.css("height"))
			} else {
				i.height = g.height
			}
		}
		if (!i.width && g.width) {
			if ($.isFunction(g.width)) {
				i.width = parseInt(g.css("width"))
			} else {
				i.width = g.width
			}
		}
		if (i.form) {
			i.el.form = i.form
		}
		var m = "fr-layout-border-panel fr-layout-border-panel-" + f;
		if (i.split == true) {
			m += " fr-layout-border-split-" + f
		}
		var h = FR.createWidget(g);
		this.options.widgets.push(h);
		var c = new FR.Panel({
			border: i.border || false,
			width: i.width,
			height: i.height,
			contentWidget: h,
			onOpen: i.onOpen,
			onClose: i.onClose,
			onResize: i.onResize,
			_resize: i._resize,
			cls: m,
			fit: false,
			title: i.title,
			collapsible: false,
			miniExpand: i.miniExpand ? {
				handler: function() {
					l._collapsePanel(i)
				}
			}: false,
			tools: [{
				iconCls: d,
				handler: function() {
					l._collapsePanel(i)
				}
			}]
		});
		if (i.split == true) {
			var a = c.element;
			var k = "";
			if (f == "north") {
				k = "s"
			}
			if (f == "south") {
				k = "n"
			}
			if (f == "east") {
				k = "w"
			}
			if (f == "west") {
				k = "e"
			}
			a.resizable({
				handles: k,
				start: function(s) {
					resizing = true;
					var o = (f == "north" || f == "south") ? $("div.fr-layout-border-split-proxy-v", e) : $("div.fr-layout-border-split-proxy-h", e);
					var r = 0,
					q = 0,
					p = 0,
					n = 0;
					var t = {
						display: "block"
					};
					if (f == "north") {
						t.top = parseInt(a.css("top")) + a.outerHeight() - o.height();
						t.left = parseInt(a.css("left"));
						t.width = a.outerWidth();
						t.height = o.height()
					} else {
						if (f == "south") {
							t.top = parseInt(a.css("top"));
							t.left = parseInt(a.css("left"));
							t.width = a.outerWidth();
							t.height = o.height()
						} else {
							if (f == "east") {
								t.top = parseInt(a.css("top")) || 0;
								t.left = parseInt(a.css("left")) || 0;
								t.width = o.width();
								t.height = a.outerHeight()
							} else {
								if (f == "west") {
									t.top = parseInt(a.css("top")) || 0;
									t.left = a.outerWidth() - o.width();
									t.width = o.width();
									t.height = a.outerHeight()
								}
							}
						}
					}
					o.css(t);
					$('<div class="fr-layout-border-mask"></div>').css({
						left: 0,
						top: 0,
						width: e.width(),
						height: e.height()
					}).appendTo(e)
				},
				resize: function(o) {
					if (f == "north" || f == "south") {
						var n = $("div.fr-layout-border-split-proxy-v", e);
						n.css("top", o.pageY - e.offset().top - n.height() / 2)
					} else {
						var n = $("div.fr-layout-border-split-proxy-h", e);
						n.css("left", o.pageX - e.offset().left - n.width() / 2)
					}
					return false
				},
				stop: function(o) {
					$("div.fr-layout-border-split-proxy-v", e).css("display", "none");
					$("div.fr-layout-border-split-proxy-h", e).css("display", "none");
					var n = c.options;
					n.width = a.outerWidth();
					n.height = a.outerHeight();
					n.left = a.css("left");
					n.top = a.css("top");
					c.doResize();
					l.doResize();
					resizing = false;
					$("div.fr-layout-border-mask", e).remove()
				}
			});
			if (i.splitSize == "mini") {
				var j = $("<div/>").addClass("x-layout-mini x-layout-mini" + f)
			}
		}
		$(e).bind("_resize",
		function(o, p) {
			var n = l.options;
			if (n.fit == true) {
				l.doResize(p)
			}
			return false
		});
		return c
	},
	_isVisible: function(a) {
		if (!a) {
			return false
		}
		return a.element.is(":visible")
	},
	_setNorthSize: function(b, a) {
		if (!a) {
			return
		}
		a.doResize({
			width: b.width(),
			height: a.options.height,
			left: 0,
			top: 0
		});
		this.cpos.top += a.options.height;
		this.cpos.height -= a.options.height
	},
	_setSouthSize: function(b, a) {
		if (!a) {
			return
		}
		a.doResize({
			width: b.width(),
			height: a.options.height,
			left: 0,
			top: b.height() - a.options.height
		});
		this.cpos.height -= a.options.height
	},
	_setEastSize: function(b, a) {
		if (!a) {
			return
		}
		a.doResize({
			width: a.options.width,
			height: this.cpos.height,
			left: b.width() - a.options.width,
			top: this.cpos.top
		});
		this.cpos.width -= a.options.width
	},
	_setWestSize: function(b, a) {
		if (!a) {
			return
		}
		a.doResize({
			width: a.options.width,
			height: this.cpos.height,
			left: 0,
			top: this.cpos.top
		});
		this.cpos.left += a.options.width;
		this.cpos.width -= a.options.width
	},
	_collapsePanel: function(d) {
		var c = this.options;
		var e = d.region;
		var f = this.element;
		var a = (d.miniExpand === true) ? 8 : 28;
		if (d.customExpand) {
			a = 0.1
		}
		if (e == "east") {
			c.center.doResize({
				width: c.center.options.width + c.east.options.width - a
			});
			c.east.element.animate({
				left: f.width()
			},
			function() {
				c.east.doClose();
				c.expandEast.doOpen();
				c.expandEast.doResize({
					top: c.east.options.top,
					left: f.width() - a,
					width: a,
					height: c.east.options.height
				})
			});
			if (!c.expandEast) {
				c.expandEast = this._createExpandPanel({
					region: "east",
					miniExpand: d.miniExpand,
					width: a
				})
			}
		} else {
			if (e == "west") {
				c.center.doResize({
					width: c.center.options.width + c.west.options.width - a,
					left: a
				});
				c.west.element.animate({
					left: -c.west.options.width
				},
				function() {
					c.west.doClose();
					c.expandWest.doOpen();
					c.expandWest.doResize({
						top: c.west.options.top,
						left: 0,
						width: a,
						height: c.west.options.height
					})
				});
				if (!c.expandWest) {
					c.expandWest = this._createExpandPanel({
						region: "west",
						miniExpand: d.miniExpand
					})
				}
			} else {
				if (e == "north") {
					var b = f.height() - a;
					if (this._isVisible(c.expandSouth)) {
						b -= c.expandSouth.options.height
					} else {
						if (this._isVisible(c.south)) {
							b -= c.south.options.height
						}
					}
					c.center.doResize({
						top: a,
						height: b
					});
					if (c.east) {
						c.east.doResize({
							top: a,
							height: b
						})
					}
					if (c.east) {
						c.west.doResize({
							top: a,
							height: b
						})
					}
					if (this._isVisible(c.expandEast)) {
						c.expandEast.doResize({
							top: a,
							height: b
						})
					}
					if (this._isVisible(c.expandWest)) {
						c.expandWest.doResize({
							top: a,
							height: b
						})
					}
					c.north.element.animate({
						top: -c.north.options.height
					},
					function() {
						c.north.doClose();
						c.expandNorth.doOpen();
						c.expandNorth.doResize({
							top: 0,
							left: 0,
							width: f.width(),
							height: a
						});
						if ($(".frozen-center").length > 0) {
							$(window).resize()
						}
					});
					if (!c.expandNorth) {
						c.expandNorth = this._createExpandPanel({
							region: "north",
							miniExpand: d.miniExpand,
							expandSize: a
						})
					}
				} else {
					if (e == "south") {
						var b = f.height() - a;
						if (this._isVisible(c.expandNorth)) {
							b -= c.expandNorth.options.height
						} else {
							if (this._isVisible(c.north)) {
								b -= c.north.options.height
							}
						}
						c.center.doResize({
							height: b
						});
						c.east.doResize({
							height: b
						});
						c.west.doResize({
							height: b
						});
						if (this._isVisible(c.expandEast)) {
							c.expandEast.doResize({
								height: b
							})
						}
						if (this._isVisible(c.expandWest)) {
							c.expandWest.doResize({
								height: b
							})
						}
						c.south.element.animate({
							top: f.height()
						},
						function() {
							c.south.doClose();
							c.expandSouth.doOpen();
							c.expandSouth.doResize({
								top: f.height() - a,
								left: 0,
								width: f.width(),
								height: a
							})
						});
						if (!c.expandSouth) {
							c.expandSouth = this._createExpandPanel({
								region: "south",
								miniExpand: d.miniExpand,
								expandSize: a
							})
						}
					}
				}
			}
		}
	},
	_createExpandPanel: function(e) {
		var b = e.region;
		var h = this.element;
		var a = this;
		var d;
		if (b == "east") {
			if (e.miniExpand === true) {
				d = "fr-layout-border-minibutton-left"
			} else {
				d = "fr-layout-border-button-left"
			}
		} else {
			if (b == "west") {
				if (e.miniExpand === true) {
					d = "fr-layout-border-minibutton-right"
				} else {
					d = "fr-layout-border-button-right"
				}
			} else {
				if (b == "north") {
					if (e.miniExpand === true) {
						d = "fr-layout-border-minibutton-down"
					} else {
						d = "fr-layout-border-button-down"
					}
				} else {
					if (b == "south") {
						if (e.miniExpand === true) {
							d = "fr-layout-border-minibutton-up"
						} else {
							d = "fr-layout-border-button-up"
						}
					}
				}
			}
		}
		if (e.miniExpand === true) {
			var c = $("<div/>").css({
				height: e.expandSize + "px",
				width: "114px",
				margin: "auto",
				cursor: "pointer"
			}).addClass(d).click(function() {
				a._expandPanel(e)
			}).mouseover(function() {
				$(this).addClass("fr-layout-border-minibutton-over")
			}).mouseout(function() {
				$(this).removeClass("fr-layout-border-minibutton-over")
			});
			var g = new FR.Panel({
				cls: "fr-layout-border-miniexpand",
				fit: false,
				collapsible: false,
				border: false,
				contentHtml: c,
				width: e.width
			});
			h.append(g.element);
			g.element.hover(function() {
				$(this).addClass("fr-layout-border-miniexpand-over")
			},
			function() {
				$(this).removeClass("fr-layout-border-miniexpand-over")
			});
			return g
		}
		var f = new FR.Panel({
			title: "&nbsp;",
			cls: "fr-layout-border-expand",
			fit: false,
			collapsible: false,
			tools: [{
				iconCls: d,
				handler: function() {
					a._expandPanel(e)
				}
			}]
		});
		h.append(f.element);
		f.element.hover(function() {
			$(this).addClass("fr-layout-border-expand-over")
		},
		function() {
			$(this).removeClass("fr-layout-border-expand-over")
		});
		return f
	},
	_expandPanel: function(c) {
		var e = this.element;
		var d = c.region;
		var b = this.options;
		var a = this;
		if (d == "east" && b.expandEast) {
			b.expandEast.doClose();
			b.east.element.stop(true, true);
			b.east.doOpen();
			b.east.doResize({
				left: e.width()
			});
			b.east.element.animate({
				left: e.width() - b.east.options.width
			},
			function() {
				a.doResize()
			})
		} else {
			if (d == "west" && b.expandWest) {
				b.expandWest.doClose();
				b.west.element.stop(true, true);
				b.west.doOpen();
				b.west.doResize({
					left: -b.west.options.width
				});
				b.west.element.animate({
					left: 0
				},
				function() {
					a.doResize()
				})
			} else {
				if (d == "north" && b.expandNorth) {
					b.expandNorth.doClose();
					b.north.element.stop(true, true);
					b.north.doOpen();
					b.north.doResize({
						top: -b.north.options.height
					});
					b.north.element.animate({
						top: 0
					},
					function() {
						a.doResize();
						if ($(".frozen-center").length > 0) {
							$(window).resize()
						}
					})
				} else {
					if (d == "south" && b.expandSouth) {
						b.expandSouth.doClose();
						b.south.element.stop(true, true);
						b.south.doOpen();
						b.south.doResize({
							top: e.height()
						});
						b.south.element.animate({
							top: e.height() - b.south.options.height
						},
						function() {
							a.doResize()
						})
					}
				}
			}
		}
	},
	doResize: function(d) {
		var a = this.options;
		var c = this.element;
		if (a.fit == true) {
			var b = c.parent();
			if (b.length > 0) {
				c.css({
					width: (d ? (d.width || a.width) : a.width),
					height: (d ? (d.height || a.height) : a.height)
				})
			}
		}
		this.cpos = {
			top: 0,
			left: 0,
			width: c.width(),
			height: c.height()
		};
		if (this._isVisible(a.expandNorth)) {
			this._setNorthSize(c, a.expandNorth)
		} else {
			this._setNorthSize(c, a.north)
		}
		if (this._isVisible(a.expandSouth)) {
			this._setSouthSize(c, a.expandSouth)
		} else {
			this._setSouthSize(c, a.south)
		}
		if (this._isVisible(a.expandEast)) {
			this._setEastSize(c, a.expandEast)
		} else {
			this._setEastSize(c, a.east)
		}
		if (this._isVisible(a.expandWest)) {
			this._setWestSize(c, a.expandWest)
		} else {
			this._setWestSize(c, a.west)
		}
		a.center.doResize(this.cpos)
	},
	doLayout: function() {
		FR.BorderLayout.superclass.doLayout.apply(this, arguments)
	}
});
$.shortcut("border", FR.BorderLayout);
FR.HorizontalLayout = FR.extend(FR.Layout, {
	_init: function() {
		FR.HorizontalLayout.superclass._init.apply(this, arguments);
		var g = this.options = $.extend({
			alignment: "center",
			hgap: 0,
			vgap: 0,
			baseCls: "fr-horizontalboxlayout"
		},
		this.options);
		this.element.addClass(g.baseCls);
		var c = g.items,
		e, a = c.length;
		g.widgets = [];
		for (e = 0; e < a; e++) {
			var b = c[e];
			var f = FR.createWidget(b.el);
			g.widgets.push(f);
			var d = f.element;
			c[e].el = d;
			this.element.append(d)
		}
		this.element.data("jlayout", jLayout.horizontal(g))
	},
	doResize: function(b) {
		FR.HorizontalLayout.superclass.doResize.call(this, b);
		var a = this.options;
		this.element.css({
			width: (b ? (b.width || a.width) : a.width),
			height: (b ? (b.height || a.height) : a.height),
			left: b ? (b.left || a.left) : a.left,
			top: b ? (b.top || a.top) : a.top
		})
	},
	doLayout: function() {
		FR.HorizontalLayout.superclass.doLayout.apply(this, arguments)
	}
});
$.shortcut("horizontal", FR.HorizontalLayout);
FR.VerticalBoxLayout = FR.extend(FR.Layout, {
	_init: function() {
		FR.VerticalBoxLayout.superclass._init.apply(this, arguments);
		var g = this.options = $.extend({
			hgap: 5,
			vgap: 5,
			baseCls: "fr-verticalboxlayout"
		},
		this.options);
		this.element.addClass(g.baseCls);
		var c = g.items,
		e, a = c.length;
		g.widgets = [];
		for (e = 0; e < a; e++) {
			var b = c[e];
			var f = FR.createWidget(b.el);
			g.widgets.push(f);
			var d = f.element;
			c[e].el = d;
			this.element.append(d)
		}
		this.element.data("jlayout", jLayout.vertical(g))
	},
	doResize: function(b) {
		FR.VerticalBoxLayout.superclass.doResize.call(this, b);
		var a = this.options;
		this.element.css({
			width: a.width,
			height: a.height,
			left: a.left,
			top: a.top
		})
	},
	doLayout: function() {
		FR.VerticalBoxLayout.superclass.doLayout.apply(this, arguments)
	}
});
$.shortcut("vertical", FR.VerticalBoxLayout);
FR.GridLayout = FR.extend(FR.Layout, {
	_init: function() {
		FR.GridLayout.superclass._init.apply(this, arguments);
		var h = this.options = $.extend({
			hgap: 0,
			vgap: 0,
			leftGap: 0,
			rightGap: 0,
			topGap: 0,
			bottomGap: 0,
			baseCls: "fr-gridlayout"
		},
		this.options);
		this.element.addClass(h.baseCls);
		var c = h.items,
		f, a = c.length;
		h.widgets = [];
		for (var d = 0; d < a; d++) {
			var b = c[d];
			if (b.el.type) {
				var g = FR.createWidget(b.el);
				h.widgets.push(g);
				var e = g.element
			} else {
				e = b.el
			}
			b.el = e;
			this.element.append(e)
		}
		this.element.data("jlayout", jLayout.frgrid(h))
	},
	doResize: function(b) {
		FR.GridLayout.superclass.doResize.call(this, b);
		var a = this.options;
		this.element.css({
			width: (b ? (b.width || a.width) : a.width),
			height: (b ? (b.height || a.height) : a.height),
			left: b ? (b.left || a.left) : a.left,
			top: b ? (b.top || a.top) : a.top
		})
	},
	doLayout: function() {
		FR.GridLayout.superclass.doLayout.apply(this, arguments)
	}
});
$.shortcut("grid", FR.GridLayout);
FR.FrozenGridLayout = FR.extend(FR.Layout, {
	_init: function() {
		FR.FrozenGridLayout.superclass._init.apply(this, arguments);
		var b = this.element;
		var a = this.options;
		$.each(a.items || [],
		function(c, d) {
			if (d.parent()[0] != b[0]) {
				d.appendTo(b)
			}
		});
		b.data("jlayout", jLayout.grid(a))
	},
	doLayout: function() {
		this.element.doLayout()
	},
	doResize: function(b) {
		FR.FrozenGridLayout.superclass.doResize.apply(this, arguments);
		var a = this.options;
		this.element.css({
			width: (b ? (b.width || a.width) : a.width) - (this.marginWidth || 0),
			height: (b ? (b.height || a.height) : a.height) - (this.marginHeight || 0),
			left: b ? (b.left || a.left) : a.left,
			top: b ? (b.top || a.top) : a.top
		})
	}
});
$.shortcut("frozengrid", FR.FrozenGridLayout);
FR.SplitLayout = FR.extend(FR.Layout, {
	_init: function() {
		FR.SplitLayout.superclass._init.apply(this, arguments);
		var a = this.options = $.extend({
			hgap: 0,
			vgap: 0,
			baseCls: "fr-split-layout"
		},
		this.options);
		this.element.addClass(a.baseCls);
		a.widgets = [];
		var f = ["aside", "center"];
		for (var h = 0; h < f.length; h++) {
			var g = f[h];
			for (var d = 0; d < a.items.length; d++) {
				var j = a.items[d];
				if (j.region != g) {
					continue
				}
				var b = j.el;
				if (b.type) {
					var e = FR.createWidget(b);
					a[g] = e;
					a.widgets.push(e);
					var c = e.element
				} else {
					c = b
				}
				j.el = c;
				this.element.append(c)
			}
		}
	},
	doResize: function(b) {
		FR.SplitLayout.superclass.doResize.call(this, b);
		var a = this.options;
		this.element.css({
			width: (b ? (b.width || a.width) : a.width),
			height: (b ? (b.height || a.height) : a.height),
			left: b ? (b.left || a.left) : a.left,
			top: b ? (b.top || a.top) : a.top
		})
	},
	doLayout: function() {
		FR.SplitLayout.superclass.doLayout.apply(this, arguments);
		this.element.doLayout()
	}
});
FR.HorizontalSplitLayout = FR.extend(FR.SplitLayout, {
	_init: function() {
		FR.HorizontalSplitLayout.superclass._init.apply(this, arguments);
		this.element.data("jlayout", jLayout.horizontalSplit(this.options))
	}
});
$.shortcut("hsplit", FR.HorizontalSplitLayout);
FR.VerticalSplitLayout = FR.extend(FR.SplitLayout, {
	_init: function() {
		FR.VerticalSplitLayout.superclass._init.apply(this, arguments);
		this.element.data("jlayout", jLayout.verticalSplit(this.options))
	}
});
$.shortcut("vsplit", FR.VerticalSplitLayout);
FR.TabPane = FR.extend(FR.Widget, {
	_init: function() {
		FR.TabPane.superclass._init.apply(this, arguments);
		var a = this.options = $.extend({
			baseCls: "fr-tabpane",
			active: 0,
			tabPosition: "top",
			tabs: [],
			scrolled: false,
			tabWidth: 110 + 4,
			fixNum: 2,
			scrollFinish: true,
			maxLength: -1,
			maxzindex: 0,
			width: "100%",
			height: "100%"
		},
		this.options);
		this.element.css({
			width: a.width,
			height: a.height
		});
		FR.$defaultImport("/com/fr/web/core/css/widget/fr.form.newtabs.css", "css");
		FR.$defaultImport("/com/fr/web/core/js/form/fr.new.formtabs.js", "js");
		this.initTabPane()
	},
	doResize: function(a) {
		this.element.css({
			width: a.width,
			height: a.height
		})
	}
});
$.shortcut("tabpane", FR.TabPane); (function() {
	$.extend(FR, {
		isAndroid: function() {
			return (navigator.appVersion.toLowerCase().indexOf("android") > 0)
		},
		isIDevice: function() {
			return (/iphone|ipad/gi).test(navigator.appVersion)
		},
		isMobile: function() {
			return FR.isAndroid() || FR.isIDevice()
		}
	})
})(); (function() {
	var e = function() {};
	function a(i) {
		this.data = i.path || i.data;
		this.imageData = [];
		this.multiplier = i.multiplier || 1;
		this.padding = i.padding || 0;
		this.fps = i.fps || 25;
		this.stepsPerFrame = ~~i.stepsPerFrame || 1;
		this.trailLength = i.trailLength || 1;
		this.pointDistance = i.pointDistance || 0.05;
		this.domClass = i.domClass || "sonic";
		this.fillColor = i.fillColor || "#FFF";
		this.strokeColor = i.strokeColor || "#FFF";
		this.stepMethod = typeof i.step == "string" ? c[i.step] : i.step || c.square;
		this._setup = i.setup || e;
		this._teardown = i.teardown || e;
		this._preStep = i.preStep || e;
		this.width = i.width;
		this.height = i.height;
		this.fullWidth = this.width + 2 * this.padding;
		this.fullHeight = this.height + 2 * this.padding;
		this.domClass = i.domClass || "sonic";
		this.setup()
	}
	var g = a.argTypes = {
		DIM: 1,
		DEGREE: 2,
		RADIUS: 3,
		OTHER: 0
	};
	var f = a.argSignatures = {
		arc: [1, 1, 3, 2, 2, 0],
		bezier: [1, 1, 1, 1, 1, 1, 1, 1],
		line: [1, 1, 1, 1]
	};
	var d = a.pathMethods = {
		bezier: function(B, p, n, k, j, r, q, m, l) {
			B = 1 - B;
			var o = 1 - B,
			A = B * B,
			w = o * o,
			z = A * B,
			v = 3 * A * o,
			u = 3 * B * w,
			s = w * o;
			return [z * p + v * r + u * m + s * k, z * n + v * q + u * l + s * j]
		},
		arc: function(n, k, p, j, o, l) {
			var i = (l - o) * n + o;
			var m = [(Math.cos(i) * j) + k, (Math.sin(i) * j) + p];
			m.angle = i;
			m.t = n;
			return m
		},
		line: function(k, m, l, j, i) {
			return [(j - m) * k + m, (i - l) * k + l]
		}
	};
	var c = a.stepMethods = {
		square: function(j, l, m, k, n) {
			this._.fillRect(j.x - 3, j.y - 3, 6, 6)
		},
		fader: function(j, l, m, k, n) {
			this._.beginPath();
			if (this._last) {
				this._.moveTo(this._last.x, this._last.y)
			}
			this._.lineTo(j.x, j.y);
			this._.closePath();
			this._.stroke();
			this._last = j
		}
	};
	a.prototype = {
		setup: function() {
			var q, u, k, w, n = this.data;
			this.canvas = document.createElement("canvas");
			this._ = this.canvas.getContext("2d");
			this.canvas.className = this.domClass;
			this.canvas.height = this.fullHeight;
			this.canvas.width = this.fullWidth;
			this.points = [];
			for (var o = -1,
			m = n.length; ++o < m;) {
				q = n[o].slice(1);
				k = n[o][0];
				if (k in f) {
					for (var v = -1,
					p = q.length; ++v < p;) {
						u = f[k][v];
						w = q[v];
						switch (u) {
						case g.RADIUS:
							w *= this.multiplier;
							break;
						case g.DIM:
							w *= this.multiplier;
							w += this.padding;
							break;
						case g.DEGREE:
							w *= Math.PI / 180;
							break
						}
						q[v] = w
					}
				}
				q.unshift(0);
				for (var j, s = this.pointDistance,
				x = s; x <= 1; x += s) {
					x = Math.round(x * 1 / s) / (1 / s);
					q[0] = x;
					j = d[k].apply(null, q);
					this.points.push({
						x: j[0],
						y: j[1],
						progress: x
					})
				}
			}
			this.frame = 0
		},
		prep: function(j) {
			if (j in this.imageData) {
				return
			}
			this._.clearRect(0, 0, this.fullWidth, this.fullHeight);
			var s = this.points,
			q = s.length,
			o = this.pointDistance,
			r, n, p;
			this._setup();
			for (var m = -1,
			k = q * this.trailLength; ++m < k && !this.stopped;) {
				n = j + m;
				r = s[n] || s[n - q];
				if (!r) {
					continue
				}
				this.alpha = Math.round(1000 * (m / (k - 1))) / 1000;
				this._.globalAlpha = this.alpha;
				this._.fillStyle = this.fillColor;
				this._.strokeStyle = this.strokeColor;
				p = j / (this.points.length - 1);
				indexD = m / (k - 1);
				this._preStep(r, indexD, p);
				this.stepMethod(r, indexD, p)
			}
			this._teardown();
			this.imageData[j] = (this._.getImageData(0, 0, this.fullWidth, this.fullWidth));
			return true
		},
		draw: function() {
			if (!this.prep(this.frame)) {
				this._.clearRect(0, 0, this.fullWidth, this.fullWidth);
				this._.putImageData(this.imageData[this.frame], 0, 0)
			}
			this.iterateFrame()
		},
		iterateFrame: function() {
			this.frame += this.stepsPerFrame;
			if (this.frame >= this.points.length) {
				this.frame = 0
			}
		},
		play: function() {
			this.stopped = false;
			var i = this;
			this.timer = setInterval(function() {
				i.draw()
			},
			1000 / this.fps)
		},
		stop: function() {
			this.stopped = true;
			this.timer && clearInterval(this.timer)
		}
	};
	var h, b;
	$.extend(FR, {
		showLoadingMark: function(i) {
			var j = i ? i: $("body");
			h = $("<div>").appendTo(j);
			b = new a({
				width: 100,
				height: 100,
				stepsPerFrame: 1,
				trailLength: 1,
				pointDistance: 0.02,
				fps: 30,
				fillColor: "#05E2FF",
				step: function(k, l) {
					this._.beginPath();
					this._.moveTo(k.x, k.y);
					this._.arc(k.x, k.y, l * 7, 0, Math.PI * 2, false);
					this._.closePath();
					this._.fill()
				},
				path: [["arc", 50, 50, 30, 0, 360]]
			});
			h.append($(b.canvas));
			b.canvas.style.marginTop = (j.height() / 2 - 50) + "px";
			b.canvas.style.marginLeft = (j.width() / 2 - 50) + "px";
			b.play()
		},
		hideLoadingMark: function() {
			if (b) {
				b.stop()
			}
			if (h) {
				h.remove()
			}
		}
	})
} ()); (function() {
	var a;
	var c;
	var b;
	$.extend(FR, {
		showDialog: function(i, f, d, h, e) {
			var g = $.extend(e, {
				title: i + (FR.i18n.PROJECTNAME == null ? "": " " + FR.i18n.PROJECTNAME),
				width: f,
				height: d,
				contentHtml: h
			});
			if (a == null) {
				a = new FR.Dialog(g)
			} else {
				a.setTitle(g.title);
				a.setContent({
					type: "contentHtml",
					content: h
				});
				a.doResize({
					width: f,
					height: d
				})
			}
			a.setVisible(true);
			return a
		},
		showFormDialog: function(d) {
			if (typeof d == "string") {
				d = {
					url: d
				}
			}
			FR.showLoadingDialog({
				title: "Loading",
				text: FR.i18n.Calculating + "......"
			});
			$.ajax($.extend({
				type: "POST",
				complete: function(f, e) {
					var g = $("<iframe width='100%' height='100%' scrolling='no' frameborder='0'>");
					FR.showDialog(d.title, d.width || 350, d.height || 280, g, d);
					FR.hideLoadingDialog()
				}
			},
			d))
		},
		showIframeDialog: function(e) {
			if (typeof e == "string") {
				e = {
					url: e
				}
			}
			var d;
			if (e.url) {
				e.url = e.url + ((/\?/.test(e.url) ? "&": "?") + "_=" + $.now());
				d = $("<iframe width='100%' height='100%' scrolling='no' frameborder='0'>");
				d.attr("src", e.url)
			} else {
				if (e.iframe) {
					d = $(e.iframe);
					if (e.widget) {
						d.asComponent(e.widget)
					}
				} else {
					d = $("<iframe width='100%' height='100%' scrolling='no' frameborder='0'>")
				}
			}
			return FR.showDialog(e.title, e.width || 350, e.height || 480, d)
		},
		showSubmitDialog: function(d) {
			if (typeof d == "string") {
				d = {
					url: d
				}
			}
			d.width = d.width || 200;
			d.height = d.height || 120;
			FR.showLoadingDialog({
				title: "Loading",
				text: FR.i18n.Calculating + "......"
			});
			$.ajax($.extend({
				type: "POST",
				complete: function(f, e) {
					FR.Msg.toast(f.responseText || FR.i18n.No_Data);
					FR.hideLoadingDialog()
				}
			},
			d))
		},
		showConfirmDialog: function(d) {
			FR.showLoadingDialog(d);
			var e = d.callback;
			$.ajax($.extend({
				type: "POST",
				complete: function(g, f) {
					FR.hideLoadingDialog();
					if (typeof(e) == "function") {
						e(g, f)
					}
				}
			},
			d))
		},
		closeDialog: function() {
			if (a != null) {
				a.setVisible(false)
			}
		},
		showLoadingDialog: function(d) {
			d = d || {};
			d.width = d.width || 200;
			d.height = d.height || 80;
			d.title = d.title || FR.i18n.Loading;
			d.closable = d.closable || false;
			d.modal = true;
			d.contentHtml = "<div class='fr-panel-loading'>" + (d.text ? d.text: FR.i18n.Loading) + "</div>";
			if (c == null) {
				c = new FR.Dialog(d)
			} else {
				c.setTitle(d.title);
				c.doResize({
					width: d.width,
					height: d.height
				});
				c.setContent({
					type: "contentHtml",
					content: d.contentHtml
				})
			}
			c.setVisible(true)
		},
		hideLoadingDialog: function() {
			if (c != null) {
				c.setVisible(false)
			}
		},
		destroyDialog: function() {
			if (a != null) {
				a.destroy();
				a = null
			}
		},
		distroyDialog: function() {
			this.destroyDialog()
		},
		showUploadDialog: function(f) {
			f.title = FR.i18n.File_Upload;
			if (b == null) {
				b = new FR.Dialog(f)
			}
			b.options = $.extend({
				url: "",
				el: this,
				allowTypes: "",
				err: "",
				callback: FR.emptyFn,
				autoUpload: false
			},
			f);
			b.doResize({
				width: b.options.autoUpload ? 360 : 440,
				height: "auto"
			});
			var d = $('<input type="file" name="file" size="35" style="width:320px"/>').change(function() {
				FR.autoSubmit(b.options, b.$form)
			});
			var e = $('<input type="button" value="' + FR.i18n.OK + '" style="width:80px;margin-top:10px;"/>)').click(function() {
				FR.autoSubmit(b.options, b.$form)
			});
			if (b.options.autoUpload) {
				b.$form = $('<form enctype="multipart/form-data" style="padding:10px"></form>').append(d)
			} else {
				b.$form = $('<form enctype="multipart/form-data" style="padding:10px"><input name="file" type="file" size="35" style="width:320px/></form>').append(e)
			}
			b.setContent({
				type: "contentHtml",
				content: b.$form
			});
			b.setVisible(true)
		},
		autoSubmit: function(g, f) {
			var d = $("input:file", f);
			if (!d.val()) {
				FR.Msg.toast(FR.i18n.No_File_Warning + "!");
				return
			} else {
				if (g.allowTypes) {
					var e = d.val().substring(d.val().lastIndexOf(".") + 1);
					if (g.allowTypes && g.allowTypes.indexOf(e) == -1) {
						FR.Msg.toast(g.err ? g.err: (e + FR.i18n.Not_Allow_To_Upload_File + "," + FR.i18n.Allow_To_Upload_Contains + g.allowTypes));
						return false
					}
				} else {
					if (g.filter) {
						if (d.val().toLowerCase().indexOf("." + g.filter) == -1) {
							FR.Msg.toast(FR.i18n.Import_Excel_Message);
							return
						}
					}
				}
			}
			FR.showLoadingDialog({
				width: 310,
				height: 80
			});
			FR.domFormSubmit(f, {
				url: g.url,
				timeout: 3000000
			},
			function(i, h) {
				FR.hideLoadingDialog();
				if (b) {
					b.setVisible(false)
				}
				if ($.isFunction(g.callback)) {
					g.callback(i, h, g.el)
				}
			})
		},
		__fileSizeFormat__: function(f) {
			if (!f) {
				return ""
			}
			var e = 0;
			var d = ["B", "KB", "MB", "GB", "TB"];
			var g = f + d[e];
			while (f > 1024 && e < d.length - 1) {
				f = f / 1024;
				e++
			}
			g = f.toFixed(2) + d[e];
			return g
		},
		correctPNG: function(g) {
			if (g instanceof $) {
				g = g[0]
			}
			var e = (g.id) ? "id='" + g.id + "' ": "";
			var h = (g.className) ? "class='" + g.className + "' ": "";
			var i = (g.title) ? "title='" + g.title + "' ": "title='" + g.alt + "' ";
			var f = "display:inline-block;" + g.style.cssText;
			if (g.align == "left") {
				f = "float:left;" + f
			}
			if (g.align == "right") {
				f = "float:right;" + f
			}
			if (g.parentElement.href) {
				f = "cursor:hand;" + f
			}
			var d = "<span " + e + h + i + 'style="width:' + g.width + "px; height:" + g.height + "px;" + f + ";filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + g.src + "', sizingMethod='scale');\"></span>";
			g.outerHTML = d
		},
		open: function(d) {
			var e = "";
			var g = /[a-zA-z]+:\/\/[^\s]*/;
			var f = /\/[^\s]*/;
			if (d.match(g) || d.match(f)) {
				e = d
			} else {
				e = "http://" + d
			}
			window.open(e)
		},
		considerBoxModelWidth: function(d) {
			if (!d) {
				return 0
			}
			return FR.parseCssToIntValue(d.css("border-left-width")) + FR.parseCssToIntValue(d.css("border-right-width")) + FR.parseCssToIntValue(d.css("padding-left")) + FR.parseCssToIntValue(d.css("padding-right"))
		},
		considerBoxModelHeight: function(d) {
			if (!d) {
				return 0
			}
			return FR.parseCssToIntValue(d.css("border-top-width")) + FR.parseCssToIntValue(d.css("border-bottom-width")) + FR.parseCssToIntValue(d.css("padding-top")) + FR.parseCssToIntValue(d.css("padding-bottom"))
		},
		parseCssToIntValue: function(d) {
			return isNaN(parseInt(d ? d: 0)) ? 0 : (parseInt(d ? d: 0))
		},
		encrypt: function(i, h) {
			if (i == "") {
				return ""
			}
			i = escape(i);
			if (!h || h == "") {
				var h = "655"
			}
			h = escape(h);
			if (h == null || h.length <= 0) {
				alert("Please enter a password with which to encrypt the message.");
				return null
			}
			var n = "";
			for (var l = 0; l < h.length; l++) {
				n += h.charCodeAt(l).toString()
			}
			var j = Math.floor(n.length / 5);
			var e = parseInt(n.charAt(j) + n.charAt(j * 2) + n.charAt(j * 3) + n.charAt(j * 4) + n.charAt(j * 5));
			var d = Math.ceil(h.length / 2);
			var k = Math.pow(2, 31) - 1;
			if (e < 2) {
				alert("Algorithm cannot find a suitable hash. Please choose a different password. \nPossible considerations are to choose a more complex or longer password.");
				return null
			}
			var f = Math.round(Math.random() * 1000000000) % 100000000;
			n += f;
			while (n.length > 10) {
				n = (parseInt(n.substring(0, 10)) + parseInt(n.substring(10, n.length), 10)).toString()
			}
			n = (e * n + d) % k;
			var g = "";
			var m = "";
			for (var l = 0; l < i.length; l++) {
				g = parseInt(i.charCodeAt(l) ^ Math.floor((n / k) * 255));
				if (g < 16) {
					m += "0" + g.toString(16)
				} else {
					m += g.toString(16)
				}
				n = (e * n + d) % k
			}
			f = f.toString(16);
			while (f.length < 8) {
				f = "0" + f
			}
			m += f;
			return m
		},
		decrypt: function(j, i) {
			if (j == "") {
				return ""
			}
			if (!i || i == "") {
				var i = "655"
			}
			i = escape(i);
			if (j == null || j.length < 8) {
				return
			}
			if (i == null || i.length <= 0) {
				return
			}
			var o = "";
			for (var m = 0; m < i.length; m++) {
				o += i.charCodeAt(m).toString()
			}
			var k = Math.floor(o.length / 5);
			var d = o.charAt(k) + o.charAt(k * 2) + o.charAt(k * 3) + o.charAt(k * 4);
			if (k * 5 < o.length) {
				d += o.charAt(k * 5)
			}
			var f = parseInt(d);
			var e = Math.round(i.length / 2);
			var l = Math.pow(2, 31) - 1;
			var g = parseInt(j.substring(j.length - 8, j.length), 16);
			j = j.substring(0, j.length - 8);
			o += g;
			while (o.length > 10) {
				o = (parseInt(o.substring(0, 10), 10) + parseInt(o.substring(10, o.length), 10)).toString()
			}
			o = (f * o + e) % l;
			var h = "";
			var n = "";
			for (var m = 0; m < j.length; m += 2) {
				h = parseInt(parseInt(j.substring(m, m + 2), 16) ^ Math.floor((o / l) * 255));
				n += String.fromCharCode(h);
				o = (f * o + e) % l
			}
			return unescape(n)
		}
	})
})(); (function() {
	var $PrintDiv, $PDFReader, $flashIframe, $appletDiv, showFlashPrintSetting, fitPaper, showAppletPrintSetting = null;
	function isShowFlashPrintSetting() {
		if (typeof showFlashPrintSetting != "boolean") {
			$(function() {
				$.ajax({
					url: FR.servletURL,
					data: {
						op: "flash_print",
						cmd: "get_fp_setting"
					},
					async: false,
					complete: function(res, status) {
						if (status == "success") {
							showFlashPrintSetting = res.responseText == "true"
						}
					}
				})
			})
		}
		return showFlashPrintSetting
	}
	function isFitPaper() {
		if (!fitPaper) {
			$(function() {
				$.ajax({
					url: FR.servletURL,
					data: {
						op: "flash_print",
						cmd: "fit_fs_paper"
					},
					async: false,
					complete: function(res, status) {
						if (status == "success") {
							fitPaper = res.responseText == "true"
						}
					}
				})
			})
		}
		return fitPaper
	}
	function isShowAppletPrintSetting() {
		if (typeof showAppletPrintSetting != "boolean") {
			$(function() {
				$.ajax({
					url: FR.servletURL,
					data: {
						op: "fr_applet",
						cmd: "applet_print_setting"
					},
					async: false,
					complete: function(res, status) {
						if (status == "success") {
							showAppletPrintSetting = res.responseText == "true"
						}
					}
				})
			})
		}
		return showAppletPrintSetting
	}
	function isAppletPrintOver(sessionID) {
		var appletPrintOver;
		$.ajax({
			url: FR.servletURL,
			data: {
				op: "fr_applet",
				cmd: "is_printover",
				sessionID: sessionID,
				data: new Date().getTime()
			},
			async: false,
			complete: function(res, status) {
				if (status == "success") {
					appletPrintOver = res.responseText == "true"
				}
			}
		});
		return appletPrintOver
	}
	function isSingleSheetFunc(sessionID) {
		var isSingleSheet;
		$.ajax({
			url: FR.servletURL,
			data: {
				op: "fr_applet",
				cmd: "is_singleSheet",
				sessionID: sessionID
			},
			async: false,
			complete: function(res, status) {
				if (status == "success") {
					isSingleSheet = res.responseText == "true"
				}
			}
		});
		return isSingleSheet
	}
	function checkPDFPrint(sessionID, popupSetup) {
		$.ajax({
			url: FR.servletURL,
			type: "POST",
			data: {
				op: "fr_pdfprint",
				cmd: "pt_check",
				sessionID: sessionID
			},
			complete: function(res, status) {
				var resText = res.responseText;
				if ("ok" == resText) { (function() {
						FR.closeDialog();
						if (popupSetup === true) {
							$PDFReader[0].print()
						} else {
							$PDFReader[0].printAllFit(true)
						}
						if (_g()) {
							_g().fireEvent("afterpdfprint")
						}
					}).defer(2000)
				} else {
					if ("gening" == resText) {
						checkPDFPrint.defer(300, this, [sessionID, popupSetup])
					} else {
						FR.Msg.toast(resText)
					}
				}
			}
		})
	}
	function __getSessionID__(url, config) {
		var sessionID = null;
		var udata;
		if (config && config.form) {
			$form = $(config.form);
			udata = $.param($form.serializeArray().concat([{
				name: "op",
				value: "getSessionID"
			}]))
		} else {
			if (config && config.formid) {
				$form = $("#" + config.formid);
				udata = $.param($form.serializeArray().concat([{
					name: "op",
					value: "getSessionID"
				}]))
			} else {
				if (config && config.data) {
					udata = $.extend({
						op: "getSessionID"
					},
					config.data)
				} else {
					udata = {
						op: "getSessionID"
					}
				}
			}
		}
		$.ajax({
			url: url,
			type: "POST",
			data: udata,
			async: false,
			complete: function(res, status) {
				if (status == "success") {
					sessionID = res.responseText
				}
			}
		});
		return sessionID
	}
	$.extend(FR, {
		doPDFPrint: function(sessionID, popupSetup) {
			var configAttr = {};
			$.ajax({
				url: FR.servletURL + "?op=fr_server&cmd=sc_get_configinfo",
				type: "POST",
				async: false,
				complete: function(res, status) {
					if (res.responseText == null || res.responseText == "") {
						return
					}
					configAttr = FR.jsonDecode(res.responseText)
				}
			});
			var config = {
				url: FR.servletURL + "?sessionID=" + sessionID,
				isPopUp: configAttr.isPopupPdfPrintSetting
			};
			FR.doURLPDFPrint(config)
		},
		doURLPDFPrint: function(config) {
			var url = arguments[0];
			var isPopUp;
			if (typeof url == "string") {
				isPopUp = arguments[1];
				config = arguments[2]
			} else {
				url = config.url;
				isPopUp = config.isPopUp
			}
			var sessionID = __getSessionID__(url, config);
			if (!$.browser.msie) {
				window.location = FR.servletURL + "?op=export&sessionID=" + sessionID + "&format=pdf&isPDFPrint=true&extype=ori";
				return
			}
			if ($PrintDiv == null) {
				$PrintDiv = $("<div>").appendTo("body")
			}
			FR.showDialog(FR.i18n.Printing, 250, 100, FR.i18n.Loading_Component + "...");
			$.ajax({
				url: FR.servletURL,
				data: {
					sessionID: sessionID,
					op: "fr_pdfprint",
					cmd: "pt_print",
					frandom: Math.random()
				},
				type: "POST",
				complete: function(res, status) {
					if (status == "success") {
						$PrintDiv[0].innerHTML = res.responseText;
						$PDFReader = $PrintDiv.find("Object");
						try {
							$PDFReader[0].gotoFirstPage();
							FR.showDialog(FR.i18n.Printing, 250, 100, FR.i18n.Loading_PDF + "...");
							checkPDFPrint.defer(300, this, [sessionID, isPopUp])
						} catch(e) {
							var content;
							$.ajax({
								type: "POST",
								url: FR.servletURL + "?op=fr_pdfprint&cmd=pt_install",
								complete: function(res, status) {
									if (status == "success") {
										var path = res.responseText;
										if (typeof path == "string" && path.length > 0 && path.length < 150) {
											content = '<div style="text-align:center;">' + FR.i18n.Please_Install + ' <a href="' + path + '" target="_blank">Adobe Reader</a>.</div>'
										}
									}
									if (!content) {
										content = '<div style="text-align:center;">' + FR.i18n.Please_Install + ' <a href="http://www.adobe.com/products/reader/" target="_blank">Adobe Reader</a>.</div>'
									}
									FR.showDialog(FR.i18n.Alert, 200, 60, content)
								}
							})
						}
					}
				}
			})
		},
		doURLAppletPrint: function(config) {
			var url = arguments[0];
			var isShowDialog;
			var pageIndex;
			if (typeof url == "string") {
				isShowDialog = arguments[1];
				if (isShowDialog == undefined || !(typeof isShowDialog == "boolean")) {
					isShowDialog = isShowAppletPrintSetting()
				}
			} else {
				url = config.url;
				isShowDialog = config.isPopUp;
				pageIndex = config.pageIndex;
				if (isShowDialog == undefined || !(typeof isShowDialog == "boolean")) {
					isShowDialog = isShowAppletPrintSetting()
				}
			}
			var index = 0;
			var bro = $.browser;
			var isIE9 = 0;
			if (bro.msie) {
				isIE9 = bro.version
			}
			if (isShowDialog) {
				var $content = $("<div>").appendTo("body").css("padding", 10);
				$("<table border=\"0\"><tr><td colspan='3' valign='middle' class='fh-nopadding'><input type='radio' name='range' value='all' checked>" + FR.i18n.HJS_All_Pages + "<tr><td colspan='3' valign='middle' class='fh-nopadding'><input type='radio' name='range' value='specify'>" + FR.i18n.HJS_Specified_Pages + ":<input type='text' name='specify' value=''>(" + FR.i18n.Example + ": 2,5,7-10,12)</td></tr><tr style='height:10px'><td colspan='3'/></tr><tr><td class='fh-nopadding' style='height:20px;'><div></div></td></tr><tr><td style='width:250px'></td><td class='yes-btn'></td><td class='cancel-btn'></td></tr></table>").appendTo($content);
				if (pageIndex != undefined && (typeof pageIndex == "number")) {
					var specify_editor = $("input:text", $content).val(pageIndex)
				} else {
					var specify_editor = $("input:text", $content).val("1-1")
				}
				new FR.Button({
					text: FR.i18n.OK,
					render: false,
					renderEl: $("<div/>").appendTo($(".yes-btn", $content)),
					handler: function() {
						FR.closeDialog();
						switch ($("input:checked", $content).val()) {
						case "specify":
							index = specify_editor.val();
							break
						}
						if ($appletDiv == null) {
							$appletDiv = $("<div>").appendTo("body")
						}
						FR.showDialog(FR.i18n.Printing, 250, 100, FR.i18n.Loading_Applet + "...");
						var sessionID = __getSessionID__(url, config);
						if (url.indexOf("reportlet") != -1 || url.indexOf("resultlets") != -1 || (config && config.data && config.data.reportlets)) {
							FR.showDialog(FR.i18n.Printing, 250, 100, FR.i18n.Executing_Report + "...");
							if (url.indexOf("?") != -1) {
								url += "&_=" + new Date().getTime()
							} else {
								url += "?_=" + new Date().getTime()
							}
							var sessionID = __getSessionID__(url, config);
							url = FR.servletURL + "?sessionID=" + sessionID
						}
						var isSingleSheet = isSingleSheetFunc(sessionID);
						var attributes = null;
						var parameters = null;
						if ($.browser.msie) {
							attributes = {
								codebase: FR.server + "/jre.exe"
							};
							parameters = {
								code: "com.fr.report.print.PrintApplet",
								archive: FR.server + "/fr-applet-7.0.jar",
								url: FR.serverURL + url + "&op=fr_applet&cmd=print",
								isIE9: isIE9,
								isShowDialog: isShowDialog,
								index: index,
								isSingleSheet: isSingleSheet
							}
						} else {
							attributes = {
								code: "com.fr.report.print.PrintApplet.class",
								archive: FR.server + "/fr-applet-7.0.jar",
								width: 0,
								height: 0
							};
							parameters = {
								url: FR.serverURL + url + "&op=fr_applet&cmd=print",
								isIE9: isIE9,
								isShowDialog: isShowDialog,
								index: index,
								isSingleSheet: isSingleSheet
							}
						}
						deployJava.runApplet(attributes, parameters, "1.4");
						FR.closeDialog();
						var isOverFunc = function() {
							if (!isAppletPrintOver(sessionID)) {
								return
							}
							clearInterval(sh);
							if (_g()) {
								_g().fireEvent("afterappletprint")
							}
						};
						var sh = setInterval(isOverFunc, 3000)
					}
				});
				new FR.Button({
					text: FR.i18n.Cancel,
					render: false,
					renderEl: $("<div/>").appendTo($(".cancel-btn", $content)),
					handler: function() {
						index = 0;
						FR.closeDialog()
					}
				});
				FR.showDialog(FR.i18n.Setting, 400, "auto", $content)
			} else {
				if (pageIndex != undefined && (typeof pageIndex == "number")) {
					index = pageIndex
				}
				var sessionID = __getSessionID__(url, config);
				if (url.indexOf("reportlet") != -1 || url.indexOf("resultlets") != -1 || (config && config.data && config.data.reportlets)) {
					FR.showDialog(FR.i18n.Printing, 250, 100, FR.i18n.Executing_Report + "...");
					if (url.indexOf("?") != -1) {
						url += "&_=" + new Date().getTime()
					} else {
						url += "?_=" + new Date().getTime()
					}
					var sessionID = __getSessionID__(url, config);
					url = FR.servletURL + "?sessionID=" + sessionID
				}
				var isSingleSheet = isSingleSheetFunc(sessionID);
				if ($.browser.msie) {
					attributes = {
						codebase: FR.server + "/jre.exe"
					};
					parameters = {
						code: "com.fr.report.print.PrintApplet",
						archive: FR.server + "/fr-applet-7.0.jar",
						url: FR.serverURL + url + "&op=fr_applet&cmd=print",
						isIE9: isIE9,
						isShowDialog: isShowDialog,
						index: index,
						isSingleSheet: isSingleSheet
					}
				} else {
					var attributes = {
						code: "com.fr.report.print.PrintApplet.class",
						archive: FR.server + "/fr-applet-7.0.jar",
						width: 0,
						height: 0
					};
					var parameters = {
						url: FR.serverURL + url + "&op=fr_applet&cmd=print",
						isIE9: isIE9,
						index: index,
						isShowDialog: isShowDialog,
						isSingleSheet: isSingleSheet
					}
				}
				deployJava.runApplet(attributes, parameters, "1.4");
				FR.closeDialog();
				var isOverFunc = function() {
					if (!isAppletPrintOver(sessionID)) {
						return
					}
					clearInterval(sh);
					if (_g()) {
						_g().fireEvent("afterappletprint")
					}
				};
				var sh = setInterval(isOverFunc, 3000)
			}
		},
		doAppletPrint: function(sessionID) {
			var url = FR.servletURL + "?sessionID=" + sessionID;
			if (_g().fireEvent("beforeappletprint") === false) {
				return
			}
			var config = {
				url: url
			};
			FR.doURLAppletPrint(config)
		},
		doFlashPrint: function(sessionID, currentPageIndex) {
			if (_g().fireEvent("beforeflashprint") === false) {
				return
			}
			var fitPaper = isFitPaper();
			FR.doPrintURL(FR.servletURL + "?sessionID=" + sessionID, currentPageIndex, fitPaper)
		},
		doPrintURL: function(url, currentPageIndex, fitPaper, config) {
			var config = arguments[3];
			if (config == undefined) {
				config = {
					url: url,
					pageIndex: currentPageIndex,
					isAutoZoom: fitPaper
				}
			}
			FR.doURLFlashPrint(config)
		},
		isInstalledFlash: function() {
			if (this.$i_flash === true) {
				return true
			}
			var i_flash = false;
			var n = navigator;
			if (n.plugins && n.plugins.length) {
				for (var ii = 0; ii < n.plugins.length; ii++) {
					if (n.plugins[ii] && n.plugins[ii].name.indexOf("Shockwave Flash") != -1) {
						i_flash = true;
						break
					}
				}
			} else {
				if (window.ActiveXObject) {
					for (var ii = 10; ii >= 2; ii--) {
						try {
							var fl = eval("new ActiveXObject('ShockwaveFlash.ShockwaveFlash." + ii + "');");
							if (fl) {
								i_flash = true;
								break
							}
						} catch(e) {}
					}
				}
			}
			this.$i_flash = i_flash;
			return i_flash
		},
		flashInstall: function() {
			var content;
			$.ajax({
				type: "POST",
				url: FR.servletURL + "?op=flash_print&cmd=get_fp_installpath",
				complete: function(res, status) {
					if (status == "success") {
						var path = res.responseText;
						if (typeof path == "string" && path.length > 0 && path.length < 150) {
							content = '<div style="text-align:center;">' + FR.i18n.Please_Install + ' <a href="' + path + '">FlashPlayer</a>.<br>' + FR.i18n.Restart_Browser_After_Installation + "</div>"
						}
					}
					if (!content) {
						content = '<div style="text-align:center;">' + FR.i18n.Please_Install + ' <a href="http://get.adobe.com/flashplayer/" target="_blank">FlashPlayer</a>.<br>' + FR.i18n.Restart_Browser_After_Installation + "</div>"
					}
					FR.showDialog(FR.i18n.Alert, 305, 75, content)
				}
			})
		},
		doURLFlashPrint: function(config) {
			var url = arguments[0];
			var currentPageIndex;
			var isShowDialog;
			var isAutoZoom;
			if (typeof url == "string") {
				isShowDialog = arguments[1];
				isAutoZoom = isFitPaper();
				if (isShowDialog == undefined || !(typeof isShowDialog == "boolean")) {
					isShowDialog = isShowFlashPrintSetting()
				}
			} else {
				url = config.url;
				currentPageIndex = config.pageIndex;
				isShowDialog = config.isPopUp;
				isAutoZoom = config.isAutoZoom;
				if (isShowDialog == undefined || !(typeof isShowDialog == "boolean")) {
					isShowDialog = isShowFlashPrintSetting()
				}
				if (isAutoZoom == undefined || !(typeof isAutoZoom == "boolean")) {
					isAutoZoom = isFitPaper()
				}
			}
			if (FR.isInstalledFlash() === true) {
				if ($flashIframe == null) {
					FR.__flash__ = function() {
						FR.doPrintURL.call(this, url, currentPageIndex, isAutoZoom, config);
						delete FR.__flash__
					};
					$flashIframe = $("<iframe>").css({
						position: "absolute",
						left: -1000,
						top: -1000
					}).attr("src", FR.servletURL + "?op=resource&resource=/com/fr/web/core/printer.html").appendTo("body")
				} else {
					var A = navigator.userAgent.toLowerCase();
					alert("-----A-"+A);
					alert($.browser.msie +"&&"+ parseInt($.browser.version));
					alert(($.browser.msie && parseInt($.browser.version) < 90));
					alert("----1-"+$flashIframe[0]);
					alert("----2-"+$flashIframe[0].contentWindow.flashMovie);
					alert("----3-"+$flashIframe[0].contentDocument);
					var fm = ($.browser.msie && parseInt($.browser.version) < 90) ? $flashIframe[0].contentWindow.flashMovie: $flashIframe[0].contentDocument.flashMovie;
					alert("----4-"+fm);
					alert("----5-"+fm.doLoadFlash);
					if (fm != null && fm.doLoadFlash != null) {
						var servletURL = url.split("?")[0];
						currentPageIndex = currentPageIndex || 1;
						var sessionID = __getSessionID__(url, config);
						$.ajax({
							type: "POST",
							url: servletURL,
							data: {
								sessionID: sessionID,
								op: "flash_print",
								cmd: "get_fp_pageinfo"
							},
							complete: function(res, status) {
								if (!FR.versionRemind(res.responseText)) {
									return
								}
								var message_array = res.responseText.split("?");
								var info_array = message_array[0].split(";");
								if (info_array[0].indexOf("FAILPASS") != -1) {
									FR.Msg.toast("当前版本不支持Flash零客户端打印, 请升级您的lic");
									return
								}
								var init_page_setting = "1-" + info_array[0];
								var offset = message_array.length > 1 ? message_array[1].split(";") : ["0.0", "0.0"];
								var print_fn = function(page_setting, isAutoZoom, paperinfo, isPrintAsImage) {
									FR.showDialog(FR.i18n.Print, 250, 100, FR.i18n.Start_Print + "...");
									alert(servletURL+"--,-- "+sessionID+"--,-- "+FR.string2ints(page_setting).join(",")+"--,-- "+isAutoZoom+"--,-- "+paperinfo+"--,-- "+isPrintAsImage);
									fm.doLoadFlash(servletURL, sessionID, FR.string2ints(page_setting).join(","), isAutoZoom, paperinfo, isPrintAsImage);
									if (_g()) {
										_g().fireEvent("afterflashprint")
									}
								};
								if (isShowDialog) {
									var $content = $("<div>").appendTo("body").css("padding", 10);
									$("<table border=\"0\"><tr><td colspan='3' valign='middle' class='fh-nopadding'><input type='radio' name='range' value='all' checked>" + FR.i18n.HJS_All_Pages + "</td></tr><tr><td colspan='3' valign='middle' class='fh-nopadding'><input type='radio' name='range' value='current'>" + FR.i18n.HJS_Current_Page + ":" + FR.i18n.HF_The_Page_Number + " " + FR.i18n.is + " " + (currentPageIndex) + "</td></tr><tr><td colspan='3' valign='middle' class='fh-nopadding'><input type='radio' name='range' value='specify'>" + FR.i18n.HJS_Specified_Pages + ":<input type='text' name='specify' value=''>(" + FR.i18n.Example + ": 2,5,7-10,12)</td></tr><tr style='height:10px'><td colspan='3'/></tr><tr><td class='fh-nopadding' style='height:20px;'><div id=\"div1\"></div></td></tr><tr><td class='fh-nopadding' style='height:20px;'><div id=\"div2\"></div></td></tr><tr><td class='offset' style='font-size:12;color:red'>" + FR.i18n.SetPrinter_Offset + ": X=" + offset[0] + "mm Y=" + offset[1] + "mm</td></tr><tr><td style='width:250px'></td><td class='yes-btn'></td><td class='cancel-btn'></td></tr></table>").appendTo($content);
									var specify_editor = $("input:text", $content).val(init_page_setting);
									var print_image_checkboxEl = new FR.CheckBox({
										text: FR.i18n.Print_As_Image,
										renderEl: $("<div/>").appendTo($("#div1", $content)),
										selected: false,
										disabled: false
									});
									var fit_paper_checkboxEl = new FR.CheckBox({
										text: FR.i18n.Print_To_Fit_Paper_Size,
										renderEl: $("<div/>").appendTo($("#div2", $content)),
										selected: isFitPaper(),
										disabled: false
									});
									new FR.Button({
										text: FR.i18n.OK,
										render: false,
										renderEl: $("<div/>").appendTo($(".yes-btn", $content)),
										handler: function() {
											FR.closeDialog();
											switch ($("input:checked", $content).val()) {
											case "current":
												init_page_setting = (currentPageIndex) + "";
												break;
											case "specify":
												init_page_setting = specify_editor.val();
												break
											}
											print_fn(init_page_setting, fit_paper_checkboxEl.selected(), info_array[1] ? info_array[1] : "", print_image_checkboxEl.selected())
										}
									});
									new FR.Button({
										text: FR.i18n.Cancel,
										render: false,
										renderEl: $("<div/>").appendTo($(".cancel-btn", $content)),
										handler: function() {
											FR.closeDialog()
										}
									});
									FR.showDialog(FR.i18n.Setting, 400, "auto", $content)
								} else {
									print_fn(init_page_setting, isAutoZoom, info_array[1] ? info_array[1] : "", false)
								}
							}
						})
					} else {
						FR.flashInstall()
					}
				}
			} else {
				FR.flashInstall()
			}
		}
	})
})();
jQuery.fn.extend({
	__load__: function(o) {
		var cfg = $.extend({
			type: "GET",
			timeout: 30000
		},
		o || {});
		cfg.callback = cfg.callback || FR.emptyFn;
		var self = this;
		$.ajax({
			url: cfg.url,
			type: cfg.type,
			async: cfg.params.async == false ? false: true,
			dataType: "html",
			data: $.extend({
				_: new Date().getTime()
			},
			cfg.params),
			timeout: cfg.timeout,
			beforeSend: function() {
				self.__loading__(true, cfg.forceDisplay)
			},
			complete: function(res, status) {
				var html = res.responseText;
				if (html == "" || html.indexOf("出错页面") != -1) {
					window.needLoadMore = false
				}
				var scripts = [];
				if (cfg.scripts === true) {
					var hd = document.getElementsByTagName("head")[0];
					var re = /(?:<script([^>]*)?>)(?:(\n|\r|.))(?:<\/script>)/ig;
					var srcRe = /\ssrc=([\'\"])(.*?)\1/i;
					var typeRe = /\stype=([\'\"])(.*?)\1/i;
					var match;
					while (match = re.exec(html)) {
						var attrs = match[1];
						var srcMatch = attrs ? attrs.match(srcRe) : false;
						if (srcMatch && srcMatch[2]) {
							var s = document.createElement("script");
							s.src = srcMatch[2];
							var typeMatch = attrs.match(typeRe);
							if (typeMatch && typeMatch[2]) {
								s.type = typeMatch[2]
							}
							hd.appendChild(s)
						} else {
							if (match[2] && match[2].length > 0) {
								scripts.push(match[2])
							}
						}
					}
					html = html.replace(/(?:<script.*?>)(?:(\n|\r|.))(?:<\/script>)/ig, "")
				}
				self.__loading__(false);
				if (FR.isMobile()) {
					FR.hideLoadingMark()
				}
				self.html(html);
				$.each(scripts,
				function(i, n) {
					if (window.execScript) {
						window.execScript(n)
					} else {
						window.eval(n)
					}
				});
				self.each(cfg.callback, [res.responseText, status, res]);
				if ($.browser.msie && $.browser.version == "6.0") {
					setTimeout(function() {
						if (!$("div.pageContentDIV")[0]) {
							return
						}
						var tr = $(".pageContentDIV tr").last();
						if (tr[0] && !tr.height() && !tr.is(":visible")) {
							tr.show()
						}
						var tds = $(".pageContentDIV td");
						for (var i = 0,
						len = tds.length; i < len; i++) {
							var td = $(tds[i]);
							if (td.attr("rowspan") == 1) {
								td.attr("rowspan", 1);
								break
							}
						}
					},
					25)
				}
			}
		});
		return this
	},
	__loadingMoreData__: function(a, b, c) {
		this.__loading__(a, b, c, true)
	},
	__loading__: function(a, b, d, c) {
		var f = this.data("indicator");
		if (!f) {
			f = $("<div class='loading-indicator'>" + FR.i18n.Loading + "</div>");
			var e = c ? {
				position: "relative",
				width: "56px"
			}: {
				position: "absolute",
				left: 0,
				top: 0
			};
			if (d) {
				var e = $.extend(e, d)
			}
			f.css(e);
			this.data("indicator", f)
		}
		if (!a) {
			f.detach()
		} else {
			if (a && !f.is(":visible")) {
				f.css("top", this.scrollTop());
				this.append(f)
			}
		}
		return this
	},
	__scroll2View__: function(a, b) {
		var d = $(a)[0];
		return $.each(this,
		function(o, g) {
			var f = $(g).offset();
			var h = $(d).offset();
			var j = f.left - h.left + d.scrollLeft,
			q = f.top - h.top + d.scrollTop,
			n = q + g.offsetHeight,
			e = j + g.offsetWidth;
			var c = d.clientHeight;
			var k = parseInt(d.scrollTop, 10);
			var p = parseInt(d.scrollLeft, 10);
			var i = k + c;
			var m = p + d.clientWidth;
			if (g.offsetHeight > c || q < k) {
				d.scrollTop = q
			} else {
				if (n > i) {
					d.scrollTop = n - c
				}
			}
			if (b !== false) {
				if (g.offsetWidth > d.clientWidth || j < p) {
					d.scrollLeft = j
				} else {
					if (e > m) {
						d.scrollLeft = e - d.clientWidth
					}
				}
				d.scrollLeft = d.scrollLeft
			}
		})
	}
});
FR.layoutFrozen = function(j, h) {
	var f = $(".frozen-center", j);
	var e = $(".frozen-corner", j);
	var d = $(".frozen-north", j);
	var i = $(".frozen-west", j);
	if (f.length > 0 || e.length > 0 || d.length > 0 || i.length > 0) {
		var c = function() {
			var n = 0;
			var m = $(window).height() - d.height();
			if ($(".parameter-container").height() > 0) {
				var o = $(".parameter-container-collapse").parent();
				var k = o.isVisible();
				var l = o.height();
				if (k && h < l) {
					h += l
				} else {
					if (!k && h > l) {
						h -= l
					}
				}
			}
			m -= h;
			return m
		};
		var b = $(".pageContentDIV", this.$contentPane);
		var g = b.hasClass("contentDIV");
		var a = function() {
			var k = $(window).width() - i.width();
			var l = c();
			if (g) {
				k -= parseInt($(".report-background").css("left"));
				l -= (parseInt($(".report-background").css("top")) + 24)
			}
			f.width(k);
			d.width(k);
			f.height(l);
			i.height(l)
		};
		a();
		f.scroll(function() {
			d.scrollLeft(f.scrollLeft());
			i.scrollTop(f.scrollTop())
		}.createDelegate(this));
		window.onresize = function() {
			a()
		}
	}
};
$.extend(FR, {
	Keys: function() {
		var a = [];
		var b = true;
		$(document).keydown(function(d) {
			if (b) {
				if (a.length > 0) {
					var c = a[0];
					if ($.isFunction(c)) {
						c.call(window, d)
					} else {
						if ($.isFunction(c.fn)) {
							c.fn.call(c.scope || window, d)
						}
					}
				}
			}
		});
		return {
			reg: function(c) {
				a.remove(c);
				a.unshift(c)
			},
			unreg: function(c) {
				a.remove(c)
			},
			enable: function(c) {
				if (typeof c == "boolean") {
					b = c
				} else {
					return b
				}
			}
		}
	} ()
});
$.extend(Date.prototype, {
	format: function(b) {
		if (Date.formatFunctions[b] == null) {
			Date.createNewFormat(b)
		}
		var a = Date.formatFunctions[b];
		return this[a]()
	},
	getTimezone: function() {
		return this.toString().replace(/^.* (?:\((.*)\)|([A-Z]{1,4})(?:[\-+][0-9]{4})?(?: -?\d+)?)$/, "$1$2").replace(/[^A-Z]/g, "")
	},
	getGMTOffset: function(a) {
		return (this.getTimezoneOffset() > 0 ? "-": "+") + String.leftPad(Math.abs(Math.floor(this.getTimezoneOffset() / 60)), 2, "0") + (a ? ":": "") + String.leftPad(this.getTimezoneOffset() % 60, 2, "0")
	},
	getDayOfYear: function() {
		var a = 0;
		Date.daysInMonth[1] = this.isLeapYear() ? 29 : 28;
		for (var b = 0; b < this.getMonth(); ++b) {
			a += Date.daysInMonth[b]
		}
		return a + this.getDate() - 1
	},
	getWeekOfYear: function() {
		var b = 86400000;
		var c = 7 * b;
		var d = Date.UTC(this.getFullYear(), this.getMonth(), this.getDate() + 3) / b;
		var a = Math.floor(d / 7);
		var e = new Date(a * c).getUTCFullYear();
		return a - Math.floor(Date.UTC(e, 0, 7) / c) + 1
	},
	isLeapYear: function() {
		var a = this.getFullYear();
		return ((a & 3) == 0 && (a % 100 || (a % 400 == 0 && a)))
	},
	getFirstDayOfMonth: function() {
		var a = (this.getDay() - (this.getDate() - 1)) % 7;
		return (a < 0) ? (a + 7) : a
	},
	getLastDayOfMonth: function() {
		var a = (this.getDay() + (Date.daysInMonth[this.getMonth()] - this.getDate())) % 7;
		return (a < 0) ? (a + 7) : a
	},
	getFirstDateOfMonth: function() {
		return new Date(this.getFullYear(), this.getMonth(), 1)
	},
	getLastDateOfMonth: function() {
		return new Date(this.getFullYear(), this.getMonth(), this.getDaysInMonth())
	},
	getDaysInMonth: function() {
		Date.daysInMonth[1] = this.isLeapYear() ? 29 : 28;
		return Date.daysInMonth[this.getMonth()]
	},
	getSuffix: function() {
		switch (this.getDate()) {
		case 1:
		case 21:
		case 31:
			return "st";
		case 2:
		case 22:
			return "nd";
		case 3:
		case 23:
			return "rd";
		default:
			return "th"
		}
	},
	clone: function() {
		return new Date(this.getTime())
	},
	clearTime: function(a) {
		if (a) {
			return this.clone().clearTime()
		}
		this.setHours(0);
		this.setMinutes(0);
		this.setSeconds(0);
		this.setMilliseconds(0);
		return this
	},
	add: function(b, c) {
		var e = this.clone();
		if (!b || c === 0) {
			return e
		}
		switch (b.toLowerCase()) {
		case Date.MILLI:
			e.setMilliseconds(this.getMilliseconds() + c);
			break;
		case Date.SECOND:
			e.setSeconds(this.getSeconds() + c);
			break;
		case Date.MINUTE:
			e.setMinutes(this.getMinutes() + c);
			break;
		case Date.HOUR:
			e.setHours(this.getHours() + c);
			break;
		case Date.DAY:
			e.setDate(this.getDate() + c);
			break;
		case Date.MONTH:
			var a = this.getDate();
			if (a > 28) {
				a = Math.min(a, this.getFirstDateOfMonth().add("mo", c).getLastDateOfMonth().getDate())
			}
			e.setDate(a);
			e.setMonth(this.getMonth() + c);
			break;
		case Date.YEAR:
			e.setFullYear(this.getFullYear() + c);
			break
		}
		return e
	},
	between: function(c, a) {
		var b = this.getTime();
		return c.getTime() <= b && b <= a.getTime()
	}
}); (function() {
	Date.patterns = {
		ISO8601Long: "Y-m-d H:i:s",
		ISO8601Short: "Y-m-d",
		ShortDate: "n/j/Y",
		LongDate: "l, F d, Y",
		FullDateTime: "l, F d, Y g:i:s A",
		MonthDay: "F d",
		ShortTime: "g:i A",
		LongTime: "g:i:s A",
		SortableDateTime: "Y-m-d\\TH:i:s",
		UniversalSortableDateTime: "Y-m-d H:i:sO",
		YearMonth: "F, Y"
	};
	Date.parseFunctions = {
		count: 0
	};
	Date.parseRegexes = [];
	Date.formatFunctions = {
		count: 0
	};
	Date.daysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
	Date.monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
	Date.getShortMonthName = function(month) {
		return Date.monthNames[month].substring(0, 3)
	};
	Date.dayNames = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
	Date.getShortDayName = function(day) {
		return Date.dayNames[day].substring(0, 3)
	};
	Date.y2kYear = 50;
	Date.monthNumbers = {
		Jan: 0,
		Feb: 1,
		Mar: 2,
		Apr: 3,
		May: 4,
		Jun: 5,
		Jul: 6,
		Aug: 7,
		Sep: 8,
		Oct: 9,
		Nov: 10,
		Dec: 11
	};
	Date.MILLI = "ms";
	Date.SECOND = "s";
	Date.MINUTE = "mi";
	Date.HOUR = "h";
	Date.DAY = "d";
	Date.MONTH = "mo";
	Date.YEAR = "y";
	Date.getMonthNumber = function(name) {
		return Date.monthNumbers[name.substring(0, 1).toUpperCase() + name.substring(1, 3).toLowerCase()]
	};
	Date.parseDate = function(input, format) {
		if (Date.parseFunctions[format] == null) {
			Date.createParser(format)
		}
		var func = Date.parseFunctions[format];
		return Date[func](input)
	};
	Date.createNewFormat = function(format) {
		var funcName = "format" + Date.formatFunctions.count++;
		Date.formatFunctions[format] = funcName;
		var code = "Date.prototype." + funcName + " = function(){return ";
		var special = false;
		var ch = "";
		for (var i = 0; i < format.length; ++i) {
			ch = format.charAt(i);
			if (!special && ch == "\\") {
				special = true
			} else {
				if (special) {
					special = false;
					code += "'" + String.escape(ch) + "' + "
				} else {
					code += Date.getFormatCode(ch)
				}
			}
		}
		eval(code.substring(0, code.length - 3) + ";}")
	};
	Date.getFormatCode = function(character) {
		switch (character) {
		case "d":
			return "String.leftPad(this.getDate(), 2, '0') + ";
		case "D":
			return "Date.getShortDayName(this.getDay()) + ";
		case "j":
			return "this.getDate() + ";
		case "l":
			return "Date.dayNames[this.getDay()] + ";
		case "N":
			return "(this.getDay() ? this.getDay() : 7) + ";
		case "S":
			return "this.getSuffix() + ";
		case "w":
			return "this.getDay() + ";
		case "z":
			return "this.getDayOfYear() + ";
		case "W":
			return "String.leftPad(this.getWeekOfYear(), 2, '0') + ";
		case "F":
			return "Date.monthNames[this.getMonth()] + ";
		case "m":
			return "String.leftPad(this.getMonth() + 1, 2, '0') + ";
		case "M":
			return "Date.getShortMonthName(this.getMonth()) + ";
		case "n":
			return "(this.getMonth() + 1) + ";
		case "t":
			return "this.getDaysInMonth() + ";
		case "L":
			return "(this.isLeapYear() ? 1 : 0) + ";
		case "o":
			return "(this.getFullYear() + (this.getWeekOfYear() == 1 && this.getMonth() > 0 ? +1 : (this.getWeekOfYear() >= 52 && this.getMonth() < 11 ? -1 : 0))) + ";
		case "Y":
			return "this.getFullYear() + ";
		case "y":
			return "('' + this.getFullYear()).substring(2, 4) + ";
		case "a":
			return "(this.getHours() < 12 ? 'am' : 'pm') + ";
		case "A":
			return "(this.getHours() < 12 ? 'AM' : 'PM') + ";
		case "g":
			return "((this.getHours() % 12) ? this.getHours() % 12 : 12) + ";
		case "G":
			return "this.getHours() + ";
		case "h":
			return "String.leftPad((this.getHours() % 12) ? this.getHours() % 12 : 12, 2, '0') + ";
		case "H":
			return "String.leftPad(this.getHours(), 2, '0') + ";
		case "i":
			return "String.leftPad(this.getMinutes(), 2, '0') + ";
		case "s":
			return "String.leftPad(this.getSeconds(), 2, '0') + ";
		case "u":
			return "String.leftPad(this.getMilliseconds(), 3, '0') + ";
		case "O":
			return "this.getGMTOffset() + ";
		case "P":
			return "this.getGMTOffset(true) + ";
		case "T":
			return "this.getTimezone() + ";
		case "Z":
			return "(this.getTimezoneOffset() * -60) + ";
		case "c":
			for (var df = Date.getFormatCode,
			c = "Y-m-dTH:i:sP",
			code = "",
			i = 0,
			l = c.length; i < l; ++i) {
				var e = c.charAt(i);
				code += e == "T" ? "'T' + ": df(e)
			}
			return code;
		case "U":
			return "Math.round(this.getTime() / 1000) + ";
		default:
			return "'" + String.escape(character) + "' + "
		}
	};
	Date.createParser = function(format) {
		var funcName = "parse" + Date.parseFunctions.count++;
		var regexNum = Date.parseRegexes.length;
		var currentGroup = 1;
		Date.parseFunctions[format] = funcName;
		var code = "Date." + funcName + " = function(input){\nvar y = -1, m = -1, d = -1, h = -1, i = -1, s = -1, ms = -1, o, z, u, v;\nvar d = new Date();\ny = d.getFullYear();\nm = d.getMonth();\nd = d.getDate();\nvar results = input.match(Date.parseRegexes[" + regexNum + "]);\nif (results && results.length > 0) {";
		var regex = "";
		var special = false;
		var ch = "";
		for (var i = 0; i < format.length; ++i) {
			ch = format.charAt(i);
			if (!special && ch == "\\") {
				special = true
			} else {
				if (special) {
					special = false;
					regex += String.escape(ch)
				} else {
					var obj = Date.formatCodeToRegex(ch, currentGroup);
					currentGroup += obj.g;
					regex += obj.s;
					if (obj.g && obj.c) {
						code += obj.c
					}
				}
			}
		}
		code += "if (u)\n{v = new Date(u * 1000);}else if (y >= 0 && m >= 0 && d > 0 && h >= 0 && i >= 0 && s >= 0 && ms >= 0)\n{v = new Date(y, m, d, h, i, s, ms);}\nelse if (y >= 0 && m >= 0 && d > 0 && h >= 0 && i >= 0 && s >= 0)\n{v = new Date(y, m, d, h, i, s);}\nelse if (y >= 0 && m >= 0 && d > 0 && h >= 0 && i >= 0)\n{v = new Date(y, m, d, h, i);}\nelse if (y >= 0 && m >= 0 && d > 0 && h >= 0)\n{v = new Date(y, m, d, h);}\nelse if (y >= 0 && m >= 0 && d > 0)\n{v = new Date(y, m, d);}\nelse if (y >= 0 && m >= 0)\n{v = new Date(y, m);}\nelse if (y >= 0)\n{v = new Date(y);}\n}return (v && (z || o))?\n    (z ? v.add(Date.SECOND, (v.getTimezoneOffset() * 60) + (z*1)) :\n        v.add(Date.HOUR, (v.getGMTOffset() / 100) + (o / -100))) : v\n;}";
		Date.parseRegexes[regexNum] = new RegExp("^" + regex + "$", "i");
		eval(code)
	};
	Date.formatCodeToRegex = function(character, currentGroup) {
		switch (character) {
		case "d":
			return {
				g:
				1,
				c: "d = parseInt(results[" + currentGroup + "], 10);\n",
				s: "(\\d{2})"
			};
		case "D":
			for (var a = [], i = 0; i < 7; a.push(Date.getShortDayName(i)), ++i) {}
			return {
				g: 0,
				c: null,
				s: "(?:" + a.join("|") + ")"
			};
		case "j":
			return {
				g:
				1,
				c: "d = parseInt(results[" + currentGroup + "], 10);\n",
				s: "(\\d{1,2})"
			};
		case "l":
			return {
				g:
				0,
				c: null,
				s: "(?:" + Date.dayNames.join("|") + ")"
			};
		case "N":
			return {
				g:
				0,
				c: null,
				s: "[1-7]"
			};
		case "S":
			return {
				g:
				0,
				c: null,
				s: "(?:st|nd|rd|th)"
			};
		case "w":
			return {
				g:
				0,
				c: null,
				s: "[0-6]"
			};
		case "z":
			return {
				g:
				0,
				c: null,
				s: "(?:\\d{1,3}"
			};
		case "W":
			return {
				g:
				0,
				c: null,
				s: "(?:\\d{2})"
			};
		case "F":
			return {
				g:
				1,
				c: "m = parseInt(Date.getMonthNumber(results[" + currentGroup + "]), 10);\n",
				s: "(" + Date.monthNames.join("|") + ")"
			};
		case "m":
			return {
				g:
				1,
				c: "m = parseInt(results[" + currentGroup + "], 10) - 1;\n",
				s: "(\\d{2})"
			};
		case "M":
			for (var a = [], i = 0; i < 12; a.push(Date.getShortMonthName(i)), ++i) {}
			return {
				g: 1,
				c: "m = parseInt(Date.getMonthNumber(results[" + currentGroup + "]), 10);\n",
				s: "(" + a.join("|") + ")"
			};
		case "n":
			return {
				g:
				1,
				c: "m = parseInt(results[" + currentGroup + "], 10) - 1;\n",
				s: "(\\d{1,2})"
			};
		case "t":
			return {
				g:
				0,
				c: null,
				s: "(?:\\d{2})"
			};
		case "L":
			return {
				g:
				0,
				c: null,
				s: "(?:1|0)"
			};
		case "o":
		case "Y":
			return {
				g:
				1,
				c: "y = parseInt(results[" + currentGroup + "], 10);\n",
				s: "(\\d{4})"
			};
		case "y":
			return {
				g:
				1,
				c: "var ty = parseInt(results[" + currentGroup + "], 10);\ny = ty > Date.y2kYear ? 1900 + ty : 2000 + ty;\n",
				s: "(\\d{1,2})"
			};
		case "a":
			return {
				g:
				1,
				c: "if (results[" + currentGroup + "] == 'am') {\nif (h == 12) { h = 0; }\n} else { if (h < 12) { h += 12; }}",
				s: "(am|pm)"
			};
		case "A":
			return {
				g:
				1,
				c: "if (results[" + currentGroup + "] == 'AM') {\nif (h == 12) { h = 0; }\n} else { if (h < 12) { h += 12; }}",
				s: "(AM|PM)"
			};
		case "g":
		case "G":
			return {
				g:
				1,
				c: "h = parseInt(results[" + currentGroup + "], 10);\n",
				s: "(\\d{1,2})"
			};
		case "h":
		case "H":
			return {
				g:
				1,
				c: "h = parseInt(results[" + currentGroup + "], 10);\n",
				s: "(\\d{2})"
			};
		case "i":
			return {
				g:
				1,
				c: "i = parseInt(results[" + currentGroup + "], 10);\n",
				s: "(\\d{2})"
			};
		case "s":
			return {
				g:
				1,
				c: "s = parseInt(results[" + currentGroup + "], 10);\n",
				s: "(\\d{2})"
			};
		case "u":
			return {
				g:
				1,
				c: "ms = parseInt(results[" + currentGroup + "], 10);\n",
				s: "(\\d{3})"
			};
		case "O":
			return {
				g:
				1,
				c: ["o = results[", currentGroup, "];\n", "var sn = o.substring(0,1);\n", "var hr = o.substring(1,3)*1 + Math.floor(o.substring(3,5) / 60);\n", "var mn = o.substring(3,5) % 60;\n", "o = ((-12 <= (hr*60 + mn)/60) && ((hr*60 + mn)/60 <= 14))?\n", "    (sn + String.leftPad(hr, 2, 0) + String.leftPad(mn, 2, 0)) : null;\n"].join(""),
				s: "([+-]\\d{4})"
			};
		case "P":
			return {
				g:
				1,
				c: ["o = results[", currentGroup, "];\n", "var sn = o.substring(0,1);\n", "var hr = o.substring(1,3)*1 + Math.floor(o.substring(4,6) / 60);\n", "var mn = o.substring(4,6) % 60;\n", "o = ((-12 <= (hr*60 + mn)/60) && ((hr*60 + mn)/60 <= 14))?\n", "    (sn + String.leftPad(hr, 2, 0) + String.leftPad(mn, 2, 0)) : null;\n"].join(""),
				s: "([+-]\\d{2}:\\d{2})"
			};
		case "T":
			return {
				g:
				0,
				c: null,
				s: "[A-Z]{1,4}"
			};
		case "Z":
			return {
				g:
				1,
				c: "z = results[" + currentGroup + "] * 1;\nz = (-43200 <= z && z <= 50400)? z : null;\n",
				s: "([+-]?\\d{1,5})"
			};
		case "c":
			var df = Date.formatCodeToRegex,
			calc = [];
			var arr = [df("Y", 1), df("m", 2), df("d", 3), df("h", 4), df("i", 5), df("s", 6), df("P", 7)];
			for (var i = 0,
			l = arr.length; i < l; ++i) {
				calc.push(arr[i].c)
			}
			return {
				g: 1,
				c: calc.join(""),
				s: arr[0].s + "-" + arr[1].s + "-" + arr[2].s + "T" + arr[3].s + ":" + arr[4].s + ":" + arr[5].s + arr[6].s
			};
		case "U":
			return {
				g:
				1,
				c: "u = parseInt(results[" + currentGroup + "], 10);\n",
				s: "(-?\\d+)"
			};
		default:
			return {
				g:
				0,
				c: null,
				s: character.replace(/([.*+?^$}{()|[\]\/\\])/g, "\\$1")
			}
		}
	};
	if ($.browser.safari) {
		Date.brokenSetMonth = Date.prototype.setMonth;
		Date.prototype.setMonth = function(num) {
			if (num <= -1) {
				var n = Math.ceil( - num);
				var back_year = Math.ceil(n / 12);
				var month = (n % 12) ? 12 - n % 12 : 0;
				this.setFullYear(this.getFullYear() - back_year);
				return Date.brokenSetMonth.call(this, month)
			} else {
				return Date.brokenSetMonth.apply(this, arguments)
			}
		}
	}
})();
var deployJava = {
	debug: null,
	firefoxJavaVersion: null,
	myInterval: null,
	preInstallJREList: null,
	returnPage: null,
	brand: null,
	locale: null,
	installType: null,
	EAInstallEnabled: false,
	EarlyAccessURL: null,
	getJavaURL: "http://java.sun.com/webapps/getjava/BrowserRedirect?host=java.com",
	appleRedirectPage: "http://www.apple.com/support/downloads/",
	oldMimeType: "application/npruntime-scriptable-plugin;DeploymentToolkit",
	mimeType: "application/java-deployment-toolkit",
	launchButtonPNG: "http://java.sun.com/products/jfc/tsc/articles/swing2d/webstart.png",
	browserName: null,
	browserName2: null,
	getJREs: function() {
		var e = new Array();
		if (deployJava.isPluginInstalled()) {
			var d = deployJava.getPlugin();
			var a = d.jvms;
			for (var c = 0; c < a.getLength(); c++) {
				e[c] = a.get(c).version
			}
		} else {
			var b = deployJava.getBrowser();
			if (b == "MSIE") {
				if (deployJava.testUsingActiveX("1.7.0")) {
					e[0] = "1.7.0"
				} else {
					if (deployJava.testUsingActiveX("1.6.0")) {
						e[0] = "1.6.0"
					} else {
						if (deployJava.testUsingActiveX("1.5.0")) {
							e[0] = "1.5.0"
						} else {
							if (deployJava.testUsingActiveX("1.4.2")) {
								e[0] = "1.4.2"
							} else {
								if (deployJava.testForMSVM()) {
									e[0] = "1.1"
								}
							}
						}
					}
				}
			} else {
				if (b == "Netscape Family") {
					deployJava.getJPIVersionUsingMimeType();
					if (deployJava.firefoxJavaVersion != null) {
						e[0] = deployJava.firefoxJavaVersion
					} else {
						if (deployJava.testUsingMimeTypes("1.7")) {
							e[0] = "1.7.0"
						} else {
							if (deployJava.testUsingMimeTypes("1.6")) {
								e[0] = "1.6.0"
							} else {
								if (deployJava.testUsingMimeTypes("1.5")) {
									e[0] = "1.5.0"
								} else {
									if (deployJava.testUsingMimeTypes("1.4.2")) {
										e[0] = "1.4.2"
									} else {
										if (deployJava.browserName2 == "Safari") {
											if (deployJava.testUsingPluginsArray("1.7.0")) {
												e[0] = "1.7.0"
											} else {
												if (deployJava.testUsingPluginsArray("1.6")) {
													e[0] = "1.6.0"
												} else {
													if (deployJava.testUsingPluginsArray("1.5")) {
														e[0] = "1.5.0"
													} else {
														if (deployJava.testUsingPluginsArray("1.4.2")) {
															e[0] = "1.4.2"
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if (deployJava.debug) {
			for (var c = 0; c < e.length; ++c) {
				alert("We claim to have detected Java SE " + e[c])
			}
		}
		return e
	},
	installJRE: function(b) {
		var a = false;
		if (deployJava.isPluginInstalled()) {
			if (deployJava.getPlugin().installJRE(b)) {
				deployJava.refresh();
				if (deployJava.returnPage != null) {
					document.location = deployJava.returnPage
				}
				return true
			} else {
				return false
			}
		} else {
			return deployJava.installLatestJRE()
		}
	},
	installLatestJRE: function() {
		if (deployJava.isPluginInstalled()) {
			if (deployJava.getPlugin().installLatestJRE()) {
				deployJava.refresh();
				if (deployJava.returnPage != null) {
					document.location = deployJava.returnPage
				}
				return true
			} else {
				return false
			}
		} else {
			var b = deployJava.getBrowser();
			var a = navigator.platform.toLowerCase();
			if ((deployJava.EAInstallEnabled == "true") && (a.indexOf("win") != -1) && (deployJava.EarlyAccessURL != null)) {
				deployJava.preInstallJREList = deployJava.getJREs();
				if (deployJava.returnPage != null) {
					deployJava.myInterval = setInterval("deployJava.poll()", 3000)
				}
				location.href = deployJava.EarlyAccessURL;
				return false
			} else {
				if (b == "MSIE") {
					return deployJava.IEInstall()
				} else {
					if ((b == "Netscape Family") && (a.indexOf("win32") != -1)) {
						return deployJava.FFInstall()
					} else {
						location.href = deployJava.getJavaURL + ((deployJava.returnPage != null) ? ("&returnPage=" + deployJava.returnPage) : "") + ((deployJava.locale != null) ? ("&locale=" + deployJava.locale) : "") + ((deployJava.brand != null) ? ("&brand=" + deployJava.brand) : "")
					}
				}
				return false
			}
		}
	},
	runApplet: function(b, f, d) {
		if (d == "undefined" || d == null) {
			d = "1.1"
		}
		var e = "^(\\d+)(?:\\.(\\d+)(?:\\.(\\d+)(?:_(\\d+))?)?)?$";
		var a = d.match(e);
		if (deployJava.returnPage == null) {
			deployJava.returnPage = document.location
		}
		if (a != null) {
			var c = deployJava.getBrowser();
			if ((c != "?") && ("Safari" != deployJava.browserName2)) {
				if (deployJava.versionCheck(d + "+")) {
					deployJava.writeAppletTag(b, f)
				} else {
					if (b && b.codebase) {
						deployJava.writeAppletTag(b, f)
					} else {
						if (deployJava.installJRE(d + "+")) {
							deployJava.refresh();
							location.href = document.location;
							deployJava.writeAppletTag(b, f)
						}
					}
				}
			} else {
				deployJava.writeAppletTag(b, f)
			}
		} else {
			if (deployJava.debug) {
				alert("Invalid minimumVersion argument to runApplet():" + d)
			}
		}
	},
	writeAppletTag: function(a, f) {
		if ($("applet").length > 0) {
			$("body").append($("applet"));
			return
		}
		if ($.browser.msie) {
			var h = [];
			var d = "<OBJECT ID='AppletPrinter' name='AppletPrinter' WIDTH='0' HEIGHT='0' CLASSID='clsid:8AD9C840-044E-11D1-B3E9-00805F499D93'";
			var c = false;
			for (var e in a) {
				d += (" " + e + '="' + a[e] + '"')
			}
			d += ">";
			h[h.length] = d;
			if (f != "undefined" && f != null) {
				for (var g in f) {
					d = '<param name="' + g + '" value="' + f[g] + '">';
					h[h.length] = d
				}
			}
			h[h.length] = ("</OBJECT>");
			$("body").append(h.join(""))
		} else {
			var h = [];
			var d = "<applet ";
			var c = false;
			for (var e in a) {
				d += (" " + e + '="' + a[e] + '"');
				if (e == "code") {
					c = true
				}
			}
			if (!c) {
				d += (' code="dummy"')
			}
			d += ">";
			h[h.length] = d;
			if (f != "undefined" && f != null) {
				var b = false;
				for (var g in f) {
					if (g == "codebase_lookup") {
						b = true
					}
					d = '<param name="' + g + '" value="' + f[g] + '">';
					h[h.length] = d
				}
				if (!b) {
					h[h.length] = ('<param name="codebase_lookup" value="false">')
				}
			}
			h[h.length] = ("</applet>");
			$("body").append(h.join(""))
		}
	},
	versionCheck: function(h) {
		var c = 0;
		var f = "^(\\d+)(?:\\.(\\d+)(?:\\.(\\d+)(?:_(\\d+))?)?)?(\\*|\\+)?$";
		var a = h.match(f);
		if (a != null) {
			var e = true;
			var b = new Array();
			for (var d = 1; d < a.length; ++d) {
				if ((typeof a[d] == "string") && (a[d] != "")) {
					b[c] = a[d];
					c++
				}
			}
			if (b[b.length - 1] == "+") {
				e = false;
				b.length--
			} else {
				if (b[b.length - 1] == "*") {
					b.length--
				}
			}
			var g = deployJava.getJREs();
			for (var d = 0; d < g.length; ++d) {
				if (deployJava.compareVersionToPattern(g[d], b, e)) {
					return true
				}
			}
			return false
		} else {
			alert("Invalid versionPattern passed to versionCheck: " + h);
			return false
		}
	},
	isWebStartInstalled: function(d) {
		var c = deployJava.getBrowser();
		if ((c == "?") || ("Safari" == deployJava.browserName2)) {
			return true
		}
		if (d == "undefined" || d == null) {
			d = "1.4.2"
		}
		var b = false;
		var e = "^(\\d+)(?:\\.(\\d+)(?:\\.(\\d+)(?:_(\\d+))?)?)?$";
		var a = d.match(e);
		if (a != null) {
			b = deployJava.versionCheck(d + "+")
		} else {
			if (deployJava.debug) {
				alert("Invalid minimumVersion argument to isWebStartInstalled(): " + d)
			}
			b = deployJava.versionCheck("1.4.2+")
		}
		return b
	},
	getJPIVersionUsingMimeType: function() {
		for (var b = 0; b < navigator.mimeTypes.length; ++b) {
			var c = navigator.mimeTypes[b].type;
			var a = c.match(/^application\/x-java-applet;jpi-version=(.*)$/);
			if (a != null) {
				deployJava.firefoxJavaVersion = a[1];
				if ("Opera" != deployJava.browserName2) {
					break
				}
			}
		}
	},
	launchWebStartApplication: function(a) {
		return false
	},
	createWebStartLaunchButtonEx: function(c, b) {
		if (deployJava.returnPage == null) {
			deployJava.returnPage = c
		}
		var a = "javascript:deployJava.launchWebStartApplication('" + c + "');";
		document.write('<a href="' + a + '" onMouseOver="window.status=\'\'; return true;"><img src="' + deployJava.launchButtonPNG + '" border="0" /></a>')
	},
	createWebStartLaunchButton: function(c, b) {
		if (deployJava.returnPage == null) {
			deployJava.returnPage = c
		}
		var a = "javascript:if (!deployJava.isWebStartInstalled(&quot;" + b + "&quot;)) {if (deployJava.installLatestJRE()) {if (deployJava.launch(&quot;" + c + "&quot;)) {}}} else {if (deployJava.launch(&quot;" + c + "&quot;)) {}}";
		document.write('<a href="' + a + '" onMouseOver="window.status=\'\'; return true;"><img src="' + deployJava.launchButtonPNG + '" border="0" /></a>')
	},
	launch: function(a) {
		document.location = a;
		return true
	},
	isPluginInstalled: function() {
		var a = deployJava.getPlugin();
		if (a && a.jvms) {
			return true
		} else {
			return false
		}
	},
	isAutoUpdateEnabled: function() {
		if (deployJava.isPluginInstalled()) {
			return deployJava.getPlugin().isAutoUpdateEnabled()
		}
		return false
	},
	setAutoUpdateEnabled: function() {
		if (deployJava.isPluginInstalled()) {
			return deployJava.getPlugin().setAutoUpdateEnabled()
		}
		return false
	},
	setInstallerType: function(a) {
		deployJava.installType = a;
		if (deployJava.isPluginInstalled()) {
			return deployJava.getPlugin().setInstallerType(a)
		}
		return false
	},
	setAdditionalPackages: function(a) {
		if (deployJava.isPluginInstalled()) {
			return deployJava.getPlugin().setAdditionalPackages(a)
		}
		return false
	},
	setEarlyAccess: function(a) {
		deployJava.EAInstallEnabled = a
	},
	isPlugin2: function() {
		if (deployJava.isPluginInstalled()) {
			if (deployJava.versionCheck("1.6.0_10+")) {
				try {
					return deployJava.getPlugin().isPlugin2()
				} catch(a) {}
			}
		}
		return false
	},
	allowPlugin: function() {
		deployJava.getBrowser();
		var a = ("Safari" != deployJava.browserName2 && "Opera" != deployJava.browserName2);
		return a
	},
	getPlugin: function() {
		deployJava.refresh();
		var a = null;
		if (deployJava.allowPlugin()) {
			a = document.getElementById("deployJavaPlugin")
		}
		return a
	},
	compareVersionToPattern: function(f, b, c) {
		var g = "^(\\d+)(?:\\.(\\d+)(?:\\.(\\d+)(?:_(\\d+))?)?)?$";
		var h = f.match(g);
		if (h != null) {
			var e = 0;
			var j = new Array();
			for (var d = 1; d < h.length; ++d) {
				if ((typeof h[d] == "string") && (h[d] != "")) {
					j[e] = h[d];
					e++
				}
			}
			var a = Math.min(j.length, b.length);
			if (c) {
				for (var d = 0; d < a; ++d) {
					if (j[d] != b[d]) {
						return false
					}
				}
				return true
			} else {
				for (var d = 0; d < a; ++d) {
					if (j[d] < b[d]) {
						return false
					} else {
						if (j[d] > b[d]) {
							return true
						}
					}
				}
				return true
			}
		} else {
			return false
		}
	},
	getBrowser: function() {
		if (deployJava.browserName == null) {
			var a = navigator.userAgent.toLowerCase();
			if (deployJava.debug) {
				alert("userAgent -> " + a)
			}
			if (a.indexOf("msie") != -1) {
				deployJava.browserName = "MSIE";
				deployJava.browserName2 = "MSIE"
			} else {
				if (a.indexOf("firefox") != -1) {
					deployJava.browserName = "Netscape Family";
					deployJava.browserName2 = "Firefox"
				} else {
					if (a.indexOf("chrome") != -1) {
						deployJava.browserName = "Netscape Family";
						deployJava.browserName2 = "Chrome"
					} else {
						if (a.indexOf("safari") != -1) {
							deployJava.browserName = "Netscape Family";
							deployJava.browserName2 = "Safari"
						} else {
							if (a.indexOf("mozilla") != -1) {
								deployJava.browserName = "Netscape Family";
								deployJava.browserName2 = "Other"
							} else {
								if (a.indexOf("opera") != -1) {
									deployJava.browserName = "Netscape Family";
									deployJava.browserName2 = "Opera"
								} else {
									deployJava.browserName = "?";
									deployJava.browserName2 = "unknown"
								}
							}
						}
					}
				}
			}
			if (deployJava.debug) {
				alert("Detected browser name:" + deployJava.browserName + ", " + deployJava.browserName2)
			}
		}
		return deployJava.browserName
	},
	testUsingActiveX: function(a) {
		var c = "JavaWebStart.isInstalled." + a + ".0";
		if (!ActiveXObject) {
			if (deployJava.debug) {
				alert("Browser claims to be IE, but no ActiveXObject object?")
			}
			return false
		}
		try {
			return (new ActiveXObject(c) != null)
		} catch(b) {
			return false
		}
	},
	testForMSVM: function() {
		var b = "{08B0E5C0-4FCB-11CF-AAA5-00401C608500}";
		if (typeof oClientCaps != "undefined") {
			var a = oClientCaps.getComponentVersion(b, "ComponentID");
			if ((a == "") || (a == "5,0,5000,0")) {
				return false
			} else {
				return true
			}
		} else {
			return false
		}
	},
	testUsingMimeTypes: function(b) {
		if (!navigator.mimeTypes) {
			if (deployJava.debug) {
				alert("Browser claims to be Netscape family, but no mimeTypes[] array?")
			}
			return false
		}
		for (var c = 0; c < navigator.mimeTypes.length; ++c) {
			s = navigator.mimeTypes[c].type;
			var a = s.match(/^application\/x-java-applet\x3Bversion=(1\.8|1\.7|1\.6|1\.5|1\.4\.2)$/);
			if (a != null) {
				if (deployJava.compareVersions(a[1], b)) {
					return true
				}
			}
		}
		return false
	},
	testUsingPluginsArray: function(b) {
		if ((!navigator.plugins) || (!navigator.plugins.length)) {
			return false
		}
		var a = navigator.platform.toLowerCase();
		for (var c = 0; c < navigator.plugins.length; ++c) {
			s = navigator.plugins[c].description;
			if (s.search(/^Java Switchable Plug-in (Cocoa)/) != -1) {
				if (deployJava.compareVersions("1.5.0", b)) {
					return true
				}
			} else {
				if (s.search(/^Java/) != -1) {
					if (a.indexOf("win") != -1) {
						if (deployJava.compareVersions("1.5.0", b) || deployJava.compareVersions("1.6.0", b)) {
							return true
						}
					}
				}
			}
		}
		if (deployJava.compareVersions("1.5.0", b)) {
			return true
		}
		return false
	},
	IEInstall: function() {
		location.href = deployJava.getJavaURL + ((deployJava.returnPage != null) ? ("&returnPage=" + deployJava.returnPage) : "") + ((deployJava.locale != null) ? ("&locale=" + deployJava.locale) : "") + ((deployJava.brand != null) ? ("&brand=" + deployJava.brand) : "") + ((deployJava.installType != null) ? ("&type=" + deployJava.installType) : "");
		return false
	},
	done: function(b, a) {},
	FFInstall: function() {
		location.href = deployJava.getJavaURL + ((deployJava.returnPage != null) ? ("&returnPage=" + deployJava.returnPage) : "") + ((deployJava.locale != null) ? ("&locale=" + deployJava.locale) : "") + ((deployJava.brand != null) ? ("&brand=" + deployJava.brand) : "") + ((deployJava.installType != null) ? ("&type=" + deployJava.installType) : "");
		return false
	},
	compareVersions: function(f, g) {
		var d = f.split(".");
		var c = g.split(".");
		for (var e = 0; e < d.length; ++e) {
			d[e] = Number(d[e])
		}
		for (var e = 0; e < c.length; ++e) {
			c[e] = Number(c[e])
		}
		if (d.length == 2) {
			d[2] = 0
		}
		if (d[0] > c[0]) {
			return true
		}
		if (d[0] < c[0]) {
			return false
		}
		if (d[1] > c[1]) {
			return true
		}
		if (d[1] < c[1]) {
			return false
		}
		if (d[2] > c[2]) {
			return true
		}
		if (d[2] < c[2]) {
			return false
		}
		return true
	},
	enableAlerts: function() {
		deployJava.browserName = null;
		deployJava.debug = true
	},
	poll: function() {
		deployJava.refresh();
		var a = deployJava.getJREs();
		if ((deployJava.preInstallJREList.length == 0) && (a.length != 0)) {
			clearInterval(deployJava.myInterval);
			if (deployJava.returnPage != null) {
				location.href = deployJava.returnPage
			}
		}
		if ((deployJava.preInstallJREList.length != 0) && (a.length != 0) && (deployJava.preInstallJREList[0] != a[0])) {
			clearInterval(deployJava.myInterval);
			if (deployJava.returnPage != null) {
				location.href = deployJava.returnPage
			}
		}
	},
	writePluginTag: function() {
		var a = deployJava.getBrowser();
		if (a == "MSIE") {
			document.write('<object classid="clsid:CAFEEFAC-DEC7-0000-0000-ABCDEFFEDCBA" id="deployJavaPlugin" width="0" height="0"></object>')
		} else {
			if (a == "Netscape Family" && deployJava.allowPlugin()) {
				deployJava.writeEmbedTag()
			}
		}
	},
	refresh: function() {
		navigator.plugins.refresh(false);
		var a = deployJava.getBrowser();
		if (a == "Netscape Family" && deployJava.allowPlugin()) {
			var b = document.getElementById("deployJavaPlugin");
			if (b == null) {
				deployJava.writeEmbedTag()
			}
		}
	},
	writeEmbedTag: function() {
		var a = false;
		if (navigator.mimeTypes != null) {
			for (var b = 0; b < navigator.mimeTypes.length; b++) {
				if (navigator.mimeTypes[b].type == deployJava.mimeType) {
					if (navigator.mimeTypes[b].enabledPlugin) {
						document.write('<embed id="deployJavaPlugin" type="' + deployJava.mimeType + '" hidden="true"/>');
						a = true
					}
				}
			}
			if (!a) {
				for (var b = 0; b < navigator.mimeTypes.length; b++) {
					if (navigator.mimeTypes[b].type == deployJava.oldMimeType) {
						if (navigator.mimeTypes[b].enabledPlugin) {
							document.write('<embed id="deployJavaPlugin" type="' + deployJava.oldMimeType + '" hidden="true" />')
						}
					}
				}
			}
		}
	},
	do_initialize: function() {
		deployJava.writePluginTag();
		if (deployJava.locale == null) {
			var b = null;
			if (b == null) {
				try {
					b = navigator.userLanguage
				} catch(a) {}
			}
			if (b == null) {
				try {
					b = navigator.systemLanguage
				} catch(a) {}
			}
			if (b == null) {
				try {
					b = navigator.language
				} catch(a) {}
			}
			if (b != null) {
				b.replace("-", "_");
				deployJava.locale = b
			}
		}
	}
};
deployJava.do_initialize();