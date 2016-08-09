<?php

namespace app\controllers;

use Yii;
use yii\web\Controller;
use app\models\Users;

class UsersController extends Controller {
	public function actionRel() {
		echo "testing bs";
		
		$sql = "INSERT INTO users (username, userid, userpassword)
VALUES ('habiba', '3', 'habiba12355')";
		$command = Yii::$app->db->createCommand ( $sql );
		$result = $command->execute ();
		
		// to call function in yii2
		
		// $model = new User();
		// $model->method();
	}
	
	// to using json in yii2
	public function actionJsontry() {
		\Yii::$app->response->format = \yii\web\Response::FORMAT_JSON;
		$items = [ 
				'some',
				'array',
				'of',
				'data' => [ 
						'associative',
						'array' 
				] 
		];
		return $items;
	}
}