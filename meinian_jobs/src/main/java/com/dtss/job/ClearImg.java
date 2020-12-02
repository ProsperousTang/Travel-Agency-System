package com.dtss.job;


import com.dtss.constant.RedisConstant;
import com.dtss.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.Set;

public class ClearImg {
    @Autowired
    JedisPool jedisPool;

    /**
     * jedisPool.getResource().sdiff(param1_BIG,param2_SMALL)两个参数大的写在前面；
     * 还要记得开启tomcat服务器
     */
    public void clearImg(){
        Set<String> sdiff = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES );
        Iterator<String> iterator = sdiff.iterator();
        while (iterator.hasNext()){
            String delImg = iterator.next();
            System.out.println("delete the picture: "+delImg);
            jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,delImg);
            QiniuUtils.deleteFileFromQiniu(delImg);
        }

    }
}
