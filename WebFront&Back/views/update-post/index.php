<?php
$postID = $_GET["postID"];
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
<!DOCTYPE h3 PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<title>Update Post</title>
<script type="text/javascript">
	history.pushState(null, null, document.title);
	window.addEventListener('popstate', function () {
	    history.pushState(null, null, document.title);
	});
	</script>
</head>
<body style="background-color: highlight;">

<form action="index.php?r=checkupdatepost" method="post">
<input type="hidden" name="postID" value="<?= $allFollwers [$postID] ["PostID"]; ?>"/>
<table border="1">
			<tr>
				<td>Edit Your Post</td>
			</tr>
			<tr>
				<td><textarea name="newContent" rows="10" cols="80"><?= $allFollwers [$postID] ["Content"]; ?></textarea></td>
			</tr>
		</table>
		<input type="hidden" name="_csrf"
			value="<?=Yii::$app->request->getCsrfToken()?>" /> <br>
		<br>
		<center>
		<input
				style="color: fuchsia; height: 50px; width: 100px; font: bold; font-size: 15px"
				type="submit" value="Update Post" />
		</center>

</form>

</body>
</html>


