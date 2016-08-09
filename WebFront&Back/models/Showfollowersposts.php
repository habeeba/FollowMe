<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "showfollowersposts".
 *
 * @property integer $id
 */
class Showfollowersposts extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'showfollowersposts';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
        ];
    }
}
