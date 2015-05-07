<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>Login</title>
<link rel=icon href=/static/img/favicon.ico type="image/png">
<link rel="stylesheet" type="text/css" href="static/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="static/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="static/css/bootstrap-social.css" />
<link rel="stylesheet" type="text/css" href="static/css/login.css" />
<link rel="stylesheet" type="text/css" href="static/css/main.css" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<script type="text/javascript" src="/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#linkedin-login").click(function() {
			$("#linkedin-signin")[0].submit();
		})
	});
</script>
</head>
<body>
	<div class="container container-main">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title ssurvey-title">
					<i class="fa fa-files-o"></i> Welcome to 
					<c:choose>
      					<c:when test="${empty param.name}">
      						SSurvey
     					</c:when>
     					<c:otherwise>
							${param.name}
      					</c:otherwise>
					</c:choose>
				</h3>
			</div>
			<div class="panel-body">
				<h4 style="text-align: justify;"><spring:message code="login.presentation" text="default text" /></h4>
				<form id="linkedin-signin" class="form-horizontal" role="form"
					action="<c:url value="/signin/linkedin?url=${url}"/>" method="POST">
					<div class="form-group" style="margin-top: 10px"></div>
					<div class="form-group">
						<div class="col-sm-8 col-sm-offset-2">
							<a id="linkedin-login"
								class="btn btn-block btn-social btn-linkedin btn-linkedin-link"
								style="text-align: center"> <i class="fa fa-linkedin"></i>
								Sign in with Linkedin
							</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>