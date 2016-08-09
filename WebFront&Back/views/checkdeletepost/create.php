<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Checkdeletepost */

$this->title = 'Create Checkdeletepost';
$this->params['breadcrumbs'][] = ['label' => 'Checkdeleteposts', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="checkdeletepost-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
