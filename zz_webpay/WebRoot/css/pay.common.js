function Browser(){
	var ua, s, i;
	this.isIE = false;
	this.isNS = false;
	this.isOP = false;
	this.isSF = false;
	ua = navigator.userAgent.toLowerCase();
	s = "opera";
	if ((i = ua.indexOf(s)) >= 0) {
		this.isOP = true;
		return;
	}
	s = "msie";
	if ((i = ua.indexOf(s)) >= 0) {
		this.isIE = true;
		return;
	}
	s = "netscape6/";
	if ((i = ua.indexOf(s)) >= 0) {
		this.isNS = true;
		return;
	}
	s = "gecko";
	if ((i = ua.indexOf(s)) >= 0) {
		this.isNS = true;
		return;
	}
	s = "safari";
	if ((i = ua.indexOf(s)) >= 0) {
		this.isSF = true;
		return;
	}
}

function findPosX(obj) {
	var curleft = 0;
	if (obj.offsetParent) {
		while (obj.offsetParent) {
			curleft += obj.offsetLeft;
			obj = obj.offsetParent;
		}
	} else if (obj.x) curleft += obj.x;
	return curleft;
}

function findPosY(obj) {
	var curtop = 0;
	if (obj.offsetParent) {
		while (obj.offsetParent) {
			curtop += obj.offsetTop;
			obj = obj.offsetParent;
		}
	} else if (obj.y) curtop += obj.y;
	return curtop;
}

function isEmail(str) {
	return checkData(str, /^[\w_-]+([\.\\w_-]*[\w_-]+)?@[\w-]+\.[a-zA-Z]+(\.[a-zA-Z]+)?$/);
}

function trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

function isInt(str) {
	return checkData(str, /^-?[0-9]+$/);
}

function checkData(str, pattern) {
	if (pattern.test(str)) {
		return true;
	} else {
		return false;
	}
}

function Div(exp1, exp2)
{
	var n1 = Math.round(exp1); //四舍五入
	var n2 = Math.round(exp2); //四舍五入

	var rslt = n1 / n2; //除

	if (rslt >= 0)
	{
		rslt = Math.floor(rslt); //返回值为小于等于其数值参数的最大整数值。
	}
	else
	{
		rslt = Math.ceil(rslt); //返回值为大于等于其数字参数的最小整数。
	}

	return rslt;
}

function sleep(numberMillis) {
	var now = new Date();
	var exitTime = now.getTime() + numberMillis;

	while (true) {
		now = new Date();
		if (now.getTime() > exitTime)
			return;
	}
}

function removeElementById(id) {
	var obj = document.getElementById(id);
	if (null==obj) return;
	obj.parentNode.removeChild(obj);
}

function escapeHTML(palinText) {
//	var div = document.createElement('div');
//	var text = document.createTextNode(palinText);
//	div.appendChild(text);
//	return div.innerHTML;
	return palinText.replace(/</gi , "&lt;").replace(/>/gi , "&gt;").replace(/\"/gi , "&#34;").replace(/\'/gi , "&#39;");
}

function unescapeHTML(htmlText) {
	return htmlText.replace(/&lt;/gi , "<").replace(/&gt;/gi , ">").replace(/&#39;/gi, "\'").replace(/&#34;/gi, "\"").replace(/&amp;/gi, "&").replace(/&#37;/gi, "%").replace(/＄/gi, "$");
}

/**
function unescapeHTML(htmlText) {
	var div = document.createElement('div');
	div.innerHTML = stripTags(htmlText);
	return div.childNodes[0].nodeValue;
}
**/

function evalCallBack(callbackObj) {
	if (typeof callbackObj == "string")
		location.href=callbackObj;
	else if (callbackObj instanceof Array)
	{
		var func = callbackObj[0] + "(";
		for (i = 1; i < callbackObj.length; i++)
		{
			if (i != 1)
				func += ",";
			func += "'" + callbackObj[i].replace(/\n/g,"\\n").replace(/\r/g,"\\n").replace(/\'/g,"\\'") + "'";
		}
		func += ")";
		eval("(" + func + ")");
	} else {
		var form = eval(callbackObj);
		try
		{
			form.submit();
		}
		catch (e)
		{
		}
	}
}

function scale_to_fit(img, nPixels) {
	var nPixelsMax = nPixels * 1;
	if (img.classname == "processed")
		return;
	if (img.height > img.width && img.height > nPixelsMax) {
		img.width = parseInt (img.width*nPixelsMax/img.height);
		img.classname="processed";
	}
	else if (img.width >= img.height && img.width > nPixelsMax) {
		img.width = nPixelsMax;
		img.classname="processed";
	}
	else {
	}
	img.style.visibility = 'visible';
}

function onLoadForScaling(imgId, nPixels) {
	var img = document.getElementById(imgId);
	if (img == null)
		return;
	if (img.height == 0 && img.width == 0){
		setTimeout('scale_to_fit(document.getElementById(\''+imgId+'\'), ' + nPixels + ')', 500);
	}
	else {
		scale_to_fit(document.getElementById(imgId), nPixels);
	}
}

function textOnfocus(text, className){
	if (text.value == text.title)
		text.value = "";
	text.className = className;
}

function textOnblur(text, className){
	if (text.value.length < 1) {
		text.value = text.title;
		text.className = trim(className).length<1 ? "font_12_disable" : className + " font_12_disable";
	} else {
		if (text.value == text.title) {
			text.className = trim(className).length<1 ? "font_12_disable" : className + " font_12_disable";
		}
	}
}
function initText(text, className){
	text.value = text.title;
	if (trim(className).length<1)
	{
		text.className="font_12_disable";
	}
	else text.className = className + " font_12_disable";
}
function isPunct(src) {
	var regex = /!|"|#|\$|%|&|'|\(|\)|\*|\+|,|-|\.|\/|:|;|<|=|>|\?|@|\[|\|]|\^|_|`|\{|\||}|~/;
	return regex.test(src);
}

function copyToClipboard(txt) {
	if(window.clipboardData) {
		window.clipboardData.clearData();
		window.clipboardData.setData("Text", txt);
		alert("复制好了，按 CTRL + V 粘贴到BBS、博客里去吧！");
	} else if(navigator.userAgent.indexOf("Opera") != -1) {
		window.location = txt;
		alert("复制好了，按 CTRL + V 粘贴到BBS、博客里去吧！");
	} else if (window.netscape) {
		try {
			netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
		} catch (e) {
			alert("您正在使用的功能被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");
		}
		var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
		if (!clip)
			return;
		var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
		if (!trans)
			return;
		trans.addDataFlavor('text/unicode');
		var str = new Object();
		var len = new Object();
		var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
		var copytext = txt;
		str.data = copytext;
		trans.setTransferData("text/unicode",str,copytext.length*2);
		var clipid = Components.interfaces.nsIClipboard;
		if (!clip)
			return false;
		clip.setData(trans,null,clipid.kGlobalClipboard);
		alert("复制好了，按 CTRL + V 粘贴到BBS、博客里去吧！");
	}
}
function copy_clip(text2copy)
{
	if (window.clipboardData)
	{
		window.clipboardData.setData("Text",text2copy);
	}
	else
	{
		var flashcopier = 'flashcopier';
		if(!document.getElementById(flashcopier))
		{
			var divholder = document.createElement('div');
			divholder.id = flashcopier;
			document.body.appendChild(divholder);
		}
		document.getElementById(flashcopier).innerHTML = '';
		var divinfo = '<embed src="/hot-docs/_clipboard.swf" FlashVars="clipboard='+escape(text2copy)+'" width="0" height="0" type="application/x-shockwave-flash"></embed>';//这里是关键
		document.getElementById(flashcopier).innerHTML = divinfo;
	}
	return true;
}

function showGroup(i) {
	document.getElementById('active_group_img_'+i).style.display='';
	document.getElementById('active_group_info_'+i).style.display='';
	document.getElementById('clear_'+i).style.display='';
}
function showMyChooseTab(index){
	for(var i=0; i<3; i++){
		if(i != index){
			hideElement("tab" + i + "data");
			var tab = document.getElementById("tab" + i);
			if(tab != null){
				tab.className = "";
			}
		}
	}
	var tab = document.getElementById("tab" + index);
	tab.className = "selected";
	showElement("tab" + index + "data");
}

var title_last = document.title;
var step = 0;
function flash_title() {
	step++;
	if (step == 3)
		step = 1;
	if (step == 1)
		document.title = '【新消息】- ' + title_last;
	if (step == 2)
		document.title = '【　　　】- ' + title_last;
	setTimeout("flash_title()",1000);
}

function validateImageFile(imageElementId, imageRequired, fileNameId, imageErrorEleId,errorMessage){
	if(typeof imageRequired == "undefined"){
		imageRequired = true;
	}
	if(typeof errorMessage == "undefined"){
		errorMessage = '图片格式错误. 请上传后缀为 JPG, JPEG, GIF, 或 PNG 格式的图片.';
	}
    var imageUrl = document.getElementById(imageElementId).value;

	  if(imageUrl == ""){
	    if(imageRequired){
	    	document.getElementById(imageErrorEleId).innerHTML = errorMessage;
	     setInputFocus(imageElementId);
	    }
	    return (!imageRequired);
	  }

		var isValidFileCheckForIE = true;
	  if ( navigator.appVersion.indexOf("MSIE") >= 0) {
	    var arVersion = navigator.appVersion.split("MSIE")
	    var version = parseFloat(arVersion[1])
	    if (version < 7.0) {

	      isValidFileCheckForIE = (imageUrl.indexOf(":") == 1);
	    }
	   }

    if(!isValidFileCheckForIE){
      document.getElementById(imageErrorEleId).innerHTML = '请指定一个正确的路径';
	    setInputFocus(imageElementId);
      return false;
    }

    if(!(imageUrl == "") && isValidFileCheckForIE) {

      var ext = imageUrl;
      var result = ext.lastIndexOf('.');

      if (result != -1) {
        ext = ext.substr(result+1).toLowerCase();
      }
      else{
        ext="";
      }

      if((ext == "jpeg") || (ext == "jpg") || (ext == "gif") || (ext == "png")){

        if(typeof fileNameId != "undefined" && fileNameId !=null){

         var fileNameEle = document.getElementById(fileNameId);
           fileNameEle.value = imageUrl;
        }
        if(typeof imageErrorEleId != "undefined" && imageErrorEleId !=null){
      	 document.getElementById(imageErrorEleId).innerHTML = '';
      	}
      	return true;
      }
      if(typeof imageErrorEleId != "undefined" && imageErrorEleId !=null){
	      document.getElementById(imageErrorEleId).innerHTML = errorMessage;
      }

      setInputFocus(imageElementId);

      return false;
    }
}
function setInputFocus(elemID) {
  var elem = document.getElementById(elemID);
  elem.focus();
  //elem.select();/*IE does not set the focus on a newly visible form*/
  // In IE7 this was causing the file dialog to open upon 2 clicks.
}
function $() {
	var elements = new Array();
	for (var i = 0; i < arguments.length; i++) {
		var element = arguments[i];

		if (typeof element == 'string')
			element = document.getElementById(element);

		if (arguments.length == 1)
			return element;

		elements.push(element);
	}

	return elements;
}
function showTopicTab(index){
	for(var i=0; i<2; i++){
		if(i != index){
			hideElmt("tab" + i + "data");
			var tab = document.getElementById("tab" + i);
			if(tab != null){
				tab.className = "";
			}
		}
	}
	var tab = document.getElementById("tab" + index);
	if(tab){
		tab.className = "selected";
	}
	showElmt("tab" + index + "data");
}
function hideElmt(elemID) {
  var elem = document.getElementById(elemID);
  elem.style.display = 'none';
}
function showElmt(elemID) {
  var elem = document.getElementById(elemID);
  elem.style.display = '';
}
function closeTips() {
	asynSubmit("/closeTips","","post",function closeTipsCallback(){
		if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			$('tips').style.display = "none";
		}
		else processError(xmlHttp.status, xmlHttp.responseText);
	}
	});
}