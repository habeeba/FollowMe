<?php
use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\Day */

$this->title = 'Update Day: ' . ' ' . $model->name;
$this->params ['breadcrumbs'] [] = [ 
		'label' => 'Days',
		'url' => [ 
				'index' 
		] 
];
$this->params ['breadcrumbs'] [] = [ 
		'label' => $model->name,
		'url' => [ 
				'view',
				'id' => $model->dayID 
		] 
];
$this->params ['breadcrumbs'] [] = 'Update';
?>
<div class="day-update">

	<h1><?= Html::encode($this->title) ?></h1>

    <?=$this->render ( '_form', [ 'model' => $model ] )?>

</div>
