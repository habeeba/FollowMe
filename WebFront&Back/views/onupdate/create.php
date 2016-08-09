<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Onupdate */

$this->title = 'Create Onupdate';
$this->params['breadcrumbs'][] = ['label' => 'Onupdates', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="onupdate-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
