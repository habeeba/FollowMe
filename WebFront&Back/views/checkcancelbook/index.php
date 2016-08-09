<?php 
$maxBookers = $_POST['maxBookers'];
$slotID = $_POST['slotID'];
$bookCount = $_POST['bookCount'];
$session = Yii::$app->session;
$session->open();
$studentID = $session->get('studentID');

$day = date('w');
$week_start = date('Y-m-d', strtotime('-'.($day+1).' days'));

$ch = curl_init("http://localhost/basic/web/index.php?r=book/cancel-book");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, "slotID=".$slotID."&Date=".$week_start."&booker=".$studentID);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$server_output = curl_exec ($ch);
curl_close($ch);

header("Location: http://localhost/basic/web/index.php?r=studenthome"); /* Redirect browser */
exit();
?>
