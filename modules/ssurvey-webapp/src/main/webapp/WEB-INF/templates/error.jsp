<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>Error Page</title>
<link rel=icon href=/static/img/favicon.ico type="image/png">
<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="/static/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="/static/css/bootstrap-social.css" />
<link rel="stylesheet" type="text/css" href="/static/css/login.css" />
<link rel="stylesheet" type="text/css" href="/static/css/main.css" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
</head>
<body>
	<div class="container container-main">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title error-title">
					<i class="fa fa-files-o"></i> An error has occurred
				</h3>
			</div>
			<div class="panel-body">
				<h4>${errorMessage}</h4>
			</div>
		</div>
	</div>
</body>
</html>