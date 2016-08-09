<?php

namespace app\models;

use Yii;
use yii\base\Model;
use yii\data\ActiveDataProvider;
use app\models\Staff;

/**
 * StaffSearch represents the model behind the search form about `app\models\Staff`.
 */
class StaffSearch extends Staff {
	/**
	 * @inheritdoc
	 */
	public function rules() {
		return [ 
				[ 
						[ 
								'formalemail',
								'name',
								'email',
								'password' 
						],
						'safe' 
				] 
		];
	}
	
	/**
	 * @inheritdoc
	 */
	public function scenarios() {
		// bypass scenarios() implementation in the parent class
		return Model::scenarios ();
	}
	
	/**
	 * Creates data provider instance with search query applied
	 *
	 * @param array $params        	
	 *
	 * @return ActiveDataProvider
	 */
	public function search($params) {
		$query = Staff::find ();
		
		$dataProvider = new ActiveDataProvider ( [ 
				'query' => $query 
		] );
		
		$this->load ( $params );
		
		if (! $this->validate ()) {
			// uncomment the following line if you do not want to return any records when validation fails
			// $query->where('0=1');
			return $dataProvider;
		}
		
		$query->andFilterWhere ( [ 
				'like',
				'formalemail',
				$this->formalemail 
		] )->andFilterWhere ( [ 
				'like',
				'name',
				$this->name 
		] )->andFilterWhere ( [ 
				'like',
				'email',
				$this->email 
		] )->andFilterWhere ( [ 
				'like',
				'password',
				$this->password 
		] );
		
		return $dataProvider;
	}
}
