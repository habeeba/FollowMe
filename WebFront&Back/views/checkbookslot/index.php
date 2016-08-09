<?php 
$maxBookers = $_POST['maxBookers'];
$slotID = $_POST['slotID'];
$bookCount = $_POST['bookCount'];
$content = $_POST['content'];

$session = Yii::$app->session;
$session->open();
$formalemail = $session->get('staffFormalEmail');

$day = date('w');
$week_start = date('Y-m-d', strtotime('-'.($day+1).' days'));

$ch = curl_init("http://localhost/basic/web/index.php?r=agenda/get-agenda-info");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, "owner=".$formalemail."&date=".$week_start);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$server_output = curl_exec ($ch);
curl_close($ch);
$output = array();
$output = json_decode($server_output , true);
$agendaID = $output['agendaID'];
$owner = $output['owner'];
$lastUpdate = $output['lastUpdate'];
$type = $output['type'];
$session->set('agendaID',$agendaID);
$session->set('owner', $owner);
$session->set('lastUpdate', $lastUpdate);
$session->set('type', $type);


$output1 = array();
$ch1 = curl_init("http://localhost/basic/web/index.php?r=agenda/show-agenda");
curl_setopt($ch1, CURLOPT_POST, 1);
curl_setopt($ch1, CURLOPT_POSTFIELDS, "agendaID=".$agendaID);
curl_setopt($ch1, CURLOPT_RETURNTRANSFER, true);
$server_output1 = curl_exec ($ch1);
curl_close($ch1);
$output1 = json_decode($server_output1 , true);


$output2 = array();
$ch2 = curl_init("http://localhost/basic/web/index.php?r=book/create-book");
curl_setopt($ch2, CURLOPT_POST, 1);
curl_setopt($ch2, CURLOPT_POSTFIELDS, "slotID=".$slotID."&Date=".$lastUpdate."&content=".$content."&booker=".$session->get("studentID"));
curl_setopt($ch2, CURLOPT_RETURNTRANSFER, true);
$server_output2 = curl_exec ($ch2);
curl_close($ch2);
$output2 = json_decode($server_output2 , true);

header("Location: http://localhost/basic/web/index.php?r=bookslot"); /* Redirect browser */
exit();