package at.fhv.teamc.jazzers;

import at.fhv.teamc.jazzers.application.api.DownloadService;
import at.fhv.teamc.jazzers.application.impl.DownloadServiceImpl;
import at.fhv.teamc.jazzers.infrastructure.HibernateDownloadRepository;

public class ServiceRegistry {
    private static DownloadService downloadService;
    private static HibernateDownloadRepository downloadRepository;

    public static DownloadService downloadService() {
        if (downloadService == null) {
            downloadService = new DownloadServiceImpl();
        }
        return downloadService;
    }

    public static HibernateDownloadRepository downloadRepository() {
        if (downloadRepository == null) {
            downloadRepository = new HibernateDownloadRepository();
        }
        return downloadRepository;
    }
}
