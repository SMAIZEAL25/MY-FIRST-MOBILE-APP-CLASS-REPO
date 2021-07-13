fun main(args: Array<String>){
	println("true")
}
object OppositesAtract {
	fun islove(
		flower1: Int,
		flower2: Int
	): Boolean {
		return if (flower != flower2){
			flower1 % 2 == 0 && flower2 % 2 !=0 || flower1 % 2 !=0 && flower2% 2 == 0
	} else {
	  flase
	}
     }
  }
