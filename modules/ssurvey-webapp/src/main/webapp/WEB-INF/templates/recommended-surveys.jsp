<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Recommended surveys</title>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/jquery.countdown.css">
	<script type="text/javascript" src="/static/js/CountDown/jquery.plugin.js"></script>
	<script type="text/javascript" src="/static/js/CountDown/jquery.countdown.js"></script>
	<script type="text/javascript" src="/static/js/jqueryUI/jquery-ui.js"></script>
	<link rel="stylesheet" type="text/css" href="/static/css/questions.css">
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<jsp:include page="/WEB-INF/templates/navbar.jsp" />
	<div class="container">
	<div class="col-md-6 col-md-offset-3">
		<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">
				<span class="glyphicon glyphicon-arrow-right"> </span>
				Recommended surveys
				<span class="glyphicon glyphicon-new-window"> </span>
			</h3>
		</div>
		<div class="panel-body">
			<ul class="list-group">
				<c:forEach var="survey" items="${surveys}">
					<li class="list-group-item"><a href="/surveys/${survey.id}">${survey.name}</a></li>
				</c:forEach>
			</ul>
		</div>
		</div>
	</div>
	</div>
</html>