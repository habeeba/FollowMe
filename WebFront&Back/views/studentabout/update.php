<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\Studentabout */

$this->title = 'Update Studentabout: ' . ' ' . $model->id;
$this->params['breadcrumbs'][] = ['label' => 'Studentabouts', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id, 'url' => ['view', 'id' => $model->id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="studentabout-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
