<%@ page pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE HTML>
<html>
<head>
<title>MU <fmt:message key="hotel"/>/<fmt:message key="registration"/></title>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="main_bg">
		<div class="wrap">
			<div class="main">
				<form action="/SummaryTask4/registration" method="post"
					name="authoriazation-form" class="authoriazation-form">
					<p align="left">
					<p
						style="font-size: 25px; padding: 30px; text-indent: 10px; color: blue; font-family: arial, helvetica, sans-serif">
						<fmt:message key="registration"/></p>
					<div>
						<label for="first_name"> <fmt:message key="first.name"/>: </label> <input type="text"
							name="first_name" />
					</div>
					<div>
						<label for="last_name"> <fmt:message key="last.name"/>: </label> <input type="text"
							name="last_name" />
					</div>
					<div>
						<label for="login"> <fmt:message key="login"/>: </label> <input type="text"
							name="login" />
					</div>
					<div>
						<label for="password"> <fmt:message key="password"/>: </label> <input type="password"
							name="password" />
					</div>
					<div>
						<label  for="password_conf" style="width: 180px; margin-left: -90px;"> <fmt:message key="confirm.password"/>: </label> <input
							type="password" name="password_conf" />
					</div>
					<div class="res_btn" style="margin-top: 10px;">
						<input type="submit" value="<fmt:message key="submit"/>"
							style="width: 150px !important; margin-left: 80px;"> <input
							type="reset" value="<fmt:message key="clear.all"/>" style="width: 150px !important;">
					</div>
				</form>
				<p align="center">
				*<fmt:message key="after.proper.registration"/>
				</p>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>