<div class="panel-heading">
	<h3 class="panel-title">
		<span class="glyphicon glyphicon-hand-right"></span>
		${question.name}
	</h3>
</div>
<div class="panel-body body ss-question-open">
	<!-- .answerArea used in the validityAnswer -->
	<textarea required="required" name="question_${question.id}"
		class="answerArea form-control"
		placeholder="${question.upperBound} chars max"></textarea>
</div>