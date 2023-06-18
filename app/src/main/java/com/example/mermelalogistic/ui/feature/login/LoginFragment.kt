package com.example.mermelalogistic.ui.feature.login

import android.text.TextUtils
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.example.mermelalogistic.R
import com.example.mermelalogistic.core.BaseFragment
import com.example.mermelalogistic.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding,LoginViewModel>(
    FragmentLoginBinding::inflate
) {
    override val viewModel : LoginViewModel by viewModels()

    private var loginType : String = "MANUFACTURER LOGIN"

    override fun onCreateFinished() {

    }

    override fun initListeners() {
        binding.apply {
            customerLogin.setOnClickListener {
                loginType = "CUSTOMER LOGIN"
                customerLogin.background = resources.getDrawable(R.drawable.switch_trcks,null)
                customerLogin.setTextColor(resources.getColor(R.color.textColor,null))
                manufacturerLogin.background = null
                singUpLayout.visibility = View.VISIBLE
                logInLayout.visibility = View.GONE
                manufacturerLogin.setTextColor(resources.getColor(R.color.pinkColor,null))
            }
            manufacturerLogin.setOnClickListener {
                loginType = "MANUFACTURER LOGIN"
                customerLogin.background = null
                customerLogin.setTextColor(resources.getColor(R.color.pinkColor,null))
                manufacturerLogin.background = resources.getDrawable(R.drawable.switch_trcks,null)
                singUpLayout.visibility = View.GONE
                logInLayout.visibility = View.VISIBLE
                manufacturerLogin.setTextColor(resources.getColor(R.color.textColor,null))
            }
            btnLogin.setOnClickListener {
                val mFactoryId = binding.tvManufacturerId.text.toString()
                val mFactoryPassword = binding.tvManufacturerPassword.text.toString()

                val cFactoryId = binding.tvCustomerId.text.toString()
                val cFactoryPassword = binding.tvCustomerPassword.text.toString()

                if(loginType == "MANUFACTURER LOGIN"){
                    viewModel.loginManufacturer(mFactoryId,mFactoryPassword)
                }
                else if(loginType == "CUSTOMER LOGIN"){
                    viewModel.loginCustomer(cFactoryId,cFactoryPassword)
                }
            }

            tvManufacturerId.doAfterTextChanged {
                checkFieldsState()
            }
            tvManufacturerPassword.doAfterTextChanged {
                checkFieldsState()
            }
            tvCustomerId.doAfterTextChanged {
                checkFieldsState()
            }
            tvCustomerPassword.doAfterTextChanged {
                checkFieldsState()
            }
        }
    }

    private fun checkFieldsState(){
        with(binding){
            if(loginType == "MANUFACTURER LOGIN"){
                btnLogin.isEnabled =
                    !(TextUtils.isEmpty(tvManufacturerId.text) || TextUtils.isEmpty(tvManufacturerPassword.text))

            }else{
                btnLogin.isEnabled =
                    !(TextUtils.isEmpty(tvCustomerId.text) || TextUtils.isEmpty(tvCustomerPassword.text))
            }
        }
    }
    override fun observeEvents() {

    }
}