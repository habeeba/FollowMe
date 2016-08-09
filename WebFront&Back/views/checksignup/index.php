<?php
$userType = $_POST["userType"];
if ($userType == "staff")
{
	$name = $_POST ['staffName'];
	$formalemail = $_POST ['staffFormalEmail'];
	$pass = $_POST ['staffPassword'];
	if($name == "" || $formalemail == "" || $pass == "")
	{
		header("Location: http://localhost/basic/web/index.php?r=signup"); /* Redirect browser */
		exit();
	}
	
	$ch = curl_init("http://localhost/basic/web/index.php?r=staff/signup");
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, "staffName=".$name."&staffFormalEmail=".$formalemail."&staffPassword=".$pass);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$server_output = curl_exec ($ch);
	curl_close($ch);
	
	if ($server_output == '{"status":"Ok"}') 
	{ 
		$session = Yii::$app->session;
		$session->open();
		$session->set('signupStaffFormalEmail', $formalemail);
		header("Location: http://localhost/basic/web/index.php?r=createagenda"); /* Redirect browser */
		exit();
	} 
	else
	{ 
		header("Location: http://localhost/basic/web/index.php?r=signup"); /* Redirect browser */
		exit();
	}
}
else 
{
	$name = $_POST['studentName'];
	$id = $_POST['studentID'];
	$email = $_POST['studentEmail'];
	$pass = $_POST['studentPassword'];
	
	if($name == "" || $id == "" || $email == "" || $pass == "")
	{
		header("Location: http://localhost/basic/web/index.php?r=signup"); /* Redirect browser */
		exit();
	}
	
	$ch = curl_init("http://localhost/basic/web/index.php?r=student/signup");
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, "studentName=".$name."&studentID=".$id."&studentEmail=".$email."&studentPassword=".$pass);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$server_output = curl_exec ($ch);
	curl_close($ch);
	if ($server_output == '{"status":"Ok"}')
	{
		header("Location: http://localhost/basic/web/index.php?r=site"); /* Redirect browser */
		exit();
	}
	else
	{
		header("Location: http://localhost/basic/web/index.php?r=signup"); /* Redirect browser */
		exit();
	}
}

?>