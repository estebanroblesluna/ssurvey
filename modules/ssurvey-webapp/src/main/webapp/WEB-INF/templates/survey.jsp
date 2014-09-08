<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/jquery.countdown.css">
	<script type="text/javascript" src="/static/js/CountDown/jquery.plugin.js"></script>
	<script type="text/javascript" src="/static/js/CountDown/jquery.countdown.js"></script>
	<script type="text/javascript" src="/static/js/jqueryUI/jquery-ui.js"></script>
	<link rel="stylesheet" type="text/css" href="/static/css/questions.css">
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>${survey.name}</title>
<script type="text/javascript">
	$(function() {
		$(".sortable").sortable();
		$(".sortable").disableSelection();
	})
</script>
</head>
<body>
	<nav class="navbar navbar-blue" role="navigation">
	    <div class="navbar-header">
	      	<a class="navbar-brand" href="#">SSurvey</a>
	    </div>
	    <div class="col-md-offset-4">
			<a class="navbar-brand" style="font-size:22px">${survey.name}</a>
	    </div>
	</nav>
	<div class="container question-container">
		<c:forEach var="question" items="${survey.questions}">
			<c:choose>
				<c:when test="${question.type == 'SINGLE_CHOICE_QUESTION' }">
					<div class="col-md-12">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">
									<span class="glyphicon glyphicon-hand-right"></span>${question.name}
								</h3>
							</div>
							<div class="panel-body">
								<ul class="list-group">
									<c:forEach var="option" items="${question.options}">
										<li class="list-group-item">
											<div class="radio">
												<label> <input type="radio" name="optionsRadios">
													${option}
												</label>
											</div>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</c:when>
				<c:when test="${question.type == 'MULTIPLE_CHOICE_QUESTION' }">
					<div class="col-md-12">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">
									<span class="glyphicon glyphicon-hand-right"></span>
									${question.name}
								</h3>
							</div>
							<div class="panel-body">
								<ul class="list-group">
									<c:forEach var="option" items="${question.options}">
										<li class="list-group-item">
											<div class="checkbox">
												<label> <input type="checkbox" value="">
													${option}
												</label>
											</div>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</c:when>
				<c:when test="${question.type == 'OPEN_ANSWER_QUESTION' }">
					<div class="col-md-12 ss-question-open">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">
									<span class="glyphicon glyphicon-hand-right"></span>
									${question.name}
								</h3>
							</div>
							<div class="panel-body">
								<textarea class="form-control" placeholder="1024 chars max"></textarea>
							</div>
						</div>
					</div>
				</c:when>
				<c:when test="${question.type == 'NUMERIC_ANSWER_QUESTION' }">
					<div class="col-md-12 ss-question-multiple">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">
									<span class="glyphicon glyphicon-hand-right"></span>
									${question.name}
								</h3>
							</div>
							<div class="panel-body">
								<c:forEach var="i" begin="${question.lowerBound}"
									end="${question.upperBound}">
									<div class="col-md-12 list-group-item">
										<label class="radio-inline"> <input type="radio"
											name="inlineRadioOptions" id="inlineRadio${i}"
											value="option${i}"> ${i}
										</label>
									</div>
								</c:forEach>
							</div>

						</div>
					</div>
				</c:when>
				<c:when test="${question.type == 'RANK_ANSWER_QUESTION' }">
					<div class="col-md-12">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">
									<span class="glyphicon glyphicon-hand-right"></span> Ranking questions
								</h3>
							</div>

							<div class="panel-body">
								<div class="panel-body">
									<ol class="list-group sortable">
										<c:forEach var="option" items="${question.options}">
											<li class="list-group-item">
												<div class="checkbox">
													<label> ${option} </label>
												</div>
											</li>
										</c:forEach>
									</ol>
								</div>
							</div>
						</div>
					</div>
				</c:when>
			</c:choose>
		</c:forEach>
		<div class="row bottom-survey-buttons">
			<div class="col-sm-2 col-sm-offset-4">
				<button type="button" class="btn btn-success">
					<span class="glyphicon glyphicon-ok-sign"></span> Accept
				</button>
			</div>
			<div class="col-sm-4">
				<button type="button" class="btn btn-danger">
					<span class="glyphicon glyphicon-remove-sign"></span> Cancel
				</button>
			</div>
		</div>
	</div>

	<div class="footer navbar-fixed-bottom">


		<nav class="navbar navbar-default" role="navigation"
			style="bottom: -20px">
			<div class="col-sm-2" style="padding-top: 8px;">
				<h5>${user.firstName} ${user.lastName}</h5>
			</div>

			<div class="col-sm-9" style="padding-top: 15px;">


				<div class="progress">
					<div class="progress-bar" role="progressbar" aria-valuenow="60"
						aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
						60%</div>
				</div>
			</div>
			<div class="col-sm-1" style="padding-top: 8px;">
				<h4 class="counter" id="time">0:00</h4>
			</div>
		</nav>

	</div>
</body>
</html>