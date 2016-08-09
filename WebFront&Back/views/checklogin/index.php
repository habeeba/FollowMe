<?php
//$userType = $_POST["userType"];
$session = Yii::$app->session;
$session->open ();
$userType = $session->get("userType");
if ($userType == "staff")
{
	$formalemail = $_POST ['staffFormalEmail'];
	$pass = $_POST ['staffPassword'];
	
	if($formalemail == "" || $pass == "")
	{
		header("Location: http://localhost/basic/web/index.php?r=signup"); /* Redirect browser */
		exit();
	}
	
	$ch = curl_init("http://localhost/basic/web/index.php?r=staff/login");
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, "staffFormalEmail=".$formalemail."&staffPassword=".$pass);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$server_output = curl_exec ($ch);
	curl_close($ch);
	if ($server_output == '{"status":"Ok"}')
	{
		$session = Yii::$app->session;
		$session->open();
		$session->set('staffFormalEmail', $formalemail);
		$session->set('signupStaffFormalEmail', $formalemail);
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
		if($agendaID == "notAgenda")
		{
			header("Location: http://localhost/basic/web/index.php?r=createagenda"); /* Redirect browser */
			exit();
		}
		else 
		{
			$owner = $output['owner'];
			$lastUpdate = $output['lastUpdate'];
			$type = $output['type'];
			$session->set('agendaID',$agendaID);
			$session->set('owner', $owner);
			$session->set('lastUpdate', $lastUpdate);
			$session->set('type', $type);
			$session->set('exceptionAgendaID',NULL);
			header("Location: http://localhost/basic/web/index.php?r=staffhome"); /* Redirect browser */
			exit();
		}
	}
	else
	{
		header("Location: http://localhost/basic/web/index.php?r=signup"); /* Redirect browser */
		exit();
	}
}
else
{
	$id = $_POST ['studentID'];
	$password = $_POST ['studentPassword'];
	
	if($id == "" || $password == "")
	{
		header("Location: http://localhost/basic/web/index.php?r=signup"); /* Redirect browser */
		exit();
	}
	
	$ch = curl_init("http://localhost/basic/web/index.php?r=student/login");
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, "studentID=".$id."&studentPassword=".$password);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$server_output = curl_exec ($ch);
	curl_close($ch);
	if ($server_output == '{"status":"Ok"}')
	{
		$session = Yii::$app->session;
		$session->open();
		$session->set('studentID', $id);
		header("Location: http://localhost/basic/web/index.php?r=studenthome"); /* Redirect browser */
		exit();
	}
	else
	{
		header("Location: http://localhost/basic/web/index.php?r=signup"); /* Redirect browser */
		exit();
	}
}


?>
