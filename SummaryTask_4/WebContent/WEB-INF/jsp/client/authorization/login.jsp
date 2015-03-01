<%@ page pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE HTML>
<html>
<head>
<title>MU <fmt:message key="hotel"/>/<fmt:message key="login"/></title>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="main_bg">
		<div class="wrap">
			<div class="main">
				<form method="post" action="${pageContext.request.contextPath}/login"
					name="authoriazation-form" class="authoriazation-form">
							<p style="font-size: 25px; padding: 30px; text-indent: 10px; color: blue; font-family: arial, helvetica, sans-serif"><fmt:message key="authorization"/></p>
						<div>
							<label for="login"><fmt:message key="login"/>:</label> <input type="text" name="login" />
						</div>
						<div>
							<label for="password"><fmt:message key="password"/>:</label> <input type="password"
								name="password" />
						</div>
						<div class="res_btn" style="margin-top: 10px;">
							<input type="submit" value="<fmt:message key="submit"/>" style="width: 200px !important; margin-left: 100px;"/> 
							<a href="registration" style="margin-left:80px;"><fmt:message key="registration"/></a>
						</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>