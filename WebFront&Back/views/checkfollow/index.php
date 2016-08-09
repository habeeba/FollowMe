<?php

$session = Yii::$app->session;
$session->open ();
$studentID = $session->get ("studentID");
$staffID = $session->get ("staffFormalEmail");
$ch = curl_init("http://localhost/basic/web/index.php?r=follow/add");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, "studentID=".$studentID."&staffFormalEmail=".$staffID);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$result = curl_exec ($ch);
curl_close($ch);
header("Location: http://localhost/basic/web/index.php?r=profile&done=ok"); /* Redirect browser */
exit();

?>
