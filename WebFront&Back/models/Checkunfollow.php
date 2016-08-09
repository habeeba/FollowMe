<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "checkunfollow".
 *
 * @property integer $id
 */
class Checkunfollow extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'checkunfollow';
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
