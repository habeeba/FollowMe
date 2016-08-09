<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "posts".
 *
 * @property integer $post_id
 * @property string $post_title
 * @property string $post_description
 * @property integer $auther_id
 */
class Posts extends \yii\db\ActiveRecord {
	/**
	 * @inheritdoc
	 */
	public static function tableName() {
		return 'posts';
	}
	
	/**
	 * @inheritdoc
	 */
	public function rules() {
		return [ 
				[ 
						[ 
								'post_title',
								'post_description',
								'auther_id' 
						],
						'required' 
				],
				[ 
						[ 
								'post_description' 
						],
						'string' 
				],
				[ 
						[ 
								'auther_id' 
						],
						'integer' 
				],
				[ 
						[ 
								'post_title' 
						],
						'string',
						'max' => 100 
				] 
		];
	}
	
	/**
	 * @inheritdoc
	 */
	public function attributeLabels() {
		return [ 
				'post_id' => 'Post ID',
				'post_title' => 'Post Title',
				'post_description' => 'Post Description',
				'auther_id' => 'Auther ID' 
		];
	}
}
