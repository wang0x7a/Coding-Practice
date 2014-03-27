/* P0902
 * Imagine a robot sitting on the upper left corner of an X by Y grid. The
 * robot can only move in two directions: right and down. How many possible
 * paths are there for the robot to go from (0, 0) from (X, Y)?
 *
 * Follow up:
 * Imagine a certain spots are "off limits", such that the robot cannot step
 * on them. Design an algorithm to find a path for the robot from the top left
 * to the bottom right.
 * */

/* Solution:
 *
 * 1) The number of possible paths from (0, 0) to (X, Y):
 *    C^X_(X+Y)
 *
 *    The probability of finding a right path:
 *    C^X_(X+Y)
 *    ---------
 *     2^(X+Y)
 *
 * 2) Key words: find a path
 *    Questions: are the "off limits" spots known in advance? Or, the robot
 *    won't know until it reaches the spots?
 *
 * */
