<?php
use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\FollowSearch */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="follow-search">

    <?php
				
$form = ActiveForm::begin ( [ 
						'action' => [ 
								'index' 
						],
						'method' => 'get' 
				] );
				?>

    <?= $form->field($model, 'followID')?>

    <?= $form->field($model, 'staffID')?>

    <?= $form->field($model, 'studentID')?>

    <div class="form-group">
        <?= Html::submitButton('Search', ['class' => 'btn btn-primary'])?>
        <?= Html::resetButton('Reset', ['class' => 'btn btn-default'])?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
