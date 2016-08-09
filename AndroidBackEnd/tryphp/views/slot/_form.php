<?php
use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\Slot */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="slot-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'dayID')->textInput()?>

    <?= $form->field($model, 'agendaID')->textInput()?>

    <?= $form->field($model, 'content')->textInput(['maxlength' => true])?>

    <?= $form->field($model, 'type')->textInput(['maxlength' => true])?>

    <?= $form->field($model, 'date')->textInput(['maxlength' => true])?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary'])?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
