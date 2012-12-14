<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/global.jsp"%>
<html>
	<script language="JavaScript1.1"
		src="/icbc/newperbank/js/includes/ControlsId.js"></script>
	<object classid=clsid:84894428-B1F9-4C88-8A45-D6B8524E53B3
		id=IcbcAssistComm style="HEIGHT: 0px; WIDTH: 0px">
	</object>
	<script language="JavaScript1.1"
		src="/icbc/newperbank/js/preventFresh.js"></script>
	<script src="/icbc/newperbank/js/includes/pebank_browsercompatible.js"></script>
	<script src="/icbc/newperbank/desktop/js/jquery-1.4.3.min.js"></script>
	<style>
html {
	display: none;
}
</style>
	<script> 	var browserflag = null;var macflag = false;//防止恶意网站iframe钓鱼if( self.location.host == top.location.host ) { document.documentElement.style.display = 'block' ; } else { top.location = self.location ; } </script>
	<HEAD>
		<TITLE>工商银行网上银行</TITLE>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
		<link href="/icbc/newperbank/css/style_login.css" rel="stylesheet"
			type="text/css">
		<script language="JavaScript" src="/icbc/newperbank/js/swapimage.js"></script>
		<script language="JavaScript" src="/icbc/newperbank/js/checkacct.js"></script>
		<script language="Javascript" src="/icbc/newperbank/js/floatTip.js"></script>
		<script language="Javascript" src="/icbc/newperbank/js/floatTip1.js"></script>
		<script language="Javascript"
			src="/icbc/newperbank/js/getcardbinfo.js"></script>
		<script language='JavaScript1.1'>cardBinList.add(new CardBin('621558','011','C','19','01000000000000000100100000000000','null','null'));cardBinList.add(new CardBin('621559','011','C','19','01000000000000000100100000000000','null','null'));cardBinList.add(new CardBin('489734','007','H','16','10000000001010000110000000000000','|001|038|','null'));cardBinList.add(new CardBin('513685','007','H','16','10000000000101000110000000000000','|001|038|','null'));cardBinList.add(new CardBin('427020','007','H','16','10000000001010000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('427029','007','H','16','10000000001010000110000000000000','|001|013|','null'));cardBinList.add(new CardBin('530990','007','H','16','10000000000101000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('438125','007','H','16','10000000001010000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('524047','007','H','16','10000000000101000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('370246','007','H','15','10000000010000000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('489735','007','H','16','10000000001010000110000000000000','|001|038|','null'));cardBinList.add(new CardBin('526836','007','H','16','10000000000101000110000000000000','|001|038|','null'));cardBinList.add(new CardBin('427030','007','H','16','10000000001010000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('427039','007','H','16','10000000001010000110000000000000','|001|013|','null'));cardBinList.add(new CardBin('530970','007','H','16','10000000000101000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('438126','007','H','16','10000000001010000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('427010','007','H','16','10000000001000100110000000000000','|001|014|','null'));cardBinList.add(new CardBin('370249','007','H','15','10000000010000100110000000000000','|001|014|','null'));cardBinList.add(new CardBin('427019','007','H','16','10000000001000100110000000000000','|001|013|','null'));cardBinList.add(new CardBin('558360','007','H','16','10000000000101100110000000000000','|001|014|','null'));cardBinList.add(new CardBin('489736','007','H','16','10000100001010100110000000000000','|001|038|','null'));cardBinList.add(new CardBin('370248','007','H','15','10000000010000100110000000000000','|001|014|','null'));cardBinList.add(new CardBin('955882','011','C','19','01000000000000000100000000000000','null','null'));cardBinList.add(new CardBin('622202','011','C','19','01000000000000000100100000000000','null','null'));cardBinList.add(new CardBin('622203','011','C','19','01000000000000000100000000000000','null','null'));cardBinList.add(new CardBin('955888','011','C','19','00100000000000000100000000000000','null','null'));cardBinList.add(new CardBin('622208','011','C','19','00100000000000000100100000000000','null','2'));cardBinList.add(new CardBin('370267','007','H','15','10000000010000000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('621288','011','C','19','00100000000000000000000000000000','null','3'));cardBinList.add(new CardBin('620058','011','C','19','01000000000000000000100000000000','null','null'));cardBinList.add(new CardBin('458441','007','H','16','10000000001010000110000000000000','|001|038|','null'));cardBinList.add(new CardBin('543098','007','H','16','10000000000101000110000000000000','|001|038|','null'));cardBinList.add(new CardBin('620086','015','C','19','00000000000000000100100000000000','null','null'));cardBinList.add(new CardBin('621225','011','C','19','01000000000000000100100000000000','null','null'));cardBinList.add(new CardBin('621226','011','C','19','01000000000000000100100000000000','null','null'));cardBinList.add(new CardBin('621227','011','C','19','00100000000000000100100000000000','null','2'));cardBinList.add(new CardBin('622206','007','H','16','10000000000000000110100000000000','|001|','null'));cardBinList.add(new CardBin('955880','003','C','19','00000000000000000100000000000000','null','null'));cardBinList.add(new CardBin('955881','003','C','19','00000000000000000100000000000000','null','null'));cardBinList.add(new CardBin('955883','014','C','19','00000000000000000000000000000000','null','null'));cardBinList.add(new CardBin('955885','014','C','19','00000000000000000000000000000000','null','null'));cardBinList.add(new CardBin('955887','014','C','19','00000000000000000000000000000000','null','null'));cardBinList.add(new CardBin('955889','014','C','19','00000000000000000000000000000000','null','null'));cardBinList.add(new CardBin('451804','007','H','16','10000100000000000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('622240','007','H','16','10000100000000000110000000000000','|001|','null'));cardBinList.add(new CardBin('458071','007','H','16','10000100000000000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('622245','007','H','16','10000100000000000110000000000000','|001|','null'));cardBinList.add(new CardBin('628286','007','H','16','10000100000000000110000000000000','|001|','null'));cardBinList.add(new CardBin('451810','007','H','16','10000000000000000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('622237','007','H','16','10000000000000000010000000000000','|001|','null'));cardBinList.add(new CardBin('622238','007','H','16','10000000000000000010000000000000','|001|','null'));cardBinList.add(new CardBin('622239','007','H','16','10000000000000000010000000000000','|001|','null'));cardBinList.add(new CardBin('622210','001','T','16','00000000000000000110000000000000','|001|','null'));cardBinList.add(new CardBin('622211','001','T','16','00000000000000000110000000000000','|001|','null'));cardBinList.add(new CardBin('622212','001','T','16','00000000000000000110000000000000','|001|','null'));cardBinList.add(new CardBin('622213','001','T','16','00000000000000000110000000000000','|001|','null'));cardBinList.add(new CardBin('622214','001','T','16','00000000000000000110000000000000','|001|','null'));cardBinList.add(new CardBin('622215','001','T','16','00000000000000000110000000000000','|001|','null'));cardBinList.add(new CardBin('622220','001','T','16','00000000000000010110000000000000','|001|','null'));cardBinList.add(new CardBin('622223','001','T','16','00000000000000010010000000000000','|001|','null'));cardBinList.add(new CardBin('622224','001','T','16','00000000000000010010000000000000','|001|','null'));cardBinList.add(new CardBin('622225','001','T','16','00000000000000010110000000000000','|001|','null'));cardBinList.add(new CardBin('622229','001','T','16','00000000000000010010000000000000','|001|','null'));cardBinList.add(new CardBin('622200','003','C','19','00000000000000000100000000000000','null','null'));cardBinList.add(new CardBin('955886','003','C','19','00001000000000000100000000000000','null','null'));cardBinList.add(new CardBin('621229','011','C','19','01000000000000000100100000000000','null','null'));cardBinList.add(new CardBin('621230','011','C','19','01000000000000000100100000000000','null','null'));cardBinList.add(new CardBin('620516','015','C','19','00000000000000000100100000000000','null','null'));cardBinList.add(new CardBin('621721','011','C','19','01000000000000000100000000000000','null','null'));cardBinList.add(new CardBin('621723','011','C','19','01000000000000000100000000000000','null','null'));cardBinList.add(new CardBin('621722','011','C','19','01000000000000000100000000000000','null','null'));cardBinList.add(new CardBin('621281','011','C','19','01000000000000000100000000000000','null','null'));cardBinList.add(new CardBin('622246','007','H','16','10000000000000000110000000000000','|001|','null'));cardBinList.add(new CardBin('548943','007','H','16','10000000000101000110000000000000','|001|013|','null'));cardBinList.add(new CardBin('544210','007','H','16','10000000000101000110000000000000','|001|013|','null'));cardBinList.add(new CardBin('451811','007','H','16','10000000000000000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('622235','007','H','16','10000000000000000110000000000000','|001|','null'));cardBinList.add(new CardBin('628288','007','H','16','10000000000000000110000000000000','|001|','null'));cardBinList.add(new CardBin('370247','007','H','15','10000000010000000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('622236','007','H','16','10000000000000000110000000000000','|001|','null'));cardBinList.add(new CardBin('356879','007','H','16','10000000000000000110010000000000','|001|014|','null'));cardBinList.add(new CardBin('356880','007','H','16','10000000000000000110010000000000','|001|027|','null'));cardBinList.add(new CardBin('623062','011','C','19','01000000000000000100000000000000','null','null'));cardBinList.add(new CardBin('356881','007','H','16','10000000000000000110010000000000','|001|014|','null'));cardBinList.add(new CardBin('356882','007','H','16','10000000000000000110010000000000','|001|027|','null'));cardBinList.add(new CardBin('543846','008','U','16','10000001000100000110000000000000','|013|001|','null'));cardBinList.add(new CardBin('427062','008','U','16','10000001001010000110000000000000','|013|001|','null'));cardBinList.add(new CardBin('454326','008','U','16','10000001001010000110000000000000','|013|001|','null'));cardBinList.add(new CardBin('545950','008','U','16','10000001000100000110000000000000','|013|001|','null'));cardBinList.add(new CardBin('427064','008','U','16','10000001001010000110000000000000','|013|001|','null'));cardBinList.add(new CardBin('454328','008','U','16','10000001001010000110000000000000','|013|001|','null'));cardBinList.add(new CardBin('427028','008','H','16','10000000001000000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('548259','008','H','16','10000000000101000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('427038','008','H','16','10000000001000000110000000000000','|001|013|','null'));cardBinList.add(new CardBin('402791','008','H','16','10000000001010000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('402792','008','H','16','10000000001000000110000000000000','|001|013|','null'));cardBinList.add(new CardBin('510529','008','H','16','10000000000101000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('439060','088','U','16','10000000101010000110000000000000','|013|','null'));cardBinList.add(new CardBin('439061','088','U','16','10000000101010000110000000000000','|013|','null'));cardBinList.add(new CardBin('454327','088','U','16','10000000101010000110000000000000','|013|','null'));cardBinList.add(new CardBin('479119','088','U','16','10000000101010000110000000000000','|013|','null'));cardBinList.add(new CardBin('479120','088','U','16','10000000101010000110000000000000','|013|','null'));cardBinList.add(new CardBin('541801','088','U','16','00000000100101000000000000000000','|013|','null'));cardBinList.add(new CardBin('541810','088','U','16','00000000100101000000000000000000','|013|','null'));cardBinList.add(new CardBin('552814','088','U','16','00000000100101000000000000000000','|013|','null'));cardBinList.add(new CardBin('524044','088','U','16','00000000100101000000000000000000','|013|','null'));cardBinList.add(new CardBin('524374','007','H','16','10000000000101000110000000000000','|001|014|038|013|028|012|029|018|027|015|087|','null'));cardBinList.add(new CardBin('528856','007','H','16','10000000000101000110000000000000','|001|014|038|013|028|012|029|018|027|015|087|','null'));cardBinList.add(new CardBin('550213','007','H','16','10000000000101000110000000000000','|001|014|038|013|028|012|029|018|027|015|087|','null'));cardBinList.add(new CardBin('625330','007','H','16','10000000000000000110100000000000','|001|','null'));cardBinList.add(new CardBin('625331','007','H','16','10000000000000000110100000000000','|001|','null'));cardBinList.add(new CardBin('625332','007','H','16','10000000000000000110100000000000','|001|','null'));cardBinList.add(new CardBin('374738','007','H','15','10000000010000000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('374739','007','H','15','10000000010000000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('622230','007','H','16','10000000000000000110000000000000','|001|014|','null'));cardBinList.add(new CardBin('622231','007','H','16','10000000000000000110000000000000','|001|013|','null'));cardBinList.add(new CardBin('622232','007','H','16','10000000000000000110000000000000','|001|038|','null'));cardBinList.add(new CardBin('622233','007','H','16','10000000000000000110000000000000','|001|014|038|013|028|012|029|018|027|015|087|','null'));cardBinList.add(new CardBin('622234','007','H','16','10000000000000000110000000000000','|001|027|','null'));cardBinList.add(new CardBin('53098','001','T','16','00000010000000000110000000000000','|001|','null'));cardBinList.add(new CardBin('88888','002','T','16','00000000000000000100000000000000','|001|','null'));cardBinList.add(new CardBin('33333','003','T','16','00000000000000000100000000000000','null','null'));cardBinList.add(new CardBin('45806','001','T','16','00000010000000000110000000000000','|001|','null'));cardBinList.add(new CardBin('9333','002','T','19','00000000000000000100000000000000','|001|','null'));cardBinList.add(new CardBin('9000','005','T','19','00000000000000000100000000000000','null','null'));cardBinList.add(new CardBin('9222','004','T','19','00000000000000000100000000000000','null','null'));currTypeList.add(new CurrType('018','新加坡元','SGD','11111011111111110000000000000000','2','SGD','SGD'));currTypeList.add(new CurrType('015','瑞士法郎','CHF','11111011111111110000000000000000','2','CHF','CHF'));currTypeList.add(new CurrType('014','美元','USD','11111011111111110000000000000000','2','$','USD'));currTypeList.add(new CurrType('807','钯金(盎司)','XPD','00000100000000000000000000000000','2','XPD','XPD'));currTypeList.add(new CurrType('013','港币','HKD','11111011111111110000000000000000','2','HKD','HKD'));currTypeList.add(new CurrType('012','英镑','GBP','11111011111111110000000000000000','2','GBP','GBP'));currTypeList.add(new CurrType('805','铂金(盎司)','XPT','00000000000000000000000000000000','2','XPT','XPT'));currTypeList.add(new CurrType('803','白银(盎司)','XAG','00000000000110000000000000000000','2','XAG','XAG'));currTypeList.add(new CurrType('801','黄金(盎司)','XAU','00000000000110000000000000000000','2','XAU','XAU'));currTypeList.add(new CurrType('029','澳大利亚元','AUD','11111011111111110000000000000000','2','AUD','AUD'));currTypeList.add(new CurrType('028','加拿大元','CAD','11111011111111110000000000000000','2','CAD','CAD'));currTypeList.add(new CurrType('027','日元','JPY','11111011111111110000000000000000','0','JPY','JPY'));currTypeList.add(new CurrType('087','新西兰元','NZD','01101010010111000000000000000000','2','NZD','NZD'));currTypeList.add(new CurrType('023','挪威克郎','NOK','01101010010111000000000000000000','2','NOK','NOK'));currTypeList.add(new CurrType('022','丹麦克朗','DKK','01101010010111000000000000000000','2','DKK','DKK'));currTypeList.add(new CurrType('084','泰国铢','THB','01100010000110000000000000000000','2','THB','THB'));currTypeList.add(new CurrType('021','瑞典克郎','SEK','11101010010111000000000000000000','2','SEK','SEK'));currTypeList.add(new CurrType('082','菲律宾比索','PHP','01100010000110000000000000000000','2','PHP','PHP'));currTypeList.add(new CurrType('081','澳门元','MOP','01100010000110000000000000000000','2','MOP','MOP'));currTypeList.add(new CurrType('907','钯金(克)','PDG','00000100000000000000000000000000','2','PDG','PDG'));currTypeList.add(new CurrType('038','欧元','EUR','11111011111111110000000000000000','2','EUR','EUR'));currTypeList.add(new CurrType('905','铂金(克)','PTG','00000000000000000000000000000000','2','PTG','PTG'));currTypeList.add(new CurrType('903','白银(克)','AGG','00000100000000000000000000000000','2','AGG','AGG'));currTypeList.add(new CurrType('901','黄金（克）','AUG','00000100000110000000000000000000','2','AUG','AUG'));currTypeList.add(new CurrType('001','人民币','RMB','00010010000110000000000000000000','2','￥','RMB'));</script>
		<script language="VBScript">			sub InstallRootCert()				if Navigator.appName<>"Microsoft Internet Explorer" then exit sub				'Get IE version				nVersionBegin=InStr(Navigator.userAgent,"MSIE ")+5				nVersionEnd=InStr(nVersionBegin,Navigator.userAgent,";")				Version=mid(Navigator.userAgent,nVersionBegin,nVersionEnd-nVersionBegin)				if Version>5.0 then					document.body.addBehavior("#default#clientCaps")					sVersion=document.body.getComponentVersion("{89820200-ECBD-11CF-8B85-00AA005B4383}","componentid")					sVersion=Replace(sVersion,",",".")					nBuildStart=InStr(3,sVersion,".")+1					nBuildEnd=InStr(nBuildStart,sVersion,".")-1					Build=mid(sVersion,nBuildStart,nBuildEnd-nBuildStart+1)				end if				if Version<6.0 or (Version=5.01 and Build<3315) then '***本行判断版本号，可以修改***					document.all.RootCertFrame.src="/icbc/newperbank/InstallRootCert.htm"				end if			end sub		</script>
		<script language="JavaScript">		<!--			//处理safari版控件 回车自动登录			//1） 增加JS函数，函数名称必须是“XReturnDown”，safeSubmit函数为页面中原来响应提交功能的函数；			var isEntered=false;//防止连续点击enter键造成浏览器死掉			function XReturnDown(item)			{ 			  if(item == "safeSubmit1"&&!isEntered)			  {			  				    document.getElementById('submitaid').focus();			    			    mySubmit();			    			  }else if(item == "verify")			  {			  	document.all.KeyPart.focus();			  }			}			var desktop=false;			function winopenrisk(){				window.open("../main/riskTipMore.jsp","","top=0, left=0,toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes, status=yes,location=yes");			}			function winopenspecial(){				window.open("../main/specialTipMore.jsp","","top=0, left=0,toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes, status=yes,location=yes");			}			function disagreement(){				alert("您尚未接受我行电子银行章程与服务协议，无法登录");				return;			}						function getfocus1(name, event){				if(pebankBrowserCompatible.getKeycode(event)==13){									eval("document.all."+name).focus();}			}								function mySubmit(){				try{								if(pebankBrowserCompatible.isSafari()){						if(typeof(navigator.mimeTypes['application/x-npsubmit-plugin'])=="undefined"){							alert("您尚未安装工行网银控件，请您先下载安装工行网银控件");							return;						}else{							var vs = "";							try{								vs=document.all.safeEdit1.getVersion();							}catch(e){vs="";}							if(vs==""||'1.0.0.5'!=document.all.safeEdit1.getVersion()){								alert("您安装的工行网银控件版本过低，请您先下载安装工行网银控件");								return;							}						}					}					var cardNum=document.logonform.logonCardNum.value;					if(cardNum.length>17&&cardNum.length!=19){							alert("请输入正确的卡(账)号或登录ID!");							logonform.logonCardNum.select();							logonform.logonCardNum.focus();							return; 	        				     						}					if(cardNum.length==0){							alert("卡(账)号或登录ID不能为空!");							logonform.logonCardNum.select();							logonform.logonCardNum.focus();							return; 	        				     						}  										if (document.all.safeEdit1.getLength()<4){						alert("密码长度至少应该为四位!");						document.all.safeEdit1.focus();						return;					}					if (!document.all.safeEdit1.isValid()){				   		alert("输入的密码不合法，请重新输入！");				   		document.all.safeEdit1.focus();						return ;					}								if (!document.all.KeyPart.isValid()){						alert("输入的验证码不合法，请重新输入！");					  	document.all.KeyPart.focus();						return ;					}										var cardbinstr=cardBinList.SupportCardBin(9);      				var cardbin="|"+cardBinList.getCardBinNoValid(cardNum)+"|";										if(cardNum.length==15&&cardbinstr.indexOf(cardbin)!=-1&&!Card2121Check15(cardNum)){						alert("请输入正确的卡(账)号!");						logonform.logonCardNum.select();						logonform.logonCardNum.focus();						return;					}else if(cardNum.length==19&&!(Card2112Check19(cardNum)||AcctCheck19(cardNum))){						alert("请输入正确的卡(账)号!");						logonform.logonCardNum.select();						logonform.logonCardNum.focus();						return;					}else if(cardNum.length==16&&!Card2112Check16(cardNum)){						alert("请输入正确的卡(账)号!");						logonform.logonCardNum.select();						logonform.logonCardNum.focus();						return;					}else{						var re =/\W/;						if (re.test(cardNum)){							alert("请输入正确的登录ID!");							logonform.logonCardNum.select();							logonform.logonCardNum.focus();							return;						}					}																				var nextYear = new Date();					nextYear.setFullYear(nextYear.getFullYear()+1);					var expires = nextYear.toGMTString();						document.cookie = "LogonStyleSelected=" + "0; expires="+expires;						if(!browserflag&&desktop&&!macflag){							alert("个性版仅支持ie7或ie8版本，请升级浏览器版本后再登录个性版。");							return;					}											if(desktop){							document.logonform.ComputID.value="12";							document.cookie = "LogonStyleSelected=" + "1; expires="+expires;						}										document.logonform.action="/servlet/com.icbc.inbs.servlet.ICBCINBSEstablishSessionServlet";															document.all.safeSubmit1.reset();					document.all.KeyPart.commitKeyPart(document.all.safeEdit1);					document.all.safeEdit1.commit(document.all.safeSubmit1);					document.all.KeyPart.commitKeyPart(document.all.KeyPart);					document.all.KeyPart.commit(document.all.safeSubmit1);					document.all.safeSubmit1.submit(logonform);					isEntered=true;					document.getElementById("submitkey").innerHTML = "<img src=\"/icbc/newperbank/images/denglu.gif\" border=\"0\">";					return false;				}catch(Exception){					alert("控件Exception:" + Exception);									}			}			//-->		</script>
	</HEAD>
	<BODY
		onload="javascript:logonform.logonCardNum.focus();hasInstallNetSign12();">
		<object id="safeSubmit1"
			codebase="/icbc/newperbank/AxSafeControls.cab#version=1,0,0,7"
			classid="CLSID:8D9E0B29-563C-4226-86C1-5FF2AE77E1D2" height=0 width=0></object>
		<iFrame id=RootCertFrame
			style="width: 0; height: 0; visibility: hidden" src="/indexicbc.htm"></iFrame>
		<script language="vbscript">InstallRootCert</script>
		<div id="tooltip2"
			style="position: absolute; visibility: hidden; clip: rect(0, 400, 400, 0); width: 400px; background-color: #FFFFFF; height: 15px">
			<layer name="nstip" width="1000px" bgColor="#FF0000"></layer>
		</div>
		<!--head begin -->
		<table width="800" border="0" align="center" cellpadding="0"
			cellspacing="0" id="logontb">
			<tr>
				<td align="right" width="243">
					&nbsp;
				</td>
				<td width="557" height=25
					background="/icbc/newperbank/images/login_02.gif">
					<table width="45%" border="0" align="right" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="5" colspan="6"></td>
						</tr>
						<td width="25%" nowrap>
							<a href="http://www.icbc.com.cn" title="点击进入工行首页"
								class="link_top" target="_parent">工行首页</a>
						</td>
						<td width="5%">
							|
						</td>
						<td width="25%" nowrap>
							<a
								href="https://corporbank.icbc.com.cn/icbc/corporbank/logon.jsp"
								title="点击进入企业网上银行" class="link_top" target="_parent">企业网上银行</a>
						</td>
						<td width="5%">
							|
						</td>
						<td width="25%" nowrap>
							<a href="/icbc/enperbank/index.jsp" title="点击进入英文版"
								class="link_top" target="_parent">English</a>
						</td>
					</table>
				</td>
			</tr>
			<tr>
				<td WIDTH=800 HEIGHT=61 colspan="2" align="right" valign="top"
					background="/icbc/newperbank/images/login_05.gif">
					<table width="42%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="font_gray">
								建议将分辨率调整为1024*768（举例）可获得最佳使用效果
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td></td>
				<td height="10"></td>
			</tr>
		</table>
		<!--head end -->
		<table width="798" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="187" height="5"></td>
				<td width="611" rowspan="2">
					<table width="90%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="6%" align="center">
								<img src="/icbc/newperbank/images/login_03.gif" width="6"
									height="11">
							</td>
							<td width="14%" class="font_stred">
								系统公告：
							</td>
							<td width="82%">
								<marquee onMouseOver="this.stop()" onMouseOut="this.start()"
									scrollamount="3" width="400">
									<a
										href="http://www.icbc.com.cn/ICBC/html/guanggao/2012nian/1101yghsjyhybwdchl/mobilebank_event.html"
										class="link_blue" target="_blank">用工行手机银行赢百万多重好礼&nbsp;1元秒杀VOLVO&nbsp;S60轿车</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a
										href="http://www.icbc.com.cn/icbc/%e5%b9%bf%e5%91%8a%e9%a1%b5%e9%9d%a2/%e7%bd%91%e7%ab%99%e5%ae%a3%e4%bc%a0%e5%b9%bf%e5%91%8a%e9%a1%b5%e9%9d%a2/%e6%80%bb%e8%a1%8c%e5%b9%bf%e5%91%8a%e9%a1%b5%e9%9d%a2/2012/0829%e5%b7%a5%e8%a1%8c%e8%b4%a6%e6%88%b7%e8%b4%b5%e9%87%91%e5%b1%9e/0829%e5%b7%a5%e8%a1%8c%e8%b4%a6%e6%88%b7%e8%b4%b5%e9%87%91%e5%b1%9e3.htm"
										class="link_blue" target="_blank">工行账户贵金属，惊喜活动强力出击</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="null" class="link_blue" target="_blank">积存金、如意金积存产品已合并至”积存贵金属”栏目下</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</marquee>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td width="187" height="13">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td bgcolor="#D8D8D8" width="200"></td>
							<td width="12" height="13">
								<img src="/icbc/newperbank/images/login_01.gif" width="12"
									height="13">
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="3" colspan="2" bgcolor="#D8D8D8"></td>
			</tr>
			<tr>
				<td height="500" valign="top" bgcolor="#E7E7E7">
					<!--left begin -->
					<table width="50%" height="30" border="0" align="center"
						cellpadding="0" cellspacing="0">
						<tr>
							<td>
								&nbsp;
							</td>
						</tr>
					</table>
					<table width="175" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td>
								<img src="/icbc/newperbank/images/login_30.gif" width="175"
									height="30">
							</td>
						</tr>
						<tr>
							<td background="/icbc/newperbank/images/login_31.gif">
								<table width="90%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<td height="25">
										<a href="/icbc/newperbank/regtip.jsp" class="link_gray"
											title="自助开通网上银行，您就可以办理账户查询以及网上买卖证券、外汇、黄金、保险等业务。">网上银行自助注册</a>
									</td>
									<tr>
										<td height="1"
											background="/icbc/newperbank/images/dot_gray.gif"></td>
									</tr>
									<td height="25">
										<a href="/icbc/newperbank/logonIdsearch125.jsp"
											class="link_gray" title="点击进入找回用户名功能。">找回用户名</a>
									</td>
									<tr>
										<td height="1"
											background="/icbc/newperbank/images/dot_gray.gif"></td>
									</tr>
									<td height="25">
										<a href="/icbc/newperbank/logonFreeze.jsp" class="link_gray"
											title="点击进入自助冻结网上银行登录。">自助冻结网上银行登录</a>
									</td>
									<tr>
										<td height="1"
											background="/icbc/newperbank/images/dot_gray.gif"></td>
									</tr>
									<td height="25">
										<a href="/icbc/newperbank/wapLogonFreeze.jsp"
											class="link_gray" title="点击进入自助冻结手机银行登录">自助冻结手机银行登录</a>
									</td>
									<tr>
										<td height="1"
											background="/icbc/newperbank/images/dot_gray.gif"></td>
									</tr>
									<td height="25">
										<a href="/icbc/newperbank/bankbook/bankbook_index.jsp"
											class="link_gray" title="使用中国工商银行的活期存折账号和密码登录，您就可以办理账户查询业务。">存折版登录</a>
									</td>
									<tr>
										<td height="1"
											background="/icbc/newperbank/images/dot_gray.gif"></td>
									</tr>
									<td height="25">
										<a
											href="/icbc/newperbank/wap/wap_register_index.jsp?channel=1"
											class="link_gray"
											title="立即开通手机银行，您可以办理账户查询、转账、缴费、投资理财、手机支付、信用卡等业务。">手机银行自助注册</a>
									</td>
									<tr>
										<td height="1"
											background="/icbc/newperbank/images/dot_gray.gif"></td>
									</tr>
									<td height="25">
										<a href="/icbc/newperbank/binding/binding_confirm_submit.jsp"
											class="link_gray" title="为客户提供手机号/E-mail与账号绑定关系确认的功能">手机号/E-mail与账号绑定确认</a>
									</td>
									<tr>
										<td height="1"
											background="/icbc/newperbank/images/dot_gray.gif"></td>
									</tr>
									<td height="25">
										<a
											href="/icbc/newperbank/e_card/ecard_financing_findname_index.jsp"
											class="link_gray" title="">e理财综合服务</a>
									</td>
									<tr>
										<td height="1"
											background="/icbc/newperbank/images/dot_gray.gif"></td>
									</tr>
									<tr>
										<td>
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<img src="/icbc/newperbank/images/login_32.gif" width="175"
									height="8">
							</td>
						</tr>
					</table>
					<br>
					<table width="175" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td>
								<img src="/icbc/newperbank/images/login_33.gif" width="175"
									height="31">
							</td>
						</tr>
						<tr>
							<td background="/icbc/newperbank/images/login_35.gif">
								<table width="90%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											<a
												href="http://www.icbc.com.cn/ICBCCOLLEGE/client/page/PageShow.aspx"
												target="_parent" class="link_gray"
												title="使用网上银行时的常见问题及解决方法。">热点问题&gt;</a>
										</td>
										<td>
											<a href="https://service.icbc.com.cn/bbs/index.jsp"
												target="_parent" class="link_gray"
												title="如果您有任何疑问，请进入论坛提出问题、立即得到解答。">工行论坛&gt;</a>
										</td>
									</tr>
									<tr>
										<td height="1"
											background="/icbc/newperbank/images/dot_gray.gif" colspan="2"></td>
									</tr>
									<tr>
										<td>
											<a
												href="http://www.icbc.com.cn/icbc/%e7%94%b5%e5%ad%90%e9%93%b6%e8%a1%8c/%e5%ae%89%e5%85%a8%e4%b8%93%e5%8c%ba/"
												target="_parent" class="link_gray"
												title="提供专业建议，协助您安全地使用网上银行。">安全提示&gt;</a>
										</td>
										<td>
											<a
												href="http://www.icbc.com.cn/icbc/html/download/yanshichengxu/personalbank/Perbank/index.htm"
												target="_parent" class="link_gray"
												title="提供网上银行的模拟程序，协助您了解并体验网上银行服务。">动态演示&gt;</a>
										</td>
									</tr>
									<tr>
										<td height="1"
											background="/icbc/newperbank/images/dot_gray.gif" colspan="2"></td>
									</tr>
									<tr>
										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<img src="/icbc/newperbank/images/login_38.gif" width="175"
									height="5">
							</td>
						</tr>
					</table>
					<br>
					<table border="0" align="center" cellpadding="0" cellspacing="0">
						<tr valign="top">
							<td>
								<a
									href="http://www.icbc.com.cn/ICBC/html/guanggao/2012nian/0511dzyhxlhd/TransferEvent.html"
									target="_blank"><img
										src="/servlet/com.icbc.inbs.servlet.ICBCHttpSourceServlet?urlName=ADImagesPath&image=zsz.gif"
										border="0" width="175" height="75"> </a>
							</td>
						</tr>
					</table>
					<table width="50%" height="20" border="0" align="center"
						cellpadding="0" cellspacing="0">
						<tr>
							<td>
								&nbsp;
							</td>
						</tr>
					</table>
					<!--left end -->
				</td>
				<td class="viploginbg" valign="top">
					<table width="100%" height="77" border="0" align="center"
						cellpadding="0" cellspacing="0"
						background="/icbc/newperbank/images/login_04bg.gif">
						<tr>
							<td width="1" bgcolor="ffffff"></td>
							<td width="30">
								&nbsp;
							</td>
							<td>
								<a
									href="http://www.icbc.com.cn/icbc/%e7%94%b5%e5%ad%90%e9%93%b6%e8%a1%8c/%e5%ae%89%e5%85%a8%e4%b8%93%e5%8c%ba/"
									target="_blank"><img
										src="/icbc/newperbank/images/loginn_03-01.gif" alt="安全须知与在线杀毒"
										title="安全须知与在线杀毒"
										onMouseOver="src='/icbc/newperbank/images/loginn_03-01-l.gif'"
										onMouseOut="src='/icbc/newperbank/images/loginn_03-01.gif'"
										name="Image41" width="130" height="47" border="0"> </a>
							</td>
							<td>
								<img src="/icbc/newperbank/images/login_09.gif" width="45"
									height="44">
							</td>
							<td>
								<a
									href="http://www.icbc.com.cn/ICBC/%e7%bd%91%e4%b8%8a%e9%93%b6%e8%a1%8c/%e4%b8%aa%e4%ba%ba%e7%bd%91%e4%b8%8a%e9%93%b6%e8%a1%8c/"
									target="_blank"><img
										src="/icbc/newperbank/images/but_on01.gif" alt="金融@家能做什么"
										title="金融@家能做什么"
										onMouseOver="src='/icbc/newperbank/images/but_on01-l.gif'"
										onMouseOut="src='/icbc/newperbank/images/but_on01.gif'"
										name="Image43" width="128" height="49" border="0"> </a>
							</td>
							<td>
								<img src="/icbc/newperbank/images/login_09.gif" width="45"
									height="44">
							</td>
							<td>
								<a href="/icbc/newperbank/regtip.jsp" target="_blank"><img
										src="/icbc/newperbank/images/but_on02.gif" alt="我要注册"
										title="我要注册"
										onMouseOver="src='/icbc/newperbank/images/but_on02-l.gif'"
										onMouseOut="src='/icbc/newperbank/images/but_on02.gif'"
										name="Image44" height="44" border="0"> </a>
							</td>
							<td>
								<img src="/icbc/newperbank/images/login_09.gif" width="45"
									height="44">
							</td>
							<td>
								<img src="/icbc/newperbank/images/but_on03.gif" alt="开始使用"
									title="开始使用"
									onMouseOver="src='/icbc/newperbank/images/but_on03-l.gif'"
									onMouseOut="src='/icbc/newperbank/images/but_on03.gif'"
									name="Image45" width="78" height="44" border="0">
							</td>
							<td width="30">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td bgcolor="ffffff"></td>
							<td colspan="3">
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
						</tr>
					</table>
					<table width="571" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="121">
								<img src="/icbc/newperbank/images/login_lab1.gif"
									name="login_lab1" width="157" height="24" border="0"
									id="login_lab1">
							</td>
						</tr>
					</table>
					<table width="571" height="300" border="0" align="center"
						cellpadding="0" cellspacing="0"
						style="background: url('/icbc/newperbank/images/bgfirst.gif') no-repeat;"#FFFFFF" >
						<tr>
							<td colspan="3" align="center" valign="top">
								<table width="95%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="1">
											&nbsp;
										</td>
										<td width="550" valign="top">
											<table width="100%" height="5" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td></td>
												</tr>
											</table>
											<table width="100%" align="center">
												<tr>
													<td height="253">
														<form name="logonform" method="post" action=""
															onSubmit="return false;">
															<input type="hidden" name="custMainPage" value="">
															<input type="hidden" name="injectTranName" value="">
															<input type="hidden" name="injectTranData" value="">
															<input type="hidden" name="injectSignStr" value="">
															<input type="hidden" name="ComputID" value="10">
															<input type="hidden" name="PlatFlag" value="0">
															<input type="hidden" name="IconInfo" value="">
															<input type="hidden" name="SnapInfo" value="">
															<input type="hidden" name="LogonTime" value="">
															<input type="hidden" name="cookieFlag" value="">
															<table width="99%" border="0" cellspacing="0"
																cellpadding="0" align="center">
																<tr>
																	<td rowspan="6" align=left valign="top">
																		<a
																			href="http://www.icbc.com.cn/icbc/html/wangyin/version/version.html"
																			title="" target="_blank"> <img
																				src="/icbc/newperbank/images/login_07.gif" border=0>
																		</a>
																		<br>
																		<font color="#888888"><nobr>
																				版本：9.7
																			</nobr> </font>
																	</td>
																	<td rowspan="6">
																		&nbsp;&nbsp;
																	</td>
																</tr>
																<tr>
																	<div id="divss" hidefocus="true" style="outline: none"></div>
																	<td height="30" align="right" width="25%" id="td1">
																		卡（账）号/用户名：
																	</td>
																	<td colspan="2">
																		<input id="logonCardNum" type="text"
																			name="logonCardNum" size="19" maxlength="19" value=""
																			onkeydown="if((pebankBrowserCompatible.isSafari()||pebankBrowserCompatible.isFirefox()||pebankBrowserCompatible.isChrome())&&pebankBrowserCompatible.getKeycode(event)==9)event.preventDefault();"
																			onKeyUp="getfocus1('safeEdit1',event)"
																			style="ime-mode: disabled">
																		<a href="/icbc/newperbank/logonIdsearch125.jsp?"
																			tabindex="10"><font color="blue">找回用户名</font> </a>
																	</td>
																</tr>
																<tr>
																	<td height="30" align="right">
																		登录密码：
																	</td>
																	<td>
																		<object id="safeEdit1"
																			codebase="/icbc/newperbank/AxSafeControls.cab#version=1,0,0,13"
																			classid="CLSID:73E4740C-08EB-4133-896B-8D0A7C9EE3CD"
																			width=145 height=21
																			onfocus="detectCapsLock('logonform','safeEdit1',500,300,400,'logontb')"
																			onblur="closeCapTip('logonform','safeEdit1')"
																			onKeyUp="getfocus1('KeyPart', event);detectCapsLock('logonform','safeEdit1',500,300,400,'logontb');">
																			<param name="name" value="logonCardPass">
																			<param name="minLength" value="4">
																			<param name="maxLength" value="30">
																			<param name="rule" value="10111">
																			<param name="UniqueID" value="135325067653021881">
																			<param name="IsPassword" value="true">
																		</object>
																		<input type="hidden" name="netType" value="130">
																	</td>
																	<td rowspan="4">
																		<div id="axsafetip" hidefocus="true"></div>
																	</td>
																</tr>
																<tr>
																	<td height="30" align="right">
																		验证码：
																	</td>
																	<td nowrap>
																		<object id="KeyPart"
																			codebase="/icbc/newperbank/AxSafeControls.cab#version=1,0,0,13"
																			classid="CLSID:73E4740C-08EB-4133-896B-8D0A7C9EE3CD"
																			width=40 height=28
																			onKeyUP="if(pebankBrowserCompatible.getKeycode(event)==13) mySubmit();">
																			<param name="name" value="verifyCode">
																			<param name="minLength" value="4">
																			<param name="maxLength" value="4">
																			<param name="rule" value="10111">
																			<param name="UniqueID" value="135325067653021881">
																			<param name="IsPassword" value="false">
																		</object>
																		<input type="hidden" name="randomId"
																			value="135325067653021881">
																		<IFRAME frameBorder="0" name="VerifyimageFrame"
																			scrolling="no"
																			src="/servlet/com.icbc.inbs.person.servlet.Verifyimage2?randomKey=135325067653021881&imageAlt=点击图片可刷新"
																			marginHeight=0 marginWidth=0 height=28 width=80
																			onload="this.contentWindow.document.oncontextmenu =  function(){return false;};"></IFRAME>
																		&nbsp;
																		<a id="link4Verifyimage2Name"
																			href="JavaScript:refreshVerifyimage();"
																			style="COLOR: blue; text-decoration: underline;"><nobr>
																				刷新验证码
																			</nobr> </a>
																		<script language="JavaScript">if(navigator.userAgent.toLowerCase().indexOf("chrome/")!=-1){//这里注意chrome的useragent也包含safari	VerifyimageFrame.location.href="/servlet/com.icbc.inbs.person.servlet.Verifyimage2?randomKey=135325067653021881&imageAlt=点击图片可刷新"+"&appendRandom="+(new Date()).getTime();}else if(navigator.userAgent.toLowerCase().indexOf("safari")!=-1|| navigator.userAgent.toLowerCase().indexOf("firefox")!=-1){	VerifyimageFrame.location.href="/servlet/com.icbc.inbs.person.servlet.Verifyimage2?randomKey=135325067653021881&imageAlt=点击图片可刷新"+"&appendRandom="+(new Date()).getTime();}function refreshVerifyimage(){	if(navigator.userAgent.toLowerCase().indexOf("chrome/")!=-1){//这里注意chrome的useragent也包含safari		var url = "/servlet/com.icbc.inbs.person.servlet.Verifyimage2?randomKey=135325067653021881&imageAlt=点击图片可刷新"+"&appendRandom="+(new Date()).getTime();		VerifyimageFrame.location.replace(url);	}else if(navigator.userAgent.toLowerCase().indexOf("safari")!=-1){//safari回退时，验证码不刷新问题		VerifyimageFrame.location.href="/servlet/com.icbc.inbs.person.servlet.Verifyimage2?randomKey=135325067653021881&imageAlt=点击图片可刷新"+"&appendRandom="+(new Date()).getTime();	}else{		VerifyimageFrame.location.reload();	}}/*if(pebankBrowserCompatible.isSafari()||pebankBrowserCompatible.isChrome()){	//屏蔽回退按钮	challengeFrame.history.go=function(){		pebankBrowserCompatible.alertHistoryTip();	}	challengeFrame.history.back=function(){		pebankBrowserCompatible.alertHistoryTip();	}}*/</script>
																		&nbsp;
																	</td>
																</tr>
																<tr>
																	<td colspan="2"></td>
																</tr>
																<tr id="stySel"></tr>
															</table>
															<table width="99%" border="0" cellspacing="0"
																cellpadding="0" align="center">
																<tr>
																	<td height="10"></td>
																</tr>
																<tr>
																	<td height="68" colspan="4">
																		&nbsp;&nbsp;&nbsp;&nbsp;您使用网上银行，须阅读并遵守
																		<a
																			href="http://www.icbc.com.cn/icbc/html/guanggao/041208/mdltkzc_041208.htm"><font
																			color=#BC0021>《中国工商银行电子银行章程》</font> </a>、
																		<a
																			href="http://www.icbc.com.cn/icbc/html/download/wyfwxy/wsyh_khfwxy.htm"><font
																			color=#BC0021>《中国工商银行电子银行个人客户服务协议》</font> </a>和
																		<a
																			href="http://www.icbc.com.cn/icbc/html/download/wyfwxy/wsyh_jygz.htm"><font
																			color=#BC0021>《中国工商银行个人网上银行交易规则》</font> </a>，如您使用贵宾版个人网上银行还须阅读并遵守
																		</font></a><a
																			href=" http://www.icbc.com.cn/ICBC/html/download/wyfwxy/fwxz_gbwy.htm"><font
																			color=#BC0021>《中国工商银行贵宾版个人网上银行服务须知》</font> </a>。
																	</td>
																</tr>
																<tr>
																	<td height="20" colspan="4">
																		<div align="center">
																			<span id="submitkey"><a
																				href="javascript:mySubmit();" id="submitaid"><img
																						src="/icbc/newperbank/images/denglu.gif"
																						border="0"> </a> </span>
																		</div>
																	</td>
																</tr>
															</table>
														</form>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="570" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="80">
								<img src="/icbc/newperbank/images/loginn_33-16.gif" width="80"
									height="27">
							</td>
							<td width="490" nowrap>
								如果您是第一次在本计算机上使用网上银行，请使用
								<a href="http://www.icbc.com.cn/ICBC/网银系统/anzhuang.htm"
									target="_blank" class="link_red">网银助手</a>进行必要的安装设置。
							</td>
						</tr>
					</table>
					<table width="527" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr height="20">
							<td>
								&nbsp;
							</td>
						</tr>
					</table>
					<table width="570" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr height="25">
							<td colspan="3">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="478" height="30">
											<img src="/icbc/newperbank/images/loginn_36-17.gif"
												width="80" height="21">
										</td>
										<td width="92" valign="bottom">
											<font color="red"><a href="javascript:winopenrisk()"
												class="link_red">更多 &gt;&gt;</a> </font>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<img src="/icbc/newperbank/images/loginn_37-18.gif"
												width="409" height="1">
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td width="10" height="25"></td>
							<td width="13" height="25" class="font_stred">
								<img src="/icbc/newperbank/images/loginn_41-19.gif" width="7"
									height="7">
							</td>
							<td width="490" nowrap>
								您每次使用网上银行后，请点击页面右上角的“安全退出”结束使用，并拔出U盾妥善保管。
							</td>
						</tr>
						<tr>
							<td width="10" height="25"></td>
							<td width="13" height="25" class="font_stred">
								<img src="/icbc/newperbank/images/loginn_41-19.gif" width="7"
									height="7">
							</td>
							<td width="490" nowrap>
								请您网上购物进行支付时，不要开启操作系统、MSN和QQ等工具的远程协助功能，一定要在核对支付金额和订单金额无误后再确认支付。
							</td>
						</tr>
						<tr>
							<td width="10" height="25"></td>
							<td width="13" height="25" class="font_stred">
								<img src="/icbc/newperbank/images/loginn_41-19.gif" width="7"
									height="7">
							</td>
							<td width="490" nowrap>
								请您在登录网上银行和进行网上支付时，要注意防范假冒工行网站的欺诈。为帮助您识别假网站，我行为您提供了防钓鱼网站安全控件，请及时下载安装。
							</td>
						</tr>
					</table>
					<table width="527" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr height="20">
							<td>
								&nbsp;
							</td>
						</tr>
					</table>
					<table width="570" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr height="25">
							<td height="30" colspan="3">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="478" height="30">
											<img src="/icbc/newperbank/images/loginn_44-20.gif"
												width="99" height="20">
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<img src="/icbc/newperbank/images/loginn_37-18.gif"
												width="409" height="1">
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td width="10" height="25"></td>
							<td width="13" height="25" class="font_stred">
								<img src="/icbc/newperbank/images/loginn_41-19.gif" width="7"
									height="7">
							</td>
							<td width="490" nowrap>
								<a
									href="http://www.icbc.com.cn/icbc/html/download/dkq/icbc_netbank_client_controls.exe"
									class="link_black" target="_blank">如您使用过程中遇到控件下载问题，请安装网银控件程序。</a>
							</td>
						</tr>
						<tr>
							<td width="10" height="25"></td>
							<td width="13" height="25" class="font_stred">
								<img src="/icbc/newperbank/images/loginn_41-19.gif" width="7"
									height="7">
							</td>
							<td width="490" nowrap>
								<a href="http://www.icbc.com.cn/icbc/网银系统/alert_1.htm"
									class="link_black" target="_blank">如果您是首次使用本机登录网上银行请先阅读系统设置指南。</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			</tbody>
		</table>
		<table width="500" height="5" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr>
				<td></td>
			</tr>
		</table>
		<!--bottom begin-->
		<TABLE cellSpacing=0 cellPadding=0 width=802 align=center border=0>
			<TBODY>
				<TR>
					<TD vAlign=top align=left width=5 height=5>
						<IMG height=7 src="/icbc/newperbank/images/but_left_top.gif"
							width=8>
					</TD>
					<TD background=/icbc/newperbank/images/but_top.gif height=5></TD>
					<TD width=5 height=5>
						<IMG height=7 src="/icbc/newperbank/images/but_right_top.gif"
							width=8>
					</TD>
				</TR>
				<TR>
					<TD background=/icbc/newperbank/images/but_left.gif></TD>
					<TD background=/icbc/newperbank/images/but_bg.gif>
						<TABLE height=35 cellSpacing=0 cellPadding=0 width=760
							align=center border=0>
							<TBODY>
								<TR>
									<TD align=middle nowrap>
										<A class=text2b
											href="http://www.icbc.com.cn/icbc/%e5%ae%a2%e6%88%b7%e6%9c%8d%e5%8a%a1/map.htm"
											target=_blank>网站地图</A> | &nbsp;
									<TD align=middle nowrap>
										<A class=text2b
											href="http://www.icbc.com.cn/icbc/%e5%ae%a2%e6%88%b7%e6%9c%8d%e5%8a%a1/service.htm"
											target=_blank>联系我们</A> | &nbsp;
									<TD align=middle nowrap>
										<A class=text2b
											href="http://www.icbc.com.cn/icbcltd/%e7%bd%91%e7%ab%99%e5%a3%b0%e6%98%8e/%e7%bd%91%e7%ab%99%e5%a3%b0%e6%98%8e.htm"
											target=_blank>网站声明</A> | &nbsp;
									<TD align=middle nowrap>
										<A class=text2b
											href="http://www.icbc.com.cn/icbc/%e5%ae%a2%e6%88%b7%e6%9c%8d%e5%8a%a1/business_offices.htm"
											target=_blank>服务网点</A> | &nbsp; 服务热线
										<span><font color="#BA0403">95588</font> </span>
									</TD>
									<TD vAlign=top align=middle width=445>
										<DIV align=right>
											中国工商银行版权所有 京ICP证 030247号&nbsp;&nbsp;&nbsp;
											<A
												href="http://www.hd315.gov.cn/beian/view.asp?bianhao=0102000120100015"
												target=_blank><IMG height=29
													src="/icbc/newperbank/images/icp.gif" width=25
													align=absMiddle border=0> </A>
										</DIV>
									</TD>
									<TD vAlign=top align=middle width=10>
										&nbsp;
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD background=/icbc/newperbank/images/but_right.gif>
						&nbsp;
					</TD>
				</TR>
				<TR>
					<TD vAlign=bottom align=left width=8 height=9>
						<IMG height=9 src="/icbc/newperbank/images/but_left_buttom.gif"
							width=8>
					</TD>
					<TD background=/icbc/newperbank/images/but_buttom.gif height=9>
						<DIV align=left></DIV>
					</TD>
					<TD vAlign=bottom align=left height=9>
						<IMG height=9 src="/icbc/newperbank/images/but_right_buttom.gif"
							width=8>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<!--bottom -->
		<form name="Logonform" method="post"
			action="/servlet/com.icbc.inbs.servlet.ICBCINBSEstablishSessionServlet"
			onSubmit="return false;">
			<input name="netType" value="130" type=hidden>
			<input name="Language" value="zh_CN" type=hidden>
		</form>
	</BODY>
</HTML>
<script language="javascript">try{				if(pebankBrowserCompatible.isIE()){			document.all.safeSubmit1.reset();		}}catch(Exception){	dealException();}function dealException(){				if(!detectAssistComm()){		//没有安装助手弹出新提示，点击后下载		document.getElementById("axsafetip").innerHTML = "<table bgcolor=\"#ffffcc\" cellspacing=\"0\" cellpadding=\"0\"><td><font color=\"#FF6600\">您有非正常运行的网银安全工具，建议您安装<a href='javascript:downloadAss();'><u><font color='blue'>网银助手</font></u></a>解决该问题。</font></td></tr></table>"		}else{		//已经安装助手提示信息不变，自动启动助手		document.getElementById("axsafetip").innerHTML = "<table bgcolor=\"#ffffcc\" cellspacing=\"0\" cellpadding=\"0\"><td><font color=\"#FF6600\">如果无法输入密码，请根据浏览器弹出提示进行控件安装。</font></td></tr></table>"		try{RunAssistComm();}catch(exception){}			}		}function downloadAss(){	window.open('http://www.icbc.com.cn/icbc/html/download/EbankToolsSoftware/ICBCSetupIntegration.msi');}function hasInstallNetSign12(){	var CLSID=""; 	var pluginDiv = document.createElement("div");	pluginDiv.setAttribute("style","display:none");	document.body.insertBefore(pluginDiv,null); 	pluginDiv.innerHTML = '<object id="objectForDetectPlugin" classid="CLSID:'+ '62B938C4-4190-4F37-8CF0-A92B0A91CC77' +'"></object>'; 	try 	{ 		if(eval("objectForDetectPlugin." + "base64Out") == undefined) 		{ 			pluginDiv.parentNode.removeChild(pluginDiv)//删除pluginDiv及其所有的子元素 		} 		else 		{				pluginDiv.innerHTML = '<object id="objectForDetectPlugin" classid="CLSID:'+ '62B938C4-4190-4F37-8CF0-A92B0A91CC77' +'" codebase="/icbc/NetSign.dll#version=1,2,2,9"></object>'; 							} 	} 	catch(e) 	{ 	} }jQuery(document).ready(function(){	var allowDesktop = '1';	var handle = null;	browserflag = checkUser();					jQuery("#stySel").html("<td id=\"styleselText\" height=\"30\" align=\"right\" >风格选择：		</td>"+			"<td id=\"stylesel\" nowrap>"+							   "<input class=\"sel\"  id=\"sel0\" TYPE=\"radio\" NAME=\"isDesktop\"  value=\"0\"  checked>"+			   "<IMG  id=\"large_0\" style=\"display:none\" src=\"/icbc/newperbank/desktop/style1/images/netbank.png\" border=\"0\" >"+			   "<IMG id=\"small_0\" src=\"/icbc/newperbank/desktop/style1/images/netbank_small.png\" border=\"0\" align=\"absmiddle\">"+			   "&nbsp;&nbsp;<input class=\"sel\"  id=\"sel1\" TYPE=\"radio\" value=\"1\" NAME=\"isDesktop\" >"+			   "<IMG  id=\"large_1\" style=\"display:none\" src=\"/icbc/newperbank/desktop/style1/images/desktop.png\" border=\"0\" >"+			   "<IMG id=\"small_1\" src=\"/icbc/newperbank/desktop/style1/images/desktop_small.png\" border=\"0\" align=\"absmiddle\">"+			"</td>");					if(""==allowDesktop||"0"==allowDesktop){			browserflag = true;			jQuery("#stySel").html("");		}		if(!browserflag){			jQuery("#sel0").attr("checked","true");			desktop = false;		}							jQuery(".sel").click(function(event){				if((jQuery("input:checked").attr("value"))=='1'){							desktop = true;				}				else{					desktop = false;				}		});						if(browserflag){		if("0"==getcookie()){		jQuery("#sel0").attr("checked","true");		desktop = false;	}	else{		jQuery("#sel1").attr("checked","true");		desktop = true;		}				}		jQuery("#small_0").mouseover(		function (){			handle = setTimeout(			function(){				jQuery("#large_0").css({"z-index":"20","position":"absolute"});						jQuery("#large_0").fadeIn();				jQuery("#large_0").css({"z-index":"20","position":"absolute","display":""});					}			,1000);			}	)		jQuery("#small_0").mouseout(		function(){			clearTimeout(handle);												}	)		jQuery("#large_0").mouseout(		function(){			clearTimeout(handle);			jQuery("#large_0").fadeOut();									}	)		jQuery("#large_0").click(		function(){						jQuery("#sel0").attr("checked","true");						desktop = false;			}	)		jQuery("#small_0").click(		function(){						jQuery("#sel0").attr("checked","true");			desktop = false;									}	)		jQuery("#small_1").mouseover(		function(){			handle = setTimeout(			function(){				jQuery("#large_1").css({"z-index":"20","position":"absolute"});							jQuery("#large_1").fadeIn();				jQuery("#large_1").css({"z-index":"20","position":"absolute","display":""});					}			,1000);					}	)		jQuery("#small_1").mouseout(		function(){			clearTimeout(handle);												}	)		jQuery("#large_1").mouseout(		function(){						jQuery("#large_1").fadeOut();						}	)		jQuery("#large_1").click(		function(){						jQuery("#sel1").attr("checked","true");						desktop = true;			}	)		jQuery("#small_1").click(		function(){						jQuery("#sel1").attr("checked","true");						desktop = true;			}	)		});function getcookie(){				var cookieStr = document.cookie;								if(cookieStr==""){					//alert("no cookie found cookie str null");					document.iconInfo.operType.value = '2';					return 1;					}								var cookieValue = cookieStr.split("; ");				//alert(cookieValue);				var varName = "LogonStyleSelected=";								var startPos = -1;				var endPos = -1;				//alert(varName);				for(var i=0;i<cookieValue.length;i++){					startPos = cookieValue[i].indexOf(varName);					//alert(startPos);					if(startPos!=0){						continue;						}					startPos += varName.length;					endPos = cookieValue[i].length;					var cookieText = unescape(cookieValue[i].substring(startPos,endPos));										//alert(logonTime);															return cookieText;					}						}                                                                                                                                                                                                function checkUser()                                                                          {                                                                                              		var ver;//浏览器版本                                                                           		var bType;//浏览器类型                                                                         		var vNumber;//版本号                                                                           		ver = navigator.appVersion;                                                                    		bType =navigator.appName;    		if(macflag){			return true;			}		                                                                  		if(bType=="Microsoft Internet Explorer")                                                       		{                                                                                              			vNumber=parseFloat(ver.substring(ver.indexOf("MSIE")+5,ver.lastIndexOf("Windows")));          					if (vNumber < 7.0){                                                                             			                                                 							return false;			}else{				return true;			}		}                                                                                              		else                                                                                           		{                                                                                              		   var PlatFlag_ebiz = '0';		   if('0'!=PlatFlag_ebiz){		   	return true;         		   }else{		   	return false;		   	}                                     						}                                                                                                                                                                                                                                                                                       }var input_flag = "";var showFlag = "false";if(pebankBrowserCompatible.isFirefox()){	input_flag = typeof(navigator.mimeTypes['application/x-icbcnpxxin-plugin-input']);	showFlag = "true"}else if(pebankBrowserCompatible.isChrome()){	input_flag = typeof(navigator.mimeTypes['application/x-icbc-plugin-chrome-npxxin-input']);	showFlag = "true"}if(showFlag=="true"){if (input_flag=="undefined") {			var o1 = document.getElementById('safeSubmit1div');  			var o2 = document.getElementById('safeEdit1div');			var o3 = document.getElementById('KeyPartdiv');  o1.style.display = "none";  o2.style.display = "none";			o3.style.display = "none";}else{			var o1 = document.getElementById('safeSubmit1div');			var o2 = document.getElementById('safeEdit1div');			var o3 = document.getElementById('KeyPartdiv');  o1.style.display = "block";  o2.style.display = "block";			o3.style.display = "block";} }       </script>