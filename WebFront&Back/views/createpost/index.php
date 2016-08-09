<?php 
?>
<!DOCTYPE h3 PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<title>Home Page</title>
<script type="text/javascript">
	history.pushState(null, null, document.title);
	window.addEventListener('popstate', function () {
	    history.pushState(null, null, document.title);
	});
	</script>
</head>
<body style="background-color: highlight;">
	<br>
	<br>
	<br>
	<form style="float: right;" action="index.php?r=site" method="post">
		<input type="hidden" name="_csrf"
			value="<?=Yii::$app->request->getCsrfToken()?>" /> <input
			style="color: fuchsia; height: 40px; width: 150px; font: bold; font-size: 15px"
			type="submit" value="Logout" />
	</form>
	<br>
	
	
	<form style="float: right;" action="index.php?r=site" method="post">
		<input type="hidden" name="_csrf"
			value="<?=Yii::$app->request->getCsrfToken()?>" /> <input
			style="color: fuchsia; height: 40px; width: 150px; font: bold; font-size: 15px"
			type="submit" value="Create Agenda" />
	</form>
	<br>
	<form style="float: right;" action="index.php?r=createexception" method="post">
		<input type="hidden" name="_csrf"
			value="<?=Yii::$app->request->getCsrfToken()?>" /> <input
			style="color: fuchsia; height: 40px; width: 150px; font: bold; font-size: 15px"
			type="submit" value="Create Exception" />
	</form>
	<br>
	<form style="float: right;" action="index.php?r=updateagenda" method="post">
		<input type="hidden" name="_csrf"
			value="<?=Yii::$app->request->getCsrfToken()?>" /> <input
			style="color: fuchsia; height: 40px; width: 150px; font: bold; font-size: 15px"
			type="submit" value="Update Agenda" />
	</form>
	<br>
	<form style="float: right;" action="index.php?r=showagenda" method="post">
		<input type="hidden" name="_csrf"
			value="<?=Yii::$app->request->getCsrfToken()?>" /> <input
			style="color: fuchsia; height: 40px; width: 150px; font: bold; font-size: 15px"
			type="submit" value="Show Agenda" />
	</form>
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
?>
<table border="0.5">
<?php 
for($i = 0; $i < (sizeof ( $allFollwers )); $i++)
{
	?>
<tr>
	<td><?= $allFollwers [$i] ["Content"];?></td>
	<td><?php echo "                                                 ";?> </td>
	<td>
	<details>
  		<summary></summary>
  			<a href="index.php?r=update-post&postID=<?=$i ?>">Update Post</a>
  			<br>
  			<a href="index.php?r=checkdeletepost&postID=<?= $allFollwers [$i] ["PostID"]; ?>">Delete Post</a>
	</details>
	</td>
	</tr>
	<tr>
	<td><?=$allFollwers [$i] ["Time"];?></td>
	<td>
	<?="<br><br><br>";?>
	</td>
</tr>
<?php }?>
</table>

</body>
</html>