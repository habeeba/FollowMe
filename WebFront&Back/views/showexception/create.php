<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Showexception */

$this->title = 'Create Showexception';
$this->params['breadcrumbs'][] = ['label' => 'Showexceptions', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="showexception-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
