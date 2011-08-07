package org.albianj.service.parser;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.albianj.service.IAlbianService;

public interface IServiceParser extends IAlbianService {

	public void init() throws MalformedURLException, URISyntaxException, Exception;
}

