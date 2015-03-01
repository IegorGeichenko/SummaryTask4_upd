<%@ page pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE HTML>
<html>
<head>
<title>MU <fmt:message key="hotel"/>/<fmt:message key="home"/></title>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="main_bg">
		<div class="wrap">
			<div class="main">
		
				<form method="post" action="${pageContext.request.contextPath}/home" name="home">
				<p style="font-size: 25px; text-align:center; padding: 10px; text-indent: 10px; color: blue; font-family: arial, helvetica, sans-serif">
							<fmt:message key="user.home.page"/></p>
				<img  width="200" height="200" style="-webkit-filter: blur(1px);margin-bottom: 100px; vertical-align: top" align="middle" alt="ERROR" src="${pageContext.request.contextPath}/images/home.png"/>
					<div>
						<label style="margin-left:20px;"  for="greeting"><fmt:message key="welcome"/>, ${user.firstName}</label>
						<p align="right" style="margin-top:-330px; margin-bottom: 10px">
						<a style="font-weight: bold; margin-top: -60px;" href="${pageContext.request.contextPath}/userOrders?user_id=${user.id}"><fmt:message key="my.booking.history"/></a>
						<p align="right" style="margin-bottom: 10px; font-weight: bold;">
						<a style="margin-top: -60px;" href="${pageContext.request.contextPath}/userRequests?user_id=${user.id}"><fmt:message key="my.requests"/></a></p>					
						<p align="right" style="font-weight: bold;">
						<a style="margin-top: -60px;"  href="${pageContext.request.contextPath}/userRequestsQueue?user_id=${user.id}"><fmt:message key="request.waiting.payment"/></a>
						</p>
					</div>
				</form>
			</div>
		</div>
	</div>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>	
</body>
</html>