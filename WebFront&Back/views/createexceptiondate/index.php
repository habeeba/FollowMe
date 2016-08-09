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
<input type="date" id="myDate" value="2016-01-01">
<button onclick="myFunction()">Choose Date</button>
<p id="demo"></p>
<script>
function myFunction() {
    var x = document.getElementById("myDate").value;
    document.getElementById("demo").innerHTML = x;
}
</script>
</center>	
	</section>
	
</body>
</html>