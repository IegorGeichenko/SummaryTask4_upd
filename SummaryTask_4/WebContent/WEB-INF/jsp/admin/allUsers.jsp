<%@ page pageEncoding="utf-8"%>
<%@ page import="java.util.List"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${currentLocale}" />
<fmt:setBundle basename="resources" />

<!DOCTYPE HTML>
<html>
<head>
<title>MU <fmt:message key="hotel" />/<fmt:message
		key="free.rooms" /></title>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>

	<!--start main -->
	<div class="main_bg">
		<div class="wrap">
			<div class="main">
				<div class="room">
				
				<p style="font-size: 25px; padding: 30px; text-indent: 10px; margin-bottom: 40px; text-align: center; color: blue; font-family: arial, helvetica, sans-serif">
							<fmt:message key="user.orders.info"/></p>
					<table  border="5" align="center" cellpadding="10"
						style="border-style: ridge double; margin-left:300px;margin-top:-35px; border-color: blue;">
						<tr>
							<th><fmt:message key="user" /></th>
							<th><fmt:message key="orders.successful" /></th>
						</tr>
					
						<c:forEach var="quantity" items="${quantity}">
							<tr align="center">
								<td>${quantity.key}</td>
								<td>${quantity.value}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>