package com.xinda.util;

import java.awt.Color;

import com.github.bingoohuang.patchca.background.SingleColorBackgroundFactory;
import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.service.AbstractCaptchaService;
import com.github.bingoohuang.patchca.text.renderer.BestFitTextRenderer;
import com.github.bingoohuang.patchca.word.RandomWordFactory;

public class DefaultPatchcaService extends AbstractCaptchaService {
	
	public DefaultPatchcaService(){
		init();
	}
	private void init(){
		setWordFactory();
		setFontFactory();
		//设置字体颜色
		this.colorFactory = new SingleColorFactory(new Color(25, 60, 170));
		//文本效果
		this.textRenderer = new BestFitTextRenderer();
		//背景
		this.backgroundFactory = new SingleColorBackgroundFactory();
		//设置干扰线
		this.filterFactory = new CurvesRippleFilterFactory(colorFactory);
		//宽
		width = 60;
		//高
	    height = 22; 
	}
	//设置文本内容、长度
	private void setWordFactory(){
		RandomWordFactory rwf = new RandomWordFactory();
		rwf.setCharacters("absdegkmnpwx23456789");
		rwf.setMaxLength(4);
		rwf.setMinLength(4);
		this.wordFactory = rwf;
	}
	//设置字体大小
	private void setFontFactory(){
		RandomFontFactory rff = new RandomFontFactory();
		rff.setMaxSize(20);
		rff.setMinSize(20);
		this.fontFactory = rff;
	}
}
