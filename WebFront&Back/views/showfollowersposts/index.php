<!DOCTYPE h3 PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<script>
$(function() 
		{
	var source = [ { value: "index.php?r=profile&staffID=ty",
        label: "Fatma Omara"
      },
      { value: "index.php?r=profile&staffID=bg",
        label: "BG"
      },
      { value: "index.php?r=profile&staffID=bg",
          label: "Medo"
        },
      { value: "index.php?r=profile&staffID=ty",
          label: "TY"
        }
    ];

	$(document).ready(function() {
	    $("#tags").autocomplete({
	        source: source,
	        select: function( event, ui ) { 
	            window.location.href = ui.item.value;
	        }
	    });
	});
    });
            
        </script>

</head>
<body>
<div class='wrapper'>
		<header>
			<!-- HEADER STARTS HERE-->
			<div class='container titles yomer-text-center'>
				
			</div>
		</header>
		</div>

<section class='aboutus' id='about'>
<center>
		<br> <br>
	<table border="0.5">
		<tr><td><center><label for="tags"></label> <input id="tags" style="text-align:center; width: 350px; height: 30px;"></center></td>
		<td><img src="https://cdn4.iconfinder.com/data/icons/business-icons-18/64/Business_Icons-52-128.png" alt="Search" width="50" height="50">
	</td>
	</tr>
	</table>
	</center>
	</section>
	<section class='aboutus' id='about'>
	<br>
	<br>
	<form style="float: right;" action="index.php?r=site" method="post">
		<input type="hidden" name="_csrf"
			value="<?=Yii::$app->request->getCsrfToken()?>" /> <input
			style="color: fuchsia; height: 40px; width: 150px; font: bold; font-size: 15px"
			type="submit" value="Logout" />
	</form>
	<br>
	<form action="index.php?r=Post/showfollowersposts" method="post"> 
		<input type="hidden" name="_csrf" value="<?=Yii::$app->request->getCsrfToken()?>" />
	</form>
	
	<?php 
	$session = Yii::$app->session;
	$session->open ();
	$studentID = $session->get ("studentID");
	$ch = curl_init("http://localhost/basic/web/index.php?r=post/showfollowersposts");
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, "studentID=".$studentID);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$result = curl_exec ($ch);
	curl_close($ch);
	$allFollwers = json_decode($result , true);
	for ($i = 0 ; $i < sizeof($allFollwers) ; $i++)
	{
		for ($j = 0 ; $j < sizeof($allFollwers[$i]); $j++)
		{
				echo $allFollwers[$i][$j]["Content"];
				echo str_repeat('&nbsp;', 10);
				echo $allFollwers[$i][$j]["Time"];
				echo "<br>";
				echo $allFollwers[$i][$j]["Owner"];
				echo "<br>";
		}
		echo "<br><br>";
	}
	?>
</section>
</body>
</html>