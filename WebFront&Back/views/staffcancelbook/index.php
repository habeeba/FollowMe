<?php 
$slotID = $_POST['slotID'];
$session = Yii::$app->session;
$session->open();
$studentID = $session->get('studentID');

$day = date('w');
$week_start = date('Y-m-d', strtotime('-'.($day+1).' days'));

$ch = curl_init("http://localhost/basic/web/index.php?r=book/get-book-content");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, "slotID=".$slotID."&date=".$week_start);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$server_output = curl_exec ($ch);
curl_close($ch);
$output = json_decode($server_output , true);
?>

<!DOCTYPE html>
<html>
<head>
<title>Cancel Book Page</title>
<meta charset="UTF-8">
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<link href='../fullcalendar.css' rel='stylesheet' />
<script type="text/javascript">
	history.pushState(null, null, document.title);
	window.addEventListener('popstate', function () {
	    history.pushState(null, null, document.title);
	});
	</script>
</head>
<body>
<div class='wrapper1'>
</div>
<div id='calendar'></div>


<section class='aboutus' id='about'>
<center>
<?php if(sizeof($output) == 0)
{?>
	<form action="index.php?r=staffhome" method="post">
	<input type="hidden" name="_csrf" value="<?=Yii::$app->request->getCsrfToken()?>" /> <br>
	<button style="margin-left: 120px;" class="mybutton1"  type="submit">Back</button>
	</form>
<?php } 
else
{
?>
<form action="index.php?r=finish" method="post">
<?php
for($i = 0; $i < sizeof($output); $i++)
{
	?>
	 <input type="checkbox" name="contentSelected[]" value="<?=$output[$i]["studentID"]?>"/> <?=$output[$i]["content"]?>
	<br>
<?php }

?>
<input type="hidden" name="slotID" value="<?=$slotID ?>"/>
<button>Cancel Book</button>
	</form>
	<br>
</center>
<br>
<form action="index.php?r=staffhome" method="post">
		<input type="hidden" name="_csrf" value="<?=Yii::$app->request->getCsrfToken()?>" /> <br>
		<button style="margin-left: 120px;" class="mybutton1"  type="submit">Back</button>
</form>
<?php 
}
?>
</section>
</body>
</html>

