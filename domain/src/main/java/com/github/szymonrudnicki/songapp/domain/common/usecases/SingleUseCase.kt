package com.github.szymonrudnicki.songapp.domain.common.usecases

import io.reactivex.Single

abstract class SingleUseCase<Results, in Params>(private val schedulersFacade: SchedulersFacade) {

    abstract fun buildUseCaseSingle(params: Params? = null): Single<Results>

    fun execute(params: Params? = null) = buildUseCaseSingleWithSchedulers(params)

    private fun buildUseCaseSingleWithSchedulers(params: Params?): Single<Results> =
            buildUseCaseSingle(params)
                    .observeOn(schedulersFacade.main())

    protected fun <Result> Single<Result>.subscribeOnMain(): Single<Result> =
            this.subscribeOn(schedulersFacade.main())

    protected fun <Result> Single<Result>.subscribeOnIO(): Single<Result> =
            this.subscribeOn(schedulersFacade.io())
}