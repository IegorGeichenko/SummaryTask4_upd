<%@ page pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE HTML>
<html>
<head>
<title>MU <fmt:message key="hotel"/>/<fmt:message key="error"/></title>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="main_bg">
		<div class="wrap">
			<div class="main">
				<form action="#" name="Error_page-form">
					<p align="left">
					<div>
						<p style="font-size: 25px; text-align: center; padding: 30px; text-indent: 10px; color: blue; font-family: arial, helvetica, sans-serif">
							<fmt:message key="error.page"/></p>
						<p>
						  <img style="-webkit-filter: blur(2px); margin-bottom: 85px" align="right" alt="ERROR" src="${pageContext.request.contextPath}/images/error.jpg">
							<label style="color: red;" for="error"><fmt:message key="${error}"/></label>
							<p style=" margin-top: 70px">
							<a href="${header.referer}"><fmt:message key="back"/></a> 
					</div>
				</form>
			</div>
			<div class="clear"></div>
			</div>
			</div>
			<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>