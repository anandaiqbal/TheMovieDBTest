package id.iglo.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import id.iglo.common.BR

abstract class BaseFragment<VM : BaseViewModel, Binding : ViewDataBinding> : Fragment() {

    abstract val vm: VM
    lateinit var binding: Binding
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.navigationEvent.observe(this) {
            findNavController().navigate(it)
        }
        vm.popBackEvent.observe(this) {
            findNavController().popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.setVariable(BR.vm, vm)
        binding.lifecycleOwner = this
        initBinding(binding)
        return binding.root
    }

    open fun initBinding(binding: Binding) {}

    fun <T> observeResponseData(
        data: MutableLiveData<AppResponse<T>>,
        success: ((T) -> Unit),
        failure: ((Throwable) -> Unit)?,
        loading: (() -> Unit)? = {}
    ) {
        data.observe(this) { response ->
            when (response) {
                is AppResponse.AppResponseSuccess -> {
                    success.invoke(response.data)
                }
                is AppResponse.AppResponseFailure -> {
                    response.e?.let {
                        failure?.invoke(it)
                    }
                }
                is AppResponse.AppResponseLoading -> {
                    loading?.invoke()
                }
            }
        }
    }
}