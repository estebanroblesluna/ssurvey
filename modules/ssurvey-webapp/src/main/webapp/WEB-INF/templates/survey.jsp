<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/jquery.countdown.css">
<script type="text/javascript"
	src="/static/js/CountDown/jquery.plugin.js"></script>
<script type="text/javascript"
	src="/static/js/CountDown/jquery.countdown.js"></script>
<script type="text/javascript" src="/static/js/jqueryUI/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="/static/css/questions.css">
<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="/static/css/main.css">
<script src="/static/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${survey.name}</title>
<script type="text/javascript">
	$(function() {
		
		$(".sortable").sortable();
		$(".sortable").disableSelection();
		
		$("#surveyForm").submit(function(){
			$(".rank-question").each(function(){
				var items = $(".rank-item",this);
				var itemValues = []
				for(var i = 0;i<items.length;i++){
					itemValues.push($(items[i]).text());
				}
				$(".rank-question-answer",this).val(itemValues.join("|"));
			})
		})
	})
</script>
</head>
<body>
	<nav class="navbar navbar-blue navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Survey</a>
			</div>


			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">${survey.name}</a></li>
				</ul>
				<div class="col-sm-4">
				<div class="progress" style="margin-top: 15px; margin-bottom: 0px;">
						<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60"
							aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
							60%</div>
				</div>
				</div>
				<div class="col-sm-1" style="padding-top: 8px;">
					<h4 class="counter" id="time">0:00</h4>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<li><a>${user.firstName} ${user.lastName}</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<form method="POST" id="surveyForm">
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
												<label> <input type="radio" required="required" name="question_${question.id}" value="${option}">
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
												<label> <input name="question_${question.id}" type="checkbox" value="${option}">
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
								<textarea required="required" name="question_${question.id}" class="form-control" placeholder="1024 chars max"></textarea>
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
										<label class="radio-inline"> <input required="required" type="radio"
											name="question_${question.id}" id="inlineRadio${i}"
											value="${i}"> ${i}
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
								<div class="panel-body rank-question">
									
									<ol class="list-group sortable">
										<c:forEach var="option" items="${question.options}">
											<li class="list-group-item">
												<div>
													<span class="glyphicon glyphicon-move"></span><label class="rank-item">${option}</label>
												</div>
											</li>
										</c:forEach>
									</ol>
									<input type="hidden" class="rank-question-answer" name="question_${question.id}">
								</div>
							</div>
						</div>
					</div>
				</c:when>
			</c:choose>
		</c:forEach>

		<div class="row bottom-survey-buttons">
			<div class="col-sm-2 col-sm-offset-4">
				<button type="submit" class="btn btn-success">
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
	</form>

</body>
</html>