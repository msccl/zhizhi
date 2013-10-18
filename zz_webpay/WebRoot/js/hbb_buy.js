var xmlHttp;
var requestType="";
function createXMLHttpRequest()
{
	if(window.ActiveXObject)
	{
		xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else if(window.XMLHttpRequest)
	{
		xmlHttp=new XMLHttpRequest();
	}
}

function fillForm() {
	try {
		var expressValue;
		var expressUnique;
		var expressIncrement;
		var radios = document.getElementsByName("express");
        for(var i=0;i<radios.length;i++)
        {
            if(radios[i].checked==true)
            {
                expressUnique = radios[i].value;
                expressValue = radios[i].getAttribute("price");
                expressIncrement = radios[i].getAttribute("increment");
            }
        }
        
			document.getElementById("num_2").innerHTML = expressValue;
			document.getElementById("money_total").innerHTML = parseFloat(document.getElementById("num_1").innerHTML) - parseFloat(expressValue);
			document.getElementById("price").value = document.getElementById("money_total").innerHTML; 
	}catch(e) {
	}
}


function addressFormValidate() {
	if (document.form1.recname.value=="") {
			alert("收货人姓名不能为空!");
			document.form1.recname.focus();
			return false;
	} else if (document.form1.n_province.value=="-1") {
			alert("请选择省!");
			document.form1.n_province.focus();
			return false;
	} else if (document.form1.n_city.value=="-1") {
			alert("请选择城市!");
			document.form1.n_city.focus();
			return false;
	} else if (document.form1.n_area.value=="-1") {
			alert("请选择城区!");
			document.form1.n_area.focus();
			return false;
	} else if (document.form1.address.value=="") {
			alert("街道地址不能为空!");
			document.form1.address.focus();
			return false;
	} else if (document.form1.zipcode.value=="") {
			alert("邮政编码不能为空!");
			document.form1.zipcode.focus();
			return false;
	} else if (document.form1.phoneSection.value=="" && document.form1.phoneCode.value=="" && document.form1.mobilePhone.value=="") {
			alert("为了让我们能够联系到您，至少要留一个电话吧!");
			document.form1.phoneSection.focus();
			return false;
	}
	return true;
}


function validate() {
	if(document.form1.num.value=="") {
		alert("至少购买一件吧！！！");
		return false;
	}
	
	var addressradios = document.getElementsByName("address");
	var addresscheck = false;
	var addressvalue = "-1";
    for(var i=0;i<addressradios.length;i++) {
		if(addressradios[i].checked==true) {
			addresscheck = true;
			addressvalue = addressradios[i].value;
			break;
		}
	}
	
	if(addresscheck==false) {
		alert("请选择收货地址或者点击其他地址新增一项!!");
		return false;
	}
	
	if(addressvalue == "-1") {
		if (document.form1.n_province.value=="-1") {
				alert("请选择省!");
				document.form1.n_province.focus();
				return false;
		} else if (document.form1.n_city.value=="-1") {
				alert("请选择城市!");
				document.form1.n_city.focus();
				return false;
		} else if (document.form1.n_area.value=="-1") {
				alert("请选择城区!");
				document.form1.n_area.focus();
				return false;
		} else if (document.form1.deliverAddress.value=="") {
				alert("街道地址不能为空!");
				document.form1.deliverAddress.focus();
				return false;
		} else if (document.form1.zipcode.value=="") {
				alert("邮政编码不能为空!");
				document.form1.zipcode.focus();
				return false;
		} else if (document.form1.recname.value=="") {
				alert("收货人姓名不能为空!");
				document.form1.recname.focus();
				return false;
		} else if (document.form1.phoneSection.value=="" && document.form1.phoneCode.value=="" && document.form1.mobilePhone.value=="") {
				alert("为了让我们能够联系到您，至少要留一个电话吧!");
				document.form1.mobilePhone.focus();
				return false;
		}
	}


	var radios = document.getElementsByName("express");
	var expresscheck = false;
    for(var i=0;i<radios.length;i++) {
		if(radios[i].checked==true) {
			expresscheck = true;
			break;
		}
	}

	if(expresscheck==false) {
		alert("请选择快递方式！");
		return false;
	}

	return true;
}