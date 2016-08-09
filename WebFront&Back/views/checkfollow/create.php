<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Checkfollow */

$this->title = 'Create Checkfollow';
$this->params['breadcrumbs'][] = ['label' => 'Checkfollows', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="checkfollow-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
