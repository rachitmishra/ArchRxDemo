interface BaseSchedulerProvider {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun io(): Scheduler
    fun network(): Executor // Add for paging library, for Rx use-cases prefer BaseSchedulerProvider.io()
}
