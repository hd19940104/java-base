public class ModuleB {
    void print(){
        System.out.println("I am in module B");
    }

    public static void main(String[] args) {
        ModuleA moduleA = new ModuleA();
        moduleA.print();
        ModuleB moduleB = new ModuleB();
        moduleB.print();
    }
}
