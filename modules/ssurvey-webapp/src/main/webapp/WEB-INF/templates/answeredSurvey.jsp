<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel=icon href=/static/img/favicon.ico type="image/png">
<script src="/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="/static/js/jqueryUI/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="/static/css/questions.css">
<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="/static/css/main.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${survey.name}</title>
</head>
<body>
	<jsp:include page="/WEB-INF/templates/navbar.jsp" />
	<div class="container" style="margin-top:90px">
		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">${survey.name}</h3>
				</div>
				<div class="message">
					<p><br>
						Your answers have been sent, and they're being processed.<br>
						Thank you.<br>
						<br>
						<script src="//platform.linkedin.com/in.js" type="text/javascript"> lang: en_US</script>
						<script type="IN/Share" data-counter="right"></script>
						<br>
					</p>
				</div>
			</div>
		</div>
	</div>
</html>