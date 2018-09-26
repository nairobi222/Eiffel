package com.etiennelenhart.eiffel.state

/**
 * Base class for one-off view model actions that require an Activity.
 *
 * Can be used as a property in [ViewState]s to signal an action to the observer:
 *
 * ```
 * data class SampleViewState(..., val event: SampleViewEvent? = null) : ViewState
 * ```
 *
 * Extending classes are best implemented as 'sealed':
 *
 * ```
 * sealed class SampleViewEvent : ViewEvent() {
 *     class ShowSample : SampleViewEvent()
 *     class DoNotCare : SampleViewEvent()
 * }
 * ```
 *
 * Observers can then check for unhandled events like this:
 *
 * ```
 * viewModel.observeState(this) { state ->
 *     state.event?.peek {
 *         when (it) {
 *             is SampleViewEvent.ShowSample -> {
 *                 ...
 *                 true
 *             }
 *             else -> false
 *         }
 *     }
 * }
 * ```
 */
abstract class ViewEvent {

    private var alreadyHandled = false

    /**
     * Allows observers to look at the event's type and decide if they can handle it.
     *
     * @param[handled] Lambda expression that is called when this event is unhandled. Should return `true` if the event
     * was handled and `false` if it was ignored or could not be handled.
     */
    fun peek(handled: (event: ViewEvent) -> Boolean) {
        if (!alreadyHandled) alreadyHandled = handled(this)
    }
}
