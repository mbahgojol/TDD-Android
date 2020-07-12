package com.blank.chapter10.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blank.chapter10.data.AppDataManager
import com.blank.chapter10.data.model.BodyLogin
import com.blank.chapter10.utils.api.ResultState
import com.blank.chapter10.utils.api.getResultStateError
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel(private val appDataManager: AppDataManager) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val stateResponseLogin = MutableLiveData<ResultState>()

    fun login(bodyLogin: BodyLogin) {
        compositeDisposable.add(
            appDataManager.login(bodyLogin)
                .doOnSubscribe {
                    stateResponseLogin.postValue(ResultState.Loading)
                }
                .subscribe({
                    stateResponseLogin.postValue(it)
                }, {
                    stateResponseLogin.postValue(getResultStateError(it))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    class Factory(private val appDataManager: AppDataManager) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(appDataManager) as T
        }
    }
}
