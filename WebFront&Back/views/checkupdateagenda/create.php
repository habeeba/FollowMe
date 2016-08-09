<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Checkupdateagenda */

$this->title = 'Create Checkupdateagenda';
$this->params['breadcrumbs'][] = ['label' => 'Checkupdateagendas', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="checkupdateagenda-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
