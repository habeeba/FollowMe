<?php

$date = $_POST['date'];

$session = Yii::$app->session;
$session->open();
$agendaID = $session->get('agendaID');
$owner = $session->get('owner');
$session->set("selectedDate" , $date);

$ch = curl_init("http://localhost/basic/web/index.php?r=agenda/show-agenda");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, "agendaID=".$agendaID);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$server_output = curl_exec ($ch);
curl_close($ch);
$output = json_decode($server_output , true) ;

$session->set("myOutput" , $output);

$ch1 = curl_init("http://localhost/basic/web/index.php?r=agenda/is-exception");
curl_setopt($ch1, CURLOPT_POST, 1);
curl_setopt($ch1, CURLOPT_POSTFIELDS, "owner=".$owner."&date=".$date);
curl_setopt($ch1, CURLOPT_RETURNTRANSFER, true);
$server_output1 = curl_exec ($ch1);
curl_close($ch1);

echo $server_output1;
if($server_output1 != "[]")
{
	header("Location: http://localhost/basic/web/index.php?r=showexception"); /* Redirect browser */
	exit();
}


?>


<html>
<head>
<title>Create Exception Page</title>

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

<form action="index.php?r=checkcreateexception" method="post">
<table border="5">
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
			<?php }
			
			?>
			
			<td>
			<input type="hidden" name="slotnum[]" value="<?=$count?>"/>
			<input type="text" name="bookCount[]" value="<?=$output[$count]['bookCount']?>" readonly="readonly" />
			<br>
			<input type="text" name="max[]" value="<?=$output[$count]['maxBookers']?>" onfocus="if (this.value=='<?=$output[$count]['maxBookers']?>') this.value = ''"/>
						<br>
			<input type="text" name="content[]" value="<?=$output[$count]['content']?>" onfocus="if (this.value=='<?=$output[$count]['content']?>') this.value = ''"/></td>
			<?php 
		}
		elseif($i == $j)
		{?>
		<td>
		<input type="hidden" name="slotnum[]" value="<?=$count?>"/>
		<input type="text" name="bookCount[]" value="<?=$output[$count]['bookCount']?>" readonly="readonly" />
			<br>
			<input type="text" name="max[]" value="<?=$output[$count]['maxBookers']?>" onfocus="if (this.value=='<?=$output[$count]['maxBookers']?>') this.value = ''"/>
						<br>
			<input type="text" name="content[]" value="<?=$output[$count]['content']?>" onfocus="if (this.value=='<?=$output[$count]['content']?>') this.value = ''"/></td>
		<?php }
		else
		{?>
		<td>
		<input type="hidden" name="slotnum[]" value="<?=$count?>"/>
		<input type="text" name="bookCount[]" value="<?=$output[$count]['bookCount']?>" readonly="readonly" />
			<br>
			<input type="text" name="max[]" value="<?=$output[$count]['maxBookers']?>" onfocus="if (this.value=='<?=$output[$count]['maxBookers']?>') this.value = ''"/>
						<br>
			<input type="text" name="content[]" value="<?=$output[$count]['content']?>" onfocus="if (this.value=='<?=$output[$count]['content']?>') this.value = ''"/></td>
		<?php }
		$count++;
		}?>
		</tr>
<?php }?>
</table>

<br>
<br>
<button class="mybutton2"  type="submit">Create Exception</button>
</form>
<br>
<form action="index.php?r=staffhome" method="post">
		<input type="hidden" name="_csrf" value="<?=Yii::$app->request->getCsrfToken()?>" /> <br>
		<button  class="mybutton2"  type="submit">Back</button>
</form>
</section>
</body>

</html>

