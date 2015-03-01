<%@ page pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE HTML>
<html>
<head>
<title>MU <fmt:message key="hotel"/>/<fmt:message key="date.picker.default"/></title>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>

	<!--start main -->
	<div class="main_bg">
		<div class="wrap">
			<div class="main">
				<div class="room">
					<p style="font-size: 25px; padding: 30px; text-indent: 10px; text-align: center; color: blue; font-family: arial, helvetica, sans-serif"><fmt:message key="current.booking.requests"/></p>
					<table border="5" align="center" cellpadding="10" style="border-style: ridge double">
						<tr>
							<th><fmt:message key="user"/></th>
							<th><fmt:message key="room.category"/></th>
							<th><fmt:message key="rooms.amount"/></th>
							<th><fmt:message key="date.booking"/></th>
							<th><fmt:message key="date.check-.in"/></th>
							<th><fmt:message key="date.check-.out"/></th>
							<th><fmt:message key="find.suitable"/></th>
						</tr>
						<c:forEach var="bookingRequests" items="${bookingRequests}">
							<tr align="center">
								<td>id=${bookingRequests.userId}</td>
								<td>${bookingRequests.category}</td>
								<td>${bookingRequests.roomsAmount} </td>
								<td>${bookingRequests.bookingDate} </td>
								<td>${bookingRequests.dateArrival} </td>
								<td>${bookingRequests.dateCheckOut} </td>
								<td>
									<a href="${pageContext.request.contextPath}/manager/roomFinder?categoryId=${bookingRequests.category.value}&roomsAmount=${bookingRequests.roomsAmount}
									&dateCheckIn=${bookingRequests.dateArrival}&dateCheckOut=${bookingRequests.dateCheckOut}&bookingRequestId=${bookingRequests.id}"><fmt:message key="find.suitable"/></a>
								</td>
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