<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Studenthome */

$this->title = 'Create Studenthome';
$this->params['breadcrumbs'][] = ['label' => 'Studenthomes', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="studenthome-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
