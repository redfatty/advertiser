package com.hj.advertiser.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MyFileUtils {

	public static boolean replaceAll(String filePath, String oldstr, String newStr) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(new File(filePath), "rw");
			String line = null;
			long lastPoint = 0; // 记住上一次的偏移量
			while ((line = raf.readLine()) != null) {
				final long ponit = raf.getFilePointer();
				if (line.contains(oldstr)) {
					String str = line.replace(oldstr, newStr);
					raf.seek(lastPoint);
					raf.writeBytes(str);
				}
				lastPoint = ponit;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

}
