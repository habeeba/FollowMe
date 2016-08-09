<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Checkcancelbook */

$this->title = 'Create Checkcancelbook';
$this->params['breadcrumbs'][] = ['label' => 'Checkcancelbooks', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="checkcancelbook-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
