<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand navbar-button" href="/surveys/">SSurvey</a>
		</div>


		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a
					style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap; max-width: 300px;">${survey.name}</a></li>
			</ul>
			<div class="col-sm-6">
				<h5 class="col-sm-2 progress-title"> Progress: </h5>
				<div class="progress" style="margin-top: 15px; margin-bottom: 0px;">
					<div class="progress-bar progress-bar-success" role="progressbar"
						aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
						style="width: 0%;">
						<p></p>
					</div>
				</div>
			</div>
			<!-- <div class="col-sm-1" style="padding-top: 8px;">
				 <h4 class="counter" id="time">0:00</h4>
			</div> -->
			<ul class="nav navbar-nav navbar-right">
				<li><a style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap; max-width: 150px;">Logged as ${user.firstName} ${user.lastName}</a></li>
				<li><a class="navbar-brand navbar-button"
					href="/j_spring_security_logout"><small>Log out</a></li>
			</ul>
		</div>
	</div>
</nav>
