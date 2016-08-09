<?php
$postID = $_GET ['postID'];
$ch = curl_init("http://localhost/basic/web/index.php?r=post/deletepost");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, "postID=".$postID);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$server_output = curl_exec ($ch);
curl_close($ch);
if ($server_output == '{"status":"Ok"}')
{
	header("Location: http://localhost/basic/web/index.php?r=staffhome"); /* Redirect browser */
	exit();
}
else
{
	echo "Faildddddddddddddd";
}


?>
