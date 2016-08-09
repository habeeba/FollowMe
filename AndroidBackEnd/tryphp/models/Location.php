<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "location".
 *
 * @property integer $id
 * @property string $staffID
 * @property string $location
 */
class Location extends \yii\db\ActiveRecord {
	/**
	 * @inheritdoc
	 */
	public static function tableName() {
		return 'location';
	}
	
	/**
	 * @inheritdoc
	 */
	public function rules() {
		return [ 
				[ 
						[ 
								'staffID',
								'location' 
						],
						'required' 
				],
				[ 
						[ 
								'staffID' 
						],
						'string',
						'max' => 50 
				],
				[ 
						[ 
								'location' 
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
				'id' => 'ID',
				'staffID' => 'Staff ID',
				'location' => 'Location' 
		];
	}
}
