<?php
use yii\helpers\Html;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model app\models\Day */

$this->title = $model->name;
$this->params ['breadcrumbs'] [] = [ 
		'label' => 'Days',
		'url' => [ 
				'index' 
		] 
];
$this->params ['breadcrumbs'] [] = $this->title;
?>
<div class="day-view">

	<h1><?= Html::encode($this->title) ?></h1>

	<p>
        <?= Html::a('Update', ['update', 'id' => $model->dayID], ['class' => 'btn btn-primary'])?>
        <?=Html::a ( 'Delete', [ 'delete','id' => $model->dayID ], [ 'class' => 'btn btn-danger','data' => [ 'confirm' => 'Are you sure you want to delete this item?','method' => 'post' ] ] )?>
    </p>

    <?=DetailView::widget ( [ 'model' => $model,'attributes' => [ 'dayID','agendaID','name' ] ] )?>

</div>
