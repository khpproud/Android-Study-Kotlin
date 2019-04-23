package com.patrick.ankonetworkexample.JSON

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class Response(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("symbols_requested")
	val symbolsRequested: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("symbols_returned")
	val symbolsReturned: Int? = null
)