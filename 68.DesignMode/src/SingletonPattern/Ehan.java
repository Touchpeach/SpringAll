package SingletonPattern;

/**
 * 饿汉式，这个比较常用，但容易产生垃圾，没有加锁的话执行会快一些，但对象是静态会初始化加载，占用内存
 */
public class Ehan {
    private static Ehan ehan = new Ehan();

    private Ehan(){};

    private static Ehan geteEhan(){
        return ehan;
    }
}

