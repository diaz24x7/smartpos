package com.credibanco.smartpos.ui.annulment

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
import com.credibanco.smartpos.data.model.AuthorizationRequestModel
import com.credibanco.smartpos.data.model.AuthorizationResponseModel
import com.credibanco.smartpos.data.service.ApiClient
import com.credibanco.smartpos.databinding.FragmentAnnulmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnnulmentFragment : Fragment() {

    private var _binding: FragmentAnnulmentBinding? = null

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

        progress = ProgressDialog(requireContext())
//        progress.setMessage(resources.getString(R.string.loading))
        progress.setCancelable(false)

        // Inflate the layout for this fragment
        apiClient = ApiClient()

        val annulmentViewModel =
            ViewModelProvider(this)[AnnulmentViewModel::class.java]

        _binding = FragmentAnnulmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        annulmentViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }


    private fun putAuthTransaction(asignacionDiaria: AuthorizationRequestModel) {
        Log.e("put", arguments?.get("myArg").toString())
        apiClient.getGeneralService(this.requireContext(),progress)
            .postAuthTransaction(
                request = asignacionDiaria
            )
            .enqueue(object : Callback<AuthorizationResponseModel> {
                override fun onResponse(
                    call: Call<AuthorizationResponseModel>,
                    response: Response<AuthorizationResponseModel>
                ) {
                    if(apiClient.enqueueOnResponse("putAuthTransaction", response.code(), "ARRAY", response.toString(), progress,requireContext(),requireActivity())){
                        /*MaterialAlertDialogBuilder(requireContext())
                            .setTitle(resources.getString(R.string.successful_register))
                            .setPositiveButton("OK") { _, _ ->  }
                            .setOnDismissListener { buttonCancel.callOnClick() }
                            .show()*/
                    }
                }
                override fun onFailure(call: Call<AuthorizationResponseModel>, t: Throwable) {
                    apiClient.enqueueOnFailure("putAuthTransaction", progress, t, requireContext())
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}