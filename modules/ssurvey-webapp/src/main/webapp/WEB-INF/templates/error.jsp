<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>Error</title>
<link rel="stylesheet" type="text/css"
	href="../static/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="../static/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="/static/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="../static/css/bootstrap-social.css" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
</head>
<body class="jumbotron">
	<div class="container">
		<h3>An error occurred:</h3>
		<h3>${errorMessage}</h3>
		<br> <br>
		<form action="/surveys/" method="GET">
			<button class="btn btn-primary">Go back to surveys</button>
		</form>

	</div>
</body>
</html>