<nav class="navbar navbar-blue" role="navigation">
    <div class="navbar-header">
      	<a class="navbar-brand" href="#">SSurvey</a>
    </div>
    <div>
    	<sec:authorize access="isAuthenticated()">
            <a class="navbar-brand pull-right" href="/j_spring_security_logout"><small>Log out</a>
       		<a class="navbar-brand pull-right" href="#"><small>Logged as ${user.firstName} ${user.lastName}</small></a>
        </sec:authorize>
    	
    </div>
</nav>