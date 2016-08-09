<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\Onupdate */

$this->title = 'Update Onupdate: ' . ' ' . $model->id;
$this->params['breadcrumbs'][] = ['label' => 'Onupdates', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id, 'url' => ['view', 'id' => $model->id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="onupdate-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
