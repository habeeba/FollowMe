<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "staff".
 *
 * @property string $formalemail
 * @property string $name
 * @property string $password
 *
 * @property Follow[] $follows
 */
class Staff extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'staff';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['formalemail', 'name', 'password'], 'required'],
            [['formalemail', 'name', 'password'], 'string', 'max' => 50],
            [['formalemail'], 'unique']
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'formalemail' => 'Formalemail',
            'name' => 'Name',
            'password' => 'Password',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getFollows()
    {
        return $this->hasMany(Follow::className(), ['staffID' => 'formalemail']);
    }
}
