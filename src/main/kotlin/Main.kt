/*
clase que representa el estado de la máquina
 */
enum class machineState() {
    CHOOSING_AN_ACTION, CHOOSIN_A_TYPE_OF_COFFEE,
}


class cofeeMachine() {
    //supplies list + amount => son varibles globales para que puedan ser accedidas desde cualquier funcion
    var water: Int = 400      // ml
    var milk: Int = 540       // ml
    var coffee: Int = 120    //g
    var cups: Int = 9     //unit
    var money: Int = 550     //$
    var state: machineState = machineState.CHOOSING_AN_ACTION


    //funcion para el llenado de los ingredientes
    fun fillFunction() {
        println("Write how many ml of water you want to add:")
        water += readLine()!!.toInt()
        println("Write how many ml of milk you want to add:")
        milk += readLine()!!.toInt()
        println("Write how many grams of coffee beans you want to add:")
        coffee += readLine()!!.toInt()
        println("Write how many disposable cups you want to add:")
        cups += readLine()!!.toInt()
    }

    fun message() {
        println(
            if (state == machineState.CHOOSING_AN_ACTION) {
                "Write action (buy, fill, take, remaining, exit):"
            }
            else {
                "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:"
            }
        )
    }

    //función que muestra en pantalla la contidad de insumos disponibles
    fun DisplayQuantitySupplies() {
        println("The coffee machine has:")
        println("$water ml of water")
        println("$milk ml of milk")
        println("$coffee g of coffee beans")
        println("$cups disposable cups")
        println("$$money of money")
        println()
    }

    fun compSuppliesAndMoney(parWater: Int, parMilk: Int, parCoffee: Int, parCups: Int, parMoney: Int): String {
        if (water < parWater) {
            return "water"
        }
        else if (milk < parMilk) {
            return "milk"
        }
        else if (coffee < parCoffee) {
            return "coffee"
        }
        else if (cups < parCups) {
            return "cups"
        }
        else {
            water -= parWater
            milk -= parMilk
            coffee -= parCoffee
            cups -= parCups
            money += parMoney
            return "ok"
        }
    }


    // funcion que recive un string como comando
    fun reedOrder(order: String): Boolean {
        if (state == machineState.CHOOSING_AN_ACTION) {
            when (order) {
                "buy" -> {
                    state = machineState.CHOOSIN_A_TYPE_OF_COFFEE
                }
                "fill" -> fillFunction()
                "take" -> {
                    println("I gave you $$money")
                    money = 0
                }
                "remaining" -> DisplayQuantitySupplies()
                "exit" -> return false
                else -> {
                    println("unrecognized order")
                }
            }
            return true
        }
        else if (state == machineState.CHOOSIN_A_TYPE_OF_COFFEE) {
            var resources = ""
            when (order) {
                "1" -> resources = compSuppliesAndMoney(250, 0, 16, 1, 4)
                "2" -> resources = compSuppliesAndMoney(350, 75, 20, 1, 7)
                "3" -> resources = compSuppliesAndMoney(200, 100, 12, 1, 6)
                "back" -> {
                    resources = "back"
                    // state=machineState.CHOOSING_AN_ACTION
                }//vuelve al menu principal
                else -> println("Invalid opcion")
            }
            if (resources == "ok") {
                println("I have enough resources, making you a coffee!")
                println()
               // state = machineState.CHOOSING_AN_ACTION
            }
            else if (resources == "back") { // no hace nado solo vuelve
               // state = machineState.CHOOSING_AN_ACTION

            }
            else {
                println("Sorry, not enough $resources!")
            }
        }
        state = machineState.CHOOSING_AN_ACTION
        return true
    }
}




fun main() {
    var continuar = true;
    val cm = cofeeMachine()
    while (continuar) {
        cm.message()
        continuar = cm.reedOrder(readln())
    }
}














