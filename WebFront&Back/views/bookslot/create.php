<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Bookslot */

$this->title = 'Create Bookslot';
$this->params['breadcrumbs'][] = ['label' => 'Bookslots', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="bookslot-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
