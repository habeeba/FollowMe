<?php

$slotNum = $_POST['slotnum'];
$max = $_POST['max'];
$content = $_POST['content'];
$bookCount = $_POST['bookCount'];
$session = Yii::$app->session;
$session->open();
$agendaID = $session->get('agendaID');
$owner = $session->get('staffFormalEmail');

$myAdenda = $session->get("myOutput");

$contentArray = array();
$maxArray = array();
$slotNumArray = array();
$count=0;
for($i = 0; $i < 36; $i++)
{
	if($myAdenda[$i]["content"] != $content[$i])
	{
		//if($myAdenda[$i]["maxBookers"] > $bookCount[$i])
		//{
		$slotNumArray[$count] = $i; 
		$contentArray[$count] = $content[$i];
		$maxArray[$count] = $max[$i];
		$count++;
		//}
	}
	else if($myAdenda[$i]["maxBookers"] != $max[$i])
	{
		//if($myAdenda[$i]["maxBookers"] > $bookCount[$i])
		//{
		$slotNumArray[$count] = $i;
		$contentArray[$count] = $content[$i];
		$maxArray[$count] = $max[$i];
		$count++;
	//}
	}
}


$week_start = $session->get("selectedDate");
$slotNumArray = json_encode($slotNumArray);
$maxArray = json_encode($maxArray);
$contentArray = json_encode($contentArray);

$day = date('w');
$week_start1 = date('Y-m-d', strtotime('-'.($day+1).' days'));


$ch = curl_init("http://localhost/basic/web/index.php?r=agenda/create-exception");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, "owner=".$owner."&lastUpdate=".$week_start."&data=".$contentArray."&slotbuff=".$maxArray."&slotnum=".$slotNumArray);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$server_output = curl_exec ($ch);
curl_close($ch);
$output = json_decode($server_output , true);

if($week_start == $week_start1)
{
	$ch = curl_init("http://localhost/basic/web/index.php?r=agenda/get-agenda-info");
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, "owner=".$owner."&date=".$week_start);
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
	header("Location: http://localhost/basic/web/index.php?r=showexception"); /* Redirect browser */
	exit();
}
else 
{
	header("Location: http://localhost/basic/web/index.php?r=showexception"); /* Redirect browser */
	exit();
}


?>