package kr.co.seoft.ca.domain.usecase.common

import io.reactivex.Scheduler

interface RxScheduler {
    val subscribeOn: Scheduler
    val observeOn: Scheduler
}
