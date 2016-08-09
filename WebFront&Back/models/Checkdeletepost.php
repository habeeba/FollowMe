<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "checkdeletepost".
 *
 * @property integer $id
 */
class Checkdeletepost extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'checkdeletepost';
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
