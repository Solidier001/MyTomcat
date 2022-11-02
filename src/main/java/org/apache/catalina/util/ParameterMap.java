package org.apache.catalina.util;

import java.util.HashMap;
import java.util.Map;

public final class ParameterMap extends HashMap {
    private static final StringManager sm=StringManager.getInstance("org.apache.catalina.util");
    private boolean locked=false;

    public ParameterMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ParameterMap(int initialCapacity) {
        super(initialCapacity);
    }

    public ParameterMap() {
        super();
    }

    public ParameterMap(Map m) {
        super(m);
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public Object put(Object key, Object value) {
        if (locked)
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        return super.put(key, value);
    }

    @Override
    public void putAll(Map m) {
        if (locked)
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        super.putAll(m);
    }

    @Override
    public void clear() {
        if (locked)
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        super.clear();
    }

    @Override
    public boolean remove(Object key, Object value) {
        if (locked)
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        return super.remove(key, value);
    }
}
