<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Questions</title>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/jquery.countdown.css">
<script type="text/javascript" src="js/CountDown/jquery.plugin.js"></script>
<script type="text/javascript" src="js/CountDown/jquery.countdown.js"></script>
<script type="text/javascript" src="static/js/jqueryUI/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="static/css/questions.css">
<link rel="stylesheet" type="text/css" href="static/css/bootstrap.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript">
	// two global variables
	/*var secondsRemaining;
	var intervalHandle;

	function resetPage() {
		document.getElementById("inputArea").style.display = "block";
	}

	function tick() {
		// grab the h1
		var timeDisplay = document.getElementById("time");

		// turn seconds into mm:ss
		var min = Math.floor(secondsRemaining / 60);
		var sec = secondsRemaining - (min * 60);

		// add a leading zero (as a string value) if seconds less than 10
		if (sec < 10) {
			sec = "0" + sec;
		}
		// concatenate with colon
		var message = min + ":" + sec;
		// now change the display
		timeDisplay.innerHTML = message;

		// stop if down to zero
		if (secondsRemaining === 0) {
			alert("Done!");
			clearInterval(intervalHandle);
			resetPage();
		}
		// subtract from seconds remaining
		secondsRemaining--;
	}

	function startCountdown() {
		// get contents of the "minutes" text box
		var minutes = document.getElementById("minutes").value;
		// check if not a number
		if (isNaN(minutes)) {
			alert("Please enter a number!");
			return;
		}
		// how many seconds?
		secondsRemaining = minutes * 60;
		// every second, call the "tick" function
		intervalHandle = setInterval(tick, 1000);
		// hide the form
		document.getElementById("inputArea").style.display = "none";
	}

	// as soon as the page is loaded...
	window.onload = function() {
		// create input text box and give it an id of "minutes"
		var inputMinutes = document.createElement("input");
		inputMinutes.setAttribute("id", "minutes");
		inputMinutes.setAttribute("type", "text");
		// create a button
		var startButton = document.createElement("input");
		startButton.setAttribute("type", "button");
		startButton.setAttribute("value", "Start Countdown");
		startButton.onclick = function() {
			startCountdown();
		};
		// add to the DOM, to the div called "inputArea"
		document.getElementById("inputArea").appendChild(inputMinutes);
		document.getElementById("inputArea").appendChild(startButton);
	};*/

	$(function() {
		$(".sortable").sortable();
		$(".sortable").disableSelection();
	})
</script>
</head>
<body>
	<div class="container" style="margin-top: 25px; margin-bottom: 55px;">
		<div class="col-md-12">
			<h3 class="text-center">${survey.name}</h3>
		</div>
		<c:forEach var="question" items="${survey.questions}">
			<c:choose>
				<c:when test="${question.type == 'SINGLE_CHOICE_QUESTION' }">
					<div class="col-md-12">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">
									<span class="glyphicon glyphicon-arrow-right"></span>${question.name}<span
										class="glyphicon glyphicon-new-window"></span>
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
									<span class="glyphicon glyphicon-hand-right"></span> Ranking
									questions
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
		<div class="row" style="margin-top: 25px; margin-bottom: 55px;">
			<div class="col-sm-2 col-sm-offset-4">
				<button type="button" class="btn btn-success">
					<span class="glyphicon glyphicon-ok-sign"></span> Confirmar
				</button>
			</div>
			<div class="col-sm-4">
				<button type="button" class="btn btn-danger">
					<span class="glyphicon glyphicon-remove-sign"></span> Cancelar
				</button>
			</div>
		</div>
	</div>

	<div class="footer navbar-fixed-bottom">


		<nav class="navbar navbar-default" role="navigation"
			style="bottom: -20px">
			<div class="col-sm-2" style="padding-top: 8px;">
				<h5>${account.firstName} ${account.lastName}</h5>
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