<?php

namespace app\controllers;

use app\models\Location;

class LocationController extends \yii\web\Controller {
	public function actionIndex() {
		return $this->render ( 'index' );
	}
	public function actionSetlocations() {
		$model = new Location ();
		
		$model->staffID = $_POST ['staffID'];
		
		$content = json_decode ( $_POST ['location'] );
		$status ["status"] = "NOT";
		
		for($i = 0; $i < sizeof ( $content ); $i ++) {
			$locationModel = new Location ();
			$locationModel->staffID = $model->staffID;
			$locationModel->location = $content [$i];
			if ($locationModel->save ()) {
				$status ["status"] = "ok";
			} else {
				$status ["status"] = "failed";
			}
		}
		echo json_encode ( $status );
	}
	public function actionGetlocations() {
		$staffID = $_POST ['staffID'];
		$model = array ();
		$model = Location::findAll ( ([ 
				'staffID' => $staffID 
		]) );
		
		$status = array ();
		if ($model == Null) {
			$status ["status"] = "null";
		} else {
			for($i = 0; $i < sizeof ( $model ); $i ++) {
				$status ["status"] = "ok";
				
				$status ["location"] [$i] = $model [$i]->location;
			}
		}
		return json_encode ( $status );
	}
}
    
