<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "checkbookslot".
 *
 * @property integer $id
 */
class Checkbookslot extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'checkbookslot';
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
