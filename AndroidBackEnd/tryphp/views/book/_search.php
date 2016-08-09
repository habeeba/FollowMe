<?php
use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\BookSearch */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="book-search">

    <?php
				
$form = ActiveForm::begin ( [ 
						'action' => [ 
								'index' 
						],
						'method' => 'get' 
				] );
				?>

    <?= $form->field($model, 'bookID')?>

    <?= $form->field($model, 'studentID')?>

    <?= $form->field($model, 'slotID')?>

    <?= $form->field($model, 'bookCount')?>

    <div class="form-group">
        <?= Html::submitButton('Search', ['class' => 'btn btn-primary'])?>
        <?= Html::resetButton('Reset', ['class' => 'btn btn-default'])?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
