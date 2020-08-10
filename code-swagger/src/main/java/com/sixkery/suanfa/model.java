package com.sixkery.suanfa;


/**
 * 单例设计模式
 *
 * @author sixkery
 * @date 2020/8/11
 */
public class model {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();
        System.out.println(instance1 == instance);
    }


}

// 饿汉式（静态常量）
class Singleton {
    // 1. 构造方法私有，外部不能 new
    private Singleton() {
    }

    // 2. 本类内部创建对象实例
    private static final Singleton instance = new Singleton();

    // 3. 提供一个公有的静态方法，返回实例对象
    public static Singleton getInstance() {
        return instance;
    }
}

/**
 * 1. 优点：写法比较简单，在类加载的时候就完成实例化，避免了线程同步的问题
 * 2. 缺点：在类装载的时候就完成了实例化，没有达到懒加载的效果，如果从始至终没有使用过这个实例，就会造成内存浪费。
 * 3. 这种方式基于类加载的机制避免了多线程的问题，不过，实例在类加载就实例化,在单例模式中大多数是调用 getInstance 方法
 * 但是导致类加载的原因有很多种，因此不能确定有其他的方式（或者其他的静态方法）导致类装载。这时候初始化 instance 就
 * 没有达到懒加载的效果
 * 结论：这种单例模式可能造成内存浪费
 */

// 饿汉式（静态代码块）
class Singleton1 {
    // 1. 构造方法私有，外部不能 new
    private Singleton1() {
    }

    // 2. 本类内部创建对象实例
    private static Singleton1 instance;

    // 静态代码块中实例化
    static {
        instance = new Singleton1();
    }

    // 3. 提供一个公有的静态方法，返回实例对象
    public static Singleton1 getInstance() {
        return instance;
    }
}

/**
 * 这种方式和上面的方式类似，只不过将实例化的过程放到了静态代码块中，也是在类装载的时候，就执行静态代码快里的代码，
 * 初始化类的实例。
 */

// 懒汉式（线程不安全）
class Singleton2 {
    // 1. 构造方法私有，外部不能 new
    private Singleton2() {
    }

    // 2. 本类内部创建对象实例
    private static Singleton2 instance;

    // 3. 提供一个公有的静态方法，当用到该方法时，才创建 instance
    public static Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}

/**
 * 1. 起到了懒加载（lazy loading）的效果，但是只能在单线程中使用。
 * 2. 如果在多线程的环境下，一个线程进入到 if (instance == null) 判断语句块，还未来得及继续进行下去。
 * 另一个线程也进入了这个判断语句，这时就会产生多个实例，所以在多线程环境下不可用。
 * 结论：在实际开发中不要使用这种方式
 */

// 懒汉式（线程安全）
class Singleton3 {
    // 1. 构造方法私有，外部不能 new
    private Singleton3() {
    }

    // 2. 本类内部创建对象实例
    private static Singleton3 instance;

    // 3. 提供一个公有的静态方法，当用到该方法时，才创建 instance 添加同步方法，保证线程安全
    public static synchronized Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}

/**
 * 1. 解决了线程安全的问题
 * 2. 效率太低了，每个线程想要获取类的实例的时候，执行 getInstance 方法都要同步，而其实这个方法只需要执行一次就够了
 * 后面的想要获取该类的实例，只需要 return 就可以了方法进行同步效率太低，
 * 结论：在开发中不建议使用
 */

// 双重检查单例模式
class Singleton4 {
    // 1. 构造方法私有，外部不能 new
    private Singleton4() {
    }

    // 2. 本类内部创建对象实例
    private static volatile Singleton4 instance;

    // 3. 提供一个静态公有方法，加入双重检查，解决线程安全问题，同时解决懒加载问题，同时保证了效率问题
    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}

/**
 * 1. 双重检查（Double-Check）概念是多线程开发中常使用到的。代码中进行了两次判断，可以保证线程安全
 * 2. 实例代码只用执行一次，后面再次访问时一个判断直接 return 实例对象避免了反复进行方法的同步
 * 3. 线程安全，延迟加载，效率较高
 * 4. 在实际开发中，推荐使用
 */


// 静态内部类方式
class Singleton5 {
    // 1. 构造方法私有，外部不能 new
    private Singleton5() {
    }

    // 2. 静态内部类 类装载的时候内部类不会执行，只有调用下边方法才会执行静态内部类的加载，起到了懒加载的效果
    private static class SingletonInstance {
        private static final Singleton5 INSTANCE = new Singleton5();
    }

    // 3. 提供一个静态公有方法，
    public static Singleton5 getInstance() {
        return SingletonInstance.INSTANCE;

    }
}
/**
 * 1. 这种方式采用类的装载机制来保证初始化实例时，只有一个线程
 * 2. 静态内部类方式在 Singleton 类被装载时并不会立即实例化，而是在需要实例化时，调用 getInstance 方法，
 *    才会装载 SingletonInstance 类，从而完成实例化，
 * 3. 类的静态属性只会在第一次加载类的时候初始化，JVM 帮我们保证了线程的安全性，在类进行初始化的时候，别的线程没有办法进入
 * 4. 避免了线程不安全，利用静态内部类特点实现延迟加载，效率高，推荐使用。
 */