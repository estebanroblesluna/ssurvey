<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<script src="/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="/static/js/jqueryUI/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="/static/css/jqueryUI/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="/static/css/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="/static/css/questions.css"/>
<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="/static/css/main.css"/>
<script src="/static/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<title>${survey.name}</title>
<link rel=icon href=/static/img/favicon.ico type="image/png"/>
<script type="text/javascript">
	$(function() {

		$(".sortable").sortable({
			connectWith: "ol",
		    cursor: 'move',
			stop: function(event, ui){
				$(".rank-order",ui.item).empty();
				$(".rank-order",ui.item).attr("class","rank-order fa fa-reorder fa-lg");
				ui.item.closest(".question-container").find(".ordered-answers").find(".list-group-item").each(function(){
					var badge = $(".rank-order",$(this));
					badge.attr("class","rank-order badge");
					badge.html($(this).index()+1);
					var container = $(".question-container[style='display: block;']")
					container.find(".ordered").find('li').each(function(){
						if($(this).has(".alert-danger")) {
							$(this).find(".alert-danger").remove();
						}
					})
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
		
		var $submitTimer = null;
		
		function startTimer() {
		    var $form = $('#survey-submit');
	        var $submitButton = $form.find(".submit-answer-button");
		    var $currentTime;
		    $(function() {
		    	$currentTime = 6000;
		        $submitTimer = setInterval(function () {updateTimer()}, 1000);
		    });
		    function updateTimer() {
		        var $timeString = Math.max((($currentTime-1000)/1000), 0) + " seconds";
		        $submitButton.html("<span class=\"glyphicon glyphicon-ok-sign\"></span> Submit (" + $timeString + ")");
		        if ($currentTime == 0) {
		        	clearInterval($submitTimer);
		        	$("#surveyForm").submit();
		        } else {
		        	$currentTime -= 1000;
		       		if ($currentTime < 0) $currentTime = 0;
		        }
		    }
		}
		function stopTimer() {
			var $form = $('#survey-submit');
	        var $submitButton = $form.find(".submit-answer-button");
	        $(function() {
	        	clearInterval($submitTimer);
	        	$submitButton.html("<span class=\"glyphicon glyphicon-ok-sign\"></span> Submit (5 seconds)");
		    });
		}
		
		var actualSize = 0;
		var numberOfQuestions = ${fn:length(survey.questions)};
		var actualPosition = 0;
		var flags = new Array(numberOfQuestions+1);
		for (var i = 0; i < flags.length; i++) flags[i] = false; 
		
		function setOpenAnswerValidationPopup(container){
			var button = $(".submit-answer-button",container)
			button.popover({
				"content": "You can't leave this unanswered.",
				"placement": "right",
				"container": "body",
				"trigger": "manual"
			})
		}
		
		function setMultipleChoiceAnswerValidationPopup(container){
			var button = $(".submit-answer-button",container)
			button.popover({
				"content": "You must choice at least one option.",
				"placement": "right",
				"container": "body",
				"trigger": "manual"
			})
		}
		
		function setSingleChoiceAnswerValidationPopup(container){
			var button = $(".submit-answer-button",container)
			button.popover({
				"content": "You must choice one option.",
				"placement": "right",
				"container": "body",
				"trigger": "manual"
			})
		}
		
		function setRankAnswerValidationPopup(container){
			var button = $(".submit-answer-button",container)
			button.popover({
				"content": "Please, sort the items.",
				"placement": "right",
				"container": "body",
				"trigger": "manual"
			})
		}
		
		function setNumericAnswerValidationPopup(container){
			var button = $(".submit-answer-button",container)
			button.popover({
				"content": "You must choice one option.",
				"placement": "right",
				"container": "body",
				"trigger": "manual"
			})
		}
		
		//Set the error buttons
		$(".question-container").each(function(){
			var container = $(this);
			switch(container.attr("data-questionType")){
				case "MULTIPLE_CHOICE_QUESTION":
					setMultipleChoiceAnswerValidationPopup(container);
				case "SINGLE_CHOICE_QUESTION":
					setSingleChoiceAnswerValidationPopup(container);
				case "OPEN_QUESTION":
					setOpenAnswerValidationPopup(container);
				case "RANK_QUESTION":
					setRankAnswerValidationPopup(container);
				case "NUMERIC_QUESTION":
					setNumericAnswerValidationPopup(container);
			}		
		})
		
		function validateAnswer(container){
			switch(container.attr("data-questionType")){
				case "MULTIPLE_CHOICE_QUESTION":
					return $("input[type='checkbox']:checked",container).length != 0;
				case "SINGLE_CHOICE_QUESTION":
					return $("input[type='radio']:checked",container).length != 0;
				case "NUMERIC_QUESTION":
					return $("input[type='radio']:checked",container).length != 0;
				case "OPEN_QUESTION":
					return $(".answerArea",container).val() != "";
				case "RANK_QUESTION":
					return $(".rank-question",container).data("answered") == true;
			}
			return true;
		}
		
		$("#start-survey").click(function(){
			var container = $(this).closest(".question-container");
			container.hide(500, function(){
				container.next().show(500);
			})
		})
		
		$(".submit-answer-button").click(function(){
			var button = $(this);
			var container = button.closest(".question-container");
			if(validateAnswer(container)){
				button.popover('hide');
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
						if (actualPosition == numberOfQuestions) startTimer();
						container.next().show(500);
					}
				})
				actualPosition++;
			} else {
				if (container.attr("data-questionType") == 'RANK_QUESTION' ){
					container.find(".unordered").find('li').each(function(){
						if(!($(this).has(".alert-danger").length)) { 
							$(this).append($('<div class="alert alert-danger" role="alert" style="padding: 5px;margin-top: 10px;margin-bottom: 0px;width: 310px;"><span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span><span class="sr-only">Error:</span>You must drop this answer into the dash box below</div>'));	
						}
					})	
				} else {
					button.popover("show");
					setTimeout(function() {
						button.popover("hide");
					}, 3000)
				}
			}
		})
		
		$(".previous-question-button").click(function(){
			var button = $(this);
			var container = button.closest(".question-container");
			var confirmationButton = container.find(".submit-answer-button");
			confirmationButton.popover('hide');
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
			if (actualPosition == numberOfQuestions) stopTimer();
			actualPosition--;
		})
		
	});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/templates/navbarWithProgress.jsp" />

	<form method="POST" id="surveyForm">
		<div class="container">
			<div class="question-container" data-questionType="presentation">
				<div class="col-md-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">
								<span class="glyphicon glyphicon-hand-right"></span>
								Survey Presentation
							</h3>
						</div>
						<div class="panel-body">
							<h4 style="text-align:justify; padding: 15px;">${survey.presentation}</h4>
						</div>
						<div class="panel-footer text-left">
							<button id="start-survey" type="button"
								class="btn btn-success">
								<span class="glyphicon glyphicon-ok-sign"></span> Start
							</button>
						</div>
					</div>
				</div>
			</div>
			<c:forEach varStatus="status" var="question" items="${survey.questions}">
				<div class="question-container" hidden="hidden" data-questionType="${question.type}">
				<div class="col-md-12">
					<div class="panel panel-primary">
						<c:choose>
							<c:when test="${question.type == 'SINGLE_CHOICE_QUESTION' }">
								<%@ include file="/WEB-INF/templates/questions/single-choice-question.jsp" %>
							</c:when>
							<c:when test="${question.type == 'MULTIPLE_CHOICE_QUESTION' }">
								<%@ include file="/WEB-INF/templates/questions/multiple-choice-question.jsp" %>
							</c:when>
							<c:when test="${question.type == 'OPEN_QUESTION' }">
								<%@ include file="/WEB-INF/templates/questions/open-question.jsp" %>
							</c:when>
							<c:when test="${question.type == 'NUMERIC_QUESTION' }">
								<%@ include file="/WEB-INF/templates/questions/numeric-question.jsp" %>
							</c:when>
							<c:when test="${question.type == 'RANK_QUESTION' }">
								<%@ include file="/WEB-INF/templates/questions/rank-question.jsp" %>
							</c:when>
						</c:choose>
						<div class="panel-footer text-left">
							<button type="button"
								class="btn btn-primary previous-question-button" 
								<c:if test="${status.index==0}">disabled="disabled"
											</c:if>>
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
						<span class="glyphicon glyphicon-hand-right"></span>
					</h3>
				</div>
				<div class="panel-body">
  					<p style="font-size:16px; margin:10px; text-align:center;"> Thank you for answered the survey. Please, confirm to send the data. </p>
  				</div>
				<div id="survey-submit" class="panel-footer text-left">
					<button type="button" class="btn btn-primary previous-question-button">
						<span class="glyphicon glyphicon-arrow-left"></span> Previous
					</button>
					<button type="button" class="btn btn-success submit-answer-button">
						<span class="glyphicon glyphicon-ok-sign"></span> Submit (5 seconds)
					</button>
				</div>
			</div>
		</div>
	</form>

</body>
</html>
