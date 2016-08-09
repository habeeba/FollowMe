<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Checkwritepost */

$this->title = 'Create Checkwritepost';
$this->params['breadcrumbs'][] = ['label' => 'Checkwriteposts', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="checkwritepost-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
