<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script src="${ctx}/scripts/boot.js" type="text/javascript"></script>
	</head>
	 
	<script type="text/javascript">
	 
	  function openFullScreen (url) {
		    var name = arguments[1] ? arguments[1] : "_blank";
		    var feature = "fullscreen=yes,channelmode=yes, titlebar=no, toolbar=no, scrollbars=no," +
		         "resizable=yes, status=no, copyhistory=no, location=no, menubar=no,width="+(screen.availWidth) +
		         "height="+(screen.availHeight);
		    var newWin = window.open(url, name, feature);
		}

		openFullScreen('${ctx}')
	
	</script>
</html>
