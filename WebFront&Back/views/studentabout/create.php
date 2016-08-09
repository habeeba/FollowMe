<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Studentabout */

$this->title = 'Create Studentabout';
$this->params['breadcrumbs'][] = ['label' => 'Studentabouts', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="studentabout-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
