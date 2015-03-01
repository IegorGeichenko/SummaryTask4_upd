<%@ page pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE HTML>
<html>
<head>
<title>MU <fmt:message key="hotel"/>/<fmt:message key="room.find"/></title>
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
						style="font-size: 25px; padding: 30px; text-indent: 10px; text-align: center; color: blue; font-family: arial, helvetica, sans-serif"><fmt:message key="suitable.rooms.by.request"/></p>
					<table border="5" align="center" cellpadding="10"
						style="border-style: ridge double">
						<tr>
							<th><fmt:message key="room.number"/></th>
							<th><fmt:message key="room.category"/></th>
							<th><fmt:message key="rooms.amount"/></th>
							<th><fmt:message key="price.per.day"/></th>
							<th><fmt:message key="send.to.user"/></th>
						</tr>
						<c:set var="bookingRequestId" value="${bookingRequestId}" scope="page">
						</c:set>
						<c:forEach var="suitableListRooms" items="${suitableListRooms}">
							<tr align="center">
								<td>${suitableListRooms.number}</td>
								<td>${suitableListRooms.categoryName}</td>
								<td>${suitableListRooms.placeAmount} </td>
								<td>${suitableListRooms.price} $</td>
								<td><a
									href="${pageContext.request.contextPath}/manager/bookingRequestUpdator?room_id=${suitableListRooms.id}&bookingRequestId=${bookingRequestId}"><fmt:message key="send"/></a></td>
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