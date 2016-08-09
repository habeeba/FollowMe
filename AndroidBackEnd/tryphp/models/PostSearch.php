<?php

namespace app\models;

use Yii;
use yii\base\Model;
use yii\data\ActiveDataProvider;
use app\models\Post;

/**
 * PostSearch represents the model behind the search form about `app\models\Post`.
 */
class PostSearch extends Post {
	/**
	 * @inheritdoc
	 */
	public function rules() {
		return [ 
				[ 
						[ 
								'postID' 
						],
						'integer' 
				],
				[ 
						[ 
								'owner',
								'content',
								'time' 
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
		$query = Post::find ();
		
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
				'postID' => $this->postID 
		] );
		
		$query->andFilterWhere ( [ 
				'like',
				'owner',
				$this->owner 
		] )->andFilterWhere ( [ 
				'like',
				'content',
				$this->content 
		] )->andFilterWhere ( [ 
				'like',
				'time',
				$this->time 
		] );
		
		return $dataProvider;
	}
}
