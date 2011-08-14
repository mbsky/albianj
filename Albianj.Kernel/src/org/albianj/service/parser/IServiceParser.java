package org.albianj.service.parser;

import org.albianj.service.IAlbianService;
import org.albianj.xml.IParser;

public interface IServiceParser extends IParser, IAlbianService {

	public void init();
}

