<?php
$ch = curl_init("http://localhost/basic/web/index.php?r=student/getstudentname");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, "studentID=".$session->get("studentID"));
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$studentName = curl_exec ($ch);
curl_close($ch);
?>

