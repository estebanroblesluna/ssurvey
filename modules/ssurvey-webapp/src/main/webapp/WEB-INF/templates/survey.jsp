<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<script src="/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="/static/js/jqueryUI/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css"
	href="/static/css/jqueryUI/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="/static/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="/static/css/questions.css">
<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="/static/css/main.css">
<script src="/static/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${survey.name}</title>
<link rel=icon href=/static/img/favicon.ico type="image/png">
<script type="text/javascript">
	$(function() {

		$(".sortable").sortable({
			connectWith: "ol",
			stop: function(event, ui){
				$(".rank-order",ui.item).empty();
				$(".rank-order",ui.item).attr("class","rank-order fa fa-reorder fa-lg");
				ui.item.closest(".question-container").find(".ordered-answers").find(".list-group-item").each(function(){
					var badge = $(".rank-order",$(this));
					badge.attr("class","rank-order badge");
					badge.html($(this).index()+1);
				})
				ui.item.closest(".rank-question").data("answered",ui.item.closest(".question-container").find(".unordered-answers").find(".list-group-item").length == 0)
			}
		});
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
		var actualSize = 0;
		var numberOfQuestions = ${fn:length(survey.questions)};
		var actualPosition = 0;
		var flags = new Array(numberOfQuestions+1);
		for (var i = 0; i < flags.length; i++) flags[i] = false; 
		
		function validateOpenAnswer(container){
			var button = $(".submit-answer-button",container)
			button.popover({
				"content": "You can't leave this unanswered.",
				"placement": "bottom",
				"container": "body",
				"trigger": "manual"
			})
		}
		
		function validateMultipleChoiceAnswer(container){
			var button = $(".submit-answer-button",container)
			button.popover({
				"content": "You must choice at least one option.",
				"placement": "bottom",
				"container": "body",
				"trigger": "manual"
			})
		}
		
		function validateSingleChoiceAnswer(container){
			var button = $(".submit-answer-button",container)
			button.popover({
				"content": "You must choice one option.",
				"placement": "bottom",
				"container": "body",
				"trigger": "manual"
			})
		}
		
		function validateRankAnswer(container){
			var button = $(".submit-answer-button",container)
			button.popover({
				"content": "Please, sort the items.",
				"placement": "left",
				"container": "body",
				"trigger": "manual"
			})
		}
		function validateNumericAnswer(container){
			var button = $(".submit-answer-button",container)
			button.popover({
				"content": "You must choice one option.",
				"placement": "left",
				"container": "body",
				"trigger": "manual"
			})
		}
		
		$(".question-container").each(function(){
			var container = $(this);
			switch(container.attr("data-questionType")){
				case "MULTIPLE_CHOICE_QUESTION":
					validateMultipleChoiceAnswer(container);
				case "SINGLE_CHOICE_QUESTION":
					validateSingleChoiceAnswer(container);
				case "OPEN_ANSWER_QUESTION":
					validateOpenAnswer(container);
				case "RANK_ANSWER_QUESTION":
					validateRankAnswer(container);
				case "NUMERIC_ANSWER_QUESTION":
					validateNumericAnswer(container);
			}		
		})
		
		function validateAnswer(container){
			switch(container.attr("data-questionType")){
				case "MULTIPLE_CHOICE_QUESTION":
					return $("input[type='checkbox']:checked",container).length != 0;
				case "SINGLE_CHOICE_QUESTION":
					return $("input[type='radio']:checked",container).length != 0;
				case "NUMERIC_ANSWER_QUESTION":
					return $("input[type='radio']:checked",container).length != 0;
				case "OPEN_ANSWER_QUESTION":
					return $(".answerArea",container).val() != "";
				case "RANK_ANSWER_QUESTION":
					return $(".rank-question",container).data("answered") == true;
			}
			return true;
		}
		
		$(".submit-answer-button").click(function(){
			var container = $(this).closest(".question-container");
			if(validateAnswer(container)){
				container.hide(500, function(){
					if (actualSize < 100 && flags[actualPosition] == false) {
						flags[actualPosition] = true;
						actualSize += 100 / numberOfQuestions;
						$(".progress-bar").width(actualSize + "%");
						$(".progress-bar").text(actualSize.toFixed(2) + "%");
					}
			
					if(container.next().length == 0){
						$("#surveyForm").submit();						
					} else {
						container.next().show(500);
					}
				})
				actualPosition++;
			} else {
				$(this).popover("show");
				var button = $(this);
				setTimeout(function() {
				    $(button).popover('hide');
				}, 3000)
			}
			
		})
		
		$(".previous-question-button").click(function(){
			var container = $(this).closest(".question-container");
			if (actualSize > 0 && flags[actualPosition] == true) {
				flags[actualPosition] = false;
				actualSize -= 100 / numberOfQuestions;
				$(".progress-bar").width(actualSize + "%");
				$(".progress-bar").text(actualSize.toFixed(2) + "%");
			}
			if(container.prev().length == 0){
				return;
			} else {
				container.hide(500, function(){
					container.prev().show(500);
				});
			}
			actualPosition--;
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
						<div class="question-container"
							data-questionType="${question.type}">
					</c:when>
					<c:otherwise>
						<div class="question-container" hidden="hidden"
							data-questionType="${question.type}">
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
									<textarea required="required" name="question_${question.id}"
										class="answerArea form-control"
										placeholder="${question.upperBound} chars max"></textarea>
								</div>
							</c:when>
							<c:when test="${question.type == 'NUMERIC_ANSWER_QUESTION' }">
								<div class="panel-body ss-question-multiple">
									<div class="col-md-12 list-group-item">
										<ul class="list-group">
											<c:forEach var="i" begin="${question.lowerBound}"
												end="${question.upperBound}">
												<span class="radio-inline"> <label><input
														required="required" type="radio"
														name="question_${question.id}"
														class="question_${question.id}" value="${i}">${i}
												</label>
												</span>
											</c:forEach>
										</ul>
										<br>
										<div class="alert alert-success"
											style="margin-bottom: 0px; margin-right: 500px" role="alert">Disagree:
											1 - Agree: 10</div>
									</div>
								</div>
							</c:when>

							<c:when test="${question.type == 'RANK_ANSWER_QUESTION' }">
								<div class="panel-body">
									<div class="panel-body rank-question" data-answerd="false">
										<ol class="list-group sortable unordered-answers">
											<c:forEach var="option" items="${question.options}">
												<li class="list-group-item">
													<div>
														<span class="rank-order fa fa-reorder fa-lg"></span><label
															class="rank-item">${option}</label>
													</div>
												</li>
											</c:forEach>
										</ol>
										<h6>Drag the answers into the dashed box:</h6>
										<ol class="list-group sortable ordered-answers">

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
		<div class="question-container" hidden="hidden">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-hand-right"></span> Confirm
					</h3>
				</div>
				<div class="panel-footer text-right">
					<button type="button"
						class="btn btn-primary previous-question-button">
						<span class="glyphicon glyphicon-arrow-left"></span> Previous
					</button>
					<button type="button" class="btn btn-success submit-answer-button">
						<span class="glyphicon glyphicon-ok-sign"></span> Submit
					</button>
				</div>
			</div>
		</div>
	</form>

</body>
</html>