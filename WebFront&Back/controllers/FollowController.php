<?php

namespace app\controllers;

use Yii;
use app\models\Follow;
use app\models\FollowSearch;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;

/**
 * FollowController implements the CRUD actions for Follow model.
 */
class FollowController extends Controller
{
    public function behaviors()
    {
        return [
            'verbs' => [
                'class' => VerbFilter::className(),
                'actions' => [
                    'delete' => ['post'],
                ],
            ],
        ];
    }

    /**
     * Lists all Follow models.
     * @return mixed
     */
    public function actionIndex()
    {
        $searchModel = new FollowSearch();
        $dataProvider = $searchModel->search(Yii::$app->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single Follow model.
     * @param integer $id
     * @return mixed
     */
    public function actionView($id)
    {
        return $this->render('view', [
            'model' => $this->findModel($id),
        ]);
    }

    /**
     * Creates a new Follow model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return mixed
     */
    public function actionCreate()
    {
        $model = new Follow();

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->followID]);
        } else {
            return $this->render('create', [
                'model' => $model,
            ]);
        }
    }
    
    /**
     * Updates an existing Follow model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param integer $id
     * @return mixed
     */
    public function actionUpdate($id)
    {
    	$model = $this->findModel($id);
    
    	if ($model->load(Yii::$app->request->post()) && $model->save()) {
    		return $this->redirect(['view', 'id' => $model->followID]);
    	} else {
    		return $this->render('update', [
    				'model' => $model,
    		]);
    	}
    }
    
    /**
     * Deletes an existing Follow model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param integer $id
     * @return mixed
     */
    public function actionDelete($id)
    {
    	$this->findModel($id)->delete();
    
    	return $this->redirect(['index']);
    }
    
    /**
     * Finds the Follow model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param integer $id
     * @return Follow the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
    	if (($model = Follow::findOne($id)) !== null) {
    		return $model;
    	} else {
    		throw new NotFoundHttpException('The requested page does not exist.');
    	}
    }
    
    
    ///////////////////////////////////////////////////////////////////////////
    
    public function actionAdd()
    {
    	$studentID = $_POST["studentID"];
		$staffID = $_POST["staffFormalEmail"];
		$status = array ();
		$checkFollow = "pinding";
    	if (Follow::find()->where(['studentID' => $studentID , 'staffID' => $staffID])->one())
    	{
    		$status["status"] = "Already Following";
    		$checkFollow = "Followed";
    	}
        else 
        {
        	$checkFollow = "NotFollowed";
        	$model = new Follow;
        	$model -> studentID = $studentID;
        	$model -> staffID = $staffID;
        	
    	   
    	 	if ($model->save()){
    	 		$status["status"] = "Followed";
    			$checkFollow = "Ok";
    	}
    	
    		else 
    		{
    			$status["status"] = "Not Saved";
    		}	
    	
    }
    
    }

    
    public static function actionRemove(){
    	$studentID = $_POST["studentID"];
    	$staffID = $_POST["staffFormalEmail"];
    	$status = array ();
    	$model = Follow::deleteAll ( [ 'studentID' => $studentID , 'staffID' => $staffID] );
		if ($model == 1) {
			$status ["status"] = "Ok";
		} else {
			$status ["status"] = "Failed To Delete From Database";
		}
		return json_encode($status);
    }
    
    //to get some all staff that some id isfollowing 
    /*public function actionMine(){
    	
    	$studentID = $_GET ['sID'];
    	$model = Follow::find()->where(['studentID' => $studentID])->all();
    	
     	$result = array();
     	$i = 0;
     	foreach($model->staffID as $getResult)
     	{
     		$result[$i] = $getResult;
     		$i++;
     	}
     	
     	 echo json_encode($result);
     	//$result['studID'] = $model -> staffID ;
        //$result['staID'] = $model -> staffID ;
        
       // echo json_encode($result);
    	
    	
    	
    }
    */
}
