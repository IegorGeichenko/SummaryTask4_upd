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
					<p
						style="font-size: 25px; padding: 30px; text-indent: 10px; text-align: center; color: blue; font-family: arial, helvetica, sans-serif">
						<fmt:message key="available.following.hotel.rooms" />
					</p>
					<form action="${pageContext.request.contextPath}/sortedRooms">
						<p>
							<select name="sortBy" style="width: 20%; ">
								<option selected value="1"><fmt:message key="sort.by" /></option>
								<option value="2"><fmt:message key="by.price"/></option>
								<option value="3"><fmt:message key="by.rooms.amount"/></option>
								<option value="4"><fmt:message key="by.category"/></option>
								<option value="5"><fmt:message key="by.room.number"/></option>
							</select> 
							<p><input type="submit"  style="display: block; background-color: #32A2E3; -webkit-background-size: 70px 25px; margin-left: 137px; color: white;" value="<fmt:message key="sort"/>"/>
						</p>
					</form>
					<table border="5" align="center" cellpadding="10"
						style="border-style: ridge double; margin-left:270px;  margin-top:-63px; border-color: blue;">
						<tr>
							<th><fmt:message key="room.number" /></th>
							<th><fmt:message key="room.category" /></th>
							<th><fmt:message key="rooms.amount" /></th>
							<th><fmt:message key="price.per.day" /></th>
							<th><fmt:message key="book.now" /></th>
						</tr>
						<c:choose>
				        <c:when test="${freeRooms != null }">
						<c:forEach var="freeRooms" items="${freeRooms}">
							<tr align="center">
								<td>${freeRooms.number}</td>
								<td>${freeRooms.categoryName}</td>
								<td>${freeRooms.placeAmount}</td>
								<td>${freeRooms.price} $</td>
								<td><a style="font-size: small;"
									href="${pageContext.request.contextPath}/reservation?room_id=${freeRooms.id}"><fmt:message
											key="book.now" /> </a></td>
							</tr>
						</c:forEach>
						</c:when>
						 <c:when test="${sortedFreeRooms != null }">
						<c:forEach var="sortedFreeRooms" items="${sortedFreeRooms}">
							<tr align="center">
								<td>${sortedFreeRooms.number}</td>
								<td>${sortedFreeRooms.categoryName}</td>
								<td>${sortedFreeRooms.placeAmount}</td>
								<td>${sortedFreeRooms.price} $</td>
								<td><a style="font-size: small;"
									href="${pageContext.request.contextPath}/reservation?room_id=${sortedFreeRooms.id}"><fmt:message
											key="book.now" /> </a></td>
							</tr>
						</c:forEach>
						</c:when>
						</c:choose>
					</table>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>