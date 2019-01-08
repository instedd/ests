{
  "id": 3,
  "accountNo": "000000003",
  "depositType": {
    "id": 100,
    "code": "depositAccountType.savingsDeposit",
    "value": "Savings"
  },
  "clientId": 12,
  "clientName": "Nduhukire Medard",
  "savingsProductId": 5,
  "savingsProductName": "WT - 3",
  "fieldOfficerId": 0,
  "status": {
    "id": 300,
    "code": "savingsAccountStatusType.active",
    "value": "Active",
    "submittedAndPendingApproval": false,
    "approved": false,
    "rejected": false,
    "withdrawnByApplicant": false,
    "active": true,
    "closed": false,
    "prematureClosed": false,
    "transferInProgress": false,
    "transferOnHold": false,
    "matured": false
  },
  "subStatus": {
    "id": 0,
    "code": "SavingsAccountSubStatusEnum.none",
    "value": "None",
    "none": true,
    "inactive": false,
    "dormant": false,
    "escheat": false,
    "block": false,
    "blockCredit": false,
    "blockDebit": false
  },
  "timeline": {
    "submittedOnDate": [
      2018,
      9,
      6
    ],
    "submittedByUsername": "mifos",
    "submittedByFirstname": "App",
    "submittedByLastname": "Administrator",
    "approvedOnDate": [
      2018,
      9,
      6
    ],
    "approvedByUsername": "mifos",
    "approvedByFirstname": "App",
    "approvedByLastname": "Administrator",
    "activatedOnDate": [
      2018,
      9,
      6
    ],
    "activatedByUsername": "mifos",
    "activatedByFirstname": "App",
    "activatedByLastname": "Administrator"
  },
  "currency": {
    "code": "UGX",
    "name": "Uganda Shilling",
    "decimalPlaces": 2,
    "inMultiplesOf": 100,
    "displaySymbol": "USh",
    "nameCode": "currency.UGX",
    "displayLabel": "Uganda Shilling (USh)"
  },
  "nominalAnnualInterestRate": 0.000000,
  "interestCompoundingPeriodType": {
    "id": 4,
    "code": "savings.interest.period.savingsCompoundingInterestPeriodType.monthly",
    "value": "Monthly"
  },
  "interestPostingPeriodType": {
    "id": 4,
    "code": "savings.interest.posting.period.savingsPostingInterestPeriodType.monthly",
    "value": "Monthly"
  },
  "interestCalculationType": {
    "id": 1,
    "code": "savingsInterestCalculationType.dailybalance",
    "value": "Daily Balance"
  },
  "interestCalculationDaysInYearType": {
    "id": 365,
    "code": "savingsInterestCalculationDaysInYearType.days365",
    "value": "365 Days"
  },
  "withdrawalFeeForTransfers": false,
  "allowOverdraft": false,
  "enforceMinRequiredBalance": false,
  "withHoldTax": false,
  "lastActiveTransactionDate": [
    2018,
    9,
    6
  ],
  "isDormancyTrackingActive": false,
  "summary": {
    "currency": {
      "code": "UGX",
      "name": "Uganda Shilling",
      "decimalPlaces": 2,
      "inMultiplesOf": 100,
      "displaySymbol": "USh",
      "nameCode": "currency.UGX",
      "displayLabel": "Uganda Shilling (USh)"
    },
    "totalDeposits": 3100000.000000,
    "totalWithdrawals": 500000.000000,
    "totalInterestPosted": 0,
    "accountBalance": 2600000.000000,
    "totalOverdraftInterestDerived": 0,
    "interestNotPosted": 0,
    "availableBalance": 2600000.000000
  },
  "transactions": [
    {
      "id": 7,
      "transactionType": {
        "id": 1,
        "code": "savingsAccountTransactionType.deposit",
        "value": "Deposit",
        "deposit": true,
        "dividendPayout": false,
        "withdrawal": false,
        "interestPosting": false,
        "feeDeduction": false,
        "initiateTransfer": false,
        "approveTransfer": false,
        "withdrawTransfer": false,
        "rejectTransfer": false,
        "overdraftInterest": false,
        "writtenoff": false,
        "overdraftFee": true,
        "withholdTax": false,
        "escheat": false,
        "amountHold": false,
        "amountRelease": false
      },
      "accountId": 3,
      "accountNo": "000000003",
      "date": [
        2018,
        9,
        6
      ],
      "currency": {
        "code": "UGX",
        "name": "Uganda Shilling",
        "decimalPlaces": 2,
        "inMultiplesOf": 100,
        "displaySymbol": "USh",
        "nameCode": "currency.UGX",
        "displayLabel": "Uganda Shilling (USh)"
      },
      "amount": 100000.000000,
      "runningBalance": 2600000.000000,
      "reversed": false,
      "submittedOnDate": [
        2018,
        9,
        6
      ],
      "interestedPostedAsOn": false,
      "submittedByUsername": "mifos"
    },
    {
      "id": 6,
      "transactionType": {
        "id": 2,
        "code": "savingsAccountTransactionType.withdrawal",
        "value": "Withdrawal",
        "deposit": false,
        "dividendPayout": false,
        "withdrawal": true,
        "interestPosting": false,
        "feeDeduction": false,
        "initiateTransfer": false,
        "approveTransfer": false,
        "withdrawTransfer": false,
        "rejectTransfer": false,
        "overdraftInterest": false,
        "writtenoff": false,
        "overdraftFee": true,
        "withholdTax": false,
        "escheat": false,
        "amountHold": false,
        "amountRelease": false
      },
      "accountId": 3,
      "accountNo": "000000003",
      "date": [
        2018,
        9,
        6
      ],
      "currency": {
        "code": "UGX",
        "name": "Uganda Shilling",
        "decimalPlaces": 2,
        "inMultiplesOf": 100,
        "displaySymbol": "USh",
        "nameCode": "currency.UGX",
        "displayLabel": "Uganda Shilling (USh)"
      },
      "amount": 300000.000000,
      "runningBalance": 2500000.000000,
      "reversed": false,
      "submittedOnDate": [
        2018,
        9,
        6
      ],
      "interestedPostedAsOn": false,
      "submittedByUsername": "mifos"
    },
    {
      "id": 5,
      "transactionType": {
        "id": 2,
        "code": "savingsAccountTransactionType.withdrawal",
        "value": "Withdrawal",
        "deposit": false,
        "dividendPayout": false,
        "withdrawal": true,
        "interestPosting": false,
        "feeDeduction": false,
        "initiateTransfer": false,
        "approveTransfer": false,
        "withdrawTransfer": false,
        "rejectTransfer": false,
        "overdraftInterest": false,
        "writtenoff": false,
        "overdraftFee": true,
        "withholdTax": false,
        "escheat": false,
        "amountHold": false,
        "amountRelease": false
      },
      "accountId": 3,
      "accountNo": "000000003",
      "date": [
        2018,
        9,
        6
      ],
      "currency": {
        "code": "UGX",
        "name": "Uganda Shilling",
        "decimalPlaces": 2,
        "inMultiplesOf": 100,
        "displaySymbol": "USh",
        "nameCode": "currency.UGX",
        "displayLabel": "Uganda Shilling (USh)"
      },
      "amount": 200000.000000,
      "runningBalance": 2800000.000000,
      "reversed": false,
      "submittedOnDate": [
        2018,
        9,
        6
      ],
      "interestedPostedAsOn": false,
      "submittedByUsername": "mifos"
    },
    {
      "id": 4,
      "transactionType": {
        "id": 1,
        "code": "savingsAccountTransactionType.deposit",
        "value": "Deposit",
        "deposit": true,
        "dividendPayout": false,
        "withdrawal": false,
        "interestPosting": false,
        "feeDeduction": false,
        "initiateTransfer": false,
        "approveTransfer": false,
        "withdrawTransfer": false,
        "rejectTransfer": false,
        "overdraftInterest": false,
        "writtenoff": false,
        "overdraftFee": true,
        "withholdTax": false,
        "escheat": false,
        "amountHold": false,
        "amountRelease": false
      },
      "accountId": 3,
      "accountNo": "000000003",
      "date": [
        2018,
        9,
        6
      ],
      "currency": {
        "code": "UGX",
        "name": "Uganda Shilling",
        "decimalPlaces": 2,
        "inMultiplesOf": 100,
        "displaySymbol": "USh",
        "nameCode": "currency.UGX",
        "displayLabel": "Uganda Shilling (USh)"
      },
      "amount": 3000000.000000,
      "runningBalance": 3000000.000000,
      "reversed": false,
      "submittedOnDate": [
        2018,
        9,
        6
      ],
      "interestedPostedAsOn": false,
      "submittedByUsername": "mifos"
    }
  ]
}