package com.example.game2048.controller

import kotlin.random.Random

class AppController {

    private var matrix = generateBasicMatrix()
    //   arrayOf(
//        arrayOf(2, 4, 8, 16),  // 16 2 8 8
//        arrayOf(4, 0, 4, 8),  // 4 16 4 0
//        arrayOf(8, 4, 2, 4),  // 2 0 8 0
//        arrayOf(0, 8, 4, 2)   // 0 0 2 0
//    )
    private val newElementAmount = 2

    init {
        addNewElement()
        addNewElement()
    }

    private fun generateBasicMatrix() = arrayOf(
        arrayOf(0, 0, 0, 0),
        arrayOf(0, 0, 0, 0),
        arrayOf(0, 0, 0, 0),
        arrayOf(0, 0, 0, 0)
    )
    private fun addNewElement() {
        val ls = ArrayList<Int>()
        for (i in matrix.indices) {
            for (j in  matrix[i].indices) {
                if (matrix[i][j] == 0) ls.add(i * 4 +j)
            }
        }
        if (ls.isEmpty()) return
        val random = Random.nextInt(from = 0, until =ls.size)  // 1
        matrix[ls[random] / 4][ls[random] % 4] = newElementAmount
    }

    fun getMatrix() : Array<Array<Int>> = matrix

    fun moveLeft() {
        val newMatrix = generateBasicMatrix()
        for (i in 0 until 4) {
            var index = 0
            var isAdd = true
            for (j in 0 until 4) {
                if (matrix[i][j] == 0) continue
                if (newMatrix[i][0] == 0) {
                    newMatrix[i][0] = matrix[i][j]
                    continue
                }
                if (matrix[i][j] == newMatrix[i][index] && isAdd) {
                    newMatrix[i][index] *=2
                    isAdd = false
                } else {
                    newMatrix[i][++index] = matrix[i][j]
                    isAdd = true
                }
            }
        }
        matrix = newMatrix
        addNewElement()
    }

    fun moveRight() {
        val newMatrix = generateBasicMatrix()
        for (i in 0 until 4) {
            var index = 3
            var isAdd = true
            for (j in 3 downTo 0 step 1) {
                if (matrix[i][j] == 0) continue
                if (newMatrix[i][3] == 0) {
                    newMatrix[i][3] = matrix[i][j]
                    continue
                }

                if (matrix[i][j] == newMatrix[i][index] && isAdd) {
                    newMatrix[i][index] *=2
                    isAdd= false
                } else {
                    newMatrix[i][--index] = matrix[i][j]
                    isAdd = true
                }
            }
        }
        matrix = newMatrix
        addNewElement()
    }

    fun moveUp() {
        val newMatrix = generateBasicMatrix()
        for (i in 0 until 4) {
            var index = 0
            var isAdd = true

            for (j in 0 until 4) {
                if (matrix[j][i] == 0) continue
                if (newMatrix[0][i] == 0) {
                    newMatrix[0][i] = matrix[j][i]
                    continue
                }
                if (matrix[j][i] == newMatrix[index][i] && isAdd) {
                    newMatrix[index][i] *= 2
                    isAdd = false
                } else {
                    newMatrix[++ index][i] = matrix[j][i]
                    isAdd = true
                }
            }
        }
        matrix =newMatrix
        addNewElement()
    }


    fun moveDown() {
        val newMatrix = generateBasicMatrix()
        for (i in 0 until 4) {
            var index = 3
            var isAdd = true

            for (j in 3 downTo  0 step 1) {
                if (matrix[j][i] == 0) continue
                if (newMatrix[3][i] == 0) {
                    newMatrix[3][i] = matrix[j][i]
                    continue
                }
                if (matrix[j][i] == newMatrix[index][i] && isAdd) {
                    newMatrix[index][i] *= 2
                    isAdd = false
                } else {
                    newMatrix[-- index][i] = matrix[j][i]
                    isAdd = true
                }
            }
        }
        matrix =newMatrix
        addNewElement()
    }

    fun hasWay() : Boolean {
        // 0 qiymat
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                if (matrix[i][j] == 0) return true
            }
        }

        // gorizantall
        for (i in 0 until 4) {
            for (j in 1 until 4) {
                if (matrix[i][j-1] == matrix[i][j]) return true
            }
        }

        // vertical
        for (i in 1 until 4) {
            for (j in 0 until 4) {
                if (matrix[j][i-1] == matrix[j][i]) return true
            }
        }

        return false
    }
}