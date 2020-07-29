package com.sickcat.common.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/07/29 2:35 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel 7778
 * @desc
 */

public class ShareReflectUtil {

    /**
     * 从 instance 到其父类 找 name 属性
     *
     * @param instance
     * @param name
     * @return
     * @throws NoSuchFieldException
     */
    public static Field findField(Object instance, String name) throws NoSuchFieldException {

        for (Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                Field field = clazz.getDeclaredField(name);

                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                return field;
            } catch (NoSuchFieldException e) {

            }
        }
        throw new NoSuchFieldException("Field" + name + " not found in " + instance.getClass());
    }

    /**
     *  从 instance 到其父类 找 name 方法
     *
     * @param instance
     * @param name
     * @param parameterTypes
     * @return
     */
    public static Method findMethod(Object instance, String name, Class<?>... parameterTypes)
            throws NoSuchMethodException {

        for (Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {

            try {
                Method method = clazz.getDeclaredMethod(name, parameterTypes);
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }

                return method;
            } catch (NoSuchMethodException e) {

            }
        }

        throw new NoSuchMethodException("Method"
                + name
                + " with parameters"
                + Arrays.asList(parameterTypes)
                + " not found in "
                + instance.getClass());
    }


    public static void expandFieldArray(Object instance, String fieldName, Object[] patchElements)
        throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{

        //拿到 classloader 中的dexelements 数组
        Field dexelementsField = findField(instance, fieldName);

        //old Elements[]
        Object[] dexElements = (Object[]) dexelementsField.get(instance);

        //合并后的数组
        Object[] newElements = (Object[]) Array.newInstance(dexElements.getClass().getComponentType(), dexElements.length + patchElements.length );

        // 先拷贝新数组 将patch数组 插入到newElements的前面
        System.arraycopy(patchElements, 0, newElements, 0, patchElements.length);
        System.arraycopy(dexElements, 0, newElements, patchElements.length, dexElements.length);

        //修改 ClassLoader中 pathList中的 dexelements
        dexelementsField.set(instance, newElements);

    }


}
