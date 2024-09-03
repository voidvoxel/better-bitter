package dev.voidvoxel.betterbitter.api

/**
 * An `Entity` that can move.
 */
interface MovingEntity {
    /**
     * Returns `true` if the `Entity` is moving. Returns `false` in all other scenarios.
     *
     * @return Whether the `Entity` is moving.
     */
    fun `betterBitter$isMoving`(): Boolean

    /**
     * Returns `true` if the `Entity` is moving horizontally. Returns `false` in all other scenarios.
     *
     * @return Whether the `Entity` is moving horizontally.
     */
    fun `betterBitter$isMovingHorizontally`(): Boolean

    /**
     * Returns `true` if the `Entity` is moving vertically. Returns `false` in all other scenarios.
     *
     * @return Whether the `Entity` is moving vertically.
     */
    fun `betterBitter$isMovingVertically`(): Boolean
}
