package cn.com.peds.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DESEncryptFile {

	// public static String key = "123456";
	public static int block = 1024 * 1024;

	public static void main(String[] args) throws Exception {

		String oldFileName = "C:/slave.txt";
		String encFileName = oldFileName.substring(0, oldFileName.lastIndexOf(".")) + ".EncCode"
				+ oldFileName.substring(oldFileName.lastIndexOf("."), oldFileName.length());
		System.out.println(encFileName);

		String decFileName = oldFileName.substring(0, oldFileName.lastIndexOf(".")) + ".DecCode"
				+ oldFileName.substring(oldFileName.lastIndexOf("."), oldFileName.length());
		System.out.println(decFileName);

		blockEncCode(oldFileName, encFileName, "123456");
		blockDecCode(encFileName, decFileName, "123456");
	}

	public static void blockEncCode(String inputFileName, String outputFileName, String key) {

		FileInputStream fileInputStream = null;
		BufferedInputStream bufferedInputStream = null;

		RandomAccessFile randomAccessFile = null;

		try {
			randomAccessFile = new RandomAccessFile(outputFileName, "rw");
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		byte[] intBytes = null;
		try {
			fileInputStream = new FileInputStream(inputFileName);
			bufferedInputStream = new BufferedInputStream(fileInputStream);
			byte[] buf = new byte[block];
			byte[] tempByte = null;
			int length = 0;
			while ((length = bufferedInputStream.read(buf)) != -1) {
				tempByte = new byte[length];
				System.arraycopy(buf, 0, tempByte, 0, length);
				tempByte = DESEncrypt.getEncCode(tempByte, key);
				intBytes = intToByte(tempByte.length);
				randomAccessFile.write(intBytes);
				randomAccessFile.write(tempByte);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			if (randomAccessFile != null) {
				try {
					randomAccessFile.close();
					randomAccessFile = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (bufferedInputStream != null) {
				try {
					bufferedInputStream.close();
					bufferedInputStream = null;
				} catch (IOException e) {
				}
			}
		}
	}

	public static void blockEncCode(String inputFileName, String outputFileName, String key, HttpServletRequest req, HttpServletResponse res) {

		FileInputStream fileInputStream = null;
		BufferedInputStream bufferedInputStream = null;
		long fileLength = -1;
		long readLength = -1;
		File inputFile = new File(inputFileName);
		fileLength = inputFile.length();

		RandomAccessFile randomAccessFile = null;

		try {
			randomAccessFile = new RandomAccessFile(outputFileName, "rw");
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		byte[] intBytes = null;
		try {
			fileInputStream = new FileInputStream(inputFile);
			bufferedInputStream = new BufferedInputStream(fileInputStream);
			byte[] buf = new byte[block];
			byte[] tempByte = null;
			int length = 0;
			while ((length = bufferedInputStream.read(buf)) != -1) {

				tempByte = new byte[length];
				System.arraycopy(buf, 0, tempByte, 0, length);
				tempByte = DESEncrypt.getEncCode(tempByte, key);
				intBytes = intToByte(tempByte.length);
				randomAccessFile.write(intBytes);
				randomAccessFile.write(tempByte);
				readLength += length;
				float per = readLength / fileLength;
//				processBarInfo(per * 100, req, res);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			if (randomAccessFile != null) {
				try {
					randomAccessFile.close();
					randomAccessFile = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (bufferedInputStream != null) {
				try {
					bufferedInputStream.close();
					bufferedInputStream = null;
				} catch (IOException e) {
				}
			}
		}
	}

	public static void blockDecCode(String inputFileName, String outputFileName, String key, HttpServletRequest req, HttpServletResponse res) {
		RandomAccessFile randomAccessFile = null;

		BufferedOutputStream bufferedOutputStream = null;
		int blockLength = -1;
		byte[] tempByte = null;
		long readLength = -1;
		long fileLength = -1;

		try {
			randomAccessFile = new RandomAccessFile(inputFileName, "r");
			bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputFileName));

			fileLength = randomAccessFile.length();

			byte[] intBytes = new byte[4];

			if (fileLength > intBytes.length) {
				randomAccessFile.read(intBytes, 0, intBytes.length);
				blockLength = bytesToInt(intBytes);
				readLength = intBytes.length;
			}

			while (blockLength != -1) {
				tempByte = new byte[blockLength];
				blockLength = randomAccessFile.read(tempByte);
				readLength += blockLength;
				tempByte = DESEncrypt.getDecCode(tempByte, key);
				bufferedOutputStream.write(tempByte);
				float per = readLength / fileLength;
//				processBarInfo(per, req, res);

				if (readLength < fileLength) {
					randomAccessFile.read(intBytes, 0, intBytes.length);
					blockLength = bytesToInt(intBytes);

					if (blockLength != -1) {
						readLength += intBytes.length;
						continue;
					}
					break;
				}
				break;
			}

		} catch (Exception e) {
			System.out.println(readLength);
			e.printStackTrace();
		} finally {
			if (randomAccessFile != null) {
				try {
					randomAccessFile.close();
					randomAccessFile = null;
				} catch (IOException e) {
				}
			}

			if (bufferedOutputStream != null) {
				try {
					bufferedOutputStream.close();
					bufferedOutputStream = null;
				} catch (IOException e) {
				}
			}
		}

	}

	public static void processBarInfo(float per, HttpServletRequest req, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			out.println("<response>");
			out.println("<percent>" + per + "</percent>");
			out.println("</response>");
			out.close();
			HttpSession session = req.getSession();
			session.setAttribute("percent", per*100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void blockDecCode(String inputFileName, String outputFileName, String key) {
		RandomAccessFile randomAccessFile = null;

		BufferedOutputStream bufferedOutputStream = null;
		int blockLength = -1;
		byte[] tempByte = null;
		long readLength = -1;
		long fileLength = -1;

		try {
			randomAccessFile = new RandomAccessFile(inputFileName, "r");
			bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputFileName));

			fileLength = randomAccessFile.length();

			byte[] intBytes = new byte[4];

			if (fileLength > intBytes.length) {
				randomAccessFile.read(intBytes, 0, intBytes.length);
				blockLength = bytesToInt(intBytes);
				readLength = intBytes.length;
			}

			while (blockLength != -1) {
				tempByte = new byte[blockLength];
				blockLength = randomAccessFile.read(tempByte);
				readLength += blockLength;
				tempByte = DESEncrypt.getDecCode(tempByte, key);
				bufferedOutputStream.write(tempByte);
				if (readLength < fileLength) {
					randomAccessFile.read(intBytes, 0, intBytes.length);
					blockLength = bytesToInt(intBytes);

					if (blockLength != -1) {
						readLength += intBytes.length;
						continue;
					}
					break;
				}
				break;
			}

		} catch (Exception e) {
			System.out.println(readLength);
			e.printStackTrace();
		} finally {
			if (randomAccessFile != null) {
				try {
					randomAccessFile.close();
					randomAccessFile = null;
				} catch (IOException e) {
				}
			}

			if (bufferedOutputStream != null) {
				try {
					bufferedOutputStream.close();
					bufferedOutputStream = null;
				} catch (IOException e) {
				}
			}
		}

	}

	public static byte[] intToByte(int i) {
		byte[] bt = new byte[4];
		bt[0] = (byte) (0xff & i);
		bt[1] = (byte) ((0xff00 & i) >> 8);
		bt[2] = (byte) ((0xff0000 & i) >> 16);
		bt[3] = (byte) ((0xff000000 & i) >> 24);
		return bt;
	}

	public static int bytesToInt(byte[] bytes) {
		int num = bytes[0] & 0xFF;
		num |= ((bytes[1] << 8) & 0xFF00);
		num |= ((bytes[2] << 16) & 0xFF0000);
		num |= ((bytes[3] << 24) & 0xFF000000);
		return num;
	}

}
