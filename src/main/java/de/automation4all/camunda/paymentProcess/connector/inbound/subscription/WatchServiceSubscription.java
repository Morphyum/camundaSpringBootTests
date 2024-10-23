package de.automation4all.camunda.paymentProcess.connector.inbound.subscription;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WatchServiceSubscription {

  private final static Logger LOG = LoggerFactory.getLogger(WatchServiceSubscription.class);


  public WatchServiceSubscription(String eventToMonitor, String directory, String pollingInterval, Consumer<WatchServiceSubscriptionEvent> callback) {
    LOG.info("Activating WatcherService subscription");
    // listen to directory
    try {
      WatchService watchService = FileSystems.getDefault().newWatchService();
      Path path = Paths.get(directory);

      WatchKey watchKey = path.register(watchService, new WatchEvent.Kind[]{StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY});

      boolean isWatchKeyValid = true;

      while (isWatchKeyValid) {
        watchKey = watchService.poll(Long.parseLong(pollingInterval), TimeUnit.SECONDS);
        if(watchKey != null) {
          for (WatchEvent<?> event : watchKey.pollEvents()) {
            LOG.info("Event kind : " + event.kind() + " - File : " + event.context() + " event to monitor: "+ eventToMonitor);
            if(event.kind().toString().equals(eventToMonitor)) {
              WatchServiceSubscriptionEvent wsse = new WatchServiceSubscriptionEvent(eventToMonitor, directory, event.context().toString());
              callback.accept(wsse);
            }
          }
          watchKey.reset();

        } else {
          LOG.info("No files during interval");
        }
      }

    } catch (Exception e) {
      LOG.error("Problem with connector "+e);
    }
  }

  public void stop() {
    LOG.info("Deactivating file watcher service");
  }

}