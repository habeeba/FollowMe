<?php

namespace app\controllers;

use Yii;
use app\models\Post;
use app\models\PostSearch;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use app\models\Follow;

/**
 * PostController implements the CRUD actions for Post model.
 */
class PostController extends Controller {
	public function behaviors() {
		return [ 
				'verbs' => [ 
						'class' => VerbFilter::className (),
						'actions' => [ 
								'delete' => [ 
										'post' 
								] 
						] 
				] 
		];
	}
	
	/**
	 * Lists all Post models.
	 * 
	 * @return mixed
	 */
	public function actionIndex() {
		$searchModel = new PostSearch ();
		$dataProvider = $searchModel->search ( Yii::$app->request->queryParams );
		
		return $this->render ( 'index', [ 
				'searchModel' => $searchModel,
				'dataProvider' => $dataProvider 
		] );
	}
	
	/**
	 * Displays a single Post model.
	 * 
	 * @param integer $id        	
	 * @return mixed
	 */
	public function actionView($id) {
		return $this->render ( 'view', [ 
				'model' => $this->findModel ( $id ) 
		] );
	}
	
	/**
	 * Creates a new Post model.
	 * If creation is successful, the browser will be redirected to the 'view' page.
	 * 
	 * @return mixed
	 */
	public function actionCreatpost() {
		$owner = $_POST ['owner'];
		$content = $_POST ['content'];
		$time = $_POST ['time'];
		$status = array ();
		
		if ($owner == NULL || $content == NULL || $time == NULL) // application/json
{
			$status ['status'] = "You Must Enter All Fieldes";
		} else {
			$postModel = new Post ();
			$postModel->owner = $owner;
			$postModel->content = $content;
			$postModel->time = $time;
			if ($postModel->save ()) {
				$status ["status"] = "ok";
				$status ["postID"] = $postModel->postID;
			} else {
				$status ["status"] = "faild";
			}
		}
		echo json_encode ( $status );
	}
	
	/**
	 * Update Post Service
	 */
	public function actionUpdate() {
		$status = array ();
		$postID = $_POST ['postID'];
		$newContent = $_POST ['newcontent'];
		$newTime = $_POST ['newtime'];
		if ($postID == NULL || $newContent == NULL || $newTime == NULL) {
			$status ['status'] = "You Must Enter All Fieldes";
		} else {
			$postModel = Post::find ()->where ( [ 
					'postID' => $postID 
			] )->one ();
			$postModel->content = $newContent;
			$postModel->time = $newTime;
			if ($postModel->save ()) {
				$status ["status"] = "ok";
			} else {
				$status ["status"] = "faild";
			}
		}
		echo json_encode ( $status );
	}
	
	/**
	 * Delete Post Service
	 */
	public function actionSend() {
		$stringh = "لولول على ";
		echo $stringh;
	}
	public function actionDelete() {
		$status = array ();
		$postID = $_POST ['postID'];
		$postID = ( int ) $postID;
		if ($postID == NULL) {
			$status ['status'] = "faild";
		} else {
			$postModel = Post::deleteAll ( [ 
					'postID' => $postID 
			] );
			if ($postModel == 1) {
				$status ["status"] = "ok";
			} else {
				$status ["status"] = "faild";
			}
		}
		echo json_encode ( $status );
	}
	
	/**
	 * Finds the Post model based on its primary key value.
	 * If the model is not found, a 404 HTTP exception will be thrown.
	 * 
	 * @param integer $id        	
	 * @return Post the loaded model
	 * @throws NotFoundHttpException if the model cannot be found
	 */
	protected function findModel($id) {
		if (($model = Post::findOne ( $id )) !== null) {
			return $model;
		} else {
			throw new NotFoundHttpException ( 'The requested page does not exist.' );
		}
	}
	public function actionGetmyposts() {
		$id = $_POST ['id'];
		$model = array ();
		$model = Post::findAll ( ([ 
				'owner' => $id 
		]) );
		
		$status = array ();
		if ($model == Null) {
			$status ["status"] = "null";
		} else {
			$j = 0;
			for($i = (sizeof ( $model )) - 1; $i >= 0; $i --) {
				$status ["status"] = "ok";
				
				$status ["content"] [$j] = $model [$i]->content;
				$status ["time"] [$j] = $model [$i]->time;
				$status ["id"] [$j] = $model [$i]->postID;
				$status ["owner"] [$j] = $model [$i]->owner;
				
				$name = StaffController::GetStaffName ( $status ["owner"] [$j] );
				// echo $name;
				$status ["name"] [$j] = $name;
				
				$j ++;
			}
		}
		return json_encode ( $status );
	}
	public function actionGetstaffpost() {
		$id = $_POST ['id'];
		$model = array ();
		$model = Post::findAll ( ([ 
				'owner' => $id 
		]) );
		
		$status = array ();
		if ($model == Null) {
			$status ["status"] = "null";
		} else {
			$j = 0;
			for($i = (sizeof ( $model )) - 1; $i >= 0; $i --) {
				$status ["status"] = "ok";
				
				$status ["content"] [$j] = $model [$i]->content;
				$status ["time"] [$j] = $model [$i]->time;
				$status ["id"] [$j] = $model [$i]->postID;
				$status ["owner"] [$j] = $model [$i]->owner;
				
				$name = StaffController::GetStaffName ( $status ["owner"] [$j] );
				// echo $name;
				$status ["name"] [$j] = $name;
				
				$j ++;
			}
		}
		return json_encode ( $status );
	}
	public function actionGetfollowerpost() {
		$check_post = 0;
		
		$id = $_POST ['id'];
		$follow = new Follow ();
		$follow->studentID = $id;
		$model = array ();
		$model = Follow::findAll ( ([ 
				'studentID' => $id 
		]) );
		$status = array ();
		$statuss = array ();
		
		if ($model == Null) {
			$status ["status"] = "faild";
			$statuss ["status"] = "null";
		} else {
			
			$status ["status"] = "ok";
			$statuss ["status"] = "ok";
			
			for($j = 0; $j < sizeof ( $model ); $j ++) {
				$status ["follower"] [$j] = $model [$j]->staffID;
			}
			$i = 0;
			$m = 0;
			$k = 0;
			for($i = 0; $i < sizeof ( $model ); $i ++) {
				$staff_id = $model [$i]->staffID;
				
				$posts = array ();
				$posts = Post::findAll ( ([ 
						'owner' => $staff_id 
				]) );
				if ($posts == Null) {
					// $statuss["status"]="faild";
					$check_post ++;
				} else {
					$status ["status"] = "ok";
					$statuss ["status"] = "ok";
					
					for($m = (sizeof ( $posts )) - 1; $m >= 0; $m --) {
						$name = "";
						$statuss ["content"] [$k] = $posts [$m]->content;
						// =$posts[$m] ->content;
						$statuss ["owner"] [$k] = $posts [$m]->owner;
						
						$name = StaffController::GetStaffName ( $statuss ["owner"] [$k] );
						$statuss ["name"] [$k] = $name;
						$statuss ["time"] [$k] = $posts [$m]->time;
						
						$k ++;
					}
					// $k += sizeof($posts);
				}
			}
		}
		if ($check_post == sizeof ( $model )) {
			$statuss ["post"] = "null";
		} else {
			$statuss ["post"] = "full";
		}
		return json_encode ( $statuss );
	}
}

