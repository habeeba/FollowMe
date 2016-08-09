<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "agenda".
 *
 * @property integer $agendaID
 * @property string $owner
 * @property string $lastUpdate
 * @property string $type
 *
 * @property Staff $owner0
 * @property Slot[] $slots
 */
class Agenda extends \yii\db\ActiveRecord {
	/**
	 * @inheritdoc
	 */
	public static function tableName() {
		return 'agenda';
	}
	
	/**
	 * @inheritdoc
	 */
	public function rules() {
		return [ 
				[ 
						[ 
								'owner',
								'lastUpdate',
								'type' 
						],
						'required' 
				],
				[ 
						[ 
								'lastUpdate' 
						],
						'safe' 
				],
				[ 
						[ 
								'owner' 
						],
						'string',
						'max' => 50 
				],
				[ 
						[ 
								'type' 
						],
						'string',
						'max' => 15 
				] 
		];
	}
	
	/**
	 * @inheritdoc
	 */
	public function attributeLabels() {
		return [ 
				'agendaID' => 'Agenda ID',
				'owner' => 'Owner',
				'lastUpdate' => 'Last Update',
				'type' => 'Type' 
		];
	}
	
	/**
	 *
	 * @return \yii\db\ActiveQuery
	 */
	public function getOwner0() {
		return $this->hasOne ( Staff::className (), [ 
				'formalemail' => 'owner' 
		] );
	}
	
	/**
	 *
	 * @return \yii\db\ActiveQuery
	 */
	public function getSlots() {
		return $this->hasMany ( Slot::className (), [ 
				'agendaID' => 'agendaID' 
		] );
	}
	public function saveAgenda(array $data = array(), array $bookbuffer = array()) {
		try {
			$this->type = 'perm';
			Agenda::save ( true );
			$agendaID = Yii::$app->db->getLastInsertID ();
			
			$slotnum = 0;
			
			for($index = 0; $index < sizeof ( $data ); $index ++) {
				$slot = new Slot ();
				$slot->saveSlot ( $agendaID, $data [$index], 'perm', $index, $bookbuffer [$index] );
			}
		} catch ( Exception $e ) {
			return 'DB Error';
		}
		return $agendaID;
	}
	public function saveException(array $data = array(), array $bookbuffer = array(), array $slotnum = array()) {
		$this->type = 'temp';
		
		Agenda::save ( true );
		$agendaID = Yii::$app->db->getLastInsertID ();
		$exist = Agenda::find ()->where ( [ 
				'agendaID' => $agendaID 
		] )->one ();
		$perm = Agenda::find ()->where ( [ 
				'owner' => $exist ['owner'],
				'type' => 'perm' 
		] )->one ();
		
		for($index = 0; $index < sizeof ( $data ); $index ++) {
			$slot = new Slot ();
			
			$slota = Slot::find ()->where ( [ 
					'agendaID' => $perm ['agendaID'],
					'slotnum' => $slotnum [$index] 
			] )->one ();
			
			$slot->saveSlot ( $agendaID, $data [$index], 'temp', $slotnum [$index], $bookbuffer [$index] );
			
			$SlotID = Yii::$app->db->getLastInsertID ();
			Book::updateAll ( array (
					'slotID' => $SlotID 
			), [ 
					'slotID' => $slota ['slotID'] 
			] );
		}
		
		return $agendaID;
	}
	public function updateExceptionAgenda(array $data = array(), array $bookbuffer = array(), array $slotnum = array()) {
		$model = new Slot ();
		$tempSlotIDs = array ();
		$exist = Agenda::find ()->where ( [ 
				'agendaID' => $this->agendaID 
		] )->one ();
		
		$permAgendaID = Agenda::find ()->where ( [ 
				'owner' => $exist ['owner'],
				'type' => 'perm' 
		] )->one ();
		$tempSlotIDs = $model->getIDs ( $this->agendaID );
		$tempindex = 0;
		$index = 0;
		for(; $index < sizeof ( $data );) {
			if ($tempindex == sizeof ( $tempSlotIDs )) {
				break;
			}
			if ($tempSlotIDs [$tempindex] ['slotnum'] == $slotnum [$index]) {
				$model->updateSlot ( $data [$index], $bookbuffer [$index], $tempSlotIDs [$index] ['slotID'] );
				$tempindex ++;
				$index ++;
			} elseif ($tempSlotIDs [$tempindex] ['slotnum'] < $slotnum [$index]) {
				$tempindex ++;
			} else {
				$model->saveSlot ( $this->agendaID, $data [$index], 'temp', $slotnum [$index], $bookbuffer [$index] );
				$index ++;
			}
		}
		for($inde = $index; $inde < sizeof ( $data ); $inde ++) {
			$model->saveSlot ( $this->agendaID, $data [$inde], 'temp', $slotnum [$inde], $bookbuffer [$inde] );
		}
		return 'updated';
	}
	public function updateAgenda(array $data = array(), array $bookbuffer = array(), array $slotnum = array()) {
		$model = new Slot ();
		$permAgendaID = array ();
		$resylt;
		$permAgendaID = $model->getIDs ( $this->agendaID );
		$permSlotIDs = $model->getIDs ( $this->agendaID );
		
		$permindex = 0;
		$b = 1;
		for($index = 0; $index < sizeof ( $data ); $permindex ++) {
			
			if ($permAgendaID [$permindex] ['slotnum'] == $slotnum [$index]) {
				echo $model->updateSlot ( $data [$index], $bookbuffer [$index], $permSlotIDs [$permindex] ['slotID'] );
				$index ++;
			}
		}
		
		return 'updated';
	}
	public function showAgenda() {
		$model = new Slot ();
		
		$exist = Agenda::find ()->where ( [ 
				'agendaID' => $this->agendaID 
		] )->one ();
		
		$permAgendaID = Agenda::find ()->where ( [ 
				'owner' => $exist ['owner'],
				'type' => 'perm' 
		] )->one ();
		
		$tempSlotIDs = array ();
		if ($exist ['type'] == 'temp') {
			$tempSlotIDs = $model->getIDs ( $this->agendaID );
		}
		
		$permSlotIDs = $model->getIDs ( $permAgendaID ['agendaID'] );
		
		$permindex = 0;
		$tempindex = 0;
		$agendforShow = array ();
		
		$index = 0;
		
		while ( $permindex < sizeof ( $permSlotIDs ) && $tempindex < sizeof ( $tempSlotIDs ) ) {
			if ($tempSlotIDs [$tempindex] ['slotnum'] == $permSlotIDs [$permindex] ['slotnum']) {
				$agendforShow [$index] ['slotID'] = $tempSlotIDs [$tempindex] ['slotID'];
				$agendforShow [$index] ['maxBookers'] = $tempSlotIDs [$tempindex] ['numberOfBookers'];
				$agendforShow [$index] ['content'] = $tempSlotIDs [$tempindex] ['content'];
				$count = Book::find ()->where ( [ 
						'slotID' => $agendforShow [$index] ['slotID'],
						'date' => $exist ['lastUpdate'] 
				] )->count ();
				
				$agendforShow [$index] ['bookCount'] = $count;
				$agendforShow [$index] ['exception'] = '1';
				
				$tempindex ++;
				$index ++;
			} else {
				
				$agendforShow [$index] ['slotID'] = $permSlotIDs [$permindex] ['slotID'];
				$agendforShow [$index] ['maxBookers'] = $permSlotIDs [$permindex] ['numberOfBookers'];
				$agendforShow [$index] ['content'] = $permSlotIDs [$permindex] ['content'];
				$count = Book::find ()->where ( [ 
						'slotID' => $agendforShow [$index] ['slotID'],
						'date' => $exist ['lastUpdate'] 
				] )->count ();
				$agendforShow [$index] ['bookCount'] = $count;
				$agendforShow [$index] ['exception'] = '0';
				
				$index ++;
			}
			$permindex ++;
		}
		
		while ( $tempindex < sizeof ( $tempSlotIDs ) ) {
			$agendforShow [$index] ['slotID'] = $tempSlotIDs [$tempindex] ['slotID'];
			$agendforShow [$index] ['maxBookers'] = $tempSlotIDs [$tempindex] ['numberOfBookers'];
			$agendforShow [$index] ['content'] = $tempSlotIDs [$tempindex] ['content'];
			$count = Book::find ()->where ( [ 
					'slotID' => $agendforShow [$index] ['slotID'],
					'date' => $exist ['lastUpdate'] 
			] )->count ();
			$agendforShow [$index] ['bookCount'] = $count;
			$agendforShow [$index] ['exception'] = '1';
			$tempindex ++;
			$index ++;
		}
		
		while ( $permindex < sizeof ( $permSlotIDs ) ) {
			$agendforShow [$index] ['slotID'] = $permSlotIDs [$permindex] ['slotID'];
			$agendforShow [$index] ['maxBookers'] = $permSlotIDs [$permindex] ['numberOfBookers'];
			$agendforShow [$index] ['content'] = $permSlotIDs [$permindex] ['content'];
			$count = Book::find ()->where ( [ 
					'slotID' => $agendforShow [$index] ['slotID'],
					'date' => $exist ['lastUpdate'] 
			] )->count ();
			$agendforShow [$index] ['bookCount'] = $count;
			$agendforShow [$index] ['exception'] = '0';
			$permindex ++;
			$index ++;
		}
		return $agendforShow;
	}
	public function showForBook($studentID) {
		$model = new Slot ();
		
		$exist = Agenda::find ()->where ( [ 
				'agendaID' => $this->agendaID 
		] )->one ();
		
		$permAgendaID = Agenda::find ()->where ( [ 
				'owner' => $exist ['owner'],
				'type' => 'perm' 
		] )->one ();
		
		$tempSlotIDs = array ();
		if ($exist ['type'] == 'temp') {
			$tempSlotIDs = $model->getIDs ( $this->agendaID );
		}
		
		$permSlotIDs = $model->getIDs ( $permAgendaID ['agendaID'] );
		
		$permindex = 0;
		$tempindex = 0;
		$agendforShow = array ();
		
		$index = 0;
		
		while ( $permindex < sizeof ( $permSlotIDs ) && $tempindex < sizeof ( $tempSlotIDs ) ) {
			if ($tempSlotIDs [$tempindex] ['slotnum'] == $permSlotIDs [$permindex] ['slotnum']) {
				$agendforShow [$index] ['slotID'] = $tempSlotIDs [$tempindex] ['slotID'];
				$agendforShow [$index] ['maxBookers'] = $tempSlotIDs [$tempindex] ['numberOfBookers'];
				$agendforShow [$index] ['content'] = $tempSlotIDs [$tempindex] ['content'];
				
				$count = Book::find ()->where ( [ 
						'slotID' => $agendforShow [$index] ['slotID'],
						'date' => $exist ['lastUpdate'] 
				] )->count ();
				
				$agendforShow [$index] ['bookCount'] = $count;
				$booked = Book::find ()->where ( [ 
						'slotID' => $agendforShow [$index] ['slotID'],
						'date' => $exist ['lastUpdate'],
						'studentID' => $studentID 
				] )->asArray ()->all ();
				if ($booked != null) {
					$agendforShow [$index] ['booked'] = '1';
				} else
					$agendforShow [$index] ['booked'] = '0';
				
				$tempindex ++;
				$index ++;
			} else {
				
				$agendforShow [$index] ['slotID'] = $permSlotIDs [$permindex] ['slotID'];
				$agendforShow [$index] ['maxBookers'] = $permSlotIDs [$permindex] ['numberOfBookers'];
				$agendforShow [$index] ['content'] = $permSlotIDs [$permindex] ['content'];
				$count = Book::find ()->where ( [ 
						'slotID' => $agendforShow [$index] ['slotID'],
						'date' => $exist ['lastUpdate'] 
				] )->count ();
				$agendforShow [$index] ['bookCount'] = $count;
				$booked = Book::find ()->where ( [ 
						'slotID' => $agendforShow [$index] ['slotID'],
						'date' => $exist ['lastUpdate'],
						'studentID' => $studentID 
				] )->asArray ()->all ();
				if ($booked != null) {
					$agendforShow [$index] ['booked'] = '1';
				} else
					$agendforShow [$index] ['booked'] = '0';
				
				$index ++;
			}
			$permindex ++;
		}
		
		while ( $tempindex < sizeof ( $tempSlotIDs ) ) {
			$agendforShow [$index] ['slotID'] = $tempSlotIDs [$tempindex] ['slotID'];
			$agendforShow [$index] ['maxBookers'] = $tempSlotIDs [$tempindex] ['numberOfBookers'];
			$agendforShow [$index] ['content'] = $tempSlotIDs [$tempindex] ['content'];
			$count = Book::find ()->where ( [ 
					'slotID' => $agendforShow [$index] ['slotID'],
					'date' => $exist ['lastUpdate'] 
			] )->count ();
			$agendforShow [$index] ['bookCount'] = $count;
			$booked = Book::find ()->where ( [ 
					'slotID' => $agendforShow [$index] ['slotID'],
					'date' => $exist ['lastUpdate'],
					'studentID' => $studentID 
			] )->asArray ()->all ();
			if ($booked != null) {
				$agendforShow [$index] ['booked'] = '1';
			} else
				$agendforShow [$index] ['booked'] = '0';
			
			$tempindex ++;
			$index ++;
		}
		
		while ( $permindex < sizeof ( $permSlotIDs ) ) {
			$agendforShow [$index] ['slotID'] = $permSlotIDs [$permindex] ['slotID'];
			$agendforShow [$index] ['maxBookers'] = $permSlotIDs [$permindex] ['numberOfBookers'];
			$agendforShow [$index] ['content'] = $permSlotIDs [$permindex] ['content'];
			$count = Book::find ()->where ( [ 
					'slotID' => $agendforShow [$index] ['slotID'],
					'date' => $exist ['lastUpdate'] 
			] )->count ();
			$agendforShow [$index] ['bookCount'] = $count;
			$booked = Book::find ()->where ( [ 
					'slotID' => $agendforShow [$index] ['slotID'],
					'date' => $exist ['lastUpdate'],
					'studentID' => $studentID 
			] )->asArray ()->all ();
			if ($booked != null) {
				$agendforShow [$index] ['booked'] = '1';
			} else
				$agendforShow [$index] ['booked'] = '0';
			
			$permindex ++;
			$index ++;
		}
		return $agendforShow;
	}
	/*
	 * public function showAgenda() {
	 * $model = new Slot ();
	 *
	 * $exist = Agenda::find ()->where ( [
	 * 'owner' => $this->owner,
	 * 'lastUpdate' => $this->lastUpdate
	 * ] )->one ();
	 * if ($exist == null) {
	 * $exist = Agenda::find ()->where ( [
	 * 'owner' => $this->owner,
	 * 'type' => 'perm'
	 * ] )->one ();
	 * }
	 * $permAgendaID = Agenda::find ()->where ( [
	 * 'owner' => $exist ['owner'],
	 * 'type' => 'perm'
	 * ] )->one ();
	 *
	 * $tempSlotIDs = array ();
	 * if ($exist ['type'] == 'temp') {
	 * $tempSlotIDs = $model->getIDs ( $this->agendaID );
	 * }
	 *
	 * $permSlotIDs = $model->getIDs ( $permAgendaID ['agendaID'] );
	 *
	 * $permindex = 0;
	 * $tempindex = 0;
	 * $agendforShow = array ();
	 * $index = 0;
	 *
	 * while ( $permindex < sizeof ( $permSlotIDs ) && $tempindex < sizeof ( $tempSlotIDs ) ) {
	 * if ($tempSlotIDs [$tempindex] ['slotnum'] == $permSlotIDs [$permindex]) {
	 * $agendforShow [$index] ['slotID'] = $tempSlotIDs [$tempindex] ['slotID'];
	 * $agendforShow [$index] ['maxBookers'] = $tempSlotIDs [$tempindex] ['numberOfBookers'];
	 * $agendforShow [$index] ['bookCount'] = $tempSlotIDs [$tempindex] ['bookCount'];
	 * $agendforShow [$index] ['content'] = $tempSlotIDs [$tempindex] ['content'];
	 * $tempindex ++;
	 * $index ++;
	 * } else {
	 * $agendforShow [$index] ['slotID'] = $permSlotIDs [$permindex] ['slotID'];
	 * $agendforShow [$index] ['maxBookers'] = $permSlotIDs [$permindex] ['numberOfBookers'];
	 * $agendforShow [$index] ['bookCount'] = $permSlotIDs [$permindex] ['bookCount'];
	 * $agendforShow [$index] ['content'] = $permSlotIDs [$permindex] ['content'];
	 *
	 * $permindex ++;
	 * $index ++;
	 * }
	 * }
	 *
	 * while ( $tempindex < sizeof ( $tempSlotIDs ) ) {
	 * $agendforShow [$index] ['slotID'] = $tempSlotIDs [$tempindex] ['slotID'];
	 * $agendforShow [$index] ['maxBookers'] = $tempSlotIDs [$tempindex] ['numberOfBookers'];
	 * $agendforShow [$index] ['bookCount'] = $tempSlotIDs [$tempindex] ['bookCount'];
	 * $agendforShow [$index] ['content'] = $tempSlotIDs [$tempindex] ['content'];
	 * $tempindex ++;
	 * $index ++;
	 * }
	 *
	 * while ( $permindex < sizeof ( $permSlotIDs ) ) {
	 * $agendforShow [$index] ['slotID'] = $permSlotIDs [$permindex] ['slotID'];
	 * $agendforShow [$index] ['maxBookers'] = $permSlotIDs [$permindex] ['numberOfBookers'];
	 * $agendforShow [$index] ['bookCount'] = $permSlotIDs [$permindex] ['bookCount'];
	 * $agendforShow [$index] ['content'] = $permSlotIDs [$permindex] ['content'];
	 * $permindex ++;
	 * $index ++;
	 * }
	 * return $agendforShow;
	 * }
	 */
}
