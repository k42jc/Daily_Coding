package com.lxd.practice.jvm.class_file;

/**
 * <b>用于展示编译后生成的class文件结构</b>
 *
 * 生成的class文件的16进制前8个字节如下(一个十六进制数占4位，一个字节占8位，所以两个十六进制数占一个字节)：
 * <pre>
 *     cafe babe 0000 0034
 * </pre>
 * <i>前4个字节为<strong>魔数</strong></i>--用于确定当前文件是否可被虚拟机接收的唯一标识：0xcafe babe
 * <i>紧接着的4个字节表示class文件的版本信息</i>：0x0000 0034,5、6字节表示次版本号，7、8字节表示住版本号，0x0032=50=>jdk1.6依次增长，本文件34表示jdk1.8
 * <br><br>
 * <strong>虚拟机只能接受比自己版本低(包括自身版本)的class文件，否则拒绝执行</strong>
 * <br><br>
 * Created by liaoxudong on 2017/7/24.
 */
public class TestClass {

    private int m;

    public int inc(){
        return m+1;
    }
}
