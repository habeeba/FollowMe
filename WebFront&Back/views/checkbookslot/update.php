<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\Checkbookslot */

$this->title = 'Update Checkbookslot: ' . ' ' . $model->id;
$this->params['breadcrumbs'][] = ['label' => 'Checkbookslots', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id, 'url' => ['view', 'id' => $model->id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="checkbookslot-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
