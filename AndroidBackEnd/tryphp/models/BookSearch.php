<?php

namespace app\models;

use Yii;
use yii\base\Model;
use yii\data\ActiveDataProvider;
use app\models\Book;

/**
 * BookSearch represents the model behind the search form about `app\models\Book`.
 */
class BookSearch extends Book {
	/**
	 * @inheritdoc
	 */
	public function rules() {
		return [ 
				[ 
						[ 
								'bookID',
								'studentID',
								'slotID',
								'bookCount' 
						],
						'integer' 
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
		$query = Book::find ();
		
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
				'bookID' => $this->bookID,
				'studentID' => $this->studentID,
				'slotID' => $this->slotID,
				'bookCount' => $this->bookCount 
		] );
		
		return $dataProvider;
	}
}
