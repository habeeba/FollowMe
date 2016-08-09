<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Createagenda */

$this->title = 'Create Createagenda';
$this->params['breadcrumbs'][] = ['label' => 'Createagendas', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="createagenda-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
