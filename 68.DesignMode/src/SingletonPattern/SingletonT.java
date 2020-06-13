package SingletonPattern;

/**
 * 懒汉式，线程安全的，但是效率低，该方法不频繁使用的情况下
 */
public class SingletonT {
    private static SingletonT instanceT;

    private SingletonT(){};

    public static synchronized SingletonT getInstance(){
        if(instanceT == null){
            instanceT = new SingletonT();
        }
        return instanceT;
    }
}
