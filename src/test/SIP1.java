package test;

import java.io.ObjectStreamException;

/**
 * Name: SIP1
 * Author: lloydfinch
 * Function: SIP1
 * Date: 2020-09-11 15:32
 * Modify: lloydfinch 2020-09-11 15:32
 */
public class SIP1 {
    private static SIP1 instance;

    /**
     * 防止被序列化创建
     * @throws ObjectStreamException
     */
    private Object readResolve() throws ObjectStreamException {
        return instance;
    }
}
