<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Finish */

$this->title = 'Create Finish';
$this->params['breadcrumbs'][] = ['label' => 'Finishes', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="finish-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
