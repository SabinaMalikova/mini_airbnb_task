package peaksoft;

import peaksoft.config.HibernateConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        HibernateConfig.getEntityManagerFactory();
    }
}
