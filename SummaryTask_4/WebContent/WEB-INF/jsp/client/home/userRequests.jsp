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
						style="font-size: 25px; padding: 30px; text-indent: 10px; text-align: center; color: blue; font-family: arial, helvetica, sans-serif"><fmt:message key="my.requests"/></p>
					<table border="5" align="center" cellpadding="10"
						style="border-style: ridge double">
						<tr>
							<th><fmt:message key="room.category"/></th>
							<th><fmt:message key="rooms.amount"/></th>
							<th><fmt:message key="date.check-.in"/></th>
							<th><fmt:message key="date.check-.out"/></th>
							<th><fmt:message key="book.room"/></th>
						</tr>
						<c:forEach var="bookingRequest" items="${bookingRequests}">
							<tr align="center">
								<td>${bookingRequest.category}</td>
								<td>${bookingRequest.roomsAmount} </td>
								<td>${bookingRequest.dateArrival} </td>
								<td>${bookingRequest.dateCheckOut} </td>
								<td><a href="${pageContext.request.contextPath}/confirmBookingRequest?bookingRequestId=${bookingRequest.id}&roomId=${bookingRequest.roomId}"><fmt:message key="confirm.booking"/></a></td>
							</tr>
						</c:forEach>
					</table>
					<a style="display: block; background-color: #32A2E3; -webkit-background-size: 70px 25px !important; color: white; margin-top: 50px; margin-right: 510px; margin-left: 510px;"
								href="${pageContext.request.contextPath}/home" align="center">
								<fmt:message key="back"/></a>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>