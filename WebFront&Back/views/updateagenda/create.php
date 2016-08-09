<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Updateagenda */

$this->title = 'Create Updateagenda';
$this->params['breadcrumbs'][] = ['label' => 'Updateagendas', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="updateagenda-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
