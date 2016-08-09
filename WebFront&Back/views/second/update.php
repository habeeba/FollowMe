<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\Second */

$this->title = 'Update Second: ' . ' ' . $model->id;
$this->params['breadcrumbs'][] = ['label' => 'Seconds', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id, 'url' => ['view', 'id' => $model->id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="second-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
