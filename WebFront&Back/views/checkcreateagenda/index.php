<?php 
$session = Yii::$app->session;
$session->open();
$owner = $session->get('signupStaffFormalEmail');

$max = $_POST['max'];
$content = $_POST['content'];
$max = json_encode($max);
$content = json_encode($content);
$day = date('w');
$week_start = date('Y-m-d', strtotime('-'.($day+1).' days'));


$ch = curl_init("http://localhost/basic/web/index.php?r=agenda/create-agenda");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, "owner=".$owner."&data=".$content."&slotbuff=".$max."&date=".$week_start);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_exec ($ch);
curl_close($ch);

header("Location: http://localhost/basic/web/index.php?r=site"); /* Redirect browser */
exit();

?>