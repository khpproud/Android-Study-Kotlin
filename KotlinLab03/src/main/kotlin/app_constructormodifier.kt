class ConstructorVisibilityTest private constructor(name: String) {
    public constructor(name: String, no: Int): this(name) {
        
    }
}