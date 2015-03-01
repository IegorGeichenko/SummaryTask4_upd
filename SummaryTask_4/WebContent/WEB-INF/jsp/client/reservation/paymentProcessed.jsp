<%@ page pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${currentLocale}" />
<fmt:setBundle basename="resources" />
<!DOCTYPE HTML>
<html>
<head>
<title>MU <fmt:message key="hotel"/>/<fmt:message key="payment.successful"/></title>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="main_bg">
		<div class="wrap">
			<div class="main">
				<div class="room">
					<div>
						<p
							style="font-size: 25px; padding: 30px; text-indent: 10px; margin-bottom: 40px; text-align: center; color: blue; font-family: arial, helvetica, sans-serif">
							<fmt:message key="payment.successful"/></p>
						<p><fmt:message key="information.of.your.reservations.you.can.see"/></p>
						<p style="margin-bottom: 40px;"><fmt:message key="on.home.page.in.'My.booking.history'"/>.</p>

						<a
							style="display: block; background-color: #32A2E3; -webkit-background-size: 70px 25px !important; width:175px; color: white; margin-right: 410px; margin-bottom:25px; margin-left: 410px;"
							href="${pageContext.request.contextPath}/home"
							align="center"><fmt:message key="home"/></a>
						
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
</body>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>

</html>