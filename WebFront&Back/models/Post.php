<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "post".
 *
 * @property integer $postID
 * @property string $owner
 * @property string $content
 * @property string $time
 *
 * @property Notification[] $notifications
 */
class Post extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'post';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['owner', 'content', 'time'], 'required'],
            [['content'], 'string'],
            [['owner', 'time'], 'string', 'max' => 50]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'postID' => 'Post ID',
            'owner' => 'Owner',
            'content' => 'Content',
            'time' => 'Time',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getNotifications()
    {
        return $this->hasMany(Notification::className(), ['postID' => 'postID']);
    }
}
