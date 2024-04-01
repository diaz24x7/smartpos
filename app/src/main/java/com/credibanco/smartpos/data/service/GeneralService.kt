package com.credibanco.smartpos.data.service


import com.credibanco.smartpos.data.model.AuthorizationRequestModel
import com.credibanco.smartpos.data.model.AuthorizationResponseModel
import com.credibanco.smartpos.data.model.AnnulmentRequestModel
import com.credibanco.smartpos.data.model.AnnulmentResponseModel
import retrofit2.Call
import retrofit2.http.*

interface GeneralService {


//---------------------------- Placa y Rutas -----------

    @POST(GeneralPaths.auth_transaction)
    fun postAuthTransaction(
        @Body request: AuthorizationRequestModel
    ): Call<AuthorizationResponseModel>

    @POST(GeneralPaths.annul_transaction)
    fun setAnnulTransaction(
        @Header("Authorization") token: String,
        @Body request: AnnulmentRequestModel
    ): Call<AnnulmentResponseModel>

}