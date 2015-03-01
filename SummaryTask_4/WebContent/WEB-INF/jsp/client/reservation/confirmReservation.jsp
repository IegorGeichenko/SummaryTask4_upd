<%@ page pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${currentLocale}" />
<fmt:setBundle basename="resources" />
<!DOCTYPE HTML>
<html>
<head>
<title>MU <fmt:message key="hotel"/>/<fmt:message key="confirmation.of.reservation"/></title>
<%@ page import="ua.nure.geichenko.SummaryTask4.model.entity.room.Room"%>
<%@ page import="ua.nure.geichenko.SummaryTask4.model.entity.order.Order"%>
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
							style="font-size: 25px; padding: 30px; text-indent: 10px; text-align: center; color: blue; font-family: arial, helvetica, sans-serif">
							<fmt:message key="confirmation.of.reservation"/></p>
						<p align="left"><fmt:message key="please,.confirm.reservation.of.selected.room"/>.</p>
						<p><fmt:message key="information.of.your.reservations.you.can.see"/></p>
						<p><fmt:message key="on.home.page.in.'My.booking.history'"/>.</p>
						<table align="center" border="5" align="left" cellpadding="10"
							style="border-style: ridge double; border-color: blue; margin-top: 35px; margin-bottom: 30px">
							<tr align="left">
								<th colspan="2" align="center"><fmt:message key="selected.room.info"/></th>
							</tr>
							<tr align="center">
								<th><fmt:message key="room.category"/></th>
								<th>${room.categoryName}</th>
								
							</tr>
							<tr align="center">
								<th><fmt:message key="rooms.amount"/></th>
								<th>${room.placeAmount}</th>
							</tr>
							<tr align="center">
								<th><fmt:message key="arrival"/></th>
								<th>${sessionScope.order.dateArrival}</th>
							</tr>
							<tr align="center">
								<th><fmt:message key="departure"/></th>
								<th>${sessionScope.order.dateCheckOut}</th>
							</tr>
							<tr align="center">
								<th><fmt:message key="days"/></th>
								<th>${sessionScope.fullDaysofBooking}</th>
							</tr>
							<tr align="center">
								<th><fmt:message key="price/day"/></th>
								<th>${room.price} $</th>
							<tr>
							<tr align="center">
								<th><fmt:message key="total.to.pay"/></th>
								<th>${order.bill} $</th>
							<tr>
						</table>

						<a
							style="display: block; background-color: #32A2E3; -webkit-background-size: 70px 25px !important; color: white; margin-right: 410px; margin-bottom:25px; margin-left: 410px;"
							href="${pageContext.request.contextPath}/successReservation"
							align="center"><fmt:message key="confirm"/> (<fmt:message key="pay"/> ${order.bill} $) </a>
						
							<a
								style="display: block; background-color: #32A2E3; -webkit-background-size: 70px 25px !important; color: white; margin-right: 410px; margin-left: 410px;"
								href="${pageContext.request.contextPath}/rooms" align="center">
								<fmt:message key="see.another.room"/></a>
					
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
</body>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>

</html>