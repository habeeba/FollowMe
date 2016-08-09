<?php
$postID = $_POST ['postID'];
$newContent = $_POST ['newContent'];
$ch = curl_init("http://localhost/basic/web/index.php?r=post/updatepost");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, "postID=".$postID."&newContent=".$newContent);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$server_output = curl_exec ($ch);
curl_close($ch);
header("Location: http://localhost/basic/web/index.php?r=staffhome"); /* Redirect browser */
exit();
?>
