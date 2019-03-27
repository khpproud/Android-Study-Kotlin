package six.one

// 변량 소비자/생성자 제한

class ConsumerProducer<in T, out R> {
    fun consumeItemT(t: T): Unit { }
    //fun consumeItemR(r: R): Unit { }          // 에러 in 위치에 형식 매개변수 R
    //fun produceItemT(): T { }                 // 에러 out 위치에 형식 매개변수 T
    fun produceItemR(): R? {
        return null
    }
}