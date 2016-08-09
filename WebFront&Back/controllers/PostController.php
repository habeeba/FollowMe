<?php

namespace app\controllers;

use Yii;
use app\models\Post;
use app\models\PostSearch;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use app\models\Follow;
use app\controllers\StaffController;
use app;

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
	public function actionCreate() {
		$model = new Post ();
		
		if ($model->load ( Yii::$app->request->post () ) && $model->save ()) {
			return $this->redirect ( [ 
					'view',
					'id' => $model->postID 
			] );
		} else {
			return $this->render ( 'create', [ 
					'model' => $model 
			] );
		}
	}
	
	/**
	 * Updates an existing Post model.
	 * If update is successful, the browser will be redirected to the 'view' page.
	 * 
	 * @param integer $id        	
	 * @return mixed
	 */
	public function actionUpdate($id) {
		$model = $this->findModel ( $id );
		
		if ($model->load ( Yii::$app->request->post () ) && $model->save ()) {
			return $this->redirect ( [ 
					'view',
					'id' => $model->postID 
			] );
		} else {
			return $this->render ( 'update', [ 
					'model' => $model 
			] );
		}
	}
	
	/**
	 * Deletes an existing Post model.
	 * If deletion is successful, the browser will be redirected to the 'index' page.
	 * 
	 * @param integer $id        	
	 * @return mixed
	 */
	public function actionDelete($id) {
		$this->findModel ( $id )->delete ();
		
		return $this->redirect ( [ 
				'index' 
		] );
	}
	
	/**
	 * Creates a new Post model.
	 * If creation is successful, the browser will be redirected to the 'view' page.
	 *
	 * @return mixed
	 */
	public function actionWrite() 
	{
		$owner = $_POST ['owner'];
		$content = $_POST ['content'];
		$dateTime = $_POST ['time'];
		$status = array ();
		$postModel = new Post ();
		$postModel->owner = ( string ) $owner;
		$postModel->content = ( string ) $content;
		$postModel->time = ( string ) $dateTime;
		if ($postModel->save ()) {
			$status ["status"] = "Ok";
		} else {
			$status ["status"] = "Failed";
		}
		return json_encode($status); 
	}
	
	/**
	 * Update Post Service
	 */
	public function actionUpdatepost() 
	{
		$postID = $_POST ['postID'];
		$newContent = $_POST ['newContent'];
		$status = array ();
		$postModel = Post::updateAll(array ('content' => $newContent), ['postID' => $postID] );
	}
	
	/**
	 * Delete Post Service
	 */
	public function actionDeletepost() {
		$postID = $_POST ['postID'];
		$status = array ();
		$postModel = Post::deleteAll ( [ 
				'postID' => $postID 
		] );
		if ($postModel == 1) {
			$status ["status"] = "Ok";
		} else {
			$status ["status"] = "Failed";
		}
		return json_encode($status);
	}
	
	/**
	 * Finds the Post model based on its primary key value.
	 * If the model is not found, a 404 HTTP exception will be thrown.
	 *
	 * @param integer $id        	
	 * @return Post the loaded model
	 * @throws NotFoundHttpException if the model cannot be found
	 */
	/*
	 * protected function findModel($id) {
	 * if (($model = Post::findOne ( $id )) !== null) {
	 * return $model;
	 * } else {
	 * throw new NotFoundHttpException ( 'The requested page does not exist.' );
	 * }
	 * }
	 */
	public function actionGetmyposts()
	{
		$staffFormalEmail = $_POST['staffFormalEmail'];
		$model = array ();
		$model = Post::findAll( ([ 
				'owner' => $staffFormalEmail 
		]) );
		$status = array ();
		$myPosts = array ();
		if ($model == NUll) {
			$status ["status"] = "Faild";
		} else {
			$status ["status"] = "Ok";
			for($i = 0; $i < sizeof ( $model ); $i ++) {
				$myPosts [$i]["Content"] = $model [$i]->content;
				$myPosts [$i]["Time"] = $model [$i]->time;
				$myPosts[$i]["PostID"] = $model[$i]->postID;
			}
		}
		return json_encode ( $myPosts );
	}
	public function actionShowfollowersposts() 
	{
		$studentID = $_POST ['studentID'];
		$followModel = array ();
		$followModel = Follow::findAll ( ([ 'studentID' => $studentID ]) );
		$status = array ();
		
		$result = array ();
		if ($followModel == NULL) {
			$status ["status"] = "Failed To Find Student With This ID";
		} else {
			$status ["status"] = "Ok";
			$i = 0;
			$j = 0;
			for($i = 0; $i < sizeof ( $followModel ); $i++) {
				$staffID = $followModel [$i]->staffID;
				$posts = array ();
				$posts = Post::findAll ( ([ 'owner' => $staffID ]) );
				if ($posts == NULL) {
					$status ["status"] = "Failed To Find Posts For This Staff";
				} else {
					$status ["status"] = "Ok";

					$allPosts = array ();
					
					for($j = 0; $j < sizeof ( $posts ); $j++) {
						$post = array ();
						$post ["Content"] = $posts [$j]->content;
						$ch = curl_init("http://localhost/basic/web/index.php?r=staff/getstaffname");
						curl_setopt($ch, CURLOPT_POST, 1);
						curl_setopt($ch, CURLOPT_POSTFIELDS, "staffFormalEmail=".$posts[$j]->owner);
						curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
						$name = curl_exec ($ch);
						$post ["Owner"] = $name;
						
						$post ["staffFormalEmail"] = $posts[$j]->owner;
						
						$post ["Time"] = $posts [$j]->time;
						$allPosts [$j] = $post;
					
					}
					$result [$i] = $allPosts;
					}
					}
					}
		
		
		return json_encode($result);
	}
	public function tt() {
		$id = $_GET ['id'];
		$posts = array ();
		
		$posts = Post::find ()->where ( [ 
				'postID' => $postID 
		] );
	}
	public function actionAftercreatepost() {
		$a = $_POST ['po'];
		$b = new Post ();
		$b->owner = 'fatma.omara@fci-cu.edu.eg';
		$b->content = 'yesss';
		$b->time = '1';
		// $b->load();
		$b->validate ();
		// var_dump($b->errors);
		// echo $b->owner."<br>".$b->content."<br>".$b->time."<br>";
		// print_r($b);
		// $b->scenario = NULL;
		if ($b->save ()) {
			print_r ( "heeeeeeere" );
		}
		// $b->save();
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
}
