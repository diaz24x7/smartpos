package com.credibanco.smartpos.ui.auth

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.credibanco.smartpos.R
import com.credibanco.smartpos.data.ApiClient
import com.credibanco.smartpos.databinding.FragmentAuthTransactionBinding
import com.credibanco.smartpos.model.AuthorizationTransactionRequestModel
import com.credibanco.smartpos.model.AuthorizationTransactionResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.UUID

class AuthTransactionFragment : Fragment() {

    private var _binding: FragmentAuthTransactionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var apiClient: ApiClient
    private lateinit var progress: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val authTransactionViewModel =
            ViewModelProvider(this)[AuthTransactionViewModel::class.java]

        progress = ProgressDialog(requireContext())
        progress.setMessage("Loading")
        progress.setCancelable(false)
        apiClient = ApiClient()

        _binding = FragmentAuthTransactionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        authTransactionViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        apiClient.getGeneralService(this.requireContext(),progress)
            .postAuthTransaction(
                AuthorizationTransactionRequestModel(
                    id = UUID.randomUUID().toString(),
                    commerceCode = "000123",
                    terminalCode = "000ABC",
                    amount = "12345",
                    card = "1234567890123456"
                )
            )
            .enqueue(object : Callback<AuthorizationTransactionResponseModel> {
                override fun onResponse(
                    call: Call<AuthorizationTransactionResponseModel>,
                    response: Response<AuthorizationTransactionResponseModel>
                ) {
                    if(apiClient.enqueueOnResponse("postAuthTransaction", response.code(), response.message(), response.toString(), progress,requireContext(),requireActivity())){
                        Log.e("postAuthTransaction", response.body()?.receiptId.toString())
                        Log.e("postAuthTransaction", response.body()?.rrn.toString())
                        Log.e("postAuthTransaction", response.body()?.statusCode.toString())
                        Log.e("postAuthTransaction", response.body()?.statusDescription.toString())
                    }
                }
                override fun onFailure(call: Call<AuthorizationTransactionResponseModel>, t: Throwable) {
                    apiClient.enqueueOnFailure("postAuthTransaction", progress, t, requireContext())
                }
            })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}