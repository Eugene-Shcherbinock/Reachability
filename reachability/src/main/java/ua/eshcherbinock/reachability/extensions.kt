package ua.eshcherbinock.reachability

internal fun Boolean.toState(): Reachability.State {
    return when (this) {
        true -> Reachability.State.REACHABLE
        else -> Reachability.State.NOT_REACHABLE
    }
}