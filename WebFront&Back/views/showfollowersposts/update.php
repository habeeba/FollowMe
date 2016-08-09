<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\Showfollowersposts */

$this->title = 'Update Showfollowersposts: ' . ' ' . $model->id;
$this->params['breadcrumbs'][] = ['label' => 'Showfollowersposts', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id, 'url' => ['view', 'id' => $model->id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="showfollowersposts-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
