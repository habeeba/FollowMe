<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Createexception */

$this->title = 'Create Createexception';
$this->params['breadcrumbs'][] = ['label' => 'Createexceptions', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="createexception-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
