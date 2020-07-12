package com.blank.chapter10.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blank.chapter10.data.AppDataManager
import com.blank.chapter10.utils.api.ResultState
import com.blank.chapter10.utils.api.getResultStateError
import com.blank.teamb_ex.BodyRegister
import io.reactivex.disposables.CompositeDisposable

class RegisterViewModel(private val appDataManager: AppDataManager) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val resultStateResponseRegister = MutableLiveData<ResultState>()

    fun register(bodyRegister: BodyRegister) {
        compositeDisposable.add(
            appDataManager.register(bodyRegister)
                .doOnSubscribe {
                    resultStateResponseRegister.postValue(ResultState.Loading)
                }
                .subscribe({
                    resultStateResponseRegister.postValue(it)
                }, {
                    resultStateResponseRegister.postValue(getResultStateError(it))
                })
        )
    }

    class Factory(private val appDataManager: AppDataManager) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RegisterViewModel(appDataManager) as T
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}