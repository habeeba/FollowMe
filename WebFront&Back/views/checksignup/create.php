<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Checksignup */

$this->title = 'Create Checksignup';
$this->params['breadcrumbs'][] = ['label' => 'Checksignups', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="checksignup-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
