<%@ page pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE HTML>
<html>
<head>
<title>MU <fmt:message key="hotel"/>/<fmt:message key="reservation"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<script src="js/jquery.min.js"></script>
<!---strat-date-piker---->
<link rel="stylesheet" href="css/jquery-ui.css" />
<script src="js/jquery-ui.js"></script>
		  <script>
				  $(function() {
				    $( "#datepicker,#datepicker1" ).datepicker({
				    	 dateFormat: "dd/mm/yy"
				    });
				  });
		  </script>
<!---/End-date-piker---->
<link type="text/css" rel="stylesheet" href="css/JFGrid.css" />
<link type="text/css" rel="stylesheet" href="css/JFFormStyle-1.css" />
		<script type="text/javascript" src="js/JFCore.js"></script>
		<script type="text/javascript" src="js/JFForms.js"></script>
		<!-- Set here the key for your domain in order to hide the watermark on the web server -->
		<script type="text/javascript">
			(function() {
				JC.init({
					domainKey: ''
				});
				})();
		</script>
<!--nav-->
<script>
		$(function() {
			var pull 		= $('#pull');
				menu 		= $('nav ul');
				menuHeight	= menu.height();

			$(pull).on('click', function(e) {
				e.preventDefault();
				menu.slideToggle();
			});

			$(window).resize(function(){
        		var w = $(window).width();
        		if(w > 320 && menu.is(':hidden')) {
        			menu.removeAttr('style');
        		}
    		});
		});
</script>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<!--start main -->
<div class="main_bg">
<div class="wrap">
	<div class="main">
		<div class="res_online">
			<p style="font-size: 25px; padding-top:30px; padding-bottom: 0px; text-indent: 10px; text-align:center; color: blue; font-family: arial, helvetica, sans-serif"><fmt:message key="free.room.reservation"/></p>
			<p class="para"></p>
		</div>	
		<form action="/SummaryTask4/reservation" method="post">		
			<div class="span_of_2">
				<div class="span2_of_1">
					<h4><fmt:message key="check-.in"/>:</h4>
					<div class="book_date btm">
						<input class="date" id="datepicker" type="text" name="checkIn" value="DD/MM/YY" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'DD/MM/YY';}">
					</div>	
				</div>
				<div class="span2_of_1">
					<h4><fmt:message key="check-.out"/>:</h4>
					<div class="book_date btm">
							<input class="date" id="datepicker1" type="text" name="checkOut" value="DD/MM/YY" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'DD/MM/YY';}">
					</div>	
				</div>
				<div class="clear"></div>
			</div>
			<div class="res_btn">
			    <input type="hidden" name="roomId" value="${roomId}"/>
				<input type="submit" value="<fmt:message key="submit"/>" style="width: 280px;">
			</div>
			</form>
	</div>
</div>
</div>		
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
		
</body>
</html>