<?php
namespace app\controllers;
use Yii;
use app\models\Student;
use app\models\StudentSearch;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
/**
 * StudentController implements the CRUD actions for Student model.
 */
class StudentController extends Controller {
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
	 * Lists all Student models.
	 * 
	 * @return mixed
	 */
	public function actionIndex() {
		$searchModel = new StudentSearch ();
		$dataProvider = $searchModel->search ( Yii::$app->request->queryParams );
		
		return $this->render ( 'index', [ 
				'searchModel' => $searchModel,
				'dataProvider' => $dataProvider 
		] );
	}
	
	/**
	 * Displays a single Student model.
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
	 * Creates a new Student model.
	 * If creation is successful, the browser will be redirected to the 'view' page.
	 * 
	 * @return mixed
	 */
	public function actionCreate() {
		$model = new Student ();
		
		if ($model->load ( Yii::$app->request->post () ) && $model->save ()) {
			return $this->redirect ( [ 
					'view',
					'id' => $model->id 
			] );
		} else {
			return $this->render ( 'create', [ 
					'model' => $model 
			] );
		}
	}
	
	/**
	 * Updates an existing Student model.
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
					'id' => $model->id 
			] );
		} else {
			return $this->render ( 'update', [ 
					'model' => $model 
			] );
		}
	}
	
	/**
	 * Deletes an existing Student model.
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
	 * Finds the Student model based on its primary key value.
	 * If the model is not found, a 404 HTTP exception will be thrown.
	 * 
	 * @param integer $id        	
	 * @return Student the loaded model
	 * @throws NotFoundHttpException if the model cannot be found
	 */
	protected function findModel($id) {
		if (($model = Student::findOne ( $id )) !== null) {
			return $model;
		} else {
			throw new NotFoundHttpException ( 'The requested page does not exist.' );
		}
	}
	
	public function actionSignup()
	{
		$name = $_POST['studentName'];
		$id = $_POST['studentID'];
		$email = $_POST['studentEmail'];
		$pass = $_POST['studentPassword'];
		$model = new Student ;
		$model->name = $name;
		$model->id = $id;
		$model->email = $email;
		$model->password = $pass;
		$status = array();
		$checkExists = Student::find ()->where ( [ 
				'id' => $id 
		] )->one ();
		if ($checkExists == NULL) {
			if ($model->save ()) {
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
		$id = $_POST ['studentID'];
		$password = $_POST ['studentPassword'];
		$model = Student::find()->where(['id' => $id , 'password' => $password])->one();
	    $status = array ();
		if ($model == NUll) {
			$status ["status"] = "Failed";
		} else {
			$status ["status"] = "Ok";
		}
		return json_encode($status);
	}
	
	public function actionGetstudentname()
	{
		$studentID = $_POST['studentID'];
		$student = new Student();
		$studentModel = Student::find ()->where ( [ 'id' => $studentID] )->one ();
		return $studentModel->name;
	}
	
}