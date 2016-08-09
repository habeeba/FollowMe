<?php

$content = $_POST ['content'];
$session = Yii::$app->session;
$session->open ();
$today = date("F j, Y, g:i a");
$dateTime = (string)$today;
$owner = $session->get ( "staffFormalEmail" );
$ch = curl_init("http://localhost/basic/web/index.php?r=post/write");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, "owner=".$owner."&content=".$content."&time=".$dateTime);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$server_output = curl_exec ($ch);
curl_close($ch);
$usermail="aliaaalifci@gmail.com";
if ($server_output == '{"status":"Ok"}')
{

	/*
	$emailfrom ="aliaaalifci@gmail.com";
	$emailsubject = "Follow Me Email";
	$emailpriority = "property";
	$emailto =["engaliaaali16@gmail.com" ,"habeeba_baioumy@hotmail.com"];
	
	$subject = "$emailsubject";
	$mailer = "$emailfrom";
	$headers = "From: $mailer \r\n";
	$headers .= "Reply-To: $mailer\r\n";
	$headers .= "MIME-Version: 1.0\r\n";
	$headers .= "Content-Type: text/html; charset=ISO-8859-1\r\n boundary=\"PHP-mixed-    \"";
	$headers .= "Importance: $emailpriority\r\n";
	$email = new PHPMailer();
	$email->From      = $mailer;
	$email->FromName  = $mailer;
	$email->Subject   = $subject;
	$email->Body      = $message;
	$addr = explode(',',$email_addresses);
	
	foreach ($addr as $ad) {
		$email->AddAddress( trim($ad) );
	}$email->isHTML(true);
	
	$email->Send();
	*/
	
$to = "engaliaaali16@gmail.com ,habeeba_baioumy@hotmail.com";
$subject = "HTML email";

$message = "
<html>
<head>
<title>HTML email</title>
</head>
<body>
<p>This email contains HTML Tags!</p>
<table>
<tr>
<th>Firstname</th>
<th>Lastname</th>
</tr>
<tr>
<td>John</td>
<td>Doe</td>
</tr>
</table>
</body>
</html>
";

// Always set content-type when sending HTML email
$headers = "MIME-Version: 1.0" . "\r\n";
$headers .= "Content-type:text/html;charset=UTF-8" . "\r\n";

// More headers
$headers .= 'From: <engaliaaali16@gmail.com>' . "\r\n";

mail($to,$subject,$message,$headers);
	
	header("Location: http://localhost/basic/web/index.php?r=staffhome"); /* Redirect browser */
	exit();
}
else
{
	echo "Faildddddddddddddd";
}

?>
