<?php
use yii\helpers\Html;
use yii\widgets\ActiveForm;
?>
<?php

if (Yii::$app->session->hasFlash ( 'success' )) {
	echo Yii::$app->session->getFlash ( 'success' );
}
?>
<?php


$form = ActiveForm::begin ();

?>
<?= $form->field($model, 'name');?>
<?= $form->field($model, 'formalemail');?>
<?=Html::submitButton('submit',['class'=>'btn btn-success']);?>