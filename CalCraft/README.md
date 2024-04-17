# (WIP) CalCraft

## An Android Calculator Learning Project

Current state demo GIFs:

<div style="display: inline-block;">
    <img src="demo-gifs/calcraft-data-input-1.gif" width="250"/>
    <img src="demo-gifs/calcraft-op-history.gif" width="250"/>
    <img src="demo-gifs/calcraft-data-input-2.gif" width="250"/>
</div>


CalCraft is an Android calculator app developed as part of a learning project in Kotlin. The project focuses on solving the order of operations in mathematical calculations with a completely custom solution built from scratch by me. This approach allowed for practical experience with classes, interfaces, and creating an extendable code architecture. The app includes basic arithmetic operations, decimal support, a scrollable history of calculations, and is planned to be expanded with scientific functions and improved UI features. The project serves as a showcase of my problem-solving skills in software development, particularly when working on complex algorithms that handle many interconnected possibilities.

This is the main solver and the trickiest part that I had to develop:

```kotlin
 fun computeBuffer(operationBuffer: MutableList<OperationToken>): Number? {
            var tokens: MutableList<OperationToken?> = mutableListOf()
            while (operationBuffer.isNotEmpty()) {
                for (i in operationBuffer.indices) {
                    tokens = readChunk(i, operationBuffer)
                    val singleOpCalc = tokens.firstOrNull { it is SingleOperandCalculation } as? SingleOperandCalculation
                    if (singleOpCalc != null) {
                        val precedingNumber = tokens[tokens.indexOf(singleOpCalc) - 1] as Number
                        val result = singleOpCalc.calculate(precedingNumber.value)
                        precedingNumber.value = result
                        if (operationBuffer.indexOf(singleOpCalc) == operationBuffer.lastIndex) {
                            operationBuffer.clear()
                            return Number(result)
                        }
                        operationBuffer.remove(singleOpCalc)
                        break
                    } else if (tokens[0] is Number && tokens[1] is Calculation && tokens[2] is Number) {
                        // process calculations according to priority
                        // any of: 2+3x, 2x3+, 3x2(=/empty) (before... 2+3x
                        // 2+3x -> +3x -> 3x
                        val firstOperand = tokens[0] as Number
                        val firstCalc = tokens[1] as Calculation
                        val secondOperand = tokens[2] as Number
                        if (tokens[3] is Calculation) {
                            val secondCalc = tokens[3] as Calculation
                            if (firstCalc.priority.value < secondCalc.priority.value) {
                                continue
                            } else if (firstCalc.priority.value >= secondCalc.priority.value) { // 2x3x -> 6x
                                val result = firstCalc.calculate(firstOperand.value, secondOperand.value)
                                firstOperand.value = result
                                operationBuffer.remove(tokens[1])
                                operationBuffer.remove(tokens[2])
                                break
                            }
                        } else {
                            val result = firstCalc.calculate(firstOperand.value, secondOperand.value) // 2x3 -> 6=
                            firstOperand.value = result
                            operationBuffer.remove(tokens[1])
                            operationBuffer.remove(tokens[2])
                            break
                        }
                    } else if (tokens[0] is Number && tokens[1] is Equal) { // FINAL RESULT, BREAKS LOOP
                        val finalResult = Result((tokens[0] as Number).value)
                        history.add((finalResult))
                        operationBuffer.clear()
                        return finalResult
                    } else if (tokens[0] is Number && tokens[1] is Calculation) { // FROM HERE: ONLY HAPPENS WHEN operationBuffer.size == 2
                        // 2+ -> missing more op.
                        val update = tokens[0] as Number
                        val op = tokens[1] as Calculation
                        operationBuffer.clear()
                        return update // requires more op.
                    } else if (tokens[0] is Number && tokens[1] == null) { // FROM HERE: ONLY HAPPENS WHEN operationBuffer.size == 1
                        val update = tokens[0] as Number
                        operationBuffer.clear()
                        return update // requires more op.
                    } else if (tokens[0] is OrderedToken && tokens[1] == null) {
                        val op = tokens[0] as OrderedToken
                        operationBuffer.clear()
                        return null // requires more numbers
                    }
                }
            }
            return null
        }
```

