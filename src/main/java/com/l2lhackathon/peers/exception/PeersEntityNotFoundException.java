package com.l2lhackathon.peers.exception;

public class PeersEntityNotFoundException extends PeersCoreException {

    private static final int NOT_FOUND = 404;

    public PeersEntityNotFoundException(String entityClassName) {
        super("Entity not found: %s".formatted(entityClassName), NOT_FOUND);
    }

    public PeersEntityNotFoundException(String entityClassName, Object entityId) {
        super("Entity not found: %s[%s]".formatted(entityClassName, entityId.toString()), NOT_FOUND);
    }

    public PeersEntityNotFoundException(Class<?> entityClazz, Object entityId) {
        this(entityClazz.getSimpleName(), entityId);
    }

    public PeersEntityNotFoundException(Class<?> entityClazz) {
        this(entityClazz.getSimpleName());
    }
}
