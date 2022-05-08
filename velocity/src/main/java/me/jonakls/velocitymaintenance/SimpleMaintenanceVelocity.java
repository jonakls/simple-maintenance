package me.jonakls.velocitymaintenance;


import com.google.inject.Inject;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

@Plugin(
        id = "SimpleMaintenance",
        name = "SimpleMaintenance",
        version = "%{version}",
        authors = "Jonakls"
)
public class SimpleMaintenanceVelocity {

    private ProxyServer proxyServer;
    private final Logger logger;

    @Inject
    public SimpleMaintenanceVelocity(ProxyServer proxyServer, Logger logger) {
        this.proxyServer = proxyServer;
        this.logger = logger;

        logger.info("Loading SimpleMaintenance");
    }
}
