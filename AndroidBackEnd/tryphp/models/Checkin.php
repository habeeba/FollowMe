<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "checkin".
 *
 * @property integer $id
 * @property string $staffID
 * @property string $mylocation
 * @property string $Time
 */
class Checkin extends \yii\db\ActiveRecord {
	/**
	 * @inheritdoc
	 */
	public static function tableName() {
		return 'checkin';
	}
	
	/**
	 * @inheritdoc
	 */
	public function rules() {
		return [ 
				[ 
						[ 
								'staffID',
								'mylocation',
								'Time' 
						],
						'required' 
				],
				[ 
						[ 
								'staffID' 
						],
						'string',
						'max' => 25 
				],
				[ 
						[ 
								'mylocation' 
						],
						'string',
						'max' => 100 
				],
				[ 
						[ 
								'Time' 
						],
						'string',
						'max' => 50 
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
				'mylocation' => 'Mylocation',
				'Time' => 'Time' 
		];
	}
	public function Deletecheckin($staffID) {
		$status = array ();
		if ($staffID == NULL) {
		} else {
			$checkModel = Checkin::deleteAll ( [ 
					'staffID' => $staffID 
			] );
		}
	}
}
