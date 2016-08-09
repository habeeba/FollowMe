<?php
use app\models\Staff;
$session = Yii::$app->session;
$session->open ();
$dataReader = Staff::findBySql("SELECT * FROM staff;")->all();
?>

<!DOCTYPE html>
<html>
<head>
<title>Home Page</title>
<meta charset="UTF-8">
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="font-awesome-4.1.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script type="text/javascript">
$(function() 
{
var labels = [];
var values = [];
var count = 0;
<?php foreach( $dataReader as $row ) { ?>
var v1 = "index.php?r=profile&staffID=";
var v2 = "<?=$row['formalemail']?>";
var temp = v1 + v2;
labels.push("<?=$row['name']?>");
values.push(temp);
count++;
<?php }
?>


var source = [];
for(var i=0; i< count; i++)  {
    source.push({value: values[i] , label: labels[i] });
}

		
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

<script type="text/javascript">
	history.pushState(null, null, document.title);
	window.addEventListener('popstate', function () {
	    history.pushState(null, null, document.title);
	});
	</script>
</head>
<body>
	<!-- WRAPPER STARTS HERE-->
	<div class='wrapper1'>
		<?php
		$ch = curl_init ( "http://localhost/basic/web/index.php?r=student/getstudentname" );
		curl_setopt ( $ch, CURLOPT_POST, 1 );
		curl_setopt ( $ch, CURLOPT_POSTFIELDS, "studentID=" . $session->get ( "studentID" ) );
		curl_setopt ( $ch, CURLOPT_RETURNTRANSFER, true );
		$studentName = curl_exec ( $ch );
		curl_close ( $ch );
		?>
		<div
			style="color: white; font: bold; font-size: 30px; left: 10px; padding-left: 50px; font-style: italic;">
			<img style="width: 80px; size: 200px;" src="img/t2.png">&nbsp;&nbsp;&nbsp;<?=$studentName ?></div>
	</div>
	<center>
		<div style="background: url('img/background.png') repeat center;">
			<table border="0.5">
				<tr>
					<td><label for="tags"></label> <input id="tags"
						style="width: 300px; height: 30px;"></td>
					<td><img src="img/search.png" alt="Search" width="50" height="50"></td>
				</tr>
			</table>
		</div>
	</center>
	<section class='aboutus' id='about'>
		<div style="float: left;" id="button">
			<ul>
				<li><a href="index.php?r=studenthome">Home</a></li>
				<li><a href="index.php?r=studentabout" title="about us">About</a></li>
				<li><a href="index.php?r=site" title="exit application">Logout</a></li>
			</ul>
		</div>
	</section>
	<?php
	$session = Yii::$app->session;
	$session->open ();
	$studentID = $session->get ( "studentID" );
	$ch = curl_init ( "http://localhost/basic/web/index.php?r=post/showfollowersposts" );
	curl_setopt ( $ch, CURLOPT_POST, 1 );
	curl_setopt ( $ch, CURLOPT_POSTFIELDS, "studentID=" . $studentID );
	curl_setopt ( $ch, CURLOPT_RETURNTRANSFER, true );
	$result = curl_exec ( $ch );
	curl_close ( $ch );
	$allFollwers = json_decode ( $result, true );
	?>
	<div style="background: url('img/background.png') repeat center;">
	<?php
	
	for($i = 0; $i < sizeof ( $allFollwers ); $i ++) {
	?>
	<div style="background: url('img/background.png') repeat center;">
	<?php 
		for($j = (sizeof($allFollwers [$i])  - 1) ; $j >= 0 ; $j--) {
			?>
			<center>
			<div style="background: url('img/background.png') repeat center;">
			
			
			<div id="button2">
				<ul>
			<?php
			
$var = $allFollwers [$i] [$j] ["staffFormalEmail"];
			$path = "index.php?r=profile&staffID=" . $var;
			$today = date ( "F j, Y, g:i a" );
			$dateTime = ( string ) $today;
			?> 
			 <div>
						<img src="img/p1.png" style="float: left; width: 60px;" /><a
							href='<?=$path ?>'> <?=$allFollwers[$i][$j]["Owner"];?></a>
					</div>
				</ul>
				<div style="background: url('img/background.png') repeat center;">
				<br>
				</div>
				<ul>
					<li><?=$allFollwers[$i][$j]["Content"];?></li>
					<li><?=$allFollwers[$i][$j]["Time"];?></li>
				</ul>
			</div>
		
		</div>	
		</center>
			<?php
		}
		?>
		<div style="background: url('img/background.png') repeat center;">
		<br>
		</div>
			</div>

	<?php
}
	?>
	</div>

</body>
</html>