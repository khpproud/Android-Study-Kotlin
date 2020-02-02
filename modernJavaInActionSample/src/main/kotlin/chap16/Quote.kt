package chap16

data class Quote(val shopName: String,
                 val price: Double,
                 val discountCode: Discount.Code) {

    companion object {
        @JvmStatic
        fun parse(s: String): Quote {
            val split = s.split(":")
            return Quote(split[0],
                split[1].toDouble(),
                Discount.Code.valueOf(split[2]))
        }
    }
}