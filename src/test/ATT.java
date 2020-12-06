package test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Name: ATT
 * Author: lloydfinch
 * Function: ATT
 * Date: 2020-09-08 09:44
 * Modify: lloydfinch 2020-09-08 09:44
 */
public class ATT {

    public static void main(String[] args) {
        Call<User> call = new Call<>() {
        };

        Type type = call.getClass().getGenericSuperclass();
        System.out.println(type);
        if (type instanceof ParameterizedType) {
            ParameterizedType type1 = (ParameterizedType) type;
            Type[] actualTypeArguments = type1.getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println(actualTypeArgument);
            }
        }
    }

    public static class User {

    }

    public static class Call<T> {
        public void fuck() {
            System.out.println("fuck in Call");
        }
    }
}
