package com.credibanco.smartpos.data

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import com.credibanco.smartpos.data.path.GeneralPaths
import com.credibanco.smartpos.data.service.GeneralService
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    private lateinit var generalService: GeneralService

    fun getGeneralService(context: Context, progress: ProgressDialog): GeneralService {
        progress.show()
        if (!::generalService.isInitialized) {
            val retrofit = getRetrofit(context, GeneralPaths.BaseURL)
            generalService = retrofit.create(GeneralService::class.java)
        }
        return generalService
    }

    fun getRetrofit(context: Context, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl.toHttpUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient(context)) // Add our Okhttp client
            .build()
    }

    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.MINUTES)
            .writeTimeout(30, TimeUnit.MINUTES)
            .build()
    }

    fun enqueueOnResponse(funName: String = "", code: Int, message: String = "", body: String = "", progress:ProgressDialog, context: Context, activity: Activity?): Boolean {
        progress.dismiss()
        Log.e("enqueueOnResponse " + code, "$funName \n $message \n $body")

        return when (code) {
            in 200..300 -> true
            else -> {
                MaterialAlertDialogBuilder(context)
                    .setTitle("Problemas al realizar el Proceso")
                    .setMessage("Por favor comunicarce con el área de Desarrollo")
                    .setPositiveButton("Aceptar") { _, _ -> }.show()
                return false
            }
        }
    }

    fun enqueueOnFailure(funName: String, progress:ProgressDialog, t: Throwable, context: Context){
        progress.dismiss()
        Log.e("enqueueOnFailure", "$funName \n" + t.toString())
        MaterialAlertDialogBuilder(context)
            .setTitle("Problemas al realizar el Proceso")
            .setMessage("Por favor comunicarce con el área de Desarrollo")
            .setPositiveButton("Aceptar") { _, _ -> }.show()
    }
}