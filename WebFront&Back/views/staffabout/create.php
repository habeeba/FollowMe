<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Staffabout */

$this->title = 'Create Staffabout';
$this->params['breadcrumbs'][] = ['label' => 'Staffabouts', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="staffabout-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
