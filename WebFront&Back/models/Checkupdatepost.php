<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "checkupdatepost".
 *
 * @property integer $id
 */
class Checkupdatepost extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'checkupdatepost';
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
