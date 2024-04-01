package com.credibanco.smartpos.data.service


import com.credibanco.smartpos.model.AuthorizationTransactionRequestModel
import com.credibanco.smartpos.model.AuthorizationTransactionResponseModel
import com.credibanco.smartpos.model.AnnulmentTransactionRequestModel
import com.credibanco.smartpos.model.AnnulmentTransactionResponseModel
import com.credibanco.smartpos.data.path.GeneralPaths
import retrofit2.Call
import retrofit2.http.*

interface GeneralService {


//---------------------------- Placa y Rutas -----------

    @POST(GeneralPaths.auth_transaction)
    fun postAuthTransaction(
        @Body request: AuthorizationTransactionRequestModel
    ): Call<AuthorizationTransactionResponseModel>

    @POST(GeneralPaths.annul_transaction)
    fun setAnnulTransaction(
        @Header("Authorization") token: String,
        @Body request: AnnulmentTransactionRequestModel
    ): Call<AnnulmentTransactionResponseModel>

}