<?php
use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\Follow */

$this->title = 'Update Follow: ' . ' ' . $model->followID;
$this->params ['breadcrumbs'] [] = [ 
		'label' => 'Follows',
		'url' => [ 
				'index' 
		] 
];
$this->params ['breadcrumbs'] [] = [ 
		'label' => $model->followID,
		'url' => [ 
				'view',
				'id' => $model->followID 
		] 
];
$this->params ['breadcrumbs'] [] = 'Update';
?>
<div class="follow-update">

	<h1><?= Html::encode($this->title) ?></h1>

    <?=$this->render ( '_form', [ 'model' => $model ] )?>

</div>
