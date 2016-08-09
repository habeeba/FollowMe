<?php 
$contentSelected = $_POST['contentSelected'];
$slotID = $_POST['slotID'];
$session = Yii::$app->session;
$session->open();

$day = date('w');
$week_start = date('Y-m-d', strtotime('-'.($day+1).' days'));

$contentSelected = json_encode($contentSelected);

echo $contentSelected;

$ch = curl_init("http://localhost/basic/web/index.php?r=book/d-cancel-book");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, "slotID=".$slotID."&Date=".$week_start."&booker=".$contentSelected);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$server_output = curl_exec ($ch);
curl_close($ch);
$output = json_decode($server_output , true);

header("Location: http://localhost/basic/web/index.php?r=staffhome"); /* Redirect browser */
exit();
?>

