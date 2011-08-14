package org.albianj.persistence.impl.persistence;

import org.albianj.persistence.impl.storage.StorageParser;
import org.albianj.service.FreeAlbianService;
import org.albianj.service.IAlbianService;
import org.albianj.xml.IParser;

public class ParserService extends FreeAlbianService implements IAlbianService
{
	public void loading() throws RuntimeException
	{
		IParser storageParser = new StorageParser();
		storageParser.init();
		IParser persistenceParser = new PersistenceParser();
		persistenceParser.init();
	}
}
