package qrcodegeneretor;


	
	 
	import java.awt.Color;
	import java.awt.Graphics2D;
	import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.IOException;
	import java.util.EnumMap;
	import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;
	 
	import com.google.zxing.BarcodeFormat;
	import com.google.zxing.EncodeHintType;
	import com.google.zxing.WriterException;
	import com.google.zxing.common.BitMatrix;
	import com.google.zxing.qrcode.QRCodeWriter;
	import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
	 
	/**
	 * @author Crunchify.com
	 * Updated: 03/20/2016 - added code to narrow border size 
	 */
	 
	public class DarveshQRCode {
	 
		// Tutorial: http://zxing.github.io/zxing/apidocs/index.html
	 
		public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			System.out.println("\nenter the information regarding to generate QR code\n" );
			String myCodeText = in.nextLine();
			System.out.println("\n enter your System path that u storing a QR code Image eg. /F:/data/QRCODE \n");
			String filePath = in.nextLine();
			int size = 250;
			String fileType = "png";
			File myFile = new File(filePath);
			try {
				
				Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
				hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
				
				// Now with zxing version 3.2.1 you could change border size (white border size to just 1)
				hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
				hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
	 
				QRCodeWriter qrCodeWriter = new QRCodeWriter();
				BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
						size, hintMap);
				int DarveshWidth = byteMatrix.getWidth();
				BufferedImage image = new BufferedImage(DarveshWidth, DarveshWidth,
						BufferedImage.TYPE_INT_RGB);
				image.createGraphics();
	 
				Graphics2D graphics = (Graphics2D) image.getGraphics();
				graphics.setColor(Color.RED);
				graphics.fillRect(0, 0, DarveshWidth, DarveshWidth);
				graphics.setColor(Color.BLACK);
	 
				for (int i = 0; i < DarveshWidth; i++) {
					for (int j = 0; j < DarveshWidth; j++) {
						if (byteMatrix.get(i, j)) {
							graphics.fillRect(i, j, 1, 1);
						}
					}
				}
				ImageIO.write(image, fileType, myFile);
			} catch (WriterException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
}}