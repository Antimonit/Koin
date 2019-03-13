package me.khol.koin.repository

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

interface OnboardingRepository {

    fun observeNavigation(): Observable<Action>
    fun observeStep(): Observable<Step>
    fun nextStep()
    fun previousStep()
}

class OnboardingRepositoryImpl : OnboardingRepository {

    private val actionSubject = PublishSubject.create<Action>()
    private val stepSubject = BehaviorSubject.createDefault(Step.ONE)
    private val step: Step get() = stepSubject.value!!

    override fun observeNavigation(): Observable<Action> = actionSubject
    override fun observeStep(): Observable<Step> = stepSubject

    override fun nextStep() {
        when (step) {
            Step.ONE -> stepSubject.onNext(Step.TWO)
            Step.TWO -> stepSubject.onNext(Step.THREE)
            Step.THREE -> actionSubject.onNext(Action.FINISH)
        }
    }

    override fun previousStep() {
        when (step) {
            Step.ONE -> actionSubject.onNext(Action.BACK)
            Step.TWO -> stepSubject.onNext(Step.ONE)
            Step.THREE -> stepSubject.onNext(Step.TWO)
        }
    }
}