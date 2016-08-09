<?php 
$session = Yii::$app->session;
$session->open();


$formalemail = $session->get('staffFormalEmail');
$day = date('w');
$week_start = date('Y-m-d', strtotime('-'.($day+1).' days'));
$ch = curl_init("http://localhost/basic/web/index.php?r=agenda/get-agenda-info");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, "owner=".$formalemail."&date=".$week_start);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$server_output = curl_exec ($ch);
curl_close($ch);
$output = array();
$output = json_decode($server_output , true);
$agendaID = $output['agendaID'];


$ch = curl_init("http://localhost/basic/web/index.php?r=agenda/show-agenda");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, "agendaID=".$agendaID);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$server_output = curl_exec ($ch);
curl_close($ch);
$output = json_decode($server_output , true); 
echo sizeof($output);
?>

<!DOCTYPE h3 PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<title>Book Page</title>
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
	function checkBook(form)
	{
		var content = form.content.value;
		var t = "You has booked in " + content;
     	alert(t);
       	return true;
	}
	</script>
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

<section class='aboutus' id='about'>
<center>
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
			<td>
			
			<?php 
			if($output[$count]['maxBookers'] != 0 && $output[$count]['maxBookers'] != $output[$count]['bookCount'])
			{?>
			<form action="index.php?r=checkbookslot" method="post"  onsubmit="return checkBook(this);">
			<input type="hidden" name="_csrf"
			value="<?=Yii::$app->request->getCsrfToken()?>" />
			<input type="hidden" name="maxBookers" value="<?=$output[$count]['maxBookers']?>"/>
			<input type="hidden" name="slotID" value="<?=$output[$count]['slotID']?>"/>
			<input type="hidden" name="bookCount" value="<?=$output[$count]['bookCount']?>"/>
			Enter Subject <input type="text" style="width: 130px;" name="content"/>
			<br>
				<button style="background-color: blue; color: white;" type="submit"><?=$output[$count]['content']?></button>
				</form>
			<?php }
			else 
			{?>
				<button type="submit"><?=$output[$count]['content']?></button>
			<?php }
				
			?>
			</td>
						<?php 
		}
		elseif($i == $j)
		{?>
			<td>
			<?php 
			if($output[$count]['maxBookers'] != 0 && $output[$count]['maxBookers'] != $output[$count]['bookCount'])
			{?>
			<form action="index.php?r=checkbookslot" method="post"  onsubmit="return checkBook(this);">
			<input type="hidden" name="_csrf"
			value="<?=Yii::$app->request->getCsrfToken()?>" />
			<input type="hidden" name="maxBookers" value="<?=$output[$count]['maxBookers']?>"/>
			<input type="hidden" name="slotID" value="<?=$output[$count]['slotID']?>"/>
			<input type="hidden" name="bookCount" value="<?=$output[$count]['bookCount']?>"/>
			Enter Subject <input type="text" style="width: 130px;" name="content"/>
			<br>
				<button style="background-color: blue; color: white;" type="submit"><?=$output[$count]['content']?></button>
				</form>
			<?php }
			else 
			{?>
				<button type="submit"><?=$output[$count]['content']?></button>
			<?php }
				
			?>
			</td>
		<?php }
		else
		{?>
			<td>
			<?php 
			if($output[$count]['maxBookers'] != 0 && $output[$count]['maxBookers'] != $output[$count]['bookCount'])
			{?>
			<form action="index.php?r=checkbookslot" method="post"  onsubmit="return checkBook(this);">
			<input type="hidden" name="_csrf"
			value="<?=Yii::$app->request->getCsrfToken()?>" />
			<input type="hidden" name="maxBookers" value="<?=$output[$count]['maxBookers']?>"/>
			<input type="hidden" name="slotID" value="<?=$output[$count]['slotID']?>"/>
			<input type="hidden" name="bookCount" value="<?=$output[$count]['bookCount']?>"/>
			Enter Subject <input type="text" style="width: 130px;" name="content"/>
			<br>
				<button style="background-color: blue; color: white;" type="submit"><?=$output[$count]['content']?></button>
				</form>
			<?php }
			else 
			{?>
				<button type="submit"><?=$output[$count]['content']?></button>
			<?php }
				
			?>
			</td>
		<?php }
		$count++;
		}?>
		</tr>
<?php }?>

</table>
</center>
<br>
<form action="index.php?r=studenthome" method="post">
		<input type="hidden" name="_csrf" value="<?=Yii::$app->request->getCsrfToken()?>" /> <br>
		<button style="margin-left: 120px;" class="mybutton1"  type="submit">Back</button>
</form>

</section>
</body>
</html>