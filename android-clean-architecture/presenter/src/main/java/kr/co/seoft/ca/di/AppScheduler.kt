package kr.co.seoft.ca.di

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.co.seoft.ca.domain.usecase.common.RxScheduler

class AppScheduler : RxScheduler {

    override val subscribeOn: Scheduler = Schedulers.io()

    override val observeOn: Scheduler = AndroidSchedulers.mainThread()
}
