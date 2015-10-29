// ----------------------------------------------
// Add Generic DOM Prototype Functions
// ----------------------------------------------
Element.prototype.hasClass = function(cssClass) {
    if (cssClass)
        return this.classList.contains(cssClass);
    return this;
};

// ----------------------------------------------
// Add TextArea Prototype Functions
// ----------------------------------------------
HTMLTextAreaElement.prototype.asPlainText = function () {
    return this.value.replace(/<[^>]+>/gm, '');
};

HTMLTextAreaElement.prototype.insertAtCaret = function (text) {
    text = text || '';
    if (document.selection) {
        // IE
        this.focus();
        var sel = document.selection.createRange();
        sel.text = text;
    } else if (this.selectionStart || this.selectionStart === 0) {
        // Others
        var startPos = this.selectionStart;
        var endPos = this.selectionEnd;
        this.value = this.value.substring(0, startPos) + text + this.value.substring(endPos, this.value.length);
        this.selectionStart = startPos + text.length;
        this.selectionEnd = startPos + text.length;
    } if (window.getSelection) {
        // IE9 e outros browsers
        var selection, range;
        selection = window.getSelection();
        if (selection.getRangeAt && selection.rangeCount) {
            range = selection.getRangeAt(0);
            range.deleteContents();

            var el = document.createElement("div");
            el.innerHTML = text;
            var frag = document.createDocumentFragment(), node, lastNode;
            while ( (node = el.firstChild) ) {
                lastNode = frag.appendChild(node);
            }
            var firstNode = frag.firstChild;
            range.insertNode(frag);

            // Preserve the selection
            if (lastNode) {
                range = range.cloneRange();
                range.setStartAfter(lastNode);
                range.collapse(true);
                selection.removeAllRanges();
                selection.addRange(range);
            }
        }
    } else {
        console.error('Função não suportada!');
    }

    return this;
};

// ----------------------------------------------
// Add Array Prototype Functions
// ----------------------------------------------
Array.prototype.isEmpty = function() {
    return (this.length > -1);
};
// Retorna o primeiro elemento de um array
Array.prototype.first = function () {
    return this[0];
};
// Retorna o ultimo elemento de um array
Array.prototype.last = function () {
    return this[this.length - 1];
};
// Remove o elemento de um array
Array.prototype.delete = function (element) {
    var index = this.indexOf(element);
    if (index !== -1)
        this.splice(index, 1);
    return (index !== -1);
};
// Verifica a existencia de um elemento em um array
Array.prototype.search = function (searchParam, searchAttr) {
    searchAttr = searchAttr || null;
    if (typeof searchParam !== 'object' && searchAttr ) {
        var result = false;
        this.forEach( function( el ) {
            if (el[searchAttr] == searchParam) {
                result = true; return;
            }
        });
        return result;
    }
    return (this.indexOf(searchParam) !== -1);
};
// Substitui um elemento por outro em um array,
// mantendo a posicao do elemento anterior
Array.prototype.replace = function (oldElement, newElement) {
    var index = this.indexOf(oldElement);
    if (index !== -1)
        this[index] = newElement;
    return this;
};
// Retorna apenas os elementos unicos de um array
Array.prototype.unique = function() {
    var array = this.concat();
    for (var i = 0; i < array.length; ++i ) {
        for (var j = i + 1; j < array.length; ++j ) {
            if (array[i] === array[j])
                array.splice(j--, 1);
        }
    }
    return array;
};
// Move um elemento array de uma posicao para outra
Array.prototype.swap = function (element, posicao) {
    var index = this.indexOf(element), newPos = 0;

    if(posicao == 1) newPos = index - 1;
        else newPos = index + 1;

    if (newPos < 0) newPos = 0;
    if (newPos >= this.length) newPos = this.length;

    this.splice(index, 1);
    this.splice(newPos, 0, element);
};
Array.prototype.toObject = function () {
       var newObject = {};
       this.forEach( function ( value, key ){
              newObject[key] = value;
       });
       return newObject;
};
// ----------------------------------------------
// Add Object Prototype Functions
// ----------------------------------------------
Object.defineProperty( Object.prototype, 'map', {
    value: function(func, context) {
        context = context || this;
        var self = this, result = {};
        Object.keys(self).forEach(function(k) {
            result[k] = func.call(context, k, self[k], self);
        });
        return result;
    }
});

Object.defineProperty( Object.prototype, 'toArray', {
    value: function(context) {
        context = context || this;
        var self = this, result = [];
        Object.keys(self).forEach(function(k) {
            result[k] = self[k];
        });
        return result;
    }
});
// ----------------------------------------------
// Add String Prototype Functions
// ----------------------------------------------
// Verifica ser a string está vazia
String.prototype.isEmpty = function() {
    return (this.length === 0 || !this.trim());
};
String.prototype.asPlainText = function () {
    return this.replace(/<[^>]+>/gm, '');
};
String.prototype.equals = function (str, ignoreCase) {
    if (ignoreCase)
        return (this.toLowerCase() == str.toLowerCase());
    else
        return (this == str);
};
// Retorna a string alterando a primeira letra para maiuscula
String.prototype.capitalize = function() {
    return this.charAt(0).toUpperCase() + this.slice(1);
};
// Retorna somente a primeira letra da string
String.prototype.first = function() {
    return this.charAt(0);
};
String.prototype.replaceAll = String.prototype.replaceAll || function(needle, replacement) {
    return this.split(needle).join(replacement);
};
// String Builder Object
function StringBuilder(string) {
    this.arrStrings = [];
    if (string || string === 0)
        this.arrStrings.push(string);
    return this;
}
StringBuilder.prototype.append = function(string) {
    if (string || string === 0)
        this.arrStrings.push(string);
    return this;
};
StringBuilder.prototype.clear = function() {
    this.arrStrings = [];
};
StringBuilder.prototype.toString = function() {
    return this.arrStrings.join("");
};
