package com.haitao.www.myformer.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 文件工具类
 */

public class FileUtil {
    static final String FILES_PATH = "CompressHelper";
    private static final int EOF = -1;
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    private FileUtil() {
        throw new UnsupportedOperationException("you can't instantiate me...");
    }

    /**
     * 根据文件路径获取文件
     *
     * @param filePath 文件路径
     * @return 文件
     */
    public static File getFileByPath(String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean isFileExists(String filePath) {
        return isFileExists(getFileByPath(filePath));
    }

    /**
     * 判断文件是否存在
     *
     * @param file 文件
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }


    /**
     * 重命名文件
     *
     * @param filePath 文件路径
     * @param newName  新名称
     * @return {@code true}: 重命名成功<br>{@code false}: 重命名失败
     */
    public static boolean rename(String filePath, String newName) {
        return rename(getFileByPath(filePath), newName);
    }

    /**
     * 重命名文件
     *
     * @param file    文件
     * @param newName 新名称
     * @return {@code true}: 重命名成功<br>{@code false}: 重命名失败
     */
    public static boolean rename(File file, String newName) {
        // 文件为空返回false
        if (file == null) return false;
        // 文件不存在返回false
        if (!file.exists()) return false;
        // 新的文件名为空返回false
        if (isSpace(newName)) return false;
        // 如果文件名没有改变返回true
        if (newName.equals(file.getName())) return true;
        File newFile = new File(file.getParent() + File.separator + newName);
        // 如果重命名的文件已存在返回false
        return !newFile.exists()
                && file.renameTo(newFile);
    }
    public static boolean isSpace(String s) {
        return (s == null || s.trim().length() == 0);
    }
    /**
     * 判断是否是目录
     *
     * @param dirPath 目录路径
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDir(String dirPath) {
        return isDir(getFileByPath(dirPath));
    }

    /**
     * 判断是否是目录
     *
     * @param file 文件
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDir(File file) {
        return isFileExists(file) && file.isDirectory();
    }

    /**
     * 判断是否是文件
     *
     * @param filePath 文件路径
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isFile(String filePath) {
        return isFile(getFileByPath(filePath));
    }

    /**
     * 判断是否是文件
     *
     * @param file 文件
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isFile(File file) {
        return isFileExists(file) && file.isFile();
    }


    /**
     * 重命名文件
     * @param file      文件
     * @param newName   新名字
     * @return          新文件
     */
    public static File renameFile(File file, String newName) {
        File newFile = new File(file.getParent(), newName);
        if (!newFile.equals(file)) {
            if (newFile.exists()) {
                if (newFile.delete()) {
                    Log.d("FileUtil", "Delete old " + newName + " file");
                }
            }
            if (file.renameTo(newFile)) {
                Log.d("FileUtil", "Rename file to " + newName);
            }
        }
        return newFile;
    }


    /**
     * 获取临时文件
     * @param context   上下文
     * @param uri       url
     * @return          临时文件
     * @throws IOException
     */
    public static File getTempFile(Context context, Uri uri) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        String fileName = getFileName(context, uri);
        String[] splitName = splitFileName(fileName);
        File tempFile = File.createTempFile(splitName[0], splitName[1]);
        tempFile = renameFile(tempFile, fileName);
        tempFile.deleteOnExit();
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(tempFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (inputStream != null) {
            copy(inputStream, out);
            inputStream.close();
        }

        if (out != null) {
            out.close();
        }
        return tempFile;
    }
    /**
     * 获取临时文件
     * @param context   上下文
     * @return          临时文件
     * @throws IOException
     */
    public static File getTempFile2(Context context, String file) throws IOException {
        Uri uri = Uri.parse(file);
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        String fileName = getFileName(context, uri);
        String[] splitName = splitFileName(fileName);
        File tempFile = File.createTempFile(splitName[0], splitName[1]);
        tempFile = renameFile(tempFile, fileName);
        tempFile.deleteOnExit();
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(tempFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (inputStream != null) {
            copy(inputStream, out);
            inputStream.close();
        }

        if (out != null) {
            out.close();
        }
        return tempFile;
    }

    /**
     * 截取文件名称
     * @param fileName 文件名称
     * @return 数组[0]:文件名称  数组[1]:后缀名
     */
    static String[] splitFileName(String fileName) {
        String name = fileName;
        String extension = "";
        int i = fileName.lastIndexOf(".");
        if (i != -1) {
            name = fileName.substring(0, i);
            extension = fileName.substring(i);
        }

        return new String[]{name, extension};
    }

    /**
     * 获取文件名称
     * @param context   上下文
     * @param uri       uri
     * @return          文件名称
     */
    static String getFileName(Context context, Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf(File.separator);
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    /**
     * 获取真实的路径
     * @param context   上下文
     * @param uri       uri
     * @return          文件路径
     */
    static String getRealPathFromURI(Context context, Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        if (cursor == null) {
            return uri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            String realPath = cursor.getString(index);
            cursor.close();
            return realPath;
        }
    }



    static int copy(InputStream input, OutputStream output) throws IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    static long copyLarge(InputStream input, OutputStream output)
            throws IOException {
        return copyLarge(input, output, new byte[DEFAULT_BUFFER_SIZE]);
    }

    static long copyLarge(InputStream input, OutputStream output, byte[] buffer)
            throws IOException {
        long count = 0;
        int n;
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    //////////////////////////
    // 文件夹拷贝
    public int copyFolder(String fromFile, String toFile) {
        // 要复制的文件目录
        File[] currentFiles;
        File root = new File(fromFile);
        // 如同判断SD卡是否存在或者文件是否存在
        // 如果不存在则 return出去
        if (!root.exists()) {
            return -1;
        }
        // 如果存在则获取当前目录下的全部文件 填充数组
        currentFiles = root.listFiles();

        // 目标目录
        File targetDir = new File(toFile);
        // 创建目录
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        // 遍历要复制该目录下的全部文件
        for (int i = 0; i < currentFiles.length; i++) {
            if (currentFiles[i].isDirectory())// 如果当前项为子目录 进行递归
            {
                copyFolder(currentFiles[i].getPath() + "/",
                        toFile + currentFiles[i].getName() + "/");

            } else// 如果当前项为文件则进行文件拷贝
            {
                CopySdcardFile(currentFiles[i].getPath(), toFile
                        + currentFiles[i].getName());
            }
        }
        return 0;
    }

    // 文件拷贝
    // 要复制的目录下的所有非子目录(文件夹)文件拷贝
    public int CopySdcardFile(String fromFile, String toFile) {
        try {
            InputStream fosfrom = new FileInputStream(fromFile);
            OutputStream fosto = new FileOutputStream(toFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c);
            }
            fosfrom.close();
            fosto.close();
            return 0;

        } catch (Exception ex) {
            return -1;
        }
    }

    // 比较两个目录下文件是否一致（文件夹个数、文件个数、文件大小）
    // （返回0表示一致，返回-1表示不一致）
    public int CheckFile(String fromFilePath, String toFilePath) {

        FilesInfo fromFile = new FilesInfo(fromFilePath); // 获取路径1下文件的信息

        FilesInfo toFile = new FilesInfo(toFilePath);// 获取路径2下文件的信息

        if (fromFile.getCheck() == -1 || fromFile.getCheck() == -1) { // 如果其中一个路径不存在
            return -1;
        } else if (fromFile.getDirectoryAmounts() != toFile
                .getDirectoryAmounts()) {// 如果两个路径下文件夹个数不一致
            return -1;
        } else if (fromFile.getFileAmounts() != toFile.getFileAmounts()) {// 如果两个路径下文件个数不一致
            return -1;
        } else { // 比较两个路径下文件的大小
            List<File> fromFileList = fromFile.getFileList(); // 获取路径1下文件List
            List<File> toFileList = toFile.getFileList(); // 获取路径2下文件List

            if (fromFileList.size() == 0 || toFileList.size() == 0) { // 如果其中一个路径下文件List大小为0返回-1
                return -1;
            }

            Iterator<File> fromFileIterator = fromFileList.iterator();

            while (fromFileIterator.hasNext()) { // 遍历fromFile

                File tempFromFile = (File) fromFileIterator.next();
                String tempFromFileName = tempFromFile.getName();
                long tempFromFileLength = tempFromFile.length();

                boolean Same = false;

                Iterator<File> toFileIterator = toFileList.iterator();
                while (toFileIterator.hasNext()) { // 遍历toFile
                    File tempToFile = (File) toFileIterator.next();
                    if (tempToFile.getName().equals(tempFromFileName)) {

                        if (tempToFile.length() == tempFromFileLength) { // 长度一致
                            Same = true;
                            break;
                        }
                        break; // 找到但长度不一致
                    }
                }
                if (!Same) {
                    return -1;
                }
            }
            return 0;
        }
    }

    //因为COPY文件到    SDCARD 需要权限，因此需要在应用程序的AndroidManifest.xml加上权限，如下：
    //<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    public void copyFileSystemToMnt() {
        Process process = null;
        try {
            //process = Runtime.getRuntime().exec("getprop ro.board.platform");
            //busybox cp
            process = Runtime.getRuntime().exec("busybox cp /system/manual /mnt/sdcard/ -rf");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public class FilesInfo {

        private final String SDCard = "/mnt/sdcard/external_sd";

        private int directoryAmounts;

        private int fileAmounts;

        private int check;

        private List<File> fileList;

        public FilesInfo(String filePath) {
            directoryAmounts = 0;

            fileAmounts = 0;

            fileList = new ArrayList<File>();

            check = init(filePath);
        }

        public int init(String filePath) {
            File[] currentFiles;
            File root = new File(filePath);
            if (!root.exists()) {
                return -1;
            }
            currentFiles = root.listFiles();

            for (int i = 0; i < currentFiles.length; i++) {
                if (currentFiles[i].isDirectory()) {
                    if (currentFiles[i].getAbsolutePath().equals(SDCard)) {
                        continue;
                    }
                    directoryAmounts++;
                    init(currentFiles[i].getAbsolutePath());
                    continue;
                }
                fileAmounts++;
                fileList.add(currentFiles[i]);
            }
            return 0;
        }

        public int getDirectoryAmounts() {
            return directoryAmounts;
        }

        public int getFileAmounts() {
            return fileAmounts;
        }

        public int getCheck() {
            return check;
        }

        public List<File> getFileList() {
            return fileList;
        }

    }


    /**
     * 根据文件绝对路径获取文件名
     *
     * @param filePath
     * @return String
     */
    public static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath))
            return "";
        return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
    }

    public void writerFileToApp(Context context, String fileName) {
        writerFileToApp(context, fileName, Context.MODE_PRIVATE);
    }

    public void writerFileToApp(Context context, String fileName, int mode) {

        OutputStream out = null;
        try {
            out = context.openFileOutput(fileName, mode);
            out.write(fileName.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static boolean writerFile(byte[] baytes, String filename) {
        boolean b = true;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(filename));
            fileOutputStream.write(baytes);

        } catch (Exception e) {
            b = false;
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    b = false;
                    e.printStackTrace();
                }
            }
        }
        return b;

    }

    ;

    public String readFileFromApp(Context context, String fileName) {
        String result = null;
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            inputStream = context.openFileInput(fileName);
            outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
            result = outputStream.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }
        return result;
    }

    public void writerFileToSDcard(Context context, String fileName, String content) {
        OutputStream out = null;
        // 判断是否sdCard是否可用
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            try {
                out = new FileOutputStream(new File(fileName));
                out.write(content.getBytes());

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            Toast.makeText(context, "不能识别SD卡",Toast.LENGTH_LONG).show();
        }
    }

    public String readFileToSDcard(Context context, String fileName) {
        BufferedReader reader = null;
        String str = null;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            try {
                reader = new BufferedReader(new FileReader(new File(fileName)));
                StringBuilder sb = new StringBuilder("");


                while ((str = reader.readLine()) != null) {
                    sb.append(str);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        } else {
            Toast.makeText(context, "不能识别SD卡",Toast.LENGTH_LONG).show();
        }
        return str.toString();
    }


    public static void createDirs(String dirs) {
        String str[] = dirs.split(File.separator);
        if (str != null && str.length > 1) {
            for (int i = 0; i < str.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j <= i; j++) {
                    sb.append(str[i]);
                    sb.append(File.separator);
                }
                createDir(sb.toString());
            }
        }
    }

    public static void createDir(String dirs) {
        File destDir = new File(dirs);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
    }


    /**
     * 得到json文件中的内容
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getLocalJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String encodeBase64File(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileInputStream inputFile = null;
            inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            return Base64.encodeToString(buffer, Base64.DEFAULT);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

    }

    public static boolean base64StringToPdf(String base64Content, String filePath) {
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            byte[] bytes = Base64.decode(base64Content, Base64.DEFAULT);//base64编码内容转换为字节数组
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
            bis = new BufferedInputStream(byteInputStream);
            File file = new File(filePath);
            File path = file.getParentFile();
            if (!path.exists()) {
                path.mkdirs();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int length = bis.read(buffer);
            while (length != -1) {
                bos.write(buffer, 0, length);
                length = bis.read(buffer);
            }
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

}
