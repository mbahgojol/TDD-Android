package com.blank.chapter10.ui.login

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.blank.chapter10.data.AppDataManager
import com.blank.chapter10.data.model.BodyLogin
import com.blank.chapter10.utils.api.ResultState
import com.blank.chapter10.utils.api.getResultStateError
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel @ViewModelInject constructor(
    private val appDataManager: AppDataManager,
    @Assisted private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {
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
}
