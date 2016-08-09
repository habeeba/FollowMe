<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Second */

$this->title = 'Create Second';
$this->params['breadcrumbs'][] = ['label' => 'Seconds', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="second-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
