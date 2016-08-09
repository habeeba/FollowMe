<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Showagenda */

$this->title = 'Create Showagenda';
$this->params['breadcrumbs'][] = ['label' => 'Showagendas', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="showagenda-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
