package com.credibanco.smartpos.data.model

class AuthorizationResponseModel (
    val receiptId: String?,
    val rrn: String?,
    val statusCode: String,
    val statusDescription: String
)