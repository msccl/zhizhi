var xmlHttp = false;
try
{
	xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
}
catch (e)
{
	try
	{
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	catch (e)
	{
		xmlHttp = false;
	}
}
if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
	xmlHttp = new XMLHttpRequest();
}

function asynSubmit(url,data,submit_method,processResponse) {
	xmlHttp.open(submit_method.toUpperCase(), submit_method.toUpperCase() == "GET"? encodeURI(url+"?"+data) : url, true);
	xmlHttp.onreadystatechange = processResponse;	
	xmlHttp.setRequestHeader("Cache-Control","no-cache");
	if (submit_method.toUpperCase() == "POST") {
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xmlHttp.send(encodeURI(data));
	} else xmlHttp.send(null);
}

function asynSubmit(url,data,submit_method,callbackMethod,params) {
	xmlHttp.open(submit_method.toUpperCase(), submit_method.toUpperCase() == "GET"? encodeURI(url+"?"+data) : url, true);
	xmlHttp.onreadystatechange = function() {processResponse(callbackMethod,params);};
	xmlHttp.setRequestHeader("Cache-Control","no-cache");
	if (submit_method.toUpperCase() == "POST") {
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xmlHttp.send(encodeURI(data));
	} else xmlHttp.send(null);
}

function processResponse(callbackMethod,params) {
	if (xmlHttp.readyState == 4)
		eval(callbackMethod)(params);
}

function generateAJAXResponseHTML(domNodes){
	var nodeHtml = '';
	for(var i=0; i<domNodes.length; i++){
		nodeHtml += domNodes[i].nodeValue;
	}
	return nodeHtml;
}

function processError(status, response) {

	if (status == 404)
		alert("对不起，您所访问的页面不存在或已经被删除！");
	else if (status == 403) {
		alert("登录超时！请返回重试……");
		location.href='/user/login';
	}
	else if (status == 400 || status == 503)
		alert(response);
	else if (status == 500)
		alert("内部错误调试中，请稍后再试！");

}


function initVarMap(varMap) {
	var data = "";
	if (varMap && null != varMap) {
		for (var name in varMap) {
			if (varMap[name] != null) {
				if (data == "")
					data = name + '=' + encodeURIComponent(varMap[name]);
				else
					data += '&' + name + '=' + encodeURIComponent(varMap[name]);
			}
		}
	}
	return data;
}


function queryExpress(provincecode,channel,productid) {
    var url="/expressdata.do";
    var data="provincecode="+provincecode+"&channel="+channel+"&pid="+productid;
    asynSubmit(url,data,"GET",expressResult);
	return true;
}

function expressResult(){
  if(xmlHttp.readyState==4){
    var response=xmlHttp.responseText;
    if(xmlHttp.status==200){
		document.getElementById("expressdata").innerHTML=xmlHttp.responseText;
    }
  }
}


function queryCity(citycode,channel,productid){
    var url="/areadata.do";
    var data="provincecode="+citycode+"&channel="+channel+"&pid="+productid;
    asynSubmit(url,data,"GET",showcity);
	return true;
}


function showcity(){
  if(xmlHttp.readyState==4){
    var response=xmlHttp.responseText;
    if(xmlHttp.status==200){
	   	arr = xmlHttp.responseText.split("||");
		document.getElementById("city").innerHTML=arr[0];
		document.getElementById("expressdata").innerHTML=arr[1];
		document.getElementById("area").innerHTML="<select id='n_area' name='n_area'><option value='-1'>请选择城区</option></select>";
    }
  }
}


function queryArea(citycode){
    var url="/areadata.do";
    var data="citycode="+citycode;
    asynSubmit(url,data,"GET",showarea);
	return true;
}

function showarea(){
  if(xmlHttp.readyState==4){
    var response=xmlHttp.responseText;
    if(xmlHttp.status==200){
		document.getElementById("area").innerHTML=xmlHttp.responseText;
    }
  }
}

