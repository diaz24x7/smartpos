package com.credibanco.smartpos.model

class AuthorizationTransactionRequestModel (
    val id: String,
    val commerceCode: String,
    val terminalCode: String,
    val amount: String,
    val card: String
)