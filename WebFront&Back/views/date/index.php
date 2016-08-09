<!DOCTYPE html>
<html>
<head>
	<title>Home Page</title>
	<meta charset="UTF-8">
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
	<script type="text/javascript">
	history.pushState(null, null, document.title);
	window.addEventListener('popstate', function () {
	    history.pushState(null, null, document.title);
	});
	</script>
</head>
<body>
<center>
<div class='wrapper1'>
</div>
	<section class='aboutus' id='about'>
	<form action="index.php?r=createexception" method="post">
<input type="date" name="date" id="myDate" value="2016-01-01" style="width: 150px; size: 40px;"/>
<button class="mybutton2" onclick="myFunction()" type="submit">Choose Date</button>
</form>
<p id="demo"></p>
<script>
function myFunction() {
    var x = document.getElementById("myDate").value;
    document.getElementById("demo").innerHTML = x;
}
</script>
<br>
<form action="index.php?r=staffhome" method="post">
		<input type="hidden" name="_csrf" value="<?=Yii::$app->request->getCsrfToken()?>" /> <br>
		<button style="margin-left: 120px;" class="mybutton1"  type="submit">Back</button>
</form>
</center>	
	</section>
	
</body>
</html>