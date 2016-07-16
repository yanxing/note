
import java.io.*;
import java.util.*;
import java.util.zip.*;

public class GZipThread extends Thread {
    private List pool;
    //已经压缩的文件数量
    private static int fileGZipCount = 0;

    public GZipThread(List pool) {
        this.pool = pool;
    }

    private static synchronized void incrementGZipFiles() {
        fileGZipCount++;
    }

    public void run() {
        while (fileGZipCount != GZipAllFiles.getAllFile()) {
            File input;
            synchronized (pool) {
                while (pool.isEmpty()) {
                    if (fileGZipCount == GZipAllFiles
                            .getAllFile()) {
                        System.out.println("Thread ending");
                        return;
                    }
                    try {
                        //没有文件，当前线程等待
                        pool.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                input = (File) pool.remove(pool.size() - 1);
                incrementGZipFiles();
            }
            gzip(input);
        }
    }

    /**
     * 压缩文件
     * @param input
     */
    public void gzip(File input){
        // 不压缩已经压缩过的文件
        if (!input.getName().endsWith(".gz")) {
            try {
                InputStream in = new FileInputStream(input);
                in = new BufferedInputStream(in);
                File output = new File(input.getParent(), input.getName()
                        + ".gz");
                // 不覆盖已经存在的文件
                if (!output.exists()) {
                    OutputStream out = new FileOutputStream(output);
                    out = new GZIPOutputStream(out);
                    out = new BufferedOutputStream(out);
                    int b;
                    while ((b = in.read()) != -1)
                        out.write(b);
                    out.flush();
                    out.close();
                    in.close();
                }
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}