/**
 * 
 */
package com.qdynasty.security.validate.impl.imagecode;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.qdynasty.security.validate.entity.ImageCode;
import com.qdynasty.security.validate.entity.ValidateCode;
import com.qdynasty.security.validate.impl.AbstractValidateCodeProcessor;

/**
 * @author fei.qin
 *
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor {

	@Override
	protected void sendValidateCode(HttpServletResponse response, ValidateCode validateCode) throws Exception {
		ImageCode imageCode = (ImageCode) validateCode;

		ImageIO.write(imageCode.getBufferedImage(), "JPEG", response.getOutputStream());
	}
}
