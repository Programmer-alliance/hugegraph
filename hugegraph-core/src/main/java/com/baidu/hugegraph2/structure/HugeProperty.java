package com.baidu.hugegraph2.structure;

import java.util.NoSuchElementException;

import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Property;

import com.baidu.hugegraph2.type.HugeTypes;
import com.baidu.hugegraph2.type.schema.PropertyKey;
import com.google.common.base.Preconditions;

/**
 * Created by jishilei on 17/3/16.
 */
public class HugeProperty<V> implements Property<V>, GraphType {

    protected final HugeElement owner;
    protected final PropertyKey key;
    protected final V value;

    public HugeProperty(final HugeElement owner, final PropertyKey key, final V value) {
        Preconditions.checkArgument(owner != null, "Property owner can't be null");
        Preconditions.checkArgument(key != null, "Property key can't be null");

        this.owner = owner;
        this.key = key;
        this.value = value;

        Preconditions.checkArgument(key.checkValue(value), String.format(
                "Invalid property value '%s' for key '%s'", value, key.name()));
    }

    public PropertyKey propertyKey() {
        return this.key;
    }

    @Override
    public HugeTypes type() {
        return HugeTypes.PROPERTY;
    }

    @Override
    public String name() {
        return this.key.name();
    }

    @Override
    public String key() {
        return this.key.name();
    }

    @Override
    public V value() throws NoSuchElementException {
        return this.value;
    }

    @Override
    public boolean isPresent() {
        return null != this.value;
    }

    @Override
    public Element element() {
        return this.owner;
    }

    @Override
    public void remove() {
        throw Property.Exceptions.propertyRemovalNotSupported();
    }

    @Override
    public String toString() {
        return String.format("{type=%s, value=%s}", this.key, this.value);
    }
}