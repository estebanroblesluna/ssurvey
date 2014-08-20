<!DOCTYPE html>
<html>
	<head>
        <title>Questions</title>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/jquery.countdown.css"> 
        <script type="text/javascript" src="js/CountDown/jquery.plugin.js"></script> 
        <script type="text/javascript" src="js/CountDown/jquery.countdown.js"></script>
        <link rel="stylesheet" type="text/css" href="static/css/questions.css">
		<link rel="stylesheet" type="text/css" href="static/css/bootstrap.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="text/javascript">
            $("#getting-started")
            .countdown("2015/01/01", function(event) {
              $(this).text(
               event.strftime('%D days %H:%M:%S')
            );
            });
        </script>
	</head>
	<body>	
	<div class="container" style="margin-top: 25px; margin-bottom: 25px;">
    
        <div class="col-md-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <span class="glyphicon glyphicon-arrow-right"></span> How is My Site?
                        <span class="glyphicon glyphicon-new-window"></span>
                    </h3>
                </div>
                <div class="panel-body">
                    <ul class="list-group">
                        <li class="list-group-item">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="optionsRadios">
                                    Good
                                </label>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="optionsRadios">
                                    Excellent
                                </label>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="optionsRadios">
                                    Bed
                                </label>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="optionsRadios">
                                    Can Be Improved
                                </label>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="optionsRadios">
                                    No Comment
                                </label>
                            </div>
                        </li>
                    </ul>
                </div>
                
            </div>
        </div>
        
        <div class="col-md-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <span class="glyphicon glyphicon-hand-right"></span> Where do you get your news?</h3>
                </div>
                <div class="panel-body">
                    <ul class="list-group">
                        <li class="list-group-item">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="">
                                    Internet
                                </label>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="">
                                    Television
                                </label>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="">
                                    Radio
                                </label>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="">
                                    Newspaper
                                </label>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="">
                                    Others
                                </label>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-md-12 ss-question-open">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <span class="glyphicon glyphicon-hand-right"></span> Open Answer</h3>
                </div>
                <div class="panel-body">
        			<textarea class="form-control" placeholder="1024 chars max"></textarea>
        		</div>
        	</div>
        </div>
        <div class="col-md-12 ss-question-multiple">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <span class="glyphicon glyphicon-hand-right"></span> Cuan importante es la cocacola en tu vida?</h3>
                </div>
                <div class="panel-body">
                <div class="col-md-12 list-group-item">
                <label class="radio-inline">
  				<input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"> 1
				</label>
				<label class="radio-inline">
 				<input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> 2
				</label>
				<label class="radio-inline">
  				<input type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3"> 3
				</label>
                </div>
                </div>

        	</div>
        </div>
        
        <div class="col-md-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <span class="glyphicon glyphicon-hand-right"></span> Ranking questions</h3>
                </div>

                <div class="panel-body">
                    <ol class="list-group">
                        <li class="list-group-item">
                            <div class="checkbox">
                                <label>
                                    Internet
                                </label>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="checkbox">
                                <label>
                                    Television
                                </label>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="checkbox">
                                <label>
                                    Radio
                                </label>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="checkbox">
                                <label>
                                    Newspaper
                                </label>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="checkbox">
                                <label>
                                    Others
                                </label>
                            </div>
                        </li>
                    </ol>
                </div>
            </div>
        </div>

    </div>
</div>
<div class="footer navbar-fixed-bottom">
<div class="col-sm-9">
<nav class="nav nav-tabs nav-stacked" role="navigation">
  <div class="form-control">
    <div class="progress">
        <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                 60%
        </div>
    </div>
  </div>
</nav>
<div class="col-sm-12">
    <div id="getting-started"></div>
</div>

</div>
</div>
</body>	
</html>