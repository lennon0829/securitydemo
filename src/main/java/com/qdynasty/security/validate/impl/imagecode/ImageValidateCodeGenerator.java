/**
 * 
 */
package com.qdynasty.security.validate.impl.imagecode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;

import com.qdynasty.constants.SecurityConfigProperties;
import com.qdynasty.security.validate.ValidateCodeGenerator;
import com.qdynasty.security.validate.entity.ImageCode;
import com.qdynasty.security.validate.entity.ValidateCode;

import cn.hutool.core.util.RandomUtil;

/**
 * @author fei.qin
 *
 */
@Component
public class ImageValidateCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private SecurityConfigProperties securityConfigProperties;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qdynasty.security.validate.ValidateCodeGenerator#generate(org.
	 * springframework.web.context.request.ServletWebRequest)
	 */
	@Override
	public ValidateCode generate(HttpServletRequest request) {

		int width = ServletRequestUtils.getIntParameter(request, "width", securityConfigProperties.getImageCodePicWidth());
		int height = ServletRequestUtils.getIntParameter(request, "height", securityConfigProperties.getImageCodePicHeight());

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = bufferedImage.getGraphics();
		graphics.setColor(getRandColor(200, 250));
		graphics.fillRect(0, 0, width, height);
		graphics.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		graphics.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = RandomUtil.randomInt(width);
			int y = RandomUtil.randomInt(height);
			int xl = RandomUtil.randomInt(12);
			int yl = RandomUtil.randomInt(12);
			graphics.drawLine(x, y, x + xl, y + yl);
		}

		StringBuffer codeBuffer = new StringBuffer();
		for (int i = 0; i < securityConfigProperties.getValidateCodeLength(); i++) {
			String rand = String.valueOf(RandomUtil.randomInt(10));
			codeBuffer.append(rand);
			graphics.setColor(new Color(20 + RandomUtil.randomInt(110), 20 + RandomUtil.randomInt(110),
					20 + RandomUtil.randomInt(110)));
			graphics.drawString(rand, 13 * i + 6, 16);
		}

		graphics.dispose();

		return new ImageCode(bufferedImage, codeBuffer.toString(), securityConfigProperties.getExpireTime());
	}

	/**
	 * 生成随机背景条纹
	 *
	 * @param fc
	 * @param bc
	 * @return
	 */
	private Color getRandColor(int fc, int bc) {
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + RandomUtil.randomInt(bc - fc);
		int g = fc + RandomUtil.randomInt(bc - fc);
		int b = fc + RandomUtil.randomInt(bc - fc);
		return new Color(r, g, b);
	}

}
