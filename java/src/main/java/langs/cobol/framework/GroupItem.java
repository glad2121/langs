package langs.cobol.framework;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public abstract class GroupItem<T extends GroupItem<T>>
        extends DataItem<T> {

    Field[] declaredFields() {
        List<Field> list = new ArrayList<Field>();
        addDeclaredFields(list, getClass());
        return list.toArray(new Field[list.size()]);
    }

    void addDeclaredFields(List<Field> list, Class<?> clazz) {
        if (clazz == null || clazz == GroupItem.class) {
            return;
        }
        addDeclaredFields(list, clazz.getSuperclass());
        for (Field field : clazz.getDeclaredFields()) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod)) {
                continue;
            }
            list.add(field);
        }
    }

    Object fieldValue(Field field) {
        try {
            field.setAccessible(true);
            return field.get(this);
        } catch (IllegalAccessException e) {
            throw new CobolException(e);
        }
    }

    DataItem<?>[] dataItems() {
        List<DataItem<?>> list = new ArrayList<DataItem<?>>();
        for (Field field : declaredFields()) {
            Object value = fieldValue(field);
            if (value instanceof DataItem) {
                list.add((DataItem<?>) value);
            }
        }
        return list.toArray(new DataItem[list.size()]);
    }

    @Override
    public int byteLength() {
        int result = 0;
        for (DataItem<?> item : dataItems()) {
            result += item.byteLength();
        }
        return result;
    }

    @Override
    public int readFrom(InputStream in) {
        int count = 0;
        for (DataItem<?> item : dataItems()) {
            int n = item.readFrom(in);
            if (n > 0) {
                count += n;
            }
            if (n < item.byteLength()) {
                if (count <= 0) {
                    return -1;
                }
                break;
            }
        }
        return count;
    }

    @Override
    public void writeTo(OutputStream out) {
        for (DataItem<?> item : dataItems()) {
            item.writeTo(out);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Field field : declaredFields()) {
            builder.append(field.getName());
            builder.append('=');
            builder.append(fieldValue(field));
            builder.append(", ");
        }
        if (builder.length() > 0) {
            builder.setLength(builder.length() - 2);
        }
        return getClass().getSimpleName() + " {" + builder + '}';
    }

    @SuppressWarnings("unchecked")
    public T clone() {
        try {
            Class<T> clazz = (Class<T>) getClass();
            Constructor<T> constructor = clazz.getConstructor(clazz);
            return constructor.newInstance(this);
        } catch (NoSuchMethodException e) {
            throw new CobolException(e);
        } catch (InstantiationException e) {
            throw new CobolException(e);
        } catch (IllegalAccessException e) {
            throw new CobolException(e);
        } catch (InvocationTargetException e) {
            throw new CobolException(e.getCause());
        }
    }

}
