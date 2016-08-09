<?php
use yii\helpers\Html;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model app\models\Follow */

$this->title = $model->followID;
$this->params ['breadcrumbs'] [] = [ 
		'label' => 'Follows',
		'url' => [ 
				'index' 
		] 
];
$this->params ['breadcrumbs'] [] = $this->title;
?>
<div class="follow-view">

	<h1><?= Html::encode($this->title) ?></h1>

	<p>
        <?= Html::a('Update', ['update', 'id' => $model->followID], ['class' => 'btn btn-primary'])?>
        <?=Html::a ( 'Delete', [ 'delete','id' => $model->followID ], [ 'class' => 'btn btn-danger','data' => [ 'confirm' => 'Are you sure you want to delete this item?','method' => 'post' ] ] )?>
    </p>

    <?=DetailView::widget ( [ 'model' => $model,'attributes' => [ 'followID','staffID','studentID' ] ] )?>

</div>
