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
<div class='wrapper1'>
</div>
	<section class='aboutus' id='about'>
	<div style="float: left;" id="button">
  		<ul>
		    <li><a href="index.php?r=staffhome">Home</a></li>
		    <li><a href="index.php?r=showagenda" title="display your agenda">Show Agenda</a></li>
		    <li><a href="index.php?r=updateagenda" title="update your agenda">Update Agenda</a></li>
		    <li><a href="index.php?r=date" title="create new exception agenda for specific week">Create Exception</a></li>
		    <li><a href="index.php?r=showexception" title="display your exception agenda">Show Exception</a></li>
		    <li><a href="index.php?r=staffabout" title="about us">About</a></li>
		    <li><a href="index.php?r=bookv" title="exit application">Logout</a></li>
		</ul>
	</div>	
	<br>

	<h2 style="color: blue; float: left; font-size: 25px;">Create Post</h2>
	<br>
	<br>
	<br>
	<form action="index.php?r=checkwritepost" method="post">
		<table border="1">
			<tr>
				<td><textarea name="content" rows="8" cols="80" onfocus="if (this.value=='Enter Your Post here') this.value = ''" >Enter Your Post here</textarea></td>
			</tr>
		</table>
		<input type="hidden" name="_csrf" value="<?=Yii::$app->request->getCsrfToken()?>" /> <br>
		<button style="margin-left: 120px;" class="mybutton1"  type="submit">Post</button>
	</form>
	<br><br><br>
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
<h1>My Posts</h1>
<br>
<center>
<table border="0.5">
<?php 
for($i = sizeof ( $allFollwers ) - 1 ; $i >= 0; $i--)
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


</center>	
	</section>
	
</body>
</html>