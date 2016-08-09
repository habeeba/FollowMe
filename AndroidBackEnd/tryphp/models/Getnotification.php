<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "getnotification".
 *
 * @property string $post_id
 * @property string $student_id
 */
class Getnotification extends \yii\db\ActiveRecord {
	/**
	 * @inheritdoc
	 */
	public static function tableName() {
		return 'getnotification';
	}
	
	/**
	 * @inheritdoc
	 */
	public function rules() {
		return [ 
				[ 
						[ 
								'post_id',
								'student_id' 
						],
						'required' 
				],
				[ 
						[ 
								'post_id',
								'student_id' 
						],
						'string',
						'max' => 30 
				] 
		];
	}
	
	/**
	 * @inheritdoc
	 */
	public function attributeLabels() {
		return [ 
				'post_id' => 'Post ID',
				'student_id' => 'Student ID' 
		];
	}
}
