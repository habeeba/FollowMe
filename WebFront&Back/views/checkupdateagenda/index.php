<?php 
$session = Yii::$app->session;
$session->open();
$agendaID = $session->get('agendaID');
$myAdenda = $session->get('myOutput');

$slotNum = $_POST['slotnum'];
$max = $_POST['max'];
$content = $_POST['content'];
$bookCount = $_POST['bookCount'];

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



$slotNumArray = json_encode($slotNumArray);
$maxArray = json_encode($maxArray);
$contentArray = json_encode($contentArray);
if($session->get("type") != "temp")
{
	$ch = curl_init("http://localhost/basic/web/index.php?r=agenda/update-agenda");
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, "agendaID=".$agendaID."&data=".$contentArray."&slotbuff=".$maxArray."&slotnum=".$slotNumArray);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$output = curl_exec ($ch);
	curl_close($ch);
	
	header("Location: http://localhost/basic/web/index.php?r=showagenda"); /* Redirect browser */
	exit();
}
else 
{
	$ch = curl_init("http://localhost/basic/web/index.php?r=agenda/onupdate");
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, "agendaID=".$agendaID."&data=".$contentArray."&slotbuff=".$maxArray."&slotnum=".$slotNumArray);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$output = curl_exec ($ch);
	curl_close($ch);
	
	header("Location: http://localhost/basic/web/index.php?r=showexception"); /* Redirect browser */
	exit();
}





?>
