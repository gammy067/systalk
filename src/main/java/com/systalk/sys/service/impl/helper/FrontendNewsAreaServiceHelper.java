package com.systalk.sys.service.impl.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrontendNewsAreaServiceHelper {
	private Logger logger = LoggerFactory.getLogger(getClass());


	private FrontendNewsAreaServiceHelper() {
	}

	private static class Loader {
		private static final FrontendNewsAreaServiceHelper instance = new FrontendNewsAreaServiceHelper();
	}

	public static FrontendNewsAreaServiceHelper getInstance() {
		return Loader.instance;
	}
}
