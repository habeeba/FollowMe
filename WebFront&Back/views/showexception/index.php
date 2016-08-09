<?php 
$session = Yii::$app->session;
$session->open();
$owner = $session->get('staffFormalEmail');

$ch = curl_init("http://localhost/basic/web/index.php?r=agenda/get-exceptions");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, "owner=".$owner);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$server_output = curl_exec ($ch);
curl_close($ch);
$output = json_decode($server_output , true) ;
?>

<html>
<head>
<title>Update Agenda Page</title>
<meta charset="UTF-8">
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
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
<br>
<form action="index.php?r=showexception" method="post">
  <select name="date">
<?php 
for($i = 0; $i < sizeof($output); $i++)
{
	?>
	<option onselect="index.php?r=second" value='<?=$output[$i]['agendaID']?>'><?=$output[$i]['lastUpdate']?></option>
<?php }?>
</select>
<br><br>
<button class="button1" type="submit">Select Date</button>
</form>

<?php 
if(isset($_POST['date']))
		{
$agendaID = $_POST['date'];
$ch = curl_init("http://localhost/basic/web/index.php?r=agenda/show-agenda");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, "agendaID=".$agendaID);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$server_output = curl_exec ($ch);
curl_close($ch);
$output = json_decode($server_output , true);
$session = Yii::$app->session;
$session->open();
$session->set('exceptionAgendaID',$agendaID);

?>


<center>
<form action="index.php?r=onupdate" method="post">
		<input type="hidden" name="_csrf" value="<?=Yii::$app->request->getCsrfToken()?>" /> <br>
		<button style="margin-left: 120px;" class="mybutton1"  type="submit">Update</button>
</form>
<br><br>

<table border="5" style="width: 1000px; height: 500px;">
<tr style="background-color: #1C54FF; color: white;"><td>Day/Slot</td><td>8-9:30</td><td>9:30-11</td><td>11:15-12:45</td><td>12:45-2:15</td><td>2:30-4</td><td>4-5:30</td></tr>
<?php 
$count = 0;
for($i = 0; $i < 6; $i++)
{	?>
<tr>
<?php 
	for($j = 0 ; $j < 6; $j++)
	{
		if($j == 0)
		{
			if($i == 0)
			{?>
				<td style="background-color: #1C54FF; color: white;">Saturday</td>
			<?php }
			elseif ($i == 1)
			{?>
				<td style="background-color: #1C54FF; color: white;">Sunday</td>
			<?php }
			elseif ($i == 2)
			{?>
				<td style="background-color: #1C54FF; color: white;">Monday</td>
			<?php }
			elseif ($i == 3)
			{?>
				<td style="background-color: #1C54FF; color: white;">Teusday</td>
			<?php }
			elseif ($i == 4)
			{?>
				<td style="background-color: #1C54FF; color: white;">Wednesday</td>
			<?php }
			else 
			{?>
				<td style="background-color: #1C54FF; color: white;">Thursday</td>
			<?php }?>
			<td><?=$output[$count]['maxBookers']?>
						<br>
						<?=$output[$count]['content']?></td>
						<?php 
		}
		elseif($i == $j)
		{?>
			<td><?=$output[$count]['maxBookers']?>
			<br>
			<?=$output[$count]['content']?></td>
		<?php }
		else
		{?>
			<td><?=$output[$count]['maxBookers']?>
			<br>
			<?=$output[$count]['content']?></td>
		<?php }
		$count++;
		}?>
		</tr>
<?php }?>

</table>
</center>
<?php }

?>
<br> <br>

<form action="index.php?r=staffhome" method="post">
		<input type="hidden" name="_csrf" value="<?=Yii::$app->request->getCsrfToken()?>" /> <br>
		<button style="margin-left: 120px;" class="mybutton1"  type="submit">Back</button>
</form>


</section>
</body>
</html>