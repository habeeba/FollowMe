<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Bookv */

$this->title = 'Create Bookv';
$this->params['breadcrumbs'][] = ['label' => 'Bookvs', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="bookv-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
