<div class="panel-heading">
	<h3 class="panel-title">
		<span class="glyphicon glyphicon-hand-right"></span>
		${question.name}
	</h3>
</div>
<div class="panel-body">
	<div class="list-group-item" style="margin-bottom:0px;">
		<ul class="list-group">
			<c:forEach var="i" begin="${question.lowerBound}"
				end="${question.upperBound}">
				<span class="radio" style="margin-top:7px; line-height:30px;"> <label>
						<c:choose>
							<c:when test="${i == 1}">
								<input required="required" type="radio"
									name="question_${question.id}"
									class="question_${question.id}" value="${i}" style="margin-top:9px">${i} &nbsp;&nbsp; Not important
							</c:when>
							<c:when test="${i == 2}">
								<input required="required" type="radio"
									name="question_${question.id}"
									class="question_${question.id}" value="${i}" style="margin-top:9px">${i} &nbsp;&nbsp; Little important
							</c:when>
							<c:when test="${i == 3}">
								<input required="required" type="radio"
									name="question_${question.id}"
									class="question_${question.id}" value="${i}" style="margin-top:9px">${i} &nbsp;&nbsp; Important
							</c:when>
							<c:when test="${i == 4}">
								<input required="required" type="radio"
									name="question_${question.id}"
									class="question_${question.id}" value="${i}" style="margin-top:9px">${i} &nbsp;&nbsp; Very important
							</c:when>
							<c:when test="${i == 5}">
								<input required="required" type="radio"
									name="question_${question.id}"
									class="question_${question.id}" value="${i}" style="margin-top:9px">${i} &nbsp;&nbsp; Extremely important
							</c:when>
							<c:otherwise>
								<input required="required" type="radio"
									name="question_${question.id}"
									class="question_${question.id}" value="${i}">${i}
							</c:otherwise>
						</c:choose>
				</label>
				</span>
			</c:forEach>
		</ul>
		<br>
	</div>
</div>