package com.credibanco.smartpos.data.model

class AuthorizationRequestModel (
    val id: String,
    val commerceCode: String,
    val terminalCode: String,
    val amount: String,
    val card: String
)