<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "checkcreateagenda".
 *
 * @property integer $id
 */
class Checkcreateagenda extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'checkcreateagenda';
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
