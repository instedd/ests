<?php
$arr=array("pageItems"=>array(array("id"=>1,"accountNo"=>"000000001",
"depositType"=>array("id"=>100,"code"=>"depositAccountType.savingsDeposit",
"value"=>"savings","currency"=>array("id"=>10,"code"=>"UGX",
"value"=>"Uganda Shillings")),"status"=>array("name"=>"active","status_pro"=>array("name"=>"pro")))));

$arr2=array("pageItems"=>array(array("id"=>1,"accountNo"=>"000000001",
"depositType"=>array("id"=>100,"code"=>"depositAccountType.savingsDeposit",
"value"=>"savings","currency"=>array("id"=>10,"code"=>"UGX",
"value"=>"Uganda Shillings")),"status"=>array("name"=>"active","status_pro"=>array("name"=>"pro")))));
	  //print_r($arr->pageItems[0]->depositType->id);
	 $json=json_encode($arr);
	 $obj=json_decode($json);
	 //print_r($obj);
	 //print_r($obj->pageItems[0]->status->status_pro->name);
$json_array='{
  "id": 3,
  "transactions": [
    
    {
      "id": 6,
      "transactionType": {
        "id": 2,
        "value": "Withdrawal"
      },
      "accountId": 3,
      "accountNo": "000000003",
      "date": [
        2018,
        9,
        6
      ],
      "currency": {
        "code": "UGX"
      },
      "amount": 300000.000000,
      "runningBalance": 2500000.000000,
      "submittedOnDate": [
        2018,
        9,
        6
      ]
    },
    {
      "id": 5,
      "transactionType": {
        "id": 2,
        "value": "Withdrawal"
      },
      "accountId": 3,
      "accountNo": "000000003",
      "date": [
        2018,
        9,
        6
      ],
      "currency": {
        "code": "UGX"
      },
      "amount": 200000.000000,
      "runningBalance": 2800000.000000,
      "submittedOnDate": [
        2018,
        9,
        6
      ]
    },
    {
      "id": 4,
      "transactionType": {
        "id": 1,
        "value": "Deposit"
      },
      "accountId": 3,
      "accountNo": "000000003",
      "date": [
        2018,
        9,
        6
      ],
      "currency": {
        "code": "UGX"
      },
      "amount": 3000000.000000,
      "runningBalance": 3000000.000000,
      "submittedOnDate": [
        2018,
        9,
        6
      ]
    }
  ]
}';
$obj1=json_decode($json_array);
//print_r($obj1->transactions[1]->amount);
//print_r($obj1->transactions[1]->runningBalance);
$count=count($obj1->transactions);
$i=0;
for($i=0;$i<$count;$i++)
{
$year=$obj1->transactions[$i]->submittedOnDate[0];
$month=$obj1->transactions[$i]->submittedOnDate[1];
$day=$obj1->transactions[$i]->submittedOnDate[2];
$date=$year."-".$month."-".$day;
print_r date_format('m/J/Y',$date);	
}

?>
	  