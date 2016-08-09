<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Updatexception */

$this->title = 'Create Updatexception';
$this->params['breadcrumbs'][] = ['label' => 'Updatexceptions', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="updatexception-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
