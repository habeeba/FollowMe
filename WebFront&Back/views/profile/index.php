<?php
use app\models\Follow;
$session = Yii::$app->session;
$session->open ();
if(!isset($_GET["done"]))
{
	$staffFormalEmail = $_GET ["staffID"];
	$session->set ( 'staffFormalEmail', $staffFormalEmail );
}
?>


<!DOCTYPE h3 PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<title>Profile Page</title>
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
<script type="text/javascript">
function change() 
{
	document.getElementById("follow").value="Following";
}
function change1() 
{
		document.getElementById("follow").value="Follow";
		document.getElementById('unFollow').style.visibility = 'hidden';
}

</script>

</head>
<body>
<div class='wrapper1'>
		<?php 
		$ch = curl_init("http://localhost/basic/web/index.php?r=staff/getstaffname");
			curl_setopt($ch, CURLOPT_POST, 1);
			curl_setopt($ch, CURLOPT_POSTFIELDS, "staffFormalEmail=".$session->get('staffFormalEmail'));
			curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
			$server_output = curl_exec ($ch);
			curl_close($ch);
		?>
		<div style="color: white; font: bold; font-size: 30px; left: 10px; padding-left: 50px; font-style: italic;"><img style="width: 80px; size: 200px;" src="img/t2.png">&nbsp;&nbsp;&nbsp;<?=$server_output ?></div> 
		
<?php
$session = Yii::$app->session;
$session->open ();
$studentID = $session->get ("studentID");
$staffID = $session->get ("staffFormalEmail");
$checkFollow = "pinding";
if (Follow::find()->where(['studentID' => $studentID , 'staffID' => $staffID])->one())
{
	$checkFollow = "Followed";
}
else
{
	$checkFollow = "NotFollowed";
}
if ($checkFollow == "NotFollowed") 
{
?>
	<form action="index.php?r=checkfollow" method="post" onsubmit="change()" style="float: center;">
		<input type="hidden" name="staffFormalEmail" value="<?=$session->get('staffFormalEmail'); ?>" />
			<input type="hidden" name="c" value=add"/>
			<div style="float: left; margin-left: 30px;">
			</div>
			<input style="margin-right: 20px; margin-bottom:500px; float:right; padding-top:0px;
padding-bottom:0px; 
color: blue; 
height: 40px; 
width: 80px; 
font: bold; 
font-size: 15px;" onchange="index.php?r=follow/add" class="mybutton1" 
			type="submit" id="follow" value="Follow" />
	</form>
	
<?php
} 
else
{
	?>
	
	<form action="" method="post" style="float: center;">
		<input type="hidden" name="staffFormalEmail"
			value="<?=$session->get('staffFormalEmail');?>" />
			<input onclick="return change();"
			class="mybutton1" style="margin-right: 20px; margin-bottom:500px; float:right; padding-top:0px;
padding-bottom:0px; 
color: blue; 
height: 40px; 
width: 80px; 
font: bold; 
font-size: 15px;" type="submit" id="follow" value="Following" />
	</form>


	<form action="index.php?r=checkunfollow" method="post" onsubmit="change()">
		<input type="hidden" name="staffFormalEmail"
			value="<?=$session->get('staffFormalEmail'); ?>" /> <input
			onclick="return change1();"
			style="margin-right: 20px; margin-bottom:500px; float:right; padding-top:0px;
padding-bottom:0px; 
color: blue; 
height: 40px; 
width: 80px; 
font: bold; 
font-size: 15px;"
			type="submit" id="unFollow" value="UnFollow" />
	</form>
	
<?php
}
?>
<form action="index.php?r=bookslot" method="post" onsubmit="change()" style="margin-right: 10px; margin-bottom:500px; float:right;">
			<input type="hidden" name="_csrf"
			value="<?=Yii::$app->request->getCsrfToken()?>" />
			<button style="padding-top:0px;
padding-bottom:0px; 
color: blue; 
height: 40px; 
width: 80px; 
font: bold; 
font-size: 15px;" type="submit">Book</button>
	</form>
	<form action="index.php?r=cancelbook" method="post" onsubmit="change()" style="margin-right: 10px; margin-bottom:500px; float:right;">
			<input type="hidden" name="_csrf"
			value="<?=Yii::$app->request->getCsrfToken()?>" />
			<button style="padding-top:0px;
padding-bottom:0px; 
color: blue; 
height: 60px; 
width: 90px; 
font: bold; 
font-size: 15px;" type="submit">Cancel Book</button>
	</form>
</div>
<section class='aboutus' id='about'>
	<br>	
<br>

<?php
$session = Yii::$app->session;
$session->open ();
$owner = $session->get ( 'staffFormalEmail' );
$ch = curl_init("http://localhost/basic/web/index.php?r=post/getmyposts");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, "staffFormalEmail=".$owner);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$result = curl_exec ($ch);
curl_close($ch);
$allFollwers = json_decode ( $result, true );
$path = "index.php?r=profile&staffID=".$owner;
?>
<h1>Posts</h1>
<br>
<?php 
for($i = sizeof ( $allFollwers ) - 1 ; $i >= 0; $i--) 
{
	?>
	<div style="background: url('img/background.png') repeat center;">
	<center>
	
	<div id="button2">
	<ul>
	<div><img src="img/p1.png" style="float:left; width: 60px;"/><a href='<?=$path ?>'> <?=$server_output?></a></div>
			</ul>
			</div>
			<ul>
			    <li><?=$allFollwers[$i]["Content"];?></li>
			    <li><?=$allFollwers[$i]["Time"];?></li>
			</ul>
			
			</center>
			</div>
<?php }
?>
<br>
<div style="background: url('img/background.png') repeat center;">
<center>
	<form action="index.php?r=studenthome" method="post" onsubmit="change()" style="float: center;">
			<input type="hidden" name="_csrf"
			value="<?=Yii::$app->request->getCsrfToken()?>" />
			<button style="padding-top:0px;
padding-bottom:0px; 
color: blue; 
height: 40px; 
width: 80px; 
font: bold; 
font-size: 15px;" type="submit">Back</button>
	</form>
</center>
</div>
</section>
</body>
</html> 



