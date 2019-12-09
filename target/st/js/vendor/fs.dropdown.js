/*! formstone v1.3.2 [dropdown.js] 2017-03-10 | GPL-3.0 License | formstone.it */

!function(a){"function"==typeof define&&define.amd?define(["jquery","./core","./scrollbar","./touch"],a):a(jQuery,Formstone)}(function(a,b){"use strict";function c(){G=b.$body}function d(b){b.multiple=this.prop("multiple"),b.disabled=this.prop("disabled")||this.is("[readonly]"),b.lastIndex=!1,b.multiple?b.links=!1:b.external&&(b.links=!0);var c=this.find("[selected]").not(":disabled"),d=this.find(":selected").not(":disabled"),e=d.text(),f=this.find("option").index(d);b.multiple||""===b.label||c.length?b.label="":(d=this.prepend('<option value="" class="'+B.item_placeholder+'" selected>'+b.label+"</option>"),e=b.label,f=0);var g=this.find("option, optgroup"),h=g.filter("option"),k=a("[for="+this.attr("id")+"]");b.tabIndex=this[0].tabIndex,this[0].tabIndex=-1,k.length&&(k[0].tabIndex=-1);var l=[B.base,b.theme,b.customClass];b.mobile?l.push(B.mobile):b.cover&&l.push(B.cover),b.multiple&&l.push(B.multiple),b.disabled&&l.push(B.disabled),b.id=this.attr("id"),b.id?b.ariaId=b.id:b.ariaId=b.rawGuid,b.ariaId+="-dropdown",b.selectedAriaId=b.ariaId+"-selected";var m="",n="";m+='<div class="'+l.join(" ")+'"id="'+b.ariaId+'" tabindex="'+b.tabIndex+'" role="listbox"',m+=b.multiple?' aria-label="multi select"':' aria-haspopup="true" aria-live="polite" aria-labeledby="'+b.selectedAriaId+'"',m+="></div>",b.multiple||(n+='<button type="button" class="'+B.selected+'" id="'+b.selectedAriaId+'" tabindex="-1">',n+=a("<span></span>").text(y(e,b.trim)).html(),n+="</button>"),n+='<div class="'+B.options+'">',n+="</div>",this.wrap(m).after(n),b.$dropdown=this.parent(A.base),b.$label=k,b.$allOptions=g,b.$options=h,b.$selected=b.$dropdown.find(A.selected),b.$wrapper=b.$dropdown.find(A.options),b.$placeholder=b.$dropdown.find(A.placeholder),b.index=-1,b.closed=!0,b.focused=!1,i(b),b.multiple||u(f,b),void 0!==a.fn.fsScrollbar&&b.$wrapper.fsScrollbar({theme:b.theme}).find(".fs-scrollbar-content").attr("tabindex",null),b.$dropdown.on(C.click,b,j),b.$selected.on(C.click,b,j),b.$dropdown.on(C.click,A.item,b,p).on(C.close,b,o),this.on(C.change,b,q),b.mobile||(this.on(C.focusIn,b,function(a){a.data.$dropdown.trigger(C.raw.focus)}),b.$dropdown.on(C.focusIn,b,r).on(C.focusOut,b,s))}function e(b){b.$dropdown.hasClass(B.open)&&b.$selected.trigger(C.click),void 0!==a.fn.fsScrollbar&&b.$wrapper.fsScrollbar("destroy"),b.$el[0].tabIndex=b.tabIndex,b.$label.length&&(b.$label[0].tabIndex=b.tabIndex),b.$dropdown.off(C.namespace),b.$options.off(C.namespace),b.$placeholder.remove(),b.$selected.remove(),b.$wrapper.remove(),b.$el.off(C.namespace).show().unwrap()}function f(a,b){if("undefined"!=typeof b){var c=a.$items.index(a.$items.filter("[data-value="+b+"]"));a.$items.eq(c).addClass(B.item_disabled),a.$options.eq(c).prop("disabled",!0)}else a.$dropdown.hasClass(B.open)&&a.$selected.trigger(C.click),a.$dropdown.addClass(B.disabled),a.$el.prop("disabled",!0),a.disabled=!0}function g(a,b){if("undefined"!=typeof b){var c=a.$items.index(a.$items.filter("[data-value="+b+"]"));a.$items.eq(c).removeClass(B.item_disabled),a.$options.eq(c).prop("disabled",!1)}else a.$dropdown.removeClass(B.disabled),a.$el.prop("disabled",!1),a.disabled=!1}function h(b){void 0!==a.fn.fsScrollbar&&b.$wrapper.fsScrollbar("destroy");var c=b.index;b.$allOptions=b.$el.find("option, optgroup"),b.$options=b.$allOptions.filter("option"),b.index=-1,c=b.$options.index(b.$options.filter(":selected")),i(b),b.multiple||u(c,b),void 0!==a.fn.fsScrollbar&&b.$wrapper.fsScrollbar({theme:b.theme}).find(".fs-scrollbar-content").attr("tabindex",null)}function i(b){for(var c="",d=0,e=0,f=b.$allOptions.length;e<f;e++){var g=b.$allOptions.eq(e),h=[];if("OPTGROUP"===g[0].tagName)h.push(B.group),g.prop("disabled")&&h.push(B.disabled),c+='<span class="'+h.join(" ")+'">'+g.attr("label")+"</span>";else{var i=g.val(),j=g.data("label"),k=b.links?"a":'button type="button"';g.attr("value")||g.attr("value",i),h.push(B.item),g.hasClass(B.item_placeholder)&&(h.push(B.item_placeholder),k="span"),g.prop("selected")&&h.push(B.item_selected),g.prop("disabled")&&h.push(B.item_disabled),c+="<"+k+' class="'+h.join(" ")+'"',b.links?"span"===k?c+=' aria-hidden="true"':(c+=' href="'+i+'"',b.external&&(c+=' target="_blank"')):c+=' data-value="'+i+'"',c+=' role="option"',g.prop("selected")&&(c+=' "aria-selected"="true"'),c+=">",c+=j?j:D.decodeEntities(y(g.text(),b.trim)),c+="</"+k+">",d++}}b.$items=b.$wrapper.html(a.parseHTML(c)).find(A.item)}function j(a){D.killEvent(a);var b=a.data;b.disabled||b.mobile||(b.closed?l(b):m(b)),k(b)}function k(b){a(A.base).not(b.$dropdown).trigger(C.close,[b])}function l(a){if(a.closed){var b=F.height(),c=a.$wrapper.outerHeight(!0),d=a.$dropdown[0].getBoundingClientRect();d.bottom+c>b-a.bottomEdge&&a.$dropdown.addClass(B.bottom),G.on(C.click+a.dotGuid,":not("+A.options+")",a,n),a.$dropdown.trigger(C.focusIn),a.$dropdown.addClass(B.open),v(a),a.closed=!1}}function m(a){a&&!a.closed&&(G.off(C.click+a.dotGuid),a.$dropdown.removeClass([B.open,B.bottom].join(" ")),a.closed=!0)}function n(b){D.killEvent(b);var c=b.data;c&&0===a(b.currentTarget).parents(A.base).length&&(m(c),c.$dropdown.trigger(C.focusOut))}function o(a){var b=a.data;b&&(m(b),b.$dropdown.trigger(C.focusOut))}function p(b){var c=a(this),d=b.data;if(D.killEvent(b),!d.disabled){var e=d.$items.index(c);d.focusIndex=e,d.$wrapper.is(":visible")&&(u(e,d,b.shiftKey,b.metaKey||b.ctrlKey),w(d)),d.multiple||m(d),d.$dropdown.trigger(C.focus)}}function q(b,c){var d=(a(this),b.data);if(!c&&!d.multiple){var e=d.$options.index(d.$options.filter(":selected"));d.focusIndex=e,u(e,d),w(d,!0)}}function r(b){D.killEvent(b);var c=(a(b.currentTarget),b.data);c.disabled||c.multiple||c.focused||(k(c),c.focused=!0,c.focusIndex=c.index,c.input="",c.$dropdown.addClass(B.focus).on(C.keyDown+c.dotGuid,c,t))}function s(b){D.killEvent(b);var c=(a(b.currentTarget),b.data);c.focused&&c.closed&&(c.focused=!1,c.$dropdown.removeClass(B.focus).off(C.keyDown+c.dotGuid),c.multiple||(m(c),c.index!==c.focusIndex&&(w(c),c.focusIndex=c.index)))}function t(c){var d=c.data;if(d.keyTimer=D.startTimer(d.keyTimer,1e3,function(){d.input=""}),13===c.keyCode)d.closed||(m(d),u(d.index,d)),w(d);else if(!(9===c.keyCode||c.metaKey||c.altKey||c.ctrlKey||c.shiftKey)){D.killEvent(c);var e=d.$items.length-1,f=d.index<0?0:d.index;if(a.inArray(c.keyCode,b.isFirefox?[38,40,37,39]:[38,40])>-1)f+=38===c.keyCode||b.isFirefox&&37===c.keyCode?-1:1,f<0&&(f=0),f>e&&(f=e);else{var g,h,i=String.fromCharCode(c.keyCode).toUpperCase();for(d.input+=i,h=d.index+1;h<=e;h++)if(g=d.$options.eq(h).text().substr(0,d.input.length).toUpperCase(),g===d.input){f=h;break}if(f<0||f===d.index)for(h=0;h<=e;h++)if(g=d.$options.eq(h).text().substr(0,d.input.length).toUpperCase(),g===d.input){f=h;break}}f>=0&&(u(f,d),v(d))}}function u(a,b,c,d){var e=b.$items.eq(a),f=b.$options.eq(a),g=e.hasClass(B.item_selected),h=e.hasClass(B.item_disabled);if(!h)if(b.multiple)if(b.mobile)g?(f.prop("selected",null).attr("aria-selected",null),e.removeClass(B.item_selected)):(f.prop("selected",!0).attr("aria-selected",!0),e.addClass(B.item_selected));else if(c&&b.lastIndex!==!1){var i=b.lastIndex>a?a:b.lastIndex,j=(b.lastIndex>a?b.lastIndex:a)+1;b.$options.prop("selected",null).attr("aria-selected",null),b.$items.filter(A.item_selected).removeClass(B.item_selected),b.$options.slice(i,j).not("[disabled]").prop("selected",!0),b.$items.slice(i,j).not(A.item_disabled).addClass(B.item_selected)}else d?(g?(f.prop("selected",null).attr("aria-selected",null),e.removeClass(B.item_selected)):(f.prop("selected",!0).attr("aria-selected",!0),e.addClass(B.item_selected)),b.lastIndex=a):(b.$options.prop("selected",null).attr("aria-selected",null),b.$items.filter(A.item_selected).removeClass(B.item_selected),f.prop("selected",!0).attr("aria-selected",!0),e.addClass(B.item_selected),b.lastIndex=a);else if(a>-1&&a<b.$items.length){if(a!==b.index){var k=f.data("label")||e.html();b.$selected.html(k).removeClass(A.item_placeholder),b.$items.filter(A.item_selected).removeClass(B.item_selected),b.$el[0].selectedIndex=a,e.addClass(B.item_selected),b.index=a}}else""!==b.label&&b.$selected.html(b.label)}function v(b){var c=b.$items.eq(b.index),d=b.index>=0&&!c.hasClass(B.item_placeholder)?c.position():{left:0,top:0},e=(b.$wrapper.outerHeight()-c.outerHeight())/2;void 0!==a.fn.fsScrollbar?b.$wrapper.fsScrollbar("resize").fsScrollbar("scroll",b.$wrapper.find(".fs-scrollbar-content").scrollTop()+d.top):b.$wrapper.scrollTop(b.$wrapper.scrollTop()+d.top-e)}function w(a,b){a.links?x(a):b||a.$el.trigger(C.raw.change,[!0])}function x(a){var b=a.$el.val();a.external?E.open(b):E.location.href=b}function y(a,b){return 0===b?a:a.length>b?a.substring(0,b)+"...":a}var z=b.Plugin("dropdown",{widget:!0,defaults:{bottomEdge:0,cover:!1,customClass:"",label:"",external:!1,links:!1,mobile:!1,theme:"fs-light",trim:0},methods:{_setup:c,_construct:d,_destruct:e,disable:f,enable:g,update:h,open:l,close:m},classes:["cover","bottom","multiple","mobile","open","disabled","focus","selected","options","group","item","item_disabled","item_selected","item_placeholder"],events:{close:"close"}}),A=z.classes,B=A.raw,C=z.events,D=z.functions,E=b.window,F=b.$window,G=(b.document,null)});