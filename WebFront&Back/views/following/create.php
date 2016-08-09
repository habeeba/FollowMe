<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Following */

$this->title = 'Create Following';
$this->params['breadcrumbs'][] = ['label' => 'Followings', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="following-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
