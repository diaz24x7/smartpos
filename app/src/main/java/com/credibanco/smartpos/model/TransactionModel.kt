package com.credibanco.smartpos.model

class TransactionModel (
    val id: String,
    val commerceCode: String,
    val terminalCode: String,
    val amount: String,
    val card: String,
    val receiptId: String?,
    val rrn: String?,
    val statusCode: String,
    val statusDescription: String,
)