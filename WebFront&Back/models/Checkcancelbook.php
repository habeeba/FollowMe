<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "checkcancelbook".
 *
 * @property integer $id
 */
class Checkcancelbook extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'checkcancelbook';
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
