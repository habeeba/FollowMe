<?php
?>
<html>
<head>
<title>Create Agenda Page</title>
<meta charset="UTF-8">
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
	history.pushState(null, null, document.title);
	window.addEventListener('popstate', function () {
	    history.pushState(null, null, document.title);
	});
	</script>
</head>
<body>
<section class='aboutus' id='about'>
<div class='wrapper'>
		<header>
			<!-- HEADER STARTS HERE-->
			<div class='container titles yomer-text-center'>
				
			</div>
		</header>
		</div>
		<h3>Hints</h3>		
		<ul style="font-size: 18px; color: black;">
<li style="float: left;">if you want to enter lectures/labs times for subject , you have to enter content only.</li>
<li style="float: left;">if you want to enter office hours for subject , you have to enter content and maximum number of students that can book.</li>
</ul>
<br><br>
<form action="index.php?r=checkcreateagenda" method="post">

<table border="5" >
<tr style="background-color: #1C54FF; color: white;" ><td>Day/Slot</td><td>8-9:30</td><td>9:30-11</td><td>11:15-12:45</td><td>12:45-2:15</td><td>2:30-4</td><td>4-5:30</td></tr>
<tr><td style="background-color: #1C54FF; color: white;">Saturday</td><td><input title="maximum number of students" type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" title="maximum number of students" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td></tr>
<tr><td style="background-color: #1C54FF; color: white;">Sunday</td><td><input title="maximum number of students" type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td></tr>
<tr><td style="background-color: #1C54FF; color: white;">Monday</td><td><input title="maximum number of students" type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td></tr>
<tr><td style="background-color: #1C54FF; color: white;">Teusday</td><td><input title="maximum number of students" type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td></tr>
<tr><td style="background-color: #1C54FF; color: white;">Wednesday</td><td><input title="maximum number of students" type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td></tr>
<tr><td style="background-color: #1C54FF; color: white;">Thursday</td><td><input title="maximum number of students" type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td><td><input type="text" name="max[]" value="0" onfocus="if (this.value=='0') this.value = ''"/><br><input type="text" name="content[]" value="closed" onfocus="if (this.value=='closed') this.value = ''"/></td></tr>
</table>
<br>
<br>
<button class="mybutton2"  type="submit"> Create Agenda </button>
</form>

</section>
</body>

</html>
