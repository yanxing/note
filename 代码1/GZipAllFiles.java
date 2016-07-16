import java.io.*;
import java.util.*;

public class GZipAllFiles {

    public final static int THREAD_COUNT = 4;
    public static int fileAll = -1;

    public static void main(String[] args) {
        Vector pool = new Vector();
        GZipThread[] threads = new GZipThread[THREAD_COUNT];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new GZipThread(pool);
            threads[i].start();
        }
        int totalFiles = 0;
        for (String arg : args) {
            File f = new File(arg);
            if (f.exists()) {
                //文件是一个目录时
                if (f.isDirectory()) {
                    File[] files = f.listFiles();
                    for (int j = 0; j < files.length; j++) {
                        //不递归处理目录
                        if (!files[j].isDirectory()) {
                            totalFiles++;
                            synchronized (pool) {
                                pool.add(0, files[j]);
                                pool.notifyAll();
                            }
                        }
                    }
                } else {
                    totalFiles++;
                    synchronized (pool) {
                        pool.add(0, f);
                        pool.notifyAll();
                    }
                }
            }
        }
        fileAll = totalFiles;
        //没有文件再添加到池中,中断等待的线程
        for (int i = 0; i < threads.length; i++) {
            threads[i].interrupt();
        }
    }

    public static int getAllFile() {
        return fileAll;
    }

}