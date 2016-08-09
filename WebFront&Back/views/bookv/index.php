<?php 
$session = Yii::$app->session;
$session->open ();
$session->set( 'staffFormalEmail',NULL );
$session->set('signupStaffFormalEmail', NULL);
$session->set('agendaID',NULL);
$session->set('owner', NULL);
$session->set('lastUpdate', NULL);
$session->set('type', NULL);
$session->set('exceptionAgendaID',NULL);
header("Location: http://localhost/basic/web/index.php?r=site"); /* Redirect browser */
exit();

?>