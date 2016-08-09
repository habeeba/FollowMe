<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Checklogin */

$this->title = 'Create Checklogin';
$this->params['breadcrumbs'][] = ['label' => 'Checklogins', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="checklogin-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
