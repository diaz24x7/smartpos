package com.credibanco.smartpos.model

class AuthorizationTransactionResponseModel (
    val receiptId: String?,
    val rrn: String?,
    val statusCode: String,
    val statusDescription: String
)