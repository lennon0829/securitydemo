/**
 * 
 */
package com.qdynasty.security.validate.entity;

import java.awt.image.BufferedImage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fei.qin
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ImageCode extends ValidateCode {

	private BufferedImage bufferedImage;

	public ImageCode(BufferedImage bufferedImage, String code, int expireTime) {
		super(code, expireTime);
		this.bufferedImage = bufferedImage;
	}
}
