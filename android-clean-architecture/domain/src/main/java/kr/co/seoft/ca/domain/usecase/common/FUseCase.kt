package kr.co.seoft.ca.domain.usecase.common

import io.reactivex.Flowable

abstract class FUseCase<in Param, Result> {

    abstract val scheduler: RxScheduler

    internal abstract fun implement(param: Param): Flowable<Result>

    fun execute(param: Param): Flowable<Result> {
        return implement(param).subscribeOn(scheduler.subscribeOn).observeOn(scheduler.observeOn)
    }
}