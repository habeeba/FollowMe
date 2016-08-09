<?php

namespace app\controllers;

use app\models\Checkin;
use app\models\Follow;

class CheckinController extends \yii\web\Controller {
	public function actionIndex() {
		return $this->render ( 'index' );
	}
	public function actionAddcheckin() {
		$status ['status'] = "oks";
		$staffID = $_POST ['staffID'];
		$mylocation = $_POST ['mylocation'];
		$time = $_POST ['time'];
		$checkinModel = new Checkin ();
		
		$checkinModel->Deletecheckin ( $staffID );
		$status = array ();
		$status ['status'] = "oks";
		if ($staffID == NULL || $mylocation == NULL || $time == NULL) {
			$status ['status'] = "You Must Enter All Fieldes";
		} 

		else {
			$checkinModel1 = new Checkin ();
			$checkinModel1->staffID = $staffID;
			$checkinModel1->mylocation = $mylocation;
			$checkinModel1->Time = $time;
			if ($checkinModel1->save ()) {
				$status ["status"] = "ok";
			} else {
				$status ["status"] = "faild";
			}
		}
		return json_encode ( $status );
	}
	public function actionDeletecheckin() {
		$status = array ();
		$staffID = $_POST ['staffID'];
		if ($staffID == NULL) {
			$status ['status'] = "not";
		} else {
			$checkModel = Checkin::deleteAll ( [ 
					'staffID' => $staffID 
			] );
			if ($checkModel == 1) {
				$status ["status"] = "ok";
			} else {
				$status ["status"] = "not";
			}
		}
		echo json_encode ( $status );
	}
	
	/*
	 * public function actionGetcheckin()
	 * {
	 * $staffID = $_GET['staffID'];
	 * $status = array ();
	 * $checkinmodel= new Checkin ();
	 * $checkinmodel= Checkin::find()->where(['staffID' => $staffID])->one();
	 * if (sizeof($checkinmodel) >0)
	 * {
	 * $status["status"] = "ok";
	 * $status["location"] = $checkinmodel->mylocation;
	 *
	 * $status["time"] = $checkinmodel->Time;
	 *
	 *
	 *
	 * }
	 * else
	 * {
	 * $status["status"] = "null";
	 *
	 * }
	 * return json_encode($status);
	 * }
	 */
	public function actionGetstudentcheckin() {
		$sID = $_POST ['sID'];
		$model = new Follow ();
		$following = array ();
		$following = $model->getfollowers ( $sID );
		$status = array ();
		$j = 0;
		$check = 0;
		if (sizeof ( $following ) == 0) {
			$status ["status"] = "no checkin";
			$status ["check"] = "no follow doc";
		} else {
			$status ["status"] = "ok";
			
			for($i = 0; $i < sizeof ( $following ); $i ++) {
				$staffID = $following [$i];
				if (Checkin::find ()->where ( [ 
						'staffID' => $staffID 
				] )->one ()) {
					$CheckinM = new Checkin ();
					$CheckinM = Checkin::find ()->where ( [ 
							'staffID' => $staffID 
					] )->one ();
					$status ["mylocation"] [$j] = $CheckinM->mylocation;
					$status ["Time"] [$j] = $CheckinM->Time;
					$status ["name"] [$j] = StaffController::GetStaffName ( $staffID );
					
					$j ++;
					$check = 1;
				}
			}
			if ($check == 0) {
				$status ["status"] = "no checkin";
			}
		}
		return json_encode ( $status );
	}
}