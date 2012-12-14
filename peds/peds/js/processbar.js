var xmlhttp;
window.onload = function() {
	xmlhttp = initXMLHttpClient();
};
initXMLHttpClient = function() {
	var XMLHTTP_IDS, xmlhttp, success = false, i;
	// Mozilla/Chrome/Safari/IE7+ (normal browsers)
	try {
		xmlhttp = new XMLHttpRequest();
	}
	// IE(?!)
	catch (e1) {
		XMLHTTP_IDS = [ 'MSXML2.XMLHTTP.5.0', 'MSXML2.XMLHTTP.4.0',
				'MSXML2.XMLHTTP.3.0', 'MSXML2.XMLHTTP', 'Microsoft.XMLHTTP' ];
		for (i = 0; i < XMLHTTP_IDS.length && !success; i++) {
			try {
				success = true;
				xmlhttp = new ActiveXObject(XMLHTTP_IDS[i]);
			} catch (e2) {
			}
		}
		if (!success) {
			throw new Error('Unable to create XMLHttpRequest!');
		}
	}
	return xmlhttp;
};
function beforeDecrypt(cryptbt, waitbar) {
	cryptbt.style.display = 'none';
	waitbar.style.display = 'inline';
};
function afterDecrypt(cryptbt, waitbar) {
	waitbar.style.display = 'none';
	cryptbt.style.display = 'inline';
};
//暂时失效/生效所有超链接
function disableAllDecrypt_a(flag,tagname) {
	var atags = document.getElementsByName(tagname);
	for ( var i = 0; i < atags.length; i++) {
		if (flag == true) {
			atags[i].className = "decrypt_a";
		} else {
			atags[i].className = "decrypt_b";
		}
	}
}
function ajaxDecryptFile(id, url,action) {
	var pwd = window
			.showModalDialog(
					"showpassword.jsp",
					"box",
					"dialogHeight: 60px; dialogWidth:100px; edge: Sunken; center: Yes; resizable: No; status: No;");

	var cryptbt = document.getElementById("decrypt" + id);
	var waitbar = document.getElementById("waitbar" + id);
	var send;
	beforeDecrypt(cryptbt, waitbar);
	if(action =='decrypt'){
	disableAllDecrypt_a(true,'decrypt_a');
	   send="?method=decryptFile&id=" + id + "&password="+pwd;
	}
	if(action=='encrypt'){
	   send="?method=encryptFile&id=" + id + "&password="+pwd;
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4) {
			if (xmlhttp.status == 200) {
				var textrecive = xmlhttp.responseText;
				try {
					textrecive = eval("(" + textrecive + ")");
					disableAllDecrypt_a(false,'decrypt_a');
					afterDecrypt(cryptbt, waitbar);
					alert(textrecive.msg);
				} catch (z) {
					alert(z);
				}
			}
		}
	};
	try {
		url += send;
		xmlhttp.open("POST", url, true);
		xmlhttp.send();
	} catch (z) {
		alert(z);
	}
};