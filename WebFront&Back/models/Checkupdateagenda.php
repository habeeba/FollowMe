<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "checkupdateagenda".
 *
 * @property integer $id
 */
class Checkupdateagenda extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'checkupdateagenda';
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
