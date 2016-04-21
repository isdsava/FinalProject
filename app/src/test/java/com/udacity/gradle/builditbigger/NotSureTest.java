package com.udacity.gradle.builditbigger;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NotSureTest{
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}
/**
import com.google.android.gms.drive.query.Query;
import com.google.appengine.tools.development.DevAppServer;
import com.google.appengine.tools.development.DevAppServerFactory;
import com.google.appengine.tools.development.testing.DevAppServerTest;
import com.google.appengine.tools.development.testing.DevAppServerTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.DevAppServerTestRunner;
import com.google.appengine.tools.development.testing.De
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalURLFetchServiceTestConfig;
import com.google.appengine.tools.info.SdkInfo;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.w3c.dom.Entity;

import java.io.File;
import java.net.URL;
import java.security.KeyFactory;
import java.util.List;


public class NotSureTest extends BlockJUnit4ClassRunner {

        public NotSureTest(Class<?> klass) throws InitializationError {
            super(startServerAndIsolateClass(klass));
        }

        private static Class<?> startServerAndIsolateClass(Class<?> klass) throws InitializationError {
            DevAppServerTest testAnno = klass.getAnnotation(DevAppServerTest.class);
            if (testAnno == null) {
                throw new InitializationError(String.format(
                        "Test uses %s but is not also annotated with %s.",
                        DevAppServerTestRunner.class.getSimpleName(), DevAppServerTest.class.getSimpleName()));
            }
            try {
                DevAppServerTestConfig config = testAnno.value().newInstance();
                DevAppServerTestHelper devAppServerTestHelper = new DevAppServerTestHelper();
                DevAppServer devServer = (config);
                return devServer.getAppContext().getClassLoader().loadClass(klass.getName());
            } catch (InstantiationException e) {
                throw new InitializationError(e);
            } catch (IllegalAccessException e) {
                throw new InitializationError(e);
            } catch (ClassNotFoundException e) {
                throw new InitializationError(e);
            }
        }

        @Override
        protected void collectInitializationErrors(List<Throwable> errors) {
            try {

                super.collectInitializationErrors(errors);
            } catch (InitializationError initializationError) {
                errors.add(initializationError);
            }
        }



   public class DevAppServerTestHelper {

        private static final String SDK_ROOT_PROP = "appengine.sdk.root";

         DevAppServer server;
         String originalSdkRoot;
         boolean started = false;

        /**
         * Run the app in the dev appserver with the provided configuration.  All
         * classes required by the application and the test must be available on the
         * provided classpath.  This method ignores wars.
         *
         * @param testConfig the config
         * @return the dv appserver wer started
         *
         * @throws IllegalStateException If a dev appserver started by this class is
         * already running.

        public  DevAppServer startServer(DevAppServerTestConfig testConfig) {
            if (started) {
                throw new IllegalStateException("Dev Appserver is already running.");
            }
            originalSdkRoot = System.getProperty(SDK_ROOT_PROP);
            System.setProperty(SDK_ROOT_PROP, testConfig.getSdkRoot().getAbsolutePath());

            String address = "127.0.0.1";
            SdkInfo.includeTestingJarOnSharedPath(true);
            server = new DevAppServerFactory().createDevAppServer(testConfig.getAppDir(),
                    testConfig.getWebXml(), testConfig.getAppEngineWebXml(), address, 0, true,
                    testConfig.installSecurityManager(), testConfig.getClasspath());
            try {
                server.start();
                System.setProperty(testConfig.getPortSystemProperty(), Integer.toString(server.getPort()));
                started = true;
                return server;
            } catch (Exception e) {
                if (e instanceof RuntimeException) {
                    throw (RuntimeException) e;
                }
                throw new RuntimeException(e);
            } finally {
                if (!started) {
                    server = null;
                    SdkInfo.includeTestingJarOnSharedPath(false);
                }
            }
        }

        /**
         * Shut down the dev appserver.

        public void stopServer() {
            SdkInfo.includeTestingJarOnSharedPath(false);
            if (server != null) {
                try {
                    server.shutdown();
                    server = null;
                } catch (Exception e) {
                    if (e instanceof RuntimeException) {
                        throw (RuntimeException) e;
                    }
                    throw new RuntimeException(e);
                }
            }
            if (originalSdkRoot == null) {
                System.clearProperty(SDK_ROOT_PROP);
            } else {
                System.setProperty(SDK_ROOT_PROP, originalSdkRoot);
            }
        }
    }
    }*/