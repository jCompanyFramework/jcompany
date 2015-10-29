var rhdemo = rhdemo || {};

angular.module('rhdemo').factory('Utils', function UtilsServiceFactory() {
    var UtilsService = $class({

        constructor: function() {
            this.SimNao = {
                S : "S",
                N : "N"
            };
        },

        isUndefinedOrNull: function(variable) {
            return !angular.isDefined(variable) || variable===null;
        },

        sanitizeObject: function (object, propertiesArray) {
            propertiesArray.forEach( function(property) {
                delete object[property];
            });
            return object;
        },

        sanitizeObjectCollection: function (arrayOfObjects, propertiesArray) {
            var newArrayOfObjects = [];
            arrayOfObjects.forEach( function(object, key) {
                newArrayOfObjects.push(sanitizeObject(object, propertiesArray));
            });
            return newArrayOfObjects;
        },

        cloneObject: function(obj){
            var GDCC = "__getDeepCircularCopy__";

            if (obj !== Object(obj)) {
                return obj; // primitive value
            }

            var set = GDCC in obj,
                cache = obj[GDCC],
                result;

            if (set && typeof cache == "function") {
                return cache();
            }

            // else
            obj[GDCC] = function() {
                return result;
            }; // overwrite

            if (obj instanceof Array) {
                result = [];
                for (var i=0; i<obj.length; i++) {
                    result[i] = this.cloneObject(obj[i]);
                }
            } else {
                result = {};
                for (var prop in obj)
                    if (prop != GDCC)
                        result[prop] = this.cloneObject(obj[prop]);
                    else if (set)
                        result[prop] = this.cloneObject(cache);
            }

            if (set) {
                obj[GDCC] = cache; // reset
            } else {
                delete obj[GDCC]; // unset again
            }

            return result;
        },

        validaObrigatorio: function (element) {
            var retorno = false;
            var obrigatorio 

            if (element){
                obrigatorio = element.querySelectorAll('.obrigatorio');
            }else{
                obrigatorio = document.querySelectorAll('.obrigatorio');
            }

            
            var obrigatorioarr = Array.prototype.slice.call(obrigatorio);

            obrigatorioarr.forEach(function(elemento) {
                if (elemento.type === "text") {
                    if (!elemento.value) {
                        elemento.classList.add("haserror");
                        retorno = true;
                    } else {
                        elemento.classList.remove("haserror");
                    }
                } else if (elemento.type === "checkbox") {
                    if (!elemento.checked) {
                        elemento.classList.add("haserror");
                        retorno = true;
                    } else {
                        elemento.classList.remove("haserror");
                    }
                } else if (elemento.type === "select-one") {
                    if ( elemento.selectedIndex <= 0 ) {
                        elemento.classList.add("haserror");
                        retorno = true;
                    } else {
                        elemento.classList.remove("haserror");
                    }
                }
            });
            return retorno;
        },

        validaEtiquetas: function (valida) {
            var obrigatorio = document.querySelector('.obrigatorioEtiqueta');
            if (valida) {
                obrigatorio.classList.add("haserror");
            } else {
                obrigatorio.classList.remove("haserror");
            }
            return valida;
        },

        validaElementos: function (valida) {
            var obrigatorio = document.querySelector('.obrigatorioElemento');

            /*var obrigatorioarr = Array.prototype.slice.call(obrigatorio);
            obrigatorioarr.forEach(function(elemento) {
            });*/

            if (valida) {
                obrigatorio.classList.add("haserror");
            } else {
                obrigatorio.classList.remove("haserror");
            }
            return valida;
        },

        validaModelo: function (valida) {
            var obrigatorio = document.querySelectorAll('.obrigatorioModelo');
            var obrigatorioarr = Array.prototype.slice.call(obrigatorio);
            obrigatorioarr.forEach(function(elemento) {
                if (valida) {
                    elemento.classList.add("haserror");
                } else {
                    elemento.classList.remove("haserror");
                }
            });
            return valida;
        },

        generateDynamicCSS: function (content) {
            var style = document.createElement("style");
                style.type="text/css";
                style.innerHTML = content;
            document.head.appendChild(style);
        },

        sanitizeXSS: function (text) {
            // Remove everything in between tags
            text = text.replace(/(<head)[\s\S]+(head>)/gi, '');

            ['style', 'script','applet','embed','noframes','noscript'].forEach( function (tag, i) {
                // var tagStripper = new RegExp('<'+tag+'.*?'+tag+'(.*?)>', 'gi');
                var tagStripper = new RegExp('(<'+tag+')[\\s\\S]+('+tag+'>)', 'gi');
                text = text.replace(tagStripper, '');
            });

            // remove tags <br /> inside other tags
            text = text.replace(/((?:<pre|(?!^))(?:(?!<\/pre|\G<\s*\/?\s*br))*)<\s*\/?\s*br\s*\/?\s*>/gi, ' $1');

            // remove tags leave content if any
            text = text.replace(/<(\/)*(\\?xml:|st1:|o:)(.*?)>/gi, '');
            text = text.replace(/<(\/)*(html|body|meta|link)(.*?)>/gi, '');

            // strip Word generated HTML comments
            text = text.replace(/<!--(.*?)-->/g, '');

            // console.log(text);
            // text = text.replace(/\uFFFD/g, '');

            return text;
        },

        sanitizeHTMLText: function (text) {
            text = this.sanitizeXSS(text);

            // remove attributes ' start="..."'
            ['start'].forEach( function (attr, i) {
                var attributeStripper = new RegExp(' ' + attr + '="(.*?)"','gi');
                text = text.replace(attributeStripper, '');
            });

            // Outras tags
            text = text.replace(/(\n|\r)/g, ' ');
            text = text.replace(/<(\w[^>]*) lang=([^ |>]*)([^>]*)/gi, "<$1$3") ;
            // text = text.replace(/<\\?\?xml[^>]*>/gi, "") ;
            // text = text.replace(/<\/?\w+:[^>]*>/gi, "") ;
            // text = text.replace( /<([^\s>]+)[^>]*>\s*<\/\1>/g, '' ) ;

            // Tamanho e posicionamento
            text = text.replace( /\s*MARGIN: 0cm 0cm 0pt\s*;/gi, "" ) ;
            text = text.replace( /\s*MARGIN: 0cm 0cm 0pt\s*"/gi, "\"" ) ;
            text = text.replace( /\s*PAGE-BREAK-BEFORE: [^\s;]+;?"/gi, "\"" ) ;

            // Formatação
            text = text.replace( /\s*tab-stops:[^;"]*;?/gi, "" ) ;
            text = text.replace( /\s*tab-stops:[^"]*/gi, "" ) ;
            text = text.replace(/<(\w[^>]*) class=([^ |>]*)([^>]*)/gi, "<$1$3") ;

            // Divisor
            text = text.replace( /<\/H\d>/gi, '<br>' ) ; //remove this to take out breaks where Heading tags were

            return text;
        },

        sanitizeStyleText: function (text) {
            // Outras tags
            text = text.replace(/(.*(?:endif-->))|([ ]?<[^>]*>[ ]?)|(&nbsp;)|([^}]*})/g,'');

            // Fontes
            text = text.replace( /\s*FONT-VARIANT: [^\s;]+;?"/gi, "\"" ) ;
            text = text.replace( /\s*face="[^"]*"/gi, "" ) ;
            text = text.replace( /\s*face=[^ >]*/gi, "" ) ;
            text = text.replace( /\s*color="[^"]*"/gi, "" ) ;
            text = text.replace( /\s*FONT-FAMILY:[^;"]*;?/gi, "" ) ;
            text = text.replace( /(<font|<FONT)([^*>]*>.*?)(<\/FONT>|<\/font>)/gi, "$2") ;
            text = text.replace( /<FONT\s*>(.*?)<\/FONT>/gi, '$1' ) ;

            // Tamanho e posicionamento
            text = text.replace( /\s*TEXT-INDENT: 0cm\s*;/gi, "" ) ;
            text = text.replace( /\s*TEXT-INDENT: 0cm\s*"/gi, "\"" ) ;
            text = text.replace( /\s*TEXT-ALIGN: [^\s;]+;?"/gi, "\"" ) ;
            text = text.replace( /size|SIZE = ([\d]{1})/g, '' ) ;

            // Formatação
            text = text.replace( /<(\w[^>]*) style="([^\"]*)"([^>]*)/gi, "<$1$3" ) ;
            text = text.replace( /\s*style="\s*"/gi, '' ) ;

            // Cabeçalhos
            text = text.replace( /<H\d>\s*<\/H\d>/gi, '' ) ;
            text = text.replace( /<H1([^>]*)>/gi, '' ) ;
            text = text.replace( /<H2([^>]*)>/gi, '' ) ;
            text = text.replace( /<H3([^>]*)>/gi, '' ) ;
            text = text.replace( /<H4([^>]*)>/gi, '' ) ;
            text = text.replace( /<H5([^>]*)>/gi, '' ) ;
            text = text.replace( /<H6([^>]*)>/gi, '' ) ;

            // Divisor
            text = text.replace( /<\/H\d>/gi, '<br>' ) ; //remove this to take out breaks where Heading tags were

            // Italico, sublinhado e tachado
            text = text.replace( /<(U|I|STRIKE)>&nbsp;<\/\1>/g, '&nbsp;' ) ;

            // Negrito
            text = text.replace( /<(B|b)>&nbsp;<\/\b|B>/g, '' ) ;

            // Destaques
            text = text.replace( /<SPAN\s*[^>]*>\s*&nbsp;\s*<\/SPAN>/gi, '&nbsp;' ) ;
            text = text.replace( /<SPAN\s*[^>]*><\/SPAN>/gi, '' ) ;
            text = text.replace( /<SPAN\s*>(.*?)<\/SPAN>/gi, '$1' ) ;

            // Paragrafos
            text = text.replace( /(<P)([^>]*>.*?)(<\/P>)/gi, "<p$2</p>" ) ;

            return text;
        },

        cleanMSOfficeStyle: function (text) {
            // remove line breaks / Mso classes / mso attributes
            text = text.replace(/(\n|\r| class=(")?Mso[a-zA-Z]+(")?)/g, ' ');
            text = text.replace(/mso-[^:]+:[^;("|')]+;?/g, ' ');
            text = text.replace(/<(\/)*(\\?xml:|st1:|o:)(.*?)>/gi, '');

            return text;
        },

        getDocumentSelection: function( htmlFormat ) {
            if (htmlFormat) {
                var range;
                if (document.selection && document.selection.createRange) {
                    range = document.selection.createRange();
                    return range.htmlText;
                } else if (window.getSelection) {
                    var selection = window.getSelection();
                    if (selection.rangeCount > 0) {
                        range = selection.getRangeAt(0);
                        var clonedSelection = range.cloneContents();
                        var div = document.createElement('div');
                        div.appendChild(clonedSelection);
                        return div.innerHTML;
                    } else {
                        return '';
                    }
                } else {
                    return '';
                }
            } else {
                return window.getSelection().toString();
            }
        },

        copyDataToClipboard: function(data) {
            var documentBody, selectRange, htmlData;

            htmlData = document.createElement('div');
            htmlData.innerHTML = data;

            documentBody = document.getElementsByTagName('body')[0];
            documentBody.appendChild(htmlData);

            if (window.getSelection) {
                window.getSelection().removeAllRanges();
            } else if (document.selection) {
                document.selection.empty();
            }

            selectRange = document.createRange();
            selectRange.selectNode(htmlData);

            window.getSelection(htmlData).addRange(selectRange);

            document.execCommand('copy');

            documentBody.removeChild(htmlData);
        },

        removeElementById: function(obj, element) {
            // remove um elemento comparando o ID
            var indice = -1;
            for (var i = 0; i < obj.length; i++) {
                if (obj[i].id === element.id) {
                    indice = i;
                    break;
                }
            }
            obj.splice(indice, 1);
        },

        encodeURIParams : function(obj, prefix) {
            var str = [];
            for(var p in obj) {
              if (obj.hasOwnProperty(p)) {
                var k = prefix ? prefix + "[" + p + "]" : p, v = obj[p];
                str.push(typeof v == "object" ?
                serialize(v, k) :
                encodeURIComponent(k) + "=" + encodeURIComponent(v));
              }
            }
          return str.join("&");
        }

    });

    return new UtilsService();
});