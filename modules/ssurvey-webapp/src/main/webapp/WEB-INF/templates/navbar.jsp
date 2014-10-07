<nav class="navbar navbar-blue" role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand navbar-button" href="/surveys/">SSurvey</a>
	</div>
	<div>
		<sec:authorize access="isAuthenticated()">
			<a class="navbar-brand pull-right navbar-button"
				href="/j_spring_security_logout"><small>Log out</a>
			<a class="navbar-brand pull-right"><small>Logged as
					${user.firstName} ${user.lastName}</small></a>
		</sec:authorize>
	</div>
</nav>