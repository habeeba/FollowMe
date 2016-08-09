<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Staffhome */

$this->title = 'Create Staffhome';
$this->params['breadcrumbs'][] = ['label' => 'Staffhomes', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="staffhome-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
