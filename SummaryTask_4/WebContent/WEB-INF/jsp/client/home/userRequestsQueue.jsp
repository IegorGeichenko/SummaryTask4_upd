<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ page pageEncoding="utf-8" %>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE HTML>
<html>
<head>
<title>MU <fmt:message key="hotel"/>/<fmt:message key="my.requests"/></title>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>

	<!--start main -->
	<div class="main_bg">
		<div class="wrap">
			<div class="main">
				<div class="room">
					<p
						style="font-size: 25px; padding: 30px; text-indent: 10px; text-align: center; color: blue; font-family: arial, helvetica, sans-serif"><fmt:message key="payment.booked.room"/></p>
					<table border="5" align="center" cellpadding="10"
						style="border-style: ridge double">
						<tr>
							<th><fmt:message key="room.category"/></th>
							<th><fmt:message key="rooms.amount"/></th>
							<th><fmt:message key="date.check-.in"/></th>
							<th><fmt:message key="date.check-.out"/></th>
							<th><fmt:message key="bill"/></th>
							<th><fmt:message key="payment"/></th>
						</tr>
						<c:forEach var="bookingRequestsQueue" items="${bookingRequestsQueue}">
							<tr align="center">
								<td>${bookingRequestsQueue.category}</td>
								<td>${bookingRequestsQueue.roomsAmount} </td>
								<td>${bookingRequestsQueue.dateArrival} </td>
								<td>${bookingRequestsQueue.dateCheckOut} </td>
								<td>${bookingRequestsQueue.bill} </td>
								<td><a href="${pageContext.request.contextPath}/confirmPayment?bookingRequestId=${bookingRequestsQueue.id}&roomId=${bookingRequestsQueue.roomId}"><fmt:message key="pay"/></a></td>
							</tr>
						</c:forEach>
					</table>
					<a style="display: block; background-color: #32A2E3; -webkit-background-size: 70px 25px !important; color: white; margin-top: 50px; margin-right: 510px; margin-left: 510px;"
								href="${pageContext.request.contextPath}/home" align="center">
								<fmt:message key="home"/></a>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>