<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Checkcreateexception */

$this->title = 'Create Checkcreateexception';
$this->params['breadcrumbs'][] = ['label' => 'Checkcreateexceptions', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="checkcreateexception-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
