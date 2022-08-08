package id.iglo.common.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavDirections

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val navigationEvent = SingleLiveEvent<NavDirections>()
    val popBackEvent = SingleLiveEvent<Any>()

    fun navigate(navDirections: NavDirections){
        navigationEvent.postValue(navDirections)
    }

    fun popBack(){
        popBackEvent.postValue(Any())
    }

}