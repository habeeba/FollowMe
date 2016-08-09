<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Checkcreateagenda */

$this->title = 'Create Checkcreateagenda';
$this->params['breadcrumbs'][] = ['label' => 'Checkcreateagendas', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="checkcreateagenda-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
