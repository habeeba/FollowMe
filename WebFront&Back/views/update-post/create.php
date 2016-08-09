<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Updatepost */

$this->title = 'Create Updatepost';
$this->params['breadcrumbs'][] = ['label' => 'Updateposts', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="updatepost-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
