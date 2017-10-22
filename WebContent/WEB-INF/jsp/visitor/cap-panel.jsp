<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<body>
		<object classid="clsid:34681DB3-58E6-4512-86F2-9477F1A9F3D8" id="CapCtrl" width="100%" height="100%" codebase="ImageCapOnWeb.cab#version=1,2,0,0" style="margin:0;padding:0;float:left;">
	      <param name="Visible" value="0">
	      <param name="AutoScroll" value="0">
	      <param name="AutoSize" value="0">
	      <param name="AxBorderStyle" value="1">
	      <param name="Caption" value="scaner">
	      <param name="Color" value="4278190095">
	      <param name="Font" value="宋体">
	      <param name="KeyPreview" value="0">
	      <param name="PixelsPerInch" value="96">
	      <param name="PrintScale" value="0.2">
	      <param name="Scaled" value="-1">
	      <param name="DropTarget" value="0">
	      <param name="HelpFile" value>
	      <param name="PopupMode" value="0">
	      <param name="ScreenSnap" value="0">
	      <param name="SnapBuffer" value="10">
	      <param name="DockSite" value="0">
	      <param name="DoubleBuffered" value="0">
	      <param name="ParentDoubleBuffered" value="0">
	      <param name="UseDockManager" value="0">
	      <param name="Enabled" value="-1">
	      <param name="AlignWithMargins" value="0">
	      <param name="ParentCustomHint" value="-1">
	      <param name="licenseMode" value="2">
	      <param name="key1" value="jpkQqZaD6QlBq6L7AIl1LA9MJ04Ds+N6Ft9b47KUxAZzgfPCATMIiQ==">
	      <param name="key2" value="UoteC+oam7pRXJD+LR6+PearD5PI+tnbTOPp1vs13dnBQrPkJFItST16wBOTEaiSWUwWZ1JnKUHcCkvM+Ie+CeRbZvvaT2ATi9yZ1Q==">
	    </object>
	</body>
	<script type="text/javascript">
	var capCtrl;
	try {
		//拍照控件初始化
		capCtrl = document.getElementById("CapCtrl");
		capCtrl.SwitchWatchOnly();
		capCtrl.start();
    }
    catch (e) {
        console.info(e);
    }
    function photograph(){
    	capCtrl.cap();
    	var baseStr64=capCtrl.jpegBase64Data;
    	if(baseStr64!=""){
    	   $("#photo1").val(baseStr64);
    	   $("#photo1-img").attr("src", "data:image/png;base64,"+baseStr64);
    	}
    	capCtrl.stop();
    }
	</script>
</html>
