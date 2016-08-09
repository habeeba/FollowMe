<?php

namespace app\models;

use yii\base\Model;

class UserForms extends Model {
	public $names;
	public $email;
	public function rules() {
		return [ 
				[ 
						[ 
								'names',
								'email' 
						],
						'required' 
				],
				[ 
						'email',
						'email' 
				] 
		]
		;
	}
}