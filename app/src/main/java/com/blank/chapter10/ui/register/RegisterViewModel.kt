package com.blank.chapter10.ui.register

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.blank.chapter10.data.AppDataManager
import com.blank.chapter10.data.local.db.entity.User
import com.blank.chapter10.utils.api.ResultState
import com.blank.chapter10.utils.api.getResultStateError
import com.blank.teamb_ex.BodyRegister
import io.reactivex.disposables.CompositeDisposable

class RegisterViewModel @ViewModelInject constructor(
    private val appDataManager: AppDataManager,
    @Assisted private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val resultStateResponseRegister = MutableLiveData<ResultState>()
    val resultStateInserDb = MutableLiveData<ResultState>()

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

    fun insertUser(user: User) {
        compositeDisposable.add(
            appDataManager.insertUser(user)
                .subscribe({
                    resultStateInserDb.postValue(ResultState.Success(it, ""))
                }, {
                    resultStateInserDb.postValue(ResultState.Error(it))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}