package com.example.mermelalogistic.core

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.mermelalogistic.R
import com.example.mermelalogistic.core.model.NavigationCommand
import com.example.mermelalogistic.ext.observe
import com.example.mermelalogistic.ext.safeNavigateFromNavController
import com.example.mermelalogistic.ext.toast
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {
    private val progressDialog by lazy {
        BaseProgressDialog(fragmentContext)
    }
    protected lateinit var fragmentContext: Context

    private var _binding: VB? = null
    protected val binding: VB get() = _binding as VB

    protected abstract val viewModel: VM
    protected abstract fun onCreateFinished()
    protected abstract fun initListeners()
    protected abstract fun observeEvents()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater.invoke(inflater)
        if (_binding == null) {
            throw IllegalArgumentException("Binding is null.")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCollectors()
        onCreateFinished()
        observeNavigate(viewModel.onNavigateState)
        initListeners()
        observeEvents()
    }

    private fun observeNavigate(onNavigateState: LiveData<Event<NavigationCommand>>) {
        observe(onNavigateState) {
            it.getContentIfNotHandled()?.let { navigationCommand ->
                handleNavigation(navigationCommand)
            }
        }
    }

    protected open fun handleNavigation(navigationCommand: NavigationCommand) {
        when (navigationCommand) {
            is NavigationCommand.ToDirection -> safeNavigateFromNavController(navigationCommand.directions)

            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }

    private fun initCollectors() {
        lifecycleScope.launchWhenStarted {
            launch {
                viewModel.isLoading.collect(::handleLoadingState)
            }
            launch {
                viewModel.toastMessageState.collect(::handleToastMessage)
            }
            /*launch {
                viewModel.errorMessageState.collect(::handleErrorMessage)
            }*/
        }
    }

    private fun handleLoadingState(isLoading: Boolean) {
        if (isLoading) {
            progressDialog.show()
        } else {
            progressDialog.dismiss()
        }
    }

    protected fun handleToastMessage(message: String?) {
        if (message == null)
            toast(getString(R.string.general_error))
        if (!message.isNullOrEmpty()) {
            toast(message)
            viewModel.toastMessageState.value = ""
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}