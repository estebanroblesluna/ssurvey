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