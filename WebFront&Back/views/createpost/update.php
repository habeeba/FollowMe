<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\Createpost */

$this->title = 'Update Createpost: ' . ' ' . $model->id;
$this->params['breadcrumbs'][] = ['label' => 'Createposts', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id, 'url' => ['view', 'id' => $model->id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="createpost-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
