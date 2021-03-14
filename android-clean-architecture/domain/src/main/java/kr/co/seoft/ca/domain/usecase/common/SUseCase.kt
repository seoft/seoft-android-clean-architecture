package kr.co.seoft.ca.domain.usecase.common

import io.reactivex.Single

abstract class SUseCase<in Param, Result> {

    abstract val scheduler: RxScheduler

    internal abstract fun implement(param: Param): Single<Result>

    fun execute(param: Param): Single<Result> {
        return implement(param).subscribeOn(scheduler.subscribeOn).observeOn(scheduler.observeOn)
    }
}