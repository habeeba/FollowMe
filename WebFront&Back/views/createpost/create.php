<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Createpost */

$this->title = 'Create Createpost';
$this->params['breadcrumbs'][] = ['label' => 'Createposts', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="createpost-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
