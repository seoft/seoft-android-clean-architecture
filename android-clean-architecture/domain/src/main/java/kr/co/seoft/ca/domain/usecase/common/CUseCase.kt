package kr.co.seoft.ca.domain.usecase.common

import io.reactivex.Completable

abstract class CUseCase<in Param> {

    abstract val scheduler: RxScheduler

    internal abstract fun implement(param: Param): Completable

    fun execute(param: Param): Completable {
        return implement(param).subscribeOn(scheduler.subscribeOn).observeOn(scheduler.observeOn)
    }
}