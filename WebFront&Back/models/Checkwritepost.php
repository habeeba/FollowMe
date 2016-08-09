<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "checkwritepost".
 *
 * @property integer $id
 */
class Checkwritepost extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'checkwritepost';
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
