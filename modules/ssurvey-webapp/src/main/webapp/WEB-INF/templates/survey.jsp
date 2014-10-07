<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script src="/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="/static/js/jqueryUI/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="/static/css/questions.css">
<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="/static/css/main.css">
<script src="/static/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${survey.name}</title>
<link rel=icon href=/static/img/favicon.ico type="image/png">
<script type="text/javascript">
	$(function() {

		$(".sortable").sortable();
		$(".sortable").disableSelection();

		$("#surveyForm").submit(function() {
			$(".rank-question").each(function() {
				var items = $(".rank-item", this);
				var itemValues = []
				for (var i = 0; i < items.length; i++) {
					itemValues.push($(items[i]).text());
				}
				$(".rank-question-answer", this).val(itemValues.join("|"));
			})
		})

	})

	$(document).ready(function() {
		var size = 0;
		var ok = true;
		var ok2 = true;
		var ok3 = true;
		var ok4 = true;
		var ok5 = true;
		/*$(".radio").click(function() {
			if (size < 100 && ok) {
				size += 33;
				console.log(size);
				$(".progress-bar").width(size + "%");
				$(".progress-bar").text(size + "%");
				ok = false;
			}
		});*/
		$(".question_103").click(function() {
			if (size < 100 && ok) {
				size += 20;
				console.log(size);
				$(".progress-bar").width(size + "%");
				$(".progress-bar").text(size + "%");
				ok = false;

			}
		});
		$(".question_104").click(function() {
			if (size < 100 && ok2) {
				size += 20;
				console.log(size);
				$(".progress-bar").width(size + "%");
				$(".progress-bar").text(size + "%");
				ok2 = false;

			}
		});
		$(".question_106").click(function() {
			if (size < 100 && ok3) {
				size += 20;
				console.log(size);
				$(".progress-bar").width(size + "%");
				$(".progress-bar").text(size + "%");
				ok3 = false;

			}
		});
		$(".question_108").click(function() {
			if (size < 100 && ok4) {
				size += 20;
				console.log(size);
				$(".progress-bar").width(size + "%");
				$(".progress-bar").text(size + "%");
				ok4 = false;

			}
		});
		$(".question_109").click(function() {
			if (size < 100 && ok5) {
				size += 20;
				console.log(size);
				$(".progress-bar").width(size + "%");
				$(".progress-bar").text(size + "%");
				ok5 = false;

			}
		});
		/*$(".body").keypress(function(){
		if ($("#open").val() == ""){
			if (size < 100 && ok3) {
				size += 34;
				console.log(size);
				$(".progress-bar").width(size + "%");
				$(".progress-bar").text(size + "%");
				ok3 = false;
			}
		}
		});*/

	});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/templates/navbarWithProgress.jsp" />

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
													<label> <input type="radio" required="required"
														name="question_${question.id}"
														class="question_${question.id}" value="${option}">
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
													<label> <input name="question_${question.id}"
														type="checkbox" value="${option}"> ${option}
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
								<div class="panel-body body">
									<textarea id="open" required="required"
										name="question_${question.id}" class="form-control"
										placeholder="1024 chars max"></textarea>
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
									<div class="col-md-12 list-group-item">
										<select class="form-control">
											<c:forEach var="i" begin="${question.lowerBound}"
												end="${question.upperBound}">
												<option required="required" type="radio"
													name="question_${question.id}" id="inlineRadio${i}"
													value="${i}">${i}</option>
											</c:forEach>
										</select>
									</div>
								</div>

							</div>
						</div>
					</c:when>
					<c:when test="${question.type == 'RANK_ANSWER_QUESTION' }">
						<div class="col-md-12">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title">
										<span class="glyphicon glyphicon-hand-right"></span>
										${question.name}
									</h3>
								</div>

								<div class="panel-body">
									<div class="panel-body rank-question">

										<ol class="list-group sortable">
											<c:forEach var="option" items="${question.options}">
												<li class="list-group-item">
													<div>
														<span class="glyphicon glyphicon-move"></span><label
															class="rank-item">${option}</label>
													</div>
												</li>
											</c:forEach>
										</ol>
										<input type="hidden" class="rank-question-answer"
											name="question_${question.id}">
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