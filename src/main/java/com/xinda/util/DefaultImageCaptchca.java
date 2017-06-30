package com.xinda.util;

import java.io.OutputStream;

import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;

public class DefaultImageCaptchca implements ImageCaptchca {
	//图像格式，默认：png
	protected String imagetype = "png";
	//Patchca默认服务类,可以通过它更改默认的图像样式
	protected DefaultPatchcaService dps = new DefaultPatchcaService();
	
	@Override
	public String generate_captchca(OutputStream os) throws Exception {
		return EncoderHelper.getChallangeAndWriteImage(dps, imagetype, os);
	}

	@Override
	public void setImageType(String type) {
		this.imagetype = type;
	}

	public void setDps(DefaultPatchcaService dps) {
		this.dps = dps;
	}
}
