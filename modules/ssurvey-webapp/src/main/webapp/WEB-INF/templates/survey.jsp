<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		var arrayLength = ${fn:length(survey.questions)};
		var pos = 0;
		var aBoolean = new Array(arrayLength);
		
		for (i = 0; i < aBoolean.length; ++i) {aBoolean[i] = false;} 
		
		$(".submit-answer-button").click(function(){
			var container = $(this).closest(".question-container");
			container.hide(500, function(){
				
				if (size < 100 && aBoolean[pos] == false) {
					size += 100 / arrayLength;
					aBoolean[pos] = true;
					console.log(size);
					$(".progress-bar").width(size + "%");
					$(".progress-bar").text(size.toFixed(2) + "%");
				}

				if(container.next().length == 0){
					$("#surveyForm").submit();
					
				} else {
					container.next().show(500);
				}
			})
			pos += 1;
		})
		
		$(".previous-question-button").click(function(){
			var container = $(this).closest(".question-container");
			if(container.prev().length == 0){
				return;
			} else {
				container.hide(500, function(){
					container.prev().show(500);
				});
			}
			pos -= 1;
		})

	});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/templates/navbarWithProgress.jsp" />

	<form method="POST" id="surveyForm">
		<div class="container">

			<c:forEach varStatus="status" var="question"
				items="${survey.questions}">
				<c:choose>
					<c:when test="${status.index == 0}">
						<div class="question-container">
					</c:when>
					<c:otherwise>
						<div class="question-container" hidden="hidden">
					</c:otherwise>
				</c:choose>
				<div class="col-md-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">
								<span class="glyphicon glyphicon-hand-right"></span>
								${question.name}
							</h3>
						</div>
						<c:choose>
							<c:when test="${question.type == 'SINGLE_CHOICE_QUESTION' }">
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
							</c:when>
							<c:when test="${question.type == 'MULTIPLE_CHOICE_QUESTION' }">
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
							</c:when>
							<c:when test="${question.type == 'OPEN_ANSWER_QUESTION' }">
								<div class="panel-body body ss-question-open">
									<textarea id="open" required="required"
										name="question_${question.id}" class="form-control"
										placeholder="1024 chars max"></textarea>
								</div>
							</c:when>
							<c:when test="${question.type == 'NUMERIC_ANSWER_QUESTION' }">
								<div class="panel-body ss-question-multiple">
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
							</c:when>

							<c:when test="${question.type == 'RANK_ANSWER_QUESTION' }">
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
							</c:when>

						</c:choose>
						<div class="panel-footer text-right">

							<button type="button"
								class="btn btn-primary previous-question-button"
								<c:if test="${status.index==0}">disabled="disabled" </c:if>>
								<span class="glyphicon glyphicon-arrow-left"></span> Previous
							</button>
							<button type="button"
								class="btn btn-success submit-answer-button">
								<span class="glyphicon glyphicon-ok-sign"></span> Next
							</button>
						</div>
					</div>
				</div>
		</div>
		</c:forEach>


		</div>
	</form>

</body>
</html>