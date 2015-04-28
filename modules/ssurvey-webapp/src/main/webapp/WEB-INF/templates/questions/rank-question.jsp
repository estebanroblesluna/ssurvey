<div class="panel-heading">
	<h3 class="panel-title">
		<span class="glyphicon glyphicon-hand-right"></span>
		${question.name} (being 1 the most important, and ${fn:length(question.options)} the least important)
	</h3>
</div>
<div class="panel-body">
	<div class="panel-body rank-question" data-answerd="false">
		<ol class="unordered list-group sortable unordered-answers"
			style="border: 2px solid; margin-bottom: 5px; margin-left: 5px; margin-top: 5px; margin-right: 5px;">
			<c:forEach var="option" items="${question.options}">
				<li class="list-group-item" >
					<div>
						<span class="rank-order fa fa-reorder fa-lg"></span><label
							class="rank-item">${option}</label>
					</div>
				</li>
			</c:forEach>
		</ol>
		<h5 style="text-align: center;">
			<img src="/static/img/up2.png" alt="Up2" style="width: 15; margin-top: -15;">
			Drag the answers into the dashed box: 
			<img src="/static/img/down2.png" alt="Down2" style="width: 15; margin-top: 10;">
		</h5>

		<ol class="ordered list-group sortable ordered-answers" 
			style="border: 2px dashed; margin-bottom: 5px; margin-left: 5px; margin-top: 5px; margin-right: 5px;">
		</ol>

		<input type="hidden" class="rank-question-answer"
			name="question_${question.id}">
	</div>
</div>