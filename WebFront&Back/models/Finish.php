<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "finish".
 *
 * @property integer $id
 */
class Finish extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'finish';
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
