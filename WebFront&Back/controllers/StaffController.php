<?php
namespace app\controllers;
use Yii;
use app\models\Staff;
use app\models\StaffSearch;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use app\models\Posts;
use app\models\Post;
use app\models\Follow;
/**
 * StaffController implements the CRUD actions for Staff model.
 */
class StaffController extends Controller {
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
	 * Lists all Staff models.
	 * 
	 * @return mixed
	 */
	public function actionIndex() {
		$searchModel = new StaffSearch ();
		$dataProvider = $searchModel->search ( Yii::$app->request->queryParams );
		
		return $this->render ( 'index', [ 
				'searchModel' => $searchModel,
				'dataProvider' => $dataProvider 
		] );
	}
	
	/**
	 * Displays a single Staff model.
	 * 
	 * @param string $id        	
	 * @return mixed
	 */
	public function actionView($id) {
		return $this->render ( 'view', [ 
				'model' => $this->findModel ( $id ) 
		] );
	}
	
	/**
	 * Creates a new Staff model.
	 * If creation is successful, the browser will be redirected to the 'view' page.
	 * 
	 * @return mixed
	 */
	public function actionCreate() {
		$model = new Staff ();
		
		if ($model->load ( Yii::$app->request->post () ) && $model->save ()) {
			return $this->redirect ( [ 
					'view',
					'id' => $model->formalemail 
			] );
		} else {
			return $this->render ( 'create', [ 
					'model' => $model 
			] );
		}
	}
	
	/**
	 * Updates an existing Staff model.
	 * If update is successful, the browser will be redirected to the 'view' page.
	 * 
	 * @param string $id        	
	 * @return mixed
	 */
	public function actionUpdate() {
		$id = 1;
		$model = $this->findModel ( $id );
		
		if ($model->load ( Yii::$app->request->post () ) && $model->save ()) {
			return $this->redirect ( [ 
					'view',
					'id' => $model->formalemail 
			] );
		} else {
			return $this->render ( 'update', [ 
					'model' => $model 
			] );
		}
	}
	
	/**
	 * Deletes an existing Staff model.
	 * If deletion is successful, the browser will be redirected to the 'index' page.
	 * 
	 * @param string $id        	
	 * @return mixed
	 */
	public function actionDelete($id) {
		$this->findModel ( $id )->delete ();
		
		return $this->redirect ( [ 
				'index' 
		] );
	}
	
	/**
	 * Finds the Staff model based on its primary key value.
	 * If the model is not found, a 404 HTTP exception will be thrown.
	 * 
	 * @param string $id        	
	 * @return Staff the loaded model
	 * @throws NotFoundHttpException if the model cannot be found
	 */
	protected function findModel($id) {
		if (($model = Staff::findOne ( $id )) !== null) {
			return $model;
		} else {
			throw new NotFoundHttpException ( 'The requested page does not exist.' );
		}
	}
	
	public function actionSignup()
	{
		$name = $_POST ['staffName'];
		$formalemail = $_POST ['staffFormalEmail'];
		$pass = $_POST ['staffPassword'];
		$staff = new Staff ();
		$staff->formalemail = $formalemail;
		$staff->name = $name;
		$staff->password = $pass;
		$status = array ();
		$checkExists = Staff::find ()->where ( [ 
				'formalemail' => $formalemail 
		] )->one ();
		$r;
		if ($checkExists == NULL) {
			if ($staff->save ()) {
				$status ["status"] = "Ok";
			} else {
				$status ["status"] = "Failed";
			}  
		} else {
			$status ["status"] = "Failed";
		}
		return json_encode($status);
	}
	
	
	public function actionLogin() {
		$formalemail = $_POST ['staffFormalEmail'];
		$pass = $_POST ['staffPassword'];
		$model = Staff::find ()->where ( [ 
				'formalemail' => $formalemail,
				'password' => $pass 
		] )->one ();
		$status = array ();
		if ($model == NUll) {
			$status ["status"] = "Failed";
		} else {
			$status ["status"] = "Ok";
		}
		return json_encode($status);
	}
	
	
	
	public function actionGetmyposts() {
		$id = $_POST ['staffFormalEmail'];
		$staff = new Staff ();
		$staff->formalemail = $id;
		$model = array ();
		$model = $staff->getPosts ();
		$status = array ();
		if ($model == Null) {
			$status ["status"] = "Failed";
		} else {
			$status ["formalemail"] = $model [0]->content;
			$status ["name"] = $model [0]->owner;
			$status ["email"] = $model [0]->time;
		}
		return json_encode ( $status );
	}
	private function checkUser() {
		echo "\n\nStatus of current user:\n";
		echo "--------------------------\n";
		echo "User ID: " . Yii::app ()->user->id . "\n";
		echo "User Name: " . Yii::app ()->user->name . "\n";
		if (Yii::app ()->user->isGuest)
			echo "There is NO user logged in.\n\n";
		else
			echo "The user is logged in.\n\n";
	}
	public function actionLogoutstaff() {
		Yii::$app->user->logout ();
	}
	public function actionGetstaff() {
		$staff = array ();
		$staff = Staff::find ()->all ();
		$all_staff = array ();
		if ($staff == NUll) {
			$all_staff ["status"] = "faild";
		} else {
			for($i = 0; $i < sizeof ( $staff ); $i ++) {
				$all_staff ["staffID"] [$i] = $staff [$i]->formalemail;
				$all_staff ["name"] [$i] = $staff [$i]->name;
			}
		}
		return json_encode ( $all_staff );
	}
	
	public function actionGetstaffname()
	{
		$staffFormalEmail = $_POST['staffFormalEmail'];
		$staff = new Staff();
		$staffModel = Staff::find ()->where ( [ 'formalemail' => $staffFormalEmail] )->one ();
		return $staffModel->name;
	}
	
}