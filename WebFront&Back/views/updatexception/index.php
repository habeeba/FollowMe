<?php 
$session = Yii::$app->session;
$session->open();
$exceptionAgendaID = $session->get('exceptionAgendaID');
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
		//echo "---> ".$i;
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
		//echo "---> ".$i;
	}
}



$slotNumArray = json_encode($slotNumArray);
$maxArray = json_encode($maxArray);
$contentArray = json_encode($contentArray);

	$ch = curl_init("http://localhost/basic/web/index.php?r=agenda/update-exception-agenda");
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, "agendaID=".$exceptionAgendaID."&data=".$contentArray."&slotbuff=".$maxArray."&slotnum=".$slotNumArray);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$output = curl_exec ($ch);
	curl_close($ch);
	
	//echo $output;
	
	header("Location: http://localhost/basic/web/index.php?r=showexception"); /* Redirect browser */
	exit();






?>

