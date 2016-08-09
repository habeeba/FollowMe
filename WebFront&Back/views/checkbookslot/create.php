<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Checkbookslot */

$this->title = 'Create Checkbookslot';
$this->params['breadcrumbs'][] = ['label' => 'Checkbookslots', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="checkbookslot-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
