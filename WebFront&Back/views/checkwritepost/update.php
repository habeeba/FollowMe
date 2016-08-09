<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\Checkwritepost */

$this->title = 'Update Checkwritepost: ' . ' ' . $model->id;
$this->params['breadcrumbs'][] = ['label' => 'Checkwriteposts', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id, 'url' => ['view', 'id' => $model->id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="checkwritepost-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
