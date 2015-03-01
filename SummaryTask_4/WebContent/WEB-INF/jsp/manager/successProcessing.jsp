<%@ page pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE HTML>
<html>
<head>
<title>MU <fmt:message key="hotel"/>/<fmt:message key="success.processing"/></title>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="main_bg">
		<div class="wrap">
			<div class="main">
				<div>
					<p style="font-size: 25px; padding: 30px; text-indent: 10px; color: blue; font-family: arial, helvetica, sans-serif">
						<fmt:message key="success.processing"/></p>
					<p style="margin-top:20px; ">
						<a href="${pageContext.request.contextPath}/manager/bookingRequestProcess"><fmt:message key="back.to.user.requests"/></a>
					</p>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>